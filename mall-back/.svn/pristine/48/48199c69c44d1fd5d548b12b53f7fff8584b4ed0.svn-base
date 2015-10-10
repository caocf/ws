package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.websys.dao.SysLogisticsDao;
import com.cplatform.mall.back.websys.entity.SysLogistics;

/**
 * 物流信息操作类 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-4 下午5:35:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class SysLogisticsService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private SysLogisticsDao sysLogisticsDao;

	public SysLogistics addOrUpdateSysLogistics(SysLogistics po) {
		return this.sysLogisticsDao.save(po);
	}

	public SysLogistics findSysLogisticsById(Long id) {
		return this.sysLogisticsDao.findOne(id);
	}

	public void delSysLogisticsById(Long id) {
		this.sysLogisticsDao.delete(id);
	}

	/**
	 * 获取物流分页数据
	 * 
	 * @param vo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<SysLogistics> findSysLogistics(SysLogistics vo, int pageNo, int pageSize) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select * from t_sys_logistics t where 1 = 1 ");
		if (StringUtils.isNotBlank(vo.getName())) {
			sqlBuff.append(" and t.name like ? ");
			params.add("%" + vo.getName().trim() + "%");
		}
		if (vo.getIsValid() != null) {
			sqlBuff.append(" and t.is_valid = ? ");
			params.add(vo.getIsValid());
		}
		sqlBuff.append("order by t.id desc");
		return dbHelper.getPage(sqlBuff.toString(), SysLogistics.class, pageNo, pageSize, params.toArray());
	}

}
