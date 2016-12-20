package com.parcel.controller;

import javax.servlet.http.HttpSession;
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

import com.parcel.entity.User_group;
import com.parcel.entity.Product;
import com.parcel.entity.User;
import com.parcel.service.GroupService;
import com.parcel.service.ProductService;

/**
 * Product기능 관련 Controller
 * @author user
 */
@Controller
public class ProductController{
	
	@Autowired
	private ProductService productService;
	@Autowired
	private GroupService groupService;
	
	/**
	 * 제품 추가 요청 페이지 처리
	 * @param model
	 * @return
	 */
	@RequestMapping("/product/addPage")
	public String registerProductPage(Model model) {
		
		model.addAttribute("machineList", productService.getMachineList());
		
		return "/parcelManager/addProductPage";
	}
	
	/**
	 * 제품 추가 요청 처리
	 * @param session
	 * @param product 추가 될 제품 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/product/addProduct", method=RequestMethod.POST)
	@ResponseBody
	public String registerProductAndValidate(HttpSession session, @RequestBody @Valid Product product, BindingResult result) {
		System.out.println("addProduct process");
		System.out.println(product.toString());
		
		product.setRegistrant((int)session.getAttribute("idx"));
		if (productService.registerProductByUser(product)) {
			return "true";
		} else {
			return "false";
		}
		
	}
	
	/**
	 * 제품에 대한 상세정보 요청 페이지 처리
	 * @param pidx
	 * @param model
	 * @return
	 */
	@RequestMapping("/product/getProductInfo")
	public String getProductInfo(int pidx, Model model) {
		
		model.addAttribute("product", productService.getProductInfo(pidx));
		
		User_group group = groupService.getGroupInfoForProductInfo(pidx);
		if (group == null) {
			model.addAttribute("hasGroup", false);
		} else {
			model.addAttribute("hasGroup", true);
			model.addAttribute("group",groupService.getGroupInfoForProductInfo(pidx));
		}
		/*
		 * 택배함 이름, 개폐상태, 등록자이름, , 등록한 시간, 택배함 코드
		 * 
		 * + 그룹 정보
		 * 그룹명 그룹 소속원
		 */
		
		return "/parcelManager/parcelInfo";
	}
	
	/**
	 * 제품 잠금 요청 처리
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/product/lock",  method=RequestMethod.POST)
	@ResponseBody
	public boolean lock(@RequestBody User user) {
		
		
		if (productService.lock(user)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 제품 열기 요청 처리
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/product/open",  method=RequestMethod.POST)
	@ResponseBody
	public boolean open(@RequestBody User user) {
		if (productService.open(user)) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	
}
