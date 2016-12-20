package com.parcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Util 기능 관련 Controller
 * @author user
 */
@Controller
public class UtilController{
	
	/**
	 * 실패 반환
	 * @return
	 */
	@RequestMapping(value="/util/false", method=RequestMethod.GET)
	public @ResponseBody boolean sendFalse() {
		return false;
	}
	/**
	 * 성공 반환
	 * @return
	 */
	@RequestMapping(value="/util/true", method=RequestMethod.GET)
	public @ResponseBody boolean sendTrue() {
		return true;
	}
}
