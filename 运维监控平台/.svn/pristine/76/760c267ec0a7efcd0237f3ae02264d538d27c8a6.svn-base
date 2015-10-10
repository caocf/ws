<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="用户概览统计" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
	$(function() {
		var chartsZcyh = $.parseJSON('${chartsZcyh}');//注册用户图表json
		var chartsDzyh = $.parseJSON('${chartsDzyh}');//定制用户图表json

		//默认渲染第一个图表
		renderCharts(1, chartsZcyh);

		//查询按钮
		$('button[name=btnQuery]').click(function() {
			var frm = document.getElementById("queryForm");
			frm.action = "${ctx}/user/statistics";
			frm.submit();
		});

		//注册用户按钮
		$('button[name=btnZcyh]').click(function() {
			$(this).siblings().removeClass('btn-primary');
			$(this).addClass('btn-primary');
			renderCharts(1, chartsZcyh);
			$('div[name=zcyh]').show();
			$('div[name=dzyh]').hide();
		});

		//定制用户按钮
		$('button[name=btnDzyh]').click(function() {
			$(this).siblings().removeClass('btn-primary');
			$(this).addClass('btn-primary');
			renderCharts(1, chartsDzyh);
			$('div[name=dzyh]').show();
			$('div[name=zcyh]').hide();
		});

		//导出按钮（注册用户）
		$('button[name=btnExcelZcyh]').click(function() {
			var frm = document.getElementById("queryForm");
			frm.action = "${ctx}/user/statistics/toExcel/zcyh";
			frm.submit();
		});

		//图表按钮（注册用户）
		$('a[name=btnChartZcyh]').click(function() {
			var type = $(this).attr('type');
			$(this).siblings().removeClass('btn-primary');
			$(this).addClass('btn-primary');
			renderCharts(type, chartsZcyh);
		});

		//导出按钮（定制用户）
		$('button[name=btnExcelDzyh]').click(function() {
			var frm = document.getElementById("queryForm");
			frm.action = "${ctx}/user/statistics/toExcel/dzyh";
			frm.submit();
		});

		//图表按钮（定制用户）
		$('a[name=btnChartDzyh]').click(function() {
			var type = $(this).attr('type');
			$(this).siblings().removeClass('btn-primary');
			$(this).addClass('btn-primary');
			renderCharts(type, chartsDzyh);
		});
	});
</script>

