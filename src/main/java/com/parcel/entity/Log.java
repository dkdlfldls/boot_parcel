package com.parcel.entity;

import java.sql.Timestamp;

/**
 * 로그 데이터 클래스
 * @author Developer
 */
public class Log {
	private int idx;
	private Integer user;
	private Integer user_group;
	private Integer group_member;
	private Integer machine;
	private Integer product;
	private Integer delivery_record;
	private Integer receive_record;
	private Integer message;
	private String log;
	private int state;
	private Timestamp time;
	private Integer detail;
	
	public Log() {
		
	}



	public int getDetail() {
		return detail;
	}
	public void setDetail(int detail) {
		this.detail = detail;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}

	public Integer getUser_group() {
		return user_group;
	}
	public void setUser_group(int user_group) {
		this.user_group = user_group;
	}
	public Integer getGroup_member() {
		return group_member;
	}
	public void setGroup_member(int group_member) {
		this.group_member = group_member;
	}
	public Integer getMachine() {
		return machine;
	}
	public void setMachine(int machine) {
		this.machine = machine;
	}
	public Integer getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public Integer getDelivery_record() {
		return delivery_record;
	}
	public void setDelivery_record(int delivery_record) {
		this.delivery_record = delivery_record;
	}
	public Integer getReceive_record() {
		return receive_record;
	}
	public void setReceive_record(int receive_record) {
		this.receive_record = receive_record;
	}
	public Integer getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}

	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}



	@Override
	public String toString() {
		return "Log [idx=" + idx + ", user=" + user + ", user_group=" + user_group + ", group_member=" + group_member
				+ ", machine=" + machine + ", product=" + product + ", delivery_record=" + delivery_record
				+ ", receive_record=" + receive_record + ", message=" + message + ", log=" + log + ", state=" + state
				+ ", time=" + time + "]";
	}
	
	
}
