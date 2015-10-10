package com.cplatform.mall.back.item.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.dao.AuditStepDao;
import com.cplatform.mall.back.entity.AuditStep;
import com.cplatform.mall.back.item.dao.ItemPostAreaLinkDao;
import com.cplatform.mall.back.item.dao.ItemPriceDao;
import com.cplatform.mall.back.item.dao.ItemSaleAreaLinkDao;
import com.cplatform.mall.back.item.dao.ItemSaleDao;
import com.cplatform.mall.back.item.dao.ItemSaleExtDao;
import com.cplatform.mall.back.item.dao.ItemSaleShopLinkDao;
import com.cplatform.mall.back.item.dao.ItemSaleUserAreaLinkDao;
import com.cplatform.mall.back.item.dao.ItemSaleUserLevelaLinkDao;
import com.cplatform.mall.back.item.dao.ItemVerifyShopLinkDao;
import com.cplatform.mall.back.item.entity.ItemPostAreaLink;
import com.cplatform.mall.back.item.entity.ItemPrice;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.item.entity.ItemSaleAreaLink;
import com.cplatform.mall.back.item.entity.ItemSaleExt;
import com.cplatform.mall.back.item.entity.ItemSaleShopLink;
import com.cplatform.mall.back.item.entity.ItemSaleUserAreaLink;
import com.cplatform.mall.back.item.entity.ItemSaleUserLevelaLink;
import com.cplatform.mall.back.item.entity.ItemVerifyShopLink;
import com.cplatform.mall.back.store.dao.StoreDao;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.sys.dao.SysFeeDao;
import com.cplatform.mall.back.sys.entity.SysFee;
import com.cplatform.mall.back.utils.PageStaticInterface;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.util2.TimeStamp;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-7-3 下午2:43:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * 
 * @deprecated 这个类不在维护，请使用ItemManageService
 */
