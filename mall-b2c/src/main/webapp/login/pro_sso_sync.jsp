<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String backURL = request.getParameter("backURL")==null?"http://www.12580life.com":request.getParameter("backURL");
backURL = backURL.indexOf("pro_sso_sync")!=-1?"http://www.12580life.com":backURL;
String mode = request.getParameter("mode");
String tip = "页面跳转中，请稍候";
if("logout".equals(mode)){
	tip = "用户已注销，如有需要请重新登陆";
}else if("change".equals(mode)){
	tip = "页面跳转中，请稍候";
}
pageContext.setAttribute("indexDomain","http://www.12580life.com");
pageContext.setAttribute("mallDomain","http://mall.12580life.com");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>页面跳转中，请稍候</title>
<meta name="keywords" content="" />
<meta http-equiv="description" content="" />
<style>
body{text-align: center;}
#wait_cont{
	position:absolute;
	margin:-50px 0 0 -110px;
	top:50%;
	left:50%;
}
</style>
<!--  -->
<script>
var proBackUrl="<%=backURL %>";
var mallDomain="${mallDomain}";
</script>
<script src="${indexDomain }/js/jquery.js" type="text/javascript"></script>
<script src="${mallDomain}/js/ssojsonp.js?v=1304231655" type="text/javascript"></script>
</head>
<body>
	<div id="wait_cont" style="color:#9c9c9c;font-size:16px;font-family:楷体"><b><%=tip %></b><br/><img src="${mallDomain}/login/loading1.gif"/></div>
</body>
</html>