<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>管理后台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<spring:url value="/static/css/main.css"/>" type="text/css" media="all" />
</head>
<body>
<div class="top_bg">
    <div class="topleft"></div>
    <div class="topright">
        您好, <strong>
        ${loginName}</strong>
        <img src="<spring:url value="/static/images/nettop_07.jpg"/>" border="0" align="absmiddle" usemap="#Map" />
        <map name="Map" id="Map">
            <area shape="circle" coords="21,30,16" href="${ctx }/menu/welcome" title="首页" target="content-iframe"/>
            <area shape="circle" coords="64,31,14" href="${ctx }/sys/user/changePwd" title="修改密码" target="content-iframe"/>
            <area shape="circle" coords="109,30,15" href="${ctx}/logout" title="退出" target="_parent"/>
        </map>
    </div>
</div>
</body>
</html>