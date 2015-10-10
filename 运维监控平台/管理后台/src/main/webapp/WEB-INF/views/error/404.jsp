<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/importer.jsp"%>
<%//response.setStatus(200);%>
<!doctype html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/ui/css/errorpage.css"/>"/>
<title>404错误</title>
</head>
<body>

<div class="error-page">
	<div class="error-img">
		<img src="<c:url value="/static/ui/images/404.png"/>"/>
	</div>
	<div class="error-desc">
	    <p class="error-code">404！</p>
	    <p class="error-desc">很抱歉，您要访问的页面不存在。</p>
	</div>
	<div class="error-act-btn">
		<a href="<c:url value="/home"/>" class="btn back-btn">返回首页</a>
		<a href="mailto:mplus@c-platform.com" class="btn contact-btn">联系我们</a>
	</div>
</div>

</body>
</html>