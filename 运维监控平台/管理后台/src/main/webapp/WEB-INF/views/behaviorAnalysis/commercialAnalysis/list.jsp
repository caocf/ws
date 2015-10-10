<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户分析" />
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
	<script type="text/javascript" src="/webapp/static/js/jquery-1.8.3.js"></script>
	
	<script type="text/javascript">
		function getFilter(){
				var shop_name=encodeURI(encodeURI($("#shop_name").val()));
				jQuery("#busiFrame").attr("src","${ctx}/analysis/commercial/commercialInfo?shop_name="+shop_name);
		}

		function getResults(){
			var frm = document.getElementById("queryForm");
			frm.action = "${ctx}/analysis/commercial/getResults";
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
                 		 <label class="control-label">商户名称：</label>
                  		<div class="controls controls-row">
                    
                  			<div>
								<input type="text" id="shop_name" name="shop_name" maxlength="40" value="${shop_name}"/>
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
			<iframe src="<%=path %>/analysis/commercial/commercialInfo?shop_name=${shop_name}" id="busiFrame" name="busiFrame" marginwidth="0"
				marginheight="0" frameborder="0" width="100%" height="760"
				scrolling="no">
			</iframe>
		</div>
		
</body>
</html>
