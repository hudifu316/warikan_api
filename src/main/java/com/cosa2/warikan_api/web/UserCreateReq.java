package com.cosa2.warikan_api.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="ユーザ作成リクエスト")
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserCreateReq {

	@NotNull
	@Size(max = 20)
	private String username;

	@Size(max = 20)
	@NotNull
	private String password;

}
