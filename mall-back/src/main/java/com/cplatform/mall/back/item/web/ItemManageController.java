package com.cplatform.mall.back.item.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.entity.AuditStep;
import com.cplatform.mall.back.item.dao.ItemManageDao;
import com.cplatform.mall.back.item.entity.HisunProductionSettle;
import com.cplatform.mall.back.item.entity.ItemOrg;
import com.cplatform.mall.back.item.entity.ItemParam;
import com.cplatform.mall.back.item.entity.ItemPrice;
import com.cplatform.mall.back.item.entity.ItemPriceType;
import com.cplatform.mall.back.item.entity.ItemPriceTypeOnArea;
import com.cplatform.mall.back.item.entity.ItemProperty;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.item.entity.ItemSalePayment;
import com.cplatform.mall.back.item.service.HisunProductionService;
import com.cplatform.mall.back.item.service.ItemManageService;
import com.cplatform.mall.back.item.web.validator.ItemFeeValidator;
import com.cplatform.mall.back.item.web.validator.ItemOrgValidator;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.store.dao.StoreDao;
import com.cplatform.mall.back.store.dao.StoreFeeDao;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.sys.entity.SysFee;
import com.cplatform.mall.back.sys.service.SysSearchIdxService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.PathUtil;
import com.cplatform.mall.back.utils.RequestUrl;
import com.cplatform.mall.back.websys.dao.SysPropertyDao;
import com.cplatform.mall.back.websys.entity.SysFileImg;
import com.cplatform.mall.back.websys.entity.SysProperty;
import com.cplatform.mall.back.websys.entity.SysTypeItemParam;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.util2.FileTools;
import com.cplatform.util2.TimeStamp;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-30 上午9:27:20
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/item/item")
public class ItemManageController {

	private static final Logger log = Logger.getLogger(ItemManageController.class);

	/** 产品分类类型 */
	private final Long ITEM_TYPE = 2L;

	@Autowired
	private ItemManageService itemManageService;

	@Autowired
	private ItemManageDao itemManageDao;

	@Autowired
	private ItemOrgValidator itemOrgValidator;

	@Autowired
	private StoreDao storeDao;

	@Autowired
	private SysPropertyDao sysPropertyDao;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private HisunProductionService hisunProductServie;

	@Autowired
	private ItemFeeValidator itemFeeValidator;

	@Autowired
	private StoreFeeDao storeFeeDao;

	@Autowired
	private LogUtils logUtils;

	@Autowired
	private SysSearchIdxService searchIdxService;

