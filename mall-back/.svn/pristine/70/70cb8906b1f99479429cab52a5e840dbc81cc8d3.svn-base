<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">
//出库
function chackGroundingStatus(){
	if($("input[name='serialNo']:checked").length == 0 ){
		 alert("请选择需要出库的礼品卡");
		 return false;
	 }
	 var str=""; 
	 $("input[name='serialNo']:checked").each(function(){  
	 	str+=$(this).val()+",";  
	 })
	 dealInfo("确认出库","./cardStockOut?serialNo="+str);
}
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
				<td>卡序列号：</td>
	             <td><input type="text" name="serialNo" value="${param.serialNo}" class="txt" style="width:120px"/></td>
	             <td>卡批次号：</td>
	             <td><input type="text" name="batchNo" value="${param.batchNo}" class="txt validate-number" style="width:120px"/></td>
	                <td>
                    <ct:btn type="search" />
                </td>
				</tr>
			</table>
		</form>
	</div>
	<div id="abc">
	</div>

	<div class="container">
		<br />
		<h3>礼品卡列表</h3>

		<div class="mainbox">

			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					 <tr>
			        	<td colspan="4" align="left">
			        	<ct:display model="stock_model" btn="st_o_btn">
			        		<ht:table-action-btn text="出库" url="" onAction="chackGroundingStatus"/>
			        	</ct:display>
			        	</td>
        			</tr>
					<tr>
						<th nowrap="nowrap" width="80"><input type="checkbox"  class="checkall" />卡序列号</th>
						<th nowrap="nowrap" width="80">卡批次号</th>
						<th nowrap="nowrap" width="80">出入库状态</th>
						<th nowrap="nowrap" width="80">领卡时间</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap" ><input type="checkbox"  name="serialNo" value="${item.serialNo }" />${item.serialNo}</td>
							<td nowrap="nowrap" class="ellipsis">${item.batchNo}</td>
							<td nowrap="nowrap" class="ellipsis">
							<c:if test="${item.storageStatus == 0 }">未入库</c:if>
							<c:if test="${item.storageStatus == 1 }">入库</c:if>
							<c:if test="${item.storageStatus == 2 }">出库</c:if>
							</td>
							<td nowrap="nowrap" class="ellipsis"><ct:time source="${item.receiveTime}" /></td>
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