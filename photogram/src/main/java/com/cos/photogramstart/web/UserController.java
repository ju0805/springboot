package com.cos.photogramstart.web;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	//회원정보 
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, 
			Model model, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto",dto);
		
		return "/user/profile";
	}
	
	//회원정보 수정 
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		//세션정보를 가져오는 다른방법 
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
//		model.addAttribute("principal", principalDetails.getUser());
		
		return "/user/update";
	}

}
