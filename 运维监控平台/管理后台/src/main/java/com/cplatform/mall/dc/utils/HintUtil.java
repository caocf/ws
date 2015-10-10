package com.cplatform.mall.dc.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HintUtil {
	
	/** 经分报表  */
	@Value("${jf_ywsr_qx}")
	private String jfYwsrQx;
	
	@Value("${jf_ywsr_qx_syhbzz}")
	private String jfYwsrQxSyhbzz;
	
	@Value("${jf_ywsr_qx_bnljwcqk}")
	private String jfYwsrQxBnljwcqk;
	
	@Value("${jf_ywsr_hx}")
	private String jfYwsrHx;
	
	@Value("${jf_ywsr_hx_syhbzz}")
	private String jfYwsrHxSyhbzz;

	@Value("${jf_ywsr_hx_bnljwcqk}")
	private String jfYwsrHxBnljwcqk;
	
	@Value("${jf_dzhy_mfb_dyxz}")
	private String jfDzhyMfbDyxz;
	
	@Value("${jf_dzhy_mfb_dyjz}")
	private String jfDzhyMfbDyjz;
	
	@Value("${jf_dzhy_mfb_dydds}")
	private String jfDzhyMfbDydds;
	
	@Value("${jf_jyyh_xs}")
	private String jfJyyhXs;
	
	@Value("${jf_jyyh_xx}")
	private String jfJyyhXx;
	
	@Value("${jf_jyyh_hj}")
	private String jfJyyhHj;
	
	@Value("${jf_khd_azs}")
	private String jfKhdAzs;
	
	@Value("${jf_khd_hyyhs}")
	private String jfKhdHyyhs;
	
	@Value("${jf_dsjy_syhbzz}")
	private String jfDsjySyhbzz;
	
	@Value("${jf_dsjy_bnljwcqk}")
	private String jfDsjyBnljwcqk;
	
	@Value("${jf_hyyh_dy}")
	private String jfHyyhDy;
	
	@Value("${jf_hyyh_yj}")
	private String jfHyyhYj;
	
	/** 经分 */
	@Value("${internet_llyj_qd_dlfwcs}")
	private String internetLlyjQdDlfwcs;
	
	@Value("${internet_llyj_qd_dlfwyh}")
	private String internetLlyjQdDlfwyh;
	
	@Value("${internet_llyj_qd_hyyzcs}")
	private String internetLlyjQdHyyzcs;
	
	@Value("${internet_llyj_qd_ddbs}")
	private String internetLlyjQdDdbs;
	
	@Value("${internet_llyj_qd_cgzfs}")
	private String internetLlyjQdCgzfs;
	
	@Value("${internet_llyj_qd_xffhbs}")
	private String internetLlyjQdXffhbs;
	
	@Value("${internet_llyj_qd_jye}")
	private String internetLlyjQdJye;
	
	@Value("${internet_llyj_qd_zflx_scb}")
	private String internetLlyjQdZflxScb;
	
	@Value("${internet_llyj_qd_zflx_jf}")
	private String internetLlyjQdZflxJf;
	
	@Value("${internet_llyj_qd_zflx_xj}")
	private String internetLlyjQdZflxXj;
	
	@Value("${internet_ggyxzb_hdcyyh}")
	private String internetGgyxzbHdcyyh;
	
	@Value("${internet_ggyxzb_hycyyh}")
	private String internetGgyxzbHycyyh;
	
	@Value("${internet_ggyxzb_zcxzyh}")
	private String internetGgyxzbZcxzyh;
	
	@Value("${internet_khjzzb_yhs}")
	private String internetKhjzzbYhs;
	
	@Value("${internet_khjzzb_dkhkdj}")
	private String internetKhjzzbDkhkdj;
	
	@Value("${internet_khjzzb_1xfyhs}")
	private String internetKhjzzb1xfyhs;
	
	@Value("${internet_khjzzb_3xfyhs}")
	private String internetKhjzzb3xfyhs;
	
	@Value("${internet_khjzzb_6xfyhs}")
	private String internetKhjzzb6xfyhs;
	
	@Value("${internet_khjzzb_jye}")
	private String internetKhjzzbJye;
	
	@Value("${internet_nbjyzb_pv}")
	private String internetNbjyzbPv;
	
	@Value("${internet_nbjyzb_uv}")
	private String internetNbjyzbUv;
	
	@Value("${internet_nbjyzb_ddzhl}")
	private String internetNbjyzbDdzhl;
	
	@Value("${internet_nbjyzb_zfzhl}")
	private String internetNbjyzbZfzhl;
	
	@Value("${internet_nbjyzb_ddsl}")
	private String internetNbjyzbDdsl;
	
	@Value("${internet_nbjyzb_jye}")
	private String internetNbjyzbJye;
	
	@Value("${internet_nbjyzb_jyyhs}")
	private String internetNbjyzbJyyhs;
	
	
	@Value("${internet_nbjyzb_pjtcl}")
	private String internetNbjyzbPjtcl;
	
	@Value("${internet_nbjyzb_rjllyms}")
	private String internetNbjyzbRjllyms;
	
	@Value("${internet_nbjyzb_rjllysc}")
	private String internetNbjyzbRjllysc;
	
	/** 日报部分 */
	@Value("${rb_dzhy_xzsmhy}")
	private String rbDzhyXzsmhy;
	
	@Value("${rb_dzhy_tdsmhy}")
	private String rbDzhyTdsmhy;
	
	@Value("${rb_dzhy_jzsmhy}")
	private String rbDzhyJzsmhy;
	
	@Value("${rb_dzhy_mfb}")
	private String rbDzhyMfb;
	
	@Value("${rb_dzhy_3yb}")
	private String rbDzhy3yb;
	
	@Value("${rb_dzhy_5yb}")
	private String rbDzhy5yb;
	
	@Value("${rb_dzhy_10yb}")
	private String rbDzhy10yb;
	
	@Value("${rb_dzhy_bn}")
	private String rbDzhyBn;
	
	@Value("${rb_dzhy_lj}")
	private String rbDzhyLj;
	
	@Value("${rb_dzhy_xzhzhy}")
	private String rbDzhyXzhzhy;
	
	@Value("${rb_zcyh_xzzcyh}")
	private String rbZcyhXzzcyh;
	
	@Value("${rb_zcyh_ljzcyh}")
	private String rbZcyhLjzcyh;
	
	@Value("${rb_jyqk_brjyze}")
	private String rbJyqkBrjyze;
	
	@Value("${rb_jyqk_xnlspjye}")
	private String rbJyqkXnlspjye;
	
	@Value("${rb_jyqk_webjye}")
	private String rbJyqkWebjye;
	
	@Value("${rb_jyqk_wapjye}")
	private String rbJyqkWapjye;
	
	@Value("${rb_jyqk_khdjye}")
	private String rbJyqkKhdjye;
	
	@Value("${rb_jyqk_dxgjye}")
	private String rbJyqkDxgjye;
	
	@Value("${rb_jyqk_xxxfjye}")
	private String rbJyqkXxxfjye;
	
	@Value("${rb_jyqk_scb}")
	private String rbJyqkScb;
	
	@Value("${rb_jyqk_xj}")
	private String rbJyqkXj;
	
	@Value("${rb_jyqk_jf}")
	private String rbJyqkJf;
	
	@Value("${rb_jyqk_djq}")
	private String rbJyqkDjq;
	
	@Value("${rb_jyqk_hb}")
	private String rbJyqkHb;
	
	@Value("${rb_jyqk_jyyhs}")
	private String rbJyqkJyyhs;
	
	@Value("${rb_jyqk_scbzjl}")
	private String rbJyqkScbzjl;
	
	@Value("${rb_jyqk_dds}")
	private String rbJyqkDds;
	
	@Value("${rb_jyqk_ddzhl}")
	private String rbJyqkDdzhl;
	
	@Value("${rb_jyqk_kdj}")
	private String rbJyqkKdj;
	
	@Value("${rb_jyqk_sckjyezb}")
	private String rbJyqkSckjyezb;
	
	@Value("${rb_hyyhsh_yh}")
	private String rbHyyhshYh;
	
	@Value("${rb_hyyhsh_sh}")
	private String rbHyyhshSh;

	@Value("${rb_khd_xzyh}")
	private String rbKhdXzyh;
	
	@Value("${rb_khd_ddzhl}")
	private String rbKhdDdzhl;
	
	@Value("${rb_khd_ddcgl}")
	private String rbKhdDdcgl;
	
	@Value("${rb_dsyw_day_yhq_dxsl}")
	private String rbDsywDayYhqDxsl;
	
	@Value("${rb_dsyw_day_yhq_cxsl}")
	private String rbDsywDayYhqCxsl;
	
	@Value("${rb_dsyw_day_yhq_dxxzl}")
	private String rbDsywDayYhqDxxzl;

	@Value("${rb_dsyw_day_yhq_cxxzl}")
	private String rbDsywDayYhqCxxzl;
	
	@Value("${rb_dsyw_day_cp_dds}")
	private String rbDsywDayCpDds;
	
	@Value("${rb_dsyw_day_cp_cyrs}")
	private String rbDsywDayCpCyrs;
	
	@Value("${rb_dsyw_day_cp_scsl}")
	private String rbDsywDayCpScsl;
	
	@Value("${rb_dsyw_day_cp_xsze}")
	private String rbDsywDayCpXsze;
	
	@Value("${rb_dsyw_week_zdxstj_dds}")
	private String rbDsywWeekZdxstjDds;
	
	@Value("${rb_dsyw_week_zdxstj_cyrs}")
	private String rbDsywWeekZdxstjCyrs;
	
	@Value("${rb_dsyw_week_zdxstj_scsl}")
	private String rbDsywWeekZdxstjScsl;
	
	@Value("${rb_dsyw_week_zdxstj_xsze}")
	private String rbDsywWeekZdxstjXsze;
	
	@Value("${rb_lt_bzfts}")
	private String rbLtBzfts;
	
	@Value("${rb_lt_hyyhs}")
	private String rbLtHyyhs;
	
	@Value("${rb_shspsl_swspsh}")
	private String rbShspslSwspsh;
	
	@Value("${rb_dxqf_bzdxfsts}")
	private String rbDxqfBzdxfsts;
	
	@Value("${rb_dxqf_bzcxfsts}")
	private String rbDxqfBzcxfsts;

	public String getJfYwsrQx() {
		return jfYwsrQx;
	}

	public String getJfYwsrQxSyhbzz() {
		return jfYwsrQxSyhbzz;
	}

	public String getJfYwsrQxBnljwcqk() {
		return jfYwsrQxBnljwcqk;
	}

	public String getJfYwsrHx() {
		return jfYwsrHx;
	}

	public String getJfYwsrHxSyhbzz() {
		return jfYwsrHxSyhbzz;
	}

	public String getJfYwsrHxBnljwcqk() {
		return jfYwsrHxBnljwcqk;
	}

	public String getJfDzhyMfbDyxz() {
		return jfDzhyMfbDyxz;
	}

	public String getJfDzhyMfbDyjz() {
		return jfDzhyMfbDyjz;
	}

	public String getJfDzhyMfbDydds() {
		return jfDzhyMfbDydds;
	}

	public String getJfJyyhXs() {
		return jfJyyhXs;
	}

	public String getJfJyyhXx() {
		return jfJyyhXx;
	}

	public String getJfJyyhHj() {
		return jfJyyhHj;
	}

	public String getJfKhdAzs() {
		return jfKhdAzs;
	}

	public String getJfKhdHyyhs() {
		return jfKhdHyyhs;
	}

	public String getJfDsjySyhbzz() {
		return jfDsjySyhbzz;
	}

	public String getJfDsjyBnljwcqk() {
		return jfDsjyBnljwcqk;
	}

	public String getJfHyyhDy() {
		return jfHyyhDy;
	}

	public String getJfHyyhYj() {
		return jfHyyhYj;
	}

	public String getInternetLlyjQdDlfwcs() {
		return internetLlyjQdDlfwcs;
	}

	public String getInternetLlyjQdDlfwyh() {
		return internetLlyjQdDlfwyh;
	}

	public String getInternetLlyjQdHyyzcs() {
		return internetLlyjQdHyyzcs;
	}

	public String getInternetLlyjQdDdbs() {
		return internetLlyjQdDdbs;
	}

	public String getInternetLlyjQdCgzfs() {
		return internetLlyjQdCgzfs;
	}

	public String getInternetLlyjQdXffhbs() {
		return internetLlyjQdXffhbs;
	}

	public String getInternetLlyjQdJye() {
		return internetLlyjQdJye;
	}

	public String getInternetLlyjQdZflxScb() {
		return internetLlyjQdZflxScb;
	}

	public String getInternetLlyjQdZflxJf() {
		return internetLlyjQdZflxJf;
	}

	public String getInternetLlyjQdZflxXj() {
		return internetLlyjQdZflxXj;
	}

	public String getInternetGgyxzbHdcyyh() {
		return internetGgyxzbHdcyyh;
	}

	public String getInternetGgyxzbHycyyh() {
		return internetGgyxzbHycyyh;
	}

	public String getInternetGgyxzbZcxzyh() {
		return internetGgyxzbZcxzyh;
	}

	public String getInternetKhjzzbYhs() {
		return internetKhjzzbYhs;
	}

	public String getInternetKhjzzbDkhkdj() {
		return internetKhjzzbDkhkdj;
	}

	public String getInternetKhjzzb1xfyhs() {
		return internetKhjzzb1xfyhs;
	}

	public String getInternetKhjzzb3xfyhs() {
		return internetKhjzzb3xfyhs;
	}

	public String getInternetKhjzzb6xfyhs() {
		return internetKhjzzb6xfyhs;
	}

	public String getInternetKhjzzbJye() {
		return internetKhjzzbJye;
	}

	public String getInternetNbjyzbPv() {
		return internetNbjyzbPv;
	}

	public String getInternetNbjyzbUv() {
		return internetNbjyzbUv;
	}

	public String getInternetNbjyzbDdzhl() {
		return internetNbjyzbDdzhl;
	}

	public String getInternetNbjyzbZfzhl() {
		return internetNbjyzbZfzhl;
	}

	public String getInternetNbjyzbDdsl() {
		return internetNbjyzbDdsl;
	}

	public String getInternetNbjyzbJye() {
		return internetNbjyzbJye;
	}

	public String getInternetNbjyzbJyyhs() {
		return internetNbjyzbJyyhs;
	}

	public String getInternetNbjyzbPjtcl() {
		return internetNbjyzbPjtcl;
	}

	public String getInternetNbjyzbRjllyms() {
		return internetNbjyzbRjllyms;
	}

	public String getInternetNbjyzbRjllysc() {
		return internetNbjyzbRjllysc;
	}

	public String getRbDzhyXzsmhy() {
		return rbDzhyXzsmhy;
	}

	public String getRbDzhyTdsmhy() {
		return rbDzhyTdsmhy;
	}

	public String getRbDzhyJzsmhy() {
		return rbDzhyJzsmhy;
	}

	public String getRbDzhyMfb() {
		return rbDzhyMfb;
	}

	public String getRbDzhy3yb() {
		return rbDzhy3yb;
	}

	public String getRbDzhy5yb() {
		return rbDzhy5yb;
	}

	public String getRbDzhy10yb() {
		return rbDzhy10yb;
	}

	public String getRbDzhyBn() {
		return rbDzhyBn;
	}

	public String getRbDzhyLj() {
		return rbDzhyLj;
	}

	public String getRbDzhyXzhzhy() {
		return rbDzhyXzhzhy;
	}

	public String getRbZcyhXzzcyh() {
		return rbZcyhXzzcyh;
	}

	public String getRbZcyhLjzcyh() {
		return rbZcyhLjzcyh;
	}

	public String getRbJyqkBrjyze() {
		return rbJyqkBrjyze;
	}

	public String getRbJyqkXnlspjye() {
		return rbJyqkXnlspjye;
	}

	public String getRbJyqkWebjye() {
		return rbJyqkWebjye;
	}

	public String getRbJyqkWapjye() {
		return rbJyqkWapjye;
	}

	public String getRbJyqkKhdjye() {
		return rbJyqkKhdjye;
	}

	public String getRbJyqkDxgjye() {
		return rbJyqkDxgjye;
	}

	public String getRbJyqkXxxfjye() {
		return rbJyqkXxxfjye;
	}

	public String getRbJyqkScb() {
		return rbJyqkScb;
	}

	public String getRbJyqkXj() {
		return rbJyqkXj;
	}

	public String getRbJyqkJf() {
		return rbJyqkJf;
	}

	public String getRbJyqkDjq() {
		return rbJyqkDjq;
	}

	public String getRbJyqkHb() {
		return rbJyqkHb;
	}

	public String getRbJyqkJyyhs() {
		return rbJyqkJyyhs;
	}

	public String getRbJyqkScbzjl() {
		return rbJyqkScbzjl;
	}

	public String getRbJyqkDds() {
		return rbJyqkDds;
	}

	public String getRbJyqkDdzhl() {
		return rbJyqkDdzhl;
	}

	public String getRbJyqkKdj() {
		return rbJyqkKdj;
	}

	public String getRbJyqkSckjyezb() {
		return rbJyqkSckjyezb;
	}

	public String getRbHyyhshYh() {
		return rbHyyhshYh;
	}

	public String getRbHyyhshSh() {
		return rbHyyhshSh;
	}

	public String getRbKhdXzyh() {
		return rbKhdXzyh;
	}

	public String getRbKhdDdzhl() {
		return rbKhdDdzhl;
	}

	public String getRbKhdDdcgl() {
		return rbKhdDdcgl;
	}

	public String getRbDsywDayYhqDxsl() {
		return rbDsywDayYhqDxsl;
	}

	public String getRbDsywDayYhqCxsl() {
		return rbDsywDayYhqCxsl;
	}

	public String getRbDsywDayYhqDxxzl() {
		return rbDsywDayYhqDxxzl;
	}

	public String getRbDsywDayYhqCxxzl() {
		return rbDsywDayYhqCxxzl;
	}

	public String getRbDsywDayCpDds() {
		return rbDsywDayCpDds;
	}

	public String getRbDsywDayCpCyrs() {
		return rbDsywDayCpCyrs;
	}

	public String getRbDsywDayCpScsl() {
		return rbDsywDayCpScsl;
	}

	public String getRbDsywDayCpXsze() {
		return rbDsywDayCpXsze;
	}

	public String getRbDsywWeekZdxstjDds() {
		return rbDsywWeekZdxstjDds;
	}

	public String getRbDsywWeekZdxstjCyrs() {
		return rbDsywWeekZdxstjCyrs;
	}

	public String getRbDsywWeekZdxstjScsl() {
		return rbDsywWeekZdxstjScsl;
	}

	public String getRbDsywWeekZdxstjXsze() {
		return rbDsywWeekZdxstjXsze;
	}

	public String getRbLtBzfts() {
		return rbLtBzfts;
	}

	public String getRbLtHyyhs() {
		return rbLtHyyhs;
	}

	public String getRbShspslSwspsh() {
		return rbShspslSwspsh;
	}

	public String getRbDxqfBzdxfsts() {
		return rbDxqfBzdxfsts;
	}

	public String getRbDxqfBzcxfsts() {
		return rbDxqfBzcxfsts;
	}
	
	/**
	 * 经分报表-业务收入
	 */
	public Map<String, String> getJf_Ywsr(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("前向收入", getJfYwsrQx());
		map.put("上月环比增长", getJfYwsrQxSyhbzz());
		map.put("本年累计完成情况", getJfYwsrQxBnljwcqk());
		map.put("后向收入", getJfYwsrQx());
		return map;
	}
	
	/**
	 * 经分报表-定制会员
	 * @return
	 */
	public Map<String, String> getJf_Dzhy(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("当月净增", getJfDzhyMfbDyjz());
		map.put("当月新增", getJfDzhyMfbDyxz());
		map.put("当月到达数", getJfDzhyMfbDydds());
		return map;
	}
	
	/**
	 * 经分报表-月交易用户数
	 * @return
	 */
	public Map<String, String> getJf_Jyyh(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("线上", getJfJyyhXs());
		map.put("线下", getJfJyyhXx());
		map.put("合计", getJfJyyhHj());
		return map;
	}
	
	/**
	 * 经分报表-客户端安装数
	 * @return
	 */
	public Map<String, String> getJf_Khd(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("客户端安装数", getJfKhdAzs());
		map.put("客户端活跃用户数", getJfKhdHyyhs());
		return map;
	}
	
	/**
	 * 经分报表-电商交易额
	 * @return
	 */
	public Map<String, String> getJf_Dsjy(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("上月环比增长", getJfDsjySyhbzz());
		map.put("本年累计完成情况", getJfDsjyBnljwcqk());
		return map;
	}
	
	/**
	 * 经分报表-活跃用户数
	 * @return
	 */
	public Map<String, String> getJf_Hyyhs(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("当月", getJfHyyhDy());
		map.put("月均", getJfHyyhYj());
		return map;
	}
	
	/**
	 * 互联网报表-流量业绩指标
	 * @return
	 */
	public Map<String, String> getInt_Llyj_Qd(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("独立访问次数", getInternetLlyjQdDlfwcs());
		map.put("独立访问用户", getInternetLlyjQdDlfwyh());
		map.put("会员验证次数", getInternetLlyjQdHyyzcs());
		map.put("订单笔数", getInternetLlyjQdDdbs());
		map.put("成功支付数", getInternetLlyjQdCgzfs());
		map.put("消费发货笔数", getInternetLlyjQdXffhbs());
		map.put("交易额", getInternetLlyjQdJye());
		map.put("商城币", getInternetLlyjQdZflxScb());
		map.put("积分", getInternetLlyjQdZflxJf());
		map.put("现金", getInternetLlyjQdZflxXj());
		return map;
	}
	
	/**
	 * 互联网报表-广告营销指标
	 * @return
	 */
	public Map<String, String> getInt_Ggyx(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("活动参与用户", getInternetGgyxzbHdcyyh());
		map.put("会员参与用户", getInternetGgyxzbHycyyh());
		map.put("注册新增用户", getInternetGgyxzbZcxzyh());
		return map;
	}
	
	/**
	 * 互联网报表-客户价值指标
	 * @return
	 */
	public Map<String, String> getInt_Khjz(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("用户数", getInternetKhjzzbYhs());
		map.put("单客户客单价", getInternetKhjzzbDkhkdj());
		map.put("每月两次及以上的消费用户数", getInternetKhjzzb1xfyhs());
		map.put("三个月两次及以上的消费用户数", getInternetKhjzzb3xfyhs());
		map.put("六个月两次及以上的消费用户数", getInternetKhjzzb6xfyhs());
		map.put("交易额", getInternetKhjzzbJye());
		return map;
	}
	
	/**
	 * 互联网报表-内部经营指标
	 * @return
	 */
	public Map<String, String> getInt_Nbjy(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("PV", getInternetNbjyzbPv());
		map.put("UV", getInternetNbjyzbUv());
		map.put("订单转化率", getInternetNbjyzbDdzhl());
		map.put("支付转化率", getInternetNbjyzbZfzhl());
		map.put("订单数量", getInternetNbjyzbDdsl());
		map.put("交易额", getInternetNbjyzbJye());
		map.put("交易用户数", getInternetNbjyzbJyyhs());
		map.put("平均跳出率", getInternetNbjyzbPjtcl());
		map.put("人均浏览页面数", getInternetNbjyzbRjllyms());
		map.put("人均浏览页时长", getInternetNbjyzbRjllysc());
		return map;
	}

	/**
	 * 日报表-定制会员
	 * @return
	 */
	public Map<String, String> getRb_Dzhy(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("新增商盟会员", getRbDzhyXzsmhy());
		map.put("退订商盟会员", getRbDzhyTdsmhy());
		map.put("净增商盟会员", getRbDzhyJzsmhy());
		map.put("免费版", getRbDzhyMfb());
		map.put("3元版", getRbDzhy3yb());
		map.put("5元版", getRbDzhy5yb());
		map.put("10元版", getRbDzhy10yb());
		map.put("包年", getRbDzhyBn());
		map.put("累计", getRbDzhyLj());
		map.put("新增红钻会员", getRbDzhyXzhzhy());
		return map;
	}
	
	/**
	 * 日报表-注册用户
	 * @return
	 */
	public Map<String, String> getRb_Zcyh(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("新增注册用户", getRbZcyhXzzcyh());
		map.put("累计注册用户", getRbZcyhLjzcyh());
		return map;
	}
	
	/**
	 * 日报表-交易情况
	 * @return
	 */
	public Map<String, String> getRb_Jyqk(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("本日总交易额", getRbJyqkBrjyze());
		map.put("虚拟类商品交易额", getRbJyqkXnlspjye());
		map.put("web交易额", getRbJyqkWebjye());
		map.put("wap交易额", getRbJyqkWapjye());
		map.put("客户端交易额", getRbJyqkKhdjye());
		map.put("短信购交易额", getRbJyqkDxgjye());
		map.put("线下消费交易额", getRbJyqkXxxfjye());
		map.put("商城币", getRbJyqkScb());
		map.put("现金", getRbJyqkXj());
		map.put("积分/M值", getRbJyqkJf());
		map.put("代金券", getRbJyqkDjq());
		map.put("红包", getRbJyqkHb());
		map.put("交易用户数", getRbJyqkJyyhs());
		map.put("商城币增加量", getRbJyqkScbzjl());
		map.put("订单数", getRbJyqkDds());
		map.put("订单转化率", getRbJyqkDdzhl());
		map.put("客单价", getRbJyqkKdj());
		map.put("商超卡交易额占比", getRbJyqkSckjyezb());
		return map;
	}
	
	/**
	 * 日报表-活跃用户商户
	 * @return
	 */
	public Map<String, String> getRb_Hyyhsh(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("活跃用户数", getRbHyyhshYh());
		map.put("活跃商户数", getRbHyyhshSh());
		return map;
	}
	
	/**
	 * 日报表-客户端
	 * @return
	 */
	public Map<String, String> getRb_Khd(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("新增用户", getRbKhdXzyh());
		map.put("订单转化率", getRbKhdDdzhl());
		map.put("订单成功率", getRbKhdDdcgl());
		return map;
	}
	
	/**
	 * 日报表-电商业务—日报
	 * @return
	 */
	public Map<String, String> getRb_Dsyw_Day(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("短信优惠券数量", getRbDsywDayYhqDxsl());
		map.put("彩信优惠券数量", getRbDsywDayYhqCxsl());
		map.put("短信优惠券下载量", getRbDsywDayYhqDxxzl());
		map.put("彩信优惠券下载量", getRbDsywDayYhqCxxzl());
		map.put("订单数", getRbDsywDayCpDds());
		map.put("参与人数", getRbDsywDayCpCyrs());
		map.put("售出数量", getRbDsywDayCpScsl());
		map.put("销售总额", getRbDsywDayCpXsze());
		return map;
	}
	
	/**
	 * 日报表-电商业务-周报
	 * @return
	 */
	public Map<String, String> getRb_Dsyw_Week(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("订单数", getRbDsywWeekZdxstjDds());
		map.put("参与人数", getRbDsywWeekZdxstjCyrs());
		map.put("售出数量", getRbDsywWeekZdxstjScsl());
		map.put("销售总额", getRbDsywWeekZdxstjXsze());
		return map;
	}
	/**
	 * 日报表-论坛
	 * @return
	 */
	public Map<String, String> getRb_Lt(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("本周发帖数", getRbLtBzfts());
		map.put("活跃用户数", getRbLtHyyhs());
		return map;
	}
	
	/**
	 * 日报表-商户商品数量
	 * @return
	 */
	public Map<String, String> getRb_Shspsl(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("实物商品商户", getRbShspslSwspsh());
		return map;
	}
	
	/**
	 * 日报表-短信群发
	 * @return
	 */
	public Map<String, String> getRb_Dxqf(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("本周短信发送条数", getRbDxqfBzdxfsts());
		map.put("本周彩信发送条数", getRbDxqfBzcxfsts());
		return map;
	}
	
}
