package com.cplatform.b2c.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.constant.OrderConstant;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.model.CardGift;
import com.cplatform.b2c.model.CardGiftDetails;
import com.cplatform.b2c.model.ItemComment;
import com.cplatform.b2c.model.ItemLottery;
import com.cplatform.b2c.model.ItemSale;
import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.SzMallOrderHistory;
import com.cplatform.b2c.model.TActOrder;
import com.cplatform.b2c.model.TActOrderNew;
import com.cplatform.b2c.model.TItemComment;
import com.cplatform.b2c.model.TMemberAddress;
import com.cplatform.b2c.model.TOrderRefund;
import com.cplatform.b2c.model.TUserCenterVisitLog;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.repository.GoodsDao;
import com.cplatform.b2c.repository.ItemSaleDao;
import com.cplatform.b2c.repository.MemberCenterRepository;
import com.cplatform.b2c.repository.VSearchGoodDao;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.ArrayUtil;
import com.cplatform.b2c.util.CTime;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.b2c.util.MenuCodeUtil;
import com.cplatform.b2c.util.PathUtil;
import com.cplatform.b2c.util.StringUtil;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.order.ActOrderExpressInfo;
import com.cplatform.order.ActOrderGoodsInfo;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PayServiceClient;
import com.cplatform.pay.PaymentInfo;

