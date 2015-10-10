package com.cplatform.mall.back.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.back.cms.model.EventRegister;
import com.cplatform.mall.back.cms.model.GroupTemplate;
import com.cplatform.mall.back.cms.service.EventRegisterService;
import com.cplatform.mall.back.cms.service.GroupTemplateService;
import com.cplatform.mall.back.cms.service.WebTemplateService;
import com.cplatform.mall.back.cms.task.SyncStaticizeTask;

/**
 * 接收Event的Controller
 * <p>
 * doPost方法接收事件中心传递过来的更新事件。
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
public class SyncEventController {

	@Autowired
	private EventRegisterService eventRegisterService;

	@Autowired
	ThreadPoolTaskExecutor threadPool;

	@Autowired
	private WebTemplateService templateService;

	@Autowired
	private GroupTemplateService groupTemplateService;

	@Autowired
	private SyncStaticizeTask syncStaticizeTask;

	/** CACHE_SIZE */
	private static final int CACHE_SIZE = 20000;

	/** cache */
	private Map<String, String> cache = new LinkedHashMap<String, String>() {

		/** serialVersionUID */
		private static final long serialVersionUID = -5819357707918092807L;

		@Override
		public String get(Object key) {
			String value = super.get(key);
			if (value != null) {
				remove(key);
				put((String) key, value);
			}
			return value;
		}

		@Override
		protected boolean removeEldestEntry(
				java.util.Map.Entry<String, String> eldest) {
			return size() > CACHE_SIZE;
		};
	};

	/** 日志记录器 */
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 检查事务ID是否已处理
	 * 
	 * @param transactionId
	 *            事务ID
	 * @return 检查是否通过
	 */
	@SuppressWarnings("unused")
	private synchronized boolean checkTransactionId(String transactionId) {
		try {
			String v = cache.get(transactionId);
			if (v == null) {
				cache.put(transactionId, transactionId);
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			logger.error("处理异常", ex);
			return true;
		}
	}

	// 私有方法，产生任务到工作队列
	private String newTask(int tid, String event, int int_IsPreview)
			throws Exception {

		return syncStaticizeTask.run(templateService.get(tid), event,
				int_IsPreview);
	}

	/**
	 * 处理时间
	 * 
	 * @param eventType
	 *            事件类型
	 * @param event
	 *            事件对象的Json, 例如
	 * @throws Exception
	 *             异常
	 */
	protected String doEvent(int code, String event, int int_IsPreview)
			throws Exception {

		EventRegister eventRegister = eventRegisterService.getByCode(code);
		String result = "";
		if (eventRegister != null) {

			try {
				switch (eventRegister.getType()) {

				// 单一模板刷新请求处理
				case EventRegister.EVENT_TYPE_TEMPLATE: {
					result = newTask(Integer.valueOf(eventRegister.getTgid()),
							event, int_IsPreview);
					break;
				}

				// 模板组刷新请求处理
				case EventRegister.EVENT_TYPE_GROUP: {
					List<GroupTemplate> list = groupTemplateService
							.getByGID(eventRegister.getTgid());
					for (GroupTemplate groupTemplate : list) {
						result = newTask(
								Integer.valueOf(groupTemplate.getTid()), event,
								int_IsPreview);
					}
					break;
				}

				default:
					break;

				}
			} catch (Exception e) {
				logger.error(" 异常:" + e.toString());
				// e.printStackTrace();
				throw e;
			}
		} else {

			logger.info("无效的事件标识：" + code);
			result = "{RET:1,MSG:'无效的事件标识'" + code + "}";
		}
		return result;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/template/event/sync/")
	@ResponseBody
	public String doPost(HttpServletRequest req, HttpServletResponse resp) {

		String transactionId = req.getHeader("event-transaction-id");
		String result = "";
		String preview = req.getHeader("preview");
		int is_preview = 0;
		if (!StringUtils.isEmpty(preview))
			is_preview = Integer.parseInt(preview);

		try {

			int eventTypeId = Integer.parseInt(req.getHeader("event-type-id"));

			String event = IOUtils.toString(req.getInputStream(), "UTF-8");
			/*
			 * if (logger.isDebugEnabled()) { logger.debug(req.getRemoteAddr() +
			 * ", " + eventTypeId + ", " + event + ", " + transactionId); } //
			 * if (transactionId == null || checkTransactionId(transactionId) ==
			 * false) { if (logger.isDebugEnabled()) {
			 * logger.debug("事务id检测未通过, " + transactionId); }
			 * resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 * return; } //
			 */
			byte[] requsetContent = new byte[req.getContentLength()];

			req.getInputStream().read(requsetContent);

			// logger.debug("接送到的消息:eventTypeId=" + eventTypeId + ",event=" +
			// event);
			result = doEvent(eventTypeId, event, is_preview);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("异常:" + ex.toString());
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return "{RET:1,MSG:'" + ex.toString() + "'}";
		}

		resp.setStatus(HttpServletResponse.SC_OK);
		if (is_preview == 0)
			return "{RET:0,MSG:'生成成功！'}";
		else
			return result;

	}
}
