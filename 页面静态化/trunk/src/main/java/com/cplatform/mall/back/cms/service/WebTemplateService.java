package com.cplatform.mall.back.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.mall.back.cms.model.WebTemplate;
import com.cplatform.mall.back.cms.repository.WebTemplateRepository;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午6:52:44
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class WebTemplateService {

	@Autowired
	private WebTemplateRepository templateRepository;

	@Transactional(readOnly = false)
	public void save(WebTemplate template) {
		templateRepository.save(template);
	}

	public List<WebTemplate> getAll() {
		return templateRepository.getAll();
	}

	public WebTemplate get(int id) {
		return templateRepository.get(id);
	}

}