<div class="row-fluid">
	<div class="span12">
		<div class="well" style="padding: 0px !important; padding-top: 19px !important;">
			<form id="queryForm" name="queryForm" class="form-horizontal" method="post">
				<div class="control-group group-search" style="margin-left: 0px;">
					<label class="control-label">选择地区：</label>
					<div class="controls controls-row">
						<select id="areaStr" name="areaStr" style="float: left;">
							<option value="">全部</option>
							<c:forEach var="area" items="${condition.areaList}">
								<option value="${area.areaCode}" <c:if test="${area.areaCode == condition.areaStr}">selected="selected"</c:if>>${area.areaName}</option>
							</c:forEach>
						</select>
					</div>
					<c:set var="dateType" value="${condition.dateType == 0}"></c:set>
					<label class="control-label">选择时间：</label>
					<div class="controls controls-row">
						<input type="radio" name="dateType" value="0" <c:if test="${dateType}">checked="checked"</c:if> /> <span>按日：</span>
						<div id="from" class="input-append date" data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
							<input id="dateStr" name="dateStr" type="text" value="${condition.dateStr}"> <span class="add-on"> <i class="icon16 i-calendar-4"></i>
							</span>
						</div>
						<br /> <input type="radio" name="dateType" value="1" <c:if test="${!dateType}">checked="checked"</c:if> /> <span>按月：</span> <select id="yearStr" name="yearStr">
							<c:forEach var="year" begin="${condition.startYear}" end="${condition.endYear}" step="1">
								<option value="${year}" <c:if test="${year == condition.yearStr}">selected="selected"</c:if>>${year}</option>
							</c:forEach>
						</select> <span class="form-middle-word">年</span> <select id="monthStr" name="monthStr">
							<c:forEach var="month" begin="${1}" end="${12}" step="1">
								<option value="${month}" <c:if test="${month == condition.monthStr}">selected="selected"</c:if>>
									<c:if test="${month<10}">0${month}</c:if>
									<c:if test="${month>=10}">${month}</c:if>
								</option>
							</c:forEach>
						</select> <span class="form-middle-word">月</span>
					</div>
				</div>
				<div class="control-group group-search">
					<label class="control-label"></label>
					<div class="controls controls-row">
						<button name="btnQuery" class="btn btn-primary" type="button">
							<i class="icon16 i-search"></i> 查询
						</button>
					</div>
				</div>
			</form>
		</div>
		<div style="float: left; width: 200px;">
			<div class="control-group group-search">
				<button name="btnZcyh" class="btn btn-primary">注册用户</button>
				<button name="btnDzyh" class="btn">定制用户</button>
			</div>
		</div>
		<div name="zcyh">
			<div style="text-align: right; margin: 5px 0px">
				<button name="btnExcelZcyh" class="btn">导出到EXCEL文件</button>
			</div>
			<table class="table table-bordered table-hover table-striped">
				<c:forEach var="data" items="${datasZcyh}" varStatus="status">
					<c:if test="${status.index == 0}">
						<thead>
							<tr>
								<c:forEach var="item" items="${data}">
									<th>${item}</th>
								</c:forEach>
							</tr>
						</thead>
					</c:if>
					<c:if test="${status.index != 0}">
						<tbody>
							<tr>
								<c:forEach var="item" items="${data}">
									<td>${item}</td>
								</c:forEach>
							</tr>
						</tbody>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<div name="dzyh" style="display: none;">
			<div style="text-align: right; margin: 5px 0px">
				<button name="btnExcelDzyh" class="btn">导出到EXCEL文件</button>
			</div>
			<table class="table table-bordered table-hover table-striped">
				<c:forEach var="data" items="${datasDzyh}" varStatus="status">
					<c:if test="${status.index == 0}">
						<thead>
							<tr>
								<c:forEach var="item" items="${data}">
									<th>${item}</th>
								</c:forEach>
							</tr>
						</thead>
					</c:if>
					<c:if test="${status.index != 0}">
						<tbody>
							<tr>
								<c:forEach var="item" items="${data}">
									<td>${item}</td>
								</c:forEach>
							</tr>
						</tbody>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<div name="zcyh" style="float: left; width: 200px;">
	<c:forEach var="data" items="${datasZcyh}" varStatus="statusI">
		<c:if test="${statusI.index == 0}">
			<c:forEach var="item" items="${data}" varStatus="statusJ">
				<c:if test="${!statusJ.first}">
					<c:if test="${statusJ.index == 1}">
						<a name="btnChartZcyh" type="${statusJ.index}" class="btn btn-primary" style="width: 150px; margin-top: 10px; font-weight: bold">${item}</a>
					</c:if>
					<c:if test="${statusJ.index != 1}">
						<a name="btnChartZcyh" type="${statusJ.index}" class="btn" style="width: 150px; margin-top: 10px; font-weight: bold">${item}</a>
					</c:if>
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
</div>

<div name="dzyh" style="float: left; width: 200px; display: none;">
	<c:forEach var="data" items="${datasDzyh}" varStatus="statusI">
		<c:if test="${statusI.index == 0}">
			<c:forEach var="item" items="${data}" varStatus="statusJ">
				<c:if test="${!statusJ.first}">
					<c:if test="${statusJ.index == 1}">
						<a name="btnChartDzyh" type="${statusJ.index}" class="btn btn-primary" style="width: 150px; margin-top: 10px; font-weight: bold">${item}</a>
					</c:if>
					<c:if test="${statusJ.index != 1}">
						<a name="btnChartDzyh" type="${statusJ.index}" class="btn" style="width: 150px; margin-top: 10px; font-weight: bold">${item}</a>
					</c:if>
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
</div>

<div style="float: left;">
	<div id="divCharts"></div>
</div>

<%@ include file="../../includes/b.jsp"%>