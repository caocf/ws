<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/if.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
		  <table class="table table-bordered table-hover table-striped">
		 	<tr>
		 		<td>品牌</td>
		 		<c:if test="${userInfo.brand == '10001'}">
		 			<td>全球通</td>
		 		</c:if>
		 		<c:if test="${userInfo.brand == '10002'}">
		 			<td>神州行</td>
		 		</c:if>
		 		<c:if test="${userInfo.brand == '10003'}">
		 			<td>动感地带</td>
		 		</c:if>
		 		<c:if test="${userInfo.brand == '10004'}">
		 			<td>行业卡</td>
		 		</c:if>
		 	</tr>
		 	<tr>
		 		<td>用户类型</td>
		 		<td>
		 			${userInfo.usertype} 
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>所在区域</td>
		 		<td>${userInfo.area_code}</td>
		 	</tr>
		 	<tr>
		 		<td>操作系统</td>
		 		<td>${userInfo.sys}</td>
		 	</tr>
		 	<tr>
		 		<td>机型</td>
		 		<td>${userInfo.model}</td>
		 	</tr>
		 	<tr>
		 		<td>积分余额</td>
		 		<td>${userInfo.score }</td>
		 	</tr><tr>
		 		<td>商城币余额</td>
		 		<td>${userInfo.coin}</td>
		 	</tr>
   		<table>
  </body>
</html>
