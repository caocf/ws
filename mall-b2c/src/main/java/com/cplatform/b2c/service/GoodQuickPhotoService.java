package com.cplatform.b2c.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.HttpClientUtils;

@Service
public class GoodQuickPhotoService {

	@Autowired
	AppConfig appConfig;

	private final Logger logger = Logger.getLogger(getClass());

	public String getQuickPhoto(String goodId, String verisonId) {
		String url = appConfig.getQuickPhotoURL();

		String param = "goods_id=" + goodId + "&version=" + verisonId;

		logger.info("查询商品快照请求参数" + param);

		try {
			String respJson = HttpClientUtils.httpGet(url, param);

			logger.info("查询商品快照返回参数" + respJson);

			return respJson;

		}
		catch (IOException e) {
			logger.error("查询商品快照内部接口异常", e);
			return "";
		}
	}
}
