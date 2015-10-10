<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">
	function rd(){
		window.location.href="./list";
	}
	$().ready(function() {
		selectRegion("#regionName","regionCode","regionName",${regionCode});
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
			<ht:menu-btn text="添加模块数据" url="/locallife/module/dataAdd?moduleId=${moduleId}" type="add"/>
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
			<table>
				<tr>
					<td>标题：</td>
            	    <td><input type="text" name="contentTitle" value="${param.contentTitle}" class="txt" style="width:120px"/></td>
					<td>区域：</td>
            	    <td>
            	    <input type="hidden" id ="regionCode" name ="regionCode" value="${param.regionCode}"/>
                    <input type="text" id ="regionName" name ="regionName"  value="${param.regionName}" readonly="true"/>
					</td>
					<td>状态：</td>
					<td>
							<select name="status">
			                        <option value="">--请选择--</option>
	                        	    <option value= "0" <c:if test="${param.status == 0 }">selected="selected"</c:if>>未审核</option>
	                        	    <option value= "1" <c:if test="${param.status == 1 }">selected="selected"</c:if>>审核通过</option>
	                        	    <option value= "2" <c:if test="${param.status == 2 }">selected="selected"</c:if>>审核驳回</option>
		                    </select>
		                    <input type="hidden" name="moduleId" value="${moduleId}"/>
					</td>
					<td><ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<br />
		<h3>模块数据列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="50">编号</th>
						<th nowrap="nowrap" width="50">模板名称</th>
						<th nowrap="nowrap" width="50">模块名称</th>
						<th nowrap="nowrap" width="50">状态</th>
						<th nowrap="nowrap" width="50">标题</th>
						<th nowrap="nowrap" width="50">区域</th>
						<th nowrap="nowrap" width="80">开始时间</th>
						<th nowrap="nowrap" width="80">结束时间</th>
						<th nowrap="nowrap" width="90">操作</th>
					</tr>
					<c:forEach items="${pageData.data}" var="info">
						<tr>
							<td width="50" nowrap="nowrap" class="ellipsis">${info.id}</td>
							<td width="50" nowrap="nowrap" class="ellipsis">${info.tempName}</td>
							<td nowrap="nowrap" width="50" class="ellipsis">${info.moduleName}</td>
							<td nowrap="nowrap" width="100" >
							<c:if test="${info.status eq 0}"> 未审核</c:if>
							<c:if test="${info.status eq 1}"> 审核通过</c:if>
							<c:if test="${info.status eq 2}"> 审核驳回</c:if>
							</td>
							<td nowrap="nowrap" width="50" class="ellipsis">${info.contentTitle}</td>
							<td nowrap="nowrap" width="50" class="ellipsis">${info.regionName}</td>
							<td nowrap="nowrap" width="80" class="ellipsis"><ct:time source="${info.startTime}" /></td>
							<td nowrap="nowrap" width="80" class="ellipsis"><ct:time source="${info.endTime}" /></td>
							<td width="90" nowrap="nowrap" >
								<c:if test="${info.status ne 1}">
								<ct:display model="local_life_module" btn="audit_btn">
									<a href="dataAudit?id=${info.id}">审核</a>
								</ct:display>
								</c:if>
								<ct:display model="local_life_module" btn="mod_btn">
									&nbsp;&nbsp;
									<a href="dataEdit?id=${info.id}">修改</a>
								</ct:display>
								 <ct:display model="local_life_module" btn="del_btn">
								 	&nbsp;&nbsp;
									<a href="#" onclick="deleteItem('dataDel?id=${info.id}');">删除</a>
								</ct:display>
								 <ct:display model="local_life_module" btn="view_btn">
								 	&nbsp;&nbsp;
								 	<a href="dataView?id=${info.id}">查看</a>
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
		<div class="buttons">
			<input type="button" class="common_btn" onclick="rd();" value="返回"/>
		</div>
	</div>

</body>
</html>