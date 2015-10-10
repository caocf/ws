<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户统计" />
<%@ include file="../../includes/t.jsp"%>
 
<script type="text/javascript">
	// ajax 提交曲线图
	  $(function (){
			$.ajax({
				url: "${ctx}/order",
				type: 'get',
				dataType: 'json',
				data: {},
				success:function(response){
					if(response==null){
						alert("请求成功，返回对象null！");
					}
					else{
						curveChart(response);
					}
				},
				error:function(){
					alert("请求失败！");
				}
			});
			
		});
</script>

<div class="row-fluid">
	<div class="span12">
		<div class="well" style="padding: 0px !important; padding-top: 19px !important;">
			<form id="queryForm" name="queryForm" class="form-horizontal" method="post">
				<div class="control-group group-search" style="margin-left: 0px;">
					<label class="control-label" for="shopNameStr">查找商户：</label>
					<div class="controls controls-row">
						<input id="shopNameStr" name="shopNameStr" type="text" value="" placeholder="商户名称" title="商户名称" />
						<button name="btnQueryMerchant" class="btn btn-primary" type="button">
							<i class="icon16 i-search"></i> 筛选商户
						</button>
					</div>
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
							<c:forEach var="month" begin="${1}" end="${12}" step="1"><option value="${month}" <c:if test="${month == condition.monthStr}">selected="selected"</c:if>>
									<c:if test="${month<10}">0${month}</c:if>
									<c:if test="${month>=10}">${month}</c:if>
								</option>
							</c:forEach>
						</select> <span class="form-middle-word">月</span>
					</div>
					<label class="control-label" for="queryStartTime">选择品类：</label>
					<div class="controls controls-row">
						<select id="categoriesPidStr" name="categoriesPidStr">
							<option value="">请选择</option>
							<c:if test="${not empty categories1}">
								<c:forEach var="c" items="${categories1}">
									<option value="${c.id}" <c:if test="${c.id eq condition.categoriesPidStr}">selected</c:if>>${c.name}</option>
								</c:forEach>
							</c:if>
						</select> <select id="categoriesIdStr" name="categoriesIdStr">
							<option value="">请选择</option>
							<c:if test="${not empty categories2}">
								<c:forEach var="c" items="${categories2}">
									<option value="${c.id}" <c:if test="${c.id eq condition.categoriesIdStr}">selected</c:if>>${c.name}</option>
								</c:forEach>
							</c:if>
						</select>
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
		<div>
			<div style="text-align: right; margin: 5px 0px">
				<button name="btnExcel" class="btn">导出到EXCEL文件</button>
			</div>
			<table class="table table-bordered table-hover table-striped">
				<c:forEach var="data" items="${datas}" varStatus="status">
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

<div style="float: left; width: 200px;">
	<c:forEach var="data" items="${datas}" varStatus="statusI">
		<c:if test="${statusI.index == 0}">
			<c:forEach var="item" items="${data}" varStatus="statusJ">
				<c:if test="${!statusJ.first}">
					<c:if test="${statusJ.index == 1}">
						<a name="btnChart" type="${statusJ.index}" class="btn btn-primary" style="width: 120px; margin-top: 10px; font-weight: bold">${item}</a>
					</c:if>
					<c:if test="${statusJ.index != 1}">
						<a name="btnChart" type="${statusJ.index}" class="btn" style="width: 120px; margin-top: 10px; font-weight: bold">${item}</a>
					</c:if>
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
</div>

<div style="float: left;">
	 <div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>
		
</div>

<%@ include file="../../includes/b.jsp"%>
<%@ include file="../../includes/chartControl.jsp"%>
 