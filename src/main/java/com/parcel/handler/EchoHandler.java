package com.parcel.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.parcel.entity.ChattingVO;
import com.parcel.entity.Message;

@Component
public class EchoHandler extends TextWebSocketHandler {
	
	private Logger logger = LoggerFactory.getLogger(TextWebSocketHandler.class);
	
	private Map<String, ArrayList<Socket>> groupChattingMap;
	private List<Socket> errorList;
	
	private final String CONNECTION = "1";
	private final String MESSAGE = "2";
	private final String BREAK_CONNECTION = "3";
	
	public EchoHandler() {
		groupChattingMap = new HashMap<String, ArrayList<Socket>>();
		errorList = new ArrayList<Socket>();
	}
	
	class Socket implements Comparable<WebSocketSession> {
		private WebSocketSession session;
		private int idx;
		
		public Socket(WebSocketSession session, int idx) {
			this.session = session;
			this.idx = idx;
		}

		@Override
		public int compareTo(WebSocketSession o) {
			// TODO Auto-generated method stub
			if (this.session == o) {
				return 0;
			} else {
				return -1;
			}
		}
	}
	
	
	/**
	 * 접속관련 이벤트 메서드
	 * @param WebSocketSession 접속한 사용자
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session){
		logger.info(session.getId() + "님 접속 시도");
		logger.info("연결  IP : " + session.getRemoteAddress().getHostString());
		//이 부분에서 도저히 사용자를 구별할 수 없어서
	}
	
	
	/**
	 * 2가지 이벤트 처리
	 * 1. send : 클라이언트가 서버로 메시지 보냄
	 * 2. emit : 서버에 연결되어 있는 클라이언트들에게 메시지를 보냄
	 * @param WebSocketSession 메시지를 보낸 클라이언트
	 * @param TextMessage 메시지의 내용
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		logger.info(session.getId() + "님이 메시지 전송 : " + message.getPayload());
		
		ChattingVO messageVo = ChattingVO.convertMessage(message.getPayload());
		try {
			if (messageVo.getType().equals(CONNECTION)) { //접속
				connect(messageVo, session);
				sendMessage(messageVo, session); //접속 했다고 메시지보낸다.	
				
			} else if (messageVo.getType().equals(BREAK_CONNECTION)) { //나감
				breakConnection(messageVo, session);
				sendMessage(messageVo, session); //접속 해제 했다고 메시지 보낸다.
				
			} else if (messageVo.getType().equals(MESSAGE)){
				//메시지 전송
				sendMessage(messageVo, session);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 클라이언트가 서버와 연결종료 
	 * @param WebSocketSession 연결을 끊은 클라이언트
	 * @param CloseStatus 연결 상태(확인 필요)
	 */ 
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
		logger.info(session.getId() + "님 접속종료");
		logger.info(status.getReason() + " " + status.getCode() + " " + status.toString());
	}
	
	
	public void connect(ChattingVO messageVo, WebSocketSession session) {
		logger.info("그룹 채팅 접속 요청");
		//tempConnectionList 에 있으면 꺼내서 맵으로 그룹 맞춰서
		
		if (groupChattingMap.containsKey(messageVo.getGroup())) {//해당 그룹의 리스트가 있는가? 
			//자기 자신한테 원래 있던 세션들의 정보를 보내야 한다.
			for(Socket s : groupChattingMap.get(messageVo.getGroup())) {
				try {
					session.sendMessage(new TextMessage(makeMessage(new ChattingVO("1", s.idx))));					
				} catch (Exception e) {
					logger.debug(e.getMessage());
				}
			}
			//자신의 정보 추가
			groupChattingMap.get(messageVo.getGroup()).add(new Socket(session, messageVo.getUserIdx())); //있으면 리스트에 세션 추가
		} else { //없으면 리스트를 만들고 세션추가한뒤 맵에 추가
			ArrayList<Socket> list = new ArrayList<Socket>(); 
			list.add(new Socket(session, messageVo.getUserIdx()));
			groupChattingMap.put(messageVo.getGroup(), list);
		}
		
		//늦게 로그인 한 사람은 이미 로그인해 있는 사람을 볼 수 없다.
		
	}
	
	public void breakConnection(ChattingVO messageVo, WebSocketSession session) {
		logger.info("그룹 채팅 나가기 요청");
		if (groupChattingMap.containsKey(messageVo.getGroup())) { //map에 해당 그룹의 리스트가 있음
			if (groupChattingMap.get(messageVo.getGroup()).contains(session)) { //리스트에 사용자가 있음
				groupChattingMap.get(messageVo.getGroup()).remove(session); //리스트에서 사용자 삭제
				
				if (groupChattingMap.get(messageVo.getGroup()).size() == 0) {//리스트에서 사용자가 전부 나가면 삭제
					logger.info("사용자가 없어 리스트 삭제함");
					groupChattingMap.remove(messageVo.getGroup());
				}
			}
		} 
	}
	
	public void sendMessage(ChattingVO messageVo, WebSocketSession session) {
		if (groupChattingMap.containsKey(messageVo.getGroup())) {
			logger.info("그룹 채팅 메시지 전송");
			String text = makeMessage(messageVo);
			for (Socket user : groupChattingMap.get(messageVo.getGroup())) {
			
				try { //유저가 그냥 창을 꺼버리거나 리프레쉬 해버린경우 빈 session이 남는데 해당 세션떄문에 에러나면 제거하고 동작하게함
					user.session.sendMessage(new TextMessage(text));								
				} catch (Exception e) {
					logger.debug(e.getMessage());
					//바로 삭제하면 안되고 리스트에 담아뒀다가 나중에 삭제한다.
					//groupChattingMap.get(messageVo.getGroup()).remove(user);
					errorList.add(user);
					
					//나갔다고 가르쳐 준다. (전체에게 가르쳐 줄 수 있지만 우선은 당사자에게만)
					try {
						session.sendMessage(new TextMessage(makeMessage(new ChattingVO("3", user.idx))));
					} catch (IOException e1) {
						logger.debug(e1.getMessage());
					}	
				}
			
			}
		}
		for (Socket errorSession : errorList) {
			try {
				groupChattingMap.get(messageVo.getGroup()).remove(errorSession);						
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		errorList.clear();
	}
	
	
	public String makeMessage(ChattingVO messageVo) {
		if (messageVo.getType().equals(MESSAGE)) {
			messageVo.setMessage(messageVo.getName() + " -> " + messageVo.getMessage());
			return ChattingVO.gson.toJson(messageVo);
			
		} else {
			return ChattingVO.gson.toJson(messageVo);
		}
		
	}
	
}
