package com.cplatform.mall.back.giftcard.web;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.giftcard.entity.GiftCard;
import com.cplatform.mall.back.giftcard.service.GiftCardService;
import com.cplatform.mall.back.utils.Constants;
import com.cplatform.mall.back.utils.JsonRespWrapper;

@Controller
@RequestMapping("/giftCard")
public class GiftCardController {

	@Autowired
	private GiftCardService giftCardService;
	
	@RequestMapping("/block/list")
	public String blockList(GiftCard giftCard, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws Exception {
		giftCard.setStatus(Constants.GIFTCARD_STATUS_BLOCK);
		getListModel(giftCard, page, model);
		model.addAttribute("menuBtn", "冻结");
		model.addAttribute("listTitle", "礼品卡冻结列表");
		model.addAttribute("action_type", "block");
		model.addAttribute("btn_value", "冻结");
		return "/giftcard/giftCard_list";
	}
	
	@RequestMapping("/loss/list")
	public String lossList(GiftCard giftCard, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws Exception {
		giftCard.setStatus(Constants.GIFTCARD_STATUS_LOSS);
		getListModel(giftCard, page, model);
		model.addAttribute("menuBtn", "挂失");
		model.addAttribute("listTitle", "礼品卡挂失列表");
		model.addAttribute("action_type", "loss");
		model.addAttribute("btn_value", "挂失");
		return "/giftcard/giftCard_list";
	}
	
	@RequestMapping("/solution/list")
	public String solutionList(GiftCard giftCard, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws Exception {
		giftCard.setStatus(Constants.GIFTCARD_STATUS_ACTIVATE);
		getListModel(giftCard, page, model);
		model.addAttribute("menuBtn", "解挂");
		model.addAttribute("listTitle", "礼品卡解挂列表");
		model.addAttribute("action_type", "solution");
		model.addAttribute("btn_value", "解挂");
		return "/giftcard/giftCard_list";
	}
	
	@RequestMapping(value = "/preAdd")
	public String preAdd(Model model, String type) {
		GiftCard giftCard = new GiftCard();
		if(StringUtils.isNotBlank(type)){
			if(type.equals("block")){
				model.addAttribute("menuBtn", "冻结");
			}else if(type.equals("loss")){
				model.addAttribute("menuBtn", "挂失");
			}else if(type.equals("solution")){
				model.addAttribute("menuBtn", "解挂");
			}
		}
		model.addAttribute("giftCard", giftCard);
		model.addAttribute("action_type", type);
		return "/giftcard/giftCard_add";
	}
	
	@RequestMapping(value = { "/detail" })
	public String viewList(GiftCard giftCard, String type, Model model) throws Exception {
		if(giftCard != null && StringUtils.isNotBlank(giftCard.getSerialNo())){
			model.addAttribute("action_type", type);
			model.addAttribute("giftCardModel", giftCardService.findGiftCard(giftCard.getSerialNo()));
		}
		return "/giftcard/detail";
	}
	
	@RequestMapping(value = "/block/edit")
	@ResponseBody
	public Object blockEdit(GiftCard giftCard) throws SQLException {
		Map<String, Object> map = giftCardService.modifyGiftCard(giftCard, 1, "礼品卡冻结");
		if((Boolean) map.get("result")){
			return JsonRespWrapper.success("冻结成功。", "/giftCard/block/list");
		}
		return JsonRespWrapper.successAlert(map.get("message").toString());
	}
	
	@RequestMapping(value = "/loss/edit")
	@ResponseBody
	public Object lossEdit(GiftCard giftCard) throws SQLException {
		Map<String, Object> map = giftCardService.modifyGiftCard(giftCard, 2, "礼品卡挂失");
		if((Boolean) map.get("result")){
			return JsonRespWrapper.success("挂失成功。", "/giftCard/loss/list");
		}
		return JsonRespWrapper.successAlert(map.get("message").toString());
	}
	
	@RequestMapping(value = "/solution/edit")
	@ResponseBody
	public Object solutionEdit(GiftCard giftCard) throws SQLException {
		Map<String, Object> map = giftCardService.modifyGiftCard(giftCard, 3, "礼品卡解挂");
		if((Boolean) map.get("result")){
			return JsonRespWrapper.success("解挂成功。", "/giftCard/solution/list");
		}
		return JsonRespWrapper.successAlert(map.get("message").toString());
	}
	
	private void getListModel(GiftCard giftCard, int page, Model model) throws Exception{
		model.addAttribute("giftCardList", giftCardService.findGiftCardPage(giftCard, page, Page.DEFAULT_PAGESIZE));
	}
	
	
}
