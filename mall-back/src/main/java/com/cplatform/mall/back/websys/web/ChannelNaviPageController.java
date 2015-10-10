package com.cplatform.mall.back.websys.web;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.sys.dao.SysTypeDao;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.dao.ChannelNaviPageDao;
import com.cplatform.mall.back.websys.entity.ChannelNaviPage;
import com.cplatform.mall.back.websys.service.ChannelNaviPageService;

/**
 * 导航栏目管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/websys/channel/navipage")
public class ChannelNaviPageController {

	@Autowired
	ChannelNaviPageService channelNaviPageService;

	@Autowired
	ChannelNaviPageDao channelNaviPageDao;

	@Autowired
	SysRegionService regionService;
	
	@Autowired
	LogUtils  logUtils;

	@Autowired
	SysTypeDao sysTypeDao;

	@RequestMapping(value = { "/list", "/", "" })
	public String listChannelNaviPage(ChannelNaviPage channelNaviPage, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		
		model.addAttribute("pageData", channelNaviPageService.findChannelNaviPage(channelNaviPage, page, Page.DEFAULT_PAGESIZE));
		return "/websys/channel/navipage/navipage_list";
	}
	
	@RequestMapping(value = "preAdd")
	public String preAdd(Model model) {
		ChannelNaviPage channelNaviPage = new ChannelNaviPage();
		model.addAttribute("channelMap", channelNaviPage.channelMap);
		model.addAttribute("channelNaviPage", channelNaviPage);
		

		return "/websys/channel/navipage/navipage_add";
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
	public Object add(ChannelNaviPage channelNaviPage, HttpServletRequest request, Model model) throws Exception {
		String msg = "";
		
		if (channelNaviPage.getId() != null) {
			msg = "修改成功！";
			
			channelNaviPage.setUpdateTime( new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			//用户id
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			channelNaviPage.setUpdateUser(sessionUser.getId());
			
			//code 由 code+id组成
//			channelNaviPage.setCode(channelNaviPage.getCode() + "_" + channelNaviPage.getId());
			channelNaviPageService.saveChannelNaviPage(channelNaviPage);
			
		}else{
			
			channelNaviPage.setCreateTime( new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			//用户id
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			channelNaviPage.setCreateUser(sessionUser.getId());
			 msg = "添加成功！";
			 //获取id
//			 ChannelNaviPage ch = channelNaviPageService.saveChannelNaviPage(channelNaviPage);
			 
			 //code 由 code+id组成
//			 ch.setCode(ch.getCode() + "_" + ch.getId());
	
			 channelNaviPageService.saveChannelNaviPage(channelNaviPage);
				logUtils.logAdd(" 导航栏目管理添加","添加成功");
		}

		return JsonRespWrapper.success(msg, "/websys/channel/navipage/list");
	}

	@RequestMapping(value = "preEdit")
	public String preEdit(@RequestParam(value = "id", required = true) Long id, Model model) {

		channelNaviPageService.preEdit(id, model);

		return "/websys/channel/navipage/navipage_add";
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
		channelNaviPageDao.delete(id);
		logUtils.logDelete(" 导航栏目管理删除","删除成功"+id);
		return JsonRespWrapper.success("删除成功！", "/websys/channel/navipage/list");
	}
}
