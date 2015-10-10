package com.cplatform.mall.back.store.web.validator;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.store.entity.StoreCode;
import com.cplatform.mall.back.store.service.StoreCodeService;

@Component
public class StoreCodeValidator implements Validator {

	@Autowired
	private StoreCodeService storeCodeService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	public void validate(Object target1, Object target2, Errors errors) {
		String[] sendChannelIdList = (String[]) target1;
		String[] sendTypeIdList = (String[]) target2;
		if (sendChannelIdList.length > 0) {
			for (int i = 0; i < sendChannelIdList.length; i++) {
				for (int j = i + 1; j < sendChannelIdList.length; j++) {
					// 过滤同一个发码渠道
					if (sendChannelIdList[i].equals(sendChannelIdList[j]) && sendTypeIdList[i].equals(sendTypeIdList[j])) {
						errors.rejectValue("errorMsg", null, "同一商户的发码渠道不可相同！");
					}
				}
			}
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		StoreCode code = (StoreCode) target;
		try {
			StoreCode storeCode = storeCodeService
			        .findStoreCodeIsExit(code.getStoreId(), code.getSendChannelId(), code.getSendTypeId(), code.getId());
			if (storeCode != null) {
				errors.rejectValue("errorMsg", null, "发码渠道已存在！");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
