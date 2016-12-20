package com.parcel.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Log프로퍼티를 불러서 읽는 클래스
 * @author user
 *
 */
@Component
public class LogProperties {

	@Autowired
	private Properties log_detail;
	
	
	public int getInt(String key) {
		try {
			return Integer.parseInt(log_detail.getProperty(key));
		} catch (Exception e) {
			return 0;
		}
		
		
	}

}
