package com.cplatform.mall.back.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.back.item.entity.HisunProductionSettle;
import com.cplatform.mall.back.model.SyncGYProductionResponseBean;
import com.cplatform.mall.back.model.SyncGYResponseBean;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.entity.StoreFee;
import com.cplatform.mall.back.store.entity.StoreSettle;
import com.cplatform.mall.back.sys.entity.SysFee;
import com.cplatform.mall.back.websys.entity.SysFile;
import com.cplatform.settle_interface.SettleInterfaceClient;
import com.cplatform.settle_interface.bean.MerchantBaseInfoAddRequest;
import com.cplatform.settle_interface.bean.MerchantBaseInfoAddResponse;
import com.cplatform.settle_interface.bean.MerchantBaseInfoDeleteRequest;
import com.cplatform.settle_interface.bean.MerchantBaseInfoDeleteResponse;
import com.cplatform.settle_interface.bean.MerchantBaseInfoModifyRequest;
import com.cplatform.settle_interface.bean.MerchantBaseInfoModifyResponse;
import com.cplatform.settle_interface.bean.MerchantBaseInfoQueryRequest;
import com.cplatform.settle_interface.bean.MerchantBaseInfoQueryResponse;
import com.cplatform.settle_interface.bean.MerchantContractAddRequest;
import com.cplatform.settle_interface.bean.MerchantContractAddResponse;
import com.cplatform.settle_interface.bean.MerchantContractModifyRequest;
import com.cplatform.settle_interface.bean.MerchantContractModifyResponse;
import com.cplatform.settle_interface.bean.MerchantFeeInfoAddRequest;
import com.cplatform.settle_interface.bean.MerchantFeeInfoAddResponse;
import com.cplatform.settle_interface.bean.MerchantFeeInfoDeleteRequest;
import com.cplatform.settle_interface.bean.MerchantFeeInfoDeleteResponse;
import com.cplatform.settle_interface.bean.MerchantFeeInfoModifyRequest;
import com.cplatform.settle_interface.bean.MerchantFeeInfoModifyResponse;
import com.cplatform.settle_interface.bean.MerchantFeeInfoQueryRequest;
import com.cplatform.settle_interface.bean.MerchantFeeInfoQueryResponse;
import com.cplatform.settle_interface.bean.MerchantGoodsTypeInfoAddRequest;
import com.cplatform.settle_interface.bean.MerchantGoodsTypeInfoAddResponse;
import com.cplatform.settle_interface.bean.MerchantGoodsTypeInfoModifyRequest;
import com.cplatform.settle_interface.bean.MerchantGoodsTypeInfoModifyResponse;
import com.cplatform.settle_interface.bean.MerchantGoodsTypeInfoQueryRequest;
import com.cplatform.settle_interface.bean.MerchantGoodsTypeInfoQueryResponse;
import com.cplatform.settle_interface.bean.MerchantProductionInfoAddRequest;
import com.cplatform.settle_interface.bean.MerchantProductionInfoAddResponse;
import com.cplatform.settle_interface.bean.MerchantProductionInfoModifyRequest;
import com.cplatform.settle_interface.bean.MerchantProductionInfoModifyResponse;
import com.cplatform.settle_interface.bean.MerchantProductionInfoQueryRequest;
import com.cplatform.settle_interface.bean.MerchantProductionInfoQueryResponse;
import com.cplatform.settle_interface.bean.MerchantSettleInfoAddRequest;
import com.cplatform.settle_interface.bean.MerchantSettleInfoAddResponse;
import com.cplatform.settle_interface.bean.MerchantSettleInfoDeleteRequest;
import com.cplatform.settle_interface.bean.MerchantSettleInfoDeleteResponse;
import com.cplatform.settle_interface.bean.MerchantSettleInfoModifyRequest;
import com.cplatform.settle_interface.bean.MerchantSettleInfoModifyResponse;
import com.cplatform.settle_interface.bean.MerchantSettleInfoQueryRequest;
import com.cplatform.settle_interface.bean.MerchantSettleInfoQueryResponse;
import com.cplatform.util2.TimeStamp;

