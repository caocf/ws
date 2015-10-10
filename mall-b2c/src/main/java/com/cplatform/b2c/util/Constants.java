package com.cplatform.b2c.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: chenke Date: 13-11-25 Time: 上午11:14
 */
public class Constants {

	public static final int ORDER_TYPE_LASHOU = 20; // 拉手

	public static final int ORDER_TYPE_MTICKET = 40; // 电影票

	public static final int ORDER_TYPE_SPIKE = 2; // 秒杀商品

	public static final int ORDER_TYPE_AUCTION = 1; // 竞拍商品

	public static final int ORDER_TYPE_WELFARE = 10; // 劳保商品订单类型

	// public static final int ORDER_TYPE_SPECIAL = 30; // 特价

	// 演出票永乐30，商城31
	public static final int ORDER_TYPE_PERFORM_YONGLE = 30;

	public static final int ORDER_TYPE_PERFORM_STORE = 31;

	public static final int ORDER_TYPE_COUPON = 60; // 代金券

	public static final int ORDER_TYPE_INTEGRAL = 50; // 河南积分兑换商品

	public static final int ORDER_TYPE_ECOUPON = 51; // 优惠券

	public static final Integer ORDER_TYPE_PROMPT = 90; // 限时抢购

	public static final Integer ORDER_TYPE_GPRS = 100; // 流量卡

	public static final String RED_USER_LEVELS = "L1"; // 红钻会员

	public static final String UNION_USER_LEVELS = "L2"; // 商盟会员

	public static final int RED_MEMBER = 1;

	public static final int STATUS_OK = 0;

	/**
	 * 业务类型 20-短信购
	 */
	public static final int SMS_BUY = 20;

	// 商品类别
	public static final String ITEM_SOURCE_DEFAULT = "0"; // 默认：商城

	public static final String ITEM_SOURCE_LASHOU = "1"; // 拉手商品

	public static final String ITEM_SOURCE_MTICKET = "1000000040"; // 电影票

	public static final String ITEM_SOURCE_YTICKET_YL = "1000000061"; // 演出票（永乐）

	public static final String ITEM_SOURCE_YTICKET_NYL = "1000000080"; // 演出票(非永乐)

	public static final String ITEM_SOURCE_HN_INTEGRAL = "4"; // 河南积分兑换商品

	public static final String ITEM_SOURCE_ECOUPON = "5"; // 优惠券

	public static final String COUPON_BUYING = "5";// 代金券

	public static final String PANIC_BUYING = "3";

	public static final String LABOUR_BUYING = "6"; // 劳保商品

	public static final String PROMPT_BUYING = "9"; // 限时抢购

	/** 虚拟商品商品分类父节点ID */
	public static final String VIRTUAL_GOODS_TYPE_PID = "100001";

	/** 代金券商品价格区间 */
	public static final Map<String, String> PRICE_MAP = new LinkedHashMap<String, String>();

	public static final Map<String, String> REVERSE_PRICE_MAP = new LinkedHashMap<String, String>();
	static {
		PRICE_MAP.put("1", "20元以下"); // 20元以下
		PRICE_MAP.put("2", "20-50元"); // 20-50元
		PRICE_MAP.put("3", "50-100元"); // 50-100元
		PRICE_MAP.put("4", "100-300元"); // 100-300元
		PRICE_MAP.put("5", "300元以上"); // 300以上

		REVERSE_PRICE_MAP.put("1", "[0 TO 20]");
		REVERSE_PRICE_MAP.put("2", "[20 TO 50]");
		REVERSE_PRICE_MAP.put("3", "[50 TO 100]");
		REVERSE_PRICE_MAP.put("4", "[100 TO 300]");
		REVERSE_PRICE_MAP.put("5", "[300 TO *]");
	}

	/** 河南用户 **/
	public static final String PROV_HENAN_REGION_CODE = "410000";

	public static final String REGION_LEVELS_ONE = "1";

	public static final String HENAN_INDEX_URL = "http://www.12580life.com/channel/";

	/** 积分价和现金价（或商城币）转换比率 */
	public static final double SCORE_PRICE_RATE = 0.015;

	/** 请求类别 */
	// 我的积分兑换商品
	public static final String HN_ACTION_INTEGRAL = "INTEGRAL";

	public static final String HN_PAGE_TITLE_INTEGRAL = "我的积分兑换商品";

	// 我的优惠券
	public static final String HN_ACTION_COUPON = "COUPON";

