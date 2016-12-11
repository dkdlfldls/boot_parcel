package com.example;

import java.nio.charset.Charset;

import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.parcel.*"})
@EnableWebMvc
public class BootParcelApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BootParcelApplication.class, args);
	}
	
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	
    	builder.sources(BootParcelApplication.class);
    	
    	return super.configure(builder);
    }
 
	@Override
	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
		//스프링 시큐리티 AND redis
		servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
        .addMappingForUrlPatterns(null, false, "/*");
		
		
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		servletContext.addFilter("encodingFilter", characterEncodingFilter).addMappingForUrlPatterns(null, false, "/*");
		
		servletContext.addFilter("deviceResolverRequestFilter", new DeviceResolverRequestFilter())
		.addMappingForUrlPatterns(null, false, "/*");
		return super.createRootApplicationContext(servletContext);
	}
}
