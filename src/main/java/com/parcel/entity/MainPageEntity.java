package com.parcel.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

/**
 * 메인 페이지에 필요한 데이터 클래스
 * @author Developer
 */
public class MainPageEntity {
	private int idx;
	@Pattern(regexp="^[A-Za-z0-9]{1,20}") private String name; //영문자, 숫자 1~20글자
	@Pattern(regexp="^[\\sA-Za-z가-힣0-9]{2,30}") private String pname;//띄어쓰기, 영문자, 한글, 숫자 2~30글자
	@Range(min=0, max=1) private int isopen; //0~1
	private int countg;
	private int countm;
	private int pidx;
	
	public MainPageEntity() {
		
	}
	
	public MainPageEntity(int idx, String name, String pname, int isopen, int countg, int countm, int pidx) {
		super();
		this.idx = idx;
		this.name = name;
		this.pname = pname;
		this.isopen = isopen;
		this.countg = countg;
		this.countm = countm;
		this.pidx = pidx;
	}
	
	
	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getIsopen() {
		return isopen;
	}
	public void setIsopen(int isopen) {
		this.isopen = isopen;
	}
	public int getCountg() {
		return countg;
	}
	public void setCountg(int countg) {
		this.countg = countg;
	}
	public int getCountm() {
		return countm;
	}
	public void setCountm(int countm) {
		this.countm = countm;
	}

	@Override
	public String toString() {
		return "MainPageEntity [idx=" + idx + ", name=" + name + ", pname=" + pname + ", isopen=" + isopen + ", countg="
				+ countg + ", countm=" + countm + ", pidx=" + pidx + "]";
	}

	
	
	
	
}
