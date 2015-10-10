<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">
$().ready(function() {	
	$(".view").click(function() {
			var id = $(this).attr("id");
			showDialog("商品内容详细信息", "${ctx}/item/item/view?id="+id,function(doc){
			},{"Width":768,"Height":600,"ShowButtonRow" : true});			
	});
});
//关联协议
function chackSettlesStatus(el){
	if(el.attr("status")!='3'){
		simpleAlert("审核通过，才能关联协议！");
		return false;
	}
	return true;
}
//上架
function chackGroundingStatus(){
	if($("input[name='itemId']:checked").length == 0 ){
		 alert("请选择需要上架的商品");
		 return false;
	 }
	 var str=""; 
	 $("input[name='itemId']:checked").each(function(){  
	 	str+=$(this).val()+",";  
	 })
	 dealInfo("确认上架","goodsOnLine/online?itemId="+str);
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

//删除
function checkDel(el){
	if($("input[name='itemId']:checked").length == 0 ){
		 alert("请选择需要删除的商品");
		 return false;
	 }
	 var str=""; 
	 $("input[name='itemId']:checked").each(function(){  
	 	str+=$(this).val()+",";  
	 })
	 dealInfo("确认删除","delete?itemId="+str);
}

//同步
function syncGy(el){
	if($("input[name='itemId']:checked").length == 0 ){
		 alert("请选择需要同步的商品");
		 return false;
	 }
	 var str=""; 
	 $("input[name='itemId']:checked").each(function(){  
	 	str+=$(this).val()+",";  
	 })
	 dealInfo("确认同步","${ctx}/item/item/getSettles/batchSyncGy?itemId="+str);
}
</script>
</head>
<body>
	<br />
	<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
			<ct:display model="meal_list" btn="add_btn">
				<ht:menu-btn text="添加套餐商品" url="/item/meal/mealAdd" />
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
			<table>
				<tr>
				
					<td>套餐名称：</td>
					<td><input type="text" name="name" value="${param.name}"
						class="txt" style="width:120px" /></td>

					<td width="70">创建时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.inputStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'queryStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="queryStartTime" id="startTime"
						value="${param.startTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime}" class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'queryEndTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
						<input type="hidden" name="queryEndTime" id="endTime"
						value="${param.endTime}" />
					</td>
				</tr>
				<tr>
				<td width="60">套餐编号：</td>
             	<td width="120"><input type="text" name="id" title="套餐编号" value="${param.id}" class="txt validate-number" style="width:100px"/></td>
					<td>状态：</td>
					<td width="200"><select name="status">
							<option value="">--请选择--</option>
							<c:forEach items="${statusMap}" var="item" varStatus="index">
								<option value="${item.key }"
									<c:if test="${item.key == param.status}">selected="selected"</c:if>>${item.value
									}</option>
							</c:forEach>
					</select>
					</td>
					<td>上下架状态：</td>
						<td width="300"><select name="isValid">
							<option value="">--请选择--</option>
							<c:forEach items="${isValidMap}" var="item" varStatus="index">
								<option value="${item.key }"
									<c:if test="${item.key == param.isValid}">selected="selected"</c:if>>${item.value
									}</option>
							</c:forEach>
					</select>
					</td>
					<td><ct:btn type="search" />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div class="container">
		<br />
		<h3>套餐商品发布列表</h3>

		<div class="mainbox">

			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					 <colgroup>
			        	<col width="100"></col>
			        	<col width=""></col>
			        	<col width=""></col>
			        	<col width="100"></col>
			        	<col width="100"></col>
			        	<col width="100"></col>
			        	<col width="100"></col>
			        	<col width=""></col>
        			</colgroup>
					 <tr>
			        	<td colspan="9" align="left">
			        	<!--  
			        	<ht:table-action-btn text="关联协议" url="/item/item/getSettles" open="true" onAction="chackSettlesStatus"/>
			        	-->
			        	<ct:display model="meal_list" btn="up_line_btn">
			        		<ht:table-action-btn text="上架" url="/item/meal/goodsOnLine/online" onAction="chackGroundingStatus"/>
			        	</ct:display>
			        	<ct:display model="meal_list" btn="down_line_btn">
			        		<ht:table-action-btn text="下架" url="/item/meal/goodsOnLine/offline"  onAction="chackUndercarriageStatus"/>
			        	</ct:display>
			        	<ct:display model="meal_list" btn="del_btn">
			        		<ht:table-action-btn text="删除" url="/item/meal/delete"  onAction="checkDel"/>
			        	</ct:display>
			        	</td>
        			</tr>
					<tr>
						<th nowrap="nowrap" width="100"><input type="checkbox"  class="checkall" />套餐编号</th>
						<th nowrap="nowrap" width="">商户名称</th>
						<th nowrap="nowrap" width="">套餐名称</th>
						<th nowrap="nowrap" width="100">开始时间</th>
						<th nowrap="nowrap" width="100">结束时间</th>
						<th nowrap="nowrap" width="100">结算状态</th>
						<th nowrap="nowrap" width="100">状态</th>
						<th nowrap="nowrap" >上架状态</th>
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap" ><input type="checkbox"  name="itemId" value="${item.id }" />${item.id}</td>
							<td nowrap="nowrap" class="ellipsis">${item.storeName}</td>
							<td nowrap="nowrap" class="ellipsis"><a href="mealView/${item.id}">${item.name}</a></td>
							<td nowrap="nowrap" ><ct:time source="${item.saleStartTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd" /></td>
							<td nowrap="nowrap" ><ct:time source="${item.saleStopTime}"  sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd"/></td>
							<td nowrap="nowrap" >${item.syncGyFlagName}</td>
							<td nowrap="nowrap" >${item.statusName}</td>
							<td nowrap="nowrap">
							<c:if test="${item.isValid == null || item.isValid == 0 }" var="flg">下架</c:if>
							<c:if test="${!flg}">上架</c:if>
							</td>
							<td nowrap="nowrap">
								<c:if test="${item.status!=-1}">	
									<ct:display model="meal_list" btn="mod_btn">
										<a href="mealEdit?id=${item.id}">修改</a>&nbsp; &nbsp;
									</ct:display>
							   		<c:if test="${item.status==0}">
										<ct:display model="meal_list" btn="send_btn">
											<a href="#" onclick="dealInfo('确认送审？','sendToAudit?id=${item.id}');">送审</a> &nbsp; &nbsp;
			                       		 </ct:display>
		                       		</c:if> 
		                       	</c:if>
		                       		 
		                       	<%--<c:if test="${item.status==3 }">
									<ct:display model="meal_list" btn="settle_btn">
										<a href="${ctx }/item/item/getSettles?itemId=${item.id}">关联协议</a>&nbsp; &nbsp;
									</ct:display>
								</c:if>
								--%><!--  
								<ct:display model="meal_list" btn="del_btn">
									<a href="#" onclick="deleteItem('delete?id=${item.id}');">删除</a>&nbsp; &nbsp;
								</ct:display>
							
								<ct:display model="meal_list" btn="up_line_btn">
									<c:if test="${item.status==3 && item.isValid == 0 }">
										<a href="#" onclick="dealInfo('确认上架？','goodsOnLine/online/${item.id}');">上架</a>
									</c:if>
								</ct:display> 
								
								
								<ct:display model="meal_list" btn="down_line_btn">
									<c:if test="${item.status==3 && item.isValid == 1 }">
										<a href="#"
											onclick="dealInfo('确认下架？','goodsOnLine/offline/${item.id}');">下架</a>
									</c:if>
								</ct:display> -->
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