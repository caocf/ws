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
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
			<table>
				<tr>
					<td>编号：</td>
					<td><input type="text" name="id" value="${param.id}" class="txt"/></td>
					<td >申请类型：</td>
                	<td>
	                    <select name="type">
	                        <option value="">--请选择--</option>
	                    	<c:forEach items="${typeMap}" var="item">
	                        	    <option value="${item.key }" <c:if test="${param.type == item.key}">selected="selected"</c:if>>${item.value }</option>
	                        </c:forEach>
	                    </select>
               	   	</td>
					<td><ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<br />
		<h3>出库申请列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap">编号</th>
						<th nowrap="nowrap">申请类型</th>   
						<th nowrap="nowrap">申请人 </th>
						<th nowrap="nowrap">申请时间 </th>
						<th nowrap="nowrap">状态</th>
						<th nowrap="nowrap">支付状态</th>
						<th nowrap="nowrap">支付金额</th>
						<th nowrap="nowrap">操作</th>
					</tr>
					<c:forEach items="${pageData.data}" var="info">
						<tr>
							<td width="50" nowrap="nowrap">${info.id}</td>
							<td nowrap="nowrap" width="100">${info.typeName}</td>
							<td nowrap="nowrap" width="100">${info.applyUserName}</td>
							<td nowrap="nowrap" width="100"><ct:time source="${info.applyTime}" /></td>
							<td nowrap="nowrap" width="100">${info.statusName}</td>
							<td nowrap="nowrap" width="100">${info.payStatusName }</td>
							<td nowrap="nowrap" width="100"><fmt:formatNumber value="${info.payment/100}" pattern="0.00"/></td>
							<td width="100" nowrap="nowrap">
								&nbsp;&nbsp;
								 <ct:display model="gift_audit2_out" btn="view_btn">
								 		<a href="../view?id=${info.id}">查看</a>
								 </ct:display>
								&nbsp;&nbsp;
								 <ct:display model="gift_audit2_out" btn="audit_btn">
										<a href="../preAudit?id=${info.id}">审核</a>
								 </ct:display>
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