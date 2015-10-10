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
			<ct:display model="local_life_module" btn="add_btn">
			<ht:menu-btn text="添加模块" url="/locallife/module/add" type="add"/>
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
			<table>
				<tr>
					<td>模块名称：</td>
					<td><input type="text" name="title" value="${param.title}" class="txt"/></td>
					<td><ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<br />
		<h3>模块列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="70">模块编号</th>
						<th nowrap="nowrap" width="60">模块编码</th>
						<th nowrap="nowrap" width="100">模块名称</th>
						<th nowrap="nowrap" width="50">模板</th>
						<th nowrap="nowrap" width="50">区域</th>
						<th nowrap="nowrap" width="50">创建人</th>
						<th nowrap="nowrap" width="80">创建时间</th>
						<th nowrap="nowrap" width="50">修改人</th>
						<th nowrap="nowrap" width="80">修改时间</th>
						<th nowrap="nowrap" width="60">操作</th>
					</tr>
					<c:forEach items="${pageData.data}" var="info">
						<tr>
							<td width="70" nowrap="nowrap" class="ellipsis">${info.id}</td>
							<td width="60" nowrap="nowrap" class="ellipsis">${info.code}</td>
							<td nowrap="nowrap" width="100" class="ellipsis"><a href="dataList?moduleId=${info.id}">${info.title}</a></td>
							<td nowrap="nowrap" width="50" class="ellipsis">${info.tempName}</td>
							<td nowrap="nowrap" width="50" class="ellipsis">${info.regionName}</td>
							<td nowrap="nowrap" width="50" class="ellipsis">${info.createUserName }</td>
							<td nowrap="nowrap" width="80"><ct:time source="${info.createTime}" /></td>
							<td nowrap="nowrap" width="50" class="ellipsis">${info.updateUserName }</td>
							<td nowrap="nowrap" width="50"><ct:time source="${info.updateTime}" /></td>
							<td width="60" nowrap="nowrap"><ct:display
									model="local_life_module" btn="mod_btn">
									<a href="edit?id=${info.id}">修改</a>
								</ct:display> &nbsp;&nbsp; <ct:display model="local_life_module" btn="del_btn">
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