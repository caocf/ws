<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
   
    <script type="text/javascript">
    
    $().ready(function() {	
    	 $("#updateRefundStatus").click(function(){
    			selectCodeStockCallBack(function(updateTime,updateUser,reasonId,reasonName){
    				var orderId = ${refund.orderId };
    				if(null != reasonId){
    					$.ajax({
    						url: G_CTX_ROOT + '/order/refund/updateRefundStatusGo?updateTime='+updateTime+'&reasonId='+reasonId+'&orderId='+orderId,
    						success: function(data) {
    							if('success' == data){
    								window.location.reload();
    							}else{
    								alert('系统出错，请联系管理员！');
    							}
    							
    					}
    					});
    				}
    				
    				
    			});
    			
    		});
    		function selectCodeStockCallBack(callback,opts){
    			var orderId = ${refund.orderId };
    			var param = $.extend({	
    				ShowMessageRow:false,
    				Height: 200,
    				Width : 500},opts||{});
    			
    			var url = G_CTX_ROOT + '/order/refund/updateRefundStatus/' + orderId ;
    			
    			showDialog("更改状态",url, function(doc,win){
    				var selectRadio = $("input[name='updateSatusSelector']:checked",doc);
    				if(!jQuery.isEmptyObject(selectRadio)){
    					var updateTime =selectRadio.attr("updateTime");
    					var updateUser =selectRadio.attr("updateUser");
    					var reasonId = selectRadio.attr("reasonId");
    					var reasonName = selectRadio.attr("reasonName");
    					if(jQuery.isFunction(callback)){
    						callback(updateTime,updateUser,reasonId,reasonName,doc,win);
    					}
    				}
    			},param);
    			
    			
    		}
    	
	});
    
   

    </script>
</head>
<body>
<div id="content">
	<div class="box"> 
		<div class="title">
			<h5>退款单</h5>
		</div>
        <div class="form">
            <div class="fields">                
                <c:if test="${not empty refund.orderId}">
                <div class="field">
                    <div class="label noinput">
                        <label for="orderId" >订单编号:</label>
                    </div>
                    <div class="input">
                    ${refund.orderId}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty actType}">
                <div class="field">
                    <div class="label noinput">
                        <label for="orderId" >订单类型:</label>
                    </div>
                    <div class="input">
                    	短信购订单
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty storeMap['name']}">
                <div class="field">
                    <div class="label noinput">
                        <label for="orderId" >退款商户:</label>
                    </div>
                    <div class="input">
                    ${storeMap['name']}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty refund.id}">
                <div class="field">
                    <div class="label noinput">
                        <label for="id" >退款单号:</label>
                    </div>
                    <div class="input">
                    ${refund.id}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty refund.reason}">
                <div class="field">
                    <div class="label noinput">
                        <label for="reason" >用户退款理由:</label>
                    </div>
                    <div class="input">
                    ${refund.reason}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty refund.shopRemark}">
                <div class="field">
                    <div class="label noinput">
                        <label for="reason" >商户退款备注:</label>
                    </div>
                    <div class="input">
                    ${refund.shopRemark}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty orderRefundGoodsList}">
				   
				        <table class="datalist"  style="width:600px">
				            <tr width="600"> 
				               <th nowrap="nowrap" width="300">退款商品名称</th>
				               <th nowrap="nowrap" width="100">数量</th>
				                 <th nowrap="nowrap" width="100">已退数量</th>
				                <th nowrap="nowrap" width="100">本次退款数量</th>
				            </tr>
				            <c:forEach items="${orderRefundGoodsList}" var="item">
				            <tr >
				                <td nowrap="nowrap"  class="ellipsis">${item.itemSale.name}</td>
				                <td nowrap="nowrap" >${item.number}</td>
				              	<td nowrap="nowrap">${item.hasBuyNunber}</td>
				                <td nowrap="nowrap">${item.backNumber} </td>
				            </tr>
				            </c:forEach>
				        </table>
				   
                <div class="field">
                   
                </div>
                </c:if>
                <div class="field">
                    <div class="label noinput">
                        <label for="id" >订单价格:</label>
                    </div>
                    <div class="input">  
	                    <c:choose>
		                    <c:when test="${not empty orderSum}">
		                    ${orderSum}元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(包含物流费：<c:choose><c:when test="${not empty expressCost}">${expressCost}</c:when><c:otherwise>0</c:otherwise></c:choose>元)
		                    </c:when>
		                    <c:otherwise>0元</c:otherwise>
			            </c:choose>	                    
                    </div>
                </div>
                <c:if test="${not empty oldRefundAmount}">
                <div class="field">
                    <div class="label noinput">
                        <label for="id" >已退金额:</label>
                    </div>
                    <div class="input">
                     	${oldRefundAmount}元
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty cash || not empty coin}">
                <div class="field">
                    <div class="label noinput">
                        <label for="refundFee" >退款金额:</label>
                    </div>
                    <div class="input">
                   	 	<c:if test="${not empty cash}">现金：${cash}元;</c:if>
                   	 	<c:if test="${not empty coin}">商城币：${coin}元;</c:if>
                   	 	<c:if test="${not empty score }">积分：${score}元;</c:if>
                   	 	<c:if test="${not empty phone }">话费：${phone}元</c:if>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty refund.createTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="createTime" >退款发起时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${refund.createTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty userCode}">
                <div class="field">
                    <div class="label noinput">
                        <label for="userId" >操作人:</label>
                    </div>
                    <div class="input">
                    ${userCode}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty refund.updateTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="updateTime" >商家确认时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${refund.updateTime}" />
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty refund.auditTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="auditTime" >审核时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${refund.auditTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty auditUserCode}">
                <div class="field">
                    <div class="label noinput">
                        <label for="auditUserId" >审核人:</label>
                    </div>
                    <div class="input">
                    ${auditUserCode}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty refund.dealTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="dealTime" >退款完成时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${refund.dealTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty refund.status}">
                <div class="info-list">
                    <div  class="info-list-title">
                  		<span style="font-weight: bold;"> 退款状态:</span>
                    </div>
                    <div class="info-list-desc" >
                    	<span style="color:red">${refund.statusName} </span>
                   		<c:if test="${refund.status == 7 }">
                    		<input type="button" value="更改" id="updateRefundStatus"/>
                   		 </c:if> 
                   		 <span style="color:red">${orderRefundException }</span>
                    </div>
                  
<%--                     <input  type="button"   id=" ${refund.orderId}" class="auditing"  value="更新"> --%>
                    
                </div>
                </c:if>
                
                <div class="buttons">
                	<a onclick="history.back();"  class="btnAnchor">关闭</a>
                </div>
            </div>
        </div>
	</div>
</div>
</body>
</html>