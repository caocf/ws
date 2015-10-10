<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(function() {
		$('a[name=analysis]').click(function() {
			var type = $(this).attr('type');
			$(this).siblings().removeClass('btn-primary');
			$(this).addClass('btn-primary');
			$('div[name=analysis]').hide();
			$('#analysis_'+type).show();
		});
	});
</script>
<div class="well"
	style="padding: 0px !important; padding-top: 19px !important;">
	<form class="form-horizontal" action="?" name="queryForm"
		id="queryForm" method="get">
		<div class="control-group group-search">
			<label class="control-label" for="queryStartTime"> 查询日期：</label>
			<div class="controls controls-row">
				<select id="year" name="year">
				<!-- 	<option value="">请选择年份</option> -->
					<c:forEach var="year" begin="${beginYear}" end="${endYear }" step="1">
						<option value="${year}"
							<c:if test="${year == year_search }">selected</c:if>>${year}</option>
					</c:forEach>
				</select> <span class="form-middle-word">年</span> <select id="month"
					name="month">
					<!-- <option value="">请选择月份</option> -->
					<c:forEach var="month" begin="1" end="12" step="1">
						<option value='<c:choose><c:when test="${month < 10 }">0${month }</c:when><c:otherwise>${month }</c:otherwise></c:choose>'
							<c:if test="${month == month_search }">selected</c:if>>${month}</option>
					</c:forEach>
				</select> <span class="form-middle-word">月</span>
				<button class="btn btn-primary" type="submit">
					<i class="icon16 i-search"></i> 筛选
				</button>
				<span>当前显示：${year_search } 年 ${month_search } 月</span>
			</div>
		</div>
	</form>
	<form id="exportForm" name="exportForm" method="post" action="${ctx}/businessOverview/analysis/export">
		<input type="hidden" value="" name="year" id="export_year" />
		<input type="hidden" value="" name="month" id="export_month" />
		<input type="hidden" value="" name="type" id="export_type" />
	</form>
</div>

<div class="page-header">
	<h4></h4>
</div>
<div>
	<a href="javascript:;" style="height: 30px;" name="analysis" class="btn btn-primary" type="ywsr">业务收入</a>
	<a href="javascript:;" style="margin-left: 15px;height: 30px;" name="analysis" class="btn" type="dzhy">定制会员数</a>
	<a href="javascript:;" style="margin-left: 15px;height: 30px;" name="analysis" class="btn" type="jyyh">月交易用户数</a>
	<a href="javascript:;" style="margin-left: 15px;height: 30px;" name="analysis" class="btn" type="khd">客户端安装数</a>
	<a href="javascript:;" style="margin-left: 15px;height: 30px;" name="analysis" class="btn" type="dsjy_jsuss">电商交易额</a>
	<a href="javascript:;" style="margin-left: 15px;height: 30px;" name="analysis" class="btn" type="hyyh">月活跃用户数</a>
</div>