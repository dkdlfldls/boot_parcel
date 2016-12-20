package com.parcel.administrator.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.parcel.entity.LogVO;
import com.parcel.service.AdminService;
import com.parcel.util.Page;

/**
 * 관리자의 로그 확인 Controller
 *
 */
@Controller
public class AdminLogController {
	
	@Autowired
	private AdminService adminSerivce;
	
	/**
	 * 로그 보는 페이지 요청 처리
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/log")
	public String getAdminLogPage(HttpSession session, Model model) {
		return "admin/logManager";
	}
	
	/**
	 * 로그 데이터 요청 처리
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/admin/log", method=RequestMethod.POST)
	public @ResponseBody LogVO  sendLogList(@RequestBody Page page) {
		LogVO vo = new LogVO();
		vo.setList(adminSerivce.getLogList(page));
		vo.setPage(page);
		
		return vo;
	}
	
	/**
	 * 로그 데이터 검색 요청 처리
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/admin/searchLog", method=RequestMethod.POST)
	public @ResponseBody LogVO  sendSearchResultOfLogList(@RequestBody Page page) {
		LogVO vo = new LogVO();
		vo.setList(adminSerivce.getSearchResultOfLogList(page));
		vo.setPage(page);
		
		return vo;
	}
	

}
