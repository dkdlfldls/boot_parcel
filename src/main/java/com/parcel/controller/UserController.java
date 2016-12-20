package com.parcel.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parcel.entity.User;
import com.parcel.service.UserService;
import com.parcel.util.IpGather;

/**
 * 사용자 관련 Controller
 * @author user
 */
@Controller
public class UserController{
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final int INT_NULL = -1;
	private final String ADMIN = "ROLE_ADMIN";

	
	
	@Autowired
	private UserService userService;
	
	/**
	 * 사용자 가입 요청 처리
	 * @param user 가입될 사용자 정보
	 * @param result
	 * @return
	 */
	@RequestMapping("/userJoin")
	public String joinAndValidate(@Valid User user, BindingResult result) {
		
		userService.join(user);
		return "redirect:/";
	}
	
	/**
	 * 사용자 로그인 요청 처리
	 * @param session
	 * @param user 로그인한 사용자 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/userLogin", method=RequestMethod.POST)
	public String loginAndValidate(HttpSession session, @Valid User user, BindingResult result) {
		int idx;
		User tempUser = userService.login(user);
		if (tempUser == null) {
			System.out.println("no user");
			return "redirect:/";
		} else {
			System.out.println("after login session ID : " + session.getId());
			session.setAttribute("idx", tempUser.getIdx());
			
			if (tempUser.getWeb_authority().equals(ADMIN)) { //admin인경우
				System.out.println("go admin log");
				return "redirect:/admin/log";
			} else {
				System.out.println("go parcel main");
				return "redirect:/parcel/main";
			}
			
		}
		
	}
	
	/**
	 * 로그아웃 요청 처리
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	/**
	 * 사용자 정보 수정 페이지 요청 처리
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user/infoModify", method=RequestMethod.GET)
	public String getUserInfoModifyPage(HttpSession session, Map<String, Object> model) {
		int idx = (int)session.getAttribute("idx");
		User tempUser = userService.getUser(idx);
		tempUser.setPw("");
		model.put("user", tempUser);
		
		return "/user/userInfoModify";
	}
	
	/**
	 * 사용자 정보 수정 요청 처리
	 * @param session
	 * @param user 수정될 사용자 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/user/infoModify", method=RequestMethod.POST)
	public String modifyUserInfoAndValidate(HttpSession session, @Valid User user, BindingResult result) {
		System.out.println("@@@ : " + user.toString());
		User tempUser = userService.getUser((int)session.getAttribute("idx"));
		tempUser.setDataForModify(user);
		userService.modifyUserInfo(tempUser);
		
		return "redirect:/user/infoModify";
	}

	/**
	 * 다운로드 테스트 요청처리
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/w3")
	public @ResponseBody File w3down(HttpServletResponse response) throws Exception {
		
		String fileName = "E:\\games\\zip\\1.png";
	    File file = new File(fileName);
	    

	    response.setHeader("Content-Length", Long.toString(file.length()));
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Content-Disposition", "attachment;fileName=\"" +  fileName + "\";");
	    
	    return file;

	}
	
	/**
	 * 테스트 아두이노 접속 요청 처리
	 * @param req
	 * @return
	 */
	@RequestMapping("/test/arduino")
	public @ResponseBody String test(HttpServletRequest req) {
		IpGather ip = new IpGather();
		System.out.println("receive request success : " + ip.ip(req));
		return "test";
	}
}
