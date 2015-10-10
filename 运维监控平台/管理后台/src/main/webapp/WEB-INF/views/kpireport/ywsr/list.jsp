<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cplatform.dbhelp.page.ListPage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="业务收入" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
/**
 * 获取当月
 */
function getSingle() {
	var frm = document.getElementById("queryForm");
	frm.action = "${ctx}/kpireport/ywsr/getSingle";
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
					<th rowspan=2>地区</th>
					<th rowspan=2>业务总收入</th>
					<th colspan=4>前向收入</th>
					<th colspan=16>后向收入</th>
				</tr>
				<tr>				
					<th>商盟会员收入</th>
					<th>彩票俱乐部会员收入</th>
					<th>文影俱乐部会员收入</th>
					<th>其他</th>
					<th>交易佣金</th>
					<th>交易佣金(现金)</th>
					<th>交易佣金(话费)</th>
					<th>交易佣金(商城币)</th>
					<th>交易佣金(积分)</th>
					<th>交易佣金(营销二维码)	</th>
					<th>交易佣金(其他)</th>
					<th>广告收入</th>
					<th>广告收入(网站广告)</th>
					<th>广告收入(互动屏广告)</th>
					<th>广告收入(其他)</th>
					<th>后向服务</th>
					<th>后向服务(平台服务)</th>
					<th>后向服务(缤纷商汇/商务秘书)</th>
					<th>后向服务(票务代理)</th>
					<th>后向服务(其他)</th>
					
				</tr>
				
				<c:forEach items="${dataReport}" var="data" varStatus="status">
				<tr>
					<td>${data['CITY_NAME']}</td>
					<td>${data['TOT_INCOM']}</td>
					<td>${data['FW_SM_MEM_INCOM']}</td>
					<td>${data['FW_CP_MEM_INCOM']}</td>
					<td>${data['FW_WY_MEM_INCOM']}</td>
					<td>${data['FW_OTHER']}</td>
					<td>${data['BW_COMMS_INCOM']}</td>
					<td>${data['BW_COMMS_CASH']}</td>
					<td>${data['BW_COMMS_BALANCE']}</td>
					<td>${data['BW_COMMS_COIN']}</td>
					<td>${data['BW_COMMS_SCORE']}</td>
					<td>${data['BW_COMMS_2DCODE']}</td>
					<td>${data['BW_COMMS_OTHER']}</td>
					<td>${data['BW_AD_INCOM']}</td>
					<td>${data['BW_AD_WEB']}</td>
					<td>${data['BW_AD_INTC_SCRN']}</td>
					<td>${data['BW_AD_OTHER']}</td>
					<td>${data['BW_SERV_INCOM']}</td>
					<td>${data['BW_SERV_PLATF']}</td>
					<td>${data['BW_SERV_SECRT']}</td>
					<td>${data['BW_SERV_TCKT']}</td>
					<td>${data['BW_SERV_OTHER']}</td>
				</c:forEach>
				<tr>
					<td>${selectedMonth}月合计</td>
					<td>${dataReportTotal['TOT_INCOM']}</td>
					<td>${dataReportTotal['FW_SM_MEM_INCOM']}</td>
					<td>${dataReportTotal['FW_CP_MEM_INCOM']}</td>
					<td>${dataReportTotal['FW_WY_MEM_INCOM']}</td>
					<td>${dataReportTotal['FW_OTHER']}</td>
					<td>${dataReportTotal['BW_COMMS_INCOM']}</td>
					<td>${dataReportTotal['BW_COMMS_CASH']}</td>
					<td>${dataReportTotal['BW_COMMS_BALANCE']}</td>
					<td>${dataReportTotal['BW_COMMS_COIN']}</td>
					<td>${dataReportTotal['BW_COMMS_SCORE']}</td>
					<td>${dataReportTotal['BW_COMMS_2DCODE']}</td>
					<td>${dataReportTotal['BW_COMMS_OTHER']}</td>
					<td>${dataReportTotal['BW_AD_INCOM']}</td>
					<td>${dataReportTotal['BW_AD_WEB']}</td>
					<td>${dataReportTotal['BW_AD_INTC_SCRN']}</td>
					<td>${dataReportTotal['BW_AD_OTHER']}</td>
					<td>${dataReportTotal['BW_SERV_INCOM']}</td>
					<td>${dataReportTotal['BW_SERV_PLATF']}</td>
					<td>${dataReportTotal['BW_SERV_SECRT']}</td>
					<td>${dataReportTotal['BW_SERV_TCKT']}</td>
					<td>${dataReportTotal['BW_SERV_OTHER']}</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</form>

<%@ include file="../../includes/b.jsp"%>