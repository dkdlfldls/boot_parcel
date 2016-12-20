package com.parcel.entity;

import java.util.List;

import com.parcel.util.Page;

/**
 * 로그 리스트 데이터 클래스
 * @author Developer
 */
public class LogVO {
	private List<Log> list;
	private Page page;
	public List<Log> getList() {
		return list;
	}
	public void setList(List<Log> list) {
		this.list = list;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
