<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cplatform.dbhelp.page.ListPage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="客户端交易信息日表" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
/**
 * 获取当月
 */
function getSingle() {
	var frm = document.getElementById("queryForm");
	frm.action = "${ctx}/kpireport/khdTrade/getSingle";
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
								<option value="${area.areaCode}" <c:if test="${area.areaCode ==  selectedAreacode}">selected="selected"</c:if>>${area.areaName}</option>
							</c:forEach>
						</select>
					</div>
					<c:set var="dateType" value="${condition.dateType == 0}"></c:set>
					<label class="control-label">选择时间：</label>
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
					<th>登陆用户</th>
					<th>新增激活用户</th>
					<th>交易用户</th>
					<th>累计激活用户数</th>
					<th>交易额</th>
					<th>总订单数</th>
					<th>成交订单</th>
					<th>未成交订单</th>
					<th>支付率</th>
					<th>转化率</th>
					<th>代金券交易额</th>
					<th>团购交易额</th>
					<th>实物交易额</th>
					<th>电影票交易额</th>
					<th>汽车票交易额</th>
					<th>彩票(高阳)交易额</th>
					<th>现金支付额</th>
					<th>商城币支付额</th>
					<th>话费支付额</th>
					<th>积分支付额</th>
				</tr>
				
				  <c:forEach var="item" varStatus="status" begin="1" end="${days}">
                <c:set var="dt" value="${selectedYear}${ct:strAppend(selectedMonth,2,'0')}${ct:strAppend(status.index,2,'0')}"></c:set>
                
				<tr>
					<td>${selectedMonth}.${status.index}</td>
					<td>${dataReport[dt]['LOG_USER_CNT']}</td>
					<td>${dataReport[dt]['FST_LOG_USER_CNT']}</td>
					<td>${dataReport[dt]['TRNSCT_USER_CNT']}</td>
					<td>${dataReport[dt]['TOT_USER_CNT']}</td>
					<td>${dataReport[dt]['TRNSCT_AMT']}</td>
					<td>${dataReport[dt]['TOT_ORDER_CNT']}</td>
					<td>${dataReport[dt]['SUCCESS_ORDER_CNT']}</td>
					<td>${dataReport[dt]['UNSUCCESS_ORDER_CNT']}</td>
					<td>${dataReport[dt]['ORDER_SUCCS_PECT']}</td>
					<td>${dataReport[dt]['TRNSCT_USER_PECT']}</td>
					<td>${dataReport[dt]['VOUCHER_TRNSCT_AMT']}</td>
					<td>${dataReport[dt]['GROUP_TRNSCT_AMT']}</td>
					<td>${dataReport[dt]['MATRLGOOD_TRNSCT_AMT']}</td>
					<td>${dataReport[dt]['FLM_TKT_AMT']}</td>
					<td>${dataReport[dt]['BUS_TKT_AMT']}</td>
					<td>${dataReport[dt]['LOTT_TKT_AMT']}</td>
					<td>${dataReport[dt]['CASH_AMT']}</td>
					<td>${dataReport[dt]['COIN_AMT']}</td>
					<td>${dataReport[dt]['BALANCE_AMT']}</td>
					<td>${dataReport[dt]['SCORE_AMT']}</td>
				</c:forEach>
				<tr>
					<td>${selectedMonth}月合计</td>
					<td>${dataReportTotal['LOG_USER_CNT']}</td>
					<td>${dataReportTotal['FST_LOG_USER_CNT']}</td>
					<td>${dataReportTotal['TRNSCT_USER_CNT']}</td>
					<td>${dataReportTotal['TOT_USER_CNT']}</td>
					<td>${dataReportTotal['TRNSCT_AMT']}</td>
					<td>${dataReportTotal['TOT_ORDER_CNT']}</td>
					<td>${dataReportTotal['SUCCESS_ORDER_CNT']}</td>
					<td>${dataReportTotal['UNSUCCESS_ORDER_CNT']}</td>
					<td>${dataReportTotal['ORDER_SUCCS_PECT']}</td>
					<td>${dataReportTotal['TRNSCT_USER_PECT']}</td>
					<td>${dataReportTotal['VOUCHER_TRNSCT_AMT']}</td>
					<td>${dataReportTotal['GROUP_TRNSCT_AMT']}</td>
					<td>${dataReportTotal['MATRLGOOD_TRNSCT_AMT']}</td>
					<td>${dataReportTotal['FLM_TKT_AMT']}</td>
					<td>${dataReportTotal['BUS_TKT_AMT']}</td>
					<td>${dataReportTotal['LOTT_TKT_AMT']}</td>
					<td>${dataReportTotal['CASH_AMT']}</td>
					<td>${dataReportTotal['COIN_AMT']}</td>
					<td>${dataReportTotal['BALANCE_AMT']}</td>
					<td>${dataReportTotal['SCORE_AMT']}</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</form>

<%@ include file="../../includes/b.jsp"%>