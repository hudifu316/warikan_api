package com.cosa2.warikan_api.web;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Validated
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

	@Size(max = 20)
	private String username;

	@NotNull
	private boolean kanji;

	@DecimalMin("0")
	@DecimalMax("2147483647")
	private BigDecimal payAmount;

}
