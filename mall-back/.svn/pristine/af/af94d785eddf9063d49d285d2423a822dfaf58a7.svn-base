package com.cplatform.mall.back.store.web.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.mall.back.store.entity.StoreAgent;
import com.cplatform.mall.back.store.service.StoreService;
	

@Component
public class StoreAgentValidator {

/*	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		// StoreAgent vo = (StoreAgent)target;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jsStoreIds", null, "请选择代理的结算商户");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", null, "代理的开始时间不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stopTime", null, "代理的结束时间不能为空");
	}*/
	/**
	 * add by xueqiang 写了时间冲突验证，时间直接在样式里加了必填选项，不需要写到验证里
	 * */
	@Autowired
	private StoreService storeService;

	
	public String validate(StoreAgent storeAgent) {
		List<StoreAgent> storeAgents =  storeService.findStoreAgentByQdStoreId(storeAgent.getQdStoreId());
		for(int i=0;i<storeAgents.size();i++){
			String startTime = storeAgents.get(i).getStartTime();
			String stopTime = storeAgents.get(i).getStopTime();
			if(Long.parseLong(storeAgent.getStartTime()) > Long.parseLong(startTime) && Long.parseLong(storeAgent.getStartTime()) < Long.parseLong(stopTime)){
				return "开始、结束时间不能冲突！";
			}
			if(Long.parseLong(storeAgent.getStopTime()) > Long.parseLong(startTime) && Long.parseLong(storeAgent.getStartTime()) < Long.parseLong(stopTime)){
				return "开始、结束时间不能冲突！";
			}
		}
		return "";
	}

}
