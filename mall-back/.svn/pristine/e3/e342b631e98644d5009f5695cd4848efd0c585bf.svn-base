<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>

<head>
<ht:head />
</head>
<body>
	<br>
	<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
		</ul>
		<br style="clear: left" />
	</div>
	<div class="container">
		<br />
		<h3>码导入记录</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" >编号</th>
						<th nowrap="nowrap" >验证码名称</th>
						<th nowrap="nowrap" >商户名称</th>
						<th nowrap="nowrap" >导入文件名</th>
						<th nowrap="nowrap" >导入数量</th>
						<th nowrap="nowrap" >创建时间</th>
						<th nowrap="nowrap" >操作人</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap">${item.id}</td>
							<td nowrap="nowrap" class="ellipsis">${item.codeName}</td>
							<td nowrap="nowrap" class="ellipsis">${item.storeName}</td>
							<td nowrap="nowrap" class="ellipsis">${item.filePath}</td>
							<td nowrap="nowrap">${item.totalCount }</td>
							<td nowrap="nowrap"><ct:time source="${item.createTime}" /></td>
							<td nowrap="nowrap">${item.userName }</td>
							
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