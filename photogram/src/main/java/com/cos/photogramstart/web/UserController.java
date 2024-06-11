package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
	
	//회원정보 
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "/user/profile";
	}
	
	//회원정보 수정 
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id) {
		return "/user/update";
	}

}
