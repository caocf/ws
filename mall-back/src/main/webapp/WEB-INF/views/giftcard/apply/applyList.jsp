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
			<ct:display model="gift_apply_out" btn="addLine_btn">
			<ht:menu-btn text="线上申请出库" url="/giftcard/apply/preAddOnLine" type="add"/>
			</ct:display>
			<ct:display model="gift_apply_out" btn="addLine_btn">
			<ht:menu-btn text="线下申请出库" url="/giftcard/apply/preAddLine" type="add"/>
			</ct:display>
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
					<td >状态：</td>
                	<td>
	                    <select name="status">
	                        <option value="">--请选择--</option>
	                    	<c:forEach items="${statusMap}" var="item">
	                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value }</option>
	                        </c:forEach>
	                    </select>
               	   	</td>
					<td >支付状态：</td>
                	<td>
	                    <select name="payStatus">
	                        <option value="">--请选择--</option>
	                    	<c:forEach items="${payStatusMap}" var="item">
	                        	    <option value="${item.key }" <c:if test="${param.payStatus == item.key}">selected="selected"</c:if>>${item.value }</option>
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
						<th nowrap="nowrap" width="100" >编号</th>
						<th nowrap="nowrap" width="100">申请类型</th>   
						<th nowrap="nowrap" width="100">订单编号</th>   
						<th nowrap="nowrap" width="100">申请人 </th>
						<th nowrap="nowrap" width="150">申请时间 </th>
						<th nowrap="nowrap" width="100">状态</th>
						<th nowrap="nowrap" width="100">支付状态</th>
						<th nowrap="nowrap" width="100">入账金额</th>
						<th nowrap="nowrap" width="200">操作</th>
					</tr>
					<c:forEach items="${pageData.data}" var="info">
						<tr>
							<td width="50" nowrap="nowrap">${info.id}</td>
							<td nowrap="nowrap" width="100">${info.typeName}</td>
							<td nowrap="nowrap" width="100">${info.orderId}</td>
							<td nowrap="nowrap" width="100">${info.applyUserName}</td>
							<td nowrap="nowrap" width="150"><ct:time source="${info.applyTime}" /></td>
							<td nowrap="nowrap" width="100">${info.statusName}</td>
							<td nowrap="nowrap" width="100">${info.payStatusName }</td>
							<td nowrap="nowrap" width="200"><fmt:formatNumber value="${info.payment/100}" pattern="0.00"/></td>
							<td width="100" nowrap="nowrap">
								<ct:display model="gift_apply_out" btn="view_btn">
										&nbsp;&nbsp;<a href="view?id=${info.id}">查看</a>
								</ct:display> 
								
								<c:if test="${ info.status eq 0 || info.status eq 1 || info.status eq 2}">
								<ct:display model="gift_apply_out" btn="mod_btn">
										&nbsp;&nbsp;<a href="preEditor?id=${info.id}">修改</a>
								</ct:display>
								
								<ct:display model="gift_apply_out" btn="del_btn">
										&nbsp;&nbsp;<a href="#" onclick="deleteItem('del?id=${info.id}');">删除</a>
								</ct:display>
								</c:if>
								
								<ct:display model="gift_apply_out" btn="giftOut_btn">
								<c:if test="${ info.status eq 1}">
										&nbsp;&nbsp;<a href="#" onclick="dealInfo('确定出库？','stockOut?id=${info.id}');">出库</a>
								</c:if>
								</ct:display>
								
								<ct:display model="gift_apply_out" btn="giftActive_btn">
								<c:if test="${ info.status eq 4}">
									&nbsp;&nbsp;<a href="#" onclick="dealInfo('确定激活？','activeCard?id=${info.id}');">激活</a>
								</c:if>
								</ct:display>
								<ct:display model="gift_apply_out" btn="view_btn">
								<c:if test="${ info.status eq 4}">
									&nbsp;&nbsp;<a href="viewOut?id=${info.id}">查看出库</a>
								</c:if>
								<c:if test="${ info.status eq 5}">
									&nbsp;&nbsp;<a href="viewOut?id=${info.id}">查看出库</a>
									&nbsp;&nbsp;<a href="viewActive?id=${info.id}">查看激活</a>
								</c:if>
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