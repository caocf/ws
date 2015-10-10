<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">
	$().ready(function() {
		$(".item_view").click(function() {
			var id = $(this).attr("id");
			window.location = 'view/' +id;

		});
	});
</script>
</head>
<body>
	<br />
	<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="salelist" method="get">
			<table>
				<tr>
					<td>商品名称：</td>
					<td><input type="text" name="name" value="${param.name}"
						class="txt" style="width:120px" /></td>
					<td width="70">创建时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.inputStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'queryStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="queryStartTime" id="queryStartTime"
						value="${param.queryStartTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime}" class="Wdate" class="txt Wdate"
						readOnly
						onclick="WdatePicker({vel:'queryEndTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
						<input type="hidden" name="queryEndTime" id="queryEndTime"
						value="${param.queryEndTime}" />
					</td>
					<td><ct:btn type="search" />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div class="container">
		<br />
		<h3>商品列表</h3>

		<div class="mainbox">
			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="50">ID</th>
						<th nowrap="nowrap" width="250">结算商户</th>
						<th nowrap="nowrap"  width="250">名称</th>
						<th nowrap="nowrap"  width="60">虚拟产品</th>
						<th nowrap="nowrap" width="60">套餐产品</th>
						 <th nowrap="nowrap" width="80">商品分类</th>
						<th nowrap="nowrap">状态</th>
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td  nowrap="nowrap">${item.id}</td>
							<td nowrap="nowrap" >${item.storeName}</td>
							<td nowrap="nowrap" >${item.name}</td>
							<td nowrap="nowrap" >${item.itemModeName}</td>
							<td nowrap="nowrap" >${item.groupFlagName}</td>
							<td nowrap="nowrap" >${item.typeName}</td>
							<td nowrap="nowrap" >${item.statusName}</td>
							<td width="100" nowrap="nowrap"><ct:display
									model="sale_list" btn="mod_btn">
									<a href="#" id="${item.id }" class="item_view">查看</a>
								</ct:display> <ct:display model="sale_list" btn="publish_btn">
									<a href="addsaleitem/${item.id}">发布</a>
								</ct:display> </td>
						</tr>
					</c:forEach>
				</table>

				<ht:page pageData="${pageData}" />

			</c:if>
			<c:if test="${empty pageData}">
				<div class="note">
					<p class="i">目前没有相关记录!</p>
				</div>
			</c:if>
		</div>

	</div>

</body>
</html>