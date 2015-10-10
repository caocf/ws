package com.cplatform.b2c.welfare.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.DateUtils;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.b2c.util.PathUtil;
import com.cplatform.b2c.welfare.dao.WelFareDao;
import com.cplatform.b2c.welfare.entity.AddressInfoModel;
import com.cplatform.b2c.welfare.entity.GoodsModel;
import com.cplatform.b2c.welfare.entity.MobileModel;
import com.cplatform.b2c.welfare.entity.OrderCenterJsonModel;
import com.cplatform.b2c.welfare.entity.WelFareDetailVo;
import com.cplatform.b2c.welfare.entity.WelFareLogOrderModel;
import com.cplatform.b2c.welfare.entity.WelFareModel;
import com.cplatform.b2c.welfare.entity.WelFareStock;
import com.cplatform.b2c.welfare.util.Constants;
import com.cplatform.b2c.welfare.util.Pagination;
import com.cplatform.dbhelp.page.ListPage;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-1 下午01:44:21
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class WelFareService {

	@Autowired
	private WelFareDao welFareDao;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private PathUtil pathUtil;

	/**
	 * 获取商品所有颜色
	 * 
	 * @return
	 */
	public List<String> AllDetail(int itemType, String type) {
		return welFareDao.AllTypeDetail(itemType, type);
	}

	/**
	 * 获取所有尺码
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> allTypes(int itemType) throws SQLException {
		return welFareDao.allTypes(itemType);
	}

	/**
	 * 获取所有备注
	 * 
	 * @return
	 */
	/*
	 * public List<String> AllRemarks() { return welFareDao.AllRemarks(); }
	 */

	/**
	 * 获取所有品牌
	 * 
	 * @return
	 */
	public List<String> AllBrand(int itemType) {
		return welFareDao.AllBrands(itemType);
	}

	/**
	 * 查询所有商品
	 * 
	 * @param request
	 * @param model
	 * @param pageNo
	 * @return
	 */
	public Pagination getwelFares(HttpServletRequest request, WelFareModel model, int pageNo) {
		Pagination pagination = null;
		String tempImg = null;
		// 获取商品个数
		int count = welFareDao.getWelFaresCount(model);

		// 计算开始数与结束数
		int startNo = (pageNo - 1) * Constants.PAGE_SIZE;
		int endNo = startNo + Constants.PAGE_SIZE;

		// 分页查询商品
		List<WelFareModel> lst = welFareDao.getwelFares(model, startNo, endNo);

		for (WelFareModel tempModel : lst) {
			tempImg = transImg(tempModel.getId(), tempModel.getImgPath());
			tempModel.setImgPath(tempImg);
		}
		// 遍历更改图片路径

		// 设置分页对象参数
		pagination = new Pagination(request, count, Constants.PAGE_SIZE, pageNo, lst, appConfig.getImgSvrHost());
		return pagination;
	}

	/**
	 * 根据id 获取商品详情
	 * 
	 * @param id
	 * @return
	 */
	public WelFareDetailVo getDetailByItemIdAndItemType(int itemId, int itemType) {
		WelFareDetailVo model = welFareDao.getDetailByItemIdAndItemType(itemId, itemType);
		WelFareModel tempModel = model.getModel();
		tempModel.setImgPath(transImg(tempModel.getId(), tempModel.getImgPath()));
		return model;
	}

	/**
	 * 根据id获取商品介绍
	 * 
	 * @param id
	 * @return
	 */
	public WelFareModel getRemark(Integer id) {
		return welFareDao.getRemark(id);
	}

	/**
	 * 
	 */
	public WelFareStock getStockBy4Param(Integer itemId, String itemColor, String ItemSize, Integer itemType) {
		return welFareDao.getStockBy4Param(itemId, itemColor, ItemSize, itemType);
	}

	public List<WelFareStock> getColorsBySize(Integer itemId, String ItemSize, Integer itemType) {
		return welFareDao.getColorsBySize(itemId, ItemSize, itemType);
	}

	public List<WelFareStock> getSizeByColor(Integer itemId, String ItemColor, Integer itemType) {
		return welFareDao.getSizeByColor(itemId, ItemColor, itemType);
	}

	/**
	 * 创建订单
	 * 
	 * @param itemId
	 * @param itemColor
	 * @param ItemSize
	 * @throws Exception
	 */
	@Transactional
	public Object createOrder(Long userId, String mobile, WelFareModel welfareModel) throws Exception {

		Object orderId = null;
		Long stockId = welfareModel.getStockId();
		Long itemId = welfareModel.getId();
		Integer itemType = welfareModel.getItemType();
		// 确认数据的准确性及库存剩余量
		welFareDao.updateStore(stockId);

		/** 根据手机号获取用户的地址等信息 */
		MobileModel mobileModel = welFareDao.getMobileModelById(new Long(mobile));
		// 获取序列
		long sequence = welFareDao.getSequence();

		/**
		 * 备注
		 */
		String orderRemark = String.valueOf(sequence);
		/**
		 * 收件人姓名
		 */
		AddressInfoModel addressInfo = new AddressInfoModel();
		addressInfo.setRECEIVER_NAME(mobileModel.getName());
		addressInfo.setADDRESS(mobileModel.getAddress());
		addressInfo.setMOBILE(mobile);
		addressInfo.setZIPCODE("215000");
		String remark = null;
		remark = mobileModel.getCompany() + mobileModel.getDepartment();
		addressInfo.setREMARK(remark);
		/*
		 * 订单标题,使用商品名称
		 */
		String sUBJECT = welfareModel.getName();

		List<GoodsModel> gOODS = new ArrayList<GoodsModel>();
		GoodsModel goodsModel = new GoodsModel();

		goodsModel.setGOOD_ID(itemId);
		goodsModel.setCOUNT(1);
		goodsModel.setDISCOUNT(0);
		gOODS.add(goodsModel);

		String result = doHttpPost(gOODS, sUBJECT, userId, addressInfo, orderRemark);
		JSONObject resultJson = JSONObject.fromObject(result);
		if (resultJson != null) {
			Object msg = resultJson.get("MSG");

			if (msg != null && msg.toString().toLowerCase().equals("success")) {
				orderId = resultJson.get("ORDER_ID");
				/*
				 * 记录日志表
				 */
				WelFareLogOrderModel model = new WelFareLogOrderModel();
				model.setId(sequence);
				model.setQuantity(1);
				model.setRemark(DateUtils.getCurrentTimeStampString3() + " stockId:" + stockId + " MSG: " + msg + " orderId:" + orderId + " "
				        + welfareModel.getItemColor() + " " + welfareModel.getItemSize() + " " + welfareModel.getItemType());
				model.setStockId(stockId);
				model.setType(0);
				welFareDao.addLogOrderInfo(model);
			} else {
				throw new RuntimeException("生成订单失败, 返回状态为:" + msg);
			}
		} else {
			throw new RuntimeException("生成订单提交参数服务器无响应");
		}

		return orderId;

	}

	/**
	 * 根据具体参数判断商品是否存在和库存量
	 * 
	 * @param stockId
	 * @param itemId
	 * @param color
	 * @param size
	 * @param itemType
	 * @return
	 */
	public WelFareModel confirm(Integer stockId, Integer itemId, String color, String size, Integer itemType) {
		WelFareModel welfareModel = welFareDao.confirm(stockId, itemId, color, size, itemType);
		return welfareModel;
	}

	/**
	 * 发送生成订单报文
	 * 
	 * @param aDDRESSINFO
	 *            地址
	 * @param gOODS
	 *            商品信息
	 * @param sUBJECT
	 *            订单标题
	 * @param uID
	 *            用户id
	 * @param uSERREMARK
	 *            备注
	 * @param mOBILE
	 * @param pHONE
	 * @return
	 */
	public String doHttpPost(List<GoodsModel> gOODS, String sUBJECT, Long uID, AddressInfoModel addressInfo, String orderRemark) {

		String url = appConfig.getCreateOrderUri();
		/**
		 * 生成json
		 */
		Long cREATESOURCE = 1L;

		OrderCenterJsonModel orderJsonMoel = new OrderCenterJsonModel();

		orderJsonMoel.setADDRESS_INFO(addressInfo);
		orderJsonMoel.setCREATE_SOURCE(cREATESOURCE);
		orderJsonMoel.setGOODS(gOODS);
		/**
		 * 订单类型是10
		 */
		orderJsonMoel.setORDER_TYPE("10");
		orderJsonMoel.setSUBJECT(sUBJECT);
		orderJsonMoel.setU_ID(uID);
		orderJsonMoel.setUSER_REMARK(orderRemark);
		JSONObject json = JSONObject.fromObject(orderJsonMoel);
		String result = null;
		Integer manager_timeout = 1000;
		Integer so_timeout = 1000;
		String requestStr = json.toString().replaceFirst("u_ID", "U_ID");
		try {
			result = HttpClientUtils.httpPost(url, requestStr, manager_timeout, so_timeout);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用订单接口失败", e);
		}
		return result;

	}

	/**
	 * 检查手机号是否允许进入
	 * 
	 * @param mobile
	 * @return
	 */
	public boolean checkMobile(String mobile) {
		List<String> mobiles = getMobiles();
		boolean flag = false;
		for (String str : mobiles) {
			if (str.equals(mobile)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 获取手机号
	 * 
	 * @return
	 */
	private List<String> getMobiles() {

		List<String> mobiles = null;
		mobiles = Constants.MOBILES;
		long nowTime = new Date().getTime();
		if (mobiles == null || mobiles.size() == 0 || nowTime - Constants.REFRESH_TIME > 1000 * 60 * 10) {
			Constants.MOBILES = welFareDao.getMobiles();
			mobiles = Constants.MOBILES;
			Constants.REFRESH_TIME = nowTime;
		}
		return mobiles;

	}

	/**
	 * 转换图片路径
	 * 
	 * @param id
	 * @return
	 */
	private String transImg(Long id, String imgName) {
		return appConfig.getImgSvrHost() + pathUtil.getPathById(2, id) + "N1/" + imgName;
	}

	/**
	 * 劳保活动列表
	 * 
	 * @param typeId
	 * @param brand
	 * @param color
	 * @param size
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ListPage<Map<String, Object>> goodsList(WelFareModel welFaremodel, int page, int pageSize) throws SQLException {
		return welFareDao.getList(welFaremodel, page, pageSize);
	}

	/**
	 * 劳保活动列表(颜色和尺码都存在)
	 * 
	 * @param typeId
	 * @param brand
	 * @param color
	 * @param size
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ListPage<Map<String, Object>> goodsListTwo(WelFareModel welFaremodel, int page, int pageSize) throws SQLException {
		return welFareDao.getListTwo(welFaremodel, page, pageSize);
	}

}
