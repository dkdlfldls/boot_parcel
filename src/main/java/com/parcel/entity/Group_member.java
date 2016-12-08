package com.parcel.entity;

public class Group_member {
	private int idx;
	private int group;
	private int member;
	private int state;
	@Override
	public String toString() {
		return "GroupMemeber [idx=" + idx + ", group=" + group + ", member=" + member + ", state=" + state + "]";
	}
	public Group_member(int idx, int group, int member, int state) {
		super();
		this.idx = idx;
		this.group = group;
		this.member = member;
		this.state = state;
	}
	public Group_member() {
		
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
