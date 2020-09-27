package com.cosa2.warikan_api.web;

import java.util.List;
import java.util.UUID;

import com.cosa2.warikan_api.domain.model.UserBill;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserUpdateDto {

	private UUID userId;

	private String username;

	private String password;

	@JsonIgnore
	private List<UserBill> bills;

}
