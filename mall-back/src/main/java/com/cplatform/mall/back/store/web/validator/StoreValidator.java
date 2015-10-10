package com.cplatform.mall.back.store.web.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.StoreService;

@Component
public class StoreValidator implements Validator {
	@Autowired
	private StoreService storeService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Store store = (Store) target;
		Store oldStore = null;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "商户名称不能为空");
		List<Store> storeList = null;
		if (store.getId() != null) {
			oldStore = this.storeService.findStoreById(store.getId());
			if (!oldStore.getName().trim().equals(store.getName().trim())) {
				storeList = storeService.findStoreListByName(store.getName());
			}
		} else {
			storeList = storeService.findStoreListByName(store.getName());
		}
		if (storeList != null && storeList.size() > 0) {
			// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null,
			// "渠道商名称已使用");
			errors.rejectValue("name", null, "商户名称已使用");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "linkPhone", null, "联系人手机号码不能为空");
		if (oldStore != null) {
			if (!oldStore.getLinkPhone().trim().equals(store.getLinkPhone().trim())) {
				storeList = storeService.findStoreListBylinkPhone(store.getLinkPhone());
			}
		} else {
			storeList = storeService.findStoreListBylinkPhone(store.getLinkPhone());
		}
		if (storeList != null && storeList.size() > 0) {
			// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "linkPhone",
			// null, "联系人手机号码已使用");
			errors.rejectValue("linkPhone", null, "联系人手机号码已使用");
		}
		if (store.getSort() == 1) {
//			if (store.getStartTime() == null || "".equals(store.getStartTime())) {
//				errors.rejectValue("startTime", null, "选择签约开始时间。");
//			}
//			if (store.getStopTime() == null || "".equals(store.getStopTime())) {
//				errors.rejectValue("stopTime", null, "选择签约结束时间。");
//			}
		} else {
			if (store.getStoreIds() == null || "".equals(store.getStoreIds())) {
//				errors.rejectValue("storeIds", null, "选择渠道商。");
//				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storeIds", null, "选择渠道商");
//				errors.rejectValue("linkPhone", null, "联系人手机号码已使用");
			}
			if (store.getBeginTime() == null || "".equals(store.getBeginTime())) {
//				errors.rejectValue("beginTime", null, "选择代理有效开始时间。");
//				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "beginTime", null, "选择代理有效开始时间。");
			}
			if (store.getEndTime() == null || "".equals(store.getEndTime())) {
//				errors.rejectValue("endTime", null, "选择代理有效结束时间。");
				//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", null, "选择代理有效结束时间。");
			}

		}

	}

}
