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
	<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
			<ct:display model="lottery_active_list" btn="add_btn">
				<ht:menu-btn text="添加活动" url="/lottery/active/add" type="add" />
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="list" method="get">
			<table>
				<tr>
					<td width="100">活动名称：</td>
					<td width="150"><input type="text" name="name"
						value="${param.name}" class="txt" style="width: 150px" /></td>
					<td width="100">活动编号：</td>
					<td width="300"><input id="id" type="text" name="id" title="活动编号"
						value="${param.id}" class="txt validate-number" style="width: 100px" />
					<td width="80px">&nbsp;</td>
				</tr>
				<tr>
					<td>活动状态：</td>
					<td><select name="status">
							<option value="">--请选择--</option>
							<c:forEach items="${statusMap }" var="item">
								<option value="${item.key }"
									<c:if test="${param.status == item.key }">selected="selected"</c:if>>${item.value}</option>
							</c:forEach>
					</select></td>
				<td width="70">活动时间：</td>
                <td >
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly
             onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2050-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                   	至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}"  class="txt Wdate"
                           readOnly 
             onfocus="WdatePicker({vel:'stopTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2050-10-01'})" />
                    <input type="hidden" name="stopTime" id="stopTime" value="${param.stopTime}" />
                </td>


					<td colspan="1">&nbsp; <ct:btn type="search" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<br />
		<h3>商户列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">


				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="100">活动编号</th>
						<th nowrap="nowrap" width="200">活动名称</th>
						<th nowrap="nowrap" width="300">有效时间</th>
						<th nowrap="nowrap" width="150">抽奖模板类型</th>
						<th nowrap="nowrap" width="100">活动状态</th>
						<th nowrap="nowrap" width="100">可中奖次数</th>
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap">${item.id}</td>
							<td nowrap="nowrap" class="ellipsis">${item.name }</td>
							<td><ct:time source="${item.startTime}" tfmt="yyyy-MM-dd HH:mm:ss"/>~~<ct:time source="${item.stopTime}" tfmt="yyyy-MM-dd HH:mm:ss"/></td>
							<td nowrap="nowrap">${item.typeName}</td>
							<td nowrap="nowrap">${item.statusName }</td>
							<td nowrap="nowrap">
								<c:if test="${item.hitLimit <0}">无限制</c:if>
								<c:if test="${item.hitLimit >0}">${item.hitLimit }</c:if>
							</td>
							<td nowrap="nowrap">
								<ct:display model="lottery_active_list" btn="view_btn">
								<a href="view?id=${item.id}">查看</a>
								</ct:display>
								<ct:display model="lottery_active_list" btn="mod_btn">
								<a href="edit?id=${item.id}">修改</a>
								</ct:display>
								<ct:display model="lottery_active_list" btn="del_btn">
								<a style="cursor: pointer;" onclick="deleteItem('delete?id=${item.id}');">删除</a>
								</ct:display>
								<ct:display model="lottery_active_list" btn="audit_btn">
								<a href="auditPage?id=${item.id}">审核</a>
								</ct:display>
							</td>
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