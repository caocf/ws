<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
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
		            	<td>商户ID：</td>
		                <td><input type="text" name="storeId" title="商户ID" value="${param.storeId}" class="txt validate-number" style="width:150px"/></td>		 
		                <td>商户名字：</td>
		                <td><input type="text" name="storeName" value="${param.storeName}" class="txt" style="width:150px"/></td>
		            </tr>
		            <tr>
		            	<td>批次号：</td>
		                <td><input type="text" name="batchNo" title="批次号" value="${param.batchNo}" class="txt validate-number" style="width:150px"/></td>		 
		                <td>卡型号：</td>
		                <td><input type="text" name="modelNo" value="${param.modelNo}" class="txt" style="width:150px"/></td>
		            </tr>
		             <tr>
		            	<td>客户名：</td>
		                <td><input type="text" name="requiredUser" value="${param.requiredUser}" class="txt" style="width:150px"/></td>	
		            	   	 <td width="90">期望发卡时间：</td>
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
		    <h3>礼品卡需求审核列表</h3>
		    <div class="mainbox">
		        <c:if test="${not empty pageData.data}">
		        <table class="datalist fixwidth">
		              <tr>
		            	<th nowrap="nowrap" width="80">批次号</th>
		            	<th nowrap="nowrap" width="">所属商户</th>
		            	<th nowrap="nowrap" width="">卡型号</th>
		            	<th nowrap="nowrap" width="80">客户名</th>
		            	<th nowrap="nowrap" width="100">需要礼品卡数量</th>
		            	<th nowrap="nowrap" width="100">期望发卡时间</th>
						<th nowrap="nowrap" width="100">需求提交时间</th>
						<th nowrap="nowrap" width="100">审核状态</th>
						<th nowrap="nowrap" width="180">操作</th>
		            </tr>
		
		            <c:forEach items="${pageData.data}" var="item">
		            <tr>
		            	<td  nowrap="nowrap">${item.batchNo}</td>
		            	<td  nowrap="nowrap">${item.storeName}</td>
		            	<td  nowrap="nowrap">${item.modelNo}</td>
		            	<td  nowrap="nowrap">${item.requiredUser}</td>
		            	<td  nowrap="nowrap">${item.cardNum}</td>
		            	<td  nowrap="nowrap"><ct:time source="${item.issuingTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd" /></td>
		            	<td  nowrap="nowrap"><ct:time source="${item.createdTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd" /></td>
		            	<td  nowrap="nowrap">${item.statusName}</td>
		            	<td  nowrap="nowrap">
		            		<ct:display model="gift_required_auditList" btn="view_btn">
								<a href="view/${item.batchNo}">查看</a>&nbsp; &nbsp;
							</ct:display>
							<ct:display model="gift_required_auditList" btn="audit_btn">
								<a href="audit/${item.batchNo}">审核</a>&nbsp; &nbsp;
							</ct:display>
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
		    </div>	
		</div>	
	</body>
</html>