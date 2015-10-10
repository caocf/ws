<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
		<ht:head/>
		<script type="text/javascript">
		function rd(){
			window.location.href="${ctx}/giftcard/apply/list";
		}
		
		function checkOrder(id){
			//${ctx}/giftcard/apply/jumpAddOnLine?orderId=${item.id}
			$.ajax({
				type: "post",
				url: "${ctx}/giftcard/apply/checkOrder?id="+id,
				dataType : "json",
				success: function(data){
					if(data.success == "error"){
						simpleWarn(data.message,null);
					}else{
						if(data.success){
							window.location.href="${ctx}/giftcard/apply/jumpAddOnLine?orderId="+id;
						}else{
							simpleAlert(data.message,null);
						}
					}
				},
				error: function(){
					simpleWarn('程序异常，请联系管理员解决！',null);
				}
				});
		}
		
		$(function(){
			$("#export").click(function(){
				$("#queryForm").attr("action","${ctx}/order/refund/exportExcell");
				$("#queryForm").submit();
				$("#queryForm").attr("action","${ctx}/order/refund/list");
			});
		});
		</script>
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
		        		<input type="hidden" name="giftFlag" value="${param.giftFlag}"/>
		            <tr>
		            	<td width="80">用户手机号：</td>
		                <td><input type="text" id="terminalId" name="terminalId" value="${param.terminalId}" class="txt validate-number" style="width:150px"/></td>		                
		            	<td width="80">订单编号：</td>
		                <td><input type="text" id="id" name="id" title="订单编号" value="${param.id}" class="txt validate-number" style="width:150px"/>	
		                <span class="error" id="advice-validate-number-id" style="display:none"></span></td>
		                <td width="80">商品编号：</td>
		                <td><input type="text" id="itemId" name="itemId" title="商品编号" value="${param.itemId}" class="txt validate-number" style="width:150px"/>	
		                <span class="error" id="advice-validate-number-itemId" style="display:none"></span></td>
		            </tr>
		            <tr>
		            	<c:if test="${ empty param.giftFlag }">
		            	<td>订单类型：</td>
						<td>
							<select id="actType" name="actType">
		                        <option value="">--请选择--</option>
		                        <c:forEach items="${actTypeMap }" var="at">
		                        	<option value="${at.key }" <c:if test="${param.actType == at.key}">selected="selected"</c:if>>${at.value }</option>
		                        </c:forEach>
		                    </select>
						</td>
						<td width="100">是否货到付款：</td>
						<td >
							<select id="payOnDelivery" name="payOnDelivery">
		                        <option value="">--请选择--</option>
		                        <c:forEach items="${payOnDeliveryMap }" var="at">
		                        	<option value="${at.key }" <c:if test="${param.payOnDelivery == at.key}">selected="selected"</c:if>>${at.value }</option>
		                        </c:forEach>
		                    </select>
						</td>
						<td>支付状态：</td>
						<td>
							<select id="payStatus" name="payStatus">
		                        <option value="">--请选择--</option>
		                        <c:forEach items="${payStatusMap }" var="ps">
		                        	<option value="${ps.key }" <c:if test="${param.payStatus == ps.key}">selected="selected"</c:if>>${ps.value }</option>
		                        </c:forEach>
		                    </select>
						</td>
						<td>关闭状态：</td>
						<td>
							<select id="closeStatus" name="closeStatus">
		                        <option value="">--请选择--</option>
		                        <c:forEach items="${closeStatusMap }" var="ps">
		                        	<option value="${ps.key }" <c:if test="${param.closeStatus == ps.key}">selected="selected"</c:if>>${ps.value }</option>
		                        </c:forEach>
		                    </select>
						</td>
						</c:if>
		            </tr>
		            <tr>
		             	<td width="70">创建时间：</td> 	
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
		                    <ct:display model="order_refund_list" btn="out_btn">
		                    <c:if test="${ empty param.giftFlag }">
		                    <a id="export" class="btn_blue" style="cursor: pointer;">导出</a>
		                    </c:if>
		                    </ct:display>
		                </td>
		            </tr>
		        </table>
		    </form>
		</div>
		<div class="container">
		    <br/>
		    <c:if test="${ empty param.giftFlag }">
		    <h3>订单列表</h3>
		    </c:if>
		    <c:if test="${ !empty param.giftFlag }">
		    <h3>礼品卡订单列表</h3>
		    </c:if>
		    <div class="mainbox">
		        <c:if test="${not empty orderPage.data}">
		        <table class="datalist fixwidth">
		            <tr>
		                <th width="70" nowrap="nowrap">订单号</th>
		            	<th width="90" nowrap="nowrap">用户手机号</th>
		            	<th width="60" nowrap="nowrap">商品类型</th>
		                <th width="120" nowrap="nowrap">创建时间</th>
		                <th width="200" nowrap="nowrap">商品名称</th>
		                <th width="60" nowrap="nowrap">订单类型</th>
		                <th width="80" nowrap="nowrap">订单状态</th>
		                <th width="250" nowrap="nowrap">退款单</th>  
		                <th nowrap="nowrap">操作</th>
		            </tr>
		
		            <c:forEach items="${orderPage.data}" var="item">
		            <tr>
		                <td  nowrap="nowrap">${item.id}</td>
		            	<td  nowrap="nowrap">${item.terminalId}</td>
		            	<td  nowrap="nowrap">
		            	${item.itemModeName }
		            	</td>
		                <td  nowrap="nowrap"><ct:time source="${item.createTime}"/></td>
		                <td  nowrap="nowrap" class="ellipsis">
		                <c:forEach items="${item.itemSales }" var="itemSale">
		                	<span>${itemSale.name }</span>
		                </c:forEach>
		                </td>
		               	<td  nowrap="nowrap">
		               	<c:choose>
		               		<c:when test="${item.payOnDelivery ==1 }">货到付款</c:when>
		               		<c:otherwise>
		               			${item.actTypeName}
		               		</c:otherwise>
		               	</c:choose>
		               	</td>
		                <td  nowrap="nowrap">${item.orderStatus}</td>
		                <td >
		                <c:choose>
			                <c:when test="${0 == item.refundCount}">
			                	无
			                </c:when>
			                <c:otherwise>
				                <c:forEach items="${item.orderRefunds }" var="refund" varStatus="status">
				                	<c:if test="${ empty param.giftFlag }">
				                	<a href="${ctx}/order/refund/view?id=${refund.id}&itemMode=${item.itemMode}&orderId=${item.id}&preUrl=list">退款单${status.index+1 }</a>
				               		</c:if>
				                </c:forEach>
				        	</c:otherwise>
			            </c:choose>    
		                </td>
		                <td width="100" nowrap="nowrap">
			                <ct:display model="order_refund_list" btn="view_btn">
			                      <a href="${ctx}/order/view?id=${item.id}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
			                </ct:display>
			                <c:if test="${item.canRefund eq true }">
			                	 <ct:display model="order_refund_list" btn="refund_add_btn">
			                	 <c:if test="${ empty param.giftFlag }">
			                 	 <a href="${ctx}/order/refund/add?orderId=${item.id}">增加退款单</a>
			                 	 </c:if>
			                 	</ct:display>
			                </c:if> 
			                <c:if test="${ !empty param.giftFlag }">
			                	<a href="#" onClick="checkOrder(${item.id});">申请出库</a>&nbsp;&nbsp;&nbsp;&nbsp;
			                </c:if>      
		                </td>
		            </tr>
		            </c:forEach>
		        </table>		
		        <ht:page pageData="${orderPage}" />		
		        </c:if>
		        <c:if test="${empty orderPage.data}">
		        <div class="note">
		            <p class="i">目前没有相关记录!</p>
		        </div>
		        </c:if>
		        <c:if test="${ !empty param.giftFlag }">
		        <div class="buttons">
					<input type="button" class="common_btn" onclick="rd();" value="返回"/>
				</div>
				</c:if>
		    </div>	
		</div>	
	</body>
</html>