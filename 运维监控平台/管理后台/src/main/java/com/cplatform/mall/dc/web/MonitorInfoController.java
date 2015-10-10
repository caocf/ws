package com.cplatform.mall.dc.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.mall.dc.dao.DcRoleDao;
import com.cplatform.mall.dc.dao.DcUserDao;
import com.cplatform.mall.dc.dao.MonitorInfoDao;
import com.cplatform.mall.dc.entity.DcRole;
import com.cplatform.mall.dc.entity.DcUser;
import com.cplatform.mall.dc.entity.DcUserRole;
import com.cplatform.mall.dc.entity.MonitorInfo;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.mall.dc.service.DcUserService;
import com.cplatform.mall.dc.service.MonitorInfoService;
import com.cplatform.mall.dc.utils.AppConfig;
import com.cplatform.mall.dc.utils.DateUtil;
import com.cplatform.mall.dc.utils.LogUtils;
import com.cplatform.mall.dc.utils.MailSender;

/**
 * 任务管理
 * <p>
 * Copyright: Copyright (c) 2014-5-21 下午05:04:46
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/monitor")
public class MonitorInfoController {
	@Autowired
	LogUtils logUtil;

	static final Log logger = LogFactory.getLog(MonitorInfoController.class);

	@Autowired
	MonitorInfoService monitorInfoService;
	
	@Autowired
	MonitorInfoDao monitorInfoDao;
	
	@Autowired
	DcUserDao dcUserDao;
	
	@Autowired
	DcUserService dcUserService;
	
	@Autowired
	DcRoleDao dcRoleDao;
	
	@Autowired
	private AppConfig config;
	
	@Autowired
	private MailSender mailSender;
	
	/**
	 * 管理员信息缓存
	 */
	private Map<Long,DcUser> userCache = new HashMap<Long,DcUser>();


	/**
	 * 跳转到工单查看页面
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "")
	public String tolist(Model model) throws SQLException {
		return "/monitor/monitor_list";
	}
	
	/**
	 * 跳转到工单添加页面
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request,Model model) throws SQLException {
		logUtil.recordOpLog("任务添加", "任务添加", LogUtils.OP_TYPE_INSERT);
		getPointUser(request,model);
		return "/monitor/monitor_add";
	}
	
	
	
	/**
	 * json动态获得最新工单信息
	 * @param request
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String getList(HttpServletRequest request,Model model) throws SQLException {
		logUtil.recordOpLog("任务管理", "任务查看", LogUtils.OP_TYPE_QUERY);
		List<MonitorInfo> monitorList = new ArrayList<MonitorInfo>();
		try{
			DcRole dcrole = getDcUserRole(request);
			if(dcrole.getRoleName().equals("超级管理员")||dcrole.getRoleName().equals("移动")){
				monitorList = monitorInfoService.getMonitorList();
			}else{
				monitorList = monitorInfoService.getMonitorList(dcrole.getRoleName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JSONArray json = JSONArray.fromObject(getList(request, monitorList));
		return json.toString();
	}
	

	
	
	
	/**
	 * 跳转到工单处理页
	 * @param model
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/monitorHandle", method = RequestMethod.GET)
	public String handle(Model model, String id) throws SQLException {
		MonitorInfo info = monitorInfoDao.findOne(Long.parseLong(id));
		getMonitorInfo(model, info);
		model.addAttribute("monitor",info);
		return "/monitor/monitor_handle";
	}
	
	/**
	 * 工单查看 
	 * @param model
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/monitorCheck", method = RequestMethod.GET)
	public String check(Model model, String id) throws SQLException {
		MonitorInfo info = monitorInfoDao.findOne(Long.parseLong(id));
		getMonitorInfo(model, info);
		model.addAttribute("monitor",info);
		return "/monitor/monitor_check";
	}
	


	/**
	 * 工单添加
	 * @param request
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String toAdd(HttpServletRequest request,Model model,MonitorInfo info) {
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		try{
			if(sessionUser!=null){
				info.setMonitor_create_name(sessionUser.getId());
			}else{
				throw new Exception("用户不存在");
			}
			String date = DateUtil.format(new Date(), "yyyyMMddHHmmss");
			String monitor_time = info.getMonitor_time();
			if(monitor_time == null || "".equals(monitor_time)){
				info.setMonitor_time(date);
			}else{
				info.setMonitor_time(monitor_time.replace("-", "").replace(" ", "").replace(":", ""));
			}
			info.setCollect_time(date);
			info.setStatus("1");
			String[] appointNames = request.getParameterValues("appointNames");
			StringBuffer appoint_deal_name = new StringBuffer();
			if(appointNames!=null&&appointNames.length>0){
				for(String name : appointNames){
					appoint_deal_name.append(name+",");
				}
			}
			String appoint = appoint_deal_name.toString();
			if(appoint.length()>0){
				appoint = appoint.substring(0,appoint_deal_name.length()-1);
			}
			info.setAppoint_deal_name(appoint);
			monitorInfoService.addMonitorInfo(info);
			model.addAttribute("msg","添加任务成功");
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","添加任务失败");
		}
		model.addAttribute("monitor", info);
		getPointUser(request,model);
		
		//邮件发送
//		String appoint_deal_name = info.getAppoint_deal_name();
//		List<DcUser> dealNameList = new ArrayList<DcUser>();
//		if(appoint_deal_name!=null&&appoint_deal_name.length()>0){
//			for(String deal_name : appoint_deal_name.split(",")){
//				Long userId = Long.parseLong(deal_name);
//				DcUser appointUser = userCache.get(userId);
//				if(appointUser == null){
//					appointUser = dcUserDao.findOne(userId);
//					if(appointUser!=null)
//						userCache.put(userId, appointUser);
//				}
//				if(appointUser!=null){
//					dealNameList.add(appointUser);
//				}
//			}
//		}
//		
//		if(dealNameList.size()>0){
//			String reviceOjb = "";
//			String ccObj = "";
//			for(int i=0;i<dealNameList.size();i++){
//				if(i==0)
//					reviceOjb = dealNameList.get(0).getEmail();
//				else
//					ccObj = ccObj + dealNameList.get(i).getEmail() + ";";
//			}
//			mailSender.sendMails(reviceOjb, ccObj, "运维系统报警邮件",
//					"警告内容："+info.getMonitor_content()+"<br/>告警时间："+info.getMonitor_time()
//					+"<br/>警告发布人："+sessionUser.getName());	
//		}
		return "/monitor/monitor_add";
	}
	
	
	/**
	 * 工单处理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/monitorHandle", method = RequestMethod.POST)
	public String tohandle(HttpServletRequest request,Model model) {
		try{
			String id = request.getParameter("seqId");
			if(id==null){
				throw new Exception("任务不存在");
			}
			MonitorInfo info = monitorInfoDao.findOne(Long.parseLong(id));
			if(info == null){
				throw new Exception("任务不存在");
			}
			if(!info.getStatus().equals("1")){
				throw new Exception("任务已处理");
			}
			SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			if(sessionUser!=null){
				info.setDeal_name(sessionUser.getId());
			}else{
				throw new Exception("用户不存在");
			}
			String date = DateUtil.format(new Date(), "yyyyMMddHHmmss");
			info.setDeal_time(date);
			info.setStatus("2");
			info.setDeal_content(request.getParameter("deal_content"));
			monitorInfoDao.save(info);
			model.addAttribute("msg","处理成功");
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","处理失败");
		}
		return "/monitor/monitor_list";
	}
	
	
	/**
	 * 工单搜索
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/search")
	public String search(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			String dateStr,String dateEnd,String status,
			Model model) throws SQLException {
		if(dateStr==null&&dateEnd==null){
			dateStr = request.getSession().getAttribute("dateStr").toString();
			dateEnd = request.getSession().getAttribute("dateEnd").toString();
			status = request.getSession().getAttribute("status").toString();
		}else{
			request.getSession().setAttribute("dateStr",dateStr);
			request.getSession().setAttribute("dateEnd",dateEnd);
			request.getSession().setAttribute("status",status);
		}
		String start = dateStr.replace("-", "").replace(" ", "").replace(":", "");
		String end = dateEnd.replace("-", "").replace(" ", "").replace(":", "");
		logUtil.recordOpLog("工单管理", "工单管理", LogUtils.OP_TYPE_QUERY);
		DcRole dcrole = null;
		try {
			dcrole = getDcUserRole(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListPage<Map<String, Object>> list = monitorInfoService.getMonitorListByProperty(start, end, status, dcrole.getRoleName(), page, pageSize);
		list = getMapList(request, list);
		model.addAttribute("monitorPage",list);
		model.addAttribute("doPush",1);
		model.addAttribute("dateStr",dateStr);
		model.addAttribute("dateEnd",dateEnd);
		model.addAttribute("status",status);
		return "/monitor/monitor_list";
	}
	
	private DcRole getDcUserRole(HttpServletRequest request) throws Exception{
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		DcRole dcrole = null;
		if(sessionUser!=null){
			dcrole = dcRoleDao.findByUserId(sessionUser.getId());
		}else{
			throw new Exception("用户不存在");
		}
		return dcrole;
	}
	
	/**
	 * 获得指定处理用户信息
	 * @param model
	 */
	private Map<String, List<DcUser>> getPointUser(HttpServletRequest request,Model model){
		try{
			
			String roles = config.getMonitorRole();
			Map<String, List<DcUser>> userMap = new HashMap<String, List<DcUser>>();
			DcRole dcrole = getDcUserRole(request);
			if(dcrole!=null){
				if(roles!=null&&!"".equals(roles)){
					String[] role = roles.split(",");
					Object[] newRole = new String[]{};
					for(int i=0;i<role.length;i++){
						String monitorRole = role[i];
						if(dcrole.getRoleName().equals("超级管理员")||dcrole.getRoleName().equals("移动")||monitorRole.equals(dcrole.getRoleName())){
							List<DcUser> dcUsers = dcUserDao.findMonitorUsers(monitorRole);
							userMap.put(monitorRole,dcUsers);
							newRole = ArrayUtils.add(newRole, monitorRole);
						}
					}
					model.addAttribute("monitorRole",newRole);
					model.addAttribute("monitorUser",userMap);
		 		}
				List<Map<String, String>> roleNames = dcUserService.findRolesByNames(roles);
				model.addAttribute("dcRoles",roleNames);
			}
			String platName = "-1";
			if(dcrole.getRoleName().equals("超级管理员")||dcrole.getRoleName().equals("移动")){
				platName = "0";
			}else {
				Map<String,String> plats = MonitorInfo.platMap;
				String plat = plats.get(dcrole.getRoleName());
				if(plat!=null){
					platName = plat;
				}
			}
			model.addAttribute("_platName",platName);
			return userMap;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 补充工单信息
	 * @param request
	 * @param monitorList
	 * @return
	 */
	private List<MonitorInfo> getList(HttpServletRequest request,List<MonitorInfo> monitorList){
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		String CONTEXT = request.getContextPath();
		if(sessionUser!=null&&monitorList!=null&&monitorList.size()>0){
			for(MonitorInfo info : monitorList){
				String appoint_deal_name = info.getAppoint_deal_name();
				String[] deal_names = appoint_deal_name.split(",");
				String txt = "<a href=\""+CONTEXT+"/monitor/monitorCheck?id=" + info.getSeqId() + "\">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;";
				if (info.getStatus().equals("1"))
					for (String name : deal_names) {
						if (Long.parseLong(name)==sessionUser.getId()) {
							txt = txt + "<a href=\""+CONTEXT+"/monitor/monitorHandle?id=" + info.getSeqId() + "\"><span style=\"color:#F00\">处理</span></a>";
							break;
						}
					}
				info.setTxt(txt);
				String monitor_time = info.getMonitor_time();
				if(monitor_time.length()==14)
					monitor_time = monitor_time.substring(0,4)+"-"+monitor_time.substring(4,6)+"-"+monitor_time.substring(6,8)
			    		+" "+monitor_time.substring(8,10)+":"+monitor_time.substring(10,12);
				info.setMonitor_time(monitor_time);
				DcUser createUser = userCache.get(info.getMonitor_create_name());
				if(createUser==null){
					createUser = dcUserDao.findOne(info.getMonitor_create_name());
					userCache.put(info.getMonitor_create_name(),createUser);
				}
				info.setTemp_name(createUser.getName());
			}
		}
		return monitorList;
	}
	
	/**
	 * 补充工单信息
	 * @param request
	 * @param monitorList
	 * @return
	 */
	private ListPage<Map<String, Object>> getMapList(HttpServletRequest request,ListPage<Map<String, Object>> monitorList){
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		String CONTEXT = request.getContextPath();
		if(sessionUser!=null&&monitorList!=null&&monitorList.getData().size()>0){
			for(Map<String, Object> info : monitorList.getData()){
				String appoint_deal_name = ""+info.get("appoint_deal_name");
				String[] deal_names = appoint_deal_name.split(",");
				String txt = "<a href=\""+CONTEXT+"/monitor/monitorCheck?id=" + info.get("seq_id") + "\">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;";
				if (info.get("status").equals("1"))
					for (String name : deal_names) {
						if (Long.parseLong(name)==sessionUser.getId()) {
							txt = txt + "<a href=\""+CONTEXT+"/monitor/monitorHandle?id=" + info.get("seq_id") + "\"><span style=\"color:#F00\">处理</span></a>";
							break;
						}
					}
				info.put("txt", txt);
				String monitor_time = ""+info.get("monitor_time");
				if(monitor_time.length()==14)
					monitor_time = monitor_time.substring(0,4)+"-"+monitor_time.substring(4,6)+"-"+monitor_time.substring(6,8)
			    		+" "+monitor_time.substring(8,10)+":"+monitor_time.substring(10,12);
				info.put("monitor_time",monitor_time);
				DcUser createUser = userCache.get(info.get("monitor_create_name"));
				if(createUser==null){
					createUser = dcUserDao.findOne(Long.parseLong(""+info.get("monitor_create_name")));
					userCache.put(Long.parseLong(""+info.get("monitor_create_name")),createUser);
				}
				info.put("temp_name",createUser.getName());
			}
		}
		return monitorList;
	}
	
	/**
	 * 补充用户信息
	 * @param model
	 * @param info
	 */
	private void getMonitorInfo(Model model, MonitorInfo info){
		String monitor_time = info.getMonitor_time();
		if(monitor_time.length()==14){
			info.setMonitor_time(DateUtil.format(monitor_time, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
		}
		DcUser createUser = userCache.get(info.getMonitor_create_name());
		if(createUser==null){
			createUser = dcUserDao.findOne(info.getMonitor_create_name());
			userCache.put(info.getMonitor_create_name(),createUser);
		}
		if(createUser!=null){
			model.addAttribute("createUser",createUser);
		}
		String appoint_deal_name = info.getAppoint_deal_name();
		List<DcUser> dealNameList = new ArrayList<DcUser>();
		if(appoint_deal_name!=null&&appoint_deal_name.length()>0){
			for(String deal_name : appoint_deal_name.split(",")){
				Long userId = Long.parseLong(deal_name);
				DcUser appointUser = userCache.get(userId);
				if(appointUser == null){
					appointUser = dcUserDao.findOne(userId);
					if(appointUser!=null)
						userCache.put(userId, appointUser);
				}
				if(appointUser!=null){
					dealNameList.add(appointUser);
				}
			}
		}
		if(!info.getStatus().equals("1")){
			DcUser dealUser = userCache.get(info.getDeal_name());
			if(dealUser==null){
				dealUser = dcUserDao.findOne(info.getDeal_name());
				userCache.put(info.getDeal_name(),dealUser);
			}
			if(dealUser!=null){
				model.addAttribute("dealUser",dealUser);
			}
			String deal_time = info.getDeal_time();
			if(deal_time!=null&&deal_time.length()==14){
				info.setDeal_time(DateUtil.format(deal_time, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
			}
		}
		model.addAttribute("appoint_deal_user",dealNameList);
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
	}

}