	/**
	 * 商品第一步审核列表
	 * 
	 * @param itemOrg
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/auditList")
	public String auditList(ItemSale itemSale, String name, String id, String queryStartTime, String queryEndTime, String groupFlag,
	        @RequestParam(value = "page", defaultValue = "1", required = false) int page,
	        @RequestParam(value = "iseckillFlag", required = false) Long iseckillFlag, HttpSession session, Model model) throws SQLException {
		// 礼品卡功能页面跳转
		itemSale.setIseckill(iseckillFlag);
		model.addAttribute("iseckillFlag", iseckillFlag);

		itemSale.setStatus(ItemSale.STATUS_BASE_NO_AUDIT);
		itemSale.setGroupFlag(0L);
		itemSale.setItemMode(0L);//实物商品
		Page<ItemSale> itemOrgPage = itemManageService.listItemSale(itemSale, page, Page.DEFAULT_PAGESIZE, "");
		model.addAttribute("statusMap", ItemOrg.statusMap);
		model.addAttribute("pageData", itemOrgPage);
		return "/item/item/item-audit-list";
	}

	/**
	 * 商品管理列表
	 * 
	 * @param itemOrg
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String itemList(ItemSale itemSale, @RequestParam(value = "page", defaultValue = "1", required = false) int page,
	        @RequestParam(value = "iseckillFlag", required = false) Long iseckillFlag, HttpSession session, Model model) throws SQLException {
		// 礼品卡功能页面跳转
		itemSale.setIseckill(iseckillFlag);
		model.addAttribute("iseckillFlag", iseckillFlag);

		itemSale.setGroupFlag(0L);// 普通商品
		itemSale.setItemMode(0L);//实物商品
		Page<ItemSale> itemOrgPage = itemManageService.listItemSale(itemSale, page, Page.DEFAULT_PAGESIZE, "");
		model.addAttribute("statusMap", ItemSale.statusMap);
		model.addAttribute("pageData", itemOrgPage);
		model.addAttribute("syncGyFlagMap", ItemSale.syncGyFlagMap);
		model.addAttribute("syncGyFlagMsg", Store.syncGyFlagMsg);
		model.addAttribute("isValidMap", ItemSale.isValidMap);
		model.addAttribute("iseckillMap", ItemSale.iseckillMap);
		return "/item/item/item-list";
	}

	/**
	 * 商品第二步审核列表
	 * 
	 * @param itemOrg
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/twoAuditList")
	public String auditListTwo(ItemSale itemSale, @RequestParam(value = "page", defaultValue = "1", required = false) int page,
	        @RequestParam(value = "iseckillFlag", required = false) Long iseckillFlag, HttpSession session, Model model) throws SQLException {
		// 礼品卡功能页面跳转
		itemSale.setIseckill(iseckillFlag);
		model.addAttribute("iseckillFlag", iseckillFlag);

		itemSale.setStatus(ItemSale.STATUS_BASE_AUDITING);
		itemSale.setGroupFlag(0L);
		itemSale.setItemMode(0L);//实物商品
		Page<ItemSale> itemOrgPage = itemManageService.listItemSale(itemSale, page, Page.DEFAULT_PAGESIZE, "");

		model.addAttribute("statusMap", ItemOrg.statusMap);
		model.addAttribute("pageData", itemOrgPage);
		return "/item/item/item-audit-list-two";
	}

	/**
	 * 商品预添加
	 * 
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/preAdd")
	public String preAddItem(Model model) throws UnsupportedEncodingException {
		ItemSale itemOrg = new ItemSale();
		model.addAttribute("itemModeMap", ItemOrg.itemModeMap);// 商品类型
		model.addAttribute("virtualTypeMap", ItemOrg.virtualTypeMap);// 虚拟类型
		model.addAttribute("groupFlagMap", ItemOrg.groupFlagMap);// 商户-门店-渠道商
		model.addAttribute("iseckillMap", itemOrg.iseckillMap);// 是否营销商品

		model.addAttribute("itemOrg", itemOrg);
		List<SysFee> feeList = this.itemManageService.getAllSysFeeList();
		model.addAttribute("feeList", feeList);

		// 查询属性
		Iterator<SysProperty> it = sysPropertyDao.findAll().iterator();
		List<SysProperty> sysPropertyList = new ArrayList<SysProperty>();
		while (it.hasNext()) {
			sysPropertyList.add(it.next());
		}
		model.addAttribute("sysPropertyList", sysPropertyList);
		// 获取会员级别类型
		List<ItemPriceType> pricetypeList = itemManageService.findItemPriceType("320000");
		model.addAttribute("pricetypeList", pricetypeList);
		return "/item/item/item-add";
	}

	/**
	 * 商品添加
	 * 
	 * @param request
	 * @param itemOrg
	 * @param session
	 * @param model
	 * @param result
	 * @param propertyIds
	 * @param itemProperyNames
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addItem(MultipartFile uploadfile, ItemSale sale, HttpSession session,
	        @RequestParam(value = "propertyId", required = false) Long[] propertyIds,
	        @RequestParam(value = "itemProperyName", required = false) String[] itemProperyNames,
	        @RequestParam(value = "flag", required = false) String[] flag, @RequestParam(required = false) String unlimitedStockNum, Model model,
	        MultipartHttpServletRequest request, BindingResult result) {
		try {
			itemOrgValidator.validate(sale, result);
			if (result.hasErrors()) {
				return JsonRespWrapper.error(result.getFieldErrors());

			}
			SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
			sale.setCreateUserId(sessionUser.getId());
			sale.setCreateTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
			sale.setSyncGyFlag(0L);
			sale.setItemMode(0L);
			if (null != unlimitedStockNum) {
				sale.setStockNum(ItemSale.INIT_STOCK_NUM);
			}
			sale.setStatus(ItemSale.STATUS_BASE_NO_AUDIT);
			sale.setIsView(1L);
			if (sale.getSaleStartTime().length() <= 8) {// 补入时分秒
				sale.setSaleStartTime(sale.getSaleStartTime() + " 00:00:00");
			}
			if (sale.getSaleStopTime().length() <= 8) {// 补入时分秒
				sale.setSaleStopTime(sale.getSaleStopTime() + " 23:59:59");
			}

			if (null == sale.getPostFlag()) {
				sale.setPostFlag(0L);
			}

			itemManageService.addItemInfo(sale, uploadfile, propertyIds, itemProperyNames, request, flag);
			// 这边添加时礼品卡商品跳转，后续添加别的商品时可以else if
			logUtils.logAdd("添加商品", "商品id:" + sale.getId());
			if (2L == sale.getIseckill() || 4L == sale.getIseckill()) {
				return JsonRespWrapper.success("添加商品成功。", "/item/item/list?iseckillFlag=2");
			} else {
				return JsonRespWrapper.success("添加商品成功。", "/item/item/list");
			}
		}
		catch (Exception e) {
			logUtils.logAdd("添加商品", "操作失败");
			log.error(e.getMessage());
			return JsonRespWrapper.error(e.getMessage());
		}

	}

	/**
	 * 商品录入时load属性参数
	 * 
	 * @param typeId
	 *            分类id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loadProperty/{typeId}/{storeId}", method = RequestMethod.GET)
	public String loadProperty(@PathVariable Long typeId, @PathVariable Long storeId, @RequestParam(required = false) Long itemId, Model model) {

		// 根据分类查询参数
		List<SysTypeItemParam> typeItemParamList = itemManageService.getTypeParamsLoad(typeId, itemId, storeId);

		model.addAttribute("typeItemParamList", typeItemParamList);
		return "/item/item/itemTypeParamsTag";
	}

	/**
	 * 根据区域编码获取价格配置
	 * 
	 * @param contentCode
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getPrice/{areaCode}", method = RequestMethod.POST)
	@ResponseBody
	public Object getPrice(@PathVariable String areaCode) throws SQLException {
		List<ItemPriceTypeOnArea> priceTypeList = new ArrayList<ItemPriceTypeOnArea>();

		/*
		 * @modify_by macl@c-platform.com
		 * @date 2013-7- start...
		 */
		// priceTypeList = priceTypeDao.findByAreaCode(areaCode);

