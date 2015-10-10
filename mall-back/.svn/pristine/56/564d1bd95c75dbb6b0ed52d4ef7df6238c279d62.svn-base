<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">

function configOpenAppItem(obj){ 
	var optype;
	if(obj.checked==true) {
		optype = "1";
	}else {
		optype = "0";
	}
	var url = G_CTX_ROOT + '/sys/open/config-openapp-item/${appId}/'+$(obj).val()+"/"+optype+"?t="+Math.random();

    jQuery.ajax({
    	type : "get",
    	url : url,
    	dataType:  'json',
    	async:false,
    	success : function(resp){	
			if(resp.success==false) {
				alert(resp.errorMessage);
			}		
    	}
    });
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
		<input type="hidden" name="iseckillFlag" value="${iseckillFlag}" />
		<input type="hidden" name="appId" value="${appId}" />
			<table>
				<tr>
					<td>商品名称：</td>
					<td><input type="text" name="name" value="${param.name}"
						class="txt" style="width:120px" /></td>					
						<td>商品分类：</td>
					<td><input type="text" name="typeName" value="${param.typeName}"
						class="txt" style="width:120px" /></td>
				</tr>
				<tr>							
						<td>商户名称：</td>
					<td><input type="text" name="storeName" value="${param.storeName}"
						class="txt" style="width:120px" /></td>

				
				
						<td>商户编号：</td>
					<td><input type="text" name="storeId" title="商户编号" value="${param.storeId}"
						class="txt validate-number" style="width:120px" /></td>
				</tr>
				<tr>
					<td>商品编号：</td>
					<td width="200"><input id="ordernumber" title="商品编号" type="text" name="id" value="${param.id}"	class="txt validate-number" style="width:120px" />
					</td>
					<td width="70">创建时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.inputStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'saleStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="saleStartTime" id="saleStartTime"
						value="${param.startTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime}" class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'saleStopTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
						<input type="hidden" name="saleStopTime" id="saleStopTime"
						value="${param.endTime}" />
					</td>					
					</tr>
					<tr>
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
				</tr>	
				<tr>	
					<td>配置状态</td>
					<td>
						<input type='radio' name='configStatus' value='0' <c:if test="${configStatus==0 }">checked</c:if> onclick="javascript:$('#queryForm').submit();" />全部
						<input type='radio' name='configStatus' value='1' <c:if test="${configStatus==1 }">checked</c:if> onclick="javascript:$('#queryForm').submit();" />已选
						<input type='radio' name='configStatus' value='2' <c:if test="${configStatus==2 }">checked</c:if> onclick="javascript:$('#queryForm').submit();" />未选
					</td>
					<td></td>
					<td><ct:btn type="search" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="abc">
	</div>

	<div class="container">
		<br />
		<h3>商品发布列表</h3>

		<div class="mainbox">

			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					<colgroup>
			        	<col width="80"></col>
			        	<col width=""></col>
			        	<col width=""></col>
			        	<col width="60"></col>
			        	<col width="60"></col>
			        	<col width="60"></col>
			        	<col width="60"></col>
        			</colgroup>
					<tr>
						<th nowrap="nowrap" width="80">编号</th>
						<th nowrap="nowrap" width="80">商户</th>
						<th nowrap="nowrap" width="80">商品名称</th>
						<th nowrap="nowrap" width="60">商品类型</th>
						<th nowrap="nowrap" width="60">商品分类</th>
						<th nowrap="nowrap" width="60">状态</th>
						<th nowrap="nowrap" >上架状态</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap" ><input type="checkbox"  name="itemId" value="${item.id }"  onclick="configOpenAppItem(this)" <c:if test='${not empty appItemMap[item.id] }'>checked</c:if> />${item.id}</td>
							<td nowrap="nowrap" class="ellipsis">${item.storeName}</td>
							<td nowrap="nowrap" class="ellipsis">${item.name}</td>
							<td nowrap="nowrap" class="ellipsis">${item.itemModeName}</td>
							<td nowrap="nowrap" class="ellipsis">${item.typeName}</td>
							<td nowrap="nowrap" class="ellipsis">${item.statusName}</td>
							<td nowrap="nowrap">
							<c:if test="${item.isValid == null || item.isValid == 0 }" var="flg">下架</c:if>
							<c:if test="${!flg}">上架</c:if>
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