package com.cplatform.b2c.web.order;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.cart.CartItem;
import com.cplatform.b2c.cart.GroupKey;
import com.cplatform.b2c.dto.GiftCardRequest;
import com.cplatform.b2c.dto.GiftCardResponse;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.dto.LogisticsFeeDTO;
import com.cplatform.b2c.dto.LogisticsFeeResp;
import com.cplatform.b2c.model.MemberInvoice;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TMemberAddress;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.service.CartService;
import com.cplatform.b2c.service.ItemSaleService;
import com.cplatform.b2c.service.MemberAddressService;
import com.cplatform.b2c.service.MemberInvoiceService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.service.PerformService;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.service.TItemParamService;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.b2c.web.validator.AddressValidator;
import com.google.common.collect.Lists;

/**
 * 下单确认页面. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-16 下午3:27
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Controller
@RequestMapping("/order")
public class ConfirmOrderController {

	@Autowired
	MemberInvoiceService memberInvoiceService;

	@Autowired
	MemberAddressService memberAddressService;

	@Autowired
	CartService cartService;

	@Autowired
	OrderService orderService;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private PerformService performService;

	@Autowired
	private TItemParamService itemParamService;

	@Autowired
	private ItemSaleService itemSaleService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private AddressValidator addressValidator;

	static final Log logger = LogFactory.getLog(ConfirmOrderController.class);

	/**
	 * 请求显示下单确认页面数据---商品详情页面--->立即购买
	 * 
	 * @param itemId
	 *            商品编号
	 * @param quantity
	 *            购买数量
	 * @param type
	 * @param businessId
	 *            商户编号
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("buynow-data")
	@ResponseBody
	public String buyNowData(@CookieValue(value = "confirm_itemId", required = false) String itemId,
	        @CookieValue(value = "confirm_quantity", required = false, defaultValue = "1") int quantity,
	        @CookieValue(value = "confirm_type", required = false) String type,
	        @CookieValue(value = "confirm_businessId", required = false) String businessId, HttpServletResponse response, Model model)
	        throws Exception {

		JsonRespWrapper result = JsonRespWrapper.json();
		if (quantity > 200) {
			return result.toString();
		}

		SessionUser sessionUser = SessionUser.getSessionUser(response);
		if (sessionUser == null) {
			return result.toString();
		}

		// 获取商品信息
		// JSONObject itemInfo = orderService.getItemFromInterface(itemId);
		ItemSaleDataDTO itemSaleDataDTO = thirdInterDao.getItemById(itemId);
		if (itemSaleDataDTO == null || itemSaleDataDTO.getItem() == null) {
			return result.toString();
		}

		GroupKey groupKey = new GroupKey(itemSaleDataDTO);

		// 判断商品的类型、用户拥有权益、商品支持的会员权益，从而重新设置商品的商城价
		String checkPrice = orderService.fillPrice(itemSaleDataDTO, type, sessionUser, businessId);
		if (Constants.AUCTION_RETURN_NULL.equals(checkPrice)) {
			return result.json("msg", Constants.AUCTION_RETURN_NULL_MESSAGE).toString();
		}

		// 计算物流价格
		if (itemSaleDataDTO.getItem().getPostFlag() == 1) {
			itemSaleService.setGoodsLogisticsFee(sessionUser, itemSaleDataDTO, quantity);
		}

		CartItem cartItem = new CartItem(groupKey, itemId, quantity, itemSaleDataDTO);
		result.json("cartItems", Lists.newArrayList(cartItem));

		int groupFlag = Integer.valueOf(itemSaleDataDTO.getItem().getGroupFlag());
		if (groupFlag == 1) {
			JSONObject groupItems = orderService.getGroupItemsFromInterface(itemId);
			result.json("groupItems", groupItems);
		}

		// 商户的发票内容数据
		List<Map<String, String>> invoiceContent = orderService.findShopInvoices(groupKey.getShopId());
		result.json("invoiceContent", invoiceContent);

		// 商户名称
		String shopName = itemSaleDataDTO.getItem().getStoreName();
		result.json("shopName", shopName);

		boolean isInSaleArea = orderService.isInSaleArea(itemSaleDataDTO, response);
		result.json("isInSaleArea", isInSaleArea);

		result.json("confirm_type", type);

		// 判断是否是支持会员商品
		boolean isVipGoods = orderService.isVipGoods(itemSaleDataDTO);
		result.json("isVipGoods", isVipGoods);

		String source = "0";
		// 商品来源
		String itemSource = itemSaleDataDTO.getItem().getSource();
		if (Constants.ITEM_SOURCE_YTICKET_YL.equals(itemSource) || Constants.ITEM_SOURCE_YTICKET_NYL.equals(itemSource)) {
			source = "3";
			// 查询演出票信息
			Map<String, String> performTicketInfo = performService.getPerformTicketInfo(itemId);
			result.json("performTicketInfo", performTicketInfo);
		}
		result.json("source", source);

		return result.toString();
	}

	/**
	 * 商品详情页面点击立即购买跳转
	 * 
	 * @param itemId
	 *            商品编号
	 * @param props
	 * @param quantity
	 *            购买数量
	 * @param type
	 * @param businessId
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("buynow")
	public String buyNow(@CookieValue(value = "confirm_itemId", required = false) String itemId,
	        @CookieValue(value = "confirm_props", required = false) String props,
	        @CookieValue(value = "confirm_quantity", required = false, defaultValue = "1") int quantity,
	        @CookieValue(value = "confirm_type", required = false) String type,
	        @CookieValue(value = "confirm_businessId", required = false) String businessId, Model model) throws SQLException {
		model.addAttribute("buynow", true);

		// 商品信息
		ItemSaleDataDTO itemSaleDataDTO = thirdInterDao.getItemById(itemId);
		// 判断是否河南商品
		if (itemSaleDataDTO != null && itemSaleDataDTO.getItem() != null && StringUtils.isNotBlank(itemSaleDataDTO.getItem().getSource())) {
			if (Constants.ITEM_SOURCE_HN_INTEGRAL.equals(itemSaleDataDTO.getItem().getSource())) {
				// 为河南商品，即返回河南页面
				return "cart/confirm-hn-order";
			}

			return "cart/confirm-order";
		}
		return "cart/confirm-order";
	}

	/**
	 * 请求显示下单确认页面数据---购物车页面--->下单
	 * 
	 * @param cartuuid
	 * @param items
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("confirm-data")
	@ResponseBody
	public String confirmData(@CookieValue(value = CartService.CART_UUID, required = false) String cartuuid,
	        @CookieValue(value = "confirm_items", required = false) String items, HttpServletResponse response) throws Exception {

		JsonRespWrapper result = JsonRespWrapper.json();

		SessionUser sessionUser = SessionUser.getSessionUser(response);
		if (sessionUser == null) {
			return result.toString();
		}

		String[] itemKeys;
		if (items == null) {
			itemKeys = null;
		} else {
			itemKeys = items.split(",");
		}
		List<CartItem> cartItems = cartService.findConfirmItems(cartuuid, itemKeys, response);
		result.json("cartItems", cartItems);

		if (cartItems.size() > 0) {
			boolean isInSaleArea = true;
			boolean isVipGoods = false;
			for (CartItem item : cartItems) {
				ItemSaleDataDTO.Item sale = item.getItemInfo().getItem();
				isInSaleArea = isInSaleArea && orderService.isInSaleArea(item.getItemInfo(), response);
				sale.setShopPrice(new BigDecimal(orderService.getSuitablePrice(item.getItemInfo(), sessionUser)));
				if (!isVipGoods) {
					isVipGoods = isVipGoods || orderService.isVipGoods(item.getItemInfo());
				}

				// 设置物流价格
				if (sale.getPostFlag() == 1) {
					itemSaleService.setGoodsLogisticsFee(sessionUser, item.getItemInfo(), item.getQuantity());
				}

				String shopId = cartItems.get(0).getGroupKey().getShopId();
				// 商户的发票内容数据
				List<Map<String, String>> invoiceContent = orderService.findShopInvoices(shopId);
				result.json("invoiceContent", invoiceContent);

				String shopName = cartItems.get(0).getItemInfo().getItem().getStoreName();
				result.json("shopName", shopName);
				result.json("isInSaleArea", isInSaleArea);
				result.json("isVipGoods", isVipGoods);
			}
		}

		return result.toString();
	}

	/**
	 * 购物车请求下单，跳转页面
	 * 
	 * @param cartuuid
	 * @param items
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("confirm")
	public String confirmOrder(@CookieValue(value = CartService.CART_UUID, required = false) String cartuuid,
	        @CookieValue(value = "confirm_items", required = false) String items, Model model) throws SQLException {
		model.addAttribute("buynow", false);
		return "cart/confirm-order";
	}

	/**
	 * 下单时使用礼金券
	 * 
	 * @param totalPrice
	 * @param itemIds
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "giftCard")
	@ResponseBody
	public GiftCardResponse giftCardResponse(@RequestParam("totalCost") BigDecimal totalPrice, @RequestParam("itemIds") String itemIds,
	        HttpServletResponse response) throws Exception {
		SessionUser user = SessionUser.getSessionUser(response);
		// 判断用户下的手机号；因为商城抵用券等都是跟用户手机号绑定的。即当用户没绑定手机号时，改用户下没有抵用券、礼金券
		if (null == user || StringUtils.isBlank(user.getTerminalId())) {
			return new GiftCardResponse();
		}
		String[] item = StringUtils.split(itemIds, ",");
		List<Long> ids = Lists.newArrayList();
		for (int i = 0; i < item.length; i++) {
			ids.add(Long.valueOf(item[i]));
		}
		GiftCardRequest cardRequest = new GiftCardRequest();
		cardRequest.setItems(ids);
		cardRequest.setMobile(user.getTerminalId());
		cardRequest.setTotalCost(totalPrice);
		return orderService.getGiftCardResponse(cardRequest);
	}

	/**
	 * 显示发票
	 * 
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("invoices")
	@ResponseBody
	public Object invoices(HttpServletResponse response) throws SQLException {
		List<MemberInvoice> result = memberInvoiceService.findInvoiceByUser(SessionUser.getSessionUser(response).getId());
		return JsonRespWrapper.json("data", result, true);
	}

	/**
	 * 删除发票
	 * 
	 * @param id
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("invoice-del")
	@ResponseBody
	public Object invoiceDel(String id, HttpServletResponse response) throws SQLException {
		memberInvoiceService.delInvoice(id, SessionUser.getSessionUser(response).getId());
		return JsonRespWrapper.success("0");
	}

	/**
	 * 增加发票
	 * 
	 * @param invoiceType
	 * @param invoiceTitleType
	 * @param invoiceTitle
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("invoice-add")
	@ResponseBody
	public Object invoiceAdd(String invoiceType, String invoiceTitleType, String invoiceTitle, HttpServletResponse response) throws SQLException {

		MemberInvoice minvoice = new MemberInvoice();
		int count = memberInvoiceService.findInvoiceCount(SessionUser.getSessionUser(response).getId());
		if (count >= 5) {
			return JsonRespWrapper.failure("发票信息不能超过5条。");
		}
		minvoice.setInvoiceType(invoiceType);
		minvoice.setInvoiceTitleType(invoiceTitleType);
		minvoice.setInvoiceTitle(invoiceTitle);
		minvoice = memberInvoiceService.addInvoice(minvoice, response);
		List<MemberInvoice> result = memberInvoiceService.findInvoiceByUser(SessionUser.getSessionUser(response).getId());
		return JsonRespWrapper.json("data", result, true);
	}

	/**
	 * 显示用户收货地址
	 * 
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("addresses")
	@ResponseBody
	public Object addresses(HttpServletResponse response) throws SQLException {
		List<TMemberAddress> result = memberAddressService.findAddressByUser(SessionUser.getSessionUser(response).getId());
		return JsonRespWrapper.json("data", result, true);
	}

	/**
	 * 用户默认收货地址
	 * 
	 * @param id
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("address-active")
	@ResponseBody
	public Object addressActive(String id, HttpServletResponse response) throws SQLException {
		memberAddressService.setDefault(id, SessionUser.getSessionUser(response).getId());
		return JsonRespWrapper.success("0");
	}

	/**
	 * 删除收货地址
	 * 
	 * @param id
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("address-del")
	@ResponseBody
	public Object addressDel(String id, HttpServletResponse response) throws SQLException {
		memberAddressService.delAddress(id, SessionUser.getSessionUser(response).getId());
		return JsonRespWrapper.success("0");
	}

	/**
	 * 修改用户收货地址
	 * 
	 * @param aid
	 * @param name
	 * @param region
	 * @param address
	 * @param zip
	 * @param mobile
	 * @param phone
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("address-mod")
	@ResponseBody
	public Object addressMod(TMemberAddress tMemberAddress, HttpServletResponse response, BindingResult bResult) throws SQLException {

		TMemberAddress maddress = new TMemberAddress();
		// 验证收货地址信息
		addressValidator.validate(maddress, bResult);
		if (bResult.hasErrors()) {
			return JsonRespWrapper.failure(bResult.getFieldErrors().get(0).getDefaultMessage());
		}
		// 判断是修改，还是增加用户收货地址
		if (null != tMemberAddress.getId() && !"".equals(tMemberAddress.getId())) {
			maddress.setId(Integer.valueOf(tMemberAddress.getId()));
		} else {
			int count = memberAddressService.findAddressCount(SessionUser.getSessionUser(response).getId());
			if (count >= 10) {
				return JsonRespWrapper.failure("个人地址信息不能超过10条。");
			}
		}
		maddress.setName(tMemberAddress.getName());
		maddress.setRegion(tMemberAddress.getRegion());
		maddress.setAddress(tMemberAddress.getAddress());
		maddress.setZipcode(tMemberAddress.getZipcode());
		maddress.setMobile(tMemberAddress.getMobile());
		maddress.setPhone(tMemberAddress.getPhone());
		maddress = memberAddressService.addOrUpdateAddress(maddress, response);
		memberAddressService.setDefault(maddress.getId().toString(), SessionUser.getSessionUser(response).getId());
		List<TMemberAddress> result = memberAddressService.findAddressByUser(SessionUser.getSessionUser(response).getId());
		return JsonRespWrapper.json("data", result, true);
	}

	@RequestMapping("/logistics_fee")
	@ResponseBody
	public Object getGoodsLogisticsFee(int quantity, Long itemId, String addressId, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		SessionUser userinfo = SessionUser.getSessionUser(response);
		ItemSaleDataDTO itemSaleDataDTO = thirdInterDao.getItemById(itemId.toString());
		/** 获取用户收货地址 */
		TMemberAddress tMemberAddress = null;
		if (StringUtils.isNotBlank(addressId)) {
			logger.info("用户页面勾选收货地址---用户编号：" + userinfo.getId() + "，收货地址：" + addressId);
			tMemberAddress = memberAddressService.findAddress(addressId, userinfo.getId());
		} else {
			// 获取用户默认收货地址
			logger.info("用户页面勾选收货地址为空， 获取默认地址---用户编号：" + userinfo.getId());
			tMemberAddress = memberAddressService.findTMemberAddressByUidAndDefult(userinfo.getId());
		}
		if ((null == tMemberAddress || tMemberAddress.getId() == null || "".equals(tMemberAddress.getId()))
		        && itemSaleDataDTO.getItem().getPostFlag() == 1) {
			// 收货地址为空
			logger.info("切换收货地址，用户收货地址为空");
			return JsonRespWrapper.failure("尊敬的用户，您的收货地址信息有误，建议您重新下单。");
		}
		// 默认收货地址所属省份编号
		String provinceCode = shopService.getProvinceCodeByRegionCode(tMemberAddress.getRegion(), null);
		LogisticsFeeDTO logisticsFeeDTO = itemSaleService.getLogisticsFeeDTO(provinceCode, quantity, itemSaleDataDTO.getItem().getId());
		LogisticsFeeResp logisticsFeeResp = itemSaleService.getLogisticsFee(logisticsFeeDTO);
		if (null != logisticsFeeResp && logisticsFeeResp.getGoods().size() > 0) {
			return CommonUtils.toYuan(logisticsFeeResp.getGoods().get(0).getLogisticsfee());
		}
		return "0";
	}
}
