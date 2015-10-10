<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />

<script>
	function doSel(x) {
		if (x.checked) {
			$("#mmsvalue",window.parent.document).val(x.value);
		}
	}
</script>
</head>
<body>
	<br />
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="mmsInfoList"
			method="get">
			<table>
				<tr>
					<td>信息内容：</td>
					<td><input type="text" name="title" value="${param.title}"
						class="txt" style="width:206px" />
					</td>
					<td width="70">创建时间：</td>
					<td width="300" />
					<input type="text" id="inputStartTime" name="inputStartTime"
						value="${param.inputStartTime == null ? inputStartTime : param.inputStartTime}"  class="txt Wdate" readOnly
						onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
					<input type="hidden" name="startTime" id="startTime" value="${param.startTime == null ? inputStartTime : param.startTime}"/> 至
					<input type="text" id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime == null ? inputEndTime : param.inputEndTime}" class="txt Wdate"
						readOnly
						onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
					<input type="hidden" name="endTime" id="endTime" value="${param.endTime == null ? inputEndTime : param.endTime}"/>
					</td>

					<td colspan="4">&nbsp; <ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<div class="mainbox">
			<c:if test="${not empty mmsinfo}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="40">选择</th>
						<th nowrap="nowrap" width="120">彩信标题</th>
						<th nowrap="nowrap">开始时间</th>
						<th nowrap="nowrap">结束时间</th>
						<th nowrap="nowrap" width="100">查看内容</th>
					</tr>
					<c:forEach items="${mmsinfo.data}" var="item">
						<tr>
							<td  nowrap="nowrap"><input type="radio" name="id"
								id="id" value="${item.id}" onclick='doSel(this);' /></td>
							<td  nowrap="nowrap">${item.title}</td>
							<td  nowrap="nowrap"><ct:time source="${item.startTime}"/></td>
							<td  nowrap="nowrap"><ct:time source="${item.endTime}"/></td>
							<td  nowrap="nowrap"><a href="javascript:void(0)" onclick=" parent.view(${item.id})">查看</a></td>
						</tr>
					</c:forEach>
				</table>

				<ht:page pageData="${mmsinfo}" />

			</c:if>
			<c:if test="${empty mmsinfo}">
				<div class="note">
					<p class="i">目前没有相关记录!</p>
				</div>
			</c:if>
		</div>

	</div>

</body>
</html>