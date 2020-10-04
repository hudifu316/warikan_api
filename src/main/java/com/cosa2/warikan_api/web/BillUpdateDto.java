package com.cosa2.warikan_api.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BillUpdateDto {

	@Id
	@NotNull
	private UUID billId;

	@Size(max = 250)
	@NotNull
	private String activityName;

	@DecimalMin("0")
	@DecimalMax("2147483647")
	@NotNull
	private BigDecimal billingAmount;

	@JsonFormat(pattern = "yyyyMMdd")
	private Date activityDate;

	@Valid
	private List<UserBillUpdateDto> users = new ArrayList<>();

}
