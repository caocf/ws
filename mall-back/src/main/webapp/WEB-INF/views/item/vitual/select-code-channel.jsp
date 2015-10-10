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
		<h3>发码渠道列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80px">选择</th>
						<th nowrap="nowrap">发码渠道</th>
						<th nowrap="nowrap">发码方式</th>
					</tr>

					  <c:forEach items="${pageData.data}" var="item" varStatus="index">
            			<tr>
             				<td nowrap="nowrap"><input type="radio"  id="radio-${index.count}" name="sendCodeSelector"   sendChannelName="${item.sendChannelName}" sendChannelId="${item.sendChannelId}"  sendTypeId="${item.sendTypeId }" sendTypeName="${item.sendTypeName}"  class="validate-one-required"/></td>
							<td nowrap="nowrap"  class="ellipsis">${item.sendChannelName}</td>
							<td nowrap="nowrap"  class="ellipsis">${item.sendTypeName}</td>
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