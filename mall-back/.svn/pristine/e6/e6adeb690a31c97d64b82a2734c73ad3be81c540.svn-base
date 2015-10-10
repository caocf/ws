package com.cplatform.mall.back.item.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.entity.AuditStep;
import com.cplatform.mall.back.item.dao.ItemManageDao;
import com.cplatform.mall.back.item.dao.ItemPriceTypeDao;
import com.cplatform.mall.back.item.entity.ItemPrice;
import com.cplatform.mall.back.item.entity.ItemPriceType;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.item.service.ItemSaleService;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.ShopService;
import com.cplatform.mall.back.sys.entity.SysFee;
import com.cplatform.mall.back.utils.JsonRespWrapper;

/**
 * 商品发布 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-3 上午8:49:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * 
 * @deprecated 这个类不在维护，请使用ItemManageController
 */
@Deprecated
@Controller
@RequestMapping(value = "item/saleitem")
public class ItemSaleController {

	private static final Logger log = Logger.getLogger(ItemManageController.class);

	@Autowired
	private ItemSaleService itemSaleService;

	@Autowired
	private ItemManageDao orgDao;

	@Autowired
	private ShopService shopService;

	@Autowired
	private ItemPriceTypeDao priceTypeDao;

	/**
	 * 商品发布管理List
	 * 
	 * @param itemSale
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String itemSaleList(ItemSale itemSale, @RequestParam(value = "page", defaultValue = "1", required = false) int page, HttpSession session,
	        Model model) throws SQLException {
		// itemSale.setStatus(5L);
		Page<ItemSale> itemSalePage = itemSaleService.listItemSale(itemSale, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", itemSalePage);
		model.addAttribute("statusMap", ItemSale.statusMap);
		model.addAttribute("syncGyFlagMap", ItemSale.syncGyFlagMap);
		model.addAttribute("syncGyFlagMsg", Store.syncGyFlagMsg);
		return "/item/saleitem/saleitem-list";
	}

	/**
	 * 查询商品列表页
	 * 
	 * @param itemSale
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/salelist")
	public String saleList(ItemSale sale, @RequestParam(value = "page", defaultValue = "1", required = false) int page, HttpSession session,
	        Model model) throws SQLException {

		Page<ItemSale> salePage = itemSaleService.listSale(sale, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", salePage);
		return "/item/saleitem/itemorg-list";
	}

	/**
	 * 进入产品发布页面
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/addsaleitem/{id}", method = RequestMethod.GET)
	public String toAddSaleItem(@PathVariable Long id, Model model) throws IOException, Exception {
		ItemSale itemSale = this.itemSaleService.findItemSaleById(id);// new
		// ItemSale();
		// itemSale.setOrgId(orgId);
		model.addAttribute("itemSale", itemSale);
		// ItemOrg itemOrg = orgDao.findOne(orgId);
		// model.addAttribute("itemOrg", itemOrg);
		// 跳转到列表页面
		return "/item/saleitem/saleitem-add";
		 
	}

	/**
	 * 完成产品发布
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/addsaleitem/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object addSaleItem(@RequestParam(required = false) String unlimitedStockNum, @PathVariable Long id,
	        @ModelAttribute("itemSale") ItemSale itemSale, Model model, HttpServletRequest request, BindingResult result) throws IOException,
	        Exception {
		try {
			if (null != unlimitedStockNum) {
				itemSale.setStockNum(ItemSale.INIT_STOCK_NUM);
			}
			ItemSale vo = this.itemSaleService.findItemSaleById(id);
			vo.setSendCodeMode(itemSale.getSendCodeMode());
			vo.setVerifyCodeType(itemSale.getVerifyCodeType());
			vo.setSendCodeChannel(itemSale.getSendCodeChannel());
			vo.setSendCodeSrc(itemSale.getSendCodeSrc());
			vo.setVerifyStartTime(itemSale.getVerifyStartTime());
			vo.setVerifyStopTime(itemSale.getVerifyStopTime());
			vo.setVerifyShopIds(itemSale.getVerifyShopIds());
			vo.setPostFlag(itemSale.getPostFlag());
			vo.setPostArea(itemSale.getPostArea());
			vo.setPostAreaCode(itemSale.getPostAreaCode());
			vo.setSaleStartTime(itemSale.getSaleStartTime());
			vo.setSaleStopTime(itemSale.getSaleStopTime());
			vo.setSaleAreaName(itemSale.getSaleAreaName());
			vo.setSaleShopName(itemSale.getSaleShopName());
			vo.setSaleShopIds(itemSale.getSaleShopIds());
			vo.setBuyLimit(itemSale.getBuyLimit());
			vo.setUserLimitCode(itemSale.getUserLimitCode());
			vo.setAreaLimitCode(itemSale.getAreaLimitCode());
			vo.setAreaLimitName(itemSale.getAreaLimitName());
			vo.setUserPerBuyNum(itemSale.getUserPerBuyNum());
			vo.setStockNum(itemSale.getStockNum());
			vo.setMarketPrice(itemSale.getMarketPrice());
			vo.setPriceTypeCode(itemSale.getPriceTypeCode());
			vo.setSaleAreaCode(itemSale.getSaleAreaCode());
			vo.setPrice(itemSale.getPrice());

			vo.setShopPrice(itemSale.getShopPrice());
			vo.setSettlePrice(itemSale.getSettlePrice());
			vo.setVerifyDay(itemSale.getVerifyDay());

			vo.setLogisticsFee(itemSale.getLogisticsFee());
			vo.setLogisticsFeeType(itemSale.getLogisticsFeeType());
			itemSaleService.saveItemSale(vo);

			// 跳转到列表页面
			return JsonRespWrapper.success("操作成功", "/item/saleitem/list");
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
		// 跳转到列表页面
	}

	/**
	 * 做逻辑删除操作
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable Long id, Model model) {
		try {
			itemSaleService.deleteItemSale(id);

			return JsonRespWrapper.success("操作成功", "/item/saleitem/list");
		}
		catch (Exception ex) {
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 进入发布产品修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) throws SQLException {
		ItemSale itemSale = itemSaleService.getItemSale(id);
		model.addAttribute("itemSale", itemSale);
		if (itemSale.getStockNum().equals(ItemSale.INIT_STOCK_NUM)) {
			model.addAttribute("storeNum", itemSale.getStockNum());
			itemSale.setStockNum(0l);
		}
		model.addAttribute("initStoreNum", ItemSale.INIT_STOCK_NUM);
		List<ItemPrice> priceList = itemSaleService.getPriceList(id);
		model.addAttribute("priceList", priceList);
		model.addAttribute("map", ItemPriceType.priceTypeMap);
		return "/item/saleitem/saleitem-edit";
	}

	/**
	 * 完成产品发布
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object doEdit(@RequestParam(required = false) String unlimitedStockNum, @PathVariable Long id,
	        @ModelAttribute("itemSale") ItemSale itemSale, Model model, HttpServletRequest request, BindingResult result) throws IOException,
	        Exception {
		try {
			if (null != unlimitedStockNum) {
				itemSale.setStockNum(ItemSale.INIT_STOCK_NUM);
			}
			ItemSale vo = this.itemSaleService.findItemSaleById(id);
			vo.setSendCodeMode(itemSale.getSendCodeMode());
			vo.setVerifyCodeType(itemSale.getVerifyCodeType());
			vo.setSendCodeChannel(itemSale.getSendCodeChannel());
			vo.setSendCodeSrc(itemSale.getSendCodeSrc());
			vo.setVerifyStartTime(itemSale.getVerifyStartTime());
			vo.setVerifyStopTime(itemSale.getVerifyStopTime());
			vo.setVerifyShopIds(itemSale.getVerifyShopIds());
			vo.setPostFlag(itemSale.getPostFlag());
			vo.setPostArea(itemSale.getPostArea());
			vo.setPostAreaCode(itemSale.getPostAreaCode());
			vo.setSaleStartTime(itemSale.getSaleStartTime());
			vo.setSaleStopTime(itemSale.getSaleStopTime());
			vo.setSaleAreaName(itemSale.getSaleAreaName());
			vo.setSaleShopName(itemSale.getSaleShopName());
			vo.setSaleShopIds(itemSale.getSaleShopIds());
			vo.setBuyLimit(itemSale.getBuyLimit());
			vo.setUserLimitCode(itemSale.getUserLimitCode());
			vo.setAreaLimitCode(itemSale.getAreaLimitCode());
			vo.setAreaLimitName(itemSale.getAreaLimitName());
			vo.setUserPerBuyNum(itemSale.getUserPerBuyNum());
			vo.setStockNum(itemSale.getStockNum());
			vo.setMarketPrice(itemSale.getMarketPrice());
			vo.setPriceTypeCode(itemSale.getPriceTypeCode());
			vo.setSaleAreaCode(itemSale.getSaleAreaCode());
			vo.setPrice(itemSale.getPrice());

			vo.setShopPrice(itemSale.getShopPrice());
			vo.setSettlePrice(itemSale.getSettlePrice());
			vo.setVerifyDay(itemSale.getVerifyDay());

			vo.setLogisticsFee(itemSale.getLogisticsFee());
			vo.setLogisticsFeeType(itemSale.getLogisticsFeeType());
			itemSaleService.saveItemSale(vo);

			// 跳转到列表页面
			return JsonRespWrapper.success("操作成功", "/item/saleitem/list");
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
		// 跳转到列表页面
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
	@RequestMapping(value = "/goodsOnLine/{optype}/{id}")
	@ResponseBody
	public Object goodsOnLine(@PathVariable Long id, @PathVariable String optype, Model model) throws IOException {
		ItemSale sale = this.itemSaleService.findItemSaleById(id);

		if ("online".equals(optype)) {
			sale.setIsValid(1L);
		} else {
			sale.setIsValid(0L);
		}
		this.itemSaleService.save(sale);
		// this.itemSaleService.pageStatic(sale);

		return JsonRespWrapper.success("操作成功", "/item/saleitem/list");
	}

	/**
	 * 审核操作
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/audit/{optype}/{id}")
	@ResponseBody
	public Object audit(@PathVariable Long id, @PathVariable String optype, @RequestParam(required = false) String buyLimit,
	        @RequestParam(required = false) String userLimitCode, @RequestParam(required = false) String areaLimitCode,
	        @RequestParam(required = false) String userPerBuyNum, @RequestParam(required = false) Long feeType,
	        @ModelAttribute("step") AuditStep step, Model model, HttpServletRequest request) {
		try {
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			step.setAuditUserId(sessionUser.getId());
			itemSaleService.auditItemSale(id, feeType, optype, step, buyLimit, userLimitCode, areaLimitCode, userPerBuyNum);
			if ("sendAudit".equals(optype)) {

				return JsonRespWrapper.success("操作成功", "/item/saleitem/list");
			} else {

				return JsonRespWrapper.success("操作成功", "/item/saleitem/list");
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			// 未知的错误消息，一般是exception catch后通知用户
			return JsonRespWrapper.error(ex.getMessage());
		}
	}

	/**
	 * 进入审核列表页面
	 * 
	 * @param contentCode
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/auditList")
	public String auditList(ItemSale itemSale, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		if (null == itemSale.getStatus()) {
			itemSale.setStatus(6L);
		}
		Page<ItemSale> itemSalePage = itemSaleService.listItemSale(itemSale, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", itemSalePage);

		return "/item/saleitem/saleitem-audit-list";
	}

	/**
	 * 进入审核填写意见页面
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
		ItemSale itemSale = itemSaleService.getItemSale(id, "0");
		model.addAttribute("itemSale", itemSale);
		List<SysFee> feeList = this.itemSaleService.getAllSysFeeList();
		model.addAttribute("feeList", feeList);
		// 跳转到列表页面
		return "/item/saleitem/saleitem_auditing";
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
		List<ItemPriceType> priceTypeList = new ArrayList<ItemPriceType>();
		priceTypeList = priceTypeDao.findByAreaCode(areaCode);

		if (null == priceTypeList || priceTypeList.size() == 0) {
			Map<String, String> priceTypeMap = ItemPriceType.priceTypeMap;
			for (Map.Entry<String, String> entry : priceTypeMap.entrySet()) {
				ItemPriceType priceType = new ItemPriceType();
				priceType.setPriceTypeCode(entry.getKey());
				priceType.setPriceType(entry.getValue());
				priceTypeList.add(priceType);
			}
		}
		return priceTypeList;
	}

	/**
	 * 进入发布产品修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SQLException {
		ItemSale itemSale = itemSaleService.getItemSale(id);
		model.addAttribute("itemSale", itemSale);
		if (itemSale.getStockNum().equals(ItemSale.INIT_STOCK_NUM)) {
			model.addAttribute("storeNum", itemSale.getStockNum());
			itemSale.setStockNum(0l);
		}
		model.addAttribute("initStoreNum", ItemSale.INIT_STOCK_NUM);
		List<ItemPrice> priceList = itemSaleService.getPriceList(id);
		model.addAttribute("priceList", priceList);
		model.addAttribute("map", ItemPriceType.priceTypeMap);
		return "/item/saleitem/saleitem-view";
	}

}
