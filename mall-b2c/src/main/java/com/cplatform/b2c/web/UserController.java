package com.cplatform.b2c.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.cache.CachedObjectType;
import com.cplatform.b2c.dto.CallBalance;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TOrderWeiBoShare;
import com.cplatform.b2c.model.TSupplierApply;
import com.cplatform.b2c.model.TUserExchangeLog;
import com.cplatform.b2c.service.JmsMessageService;
import com.cplatform.b2c.service.MemberCenterService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.service.UserSercvice;
import com.cplatform.b2c.service.VerifyCodeService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CTime;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

@Controller
public class UserController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserSercvice userSercvice;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private VerifyCodeService verifyCodeService;

	@Autowired
	private JmsMessageService jmsMessageService;

	@Autowired
	private MemberCenterService centerService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/isLogin")
	@ResponseBody
	public void isLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("callback") String callback) {
		response.setContentType("text/html; charset=utf-8");

		Map<String, String> ret = new HashMap<String, String>();
		LoginUserBean loginUser = new SSOAgent(request, response).loginUserInfo("mall");
		if (loginUser == null) {
			ret.put("code", "0");
		} else {
			ret.put("code", "1");
			ret.put("nickName", loginUser.getNickName());
		}

		logger.info("登录状态:" + ret);
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(ret);
			if (callback != null && !"".equals(callback)) {
				out.print(callback + "(" + json.toString() + ")");
			} else {
				out.print(json);
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进入兑换商城币的方法
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/goExchange")
	public String goExchange(HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		Map<String, String> userScoreMap = new HashMap<String, String>();
		// 验证用户是否登录，并且是否绑定手机号
		if (userinfo != null && userinfo.getTerminalId() != null) {
			try {
				// http返回的用户积分等信息
				userScoreMap = userSercvice.queryExchangePoints(userinfo.getTerminalId());
				if (!userScoreMap.isEmpty() && userScoreMap.get("statusCode").equals("0")) {
					userScoreMap.put("percent", appConfig.getExchange_Percent() + "");
					model.addAttribute("userScore", userScoreMap);
					model.addAttribute("webroot", appConfig.getWebRoot());
					model.addAttribute("noPhone", "1");
					return "exchange/exchange";
				}
			}
			catch (Exception e) {
				model.addAttribute("noPhone", "0");
				return "exchange/exchange";
			}

		}
		return "error/404";

	}

	/**
	 * 兑换商城币方法
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/center/doExchange")
	@ResponseBody
	public Object exchangePoints(HttpServletRequest request, HttpServletResponse response) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (StringUtils.isBlank(userinfo.getTerminalId())) {
			return JsonRespWrapper.failure("未绑定移动手机号码，请前往“个人中心>基本资料”进行绑定");
		}
		// 用户手机号
		String mobile = userinfo.getTerminalId();
		// 用户总的积分
		String myscore = request.getParameter("userscore");
		// 用户输入想兑换的商城币
		String amount = request.getParameter("mallpoint");
		// 比例
		Integer percent = appConfig.getExchange_Percent();
		// 页面显示需要兑换这些商城币所需要的积分
		String needscore = request.getParameter("needscore");
		String verifyCode = request.getParameter("verifyCode");
		// type=1是兑换商城币
		String type = "1";
		// 用户ip
		String userIp = userSercvice.getRemoteIpAddr(request);

		String cachedCode = verifyCodeService.getVerifyCode(CachedObjectType.VERIFY_COIN.getPrefix() + userinfo.getId());
		if (cachedCode == null) {
			return JsonRespWrapper.failure("验证码已过期，请重新获取");
		}
		if (!verifyCode.equals(cachedCode)) {
			return JsonRespWrapper.failure("验证码错误");
		}

		// 兑换
		boolean flag = false;

		// 兑换的商城币和所需的积分不为空
		if ((amount != null && !amount.equals("")) && (needscore != null && !needscore.equals(""))) {
			// 计算出所兑换的商城币需要的积分
			Integer nScore = Integer.parseInt(amount) * percent;
			// 计算出来的积分和页面显示的需要的积分相等，并且个人总的积分大于等于兑换所需积分数
			if (nScore == Integer.parseInt(needscore) && Integer.parseInt(myscore) >= Integer.parseInt(needscore)) {
				try {
					flag = userSercvice.exchangePoints(mobile, userIp, type, amount);
					if (flag) {
						return JsonRespWrapper.json("msg", "成功兑换商城币！", true);
					} else {
						return JsonRespWrapper.json("msg", "未成功兑换商城币！", false);
					}

				}
				catch (Exception e) {
					return JsonRespWrapper.json("msg", "未成功兑换商城币！", false);
				}
			}
		}
		return JsonRespWrapper.json("msg", "未成功兑换商城币！", false);
	}

	@RequestMapping("center/sendMessage")
	@ResponseBody
	public Object sendMessage(HttpServletResponse res) {

		SessionUser userinfo = SessionUser.getSessionUser(res);

		String verifyCode = verifyCodeService.generateVerifyCode(CachedObjectType.VERIFY_COIN.getPrefix() + userinfo.getId());

		String message = MessageFormat.format(appConfig.getSmsExchangeScore(), verifyCode);

		try {
			jmsMessageService.sendSms(message, userinfo.getTerminalId());
		}
		catch (Exception ex) {
			logger.warn("发送验证码消息失败, 验证码：" + verifyCode, ex);
		}

		return JsonRespWrapper.success("type", "success");// .json("randomKey",
		                                                  // verifyCode);
	}

	/**
	 * 查询兑换商城币日志方法
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/exchangeLog")
	public String getExchangeLogs(HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		// 存放从db获取的兑换商城币日志
		List<TUserExchangeLog> exchangeLogs = new ArrayList<TUserExchangeLog>();
		if (userinfo != null && (userinfo.getTerminalId() != null && !userinfo.getTerminalId().equals(""))) {
			exchangeLogs = userSercvice.getExchangeLogs(userinfo.getTerminalId());
			model.addAttribute("exchangeLogs", exchangeLogs);

		} else {
			model.addAttribute("exchangeLogs", exchangeLogs);
		}
		model.addAttribute("webroot", appConfig.getWebRoot());
		return "exchange/exchangelog";
	}

	@RequestMapping(value = "/user/sendBindSmsCode")
	@ResponseBody
	public Object sendBindSmsCode(HttpServletRequest request, HttpServletResponse response, @RequestParam("mobile") String mobile) {
		// 判断是否是移动手机号
		try {
			String areaCode = jmsMessageService.queryAreaCode(mobile);
			if (areaCode == null) {
				return JsonRespWrapper.json("msg", "请输入江苏移动手机号", false);
			}
		}
		catch (SQLException e) {
			return JsonRespWrapper.json("msg", "未知错误0", false);
		}

		// 判断登录用户帐号是否可以合法绑定
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (userinfo == null) {
			return JsonRespWrapper.json("msg", "用户未登录", false);
		}
		if (userinfo.getTerminalId() != null && !"".equals(userinfo.getTerminalId())) {
			return JsonRespWrapper.json("msg", "您已经绑定手机号:" + userinfo.getTerminalId(), false);
		}

		// 发送验证码
		String verifyCode = verifyCodeService.generateVerifyCode(CachedObjectType.VERIFY_BIND.getPrefix() + userinfo.getId() + mobile);
		String message = MessageFormat.format(appConfig.getSmsBindMobile(), verifyCode);
		try {
			jmsMessageService.sendSms(message, mobile);
		}
		catch (Exception ex) {
			logger.warn("发送绑定验证码消息失败, 验证码：" + verifyCode, ex);
			return JsonRespWrapper.json("msg", "未知错误1", false);
		}

		return JsonRespWrapper.json("msg", "验证码已经发送,请注意查收", true);
	}

	@RequestMapping(value = "/user/verifyBindSmsCode")
	@ResponseBody
	public Object verifyBindSmsCode(HttpServletRequest request, HttpServletResponse response, @RequestParam("mobile") String mobile,
	        @RequestParam("code") String code) {
		// 判断是否是移动手机号
		try {
			String areaCode = jmsMessageService.queryAreaCode(mobile);
			if (areaCode == null) {
				return JsonRespWrapper.json("msg", "请输入江苏移动手机号", false);
			}
		}
		catch (SQLException e) {
			return JsonRespWrapper.json("msg", "未知错误0", false);
		}

		// 判断登录用户帐号是否可以合法绑定
		SSOAgent agent = new SSOAgent(request, response);
		LoginUserBean userinfo = agent.loginUserInfo("mall");
		if (userinfo == null) {
			return JsonRespWrapper.json("msg", "用户未登录", false);
		}
		if (userinfo.getTerminalId() != null && !"".equals(userinfo.getTerminalId())) {
			return JsonRespWrapper.json("msg", "您已经绑定手机号:" + userinfo.getTerminalId(), false);
		}

		// 获取验证码
		String verifyCode = verifyCodeService.getVerifyCode(CachedObjectType.VERIFY_BIND.getPrefix() + userinfo.getId() + mobile);
		if (verifyCode == null) {
			return JsonRespWrapper.json("msg", "验证码有误或已过期", false);
		}
		if (!verifyCode.equals(code)) {
			return JsonRespWrapper.json("msg", "验证码有误, 请重新输入", false);
		}

		// 处理手机号绑定问题
		if (centerService.isExistMobile(mobile)) {
			return JsonRespWrapper.json("msg", "手机号已经被绑定", false);
		}

		if (centerService.bindMobile(userinfo.getId(), mobile)) {
			agent.refreshUserInfo(userinfo.getId().toString(), "-1", "www");
			return JsonRespWrapper.json("msg", "success", true);
		} else {
			return JsonRespWrapper.json("msg", "未知错误9", false);
		}
	}

	@RequestMapping(value = "/center/myBalance")
	public String myBalance(HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		Map<String, String> userScoreMap = new HashMap<String, String>();
		Map<String, String> userCoinMap = new HashMap<String, String>();
		// 验证用户是否登录，并且是否绑定手机号
		if (userinfo != null && userinfo.getTerminalId() != null) {
			try {
				// http返回的用户积分等信息
				// 查积分
				userScoreMap = userSercvice.queryExchangePoints(userinfo.getTerminalId());
				// 查商城币
				userCoinMap = userSercvice.queryMyCoin(userinfo.getTerminalId());
				// 查询话费余额 to-do
				CallBalance callBalance = orderService.getBalanceInfo(userinfo.getTerminalId());

				if (!userScoreMap.isEmpty() && userScoreMap.get("statusCode").equals("0") && !userCoinMap.isEmpty()
				        && userCoinMap.get("statusCode").equals("0")) {
					model.addAttribute("userScore", userScoreMap);
					float scale = Float.parseFloat(userCoinMap.get("coin")) / 100;
					DecimalFormat fnum = new DecimalFormat("##0.00");
					userCoinMap.put("coin", fnum.format(scale) + "");
					model.addAttribute("userCoin", userCoinMap);
					model.addAttribute("webroot", appConfig.getWebRoot());
					model.addAttribute("noPhone", "1");
					model.addAttribute("balance", fnum.format(CommonUtils.toYuan(callBalance.getBalance()).floatValue()));
					model.addAttribute("limitFee", fnum.format(CommonUtils.toYuan(callBalance.getLimitFee()).floatValue()));
					model.addAttribute("useFee", fnum.format(CommonUtils.toYuan(callBalance.getUseFee()).floatValue()));
					return "center/myBalance";
				}
			}
			catch (Exception e) {
				model.addAttribute("noPhone", "0");
				return "center/myBalance";
			}

		}
		return "error/404";

	}

	@RequestMapping(value = "/center/weiboShare")
	@ResponseBody
	public void weiboShare(@RequestParam("orderId") String orderId, @RequestParam("goodsName") String goodsName,
	        @RequestParam("goodsType") String goodsType, @RequestParam("payTime") String payTime, HttpServletRequest request,
	        HttpServletResponse response, Model model) {
		try {
			LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
			if (userinfo != null) {
				TOrderWeiBoShare weiBoShare = new TOrderWeiBoShare();
				weiBoShare.setOrderId(Long.parseLong(orderId));
				weiBoShare.setGoodsName(goodsName);
				weiBoShare.setGoodsType(goodsType);
				weiBoShare.setPayTime(payTime);
				weiBoShare.setShareTime(CTime.getTime(14));
				weiBoShare.setStatus(0);
				weiBoShare.setCity(userinfo.getAreaCode());
				weiBoShare.setIsDel(0);
				String isLeaguer = "no";
				if (userinfo.getRedMember() != null && userinfo.getRedMember() == 1) {
					isLeaguer = "yes";
				}
				weiBoShare.setMobile(userinfo.getTerminalId());
				weiBoShare.setUserId(userinfo.getId());
				weiBoShare.setIsLeaguer(isLeaguer);
				weiBoShare.setDiscribe("微博晒单");
				userSercvice.insertWeiBoShare(weiBoShare);
				logger.info("用户：" + userinfo.getId() + ";在" + goodsType + "微博晒单；订单号：" + orderId + "；商品名称：" + goodsName + "插入晒单表成功！");
			}
		}
		catch (Exception ex) {
			logger.error("订单功能：" + goodsType + "；微博晒单出错" + ex.getMessage());
		}

	}

	@RequestMapping("/addProvider")
	public String addProvider() {
		return "center/addProvider";
	}

	@RequestMapping(value = "/becomeProvider")
	@ResponseBody
	public Object becomeProvider(HttpServletRequest request) {

		String randVerifycode = (String) request.getSession().getAttribute("randVerifycode");
		String verifyCode = request.getParameter("verifyCode");
		if (verifyCode != null && verifyCode.equalsIgnoreCase(randVerifycode)) {
			TSupplierApply tSupplierApply = new TSupplierApply();
			tSupplierApply.setSupplierName(request.getParameter("supplierName"));
			tSupplierApply.setBusinessScope(request.getParameter("businessScope"));
			tSupplierApply.setAddress(request.getParameter("address"));
			tSupplierApply.setMobile(request.getParameter("mobile"));
			tSupplierApply.setPhone(request.getParameter("phone"));
			tSupplierApply.setStatus(0);
			tSupplierApply.setContact(request.getParameter("contact"));

			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			tSupplierApply.setCreateTime(sf.format(new Date()));
			boolean flag = userSercvice.saveTSupplierApply(tSupplierApply);
			if (flag) {
				logger.info("供应商申请成功");
				return JsonRespWrapper.success("type", "success");
			}
			return JsonRespWrapper.failure("type", "failure");
		}
		logger.info("随机码输入错误，期望【" + randVerifycode + "】，输入【" + verifyCode + "】");
		return JsonRespWrapper.failure("type", "errorVerifyCode");
	}

	@RequestMapping("/getVerifyCodeImg")
	public void getVerifyCodeImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 在内存中创建图象
		int width = 200;
		int height = 80;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.ITALIC, 80));

		// 画边框
		g.setColor(new Color(0, 0, 0));
		g.drawRect(0, 0, width - 1, height - 1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(Color.blue);
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(100);
			int yl = random.nextInt(90);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = null;
			// 随机生成数字或者字母
			if (random.nextInt(10) > 5) {
				rand = String.valueOf((char) (random.nextInt(10) + 48));
			} else {
				rand = String.valueOf((char) (random.nextInt(26) + 65));
			}
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(random.nextInt(80), random.nextInt(80), random.nextInt(80)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 40 * i + 15, 70);
		}
		// 图象生效
		g.dispose();
		HttpSession session = request.getSession();
		session.setAttribute("randVerifycode", sRand);
		// 输出图象到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
}
