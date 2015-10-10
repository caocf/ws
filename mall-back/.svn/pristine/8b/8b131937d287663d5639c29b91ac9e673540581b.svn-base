package com.cplatform.mall.back.member.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.member.dao.MemberDao;
import com.cplatform.mall.back.member.entity.Member;
import com.cplatform.mall.back.utils.data.RoleDataUtils;

/**
 * 会员相关Service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-25 下午02:58:59
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Service
public class MemberService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private MemberDao memberDao;

	/**
	 * 会员查询
	 * 
	 * @param member
	 *            会员实体类
	 * @param pageNo
	 *            当前页
	 * @return
	 * @throws SQLException
	 */
	public Page<Member> memberQuery(Member member, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select m.* from t_member m where 1=1 ");
		List params = new ArrayList();
		if (null != member) {
			Long id = member.getId();
			String idStr = null;
			if (id != null)
				idStr = id.toString();
			if (StringUtils.isNotEmpty(idStr)) {
				sql.append(" and m.id = ? ");
				params.add(member.getId());
			}
			if (StringUtils.isNotEmpty(member.getEmail())) {
				sql.append(" and m.email like ? ");
				params.add("%" + member.getEmail().trim() + "%");
			}
			if (StringUtils.isNotEmpty(member.getTerminalId())) {
				sql.append(" and m.terminal_id = ? ");
				params.add(member.getTerminalId().trim());
			}
			if (StringUtils.isNotBlank(member.getRegTimeBegin())) {
				String beginTime = member.getRegTimeBegin() + "000000";
				sql.append("and m.reg_time > ? ");
				params.add(beginTime);
			}
			if (StringUtils.isNotBlank(member.getRegTimeEnd())) {
				String endTime = member.getRegTimeEnd() + "235959";
				sql.append("and m.reg_time < ? ");
				params.add(endTime);
			}
			if (StringUtils.isNotEmpty(member.getStatus())) {
				sql.append(" and m.status = ? ");
				params.add(member.getStatus());
			}
			if (StringUtils.isNotEmpty(member.getUserLevel())) {
				sql.append(" and m.user_level = ? ");
				params.add(member.getUserLevel());
			}
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_MEMBER));
		sql.append(" order by m.id desc ");
		return dbHelper.getPage(sql.toString(), Member.class, pageNo, Page.DEFAULT_PAGESIZE, params.toArray());
	}

	/**
	 * 查询指定会员
	 * 
	 * @param id
	 */
	@Transactional
	public Member findOneMember(Long id) {
		Member member = memberDao.findOne(id);
		return member;
	}

	/**
	 * 会员录入
	 * 
	 * @param member
	 */
	@Transactional
	public Member saveMember(Member member) {
		// 保存会员信息
		member = memberDao.save(member);
		return member;
	}
}
