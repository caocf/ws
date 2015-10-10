package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.PathUtil.PathInfo;
import com.cplatform.mall.back.websys.dao.SysTemplateDao;
import com.cplatform.mall.back.websys.entity.SysTemplateInfo;

/**
 * 模版管理Service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhouhui@c-platform.com
 * @version 1.0.0
 */
@Service
public class SysTemplateService {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	AppConfig appConfig;

	@Autowired
	SysTemplateDao dao;

	@Autowired
	BsFileService bsFileService;

	/**
	 * 分页查询所有的模版信息
	 * 
	 * @param info
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<SysTemplateInfo> getSysTempLateInfo(SysTemplateInfo info, int pageNo, int pageSize) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select * from t_sys_template_info t where 1 = 1 ");
		if (StringUtils.isNotBlank(info.gettName())) {
			sqlBuff.append(" and t.tname like ? ");
			params.add("%" + info.gettName().trim() + "%");
		}
		sqlBuff.append("order by t.id desc");
		return dbHelper.getPage(sqlBuff.toString(), SysTemplateInfo.class, pageNo, pageSize, params.toArray());

	}

	/**
	 * 查询所有模版信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Page<SysTemplateInfo> list() throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select * from t_sys_template_info t where 1 = 1 ");
		sqlBuff.append("order by t.id desc");
		return dbHelper.getPage(sqlBuff.toString(), SysTemplateInfo.class);

	}

	/**
	 * 查询单个模版信息
	 * 
	 * @param id
	 * @return
	 */
	public SysTemplateInfo findById(int id) {
		return dao.findById(id);
	}
       
	/**
	 * 保存模版信息
	 * 
	 * @param sys
	 * @param uploadFile
	 * @param isValid
	 * @throws Exception
	 */
	@Transactional
	public void saveTemplate(SysTemplateInfo sys, MultipartFile uploadFile, int isValid) throws Exception {
		if (uploadFile != null && !uploadFile.isEmpty()) {
			PathInfo pathInfo = bsFileService.dealModuleFile(uploadFile, BsFileService.MODULE_TEMPLATE);
			sys.setFilePath(uploadFile.getOriginalFilename());
		}
		// sys.setOutputCharTset("GBK");
		sys.setType(isValid);
		dao.save(sys);
	}
}