	public static final String HN_PAGE_TITLE_COUPON = "我的优惠券";

	/** 申请退款 */
	public static final String APPLY_REFUND_NO_ORDER = "没有该订单";

	public static final String APPLY_REFUND_NOT_USER = "您提交的订单有误";

	public static final String APPLY_REFUND_ALREADY = "每个订单只能申请退款一次";

	/** 判断是否需要发起退款申请 */
	// 可以发起退款申请
	public static final Integer ALLOW_REFUND = 1;

	// 不要发起退款申请，未付款
	public static final Integer UNALLOW_REFUND_UNPAY = 0;

	// 不要发起退款申请，已有该操作
	public static final Integer UNALLOW_REFUND_HAVA = 2;

	/** 订单创建来源 */
	public static final Integer ORDER_CREATE_SOURCE_WEB = 1;// 网站

	public static final Integer ORDER_CREATE_SOURCE_WAP = 2;// WAP

	public static final Integer ORDER_CREATE_SOURCE_KHD = 3;// 客户端

	public static final Integer ORDER_CREATE_SOURCE_NOTE = 4;// 短信

	public static final Integer ORDER_CREATE_SOURCE_INTERFACE = 5;// 外部接口

	public static final Integer ORDER_CREATE_SOURCE_OTHER = 0;// 其他

	/** 个人中心个人资料修改 */
	// 可上传头像
	public static final String USER_CENTER_INFO_SUCCESS = "0";

	// 文件的格式不对(不是.gif或.jpg文件)"
	public static final String USER_CENTER_INFO_FAIL_SUFFIX = "1";

	// 上传图片超过2M
	public static final String USER_CENTER_INFO_FAIL_SIZE = "2";

	// 没有上传图片
	public static final String USER_CENTER_INFO_FAIL_NOPHOTO = "3";

	// 操作报错
	public static final String USER_CENTER_INFO_FAIL_ERROR = "4";

	// 没能取到用户的信息
	public static final String USER_CENTER_INFO_FAIL_NOTLOGIN = "5";

	// 修改个人中心信息昵称重复
	public static final String USER_CENTER_INFO_FAIL_NICKNAME = "6";

	// 修改个人中心信息成功
	public static final String USER_CENTER_INFO_UPDATE_SUCCESS = "SUC";

	// 修改个人中心信息失败
	public static final String USER_CENTER_INFO_UPDATE_FAIL = "FA";

	// 绑定邮箱
	public static final String SESSION_CENTER_USER_BIND_EMAIL_KEY = "SESSION_CENTER_USER_BIND_EMAIL_KEY";

	// 绑定手机号
	public static final String SESSION_CENTER_USER_BIND_MOBLIE_KEY = "SESSION_CENTER_USER_BIND_MOBLIE_KEY";

	// 修改手机号_第一步
	public static final String SESSION_CENTER_USER_EDIT_MOBLIE_STEP1_KEY = "SESSION_CENTER_USER_EDIT_MOBLIE_STEP1_KEY";

	// 修改手机号_第二步
	public static final String SESSION_CENTER_USER_EDIT_MOBLIE_STEP2_KEY = "SESSION_CENTER_USER_EDIT_MOBLIE_STEP2_KEY";

	/** 验证商品对应会员购买验证 */
	// 没有userLevels
	public static final String VIP_ITEM_SALE_NO_USER_LEVELS = "NO_USER_LEVELS";

	// 没有对应的Levels
	public static final String VIP_NO_FIND_LEVELS = "NO_FIND_LEVELS";

	// 商品没有限制
	public static final String VIP_NO_LIMIT_LEVELS = "NO_LIMIT_LEVELS";

	/** 验证商品是否竞拍商品 */
	// 接口查询为空值
	public static final String AUCTION_RETURN_NULL = "RETURN_NULL";

	// 接口为空时，提醒信息
	public static final String AUCTION_RETURN_NULL_MESSAGE = "很抱歉，竞拍/秒杀订单生成失败，请重新下单或联系客服";

	/** 个人中心 */
	// 我购买的商品页面错误信息提示语
	public static final String CENTER_BUY_ERROR_MESSAGE = "订单查询失败，请稍后再试";

	// 没有绑定手机号提示
	public static final String CENTER_NO_MOBILE_ERROR_MESSAGE = "未绑定移动手机号码，请前往“个人中心>基本资料”进行绑定";
}
