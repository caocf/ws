<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cplatform.dbhelp.page.ListPage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="领导日报" />
<%@ include file="../../includes/t.jsp"%>

<form class="form-horizontal" action="?" name="queryForm" id="queryForm"
	method="post">
	<div class="row-fluid">
		<div class="span12">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="control-group group-search" style="margin-left: 0px;">
					<label class="control-label" for="queryStartTime">查询时间：</label>
					<div class="controls controls-row">
						<select id="year" name="year">
							<!--                                 <option value="">请选择年份</option> -->
							<c:forEach var="year" begin="${beginYear}" end="${endYear}"
								step="1">
								<option value="${year}"
									<c:if test="${year == selectedYear}">selected</c:if>>${year}</option>
							</c:forEach>
						</select> <span class="form-middle-word">年</span> <select id="month"
							name="month">
							<!--                                 <option value="">请选择月份</option> -->
							<c:forEach var="month" begin="1" end="12" step="1">
								<option
									value='<c:choose><c:when test="${month < 10}">0${month}</c:when><c:otherwise>${month}</c:otherwise></c:choose>'
									<c:if test="${month == selectedMonth}">selected</c:if>>${month}</option>
							</c:forEach>
						</select> <span class="form-middle-word">月</span>

						<c:if test="${queryType == 'single'}">
							<button class="btn btn-primary" onclick="getSingle();">
								<i class="icon16 i-search"></i> 筛选
							</button>
							<button class="btn" onclick="getTotal();">
								<i class="icon16 i-search"></i> 查询累计
							</button>
						<span>当前显示：${selectedYear } 年 ${selectedMonth } 月</span>
						</c:if>
						<c:if test="${queryType != 'single'}">
							<button class="btn" onclick="getSingle();">
								<i class="icon16 i-search"></i> 筛选
							</button>
							<button class="btn btn-primary" onclick="getTotal();">
								<i class="icon16 i-search"></i> 查询累计
							</button>
						<span>当前显示：${selectedYear } 年 01 月-${selectedYear } 年 ${selectedMonth } 月</span>
						</c:if>
					</div>
				</div>
			</div>

			<div id="T_KHJZ">
				<c:if test="${empty Datas_KHJZ}">
					<div class="alert alert-info" style="margin-top: 20px;">
						<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
						</strong>
					</div>
				</c:if>
				<c:if test="${not empty Datas_KHJZ}">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<c:forEach items="${Datas_KHJZ}" var="list" varStatus="khjz">
									<c:if test="${khjz.index == 0 }">
										<c:forEach items="${list}" var="item" varStatus="itemindex">
											<c:choose>
												<c:when test="${itemindex.index == 0 }">
													<th></th>
												</c:when>
												<c:otherwise>
													<th>${item}</th>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${Datas_KHJZ}" var="list" varStatus="khjz">
								<c:if test="${khjz.index != 0 }">
									<tr>
										<c:forEach items="${list}" var="item" varStatus="itemindex">
											<td
												<c:if test="${itemindex.index == 0 }">title="${khjz_hint[item] }"</c:if>>${item
												}</td>
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
</form>

<%@ include file="../../includes/b.jsp"%>