package com.cplatform.mall.back.websys.web;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.dao.ChannelNaviOperDao;
import com.cplatform.mall.back.websys.dao.ChannelNaviPageDao;
import com.cplatform.mall.back.websys.entity.ChannelNaviOper;
import com.cplatform.mall.back.websys.service.ChannelNaviOperService;

/**
 * 导航栏目配置
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/websys/channel/navioper")
public class ChannelNaviOperController {

	@Autowired
	ChannelNaviOperService channelNaviOperService;

	@Autowired
	ChannelNaviOperDao channelNaviOperDao;
	@Autowired
	private LogUtils  logUtils;

	@Autowired
	SysRegionService regionService;
	
	@Autowired
	ChannelNaviPageDao channelNaviPageDao;

	@RequestMapping(value = { "/list", "/", "" })
	public String listChannelNaviPage(ChannelNaviOper channelNaviOper, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		
		model.addAttribute("pageData", channelNaviOperService.findChannelNaviOper(channelNaviOper, page, Page.DEFAULT_PAGESIZE));
		return "/websys/channel/navigationOper/navigationOper_list";
	}
	
	@RequestMapping(value = "preAdd")
	public String preAdd(Model model) {
		ChannelNaviOper channelNaviOper = new ChannelNaviOper();
		//地域默认为全国
		channelNaviOper.setRegionCode("0");
		channelNaviOper.setRegionName("全国");
		model.addAttribute("channelMap", channelNaviOper.channelMap);
		model.addAttribute("channelNaviOper", channelNaviOper);
		model.addAttribute("method", "add");
		//添加pagecode选项
		model.addAttribute("pageCodeList",channelNaviPageDao.findChannelNaviPage());
		

		return "/websys/channel/navigationOper/navigationOper_add";
	}

	/**
	 * 添加
	 * 
	 * @param channelCatalog
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(ChannelNaviOper channelNaviOper, HttpServletRequest request, Model model) throws Exception {
		String msg = "";
		//地域不选，默认为全部  0
		if (StringUtils.isBlank(channelNaviOper.getRegionCode()))
		{
			channelNaviOper.setRegionCode("0");
		}
		if (channelNaviOper.getId() != null) 
		{
			msg = "修改成功！";
			channelNaviOper.setUpdateTime( new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			//用户id
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			channelNaviOper.setUpdateUser(sessionUser.getId());
			
			//code 由 code+id组成
//			channelNaviOper.setCode(channelNaviOper.getCode() + "_" + channelNaviOper.getId());		
			
			Long pageId = channelNaviPageDao.findChannelNaviPageId(channelNaviOper.getPageCode());
			channelNaviOper.setPageId(pageId);
			logUtils.logAdd("添加地域","添加成功,地域编号:"+channelNaviOper.getId());
			channelNaviOperService.saveChannelNaviOper(channelNaviOper);
			
			
		}
		else
		{
			msg = "添加成功！";
			 
			//创建时间
			channelNaviOper.setCreateTime( new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			//用户id
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			channelNaviOper.setCreateUser(sessionUser.getId());
				
			//code 由 code+id组成
//			ChannelNaviOper cn =  channelNaviOperService.saveChannelNaviOper(channelNaviOper);
//			cn.setCode(cn.getCode() + "_" + cn.getId());			
			//根据code查id
			Long pageId = channelNaviPageDao.findChannelNaviPageId(channelNaviOper.getPageCode());
			channelNaviOper.setPageId(pageId);
			logUtils.logAdd("添加地域","添加成功,地域编号:"+channelNaviOper.getId());
			channelNaviOperService.saveChannelNaviOper(channelNaviOper);
		}

		return JsonRespWrapper.success(msg, "/websys/channel/navioper/list");
	}

	@RequestMapping(value = "preEdit")
	public String preEdit(@RequestParam(value = "id", required = true) Long id, Model model) {

		channelNaviOperService.preEdit(id, model);

		return "/websys/channel/navigationOper/navigationOper_add";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object deleteNaviPage(@RequestParam Long id, Model model) {
		channelNaviOperDao.delete(id);
		logUtils.logDelete("删除地域","删除成功,地域编号:"+id);
		return JsonRespWrapper.success("删除成功！", "/websys/channel/navioper/list");
	}
}
