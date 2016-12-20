package com.parcel.util;

/**
 * 코드와 관련된 작업을 정의해둔 인터페이스
 *
 */
public interface CodeMaker {
	
	/**
	 * 코드를 만드는 작업
	 * @param codeSize 코드의 크기
	 * @return
	 */
	public String makeCode(int codeSize);
	
	/**
	 * 코드를 암호화 하는 작업
	 * @param code 암호화될 코드
	 * @return
	 */
	public String encode(String code);
	
	/**
	 * 코드를 복호화 하는 작업
	 * @param secretCode 복호화 할 암호화된 코드
	 * @return
	 */
	public String decode(String secretCode);
}
