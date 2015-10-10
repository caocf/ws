package com.cplatform.mall.dc.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class DailyUtils {
	
	public static final String OPERATE_DZHY = "DZHY";
	public static final String OPERATE_ZCYH = "ZCYH";
	public static final String OPERATE_JYQK = "JYQK";
	public static final String OPERATE_HYYHSH = "HYYHSH";
	public static final String OPERATE_ZDYZ = "ZDYZ";
	public static final String OPERATE_KHD = "KHD";
	public static final String OPERATE_DSYW_DAY = "DSYW_DAY";
	public static final String OPERATE_DSYW_WEEK = "DSYW_WEEK";
	public static final String OPERATE_LT = "LT";
	public static final String OPERATE_SHSPSL = "SHSPSL";
	public static final String OPERATE_DXQF = "DXQF";
	public static final String OPERATE_DXG = "DXG";
	public static final String OPERATE_PVUV = "PVUV";
	
	// 定制会员表列名
	public static final String[] RB_DZHY_TITLE = {"日报表", "新增商盟会员", "退订商盟会员", "净增商盟会员", "免费版", "3元版", "5元版",
			"10元版", "包年", "累计", "新增红钻会员", "退订红钻会员", "净增红钻会员", "累计红钻会员" };
	
	public List<String> getRB_DZHY_SEC(){
		List<String> RB_DZHY_SEC  = new ArrayList<String>();
		RB_DZHY_SEC.add(0, "新增商盟会员_&&_1");
		RB_DZHY_SEC.add(1, "退订商盟会员_&&_1");
		RB_DZHY_SEC.add(2, "净增商盟会员_&&_1");
		RB_DZHY_SEC.add(3, "累计商盟会员_&&_6");
		RB_DZHY_SEC.add(4, "新增红钻会员_&&_1");
		RB_DZHY_SEC.add(5, "退订红钻会员_&&_1");
		RB_DZHY_SEC.add(6, "净增红钻会员_&&_1");
		RB_DZHY_SEC.add(7, "累计红钻会员_&&_1");
		return RB_DZHY_SEC;
	}
	
	public List<String> getRB_DZHY_FIR(){
		List<String> RB_DZHY_FIR = new ArrayList<String>();
		RB_DZHY_FIR.add(0, "商盟会员_&&_9");
		RB_DZHY_FIR.add(1, "红钻会员_&&_4");
		return RB_DZHY_FIR;
	}

	// 注册用户表列名
	public static final String[] RB_ZCYH_TITLE = {"日报表", "新增注册用户", "累计注册用户" };

	// 交易情况列名
	public static final String[] RB_JYQK_TITLE = {"日报表", "本日总交易额", "实物类商品交易", "虚拟类商品交易额", "web交易额", "wap交易额",
			"客户端交易额", "短信购交易额", "线下消费交易额", "商城币", "现金", "积分/M值", "代金券", "红包",
			"交易用户数", "商城币增加量", "订单数", "订单转化率", "客单价", "商超卡交易额占比", "现金交易额占比" };
	
	public List<String> getRB_JYQK_SEC(){
		List<String> RB_JYQK_SEC  = new ArrayList<String>();
		RB_JYQK_SEC.add(0, "本日总交易额_&&_1");
		RB_JYQK_SEC.add(1, "商品分类_&&_2");
		RB_JYQK_SEC.add(2, "渠道分类_&&_5");
		RB_JYQK_SEC.add(3, "支付方式_&&_5");
		RB_JYQK_SEC.add(4, "交易用户数_&&_1");
		RB_JYQK_SEC.add(5, "商城币增加量_&&_1");
		RB_JYQK_SEC.add(6, "订单数_&&_1");
		RB_JYQK_SEC.add(7, "订单转化率_&&_1");
		RB_JYQK_SEC.add(8, "客单价_&&_1");
		RB_JYQK_SEC.add(9, "商超卡交易额占比_&&_1");
		RB_JYQK_SEC.add(10, "现金交易额占比_&&_1");
		return RB_JYQK_SEC;
	}

	// 活跃用户商户列名
	public static final String[] RB_HYYHSH_TITLE = {"日报表", "活跃用户数", "活跃商户数" };

	// 终端验证列名
	public static final String[] RB_ZDYZ_TITLE = {"日报表", "身份验证人次", "折扣券验证人次", "U团验证人次", "累计POS终端数量" };

	// 客户端列名
	public static final String[] RB_KHD_TITLE = {"日报表", "交易额", "登录量", "登录人数", "新增用户", "订单转化率", "订单成功率" };

	// 电商业务_按日统计列名
	public static final String[] RB_DAY_TITLE = {"日报表", "短信优惠券数量", "彩信优惠券数量", "短信优惠券下载量", "彩信优惠券下载量",
			"短信优惠券验证量", "彩信优惠券验证量", "订单数", "参与人数", "售出数量", "销售总额", "团购数",
			"参与人数", "售出数量", "销售总额" };
	
	public List<String> getRB_DAY_SEC(){
		List<String> RB_DAY_SEC  = new ArrayList<String>();
		RB_DAY_SEC.add(0, "优惠券统计_&&_6");
		RB_DAY_SEC.add(1, "彩票统计_&&_4");
		RB_DAY_SEC.add(2, "团购统计_&&_4");
		return RB_DAY_SEC;
	}


	// 电商业务_按周统计列名
	public static final String[] RB_WEEK_TITLE = {"日报表", "订单数", "参与人数", "售出数量", "销售总额", "代金券数", "参与人数", "售出数量",
			"销售总额", "订单数", "参与人数", "售出数量", "销售总额", "订单数", "参与人数", "售出数量",
			"销售总额", "订单数", "成功订单数", "成功参与人数", "成功参与商户数量", "订单数", "参与人数",
			"售出数量", "销售总额" };
	
	public List<String> getRB_WEEK_SEC(){
		List<String> RB_WEEK_SEC  = new ArrayList<String>();
		RB_WEEK_SEC.add(0, "终端销售统计_&&_4");
		RB_WEEK_SEC.add(1, "代金券统计_&&_4");
		RB_WEEK_SEC.add(2, "电影票统计_&&_4");
		RB_WEEK_SEC.add(3, "汽车票统计_&&_4");
		RB_WEEK_SEC.add(4, "订餐统计_&&_4");
		RB_WEEK_SEC.add(5, "门票统计量_&&_4");
		return RB_WEEK_SEC;
	}

	// 论坛表列名
	public static final String[] RB_LT_TITLE = {"日报表", "本周发帖数", "本周回帖数", "活跃用户数" };

	// 商户商品数量列名
	public static final String[] RB_SHSPSL_TITLE = {"日报表", "本周新增商户数量", "本周新增折扣商户数量", "本周新增非折扣商户数量",
			"本周新增商户点评数量", "累计商户总量", "累计折扣商户总量", "累计非折后商户总量", "本周新增商品数",
			"累计商品数", "实物商品商户" };

	//短信群发列名
	public static final String[] RB_DXQF_TITLE = {"日报表", "本周短信发送条数", "本周彩信发送条数"};
	
	//短信购列名
	public static final String[] RB_DXG_TITLE = {"日报表", "订单数", "交易额", "订单数", "交易额", "订单数", "交易额", "订单数", "交易额"};

	public List<String> getRB_DXG_SEC(){
		List<String> RB_DXG_SEC  = new ArrayList<String>();
		RB_DXG_SEC.add(0, "实物购_&&_2");
		RB_DXG_SEC.add(1, "虚拟购_&&_2");
		RB_DXG_SEC.add(2, "实物购_&&_2");
		RB_DXG_SEC.add(3, "虚拟购_&&_2");
		return RB_DXG_SEC;
	}
	
	public List<String> getRB_DXG_FIR(){
		List<String> RB_DXG_FIR = new ArrayList<String>();
		RB_DXG_FIR.add(0, "商城币_&&_4");
		RB_DXG_FIR.add(1, "积分/M值_&&_4");
		return RB_DXG_FIR;
	}

	//PVUV列名
	public static final String[] RB_PVUV_TITLE = {"日报表", "本周PV", "本周UV"};

}
