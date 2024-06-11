package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

	//main page
	@GetMapping({"/", "/image/story"})
	public String story() {
		return "/image/story";
	}
	
	//인기글 page 
	@GetMapping("/image/popular")
	public String popular() {
		return "/image/popular";
	}
	
	//img 등록  
	@GetMapping("/image/upload")
	public String upload() {
		return "/image/upload";
	}
}
