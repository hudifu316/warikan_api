package com.cosa2.warikan_api.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BillCreateDto {

	@NotNull
	private String activityName;

	@NotNull
	private BigDecimal billingAmount;

	@NotNull
	private Date activityDate;

	private List<UserBillCreateDto> users = new ArrayList<>();

	public void setBill(String activityName, BigDecimal billingAmount, Date activityDate) {
		this.activityName = activityName;
		this.billingAmount = billingAmount;
		this.activityDate = activityDate;
	}

}
