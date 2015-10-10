package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.constant.StoreIndexOrder;
import com.cplatform.b2c.model.TGoodShelf;
import com.cplatform.b2c.model.TGoodshelfGoodsRel;
import com.cplatform.b2c.model.TShop;
import com.cplatform.b2c.model.TShopCustomerService;
import com.cplatform.b2c.model.TShopHomepageShow;
import com.cplatform.b2c.model.TShopSettings;
import com.cplatform.b2c.model.TStore;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.repository.TGoodShelfDao;
import com.cplatform.b2c.repository.TGoodshelfGoodsRelDao;
import com.cplatform.b2c.repository.TShopCustomerServiceDao;
import com.cplatform.b2c.repository.TShopDao;
import com.cplatform.b2c.repository.TShopHomepageShowDao;
import com.cplatform.b2c.repository.TShopSettingsDao;
import com.cplatform.b2c.repository.TStoreDao;
import com.cplatform.b2c.repository.VSearchGoodDao;

/**
 * 商户信息相关类
 * <p>
 * Copyright: Copyright (c) 2013-6-1 上午10:12:16
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class ShopService {

	Logger logger = Logger.getLogger(ShopService.class);

	@Autowired
	private TGoodShelfDao tGoodShelfDao;

	@Autowired
	private TShopHomepageShowDao tShopHomepageShowDao;

	@Autowired
	private TShopDao tShopDao;

	@Autowired
	private TStoreDao tStoreDao;

	@Autowired
	private VSearchGoodDao vSearchGoodDao;

	@Autowired
	private TGoodshelfGoodsRelDao tGoodshelfGoodsRelDao;

	@Autowired
	private TShopSettingsDao tShopSettingsDao;

	@Autowired
	private TShopCustomerServiceDao tShopCustomerServiceDao;

	@Autowired
	private CommonCacheService commonCacheService;

	public List<TGoodShelf> getList(String shopId) {
		return tGoodShelfDao.getList(shopId);
	}

	public TShop getShop(String shopId) {
		return tShopDao.get(shopId);
	}

	public TStore getStore(String storeId) {
		return tStoreDao.get(storeId);
	}

	public List<TShopHomepageShow> getShelfList(Integer shopId, Integer shopClass) {
		return tShopHomepageShowDao.getList(shopId, shopClass);
	}

	public List<TGoodshelfGoodsRel> getShelfGoodList(Integer shelfId) {
		return tGoodshelfGoodsRelDao.getList(shelfId);
	}

	public List<VSearchGood> getProductList(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return null;
		}
		return vSearchGoodDao.getList(ids);
	}

	public List<VSearchGood> getProductList(Integer[] ids, int maxResults) {
		if (ids == null || ids.length == 0) {
			return null;
		}
		return vSearchGoodDao.getList(ids, maxResults);
	}

	public Integer[] getGIdsByShelfId(Integer shelfId, Integer shopClass) {
		if (shelfId == null || shopClass == null) {
			return null;
		}

		Integer[] shelfIds = new Integer[] {};
		List<TGoodShelf> shelfList = tGoodShelfDao.getTypeListByParent(shelfId, shopClass);
		if (shelfList != null && shelfList.size() > 0) {
			for (TGoodShelf shelf : shelfList) {
				shelfIds = (Integer[]) ArrayUtils.add(shelfIds, shelf.getId());
			}
		}

		if (shelfIds == null || shelfIds.length == 0) {
			return null;
		}

		List<TGoodshelfGoodsRel> goodList = tGoodshelfGoodsRelDao.getList(shelfIds);
		Integer[] GIds = new Integer[] {};
		if (goodList != null && goodList.size() > 0) {
			for (TGoodshelfGoodsRel good : goodList) {
				GIds = (Integer[]) ArrayUtils.add(GIds, good.getGoodId());
			}
		}
		return GIds;
	}

	public List<VSearchGood> getProductListBy(Integer GStoreId, String GName, String GShopPriceLow, String GShopPriceHigh, Integer[] GIds,
	        StoreIndexOrder order, int firstResult, int maxResults) {
		return vSearchGoodDao.getListBy(GStoreId, GName, GShopPriceLow, GShopPriceHigh, GIds, order, firstResult, maxResults);
	}

	public int getProductCountBy(Integer GStoreId, String GName, String GShopPriceLow, String GShopPriceHigh, Integer[] GIds) {
		return vSearchGoodDao.getCountBy(GStoreId, GName, GShopPriceLow, GShopPriceHigh, GIds);
	}

	public TShopSettings getTShopSettings(Integer shopId, Integer shopClass) {
		return tShopSettingsDao.get(shopId, shopClass);
	}

	public List<TShopCustomerService> getFeixinList(Integer shopId, Integer shopClass) {
		return tShopCustomerServiceDao.getList(shopId, shopClass);
	}

	public List<Map<String, Object>> getOtherItemsByShopId(Long storeId, Long saleId) throws SQLException {
		return tShopDao.getOtherItemsByShopId(storeId, saleId);
	}

	public List<Map<String, Object>> getOtherAgentItemsByShopId(Long storeId, Long saleId) throws SQLException {
		return tShopDao.getOtherAgentItemsByShopId(storeId, saleId);
	}

	/**
	 * 获取商户所在省份的编号
	 * 
	 * @param storeId
	 * @return
	 * @throws SQLException
	 */
	public String getProvinceCodeByStoreId(Long storeId) throws SQLException {
		Map<String, String> map = tShopDao.getProvinceCodeByStoreId(storeId);
		if (null == map || map.isEmpty()) {
			return null;
		}
		return this.getProvinceCodeByRegionCode(null, map);
	}

	/**
	 * 根据地区编号查找到对应的省编号
	 * 
	 * @param regionCode
	 * @return
	 */
	public String getProvinceCodeByRegionCode(String regionCode, Map<String, String> cmap) {
		String provinceCode = null;
		Map<String, String> map = null;
		if (null != cmap && !cmap.isEmpty()) {
			logger.info("获取商户所在省份的编号");
			map = cmap;
		} else {
			logger.info("根据地区编号查找到对应的省编号：regionCode：" + regionCode);
			map = commonCacheService.getRegionByRegionCode(regionCode);
		}
		if (null != map && !map.isEmpty()) {
			if (StringUtils.isNotBlank(map.get("region_level"))) {
				int regionLevel = Integer.parseInt(map.get("region_level"));
				if (regionLevel > 1) {
					for (int i = regionLevel; i > 1; i--) {
						map = commonCacheService.getRegionByRegionCode(map.get("parent_region"));
					}
					provinceCode = this.getProvinceCode(map, provinceCode);
				} else {
					provinceCode = this.getProvinceCode(map, provinceCode);
				}
			}
		}
		return provinceCode;
	}

	/**
	 * 对查询结果处理，获取省编号
	 * 
	 * @param map
	 * @param provinceCode
	 * @return
	 */
	private String getProvinceCode(Map<String, String> map, String provinceCode) {
		if (null != map && !map.isEmpty() && StringUtils.isNotBlank(map.get("region_level"))) {
			if ("0".equals(map.get("region_level"))) {
				provinceCode = map.get("region_code");
			} else if ("1".equals(map.get("region_level"))) {
				provinceCode = map.get("parent_region");
			}
		}
		return provinceCode;
	}
}
