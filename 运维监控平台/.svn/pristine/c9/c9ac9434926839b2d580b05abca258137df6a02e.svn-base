<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="引擎管理" />
<%@ include file="../../includes/t.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function changePage(module){
			var divs = [ "engine", "algorithm"];
			for (var i = 0; i < divs.length; i++) {
				if (divs[i] == module) {
					document.getElementById(divs[i]).className = "btn btn-primary";
					$("#busiFrame").attr("src","${ctx}/recommendMgmt/engineMgmt/managerList?module="+module);
				}else{
					document.getElementById(divs[i]).className = "btn";
				}
			}
		}
	
	</script>
  </head>
  
  <body>
   		<div style="margin: 5px 0px">
			<a href="#" style="padding-right: 10px"
				onclick="changePage('engine');"> <font id="engine"
				style="font-weight: bold;" class="btn btn-primary">引擎管理</font> </a>
			<a href="#" style="padding-right: 10px"
				onclick="changePage('algorithm');"> <font id="algorithm" 
				style="font-weight: bold;" class="btn">算法管理</font> </a>
		</div>
		<div>
			<iframe src="<%=path%>/recommendMgmt/engineMgmt/managerList" id="busiFrame" name="busiFrame" marginwidth="0"
					marginheight="0" frameborder="0" width="100%" height="580"
					scrolling="auto">
			</iframe>
		</div>
  </body>
</html>
