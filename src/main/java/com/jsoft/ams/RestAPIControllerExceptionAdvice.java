package com.jsoft.ams;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsoft.ams.exception.APIException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("com.jsoft.ams")
public class RestAPIControllerExceptionAdvice {

	@ExceptionHandler(APIException.class)
	public ResponseEntity<?> apiExceptionHandler(HttpServletRequest request, APIException e) {
		Map<String, Object> response = new HashMap();
		response.put("message", e.getMessage());
		response.put("status", "error");
		return ResponseEntity.status(e.getStatus()).body(response);
	}

	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> accessDeniedExceptionHandler(HttpServletRequest request, AccessDeniedException e) {
		log.error("Handling error", e);
		Map<String, Object> response = new HashMap();
		//response.put("username", loggedInUser);
		response.put("message", e.getMessage());
		response.put("cause", e.getCause() != null? e.getCause().getMessage() : "");
		response.put("request_url", request.getRequestURI());
		response.put("params", request.getParameterMap());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> defaultErrorHandler(HttpServletRequest request, Exception e) {
		log.error("Handling error", e);
		Map<String, Object> response = new HashMap<>();
		//response.put("username", loggedInUser);
		response.put("message", e.getMessage());
		response.put("cause", e.getCause() != null? e.getCause().getMessage() : "");
		response.put("request_url", request.getRequestURI());
		response.put("params", request.getParameterMap());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
