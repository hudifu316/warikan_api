package com.cosa2.warikan_api.web;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class ErrorResponse {
	@JsonProperty("Error")
	private Error error;

	public ErrorResponse(String msg) {
		this.error = new Error(msg);
	}

	public ErrorResponse(String msg, MultiValueMap<String, String> map) {
		this.error = new Error(msg, map);
	}

	@Value
	private class Error {

		public Error(String msg, MultiValueMap<String, String> map) {
			message = msg;
			errors = map;
		}

		public Error(String msg) {
			message=msg;
			errors=null;
		}

		@Schema(example = "error message")
		@JsonProperty("Message")
		private final String message;

		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonProperty("Errors")
		private MultiValueMap<String, String> errors;
	}

}
