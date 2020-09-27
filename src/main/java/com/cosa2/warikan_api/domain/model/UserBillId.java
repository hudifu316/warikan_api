package com.cosa2.warikan_api.domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBillId implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID userId;
	private UUID billId;

}
