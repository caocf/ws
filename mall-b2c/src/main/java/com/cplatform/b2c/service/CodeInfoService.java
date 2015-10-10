package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.model.CodeInfo;
import com.cplatform.b2c.repository.CodeInfoDao;
import com.cplatform.verifycode.CodeServiceClient;
import com.cplatform.verifycode.RevokeReqInfo;
import com.cplatform.verifycode.RevokeRespInfo;

/**
 * 码处理逻辑. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-20 上午9:42:05
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class CodeInfoService {

	private final Logger logger = Logger.getLogger(CodeInfoService.class);

	@Autowired
	private CodeInfoDao codeInfoDao;

	@Autowired
	private CodeServiceClient verifyClient;

	/**
	 * 根据码表ID查找
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public CodeInfo findCodeById(String id) throws SQLException {
		return codeInfoDao.findCodeById(id);
	}

	/**
	 * 订单编号
	 * 
	 * @param actOrderId
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listValidCodesByActOrderId(Long actOrderId) throws SQLException {
		return codeInfoDao.listValidCodesByActOrderId(actOrderId);
	}

	/**
	 * 拉手退款单退码
	 * 
	 * @param actOrderId
	 *            支付订单编号
	 * @throws Exception
	 */
	public void refundVerifyCodeLS(Long actOrderId) throws Exception {
		List<CodeInfo> codeInfos = this.listCodesByActOrderId(actOrderId);
		logger.info("商品订单数量:" + codeInfos.size());
		for (CodeInfo code : codeInfos) {
			this.refundVerifyCodeByItemOrderId(code);
		}
	}

	/**
	 * 根据业务订单号查询码（只包含商品订单号、业务订单号、发码平台字段）
	 * 
	 * @param actOrderId
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listCodesByActOrderId(Long actOrderId) throws SQLException {
		return codeInfoDao.listCodesByActOrderId(actOrderId);
	}

	/**
	 * 根据业务订单号查询码
	 * 
	 * @param actOrderId
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listOrderCodesByActOrderId(Long actOrderId) throws SQLException {
		return codeInfoDao.listOrderCodesByActOrderId(actOrderId);
	}

	/**
	 * 查询订单中虚拟商品的验证码
	 * 
	 * @param storeCode
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listStoreCodes(CodeInfo codeInfo) throws SQLException {
		return codeInfoDao.listStoreCodes(codeInfo);
	}

	/**
	 * 拉手退码接口
	 * 
	 * @param codeInfo
	 *            码实体类
	 * @return
	 */
	public RevokeRespInfo refundVerifyCodeByItemOrderId(CodeInfo codeInfo) {
		RevokeReqInfo request = new RevokeReqInfo();
		request.setCode(codeInfo.getItemOrderId() + "");// 商品订单号
		request.setCodeType(RevokeReqInfo.CodeType.OrderNo);
		request.setPlatform_id(codeInfo.getPlatformId());// 码平台
		RevokeRespInfo response = null;
		try {
			response = verifyClient.revoke(request);
		}
		catch (Exception e) {
			logger.error("调用退码接口异常:" + e.getMessage());
		}
		logger.info("调用退码接口返回信息：" + response.toString());
		return response;
	}

	/**
	 * 退码通用方法（拉手不用）
	 * 
	 * @param codeInfo
	 *            码实体类
	 * @return
	 */
	public RevokeRespInfo refundVerifyCode(CodeInfo codeInfo) {
		RevokeReqInfo request = new RevokeReqInfo();
		request.setCode(codeInfo.getOrderId());// 码编号
		request.setCodeType(RevokeReqInfo.CodeType.OrderNo);
		// request.setfExtReqInfo(null);
		request.setPlatform_id(codeInfo.getPlatformId());// 码平台
		// request.setStoreId(verifycode.get("store_id"));
		RevokeRespInfo response = null;
		try {
			response = verifyClient.revoke(request);
		}
		catch (Exception e) {
			logger.error("调用退码接口异常:" + e.getMessage());
		}
		logger.info("调用退码接口返回信息：" + response.toString());
		return response;
	}

}
