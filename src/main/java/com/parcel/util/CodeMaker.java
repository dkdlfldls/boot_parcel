package com.parcel.util;

public interface CodeMaker {
	public String makeCode(int codeSize);
	public String encode(String code);
	public String decode(String secretCode);
}
