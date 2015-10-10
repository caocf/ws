package com.cplatform.mall.back.giftcard.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.giftcard.entity.GiftCardCbHandle;
import com.cplatform.mall.back.giftcard.entity.GiftRequired;
import com.cplatform.mall.back.giftcard.service.GiftCardExchangeService;

/**
 * @Title	卡兑换控制层
 * @Description
 * @Copyright: Copyright (c) 2013-9-26
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Controller
@RequestMapping(value = "/gift/exchange")
public class GiftCardExchangeController {
	@Autowired
	private GiftCardExchangeService giftCardExchangeService;
	
	/**
	 * 礼品卡兑换记录列表
	 * @param giftRequired
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/exchangelist")
	public String exchangeList(GiftRequired giftRequired,@RequestParam(value = "page", defaultValue = "1", required = false) int page, Model model) throws SQLException {
		Page<GiftRequired> pageData = giftCardExchangeService.exchangeList(giftRequired, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageData);
		return "/giftcard/exchange/giftRequired-exchangeList";
	}
	
	/**
	 * 查看卡需求详细兑换记录列表
	 * @param giftCardCbHandle
	 * @param batchNo
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String exchangeView(GiftCardCbHandle giftCardCbHandle,
			Long batchNo,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			HttpServletRequest request,
			Model model) throws SQLException {
		giftCardCbHandle.setBatchNo(batchNo);
		String backUrl=request.getParameter("backUrl");
		Page<GiftCardCbHandle> pageData=giftCardExchangeService.exchangeViewList(giftCardCbHandle,  page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageData);
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("cardStatusMap", GiftCardCbHandle.cardStatusMap);
		model.addAttribute("statusMap", GiftCardCbHandle.statusMap);
		model.addAttribute("backUrl", backUrl);
		return "/giftcard/exchange/exchange-viewList";
	}
	
	
	/**
	 * 导出Excell
	 * @param batchNo	批次号
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public void download(Long batchNo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		giftCardExchangeService.download(batchNo,request, response);
	}

}
