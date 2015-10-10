package com.cplatform.b2c.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.e;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.cache.CachedObjectType;
import com.cplatform.b2c.dto.MemberDTO;
import com.cplatform.b2c.dto.UserCenterObj;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TMember;
import com.cplatform.b2c.service.JmsMessageService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.service.TMemberService;
import com.cplatform.b2c.service.VerifyCodeService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.b2c.util.MemCacheUserCenterUtil;
import com.cplatform.b2c.util.ValidateUtil;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.soa.muc.SoaMemberService;
import com.cplatform.soa.muc.model.Member;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

/**
 * 个人中心-基础信息修改. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-2-20 上午9:49:46
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/center")
public class MemberController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TMemberService tMemberService;

	@Autowired
	private VerifyCodeService verifyCodeService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private JmsMessageService jmsMessageService;

	@Autowired
	private SoaMemberService soaMemberService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private MemCacheUserCenterUtil memCacheUserCenterUtil;

	@Autowired
	private ValidateUtil validateUtil;

	/**
	 * 跳转个人基本信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(HttpServletRequest request, HttpServletResponse response, Model model) throws SQLException {
		SessionUser userinfo = SessionUser.getSessionUser(response);
		// 用户的编号
		Long userId = userinfo.getId();
		Member member = soaMemberService.info(String.valueOf(userId), SoaMemberService.TYPE_ID);
		model.addAttribute("tMember", member);
		return "center/basic/info";
	}
	
	/**
	 * 获取个人基本信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMemberInfo") 
	@ResponseBody
	public Object getMemberInfo(HttpServletRequest request, HttpServletResponse response){
		try {
			SessionUser userinfo = SessionUser.getSessionUser(response);
			Long userId = userinfo.getId();
			Member member=soaMemberService.info(String.valueOf(userId), SoaMemberService.TYPE_ID);
			logger.info("member info,nick name:"+member.getNickName()+"--ovater:"+member.getAvatar());
			MemberDTO memberDTO=new MemberDTO(member);
			memberDTO.setIsRedMember(orderService.isRedMember(userinfo));
			memberDTO.setIsUnionMember(orderService.isUnionMember(userinfo.getTerminalId()));
			return memberDTO;   
        }
        catch (Exception e) {
	        // TODO: handle exception
        	logger.error("获取用户信息异常"+e.getMessage());
        	return null;
        }
		
	}

	/**
	 * 跳转个人基本信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public void modifyInfo(TMember tMember, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		SessionUser userinfo = SessionUser.getSessionUser(response);
		PrintWriter printWriter = response.getWriter();
		try {
			if (null == userinfo) {
				printWriter.print("<script>alert('用户未登录');eval(parent.location='info.chtml');</script>");
			}

			// 修改用户信息返回值
			String flag = tMemberService.updateInfo(tMember, userinfo.getId(), request, response);
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/html");

			logger.info("获取提示信息");
			String alertStr = tMemberService.getAlert(flag);
			printWriter.print(alertStr);
			printWriter.flush();
			printWriter.close();
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			printWriter.print("<script>alert('修改用户信息失败!');eval(parent.location='info.chtml');</script>");
			printWriter.flush();
			printWriter.close();
		}
	}

	/**
	 * 修改邮件跳转
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 */
	@RequestMapping(value = "/bindEmail", method = RequestMethod.GET)
	public String bindEmail(Model model) {
		return "center/basic/bind-email";
	}

	/**
	 * 发送邮箱验证绑定邮箱
	 * 
	 * @param email
	 * @param genValidCode
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/bindEmail/sendMail", method = RequestMethod.POST)
	@ResponseBody
	public Object sendEmail(@RequestParam("email") String email, @RequestParam("genValidCode") String genValidCode, HttpServletRequest request,
	        HttpServletResponse response, HttpSession session) throws SQLException, IOException {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (null == userinfo) {
			return JsonRespWrapper.success("用户未登录");
		}

		if (StringUtils.isNotBlank(userinfo.getEmail())) {
			return JsonRespWrapper.success("您已绑定邮箱:" + userinfo.getEmail());
		}

		if (StringUtils.isNotBlank(email)) {
			email = email.trim();
			if (email.length() <= 50) {
				if (StringUtils.isNotBlank(genValidCode)) {
					// 判断验证码是否相等
					if (genValidCode.equalsIgnoreCase((String) session.getAttribute(Constants.SESSION_CENTER_USER_BIND_EMAIL_KEY + userinfo.getId()))) {
						// 处理邮箱绑定问题
						if (tMemberService.isExistEmail(email)) {
							return JsonRespWrapper.success("邮箱已经被绑定");
						}
						// 发送邮件操作
						tMemberService.sendMail(email, UserCenterObj.BIND_EMAIL, userinfo.getId().toString());
						session.setAttribute(UserCenterObj.BIND_EMAIL + userinfo.getId(), email);
						logger.info("发送邮件成功");
						return JsonRespWrapper.redirectSuccess("", "../center/bind-email-success");
					} else {
						// 验证码不相等
						logger.error("绑定验证码不相等");
						return JsonRespWrapper.success("验证码有误，请重新输入");
					}
				} else {
					// 验证码为空
					logger.error("绑定验证码为空");
					return JsonRespWrapper.success("验证码不能为空");
				}
			} else {
				// 邮箱长度太长
				logger.info("邮箱长度过长，邮箱：" + email + "，长度：" + email.length());
				return JsonRespWrapper.success("邮箱地址过长，请输入50个字符之内");
			}
		} else {
			// 邮箱为空
			logger.error("绑定邮箱为空");
			return JsonRespWrapper.success("邮箱不能为空");
		}
	}

	/**
	 * 重新发送邮箱验证绑定邮箱
	 * 
	 * @param email
	 * @param genValidCode
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/rest-bindEmail/sendMail", method = RequestMethod.POST)
	@ResponseBody
	public Object restSendEmail(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (null == userinfo) {
			return JsonRespWrapper.success("用户未登录");
		}

		if (StringUtils.isNotBlank(userinfo.getEmail())) {
			return JsonRespWrapper.success("您已绑定邮箱:" + userinfo.getEmail());
		}

		String email = ObjectUtils.toString(session.getAttribute(UserCenterObj.BIND_EMAIL + userinfo.getId()));
		if (StringUtils.isNotBlank(email)) {
			email = email.trim();
			if (email.length() <= 50) {
				// 处理邮箱绑定问题
				if (tMemberService.isExistEmail(email)) {
					return JsonRespWrapper.success("邮箱已经被绑定");
				}
				// 发送邮件操作
				tMemberService.sendMail(email, UserCenterObj.BIND_EMAIL, userinfo.getId().toString());
				session.setAttribute(UserCenterObj.BIND_EMAIL + userinfo.getId(), email);
				logger.info("发送邮件成功");
				return JsonRespWrapper.success("重新发送成功");
			} else {
				// 邮箱长度太长
				logger.info("重发邮箱长度过长，邮箱：" + email + "，长度：" + email.length());
				return JsonRespWrapper.success("邮箱地址过长，请输入50个字符之内");
			}
		} else {
			// 邮箱为空
			logger.error("绑定邮箱为空");
			return JsonRespWrapper.success("请在30分钟内进行重新发送");
		}
	}

	/**
	 * 绑定邮箱
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/bind-email/validate", method = RequestMethod.GET)
	@ResponseBody
	public void doBindEmail(@RequestParam("code") String genValidCode, @RequestParam("id") Long id, HttpServletRequest request,
	        HttpServletResponse response) throws SQLException, IOException {

		PrintWriter printWriter = response.getWriter();
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		try {
			LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
			String userId = userinfo.getId().toString();
			if (tMemberService.isCurrentUser(id, request, response)) {
				// 获取当前操作对象
				UserCenterObj userCenterObj = tMemberService.findByKey(userId, genValidCode, UserCenterObj.BIND_EMAIL);
				// 验证信息
				if (memCacheUserCenterUtil.check(userId, genValidCode, UserCenterObj.BIND_EMAIL)) {
					if (null != userCenterObj && StringUtils.isNotBlank(userCenterObj.getEmail())) {
						soaMemberService.updateEmail(userinfo.getId(), userCenterObj.getEmail().trim());
						SSOAgent agent = new SSOAgent(request, response);
						agent.refreshUserInfo(userinfo.getId().toString(), "-1", "www");
						printWriter.print("<script>alert('绑定邮箱成功');eval(parent.location='../info.chtml');</script>");
						printWriter.flush();
						printWriter.close();
					}
					logger.error("用户信息获取错误");
					printWriter.print("<script>alert('绑定邮箱失败，请核对信息后重新绑定');eval(parent.location='../info.chtml');</script>");
					printWriter.flush();
					printWriter.close();
				} else {
					logger.error("请核对信息");
					printWriter.print("<script>alert('绑定邮箱失败，请核对信息后重新绑定');eval(parent.location='../info.chtml');</script>");
					printWriter.flush();
					printWriter.close();
				}
			} else {
				logger.info("操作人与登录人不同：当前登录人id：" + userId + "-----传过来id" + id);
				printWriter.print("<script>alert('绑定邮箱失败，请核对信息后重新绑定');eval(parent.location='../info.chtml');</script>");
				printWriter.flush();
				printWriter.close();
			}
		}
		catch (Exception ex) {
			logger.info(ex.getMessage());
			printWriter.print("<script>alert('绑定邮箱失败，请核对信息后重新绑定');eval(parent.location='../info.chtml');</script>");
			printWriter.flush();
			printWriter.close();
		}
	}

	/**
	 * 修改邮件跳转
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 */
	@RequestMapping(value = "/editEmail", method = RequestMethod.GET)
	public String editEmail() {
		return "center/basic/edit-email";
	}

	/**
	 * 发送邮箱验证修改邮箱
	 * 
	 * @param email
	 * @param genValidCode
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/editEmail/sendMail", method = RequestMethod.POST)
	@ResponseBody
	public Object sendEmail(@RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response, HttpSession session)
	        throws SQLException, IOException {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (null == userinfo) {
			return JsonRespWrapper.success("用户未登录");
		}

		if (StringUtils.isNotBlank(email)) {
			email = email.trim();
			if (email.length() <= 50) {
				if (!email.equals(tMemberService.getTMemberById(userinfo.getId()).getEmail().trim())) {
					logger.info("修改邮箱：当前输入的邮箱编号与原来的不相等");
					// 处理邮箱修改问题
					if (tMemberService.isExistEmail(email)) {
						return JsonRespWrapper.success("邮箱已经被绑定");
					}
				}
				// 发送邮件操作
				tMemberService.sendMail(email, UserCenterObj.EDIT_EMAIL, userinfo.getId().toString());
				session.setAttribute(UserCenterObj.EDIT_EMAIL + userinfo.getId(), email);
				logger.info("发送邮件成功");
				return JsonRespWrapper.redirectSuccess("", "../center/edit-email-success");
			} else {
				// 邮箱长度太长
				logger.info("修改邮箱长度过长，邮箱：" + email + "，长度：" + email.length());
				return JsonRespWrapper.success("邮箱地址过长，请输入50个字符之内");
			}
		} else {
			// 邮箱为空
			logger.error("绑定邮箱为空");
			return JsonRespWrapper.success("邮箱不能为空");
		}
	}

	/**
	 * 重新发送邮箱验证修改邮箱
	 * 
	 * @param email
	 * @param genValidCode
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/rest-editEmail/sendMail", method = RequestMethod.POST)
	@ResponseBody
	public Object restEditSendEmail(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (null == userinfo) {
			return JsonRespWrapper.success("用户未登录");
		}
		String email = ObjectUtils.toString(session.getAttribute(UserCenterObj.BIND_EMAIL + userinfo.getId()));
		if (StringUtils.isNotBlank(email)) {
			// 处理邮箱修改问题
			email = email.trim();
			if (email.length() <= 50) {
				if (!email.equals(tMemberService.getTMemberById(userinfo.getId()).getEmail().trim())) {
					logger.info("重新发送邮箱：当前输入的邮箱编号与原来的不相等");
					if (tMemberService.isExistEmail(email)) {
						return JsonRespWrapper.success("邮箱已经被绑定");
					}
				}
				// 发送邮件操作
				tMemberService.sendMail(email, UserCenterObj.EDIT_EMAIL, userinfo.getId().toString());
				session.setAttribute(UserCenterObj.EDIT_EMAIL + userinfo.getId(), email);
				logger.info("发送邮件成功");
				return JsonRespWrapper.success("重新发送成功");
			} else {
				// 邮箱长度太长
				logger.info("重发修改邮箱长度过长，邮箱：" + email + "，长度：" + email.length());
				return JsonRespWrapper.success("邮箱地址过长，请输入50个字符之内");
			}
		} else {
			// 邮箱为空
			logger.error("绑定邮箱为空");
			return JsonRespWrapper.success("请在30分钟内进行重新发送");
		}
	}

	/**
	 * 修改邮箱
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/edit-email/validate", method = RequestMethod.GET)
	@ResponseBody
	public void doEditEmail(@RequestParam("code") String genValidCode, @RequestParam("id") Long id, HttpServletRequest request,
	        HttpServletResponse response) throws SQLException, IOException {

		PrintWriter printWriter = response.getWriter();
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		try {
			LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
			String userId = userinfo.getId().toString();
			if (tMemberService.isCurrentUser(id, request, response)) {
				// 获取当前操作对象
				UserCenterObj userCenterObj = tMemberService.findByKey(userId, genValidCode, UserCenterObj.EDIT_EMAIL);
				// 验证信息
				if (memCacheUserCenterUtil.check(userId, genValidCode, UserCenterObj.EDIT_EMAIL)) {
					if (null != userCenterObj && StringUtils.isNotBlank(userCenterObj.getEmail())) {
						soaMemberService.updateEmail(userinfo.getId(), userCenterObj.getEmail().trim());
						SSOAgent agent = new SSOAgent(request, response);
						agent.refreshUserInfo(userinfo.getId().toString(), "-1", "www");
						printWriter.print("<script>alert('修改邮箱成功');eval(parent.location='../info.chtml');</script>");
					}
					logger.error("用户信息获取错误");
					printWriter.print("<script>alert('修改邮箱失败，请核对信息后重新修改');eval(parent.location='../info.chtml');</script>");
					printWriter.flush();
					printWriter.close();
				} else {
					logger.error("请核对信息");
					printWriter.print("<script>alert('修改邮箱失败，请核对信息后重新修改');eval(parent.location='../info.chtml');</script>");
					printWriter.flush();
					printWriter.close();
				}
			} else {
				logger.info("操作人与登录人不同：当前登录人id：" + userId + "-----传过来id" + id);
				printWriter.print("<script>alert('修改邮箱失败，请核对信息后重新修改');eval(parent.location='../info.chtml');</script>");
				printWriter.flush();
				printWriter.close();
			}
		}
		catch (Exception ex) {
			logger.info(ex.getMessage());
			printWriter.print("<script>alert('修改邮箱失败，请核对信息后重新修改');eval(parent.location='../info.chtml');</script>");
			printWriter.flush();
			printWriter.close();
		}
	}

	/**
	 * 绑定邮箱成功页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/bind-email-success")
	public String bindEmailSuccess(HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		String email = ObjectUtils.toString(request.getSession().getAttribute(UserCenterObj.BIND_EMAIL + userinfo.getId()));
		model.addAttribute("mailWeb", (StringUtils.isNotBlank(email)) ? tMemberService.mailWebAddress(email) : null);
		model.addAttribute("type", UserCenterObj.BIND_EMAIL);
		return "center/basic/email-success";
	}

	/**
	 * 修改邮箱成功页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit-email-success")
	public String editEmailSuccess(HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		String email = ObjectUtils.toString(request.getSession().getAttribute(UserCenterObj.EDIT_EMAIL + userinfo.getId()));
		model.addAttribute("mailWeb", (StringUtils.isNotBlank(email)) ? tMemberService.mailWebAddress(email) : null);
		model.addAttribute("type", UserCenterObj.EDIT_EMAIL);
		return "center/basic/email-success";
	}

	/**
	 * 绑定手机号跳转
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 */
	@RequestMapping(value = "/bindMobile", method = RequestMethod.GET)
	public String bindMobile(HttpServletRequest request, HttpServletResponse response, Model model) {
		String backUrl = request.getParameter("backUrl");
		model.addAttribute("backUrl", backUrl);
		return "center/basic/bind-mobile";
	}

	/**
	 * 绑定手机号
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
	@ResponseBody
	public Object doBindMobile(Member tMember, @RequestParam("validCode") String validCode, HttpServletRequest request, HttpServletResponse response)
	        throws SQLException {
		return operateMobile(tMember, validCode, CachedObjectType.VERIFY_USERCENTER_BIND.getPrefix(), "绑定", request, response);

	}

	/**
	 * 修改手机号跳转
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 */
	@RequestMapping(value = "/editMobile", method = RequestMethod.GET)
	public String editMobile() {
		return "center/basic/edit-mobile";
	}

	/**
	 * 修改手机号_核实原来手机号
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/editMobile/step1", method = RequestMethod.POST)
	@ResponseBody
	public Object doEditMobileStep1(Member tMember, @RequestParam("validCode") String validCode, HttpServletRequest request,
	        HttpServletResponse response) throws SQLException {
		return operateMobile(tMember, validCode, CachedObjectType.VERIFY_USERCENTER_EDIT_STEP1.getPrefix(), "核实", request, response);
	}

	/**
	 * 修改手机号
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/editMobile/step2", method = RequestMethod.POST)
	@ResponseBody
	public Object doEditMobileStep2(Member tMember, @RequestParam("validCode2") String validCode, HttpServletRequest request,
	        HttpServletResponse response) throws SQLException {
		return operateMobile(tMember, validCode, CachedObjectType.VERIFY_USERCENTER_EDIT_STEP2.getPrefix(), "修改", request, response);
	}

	/**
	 * 跳转重置密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPwd", method = RequestMethod.GET)
	public String toEditPwd() {
		return "center/basic/edit-pwd";
	}

	/**
	 * 跳转修改密码
	 * 
	 * @return
	 */
	@RequestMapping("/editPwdSuccess")
	public String editPwdSuccess() {
		return "center/basic/reset-success";
	}

	/**
	 * 修改密码
	 * 
	 * @param tMember
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editPwd", method = RequestMethod.POST)
	@ResponseBody
	public Object doEditPwd(TMember tMember, HttpServletRequest request, HttpServletResponse response) {
		SessionUser userinfo = SessionUser.getSessionUser(response);
		if (StringUtils.isNotBlank(tMember.getOdlPwd())) {
			if (StringUtils.isNotBlank(tMember.getUserPass())) {
				if (StringUtils.isNotBlank(tMember.getUserPassAg())) {
					if (tMember.getUserPass().trim().equals(tMember.getUserPassAg().trim())) {
						boolean flag = soaMemberService.checkPass(String.valueOf(userinfo.getId()), SoaMemberService.TYPE_ID, tMember.getOdlPwd()
						        .trim());
						if (flag) {
							logger.info("修改密码");
							soaMemberService.updatePass(userinfo.getId(), tMember.getUserPass().trim());
							logger.info("修改密码成功");
							return JsonRespWrapper.redirectSuccess("修改密成功", "editPwdSuccess.chtml");
						} else {
							return JsonRespWrapper.success("原密码输入错误");
						}
					} else {
						return JsonRespWrapper.success("两次密码输入不一致，请核实后重新输入");
					}
				} else {
					return JsonRespWrapper.success("确认新密码不能为空");
				}
			} else {
				return JsonRespWrapper.success("新密码不能为空");
			}
		} else {
			return JsonRespWrapper.success("原密码不能为空");
		}
	}

	/**
	 * 验证用户原来密码
	 * 
	 * @param odlPwd
	 * @return
	 */
	@RequestMapping(value = "/checkOldPwd", method = RequestMethod.POST)
	@ResponseBody
	public String checkOldPwd(String odlPwd, HttpServletRequest request, HttpServletResponse response) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (StringUtils.isNotBlank(odlPwd)) {
			boolean flag = soaMemberService.checkPass(String.valueOf(userinfo.getId()), SoaMemberService.TYPE_ID, odlPwd.trim());
			if (flag) {
				return "1";
			}
		}
		return "0";
	}

	/**
	 * 手机绑定发校验码
	 * 
	 * @param res
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/bind-mobile/sendCode")
	@ResponseBody
	public Object sendMessage(HttpServletResponse res, @RequestParam("mobile") String mobile,
	        @RequestParam("validBindMobileCode") String validBindMobileCode, HttpSession session) {

		if (StringUtils.isNotBlank(mobile)) {
			if (StringUtils.isNotBlank(validBindMobileCode)) {
				SessionUser userinfo = SessionUser.getSessionUser(res);

				// 判断验证码是否相等
				if (validBindMobileCode.equalsIgnoreCase((String) session.getAttribute(Constants.SESSION_CENTER_USER_BIND_MOBLIE_KEY
				        + userinfo.getId()))) {
					logger.info("开始发校验码");

					String verifyCode = verifyCodeService.generateVerifyCode(CachedObjectType.VERIFY_USERCENTER_BIND.getPrefix() + userinfo.getId());
					String message = MessageFormat.format(appConfig.getSmsBindMobile(), verifyCode);

					try {
						jmsMessageService.sendSms(message, mobile.trim());
					}
					catch (Exception ex) {
						logger.warn("发送验证码消息失败, 验证码：" + verifyCode, ex);
					}

					return JsonRespWrapper.success("type", "success");
				} else {
					// 验证码不相等
					logger.error("绑定验证码不相等");
					return JsonRespWrapper.success("验证码有误，请重新输入");
				}
			} else {
				// 验证码为空
				logger.error("绑定验证码为空");
				return JsonRespWrapper.success("验证码不能为空");
			}
		} else {
			// 手机号为空
			logger.error("绑定手机号为空");
			return JsonRespWrapper.success("手机号不能为空");
		}
	}

	/**
	 * 修改手机号发校验码
	 * 
	 * @param res
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/edit-mobile/step1/sendCode")
	@ResponseBody
	public Object editMobileStep1SendMessage(HttpServletResponse res, @RequestParam("mobile") String mobile,
	        @RequestParam("validEditMobileStep1Code") String validBindMobileCode,
	        @RequestParam(value = "step", defaultValue = "", required = false) String step, HttpSession session) {

		if (StringUtils.isNotBlank(mobile)) {
			if (StringUtils.isNotBlank(validBindMobileCode)) {
				SessionUser userinfo = SessionUser.getSessionUser(res);

				// 图片验证码key值
				String session_key = "";
				// 校验码key值
				String cach_key = "";
				if (StringUtils.isBlank(step)) {
					step = UserCenterObj.EDIT_MOBILE_STEP1;
				}
				if (UserCenterObj.EDIT_MOBILE_STEP1.equals(step)) {
					// 判断输入的原来手机号是否是该用户绑定的手机号
					Member member = soaMemberService.info(userinfo.getId().toString(), SoaMemberService.TYPE_ID);
					if (null == member || !mobile.equals(member.getTerminalId())) {
						logger.error("输入的原号码错误，请核实后再输");
						return JsonRespWrapper.success("请输入原手机号码");
					}
					session_key = Constants.SESSION_CENTER_USER_EDIT_MOBLIE_STEP1_KEY;
					cach_key = CachedObjectType.VERIFY_USERCENTER_EDIT_STEP1.getPrefix();
				} else if (UserCenterObj.EDIT_MOBILE_STEP2.equals(step)) {
					session_key = Constants.SESSION_CENTER_USER_EDIT_MOBLIE_STEP2_KEY;
					cach_key = CachedObjectType.VERIFY_USERCENTER_EDIT_STEP2.getPrefix();
				}

				// 判断验证码是否相等
				if (validBindMobileCode.equalsIgnoreCase((String) session.getAttribute(session_key + userinfo.getId()))) {

					logger.info("开始发校验码");

					String verifyCode = verifyCodeService.generateVerifyCode(cach_key + userinfo.getId());
					String message = MessageFormat.format(appConfig.getSmsBindMobile(), verifyCode);

					try {
						jmsMessageService.sendSms(message, mobile.trim());
					}
					catch (Exception ex) {
						logger.warn("发送验证码消息失败, 验证码：" + verifyCode, ex);
					}

					return JsonRespWrapper.success("type", "success");
				} else {
					// 验证码不相等
					logger.error("绑定验证码不相等");
					return JsonRespWrapper.success("验证码有误，请重新输入");
				}
			} else {
				// 验证码为空
				logger.error("绑定验证码为空");
				return JsonRespWrapper.success("验证码不能为空");
			}
		} else {
			// 手机号为空
			logger.error("手机号为空");
			return JsonRespWrapper.success("手机号不能为空");
		}
	}

	/**
	 * 修改手机号第二步发码
	 * 
	 * @param res
	 * @param mobile
	 * @param validBindMobileCode
	 * @param session
	 * @return
	 */
	@RequestMapping("/edit-mobile/step2/sendCode")
	@ResponseBody
	public Object editMobileStep2SendMessage(HttpServletResponse res, @RequestParam("mobile") String mobile,
	        @RequestParam("validEditMobileStep2Code") String validBindMobileCode, HttpSession session) {

		return editMobileStep1SendMessage(res, mobile, validBindMobileCode, UserCenterObj.EDIT_MOBILE_STEP2, session);
	}

	/**
	 * 操作手机：诸如绑定手机号；修改手机号；
	 * 
	 * @param tMember
	 * @param validCode
	 *            验证码
	 * @param validCodeType
	 *            发码方式
	 * @param hint
	 *            提示信息
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	private Object operateMobile(Member tMember, String validCode, String validCodeType, String hint, HttpServletRequest request,
	        HttpServletResponse response) throws SQLException {
		String backUrl = request.getParameter("backUrl");
		SessionUser userinfo = SessionUser.getSessionUser(response);

		if (null == userinfo) {
			return JsonRespWrapper.success("用户未登录");
		}

		// 判断是否绑定手机号
		if (CachedObjectType.VERIFY_USERCENTER_BIND.getPrefix().equals(validCodeType)) {
			if (StringUtils.isNotBlank(userinfo.getTerminalId())) {
				return JsonRespWrapper.success("您已经绑定手机号:" + userinfo.getTerminalId());
			}
		}

		if (StringUtils.isNotBlank(tMember.getTerminalId())) {
			// 验证是否是移动手机号码
			if (!validateUtil.isChinaMobile(tMember.getTerminalId().trim())) {
				return JsonRespWrapper.success("请输入移动手机号");
			}
			if (StringUtils.isNotBlank(validCode)) {
				// 判断验证码是否相等
				// 获取所发校验码
				String verifyCode = verifyCodeService.getVerifyCode(validCodeType + userinfo.getId());
				if (verifyCode == null) {
					return JsonRespWrapper.success("验证码有误或已过期");
				}

				if (!verifyCode.equals(validCode)) {
					return JsonRespWrapper.success("验证码有误, 请重新输入");
				}

				// 判断是绑定手机号、修改手机号，还是核实原手机号
				if (CachedObjectType.VERIFY_USERCENTER_BIND.getPrefix().equals(validCodeType)) { // 绑定手机号
					// 验证手机号是否已存在
					if (tMemberService.isExistMobile(tMember.getTerminalId().trim())) {
						return JsonRespWrapper.success("手机号已经被绑定");
					}
					logger.info("准备操作数据库对mobile字段进行修改");
					return updateMobile(tMember, hint, request, response, backUrl, userinfo);
				} else if (CachedObjectType.VERIFY_USERCENTER_EDIT_STEP1.getPrefix().equals(validCodeType)) { // 修改手机号时，核实手机号信息
					// 验证所输入的手机号是否与原手机号一致
					Member member = soaMemberService.info(userinfo.getId().toString(), SoaMemberService.TYPE_ID);
					if (null != member && (!member.getTerminalId().equals(tMember.getTerminalId().trim()))) {
						return JsonRespWrapper.success("原手机号输入错误");
					}
					logger.info("核实原手机号通过");
					return JsonRespWrapper.success().json("editMobileStep1", "true");
				} else if (CachedObjectType.VERIFY_USERCENTER_EDIT_STEP2.getPrefix().equals(validCodeType)) {
					// 验证手机号是否已存在
					if (!tMember.getTerminalId().trim().equals(tMemberService.getTMemberById(userinfo.getId()).getTerminalId())) {
						if (tMemberService.isExistMobile(tMember.getTerminalId().trim())) {
							return JsonRespWrapper.success("手机号已经被绑定");
						}
					} else {
						logger.info("修改手机号和原来的手机号一样，用户编号：" + userinfo.getId());
						return JsonRespWrapper.success("原手机号码不能与当前手机号码相同");
					}

					logger.info("准备操作数据库对mobile字段进行修改");
					return updateMobile(tMember, hint, request, response, backUrl, userinfo);
				}
				return JsonRespWrapper.success().json("editMobileStep1", "false");
			} else {
				// 验证码为空
				logger.error(hint + "验证码为空");
				return JsonRespWrapper.success("校验码不能为空");
			}
		} else {
			// 手机号码为空
			logger.error("手机为空");
			return JsonRespWrapper.success("手机号不能为空");
		}
	}

	/**
	 * 操作数据库字段Mobile
	 * 
	 * @param tMember
	 * @param hint
	 * @param request
	 * @param response
	 * @param backUrl
	 * @param userinfo
	 * @return
	 */
	private Object updateMobile(Member tMember, String hint, HttpServletRequest request, HttpServletResponse response, String backUrl,
	        LoginUserBean userinfo) {
		// 修改用户手机信息
		soaMemberService.updateMobile(userinfo.getId(), tMember.getTerminalId().trim());
		SSOAgent agent = new SSOAgent(request, response);
		agent.refreshUserInfo(userinfo.getId().toString(), "-1", "www");
		if (StringUtils.isNotBlank(backUrl)) {
			return JsonRespWrapper.redirectSuccess(hint + "手机成功", backUrl);
		}
		return JsonRespWrapper.redirectSuccess(hint + "手机成功", "info.chtml");
	}

}
