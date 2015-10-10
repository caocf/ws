package com.cplatform.b2c.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-28 下午2:06
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Configuration
public class AppConfig {

	@Value("${interface.iteminfo}")
	private String interfaceItemInfo;

	@Value("${interface.root}")
	private String interfaceRoot;

	@Value("${pay.channel:demo}")
	private String payChannel;

	@Value("${interface.groupinfo}")
	private String interfaceGroupInfo;

	@Value("${interface.groupItems}")
	private String interfaceGroupItems;

	@Value("${interface.coininfo}")
	private String interfaceCoinInfo;

	@Value("${test:true}")
	private boolean test;

	@Value("${Server.Host}")
	private String server_host;

	@Value("${template.pay.vcode:本次支付验证码： %s，请在5分钟以内使用。如有任何问题，请及时联系商城客服4001511511。}")
	private String templatePayVcode;

	@Value("${template.order.ok:尊敬的%s，您在移动商城的订单：%s，已经提交成功。如有任何问题，请及时联系商城客服4001511511。}")
	private String templateOrderOk;

	@Value("${jms.sms.destination:q_sms_mt_act_1}")
	private String jmsSmsDestination;

	@Value("${jms.sms.spcode:10658618}")
	private String jmsSmsSpcode;

	@Value("${lottery.order.url}")
	private String lotteryOrderUrl;

	@Value("${lottery.order.detail.url}")
	private String lotteryOrderDetailUrl;

	@Value("${lottery.order.src}")
	private String lotteryOrderSrc;

	@Value("${auction.order.url}")
	private String auctionOrderurl;

	@Value("${tips.order.pay}")
	private String tipsOrderPay;

	@Value("${special.good.id}")
	private String specialGoodId;

	@Value("${mall.sapi.url}")
	private String spaiUrl;

	@Value("${gift.card.query}")
	private String giftCardQuery;

	@Value("${gift.card.verify}")
	private String giftCardVerify;

	@Value("${gift.card.pay}")
	private String giftCardPay;

	@Value("${interface.balanceInfo}")
	private String balanceInfo;

	@Value("${interface.bill.addr}")
	private String wayBillAddr;

	@Value("${item.salenum.default}")
	private int saleNumDefault;

	@Value("${api.order.create:}")
	private String createOrderUri;

	@Value("${api.order.pay}")
	private String payOrderUri;

	@Value("${union.member.url}")
	private String unionMemberUri;

	@Value("${welfare.query.url}")
	private String welfareQueryUri;

	@Value("${welfare.goods.id}")
	private Long welfareGoodsId;

	@Value("${welfare.admin.mobile}")
	private String welfareAdminMobile;

	@Value("${lashou.query.url}")
	private String lashouQueryUrl;

	@Value("${api.item.price}")
	private String finalPriceUrl;

	@Value("${market.backOrder.url}")
	private String backOrderUrl;

	@Value("${perform.expire.time}")
	private String performExpireTime;

	@Value("${perform.interface.url}")
	private String performInterfaceUrl;

	@Value("${henan_refund_code_url}")
	private String henanRefundCodeUrl;

	@Value("${resendAddress:}")
	private String resendAddress;

	@Value("${USER.PHOTO.FILE.DIR}")
	private String photoDir;

	@Value("${USER.LITTER.PHOTO.FILE.DIR}")
	private String photoDirLittle;

	@Value("${GOOD.QUICK.PHOTO}")
	private String quickPhotoURL;

	@Value("${www.header.url}")
	private String headerUrl;

