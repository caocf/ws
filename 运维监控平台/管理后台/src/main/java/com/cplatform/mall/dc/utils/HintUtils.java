package com.cplatform.mall.dc.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 提示信息工具类<br>
 * <p>
 * Copyright: Copyright (c) 2014-1-8 上午11:36:19
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
public class HintUtils {

	/**
	 * 获取提示信息列表
	 * 
	 * @param prefix
	 *            前缀
	 * @return 提示信息列表
	 */
	public static String[] getHint(String prefix) {
		if (StringUtils.isBlank(prefix)) {
			return null;
		}

		if (StringUtils.startsWith(prefix, "rb_")) {// 日报
			// 日报-定制会员
			if (StringUtils.equals(prefix, "rb_dzhy")) {
				String[] hints = { "", "定制时间为当天的定制会员数", "退订时间是当天的退订会员数", "当天累计商盟会员-前一天累计商盟会员", "当天累计免费商盟会员", "当天累计3元商盟会员", "当天累计5元商盟会员",
				        "当天累计10元商盟会员", "当天累计包年商盟会员", "当天累计商盟会员总数", "商城数据当日新增红钻会员", "商城数据当日退订红钻会员", "商城数据当日净增红钻会员", "商城数据当日累计红钻会员" };
				return hints;
			}

			// 日报-注册用户
			if (StringUtils.equals(prefix, "rb_zcyh")) {
				String[] hints = { "", "注册时间为当天的注册用户数", "截至当天注册用户总数" };
				return hints;
			}

			// 日报-交易情况
			if (StringUtils.equals(prefix, "rb_jyqk")) {
				String[] hints = { "", "包含积分、商城币、现金按1积分=0.015元", "", "商城割接过来的商品算做虚拟商品", "订单来源渠道为web的交易额", "订单来源渠道为wap的交易额", "订单来源渠道为客户端的交易额",
				        "订单来源渠道为短信购的交易额", "商城数据", "使用商城币支付的交易额", "使用现金支付的交易额", "使用积分/M值支付的交易额", "使用代金券支付的交易额", "使用红包支付的交易额", "当天发生交易用户数", "商城数据",
				        "当天交易订单数", "（当日订单数-信购订单数）/当日UV", "当日总交易额/交易用户数", "商超卡交易额占比", "现金交易额占比" };
				return hints;
			}

			// 日报-活跃用户商户
			if (StringUtils.equals(prefix, "rb_hyyhsh")) {
				String[] hints = {
				        "",
				        "当日使用电商业务用户数（主要有登录、会员验证、验证码验证、会员编号下载、购买商品、参与点评、发帖跟帖、下载/打印优惠券、安装并激活客户端、维护商户信息（含修改报错和上传图片、上传新商户信息）、订餐、网站各类信息查询、参与竞拍、秒杀、短信问答等营销活动用户（同一用户名去重统计）",
				        "当日使用过商盟产品的商户，以下行为视为使用：通过WEB/WAP/客户端/短信/C-BOX/pos及其他验证终端进行过会员身份、消费验证、发布或更新信息、发布团购、优惠券或O2O商品。" };
				return hints;
			}

			// 日报-终端验证
			if (StringUtils.equals(prefix, "rb_zdyz")) {
				String[] hints = { "", "身份验证人次", "折扣券验证人次", "U团验证人次", "累计POS终端数量" };
				return hints;
			}

			// 日报-客户端
			if (StringUtils.equals(prefix, "rb_khd")) {
				String[] hints = { "", "交易额", "当日登录为首次登录的用户数", "登录人数", "新增用户", "提交订单人数/登录人数", "支付成功人数/提交订单人数" };
				return hints;
			}

			// 日报-电商业务按日
			if (StringUtils.equals(prefix, "rb_dsywDaily")) {
				String[] hints = { "", "有效期内短信优惠券数量", "有效期内彩信优惠券数量", "当天短信优惠券下载量", "当天彩信优惠券下载量", "短信优惠券验证量", "彩信优惠券验证量", "订单数", "参与人数", "售出数量",
				        "销售总额", "团购数", "参与人数", "售出数量", "销售总额" };
				return hints;
			}

			// 日报-电商业务按周
			if (StringUtils.equals(prefix, "rb_dsywWeekly")) {
				String[] hints = { "", "订单数", "参与人数", "售出数量", "销售总额", "代金券数", "参与人数", "售出数量", "销售总额", "订单数", "参与人数", "售出数量", "销售总额", "订单数", "参与人数",
				        "售出数量", "销售总额", "订单数", "成功订单数", "成功参与人数", "成功参与商户数量", "订单数", "参与人数", "售出数量", "销售总额" };
				return hints;
			}

			// 日报-论坛
			if (StringUtils.equals(prefix, "rb_lt")) {
				String[] hints = { "", "地市本周发帖数，地市来自用户所属地市", "地市本周回帖数，地市来自用户所属地市", "本周有发帖或回帖的用户数" };
				return hints;
			}

			// 日报-商户商品数量
			if (StringUtils.equals(prefix, "rb_shspsl")) {
				String[] hints = { "", "本周新增商户数量", "本周新增折扣商户数量", "本周新增非折扣商户数量", "本周新增商户点评数量", "累计商户总量", "累计折扣商户总量", "累计非折后商户总量", "本周新增商品数", "累计商品数",
				        "实物商品商户" };
				return hints;
			}

			// 日报-短信群发
			if (StringUtils.equals(prefix, "rb_dxqf")) {
				String[] hints = { "", "1065858******平台下发的短信条数", "1065858******平台下发的彩信条数" };
				return hints;
			}

			// 日报-短信购
			if (StringUtils.equals(prefix, "rb_dxg")) {
				String[] hints = { "", "订单数", "交易额", "订单数", "交易额", "订单数", "交易额", "订单数", "交易额" };
				return hints;
			}

			// 日报-PVUV
			if (StringUtils.equals(prefix, "rb_pvuv")) {
				String[] hints = { "", "本周PV", "本周UV" };
				return hints;
			}
		}

		return null;
	}
}
