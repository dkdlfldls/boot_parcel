package com.parcel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.parcel.interceptor.ControllerSessionInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private ControllerSessionInterceptor controllerSessionInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(controllerSessionInterceptor)
			.addPathPatterns("/parcel/main")
			.addPathPatterns("/group/**")
			.addPathPatterns("/product/**")
			.addPathPatterns("/message/**")
			.addPathPatterns("/user/**")
			.addPathPatterns("/admin/**");
		registry.addInterceptor(new DeviceResolverHandlerInterceptor());
		registry.addInterceptor(new SitePreferenceHandlerInterceptor());
	}
	
}