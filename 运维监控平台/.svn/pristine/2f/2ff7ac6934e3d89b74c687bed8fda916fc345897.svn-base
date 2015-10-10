<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cplatform.dbhelp.page.ListPage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="领导日报" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
/**
 * 获取当月
 */
function getSingle() {
	var frm = document.getElementById("queryForm");
	frm.action = "${ctx}/businessOverview/leaderdaily/getSingle";
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
					<th colspan=2 width="50%">指标数据</th>
					<td colspan=2 width="50%"></td>
				</tr>
				<tr>
					<th width="25%">累计注册用户数</th>
					<td width="25%">${dataLJZCYH}</td>
					<th width="25%">当月现金支付占比</th>
					<td width="25%">${dataDYXJZB}</td>
				</tr>
				<tr>
					<th>累计会员数</th>
					<td>${dataDQHYS}</td>
					<th>当月累计商城币增加</th>
					<td>${dataSCBZJE['SUM']}</td>
				</tr>
				<tr>
					<th>当月活跃用户数</th>
					<td>${dataDYHYYH} </td>
					<th>日均PV/UV</th>
					<td></td>
				</tr>
				<tr>
					<th>当月客户端活跃数</th>
					<td> </td>
					<th>当月客户端激活数</th>
					<td> </td>
				</tr>
				<tr>
					<th>当月累计交易用户数</th>
					<td>${dataDYLJJYYH} </td>
					<th>上架商品数</th>
					<td>${dataSJSPS} </td>
				</tr>
			</table>
			<br />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th rowspan=2>日期</th>
					<th colspan=7>交易额</th>
					<th rowspan=2>订单总量</th>
					<th colspan=6>支付方式</th>
					<th rowspan=2>商城币增加额</th>
				</tr>
				<tr>
					<th>交易总额</th>
					<th>逛商城</th>
					<th>客户端</th>
					<th>线下消费</th>
					<th>彩票</th>
					<th>电影票</th>
					<th>团购</th>
					<th>代金券</th>
					<th>商城币</th>
					<th>现金支付金额</th>
					<th>积分M值支付金额</th>
					<th>话费支付金额</th>
					<th>其它（红包等）</th>
				</tr>
                <c:forEach var="item" varStatus="status" begin="1" end="${days}">
                <c:set var="dt" value="${selectedYear}${ct:strAppend(selectedMonth,2,'0')}${ct:strAppend(status.index,2,'0')}"></c:set>
                
				<tr>
					<td>${selectedMonth}.${status.index}</td>
					<td>${dataJYZE[dt]}</td>
                    <td>${dataWZBHWAP[dt]}</td>    
                    <td>${dataKHD[dt]}</td>                
                    <td>${dataXXXFPT[dt]}</td>
					<td>${dataCPJYE[dt]}</td>
					<td>${dataDYPJYE[dt]}</td>
					<td>${dataTGJYE[dt]}</td>
					<td>${dataO2OJYE[dt]}</td>
					<td>${dataZDD[dt]}</td>
					<td>${dataZFFS['coin'][dt]}</td>
					<td>${dataZFFS['cash'][dt]}</td>
					<td>${dataZFFS['score'][dt]}</td>
					<td>${dataZFFS['balance'][dt]}</td>
					<td>${dataQTZFJE[dt]}</td>
					<td>${dataSCBZJE[dt]}</td>
				</tr>
				</c:forEach>
				<tr>
					<td>${selectedMonth}月</td>
					<td>${dataJYZE['SUM']}</td>
                    <td>${dataWZBHWAP['SUM']}</td>
                    <td>${dataKHD['SUM']}</td>
                    <td>${dataXXXFPT['SUM']}</td>
					<td>${dataCPJYE['SUM']}</td>
					<td>${dataDYPJYE['SUM']}</td>
					<td>${dataTGJYE['SUM']}</td>
					<td>${dataO2OJYE['SUM']}</td>
					<td>${dataZDD['SUM']}</td>
					<td>${dataZFFS['SUM']['coin']}</td>
					<td>${dataZFFS['SUM']['cash']}</td>
					<td>${dataZFFS['SUM']['score']}</td>
					<td>${dataZFFS['SUM']['balance']}</td>
					<td>${dataQTZFJE['SUM']}</td>
					<td>${dataSCBZJE['SUM']}</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</form>

<%@ include file="../../includes/b.jsp"%>