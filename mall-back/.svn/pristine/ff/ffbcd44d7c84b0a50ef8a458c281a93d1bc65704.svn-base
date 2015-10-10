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
			showDialog("渠道商审核", "../../auditPage?id="+id+"&auditStep=${auditStep}&status=${status+1}",function(doc){
				commonSubmit(doc.getElementById('fm'));
				//doc.getElementById('fm').submit();
				//doc.submited=true;
				simpleAlert('操作成功',function() {
					window.location.reload();
				});
			},{"Width":600,"Height":270,"ShowMessageRow":false});
			

		});

	});
	
	
  function isDigit(value) 
  { 
  var patrn=/^[0-9]{0,20}$/; 
    if (!patrn.exec(value)){ 
        alert("请输入正确的编号");
    return false}{ 
  return true 
  }
} 


$(function (){$("#queryForm").submit(function(){
    		var isPass = true;
    		isPass = isDigit($("#ordernumber").val());
    		return isPass;
    	})});
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
		<form name="queryForm" id="queryForm" action="" method="get">
			<table>
				<tr>
					<td width="100">商户名称：</td>
					<td><input type="text" name="name" value="${param.name}"
						class="txt" style="width:150px" />
					</td>
					<td width="100">商户编号：</td>
					<td><input id="ordernumber" type="text" name="id" value="${param.id}"	class="txt" style="width:150px" /></td>
				</tr>
			
				<tr>
					<td >创建时间：</td>
					<td  />
					<input type="text" id="inputStartTime" name="inputStartTime"
						value="${param.inputStartTime}" class="txt Wdate" readOnly
						onfocus="WdatePicker({vel:'beginTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
					<input type="hidden" name="beginTime" id="beginTime"
						value="${param.beginTime}" /> 至
					<input type="text" id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime}"  class="txt Wdate"
						readOnly
						onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
					<input type="hidden" name="endTime" id="endTime"
						value="${param.endTime}" />
					</td>
					<td colspan="2">&nbsp; <ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<br />
		<h3>非结算商户列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">


				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="60">商户编号</th>
						<th nowrap="nowrap" width="300">商户名称</th>
		                <th nowrap="nowrap"  width="70">区域</th>
						<th nowrap="nowrap"  width="70">商户状态</th>
						<th nowrap="nowrap"  width="150">创建时间</th>
		                <th nowrap="nowrap">操作</th>
					</tr>
					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap">${item.id}</td>
							<td nowrap="nowrap" class="ellipsis"> <a href="noneStoreView/${item.id}">${item.name }</a></td>
							<td nowrap="nowrap">${item.areaName}</td>
							<td nowrap="nowrap">${item.statusName }</td>
							<td nowrap="nowrap">
							<ct:time source="${item.createTime}" />
							</td>
							<td width="100" nowrap="nowrap">
								<c:if test="${item.status==status}">
									<ct:display model="non_store_audit" btn="audit_btn">
									  <a href="noneAuditPage?id=${item.id}&auditStep=${auditStep}&status=${status+1}">审核</a>&nbsp;&nbsp;
									</ct:display>
								</c:if>
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