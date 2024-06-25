package com.cos.photogramstart.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {
	
	private final ImageService imageService;

	//main page
	@GetMapping({"/", "/image/story"})
	public String story() {
		return "/image/story";
	}
	
	//인기글 page 
	@GetMapping("/image/popular")
	public String popular(Model model) {
		
		List<Image> imags = imageService.인기사진();
		model.addAttribute("images", imags);
		
		return "/image/popular";
	}
	
	//img 등록  
	@GetMapping("/image/upload")
	public String upload() {
		return "/image/upload";
	}
	
	@PostMapping("/image")
	public String imageUpdate(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		//이미지 유효성 검사 
		if(imageUploadDto.getFile().isEmpty()) {
			throw new CustomValidationException("이미지가 첨부되지않았습니다.", null);
		}
		
		imageService.사진업로드(imageUploadDto, principalDetails);
		return "redirect:/user/"+principalDetails.getUser().getId();
		
	}
}
