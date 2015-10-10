package com.cplatform.b2c.repository;

import java.io.IOException;
import java.math.BigDecimal;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.b2c.dto.CallBalance;
import com.cplatform.b2c.dto.CreateOrderDTO;
import com.cplatform.b2c.dto.CreateOrderResponse;
import com.cplatform.b2c.dto.GiftCardRequest;
import com.cplatform.b2c.dto.GiftCardResponse;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.dto.LashouResponse;
import com.cplatform.b2c.dto.LogisticsFeeDTO;
import com.cplatform.b2c.dto.LogisticsFeeResp;
import com.cplatform.b2c.dto.PayDTO;
import com.cplatform.b2c.dto.PayOrderResponse;
import com.cplatform.b2c.dto.PurchaseCallback;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.b2c.util.JsonMapper;
import com.cplatform.b2c.web.order.OrderCommonException;

/**
 * 第三方接口 User: cuikai Date: 13-11-20 Time: 下午3:54
 */
@Component
public class ThirdInterDao {

	private static Logger logger = Logger.getLogger(ThirdInterDao.class);

	private static JsonMapper mapper;

	static {
		mapper = JsonMapper.buildNormalMapper();
	}

	@Autowired
	private AppConfig appConfig;

	/**
	 * 获取商品信息
	 * 
	 * @param itemId
	 * @return 如果商品接口返回的数据不正确，则返回null
	 */
	@Deprecated
	public JSONObject getItem(String itemId) {
		if (itemId == null)
			return null;
		ItemSaleDataDTO item = getItemById(itemId);
		if (item == null)
			return null;
		return JSONObject.fromObject(item);
	}

