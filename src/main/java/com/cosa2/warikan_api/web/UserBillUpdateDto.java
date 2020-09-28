package com.cosa2.warikan_api.web;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserBillUpdateDto {
	public UserBillUpdateDto() {
	}

	public UserBillUpdateDto(UUID userId2, String username2, boolean kanji2, BigDecimal payAmount2) {
		this.userId = userId2;
		this.username = username2;
		this.kanji = kanji2;
		this.payAmount = payAmount2;
	}

	@Id
	@NotNull
	private UUID userId;

	private String username;

	private boolean kanji;

	private BigDecimal payAmount;

}
