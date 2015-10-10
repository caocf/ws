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
		            	<td>退款单号：</td>
		                <td><input type="text" id="id" name="id" title="退款单号" value="${param.id}" class="txt validate-number" style="width:150px"/>	
		                <span class="error" id="advice-validate-number-id" style="display:none"></span></td>
		            	<td>订单编号：</td>
		                <td><input type="text" id="orderId" name="orderId" title="订单编号" value="${param.orderId}" class="txt validate-number" style="width:150px"/>	
		                <span class="error" id="advice-validate-number-orderId" style="display:none"></span></td>		            	
		            </tr>
		            <tr>
		                <td>退款单类型：</td>
						<td>
							<select name="refundType">
		                        <option value="">--请选择--</option>
		                        <c:forEach items="${refundTypeMap }" var="s">
		                        	<option value="${s.key }" <c:if test="${param.refundType == s.key}">selected="selected"</c:if>>${s.value }</option>
		                        </c:forEach>
		                    </select>
						</td>
		            	<td>退款状态：</td>
						<td>
							<select name="status">
		                        <option value="">--请选择--</option>
		                        <c:forEach items="${statusMap }" var="s">
		                        	<option value="${s.key }" <c:if test="${param.status == s.key}">selected="selected"</c:if>>${s.value }</option>
		                        </c:forEach>
		                    </select>
						</td>
						<td width="70">发起时间：</td> 	
		                <td >
		                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
		                           readOnly
		             onfocus="WdatePicker({vel:'createTimeBegin',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2050-10-01\'}'})" />
		                    <input type="hidden" name="createTimeBegin" id="createTimeBegin" value="${param.createTimeBegin}"/>
		                   	至
		                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}"  class="txt Wdate"
		                           readOnly 
		             onfocus="WdatePicker({vel:'createTimeEnd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2050-10-01'})" />
		                    <input type="hidden" name="createTimeEnd" id="createTimeEnd" value="${param.createTimeEnd}" />
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
		    <h3>退款单审核列表</h3>
		    <div class="mainbox">
		        <c:if test="${not empty orderRefundPage.data}">
		        <table class="datalist fixwidth">
		            <tr>
		                <th nowrap="nowrap" width="120">退款单号</th>
		                <th nowrap="nowrap" width="100">退款单类型</th>
		                <th nowrap="nowrap" width="120">手机号</th>
		                <th nowrap="nowrap" width="100">商品类型</th>
		            	<th nowrap="nowrap" width="150">退款发起时间</th>
		                <th nowrap="nowrap" width="150">处理状态</th>
		                <th nowrap="nowrap" width="150">订单号</th>
		                <th nowrap="nowrap">操作</th>
		            </tr>
		
		            <c:forEach items="${orderRefundPage.data}" var="item">
		            <tr>
		                <td  nowrap="nowrap">
		                <a href="${ctx}/order/refund/view?id=${item.id}&orderId=${item.orderId}&itemMode=${item.itemMode}&preUrl=audit">${item.id}</a>
		                </td>
		                <td  nowrap="nowrap">${item.refundTypeName }</td>
		                <td  nowrap="nowrap">${item.terminalId }</td>
		                <td  nowrap="nowrap"><c:if test="${item.itemMode eq 0}">实物</c:if><c:if test="${item.itemMode eq 1}">虚拟物</c:if></td>
		                <td  nowrap="nowrap"><ct:time source="${item.createTime}"/></td>
		                <td  nowrap="nowrap" >
		                <span 
		                <c:if test="${item.status ==3}"> title="${item.shopRemark==null ? '暂无拒绝理由' : item.shopRemark}"</c:if> >
		                ${item.statusName}
		                </span>
		                </td>
		                <td  nowrap="nowrap">${item.orderId}</td>
		                <td width="100" nowrap="nowrap">
			                <c:if test="${item.status == 2}">
			                	<ct:display model="order_refund_audit_list" btn="audit_btn">
	                			<a href="refund_auditing?id=${item.id}&whereAbout=list">审核</a>&nbsp;&nbsp;
	                			</ct:display>
		                	</c:if>
		                	<c:if test="${item.status == 4}">
			                	<ct:display model="order_refund_audit_list" btn="refund_refund_btn">
		                		<a href="#"   onclick="dealInfo('确定要退款吗？\n退款操作耗时较长，请耐心等待','refund/${item.id}');">退款</a>
		                		</ct:display>
		                	</c:if>              
		                	
		                </td>
		            </tr>
		            </c:forEach>
		        </table>		
		        <ht:page pageData="${orderRefundPage}" />		
		        </c:if>
		        <c:if test="${empty orderRefundPage.data}">
		        <div class="note">
		            <p class="i">目前没有相关记录!</p>
		        </div>
		        </c:if>
		    </div>	
		</div>	
	</body>
</html>