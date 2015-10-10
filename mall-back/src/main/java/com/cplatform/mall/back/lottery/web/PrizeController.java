package com.cplatform.mall.back.lottery.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.lottery.entity.LotteryActive;
import com.cplatform.mall.back.lottery.entity.Prize;
import com.cplatform.mall.back.lottery.service.LotteryActiveService;
import com.cplatform.mall.back.lottery.service.PrizeService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;

@Controller
@RequestMapping(value = "/lottery/prize")
public class PrizeController {
	@Autowired
	private PrizeService prizeService;
	@Autowired
	private LotteryActiveService lotteryActiveService;
	@Autowired
    private	LogUtils  logUtils;
	/**
	 * 列表展示页面
	 * */
	@RequestMapping(value = "/list")
	public String list(Prize prize, @RequestParam(value = "page", required = false, defaultValue = "1") int page,  Model model) throws SQLException {
		Page<Prize> pageData = prizeService.findPrizeList(prize, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageData);
		model.addAttribute("statusMap",Prize.statusMap);
		return "/lottery/prize/prize-list";
	}
	/**
	 * 进入添加页面
	 * */
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add(Prize prize,Model model) throws SQLException {
		return "/lottery/prize/prize-add";
	}
	/**
	 * 进行添加
	 * */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object save(Prize prize) throws SQLException {
		prizeService.save(prize);
		logUtils.logAdd("添加奖品成功", "");
	 return JsonRespWrapper.success("添加奖品成功。", "/lottery/prize/list");
	}
	/**
	 * 进入活动页面
	 * */
	@RequestMapping(value = "/selectActive")
	public String selectActive(LotteryActive active, @RequestParam(value = "page", required = false, defaultValue = "1") int page,  Model model) throws SQLException {
		Page<LotteryActive> pageData = lotteryActiveService.listLotteryActiveAndConfig(active, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageData);
		return "/lottery/prize/active-select";
	}
	/**
	 * 进入修改页面
	 * */
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id") Long id,@RequestParam(value = "actId") Long actId,Prize prize ,Model model) throws SQLException {
		prize = prizeService.findById(id);
		model.addAttribute("prize", prize);
		LotteryActive active = lotteryActiveService.findById(prize.getActiveId());
		LotteryActive prizeNumValue = lotteryActiveService.findActiveAndConfigById(actId);
		String activeName = active.getName();
		model.addAttribute("activeName",activeName);
		model.addAttribute("prizeNumValue",prizeNumValue.getPrizeNumValue());
		return "/lottery/prize/prize-edit";
	}
	/**
	 * 进行修改
	 * */
	@RequestMapping(value = "/edit/{id}",method = RequestMethod.POST)
	@ResponseBody
	public Object editSave(@PathVariable Long id,Prize prize ,Model model) throws SQLException {
		prize.setId(id);
		prizeService.save(prize);
		logUtils.logModify("奖品修改成功", "");
		 return JsonRespWrapper.success("奖品修改成功。", "/lottery/prize/list");
	}
	/**
	 * 删除
	 * */
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable Long id,Prize prize ,Model model) throws SQLException {
		prizeService.delete(id);
		logUtils.logDelete("奖品删除成功", "");
		return JsonRespWrapper.success("奖品删除成功。", "/lottery/prize/list");
	}
	/**
	 * 查看页面
	 * */
	@RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
	public String view(@PathVariable Long id,Prize prize ,Model model) throws SQLException {
		prize = prizeService.findById(id);
		LotteryActive active = lotteryActiveService.findById(prize.getActiveId());
		String activeName = active.getName();
		model.addAttribute("activeName",activeName);
		model.addAttribute("prize", prize);
		return "/lottery/prize/prize-view";
	}
	/**
	 * 进行ajax判断
	 * @throws SQLException 
	 * @throws IOException 
	 * */
	@RequestMapping(value = "/isAdd",method = RequestMethod.GET)
	@ResponseBody
	public Object isAdd(@RequestParam(value = "activeId") Long activeId,@RequestParam(value = "hitLevel") Long hitLevel,HttpServletResponse response,Model model) throws SQLException, IOException{
		String flag = prizeService.hitLevelIsAdd(activeId, hitLevel);
		return JsonRespWrapper.successAlert(flag);
	}
}
