<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/if.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateEngine.jsp' starting page</title>
    
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
    <div class="control-group group-search">
		  	<div>
		  		<c:forEach items="${moduleList}" var="module">
		  			<input name="module" value="${module.moduleId }" id="module"  type="checkbox" checked="checked" />${module.moduleName}
		  		
		  		</c:forEach>
		    </div>
          <div class="controls controls-row">
              <button type="button" class="btn btn-primary" id="sub" onclick="doUpdate();">
                  <i class="icon16 i-disk"></i> 保 存
              </button>
            
              <button class="btn" type="button" onclick='onCancel();'>
                  <i class="icon16 i-exit"></i> 返 回
              </button>
          </div>
   </div>	  
  </body>
</html>
