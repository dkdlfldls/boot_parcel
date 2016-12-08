package com.parcel.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

public class Message {
	private int idx;
	private int receiver;
	@Length(max=100) private String message; //100글자 이내
	private Timestamp send_time;
	private int state;
	private int show;
	
	public Message() {
		
	}
	
	public Message(int idx, int receiver, String message, Timestamp send_time, int state, int show) {
		super();
		this.idx = idx;
		this.receiver = receiver;
		this.message = message;
		this.send_time = send_time;
		this.state = state;
		this.show = show;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getSend_time() {
		return send_time;
	}

	public void setSend_time(Timestamp send_time) {
		this.send_time = send_time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "Message [idx=" + idx + ", receiver=" + receiver + ", message=" + message + ", send_time=" + send_time
				+ ", state=" + state + ", show=" + show + "]";
	}
	
	
	
	
}
