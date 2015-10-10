package com.cplatform.mall.back.item.virtual.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.dao.AuditStepDao;
import com.cplatform.mall.back.entity.AuditStep;
import com.cplatform.mall.back.item.dao.HisunProductionLinkDao;
import com.cplatform.mall.back.item.dao.HisunProductionSettleDao;
import com.cplatform.mall.back.item.dao.ItemGroupLinkDao;
import com.cplatform.mall.back.item.dao.ItemManageDao;
import com.cplatform.mall.back.item.dao.ItemParamDao;
import com.cplatform.mall.back.item.dao.ItemPostAreaLinkDao;
import com.cplatform.mall.back.item.dao.ItemPriceDao;
import com.cplatform.mall.back.item.dao.ItemPriceTypeDao;
import com.cplatform.mall.back.item.dao.ItemPropertyDao;
import com.cplatform.mall.back.item.dao.ItemSaleAreaLinkDao;
import com.cplatform.mall.back.item.dao.ItemSaleExtDao;
import com.cplatform.mall.back.item.dao.ItemSalePaymentDao;
import com.cplatform.mall.back.item.dao.ItemSaleShopLinkDao;
import com.cplatform.mall.back.item.dao.ItemSaleUserAreaLinkDao;
import com.cplatform.mall.back.item.dao.ItemSaleUserLevelaLinkDao;
import com.cplatform.mall.back.item.dao.ItemTagDao;
import com.cplatform.mall.back.item.dao.ItemVerifyShopLinkDao;
import com.cplatform.mall.back.item.entity.HisunProductionLink;
import com.cplatform.mall.back.item.entity.HisunProductionSettle;
import com.cplatform.mall.back.item.entity.ItemGroupLink;
import com.cplatform.mall.back.item.entity.ItemParam;
import com.cplatform.mall.back.item.entity.ItemPostAreaLink;
import com.cplatform.mall.back.item.entity.ItemPrice;
import com.cplatform.mall.back.item.entity.ItemPriceType;
import com.cplatform.mall.back.item.entity.ItemPriceTypeOnArea;
import com.cplatform.mall.back.item.entity.ItemProperty;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.item.entity.ItemSaleAreaLink;
import com.cplatform.mall.back.item.entity.ItemSaleExt;
import com.cplatform.mall.back.item.entity.ItemSalePayment;
import com.cplatform.mall.back.item.entity.ItemSaleShopLink;
import com.cplatform.mall.back.item.entity.ItemSaleUserAreaLink;
import com.cplatform.mall.back.item.entity.ItemSaleUserLevelaLink;
import com.cplatform.mall.back.item.entity.ItemTag;
import com.cplatform.mall.back.item.entity.ItemVerifyShopLink;
import com.cplatform.mall.back.item.entity.LsArea;
import com.cplatform.mall.back.item.entity.LsCatalog;
import com.cplatform.mall.back.item.service.HisunProductionService;
import com.cplatform.mall.back.item.web.validator.ItemFeeValidator;
import com.cplatform.mall.back.model.ItemEventBean;
import com.cplatform.mall.back.store.dao.GoodShelfRelDao;
import com.cplatform.mall.back.store.dao.ShopDao;
import com.cplatform.mall.back.store.dao.StoreDao;
import com.cplatform.mall.back.store.entity.GoodsShelfRel;
import com.cplatform.mall.back.store.entity.Shop;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.StoreFeeService;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.sys.dao.SysFeeDao;
import com.cplatform.mall.back.sys.entity.SysFee;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.service.SysFeeService;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.sys.service.SysSearchIdxService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.Constants;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.PageStaticInterface;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.mall.back.websys.dao.SysFileImgDao;
import com.cplatform.mall.back.websys.dao.SysFileImgLinkDao;
import com.cplatform.mall.back.websys.dao.SysTypeItemParamDao;
import com.cplatform.mall.back.websys.entity.PageStaticInfo;
import com.cplatform.mall.back.websys.entity.SysFileImg;
import com.cplatform.mall.back.websys.entity.SysTypeItemParam;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.mall.back.websys.service.FloorCatalogService;
import com.cplatform.mall.back.websys.service.PageStaticManageService;
import com.cplatform.util2.FileTools;
import com.cplatform.util2.TimeStamp;