/**
 * 个人中心 服务类 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) Jun 6, 2013 7:06:03 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class MemberCenterService {

	private final Logger logger = Logger.getLogger(MemberCenterService.class);

	@Autowired
	private MemberCenterRepository repository;

	@Autowired
	private VSearchGoodDao goodDao;

	@Autowired
	private PathUtil pathUtil;

	@Autowired
	private PayServiceClient payServiceClient;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private ItemSaleDao itemSaleDao;

	/**
	 * 获取我的评价
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<ItemComment> getComments(PageInfo pageInfo, String userId) {
		List<Object[]> list = repository.getComments(pageInfo, userId);
		List<ItemComment> results = new ArrayList<ItemComment>();
		for (Object[] l : list) {
			ItemComment co = new ItemComment();
			co.setId(Integer.valueOf(l[0].toString()));
			co.setShort_name(l[1].toString());
			co.setFile_name(l[2].toString());
			co.setCommentContent(l[3].toString());
			co.setRank(Integer.valueOf(l[4].toString()));
			co.setCommentTime(l[5].toString().substring(0, 16));
			co.setReplyContent(String.valueOf(l[6]).equals("null") ? "" : String.valueOf(l[6]));
			String time = String.valueOf(l[7]);
			co.setReplyTime(time.equals("null") ? "" : time.substring(0, 16));
			co.setNickname(String.valueOf(l[8]).equals("null") ? "" : String.valueOf(l[8]));
			co.setPath(pathUtil.getPathById(1, Long.valueOf(l[9].toString())));
			co.setStatus(Integer.valueOf(l[10].toString()));
			co.setSale_id(Integer.valueOf(l[9].toString()));
			results.add(co);
		}
		return results;
	}

	/**
	 * 获取我的咨询
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<ItemComment> getConsults(PageInfo pageInfo, String userId) {
		List<Object[]> list = repository.getConsults(pageInfo, userId);
		List<ItemComment> results = new ArrayList<ItemComment>();
		for (Object[] l : list) {
			ItemComment co = new ItemComment();
			co.setId(Integer.valueOf(l[0].toString()));
			co.setShort_name(l[1].toString());
			co.setFile_name(l[2].toString());
			co.setCommentContent(l[3].toString());
			co.setQuestion_type(l[4].toString());
			co.setCommentTime(l[5].toString().substring(0, 16));
			co.setReplyContent(String.valueOf(l[6]).equals("null") ? "" : String.valueOf(l[6]));
			String time = String.valueOf(l[7]);
			co.setReplyTime(time.equals("null") ? "" : time.substring(0, 16));
			co.setNickname(String.valueOf(l[8]).equals("null") ? "" : String.valueOf(l[8]));
			co.setPath(pathUtil.getPathById(1, Long.valueOf(l[9].toString())));
			co.setStatus(Integer.valueOf(l[10].toString()));
			co.setSale_id(Integer.valueOf(l[9].toString()));
			results.add(co);
		}
		return results;
	}

	/**
	 * 获取我的评价总页数
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getCommentsScript(PageInfo pageInfo, String userId) {
		return repository.getCommentsScript(pageInfo, userId);
	}

	/**
	 * 获取我的咨询总页数
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getConsultsScript(PageInfo pageInfo, String userId) {
		return repository.getConsultsScript(pageInfo, userId);
	}

	/**
	 * 获取我的收货地址
	 * 
	 * @param userId
	 * @return
	 */
	public List<String[]> getAddresses(String userId) {
		return repository.getAddresses(userId);
	}

	/**
	 * 保存或更新收货地址
	 * 
	 * @param address
	 */
	public boolean saveOrUpdateAddress(TMemberAddress address) {
		// address.setRegion(repository.getRegionCode(address.getRegion()));
		address.setCreateTime(CTime.getTime(14));
		address.setUpdateTime(CTime.getTime(14));
		return repository.saveOrUpdateAddress(address);
	}

	/**
	 * 删除地址
	 * 
	 * @param address
	 * @return
	 */
	public boolean delAddress(String id) {
		return repository.delAddress(repository.getAddress(id));
	}

	/**
	 * 获取地址
	 * 
	 * @param id
	 * @return
	 */
	public TMemberAddress getAddress(String id) {
		return repository.getAddress(id);
	}

	/**
	 * 设置默认地址
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	public boolean setAddress(String id, String userId) {
		return repository.setAddress(id, userId);
	}

	/**
	 * 获取商品
	 * 
	 * @param id
	 * @return
	 */
	public VSearchGood getSearchGood(String id) {
		return goodDao.get(id);
	}

	/**
	 * 获取商品
	 * 
	 * @param id
	 * @return
	 */
	public List<VSearchGood> getSearchGoods(String id) {
		String[] ids = id.split(",");
		Integer[] saleIds = new Integer[ids.length];
		for (int i = 0; i < ids.length; i++) {
			saleIds[i] = Integer.valueOf(ids[i]);
		}
		return goodDao.getList(saleIds);
	}

	/**
	 * 进入评价页面
	 * 
	 * @param saleId
	 * @return
	 */
	public List<ItemSaleDataDTO> getCommentItems(String saleId) {
		// 判断saleId是否为空
		if (StringUtils.isNotBlank(saleId)) {
			String[] itemIds = saleId.split(",");
			if (null != itemIds && itemIds.length > 0) {
				return itemSaleDao.findHistoryItems(itemIds);
			}
		}
		return null;
	}

	/**
	 * 根据订单中的编号查询出，商品的类型，以及是否需要物流配送
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<ItemSale> getItemsById(String id) throws SQLException {
		Object[] params = id.split(",");
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.setLength(0);
		sqlBuilder.append("SELECT item_mode,post_flag FROM T_ITEM_SALE s WHERE s.id in ( ");
		for (int i = 0; i < params.length; i++) {
			sqlBuilder.append("?");
			if (i != params.length - 1) {
				sqlBuilder.append(",");
			}
		}
		sqlBuilder.append(")");
		return dbHelper.getBeanList(sqlBuilder.toString(), ItemSale.class, params);
	}

	/**
	 * 获取配送方式
	 * 
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public String getExpress(String ids) throws SQLException {
		List<ItemSale> searchGoods = getItemsById(ids);
		String express = "物流配送";
		for (ItemSale good : searchGoods) {
			if (good.getItemMode() == 1) {
				express = "无需物流";
				break;
			} else {
				if (null != good.getPostFlag() && good.getPostFlag() == 1) {
					express = "物流配送";
					break;
				} else {
					express = "无需物流";
				}
			}
		}
		return express;
	}

	/**
	 * 是否为虚拟商品
	 * 
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public String getIsXuNi(String ids) throws SQLException {
		List<ItemSale> searchGoods = getItemsById(ids);
		String isXuNi = "0";
		for (ItemSale good : searchGoods) {
			if (1 == good.getItemMode()) {
				isXuNi = "1";
				break;
			}
		}
		return isXuNi;
	}

	/**
	 * 获取结算总金额
	 * 
	 * @param info
	 * @param goodAmount
	 * @param discount
	 * @return
	 * @throws Exception
	 */
	public String getTotal(ActOrderInfo info, int goodAmount, int discount, String isXuNi, BigDecimal marketPriceAount) throws Exception {
		StringBuffer amount = new StringBuffer();
		int expressFee = 0;
		if (null != info.getExpressInfo()) {
			expressFee = info.getExpressInfo().getExpressCost();
		}
		int total = goodAmount + expressFee - discount;
		DecimalFormat fnum = new DecimalFormat("##0.00");
		// 判断是否是货到付款
		if (info.getPayOnDelivery() == 1) {
			amount.append("<label class=\"order_totalprice\">应付").append(fnum.format(Float.valueOf(total) / 100)).append("元</label>");
		} else {
			if (Constants.ORDER_TYPE_PROMPT.equals(info.getOrderType())) { // 限时抢购订单
				amount.append("商品金额：").append(fnum.format(Float.valueOf(marketPriceAount.intValue()) / 100)).append("元");
			} else {
				amount.append("商品金额：").append(fnum.format(Float.valueOf(goodAmount) / 100)).append("元");
			}

			if (StringUtils.equals("0", isXuNi)) {
				amount.append(" + 运费：").append(fnum.format(Float.valueOf(expressFee) / 100)).append("元");
			}

			if (Constants.ORDER_TYPE_PROMPT.equals(info.getOrderType())) { // 限时抢购订单
				amount.append(" - 限时抵扣：").append(fnum.format(Float.valueOf(marketPriceAount.subtract(new BigDecimal(goodAmount)).intValue()) / 100))
				        .append("元");
			}

			logger.info("调用礼金券接口获得优惠/if_card/info开始");
			// 调用礼金券接口获得优惠
			String json = this.getCardGiftByOrderId(info.getId());
			logger.info("调用礼金券接口获得优惠/if_card/info结果" + json);
			// String json =
			// "{\"flag\":0,\"msg\":\"success\",\"giftId\":45982343,\"cardName\":\"10元礼金券\",\"payment\":[{\"itemId\":1234567,\"payAmount\":3.33},{\"itemId\":434567,\"payAmount\":6.67}]}";
			int cardPay = 0;
			if (json != null) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject = JSONObject.fromObject(json);
					if (jsonObject.getInt("flag") == 0) {
						JSONArray paymentMap = jsonObject.getJSONArray("payment");
						for (int i = 0; i < paymentMap.size(); i++) {
							cardPay += paymentMap.getJSONObject(i).getDouble("payAmount") * 100;
						}
						float dis = Float.valueOf(discount) - Float.valueOf(cardPay);
						if (dis > 0) {
							amount.append(" - 优惠金额：").append(fnum.format(Float.valueOf(dis) / 100)).append("元");
						}
						if (cardPay > 0) {
							amount.append(" - 礼金券抵扣：").append(fnum.format(Float.valueOf(cardPay) / 100)).append("元");
						}
						// total = total - cardPay;
					} else {
						if (discount > 0) {
							amount.append(" - 优惠金额：").append(fnum.format(Float.valueOf(discount) / 100)).append("元");
						}
					}
				}
				catch (Exception ex) {
					logger.error("调用礼金券接口获得优惠/if_card/info错误" + ex.getMessage());
					if (discount > 0) {
						amount.append(" - 优惠金额：").append(fnum.format(Float.valueOf(discount) / 100)).append("元");
					}
				}

			} else {
				if (discount > 0) {
					amount.append(" - 优惠金额：").append(fnum.format(Float.valueOf(discount) / 100)).append("元");
				}
			}
			amount.append(" = <label class=\"order_totalprice\">应付总金额：").append(fnum.format(Float.valueOf(total) / 100)).append("元</label>");
		}
		// if (null != info.getPaymentInfos() && info.getPaymentInfos().size() >
		// 0) {
		// for (ActOrderPaymentInfo pay : info.getPaymentInfos()) {
		// if ("coin".equals(pay.getCurrency())) {
		// amount.append(" -
		// 商城币支付：").append(fnum.format(Float.valueOf(pay.getAmount()) /
		// 100)).append("元");
		// total = total - pay.getAmount();
		// }
		// }
		// }

		return amount.toString();
	}

	/**
	 * 获取订单状态
	 * 
	 * @param isXuNi
	 * @param info
	 * @return
	 */
	public int getStatus(String isXuNi, ActOrderInfo info) {
		int status = 1;
		if (StringUtils.equals("0", isXuNi)) {
			logger.info("orderView=isXuNi===========isXuNi=" + isXuNi + "==============status=" + status);
			ActOrderExpressInfo expressInfo = info.getExpressInfo();
			if (null != expressInfo) {
				if (expressInfo.getStatus().intValue() == 2) {
					status = 4;// 已完成
				} else if (expressInfo.getStatus().intValue() == 1) {
					status = 3;// 待收货
				} else if (info.getCloseStatus().intValue() == 1) {
					status = 5;// 已取消
				} else if (info.getPayStatus().intValue() == 2) {
					status = 2;// 已付款
				} else if (info.getPayStatus().intValue() == 1) {
					status = 6;// 支付中
				} else if (info.getPayStatus().intValue() == 0) {
					status = 1;// 待付款
				} else if (expressInfo.getStatus().intValue() == 0) {
					status = 2;// 已付款
				}
			} else if (info.getCloseStatus().intValue() == 1) {
				status = 5;// 已取消
			} else if (info.getPayStatus().intValue() == 2) {
				status = 2;// 已付款
			} else if (info.getPayStatus().intValue() == 1) {
				status = 6;// 支付中
			} else if (info.getPayStatus().intValue() == 0) {
				status = 1;// 待付款
			}
		} else {
			if (info.getCloseStatus().intValue() == 1) {
				status = 5;// 已取消
			} else if (info.getPayStatus().intValue() == 2) {
				status = 2;// 已付款
			} else if (info.getPayStatus().intValue() == 1) {
				status = 6;// 支付中
			} else if (info.getPayStatus().intValue() == 0) {
				status = 1;// 待付款
			}
		}
		logger.info("orderView=return=status=========================" + status);
		return status;
	}

	/**
	 * 获取验证码
	 * 
	 * @param orderId
	 * @return
	 */
	public List<Object[]> getVerifyCode(Long orderId) {
		return repository.getVerifyCode(orderId);
	}

	/**
	 * 获取支付信息
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, String> getPayInfo(ActOrderInfo info) {
		Map<String, String> map = new HashMap<String, String>();
		int payCoin = 0;
		int payCash = 0;
		int payScore = 0;
		int payBalance = 0;
		if (null != info) {
			List<ActOrderPaymentInfo> payments = info.getPaymentInfos();
			if (null != payments) {
				try {
					List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(info.getId());
					for (PayOrderInfo payOrderInfo : payOrderInfos) {
						if (payOrderInfo.getStatus() == PayOrderInfo.STATUS_SUCCESS) {
							List<PaymentInfo> infos = payOrderInfo.getPayments();
							for (PaymentInfo pay : infos) {
								if (StringUtils.equalsIgnoreCase(pay.getCurrency(), "coin")) {
									payCoin += pay.getAmount();
								} else if (StringUtils.equalsIgnoreCase(pay.getCurrency(), "cash")) {
									payCash += pay.getAmount();
								} else if (StringUtils.equalsIgnoreCase(pay.getCurrency(), "score")) {
									payScore += pay.getAmount();
								} else if (StringUtils.equalsIgnoreCase(pay.getCurrency(), "balance")) {
									payBalance += pay.getAmount();
								}
							}
						}
					}

				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		DecimalFormat fnum = new DecimalFormat("##0.00");
		map.put("cash", String.valueOf(payCash));
		map.put("coin", String.valueOf(payCoin));
		map.put("score", String.valueOf(payScore));
		map.put("balance", String.valueOf(payBalance));
		map.put("total", fnum.format(Float.valueOf(info.getTotalPayAmount()) / 100));
		return map;
	}

	/**
	 * 保存评论
	 * 
	 * @param comment
	 * @return
	 */
	public boolean saveComment(TItemComment comment) {
		comment.setUpdateTime(CTime.getTime(14));
		comment.setType(1);
		comment.setStatus(0);
		return repository.saveComment(comment);
	}

	public List<String[]> getRegion(String type, String parentId) {
		List<Object[]> l = repository.getRegion(type, parentId);
		List<String[]> reses = new ArrayList<String[]>();
		for (Object[] ll : l) {
			String[] res = new String[2];
			res[0] = ll[0].toString();
			res[1] = ll[1].toString();
			reses.add(res);
		}
		return reses;
	}

	/**
	 * 获取我的订单总页数
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param flag
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String getOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery) {
		return repository.getOrdersScript(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery);
	}

	/**
	 * 获取订单
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
//	public int getOrdersByStatus(String userId, String status, String isdelivery,Long [] orderTypes) {
//		return repository.getOrdersByStatus(userId, status, isdelivery,orderTypes);
//	}
	
	/**
	 * 修改查询代付款和代收货的状态 根据条件获取条数
	 * 
	 * @param userId
	 *            登录用户Id
	 * @param status
	 *            订单状态 1-代付款 3-代收货
	 * @param orderTypes
	 *            要查询的orderType
	 * @param startTime 
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return int 条数
	 */
	public int countOrdersByStatus(String userId, String status, String startTime, String endTime, String name, String flag, String isdelivery,
			Long[] orderTypes) {
		if(ArrayUtil.isEmpty(orderTypes)){
			orderTypes=ArrayUtil.toLongArr(OrderConstant.COMMON_PRODUCT);
		}
		return repository.countOrdersByStatus(userId, status, startTime,endTime,name,flag,isdelivery,orderTypes);
	}

	/**
	 * 获取货到付款的订单
	 * 
	 * @param userId
	 * @return
	 */
	public int getOrdersByDelivery(String userId, String isdelivery) {
		return repository.getOrdersByDelivery(userId, isdelivery);
	}

	/**
	 * 获取用户订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param flag
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<TActOrder> getOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery) {
		return repository.getOrders(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery);
	}

	/**
	 * 订单是否评价
	 * 
	 * @param orderId
	 * @return
	 */
	public String getOrderComment(Long orderId) {
		return repository.getOrderComment(orderId);
	}

	/**
	 * 获取退款信息
	 * 
	 * @param orderId
	 * @param info
	 * @return
	 */
	public List<TOrderRefund> getRefunds(String orderId, ActOrderInfo info) {
		return repository.getRefunds(Long.valueOf(orderId), info);
	}

	/**
	 * 获取某个订单中的商品
	 * 
	 * @param orderId
	 * @return
	 */
	public String getSaleIdByOrder(String orderId) {
		return repository.getSaleIdByOrder(orderId);
	}

	/**
	 * 删除收藏
	 * 
	 * @param userId
	 * @param saleIds
	 * @return
	 */
	public boolean delFavorite(String favoriteId, String userId, String type) {
		Integer[] favoriteIds = new Integer[] {};
		String[] ss = favoriteId.split(",");
		for (String s : ss) {
			favoriteIds = (Integer[]) ArrayUtils.add(favoriteIds, Integer.valueOf(s));
		}
		return repository.delFavorite(favoriteIds, userId, type);
	}

	/**
	 * 获取我的收藏商品
	 * 
	 * @param userId
	 * @param pageInfo
	 * @return
	 */
	public List<Object[]> getFavoriteGoods(String userId, PageInfo pageInfo) {
		return repository.getFavoriteGoods(userId, pageInfo);
	}

	/**
	 * 获取我的收藏商品总页数
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getFavoriteGoodsScript(PageInfo pageInfo, String userId) {
		return repository.getFavoriteGoodsScript(pageInfo, userId);
	}

	/**
	 * 获取我的收藏商户
	 * 
	 * @param userId
	 * @param pageInfo
	 * @return
	 */
	public List<Object[]> getFavoriteStores(String userId, PageInfo pageInfo) {
		return repository.getFavoriteStores(userId, pageInfo);
	}

	/**
	 * 获取我的收藏商户总页数
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getFavoriteStoresScript(PageInfo pageInfo, String userId) {
		return repository.getFavoriteStoresScript(pageInfo, userId);
	}

	/**
	 * G实惠我的订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param flag
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public String getgCheapOrders(String userId, String name, String startTime, String endTime, String type, String curpage) throws Exception {
		return repository.getgCheapOrders(userId, name, startTime, endTime, type, curpage);
	}

	/**
	 * G实惠订单分页
	 * 
	 * @param curpage
	 * @param json
	 * @return
	 */
	public PageInfo getgCheapOrdersPageInfo(String curpage, JSONObject json) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		pageInfo.setRecordCount(Integer.valueOf(json.getString("TOTAL_ROW")));
		pageInfo.setPageRows(10);
		pageInfo.setPageTotals();

		return pageInfo;
	}

	public String getgCheapDeposit(Long userId) {
		return repository.getgCheapDeposit(userId);
	}

	public boolean isExistMobile(String mobile) {
		return repository.isExistMobile(mobile);
	}

	public boolean bindMobile(Long userId, String mobile) {
		return repository.bindMobile(userId, mobile);
	}

	public Date getGCheapCreateTime(String businessId, String type) {
		return repository.getGCheapCreateTime(businessId, type);
	}

	/**
	 * 请求开通红钻
	 * 
	 * @param terminalId
	 * @return
	 */
	public String requestOpenRedDiamond(String terminalId) {
		String url = appConfig.getSpaiUrl() + "/api/v1/auth/openRedDiamondByTerminal?TERMINAL_ID=" + terminalId;
		try {
			return CommonUtils.getResponseFromServer(url, "UTF-8");
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 按照条件获取老商城订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public List<SzMallOrderHistory> getSZMallOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
		return repository.getSZMallOrders(userId, name, pageInfo, startTime, endTime, status);
	}

	/**
	 * 按照条件获取老商城订单分页方法
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public String getSZMallOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
		return repository.getSZMallOrdersScript(userId, name, pageInfo, startTime, endTime, status);
	}

	/**
	 * 获取我的礼金券
	 * 
	 * @param userId
	 * @param pageInfo
	 * @param status
	 * @return
	 */
	public List<CardGiftDetails> getCardGifts(String phone, PageInfo pageInfo) {
		List<Object[]> objects = repository.getCardGifts(phone, pageInfo);
		List<CardGiftDetails> details = new ArrayList<CardGiftDetails>();
		if (objects != null && objects.size() > 0) {
			for (Object[] obj : objects) {
				CardGiftDetails detail = new CardGiftDetails();
				detail.setId(Long.parseLong(obj[0].toString()));
				List<CardGift> gifts = repository.getGiftCardByCardId(Long.parseLong(obj[1].toString()));
				if (gifts != null && gifts.size() > 0) {
					detail.setGift(gifts.get(0));
					detail.setStatus(obj[2].toString());
					detail.setMobile(String.valueOf(obj[3]).equals("null") ? "" : String.valueOf(obj[3]));
					detail.setOrderId(String.valueOf(obj[4]).equals("null") ? "" : String.valueOf(obj[4]));
					String money = String.valueOf(obj[5]).equals("null") ? "" : String.valueOf(obj[5]);
					if (!StringUtils.isBlank(money)) {
						detail.setUseMoney(new BigDecimal(money));
					} else {
						detail.setUseMoney(new BigDecimal(0.00));
					}

					detail.setUseTime(String.valueOf(obj[6]).equals("null") ? "" : String.valueOf(obj[6]));
					detail.setCreateTime(String.valueOf(obj[7]).equals("null") ? "" : String.valueOf(obj[7]));
					detail.setUpdateTime(String.valueOf(obj[8]).equals("null") ? "" : String.valueOf(obj[8]));
					detail.setCardLink(String.valueOf(obj[9]).equals("null") ? "" : String.valueOf(obj[9]));
					details.add(detail);
				}
			}
		}
		return details;
	}

	/**
	 * 获取我的礼金券分页方法
	 * 
	 * @param userId
	 * @param pageInfo
	 * @param status
	 * @return
	 */
	public String getCardGiftsScript(String phone, PageInfo pageInfo) {
		return repository.getCardGiftsScript(phone, pageInfo);
	}

	/**
	 * 查询我的礼品卡订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public List<TActOrder> getGiftOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
		return repository.getGiftOrders(userId, name, pageInfo, startTime, endTime, status);
	}

	/**
	 * 查询我的礼品卡订单分页类
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public String getGiftOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
		return repository.getGiftOrdersScript(userId, name, pageInfo, startTime, endTime, status);
	}

	/**
	 * 查询礼品卡营销平台接口
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public String getCardGiftByOrderId(Long orderId) throws Exception {
		String url = appConfig.getCardCheckUrl();
		String jsonString = "{\"channel\":1,\"orderId\":" + orderId + "}";
		String response = HttpClientUtils.httpPost(url, jsonString, 0, 0);
		return response;

	}

	/**
	 * 获取该用户是否有参加红包的资格
	 * 
	 * @param terminalId
	 * @return
	 */
	public boolean getBonusTerminal(Long terminalId) {
		return repository.getBonusTerminal(terminalId);
	}

	/**
	 * 查询我的红包的订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<TActOrderNew> getRedOrders(String userId, PageInfo pageInfo) {
		return repository.getRedOrders(userId, pageInfo);
	}

	/**
	 * 查询我的红包订单分页类
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String getRedOrdersScript(String userId, PageInfo pageInfo) {
		return repository.getRedOrdersScript(userId, pageInfo);
	}

	/**
	 * 查询我的团购订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public List<TActOrder> getGroupBuyOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status,
	        String movieType) {
		return repository.getGroupBuyOrders(userId, name, pageInfo, startTime, endTime, status, movieType);
	}

	/**
	 * 查询我的团购订单分页类
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public String getGroupBuyOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status,
	        String movieType) {
		return repository.getGroupBuyOrdersScript(userId, name, pageInfo, startTime, endTime, status, movieType);
	}

	/**
	 * 查询我的团购订单 - 新商城
	 * 
	 * @param userId
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public List<TActOrder> getGoodsOrders(String userId, String name, String startTime, String endTime, String status) {
		return repository.getGoodsOrders(userId, name, startTime, endTime, status);
	}

	/**
	 * 查询我的团购订单 - 商盟
	 * 
	 * @param userId
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public List<TActOrder> getGoodsOrders777(String userId, String name, String startTime, String endTime, String status) {
		return repository.getGoodsOrders777(userId, name, startTime, endTime, status);
	}

	/**
	 * 查询我的演出票订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public List<TActOrder> getPerformOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
		return repository.getPerformOrders(userId, name, pageInfo, startTime, endTime, status);
	}

	/**
	 * 查询我的演出票订单分页类
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	public String getPerformOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
		return repository.getPerformOrdersScript(userId, name, pageInfo, startTime, endTime, status);
	}

	/**
	 * 获得商品验证码信息
	 * 
	 * @param order_id
	 * @return
	 */
	public List<String[]> getVerifyInfo(String order_id, int flag) {
		return goodsDao.getVerifyInfo(order_id, flag);
	}

	/**
	 * 获取代金券补发短信次数
	 * 
	 * @param order_id
	 * @return
	 */
	public String[] getSendLog(String order_id, String code) {
		return goodsDao.getSendLog(order_id, code);
	}

	/**
	 * 保存补发记录
	 * 
	 * @param order_id
	 *            业务订单号
	 * @param good_id
	 *            商品id
	 * @param userId
	 *            用户id
	 * @param terminal_id
	 *            用户手机号码
	 * @param code
	 *            验证码
	 * @return
	 */
	public int inserSendLog(String order_id, String good_id, long userId, String terminal_id, String code) {
		return goodsDao.inserSendLog(order_id, good_id, userId, terminal_id, code);
	}

	/**
	 * 验证码补发，码未使用且未过期
	 * 
	 * @param code
	 * @param moblie
	 */
	public String resendCode(String code, String mobile, String order_id, String good_id, String end_time) {
		return goodsDao.resendCode(code, mobile, order_id, good_id, end_time);
	}

	/**
	 * 更新补发次数
	 * 
	 * @param order_id
	 * @return
	 */
	public int updateSendLog(String order_id, String remark, String code) {
		return goodsDao.updateSendLog(order_id, remark, code);
	}

	/**
	 * 改造后的我购买的商品订单方法
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param flag
	 * @param isdelivery
	 * @return
	 */
	public List<TActOrderNew> getNewOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status,
	        String flag, String isdelivery,Long[] orderTypes) {
		if(ArrayUtil.isEmpty(orderTypes)){
			orderTypes=ArrayUtil.toLongArr(OrderConstant.COMMON_PRODUCT);
		}
		return repository.getNewOrders(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery,orderTypes);
	}

	/**
	 * 记录用户中心向导记录，第一次登陆插入记录
	 * @param userId
	 * @return
	 */
	public String logUserCenterVisit(Long userId) {
		TUserCenterVisitLog visitLog = repository.getUserCenterVisitLog(userId);
		logger.info("------------------visitLog info "+visitLog);
		if (visitLog != null && "1".equals(StringUtil.getString(visitLog.getStatus()))) {
			return "visited";
		} else { 
			logger.info("userVisitlog is empty,and userId=" + userId);
			visitLog = new TUserCenterVisitLog();
			visitLog.setUserId(userId);
			visitLog.setStatus(1);
			visitLog.setVisitTime(CTime.getTime(14));
			repository.logUserCenterVisit(visitLog);
			return null;
		}
	}
	/**
	 * 改造后的我购买的商品的分页方法
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param flag
	 * @param isdelivery
	 * @return
	 */
	public String getNewOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery,Long[] orderTypes) {
		if(ArrayUtil.isEmpty(orderTypes)){
			orderTypes=ArrayUtil.toLongArr(OrderConstant.COMMON_PRODUCT);
		}
		return repository.getNewOrdersScript(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery,orderTypes);
	}

	/**
	 * 获取代金券订单的验证码列表
	 * 
	 * @param orderId
	 * @return
	 */
	public List<String[]> getVerifyCodeDetail(String orderId) {
		return goodsDao.getVerifyCodeDetail(orderId);

	}

	/**
	 * 判断o2o商品是否由第三方平台提供验证码
	 * 
	 * @param goodId
	 * @return
	 */
	public boolean isTpVerifxyCodeGood(String goodId) {
		return goodsDao.isTpVerifxyCodeGood(goodId);
	}

	/**
	 * 查询出商品的参数属性
	 * 
	 * @param goodsId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getGoodsParam(long goodsId) throws SQLException {
		return repository.findGoodsParam(goodsId);
	}

	/**
	 * 查询单个商品的详细 属性
	 * 
	 * @param goodsId
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getGoodsDetail(long goodsId) throws SQLException {
		return repository.findGoodsDetail(goodsId);
	}

	/**
	 * 获取我的中奖信息
	 * 
	 * @param pageInfo
	 * @return
	 * @throws ParseException
	 *             日期转换异常
	 */
	public List<ItemLottery> getLotteryList(PageInfo pageInfo, String userTel) throws ParseException {
		// 日期格式控制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 根据页面,手机号分页查询结果
		List<Object[]> list = repository.getLotteryList(pageInfo, userTel);
		List<ItemLottery> results = new ArrayList<ItemLottery>();
		// 遍历查询结果,对查询结果进行封装
		for (Object[] obj : list) {
			ItemLottery il = new ItemLottery();
			il.setActiveName(StringUtil.getString(obj[0]));
			// 设置日期为空的时候默认日期格式,避免""带来的日期转换异常
			il.setHitTime(sdf.parse(String.valueOf(obj[1]).equals("null") ? "1970-01-01 00:00:00" : String.valueOf(obj[1])));
			il.setLotteryInfo(StringUtil.getString(obj[2]));
			results.add(il);
		}
		return results;
	}

	/**
	 * 获取我的中奖信息总页数
	 * 
	 * @param pageInfo
	 * @param userTel
	 * @return
	 */
	public String getLotteryScript(PageInfo pageInfo, String userTel) {
		return repository.getLotteryScript(pageInfo, userTel);
	}

	/**
	 * 跳转操作页面
	 * 
	 * @param menucode
	 * @return
	 */
	public String getBackUrl(String menucode) {
		String backUrl = "redirect:./orderManager.chtml";
		if (StringUtils.isNotBlank(menucode)) {
			if (MenuCodeUtil.MENU_CODE_ORDER_MANAGER.equals(menucode)) {
				backUrl = "redirect:./orderManager.chtml";
			} else if (MenuCodeUtil.MENU_CODE_ORDER_WELFARE.equals(menucode)) {
				backUrl = "redirect:./redPackage.chtml";
			}
		}
		return backUrl;
	}

	/**
	 * 判断当前订单是否是限时抢购订单
	 * 
	 * @param item
	 * @return
	 */
	public boolean checkIsPromptOrder(Integer orderType) {
		if (Constants.ORDER_TYPE_PROMPT.equals(orderType)) {
			return true;
		}
		return false;
	}

	/**
	 * 计算限时抢购商品市场价
	 * 
	 * @param marketPriceAount
	 * @param good
	 * @param itemSaleDataDTO
	 * @return
	 */
	public BigDecimal getMarketPriceAmount(BigDecimal marketPriceAount, ActOrderGoodsInfo good, ItemSaleDataDTO itemSaleDataDTO) {
		return marketPriceAount.add(new BigDecimal(good.getCount()).multiply(itemSaleDataDTO.getItem().getMarketPrice())
		        .multiply(new BigDecimal(100)));
	}

}
