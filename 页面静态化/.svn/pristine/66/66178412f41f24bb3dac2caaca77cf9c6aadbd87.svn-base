package com.cplatform.mall.back.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.mall.back.cms.model.EventRegister;
import com.cplatform.mall.back.cms.model.GroupTemplate;
import com.cplatform.mall.back.cms.service.EventRegisterService;
import com.cplatform.mall.back.cms.service.GroupTemplateService;
import com.cplatform.mall.back.cms.service.TempGroupService;
import com.cplatform.mall.back.cms.service.WebTemplateService;
import com.cplatform.mall.back.cms.task.StaticizeTask;

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
public class EventController {

	@Autowired
	private EventRegisterService eventRegisterService;

	@Autowired
	private WebTemplateService webTemplateService;

	@Autowired
	private TempGroupService tempGroupService;

	@Autowired
	private ApplicationContext cx;

	@Autowired
	ThreadPoolTaskExecutor threadPool;

	@Autowired
	private WebTemplateService templateService;

	@Autowired
	private GroupTemplateService groupTemplateService;

	@RequestMapping(value = "/template/event/add")
	public String add(HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam("memo") String memo, @RequestParam("code") int code,
			@RequestParam("type") int type, Model model) {
		String tgid = "";
		String tgName = "";
		if (type == EventRegister.EVENT_TYPE_TEMPLATE) {
			tgid = request.getParameter("tid");
			tgName = webTemplateService.get(Integer.valueOf(tgid)).gettName();
		} else {
			tgid = request.getParameter("gid");
			tgName = tempGroupService.get(tgid).getgName();
		}

		eventRegisterService.save(EventRegister.newInstance(name, memo, type,
				tgid, code, tgName));

		return list(model);
	}

	@RequestMapping(value = "/template/event/list")
	public String list(Model model) {

		model.addAttribute("list", eventRegisterService.getAll());
		model.addAttribute("tlist", webTemplateService.getAll());
		model.addAttribute("glist", tempGroupService.getAll());

		return "event";
	}

	// 私有方法，产生任务到工作队列
	private void newTask(String tid) throws Exception {

		StaticizeTask staticizeTask = cx.getBean(StaticizeTask.class);
		staticizeTask.setWebTemplate(templateService.get(Integer.valueOf(tid)));
		threadPool.execute(staticizeTask);
	}

	@RequestMapping(value = "/template/event/call/{code}")
	public String process(@PathVariable int code, Model model) {

		EventRegister eventRegister = eventRegisterService.getByCode(code);
		if (eventRegister != null) {

			try {
				switch (eventRegister.getType()) {

				// 单一模板刷新请求处理
				case EventRegister.EVENT_TYPE_TEMPLATE: {
					newTask(eventRegister.getTgid());
					break;
				}

				// 模板组刷新请求处理
				case EventRegister.EVENT_TYPE_GROUP: {
					List<GroupTemplate> list = groupTemplateService
							.getByGID(eventRegister.getTgid());
					for (GroupTemplate groupTemplate : list) {
						newTask(groupTemplate.getTid());
					}
					break;
				}

				default:
					break;

				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return "";
	}
}
