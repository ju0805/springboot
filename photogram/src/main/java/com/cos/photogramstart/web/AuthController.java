package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class AuthController {
	
	//log
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	private final AuthService authService;
	
	//생성자 
//	public AuthController(AuthService authService) {
//		this.authService = authService;
//	}

	
	@GetMapping("/auth/signin")
	public String singinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String singupForm() {
		return "auth/signup";
	}
	
	//회원가입 처리 
	//validation check
	@PostMapping("/auth/signup")
	public String singup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		
		User user = signupDto.toEntity();
		//DB insert
		authService.회원가입(user);
		
		return "auth/signin";
		
		
	}

}
