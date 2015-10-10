package com.cplatform.tag;

import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.PathUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 获取商品、商户、及图片的路径 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) Jun 17, 2013 3:56:43 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class ItemPathTag extends TagSupport {

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ItemPathTag.class);

	/**
	 * 商品id 或 商户id
	 */
	protected String id = null;

	/**
	 * 1商品，3商户，2图片
	 */
	protected String flag = null;

	@Override
	public int doStartTag() throws JspException {
		String ret = null;
		try {
			PathUtil pathUtil = WebApplicationContextUtils
					.getWebApplicationContext(pageContext.getServletContext())
					.getBean(PathUtil.class);
			ret = pathUtil.getPathById(flag, id);
			pageContext.getOut().write(ret);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
		}
		return 0;
	}

	@Override
	public void release() {
		super.release();
		id = null;
		flag = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
