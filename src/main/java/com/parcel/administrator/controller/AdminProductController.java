package com.parcel.administrator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parcel.entity.Product;
import com.parcel.entity.ProductVO;
import com.parcel.service.AdminService;
import com.parcel.util.Page;

/**
 * 관리자 제품관리 Controller
 *
 */
@Controller
public class AdminProductController {
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 제품 관리 페이지 요청 처리
	 * @return
	 */
	@RequestMapping(value="/admin/productPage", method=RequestMethod.GET)
	public String adminProductPage() {
		return "/admin/productManager";
	}
	
	/**
	 * 제품 데이터 요청 페이징 처리
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/admin/productPage", method=RequestMethod.POST)
	public @ResponseBody ProductVO addProduct(@RequestBody Page page) {
		System.out.println(page.toString());
		ProductVO vo = new ProductVO();
		vo.setList(adminService.getProductList(page));
		vo.setPage(page);
		return vo;
	}
	
	/**
	 * 제품 데이터 추가 처리
	 * @param product 추가될 제품 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/admin/addProduct", method=RequestMethod.POST)
	public @ResponseBody Product addProductAndValidate(@RequestBody @Valid Product product, BindingResult result) {
		Product p = new Product();
		
		return p;
	}
	
	/**
	 * 제품 수정 처리
	 * @param product 수정될 제품 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/admin/modifyProduct", method=RequestMethod.POST)
	public @ResponseBody Product modifyProductAndValidate(@RequestBody @Valid Product product, BindingResult result) {
		Product p = new Product();
		
		return p;
	}
	
	/**
	 * 제품 삭제 처리
	 * @param product 삭제될 제품 정보
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/admin/deleteProduct", method=RequestMethod.POST)
	public @ResponseBody Product deleteProductAndValidate(@RequestBody @Valid Product product, BindingResult result) {
		Product p = new Product();
		
		return p;
	}
}
