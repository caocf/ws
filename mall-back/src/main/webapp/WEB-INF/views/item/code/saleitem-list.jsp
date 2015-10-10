<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />

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
		<form name="queryForm" id="queryForm" action="../codelist/${codetype }" method="get">
			<table>
				<tr>
					<td>商户名称：</td>
					<td><input type="text" name="storeName" value="${param.storeName}"
						class="txt" style="width:120px" />
						
							<td>商户编号：</td>
					<td><input type="text" name="storeId" value="${param.storeId}"
						class="txt validate-number" style="width:120px" />
					</td>
					
					
					<td width="70">销售时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.inputStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'saleStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="saleStartTime" id="saleStartTime"
						value="${param.saleStartTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime}" class="txt Wdate"
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
								<c:if test="${param.verifyCodeType == 1}">selected="selected"</c:if>>一维码</option>
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
						<th nowrap="nowrap" width="100">商品编号</th>
						<th nowrap="nowrap" width="250">商户名称</th>
						<th nowrap="nowrap" width="200">商品名称</th>
						<th nowrap="nowrap" width="60">码类型</th>
						<th nowrap="nowrap" width="50">码数量</th>
						<th nowrap="nowrap" width="100">发码方式</th>
						<th nowrap="nowrap" width="120">销售截止时间</th>
									
						<th nowrap="nowrap"  width="100">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item" varStatus="i">
						<tr>
							<td nowrap="nowrap" >${item.id}</td>
							<td nowrap="nowrap" class="ellipsis">${item.storeName}</td>
							<td nowrap="nowrap" class="ellipsis"><a href="../../item/view/${item.id} ">${item.name}</a></td>
							<td nowrap="nowrap" >${item.verifyCodeTypeName}</td>
							<td nowrap="nowrap" >${countList[i.index]}</td>
							<td nowrap="nowrap"  >${item.sendCodeModeName}</td>
							<td nowrap="nowrap" >
							<ct:time source="${item.saleStopTime}"   />
							</td>
							<td nowrap="nowrap">
							<c:if test="${countList[i.index] > 0 }">
							    
							    <c:if test="${item.virtualType==1 }">
							      <ct:display	model="storecode" btn="view_btn">
							    	<a href="../cardCodeList?itemId=${item.id}&storeId=${item.storeId}">查看码</a>&nbsp;&nbsp;
							      </ct:display> 
							    </c:if>
							     <c:if test="${item.virtualType!=1}">
							      <ct:display	model="storecode" btn="view_btn">
							     	<a href="../storeCodeList?itemId=${item.id}&storeId=${item.storeId}">查看码</a>&nbsp;&nbsp;
							      </ct:display> 
							     </c:if>
									
								
							</c:if>
								 
								 <c:if test="${item.virtualType==1 }">
								 	<ct:display	model="storecode" btn="imp_btn">
							    		<a href="../importCode/cardcode/?itemId=${item.id}&storeId=${item.storeId}">导入码</a>
							    	</ct:display> 
							    </c:if>
							     <c:if test="${item.virtualType!=1}">
							     	<ct:display	model="storecode" btn="imp_btn">
							     		<a href="../importCode/storecode/?itemId=${item.id}&storeId=${item.storeId}">导入码</a>
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