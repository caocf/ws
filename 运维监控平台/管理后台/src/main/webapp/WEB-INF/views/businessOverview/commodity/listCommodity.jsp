<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商品列表" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
	$(function() {
		//筛选商品按钮
		$('button[name=btnQueryCommodity]').click(function() {
			var frm = document.getElementById("queryForm");
			frm.itemNameStr.value = encodeURI(frm.itemNameStr.value, "");
			frm.action = "${ctx}/businessOverview/commodity/listCommodity";
			frm.submit();
		});

		//返回按钮
		$('button[name=btnBack]').click(function() {
			var frm = document.getElementById("queryForm");
			frm.action = "${ctx}/businessOverview/commodity";
			frm.submit();
		});

		//查询商品明细
		$('a[name=queryDetail]').click(function() {
			var frm = document.getElementById("queryDatailForm");
			frm.itemIdStr.value = $(this).attr('itemId');
			frm.itemNameStr.value = $(this).attr('itemName');
			frm.action = "${ctx}/businessOverview/commodity/detail";
			frm.submit();
		});
	});
</script>

<div class="row-fluid">
	<div class="span12">
		<div class="well" style="padding: 0px !important; padding-top: 19px !important;">
			<form id="queryForm" name="queryForm" class="form-horizontal" method="get" action="?">
				<div class="control-group group-search" style="margin-left: 0px;">
					<label class="control-label" for="itemNameStr">查找商品：</label>
					<div class="controls controls-row">
						<input id="itemNameStr" name="itemNameStr" type="text" value="${itemNameStr}" placeholder="商品名称" title="商品名称" />
						<button name="btnQueryCommodity" class="btn btn-primary" type="button">
							<i class="icon16 i-search"></i> 筛选商品
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
		<c:if test="${empty listCommodity}">
			<div class="alert alert-info" style="margin-top: 20px;">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
				</strong>
			</div>
		</c:if>
		<c:if test="${not empty listCommodity}">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>商品编号</th>
						<th>商品名称</th>
						<th>所属商户</th>
						<th>所在城市</th>
						<th>所属分类</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listCommodity.data}" var="item">
						<tr>
							<td>${item.itemId}</td>
							<td><a href="javascript:;" name="queryDetail" itemId="${item.itemId}" itemName="${item.itemName}">${item.itemName}</a></td>
							<td>${item.shopName}</td>
							<td>${item.areaName}</td>
							<td>${item.categoryName}</td>
							<td><a href="javascript:;" name="queryDetail" itemId="${item.itemId}" itemName="${item.itemName}">查看</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ht:page pageData="${listCommodity}" />
		</c:if>
	</div>
</div>

<form id="queryDatailForm" name="queryDatailForm" class="form-horizontal" method="post">
	<input type="hidden" name="itemIdStr" value="" /> <input type="hidden" name="itemNameStr" value="" />
</form>

<!-- 导入头部 -->
<%@ include file="../../includes/b.jsp"%>
<!-- 导入头部结束 -->