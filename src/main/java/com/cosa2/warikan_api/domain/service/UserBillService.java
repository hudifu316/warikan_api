package com.cosa2.warikan_api.domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cosa2.warikan_api.common.exception.UserBillException;
import com.cosa2.warikan_api.domain.model.Bill;
import com.cosa2.warikan_api.domain.model.User;
import com.cosa2.warikan_api.domain.model.UserBill;
import com.cosa2.warikan_api.domain.repository.BillRepository;
import com.cosa2.warikan_api.domain.repository.UserRepository;
import com.cosa2.warikan_api.web.BillCreateReq;
import com.cosa2.warikan_api.web.UserBillCreateDto;
import com.github.dozermapper.core.Mapper;

@Service
@Transactional
public class UserBillService {

	@Autowired
	BillRepository billRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Mapper mapper;

	public Bill warikan(BillCreateReq billCreation) {
		Bill bill;
		List<User> users = new ArrayList<>();

		bill = mapper.map(billCreation, Bill.class);
		bill.removeAllUsers();

		for (UserBillCreateDto user : billCreation.getUsers()) {
			if (user.getUserId() != null && userRepository.findById(user.getUserId()).isPresent()) {
				users.add(userRepository.findById(user.getUserId()).orElseThrow());
			} else {
				user.setUserId(null);
				users.add(mapper.map(user, User.class));
			}
		}
		userRepository.saveAll(users);

		for (int i = 0; i < billCreation.getUsers().size(); i++) {
			bill.addUser(users.get(i), billCreation.getUsers().get(i).isKanji());
		}

		// ユーザ数チェック
		if (bill.getUsers().isEmpty()) {
			throw new UserBillException("Users.size should > 0");
		}

		// 幹事フラグチェック
		if (!bill.checkKanjiIsOnlyOne()) {
			throw new UserBillException("KanjiFlag.count should == 1");
		}

		// 均等割り
		kintouWari(bill);

		// 保存＆レスポンス
		return billRepository.save(bill);
	}

	private void kintouWari(Bill bill) {

		BigDecimal[] payAmount = bill.getBillingAmount().divideAndRemainder(BigDecimal.valueOf(bill.getUsers().size()));

		for (UserBill user : bill.getUsers()) {
			if (user.isKanji()) {
				user.setPayAmount(payAmount[0].add(payAmount[1]));
			} else {
				user.setPayAmount(payAmount[0]);
			}
		}
	}

}