		priceTypeList = itemManageService.getItemPriceTypeByAreaCodes(areaCode);
		/* ... end */

		return priceTypeList;
	}

	/**
	 * 进入商品照片添加
	 * 
	 * @param itemId
	 *            商品id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/img", method = RequestMethod.GET)
	public String imgAddItem(@RequestParam(value = "itemId", required = false) Long itemId, Model model) {
		model.addAttribute("itemId", itemId);
		try {
			// 普通图片
			List<SysFileImg> fileLink = itemManageService.getItemImg(itemId, BsFileService.BS_ITEM_PIC_KEY);
			model.addAttribute("fileLink", fileLink);
			// 封面图片
			List<SysFileImg> coverFileLink = itemManageService.getItemImg(itemId, BsFileService.BS_ITEM_COVER_PIC_KEY);
			model.addAttribute("coverFileLink", coverFileLink);
			model.addAttribute("itemId", itemId);
		}
		catch (SQLException e) {
			log.error(e.getMessage());
		}
		// 查询图片
		return "/item/item/item-add-three";
	}

	/**
	 * 上传商品图片
	 * 
	 * @param itemId
	 *            商品id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/imgUpload/{itemId}")
	@ResponseBody
	public Object imgAddItem(@PathVariable Long itemId, MultipartFile Filedata, Model model) {
		String fileName = Filedata.getOriginalFilename();
		SysFileImg fileImg = null;
		try {
			String ext = FileTools.getExtFilename(fileName);
			fileImg = itemManageService.saveImg(itemId, Filedata.getInputStream(), ext);
			String path = PathUtil.getItemPic(itemId, 4);

			String imgPath = appConfig.getUploadItemPath() + path + fileImg.getFileName();
			fileImg.setFileWebPath(imgPath);
			ItemSale sale = itemManageService.findItemSaleById(itemId);
			sale.setIsValid(0L);
			itemManageService.modifyStoreStutas(sale);

		}
		catch (IOException e) {
			logUtils.logAdd("商品添加图片", "操作失败，商品id:" + itemId + e.getMessage());
			log.error("上传商品图片失败：" + e.getMessage());
		}
		logUtils.logAdd("商品添加图片", "商品id:" + itemId);
		return fileImg;
	}

	/**
	 * 删除商品图片
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteItemImgAjax/{fileId}", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteItemImgAjax(@PathVariable Long fileId, Model model) {
		try {
			itemManageService.deleteItemPic(fileId);
			logUtils.logDelete("商品删除图片", "");
			return JsonRespWrapper.successAlert("删除成功！");
		}
		catch (Exception e) {
			logUtils.logDelete("商品删除图片失败", e.getMessage());
			log.error("删除商品图片失败：" + e.getMessage());
			return JsonRespWrapper.successAlert("删除失败！");
		}
		// 跳转到列表页面

	}

	/**
	 * 设置商品封面图片
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/coverItemImgAjax/{fileId}/{itemId}", method = RequestMethod.POST)
	@ResponseBody
	public Object coverItemImgAjax(@PathVariable Long fileId, @PathVariable Long itemId, Model model) {
		try {
			itemManageService.coverItemImg(fileId, itemId);
			return JsonRespWrapper.successAlert("设置成功！");
		}
		catch (Exception e) {
			log.error("设置商品封面图片失败：" + e.getMessage());
			return JsonRespWrapper.successAlert("设置失败！");
		}
		// 跳转到列表页面

	}

	/**
	 * 商品基本信息修改
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws UnsupportedEncodingException
	 */

	@RequestMapping(value = "/preEdit/{id}", method = RequestMethod.GET)
	public String preEditItem(@PathVariable Long id, Model model) throws SQLException, UnsupportedEncodingException {
		ItemSale sale = itemManageService.findItemSaleById(id);
		if (null != sale.getRemark()) {
			sale.setRemark(sale.getRemark().trim());// 兼容导入的商品数据
		}
		if (StringUtils.isNotEmpty(sale.getPrice())) {
			if (sale.getPrice().startsWith(".")) {
				sale.getPrice().replace(".", "0.");
			}
		}

		if (sale.getSaleStartTime().length() <= 8) {// 补入时分秒
			sale.setSaleStartTime(sale.getSaleStartTime() + "000000");
		}
		if (sale.getSaleStopTime().length() <= 8) {
			sale.setSaleStopTime(sale.getSaleStopTime() + "235959");
		}
		// 获取商品标签列表
		String tag = itemManageService.getTag(id);
		if (null != tag && !"".equals(tag)) {
			sale.setTag(tag.replaceAll(",", ";"));
		}
		// 获取分类名称
		String typeName = itemManageService.getTypeName(sale.getTypeId());
		sale.setTypeName(typeName);
		model.addAttribute("itemModeMap", ItemSale.itemModeMap);// 商品类型
		model.addAttribute("virtualTypeMap", ItemSale.virtualTypeMap);// 虚拟类型
		model.addAttribute("groupFlagMap", ItemSale.groupFlagMap);// 商户-门店-渠道商
		model.addAttribute("iseckillMap", sale.iseckillMap);// 是否营销商品
		model.addAttribute("sale", sale);
		Store store = storeDao.findOne(sale.getStoreId());
		model.addAttribute("store", store);
		List<SysFee> feeList = this.itemManageService.getAllSysFeeList();
		model.addAttribute("feeList", feeList);
		// 查询系统属性，下拉列表用
		Iterator<SysProperty> it = sysPropertyDao.findAll().iterator();
		List<SysProperty> sysPropertyList = new ArrayList<SysProperty>();
		for (; it.hasNext();) {
			sysPropertyList.add(it.next());
		}
		model.addAttribute("sysPropertyList", sysPropertyList);
		// 查询用户添加的属性
		List<ItemProperty> itemProperty = itemManageService.getItemProperty(id);
		model.addAttribute("itemProperty", itemProperty);

		// 根据分类查询参数
		List<SysTypeItemParam> typeItemParamList = itemManageService.getTypeParamsLoad(sale.getTypeId(), id, 0l);
		model.addAttribute("typeItemParamList", typeItemParamList);
		itemManageService.getItemSale(sale);
		// 如果不限库存
		if (ItemSale.INIT_STOCK_NUM.equals(sale.getStockNum())) {
			model.addAttribute("storeNum", sale.getStockNum());
			sale.setStockNum(0l);
		}
		model.addAttribute("initStoreNum", ItemSale.INIT_STOCK_NUM);

		/**
		 * 获取所有配置价格，包括已配置价格
		 * 
		 * @modify_by macl@c-platform.com start>>>>>
		 **/
		// 已配置价格
		// List<ItemPrice> priceList = itemManageService.getPriceList(id);
		// model.addAttribute("priceList", priceList);
		List<ItemPriceType> priceTypeList = itemManageService.getAllPriceTypeList(id);
		model.addAttribute("priceList", priceTypeList);
		/** 获取所有配置价格，包括已配置价格 <<<<<end **/

		model.addAttribute("map", ItemPriceType.priceTypeMap);
		model.addAttribute("sale", sale);

		List<ItemParam> paralist = itemManageService.findCustomParamListByItemId(sale.getId());
		model.addAttribute("paralist", paralist);

		// 获取会员级别类型
		List<ItemPriceType> pricetypeList = itemManageService.findItemPriceType("320000");
		model.addAttribute("pricetypeList", pricetypeList);

		return "/item/item/item-edit";
	}

	/**
	 * 商品修改
	 * 
	 * @param request
	 * @param itemOrg
	 * @param session
	 * @param model
	 * @param result
	 * @param propertyIds
	 * @param itemProperyNames
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/preEdit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object editItem(MultipartFile uploadfile, ItemSale sale, HttpSession session,
	        @RequestParam(value = "propertyId", required = false) Long[] propertyIds,
	        @RequestParam(value = "itemProperyName", required = false) String[] itemProperyNames,
	        @RequestParam(value = "flag", required = false) String[] flag, @RequestParam(required = false) String unlimitedStockNum, Model model,
	        MultipartHttpServletRequest request, BindingResult result) {
		try {
			itemOrgValidator.validate(sale, result);
			if (result.hasErrors()) {
				return JsonRespWrapper.error(result.getFieldErrors());

			}
			SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
			ItemSale oldItemOrg = itemManageService.findItemSaleById(sale.getId());
			sale.setCreateUserId(oldItemOrg.getCreateUserId());
			sale.setCreateTime(oldItemOrg.getCreateTime());
			sale.setUpdateTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
			sale.setUpdateUserId(sessionUser.getId());
			sale.setImgPath(oldItemOrg.getImgPath());
			sale.setShopUserId(oldItemOrg.getShopUserId());
			sale.setShopClass(oldItemOrg.getShopClass());
			sale.setStoreId(oldItemOrg.getStoreId());
			sale.setSyncGyFlag(oldItemOrg.getSyncGyFlag());
			sale.setIsView(oldItemOrg.getIsView());
			sale.setItemMode(0L);
			if (sale.getSaleStartTime().length() <= 10) {// 补入时分秒
				sale.setSaleStartTime(sale.getSaleStartTime() + " 00:00:00");
			}
			if (sale.getSaleStopTime().length() <= 10) {
				sale.setSaleStopTime(sale.getSaleStopTime() + " 23:59:59");
			}
			if (null != unlimitedStockNum) {
				sale.setStockNum(ItemSale.INIT_STOCK_NUM);
			}
			Store store = this.storeDao.findOne(sale.getStoreId());
			if (store.getItemEditAuditFlag() == 1L) {
				sale.setStatus(oldItemOrg.getStatus());
			} else {
				sale.setStatus(ItemSale.STATUS_BASE_NO_AUDIT);
			}
			if (null == sale.getPostFlag()) {
				sale.setPostFlag(0L);
			}
			sale.setCashIdgoods(oldItemOrg.getCashIdgoods());
			sale.setCoinIdgoods(oldItemOrg.getCoinIdgoods());
			sale.setScoreIdgoods(oldItemOrg.getScoreIdgoods());
			itemManageService.addItemInfo(sale, uploadfile, propertyIds, itemProperyNames, request, flag);
			// return JsonRespWrapper.success("修改商品信息成功", "/item/item/list");
			// 返回到来源页面
			String backUrl = request.getParameter("backUrl");
			logUtils.logModify("修改商品", "商品id:" + sale.getId());
			return JsonRespWrapper.success("修改商品信息成功", backUrl);
		}
		catch (Exception e) {
			logUtils.logModify("修改商品", "修改失败，商品id:" + sale.getId());
			log.error(e.getMessage());
			return JsonRespWrapper.error(e.getMessage());
		}

	}

	/**
	 * 商品删除
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws MalformedURLException
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object deleteItem(@RequestParam(value = "itemId", required = false) String itemId, Model model, HttpServletRequest request)
	        throws MalformedURLException {
		String[] idarray = itemId.split(",");
		if (idarray != null) {
			for (String id : idarray) {
				if (!"".equals(id)) {
					ItemSale sale = this.itemManageService.findItemSaleById(Long.parseLong(id));
					sale.setStatus(ItemSale.STATUS_DELETE);
					// add by xueqiang >>>
					sale.setIsValid(0L);
					// <<<
					this.itemManageService.saveItemSale(sale);
				}
				logUtils.logDelete("商品删除", "商品id:" + id);
			}
		}
		URL url = new URL(appConfig.getSearchidxurl());
		String msg = searchIdxService.searchIndexUpdate(request, url, "全量");
		return JsonRespWrapper.successReload("删除成功," + msg);
	}

	/**
	 * 删除商品属性
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteItemPropertyAjax/{propertyId}", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteItemPropertyAjax(@PathVariable Long propertyId, Model model) throws IOException, Exception {

		try {
			itemManageService.deleteItemProperty(propertyId);
			logUtils.logDelete("删除商品属性", "");
			return JsonRespWrapper.successAlert("删除成功！");
		}
		catch (Exception e) {
			logUtils.logDelete("删除商品属性失败", e.getMessage());
			return JsonRespWrapper.error("删除失败：" + e.getMessage());
		}
	}

	/**
	 * 商品基本信息查看
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws UnsupportedEncodingException
	 */

	@RequestMapping(value = "/view/{id}")
	public String preViewItem(@PathVariable Long id, Model model) throws SQLException, UnsupportedEncodingException {
		ItemSale sale = itemManageService.findItemSaleById(id);
		List<AuditStep> auditStepList = this.itemManageService.finditemAuditSteplist(sale.getId());
		// 获取商品标签列表
		String tag = itemManageService.getTag(id);
		if (null != tag && !"".equals(tag)) {
			sale.setTag(tag.replaceAll(",", ";"));
		}
		// 获取分类名称
		String typeName = itemManageService.getTypeName(sale.getTypeId());
		sale.setTypeName(typeName);
		model.addAttribute("itemModeMap", ItemSale.itemModeMap);// 商品类型
		model.addAttribute("virtualTypeMap", ItemSale.virtualTypeMap);// 虚拟类型
		model.addAttribute("groupFlagMap", ItemSale.groupFlagMap);// 商户-门店-渠道商
		model.addAttribute("iseckillMap", ItemSale.iseckillMap);
		model.addAttribute("sale", sale);
		Store store = storeDao.findOne(sale.getStoreId());
		model.addAttribute("store", store);
		List<SysFee> feeList = this.itemManageService.getAllSysFeeList();
		model.addAttribute("feeList", feeList);

		// 查询系统属性，下拉列表用
		Iterator<SysProperty> it = sysPropertyDao.findAll().iterator();
		List<SysProperty> sysPropertyList = new ArrayList<SysProperty>();
		for (; it.hasNext();) {
			sysPropertyList.add(it.next());
		}
		model.addAttribute("sysPropertyList", sysPropertyList);
		// 查询用户添加的属性
		List<ItemProperty> itemProperty = itemManageService.getItemProperty(id);
		model.addAttribute("itemProperty", itemProperty);

		// 根据分类查询参数
		List<SysTypeItemParam> typeItemParamList = itemManageService.getTypeParamsLoad(sale.getTypeId(), id, 0l);
		model.addAttribute("typeItemParamList", typeItemParamList);
		// ////
		itemManageService.getItemSale(sale);
		// 如果不限库存
		if (ItemSale.INIT_STOCK_NUM.equals(sale.getStockNum())) {
			model.addAttribute("storeNum", sale.getStockNum());
			sale.setStockNum(0l);
		}
		model.addAttribute("initStoreNum", ItemSale.INIT_STOCK_NUM);
		List<ItemPrice> priceList = itemManageService.getPriceList(id);
		model.addAttribute("priceList", priceList);

		model.addAttribute("map", ItemPriceType.priceTypeMap);

		model.addAttribute("sale", sale);

		List<ItemParam> paralist = itemManageService.findCustomParamListByItemId(sale.getId());
		model.addAttribute("paralist", paralist);
		model.addAttribute("auditStepList", auditStepList);

		// 获取会员级别类型
		List<ItemPriceType> pricetypeList = itemManageService.findItemPriceType("320000");
		model.addAttribute("pricetypeList", pricetypeList);

		// 获取商品支付扩展信息
		ItemSalePayment itemSalePayment = itemManageService.findItemSalePaymentByItemId(sale.getId());
		model.addAttribute("itemSalePayment", itemSalePayment);

		model.addAttribute("scoreIdgoodsMap", ItemSale.scoreIdgoodsMap);
		model.addAttribute("cashIdgoodsMap", ItemSale.cashIdgoodsMap);
		model.addAttribute("coinIdgoodsMap", ItemSale.coinIdgoodsMap);

		return "/item/item/item-view";
	}

	/**
	 * 商品上架下架
	 * 
	 * @param id
	 * @param optype
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsOnLine/{optype}")
	@ResponseBody
	public Object goodsOnLine(@RequestParam(value = "itemId", required = false) String itemId, @PathVariable String optype, Model model,
			HttpServletRequest request) throws Exception {
		String[] idarray = itemId.split(",");
		String msg = "";
		String str = "索引增量更新成功";
		if (idarray != null) {
			for (String id : idarray) {
				if ("".equals(id)) {
					continue;
				}
				ItemSale sale = this.itemManageService.findItemSaleById(Long.valueOf(id));
				// 上架
				if ("online".equals(optype)) {
					msg = itemManageService.itemOnline(msg, sale);
					itemManageService.saveItemSale(sale);
					logUtils.logOther("商品上架", "商品id：" + id);
				} else {// 下架
					sale.setIsValid(0L);
					try {
						msg += this.itemManageService.offLineMeal(sale.getId());
					}
					catch (SQLException e) {
						msg += sale.getName() + "下架失败。";
						continue;
					}
					msg += sale.getName() + "下架成功。";
					this.itemManageService.addOrUpdateItemSale(sale);
					logUtils.logOther("商品下架", "商品id：" + id);
				}
			}
			try {
				RequestUrl.get(appConfig.getSearchidxaddurl());
			}
			catch (Exception e) {
				str = "索引增量更新失败!";
				log.error(e.getMessage());
				logUtils.logOther("索引增量更新失败", e.getMessage());
			}
			log.info(str);
		}
		return JsonRespWrapper.successReload(msg + str);
	}

	/**
	 * 商品送审
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendToAudit")
	@ResponseBody
	public Object sendToAudit(@RequestParam Long id, Model model) {
		ItemSale sale = itemManageService.findItemSaleById(id);
		String result = itemManageService.sendToAudit(sale);
		return JsonRespWrapper.successReload(result);
	}

	/**
	 * 进入第一步审核填写意见页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditing/{id}", method = RequestMethod.GET)
	public String toAudit(@PathVariable Long id, Model model) throws IOException, Exception {
		AuditStep step = new AuditStep();
		step.setBsId(id);
		model.addAttribute("step", step);
		ItemSale sale = itemManageService.findItemSaleById(id);
		itemManageService.getItemSale(sale);
		model.addAttribute("itemSale", sale);

		/**
		 * 商户费率改从t_store_fee表中查询
		 * 
		 * @modify_by macl@c-platform.com start...
		 */
		// List<SysFee> feeList = this.itemManageService.getAllSysFeeList();
		List<SysFee> feeList = this.itemManageService.getValidSysFee(sale.getStoreId());
		/** 商户费率改从t_store_fee表中查询 ... end */

		model.addAttribute("feeList", feeList);

		// 获取会员级别类型
		List<ItemPriceType> pricetypeList = itemManageService.findItemPriceType("320000");
		model.addAttribute("pricetypeList", pricetypeList);

		// 获取商品支付扩展信息
		ItemSalePayment itemSalePayment = itemManageService.findItemSalePaymentByItemId(sale.getId());
		model.addAttribute("itemSalePayment", itemSalePayment);

		// 防止AJAX缓存
		model.addAttribute("nowTime", TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));

		// 跳转到列表页面
		return "/item/item/item-audit";
	}

	/**
	 * 第一步审核操作
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/audit/{id}")
	@ResponseBody
	public Object audit(ItemSale sale, @RequestParam(value = "nowTime", required = false) String nowTime, @PathVariable Long id,
	        @ModelAttribute("step") AuditStep step, Model model, HttpServletRequest request) {
		if (sale.getPayType() == 0) {// 单一支付类型
			if (sale.getCashIdgoods() == null || "".equals(sale.getCashIdgoods())) {
				sale.setCashIdgoods(0L);
			}
			if (sale.getScoreIdgoods() == null || "".equals(sale.getScoreIdgoods())) {
				sale.setScoreIdgoods(0L);
			}
			if (sale.getCoinIdgoods() == null || "".equals(sale.getCoinIdgoods())) {
				sale.setCoinIdgoods(0L);
			}
			// 组合支付比例
			sale.setCashPayRatio(0l);
			sale.setOtherPayRatio(0l);
		} else {// 组合支付类型
			sale.setCashIdgoods(0L);
			sale.setScoreIdgoods(0L);
			sale.setCoinIdgoods(0L);

			if (sale.getGroupPayType() == 1) {// 现金+商城币
				sale.setCashIdgoods(1L);
				sale.setCoinIdgoods(1L);
			} else { // 现金+积分
				sale.setCashIdgoods(1L);
				sale.setScoreIdgoods(1L);
			}
		}
		// 是否支付话费支付
		if (sale.getBillIdGoods() == null || "".equals(sale.getBillIdGoods())) {
			sale.setBillIdGoods(0L);
		}
		try {
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			step.setAuditUserId(sessionUser.getId());
			// 更新到最新的商品状态，如果用sale.getStatus就不是最新的状态了
			ItemSale is = itemManageService.findItemSaleById(id);
			long status = is.getStatus();
			if (1L != status) {
				return JsonRespWrapper.success("操作失败，商品状态错误", "/item/item/auditList");
			}
			itemManageService.auditItemSale(sale, step);
			// return JsonRespWrapper.success("操作成功", "/item/item/auditList");
			// 返回到来源页面
			String backUrl = request.getParameter("backUrl");
			logUtils.logAudit("商品初审", "商品id：" + id);
			return JsonRespWrapper.success("操作成功", backUrl);
		}
		catch (Exception ex) {
			// 未知的错误消息，一般是exception catch后通知用户
			logUtils.logAudit("商品初审操作失败", "商品id：" + id);
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 进入第二步审核填写意见页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/twoAuditing/{id}", method = RequestMethod.GET)
	public String twoAudit(@PathVariable Long id, Model model) throws IOException, Exception {
		List<AuditStep> auditStepList = this.itemManageService.finditemAuditSteplist(id);
		model.addAttribute("id", id);
		String auditRemark = auditStepList.get(auditStepList.size() - 1).getRemark();
		model.addAttribute("auditRemark", auditRemark);
		return "/item/item/item-audit-two";
	}

	/**
	 * 商品第二步审核
	 * 
	 * @param id
	 * @param status
	 * @param model
	 * @return
	 * @throws MalformedURLException
	 */
	@RequestMapping(value = "/auditTwo/{id}")
	@ResponseBody
	public Object audit(@PathVariable Long id, @RequestParam(value = "status", required = true) Long status,
	        @RequestParam(value = "remark", required = true) String remark, Model model, HttpServletRequest request) throws MalformedURLException {
		ItemSale sale = itemManageService.findItemSaleById(id);
		sale.setStatus(status);
		AuditStep step = new AuditStep();
		step.setAuditUserId(SessionUser.getSessionUser().getId());
		// if (ItemSale.STATUS_BASE_AUDIT_RETJECT.equals(status)) {
		// step.setStatus(2L);
		// } else {
		// step.setStatus(1L);
		// }
		step.setStatus(status);
		step.setRemark(remark);
		sale.setAuditTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
		String result = itemManageService.auditItem(sale, step);

		// return JsonRespWrapper.success(result, "/item/item/twoAuditList");
		// 返回到来源页面
		String backUrl = request.getParameter("backUrl");
		// 审核通过之后直接上架
		if ("审核通过".equals(result)) {
			String msg = itemManageService.itemOnline(result + "，", sale);
			String str = "索引增量更新成功！";
			try {
				RequestUrl.get(appConfig.getSearchidxaddurl());
			}
			catch (Exception e) {
				str = "索引增量更新失败！";
			}
			return JsonRespWrapper.success(msg + str, backUrl);
		} else {
			return JsonRespWrapper.success(result, backUrl);
		}

	}

	// -------------------商品查询选择--------------------

	/**
	 * 优惠套餐选择
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectItems")
	public String selectItems(ItemSale itemSale, String ids, @RequestParam(required = false, value = "page", defaultValue = "1") int page, Model model)
	        throws IOException, Exception {
		Page<ItemSale> itemPage = null;

		if (ids != null && !"".equals(ids)) {
			if (ids.endsWith(",")) {
				ids = ids.substring(0, ids.lastIndexOf(","));
			}
			String[] idStrs = ids.split(",");
			List<Long> idsList = new ArrayList<Long>();
			for (String id : idStrs) {
				idsList.add(Long.valueOf(id));
			}
			List<ItemSale> rightItemlList = itemManageDao.findByIdInAndStatus(idsList, ItemSale.STATUS_BASE_AUDIT_PASS);
			model.addAttribute("rightItemlList", rightItemlList);
		}
		itemSale.setStatus(ItemSale.STATUS_BASE_AUDIT_PASS);
		itemSale.setGroupFlag(0L);// 普通商品
		itemSale.setIsValid(1L);// 上架商品
		itemSale.setStockNumFilter(0L);
		itemPage = itemManageService.listItemSale(itemSale, page, 15, ids);
		model.addAttribute("itemPage", itemPage);
		// 跳转到列表页面
		return "/item/item/item-multiple-list";
	}

	/**
	 * 优惠套餐选择
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectSingleItem")
	public String selectSingleItem(ItemSale itemSale, @RequestParam(value = "ids", required = false) String ids,
	        @RequestParam(required = false, value = "page", defaultValue = "1") int page, Model model) throws IOException, Exception {
		Page<ItemSale> itemPage = null;
		itemSale.setStatus(ItemSale.STATUS_BASE_AUDIT_PASS);
		itemSale.setGroupFlag(0L);// 普通商品
		itemSale.setIsValid(1L);// 上架商品
		itemPage = itemManageService.listItemSale(itemSale, page, 10, "");

		model.addAttribute("itemPage", itemPage);
		// 跳转到列表页面
		return "/item/item/item-single-list";
	}

	/**
	 * 优惠套餐选择
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectItemAjaxQuery")
	@ResponseBody
	public Object groupItemsAjax(ItemSale itemSale, @RequestParam(required = false, value = "page", defaultValue = "1") int page, Model model)
	        throws IOException, Exception {

		itemSale.setStatus(ItemSale.STATUS_BASE_AUDIT_PASS);
		itemSale.setIsValid(1L);// 上架商品
		itemSale.setGroupFlag(0L);// 普通商品
		// 跳转到列表页面
		Page<ItemSale> itemPage = itemManageService.listItemSale(itemSale, page, 15, "");
		return itemPage;
	}

	/**
	 * 同步高阳，生成商品协议时调用此方法。
	 * 
	 * @param id
	 * @return
	 */
	// @RequestMapping(value = "/syncGy/{id}")
	@RequestMapping(value = "/getSettles/syncGy/{id}")
	@ResponseBody
	public Object syncGy(@PathVariable Long id, Model model) {
		try {
			ItemSale vo = itemManageService.findItemSaleById(id);
			String msg = "同步完成";
			Map<String, String> flag = this.itemManageService.syncItem(vo);
			// if () {
			// msg = "同步失败";
			// }
			// SyncGYResponseBean bean = null;
			// if (vo != null) {
			// if (vo.getSyncGyFlag() == 0L) {
			// bean = this.syncInterface.syncAddGoods(vo);
			// } else {
			// bean = this.syncInterface.syncUpdateGoods(vo);
			// }
			// if (bean != null) {
			// vo.setSyncGyFlag(Long.valueOf(bean.getCode()));
			// this.itemSaleService.save(vo);
			// msg = bean.getMsg();
			// } else {
			// msg = "同步失败";
			// }
			// } else {
			// return JsonRespWrapper.success("未找到商品资料", "/item/saleitem/list");
			// }

			/**
			 * 跳转到协议配置页面
			 * 
			 * @modify_by macl@c-platform.com
			 */
			logUtils.logOther("同步高阳，生成商品协议时调用方法", "");
			return JsonRespWrapper.success(flag.get("msg"), "/item/item/getSettles?itemId=" + id);

		}
		catch (Exception ex) {
			// 未知的错误消息，一般是exception catch后通知用户
			logUtils.logOther("同步操作失败", ex.getMessage());
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 同步高阳，批量同步
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getSettles/batchSyncGy")
	@ResponseBody
	public Object batchSyncGy(@RequestParam(value = "itemId", required = false) String itemId, Model model) {
		String[] idarray = itemId.split(",");
		String msg = "";
		if (idarray != null) {
			for (String id : idarray) {
				if (!"".equals(id)) {
					ItemSale vo = itemManageService.findItemSaleById(Long.parseLong(id));
					try {
						Map<String, String> flag = this.itemManageService.syncItem(vo);
						if (flag.get("msg") == null || "".equals(flag.get("msg"))) {
							msg += vo.getName() + "同步完成！";
						} else {
							msg += vo.getName() + flag.get("msg");
						}
					}
					catch (Exception ex) {
						// 未知的错误消息，一般是exception catch后通知用户
						msg += vo.getName() + "同步异常！";
						log.error(ex.getMessage());
						logUtils.logOther("同步异常", ex.getMessage());
					}
				}
				logUtils.logOther("同步高阳，批量同步", "商品id：" + id);
			}
		}
		return JsonRespWrapper.successReload(msg);

	}

	/**
	 * 获得商品协议。
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/getSettles")
	public String getSettles(@ModelAttribute("settle") HisunProductionSettle settle, @RequestParam(value = "itemId", required = false) Long itemId,
	        @RequestParam(value = "page", defaultValue = "1", required = false) int page, Model model) throws SQLException {

		ItemSale itemSale = itemManageService.findItemSaleById(itemId);
		settle.setStoreId(itemSale.getStoreId().toString());
		settle.setType(0L);
		// 该商户所有的商品协议
		Page<HisunProductionSettle> settleList = hisunProductServie.findSettle(settle, page);

		// 已关联的协议
		HisunProductionSettle linkedSettle = null;
		try {
			linkedSettle = hisunProductServie.findLinkedSettle(itemSale.getId());
		}
		catch (SQLException e) {
			log.error("查询商品已关联的协议失败：" + e.getMessage());
		}

		model.addAttribute("settlePage", settleList);
		model.addAttribute("itemSale", itemSale);
		model.addAttribute("linkedSettle", linkedSettle);
		model.addAttribute("statusMap", HisunProductionSettle.getStatusMap());
		model.addAttribute("typeMap", HisunProductionSettle.getTypeMap());

		return "/item/item/item-settle-list";
	}

	/**
	 * 商品 关联到 商品协议。
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getSettles/linkSettle")
	@ResponseBody
	public Object linkSettle(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "itemId", required = true) String itemId,
	        Model model) {
		String msg = "";
		int result = itemManageService.linkSettle(Long.parseLong(id), itemId);

		if (1 == result) {
			msg = "关联商品协议成功！";
		} else if (2 == result) {
			msg = "已关联该协议，不可重复关联！";
			return JsonRespWrapper.successAlert(msg);
		} else {
			msg = "关联协议失败！";
			return JsonRespWrapper.successAlert(msg);
		}
		return JsonRespWrapper.success(msg, "/item/item/getSettles?itemId=" + itemId);
	}

	/**
	 * 取消商品关联协议
	 * 
	 * @param settleId
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/getSettles/cancelLinkSettle")
	@ResponseBody
	public Object cancelLinkSettle(@RequestParam(value = "settleId", required = true) Long settleId,
	        @RequestParam(value = "itemId", required = true) Long itemId) {
		int result = itemManageService.cancelLinkSettle(settleId, itemId);
		if (1 == result) {
			return JsonRespWrapper.success("操作成功！", "/item/item/getSettles?itemId=" + itemId);
		}
		return JsonRespWrapper.successAlert("操作失败！");
	}

	/**
	 * 商品预览
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/view")
	public Object storebrowse(@RequestParam(required = false) Long id, Model model) throws IOException {

		ItemSale sale = this.itemManageService.findItemSaleById(id);
		String msg = this.itemManageService.pageBrowseStatic(sale);
		model.addAttribute("mag", msg);
		return "/item/item/Browse_view";

	}
}
