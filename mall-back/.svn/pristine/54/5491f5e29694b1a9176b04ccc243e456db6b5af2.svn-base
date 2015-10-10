package com.cplatform.mall.back.item.web;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.item.entity.StoreCode;
import com.cplatform.mall.back.item.service.CodeService;
import com.cplatform.mall.back.item.service.ItemManageService;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.util2.TimeStamp;

@Controller
@RequestMapping(value = "item/code/bak")
public class CodeController {

	@Autowired
	private LogUtils logUtils;
	private static final Logger log = Logger.getLogger(ItemManageController.class);
	@Autowired
	private CodeService codeService;

	@Autowired
	private ItemManageService itemManageService;

	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/codelist/{codetype}")
	public String codelist(@PathVariable String codetype, ItemSale itemSale,
	        @RequestParam(value = "page", defaultValue = "1", required = false) int page, Model model) throws SQLException {
		itemSale.setSendCodeChannel(2L);// 只显示第三方通道的商品
		Page<ItemSale> itemSalePage = codeService.listItemSale(itemSale, codetype, page, Page.DEFAULT_PAGESIZE);
		List<Object> countList = new ArrayList<Object>();
		codeService.makeCountList(itemSalePage.getData(), countList, codetype);
		model.addAttribute("pageData", itemSalePage);
		model.addAttribute("countList", countList);
		model.addAttribute("codetype", codetype);
		return "/item/code/saleitem-list";
	}

	@RequestMapping(value = "/importCode/{codetype}", method = RequestMethod.GET)
	public String toImportStoreCodePage(@PathVariable String codetype, @RequestParam("itemId") Long itemId, @RequestParam("storeId") Long storeId,
	        Model model) {
		StoreCode vo = new StoreCode();
		model.addAttribute("code", vo);
		model.addAttribute("itemId", itemId);
		model.addAttribute("storeId", storeId);
		ItemSale itemSale = itemManageService.findItemSaleById(itemId);
		Store store = storeService.findStoreById(storeId);
		model.addAttribute("itemSale", itemSale);
		model.addAttribute("store", store);
		model.addAttribute("codetype", codetype);
		return "/item/code/improt-code";
	}

	@RequestMapping(value = "/importCode/{codetype}", method = RequestMethod.POST)
	@ResponseBody
	public Object importCode(@PathVariable String codetype, MultipartFile uploadfile, @ModelAttribute("code") StoreCode code, Model model,
	        BindingResult result) throws Exception {
		if (uploadfile.isEmpty()) {
			// return JsonRespWrapper.successAlert("请上传码文件");
			result.rejectValue("code", null, "请上传码文件。");
		}
		if (result.hasErrors()) {
			return JsonRespWrapper.error(result.getFieldErrors());
		}
		InputStream in = uploadfile.getInputStream();
		code.setCreateDate(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
		code.setGenerateDate("");
		code.setVerifyDate("");
		code.setOrderId("");
		code.setStatus("100");
		code.setUserId(SessionUser.getSessionUser().getId().toString());
		boolean flag = false;
		if ("storecode".equals(codetype)) {
			try {
				flag = this.codeService.importStoreCode(code, in);
			} catch (Exception e) {
				if(e.toString().indexOf("unique constraint") != -1){
					return JsonRespWrapper.successAlert("该码表已存在，请导入新码！");
				}
				return JsonRespWrapper.successAlert("导入失败！");
			}
			if (flag) {
				return JsonRespWrapper.success("导入成功", "/item/code/storeCodeList?itemId=" + code.getItemId() + "&storeId=" + code.getStoreId());
			} else {
				return JsonRespWrapper.successAlert("导入失败！");
			}
		} else {
			try{
			flag = this.codeService.importCardCode(code, in);
			} catch (Exception e) {
				log.error(e.getMessage());
				if(e.toString().indexOf("unique constraint") != -1){
					return JsonRespWrapper.successAlert("该码表已存在，请导入新码！");
				}
				return JsonRespWrapper.successAlert("导入失败！");
			}
			if (flag) {
				logUtils.logOther("商户码管理", "导入码，商户编号:"+code);
				return JsonRespWrapper.success("导入成功", "/item/code/cardCodeList?itemId=" + code.getItemId() + "&storeId=" + code.getStoreId());
			} else {
				return JsonRespWrapper.successAlert("导入失败！");
			}
		}

	}

	@RequestMapping(value = "/storeCodeList")
	public String storeCodeList(StoreCode vo, @RequestParam("itemId") String itemId, @RequestParam("storeId") String storeId,
	        @RequestParam(value = "page", defaultValue = "1", required = false) int page, Model model) throws SQLException {
		vo.setItemId(itemId);
		vo.setStoreId(storeId);
		Page<StoreCode> pageData = this.codeService.findStoreCodeList(vo, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageData);
		model.addAttribute("statusMap", StoreCode.statusMap);
		return "/item/code/store-code-list";
	}

	@RequestMapping(value = "/cardCodeList")
	public String cardCodeList(StoreCode vo, @RequestParam("itemId") String itemId, @RequestParam("storeId") String storeId,
	        @RequestParam(value = "page", defaultValue = "1", required = false) int page, Model model) throws SQLException {
		vo.setItemId(itemId);
		vo.setStoreId(storeId);
		Page<StoreCode> pageData = this.codeService.findCardCodeList(vo, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageData);
		model.addAttribute("statusMap", StoreCode.statusMap);
		return "/item/code/card-code-list";
	}

	@RequestMapping(value = "/downTemplate/{codetype}")
	public String downTemplate(@PathVariable String codetype, Model model) {
		String downUrl = "";
		if ("storecode".equals(codetype)) {
			downUrl = "/item/code/storecodetemplate";
		} else {
			downUrl = "/item/code/cardcodetemplate";
		}
		return downUrl;
	}
}
