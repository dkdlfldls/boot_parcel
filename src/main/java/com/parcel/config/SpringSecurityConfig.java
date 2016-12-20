package com.parcel.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Spring security config
 * @author Developer
 * Java Config
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT id username, pw password, state enabled FROM user WHERE id=?")
		.authoritiesByUsernameQuery("SELECT id username, web_authority role FROM user WHERE id=?")
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/group/**").access("hasRole('ROLE_MEMBER')")
		.antMatchers("/parcel/**").access("hasRole('ROLE_MEMBER')")
		.antMatchers("/message/**").access("hasRole('ROLE_MEMBER')")
		.antMatchers("/product/**").access("hasRole('ROLE_MEMBER')")
		.antMatchers("/user/**").access("hasRole('ROLE_MEMBER')")
		.antMatchers("/echo-ws").access("hasRole('ROLE_MEMBER')")
		.antMatchers("/").permitAll()
		.and().formLogin().loginPage("/").loginProcessingUrl("/login").usernameParameter("id").passwordParameter("pw")
		.failureUrl("/").successForwardUrl("/userLogin")
		.and().logout().invalidateHttpSession(true).logoutUrl("/user/logout").logoutSuccessUrl("/")
		.and().csrf().disable()
		.exceptionHandling().accessDeniedPage("/logout");
	}
	
}
