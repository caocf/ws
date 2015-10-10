<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">
	$().ready(function() {
		$(".item_audit").click(function() {
			var id = $(this).attr("id");
			showDialog("发布商品审核", "${ctx}/item/saleitem/auditing/"+id,null,{"Width":700,"Height":600,"ShowButtonRow" : false});
		});
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
		<form name="queryForm" id="queryForm" action="auditList" method="post">
			<table>
				<tr>
					<td>商户名称：</td>
					<td><input type="text" name="storeName" value="${param.storeName}"
						class="txt" style="width:120px" />
					</td>
					<td>商品名称：</td>
					<td><input type="text" name="itemName" value="${param.itemName}"
						class="txt" style="width:120px" />
					</td>
					
					<td width="70">销售时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.inputStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'saleStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="saleStartTime" id="saleStartTime"
						value="${param.saleStartTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime}" class="Wdate" class="txt Wdate"
						readOnly
						onclick="WdatePicker({vel:'queryEndTime',saleStopTime:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
						<input type="hidden" name="saleStopTime" id="saleStopTime"
						value="${param.saleStopTime}" /></td>
					</tr>
				<tr>
				<td>码类型：</td>
					<td><select name="verifyCodeType">
							<option value="">--请选择--</option>
							<option value="1"
								<c:if test="${param.isBsAccount == 1}">selected="selected"</c:if>>一维码</option>
							<option value="2"
								<c:if test="${param.verifyCodeType == 2}">selected="selected"</c:if>>二维码</option>
					</select></td>
					<td>发码方式：</td>
					<td><select name="sendCodeMode">
							<option value="">--请选择--</option>
							<option value="0"
								<c:if test="${param.sendCodeMode == 0}">selected="selected"</c:if>>不发码</option>
							<option value="1"
								<c:if test="${param.sendCodeMode == 1}">selected="selected"</c:if>>按照订单发码</option>
							<option value="2"
								<c:if test="${param.sendCodeMode == 2}">selected="selected"</c:if>>按照商品个数发码</option>	
					</select></td>
					
					
					
						
						
					<td><ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div class="container">
		<br />
		<h3>商品发布列表</h3>

		<div class="mainbox">
			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="150">商户名称</th>
						<th nowrap="nowrap"  width="250">商品名称</th>
						<th nowrap="nowrap" width="100">码类型</th>
						<th nowrap="nowrap" width="100">发码方式</th>
						<th nowrap="nowrap" width="120">销售有效时间</th>
						<th nowrap="nowrap" width="60">审核状态</th>
					
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap" >${item.storeName}</td>
							<td nowrap="nowrap" >${item.name}</td>
							<td nowrap="nowrap" >${item.verifyCodeTypeName}</td>
							<td nowrap="nowrap" >${item.sendCodeModeName}</td>
							<td nowrap="nowrap" >
							<ct:time source="${item.saleStartTime}" />--<ct:time source="${item.saleStopTime}" />
							</td>
							<td nowrap="nowrap" >${item.statusName}</td>
							<td  nowrap="nowrap">
							    <ct:display	model="audit_list" btn="view_btn">
									<a href="#" id="${item.id }" class="item_view">查看</a>
								</ct:display> 
								<c:if test="${item.status==6 }">
								<ct:display model="audit_list" btn="audit_btn">
									<a href="#" id="${item.id}" class="item_audit">审核</a>&nbsp;&nbsp;
								</ct:display>
								
								</c:if>
							</td>
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