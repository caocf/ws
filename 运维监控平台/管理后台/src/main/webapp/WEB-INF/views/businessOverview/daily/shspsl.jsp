<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="日报-商户商品数量" />
<%@ include file="../../includes/t.jsp"%>
<%@ include file="common.jsp"%>

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
					<label class="control-label">选择时间：</label>
					<div class="controls controls-row">
						<input type="radio" name="dateType" value="0" <c:if test="${condition.dateType=='0'}">checked="checked"</c:if> /> <span>按日：</span>
						<div id="from" class="input-append date" data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
							<input id="dateStr" name="dateStr" type="text" value="${condition.dateStr}"> <span class="add-on"> <i class="icon16 i-calendar-4"></i>
							</span>
						</div>
						<br /> <input type="radio" name="dateType" value="1" <c:if test="${condition.dateType=='1'}">checked="checked"</c:if> /> <span>按月：</span> <select id="yearStr" name="yearStr">
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
						</select> <span class="form-middle-word">月</span> <br /> <input type="radio" name="dateType" value="2" <c:if test="${condition.dateType=='2'}">checked="checked"</c:if> /> <span>按周：</span> <select id="weekStr" name="weekStr" style="width: 150px;">
							<c:forEach var="week" items="${condition.weekList}">
								<option value="${week}" <c:if test="${week == condition.weekStr}">selected="selected"</c:if>>${week}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group group-search">
					<label class="control-label"></label>
					<div class="controls controls-row">
						<button name="btnQuery" class="btn btn-primary" type="button" value="shspsl">
							<i class="icon16 i-search"></i> 查询
						</button>
					</div>
				</div>
			</form>
		</div>
		<div>
			<c:if test="${empty datas}">
				<div class="alert alert-info" style="margin-top: 20px;">
					<strong> <i class="icon24 i-info"></i> ${condition.startDate}至${condition.endDate}无相关数据！
					</strong>
				</div>
			</c:if>
			<c:if test="${not empty datas}">
				<div style="text-align: left;">当前显示为：${condition.startDate}至${condition.endDate}数据</div>
				<div style="text-align: right; margin: 5px 0px">
					<button name="btnExcel" class="btn" value="shspsl">导出到EXCEL文件</button>
				</div>
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<c:forEach var="data" items="${datas}" varStatus="status">
							<c:if test="${status.index == 0}">
								<tr>
									<c:forEach var="item" items="${data}" varStatus="itemStatus">
										<c:choose>
											<c:when test="${itemStatus.index == 0}">
												<th></th>
											</c:when>
											<c:otherwise>
												<th>${item}</th>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</thead>
					<tbody>
						<c:forEach var="data" items="${datas}" varStatus="status">
							<c:if test="${status.index != 0}">
								<tr>
									<c:forEach var="item" items="${data}" varStatus="itemStatus">
										<c:choose>
											<c:when test="${itemStatus.index == 0}">
												<td class="tdTitle" title="${hints[status.index]}">${item}</td>
											</c:when>
											<c:otherwise>
												<td>${item}</td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>

<%@ include file="../../includes/b.jsp"%>