package com.cosa2.warikan_api.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cosa2.warikan_api.domain.model.User;
import com.cosa2.warikan_api.domain.repository.UserRepository;
import com.github.dozermapper.core.Mapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Mapper mapper;

	@PostMapping
	@Validated
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "成功", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserCreateRes.class)) }),
			@ApiResponse(responseCode = "400", description = "失敗", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }), })
	@Operation(summary = "ユーザを作成する", description = "割り勘するユーザを予め作成する", tags = "users")
	public UserCreateRes postUser(@RequestBody @Valid UserCreateReq dto) {
		User user = mapper.map(dto, User.class);
		user = userRepository.save(user);
		return mapper.map(user, UserCreateRes.class);
	}

	@GetMapping
	@Operation(summary = "ユーザ一覧取得", description = "ユーザ一覧を取得する", tags = "users")
	public List<UserCreateRes> getUser() {
		List<UserCreateRes> usersDtos = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			usersDtos.add(mapper.map(user, UserCreateRes.class));
		}
		return usersDtos;
	}

}
