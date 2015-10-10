<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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

		<title>My JSP 'commodityInfo.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
	
	<script type="text/javascript">

		function onClick(name,id){
			var shop_name = encodeURI(encodeURI(name));
			window.location.href="${ctx}/analysis/commercial/getItemList?shop_name="+shop_name+"&shopId="+id;
			window.parent.document.getElementById('shop_name').value=name;
		}
	</script>
	</head>

	<body>

		<c:if test="${empty commercialList.data}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>
		<c:if test="${not empty commercialList.data}">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>
							商户ID
						</th>
						<th>
							商户名称
						</th>
						<th>
							所在城市
						</th>
						<th>
							操作
						</th>

					</tr>
				</thead>

				<tbody>
					<c:forEach items="${commercialList.data}" var="item">
						<tr>
							<td class="center vcenter">
								${item[0]}
							</td>
							<td class="center vcenter">
								${item[1]}
							</td>
							<td class="center vcenter">
								${item[2]}
							</td>

							<td class="center vcenter">
								<a href="#" onclick="onClick('${item[1]}','${item[0] }');" >客户细分</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ht:page pageData="${commercialList}" />
		</c:if>
	</body>
</html>
