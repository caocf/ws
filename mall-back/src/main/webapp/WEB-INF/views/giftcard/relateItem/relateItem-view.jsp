<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>

<head>
<ht:head />
</head>
<body>
<!-- 	<div id="search-menu"> -->
<!-- 		<ul> -->
<%-- 			<ht:menu-btn type="search" /> --%>
<!-- 		</ul> -->
<!-- 		<br style="clear: left" /> -->
<!-- 	</div> -->
<!-- 	<div class="queryContainer"> -->
<!-- 	</div> -->
	<div class="container">
		<br />
		<h3>绑定商品列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="150">绑定商品编号</th>
<!-- 						<th nowrap="nowrap" width="150">批次号</th> -->
						<th nowrap="nowrap" width="150">商品编号</th>
						<th nowrap="nowrap" width="200">商品名称</th>
						<th nowrap="nowrap" width="200">商户名称</th>
<!-- 						<th nowrap="nowrap" >状态</th> -->
					</tr>
					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap">${item.id}</td>
<%-- 							<td nowrap="nowrap">${item.batchNo}</td> --%>
							<td nowrap="nowrap">${item.itemId}</td>
							<td nowrap="nowrap" class="ellipsis">${item.itemName }</td>
							<td nowrap="nowrap" class="ellipsis">${item.storeName}</td>
<%-- 							<td nowrap="nowrap">${item.statusName}</td> --%>
						</tr>
					</c:forEach>
				</table>

				<ht:page pageData="${pageData}" />
			</c:if>
			<c:if test="${empty pageData.data}">
				<div class="note">
					<p class="i">目前没有相关记录!</p>
				</div>
			</c:if>
			<input type="button" class="common_btn" onclick="history.back();" value="返回" />
		</div>

	</div>

</body>
</html>