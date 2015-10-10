package com.cplatform.mall.back.locallife.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.locallife.dao.ModuleDataTempletDao;
import com.cplatform.mall.back.locallife.entity.ModuleDataTemplet;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.PathUtil.PathInfo;
import com.cplatform.mall.back.websys.entity.SysTemplateInfo;
import com.cplatform.mall.back.websys.service.BsFileService;

/**
 * 模版管理Service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author mudeng@c-platform.com
 * @version 1.0.0
 */
@Service
public class LocalLifeTempletService {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	AppConfig appConfig;

	@Autowired
	ModuleDataTempletDao dao;

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
	public Page<ModuleDataTemplet> getModuleDataTempletPage(ModuleDataTemplet c, int pageNo, int pageSize) throws Exception {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.*,(select u.user_name from t_sys_user u where u.id = t.create_user) as createUserName,(select u.user_name from t_sys_user u where u.id = t.update_user) as updateUserName from t_module_data_templet t where 1 = 1 ");
				if (StringUtils.isNotEmpty(c.getTitle())) {
						sqlBuff.append(" and t.title like ? ");
						params.add("%" + c.getTitle().trim() + "%");
					}
		sqlBuff.append("order by t.id desc");
		return dbHelper.getPage(sqlBuff.toString(), ModuleDataTemplet.class, pageNo, pageSize, params.toArray());

	}

	/**
	 * 查询所有模版信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Page<SysTemplateInfo> list() throws Exception {
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
	public ModuleDataTemplet findById(Long id) {
		return dao.findOne(id);
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
	public void saveTemplate(ModuleDataTemplet moduleDataTemplet, MultipartFile uploadFile) throws Exception {
		if (uploadFile != null && !uploadFile.isEmpty()) {
			PathInfo pathInfo = bsFileService.dealModuleFile(uploadFile, BsFileService.LOCAL_LIFE);
//			moduleDataTemplet.setConfPath(pathInfo.getWebPath()+uploadFile.getOriginalFilename());
			moduleDataTemplet.setConfPath(pathInfo.getRealWebPath(""));
		}
		moduleDataTemplet.setType(1L);
		dao.save(moduleDataTemplet);
	}
}