	public ItemSaleDataDTO getItemById(String itemId) {
		try {
			String resp = HttpClientUtils.httpGet(appConfig.getInterfaceItemInfo(), "saleId=" + itemId);
			return mapper.fromJson(resp, ItemSaleDataDTO.class);
		}
		catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public GiftCardResponse getGiftCardResponse(GiftCardRequest cardRequest) {
		try {
			String respJson = HttpClientUtils.httpPost(appConfig.getGiftCardQuery(), mapper.toJson(cardRequest));
			return mapper.fromJson(respJson, GiftCardResponse.class);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new GiftCardResponse();
	}

	/**
	 * 礼金券支付
	 * 
	 * @param request
	 * @return
	 */
	public GiftCardResponse callGiftCardPay(GiftCardRequest request) {
		try {
			String respJson = HttpClientUtils.httpPost(appConfig.getGiftCardPay(), mapper.toJson(request));
			return mapper.fromJson(respJson, GiftCardResponse.class);
		}
		catch (Exception e) {
			throw new OrderCommonException("调用礼金券pay接口异常");
		}
	}

	/**
	 * 礼金券验证
	 * 
	 * @param request
	 * @return
	 */
	public GiftCardResponse.Data getGiftCardDetail(GiftCardRequest request) {
		try {
			String respJson = HttpClientUtils.httpPost(appConfig.getGiftCardVerify(), mapper.toJson(request));
			return mapper.fromJson(respJson, GiftCardResponse.Data.class);
		}
		catch (Exception e) {

			throw new OrderCommonException("调用礼金券check接口异常");
		}
	}

	public int getItemFinalPrice(String itemId, String userId, String orderType, String businessId) {

		try {
			String params = "itemId=" + itemId + "&userId=" + userId + "&orderType=" + orderType + "&businessId=" + businessId;
			String respJson = HttpClientUtils.httpGet(appConfig.getFinalPriceUrl(), params);
			return NumberUtils.toInt(respJson);
		}
		catch (Exception e) {
			throw new OrderCommonException("查询商品最终价格异常");
		}
	}

	public LashouResponse queryLashouItem(String itemId) throws Exception {

		String resp = HttpClientUtils.httpGet(appConfig.getLashouQueryUrl(), "itemId=" + itemId);
		return mapper.fromJson(resp, LashouResponse.class);
	}

	/**
	 * 营销接口
	 * 
	 * @return
	 */
	public JSONObject getAuctionOrderInterface(String goodsId, String type, String businessId) {
		if (StringUtils.isBlank(goodsId))
			return null;
		type = (type == null ? "0" : type);
		String resp = "{}";
		try {
			resp = CommonUtils.getResponseFromServer(appConfig.getAuctionOrderurl() + "?goodsNo=" + goodsId + "&type=" + type + "&businessId="
			        + businessId, "utf-8");
			/** modify by zhangdong */
			// 发现数据接口查询出没有该数据的时候返回：{MSG=no product found!, FLAG=0}，而非{MSG=\"no
			// product found!\", FLAG=0}
			// 判断返回的resp字符串中是否含有FLAG=0
			if (resp.indexOf("FLAG=0") != -1) {
				return null;
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return JSONObject.fromObject(resp);
	}

	/**
	 * @param itemId
	 * @return
	 */
	public JSONObject getGroupItemsFromInterface(String itemId) {
		try {
			if (itemId == null)
				return null;
			String resp = HttpClientUtils.httpGet(appConfig.getInterfaceGroupItems(), "saleId=" + itemId);
			JSONObject jsonObject = JSONObject.fromObject(resp);
			if (jsonObject.getJSONArray("items").isEmpty()) {
				return null;
			} else {
				return jsonObject;
			}
		}
		catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public JSONObject getCoinFromInterface(String mobile) {
		try {
			String resp = HttpClientUtils.httpGet(appConfig.getInterfaceCoinInfo(), "mobile=" + mobile);
			return JSONObject.fromObject(resp);
		}
		catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 话费查询
	 * 
	 * @param mobile
	 * @return
	 */
	public CallBalance getBalanceInfo(String mobile) {
		Validate.notNull(mobile, "手机号码不能为空");
		try {
			String resp = HttpClientUtils.httpGet(appConfig.getBalanceInfo(), "mobile=" + mobile);
			return mapper.fromJson(resp, CallBalance.class);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new CallBalance();
	}

	public CreateOrderResponse createOrder(CreateOrderDTO createOrderDTO) throws Exception {

		String respJson = HttpClientUtils.httpPost(appConfig.getCreateOrderUri(), mapper.toJson(createOrderDTO));
		return mapper.fromJson(respJson, CreateOrderResponse.class);
	}

	public PayOrderResponse payOrder(PayDTO payDTO) throws Exception {

		String resp = HttpClientUtils.httpPost(appConfig.getPayOrderUri(), mapper.toJson(payDTO));
		return mapper.fromJson(resp, PayOrderResponse.class);
	}

	/**
	 * 劳保红包支付
	 * 
	 * @param payDTO
	 * @return
	 * @throws Exception
	 */
	public PayOrderResponse payWelfareOrder(com.cplatform.b2c.model.PayDTO payDTO) throws Exception {

		String resp = HttpClientUtils.httpPost(appConfig.getPayOrderUri(), mapper.toJson(payDTO));
		return mapper.fromJson(resp, PayOrderResponse.class);
	}

	public PurchaseCallback.Response notifyWhenPayed(PayDTO payDTO) {
		try {
			PurchaseCallback.Request requestDTO = new PurchaseCallback.Request();
			requestDTO.setActOrderId(String.valueOf(payDTO.getOrderId()));
			requestDTO.setBuyId(String.valueOf(payDTO.getUserId()));
			requestDTO.setSrc("1");
			String resp = HttpClientUtils.httpGet(appConfig.getBackOrderUrl(), mapper.toJson(requestDTO));
			return mapper.fromJson(resp, PurchaseCallback.Response.class);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new PurchaseCallback.Response();
	}

	/**
	 * 劳保商品
	 * 
	 * @param payDTO
	 * @return
	 */
	public PurchaseCallback.Response notifyWhenWelfarePayed(com.cplatform.b2c.model.PayDTO payDTO) {
		try {
			PurchaseCallback.Request requestDTO = new PurchaseCallback.Request();
			requestDTO.setActOrderId(String.valueOf(payDTO.getOrderId()));
			requestDTO.setBuyId(String.valueOf(payDTO.getUserId()));
			requestDTO.setSrc("1");
			String resp = HttpClientUtils.httpGet(appConfig.getBackOrderUrl(), mapper.toJson(requestDTO));
			return mapper.fromJson(resp, PurchaseCallback.Response.class);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new PurchaseCallback.Response();
	}

	/**
	 * 永乐演出票订单下单接口判断
	 * 
	 * @param productId
	 *            永乐票品ID
	 * @param productPlayid
	 *            永乐票价ID
	 * @param buyType
	 *            购买类型
	 * @param ticketNumber
	 *            数量
	 * @param ticketPrice
	 *            单价
	 * @return
	 */
	public String performBeforeOrder(String productId, String productPlayid, String buyType, int ticketNumber, BigDecimal ticketPrice) {
		String result = "";
		try {
			StringBuffer paramBuff = new StringBuffer(200);
			paramBuff.append("productId=").append(productId).append("&");
			paramBuff.append("priceId=").append(productPlayid).append("&");
			paramBuff.append("buyType=").append(buyType).append("&");
			paramBuff.append("ticketNumber=").append(ticketNumber).append("&");
			paramBuff.append("ticketPrice=").append(ticketPrice);
			logger.info("调用演出票内部接口，请求参数：" + paramBuff.toString());
			result = HttpClientUtils.httpGet(appConfig.getPerformInterfaceUrl() + "/getTicketStatus", paramBuff.toString());
			if (StringUtils.isNotBlank(result)) {
				result = new String(Base64.decodeBase64(result.getBytes("UTF-8")), "UTF-8");
			}
			logger.info("调用演出票内部接口，请求返回：" + result);
		}
		catch (Exception e) {
			logger.error("调用演出票内部接口", e);
		}
		return result;
	}

	/**
	 * 河南积分退码请求
	 * 
	 * @param orderId
	 *            订单编号
	 * @return
	 */
	public JSONObject refundVerifyCodeHN(Long orderId) {
		if (StringUtils.isBlank(String.valueOf(orderId)))
			return null;
		String resp = "{}";
		try {
			resp = CommonUtils.getResponseFromServer(appConfig.getHenanRefundCodeUrl() + orderId, "utf-8");
			logger.info("河南积分订单退码请求:" + resp);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return JSONObject.fromObject(resp);
	}

	/**
	 * 获取需支付的物流价格
	 * 
	 * @param logisticsFeeDTO
	 * @return
	 * @throws Exception
	 */
	public LogisticsFeeResp getLogisticsFee(LogisticsFeeDTO logisticsFeeDTO) throws Exception {
		String respJson = HttpClientUtils.httpPost(appConfig.getLogisticsFee(), mapper.toJson(logisticsFeeDTO));
		return mapper.fromJson(respJson, LogisticsFeeResp.class);
	}

}
