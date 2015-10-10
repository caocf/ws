<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户列表" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
	$(function() {
		//筛选商户按钮
		$('button[name=btnQueryMerchant]').click(function() {
			var frm = document.getElementById("queryForm");
			frm.shopNameStr.value = encodeURI(frm.shopNameStr.value, "");
			frm.action = "${ctx}/businessOverview/merchant/listMerchant";
			frm.submit();
		});

		//返回按钮
		$('button[name=btnBack]').click(function() {
			var frm = document.getElementById("queryForm");
			frm.action = "${ctx}/businessOverview/merchant";
			frm.submit();
		});

		//查询商户明细
		$('a[name=queryDetail]').click(function() {
			var frm = document.getElementById("queryDatailForm");
			frm.shopIdStr.value = $(this).attr('shopId');
			frm.shopNameStr.value = $(this).attr('shopName');
			frm.action = "${ctx}/businessOverview/merchant/detail";
			frm.submit();
		});
	});
</script>

<div class="row-fluid">
	<div class="span12">
		<div class="well" style="padding: 0px !important; padding-top: 19px !important;">
			<form id="queryForm" name="queryForm" class="form-horizontal" method="get" action="?">
				<div class="control-group group-search" style="margin-left: 0px;">
					<label class="control-label" for="shopNameStr">查找商户：</label>
					<div class="controls controls-row">
						<input id="shopNameStr" name="shopNameStr" type="text" value="${shopNameStr}" placeholder="商户名称" title="商户名称" />
						<button name="btnQueryMerchant" class="btn btn-primary" type="button">
							<i class="icon16 i-search"></i> 筛选商户
						</button>
						<button name="btnBack" class="btn" type="button">
							<i class="icon16"></i> 返回
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div>
		<c:if test="${empty listMerchant}">
			<div class="alert alert-info" style="margin-top: 20px;">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
				</strong>
			</div>
		</c:if>
		<c:if test="${not empty listMerchant}">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>商户编号</th>
						<th>商户名称</th>
						<th>所在城市</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listMerchant.data}" var="item">
						<tr>
							<td>${item.shopId}</td>
							<td><a href="javascript:;" name="queryDetail" shopId="${item.shopId}" shopName="${item.shopName}">${item.shopName}</a></td>
							<td>${item.areaName}</td>
							<td><a href="javascript:;" name="queryDetail" shopId="${item.shopId}" shopName="${item.shopName}">查看</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ht:page pageData="${listMerchant}" />
		</c:if>
	</div>
</div>

<form id="queryDatailForm" name="queryDatailForm" class="form-horizontal" method="post">
	<input type="hidden" name="shopIdStr" value="" /> <input type="hidden" name="shopNameStr" value="" />
</form>

<!-- 导入头部 -->
<%@ include file="../../includes/b.jsp"%>
<!-- 导入头部结束 -->