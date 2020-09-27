package com.cosa2.warikan_api.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cosa2.warikan_api.common.exception.UserBillException;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (!(body instanceof ErrorResponse)) {
			body = new ErrorResponse(status.getReasonPhrase());
		}
		return new ResponseEntity<>(body, headers, status);
	}

	@ExceptionHandler({ UserBillException.class })
	public ResponseEntity<Object> handle404(UserBillException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		ErrorResponse body = new ErrorResponse(ex.getMessage());
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return this.handleExceptionInternal(ex, body, headers, status, request);
	}

}
