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

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description="割り勘レスポンス")
@Validated
@Data
public class BillCreateRes {

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

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Schema(type="string" ,format = "date", example = "2020-02-17")
	private Date activityDate;

	@Valid
	private List<UserBillUpdateDto> users = new ArrayList<>();

}
