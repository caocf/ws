<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />

<script type="text/javascript">

</script>
</head>
<body>
	<br />
	<div class="container">
		<br />
		<h3>码库列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80px">选择</th>
						<th nowrap="nowrap">码库编号</th>
						<th nowrap="nowrap">码库名称</th>
					</tr>

					  <c:forEach items="${pageData.data}" var="item" varStatus="index">
            			<tr>
             				<td nowrap="nowrap"><input type="radio"  id="radio-${index.count}" name="sendCodeSelector"   codeStockId="${item.id }"  codeStockName="${item.name }"   class="validate-one-required"/></td>
							<td nowrap="nowrap"  class="ellipsis">${item.id}</td>
							<td nowrap="nowrap"  class="ellipsis">${item.name}</td>
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
		</div>

	</div>

</body>
</html>