package com.cplatform.mall.back.giftcard.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.giftcard.entity.GiftCard;
import com.cplatform.mall.back.giftcard.entity.GiftCardStorageExt;
import com.cplatform.mall.back.giftcard.entity.GiftRequired;
import com.cplatform.mall.back.giftcard.service.GiftStockService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.ReadExcel;
import com.google.common.collect.Maps;

/**
 * 
 * @author mudeng
 * 礼品卡库存管理Controller
 *
 */
@Controller
@RequestMapping("/giftcard/stock")
public class GiftStockController {

	@Autowired
	GiftStockService stockService;

	/**
	 * 
	 * @param giftRequired
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 * 展示成品卡批次列表
	 */
	@RequestMapping(value = { "/list", "/", "" })
	public String list(GiftRequired giftRequired, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		try {
			model.addAttribute("pageData", stockService.findGiftRequired(giftRequired, page, Page.DEFAULT_PAGESIZE));
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "/giftcard/stock/stock-list";
	}
	@RequestMapping(value = { "/selectBatchNolist" })
	public String selectBatchNolist(GiftRequired giftRequired, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		try {
			model.addAttribute("pageData", stockService.selectBatchNolist(giftRequired, page, Page.DEFAULT_PAGESIZE));
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "/giftcard/stock/select-batchno-list";
	}
	@RequestMapping(value = { "/linklist" })
	public String linkList(GiftRequired giftRequired, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		try {
			model.addAttribute("pageData", stockService.linkList(giftRequired, page, Page.DEFAULT_PAGESIZE));
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "/giftcard/stock/linkList";
	}
	/**
	 * 
	 * @param giftCard
	 * @param page
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * 展示成品卡列表
	 */
	@RequestMapping(value = { "/cardList" })
	public String listCard(GiftCard giftCard, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request) {
		String flag = "1";
		if(request.getParameter("batchNo")!=null&&request.getParameter("batchNo")!=""){
			giftCard.setBatchNo(Long.valueOf(request.getParameter("batchNo")));
		}
		if(request.getParameter("flag")!=null&&request.getParameter("flag")!=""){
			flag = request.getParameter("flag");
		}
		try {
			model.addAttribute("pageData", stockService.findGiftCardPage(giftCard, page, Page.DEFAULT_PAGESIZE,flag));
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "/giftcard/stock/card-list"+flag;
	}
	
	/**
	 * 
	 * @param giftCardStorageExt
	 * @param page
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * 展示流水列表
	 */
	@RequestMapping(value = { "/detailList" })
	public String detailList(GiftCardStorageExt giftCardStorageExt, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request){
		if(request.getParameter("batchNo")!=null&&request.getParameter("batchNo")!=""){
			giftCardStorageExt.setBatchNo(Long.valueOf(request.getParameter("batchNo")));
		}
		try {
			model.addAttribute("pageData", stockService.findGiftExtPage(giftCardStorageExt, page, Page.DEFAULT_PAGESIZE));
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "/giftcard/stock/detail-list";
	}
	
	/**
	 * 
	 * @param batchNo
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * 全部入库功能
	 */
	@RequestMapping(value = "/stockIn")
	@ResponseBody
	public Object stockIn(@RequestParam Long batchNo, Model model, HttpServletRequest request){
		String msg="入库成功";
		try {
			msg = stockService.stockIn(batchNo,request);
		} catch (Exception e) {
//			e.printStackTrace();
			msg = "入库异常，请联系管理员！";
		}
		return JsonRespWrapper.success(msg, "/giftcard/stock/list");
	}
	@RequestMapping(value = "/linkItem")
	@ResponseBody
	public Object linkItem(@RequestParam Long batchNo,@RequestParam Long itemId, Model model, HttpServletRequest request){
		HashMap<String, Object> map = Maps.newHashMap();
		try {
			GiftRequired giftRequired = new GiftRequired();
			giftRequired.setSaleId(itemId);
			List<GiftRequired> giftRequiredList = stockService.giftRequiredList(giftRequired);
			if(giftRequiredList.size()>0){
				map.put("success", false);
				map.put("message", "该商品已经关联礼品卡，不能再次关联！");
				return map;
			}else{
				stockService.linkItem(batchNo, itemId);
				map.put("success", true);
				return map;
			}
		} catch (Exception e) {
			map.put("success", "error");
			map.put("message", "程序异常，请联系管理员！");
			return map;
		}
	}
	
	/**
	 * 
	 * @param serialNo
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * 可多选批量出库功能
	 */
	@RequestMapping(value = "/cardStockOut")
	@ResponseBody
	public Object cardStockOut(@RequestParam String serialNo, Model model, HttpServletRequest request){
		String[] serialNos = serialNo.split(",");
		String backUrl = request.getParameter("backUrl");
		String msg = "出库成功";
		try {
			msg = stockService.cardStockOut(serialNos,request,"0");
		} catch (Exception e) {
//			e.printStackTrace();
			msg = "出库异常，请联系管理员！";
		}
		return JsonRespWrapper.success(msg, backUrl+"&flag=2");
	}
	
	/**
	 * 
	 * @param serialNo
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * 可多选批量入库功能
	 */
	@RequestMapping(value = "/cardStockIn")
	@ResponseBody
	public Object cardStockIn(@RequestParam String serialNo, Model model, HttpServletRequest request) {
		String[] serialNos = serialNo.split(",");
		String backUrl = request.getParameter("backUrl");
		String msg = "入库成功";
		try {
			msg = stockService.cardStockIn(serialNos,request,"0");
		} catch (Exception e) {
//			e.printStackTrace();
			msg = "入库异常，请联系管理员！";
		}
		return JsonRespWrapper.success(msg, backUrl+"&flag=1");
	}
	
	/**
	 * 
	 * @param batchNo
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * 全部出库功能
	 */
	@RequestMapping(value = "/stockOut")
	@ResponseBody
	public Object stockOut(@RequestParam Long batchNo, Model model, HttpServletRequest request) {
		String msg = "出库成功";
		try {
			msg = stockService.stockOut(batchNo,request);
		} catch (Exception e) {
//			e.printStackTrace();
			msg = "出库异常，请联系管理员！";
		}
		return JsonRespWrapper.success(msg, "/giftcard/stock/list");
	}
	
	/**
	 * 
	 * @param uploaditemfile
	 * @param request
	 * @return
	 * 跳转到excel导入页面
	 */
	@RequestMapping(value = "/preImportExcel")
	public String preImportExcel( Model model, HttpServletRequest request) {
		String flag = "1";
		String batchNo = "";
		if(request.getParameter("flag")!=null&&!"".equals(request.getParameter("flag"))){
			flag = request.getParameter("flag");
		}
		if(request.getParameter("batchNo")!=null&&!"".equals(request.getParameter("batchNo"))){
			batchNo = request.getParameter("batchNo");
		}
		model.addAttribute("flag",flag);
		model.addAttribute("batchNo",batchNo);
		return "/giftcard/stock/importExcel";
	}
	
	/**
	 * 
	 * @param uploaditemfile
	 * @param request
	 * @return
	 * excel导入功能
	 */
	@RequestMapping(value = "importExcel")
	@ResponseBody
	public Object importExcel(MultipartFile uploaditemfile, HttpServletRequest request) {
		String flag = "1";
		String inoutFlag  =  "0";
		int cardSum = 0;
		String batchNo = "";
		if(request.getParameter("flag")!=null&&!"".equals(request.getParameter("flag"))){
			flag = request.getParameter("flag");
		}
		if(request.getParameter("cardSum")!=null&&!"".equals(request.getParameter("cardSum"))){
			cardSum = Integer.valueOf(request.getParameter("cardSum"));
		}
		if(request.getParameter("inoutFlag")!=null&&!"".equals(request.getParameter("inoutFlag"))){
			inoutFlag = request.getParameter("inoutFlag");
			
		}
		if(request.getParameter("batchNo")!=null&&!"".equals(request.getParameter("batchNo"))){
			batchNo = request.getParameter("batchNo");
		}
		if (uploaditemfile != null && uploaditemfile.getOriginalFilename() != null && !"".equals(uploaditemfile.getOriginalFilename())) {
			if (uploaditemfile.getOriginalFilename().indexOf(".xlsx") < 0 && uploaditemfile.getOriginalFilename().indexOf(".xls") < 0) {
				return JsonRespWrapper.successReload("上传文件格式不正确！");
			}
		} else {
			uploaditemfile = null;
		}
		String filepath = request.getParameter("filepath");
		String msg = "入库成功！";
		
		try {
			if("1".equals(flag)){
				if(uploaditemfile==null){
					return JsonRespWrapper.successAlert("没有文件上传");
				}
				String[] serialNos = ReadExcel.getCardData(uploaditemfile.getInputStream(), 1, filepath);
				msg = stockService.cardStockIn(serialNos, request,flag);
			}else{
				if("1".equals(inoutFlag)){
					if(uploaditemfile==null){
						return JsonRespWrapper.successAlert("没有文件上传");
					}
					String[] serialNos = ReadExcel.getCardData(uploaditemfile.getInputStream(), 1, filepath);
					msg = stockService.cardStockOut(serialNos, request,flag);
				}else{
					msg = stockService.cardStockOutBySum(request,cardSum,batchNo);
				}
			}
		}
		catch (Exception e) {
			JsonRespWrapper.error("操作异常：" + e.getMessage());
			e.printStackTrace();
			msg = "操作异常,请联系管理员!";
		}
		return JsonRespWrapper.successAlert(msg);
	}
	
	@RequestMapping(value = "exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		stockService.exportExcel(request, response);
	}
}
