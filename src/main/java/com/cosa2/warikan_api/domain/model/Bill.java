package com.cosa2.warikan_api.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.BooleanUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "bills")
@Entity
public class Bill extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public Bill() {
	}

	public Bill(String activityName, BigDecimal billingAmount, Date activityDate) {
		this();
		this.activityName = activityName;
		this.billingAmount = billingAmount;
		this.activityDate = activityDate;
	}

	@Id
	@GeneratedValue
	private UUID billId;

	private String activityName;

	private BigDecimal billingAmount;

	private Date activityDate;

	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserBill> users = new ArrayList<>();

	public void addUser(User user, boolean kanji) {
		UserBill userBill = new UserBill(user, this, kanji);
		this.users.add(userBill);
		user.getBills().add(userBill);
	}

	public void removeUser(User user) {
		UserBill userBill = new UserBill(user, this);
		user.getBills().remove(userBill);
		users.remove(userBill);
		userBill.setUser(null);
		userBill.setBill(null);
	}

	public void removeAllUsers() {
		for (UserBill user : new ArrayList<>(users)) {
			// userBill.getUser().getBills().remove(userBill);
			users.remove(user);
			user.setUser(null);
			user.setBill(null);
		}
	}

	public boolean checkKanjiIsOnlyOne() {
		Optional<Integer> userCount = users.stream().map(f -> BooleanUtils.toInteger(f.isKanji()))
				.reduce((accum, value) -> accum + value);
		return (userCount.isPresent() && userCount.get() == 1);
	}

}
