package com.parcel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.parcel.service.UserService;

/**
 * 세션이 필요한 페이지에 대한 요청을 처리하기전
 * 세션정보가 있는지 미리 확인하는 인터셉터
 * @author Developer
 */
@Component
public class ControllerSessionInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 세션이 있으면 요청 처리를 진행하고
	 * 없으면 로그인 페이지로 돌아가도록 하는 메서드
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("interceptor access");
		try {
			//세션에 idx 없으면 index로
			if(request.getSession() == null || request.getSession().getAttribute("idx") == null ){
					System.out.println("interceptor : no session OR no idx");
					response.sendRedirect("/");	
					return false;
			}
			System.out.println("##session id " + request.getSession().getId());
			int idx = (int)request.getSession().getAttribute("idx");
			System.out.println("idx = " + idx);
			System.out.println("user servicel" + userService);
			System.out.println("userEntity = " + userService.getMainPageEntityForUserInfo(idx));
			
			
			request.getSession().setAttribute("userEntity", userService.getMainPageEntityForUserInfo(idx));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//세션에idx 있으면 원래대로
		
		
		return true;
	}
}
