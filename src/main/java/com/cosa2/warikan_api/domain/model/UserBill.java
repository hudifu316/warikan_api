package com.cosa2.warikan_api.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class UserBill extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public UserBill() {
	}

	public UserBill(User user2, Bill bill2, boolean kanji) {
		this.userBillId = new UserBillId(user2.getUserId(), bill2.getBillId());
		this.user = user2;
		this.bill = bill2;
		this.kanji = kanji;
	}

	public UserBill(User user2, Bill bill2) {
		this.userBillId = new UserBillId(user2.getUserId(), bill2.getBillId());
		this.user = user2;
		this.bill = bill2;
	}

	@EmbeddedId
	private UserBillId userBillId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("billId")
	private Bill bill;

	private boolean kanji;

	private BigDecimal payAmount;

}
