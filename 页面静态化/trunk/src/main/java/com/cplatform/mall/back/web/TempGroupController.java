package com.cplatform.mall.back.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.mall.back.cms.model.GroupTemplate;
import com.cplatform.mall.back.cms.model.TempGroup;
import com.cplatform.mall.back.cms.model.WebTemplate;
import com.cplatform.mall.back.cms.service.GroupTemplateService;
import com.cplatform.mall.back.cms.service.TempGroupService;
import com.cplatform.mall.back.cms.service.WebTemplateService;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午5:07:39
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Controller
public class TempGroupController {

	@Autowired
	private TempGroupService tempGroupService;

	@Autowired
	private WebTemplateService templateService;

	@Autowired
	private GroupTemplateService groupTemplateService;

	@RequestMapping(value = "/template/group/add")
	public String add(@RequestParam("name") String name,
			@RequestParam("memo") String memo, Model model) {

		tempGroupService.save(TempGroup.newInstance(name, memo));

		return list(model);
	}

	@RequestMapping(value = "/template/group/list")
	public String list(Model model) {
		model.addAttribute("list", tempGroupService.getAll());
		return "group";
	}

	@RequestMapping(value = "/template/group/ref/{gid}")
	public String getGroupTemplateRef(@PathVariable("gid") String gid,
			Model model) {

		List<WebTemplate> tlist = templateService.getAll();

		List<GroupTemplate> glist = groupTemplateService.getByGID(gid);

		List<WebTemplate> rlist = new ArrayList<WebTemplate>();

		for (GroupTemplate groupTemplate : glist) {

			for (WebTemplate webTemplate : tlist) {

				if (groupTemplate.getTid().equals(webTemplate.getId())) {

					rlist.add(webTemplate);
				}

			}

		}

		tlist.removeAll(rlist);

		model.addAttribute("lp", tlist);
		model.addAttribute("rp", rlist);
		model.addAttribute("group", tempGroupService.get(gid));
		return "grouptempl";
	}

	@RequestMapping(value = "/template/group/ref/update")
	public String updateRef(@RequestParam("gid") String gid,
			@RequestParam("tids") String tids, Model model) {

		groupTemplateService.deleteByGroupId(gid);

		String[] idArray = tids.split(",");

		for (String tid : idArray) {
			groupTemplateService.save(GroupTemplate.newInstance(gid, tid));

		}

		return list(model);
	}
}
