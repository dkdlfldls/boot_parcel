package com.parcel.entity;

import java.util.List;

import javax.validation.constraints.Pattern;

/**
 * 사용자 그룹 데이터 클래스
 * @author Developer
 */
public class User_group {
	
	private int idx;
	@Pattern(regexp="^[\\sA-Za-z가-힣0-9]{1,30}") private String group_name; //띄어쓰기, 영문자, 한글, 숫자 1~30글자
	private int manager;
	private int product;
	private int state;
	private List<User> groupUserList;
	@Pattern(regexp="^[a-z0-9]{1,30}") private String pw;
	@Pattern(regexp="^[a-z]{45}$") private String code; //영문 숫자 45글자
	@Pattern(regexp="^[A-Za-z0-9]{1,20}") private String managerName; //영문자, 숫자 1~20글자
	@Pattern(regexp="^[\\sA-Za-z가-힣0-9]{2,30}") private String productName;//띄어쓰기, 영문자, 한글, 숫자 2~30글자
	private int groupMemberCnt;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public List<User> getGroupUserList() {
		return groupUserList;
	}
	public void setGroupUserList(List<User> groupUserList) {
		this.groupUserList = groupUserList;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getGroupMemberCnt() {
		return groupMemberCnt;
	}
	public void setGroupMemberCnt(int groupMemberCnt) {
		this.groupMemberCnt = groupMemberCnt;
	}
	public User_group() {
		
	}
	public User_group(int idx, String group_name, int manager, int product, int state, List<User> groupUserList, String pw,
			String code) {
		super();
		this.idx = idx;
		this.group_name = group_name;
		this.manager = manager;
		this.product = product;
		this.state = state;
		this.groupUserList = groupUserList;
		this.pw = pw;
		this.code = code;
	}
	
	
	
	public User_group(int idx, String group_name, int manager, int product, int state, List<User> groupUserList, String pw,
			String code, String managerName, String productName, int groupMemberCnt) {
		super();
		this.idx = idx;
		this.group_name = group_name;
		this.manager = manager;
		this.product = product;
		this.state = state;
		this.groupUserList = groupUserList;
		this.pw = pw;
		this.code = code;
		this.managerName = managerName;
		this.productName = productName;
		this.groupMemberCnt = groupMemberCnt;
	}
	@Override
	public String toString() {
		return "Group [idx=" + idx + ", group_name=" + group_name + ", manager=" + manager + ", product=" + product
				+ ", state=" + state + ", groupUserList=" + groupUserList + ", pw=" + pw + ", code=" + code
				+ ", managerName=" + managerName + ", productName=" + productName + ", groupMemberCnt=" + groupMemberCnt
				+ "]";
	}
	

}
