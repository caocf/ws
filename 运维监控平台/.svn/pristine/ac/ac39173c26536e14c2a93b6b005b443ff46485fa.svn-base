<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cplatform.dbhelp.page.ListPage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="彩票统计" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
/**
 * 获取当月
 */
function getSingle() {
	var frm = document.getElementById("queryForm");
	frm.action = "${ctx}/businessOverview/caipiao/getSingle";
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
				<div class="control-group group-search" style="margin-left: 0px;">
					<label class="control-label" for="queryStartTime">
						查询时间：
					</label>
					<div class="controls controls-row">
						<select id="year" name="year">
							<!--                                 <option value="">请选择年份</option> -->
							<c:forEach var="year" begin="${beginYear}" end="${endYear}"
								step="1">
								<option value="${year}"
									<c:if test="${year == selectedYear}">selected</c:if>>
									${year}
								</option>
							</c:forEach>
						</select>
						<span class="form-middle-word">年</span>
						<select id="month" name="month">
							<!--                                 <option value="">请选择月份</option> -->
							<c:forEach var="month" begin="1" end="12" step="1">
								<option
									value='<c:choose><c:when test="${month < 10}">0${month}</c:when><c:otherwise>${month}</c:otherwise></c:choose>'
									<c:if test="${month == selectedMonth}">selected</c:if>>
									${month}
								</option>
							</c:forEach>
						</select>
						<span class="form-middle-word">月</span>

						<c:if test="${queryType == 'single'}">
							<button class="btn btn-primary" onclick="getSingle();"><i class="icon16 i-search"></i> 筛选</button>
							<span>当前显示：${selectedYear } 年 ${selectedMonth } 月</span>
						</c:if>
						<c:if test="${queryType != 'single'}">
							<button class="btn" onclick="getSingle();"><i class="icon16 i-search"></i> 筛选</button>
						</c:if>
					</div>
				</div>
			</div>
			<div>
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th rowspan=2>日期</th>
					<th colspan=2>用户项</th>
					<th colspan=4>交易项</th>
					<th colspan=4>支付方式</th>
					<th colspan=3>彩票(交易额)</th>
				</tr>
				<tr>
					<th>登陆用户</th>
					<th>交易用户</th>
					<th>交易额</th>
					<th>总订单</th>
					<th>成交订单</th>
					<th>未成交订单</th>
					<th>商城币</th>
					<th>积分</th>
					<th>现金</th>
					<th>话费	</th>
					<th>Android</th>
					<th>IOS</th>
					<th>专版</th>
				</tr>
                <c:forEach var="item" varStatus="status" begin="1" end="${days}">
                <c:set var="dt" value="${selectedYear}${ct:strAppend(selectedMonth,2,'0')}${ct:strAppend(status.index,2,'0')}"></c:set>
                
				<tr>
					<td>${selectedMonth}.${status.index}</td>
					<td>${dataReport[dt]['LOGIN_USER_COUNT']}</td>
                    <td>${dataReport[dt]['PAY_USER_COUNT']}</td>    
                    <td>${dataReport[dt]['TOTAL_ORDER_AMOUNT']}</td>                
                    <td>${dataReport[dt]['TOTAL_ORDER_COUNT']}</td>
					<td>${dataReport[dt]['SUCESS_ORDER_COUNT']}</td>
					<td>${dataReport[dt]['FAIL_ORDER_COUNT']}</td>
					<td>${dataReport[dt]['COIN']}</td>
					<td>${dataReport[dt]['JIFEN']}</td>
					<td>${dataReport[dt]['CASH']}</td>
					<td>${dataReport[dt]['FEE']}</td>
					<td>${dataReport[dt]['ANDROID_AMOUNT']}</td>
					<td>${dataReport[dt]['IOS_AMOUNT']}</td>
					<td>${dataReport[dt]['MO_AMOUNT']}</td>
				</tr>
				</c:forEach>
				<tr>
					<td>${selectedMonth}月合计</td>
					<td>${dataReportTotal['LOGIN_USER_COUNT']}</td>
					<td>${dataReportTotal['PAY_USER_COUNT']}</td>    
					<td>${dataReportTotal['TOTAL_ORDER_AMOUNT']}</td>                
					<td>${dataReportTotal['TOTAL_ORDER_COUNT']}</td>
					<td>${dataReportTotal['SUCESS_ORDER_COUNT']}</td>
					<td>${dataReportTotal['FAIL_ORDER_COUNT']}</td>
					<td>${dataReportTotal['COIN']}</td>
					<td>${dataReportTotal['JIFEN']}</td>
					<td>${dataReportTotal['CASH']}</td>
					<td>${dataReportTotal['FEE']}</td>
					<td>${dataReportTotal['ANDROID_AMOUNT']}</td>
					<td>${dataReportTotal['IOS_AMOUNT']}</td>
					<td>${dataReportTotal['MO_AMOUNT']}</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</form>

<%@ include file="../../includes/b.jsp"%>