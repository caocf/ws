package com.cplatform.mall.back.sys.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.mall.back.sys.entity.SysFee;
import com.cplatform.mall.back.sys.entity.SysSpcode;

/**
 * Title.数据字典管理 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-16 下午2:10:59
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class SysDataService {

	@Autowired
	SysFee sysFee;

	@Autowired
	SysSpcode sysSpcode;

	/** 系统 */
	private static Map<String, String> sysDataMap = new HashMap<String, String>();
	static {

	}

}
