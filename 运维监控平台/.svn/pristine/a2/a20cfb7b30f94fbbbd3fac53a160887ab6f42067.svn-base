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

		<title>My JSP 'commodityInfo.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
			function onClick(name,id){
				var good_title = encodeURI(encodeURI(name));
				window.location.href="${ctx}/analysis/commodity/getItemList?good_title="+good_title+"&itemId="+id;
				window.parent.document.getElementById('good_title').value=name;
			}
		</script>
	</head>
	<body>
		
		<c:if test="${empty commodityList.data}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>
		<c:if test="${not empty commodityList.data}">
			<div class="tab-pane fade in active" id="home">
			 <table class="table table-bordered table-hover table-striped">
				<thead>
				<tr>
					<th>
						商品ID
					</th>
					<th>
						商品名称
					</th>
					<th>
						所属商户
					</th>
					<th>
						所属地域
					</th>
					<th>
						所属分类
					</th>
					<th>
						操作
					</th>
				</tr>
			 </thead>
			
			<tbody>
			<c:forEach items="${commodityList.data}" var="item">
				<tr>
					<td>
						${item[0] }
					</td>
					<td>
						${item[2] }
					</td>
					<td>
						${item[1] }
					</td>
					<td>
						${item[3] }
					</td>
					<td>
						${item[4] }
					</td>
					<td>
						<a href="#" onclick="onClick('${item[2]}','${item[0] }');" target="_top">客户细分</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		  </table>
	  </div>
	  <ht:page pageData="${commodityList}" />
	</c:if>
	</body>
</html>
