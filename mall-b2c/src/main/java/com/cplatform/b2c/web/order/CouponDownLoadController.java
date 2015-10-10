package com.cplatform.b2c.web.order;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.service.CartService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 
 * @author zyl
 * 
 */
@Controller
@RequestMapping("/coupon")
public class CouponDownLoadController {

	private static final Log logger = LogFactory
			.getLog(CouponDownLoadController.class);

	@Autowired
	CartService cartService;

	@Autowired
	OrderService orderService;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	AppConfig appConfig;

	@RequestMapping("download")
	@ResponseBody
	public Object downloadItem(String itemId, int quantity,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		LoginUserBean user = new SSOAgent(request, response).loginUserInfo("mall");
		if(user == null){
			return JsonRespWrapper.failure("-2");
		}
		
		// 判断是否需要绑定手机号
		if (isAbleToBindMobile(request, response)) {
			return JsonRespWrapper.failure("-1");
		}
		
		// 获取商品信息
		ItemSaleDataDTO item = thirdInterDao.getItemById(itemId);

		String failureMsg = checkCommonLimit(item, quantity, response);
		if (StringUtils.isNotBlank(failureMsg)) {
			return JsonRespWrapper.failure(failureMsg);
		}
		
		JSONObject retJson = getOrder(user.getId(), Long.parseLong(itemId));
		return JsonRespWrapper.success("createOrder", retJson);
	}

	private String checkCommonLimit(ItemSaleDataDTO item, int quantity,
			HttpServletResponse response) {

		if (item == null) {
			return "商品不存在";
		}

		if (quantity > 200) {
			return "购买数量超出最大限制";
		}

		int stockNum = item.getItem().getStocknum();
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

	private boolean isAbleToBindMobile(HttpServletRequest req,
			HttpServletResponse res) {
		LoginUserBean user = new SSOAgent(req, res).loginUserInfo("mall");
		if (user != null
				&& (user.getTerminalId() == null || "".equals(user
						.getTerminalId()))) {
			return true;
		} else {
			return false;
		}
	}

	private JSONObject getOrder(Long userId, long goodsId) {
		JSONObject jsonObj = null;
		String url = appConfig.getCreateOrderUri();
		try {
			String json = "{\"U_ID\":"
					+ userId
					+ ",\"CREATE_SOURCE\":1,\"ORDER_TYPE\":51,\"GOODS\":[{\"COUNT\":1,\"GOOD_ID\":"
					+ goodsId + "}]}";

			logger.info("0元订单下单接口==》发送POST请求：" + url);
			logger.info("请求参数：" + json);
			String result = HttpClientUtils.httpPost(url, json, 1000, 1000);
			logger.info("0元订单下单接口返回结果：" + result);
			JSONObject jsonResult = JSONObject.fromObject(result);
			jsonObj = (JSONObject) jsonResult.toBean(jsonResult, JSONObject.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}

}
