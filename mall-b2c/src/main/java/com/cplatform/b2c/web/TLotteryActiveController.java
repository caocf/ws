package com.cplatform.b2c.web;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.URLDecoder;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.model.TLotteryActiveConf;
import com.cplatform.b2c.model.TLotteryPrize;
import com.cplatform.b2c.service.TLotteryActiveConfService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

@RequestMapping(value = "active")
@Controller
public class TLotteryActiveController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	TLotteryActiveConfService tLotteryActiveConfService;

	@Autowired
	private AppConfig appConfig;

	@RequestMapping(value = "/lottery_rotate", method = RequestMethod.GET)
	@ResponseBody
	public String init(@RequestParam int activeId, Model model, HttpServletRequest requet, HttpServletResponse response) {

		String img = appConfig.getUpload_lotty();

		Map<String, Object> mapModel = new HashMap<String, Object>();

		List<TLotteryActiveConf> tLotteryActiveConfs = tLotteryActiveConfService.getActiveConfList(activeId);

		List<TLotteryPrize> tLotteryPrizes = tLotteryActiveConfService.getPrizeList(activeId);

		List<Map<String, Object>> prizes = new ArrayList<Map<String, Object>>();

		for (TLotteryPrize tp : tLotteryPrizes) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("activeId", tp.getActiveId());
			map.put("hitLevel", tp.getHitLevel());
			map.put("hitMsg", tp.getHitMsg());
			map.put("name", tp.getName());
			map.put("position", tp.getPosition());
			prizes.add(map);
		}
		mapModel.put("confs", tLotteryActiveConfs);
		mapModel.put("prizes", prizes);
		mapModel.put("imgUrl", img);
		mapModel.put("activeId", activeId);
		JSONObject json = JSONObject.fromObject(mapModel);
		return "抽奖配置信息：" + json;
	}

	/**
	 * 获得中奖信息
	 * 
	 * @param act_id
	 * @param response
	 * @param requet
	 */
	@RequestMapping(value = "/prize", method = RequestMethod.POST)
	public void post(@RequestParam int act_id, HttpServletResponse response, HttpServletRequest requet) {
		LoginUserBean userinfo = null;
		String mobile = null;
		String url = appConfig.getActive_Prize();
		String result;
		PrintWriter out = null;
		userinfo = new SSOAgent(requet, response).loginUserInfo("mall");
		logger.info("登陆用户:" + userinfo);
		try {
			if (userinfo == null) {
				out = response.getWriter();
				out.print("login");
			} else {
				mobile = userinfo.getTerminalId();
				if (StringUtils.isBlank(mobile)) {
					out = response.getWriter();
					out.print("noMobile");
				} else {
					// mobile = "15895564545";
					logger.debug("开始连接远程服务器：" + url + "?mobile=" + mobile + "&act_id=" + act_id);
					result = HttpClientUtils.httpGet(url, "mobile=" + mobile + "&act_id=" + act_id, 0, 0);
					logger.debug("远程服务器连接成功；返回结果：" + URLDecoder.decode(result, "UTF-8"));
					out = response.getWriter();
					if (result != null) {
						out.print(URLDecoder.decode(result, "UTF-8"));
					}

				}
			}

		}
		catch (Exception e1) {
			logger.error("连接失败");
			e1.printStackTrace();
		}
		finally {
			if (out != null) {
				out.flush();
				out.close();
			}

		}

	}

	/*
	 * 获得中奖信息的列表
	 */
	@RequestMapping(value = "/prizeMessage")
	public String query(Model model, @RequestParam int actId) {
		try {
			List<Map<String, Object>> data = tLotteryActiveConfService.getMessage(actId);
			for (Map<String, Object> map : data) {
				String mobile = (String) map.get("TARGET_ID");
				map.put("TARGET_ID", getMobile(mobile));
			}
			model.addAttribute("messages", data);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return "active/redpacket";
	}

	private String getMobile(String mobile) {
		String foot = mobile.substring(7);
		String top = mobile.substring(0, 3);
		String m = top + "****" + foot;
		return m;
	}
}
