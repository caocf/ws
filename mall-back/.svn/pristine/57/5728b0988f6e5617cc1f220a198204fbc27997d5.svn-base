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
			<ct:display model="template_list" btn="add_btn">
			<ht:menu-btn text="添加模版" url="/websys/template/add" />
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
			<table>
				<tr>
					<td>模版名称：</td>
					<td><input type="text" name="tName" value="${param.tName}" class="txt" style="width:206px"/>
					</td>
					<td colspan="2">&nbsp; <ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<br />
		<h3>模版列表21</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap">模版编号</th>
						<th nowrap="nowrap">模板名称</th>
						<th nowrap="nowrap">生成方式</th>
						<th nowrap="nowrap">数据查询路径</th>
						<th nowrap="nowrap">模板上传保存全路径</th>
						<th nowrap="nowrap">输出文件编码类型</th>
						<th nowrap="nowrap">操作</th>
					</tr>
					<c:forEach items="${pageData.data}" var="snow">
						<tr>
							<td width="50" nowrap="nowrap">${snow.id}</td>
							<td nowrap="nowrap" width="100">${snow.tName}</td>
							<td nowrap="nowrap" width="100">${snow.type eq 1 ? '模版接口':'页面访问'}</td>
							<td nowrap="nowrap" width="100">${snow.dataUrl }</td>
							<td nowrap="nowrap" width="100">${snow.filePath}</td>
							<td nowrap="nowrap" width="100">${snow.outputCharTset}</td>
							<td width="100" nowrap="nowrap"><ct:display
									model="template_list" btn="mod_btn">
									<a href="edit?id=${snow.id}">修改</a>
								</ct:display> &nbsp;&nbsp; <ct:display model="template_list" btn="del_btn">
									<a href="#" onclick="deleteItem('del?id=${snow.id}');">删除</a>
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