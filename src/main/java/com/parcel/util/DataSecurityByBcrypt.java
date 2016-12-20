package com.parcel.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * DataSecurity를 BCrypt방법으로 구현한 클래스
 * @author user
 *
 */
@Component
public class DataSecurityByBcrypt implements DataSecurity {
	
	@Override
	public String encrypt(String origin) {
		if (origin == null) {
			return null;
		} else {
			return BCrypt.hashpw(origin, BCrypt.gensalt());
		}
	}

	@Override
	/**
	 * not realize
	 * do not use
	 */
	public String decrypt(String code) {
		return null;
	}

	@Override
	public boolean check(String origin, String code) {
		// TODO Auto-generated method stub
		try {
			if (origin != null && code != null) {
				return BCrypt.checkpw(origin, code);
			} else {
				return false;
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
