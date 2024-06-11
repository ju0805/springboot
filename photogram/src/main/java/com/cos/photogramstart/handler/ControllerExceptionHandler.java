package com.cos.photogramstart.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.utill.Script;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	//runtime exception 처리 
	@ExceptionHandler(CustomValidationException.class)
	public String vaildationException(CustomValidationException e) {
		
		return Script.back(e.getErrorMap().toString());
		
	}

}
