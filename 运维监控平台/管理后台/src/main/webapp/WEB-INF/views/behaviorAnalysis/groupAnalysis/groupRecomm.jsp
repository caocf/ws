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

		<title>My JSP 'userInfo.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(document).ready(function(){
			$("#back-to-top").hide();
		})
		
	    
	</script>
	
	
	</head>

	<body>

		<c:if var="flag" test="${fn:length(itemStr.recommendItems) lt 0}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>
		<c:if test="${!flag}">
			<div class="tab-pane fade in active" id="home">
				<form action="" name="itemForm" id="itemForm" method="get">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>
									站内ID
								</th>
								<th>
									推荐名称
								</th>
								<th>
									分类
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${itemStr.recommendItems}" var="recommendItem">
								<c:forEach items="${recommendItem.itemList}" var="item">
									<tr>
										<td class="center vcenter">
											${item.id}
										</td>
										<td class="center vcenter">
											${item.name}
										</td>
	
										<td class="center vcenter">
											${item.category}
										</td>
									</tr>
									</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</c:if>

	</body>
</html>
