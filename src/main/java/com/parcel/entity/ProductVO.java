package com.parcel.entity;

import java.util.List;

import com.parcel.util.Page;

public class ProductVO {
	private List<Product> list;
	private Page page;
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
