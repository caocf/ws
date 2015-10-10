<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
		<ht:head/>
	</head>
	<body>	
	<br/>
			<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer" >
		    <form name="queryForm" id="queryForm" action="?" method="get">
		        <table>
		            <tr>
		             	<td>编号：</td>
		                <td><input type="text" name="id" value="${param.id}" title="编号" class="txt validate-number" style="width:150px"/></td>
		                         <td width="70">中奖时间：</td>
                <td />
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
                	
		                </tr>
		            <tr>
		            	<td colspan="4">
		                    <ct:btn type="search" />
		                </td>
		                
		            </tr>
		        </table>
		    </form>
		</div>
		<div class="container">
		    <br/>
			 <ct:display model="lottery_log_list" btn="export_btn">
                <a href="#" onclick="download">导出数据</a>
                </ct:display>
		    
		    <div class="mainbox">
		        <c:if test="${not empty pageData.data}">
		        <table class="datalist fixwidth">

	        	
		            <tr>
		            	<th nowrap="nowrap" width="80">编号</th>
		                <th nowrap="nowrap" width="80">奖品等级</th>
		                <th nowrap="nowrap" width="100">中奖手机号</th>
		                <th nowrap="nowrap">中奖内容</th>
		                <th nowrap="nowrap" width="150">中奖时间</th>
		            </tr>
		
		            <c:forEach items="${pageData.data}" var="item">
		            <tr>
		            	<td  nowrap="nowrap">${item.id}</td>
		                <td  nowrap="nowrap" >${item.hitLevel}</td>
		                <td  nowrap="nowrap" >${item.targetId}</td>
		               	<td  nowrap="nowrap" class="ellipsis">${item.content }</td>
		                <td  nowrap="nowrap"><ct:time source="${item.hitTime}"/></td>
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
		    </div>	
		</div>	
	</body>
</html>