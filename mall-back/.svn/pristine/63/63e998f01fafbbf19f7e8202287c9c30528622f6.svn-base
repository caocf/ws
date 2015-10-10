package com.cplatform.mall.back.sys.web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.store.dao.ShopDao;
import com.cplatform.mall.back.store.dao.ShopUserDao;
import com.cplatform.mall.back.store.dao.StoreDao;
import com.cplatform.mall.back.store.entity.Shop;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.sys.dao.OpenCustomerDao;
import com.cplatform.mall.back.sys.dao.OpenRoleItemDao;
import com.cplatform.mall.back.sys.entity.OpenCustomer;
import com.cplatform.mall.back.sys.entity.OpenRoleItem;
import com.cplatform.mall.back.sys.service.OpenService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.RequestUrl;

/**
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author mudeng-ca@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("sys/open")
public class OpenController {

	static final Log logger = LogFactory.getLog(OpenController.class);

	@Autowired
	ShopUserDao shopUserDao;
	
	@Autowired
	ShopDao shopDao;
	
	@Autowired
	StoreDao storeDao;
	
	@Autowired
	private LogUtils  logUtils;

	@Autowired
	OpenService openService;
	
	@Autowired
	OpenCustomerDao openCustomerDao;
	
	@Autowired
	OpenRoleItemDao openRoleItemDao;
	
	@Autowired
	private AppConfig appConfig;
	
	@RequestMapping(value = "/roleitem-list")
	public String roleitemList(@ModelAttribute("customerService") OpenRoleItem openRoleItem,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpSession session, Model model) throws SQLException {
		Page<OpenRoleItem> openRoleItemList = this.openService.getPageListRoleItem(openRoleItem, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", openRoleItemList);
		return "sys/open/openroleitem-list";
	}
	
	@RequestMapping(value = "/customer-list")
	public String customerList(@ModelAttribute("customerService") OpenCustomer openCustomer,
	        @RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpSession session, Model model) throws SQLException {
		Page<OpenCustomer> openCustomerList = this.openService.getPageListCustomer(openCustomer, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("statusMap", openCustomer.statusMap);
		model.addAttribute("pageData", openCustomerList);
		return "sys/open/opencustomer-list";
	}
	
	@RequestMapping(value = "customer-view", method = RequestMethod.GET)
	public String viewRoleItem(@RequestParam(value = "appId", required = false, defaultValue = "") String appId,Model model, HttpSession session) throws SQLException {
		OpenCustomer openCustomer = openCustomerDao.findOne(Long.valueOf(appId));
		List<OpenRoleItem> listOpenRoleItem = openService.getOpenRoleItemList();
		List<Map<String,String>> listRoleItemMap = openService.getListRoleItemMap(Long.valueOf(appId));
		if("2".equals(openCustomer.getAppType())){
			if(openCustomer.getShopId()!=null){
				Shop shop = shopDao.findOne(openCustomer.getShopId());
				if(shop!=null){
					openCustomer.setShopName(shop.getName());
				}
			}
			if(openCustomer.getStoreId()!=null){
				Store store = storeDao.findOne(openCustomer.getStoreId());
				if(store!=null){
					openCustomer.setStoreName(store.getName());
				}
			}
		}
		model.addAttribute("listRoleItemMap", listRoleItemMap);
		model.addAttribute("listOpenRoleItem", listOpenRoleItem);
		model.addAttribute("openCustomer", openCustomer);
		return "sys/open/opencustomer-view";
	}
	
	@RequestMapping(value = "roleitem-view", method = RequestMethod.GET)
	public String viewCustomer(@RequestParam(value = "id", required = false, defaultValue = "") Long id,Model model, HttpSession session) {
		OpenRoleItem openRoleItem = openRoleItemDao.findOne(id);
		model.addAttribute("openRoleItem", openRoleItem);
		return "sys/open/openroleitem-view";
	}
	
	@RequestMapping(value = "customer-add", method = RequestMethod.GET)
	public String addcustomer(Model model, HttpSession session) throws SQLException {
		OpenCustomer openCustomer = new OpenCustomer();
		List<OpenRoleItem> listOpenRoleItem = openService.getOpenRoleItemList();
		model.addAttribute("shopClassMap", openCustomer.shopClassMap);
		model.addAttribute("appTypeMap", openCustomer.appTypeMap);
		model.addAttribute("openCustomer", openCustomer);
		model.addAttribute("listOpenRoleItem", listOpenRoleItem);
		return "sys/open/opencustomer-add";
	}
	
	@RequestMapping(value = "customer-add", method = RequestMethod.POST)
	@ResponseBody
	public Object addcustomer(@ModelAttribute("openCustomer") OpenCustomer openCustomer, HttpServletRequest request, HttpSession session) throws SQLException {
	try {
		openCustomer.setStatus("0");
		String[] roles = request.getParameterValues("role");
		String msg = openService.validIps(openCustomer);
		if(msg!=null){
			return JsonRespWrapper.successAlert(msg);
		}
		openService.getRanddomString(16, openCustomer);
		if("1".equals(openCustomer.getAppType())){
			openCustomer.setShopClass(null);
			openCustomer.setShopId(null);
			openCustomer.setStoreId(null);
		}
		OpenCustomer oc = openCustomerDao.save(openCustomer);
		openService.saveRole(openCustomer, roles);
		refreshOpenCache("第三方应用添加,appId="+oc.getAppId()+",");
		logUtils.logAdd("第三方应用管理", "第三方应用添加，第三方应用编号：" +openCustomer.getAppId());
	} catch (Exception e) {
		logUtils.logAdd("第三方应用管理", "第三方应用添加失败，第三方应用编号：" +openCustomer.getAppId());
	}
		return JsonRespWrapper.success("第三方应用添加成功", "/sys/open/customer-list");
	}
	
	@RequestMapping(value = "roleitem-add", method = RequestMethod.GET)
	public String addRoleItem(Model model, HttpSession session) {
		OpenRoleItem openRoleItem = new OpenRoleItem();
		model.addAttribute("openRoleItem", openRoleItem);
		return "sys/open/openroleitem-add";
	}
	
	@RequestMapping(value = "roleitem-add", method = RequestMethod.POST)
	@ResponseBody
	public Object addRoleItem(@ModelAttribute("openRoleItem") OpenRoleItem openRoleItem, HttpServletRequest request, HttpSession session) {
		OpenRoleItem opi = openRoleItemDao.save(openRoleItem);
		refreshOpenCache("权限项目添加,id="+opi.getId()+",");
		return JsonRespWrapper.success("权限项目添加成功", "/sys/open/roleitem-list");
	}
	

	@RequestMapping(value = "customer-edit", method = RequestMethod.GET)
	public String editCustomer( @RequestParam(value = "appId", required = false, defaultValue = "") String appId,Model model, HttpSession session) throws SQLException {
		OpenCustomer openCustomer = openCustomerDao.findOne(Long.valueOf(appId));
		if("2".equals(openCustomer.getAppType())){
			if(openCustomer.getShopId()!=null){
				Shop shop = shopDao.findOne(openCustomer.getShopId());
				if(shop!=null){
					openCustomer.setShopName(shop.getName());
				}
			}
			if(openCustomer.getStoreId()!=null){
				Store store = storeDao.findOne(openCustomer.getStoreId());
				if(store!=null){
					openCustomer.setStoreName(store.getName());
				}
			}
		}
		List<OpenRoleItem> listOpenRoleItem = openService.getOpenRoleItemList();
		List<Map<String,String>> listRoleItemMap = openService.getListRoleItemMap(Long.valueOf(appId));
		model.addAttribute("shopClassMap", openCustomer.shopClassMap);
		model.addAttribute("appTypeMap", openCustomer.appTypeMap);
		model.addAttribute("listRoleItemMap", listRoleItemMap);
		model.addAttribute("listOpenRoleItem", listOpenRoleItem);
		model.addAttribute("openCustomer", openCustomer);
		return "sys/open/opencustomer-edit";
	}
	

	@RequestMapping(value = "customer-edit", method = RequestMethod.POST)
	@ResponseBody
	public Object editCustomer(@ModelAttribute("openCustomer") OpenCustomer openCustomer, HttpServletRequest request, HttpSession session) throws SQLException {
	try {
		String[] roles = request.getParameterValues("role");
		String msg = openService.validIps(openCustomer);
		if(msg!=null){
			return JsonRespWrapper.successAlert(msg);
		}
		openService.saveRole(openCustomer, roles);
		openCustomer.setStatus("0");
		if("1".equals(openCustomer.getAppType())){
			openCustomer.setShopClass(null);
			openCustomer.setShopId(null);
			openCustomer.setStoreId(null);
		}
		openCustomerDao.save(openCustomer);
		refreshOpenCache("第三方应用修改,appId="+openCustomer.getAppId()+",");
		logUtils.logModify("第三方应用管理", "第三方应用修改，第三方应用编号：" +openCustomer.getAppId());
	} catch (Exception e) {
		logUtils.logModify("第三方应用管理", "第三方应用修改失败，第三方应用编号：" +openCustomer.getAppId());
		// TODO: handle exception
	}
		return JsonRespWrapper.success("第三方应用修改成功", "/sys/open/customer-list");
	}
	
	@RequestMapping(value = "roleitem-edit", method = RequestMethod.GET)
	public String editRoleItem( @RequestParam(value = "id", required = false, defaultValue = "") Long id,Model model, HttpSession session) {
		OpenRoleItem openRoleItem = openRoleItemDao.findOne(id);
		model.addAttribute("openRoleItem", openRoleItem);
		return "sys/open/openroleitem-edit";
	}
	

	@RequestMapping(value = "roleitem-edit", method = RequestMethod.POST)
	@ResponseBody
	public Object editRoleItem(@ModelAttribute("openRoleItem") OpenRoleItem openRoleItem, HttpServletRequest request, HttpSession session) {
		openRoleItemDao.save(openRoleItem);
		refreshOpenCache("权限项目修改,id="+openRoleItem.getId()+",");
		return JsonRespWrapper.success("权限项目修改成功", "/sys/open/roleitem-list");
	}
	
	@RequestMapping(value = "roleitem-del", method = RequestMethod.GET)
	@ResponseBody
	public Object delRoleItem( @RequestParam(value = "id", required = false, defaultValue = "") Long id,Model model, HttpSession session) {
		OpenRoleItem openRoleItem = openRoleItemDao.findOne(id);
		openRoleItemDao.delete(openRoleItem);
		refreshOpenCache("权限项目删除,id="+id+",");

		return JsonRespWrapper.success("权限项目删除成功", "/sys/open/roleitem-list");
	}
	
	@RequestMapping(value = "customer-del", method = RequestMethod.GET)
	@ResponseBody
	public Object delRoleItem( @RequestParam(value = "appId", required = false, defaultValue = "") String appId,Model model, HttpSession session) {
		OpenCustomer openCustomer = openCustomerDao.findOne(Long.valueOf(appId));
		openCustomerDao.delete(openCustomer);
		refreshOpenCache("第三方应用删除,appId="+appId+",");
		logUtils.logDelete("第三方应用管理", "第三方应用删除，第三方应用编号：" +openCustomer.getAppId());
		return JsonRespWrapper.success("第三方应用删除成功", "/sys/open/customer-list");
	}
	
	@RequestMapping(value = "customer-audit", method = RequestMethod.GET)
	public String auditCustomer( @RequestParam(value = "appId", required = false, defaultValue = "") String appId,Model model, HttpSession session) throws SQLException {
		OpenCustomer openCustomer = openCustomerDao.findOne(Long.valueOf(appId));
		model.addAttribute("openCustomer", openCustomer);
		return "sys/open/opencustomer-audit";
	}
	

	@RequestMapping(value = "customer-audit", method = RequestMethod.POST)
	@ResponseBody
	public Object auditCustomer(@ModelAttribute("openCustomer") OpenCustomer openCustomer, HttpServletRequest request, HttpSession session) throws SQLException {
		String audit = request.getParameter("audit");
		openCustomer = openCustomerDao.findOne(openCustomer.getAppId());
		openCustomer.setStatus(audit);
		openCustomerDao.save(openCustomer);
		refreshOpenCache("第三方应用审核,appId="+openCustomer.getAppId()+",");
		return JsonRespWrapper.success("第三方应用审核成功", "/sys/open/customer-list");
	}
	
	@RequestMapping(value = "customer-upline", method = RequestMethod.GET)
	@ResponseBody
	public Object upLineRoleItem( @RequestParam(value = "appId", required = false, defaultValue = "") String appId,Model model, HttpSession session) {
		OpenCustomer openCustomer = openCustomerDao.findOne(Long.valueOf(appId));
		openCustomer.setStatus("3");
		openCustomerDao.save(openCustomer);
		refreshOpenCache("第三方应用上线,appId="+openCustomer.getAppId()+",");
		return JsonRespWrapper.success("第三方应用上线成功", "/sys/open/customer-list");
	}
	
	@RequestMapping(value = "customer-downline", method = RequestMethod.GET)
	@ResponseBody
	public Object downLinedelRoleItem( @RequestParam(value = "appId", required = false, defaultValue = "") String appId,Model model, HttpSession session) {
		OpenCustomer openCustomer = openCustomerDao.findOne(Long.valueOf(appId));
		openCustomer.setStatus("4");
		openCustomerDao.save(openCustomer);
		logUtils.logOther("第三方应用管理", "第三方应用下线，第三方应用编号：" +openCustomer.getAppId());
		refreshOpenCache("第三方应用下线,appId="+openCustomer.getAppId()+",");
		return JsonRespWrapper.success("第三方应用下线成功", "/sys/open/customer-list");
	}
	/**
	 * 应用待配置商品列表
	 * 
	 * @param itemOrg
	 * @param page
	 * @param session
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/item-list")
	public String itemList(
			ItemSale itemSale,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "iseckillFlag", required = false) Long iseckillFlag,
			@RequestParam(value = "appId", required = false) Long appId,
			@RequestParam(value = "configStatus", required = false) int configStatus,
			HttpSession session, Model model) throws SQLException {
			//礼品卡功能页面跳转
			itemSale.setIseckill(iseckillFlag);
			model.addAttribute("iseckillFlag",iseckillFlag);
			
			if(itemSale.getIsValid()==null) {
				model.addAttribute("isValid", 1l);
			}
			
			if(itemSale.getStatus()==null) {
				model.addAttribute("status", 3);
			}
	
		itemSale.setGroupFlag(0L);// 普通商品
		Page<ItemSale> itemOrgPage = openService.listItemSale(itemSale,
				page, Page.DEFAULT_PAGESIZE, "",configStatus,appId);
		model.addAttribute("statusMap", ItemSale.statusMap);
		model.addAttribute("pageData", itemOrgPage);
		model.addAttribute("syncGyFlagMap", ItemSale.syncGyFlagMap);
		model.addAttribute("syncGyFlagMsg", Store.syncGyFlagMsg);
		model.addAttribute("isValidMap", ItemSale.isValidMap);
		model.addAttribute("iseckillMap", ItemSale.iseckillMap);
		model.addAttribute("appItemMap",openService.getAppItemMap(appId));
		model.addAttribute("appId", appId);
		model.addAttribute("configStatus", configStatus);
		return "/item/item/item-select-list";
	}
	
	@RequestMapping(value = "config-openapp-item/{appId}/{itemId}/{optype}", method = RequestMethod.GET)
	@ResponseBody
	public Object configOpenAppItem(@PathVariable Long appId,@PathVariable Long itemId,@PathVariable int optype
			, HttpServletRequest request, HttpSession session) {
		try {
			openService.updateAppItem(appId, itemId, optype);
			logUtils.logOther("第三方应用管理", "开放接口缓存，第三方应用编号：" +itemId);
			return JsonRespWrapper.success();
			
		}
		catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return JsonRespWrapper.error(ex.getMessage());
		}
	}
	
	private void refreshOpenCache(String logMessage) {
		try {
			RequestUrl.post(appConfig.getOpenCacheRefreshUrl() + "cache/customer/allrefresh");
			logger.info(logMessage+"开放接口更新，调用缓存刷新接口成功。");
		}
		catch (Exception e) {

			logger.error(logMessage+"开放接口缓存重置异常：" + e.getMessage());
			logUtils.logOther(logMessage+"开放接口缓存重置异常", "开放接口更新，调用缓存刷新接口失败");
		}
	}
}
