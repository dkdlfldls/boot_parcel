package com.parcel.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

/**
 * 사용자 데이터 클래스
 * @author Developer
 */
public class User {
	
	private int idx;
	@Pattern(regexp="[A-Za-z0-9]{1,20}") private String id; //영문,숫자 1~20글자
	@Pattern(regexp="[A-Za-z0-9]{1,20}", message="error 에러") private String pw; //영문,숫자 1~20글자
	@Pattern(regexp="^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}") private String phone; // xx-xxxx-xxxx
	@Email private String email; //email
	private String web_authority;
	private int state;
	@Pattern(regexp="^[A-Za-z0-9가-힣]{1,20}") private String name; //영문자, 숫자 1~20글자
	private int productIdx;
	private boolean hasGroup;
	@Pattern(regexp="^[A-Za-z0-9]{1,45}") private String productName; //영문자, 숫자 1~45글자

	private int groupIdx;
	
	public User(int idx, String id, String pw, String phone, String email, String web_authority, int state) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.web_authority = web_authority;
		this.state = state;
	}
	
	
	
	public User(int idx, String id, String pw, String phone, String email, String web_authority, int state, String name,
			int productIdx) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.web_authority = web_authority;
		this.state = state;
		this.name = name;
		this.productIdx = productIdx;
	}

	


	public User(int idx, String id, String pw, String phone, String email, String web_authority, int state, String name,
			int productIdx, boolean hasGroup) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.web_authority = web_authority;
		this.state = state;
		this.name = name;
		this.productIdx = productIdx;
		this.hasGroup = hasGroup;
	}
	
	



	public User(int idx, String id, String pw, String phone, String email, String web_authority, int state, String name,
			int productIdx, boolean hasGroup, String productName) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.web_authority = web_authority;
		this.state = state;
		this.name = name;
		this.productIdx = productIdx;
		this.hasGroup = hasGroup;
		this.productName = productName;
	}



	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int idx) {
		// TODO Auto-generated constructor stub
		this.idx = idx;
	}
	
	



	public int getIdx() {
		return idx;
	}



	public void setIdx(int idx) {
		this.idx = idx;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPw() {
		return pw;
	}



	public void setPw(String pw) {
		this.pw = pw;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getWeb_authority() {
		return web_authority;
	}



	public void setWeb_authority(String web_authority) {
		this.web_authority = web_authority;
	}



	public int getState() {
		return state;
	}



	public void setState(int state) {
		this.state = state;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getProductIdx() {
		return productIdx;
	}



	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}



	public boolean isHasGroup() {
		return hasGroup;
	}



	public void setHasGroup(boolean hasGroup) {
		this.hasGroup = hasGroup;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public int getGroupIdx() {
		return groupIdx;
	}



	public void setGroupIdx(int groupIdx) {
		this.groupIdx = groupIdx;
	}



	@Override
	public String toString() {
		return "User [idx=" + idx + ", id=" + id + ", pw=" + pw + ", phone=" + phone + ", email=" + email
				+ ", web_authority=" + web_authority + ", state=" + state + ", name=" + name + ", productIdx="
				+ productIdx + ", hasGroup=" + hasGroup + ", productName=" + productName + "]";
	}

	/**
	 * user정보를 바꿀 때 pw, name, email, phone만 바꿔주는 메서드
	 * @param user 새로 받을 값을 가진 User객체
	 */
	public void setDataForModify(User user) {
		this.pw = user.getPw();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
	}

	



	
	
	
	
}
