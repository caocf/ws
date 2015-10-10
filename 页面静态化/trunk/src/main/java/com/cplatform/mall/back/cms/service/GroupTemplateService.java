package com.cplatform.mall.back.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.mall.back.cms.model.GroupTemplate;
import com.cplatform.mall.back.cms.repository.GroupTemplateRepository;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午1:16:27
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Service
@Transactional
public class GroupTemplateService {

	@Autowired
	private GroupTemplateRepository templateRepository;

	@Transactional(readOnly = false)
	public void save(GroupTemplate groupTemplate) {
		templateRepository.save(groupTemplate);
	}

	@Transactional(readOnly = false)
	public void deleteByGroupId(String gid) {
		templateRepository.deleteByGroupId(gid);
	}

	public List<GroupTemplate> getByGID(String gid) {
		return templateRepository.getByGID(gid);
	}
	
	

}
