<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cplatform.dbhelp.page.ListPage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="客户端激活用户日表" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
/**
 * 获取当月
 */
function getSingle() {
	var frm = document.getElementById("queryForm");
	frm.action = "${ctx}/kpireport/khdActivate/getSingle";
	frm.submit();
}
</script>
<style type="text/css">
.table td{text-align:right;}
</style>
<form class="form-horizontal" action="?" name="queryForm" id="queryForm"
	method="post">
	<div class="row-fluid">
		<div class="span12">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<form id="queryForm" name="queryForm" class="form-horizontal" method="post">
				<div class="control-group group-search" style="margin-left: 0px;">
					<label class="control-label">选择地区：</label>
					<div class="controls controls-row">
						<select id="area_code" name="area_code" style="float: left;">
							<option value="">全部</option>
							<c:forEach var="area" items="${areaList}">
								<option value="${area.areaCode}" <c:if test="${area.areaCode == selectedAreacode}">selected="selected"</c:if>>${area.areaName}</option>
							</c:forEach>
						</select>
					</div>
					<label class="control-label">选择时间：</label>
					<div class="controls controls-row">
						<select id="year" name="year">
							<c:forEach var="year" begin="${beginYear}" end="${endYear}" step="1">
								<option value="${year}" <c:if test="${year == selectedYear}">selected="selected"</c:if>>${year}</option>
							</c:forEach>
						</select> <span class="form-middle-word">年</span> <select id="month" name="month">
							<c:forEach var="month" begin="${1}" end="${12}" step="1">
								<option value="${month}" <c:if test="${month == selectedMonth}">selected="selected"</c:if>>
									${month}
								</option>
							</c:forEach>
						</select> <span class="form-middle-word">月</span>
						
						<c:if test="${queryType == 'single'}">
							<button class="btn btn-primary" onclick="getSingle();"><i class="icon16 i-search"></i> 筛选</button>
							<span>当前显示：${selectedYear } 年 ${selectedMonth } 月</span>
						</c:if>
					</div>
				</div>
				
			</form>
			</div>
			<div>
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th rowspan=2>日期</th>
				</tr>
				<tr>				
					<th>激活用户数</th>
				</tr>
				
				  <c:forEach var="item" varStatus="status" begin="1" end="${days}">
                <c:set var="dt" value="${selectedYear}${ct:strAppend(selectedMonth,2,'0')}${ct:strAppend(status.index,2,'0')}"></c:set>
                
				<tr>
					<td>${selectedMonth}.${status.index}</td>
					<td>${dataReport[dt]['USER_CNT']}</td>
				</c:forEach>
				<tr>
					<td>${selectedMonth}月合计</td>
					<td>${dataReportTotal['USER_CNT']}</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</form>

<%@ include file="../../includes/b.jsp"%>