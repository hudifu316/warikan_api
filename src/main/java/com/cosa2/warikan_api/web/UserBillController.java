package com.cosa2.warikan_api.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cosa2.warikan_api.common.exception.UserBillException;
import com.cosa2.warikan_api.domain.model.Bill;
import com.cosa2.warikan_api.domain.model.UserBill;
import com.cosa2.warikan_api.domain.repository.UserRepository;
import com.cosa2.warikan_api.domain.service.UserBillService;
import com.github.dozermapper.core.Mapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/warikan")
public class UserBillController {

	@Autowired
	private UserBillService billService;

	@Autowired
	Mapper mapper;

	@Autowired
	UserRepository userRepository;

	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "成功", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = BillCreateRes.class)) }),
			@ApiResponse(responseCode = "400", description = "失敗", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }), })
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "割り勘を作成する", description = "ユーザごとの割り勘額をJSONで返却する。ユーザは存在しなければ新規に作成する", tags = "bills")
	public BillCreateRes postBill(
			@Parameter(description = "割り勘金額と割り勘するユーザの情報を指定") @Valid @RequestBody BillCreateReq billCreateDTO) {
		// Check only User.UUID or User.name is set.
		for (UserBillCreateDto user : billCreateDTO.getUsers()) {
			if (user.getUserId() != null && user.getUsername() != null) {
				throw new UserBillException("Set an exist User.UUID or new User.name.");
			}
			if (user.getUserId() != null && !userRepository.existsById(user.getUserId())) {
				throw new UserBillException("User.UUID does not exist.");
			}
		}

		Bill bill = billService.warikan(billCreateDTO);
		for (UserBill userBill : bill.getUsers()) {
			mapper.map(userBill, UserBillUpdateDto.class);
		}
		return mapper.map(bill, BillCreateRes.class);

	}
}
