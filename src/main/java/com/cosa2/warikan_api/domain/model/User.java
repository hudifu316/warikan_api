package com.cosa2.warikan_api.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
@Entity
public class User extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public User() {
	}

	public User(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue
	private UUID userId;

	private String username;

	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserBill> bills = new ArrayList<>();

	public void addBill(Bill bill, boolean kanji) {
		UserBill userBill = new UserBill(this, bill, kanji);
		this.bills.add(userBill);
		bill.getUsers().add(userBill);
	}

	public void removeBill(Bill bill) {
		UserBill userBill = new UserBill(this, bill);
		bill.getUsers().remove(userBill);
		bills.remove(userBill);
		userBill.setUser(null);
		userBill.setBill(null);
	}

	public void removeAllBills() {
		for (UserBill userBill : new ArrayList<>(bills)) {
			userBill.getUser().getBills().remove(userBill);
			bills.remove(userBill);
			userBill.setUser(null);
			userBill.setBill(null);
		}
	}

}
