package com.hungry.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserRuntimeException extends RuntimeException {

	
	
	public ResponseEntity<Void> entityEception() {
		return null;
	}

}
