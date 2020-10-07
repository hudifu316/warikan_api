package com.cosa2.warikan_api.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserCreateDto {

	@NotNull
	@Size(max = 20)
	private String username;

	@Size(max = 20)
	@NotNull
	private String password;

}
