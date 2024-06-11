package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder encode(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		//기존 시큐리티 기능 비활성화 
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/", "/user/**", "/image/**", "subscribe/**", "/comment/**").authenticated() //인증이 필요한 url
		.anyRequest().permitAll() 
		.and().formLogin().loginPage("/auth/signin") //인증이 필요한 url입력시 해당 url로 이동 
		.defaultSuccessUrl("/"); // 로그인 성공시 해당 url로 이동 
		
	}
}
