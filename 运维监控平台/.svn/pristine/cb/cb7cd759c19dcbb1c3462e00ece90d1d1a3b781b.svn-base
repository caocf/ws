<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商品分析" />
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
	<script type="text/javascript"
			src="/webapp/static/js/jquery-1.8.3.js"></script>
	
	<script type="text/javascript">

		function getFilter(){
<%--			var frm = document.getElementById("queryForm");--%>
			var good_title=encodeURI(encodeURI($("#good_title").val()));
<%--			frm.action = "${ctx}/analysis/commodity/";--%>
<%--			frm.submit();--%>
			jQuery("#busiFrame").attr("src","${ctx}/analysis/commodity/commodityInfo?good_title="+good_title);
		}

		function getResults(){
			var frm = document.getElementById("queryForm");
			frm.action = "${ctx}/analysis/commodity/getResults";
			frm.submit();
		}
	</script>
  </head>
  
  <body>
   		<div class="daily" id="daily_dxqf">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="tab-pane fade in active" id="home">
					<form class="form-horizontal" action="" name="queryForm"
						id="queryForm" method="post">
            			<div class="tab-pane fade in active" id="home">
              			 <div class="control-group group-search" style="margin-left: 0px;">
                 		 <label class="control-label">商品名称：</label>
                  		<div class="controls controls-row">
                    
                  			<div>
								<input type="text" id="good_title" name="good_title" maxlength="40" value="${good_title}"/>
								<button class="btn btn-primary" type="button" onclick="getFilter();">
                                    <i class="icon16 i-search"></i> 筛选
                                </button>
                                
                                <button class="btn btn-primary" onclick="getResults();">
                                    <i class="icon16 i-search"></i> 查看历史结果
                                </button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
		
		<div>
			<iframe src="<%=path %>/analysis/commodity/commodityInfo?good_title=${good_title}" id="busiFrame" name="busiFrame" marginwidth="0"
				marginheight="0" frameborder="0" width="100%" height="760"
				scrolling="no">
			</iframe>
		</div>
		
</body>
</html>
