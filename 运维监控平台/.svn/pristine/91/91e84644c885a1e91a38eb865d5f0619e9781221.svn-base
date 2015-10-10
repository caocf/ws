<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="推荐效果" />
<%@ include file="../../includes/t.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <script type="text/javascript">
		function changePage(module){
			var divs = [ "channel", "engine","contentId"];
			for (var i = 0; i < divs.length; i++) {
				if (divs[i] == module) {
					document.getElementById(divs[i]).className = "btn btn-primary";
					$("#busiFrame").attr("src","${ctx}/recommendMgmt/effect/effectInfo?module="+divs[i]);
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
				onclick="changePage('channel');"> <font id="channel"
				style="font-weight: bold;" class="btn btn-primary">渠道效果</font> </a>
			<a href="#" style="padding-right: 10px"
				onclick="changePage('engine');"> <font id="engine"
				style="font-weight: bold;" class="btn">引擎效果</font> </a>
			<a href="#" style="padding-right: 10px"
				onclick="changePage('contentId');"> <font id="contentId" 
				style="font-weight: bold;" class="btn">内容效果</font> </a>
	</div>
	<div>
		<iframe src="<%=path%>/recommendMgmt/effect/effectInfo" id="busiFrame" name="busiFrame" marginwidth="0"
					marginheight="0" frameborder="0" width="100%" height="1800"
					scrolling="no">
		</iframe>
			
	</div>
  </body>
</html>
