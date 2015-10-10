package com.cplatform.b2c.model;

import com.cplatform.b2c.util.AppConfig;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.sso.lmsh.bean.LoginUserBean;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUser extends LoginUserBean {

	public static final String SESSION_USER_KEY = "session_member_key__";

	public static SessionUser getSessionUser(HttpServletResponse response) {
		HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		ServletContext servletContext = curRequest.getSession().getServletContext();
		AppConfig appConfig = WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean(AppConfig.class);
		if (appConfig.isTest()) {
			HttpSession session = curRequest.getSession();
			// SessionUser sessionUser=(SessionUser)
			// session.getAttribute(SESSION_USER_KEY);
			// TODO set mobile or name....
			SessionUser sessionUser = new SessionUser();
			sessionUser.setId(151022803l);
			sessionUser.setAreaCode("320000");
			sessionUser.setNickName("nick name");
			return sessionUser;
		} else {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			SSOAgent agent = new SSOAgent(request, response);
			LoginUserBean userBean = agent.loginUserInfo("mall");

			SessionUser sessionUser = null;
			try {
				if (userBean != null) {
					agent.refreshUserInfo(userBean.getId().toString(), "-1", "www");
					userBean = agent.loginUserInfo("mall");
					sessionUser = new SessionUser();
					BeanUtils.copyProperties(sessionUser, userBean);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return sessionUser;
		}
	}

	private String areaCode;

	private String terminalId;

	private Long id;
	
	private String nickName;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
    public String getNickName() {
    	return nickName;
    }

	
    public void setNickName(String nickName) {
    	this.nickName = nickName;
    }
    
	
}
