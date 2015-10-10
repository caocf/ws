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
		        <ht:menu-btn type="search"/>
		    </ul>
		    <br style="clear: left" />
		</div>
		<div class="queryContainer" >
		    <form name="queryForm" id="queryForm" action="?" method="get">
		        <table>
		            <tr>
		            	<td>手机号：</td>
		                <td><input type="text" name="terminalId" value="${param.terminalId}" class="txt validate-number" style="width:100px"/></td>	
		                <td width="50">订单号：</td>
		                <td><input type="text" name="orderId" value="${param.orderId}" class="txt validate-number" style="width:100px"/></td>			
         
                	<td>收货地址状态：</td>
						<td width="100"><select name="isAdd">
							<option value="">--请选择--</option>
							<c:forEach items="${isAddAddress}" var="item" varStatus="index">
								<option value="${item.key }"
									<c:if test="${item.key == param.isAdd}">selected="selected"</c:if>>${item.value}</option>
							</c:forEach>
					</select>
					</td>
					
					<td>订单支付状态：</td>
						<td width="100"><select name="payStatus">
							<option value="">--请选择--</option>
							<c:forEach items="${payStatusMap}" var="item" varStatus="index">
								<option value="${item.key }"
									<c:if test="${item.key == param.payStatus}">selected="selected"</c:if>>${item.value}</option>
							</c:forEach>
					</select>
					</td>
                
		            </tr>
		            <tr>
		             <td width="70">下单时间：</td>
		                 <td>
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
		    <h3>订单列表</h3>
		    <div class="mainbox">
		        <c:if test="${not empty pageData.data}">
		        <table class="datalist fixwidth">
		            <tr>
		            	<th nowrap="nowrap">订单号</th>
		            	<th nowrap="nowrap">手机号码</th>
		                <th nowrap="nowrap">下单时间</th>
		                <th nowrap="nowrap">订单类型</th>
		                <th nowrap="nowrap">收货地址状态</th>
		                <th nowrap="nowrap">订单支付状态</th>
		                <th nowrap="nowrap">发货状态</th>
		                <th nowrap="nowrap">操作</th>
		            </tr>
		
		            <c:forEach items="${pageData.data}" var="item">
		            <tr>
		            	<td  nowrap="nowrap">${item.orderId}</td>
		            	<td  nowrap="nowrap">${item.terminalId}</td>
		               	<td  nowrap="nowrap"><ct:time source="${item.createTime}"/></td>
		                <td  nowrap="nowrap">${item.actTypeName}</td>
		                <td  nowrap="nowrap"><c:if test="${empty item.address}"> 未添加</c:if><c:if test="${!empty item.address}"> 已添加</c:if></td>
		                <td  nowrap="nowrap">${item.payStatusName}</td>
		                <td  nowrap="nowrap">
		                ${item.sendStatusName}
		                <c:if test="${item.sendStatus == null}">未发货</c:if>
		                </td>
		                <td width="100" nowrap="nowrap">
			            	<ct:display model="order_smsbuy_list" btn="view_btn">
			               		<a href="${ctx}/order/smsbuy/view?orderId=${item.orderId}"><c:if test="${!empty item.address}">查看</c:if></a>
			            	</ct:display>
			            	<ct:display model="order_smsbuy_list" btn="mod_btn">
	                       <a href="${ctx}/order/smsbuy/viewAdd?id=${item.terminalId}&createTime=${item.createTime}&orderId=${item.orderId}">
	                       		<c:if test="${empty item.address}"> 添加地址</c:if>
	                       		<c:if test="${item.sendStatus == null || item.sendStatus == 0}"><c:if test="${!empty item.address}">修改地址</c:if></c:if>
	                       </a>
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