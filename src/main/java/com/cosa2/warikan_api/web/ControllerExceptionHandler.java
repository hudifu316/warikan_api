package com.cosa2.warikan_api.web;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cosa2.warikan_api.common.exception.UserBillException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

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
	public ResponseEntity<Object> handleUserBillException(UserBillException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		ErrorResponse body = new ErrorResponse(ex.getMessage());
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return this.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		BindingResult result = ex.getBindingResult();
		for (ObjectError error : result.getGlobalErrors()) {
			map.add("global", error.getDefaultMessage());
		}
		for (FieldError error : result.getFieldErrors()) {
			map.add(error.getField(), error.getDefaultMessage());
		}
		ErrorResponse body = new ErrorResponse("MethodArgumentNotValid", map);

		return this.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		BindingResult result = ex.getBindingResult();
		for (ObjectError error : result.getGlobalErrors()) {
			map.add("global", error.getDefaultMessage());
		}
		for (FieldError error : result.getFieldErrors()) {
			map.add(error.getField(), error.getDefaultMessage());
		}
		ErrorResponse body = new ErrorResponse("BindException", map);

		return this.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		ErrorResponse body = new ErrorResponse("HttpMessageNotReadable");

		Throwable t = ex.getCause();
		if (t instanceof InvalidFormatException) {
			InvalidFormatException ife = (InvalidFormatException) t;

			for (Reference r : ife.getPath()) {
				map.add(r.getFieldName(), ife.getValue() + " can't mapping to " + ife.getTargetType().getName());
			}
			body = new ErrorResponse("InvalidFormat", map);

		} else if (t instanceof JsonParseException) {
			body = new ErrorResponse("JsonParseError");
		}

		return this.handleExceptionInternal(ex, body, headers, status, request);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			map.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath(), violation.getMessage());
		}

		ErrorResponse body = new ErrorResponse("ConstraintViolation", map);
		return this.handleExceptionInternal(ex, body, null, HttpStatus.BAD_REQUEST, request);
	}

}
