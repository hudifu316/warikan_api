package com.cosa2.warikan_api.common.util;

import com.cosa2.warikan_api.domain.model.UserBill;
import com.cosa2.warikan_api.web.UserBillUpdateDto;
import com.github.dozermapper.core.DozerConverter;

public class UserBillToUserBillDtoConverter extends DozerConverter<UserBill, UserBillUpdateDto> {

	public UserBillToUserBillDtoConverter() {
		super(UserBill.class, UserBillUpdateDto.class);
	}

	public UserBillToUserBillDtoConverter(Class<UserBill> prototypeA, Class<UserBillUpdateDto> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public UserBillUpdateDto convertTo(UserBill source, UserBillUpdateDto destination) {
		if(source == null) {
			return null;
		}
		return new UserBillUpdateDto(source.getUser().getUserId(), source.getUser().getUsername(), source.isKanji(), source.getPayAmount());
	}

	@Override
	public UserBill convertFrom(UserBillUpdateDto source, UserBill destination) {
		if(source == null) {
			return null;
		}
		UserBill dest = new UserBill();
		dest.setKanji(source.isKanji());
		return dest;
	}

}