/**
 * 商品管理服务. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-5 下午3:44:47
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class VitualItemService {

	private static final Logger log = Logger.getLogger(VitualItemService.class);

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private BsFileService bsFileService;

	@Autowired
	private ItemManageDao itemManageDao;

	@Autowired
	private ItemPropertyDao itemPropertyDao;

	@Autowired
	private ItemTagDao itemTagDao;

	@Autowired
	private ItemGroupLinkDao itemGroupLinkDao;

	@Autowired
	private SysTypeItemParamDao sysTypeItemParamDao;

	@Autowired
	private ItemParamDao itemParamDao;

	@Autowired
	SysFileImgDao fileImgDao;

	@Autowired
	private StoreDao storeDao;

	@Autowired
	private AuditStepDao stepDao;

	@Autowired
	SysFileImgLinkDao fileLinkDao;

	@Autowired
	private StoreService storeService;

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
	private ItemPriceDao priceDao;

	@Autowired
	private ItemSaleExtDao saleExtDao;

	@Autowired
	private PageStaticInterface pageStatic;

	@Autowired
	private GoodShelfRelDao goodRelDao;

	@Autowired
	private HisunProductionService hisunProductionService;

	@Autowired
	private SysFeeDao sysFeeDao;

	@Autowired
	private FloorCatalogService floorCatalogService;

	@Autowired
	private PageStaticManageService pageStaticManageService;

	@Autowired
	private StoreFeeService storeFeeService;

	@Autowired
	private SysFeeService sysFeeService;

	@Autowired
	private HisunProductionLinkDao hisunProductionLinkDao;

	@Autowired
	private HisunProductionSettleDao hisunProductionSettleDao;

	@Autowired
	private ItemPriceTypeDao itemPriceTypeDao;

	@Autowired
	private SysRegionService sysRegionService;
	
	@Autowired
	private AppConfig appConfig;
	
	@Autowired
	private SysSearchIdxService searchIdxService;
	
	@Autowired
	private ItemFeeValidator itemFeeValidator;
	
	@Autowired
	private LogUtils logUtils;
	
	@Autowired
	private ItemSalePaymentDao itemSalePaymentDao;
	
	@Autowired
	private ShopDao shopDao;
	

	public ItemSale addOrUpdateItemSale(ItemSale po) {
		return this.itemManageDao.save(po);
	}

	/**
	 * 更新商品清结算状态
	 * 
	 * @param id
	 *            商品id
	 * @param status
	 *            更新后状态
	 * @return
	 */
	public ItemSale updateSaleGYStatus(Long id, Long status) {
		ItemSale ret = this.findItemSaleById(id);
		if (ret != null) {
			ret.setSyncGyFlag(status);
			return addOrUpdateItemSale(ret);
		}
		return ret;
	}

	public List<ItemParam> findItemParamListByItemId(Long itemId) {
		return this.itemParamDao.findByItemId(itemId);
	}

	public ItemSale findOneItemSale(Long id) {
		if (id != null) {
			return this.itemManageDao.findOne(id);
		}
		return null;
	}

	public List<ItemParam> findCustomParamListByItemId(Long itemId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select * from t_item_param t where t.item_id = ? and t.param_id is null");
		return dbHelper.getBeanList(sqlBuff.toString(), ItemParam.class, new Object[] { itemId });
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
	public Page<ItemSale> listItemSale(ItemSale itemSale, int pageNo, int pageSize, String ids) throws SQLException {
		StringBuilder sql = new StringBuilder(100);
		List<Object> params = new ArrayList<Object>();
		sql.append("select sa.*,st.name as storeName,sa.name as itemName ,stype.name as typeName")
		        .append("	from t_item_sale sa  join  t_store st on  sa.store_id = st.id  ")
		        .append(" left join  t_sys_type stype  on  sa.type_Id = stype.id ")
				 .append(" left join t_item_lashou lashou on sa.id = lashou.item_id ");
//		if (StringUtils.isNotEmpty(itemSale.getSaleAreaCode())) {
//			sql.append(" join t_item_sale_area_link isal on isal.sale_id = sa.id and isal.REGION_CODE = ? ");
//			params.add(itemSale.getSaleAreaCode());
//		}

		sql.append(" where 1=1 ");
		sql.append(" and sa.item_mode = 1 ");//虚拟商品
		if (StringUtils.isNotEmpty(itemSale.getStoreName())) {
			sql.append(" and st.name like ? ");
			params.add("%" + itemSale.getStoreName().trim() + "%");
		}

		if (StringUtils.isNotEmpty(itemSale.getTypeName())) {
			sql.append(" and stype.name like ? ");
			params.add("%" + itemSale.getTypeName().trim() + "%");
		}

		if (StringUtils.isNotEmpty(ids)) {
			sql.append(" and sa.id not in ( ").append(ids).append(" )");
		}

		if (itemSale.getId() != null) {
			sql.append(" and sa.id = ? ");
			params.add(itemSale.getId());
		}

		if (StringUtils.isNotEmpty(itemSale.getName())) {
			sql.append(" and sa.name like ? ");
			params.add("%" + itemSale.getName().trim() + "%");
		}
		// 查询更新时间
		if (StringUtils.isNotEmpty(itemSale.getSaleStartTime())) {
			sql.append(" and sa.update_time >= ? ");
			params.add(itemSale.getSaleStartTime() + "000000");
		}
		if (StringUtils.isNotEmpty(itemSale.getSaleStopTime())) {
			sql.append(" and sa.update_time <= ? ");
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
		// 默认不显示删除的，除非手动指定删除条件
		if (itemSale.getStatus() != null) {
			sql.append(" and sa.status = ? ");
			params.add(itemSale.getStatus());
		} else {
			sql.append(" and sa.status != " + ItemSale.STATUS_DELETE);
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
		if (itemSale.getIsValid() != null) {
			sql.append(" and sa.is_valid = ? ");
			params.add(itemSale.getIsValid());
		}
		if (itemSale.getItemMode() != null) {
			sql.append(" and sa.item_mode = ? ");
			params.add(itemSale.getItemMode());
		}
		// 套餐商品过滤库存 xq
		if (itemSale.getStockNumFilter() != null) {
			sql.append(" and sa.stock_num != ? ");
			params.add(itemSale.getStockNumFilter());
		}
		// 礼品卡功能
		if (null != itemSale.getIseckill()) {
			sql.append(" and sa.iseckill = ? ");
			params.add(itemSale.getIseckill());
		}
		//拉手
		if(null != itemSale.getItemSource() && 1 == itemSale.getItemSource()){
			sql.append(" and lashou.id is not null");
		}
		//普通
		if(null != itemSale.getItemSource() && 0 == itemSale.getItemSource()){
			sql.append(" and lashou.id is  null");
		}
		//地市过滤
		if(StringUtils.isNotEmpty(itemSale.getSaleAreaCode())){
			sql.append(" and instr( (select to_char(wm_concat(region_code)) region_code ")
				.append(" from t_item_sale_area_link area_link ")
				.append(" where sa.id = area_link.sale_id)," + itemSale.getSaleAreaCode()+") > 0");
		}
		
		// if (StringUtils.isNotEmpty(itemSale.getSaleAreaCode())) {
		// sql.append(" join t_item_sale_area_link isal on isal.sale_id = sa.id and isal.REGION_CODE = ? ");
		// params.add(itemSale.getSaleAreaCode());
		// }

		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_ITEM));// 控制数据访问
		/* 修改排序方式 @modify_by macl@c-paltform.com @date 2013-7-12 start .... */
		// sql.append(" order by sa.update_time desc");
		sql.append(" order by  sa.update_time desc, sa.status asc ");
		/* 修改排序方式 ....end */
		return dbHelper.getPage(sql.toString(), ItemSale.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 加载分类参数
	 * 
	 * @param typeId
	 * @param model
	 * @throws SQLException
	 */
	public List<SysTypeItemParam> getTypeParamsLoad(Long typeId, Long itemId, Long shopId) {
		List<SysTypeItemParam> sysTypeItemParamList = null;
		try {
			if (null == itemId) {
				String sql = "select  a.* from t_sys_type_item_param a where a.type_id=? and (a.shop_id is null or a.shop_id=?)";
				sysTypeItemParamList = dbHelper.getBeanList(sql, SysTypeItemParam.class, new Object[] { typeId, shopId });
			} else {
				StringBuilder sql = new StringBuilder(100);
				sql.append("select a.*, b.param_value as userParamValue ");
				sql.append("  from t_sys_type_item_param a ");
				sql.append("  left join t_item_param b on a.type_id = b.type_id  and a.param_key=b.param_key and a.id=b.param_id");
				sql.append("                          and b.item_id = ? ");
				sql.append(" where a.type_id = ? and (a.shop_id is null or a.shop_id=?)  order by a.rank asc");
				sysTypeItemParamList = dbHelper.getBeanList(sql.toString(), SysTypeItemParam.class, new Object[] { itemId, typeId, shopId });

			}
		}
		catch (SQLException e) {
			log.error(e.getMessage());
		}
		return sysTypeItemParamList;

	}

	/**
	 * 添加商品信息
	 * 
	 * @param itemOrg
	 * @param request
	 * @param itemTagNames
	 * @param propertyIds
	 * @param itemProperyNames
	 * @throws IOException
	 * @throws SQLException
	 */
	@Transactional
	public ItemSale addItemInfo(ItemSale sale, MultipartFile uploadfile, Long[] propertyIds, String[] itemProperyNames,
	        MultipartHttpServletRequest request, String[] flag) throws IOException, SQLException {
		sale.setLogisticsFee(sale.getLogisticsFee() == null ? 0 : sale.getLogisticsFee());
		sale.setLogisticsFeeType(sale.getLogisticsFeeType() == null ? 0 : sale.getLogisticsFeeType());
		sale.setIsValid(0L);
		sale.setUpdateTime(TimeStamp.getTime(14));
		sale.setSaleStartTime(sale.getSaleStartTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		sale.setSaleStopTime(sale.getSaleStopTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		if (null != sale.getVerifyStartTime() && !"".equals(sale.getVerifyStartTime())) {
			sale.setVerifyStartTime(sale.getVerifyStartTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		}
		if (null != sale.getVerifyStopTime() && !"".equals(sale.getVerifyStopTime())) {
			sale.setVerifyStopTime(sale.getVerifyStopTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		}

		itemManageDao.save(sale);
		// 保存标签
		saveItemTag(sale);
		// 保存商品封面图
		saveCoverItemImg(uploadfile, sale);
		// 保存商品参数
		saveItemParam(request, sale);
		// 保存产品属性
		addItemPropertyInfo(sale.getId(), propertyIds, itemProperyNames, request, flag);
		// 保存配送区域
		savePostArea(sale);
		// 保存验证门店
		saveVerifyShop(sale);
		// 保存销售 门店
		saveSaleShop(sale);
		// 保存销售 区域
		saveSaleArea(sale);
		// 保存购买区域限制
		saveAreaLimit(sale);
		// 购买会员限制
		saveUserLimit(sale);
		// 保存运费信息
		saveItemSaleExt(sale);
		//保存支付信息
		saveItemSalePayment(sale);
		// 保存货架分类
		// saveGoodShelfGoodRel(sale);
		// 保存套餐商品信息
		saveMeal(sale);
		itemManageDao.save(sale);
		return sale;
	}

	/**
	 * 保存套餐商品
	 * 
	 * @param sale
	 */
	private void saveMeal(ItemSale sale) {

		if (sale.getGroupFlag() != 1L) {
			return;
		}
		// 删除优惠套餐
		itemGroupLinkDao.deleteByItemId(sale.getId());
		// 保存商品优惠套餐
		String[] itemIdsStr = sale.getItemIds().split(",");
		for (String item : itemIdsStr) {
			ItemGroupLink itemGroupLink = new ItemGroupLink();
			itemGroupLink.setItemOrgId(sale.getId());
			itemGroupLink.setItemSaleId(Long.valueOf(item));
			itemGroupLinkDao.save(itemGroupLink);
		}

	}

	/**
	 * 保存套餐商品(去掉其他功能)
	 * 
	 * @param sale
	 * @throws IOException
	 */
	@Transactional
	public void saveMealtg(ItemSale sale) throws IOException {
		sale.setLogisticsFee(sale.getLogisticsFee() == null ? 0 : sale.getLogisticsFee());
		sale.setLogisticsFeeType(sale.getLogisticsFeeType() == null ? 0 : sale.getLogisticsFeeType());
		itemManageDao.save(sale);
		// 保存配送区域
		savePostArea(sale);
		// 保存运费信息
		saveItemSaleExt(sale);
		// 保存销售 区域
		saveSaleArea(sale);
		if (sale.getGroupFlag() != 1L) {
			return;
		}
		// 删除优惠套餐
		itemGroupLinkDao.deleteByItemId(sale.getId());
		// 保存商品优惠套餐
		String[] itemIdsStr = sale.getItemIds().split(",");
		for (String item : itemIdsStr) {
			ItemGroupLink itemGroupLink = new ItemGroupLink();
			itemGroupLink.setItemOrgId(sale.getId());
			itemGroupLink.setItemSaleId(Long.valueOf(item));
			itemGroupLinkDao.save(itemGroupLink);
		}
	}

	/**
	 * 保存商品封面图
	 * 
	 * @param resItemSale
	 * @throws IOException
	 */
	private void saveCoverItemImg(MultipartFile uploadfile, ItemSale resItemSale) throws IOException {
		// 保存封面图
		if (null != uploadfile && !uploadfile.isEmpty()) {
			// 保存文件表
			String fileName = uploadfile.getOriginalFilename();
			SysFileImg sysFileImg = bsFileService.dealBsImgFile(uploadfile.getInputStream(), FileTools.getExtFilename(fileName),
			                                                    BsFileService.BS_ITEM_COVER_PIC_KEY, resItemSale.getId(), true);
			resItemSale.setImgPath(sysFileImg.getFileName());

		}
	}

	/**
	 * 保存标签
	 * 
	 * @param resItemSale
	 */
	private void saveItemTag(ItemSale resItemSale) {
		// 删除商品标签信息
		itemTagDao.deleteByItemId(resItemSale.getId());
		// 添加商品标签信息
		if (resItemSale.getTag() != null && !"".equals(resItemSale.getTag())) {
			String itemOrgTag = resItemSale.getTag().replaceAll("；", ";");
			if (itemOrgTag.endsWith(";")) {
				itemOrgTag = itemOrgTag.substring(0, itemOrgTag.lastIndexOf(";"));
			}
			String[] itemTagNames = itemOrgTag.split(";");
			List<ItemTag> itemTagList = new ArrayList<ItemTag>();
			for (String str : itemTagNames) {
				ItemTag itemTag = new ItemTag();
				itemTag.setTag(str);
				itemTag.setItemId(resItemSale.getId());
				itemTagList.add(itemTag);
			}
			itemTagDao.save(itemTagList);
		}
	}

	/**
	 * 保存产品参数
	 * 
	 * @param itemParamList
	 * @param resItemSale
	 * @throws SQLException
	 */
	private void saveItemParam(MultipartHttpServletRequest request, ItemSale sale) throws SQLException {
		List<SysTypeItemParam> sysTypeItemParamList = sysTypeItemParamDao.findByTypeId(sale.getTypeId());
		List<ItemParam> itemParamList = new ArrayList<ItemParam>();
		if (null != sysTypeItemParamList && sysTypeItemParamList.size() >= 0) {
			for (SysTypeItemParam param : sysTypeItemParamList) {
				Long paramId = param.getId();
				String paramValue = "";
				if (param.getParamType() == 2) {
					String arry[] = request.getParameterValues("itemParam_" + paramId);
					if (null != arry) {
						for (String str : arry) {
							paramValue = paramValue + str + ",";
						}
						paramValue.substring(paramValue.lastIndexOf(","));
					}
				} else {
					paramValue = request.getParameter("itemParam_" + paramId);
				}

				if (StringUtils.isNotEmpty(paramValue)) {
					ItemParam itemParam = new ItemParam(sale.getTypeId(), sale.getId(), paramId, param.getParamKey(), paramValue, null);
					itemParamList.add(itemParam);
				}
			}
		}
		// 自定义参数
		String[] customparakey = request.getParameterValues("customparakey");
		String[] customparavalue = request.getParameterValues("customparavalue");
		if (customparakey != null) {
			for (int i = 0; i < customparakey.length; i++) {
				ItemParam itemParam = new ItemParam(sale.getTypeId(), sale.getId(), null, customparakey[i], customparavalue[i], null);
				itemParamList.add(itemParam);
			}
		}

		// 删除产品参数信息
		this.deleteParamByItemId(sale.getId());

		if (itemParamList.size() > 0) {
			itemParamDao.save(itemParamList);
		}
	}

	/**
	 * 保存商品属性相关信息
	 * 
	 * @param resItemOrg
	 * @param propertyIds
	 * @param itemProperyNames
	 * @param propertyPicList
	 * @throws IOException
	 * @throws SQLException
	 */
	private void addItemPropertyInfo(Long itemId, Long[] propertyIds, String[] itemProperyNames, MultipartHttpServletRequest request, String[] flag)
	        throws IOException, SQLException {

		// 添加商品属性
		if (itemProperyNames != null) {
			// 参数处理
			List<MultipartFile> propertyPicList = request.getFiles("uploadPropertyPic");// 属性图片
			int index = 0;
			for (int i = 0; i < itemProperyNames.length; i++) {
				ItemProperty itemProperty = new ItemProperty();
				itemProperty.setItemId(itemId);
				itemProperty.setContent(itemProperyNames[i]);
				itemProperty.setPropertyId(propertyIds[i]);
				ItemProperty itemP = itemPropertyDao.save(itemProperty);
				SysFileImg sysFileImg;
				// 属性有图片处理图片
				if (flag[i].equals("1")) {
					if (propertyPicList.size() > index) {
						MultipartFile properyPic = propertyPicList.get(index);
						if (properyPic != null && properyPic.getInputStream() != null) {
							String ext = FileTools.getExtFilename(properyPic.getOriginalFilename());
							sysFileImg = bsFileService.dealBsImgFile(properyPic.getInputStream(), ext, BsFileService.BS_PROPERTY_PIC_KEY, itemP
							        .getId(), false);
							itemP.setFileId(sysFileImg.getId());
							itemP.setImgPath(sysFileImg.getFileWebPath());
							itemPropertyDao.save(itemP);
						}
						index++;
					}
				}
			}
		}

	}

	/**
	 * 保存商品物流配送区域
	 * 
	 * @param itemSale
	 * @throws IOException
	 */
	private void savePostArea(ItemSale itemSale) throws IOException {
		postAreaLinkDao.deleteBySaleId(itemSale.getId());
		if (null != itemSale.getPostAreaCode() && !"".equals(itemSale.getPostAreaCode())) {
			if (itemSale.getPostFlag() == 1l) {
				String[] postArea = itemSale.getPostAreaCode().split(",");
				for (String str : postArea) {
					ItemPostAreaLink postAreaLink = new ItemPostAreaLink();
					postAreaLink.setSaleId(itemSale.getId());
					postAreaLink.setRegionCode(str);
					postAreaLinkDao.save(postAreaLink);
				}
			}
		}

	}

	/**
	 * 保存商品验证门店
	 * 
	 * @param itemSale
	 * @throws IOException
	 */
	private void saveVerifyShop(ItemSale itemSale) throws IOException {
		verifyShopLinkDao.deleteBySaleId(itemSale.getId());
		if (null != itemSale.getVerifyShopIds() && !"".equals(itemSale.getVerifyShopIds())) {
			// 保存验证门店
			String[] verifyShopIds = itemSale.getVerifyShopIds().split(",");
			for (String str : verifyShopIds) {
				ItemVerifyShopLink postAreaLink = new ItemVerifyShopLink();
				postAreaLink.setSaleId(itemSale.getId());
				postAreaLink.setShopId(Long.valueOf(str));
				postAreaLink.setStoreId(itemSale.getStoreId());
				verifyShopLinkDao.save(postAreaLink);
			}
		}

	}

	/**
	 * 保存商品销售门店
	 * 
	 * @param itemSale
	 * @throws IOException
	 */
	private void saveSaleShop(ItemSale itemSale) throws IOException {
		saleShopLinkDao.deleteBySaleId(itemSale.getId());
		if (null != itemSale.getSaleShopIds() && !"".equals(itemSale.getSaleShopIds())) {
			// 销售门店
			String[] saleShopIds = itemSale.getSaleShopIds().split(",");
			for (String str : saleShopIds) {
				ItemSaleShopLink postAreaLink = new ItemSaleShopLink();
				postAreaLink.setSaleId(itemSale.getId());
				postAreaLink.setShopId(Long.valueOf(str));
				postAreaLink.setStoreId(itemSale.getStoreId());
				saleShopLinkDao.save(postAreaLink);
			}
		}

	}

	/**
	 * 保存商品销售区域
	 * 
	 * @param itemSale
	 *            modify by xq
	 * @throws IOException
	 */
	private void saveSaleArea(ItemSale itemSale) throws IOException {
		saleAreaLinkDao.deleteBySaleId(itemSale.getId());
		if (null != itemSale.getSaleAreaCode() && !"".equals(itemSale.getSaleAreaCode())) {
			String[] saleAreaArea = itemSale.getSaleAreaCode().split(",");
			for (String str : saleAreaArea) {
				if ("0000".equals(str.substring(2, 6))) {
					List<SysRegion> sysRegions = sysRegionService.findByParentRegion(str);
					for (SysRegion sysRegion : sysRegions) {
						ItemSaleAreaLink sAreaLink = new ItemSaleAreaLink();
						sAreaLink.setSaleId(itemSale.getId());
						sAreaLink.setRegionCode(sysRegion.getRegionCode());
						saleAreaLinkDao.save(sAreaLink);
						// 根据区域保存价格信息
						saveSalePrice(itemSale, sysRegion.getRegionCode());
					}
					ItemSaleAreaLink sAreaLink = new ItemSaleAreaLink();
					sAreaLink.setSaleId(itemSale.getId());
					sAreaLink.setRegionCode(str);
					saleAreaLinkDao.save(sAreaLink);
					// 根据区域保存价格信息
					saveSalePrice(itemSale, str);

				} else {
					ItemSaleAreaLink sAreaLink = new ItemSaleAreaLink();
					sAreaLink.setSaleId(itemSale.getId());
					sAreaLink.setRegionCode(str);
					saleAreaLinkDao.save(sAreaLink);
					// 根据区域保存价格信息
					saveSalePrice(itemSale, str);
				}
			}
		}

	}

	/**
	 * 保存商品价格体系
	 * 
	 * @param itemSale
	 * @param saleArea
	 *            对应的销售区域
	 * @throws IOException
	 */
	private void saveSalePrice(ItemSale itemSale, String saleArea) throws IOException {
		priceDao.deleteBySaleId(itemSale.getId());
		if (null != itemSale.getPrice() && !"".equals(itemSale.getPrice())) {
			// 价格
			String priceArry[] = itemSale.getPrice().split(",");
			String priceTypeArry[] = itemSale.getPriceTypeCode().split(",");
			for (int i = 0; i < priceArry.length; i++) {
				ItemPrice itemPrice = new ItemPrice();
				itemPrice.setAreaCode(saleArea);
				itemPrice.setItemId(itemSale.getId());
				itemPrice.setPrice(Float.valueOf(priceArry[i]));
				itemPrice.setPriceTypeCode(priceTypeArry[i]);
				itemPrice.setSaleId(itemSale.getId());
				itemPrice.setStoreId(itemSale.getStoreId());
				if (Float.valueOf(priceArry[i]) > 0) {
					priceDao.save(itemPrice);
				}
			}
		}

	}

	/**
	 * 保存商品购买用户的区域限制
	 * 
	 * @param itemSale
	 * @throws IOException
	 */
	private void saveAreaLimit(ItemSale itemSale) throws IOException {
		userAreaLinkDao.deleteBySaleId(itemSale.getId());
		// 用户购买区域限制
		if (null != itemSale.getAreaLimitCode()) {
			String[] postArea = itemSale.getAreaLimitCode().split(",");
			for (String str : postArea) {
				ItemSaleUserAreaLink userAreaLink = new ItemSaleUserAreaLink();
				userAreaLink.setSaleId(itemSale.getId());
				userAreaLink.setRegionCode(str);
				userAreaLinkDao.save(userAreaLink);
			}
		}
	}

	/**
	 * 保存商品购买会员限制
	 * 
	 * @param itemSale
	 * @throws IOException
	 */
	private void saveUserLimit(ItemSale itemSale) throws IOException {
		userLevelaLinkDao.deleteBySaleId(itemSale.getId());
		// 会员限制
		if (null != itemSale.getUserLimitCode()) {
			String[] postUser = itemSale.getUserLimitCode().split(",");
			for (String str : postUser) {
				ItemSaleUserLevelaLink userLevelaLink = new ItemSaleUserLevelaLink();
				userLevelaLink.setSaleId(itemSale.getId());
				userLevelaLink.setUserLevel(str);
				userLevelaLinkDao.save(userLevelaLink);
			}
		}
	}

	/**
	 * 保存商品货架分类
	 * 
	 * @param itemSale
	 * @throws IOException
	 */
	private void saveGoodShelfGoodRel(ItemSale itemSale) throws IOException {
		goodRelDao.delGoodShelfGoodRel(itemSale.getId());
		if (null != itemSale.getGoodTypeId() && !"".equals(itemSale.getGoodTypeId())) {
			GoodsShelfRel goodsShelf = new GoodsShelfRel();
			goodsShelf.setShelfId(Long.valueOf(itemSale.getGoodTypeId()));
			goodsShelf.setGoodId(itemSale.getId());
			goodRelDao.save(goodsShelf);
		}
	}

	/**
	 * 保存商品运费信息（不管需不需要物流运费表都增加一条数据）
	 * 
	 * @param itemSale
	 * @throws IOException
	 */

	private void saveItemSaleExt(ItemSale itemSale) throws IOException {
		// 查询商品运费
		ItemSaleExt itemSaleExt = saleExtDao.findBySaleId(itemSale.getId());
		if (null == itemSaleExt) {
			itemSaleExt = new ItemSaleExt();
			itemSaleExt.setSaleId(itemSale.getId());
			itemSaleExt.setSaleNum(0l);
			itemSaleExt.setClickNum(0l);
			itemSaleExt.setCommentNum(0l);
			itemSaleExt.setUserNum(0l);
			itemSaleExt.setCollectNum(0l);
			itemSaleExt.setRank(Double.parseDouble("0"));
		}
		// 物流运费移到商品信息表
		// itemSaleExt.setLogisticsFee(itemSale.getLogisticsFee() == null ? 0 :
		// itemSale.getLogisticsFee());
		// itemSaleExt.setLogisticsFeeType(itemSale.getLogisticsType() == null ?
		// 0 : itemSale.getLogisticsType());
		saleExtDao.save(itemSaleExt);

	}

	/**
	 * 根据发布id查询
	 * 
	 * @param id
	 * @return
	 */
	public void getItemSale(ItemSale itemSale) {
		Long id = itemSale.getId();
		String buyLimit = "";
		try {

			// 已发布商品销售门店表
			String[] saleShop = select(id, getItemSaleShopLinkSql());
			if (null != saleShop && saleShop.length > 0) {
				itemSale.setSaleShopIds(saleShop[0]);
				itemSale.setSaleShopName(saleShop[1]);
			}

			// 已发布商品销售区域modify by xq
			String[] saleArear = select(id, getItemSaleAreaLinkSql());
			if (null != saleArear && saleArear.length > 0) {
				String saleAreaCode = saleArear[0];
				String[] saleAreaCodes = saleAreaCode.split(",");
				List<String> provionCode = new ArrayList<String>();
				for (String string : saleAreaCodes) {
					if (string != null && string.length() >= 6 && "0000".equals(string.substring(2, 6))) {
						provionCode.add(string);
					}
				}
				if (provionCode.size() > 0) {
					String s = "";
					for (int i = 0; i < provionCode.size(); i++) {
						if (i == provionCode.size() - 1) {
							s += provionCode.get(i);
						} else {
							s += provionCode.get(i) + ",";
						}
					}
					itemSale.setSaleAreaCode(s);
					String str = "";
					for (int i = 0; i < provionCode.size(); i++) {
						if (i == provionCode.size() - 1) {
							str += sysRegionService.findByRegionCode(provionCode.get(i)).getRegionName();
						} else {
							str += sysRegionService.findByRegionCode(provionCode.get(i)).getRegionName() + ",";
						}
					}
					itemSale.setSaleAreaName(str);
				} else {
					itemSale.setSaleAreaCode(saleArear[0]);
					itemSale.setSaleAreaName(saleArear[1]);
				}
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
			// if (null != itemSaleExt) {
			// itemSale.setLogisticsFee(itemSaleExt.getLogisticsFee());
			// itemSale.setLogisticsType(itemSaleExt.getLogisticsFeeType());
			// }
			// 货架类型
			String[] goodRel = select(id, getGoodShelfGoodRelSql());
			if (null != goodRel) {
				itemSale.setGoodTypeId(goodRel[0]);
				itemSale.setGoodTypeName(goodRel[1]);
			}

		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private String[] select(Long id, String sql) throws Exception {
		return dbHelper.getArray(sql, new Object[] { id });

	}

	private String getGoodShelfGoodRelSql() {
		StringBuilder bufferSql = new StringBuilder(100);
		bufferSql.append("select to_char(wm_concat(b.id)), to_char(wm_concat(b.title)) ");
		bufferSql.append("  from t_goodshelf_goods_rel a, t_good_shelf b ");
		bufferSql.append(" where a.shelf_id = b.id ");
		bufferSql.append("   and a.good_id = ? ");
		bufferSql.append(" group by a.good_id");

		return bufferSql.toString();
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
		bufferSql.append("select to_char(wm_concat(l.region_code)),to_char( wm_concat(s.region_name)) ");
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

	public List<ItemPrice> getPriceList(Long saleId) throws SQLException {
		List<ItemPrice> list = new ArrayList<ItemPrice>();
		StringBuilder bufferSql = new StringBuilder(100);

		// 本版价格不关联地域
		// @modify_by macl@c-platform.com start >>>>>>>>>>>>>>
		bufferSql.append("select t.*, pt.price_type ");
		bufferSql.append("  from t_item_price t ");
		// bufferSql
		// .append("  left join t_item_price_type pt on t.area_code = pt.area_code ");
		// bufferSql
		// .append("                                and t.price_type_code = pt.price_type_code");
		bufferSql.append("  left join t_item_price_type pt on ");
		bufferSql.append("                               t.price_type_code = pt.price_type_code");
		bufferSql.append("  where t.sale_id = ? ");

		// 本版价格不关联地域 <<<<<<<<<<<<<<<<<

		try {
			list = dbHelper.getBeanList(bufferSql.toString(), ItemPrice.class, new Object[] { saleId });
		}
		catch (SQLException e) {
			log.error(e.getMessage());
			throw e;
		}
		return list;
	}

	/**
	 * 获取该商品的所有配置价格，包括该商品已设置的价格。
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<ItemPriceType> getAllPriceTypeList(Long saleId) throws SQLException {
		StringBuilder bufferSql = new StringBuilder();

		bufferSql.append(" SELECT t.*, to_char(nvl(p.price ,0),'fm999999990.00') price ");
		bufferSql.append("   FROM t_Item_Price_Type t ");
		bufferSql.append("       LEFT JOIN t_Item_Price p ON  ");
		bufferSql.append("             t.price_type_code = p.price_type_code ");
		bufferSql.append("            AND p.item_id = ? ");
		bufferSql.append("  WHERE t.area_code = 320000 ");
		List<ItemPriceType> list = new ArrayList<ItemPriceType>();

		try {
			list = dbHelper.getBeanList(bufferSql.toString(), ItemPriceType.class, new Object[] { saleId });
		}
		catch (SQLException e) {
			log.error(e.getMessage());
			throw e;
		}
		return list;
	}

	/**
	 * 商品送审
	 * 
	 * @param itemOrg
	 * @return
	 */
	@Transactional
	public String sendToAudit(ItemSale sale) {
		Long storeId = sale.getStoreId();
		Store store = storeDao.findOne(storeId);

		String result = "送审失败";
		if (store == null || store.getStatus() == null || store.getStatus() != 3) {
			return "商户状态异常！状态=" + Store.statusMap.get(store.getStatus());
		}
		if (store.getItemPublishAuditFlag() == 1) {// 商户商品编辑是否免审 0--需要审核1--免审
			sale.setStatus(ItemSale.STATUS_BASE_AUDIT_PASS);
			// sale.setIsValid(1L);
			result = "商品免审，审核通过";
		} else {
			sale.setStatus(ItemSale.STATUS_BASE_NO_AUDIT);
			result = "送审成功，待审核";
		}
		itemManageDao.save(sale);
		return result;
	}

	private void deleteParamByItemId(Long itemId) throws SQLException {
		dbHelper.execute("delete from t_item_param where item_id = ?", itemId);
	}

	/**
	 * 获取标签
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public List<SysFileImg> getItemImg(Long itemId) throws SQLException {
		List<SysFileImg> l = fileImgDao.findByBsKeyAndBsId(BsFileService.BS_ITEM_PIC_KEY, itemId);

		return l;
	}

	/**
	 * 保存商品图片
	 * 
	 * @param id
	 *            商品id
	 * @param is
	 *            文件流
	 * @param ext
	 *            文件后缀
	 */
	@Transactional
	public SysFileImg saveImg(Long id, InputStream is, String ext) {
		return bsFileService.dealBsImgFile(is, ext, BsFileService.BS_ITEM_PIC_KEY, id, false);
	}

	/**
	 * 删除商品图片
	 * 
	 * @param propertyId
	 * @param picId
	 */
	public void deleteItemPic(Long picId) {

		if (picId != null) {// 删除图片
			bsFileService.deleteBsFileImg(picId);
		}
	}

	/**
	 * 设置商品封面图
	 * 
	 * @param fileId
	 * @param itemId
	 * @throws SQLException
	 */
	@Transactional
	public void coverItemImg(Long fileId, Long itemId) throws SQLException {
		StringBuilder sql = new StringBuilder(100);
		sql.setLength(0);
		// 把封面转为普通
		sql.append("update t_sys_file_img ");
		sql.append("   set bs_key = ? ");
		sql.append(" where    bs_id = ?    and bs_key = ? ");
		dbHelper.execute(sql.toString(), new Object[] { BsFileService.BS_ITEM_PIC_KEY, itemId, BsFileService.BS_ITEM_COVER_PIC_KEY });

		// 把普通转成封面
		sql.setLength(0);
		sql.append("update t_sys_file_img");
		sql.append("   set bs_key = ? ");
		sql.append(" where id =?");
		dbHelper.execute(sql.toString(), new Object[] { BsFileService.BS_ITEM_COVER_PIC_KEY, fileId });

		SysFileImg fileImg = fileImgDao.findOne(fileId);
		sql.setLength(0);
		sql.append("update t_item_sale ");
		sql.append("   set IMG_PATH = ? ");
		sql.append(" where id = ? ");
		dbHelper.execute(sql.toString(), new Object[] { fileImg.getFileName(), itemId });

	}

	/**
	 * 获取套餐商品
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public String[] getGroupItem(Long itemId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("select to_char(wm_concat(l.item_sale_id)), to_char(wm_concat(t.name||'\n')) ");
		sql.append("  from t_item_group_link l, t_item_sale t ");
		sql.append(" where l.item_org_id = ? ");
		sql.append("   and t.id = l.item_sale_id");

		return dbHelper.getArray(sql.toString(), new Object[] { itemId });
	}

	/**
	 * 获取标签
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public String getTag(Long itemId) throws SQLException {
		String sql = "select to_char(wm_concat(tag)) from t_item_tag where item_id=?";
		return dbHelper.queryScalar(sql, new Object[] { itemId });
	}

	/**
	 * 获取商品分类名称
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public String getTypeName(Long typeid) throws SQLException {
		String name = "";
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);

		/**
		 * 只显示当前分类名称，不显示其父分类名称
		 * 
		 * @modify macl@c-platform.com start>>>
		 **/
		// sql.append("select name from t_sys_type start with id = ? connect by prior p_Id = id  order by id asc");
		sql.append("select name from t_sys_type where id = ? ");
		/** 只显示当前分类名称，不显示其父分类名称 <<< end **/

		List<String[]> nameList = dbHelper.getArrayList(sql.toString(), new Object[] { typeid });
		for (String str[] : nameList) {
			name = name + str[0] + "/";
		}
		return name;
	}

	/**
	 * 加载分类参数
	 * 
	 * @param typeId
	 * @param model
	 * @throws SQLException
	 */
	public List<ItemProperty> getItemProperty(Long itemId) {
		List<ItemProperty> itemProperty = null;
		StringBuilder sql = new StringBuilder(100);

		sql.append("select a.*, c.file_web_path filePath,p.content propertyName");
		sql.append("  from t_item_property a ");
		sql.append("  left join t_sys_file_img c on a.file_id = c.id   ,t_sys_property p");
		sql.append(" where a.item_id = ? and a.property_id=p.id");

		try {
			itemProperty = dbHelper.getBeanList(sql.toString(), ItemProperty.class, new Object[] { itemId });
		}
		catch (SQLException e) {
			log.error(e.getMessage());
		}

		return itemProperty;

	}

	/**
	 * 获取商品图片
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public List<SysFileImg> getItemImg(Long itemId, String key) throws SQLException {
		List<SysFileImg> imgList = fileImgDao.findByBsKeyAndBsId(key, itemId);
		return imgList;
	}

	/**
	 * 删除商品属性
	 * 
	 * @param propertyId
	 * @param picId
	 */
	@Transactional
	public void deleteItemProperty(Long propertyId) {

		// 删除属性
		if (propertyId != null) {
			ItemProperty itemProperty = itemPropertyDao.findOne(propertyId);
			deleteItemPic(itemProperty.getFileId());
			if (itemProperty != null) {
				itemPropertyDao.delete(itemProperty);
			}
		}
	}

	/**
	 * 第一步审核商品
	 * 
	 * @param id
	 * @param feeType
	 * @param step
	 * @param buyLimit
	 * @param userLimitCode
	 * @param areaLimitCode
	 * @param userPerBuyNum
	 * @throws Exception
	 */

	@Transactional
	public String auditItemSale(ItemSale vo, AuditStep step) throws Exception {
		String msg = "审核失败";
		ItemSale itemSale = itemManageDao.findOne(vo.getId());
		Store store = storeDao.findOne(itemSale.getStoreId());
		if (store == null || store.getStatus() != 3L) {
			return "该商户未审核通过，商品不能审核！商户状态：" + Store.statusMap.get(store.getStatus());
		}
		itemSale.setFeeType(vo.getFeeType());
		if (step.getStatus() == 3) {
			itemSale.setStatus(ItemSale.STATUS_BASE_AUDIT_PASS);
			// itemSale.setIsValid(1L);
			itemSale.setBuyLimit(vo.getBuyLimit());
			itemSale.setAreaLimitCode(vo.getAreaLimitCode());
			itemSale.setUserLimitCode(vo.getUserLimitCode());
			// 是否显示默认为1：显示
			// itemSale.setIsView(vo.getIsView());
			// 直接写死，无语,改掉了
			itemSale.setIsView(vo.getIsView());
			itemSale.setSettlePrice(vo.getSettlePrice());
			itemSale.setUserPerBuyNum(vo.getUserPerBuyNum());
			// 支付方式
			itemSale.setCashIdgoods(vo.getCashIdgoods());
			itemSale.setCoinIdgoods(vo.getCoinIdgoods());
			itemSale.setScoreIdgoods(vo.getScoreIdgoods());
			// 商品支付扩展表
			saveItemSalePayment(vo);
			// 保存用户购买区域限制
			saveAreaLimit(itemSale);
			// 购买会员限制
			saveUserLimit(itemSale);
			step.setStatus(ItemSale.STATUS_BASE_AUDIT_PASS);
			msg =  "审核通过";
		} else {
			itemSale.setStatus(ItemSale.STATUS_BASE_AUDIT_RETJECT);
			step.setStatus(ItemSale.STATUS_BASE_AUDIT_RETJECT);
			msg =  "审核驳回";
		}

		saveAuditStep(step, vo.getId());

		// if (buyLimit.indexOf("2") != -1) {
		// itemSale.setUserPerBuyNum(Long.valueOf(userPerBuyNum));
		// }
		itemManageDao.save(itemSale);
		return msg;
	}
	public void saveItemSalePayment(ItemSale itemSale) {
		ItemSalePayment itemSalePayment = itemSalePaymentDao.findByItemId(itemSale.getId());
		if (itemSalePayment == null) {
			itemSalePayment = new ItemSalePayment();
		}
		itemSalePayment.setItemId(itemSale.getId());
		itemSalePayment.setPayType(itemSale.getPayType());
		itemSalePayment.setCashPayRatio(itemSale.getCashPayRatio());
		itemSalePayment.setOtherPayRatio(itemSale.getOtherPayRatio());
		itemSalePayment.setBillPay(itemSale.getBillIdGoods());
		itemSalePaymentDao.save(itemSalePayment);
	}

	/**
	 * 查询商品支付扩展信息
	 */
	public ItemSalePayment findItemSalePaymentByItemId(Long itemId) {
		return itemSalePaymentDao.findByItemId(itemId);
	}


	/**
	 * 保存审批意见
	 * 
	 * @param step
	 * @param bssId
	 */
	private void saveAuditStep(AuditStep step, Long bssId) {
		step.setBsType(4l);
		step.setUpdateTime(TimeStamp.getTime(14));
		step.setBsId(bssId);
		step.setBsTabel("T_ITEM_SALE");
		stepDao.save(step);
	}

	/**
	 * 商品第二部审核
	 * 
	 * @param itemOrg
	 * @return
	 */
	@Transactional
	public String auditItem(ItemSale sale, AuditStep step) {
		Store store = storeDao.findOne(sale.getStoreId());

		String result = "审核失败";
		if (store == null || store.getStatus() != 3L) {
			return "该商户未审核通过，商品不能审核！商户状态：" + Store.statusMap.get(store.getStatus());
		}
		itemManageDao.save(sale);
		if (step != null) {
			saveAuditStep(step, sale.getId());
		}
		if (ItemSale.STATUS_BASE_AUDIT_PASS.equals(sale.getStatus())) {
			result = "审核通过";
		} else if (ItemSale.STATUS_BASE_AUDIT_RETJECT.equals(sale.getStatus())) {
			result = "审核驳回";
		}

		return result;
	}

	/**
	 * 调用页面静态化接口。
	 * 
	 * @param sale
	 * @return
	 * @throws IOException
	 */
	public String pageStatic(ItemSale sale) {

		String msg = "生成失败！";
		ItemEventBean obj = new ItemEventBean();
		obj.setItem_name(sale.getName());
		obj.setItem_no(sale.getId().toString());
		obj.setItem_type(sale.getTypeId().toString());
		
		setAddresses(sale,obj);
		
		/**
		 * 支付类型 start>>>
		 * 
		 * @modfify_by macl@c-platform
		 **/
		StringBuilder sb = new StringBuilder();
		if (null != sale.getCashIdgoods() && 1L == sale.getCashIdgoods()) {
			sb.append(" 现金 ");
		}

		if (null != sale.getCoinIdgoods() && 1L == sale.getCoinIdgoods()) {
			sb.append(" 商城币 ");
		}

		if (null != sale.getScoreIdgoods() && 1L == sale.getScoreIdgoods()) {
			sb.append(" 积分 ");
		}
		ItemSalePayment itemSalePayment  = itemSalePaymentDao.findByItemId(sale.getId());
		if(null != itemSalePayment && 1L == itemSalePayment.getBillPay()){
			sb.append(" 话费 ");
		}
		// obj.setPay_method("现金");
		obj.setPay_method(sb.toString());
		/** 支付类型 <<<end **/

		obj.setPay_hint(sale.getWarmPrompt());
		obj.setShop_id(sale.getStoreId().toString());
		Store store = this.storeService.findStoreById(sale.getStoreId());
		obj.setShop_name(store.getName());

		if (sale.getItemMode() == null) {
			obj.setItem_mode("");
		} else {
			obj.setItem_mode(sale.getItemMode().toString());
		}
		obj.setAfter_service("售后服务");
		obj.setItem_intro(sale.getRemark());
		String[] imgs = null;
		try {
			List<SysFileImg> fileLink = getItemImg(sale.getId(), BsFileService.BS_ITEM_PIC_KEY);
			// List<SysFileImg> coverFileLink = getItemImg(sale.getId(),
			// BsFileService.BS_ITEM_COVER_PIC_KEY);

			if (fileLink != null && fileLink.size() > 0) {
				imgs = new String[fileLink.size() + 1];
				imgs[0] = StringUtils.defaultString(sale.getImgPath(), "");
				for (int i = 0; i < fileLink.size(); i++) {
					imgs[i + 1] = StringUtils.defaultString(fileLink.get(i).getFileName(), "");
				}

			} else {
				imgs = new String[1];
				imgs[0] = StringUtils.defaultString(sale.getImgPath(), "");
			}
			obj.setImgs(imgs);

			List<ItemParam> itemParamList = findItemParamListByItemId(sale.getId());
			if (itemParamList != null && itemParamList.size() > 0) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < itemParamList.size(); i++) {
					map.put(itemParamList.get(i).getParamKey(), itemParamList.get(i).getParamValue());
				}
				obj.setItem_param(map);
			} else {
				obj.setItem_param(new HashMap<String, String>());
			}
			msg = this.pageStatic.pageStatic(obj);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			PageStaticInfo pageStaticInfo = new PageStaticInfo();
			pageStaticInfo.setStatus(PageStaticInfo.STATUS_FAIL);
			pageStaticInfo.setType(PageStaticInfo.TYPE_ITEM);
			pageStaticInfo.setResourceId(sale.getId());
			pageStaticInfo.setCreateTime((TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss)));
			pageStaticManageService.saveOrUpdate(pageStaticInfo);
			/* <<<end */
		}
		return msg;
	}
	public void setAddresses(ItemSale sale,ItemEventBean obj){
		//Map<String,Map<String,List<String>>> map = new HashMap<String, Map<String,List<String>>>();
		Map<String,List<List<String>>> lessMap = new HashMap<String, List<List<String>>>();
		List<ItemVerifyShopLink> shopLinks = verifyShopLinkDao.findBySaleId(sale.getId());
		List<String> citys = new ArrayList<String>();
		//List<Address>  addresses = new ArrayList<Address>();
		for(int i=0;i<shopLinks.size();i++){
			ItemVerifyShopLink shopLink = shopLinks.get(i);
			Shop shop = shopDao.findOne(shopLink.getShopId());
			if(citys.indexOf(shop.getAreaCode()) == -1){
				citys.add(shop.getAreaCode());
			}
		}
		for(int i=0;i<citys.size();i++){
			//Address address = new Address();
			SysRegion sysRegion = sysRegionService.findByRegionCode(citys.get(i));
			List<List<String>> list = new ArrayList<List<String>>();
			//address.setCity(sysRegion.getRegionName().replaceAll("市", ""));
			//List<ShopAddress> shops = new ArrayList<ShopAddress>();
			for(int j=0;j<shopLinks.size();j++){
				ItemVerifyShopLink shopLink = shopLinks.get(j);
				Shop shop = shopDao.findOne(shopLink.getShopId());
				if(citys.get(i).equals(shop.getAreaCode())){
					List<String> lessList = new ArrayList<String>();
					//ShopAddress shopAddress = new ShopAddress();
					//shopAddress.setShopId(shop.getId()+"");
					//shopAddress.setShopName(shop.getName());
					SysRegion sr = sysRegionService.findByRegionCode(shop.getAreaCode());
					//shopAddress.setCityCode(sr.getAreaCode());
					//shopAddress.setShopAdrr(shop.getAddress());
					//shopAddress.setShopTel(shop.getPhone());
					//shopAddress.setURL("");
					lessList.add(shop.getId()+"");
					lessList.add(sr.getAreaCode());
					lessList.add(shop.getName());
					lessList.add(shop.getAddress());
					lessList.add(shop.getPhone());
					lessList.add("");
					//shops.add(shopAddress);
					list.add(lessList);
				}
			}
			lessMap.put(sysRegion.getRegionName().replaceAll("市", ""), list);
			//address.setShops(shops);
			//addresses.add(address);
			//map.put("addresses", lessMap);
		}
	    //obj.setAddresses(addresses);
		obj.setAddresses(lessMap);
	}

	/**
	 * 调用商品浏览页面静态化接口。
	 * 
	 * @param sale
	 * @return
	 * @throws IOException
	 */
	public String pageBrowseStatic(ItemSale sale) {

		String msg = "";
		ItemEventBean obj = new ItemEventBean();
		obj.setItem_name(sale.getName());
		obj.setItem_no(sale.getId().toString());
		obj.setItem_type(sale.getTypeId().toString());
		
		setAddresses(sale,obj);
		/**
		 * 支付类型 start>>>
		 * 
		 * @modfify_by macl@c-platform
		 **/
		StringBuilder sb = new StringBuilder();
		if (null != sale.getCashIdgoods() && 1L == sale.getCashIdgoods()) {
			sb.append(" 现金 ");
		}

		if (null != sale.getCoinIdgoods() && 1L == sale.getCoinIdgoods()) {
			sb.append(" 商城币 ");
		}

		if (null != sale.getScoreIdgoods() && 1L == sale.getScoreIdgoods()) {
			sb.append(" 积分 ");
		}
		ItemSalePayment itemSalePayment  = itemSalePaymentDao.findByItemId(sale.getId());
		if(null != itemSalePayment && 1L == itemSalePayment.getBillPay()){
			sb.append(" 话费 ");
		}
		// obj.setPay_method("现金");
		obj.setPay_method(sb.toString());
		/** 支付类型 <<<end **/
		obj.setPay_hint(sale.getWarmPrompt());
		obj.setShop_id(sale.getStoreId().toString());
		setAddresses(sale,obj);
		Store store = this.storeService.findStoreById(sale.getStoreId());
		obj.setShop_name(store.getName());

		if (sale.getItemMode() == null) {
			obj.setItem_mode("");
		} else {
			obj.setItem_mode(sale.getItemMode().toString());
		}

		obj.setAfter_service("售后服务");
		obj.setItem_intro(sale.getRemark());
		String[] imgs = null;
		try {
			List<SysFileImg> fileLink = getItemImg(sale.getId(), BsFileService.BS_ITEM_PIC_KEY);

			if (fileLink != null && fileLink.size() > 0) {
				imgs = new String[fileLink.size() + 1];
				imgs[0] = StringUtils.defaultString(sale.getImgPath(), "");
				for (int i = 0; i < fileLink.size(); i++) {
					imgs[i + 1] = StringUtils.defaultString(fileLink.get(i).getFileName(), "");
				}

			} else {
				imgs = new String[1];
				imgs[0] = StringUtils.defaultString(sale.getImgPath(), "");
			}
			obj.setImgs(imgs);

			List<ItemParam> itemParamList = findItemParamListByItemId(sale.getId());
			if (itemParamList != null && itemParamList.size() > 0) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < itemParamList.size(); i++) {
					map.put(itemParamList.get(i).getParamKey(), itemParamList.get(i).getParamValue());
				}
				obj.setItem_param(map);
			} else {
				obj.setItem_param(new HashMap<String, String>());
			}
			msg = this.pageStatic.pageBrowse(obj);
		}
		catch (Exception e) {
			msg = Constants.STATIC_FAIL;
			log.error(msg + e.getMessage());

			/*
			 * 调用接口失败，将商品相关数据添加到“页面静态化管理表”中
			 * @add_by macl@c-platform.comstart>>>
			 */
			PageStaticInfo pageStaticInfo = new PageStaticInfo();
			pageStaticInfo.setStatus(PageStaticInfo.STATUS_FAIL);
			pageStaticInfo.setType(PageStaticInfo.TYPE_ITEM);
			pageStaticInfo.setResourceId(sale.getId());
			pageStaticInfo.setCreateTime((TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss)));
			pageStaticManageService.saveOrUpdate(pageStaticInfo);
			/* <<<end */
		}
		return msg;
	}

	public Map syncItem(ItemSale sale) throws Exception {
		return hisunProductionService.itemLinkManage(sale);
	}

	public List<SysFee> getAllSysFeeList() {
		return this.sysFeeDao.getAllFee();
	}

	/**
	 * 根据商品id查询商品信息
	 * 
	 * @param id
	 * @return
	 */
	public ItemSale findItemSaleById(Long id) {
		return this.itemManageDao.findOne(id);
	}

	/**
	 * 保存商品对象
	 * 
	 * @param itemSale
	 * @return
	 */
	public ItemSale saveItemSale(ItemSale itemSale) {
		return this.itemManageDao.save(itemSale);
	}

	public SysFee findSysFeeById(Long id) {
		return this.sysFeeDao.findOne(id);
	}

	/**
	 * 判断套餐商品所包含的产品有没有下架的
	 * 
	 * @param itemOrgId
	 * @return
	 */
	public String whetherOnline(Long itemOrgId) {
		// boolean flag = true;
		String msg = "";
		List<ItemGroupLink> list = this.itemGroupLinkDao.findByItemOrgId(itemOrgId);
		if (list != null && list.size() > 0) {
			for (ItemGroupLink vo : list) {
				ItemSale sale = this.itemManageDao.findOne(vo.getItemSaleId());
				if (sale == null) {
					msg += vo.getItemSaleId() + "商品不存在。";
					// flag = false;
					// break;
				} else {
					// 如果所包含的商品中有1个下架了，则该套餐商品不能上架
					if (sale.getIsValid() == null || sale.getIsValid() == 0L) {
						msg += sale.getName() + "已下架。";
						// flag = false;
						// sale.getName()
						// break;
					}
				}
			}
		}
		return msg;
	}

	/**
	 * 下架商品，关联下架套餐商品
	 * 
	 * @param itemSaleId
	 * @throws SQLException
	 */
	public String offLineMeal(Long itemSaleId) throws SQLException {
		String msg = "";
		List<ItemGroupLink> list = this.itemGroupLinkDao.findByItemSaleId(itemSaleId);
		if (list != null && list.size() > 0) {
			msg += "该商品影响到:";
			for (ItemGroupLink vo : list) {
				ItemSale sale = this.findItemSaleById(vo.getItemOrgId());
				sale.setIsValid(0L);
				this.addOrUpdateItemSale(sale);
				msg += sale.getName() + "，套餐下架成功。";
			}
		}
		/*
		 * 商品下架时，同时将频道楼层分类中的该商品禁用 。
		 * @add_by macl@c-platform.com
		 * @date 2013-7-12
		 */
		floorCatalogService.updateStatusByItem(itemSaleId, 0);
		return msg;
	}

	public List<ItemPriceTypeOnArea> getItemPriceTypeByAreaCodes(String areaCodes) throws SQLException {
		String sql = " select priceType.* , region.region_name regionName from "
		        + "	      ( select it.* from t_item_price_type it where  it.area_code in( " + areaCodes
		        + "        ))  priceType , t_sys_region region " + "		where  region.region_code=priceType.area_Code ";

		return dbHelper.getBeanList(sql, ItemPriceTypeOnArea.class, null);
	}

	public List<AuditStep> finditemAuditSteplist(Long id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select ads.*  from t_audit_step ads where ads.BS_TABEL = 'T_ITEM_SALE' and ads.BS_ID = ? order by ads.UPDATE_TIME");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(id);
		return dbHelper.getBeanList(sql.toString(), AuditStep.class, paramsList.toArray());
	}

	public String[] getTime(String ids) throws SQLException {
		return dbHelper.getArray("select max(t.sale_start_time) sart ,min(t.sale_stop_time) stop from t_item_sale t where t.id in (" + ids + ")",
		                         null);
	}

	/**
	 * 根据商户结算生效日期来判断是符上架
	 * 
	 * @param itemSaleId
	 *            商品号
	 * @return
	 * @throws SQLException
	 * @author liujun 2013-08-22 17：11
	 */
	public boolean isBolleanItemSaleDateOnline(long itemSaleId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		String value = "0";
		boolean isOnline = false;
		sqlBuff.setLength(0);
		sqlBuff.append("select count(a.id) from T_ITEM_SALE a, t_store b, t_store_settle c ");
		sqlBuff.append("  where a.store_id=b.id ");
		sqlBuff.append(" and b.id=c.store_id ");
		sqlBuff.append(" and b.id=c.store_id ");
		// sqlBuff.append(" and b.sync_gy_flag = '3' ");
		// sqlBuff.append(" and c.sync_gy_flag = '3' ");
		sqlBuff.append(" and to_char(sysdate, 'yyyyMMdd') >= c.effort_date ");
		sqlBuff.append(" and to_char(sysdate, 'yyyyMMdd') < c.expiry_date ");

		sqlBuff.append(" and a.id = ? ");
		params.add(itemSaleId);
		try {
			value = this.dbHelper.queryScalar(sqlBuff.toString(), params.toArray());
		}
		catch (Exception e) {
			e.printStackTrace();
			isOnline = false;
			throw new SQLException();

		}
		if (Integer.valueOf(value) > 0) {
			isOnline = true;
		} else {
			isOnline = false;
		}
		return isOnline;
	}

	/**
	 * 判断商户基本信息清算状态是否符合上架
	 * 
	 * @param itemSaleId
	 *            商品号
	 * @return
	 * @throws SQLException
	 * @author liujun 2013-08-22 17：11
	 */
	public boolean isBolleanItemSaleOnline(long itemSaleId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		String value = "0";
		boolean isOnline = false;
		sqlBuff.setLength(0);
		sqlBuff.append("select count(a.id) from T_ITEM_SALE a, t_store b ");
		sqlBuff.append("  where a.store_id=b.id ");
		// sqlBuff.append(" and b.id=c.store_id ");
		// sqlBuff.append(" and b.id=c.store_id ");
		sqlBuff.append(" and b.sync_gy_flag = '3' ");// 商户清算状态
		// sqlBuff.append(" and c.sync_gy_flag = '3' ");
		// sqlBuff.append(" and to_char(sysdate, 'yyyyMMdd') >= c.effort_date ");
		// sqlBuff.append(" and to_char(sysdate, 'yyyyMMdd') < c.expiry_date ");

		sqlBuff.append(" and a.id = ? ");
		params.add(itemSaleId);
		try {
			value = this.dbHelper.queryScalar(sqlBuff.toString(), params.toArray());
		}
		catch (Exception e) {
			e.printStackTrace();
			isOnline = false;
			throw new SQLException();

		}
		if (Integer.valueOf(value) > 0) {
			isOnline = true;
		} else {
			isOnline = false;
		}
		return isOnline;
	}

	/**
	 * 根据商户结算清算状态判断是否符合上架
	 * 
	 * @param itemSaleId
	 *            商品号
	 * @return
	 * @throws SQLException
	 * @author liujun 2013-08-22 17：11
	 */
	public boolean isBolleanItemSaleSettleOnline(long itemSaleId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		String value = "0";
		boolean isOnline = false;
		sqlBuff.setLength(0);
		sqlBuff.append("select count(a.id) from T_ITEM_SALE a, t_store b, t_store_settle c ");
		sqlBuff.append("  where a.store_id=b.id ");
		sqlBuff.append(" and b.id=c.store_id ");
		sqlBuff.append(" and b.id=c.store_id ");
		// /sqlBuff.append(" and b.sync_gy_flag = '3' ");
		sqlBuff.append(" and c.sync_gy_flag = '3' ");// /商户结算清算状态
		// sqlBuff.append(" and to_char(sysdate, 'yyyyMMdd') >= c.effort_date ");
		// sqlBuff.append(" and to_char(sysdate, 'yyyyMMdd') < c.expiry_date ");

		sqlBuff.append(" and a.id = ? ");
		params.add(itemSaleId);
		try {
			value = this.dbHelper.queryScalar(sqlBuff.toString(), params.toArray());
		}
		catch (Exception e) {
			e.printStackTrace();
			isOnline = false;
			throw new SQLException();

		}
		if (Integer.valueOf(value) > 0) {
			isOnline = true;
		} else {
			isOnline = false;
		}
		return isOnline;
	}

	/**
	 * 获得有效的费率。
	 * 
	 * @param storeId
	 * @return
	 * @throws SQLException
	 */
	public List<SysFee> getValidSysFee(Long storeId) throws SQLException {
		return storeFeeService.getValidById(storeId);
	}

	/**
	 * 商品 关联到 商品协议。
	 * 
	 * @param settleId
	 * @param itemId
	 * @return 1:关联成功，2：该商品已经关联了该协议，3：关联协议失败
	 */
	@Transactional
	public int linkSettle(Long settleId, String itemId) {
		if (null == settleId || StringUtils.isEmpty(itemId)) {
			return 3;
		}
		HisunProductionLink existsLink = hisunProductionLinkDao.findByItemIdAndSettleId(Long.parseLong(itemId.trim()), settleId);
		if (null != existsLink) {
			return 2;
		} else {
			HisunProductionSettle settle = hisunProductionService.findOneSettle(settleId);
			if(settle.getStatus() != 1 || settle.getSyncGyStatus1() !=3  ){
				return 4;
			}
			if( Long.parseLong(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss)) < Long.parseLong(settle.getProductionefftime()) || Long.parseLong(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss)) > Long.parseLong(settle.getProductionexptime())){
				return 5;
			}
			HisunProductionLink link = new HisunProductionLink();
			Long itemSaleId = Long.parseLong(itemId.trim());
			link.setItemId(itemSaleId);
			link.setProductionId(settle.getProductionid());

			// @add_by macl@c-platform.com start>>>
			// itemSale的FeeType字段在商品初审的时候设置
			ItemSale itemSale = findOneItemSale(itemSaleId);
			link.setProductionType(itemSale.getFeeType().toString());
			settle.setProductiontype(itemSale.getFeeType().toString());
			// @add_by macl@c-platform.com <<<end

			/**
			 * 把商品名称绑定到settle对象中
			 * 
			 * @author liujun 2013-08-28 11:54
			 */
			settle.setProductionname(itemSale.getName());

			link.setSettleId(settleId);

			// TODO
			// 这个方法中，是一对一的关联关系，需要修改,根据itemsaleid查询，而不是根据settleId查询重复数据，并删除之。
			hisunProductionService.saveLink(link, settle);
		}

		return 1;
	}

	/**
	 * 取消商品与商品协议的关联。
	 * 
	 * @param settleId
	 * @param itemId
	 * @return 1:成功，0：失败
	 */
	@Transactional
	public int cancelLinkSettle(Long settleId, Long itemId) {
		if (null == settleId || null == itemId) {
			return 0;
		}
		HisunProductionLink existsLink = hisunProductionLinkDao.findByItemIdAndSettleId(itemId, settleId);
		HisunProductionSettle settle = hisunProductionSettleDao.findOne(settleId);
		if (null != existsLink && null != settle) {
			hisunProductionLinkDao.delete(existsLink);
			if (settle.getType() == 1L) {
				hisunProductionSettleDao.delete(settle);
			}
			/**
			 * 关联取消后，删除商品名
			 * 
			 * @auther liujun
			 */
			settle.setProductionname("--");
			hisunProductionSettleDao.save(settle);
			updateSaleGYStatus(itemId, 0L);
			return 1;
		}
		return 0;

	}

	public ItemSale modifyStoreStutas(ItemSale itemSale) {

		return itemManageDao.save(itemSale);
	}

	/**
	 * 查询商品会员级别限制类型
	 */
	public List<ItemPriceType> findItemPriceType(String areaCode) {
		return itemPriceTypeDao.findByAreaCode(areaCode);
	}
	
	/**
	 * 商品上架
	 * @param request
	 * @param result
	 * @param sale
	 * @return
	 * @throws MalformedURLException
	 */
	@Transactional
	public String itemOnline(String msg,ItemSale sale) throws MalformedURLException{
		String result = itemFeeValidator.validateIsLinkSettle(sale);
		if (StringUtils.isNotEmpty(result)) {
			msg += result;
			return msg;
		}
		if (sale.getStatus() != ItemSale.STATUS_BASE_AUDIT_PASS) {
			msg += sale.getName() + "商品状态有误，不能上架!";
			return msg;
		}else if(null!=sale.getStockNum() && sale.getStockNum()==0L){
			msg +=sale.getName()+ "商品库存不足，不能上架!";
			return msg;
		}else {
			sale.setIsValid(1L);
			sale.setGroundingTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
			msg += sale.getName()+ "上架成功," + this.pageStatic(sale);
			this.addOrUpdateItemSale(sale);
		}
		logUtils.logOther("商品上架", "商品id："+sale.getId());
		return msg;
	}
	@Transactional
	public String mealOnine(String msg,ItemSale sale){
		String result = itemFeeValidator.validateIsLinkSettle(sale);
		if (StringUtils.isNotEmpty(result)) {
			msg += result;
			return msg;
		} else if (sale.getStatus() != ItemSale.STATUS_BASE_AUDIT_PASS) {
			msg += sale.getName() + "商品状态有误，不能上架!";
			return msg;
		}else if(null!=sale.getStockNum() && sale.getStockNum()==0L){
			msg +=sale.getName()+ "商品库存不足，不能上架!";
			return msg;
		}else{
			String str = whetherOnline(sale.getId());
			if ("".equals(str)) {
				sale.setIsValid(1L);
				addOrUpdateItemSale(sale);
				msg += sale.getName() + "上架成功！";
			} else {
				msg += str + sale.getName()+"套餐商品里面包含的商品中有已经下架的商品，该套餐商品暂不能上架";
				return msg;
			}
		}
		logUtils.logOther("套餐商品上架", "套餐商品id:"+sale.getId());
		return msg;
	}
	public LsArea findLsAreaById (Long id) throws SQLException{
		return dbHelper.getBean("select * from t_lashou_area t where t.id = ? ", LsArea.class, new Object[]{id});
	}
	public LsCatalog findLsCatalogById(Long id) throws SQLException{
		return dbHelper.getBean("select * from t_lashou_category t where t.id = ? ", LsCatalog.class, new Object[]{id});
	}
}
