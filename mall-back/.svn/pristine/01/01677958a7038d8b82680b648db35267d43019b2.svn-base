<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />

<script type="text/javascript">
	//翻页
	function turnPage(pageIndex) {
		var url = $("#url").val();
		var orderId=$("#orderId").val();
		if (url.indexOf("?") > 0) {
			url = url + "&pageIndex=" + pageIndex+"&orderId="+orderId;
		} else {
			url = url + "/" + pageIndex;
		}
		window.location = url;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="mainbox">
			<input type="hidden" id="orderId" value="${orderId}"/>
			<c:if test="${not empty pagination.dataList}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80">时间</th>
						<th nowrap="nowrap" width="250">地点和追踪进度</th>
					</tr>

					  <c:forEach items="${pagination.dataList}" var="data" varStatus="index">
            			<tr>
             				<td >
             				<span>${data.time }</span>
             				</td>
							<td >${data.context}</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="../includes/pagination.jsp"%>
			</c:if>
			<c:if test="${empty pagination.dataList}">
				<div class="note">
					<p class="i">目前没有相关记录!</p>
				</div>
			</c:if>
				
			
		</div>

	</div>

</body>
</html>