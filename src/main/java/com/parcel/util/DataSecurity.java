package com.parcel.util;

public interface DataSecurity {
	public String encrypt(String origin);
	public String decrypt(String code);
	public boolean check(String origin, String code);
}
