package com.cplatform.mall.back.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.mall.back.dao.AuditStepDao;
import com.cplatform.mall.back.entity.AuditStep;
import com.cplatform.util2.TimeStamp;

/**
 * 审核步骤 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 下午2:54:49
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service("auditStepService")
public class AuditStepService {

	/**
	 * logger
	 */
	protected static final Log logger = LogFactory.getLog(AuditStepService.class);

	@Autowired
	private AuditStepDao auditStepDao;

	/**
	 * 保存审核记录
	 * @param bsId 业务数据id
	 * @param auditUserId 审核人
	 * @param bsTabel 业务表名
	 * @param bsType  业务类型
	 * @param remark  审核说明
	 */
	public void saveAuditStep(Long bsId, Long auditUserId, String bsTabel, Long bsType, String remark) {
		AuditStep audit = new AuditStep();
		audit.setBsId(bsId);
		audit.setAuditUserId(auditUserId);
		audit.setBsTabel(bsTabel);
		audit.setBsType(bsType);
		audit.setRemark(remark);
		audit.setUpdateTime(TimeStamp.getTime(14));
		auditStepDao.save(audit);
	}
}
