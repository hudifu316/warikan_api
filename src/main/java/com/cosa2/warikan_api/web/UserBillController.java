package com.cosa2.warikan_api.web;

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
import com.cosa2.warikan_api.domain.service.UserBillService;
import com.github.dozermapper.core.Mapper;

@RestController
@RequestMapping("/warikan")
public class UserBillController {

	@Autowired
	private UserBillService billService;

	@Autowired
	Mapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BillUpdateDto postBill(@RequestBody BillCreateDto billCreateDTO) {
		// Check only User.UUID or User.name is setted.
		for (UserBillCreateDto user : billCreateDTO.getUsers()) {
			if (user.getUserId() != null && user.getUsername() != null) {
				throw new UserBillException("only User.UUID or User.name can be setted.");
			}
		}

		Bill bill = billService.warikan(billCreateDTO);
		for (UserBill userBill : bill.getUsers()) {
			mapper.map(userBill, UserBillUpdateDto.class);
		}
		return mapper.map(bill, BillUpdateDto.class);

	}
}
