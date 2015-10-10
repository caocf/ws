package com.cplatform.mall.back.cont.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.cont.dao.ContSmsDao;
import com.cplatform.mall.back.cont.dao.ContentCodeDao;
import com.cplatform.mall.back.cont.entity.ContSms;
import com.cplatform.mall.back.cont.entity.ContentCode;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
/**
 * 
 * 短信内容相关Service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-25 下午02:58:59
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Service
public class ContSmsService {
	@Autowired
	private LogUtils logUtils;
	
	@Autowired
	private DbHelper dbHelper;
	
	@Autowired
	private ContSmsDao contSmsDao;
	
	@Autowired
	private ContentCodeDao contentCodeDao;
	
	/**
	 * 短信内容查询
	 * @param contSms    短信内容实体类
	 * @param pageNo    当前页
	 * @return
	 * @throws SQLException
	 */
	public Page<ContSms> findContSms(ContSms contSms, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cont.*, unit.name unitName, src.name srcName from t_cont_sms cont, t_sys_unit unit, t_cont_code src where 1=1 ");
		sql.append("   and cont.unit_id = unit.id ");
		sql.append("   and cont.content_src = src.code ");
		List params = new ArrayList();
		if(null != contSms){
			if (StringUtils.isNotEmpty(contSms.getContentSrc())) {
				sql.append(" and cont.content_src = ? ");
				params.add(contSms.getContentSrc());
			}
			if (StringUtils.isNotEmpty(contSms.getUnitId())) {
				sql.append(" and cont.unit_id = ? ");
				params.add(contSms.getUnitId());
			}
			if (StringUtils.isNotEmpty(contSms.getContent())) {
				sql.append(" and cont.content like ? ");
				params.add("%" + contSms.getContent().trim() + "%");
			}
			if (StringUtils.isNotEmpty(contSms.getKeyword())) {
				sql.append(" and cont.keyword like ? ");
				params.add("%" + contSms.getKeyword().trim() + "%");
			}
			if (null != contSms.getStatus()) {
				sql.append(" and cont.status = ? ");
				params.add(contSms.getStatus());
			}
			if (StringUtils.isNotEmpty(contSms.getStartTime())) {
				sql.append(" and cont.start_time >= ? ");
				params.add(contSms.getStartTime().replaceAll("-", "") + "000000");
			}
			if (StringUtils.isNotEmpty(contSms.getEndTime())) {
				sql.append(" and cont.end_time <= ? ");
				params.add(contSms.getEndTime().replaceAll("-", "") + "235959");
			}
			if (StringUtils.isNotEmpty(contSms.getUnitName())) {
				sql.append(" and unit.name like ? ");
				params.add("%" + contSms.getUnitName().trim() + "%");
			}
			if (StringUtils.isNotEmpty(contSms.getSrcName())) {
				sql.append(" and src.name like ? ");
				params.add("%" + contSms.getSrcName().trim() + "%");
			}
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODUE_SMSCONTENT));// 控制数据访问
	
		sql.append(" order by cont.id desc ");
		return dbHelper.getPage(sql.toString(), ContSms.class, pageNo, Page.DEFAULT_PAGESIZE, params.toArray());
	}
	
	/**
	 * 查询指定短信内容
	 * @param id    短信内容ID
	 */
	@Transactional
	public ContSms findOneContSms(Long id) {
		ContSms contSms = contSmsDao.findOne(id);
		return contSms;		
	}

	
	
	/**
	 * 根据code查找短信内容源
	 * @return
	 */
	@Transactional
	public ContentCode findCodeBySrc(String code){
		ContentCode contentCode = contentCodeDao.findContentCodeByCode(code);
		return contentCode;
	}
	
	/**
	 * 短信内容录入
	 * @param contSms    短信内容实体类
	 */
	@Transactional
	public ContSms saveContSms(ContSms contSms) {
		if(1L == contSms.getStatus()){
			contSms.setAdvice(null);
		}
		//保存短信内容
		contSms = contSmsDao.save(contSms);
		return contSms;		
	}
	
	/**
	 * 删除短信内容
	 * @param id
	 */
	@Transactional
	public void deleteContSms(Long id) {
		contSmsDao.delete(id);
		logUtils.logDelete("成功删除短信内容", "ID:" + id);	
	}

}

