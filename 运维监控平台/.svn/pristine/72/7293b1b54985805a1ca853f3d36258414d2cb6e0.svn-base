<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户统计" />
<%@ include file="../includes/t.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <script type="text/javascript" src="${ctx }/static/js/datepicker/WdatePicker.js" ></script>
<link href="${ctx }/static/js/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<body>
<form id="handleForm" name="handleForm" class="form-horizontal"  method="post">
<c:if test="${!empty monitor}">
		<input type="hidden" name="seqId" value="${monitor.seqId }" />
</c:if>
<table style="width:600px; text-align:left; padding:0; margin:0;" border="1">
	<tr>
		<td width="200px"><label class="control-label" for="itemNameStr" style="margin-top:18px;">平台名称：</label></td>
		<td>
			<c:if test="${!empty monitor}">
				<c:if test="${monitor.platName=='1' }" >方正</c:if>
				<c:if test="${monitor.platName=='2' }" >高阳</c:if>
				<c:if test="${monitor.platName=='3' }" >宽连</c:if>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警级别：</label></td>
		<td>
			<c:if test="${!empty monitor}">
				<c:out value="${monitor.monitorLevel }"></c:out>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警类型：</label></td>
		<td>
			<c:if test="${!empty monitor}">
				<c:if test="${monitor.monitorType=='1' }" >数据库</c:if>
				<c:if test="${monitor.monitorType=='2' }" >系统</c:if>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警发布人：</label></td>
		<td>
			<SPAN style="color:blue;"><c:out value="${createUser.name }"></c:out></SPAN>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >指定处理人：</label></td>
		<td>
			<c:if test="${!empty appoint_deal_user}">
			<c:forEach var="dealUser" items="${appoint_deal_user }">
				<SPAN style="color:blue;"><c:out value="${dealUser.name }"></c:out></SPAN>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警内容：</label></td>
		<td>
			<c:if test="${!empty monitor}">
				<c:out value="${monitor.monitor_content }"></c:out>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td ><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警时间：</label></td>
		<td>
			<c:if test="${!empty monitor}">
				<c:out value="${monitor.monitor_time }"></c:out>
			</c:if>
		</td>
	</tr>
	<tr>	
	<td ><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警时间：</label></td>
		<td>
			<c:if test="${!empty monitor}">
				<span style="color:red;"><c:if test="${monitor.status=='1' }" >未处理</c:if></span>
				<span style="color:blue;"><c:if test="${monitor.status=='2' }" >已处理</c:if></span>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >处理信息：</label></td>
		<td>
			<c:if test="${!empty monitor}">
				<c:out value="${monitor.deal_content }"></c:out>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >处理人员：</label></td>
		<td>
			<c:if test="${!empty dealUser}">
				<span style="color:red;"><c:out value="${dealUser.name }"></c:out></span>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >处理时间：</label></td>
		<td>
			<c:if test="${!empty monitor}">
				<c:out value="${monitor.deal_time }"></c:out>
			</c:if>
		</td>
	</tr>
	<tr>	
	<td colspan="2">
			<button name="btnQuery" class="btn btn-primary" style="margin-top:0px;margin-left: 180px" type="button" onclick="returnList();">
				<i class="icon16 i-search"></i> 返回
			</button>
		</td>
	</tr>
</table>
</form>
<script>
	function returnList(){
		window.location.href="${ctx}/monitor";
	}
</script>
</body> 