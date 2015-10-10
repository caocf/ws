package com.cplatform.mall.back.store.web.validator;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.store.entity.StoreSettle;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.store.service.StoreSettleService;
import com.cplatform.util2.TimeStamp;

@Component
public class StoreSettleValidator implements Validator {

	@Autowired
	private StoreService storeService;

	@Autowired
	private StoreSettleService storeSettleService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		StoreSettle oldSettle = null;
		List<StoreSettle> list = null;
		StoreSettle vo = (StoreSettle) target;

		try {
			if (vo.getId() != null) {
				oldSettle = this.storeService.findStoreSettleById(vo.getId());
				if (!oldSettle.getSettleAc().trim().equals(vo.getSettleAc().trim())) {
					oldSettle = this.storeService.findStoreSettleById(vo.getId());
				}
				if (!vo.getEffortDate().equals(oldSettle.getEffortDate()) || !vo.getExpiryDate().equals(oldSettle.getExpiryDate())) {
					if (!storeSettleService.dateIsValid(vo)) {
						errors.rejectValue("effortDate", null, "请检查生效时间是否冲突");
						errors.rejectValue("expiryDate", null, "请检查失效时间是否冲突");
					}
				}

			} else {
				String time = TimeStamp.getTime(TimeStamp.YYYYMMDD);
				if (Integer.valueOf(vo.getEffortDate()) < Integer.valueOf(time)) {
					errors.rejectValue("effortDate", null, "生效时间不能小于当前时间");
				} else {
					if (!storeSettleService.dateIsValid(vo)) {
						errors.rejectValue("effortDate", null, "请检查生效时间是否冲突");
						errors.rejectValue("expiryDate", null, "请检查失效时间是否冲突");
					}
				}
				list = this.storeService.findStoreSettleList(vo.getSettleAc(),vo.getStoreId());

			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "settleAc", null, "结算账户不能为空");

			if (list != null && list.size() > 0) {
				// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "settleAc",
				// null, "结算账户已存在");
				errors.rejectValue("settleAc", null, "结算账户已存在");
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
