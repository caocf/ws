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
			showDialog("查看模版信息", "view?id=" + id, function(doc) {
			}, {
				"Width" : 400,
				"Height" : 700,
				"ShowMessageRow" : false,
				"ShowButtonRow" : false
			});

		});
	});
</script>

</head>
<body>
	<br />
	<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
			<ct:display model="local_life_template" btn="add_btn">
			<ht:menu-btn text="添加模版" url="/locallife/template/add" type="add"/>
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
			<table>
				<tr>
					<td>模版名称：</td>
					<td><input type="text" name="title" value="${param.title}" class="txt"/></td>
					<td><ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<br />
		<h3>模版列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap">模版编号</th>
						<th nowrap="nowrap">模板名称</th>
						<th nowrap="nowrap">模板类型</th>
						<th nowrap="nowrap">创建人</th>
						<th nowrap="nowrap">创建时间</th>
						<th nowrap="nowrap">修改人</th>
						<th nowrap="nowrap">修改时间</th>
						<th nowrap="nowrap">操作</th>
					</tr>
					<c:forEach items="${pageData.data}" var="info">
						<tr>
							<td width="50" nowrap="nowrap">${info.id}</td>
							<td nowrap="nowrap" width="100">${info.title}</td>
							<td nowrap="nowrap" width="100">${info.type eq 1 ? '通用模板':''}</td>
							<td nowrap="nowrap" width="100">${info.createUserName }</td>
							<td nowrap="nowrap" width="100"><ct:time source="${info.createTime}" /></td>
							<td nowrap="nowrap" width="100">${info.updateUserName }</td>
							<td nowrap="nowrap" width="100"><ct:time source="${info.updateTime}" /></td>
							<td width="100" nowrap="nowrap"><ct:display
									model="local_life_template" btn="mod_btn">
									<a href="edit?id=${info.id}">修改</a>
								</ct:display> &nbsp;&nbsp; <ct:display model="local_life_template" btn="del_btn">
									<a href="#" onclick="deleteItem('del?id=${info.id}');">删除</a>
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