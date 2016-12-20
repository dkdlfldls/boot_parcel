package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.parcel.service.UserService;

/**
 * 메인 페이지 및 기본 페이지 Controller
 * @author user
 */
@Controller
public class MainPageController {
	
	private Logger logger = LoggerFactory.getLogger(MainPageController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 인덱스 페이지 요청 처리
	 * @param session
	 * @return
	 */
	@RequestMapping("/")
	public String getIndexPage(HttpSession session) {
		
		
		if (session.getAttribute("idx") != null) {
			return "redirect:/parcel/main";
		} else {
			
			return "index";
		}
		
	}
	
	/**
	 * 메인 페이지 요청 처리
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/parcel/main")
	public String getMainPage(HttpSession session, Model model) {
		System.out.println("parcel main : session id = " + session.getId());
		int idx = (int) session.getAttribute("idx");
		model.addAttribute("listEntity", userService.getMainPageEntityList(idx));
		return "/main/main";
		
	}
	
	/**
	 * 가입 페이지 요청 처리
	 * @return
	 */
	@RequestMapping("/join")
	public String getJoinPage() {
		return "/user/join";
	}
	
	/**
	 * 제품소개 페이지 요청 처리
	 * @return
	 */
	@RequestMapping("/intro/main")
	public String getIntroPage() {
		return "/intro/introMain";
	}

}
