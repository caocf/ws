<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />

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
		<form name="queryForm" id="queryForm" action="cardCodeList" method="get">
		<input type="hidden" name="storeId" value="${storeId }">
		<input type="hidden" name="itemId" value="${itemId }">
			<table>
				<tr>
					<td width="70">创建日期：</td>
					<td width="120"><input type="text" id="validDate"
						name="createDate" value="${param.createDate}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'validDate',realDateFmt:'yyyy-MM-dd',maxDate:'#F{\'2050-01-01\'}'})" />
					</td>
				
				<td>码状态：</td>
					<td><select name="status">
							<option value="">--请选择--</option>
							 <c:forEach items="${statusMap}" var="item" varStatus="index">
                        		<option value="${item.key }" <c:if test="${item.key == param.status}">selected="selected"</c:if>>${item.value }</option>
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
		<h3>码列表</h3>

		<div class="mainbox">
			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80">ID</th>
						<th nowrap="nowrap" width="150">卡号</th>
						<th nowrap="nowrap" width="150">创建时间</th>
						<th nowrap="nowrap" width="150">订单号</th>
						<th nowrap="nowrap"  width="150">码状态</th>
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item" varStatus="i">
						<tr>
							<td nowrap="nowrap" >${item.id}</td>
							<td nowrap="nowrap" >${item.cardId}</td>
							<td nowrap="nowrap" >
							<ct:time source="${item.createDate}" />
							</td>
							<td nowrap="nowrap" >
							${item.orderId}
							</td>
							<td nowrap="nowrap">${item.statusName }</td>
							<td nowrap="nowrap">
							   &nbsp;
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