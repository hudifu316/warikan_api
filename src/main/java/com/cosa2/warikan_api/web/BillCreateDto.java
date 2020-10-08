package com.cosa2.warikan_api.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="割り勘作成リクエスト")
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BillCreateDto {

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
	private List<UserBillCreateDto> users = new ArrayList<>();

	public void setBill(String activityName, BigDecimal billingAmount, Date activityDate) {
		this.activityName = activityName;
		this.billingAmount = billingAmount;
		this.activityDate = activityDate;
	}

}
