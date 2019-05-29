package com.hungry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(MultipartException.class)
	public  ResponseEntity<MultipartException> userImageUploadException(MultipartException exception) {
	
		///System.out.println(exception);
		return new ResponseEntity<MultipartException>(exception,HttpStatus.NO_CONTENT);
		//System.out.println(exception);
	}
	
	
	
	
	
	
	
	

}
