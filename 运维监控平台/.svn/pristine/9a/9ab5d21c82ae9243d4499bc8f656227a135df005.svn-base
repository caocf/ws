<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="../../includes/if.jsp"%>
<%@ page import="java.text.DecimalFormat" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript">
		function queryList(){
			
				$('#queryForm').action="${ctx}/recommendMgmt/effect/effectInfo";
				$('#queryForm').submit();
			}; 
		</script>

	</head>

	<body>
		<form class="form-horizontal" action="" name="queryForm"
			id="queryForm" method="get">
			<div class="row-fluid">
				<div class="span12">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="home">
							<div class="control-group group-search" style="margin-left: 0px;">
								<label class="control-label" for="queryStartTime">
									选择时间：
								</label>
								<div class="controls controls-row">
									<div id="from" class="input-append date"
										data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
										<input id="date" name="selectedDate" type="text"
											value="${selectedDate}">
										<span class="add-on"> <i class="icon16 i-calendar-4"></i>
										</span>
									</div>
									<input type="hidden" name="module" value=${module} >
									<input type="radio" name="dateType" value="2" 
										<c:if test="${not empty dateType && dateType == '2'}"> checked="checked"</c:if> />
									当日
									<input type="radio" name="dateType" value="0" 
										<c:if test="${ empty dateType || dateType == '0'}"> checked="checked"</c:if> />
									当月
									<input type="radio" name="dateType" value="3" 
										<c:if test="${not empty dateType && dateType == '3'}"> checked="checked"</c:if> />
									过去30天
									<input type="radio" name="dateType" value="1" 
										<c:if test="${not empty dateType && dateType == '1'}"> checked="checked"</c:if> />
									过去6个月
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn btn-primary" onclick = queryList();>
										<i class="icon16 i-search"></i> 过滤
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div>
			<c:if test="${empty engineEffect.data}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>
		<c:if test="${not empty engineEffect.data}">
				<div class="tab-pane fade in active" id="home">
			 <table class="table table-bordered table-hover table-striped">
				<thead>
				<tr>
					<th>
						引擎内容/分类        
					</th>
					<th>
						时间
					</th>
					<th>
						点击次数
					</th>
					<th>
						推荐次数
					</th>
					<th>
						推荐成功率
					</th>
				</tr>
			 </thead>
			
			<tbody>
				<c:forEach items="${engineEffect.data}" var="item">
					<tr>
						<td class="center vcenter">
							<c:if test="${not empty item[0] and item[0]=='groupBuy' }">商城商品推荐引擎</c:if>
							<c:if test="${not empty item[0] and item[0]=='cashVoucher' }">电子商务本地化引擎</c:if>
							<c:if test="${not empty item[0] and item[0]=='coupon' }">优惠券</c:if>
							<c:if test="${not empty item[0] and item[0]=='shop' }">位置推荐引擎</c:if>
						</td>
						<td class="center vcenter">
							${item[1]}
							
<%--							double percent = df.format(Double.valueOf('${item[2]}')/Double.valueOf('${item[3]}'));--%>
						</td>
						
						<td class="center vcenter">
							${item[2]}
						</td>
						<td class="center vcenter">
							${item[3]}
						</td>
						<td class="center vcenter">
							<c:if test="${not empty item[3] && item[3]!=0 && not empty item[2]}">
								<fmt:formatNumber pattern="#.##%" value="${item[2]/item[3]}"></fmt:formatNumber>
							</c:if>
							<c:if test="${not empty item[3] && item[3]!=0 && empty item[2]}">
								<fmt:formatNumber pattern="#.##%" value="${0/item[3]}"></fmt:formatNumber>
							</c:if>
							<c:if test="${empty item[3]}">
								∞
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
			<ht:page pageData="${engineEffect}" />
	</c:if>
	 
		
		</div>
		
		
	</body>
</html>