@Deprecated
@Service
public class ItemSaleService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private ItemSaleDao itemSaleDao;

	/** 物流配送区域 **/
	@Autowired
	private ItemPostAreaLinkDao postAreaLinkDao;

	/** 验证门店 **/
	@Autowired
	private ItemVerifyShopLinkDao verifyShopLinkDao;

	/** 销售门店 **/
	@Autowired
	private ItemSaleShopLinkDao saleShopLinkDao;

	/** 购买用户地市限制 **/
	@Autowired
	private ItemSaleUserAreaLinkDao userAreaLinkDao;

	/** 商品会员购买限制 **/
	@Autowired
	private ItemSaleUserLevelaLinkDao userLevelaLinkDao;

	/** 销售区域 **/
	@Autowired
	private ItemSaleAreaLinkDao saleAreaLinkDao;

	@Autowired
	private StoreDao storeDao;

	@Autowired
	private AuditStepDao stepDao;

	@Autowired
	private ItemPriceDao priceDao;

	@Autowired
	private SysFeeDao sysFeeDao;

	@Autowired
	private PageStaticInterface pageStatic;

	@Autowired
	private StoreService storeService;

	@Autowired
	private ItemManageService itemManageService;

	@Autowired
	private ItemSaleExtDao saleExtDao;

	public List<SysFee> getAllSysFeeList() {
		return this.sysFeeDao.getAllFee();
	}

	public SysFee findSysFeeById(Long id) {
		return this.sysFeeDao.findOne(id);
	}

	public ItemSale findItemSaleById(Long id) {
		return this.itemSaleDao.findOne(id);
	}

	public ItemSale save(ItemSale sale) {
		return this.itemSaleDao.save(sale);
	}

	/**
	 * 查询商品信息
	 * 
	 * @param itemOrg
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<ItemSale> listSale(ItemSale itemOrg, int pageNo, int pageSize) throws SQLException {
		itemOrg.setStatus(ItemSale.STATUS_BASE_AUDIT_PASS);
		return listItemSale(itemOrg, pageNo, pageSize);
	}

	/**
	 * 保存商品发布信息
	 * 
	 * @param itemSale
	 * @throws IOException
	 */

	@Transactional
	public void saveItemSale(ItemSale itemSale) throws IOException {

		itemSale.setStatus(5L);
		itemSale.setIsValid(0L);
		itemSale.setSyncGyFlag(0l);
		itemSale.setSaleStartTime(itemSale.getSaleStartTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		itemSale.setSaleStopTime(itemSale.getSaleStopTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		if (null != itemSale.getVerifyStartTime() && !"".equals(itemSale.getVerifyStartTime())) {
			itemSale.setVerifyStartTime(itemSale.getVerifyStartTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		}
		if (null != itemSale.getVerifyStopTime() && !"".equals(itemSale.getVerifyStopTime())) {
			itemSale.setVerifyStopTime(itemSale.getVerifyStopTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		}

		ItemSale dto = itemSaleDao.save(itemSale);
		// 不需要物流配送把设置的配送区域删除
		postAreaLinkDao.deleteBySaleId(dto.getId());
		verifyShopLinkDao.deleteBySaleId(dto.getId());
		saleShopLinkDao.deleteBySaleId(dto.getId());
		userAreaLinkDao.deleteBySaleId(dto.getId());
		userLevelaLinkDao.deleteBySaleId(dto.getId());
		saleAreaLinkDao.deleteBySaleId(dto.getId());
		priceDao.deleteBySaleId(dto.getId());
		// 判断发码方式 sendCodeMode
		if (itemSale.getSendCodeMode() == 0)// 不发码
		{
			itemSale.setVerifyStartTime("");
			itemSale.setVerifyStopTime("");
			itemSale.setSendCodeChannel(null);
			itemSale.setSendCodeSrc(null);
			if (itemSale.getPostFlag() == 1) {// 需要物流配送
				String[] postArea = itemSale.getPostAreaCode().split(",");
				for (String str : postArea) {
					ItemPostAreaLink postAreaLink = new ItemPostAreaLink();
					postAreaLink.setSaleId(dto.getId());
					postAreaLink.setRegionCode(str);
					postAreaLinkDao.save(postAreaLink);
				}
			}

		} else {

			// 保存验证门店
			String[] verifyShopIds = itemSale.getVerifyShopIds().split(",");
			for (String str : verifyShopIds) {
				ItemVerifyShopLink postAreaLink = new ItemVerifyShopLink();
				postAreaLink.setSaleId(dto.getId());
				postAreaLink.setShopId(Long.valueOf(str));
				postAreaLink.setStoreId(itemSale.getStoreId());
				verifyShopLinkDao.save(postAreaLink);
			}

		}
		// 销售區域
		if (null != itemSale.getSaleAreaCode() && !"".equals(itemSale.getSaleAreaCode())) {
			String[] saleAreaArea = itemSale.getSaleAreaCode().split(",");
			for (String str : saleAreaArea) {
				ItemSaleAreaLink sAreaLink = new ItemSaleAreaLink();
				sAreaLink.setSaleId(dto.getId());
				sAreaLink.setRegionCode(str);
				saleAreaLinkDao.save(sAreaLink);
			}
			// 价格
			String priceArry[] = itemSale.getPrice().split(",");
			String priceTypeArry[] = itemSale.getPriceTypeCode().split(",");
			for (int i = 0; i < priceArry.length; i++) {
				ItemPrice itemPrice = new ItemPrice();
				itemPrice.setAreaCode(saleAreaArea[0]);
				itemPrice.setItemId(itemSale.getId());
				itemPrice.setPrice(Float.valueOf(priceArry[i]));
				itemPrice.setPriceTypeCode(priceTypeArry[i]);
				itemPrice.setSaleId(dto.getId());
				itemPrice.setStoreId(itemSale.getStoreId());
				priceDao.save(itemPrice);
			}
		}

		// 销售门店
		String[] saleShopIds = itemSale.getSaleShopIds().split(",");
		for (String str : saleShopIds) {
			ItemSaleShopLink postAreaLink = new ItemSaleShopLink();
			postAreaLink.setSaleId(dto.getId());
			postAreaLink.setShopId(Long.valueOf(str));
			postAreaLink.setStoreId(itemSale.getStoreId());
			saleShopLinkDao.save(postAreaLink);
		}
		// 购买区域限制
		if (itemSale.getBuyLimit().indexOf("1") != -1) {
			String[] postArea = itemSale.getAreaLimitCode().split(",");
			for (String str : postArea) {
				ItemSaleUserAreaLink userAreaLink = new ItemSaleUserAreaLink();
				userAreaLink.setSaleId(dto.getId());
				userAreaLink.setRegionCode(str);
				userAreaLinkDao.save(userAreaLink);
			}
		}
		// 会员限制
		if (itemSale.getBuyLimit().indexOf("0") != -1) {
			String[] postUser = itemSale.getUserLimitCode().split(",");
			for (String str : postUser) {
				ItemSaleUserLevelaLink userLevelaLink = new ItemSaleUserLevelaLink();
				userLevelaLink.setSaleId(dto.getId());
				userLevelaLink.setUserLevel(str);
				userLevelaLinkDao.save(userLevelaLink);
			}
		}
		if (itemSale.getBuyLimit().indexOf("2") == -1) {
			itemSale.setUserPerBuyNum(null);
		}

		// 运费
		ItemSaleExt itemSaleExt = saleExtDao.findBySaleId(dto.getId());

		if (null == itemSaleExt) {
			itemSaleExt = new ItemSaleExt();
			itemSaleExt.setSaleId(dto.getId());
			itemSaleExt.setSaleNum(0l);
			itemSaleExt.setClickNum(0l);
			itemSaleExt.setCommentNum(0l);
			itemSaleExt.setUserNum(0l);
			itemSaleExt.setCollectNum(0l);
			itemSaleExt.setRank(Double.parseDouble("0"));
		}
		// itemSaleExt.setLogisticsFee(itemSale.getLogisticsFee() == null ? 0 :
		// itemSale.getLogisticsFee());
		// itemSaleExt.setLogisticsFeeType(itemSale.getLogisticsType() == null ?
		// 0 : itemSale.getLogisticsType());
		saleExtDao.save(itemSaleExt);

		itemSaleDao.save(itemSale);
	}

	/**
	 * 查询商品发信息
	 * 
	 * @param itemOrg
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<ItemSale> listItemSale(ItemSale itemSale, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder(100);
		sql.append("select sa.*,st.name as storename,sa.name as itemName ,stype.name as typeName")
		        .append("	from t_item_sale sa  join  t_store st on  sa.store_id = st.id  ")
		        .append(" left join  t_sys_type stype  on  sa.type_Id = stype.id where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if (StringUtils.isNotEmpty(itemSale.getStoreName())) {
			sql.append(" and st.name like ? ");
			params.add("%" + itemSale.getStoreName() + "%");
		}

		if (StringUtils.isNotEmpty(itemSale.getItemName())) {
			sql.append(" and sa.name like ? ");
			params.add("%" + itemSale.getItemName() + "%");
		}

		if (StringUtils.isNotEmpty(itemSale.getItemName())) {
			sql.append(" and sa.store_id = ? ");
			params.add(itemSale.getStoreId());
		}

		if (StringUtils.isNotEmpty(itemSale.getSaleStartTime())) {
			sql.append(" and sa.sale_start_time >= ? ");
			params.add(itemSale.getSaleStartTime() + "000000");
		}
		if (StringUtils.isNotEmpty(itemSale.getSaleStopTime())) {
			sql.append(" and sa.sale_stop_time <= ? ");
			params.add(itemSale.getSaleStopTime() + "235959");
		}

		if (StringUtils.isNotEmpty(itemSale.getVerifyCodeType() == null ? "" : itemSale.getVerifyCodeType().toString())) {
			sql.append(" and sa.verify_code_type = ? ");
			params.add(itemSale.getVerifyCodeType());
		}

		if (StringUtils.isNotEmpty(itemSale.getSendCodeMode() == null ? "" : itemSale.getSendCodeMode().toString())) {
			sql.append(" and sa.send_code_mode = ? ");
			params.add(itemSale.getSendCodeMode());
		}
		if (itemSale.getSendCodeChannel() != null) {
			sql.append(" and sa.send_code_channel = ? ");
			params.add(itemSale.getSendCodeChannel());
		}
		if (itemSale.getStatus() != null) {
			sql.append(" and sa.status = ? ");
			params.add(itemSale.getStatus());
		}
		if (itemSale.getSyncGyFlag() != null) {
			sql.append(" and sa.sync_gy_flag = ? ");
			params.add(itemSale.getSyncGyFlag());
		}
		if (itemSale.getStoreId() != null) {
			sql.append(" and sa.store_id = ? ");
			params.add(itemSale.getStoreId());
		}
		if (itemSale.getGroupFlag() != null) {
			sql.append(" and sa.group_flag = ? ");
			params.add(itemSale.getGroupFlag());
		}
		sql.append(" order by sa.id desc");
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_ITEM));// 控制数据访问
		return dbHelper.getPage(sql.toString(), ItemSale.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * <<<<<<< .mine 查询商品发信息
	 * 
	 * @param itemSale
	 * @return
	 * @throws SQLException
	 */

	public List<ItemSale> listItemSale(ItemSale itemSale) throws SQLException {
		StringBuilder sql = new StringBuilder(2000);
		sql.append("select sa.*,st.name as storename from t_item_sale sa, t_store st where sa.is_valid=1 and sa.store_id = st.id ");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(itemSale.getStoreName())) {
			sql.append(" and st.name like '%" + itemSale.getStoreName() + "%'");
		}

		if (StringUtils.isNotEmpty(itemSale.getName())) {
			sql.append(" and sa.name like '%" + itemSale.getName() + "%' ");
		}

		if (StringUtils.isNotEmpty(itemSale.getSaleStartTime())) {
			sql.append(" and sa.sale_start_time >= ? ");
			params.add(itemSale.getSaleStartTime() + "000000");
		}
		if (StringUtils.isNotEmpty(itemSale.getSaleStopTime())) {
			sql.append(" and sa.sale_stop_time <= ? ");
			params.add(itemSale.getSaleStopTime() + "235959");
		}

		if (StringUtils.isNotEmpty(itemSale.getStatus() == null ? "" : itemSale.getStatus().toString())) {
			sql.append(" and sa.status = ? ");
			params.add(itemSale.getStatus());
		}
		if (StringUtils.isNotEmpty(itemSale.getVerifyCodeType() == null ? "" : itemSale.getVerifyCodeType().toString())) {
			sql.append(" and sa.verify_code_type = ? ");
			params.add(itemSale.getVerifyCodeType());
		}

		if (StringUtils.isNotEmpty(itemSale.getSendCodeMode() == null ? "" : itemSale.getSendCodeMode().toString())) {
			sql.append(" and sa.send_code_mode = ? ");
			params.add(itemSale.getSendCodeMode());
		}
		sql.append(" order by sa.id desc");
		return dbHelper.getBeanList(sql.toString(), ItemSale.class, params.toArray());
		// getPage(sql.toString(), ItemSale.class, pageNo, pageSize,
		// params.toArray());
	}

	/**
	 * 查询商品发信息
	 * 
	 * @param itemSale
	 * @return
	 * @throws SQLException
	 */

	public Page<ItemSale> pageItemSale(ItemSale itemSale) throws SQLException {
		StringBuilder sql = new StringBuilder(2000);
		sql.append("select sa.*,st.name as storename from t_item_sale sa, t_store st where sa.is_valid=1 and sa.store_id = st.id ");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(itemSale.getStoreName())) {
			sql.append(" and st.name like '%" + itemSale.getStoreName() + "%'");
		}

		if (StringUtils.isNotEmpty(itemSale.getName())) {
			sql.append(" and sa.name like '%" + itemSale.getName() + "%' ");
		}

		if (StringUtils.isNotEmpty(itemSale.getSaleStartTime())) {
			sql.append(" and sa.sale_start_time >= ? ");
			params.add(itemSale.getSaleStartTime() + "000000");
		}
		if (StringUtils.isNotEmpty(itemSale.getSaleStopTime())) {
			sql.append(" and sa.sale_stop_time <= ? ");
			params.add(itemSale.getSaleStopTime() + "235959");
		}

		if (StringUtils.isNotEmpty(itemSale.getStatus() == null ? "" : itemSale.getStatus().toString())) {
			sql.append(" and sa.status = ? ");
			params.add(itemSale.getStatus());
		}
		if (StringUtils.isNotEmpty(itemSale.getVerifyCodeType() == null ? "" : itemSale.getVerifyCodeType().toString())) {
			sql.append(" and sa.verify_code_type = ? ");
			params.add(itemSale.getVerifyCodeType());
		}

		if (StringUtils.isNotEmpty(itemSale.getSendCodeMode() == null ? "" : itemSale.getSendCodeMode().toString())) {
			sql.append(" and sa.send_code_mode = ? ");
			params.add(itemSale.getSendCodeMode());
		}
		sql.append(" order by sa.id desc");
		return dbHelper.getPage(sql.toString(), ItemSale.class, 1, 20, params.toArray());
		// .getBeanList(sql.toString(), ItemSale.class, params.toArray());
	}

	/**
	 * ======= >>>>>>> .r16627 删除产品发布
	 * 
	 * @param id
	 * @throws IOException
	 */

	@Transactional
	public void deleteItemSale(Long id) throws Exception {
		ItemSale itemSale = itemSaleDao.findOne(id);
		itemSale.setIsValid(0l);
		itemSale.setStatus(-1L);
		itemSaleDao.save(itemSale);

	}

	/**
	 * 根据发布id查询
	 * 
	 * @param id
	 * @return
	 */
	public ItemSale getItemSale(Long id, String... args) {
		ItemSale itemSale = itemSaleDao.findOne(id);
		String buyLimit = "";
		try {
			if (args.length == 0) {

				// 已发布商品销售门店表
				String[] saleShop = select(id, getItemSaleShopLinkSql());
				if (null != saleShop && saleShop.length > 0) {
					itemSale.setSaleShopIds(saleShop[0]);
					itemSale.setSaleShopName(saleShop[1]);
				}

				// 已发布商品销售区域
				String[] saleArear = select(id, getItemSaleAreaLinkSql());
				if (null != saleArear && saleArear.length > 0) {
					itemSale.setSaleAreaCode(saleArear[0]);
					itemSale.setSaleAreaName(saleArear[1]);
				}
				// 已发布商品验证门店表
				String[] verifyShop = select(id, getItemVerifyShopLinkSql());
				if (null != verifyShop && verifyShop.length > 0) {
					itemSale.setVerifyShopIds(verifyShop[0]);
					itemSale.setVerifyShopName(verifyShop[1]);
				}
				// 已发布商品物流配送区域表
				String[] postArea = select(id, getItemPostAreaLinkSql());
				if (null != postArea && postArea.length > 0) {
					itemSale.setPostAreaCode(postArea[0]);
					itemSale.setPostArea(postArea[1]);
				}
			}
			// 已发布商品用户购买限制区域表
			String[] saleUserArea = select(id, getItemSaleUserAreaLinkSql());
			if (null != saleUserArea && !"".equals(saleUserArea[0])) {
				itemSale.setAreaLimitCode(saleUserArea[0]);
				itemSale.setAreaLimitName(saleUserArea[1]);
				buyLimit = buyLimit + "1";
			}

			// 购买会员限制
			String[] saleUserlevel = select(id, getItemSaleUserLevelLinkSql());
			if (null != saleUserlevel && !"".equals(saleUserlevel[0])) {
				itemSale.setUserLimitCode(saleUserlevel[0]);
				buyLimit = buyLimit + ",0";
			}

			// 单个用户购买数量
			if (itemSale.getUserPerBuyNum() != null) {
				buyLimit = buyLimit + ",2";
			}
			itemSale.setBuyLimit(buyLimit);

			// 运费
			// ItemSaleExt itemSaleExt = saleExtDao.findBySaleId(id);
			// itemSale.setLogisticsFee(itemSaleExt.getLogisticsFee());
			// itemSale.setLogisticsType(itemSaleExt.getLogisticsFeeType());

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemSale;
	}

	private String[] select(Long id, String sql) throws Exception {
		return dbHelper.getArray(sql, new Object[] { id });

	}

	private String getItemSaleShopLinkSql() {
		StringBuilder bufferSql = new StringBuilder(100);
		bufferSql.append("select to_char(wm_concat(l.shop_id)), to_char(wm_concat(s.name)) ");
		bufferSql.append("  from t_item_sale_shop_link l, t_shop s ");
		bufferSql.append(" where l.sale_id = ? ");
		bufferSql.append("   and l.shop_id = s.id");

		return bufferSql.toString();
	}

	private String getItemVerifyShopLinkSql() {
		StringBuilder bufferSql = new StringBuilder(100);
		bufferSql.append("select to_char(wm_concat(l.shop_id)), to_char(wm_concat(s.name)) ");
		bufferSql.append("  from t_item_verify_shop_link l, t_shop s ");
		bufferSql.append(" where l.sale_id = ? ");
		bufferSql.append("   and l.shop_id = s.id");
		return bufferSql.toString();
	}

	private String getItemPostAreaLinkSql() {
		StringBuilder bufferSql = new StringBuilder(100);
		bufferSql.append("select to_char(wm_concat(l.region_code)), to_char(wm_concat(s.region_name)) ");
		bufferSql.append("  from t_item_post_area_link l, t_sys_region s ");
		bufferSql.append(" where l.sale_id = ? ");
		bufferSql.append("   and l.region_code = s.region_code");

		return bufferSql.toString();
	}

	private String getItemSaleUserAreaLinkSql() {
		StringBuilder bufferSql = new StringBuilder(100);
		bufferSql.append("select to_char(wm_concat(l.region_code)), to_char(wm_concat(s.region_name)) ");
		bufferSql.append("  from t_item_sale_user_area_link l, t_sys_region s ");
		bufferSql.append(" where l.sale_id = ? ");
		bufferSql.append("   and l.region_code = s.region_code");

		return bufferSql.toString();
	}

	private String getItemSaleUserLevelLinkSql() {
		StringBuilder bufferSql = new StringBuilder(100);
		bufferSql.append("select to_char(wm_concat(user_level)) from t_item_sale_user_level_link where sale_id=? ");

		return bufferSql.toString();
	}

	private String getItemSaleAreaLinkSql() {
		StringBuilder bufferSql = new StringBuilder(100);
		bufferSql.append("select to_char(wm_concat(l.region_code)), to_char(wm_concat(s.region_name)) ");
		bufferSql.append("  from t_item_sale_area_link l, t_sys_region s ");
		bufferSql.append(" where l.sale_id = ? ");
		bufferSql.append("   and l.region_code = s.region_code");

		return bufferSql.toString();
	}

	public List<ItemPrice> getPriceList(Long saleId) {
		List<ItemPrice> list = new ArrayList<ItemPrice>();
		StringBuilder bufferSql = new StringBuilder(100);
		bufferSql.append("select t.*, pt.price_type ");
		bufferSql.append("  from t_item_price t ");
		bufferSql.append("  left join t_item_price_type pt on t.area_code = pt.area_code ");
		bufferSql.append("                                and t.price_type_code = pt.price_type_code");
		bufferSql.append("  where t.sale_id = ? ");
		try {
			list = dbHelper.getBeanList(bufferSql.toString(), ItemPrice.class, new Object[] { saleId });
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 审核产品发布
	 * 
	 * @param id
	 * @throws IOException
	 */

	@Transactional
	public void auditItemSale(Long id, Long feeType, String optype, AuditStep step, String buyLimit, String userLimitCode, String areaLimitCode,
	        String userPerBuyNum) throws Exception {
		ItemSale itemSale = itemSaleDao.findOne(id);
		itemSale.setFeeType(feeType);
		if ("sendAudit".equals(optype)) {// 送审
			Store store = storeDao.findOne(itemSale.getStoreId());
			if (store.getItemPublishAuditFlag() == 0) {// 需要审核
				itemSale.setStatus(6L);
			} else if (store.getItemPublishAuditFlag() == 1) {// 发布商品免审
				itemSale.setStatus(8L);
				itemSale.setIsValid(1L);// 直接上架
			}
		}
		if ("doAudit".equals(optype)) {// 审核
			if (step.getStatus() == 1) {
				itemSale.setStatus(8L);
				itemSale.setIsValid(1L);
			} else {
				itemSale.setStatus(9L);
			}
			step.setBsType(5l);
			step.setUpdateTime(TimeStamp.getTime(14));
			step.setBsId(id);
			step.setBsTabel("T_ITEM_SALE");
			stepDao.save(step);

			// 处理页面审核需要修改的部分
			// 购买区域限制
			userAreaLinkDao.deleteBySaleId(id);
			userLevelaLinkDao.deleteBySaleId(id);
			if (buyLimit.indexOf("1") != -1) {

				String[] postArea = areaLimitCode.split(",");
				for (String str : postArea) {
					ItemSaleUserAreaLink userAreaLink = new ItemSaleUserAreaLink();
					userAreaLink.setSaleId(id);
					userAreaLink.setRegionCode(str);
					userAreaLinkDao.save(userAreaLink);
				}
			}

			// 会员限制
			if (buyLimit.indexOf("0") != -1) {
				String[] postUser = userLimitCode.split(",");
				for (String str : postUser) {
					ItemSaleUserLevelaLink userLevelaLink = new ItemSaleUserLevelaLink();
					userLevelaLink.setSaleId(id);
					userLevelaLink.setUserLevel(str);
					userLevelaLinkDao.save(userLevelaLink);
				}
			}
			if (buyLimit.indexOf("2") != -1) {
				itemSale.setUserPerBuyNum(Long.valueOf(userPerBuyNum));
			}
		}
		itemSaleDao.save(itemSale);

	}

}
