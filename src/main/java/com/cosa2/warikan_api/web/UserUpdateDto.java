package com.cosa2.warikan_api.web;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cosa2.warikan_api.domain.model.UserBill;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserUpdateDto {

	@NotNull
	private UUID userId;

	@Size(max = 20)
	@NotNull
	private String username;

	@Size(max = 20)
	@NotNull
	private String password;

	@JsonIgnore
	private List<UserBill> bills;

}
