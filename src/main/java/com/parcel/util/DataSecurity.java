package com.parcel.util;

/**
 * 데이터를 암호화 및 복호화 하고 확인하는 작업을 정의해둔 인터페이스
 * @author user
 *
 */
public interface DataSecurity {
	
	/**
	 * 데이터를 암호화 하는 작업
	 * @param origin
	 * @return
	 */
	public String encrypt(String origin);
	
	/**
	 * 데이터를 복호화 하는 작업
	 * @param code
	 * @return
	 */
	public String decrypt(String code);
	
	/**
	 * 암호화된 데이터와 일반 데이터를 비교하여 같은 데이터인지 확인하는 작업
	 * @param origin 일반 데이터
	 * @param code 암호화된 데이터
	 * @return
	 */
	public boolean check(String origin, String code);
}
