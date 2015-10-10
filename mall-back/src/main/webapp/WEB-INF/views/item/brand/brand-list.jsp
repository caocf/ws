<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">
	$().ready(function() {
	});
</script>
</head>
<body>
	<br />
	<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
			<ct:display model="brand_list" btn="add_btn">
				<ht:menu-btn text="添加品牌" url="/item/brand/addbrand" />
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="list" method="get">
			<table>
				<tr>
					<td>品牌名称：</td>
					<td><input type="text" name="name" value="${param.name}"
						class="txt" style="width:120px" />
					</td>
					<td width="70">创建时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.inputStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'queryStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="queryStartTime" id="queryStartTime"
						value="${param.queryStartTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime}"  class="txt Wdate"
						readOnly
						onclick="WdatePicker({vel:'queryEndTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
						<input type="hidden" name="queryEndTime" id="queryEndTime"
						value="${param.queryEndTime}" /></td>
					<td><ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div class="container">
		<br />
		<h3>品牌列表</h3>

		<div class="mainbox">
			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80">品牌编号</th>
						<th nowrap="nowrap" width="300">品牌名称</th>
						<th nowrap="nowrap"  width="200">创建时间</th>
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap" >${item.id}</td>
							<td nowrap="nowrap"  class="ellipsis">${item.name}</td>
							<td nowrap="nowrap" ><ct:time		source="${item.createTime}" />
							</td>
							<td width="100" nowrap="nowrap">
								<ct:display model="brand_list" btn="view_btn">
									<a href="view/${item.id }" id="${item.id }" class="item_view">查看</a>
								</ct:display> 
								<ct:display model="brand_list" btn="mod_btn">
									<a href="edit/${item.id}">修改</a>
								</ct:display> 
								<ct:display model="brand_list" btn="del_btn">
									<a href="#" onclick="deleteItem('delete/${item.id}');">删除</a>
								</ct:display></td>
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