	public String getHeaderUrl() {
		return headerUrl;
	}

	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}

	public String getPhotoDirLittle() {
		return photoDirLittle;
	}

	public String getPhotoDir() {
		return photoDir;
	}

	public String getResendAddress() {
		return resendAddress;
	}

	public void setResendAddress(String resendAddress) {
		this.resendAddress = resendAddress;
	}

	public String getBackOrderUrl() {
		return backOrderUrl;
	}

	public String getFinalPriceUrl() {
		return finalPriceUrl;
	}

	public String getLashouQueryUrl() {
		return lashouQueryUrl;
	}

	public String getWelfareAdminMobile() {
		return welfareAdminMobile;
	}

	public Long getWelfareGoodsId() {
		return welfareGoodsId;
	}

	public String getWelfareQueryUri() {
		return welfareQueryUri;
	}

	public String getUnionMemberUri() {
		return unionMemberUri;
	}

	public String getCreateOrderUri() {
		return createOrderUri;
	}

	public String getPayOrderUri() {
		return payOrderUri;
	}

	public int getSaleNumDefault() {
		return saleNumDefault;
	}

	public String getWayBillAddr() {
		return wayBillAddr;
	}

	public void setWayBillAddr(String wayBillAddr) {
		this.wayBillAddr = wayBillAddr;
	}

	public String getBalanceInfo() {
		return balanceInfo;
	}

	public String getGiftCardQuery() {
		return giftCardQuery;
	}

	public String getGiftCardVerify() {
		return giftCardVerify;
	}

	public String getGiftCardPay() {
		return giftCardPay;
	}

	public String getSpaiUrl() {
		return spaiUrl;
	}

	public String getTipsOrderPay() {
		return tipsOrderPay;
	}

	public String getAuctionOrderurl() {
		return auctionOrderurl;
	}

	public String getJmsSmsSpcode() {
		return jmsSmsSpcode;
	}

	public String getJmsSmsDestination() {
		return jmsSmsDestination;
	}

	public String getTemplateOrderOk() {
		return templateOrderOk;
	}

	public String getTemplatePayVcode() {
		return templatePayVcode;
	}

	public boolean isTest() {
		return test;
	}

	public String getInterfaceCoinInfo() {
		return interfaceCoinInfo;
	}

	public String getInterfaceGroupItems() {
		return interfaceGroupItems;
	}

	/*
	 * B2c 购物街静态页面相对于应用的物理路径保存的 路径
	 */
	@Value("${B2c.Index.RSPath}")
	private String B2c_Index_RSPath;

	/*
	 * B2c 购物街静态页面模板相对于应用的物理路径存放的 路径
	 */
	@Value("${B2c.Index.RFPath}")
	private String B2c_Index_RFPath;

	/*
	 * B2c 购物街静态页面生成信任ip
	 */
	@Value("${B2c.Index.TIP}")
	private String B2c_Index_TIP;

	@Value("${B2c.Index.BASE_SORTID}")
	private String B2c_Index_BASE_SORTID;

	@Value("${B2c.Item.SaveRootPath}")
	private String B2c_Item_SaveRootPath;

	@Value("${B2c.Item.Notice.Path}")
	private String B2c_Item_Notice_Path;

	@Value("${B2c.Filter.Login.Path}")
	private String B2c_Filter_Login_Path;

	@Value("${B2c.Index.LOGIN_URL}")
	private String B2c_Index_Login_Url;

	@Value("${B2c.Index.HN_LOGIN_URL}")
	private String B2c_Index_HN_Login_Url;

	@Value("${B2c.Store.Logo.Path}")
	private String B2c_Store_Logo_Path;

	@Value("${B2c.Store.Shelf.Path}")
	private String B2c_Store_Shelf_Path;

	@Value("${B2c.Channel.Pic.Path}")
	private String B2c_Channel_Pic_Path;

	@Value("${B2c.Web.Port}")
	private String B2c_Web_Port;

	@Value("${B2c.ad.path}")
	private String B2c_ad_path;

	/*
	 * 应用web根
	 */
	@Value("${WebApp.Root}")
	private String webRoot;

	@Value("${Image.Server.Host}")
	private String imgSvrHost;

	@Value("${Search_Http_Url}")
	private String Search_Http_Url;

	@Value("${Menu_Type_Search_Url}")
	private String Menu_Type_Search_Url;

	@Value("${exchange_points.exchange_url}")
	private String Exchange_Points_Url;

	@Value("${exchange_points.query_url}")
	private String Exchange_Query_Url;

	@Value("${exchange_points.manager_timeout}")
	private String Exchange_Manager_Timeout;

	@Value("${exchange_points.so_timeout}")
	private String Exchange_So_Timeout;

	@Value("${exchange_points.exchange_percent}")
	private Integer Exchange_Percent;

	@Value("${active_prize_url}")
	private String Active_Prize;

	@Value("${upload.lottery.path}")
	private String Upload_lotty;

	@Value("${sms.create.order}")
	private String smsCreateOrder;

	@Value("${sms.pay.coin}")
	private String smsPayCoin;

	@Value("${sms.order.success}")
	private String smsOrderSuccess;

	@Value("${sms.receipt.confirm}")
	private String smsReceiptConfirm;

	@Value("${sms.bind.mobile}")
	private String smsBindMobile;

	@Value("${order.type.common_time}")
	private int orderCommonTime;

	@Value("${order.type.spike_time}")
	private int orderSpikeTime;

	@Value(("${order.type.auction_time}"))
	private int orderAuctionTime;

	@Value("${auction.seckill.order_url}")
	private String gCheapOrderUrl;

	@Value("${gcheap.deposit_url}")
	private String gCheapDepositUrl;

	@Value("${gcheap.auction_outtime}")
	private int gCheapAuctionTime;

	@Value("${gcheap.seckill_outtime}")
	private int gCheapSeckillTime;

	@Value("${card.check.url}")
	private String cardCheckUrl;

	@Value("${UMP.queryOrderList.url}")
	private String umpQueryOrderListUrl;

	@Value("${UMP.queryOrder.url}")
	private String umpQueryOrderUrl;

	@Value("${UMP.resendOrder.url}")
	private String umpResendOrderUrl;

	@Value("${UMP.channelId}")
	private String umpChannelId;

	@Value("${UMP.portalId}")
	private String umpPortalId;

	@Value("${UMP.secretKey}")
	private String umpSecretKey;

	@Value("${schema.777}")
	private String schema777;

	@Value("${query.goods.solr.url}")
	private String queryGoodsSolrUrl;

	@Value("${shlm.voucher.server.host}")
	private String shlmVoucheSrvHost;

	@Value("${shlm.voucher.img.webpath}")
	private String shlmVoucherImgWebpath;

	@Value("${red_package_merchid}")
	private String redPackageMerchid;

	@Value("${red_package_repsonse_type}")
	private String redPackageRepsonseType;

	@Value("${recommend.interface.url}")
	private String recommendInterfaceUrl;

	@Value("${sms.exchange.score}")
	private String smsExchangeScore;

	@Value("${interface.goods.logisticsFee}")
	private String logisticsFee;

	@Value("${djq.homepage.warn.message}")
	private String djqSmsMessage;

	@Value("${djq.homepage.warn.message.mobilelist}")
	private String djqSmsMobileList;

	public String getUmpQueryOrderListUrl() {
		return umpQueryOrderListUrl;
	}

	public String getUmpQueryOrderUrl() {
		return umpQueryOrderUrl;
	}

	public String getUmpResendOrderUrl() {
		return umpResendOrderUrl;
	}

	public String getUmpChannelId() {
		return umpChannelId;
	}

	public String getUmpPortalId() {
		return umpPortalId;
	}

	public String getUmpSecretKey() {
		return umpSecretKey;
	}

	public String getSchema777() {
		return schema777;
	}

	public int getOrderCommonTime() {
		return orderCommonTime;
	}

	public int getOrderSpikeTime() {
		return orderSpikeTime;
	}

	public int getOrderAuctionTime() {
		return orderAuctionTime;
	}

	public String getImgSvrHost() {
		return imgSvrHost;
	}

	public void setImgSvrHost(String imgSvrHost) {
		this.imgSvrHost = imgSvrHost;
	}

	public String getInterfaceRoot() {
		return interfaceRoot;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getB2c_Item_Notice_Path() {
		return B2c_Item_Notice_Path;
	}

	public void setB2c_Item_Notice_Path(String b2c_Item_Notice_Path) {
		B2c_Item_Notice_Path = b2c_Item_Notice_Path;
	}

	public String getB2c_Item_SaveRootPath() {
		return B2c_Item_SaveRootPath;
	}

	public void setB2c_Item_SaveRootPath(String b2c_Item_SaveRootPath) {
		B2c_Item_SaveRootPath = b2c_Item_SaveRootPath;
	}

	public String getInterfaceItemInfo() {
		return interfaceItemInfo;
	}

	public String getWebRoot() {
		return webRoot;
	}

	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}

	public String getB2c_Index_RSPath() {
		return B2c_Index_RSPath;
	}

	public void setB2c_Index_RSPath(String b2c_Index_RSPath) {
		B2c_Index_RSPath = b2c_Index_RSPath;
	}

	public String getB2c_Index_RFPath() {
		return B2c_Index_RFPath;
	}

	public void setB2c_Index_RFPath(String b2c_Index_RFPath) {
		B2c_Index_RFPath = b2c_Index_RFPath;
	}

	public String getB2c_Index_TIP() {
		return B2c_Index_TIP;
	}

	public void setB2c_Index_TIP(String b2c_Index_TIP) {
		B2c_Index_TIP = b2c_Index_TIP;
	}

	public String getB2c_Index_BASE_SORTID() {
		return B2c_Index_BASE_SORTID;
	}

	public void setB2c_Index_BASE_SORTID(String b2cIndexBASESORTID) {
		B2c_Index_BASE_SORTID = b2cIndexBASESORTID;
	}

	public String getInterfaceGroupInfo() {
		return interfaceGroupInfo;
	}

	public String getB2c_Filter_Login_Path() {
		return B2c_Filter_Login_Path;
	}

	public void setB2c_Filter_Login_Path(String b2cFilterLoginPath) {
		B2c_Filter_Login_Path = b2cFilterLoginPath;
	}

	public String getB2c_Index_Login_Url() {
		return B2c_Index_Login_Url;
	}

	public void setB2c_Index_Login_Url(String b2cIndexLoginUrl) {
		B2c_Index_Login_Url = b2cIndexLoginUrl;
	}

	public String getSearch_Http_Url() {
		return Search_Http_Url;
	}

	public void setSearch_Http_Url(String search_Http_Url) {
		Search_Http_Url = search_Http_Url;
	}

	public String getB2c_Store_Logo_Path() {
		return B2c_Store_Logo_Path;
	}

	public void setB2c_Store_Logo_Path(String b2cStoreLogoPath) {
		B2c_Store_Logo_Path = b2cStoreLogoPath;
	}

	public String getB2c_Store_Shelf_Path() {
		return B2c_Store_Shelf_Path;
	}

	public void setB2c_Store_Shelf_Path(String b2cStoreShelfPath) {
		B2c_Store_Shelf_Path = b2cStoreShelfPath;
	}

	public String getMenu_Type_Search_Url() {
		return Menu_Type_Search_Url;
	}

	public void setMenu_Type_Search_Url(String menu_Type_Search_Url) {
		Menu_Type_Search_Url = menu_Type_Search_Url;
	}

	public String getB2c_Channel_Pic_Path() {
		return B2c_Channel_Pic_Path;
	}

	public void setB2c_Channel_Pic_Path(String b2cChannelPicPath) {
		B2c_Channel_Pic_Path = b2cChannelPicPath;
	}

	public String getB2c_Web_Port() {
		return B2c_Web_Port;
	}

	public void setB2c_Web_Port(String b2cWebPort) {
		B2c_Web_Port = b2cWebPort;
	}

	public String getExchange_Points_Url() {
		return Exchange_Points_Url;
	}

	public void setExchange_Points_Url(String exchange_Points_Url) {
		Exchange_Points_Url = exchange_Points_Url;
	}

	public String getExchange_Query_Url() {
		return Exchange_Query_Url;
	}

	public void setExchange_Query_Url(String exchange_Query_Url) {
		Exchange_Query_Url = exchange_Query_Url;
	}

	public String getExchange_Manager_Timeout() {
		return Exchange_Manager_Timeout;
	}

	public void setExchange_Manager_Timeout(String exchange_Manager_Timeout) {
		Exchange_Manager_Timeout = exchange_Manager_Timeout;
	}

	public String getExchange_So_Timeout() {
		return Exchange_So_Timeout;
	}

	public void setExchange_So_Timeout(String exchange_So_Timeout) {
		Exchange_So_Timeout = exchange_So_Timeout;
	}

	public Integer getExchange_Percent() {
		return Exchange_Percent;
	}

	public void setExchange_Percent(Integer exchange_Percent) {
		Exchange_Percent = exchange_Percent;
	}

	public String getB2c_ad_path() {
		return B2c_ad_path;
	}

	public void setB2c_ad_path(String b2c_ad_path) {
		B2c_ad_path = b2c_ad_path;
	}

	public String getActive_Prize() {
		return Active_Prize;
	}

	public void setActive_Prize(String active_Prize) {
		Active_Prize = active_Prize;
	}

	public String getServer_host() {
		return server_host;
	}

	public void setServer_host(String server_host) {
		this.server_host = server_host;
	}

	public String getUpload_lotty() {
		return Upload_lotty;
	}

	public void setUpload_lotty(String upload_lotty) {
		Upload_lotty = upload_lotty;
	}

	public String getSmsCreateOrder() {
		return smsCreateOrder;
	}

	public void setSmsCreateOrder(String smsCreateOrder) {
		this.smsCreateOrder = smsCreateOrder;
	}

	public String getSmsPayCoin() {
		return smsPayCoin;
	}

	public void setSmsPayCoin(String smsPayCoin) {
		this.smsPayCoin = smsPayCoin;
	}

	public String getSmsOrderSuccess() {
		return smsOrderSuccess;
	}

	public void setSmsOrderSuccess(String smsOrderSuccess) {
		this.smsOrderSuccess = smsOrderSuccess;
	}

	public String getSmsReceiptConfirm() {
		return smsReceiptConfirm;
	}

	public void setSmsReceiptConfirm(String smsReceiptConfirm) {
		this.smsReceiptConfirm = smsReceiptConfirm;
	}

	public String getgCheapOrderUrl() {
		return gCheapOrderUrl;
	}

	public void setgCheapOrderUrl(String gCheapOrderUrl) {
		this.gCheapOrderUrl = gCheapOrderUrl;
	}

	public void setgCheapDepositUrl(String gCheapDepositUrl) {
		this.gCheapDepositUrl = gCheapDepositUrl;
	}

	public String getgCheapDepositUrl() {
		return gCheapDepositUrl;
	}

	public String getLotteryOrderUrl() {
		return lotteryOrderUrl;
	}

	public void setLotteryOrderUrl(String lotteryOrderUrl) {
		this.lotteryOrderUrl = lotteryOrderUrl;
	}

	public String getLotteryOrderSrc() {
		return lotteryOrderSrc;
	}

	public void setLotteryOrderSrc(String lotteryOrderSrc) {
		this.lotteryOrderSrc = lotteryOrderSrc;
	}

	public int getgCheapAuctionTime() {
		return gCheapAuctionTime;
	}

	public void setgCheapAuctionTime(int gCheapAuctionTime) {
		this.gCheapAuctionTime = gCheapAuctionTime;
	}

	public int getgCheapSeckillTime() {
		return gCheapSeckillTime;
	}

	public void setgCheapSeckillTime(int gCheapSeckillTime) {
		this.gCheapSeckillTime = gCheapSeckillTime;
	}

	public String getSmsBindMobile() {
		return smsBindMobile;
	}

	public void setSmsBindMobile(String smsBindMobile) {
		this.smsBindMobile = smsBindMobile;
	}

	public String getSpecialGoodId() {
		return specialGoodId;
	}

	public void setSpecialGoodId(String specialGoodId) {
		this.specialGoodId = specialGoodId;
	}

	public String getLotteryOrderDetailUrl() {
		return lotteryOrderDetailUrl;
	}

	public void setLotteryOrderDetailUrl(String lotteryOrderDetailUrl) {
		this.lotteryOrderDetailUrl = lotteryOrderDetailUrl;
	}

	public String getCardCheckUrl() {
		return cardCheckUrl;
	}

	public void setCardCheckUrl(String cardCheckUrl) {
		this.cardCheckUrl = cardCheckUrl;
	}

	public String getQueryGoodsSolrUrl() {
		return queryGoodsSolrUrl;
	}

	public void setQueryGoodsSolrUrl(String queryGoodsSolrUrl) {
		this.queryGoodsSolrUrl = queryGoodsSolrUrl;
	}

	public String getShlmVoucherSrvHost() {
		return shlmVoucheSrvHost;
	}

	public void setShlmVoucherSrvHost(String shlmVoucheSrvHost) {
		this.shlmVoucheSrvHost = shlmVoucheSrvHost;
	}

	public String getShlmVoucherImgWebpath() {
		return shlmVoucherImgWebpath;
	}

	public void setShlmVoucherImgWebpath(String shlmVoucherImgWebpath) {
		this.shlmVoucherImgWebpath = shlmVoucherImgWebpath;
	}

	public String getB2c_Index_HN_Login_Url() {
		return B2c_Index_HN_Login_Url;
	}

	public void setB2c_Index_HN_Login_Url(String b2c_Index_HN_Login_Url) {
		B2c_Index_HN_Login_Url = b2c_Index_HN_Login_Url;
	}

	public String getPerformExpireTime() {
		return performExpireTime;
	}

	public void setPerformExpireTime(String performExpireTime) {
		this.performExpireTime = performExpireTime;
	}

	public String getPerformInterfaceUrl() {
		return performInterfaceUrl;
	}

	public void setPerformInterfaceUrl(String performInterfaceUrl) {
		this.performInterfaceUrl = performInterfaceUrl;
	}

	public String getHenanRefundCodeUrl() {
		return henanRefundCodeUrl;
	}

	public void setHenanRefundCodeUrl(String henanRefundCodeUrl) {
		this.henanRefundCodeUrl = henanRefundCodeUrl;
	}

	@Value("${laobao_type}")
	private String laoBaoType;

	public String getLaoBaoType() {
		return laoBaoType;
	}

	public String getRedPackageMerchid() {
		return redPackageMerchid;
	}

	public void setRedPackageMerchid(String redPackageMerchid) {
		this.redPackageMerchid = redPackageMerchid;
	}

	public String getRedPackageRepsonseType() {
		return redPackageRepsonseType;
	}

	public void setRedPackageRepsonseType(String redPackageRepsonseType) {
		this.redPackageRepsonseType = redPackageRepsonseType;
	}

	public String getRecommendInterfaceUrl() {
		return recommendInterfaceUrl;
	}

	public void setRecommendInterfaceUrl(String recommendInterfaceUrl) {
		this.recommendInterfaceUrl = recommendInterfaceUrl;
	}

	public String getQuickPhotoURL() {
		return quickPhotoURL;
	}

	public void setQuickPhotoURL(String quickPhotoURL) {
		this.quickPhotoURL = quickPhotoURL;
	}

	public String getSmsExchangeScore() {
		return smsExchangeScore;
	}

	public void setSmsExchangeScore(String smsExchangeScore) {
		this.smsExchangeScore = smsExchangeScore;
	}

	public String getLogisticsFee() {
		return logisticsFee;
	}

	public String getDjqSmsMessage() {
		return djqSmsMessage;
	}

	public void setDjqSmsMessage(String djqSmsMessage) {
		this.djqSmsMessage = djqSmsMessage;
	}

	public String getDjqSmsMobileList() {
		return djqSmsMobileList;
	}

	public void setDjqSmsMobileList(String djqSmsMobileList) {
		this.djqSmsMobileList = djqSmsMobileList;
	}

}
