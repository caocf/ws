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
		<h3>费率列表</h3>
		<div class="mainbox">
			<c:if test="${not empty feeList}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80px">选择</th>
						<th nowrap="nowrap">费率名称</th>
						<th nowrap="nowrap" width="100px">费率</th>
					</tr>

					  <c:forEach items="${feeList}" var="item" varStatus="index">
            			<tr>
             				<td nowrap="nowrap"><input type="radio"  id="radio-${index.count}" name="shopIdSelector"  shopName="${item.name}"  value="${item.id}"  class="validate-one-required"/></td>
							<td nowrap="nowrap"  class="ellipsis">${item.name}</td>
							<td nowrap="nowrap">${item.fee}</td>
						</tr>
					</c:forEach>
				</table>

			</c:if>
			<c:if test="${empty feeList}">
				<div class="note">
					<p class="i">目前没有相关记录!</p>
				</div>
			</c:if>
		</div>

	</div>

</body>
</html>