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
	public UserUpdateDto postUser(@RequestBody @Valid UserCreateDto dto) {
		User user = mapper.map(dto, User.class);
		user = userRepository.save(user);
		return mapper.map(user, UserUpdateDto.class);
	}

	@GetMapping
	public List<UserUpdateDto> getUser() {
		List<UserUpdateDto> usersDtos = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			usersDtos.add(mapper.map(user, UserUpdateDto.class));
		}
		return usersDtos;
	}

}
