package com.cplatform.b2c.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.dto.ItemSaleDataDTO;

/**
 * 商品详情页展示. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-4-2 下午5:29:58
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class ProductService {

	Logger logger = Logger.getLogger(ProductService.class);

	/**
	 * 限时抢购商品详情页面限时
	 * 
	 * @param level
	 * @param item
	 * @return
	 */
	public String getPromptPrice(String level, ItemSaleDataDTO item) {
		StringBuilder promptPrice = new StringBuilder();
		promptPrice.setLength(0);

		BigDecimal market_price = item.getItem().getMarketPrice();
		BigDecimal shop_price = item.getItem().getShopPrice();
		if (market_price.compareTo(shop_price) > 0) {
			promptPrice.append("<li id=\"summary-market-price\">");
			promptPrice.append("<div class=\"dt\">商城价：</div>");
			promptPrice.append("<div class=\"dd\"><span class=\"breaktext\">￥");
			promptPrice.append(market_price);
			promptPrice.append("</span></div></li>");
		}

		logger.info("该限时抢购商品限制会员购买---会员限制：" + level + "，商品编号：" + item.getItem());
		// 判断当前商品是否限制会员购买
		promptPrice.append("<li id=\"summary-price\">");
		if (StringUtils.isNotBlank(item.getItem().getUserLevels()) && !"L0".equals(item.getItem().getUserLevels())) {
			// 有会员限制，此处item_sale表中shop_price，与item_price表中会员价都是通过一个字段传入的。可以这样判断
			promptPrice.append("<div class=\"dt\">会员特价：</div>");
			promptPrice.append("<div class=\"dd\"><strong class=\"p-price\">");
			promptPrice.append("<span class=\"p-price\">");
		} else {
			promptPrice.append("<div class=\"dt\">限时特价：</div>");
			promptPrice.append("<div class=\"dd\"><strong class=\"p-price\">");
			promptPrice.append("<span class=\"col_link\">");
		}
		promptPrice.append("￥").append(shop_price);
		promptPrice.append(StringUtils.equals("L0", level) ? "" : "");
		promptPrice.append("</span></strong><em id=\"a-tips\">&nbsp;</em></div>");
		promptPrice.append("</li>");
		return promptPrice.toString();
	}
}
