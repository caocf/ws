package com.cplatform.mall.back.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.mall.back.cms.model.EventRegister;
import com.cplatform.mall.back.cms.repository.EventRegisterRepository;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午4:42:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Service
@Transactional
public class EventRegisterService {

	@Autowired
	private EventRegisterRepository eventRegisterRepository;

	@Transactional(readOnly = false)
	public void save(EventRegister eventRegister) {
		eventRegisterRepository.save(eventRegister);
	}

	@Transactional(readOnly = false)
	public void deleteById(String id) {
		eventRegisterRepository.deleteById(id);
	}

	public List<EventRegister> getAll() {
		return eventRegisterRepository.getAll();
	}

	public EventRegister getByCode(int code) {
		return eventRegisterRepository.getByCode(code);
	}

}
