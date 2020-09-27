package com.cosa2.warikan_api.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BillUpdateDto {

	@Id
	@NotNull
	private UUID billId;

	@NotNull
	private String activityName;

	@NotNull
	private BigDecimal billingAmount;

	@NotNull
	private Date activityDate;

	private List<UserBillUpdateDto> users = new ArrayList<>();

}
