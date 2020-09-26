package com.jsoft.ams.exception;

import org.springframework.http.HttpStatus;

public class APIException extends ApplicationException{
	
	public APIException(HttpStatus status, String message, String... params) {
		super(status, message, params);
	}

	public APIException(HttpStatus status, String message) {
		super(status, message);
	}

	public APIException(String message, String... params) {
		super(message, params);
	}

}
