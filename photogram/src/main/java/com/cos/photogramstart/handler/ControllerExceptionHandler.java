package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.utill.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	//exception 처리 js
	@ExceptionHandler(CustomValidationException.class)
	public String vaildationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}
	
	
	//exception 처리 ajax
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> vaildationApiException(CustomValidationApiException e) {
		return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()),HttpStatus.BAD_REQUEST);
		
	}

}
