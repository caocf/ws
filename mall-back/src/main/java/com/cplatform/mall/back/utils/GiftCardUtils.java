package com.cplatform.mall.back.utils;

import jxl.common.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.gift_card_interface.GiftCardClient;
import com.cplatform.gift_card_interface.bean.BaseResponse;
import com.cplatform.gift_card_interface.bean.CardOperateRequest;

/**
 * 
 * Title.礼品卡功能高阳接口<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-9-18 下午01:43:18
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: macl@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Component
public class GiftCardUtils {
	private static Logger log = Logger.getLogger(GiftCardUtils.class);

	@Autowired
	private AppConfig config;

	@Autowired
	private LogUtils logUtils;

	private void init() {
		try {
			// 需要完成AppConfig方法
			GiftCardClient.init(config.getGiftCardSyncIp(), config
					.getGiftCardSyncPort(), config.getGiftCardSyncPlatCode(),
					config.getGiftCardSyncMd5Key(), config
							.getGiftCardSyncDesKey());
		} catch (Exception e) {
			logUtils.logAdd("初始化礼品卡高阳接口类失败", e.getMessage());
			log.error("初始化礼品卡高阳接口类失败：" + e.getMessage());
		}

	}

	public boolean operateCard(String serialNo, String type, String content) {
		boolean flag = false; 
		init();
		CardOperateRequest request = new CardOperateRequest();
		request.setCardno(serialNo);
		request.setType(type); // 0 激活
		request.setOrderno(serialNo);
		request.setContent("");

		BaseResponse response = null;
		try {
			response = GiftCardClient.getInstance().sendRequest(request, 10000);
			 if (null != response) {
					if ("SUCCESS".equals(response.getRetcode().toUpperCase())) {
						flag = true;
						logUtils.logAdd(content, content + "成功:serialNo:" + serialNo);
					}
				}
		} catch (Exception e) {
			log.error("调用礼品卡激活接口失败：：" + e.getMessage());
			logUtils.logAdd(content, "调用" + content + "接口失败：：" + e.getMessage());
		}
		 return flag;
	}
}

