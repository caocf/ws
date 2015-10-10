<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
		<ht:head/>
	</head>
	<script type="text/javascript">
	$(function(){
		$("#back").click(function(){
// 			location="${ctx}/gift/exchange/exchangelist";
			location='${backUrl}';
		});
	});
	</script>
	<body>	
		<br/>
			<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer" >
		   <form name="queryForm" id="queryForm" action="?" >
		   		<input type="hidden" name="batchNo" value="${batchNo }">
		   		<input type="hidden" name="backUrl" value="${backUrl }">
		        <table>
		            <tr>
		                <td>卡型号：</td>
		                <td><input type="text" name="modelNo" value="${param.modelNo}" class="txt" style="width:150px"/></td>
		                <td>礼品卡号：</td>
		                <td><input type="text" name="serialNo" value="${param.serialNo}" class="txt" style="width:150px"/></td>
		            </tr>
		            <tr>
<!-- 		            	<td>礼品卡状态：</td> -->
<!-- 						<td width="100"><select name="cardStatus"> -->
<!-- 							<option value="">--请选择--</option> -->
<%-- 							<c:forEach items="${cardStatusMap}" var="item" varStatus="index"> --%>
<%-- 								<option value="${item.key }" --%>
<%-- 									<c:if test="${item.key == param.cardStatus}">selected="selected"</c:if>>${item.value}</option> --%>
<%-- 							</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</td> -->
<!-- 		            	<td>卡兑换状态：</td> -->
<!-- 						<td width="100"><select name="status"> -->
<!-- 							<option value="">--请选择--</option> -->
<%-- 							<c:forEach items="${statusMap}" var="item" varStatus="index"> --%>
<%-- 								<option value="${item.key }" --%>
<%-- 									<c:if test="${item.key == param.status}">selected="selected"</c:if>>${item.value}</option> --%>
<%-- 							</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</td> -->
			            <td width="90">兑换时间：</td>
			                <td >
			                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
			                           readOnly
			             onfocus="WdatePicker({vel:'beginTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2050-10-01\'}'})" />
			                    <input type="hidden" name="beginTime" id="beginTime" value="${param.beginTime}"/>
			                   	至
			                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}"  class="txt Wdate"
			                           readOnly 
			             onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2050-10-01'})" />
			                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
			                </td>
			                <td colspan="4">
			                    <ct:btn type="search" />
			                </td>	 	 		 
		            </tr>
		        </table>
		    </form>
		</div>
		<div class="container">
		    <br/>
		    <h3>礼品卡列表</h3>
		    <div class="mainbox">
		        <c:if test="${not empty pageData.data}">
		        	<tr>
				       <td colspan="8" align="left">
				       		<ct:display model="gift_exchange_list" btn="out_btn">
				        	<a href="download?batchNo=${batchNo }"  class="btn_blue" >导出</a>
				        	</ct:display>
			        	</td>
        			</tr>
		        <table class="datalist fixwidth">
		            <tr>
		            	<th nowrap="nowrap" width="120">批次号</th>
		            	<th nowrap="nowrap" width="150">卡型号</th>
		            	<th nowrap="nowrap" width="120">礼品卡号</th>
		            	<th nowrap="nowrap" width="100">兑换用户</th>
		            	<th nowrap="nowrap" width="100">兑换时间</th>
<!-- 		            	<th nowrap="nowrap" width="100">礼品卡状态</th> -->
		            	<th nowrap="nowrap" width="100">兑换状态</th>
		            </tr>
		
		            <c:forEach items="${pageData.data}" var="item">
		            <tr>
		            	<td  nowrap="nowrap">${item.batchNo}</td>
		            	<td  nowrap="nowrap" class="ellipsis">${item.modelNo}</td>
		            	<td  nowrap="nowrap" >${item.serialNo}</td>
		            	<td  nowrap="nowrap">
		            	<c:if test="${not empty item.userName}">${item.userName }</c:if>
		            	<c:if test="${empty item.userName}">
		            		<c:if test="${not empty  item.terminalId}">${item.terminalId}</c:if>
		            		<c:if test="${empty  item.terminalId}">${item.email}</c:if>
		            	</c:if>
		            	</td>
		            	<td  nowrap="nowrap">
		            	<ct:time source="${item.createTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss" />
		            	</td>
<!-- 		            	<td  nowrap="nowrap"> -->
<%-- 		            	${item.cardStatusName} --%>
<!-- 		            	</td> -->
		            	<td  nowrap="nowrap">
		            	<c:choose>
		            		<c:when test="${item.status ==1}">已兑换</c:when>
		            		<c:when test="${item.status ==3}">兑换异常</c:when>
		            		<c:otherwise>
		            			未兑换
		            		</c:otherwise>
		            	</c:choose>
		            	</td>
		            </tr>
		            </c:forEach>
		        </table>		
		        <ht:page pageData="${pageData}" />		
		        </c:if>
		        <c:if test="${empty pageData.data}">
		        <div class="note">
		            <p class="i">目前没有相关记录!</p>
		        </div>
		        </c:if>
		       	<input id="back" type="button" class="common_btn"  value="返回" />
		    </div>	
		</div>	
	</body>
</html>