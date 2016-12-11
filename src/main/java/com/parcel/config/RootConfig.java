package com.parcel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={InterceptorConfig.class, MvcConfig.class, PropertiesConfig.class, SpringSecurityConfig.class, WebSocketConfig.class})
public class RootConfig {

}
