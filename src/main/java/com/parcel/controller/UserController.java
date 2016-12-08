package com.parcel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcel.entity.User;
import com.parcel.service.UserService;

@Controller
public class UserController{
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final int INT_NULL = -1;
	private final String ADMIN = "ROLE_ADMIN";

	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/userJoin")
	public String joinAndValidate(@Valid User user, BindingResult result) {
		
		userService.join(user);
		return "redirect:/";
	}
	
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
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/user/infoModify", method=RequestMethod.GET)
	public String getUserInfoModifyPage(HttpSession session, Map<String, Object> model) {
		int idx = (int)session.getAttribute("idx");
		User tempUser = userService.getUser(idx);
		tempUser.setPw("");
		model.put("user", tempUser);
		
		return "/user/userInfoModify";
	}
	
	@RequestMapping(value="/user/infoModify", method=RequestMethod.POST)
	public String modifyUserInfoAndValidate(HttpSession session, @Valid User user, BindingResult result) {
		System.out.println("@@@ : " + user.toString());
		User tempUser = userService.getUser((int)session.getAttribute("idx"));
		tempUser.setDataForModify(user);
		userService.modifyUserInfo(tempUser);
		
		return "redirect:/user/infoModify";
	}

	@RequestMapping("/w3")
	public @ResponseBody File w3down(HttpServletResponse response) throws Exception {
		
		String fileName = "E:\\games\\zip\\1.png";
	    File file = new File(fileName);
	    

	    response.setHeader("Content-Length", Long.toString(file.length()));
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Content-Disposition", "attachment;fileName=\"" +  fileName + "\";");
	    
	    return file;

	}
}
