package com.parcel.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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

