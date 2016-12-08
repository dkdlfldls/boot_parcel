package com.parcel.administrator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.service.AdminService;

@Controller
public class AdminMachineController {

	@Autowired
	private AdminService adminService;
	
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
