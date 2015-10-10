package com.cplatform.mall.back.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.mall.back.cms.model.TempGroup;
import com.cplatform.mall.back.cms.repository.TempGroupRepository;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 上午9:55:41
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Service
@Transactional
public class TempGroupService {

	@Autowired
	private TempGroupRepository tempGroupRepository;

	@Transactional(readOnly = false)
	public void save(TempGroup tempGroup) {
		tempGroupRepository.save(tempGroup);
	}

	public List<TempGroup> getAll() {
		return tempGroupRepository.getAll();
	}

	public TempGroup get(String id) {
		return tempGroupRepository.get(id);
	}

}
