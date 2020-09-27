package com.cosa2.warikan_api.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class ErrorResponse {
	@JsonProperty("Error")
	private Error error;

	public ErrorResponse(String msg) {
		this.error = new Error(msg);
	}

	@Value
	private class Error {
		@JsonProperty("Message")
		private final String message;
	}

}
