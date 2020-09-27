package com.cosa2.warikan_api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cosa2.warikan_api.common.exception.UserBillException;
import com.cosa2.warikan_api.domain.model.Bill;
import com.cosa2.warikan_api.domain.model.UserBill;
import com.cosa2.warikan_api.web.BillCreateDto;
import com.cosa2.warikan_api.web.UserBillCreateDto;

@SpringBootTest
class UserBillServiceTest {

	@Autowired
	UserBillService service;

	@Test
	void normal_success() {// 通常成功ケース
		BillCreateDto billCreation = new BillCreateDto();
		billCreation.setBill("normal_success", new BigDecimal("99999991"), new Date());

		billCreation.setUsers(_createUsers(3, 0));

		Bill bill = new Bill();

		try {
			bill = service.warikan(billCreation);
		} catch (Exception e) {
			e.printStackTrace();
			fail("予期しない例外");
		}

		assertThat(bill.getActivityName()).isEqualTo("normal_success");
		assertThat(bill.getActivityDate()).isInSameMinuteAs(new Date());
		assertThat(bill.getBillingAmount()).isEqualTo(new BigDecimal("99999991"));

		for (UserBill actual : bill.getUsers()) {
			if (actual.isKanji()) {
				assertThat(actual.getPayAmount()).isEqualTo(new BigDecimal("33333331"));
			} else {
				assertThat(actual.getPayAmount()).isEqualTo(new BigDecimal("33333330"));
			}
		}
	}

	@Test
	void warizan_seido1() { // billingAmount < user.count
		BillCreateDto billCreation = new BillCreateDto();
		billCreation.setBill("warizan_seido1", new BigDecimal("2"), new Date());

		billCreation.setUsers(_createUsers(3, 1));

		Bill bill = new Bill();

		try {
			bill = service.warikan(billCreation);
		} catch (Exception e) {
			e.printStackTrace();
			fail("予期しない例外");
		}

		assertThat(bill.getActivityName()).isEqualTo("warizan_seido1");
		assertThat(bill.getActivityDate()).isInSameMinuteAs(new Date());
		assertThat(bill.getBillingAmount()).isEqualTo(new BigDecimal("2"));

		for (UserBill actual : bill.getUsers()) {
			if (actual.isKanji()) {
				assertThat(actual.getPayAmount()).isEqualTo(new BigDecimal("2"));
			} else {
				assertThat(actual.getPayAmount()).isEqualTo(new BigDecimal("0"));
			}
		}
	}

	@Test
	void warizan_seido2() { // billingAmount = 0
		BillCreateDto billCreation = new BillCreateDto();
		billCreation.setBill("warizan_seido2", new BigDecimal("0"), new Date());

		billCreation.setUsers(_createUsers(3, 1));

		Bill bill = new Bill();

		try {
			bill = service.warikan(billCreation);
		} catch (Exception e) {
			e.printStackTrace();
			fail("予期しない例外");
		}

		assertThat(bill.getActivityName()).isEqualTo("warizan_seido2");
		assertThat(bill.getActivityDate()).isInSameMinuteAs(new Date());
		assertThat(bill.getBillingAmount()).isEqualTo(new BigDecimal("0"));

		for (UserBill actual : bill.getUsers()) {
			if (actual.isKanji()) {
				assertThat(actual.getPayAmount()).isEqualTo(new BigDecimal("0"));
			} else {
				assertThat(actual.getPayAmount()).isEqualTo(new BigDecimal("0"));
			}
		}
	}

	@Test
	void user_count_check() {// user==0
		BillCreateDto billCreation = new BillCreateDto();
		billCreation.setBill("user_count_check", new BigDecimal("100000"), new Date());

		assertThatThrownBy(() -> {
			service.warikan(billCreation);
		}).isInstanceOf(UserBillException.class).hasMessageContaining("Users.size should > 0");
	}

	@Test
	void kanji_flag_check_2() {// kanji.count > 1
		BillCreateDto billCreation = new BillCreateDto();
		billCreation.setBill("kanji_flag_check_2", new BigDecimal("0"), new Date());
		List<UserBillCreateDto> users = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			UserBillCreateDto user = new UserBillCreateDto("testuser_" + i, true);
			users.add(user);
		}
		billCreation.setUsers(users);

		assertThatThrownBy(() -> {
			service.warikan(billCreation);
		}).isInstanceOf(UserBillException.class).hasMessageContaining("KanjiFlag.count should == 1");
	}

	@Test
	void kanji_flag_check_0() {
		BillCreateDto billCreation = new BillCreateDto();
		billCreation.setBill("kanji_flag_check_0", new BigDecimal("0"), new Date());
		List<UserBillCreateDto> users = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			UserBillCreateDto user = new UserBillCreateDto("testuser_" + i, false);
			users.add(user);
		}
		billCreation.setUsers(users);

		assertThatThrownBy(() -> {
			service.warikan(billCreation);
		}).isInstanceOf(UserBillException.class).hasMessageContaining("KanjiFlag.count should == 1");
	}

	private List<UserBillCreateDto> _createUsers(int userCount, int kanjiFlagBanme) {
		if (kanjiFlagBanme > userCount) {
			fail("cant create users.");
		}

		List<UserBillCreateDto> users = new ArrayList<>();

		for (int i = 0; i < userCount; i++) {
			UserBillCreateDto user = new UserBillCreateDto("testuser_" + i, (i == kanjiFlagBanme) ? true : false);
			users.add(user);
		}

		return users;
	}

}
