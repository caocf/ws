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
		<form name="queryForm" id="queryForm" action="${ctx }/store/store/auditList/${auditStep }" method="get">
			<table>
				<tr>
					<td width="100">商户名称：</td>
					<td><input type="text" name="name" value="${param.name}"
						class="txt" style="width:206px" />
					</td>
					<td width="100">商户编号：</td>
					<td><input id="ordernumber" type="text" name="id" value="${param.id}"	class="txt" style="width:100px" /></td>
				</tr>
				<tr>
					<td>商户类型：</td>
					<td>
					 <select name="shopClass">
                        <option value="">--请选择--</option>
                        <c:forEach items="${storeClassMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.shopClass == item.key }">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
					</td>

<!-- 					<td>结算同步：</td> -->
<!-- 					<td><select name="syncGyFlag"> -->
<!-- 							<option value="">--请选择--</option> -->
<!-- 							<option value="0" -->
<%-- 								<c:if test="${param.syncGyFlag == 0}">selected="selected"</c:if>>未成功同步</option> --%>
<!-- 							<option value="1" -->
<%-- 								<c:if test="${param.syncGyFlag == 1}">selected="selected"</c:if>>成功同步</option> --%>
<!-- 					</select></td> -->
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
		<h3>商户列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">


				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="60">商户编号</th>
						<th nowrap="nowrap" width="400">商户名称</th>
		                <th nowrap="nowrap"  width="70">商户类型</th>
		                <th nowrap="nowrap"  width="70">区域</th>
						<th nowrap="nowrap"  width="70">审核状态</th>
						<%--<th nowrap="nowrap"  width="70">同步结算</th>--%>
		                <th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap">${item.id}</td>
							<td nowrap="nowrap"  class="ellipsis">   <a href="../storeView/${item.id}?auditStep=${auditStep}">${item.name}</a></td>
							<td nowrap="nowrap">${item.shopClassName}</td>
							<td nowrap="nowrap">${item.areaName}</td>
							<td nowrap="nowrap">${item.statusName }</td>
							<%--<td nowrap="nowrap">${item.syncGyFlagName }</td>--%>
							<td width="100" nowrap="nowrap">
								<c:if test="${item.status==status}">
									<ct:display model="store_list" btn="audit_btn">
									  <a href="../auditPage?id=${item.id}&auditStep=${auditStep}&status=${status+1}">审核</a>&nbsp;&nbsp;
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