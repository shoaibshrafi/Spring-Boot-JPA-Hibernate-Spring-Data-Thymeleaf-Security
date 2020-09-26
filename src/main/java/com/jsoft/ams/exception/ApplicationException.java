package com.jsoft.ams.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException{

	private HttpStatus status = HttpStatus.BAD_REQUEST;
	private String message;
	
	public ApplicationException(HttpStatus status, String message, String... params) {
		super(params != null ? String.format(message, params) : message);
		this.status = status;
		this.message = params != null ? String.format(message, params) : message;
	}

	public ApplicationException(HttpStatus status, String message) {
		this(status, message, null);
	}

	public ApplicationException(String message, String... params) {
		this(HttpStatus.BAD_REQUEST, message, params);
	}

	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
