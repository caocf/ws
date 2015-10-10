<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>商品详情</title> 
    
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
  <div>
  <c:choose>
  	<c:when test="${item.remark ==null ||item.remark == '' }">
  	<div style="width: 100%;height: 200px;text-align: center;line-height: 200px;">
		该商品 暂无介绍信息...
	</div>  	
  	</c:when>
  	<c:otherwise>
  	 ${item.remark }
  	</c:otherwise>
  </c:choose>
  
  </div>
  
    
  </body>
</html>
