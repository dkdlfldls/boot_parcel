package com.parcel.config;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 프로퍼티 설정
 * 사용할 프로퍼티가 있다면 이 곳 에서 설정
 * @author Developer
 * Java Config
 *
 */
@Configuration
public class PropertiesConfig {

	@Bean(name = "log_detail")
	public Properties log_detail() {
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(new ClassPathResource("properties/log_detail.properties").getFile()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
}

