<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
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
		<h3>更改原因列表</h3>
		<div class="mainbox">
			<c:if test="${not empty reasonMap}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80px">选择</th>
						<th nowrap="nowrap">更改原因</th>
					</tr>

					  <c:forEach items="${reasonMap}" var="item" varStatus="index">
            			<tr>
             				<td nowrap="nowrap"><input type="radio"  id="radio-${index.count}" name="updateSatusSelector" updateTime="${updateTime }" updateUser="${updateUser }"  reasonId="${item.key }" reasonName="${ item.value}" class="validate-one-required"/></td>
							<td nowrap="nowrap"  class="ellipsis">${item.value}</td>
						</tr>
					</c:forEach>
				</table>
				<ht:page pageData="${pageData}" />

			</c:if>
		</div>

	</div>

</body>
</html>