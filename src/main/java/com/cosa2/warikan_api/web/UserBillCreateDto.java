package com.cosa2.warikan_api.web;

import java.util.UUID;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserBillCreateDto {

	public UserBillCreateDto() {

	}

	public UserBillCreateDto(UUID userId, boolean kanjiFlag) {
		this.userId = userId;
		this.kanji = kanjiFlag;
	}

	public UserBillCreateDto(String username, boolean kanjiFlag) {
		this.username = username;
		this.kanji = kanjiFlag;
	}

	@Id
	private UUID userId;

	@Size(max = 20)
	private String username;

	@NotNull
	private boolean kanji;

}
