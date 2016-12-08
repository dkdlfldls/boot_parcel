package com.parcel.util;

import java.util.Random;

import org.springframework.stereotype.Component;


//bcrypt를 통한 암호화 이러다보니 decode불가능
@Component
public class UserCodeMaker implements CodeMaker{
	private static String[] charArr = {"a","b","c","d"
			,"e","f","g","h","i"
			,"j","k","l","m","n"
			,"o","p","q","r","s"
			,"t","u","v","w","x","y","z"}; //26자
	private static final int charSize = 26;
	//private static final int codeSize = 6;
	@Override
	public String makeCode(int codeSize) {
		
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		Random randomNum = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < codeSize; i++) {
			sb.append(charArr[randomNum.nextInt(charSize - 1)]);
		}
		return sb.toString();
	}

	@Override
	public String encode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decode(String secretCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
