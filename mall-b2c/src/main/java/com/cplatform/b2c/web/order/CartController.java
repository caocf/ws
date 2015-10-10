package com.cplatform.b2c.web.order;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.cart.CartBox;
import com.cplatform.b2c.cart.CartGroup;
import com.cplatform.b2c.cart.CartItem;
import com.cplatform.b2c.cart.CartOpException;
import com.cplatform.b2c.cart.GroupKey;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.model.Member;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.service.CartService;
import com.cplatform.b2c.service.ItemSaleService;
import com.cplatform.b2c.service.MemberCenterService;
import com.cplatform.b2c.service.MemberService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.service.TBonusTerminalService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.b2c.util.TimeUtil;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-16 下午3:24
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	static final Log logger = LogFactory.getLog(CartController.class);

	@Autowired
	AppConfig appConfig;

	@Autowired
	CartService cartService;

	@Autowired
	OrderService orderService;

	@Autowired
	MemberService memberService;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private MemberCenterService memberCenterService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private TBonusTerminalService tBonusTerminalService;

	@Autowired
	private ItemSaleService itemSaleService;

	@RequestMapping("quick-start")
	public String itemSample() {
		return "cart/quick-start";
	}

	@RequestMapping("login")
	@ResponseBody
	public Object login(String username, HttpSession session) throws SQLException {
		Member member = memberService.findByUserName(username);
		SessionUser sessionUser = new SessionUser();
		sessionUser.setId(member.getId());
		sessionUser.setEmail(member.getEmail());
		sessionUser.setTerminalId(member.getTerminalId());
		sessionUser.setNickName(member.getUserName());
		sessionUser.setRedMember(1);
		session.setAttribute(SessionUser.SESSION_USER_KEY, sessionUser);
		return JsonRespWrapper.success("0");
	}

	@RequestMapping("logout")
	@ResponseBody
	public Object logout(HttpSession session) throws SQLException {
		session.invalidate();
		return JsonRespWrapper.success("0");
	}

	/**
	 * 显示购物车页面
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping("show")
	public String cart(HttpServletResponse response) {
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		if (sessionUser != null) {
			if (sessionUser != null && StringUtils.isNotBlank(sessionUser.getAreaCode())) {
				String provRegionCode = shopService.getProvinceCodeByRegionCode(sessionUser.getAreaCode(), null);
				if (StringUtils.isNotBlank(provRegionCode) && Constants.PROV_HENAN_REGION_CODE.equals(provRegionCode)) {
					return "cart/hn-show";
				}
			}
		}
		return "cart/show";
	}

	/**
	 * 加载购物车页面数据
	 * 
	 * @param cartuuid
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("show-data")
	@ResponseBody
	public Object showData(@CookieValue(value = CartService.CART_UUID, required = false) String cartuuid, HttpServletResponse response)
	        throws SQLException {
		CartBox cartBox = cartService.getCartBox(cartuuid, response);
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		if (cartBox != null && sessionUser != null) {
			List<CartGroup> cartGroups = cartBox.getCartGroups();
			for (CartGroup cartGroup : cartGroups) {
				List<CartItem> cartItems = cartGroup.getCartItems();
				for (CartItem item : cartItems) {
					ItemSaleDataDTO.Item itemSaleDataDTO = item.getItemInfo().getItem();
					itemSaleDataDTO.setShopPrice(new BigDecimal(orderService.getSuitablePrice(item.getItemInfo(), sessionUser)));
				}
			}
		}
		return cartBox == null ? "{}" : cartBox.toString();
	}

	@RequestMapping("buyitem")
	@ResponseBody
	public Object buyItem(String itemId, int quantity, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		SessionUser userInfo = SessionUser.getSessionUser(response);

		// 获取商品信息
		ItemSaleDataDTO item = thirdInterDao.getItemById(itemId);

		// 判断当前商品是否到销售时间
		boolean checkStartTime = itemSaleService.checkStartTime(item.getItem());
		if (!checkStartTime) {
			return JsonRespWrapper.failure("尊敬的用户您好，商品尚未开始销售，请您继续关注");
		}

		// 判断是否是限时购买商品
		if (itemSaleService.checkIsPromptGoods(item.getItem())) {
			// 检查商品对于会员级别的限制
			String limitVipBuy = itemSaleService.checkGoodsBuyLimitVip(item.getItem(), userInfo);
			if (StringUtils.isNotBlank(limitVipBuy)) {
				if (Constants.RED_USER_LEVELS.equals(limitVipBuy)) {
					return JsonRespWrapper.failure("对不起，该商品仅限红钻会员购买，如需购买，请开通红钻会员，谢谢！");
				} else if (Constants.UNION_USER_LEVELS.equals(limitVipBuy)) {
					return JsonRespWrapper.failure("对不起，该商品仅限商盟会员购买，如需购买，请开通商盟会员，谢谢！");
				}
			}

			// 判断限购数量
			logger.info("验证用户购限制买数量");
			if (!itemSaleService.checkLimitBuyNum(quantity, item.getItem().getUserPerBuyNum())) {
				return JsonRespWrapper.failure("用户您好，购买数量超过限制数量");
			}

			logger.info("验证库存制买数量");
			if (!itemSaleService.checkStock(quantity, item.getItem().getStocknum())) {
				return JsonRespWrapper.failure("用户您好，当前库存不足，请继续关注。可能会有惊喜哦!");
			}

			// 判断当前活动时间是否开始
			if (TimeUtil.compareTime(TimeUtil.now(), item.getItem().getStopTime()) > 0) {
				return JsonRespWrapper.failure("特价活动结束，商品已下架");
			}

		}

		// 判断是否发码商品
		boolean isVipGoods = orderService.isVipGoods(item);
		if (isVipGoods) {
			// 判断是否需要绑定手机号
			if (isAbleToBindMobile(request, response)) {
				return JsonRespWrapper.failure("未绑定移动手机号码，请前往“个人中心>基本资料”进行绑定");
			}
		}

		// 1、判断是否劳保商品
		boolean isLabourGoodsFlag = tBonusTerminalService.isLabourGoods(item);

		// 2、判断用户是否有购买劳保商品权限
		if (isLabourGoodsFlag) {
			boolean isBonusTerminal = tBonusTerminalService.isBonusTerminal(userInfo);
			if (!isBonusTerminal) {
				// 没权限购买劳保商品给予友好提示
				return tBonusTerminalService.unBonusTerminal();
			}
		}

		String failureMsg = checkCommonLimit(item, quantity, response);
		if (StringUtils.isNotBlank(failureMsg)) {
			return JsonRespWrapper.failure(failureMsg);
		}

		return JsonRespWrapper.success();
	}

	@RequestMapping("additem")
	@ResponseBody
	public Object addItem(String itemId, int quantity, @CookieValue(value = CartService.CART_UUID, required = false) String cartuuid,
	        HttpServletRequest request, HttpServletResponse response) throws SQLException {

		SessionUser userInfo = SessionUser.getSessionUser(response);

		// 获取商品信息
		ItemSaleDataDTO item = thirdInterDao.getItemById(itemId);

		// 判断当前商品是否到销售时间
		boolean checkStartTime = itemSaleService.checkStartTime(item.getItem());
		if (!checkStartTime) {
			return JsonRespWrapper.failure("尊敬的用户您好，商品尚未开始销售，请您继续关注");
		}

		// 判断是否是限时购买商品
		if (itemSaleService.checkIsPromptGoods(item.getItem())) {
			// 检查商品对于会员级别的限制
			String limitVipBuy = itemSaleService.checkGoodsBuyLimitVip(item.getItem(), userInfo);
			if (StringUtils.isNotBlank(limitVipBuy)) {
				if (Constants.RED_USER_LEVELS.equals(limitVipBuy)) {
					return JsonRespWrapper.failure("对不起，该商品仅限红钻会员购买，如需购买，请开通红钻会员，谢谢！");
				} else if (Constants.UNION_USER_LEVELS.equals(limitVipBuy)) {
					return JsonRespWrapper.failure("对不起，该商品仅限商盟会员购买，如需购买，请开通商盟会员，谢谢！");
				}
			}

			// 判断限购数量
			logger.info("验证用户购限制买数量");
			if (!itemSaleService.checkLimitBuyNum(quantity, item.getItem().getUserPerBuyNum())) {
				return JsonRespWrapper.failure("用户您好，购买数量超过限制数量");
			}

			logger.info("验证库存制买数量");
			if (!itemSaleService.checkStock(quantity, item.getItem().getStocknum())) {
				return JsonRespWrapper.failure("用户您好，当前库存不足，请继续关注。可能会有惊喜哦!");
			}

			// 判断当前活动时间是否开始
			if (TimeUtil.compareTime(TimeUtil.now(), item.getItem().getStopTime()) > 0) {
				return JsonRespWrapper.failure("特价活动结束，商品已下架");
			}

		}

		// 判断是否发码商品
		boolean isVipGoods = orderService.isVipGoods(item);
		if (isVipGoods) {
			// 判断是否需要绑定手机号
			if (isAbleToBindMobile(request, response)) {
				return JsonRespWrapper.failure("未绑定移动手机号码，请前往“个人中心>基本资料”进行绑定");
			}
		}
		// 劳保商品处理
		if (!appConfig.isTest()) {
			// 1、判断是否劳保商品
			boolean isLabourGoodsFlag = tBonusTerminalService.isLabourGoods(item);

			// 2、判断用户是否有购买劳保商品权限
			if (isLabourGoodsFlag) {
				boolean isBonusTerminal = tBonusTerminalService.isBonusTerminal(userInfo);
				if (!isBonusTerminal) {
					// 没权限购买劳保商品给予友好提示
					return tBonusTerminalService.unBonusTerminal();
				}
			}
		}

		String failureMsg = checkCommonLimit(item, quantity, response);
		if (StringUtils.isNotBlank(failureMsg)) {
			return JsonRespWrapper.failure(failureMsg);
		}

		GroupKey groupKey = new GroupKey(item);
		Long loginUserId = SessionUser.getSessionUser(response) == null ? 0L : SessionUser.getSessionUser(response).getId();

		// 虚拟商品
		String itemMode = String.valueOf(item.getItem().getItemMode());
		if (itemMode.equals("1")) {
			return JsonRespWrapper.failure("虚拟商品不允许加入购物车");
		}

		if (!appConfig.isTest()) {
			try {
				cartService.canAddToCart(itemId, groupKey.getShopId(), quantity, loginUserId);
			}
			catch (CartOpException ex) {
				return JsonRespWrapper.failure("此商品不能加入购物车, " + ex.getMessage());
			}
		}

		CartItem cartItem = new CartItem(groupKey, itemId, quantity);
		cartService.addCartItem(cartItem, cartuuid, response);
		return JsonRespWrapper.success("添加成功");
	}

	private String checkCommonLimit(ItemSaleDataDTO item, int quantity, HttpServletResponse response) {

		if (item == null) {
			return "商品不存在";
		}

		if (quantity > 200) {
			return "购买数量超出最大限制";
		}

		int stockNum = item.getItem().getStocknum();
		if (stockNum == 0) {
			return "太火啦，此商品已经卖光啦！";
		}
		if (stockNum > 0 && quantity > stockNum) {
			return "购买的数量超出库存数";
		}

		String limit = orderService.checkUserLevelLimit(item, response);
		if (limit != null) {
			if (Constants.RED_USER_LEVELS.equals(limit)) {
				return "对不起，该商品仅限红钻会员购买，如需购买，请开通红钻会员，谢谢！";
			} else if (Constants.UNION_USER_LEVELS.equals(limit)) {
				return "对不起，该商品仅限商盟会员购买，如需购买，请开通商盟会员，谢谢！";
			}
		}
		return null;
	}

	@RequestMapping("changeCount")
	@ResponseBody
	public Object changeCount(String itemKey, int count, @CookieValue(value = CartService.CART_UUID, required = false) String cartuuid,
	        HttpServletResponse response) throws SQLException {
		try {
			cartService.changeCartItemCount(itemKey, count, cartuuid, response);
			return JsonRespWrapper.success("0");
		}
		catch (CartOpException ex) {
			return JsonRespWrapper.failure(ex.getMessage());
		}
	}

	@RequestMapping("del")
	@ResponseBody
	public Object del(String itemKey, @CookieValue(value = CartService.CART_UUID, required = false) String cartuuid, HttpServletResponse response)
	        throws SQLException {
		try {
			cartService.delCartItem(itemKey, cartuuid, response);
			return JsonRespWrapper.success("0");
		}
		catch (CartOpException ex) {
			return JsonRespWrapper.failure(ex.getMessage());
		}
	}

	@RequestMapping("delall")
	@ResponseBody
	public Object delall(@CookieValue(value = CartService.CART_UUID, required = false) String cartuuid, HttpServletResponse response)
	        throws SQLException {
		try {
			cartService.delCartBox(cartuuid, response);
			return JsonRespWrapper.success("0");
		}
		catch (CartOpException e) {
			return JsonRespWrapper.failure(e.getMessage());
		}
	}

	private boolean isAbleToBindMobile(HttpServletRequest req, HttpServletResponse res) {
		if (appConfig.isTest()) {
			return false;
		}

		SessionUser user = SessionUser.getSessionUser(res);
		if (user != null && (user.getTerminalId() == null || "".equals(user.getTerminalId()))) {
			return true;
		} else {
			return false;
		}
	}

}
