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
						<th>
							全网
						</th>
						<th>
							数值
						</th>
						<th>
							备注：运营统计总表中，包括mo生活网站中所有的数据，以及线下数据
						</th>
					</tr>
					<tr>
						<td>
							累计注册用户数
						</td>
						<td colspan="2">
							${dataLJZCYH}
						</td>
					</tr>
					<tr>
						<td>
							当月活跃用户
						</td>
						<td colspan="2">
							${dataDYHYYH}
						</td>
					</tr>
					<tr>
						<td>
							当月累计交易用户数
						</td>
						<td colspan="2">
							${dataDYLJJYYH}
						</td>
					</tr>
					<tr>
						<td>
							当前会员数
						</td>
						<td colspan="2">
							${dataDQHYS}
						</td>
					</tr>
					<tr>
						<td>
							上架商品数
						</td>
						<td colspan="2">
							${dataSJSPS}
						</td>
					</tr>
					<tr>
						<td>
							当月现金支付占比
						</td>
						<td colspan="2">
							3.50%
						</td>
					</tr>
				</table>
				<br />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<td rowspan=3>日期/项目</td>
                    <td colspan=21>统计项</td>
                    <td colspan=3>分析项</td>
                </tr>
                <tr><td colspan=5>交易额</td>
                    <td rowspan=2>商城币增加额</td>
                    <td colspan=5>订单数</td>
                    <td rowspan=2>UV</td>
                    <td rowspan=2>PV</td>
                    <td colspan=2>会员</td>
                    <td rowspan=2>新增注册用户数</td>
                    <td colspan=5>支付方式</td>
                    <td rowspan=2>现金占比</td>
                    <td rowspan=2>订单均价</td>
                    <td rowspan=2>订单转化率（不含短信购，线下）</td>
                </tr>
                <tr>
                    <td>交易总额</td>
                    <td>网站（包含WAP端）</td>
                    <td>客户端</td>
                    <td>短信购</td>
                    <td>线下消费平台</td>
                    <td>总订单数</td>
                    <td>网站订单数</td>
                    <td>客户端订单数</td>
                    <td>短信购订单数</td>
                    <td>线下订单数</td>
                    <td>新增会员数</td>
                    <td>退订会员数</td>
                    <td>商城币</td>
                    <td>现金支付金额</td>
                    <td>积分M值支付金额</td>
                    <td>话费支付金额</td>
                    <td>其它</td>
                </tr>
                <c:forEach var="item" varStatus="status" begin="1" end="${days}">
                    <c:set var="dt" value="${selectedYear}${ct:strAppend(selectedMonth,2,'0')}${ct:strAppend(status.index,2,'0')}"></c:set>
                <tr>
                    <td>${selectedMonth}.${status.index}</td>
                    <td>${dataJYZE[dt]}</td>
                    <td>${dataWZBHWAP[dt]}</td>
                    <td>${dataKHD[dt]}</td>
                    <td>${dataDXG[dt]}</td>
                    <td>${dataXXXFPT[dt]}</td>
                    <td>1809230</td>
                    <td>${dataZDD[dt]}</td>
                    <td>${dataWZDD[dt]}</td>
                    <td>${dataKHDDD[dt]}</td>
                    <td>${dataDXGDD[dt]}</td>
                    <td>${dataXXDD[dt]}</td>
                    <td>1</td>
                    <td>2</td>
                    <td>${dataXZHY[dt]}</td>
                    <td>${dataTDHY[dt]}</td>
                    <td>${dataXZZCHY[dt]}</td>
                    <td>${dataSCBZFJE[dt]}</td>
                    <td>${dataXJZFJE[dt]}</td>
                    <td>${dataJFZFJE[dt]}</td>
                    <td>${dataHFZFJE[dt]}</td>
                    <td>76950.0</td>
                    <td>1.31%</td>
                    <td>89</td>
                    <td>37.23%</td>
                </tr>
                </c:forEach>
                <tr>
                    <td>总计</td>
                    <td>${dataJYZE['SUM']}</td>
                    <td>${dataWZBHWAP['SUM']}</td>
                    <td>${dataKHD['SUM']}</td>
                    <td>${dataDXG['SUM']}</td>
                    <td>${dataXXXFPT['SUM']}</td>
                    <td>7121676</td>
                    <td>${dataZDD['SUM']}</td>
                    <td>${dataWZDD['SUM']}</td>
                    <td>${dataKHDDD['SUM']}</td>
                    <td>${dataDXGDD['SUM']}</td>
                    <td>${dataXXDD['SUM']}</td>
                    <td>1</td>
                    <td>2</td>
                    <td>${dataXZHY['SUM']}</td>
                    <td>${dataTDHY['SUM']}</td>
                    <td>${dataXZZCHY['SUM']}</td>
                    <td>${dataSCBZFJE['SUM']}</td>
                    <td>${dataXJZFJE['SUM']}</td>
                    <td>${dataJFZFJE['SUM']}</td>
                    <td>${dataHFZFJE['SUM']}</td>
                    <td>1116908.41</td>
                    <td>3.50%</td>
                    <td>107</td>
                    <td>41.50%</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</form>

<%@ include file="../../includes/b.jsp"%>