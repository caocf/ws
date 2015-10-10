package com.cplatform.mall.back.item.web.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.mall.back.item.dao.HisunProductionLinkDao;
import com.cplatform.mall.back.item.entity.HisunProductionLink;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.item.service.ItemManageService;
import com.cplatform.mall.back.store.dao.StoreFeeDao;
import com.cplatform.mall.back.store.entity.StoreFee;
import com.cplatform.mall.back.utils.Constants;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-8-8 下午04:50:19
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: macl@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Component
public class ItemFeeValidator {

	@Autowired
	private StoreFeeDao storeFeeDao;

	@Autowired
	private HisunProductionLinkDao hisunProductionLinkDao;
	
	@Autowired
	private ItemManageService itemManageService;

	/**
	 * 商品上架的时候，判断商品的商户费率，如果商户费率是按"商品结算"，则检查该商品是否关联了商品协议。
	 * 
	 * @author macl@c-platform.com
	 */
	public String validateIsLinkSettle(ItemSale itemSale) {

		if (null == itemSale.getFeeType() || "".equals(itemSale.getFeeType())) {
			return itemSale.getName()+"尚未选择费率分类，无法上架！";
		}
		// 商户费率
		List<StoreFee> storeFee = storeFeeDao.findByProductionTypeAndStoreId(itemSale.getFeeType().toString(), itemSale.getStoreId());
		if (null == storeFee || storeFee.size() <= 0 || StringUtils.isEmpty(storeFee.get(0).getClearType())) {
			return itemSale.getName()+"费率清算类别(P/C)为空，无法上架！";
		}
		
		/**
		 * 商品上架的时候，对商户基本信息清算状态，商户结算信息清算状态，商户生效日期，做判断。只有符合条件的才可以上架
		 * @author liujun
		 */
		boolean isOnline=false;
		boolean isSettleOnline=false;
		boolean isDateOnline=false;
		
		try {
			isOnline=itemManageService.isBolleanItemSaleOnline(itemSale.getId());
			if (!isOnline) {
				return itemSale.getName()+"请检查商户清算状态！";
			}
			
			isSettleOnline=itemManageService.isBolleanItemSaleSettleOnline(itemSale.getId());
			if (!isSettleOnline) {
				return itemSale.getName()+"请检查商户结算的清算状态！";
			}
			
			isDateOnline=itemManageService.isBolleanItemSaleDateOnline(itemSale.getId());
			if (!isDateOnline) {
				return itemSale.getName()+"请检查商户结算的生效日期，结算日期！";
			}
		} catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
		// 如果商户费率是按“商品结算”
		if (Constants.STORE_FEE_CLEAR_TYPE_P.equals(storeFee.get(0).getClearType())) {

			HisunProductionLink productionLink = hisunProductionLinkDao.findByItemIdAndProductionType(itemSale.getId(), itemSale.getFeeType()
			        .toString());

			if (null == productionLink) {
				return itemSale.getName()+"尚未关联商品协议，无法上架！";
			}
			// 上架前需要判断高阳同步状态
			if (itemSale.getSyncGyFlag() != 3L) {
				return itemSale.getName()+"商品状态未和清算系统同步，无法上架！";
			}
			// 按商品结算，结算价==协议价
			if (null != productionLink.getSettlePrice() || !"".equals(productionLink.getSettlePrice())) {
				itemSale.setSettlePrice(productionLink.getSettlePrice());
			}

		} else {
			// 如果商户费率是按商品分类结算，结算价==商城价
			itemSale.setSettlePrice(itemSale.getShopPrice());
		}
		return "";
	}
}
