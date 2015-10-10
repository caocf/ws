package com.cplatform.mall.back.cont.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.cont.dao.ContentCodeDao;
import com.cplatform.mall.back.cont.entity.ContentCode;
import com.cplatform.mall.back.utils.data.RoleDataUtils;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-25 下午1:44:15
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class ContentCodeService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private ContentCodeDao contentCodeDao;

	/**
	 * @param contentCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<ContentCode> listContentCode(ContentCode contentCode, int pageNo, int pageSize) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM T_CONT_CODE WHERE 1=1");
		if (contentCode != null) {
			if (contentCode.getCode() != null) {
				sql.append(" AND UPPER(CODE) LIKE  '%'||UPPER('" + contentCode.getCode().trim() + "')||'%'");
			}
			if (StringUtils.isNotEmpty(contentCode.getName())) {
				sql.append(" AND UPPER(NAME) LIKE '%'||UPPER('" + contentCode.getName().trim() + "')||'%'");
			}
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODUE_CONTSRC));
		// 控制数据访问
		sql.append(" ORDER BY ID DESC");
		return dbHelper.getPage(sql.toString(), ContentCode.class, pageNo, pageSize, null);
	}

	/**
	 * 获取内容源
	 * @param contType
	 * @return
	 * @throws SQLException
	 */
	public List<ContentCode> getContentCodeList(Long contType) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select * from t_cont_code t where t.cont_type = ?");
		sqlBuff.append(RoleDataUtils.getRoleData(RoleDataUtils.MODUE_CONTSRC));
		sqlBuff.append(" order by t.id desc");
		return dbHelper.getBeanList(sqlBuff.toString(), ContentCode.class, new Object[] { contType });

	}
}
