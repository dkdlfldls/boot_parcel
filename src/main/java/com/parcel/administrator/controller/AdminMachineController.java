package com.parcel.administrator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.service.AdminService;
import com.parcel.util.Page;

/**
 * 관리자의 기종관리 Controller
 *
 */
@Controller
public class AdminMachineController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 기종관리 페이지 요청
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/machine")
	public String getAdminMachinePage(Page page, Model model) {
		
		model.addAttribute("machineList", adminService.getMachineList());
		
		return "admin/machineManager";
	}
	
	/**
	 * 관리자으 기종 수정 요청 처리
	 * @param machine 수정될 기종 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/admin/modifyMachine", method=RequestMethod.POST)
	public @ResponseBody boolean modifyMachineName(@RequestBody @Valid Machine machine, BindingResult result) {
		if (!result.hasErrors()) {
			if(adminService.modifyMachineName(machine)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 관리자의 기종 추가 요청 처리
	 * @param machine 추가될 기종 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/admin/addMachine", method=RequestMethod.POST)
	public @ResponseBody boolean addMachine(@RequestBody @Valid Machine machine, BindingResult result) {
		if (!result.hasErrors()) {
			if (adminService.addMachine(machine)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 관리자의 기종 삭제 요청 처리
	 * @param machine 삭제될 기종 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/admin/deleteMachine", method=RequestMethod.POST)
	public @ResponseBody boolean deleteMachine(@RequestBody Machine machine, BindingResult result) {
		if (!result.hasErrors()) {
			if (adminService.deleteMachine(machine)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
