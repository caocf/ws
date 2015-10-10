package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TMember;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.soa.muc.model.Member;

/**
 * 人中心-基本信息 个人中心. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-2-20 上午10:28:36
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Repository
public class TMemberDao {

	@Autowired
	private DbHelper dbHelper;

	/**
	 * 通过用户的编号获取用户信息
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 * @throws SQLException
	 */
	public TMember getTMemberById(Long id) throws SQLException {
		String sql = "SELECT * FROM T_MEMBER m WHERE m.id = ? ";
		return dbHelper.getBean(sql, TMember.class, id);
	}

	/**
	 * 判断手机号是否被绑定
	 * 
	 * @param terminalId
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistMobile(String terminalId) throws SQLException {
		String sql = "SELECT id FROM T_MEMBER WHERE terminal_id = ?";
		List<Map<String, String>> list = dbHelper.getMapList(sql, terminalId);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断邮箱号是否被绑定
	 * 
	 * @param terminalId
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistEmail(String email) throws SQLException {
		String sql = "SELECT id FROM T_MEMBER WHERE email = ? ";
		List<Map<String, String>> list = dbHelper.getMapList(sql, email);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查昵称是否重复
	 * 
	 * @param id
	 * @param nickName
	 * @return
	 * @throws SQLException
	 */
	public boolean repeatNickname(Long id, String nickName) throws SQLException {
		String sql = "SELECT id FROM T_MEMBER m WHERE m.id <> ? AND m.NICK_NAME = ? ";
		String result = dbHelper.queryScalar(sql, new Object[] { id, nickName });
		return StringUtils.isNotBlank(result);
	}

	/**
	 * 根据邮箱查找用户
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public Member findByEmail(String email) throws SQLException {
		return dbHelper.getBean("select * from t_member where email = ?", Member.class, email);
	}

	/**
	 * 根据手机号查找用户
	 * 
	 * @param terminalId
	 * @return
	 * @throws SQLException
	 */
	public Member findByTerminalId(String terminalId) throws SQLException {
		return dbHelper.getBean("select * from t_member where terminal_id = ?", Member.class, terminalId);
	}
}
