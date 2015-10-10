<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
    </head>
<body>
<br/>
<div class="container">
    <br/>
    	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
			<table>
				<tr>
					<td>门店名称：</td>
					<td><input type="text" name="name" value="${param.name}" class="txt" style="width:80px" />
					</td>
					<td>门店编号：</td>
					<td><input id="ordernumber" type="text" name="id" value="${param.id}" title="商户编号" class="txt validate-number" style="width:50px" />
					</td>
					<td>区域：</td>
					<td><input type="text" name="regionName" value="${param.regionName}" class="txt" style="width:50px" />
					</td>
				
					<td >&nbsp; <ct:btn type="search" /></td>
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
						<th nowrap="nowrap" width="30">选择</th>
						<th nowrap="nowrap" width="70">门店ID</th>
		            	<th nowrap="nowrap" width="180">所属商户</th>
		                <th nowrap="nowrap" width="180">名称</th>
		                <th nowrap="nowrap" width="60">区域</th>
		                <th nowrap="nowrap" width="60">签约状态</th>
		                <th nowrap="nowrap" width="50">连锁</th>
		                <th nowrap="nowrap" width="50">总店</th>
					</tr>

					  <c:forEach items="${pageData.data}" var="item" varStatus="index">
            			<tr>
             				<td nowrap="nowrap"><input type="radio"  id="radio-${index.count}" name="shopIdSelector" storeName="${item.storeName}" storeId="${item.acShopId}" shopClass="${item.shopClass }"  shopName="${item.name}"  value="${item.id}"  class="validate-one-required"/></td>
							<td nowrap="nowrap">${item.id}</td>
			                <td nowrap="nowrap">${item.acShopId}</td>
			                <td nowrap="nowrap">${item.name}</td>
			                <td nowrap="nowrap">${item.regionName}</td>
			                <td nowrap="nowrap">${item.sortName}</td>
			                <td nowrap="nowrap">${item.isChainName}</td>
			                <td nowrap="nowrap">${item.isBaseShopName}</td>
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
</div>
</body>
</html>