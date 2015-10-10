package com.cplatform.b2c.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

/**
 * 劳保商品. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-12 上午9:32:50
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class TBonusTerminalService {

	@Autowired
	private MemberCenterService memberCenterService;

	Logger logger = Logger.getLogger(TBonusTerminalService.class);

	/**
	 * 判断是否是劳保商品
	 * 
	 * @param item
	 */
	public boolean isLabourGoods(ItemSaleDataDTO item) {
		logger.info("判断是否是劳保商品");
		if (null != item && null != item.getItem() && Constants.LABOUR_BUYING.equals(item.getItem().getIseckill())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是劳保商品，并且用户是否有权限购买
	 * 
	 * @param userInfo
	 * @param item
	 * @return true : 是劳保商品且用户有购买的权限；false : 不是劳保商品或是劳保商品但用户没有购买权限
	 */
	public Boolean isBonusTerminal(LoginUserBean userInfo) {
		logger.info("劳保商品判断用户是否有权限");
		// 目标库用户判断
		boolean flag = false;
		if (null != userInfo && StringUtils.isNotBlank(userInfo.getTerminalId())) {
			flag = memberCenterService.getBonusTerminal(Long.parseLong(userInfo.getTerminalId()));
		}
		logger.info("判断用户是否可以参加劳保活动资格flag====" + flag);
		return flag;
	}

	public Boolean isBonusTerminal(SessionUser userInfo) {
		logger.info("劳保商品判断用户是否有权限");
		// 目标库用户判断
		boolean flag = false;
		if (null != userInfo && StringUtils.isNotBlank(userInfo.getTerminalId())) {
			flag = memberCenterService.getBonusTerminal(Long.parseLong(userInfo.getTerminalId()));
		}
		logger.info("判断用户是否可以参加劳保活动资格flag====" + flag);
		return flag;
	}

	/**
	 * 是劳保商品，用户没有购买权限返回去
	 * 
	 * @return
	 */
	public Object unBonusTerminal() {
		return JsonRespWrapper.failure("很抱歉，该商品仅限特定用户购买，还有更多优质商品等着您，快去看看吧！");
	}
}
