package com.parcel.administrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.parcel.entity.LogVO;
import com.parcel.service.AdminService;
import com.parcel.util.Page;

@Controller
public class AdminLogController {
	
	@Autowired
	private AdminService adminSerivce;
	
	@RequestMapping(value="/admin/log", method=RequestMethod.POST)
	public @ResponseBody LogVO  sendLogList(@RequestBody Page page) {
		LogVO vo = new LogVO();
		vo.setList(adminSerivce.getLogList(page));
		vo.setPage(page);
		
		return vo;
	}
	
	@RequestMapping(value="/admin/searchLog", method=RequestMethod.POST)
	public @ResponseBody LogVO  sendSearchResultOfLogList(@RequestBody Page page) {
		LogVO vo = new LogVO();
		vo.setList(adminSerivce.getSearchResultOfLogList(page));
		vo.setPage(page);
		
		return vo;
	}
	

}
