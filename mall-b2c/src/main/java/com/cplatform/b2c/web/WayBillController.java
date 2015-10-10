package com.cplatform.b2c.web;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cplatform.b2c.service.WayBillService;
import com.cplatform.b2c.util.AppConfig;

/**
 * 物流订单查询操作.
 * <p>
 * Copyright: Copyright (c) 2013-6-9 上午9:34:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author 季霁
 */
@Controller
public class WayBillController {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private WayBillService wayBillService;

	@Autowired
	private AppConfig appConfig;

	/**
	 * 快递公司 名称与简称对应关系
	 */
	private static Map<String, String> company = new HashMap<String, String>();

	/**
	 * 
	 */
	static {
		company.put("联邦快递", "lianbangkuaidi");
		company.put("国通快递", "guotong");
		company.put("宅急送", "zhaijisong");
		company.put("汇通快递", "huitongkuaidi");
		company.put("天天快递", "tiantian");
		company.put("中通快递", "zhongtong");
		company.put("韵达快递", "yunda");
		company.put("EMS", "ems");
		company.put("圆通快递", "yuantong");
		company.put("顺丰快递", "shunfeng");
		company.put("申通快递", "shentong");
		company.put("优速快递", "yousuwuliu");
		company.put("全峰快递", "quanfengkuaidi");
		company.put("快捷速递", "kuaijiesudi");
		company.put("速尔快递", "sunerkuaidi");
		company.put("能达速递", "nengdasudi");
		company.put("邮政小包", "youzhengxiaobao");
	}

	/**
	 * http://api.kuaidi100.com/api?id=524afc31dbf23d67&com=huitongkuaidi&nu=
	 * 210090644209&show=1&muti=1&order=desc id=524afc31dbf23d67 api对应的key值
	 * com=huitongkuaidi 是物流公司拼音（对应关系如下：） nu=210090644209 运单号
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bill/getWayBill")
	public void getOrder(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		// response.setCharacterEncoding("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 1.获取参数
		// 运单号
		String nu = request.getParameter("nu");

		// 物流公司名称
		String companyName = request.getParameter("com");
		String companyShort = company.get(companyName);

		logger.info("~~~~~~~~~~~~~nu:" + nu + " companyName:" + companyName);

		// 2.从第三方网站获取信息
		InputStream result = null;
		result = wayBillService.getResult(nu, companyShort);

		// 3.把结果转化成html
		if (null == result) {
			String noResultError = "No query results!";
			outData(out, noResultError);
			return;
		}
		wayBillService.transform(result, appConfig.getWayBillAddr(), out);
	}

	private void outData(PrintWriter out, String str) {
		out.print(str);
		out.flush();
		try {
			out.close();
		}
		catch (Exception e) {
			logger.info("PrintWriter out close error" + e);
		}
	}

}