/**
 * 高阳同步接口 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-8 下午2:09:14
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class SyncInterface {

	private static final Logger log = Logger.getLogger(SyncInterface.class);

	public StringBuilder sqlBuff = new StringBuilder(50);

	@Autowired
	public DbHelper dbHelper;

	@Autowired
	public AppConfig config;

	// @Autowired
	// private StoreService storeService;

	// @Autowired
	// private SysRegionService regionService;

	@Autowired
	private LogUtils logUtils;

	public void init() {
		try {
			SettleInterfaceClient.getInstance().init(config.getSyncGyUserId(), config.getSyncGyKey());
		}
		catch (Exception e) {
			logUtils.logAdd("初始化高阳同步接口失败", e.getMessage());
			log.error(e.getMessage());
		}

	}

	/**
	 * 添加商品分类
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean syncAddGoodsType(SysFee vo) {
		init();
		SyncGYResponseBean bean = null;
		try {
			MerchantGoodsTypeInfoAddRequest request = new MerchantGoodsTypeInfoAddRequest();
			// request.setGoodstypenumber("");// 分类数量

			request.setServiceid(config.getSyncGyServiceId());// 业务编号
			request.setGoodstypeid(vo.getId().toString());// 分类编号
			request.setGoodstypename(vo.getName());// 分类名称
			request.setGoodstypelevel("1");// 分类层级
			request.setUpgoodstypeid(vo.getId().toString());// 父类编号

			MerchantGoodsTypeInfoAddResponse response = (MerchantGoodsTypeInfoAddResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
			}

		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，同步商品分类失败", e.getMessage());
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 修改商品分类
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean syncUpdateGoodsType(SysFee vo) {
		init();
		SyncGYResponseBean bean = null;
		try {
			MerchantGoodsTypeInfoModifyRequest request = new MerchantGoodsTypeInfoModifyRequest();
			request.setServiceid(config.getSyncGyServiceId());// 业务编号
			request.setGoodstypeid(vo.getId().toString());// 分类编号
			request.setGoodstypename(vo.getName());// 分类名称

			request.setGoodstypelevel("1");// 分类层级
			request.setUpgoodstypeid(vo.getId().toString());// 父类编号

			MerchantGoodsTypeInfoModifyResponse response = (MerchantGoodsTypeInfoModifyResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，修改商品分类失败", e.getMessage());
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 商户费率资料新增同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean syncAddFee(StoreFee vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantFeeInfoAddRequest request = new MerchantFeeInfoAddRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getStoreId().toString());// 商户编号
			request.setServiceid(config.getSyncGyServiceId());// 业务编号
			request.setCapitaltype(vo.getCapitalType().toString());// 资金种类
			request.setTrademode(vo.getTradeMode().toString());// 交易方式
			request.setFeetype(vo.getFeeType());// 费用类型
			// request.setFeetype("01");// 费用类型
			request.setProductiontype(vo.getProductionType());// 商品类别
			request.setCleartype(vo.getClearType());// 清算类别
			request.setEffortdate(vo.getEffortdate());// 生效日期
			request.setExpirydate(vo.getExpirydate());// 失效日期
			request.setFeedrtflag(vo.getFeedrtflag());// 费用方向
			request.setFeeperiodunit(vo.getFeeperiodunit());// 收费周期单位
			request.setFeeperiodnumber(vo.getFcName());// 收费同期数量
			request.setFeemode(vo.getFeemode().toString());// 收费方式
			request.setFeemothod(vo.getFeemothod().toString());// 计费方法
			request.setBeganamount(vo.getBeganamount());// 计费起始金额(笔数)
			request.setMinfeeamount(vo.getMinfeeamount());// 最低收费金额
			request.setMaxfeeamount(vo.getMaxfeeamount());// 最高收费金额
			request.setFeebasicflag(vo.getFeebasicflag().toString());// 费用计算依据
			request.setFeelevelflag(vo.getFeelevelflag());// 分层/套档标志
			request.setFeelvlbasicflag(vo.getFeelvlbasicflag().toString());// 分层/套档依据
			request.setUpreference1(vo.getUpreference1());// 计费参考1
			request.setFixfeeamount1(vo.getFixfeeamount1());// 固定费用金额1
			request.setFeerate1(vo.getFeerate1());// 费率1
			request.setUpreference2(vo.getUpreference2());// 计费参考2
			request.setFixfeeamount2(vo.getFixfeeamount2());// 固定费用金额2
			request.setFeerate2(vo.getFeerate2());// 费率2
			request.setUpreference3(vo.getUpreference3());// 计费参考3
			request.setFixfeeamount3(vo.getFixfeeamount3());// 固定费用金额3
			request.setFeerate3(vo.getFeerate3());// 费率3
			request.setUpreference4(vo.getUpreference4());// 计费参考4
			request.setFixfeeamount4(vo.getFixfeeamount4());// 固定费用金额4
			request.setFeerate4(vo.getFeerate4());// 费率4
			request.setUpreference5(vo.getUpreference5());// 计费参考5
			request.setFixfeeamount5(vo.getFixfeeamount5());// 固定费用金额5
			request.setFeerate5(vo.getFeerate5());// 费率5
			MerchantFeeInfoAddResponse response = (MerchantFeeInfoAddResponse) SettleInterfaceClient.getInstance().sendRequest(request,
			                                                                                                                   config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(response.getMerchid());
				// String status = response.getResponsecode();

			}

		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商户费率资料新增接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 商户费率资料修改同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean syncUpdateFee(StoreFee vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantFeeInfoModifyRequest request = new MerchantFeeInfoModifyRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getStoreId().toString());// 商户编号
			request.setServiceid(config.getSyncGyServiceId());// 业务编号
			request.setCapitaltype(vo.getCapitalType().toString());// 资金种类
			request.setTrademode(vo.getTradeMode().toString());// 交易方式
			request.setFeetype(vo.getFeeType());// 费用类型
			// request.setFeetype("01");// 费用类型
			request.setProductiontype(vo.getProductionType());// 商品类别
			request.setCleartype(vo.getClearType());// 清算类别
			request.setEffortdate(vo.getEffortdate());// 生效日期
			request.setExpirydate(vo.getExpirydate());// 失效日期
			request.setFeedrtflag(vo.getFeedrtflag());// 费用方向
			request.setFeeperiodunit(vo.getFeeperiodunit());// 收费周期单位
			request.setFeeperiodnumber(vo.getFcName());// 收费同期数量
			request.setFeemode(vo.getFeemode().toString());// 收费方式
			request.setFeemothod(vo.getFeemothod().toString());// 计费方法
			request.setBeganamount(vo.getBeganamount());// 计费起始金额(笔数)
			request.setMinfeeamount(vo.getMinfeeamount());// 最低收费金额
			request.setMaxfeeamount(vo.getMaxfeeamount());// 最高收费金额
			request.setFeebasicflag(vo.getFeebasicflag().toString());// 费用计算依据
			request.setFeelevelflag(vo.getFeelevelflag());// 分层/套档标志
			request.setFeelvlbasicflag(vo.getFeelvlbasicflag().toString());// 分层/套档依据
			request.setUpreference1(vo.getUpreference1());// 计费参考1
			request.setFixfeeamount1(vo.getFixfeeamount1());// 固定费用金额1
			request.setFeerate1(vo.getFeerate1());// 费率1
			request.setUpreference2(vo.getUpreference2());// 计费参考2
			request.setFixfeeamount2(vo.getFixfeeamount2());// 固定费用金额2
			request.setFeerate2(vo.getFeerate2());// 费率2
			request.setUpreference3(vo.getUpreference3());// 计费参考3
			request.setFixfeeamount3(vo.getFixfeeamount3());// 固定费用金额3
			request.setFeerate3(vo.getFeerate3());// 费率3
			request.setUpreference4(vo.getUpreference4());// 计费参考4
			request.setFixfeeamount4(vo.getFixfeeamount4());// 固定费用金额4
			request.setFeerate4(vo.getFeerate4());// 费率4
			request.setUpreference5(vo.getUpreference5());// 计费参考5
			request.setFixfeeamount5(vo.getFixfeeamount5());// 固定费用金额5
			request.setFeerate5(vo.getFeerate5());// 费率5

			MerchantFeeInfoModifyResponse response = (MerchantFeeInfoModifyResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(vo.getMerchid().toString());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商户费率资料修改接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}

		return bean;
	}

	/**
	 * 商户结算资料新增同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean syncAddSettle(StoreSettle vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantSettleInfoAddRequest request = new MerchantSettleInfoAddRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getStoreId().toString());// 商户编号
			request.setServiceid(config.getSyncGyServiceId());// 业务编号
			request.setEffortdate(vo.getEffortDate());// 生效日期
			request.setExpirydate(vo.getExpiryDate());// 失效日期
			request.setSettletype(vo.getSettleType().toString());// 结算类型
			request.setFeesettle(vo.getFeeSettle().toString());// 佣金结算
			request.setSettleperiod(vo.getSettlePeriod().toString());// 结算周期
			request.setSettleday(vo.getSettleDay().toString());// 结算日
			request.setSettledaybit(vo.getSettleDaybit().toString());// 结算日标志位
			request.setSettletrfdays(vo.getSettleTrfdays().toString());// 结算划款天数
			request.setSettlebeginamt(vo.getSettleBeginamt().toString());// 结算起始金额
			request.setMinretainedamt(vo.getMinRetainedamt().toString());// 最低留存金额
			request.setWithdrawbankid(vo.getWithdrawBankid());// 结算银行
			request.setOpenbankdesc(vo.getOpenbankDesc());// 银行详细信息

			request.setBankacname(vo.getBankAcname());// 户名
			request.setSettleac(vo.getSettleAc());// 结算账号

			MerchantSettleInfoAddResponse response = (MerchantSettleInfoAddResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(response.getMerchid());

			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商户结算资料新增接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}

		return bean;
	}

	/**
	 * 商户结算资料修改同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean syncUpdateSettle(StoreSettle vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantSettleInfoModifyRequest request = new MerchantSettleInfoModifyRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getStoreId().toString());// 商户编号
			request.setServiceid(config.getSyncGyServiceId());// 业务编号

			request.setEffortdate(vo.getEffortDate());// 生效日期

			request.setExpirydate(vo.getExpiryDate());// 失效日期

			request.setSettletype(vo.getSettleType().toString());// 结算类型

			request.setFeesettle(vo.getFeeSettle().toString());// 佣金结算
			request.setSettleperiod(vo.getSettlePeriod().toString());// 结算周期

			request.setSettleday(vo.getSettleDay().toString());// 结算日

			request.setSettledaybit(vo.getSettleDaybit().toString());// 结算日标志位
			request.setSettletrfdays(vo.getSettleTrfdays().toString());// 结算划款天数
			request.setSettlebeginamt(vo.getSettleBeginamt().toString());// 结算起始金额

			request.setMinretainedamt(vo.getMinRetainedamt().toString());// 最低留存金额

			request.setWithdrawbankid(vo.getWithdrawBankid());// 结算银行

			request.setOpenbankdesc(vo.getOpenbankDesc());// 银行详细信息

			request.setBankacname(vo.getBankAcname());// 户名

			request.setSettleac(vo.getSettleAc());// 结算账号

			MerchantSettleInfoModifyResponse response = (MerchantSettleInfoModifyResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(vo.getMerchid().toString());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商户结算资料修改接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}

		return bean;
	}

	/**
	 * 商户新增同步
	 * 
	 * @param vo
	 */
	public SyncGYResponseBean syncAddStore(Store vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantBaseInfoAddRequest request = new MerchantBaseInfoAddRequest();
		try {
			request.setBusinesid(vo.getId().toString());// 商户编号
			request.setServiceid(config.getSyncGyServiceId());// 业务编号
			request.setAreacode(vo.getAreaCode());// 所在地
			// request.setAreacode("南京");// 所在地
			request.setMerchname(vo.getName());// 中文名称
			if (vo.getShortName() != null && !"".equals(vo.getShortName())) {
				request.setMerchabbreviation(vo.getShortName());// 商户简称
			} else {
				request.setMerchabbreviation(vo.getName());// 商户简称
			}
			request.setPayalias(vo.getName());// 支付别名
			request.setMerchtype("0");// 商户类型
			request.setMerchclass("0");// 商户标识
			request.setMerchflag("A");// 商户类别
			request.setMerchattribute("9");// 商户性质
			request.setPercrpflag("1");// 个企标志
			// request.setNeedinvflag("Y");// 是否开具发票
			request.setNeedinvflag("N");// 是否开具发票
			request.setInvmode("2");// 开具发票方式
			request.setRefundfeemode("1");// 退货手续费处理方式
			request.setAicmemflag("N");// 红盾315成员
			request.setMerchsrc("3");// 商户来源
			request.setClearflag("2");// 清分标志
			request.setRegisteraddr(vo.getAddress());// 商户注册地址
			// request.setFax(vo.getLinkFax());// 传真
			request.setRefundfeemode("1");// 退货手续费处理方式
			request.setCreateopr(vo.getSysUserId().toString());// 建立操作员
			request.setUpdateopr("0");// 更新操作员
			request.setCreateopr(vo.getSysUserId() + "");// 建立操作员
			request.setReviewopr("0");// 审核操作员
			request.setInvtitle(vo.getName());// 发票抬头
			request.setRefundmode("2");// 退货方式
			request.setUpdateeffdate(vo.getCreateTime().substring(0, 8));// 生效日期

			MerchantBaseInfoAddResponse response = (MerchantBaseInfoAddResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(response.getMerchid());

			}

		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商户新增接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		} //
		return bean;
	}

	/**
	 * 商户修改同步
	 * 
	 * @param vo
	 */
	public SyncGYResponseBean syncUpdateStore(Store vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantBaseInfoModifyRequest request = new MerchantBaseInfoModifyRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getId().toString());// 商户编号
			request.setServiceid(config.getSyncGyServiceId());// 业务编号
			request.setAreacode(vo.getAreaCode());// 所在地
			// request.setAreacode("南京");// 所在地
			request.setMerchname(vo.getName());// 中文名称
			if (vo.getShortName() != null && !"".equals(vo.getShortName())) {
				request.setMerchabbreviation(vo.getShortName());// 商户简称
			} else {
				request.setMerchabbreviation(vo.getName());// 商户简称
			}
			request.setPayalias(vo.getName());// 支付别名
			request.setMerchtype("0");// 商户类型
			request.setMerchclass("0");// 商户标识
			request.setMerchflag("A");// 商户类别
			request.setMerchattribute("9");// 商户性质
			request.setPercrpflag("1");// 个企标志
			// request.setNeedinvflag("Y");// 是否开具发票
			request.setNeedinvflag("N");// 是否开具发票
			request.setInvmode("2");// 开具发票方式
			request.setRefundfeemode("1");// 退货手续费处理方式
			request.setAicmemflag("N");// 红盾315成员
			request.setMerchsrc("3");// 商户来源
			request.setClearflag("2");// 清分标志
			request.setRegisteraddr(vo.getAddress());// 商户注册地址
			// request.setFax(vo.getLinkFax());// 传真
			request.setRefundfeemode("1");// 退货手续费处理方式
			request.setCreateopr(vo.getSysUserId().toString());// 建立操作员
			request.setUpdateopr("0");// 更新操作员
			request.setCreateopr(vo.getSysUserId() + "");// 建立操作员
			request.setReviewopr("0");// 审核操作员
			request.setInvtitle(vo.getName());// 发票抬头
			request.setRefundmode("2");// 退货方式
			request.setUpdateeffdate(vo.getCreateTime().substring(0, 8));// 生效日期

			MerchantBaseInfoModifyResponse response = (MerchantBaseInfoModifyResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(vo.getMerchid().toString());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商户修改接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 查询商户基本资料
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean queryStoreStatus(Store vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantBaseInfoQueryRequest request = new MerchantBaseInfoQueryRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getId().toString());// 商户编号
			request.setServiceid(config.getSyncGyServiceId());// 业务编号
			MerchantBaseInfoQueryResponse response = (MerchantBaseInfoQueryResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(response.getMerchid());
				bean.setStatus(response.getStatus());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，查询商户基本资料接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 查询商户结算资料
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean queryStoreSettle(StoreSettle vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantSettleInfoQueryRequest request = new MerchantSettleInfoQueryRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getStoreId().toString());
			request.setServiceid(config.getSyncGyServiceId());
			request.setEffortdate(vo.getEffortDate());
			request.setExpirydate(vo.getExpiryDate());

			MerchantSettleInfoQueryResponse response = (MerchantSettleInfoQueryResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(response.getMerchid());
				bean.setStatus(response.getStatus());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，查询商户结算资料接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 查询商户费率资料
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean queryStoreFee(StoreFee vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantFeeInfoQueryRequest request = new MerchantFeeInfoQueryRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getStoreId().toString());
			request.setServiceid(config.getSyncGyServiceId());
			MerchantFeeInfoQueryResponse response = (MerchantFeeInfoQueryResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setMerchantFeeInfoList(response.getMerchfeedt());
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setMerchid(response.getMerchid());

			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，查询商户费率资料接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 查询商品分类资料
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean queryGoodsType(SysFee vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantGoodsTypeInfoQueryRequest request = new MerchantGoodsTypeInfoQueryRequest();
		try {
			request.setServiceid(config.getSyncGyServiceId());
			request.setGoodstypeid(vo.getId().toString());
			MerchantGoodsTypeInfoQueryResponse response = (MerchantGoodsTypeInfoQueryResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());

			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，查询商品分类资料接口同步失败", "接口调用失败，具体信息请查看log.error日志");
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 商品协议信息新增同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYProductionResponseBean syncAddSettle(HisunProductionSettle settle, String productionid) {
		init();
		SyncGYProductionResponseBean bean = null;
		try {
			MerchantProductionInfoAddRequest request = new MerchantProductionInfoAddRequest();
			request.setMerchid(settle.getMerchid());// 商户在统一清算平台中的编号
			request.setBusinesid(settle.getStoreId().toString());// 商户编号
			request.setServiceid(settle.getServiceid());// 业务编号
			request.setContractid(settle.getContractid());// 合同编号
			request.setContracteffdate(settle.getContracteffdate());// 合同生效日期
			request.setContractexpdate(settle.getContractexpdate());// 合同失效时间
			if (null != productionid) {
				request.setProductionid(productionid);// 商品编号
			} else {
				request.setProductionid(settle.getProductionid());// 商品编号
			}
			request.setProductionname(settle.getProductionname());// 商品名称
			request.setProductiontype(settle.getProductiontype());// 商品类别
			request.setCapitaltype(settle.getCapitalType().toString());// 资金种类
			request.setVerifyflag(settle.getVerifyflag());// 验证标识
			request.setVerifysettleflag(settle.getVerifysettleflag());
			request.setProductionefftime(settle.getProductionefftime());// 产品上线时间
			request.setProductionexptime(settle.getProductionexptime());// 产品下线时间
			request.setVerifyexpdate(settle.getVerifyexpdate());// 验证截止日期

			request.setCityid(settle.getCityid());// 地市编码
			request.setCityname(settle.getCityname());// 地市名称

			request.setCityprofitrate(settle.getCityprofitrate());// 地市分成比例
			request.setAgentid(settle.getAgentid());// 代理商编码
			request.setAgentname(settle.getAgentname());// 代理商名称
			request.setAgentprofitrate(settle.getAgentprofitrate());// 代理商分成比例
			request.setPrice(String.valueOf(((Double) (settle.getPrice() == null ? 0 : settle.getPrice() * 100)).intValue())); // 换算成分
			request.setSettlementprice(String.valueOf(((Double) (settle.getSettlementprice() == null ? 0 : settle.getSettlementprice() * 100))
			        .intValue()));// 结算单价
			if (null != settle.getSettleperiod()) {
				request.setSettleperiod(settle.getSettleperiod().toString());// 结算分期数
			}
			request.setSettleperiodtype(settle.getSettleperiodtype());// 结算分期类型
			request.setSettledate1(settle.getSettledate1());// 结算时间
			request.setSettlerate1(settle.getSettlerate1());// 结算比例
			request.setSettledate2(settle.getSettledate2());// 结算时间
			request.setSettlerate2(settle.getSettlerate2());// 结算比例
			request.setSettledate3(settle.getSettledate3());// 结算时间
			request.setSettlerate3(settle.getSettlerate3());// 结算比例

			MerchantProductionInfoAddResponse response = (MerchantProductionInfoAddResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYProductionResponseBean();

				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setProductionid(request.getProductionid());
				bean.setSettleId(settle.getId());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商品协议信息新增接口同步失败", e.getMessage());
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 商品协议信息修改同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYProductionResponseBean syncUpdateSettle(HisunProductionSettle settle, String productionid) {
		init();
		SyncGYProductionResponseBean bean = null;
		try {
			MerchantProductionInfoModifyRequest request = new MerchantProductionInfoModifyRequest();
			request.setMerchid(settle.getMerchid());// 商户在统一清算平台中的编号
			request.setBusinesid(settle.getStoreId().toString());// 商户编号
			request.setServiceid(settle.getServiceid());// 业务编号
			request.setContractid(settle.getContractid());// 合同编号
			request.setContracteffdate(settle.getContracteffdate());// 合同生效日期
			request.setContractexpdate(settle.getContractexpdate());// 合同失效时间
			if (null != productionid) {
				request.setProductionid(productionid);// 商品编号
			} else {
				request.setProductionid(settle.getProductionid());// 商品编号
			}
			request.setProductionname(settle.getProductionname());// 商品名称
			request.setProductiontype(settle.getProductiontype());// 商品类别
			request.setCapitaltype(settle.getCapitalType().toString());// 资金种类
			request.setVerifyflag(settle.getVerifyflag());// 验证标识
			request.setVerifysettleflag(settle.getVerifysettleflag());
			request.setProductionefftime(settle.getProductionefftime());// 产品上线时间
			request.setProductionexptime(settle.getProductionexptime());// 产品下线时间
			request.setVerifyexpdate(settle.getVerifyexpdate());// 验证截止日期

			request.setCityid(settle.getCityid());// 地市编码
			request.setCityname(settle.getCityname());// 地市名称

			request.setCityprofitrate(settle.getCityprofitrate());// 地市分成比例
			request.setAgentid(settle.getAgentid());// 代理商编码
			request.setAgentname(settle.getAgentname());// 代理商名称
			request.setAgentprofitrate(settle.getAgentprofitrate());// 代理商分成比例
			request.setPrice(String.valueOf(((Double) (settle.getPrice() == null ? 0 : settle.getPrice() * 100)).intValue())); // 换算成分
			request.setSettlementprice(String.valueOf(((Double) (settle.getSettlementprice() == null ? 0 : settle.getSettlementprice() * 100))
			        .intValue()));// 结算单价

			if (null != settle.getSettleperiod()) {
				request.setSettleperiod(settle.getSettleperiod().toString());// 结算分期数
			}
			request.setSettleperiodtype(settle.getSettleperiodtype());// 结算分期类型
			request.setSettledate1(settle.getSettledate1());// 结算时间
			request.setSettlerate1(settle.getSettlerate1());// 结算比例
			request.setSettledate2(settle.getSettledate2());// 结算时间
			request.setSettlerate2(settle.getSettlerate2());// 结算比例
			request.setSettledate3(settle.getSettledate3());// 结算时间
			request.setSettlerate3(settle.getSettlerate3());// 结算比例

			MerchantProductionInfoModifyResponse response = (MerchantProductionInfoModifyResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYProductionResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
				bean.setProductionid(request.getProductionid());
				bean.setSettleId(settle.getId());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商品协议信息修改接口同步失败", e.getMessage());
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 商品协议查询
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYProductionResponseBean querySettle(HisunProductionSettle settle) {
		init();
		SyncGYProductionResponseBean bean = null;
		MerchantProductionInfoQueryRequest request = new MerchantProductionInfoQueryRequest();
		try {
			request.setMerchid(settle.getMerchid());
			request.setBusinesid(settle.getStoreId().toString());
			request.setServiceid(settle.getServiceid());
			request.setContractid(settle.getContractid());
			request.setProductionid(settle.getProductionid());
			request.setProductiontype(settle.getProductiontype());
			// request.setCapitaltype(settle.getCapitalType().toString());
			MerchantProductionInfoQueryResponse response = (MerchantProductionInfoQueryResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYProductionResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMerchid(response.getMerchid());
				bean.setMsg(response.getResponsemessage());
				bean.setStatus(response.getStatus());
				bean.setProductionid(request.getProductionid());
				bean.setSettleId(settle.getId());
			}

		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商品协议查询接口同步失败", e.getMessage());
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 删除商户同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean delStore(Store vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantBaseInfoDeleteRequest request = new MerchantBaseInfoDeleteRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getId().toString());
			request.setServiceid(config.getSyncGyServiceId());
			MerchantBaseInfoDeleteResponse response = (MerchantBaseInfoDeleteResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setMsg(response.getResponsemessage());
				bean.setCode(response.getResponsecode());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，删除商户接口同步失败", e.getMessage());
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 删除商户结算同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean delStorSettle(StoreSettle vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantSettleInfoDeleteRequest request = new MerchantSettleInfoDeleteRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getId().toString());
			request.setServiceid(config.getSyncGyServiceId());
			request.setEffortdate(vo.getEffortDate());
			request.setExpirydate(vo.getExpiryDate());
			MerchantSettleInfoDeleteResponse response = (MerchantSettleInfoDeleteResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setMsg(response.getResponsemessage());
				bean.setCode(response.getResponsecode());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，删除商户结算接口同步失败", e.getMessage());
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 删除商户费率同步
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean delStorFee(StoreFee vo) {
		init();
		SyncGYResponseBean bean = null;
		MerchantFeeInfoDeleteRequest request = new MerchantFeeInfoDeleteRequest();
		try {
			request.setMerchid(vo.getMerchid());
			request.setBusinesid(vo.getId().toString());
			request.setServiceid(config.getSyncGyServiceId());
			request.setEffortdate(vo.getEffortdate());
			request.setExpirydate(vo.getExpirydate());
			request.setCapitaltype(vo.getClearType());
			request.setProductiontype(vo.getProductionType());
			MerchantFeeInfoDeleteResponse response = (MerchantFeeInfoDeleteResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setMsg(response.getResponsemessage());
				bean.setCode(response.getResponsecode());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，删除商户费率接口同步失败", e.getMessage());
			log.error(e.getMessage());
		}
		return bean;
	}

	/**
	 * 商户合同维护-新增
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean syncAddBusiContent(Store store, StoreSettle storeSettle, SysFile sysFile) {
		init();
		SyncGYResponseBean bean = null;
		MerchantContractAddRequest request = new MerchantContractAddRequest();
		try {
			request.setMerchid(storeSettle.getMerchid());
			request.setBusinesid(storeSettle.getStoreId() + "");
			request.setServiceid(config.getSyncGyServiceId());
			request.setContractid(storeSettle.getStoreId() + "");
			request.setContractname(storeSettle.getStoreId() + "");
			request.setContractmain(storeSettle.getStoreId() + "");
			request.setContracttype("02");
			request.setEffdate(TimeStamp.getTime(8));
			request.setExpdate(storeSettle.getExpiryDate());
			request.setServerip(config.getFtpServerIp());
			request.setServerport(config.getFtpServerPort());
			request.setUser(config.getFtpUser());
			request.setPassword(config.getFtpPasswd());
			request.setRemotepath(config.getFtpRemotePath());

			String fileName = sysFile.getFileWebPath();
			if (fileName != null) {
				if (fileName.lastIndexOf("/") > 0) {
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
				}
				request.setFilename(fileName);
			} else {
				request.setFilename("");
			}

			MerchantContractAddResponse response = (MerchantContractAddResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());
			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商户合同维护新增异常", e.getMessage());
			log.error(e.getMessage());
		}

		return bean;
	}

	/**
	 * 商户合同维护-修改
	 * 
	 * @param vo
	 * @return
	 */
	public SyncGYResponseBean syncUpdateBusiContent(Store store, StoreSettle storeSettle, SysFile sysFile) {
		init();
		SyncGYResponseBean bean = null;
		MerchantContractModifyRequest request = new MerchantContractModifyRequest();
		try {
			request.setMerchid(storeSettle.getMerchid());
			request.setBusinesid(storeSettle.getStoreId() + "");
			request.setServiceid(config.getSyncGyServiceId());
			request.setContractid(storeSettle.getStoreId() + "");
			request.setContractname(storeSettle.getStoreId() + "");
			request.setContractmain(storeSettle.getStoreId() + "");
			request.setContracttype("02");
			request.setEffdate(TimeStamp.getTime(8));
			request.setExpdate(storeSettle.getExpiryDate());
			request.setServerip(config.getFtpServerIp());
			request.setServerport(config.getFtpServerPort());
			request.setUser(config.getFtpUser());
			request.setPassword(config.getFtpPasswd());
			request.setRemotepath(config.getFtpRemotePath());

			String fileName = sysFile.getFileWebPath();
			if (fileName != null) {
				if (fileName.lastIndexOf("/") > 0) {
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
				}
				request.setFilename(fileName);
			} else {
				request.setFilename("");
			}

			MerchantContractModifyResponse response = (MerchantContractModifyResponse) SettleInterfaceClient.getInstance()
			        .sendRequest(request, config.getSyncGyUrl());

			if (response != null) {
				bean = new SyncGYResponseBean();
				bean.setCode(response.getResponsecode());
				bean.setMsg(response.getResponsemessage());
			}
		}
		catch (Exception e) {
			logUtils.logAdd("高阳接口，商户合同维护修改异常", e.getMessage());
			log.error(e.getMessage());
		}

		return bean;
	}

}
