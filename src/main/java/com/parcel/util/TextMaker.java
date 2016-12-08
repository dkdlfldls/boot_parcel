package com.parcel.util;

import org.springframework.stereotype.Component;

import com.parcel.entity.User_group;

@Component
public class TextMaker {

	public String join(String id) {
		return id + "님이 회원가입 하였습니다";
	}
	
	public String login(int idx, String id) {
		return "idx : " + idx + " / id : " + id + "님이 로그인 하였습니다.";
	}
	
	public String addGroup(int product, int userIdx, String groupName) {
		return "product : " + product + " / user idx : " + userIdx + " / group name : " + groupName + " 그룹이 만들어 졌습니다";
	}
	
	public String joinGroup(int userIdx, int product, int groupIdx) {
		return userIdx + "번 사용자가 product : " + product + " / group idx : " + groupIdx + "에 가입하였습니다.";
	}
	
	public String deleteGroup(int groupIdx) {
		return groupIdx + "번 그룹이 제거되었습니다.";
	}
	
	public String dropGroup(int userIdx, int groupIdx) {
		return groupIdx + "번 그룹에서 " + userIdx + "번 사용자가 탈퇴하였습니다.";
	}

	public String checkMessage(int userIdx, int messageIdx) {
		return userIdx + "번 님이 " + messageIdx + "번 메시지를 확인하였습니다.";
	}
	
	public String registerProduct(int userIdx, int productIdx) {
		return userIdx + "번 님이 " + productIdx + "번 제품을 등록하였습니다.";
	}
	
	public String lockProduct(int userIdx, int productIdx) {
		return userIdx + "번 님이 " + productIdx + "번 제품을 잠궜습니다.";
	}
	
	public String unlockProduct(int userIdx, int productIdx) {
		return userIdx + "번 님이 " + productIdx + "번 제품을 열었습니다.";
	}

	public String inviteUserById(int idx, String id) {
		
		return idx + "번 님이 id=" + id +" 님을 초대하였습니다.";
	}
	public String inviteUserByEmail(int idx, String email) {
		
		return idx + "번 님이 email=" + email +" 님을 초대하였습니다.";
	}
	
	public String inviteEmailContent(User_group group) {
		//로컬변수니까 StringBuilder로 한다.
		StringBuilder sb = new StringBuilder();
		sb.append("group code : " + group.getCode()).append("<br>");
		sb.append("group password : " + group.getPw()).append("<br>");
		return sb.toString();
	}
}
