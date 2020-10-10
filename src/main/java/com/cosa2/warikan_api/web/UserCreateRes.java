package com.cosa2.warikan_api.web;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.cosa2.warikan_api.domain.model.UserBill;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description="ユーザ作成レスポンス")
@Validated
@Data
public class UserCreateRes {

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
