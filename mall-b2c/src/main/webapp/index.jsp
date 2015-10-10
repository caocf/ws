<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ct" uri="/cplatform-tag" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="org.springframework.web.context.WebApplicationContext" %>
<%@page import="com.cplatform.b2c.service.CommonCacheService"%>
<%@page import="com.cplatform.b2c.constant.AreaCode"%>
<%@ page import="com.cplatform.b2c.util.CookieUtils" %>
<% 

String areaCode = "";
Cookie areaCookie = CookieUtils.getCookie(request, "areaCode");
if(areaCookie == null|| "".equals(areaCookie.getValue())){
	areaCode = "0512";
}else{
	areaCode = areaCookie.getValue();
	//不支持河南
	if(AreaCode.heNanAreaCodeSet.contains(areaCode)){
		areaCode = "0512";
	}
}

WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
CommonCacheService commonservice = (CommonCacheService) ac.getBean("commonCacheService");

String spell = commonservice.getRegionSpellByAreaCode(areaCode, "1");
response.sendRedirect("channel/"+spell+".htm");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>首页</title>
</head>
<body id="usercenter">
</body>
</html>
