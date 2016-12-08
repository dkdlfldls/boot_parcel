package com.parcel.entity;

import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

public class Machine {
	private int idx;
	@Pattern(regexp="^[A-Za-z0-9]{1,45}") private String machine_name; //영문자, 숫자 1~45글자
	private int state;
	private Timestamp sign_up_date;
	
	public Machine() {
		// TODO Auto-generated constructor stub
	}

	public Machine(int idx, String machine_name, int state, Timestamp sign_up_date) {
		super();
		this.idx = idx;
		this.machine_name = machine_name;
		this.state = state;
		this.sign_up_date = sign_up_date;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getMachine_name() {
		return machine_name;
	}

	public void setMachine_name(String machine_name) {
		this.machine_name = machine_name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Timestamp getSign_up_date() {
		return sign_up_date;
	}

	public void setSign_up_date(Timestamp sign_up_date) {
		this.sign_up_date = sign_up_date;
	}

	@Override
	public String toString() {
		return "Machine [idx=" + idx + ", machine_name=" + machine_name + ", state=" + state + ", sign_up_date="
				+ sign_up_date + "]";
	}
	
	
	
}
