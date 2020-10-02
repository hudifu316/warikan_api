package com.cosa2.warikan_api.web;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return this.handleExceptionInternal(ex, "MethodArgumentNotValid", headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return this.handleExceptionInternal(ex, "BindException", headers, status, request);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		return this.handleExceptionInternal(ex, "Constraint", null, HttpStatus.BAD_REQUEST, request);
	}

}
