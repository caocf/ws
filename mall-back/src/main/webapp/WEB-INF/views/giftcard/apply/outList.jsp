<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">
//上架
function chackGroundingStatus(){
	if($("input[name='serialNo']:checked").length == 0 ){
		 alert("请选择需要出库的礼品卡");
		 return false;
	 }
	 var str=""; 
	 $("input[name='serialNo']:checked").each(function(){  
	 	str+=$(this).val()+",";  
	 })
	 dealInfo("确认出库","./cardStockIn?serialNo="+str);
}
//下架
function chackUndercarriageStatus(el){
	if($("input[name='itemId']:checked").length == 0 ){
		 alert("请选择需要下架的商品");
		 return false;
	 }
	 var str=""; 
	 $("input[name='itemId']:checked").each(function(){  
	 	str+=$(this).val()+",";  
	 })
	 dealInfo("确认下架","goodsOnLine/offline?itemId="+str);
}
function rd(){
	window.location.href="./list";
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
	             <td><input type="text" name="serialNo" value="${param.serialNo}" class="txt" style="width:120px"/>
	             <input type="hidden" name="id" value="${applyId}" class="txt" style="width:120px"/></td>
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
		<h3>出库列表</h3>

		<div class="mainbox">

			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80">编号</th>
						<th nowrap="nowrap" width="80">卡序列号</th>
						<th nowrap="nowrap" width="80">卡批次号</th>
						<th nowrap="nowrap" width="80">出入库状态</th>
						<th nowrap="nowrap" width="80">操作时间</th>
						<th nowrap="nowrap" width="80">操作人</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap" >${item.id}</td>
							<td nowrap="nowrap" >${item.serialNo}</td>
							<td nowrap="nowrap" >${item.batchNo}</td>
							<td nowrap="nowrap" >
							<c:if test="${item.storageStatus == 0}">出库</c:if>
							<c:if test="${item.storageStatus == 1}">入库</c:if>
							</td>
							<td nowrap="nowrap" class="ellipsis"><ct:time source="${item.operatedTime}" /></td>
							<td nowrap="nowrap" class="ellipsis">${item.username}</td>
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
		 <div class="buttons">
			<input type="button" class="common_btn" onclick="rd();" value="返回"/>
		</div>
	</div>
</body>
</html>