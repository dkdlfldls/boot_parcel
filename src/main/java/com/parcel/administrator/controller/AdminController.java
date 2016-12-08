package com.parcel.administrator.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.parcel.service.AdminService;
import com.parcel.util.Page;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/admin/log")
	public String getAdminLogPage(HttpSession session, Model model) {
		return "admin/logManager";
	}
	
	@RequestMapping("/admin/machine")
	public String getAdminMachinePage(Page page, Model model) {
		
		model.addAttribute("machineList", adminService.getMachineList());
		
		return "admin/machineManager";
	}
}
