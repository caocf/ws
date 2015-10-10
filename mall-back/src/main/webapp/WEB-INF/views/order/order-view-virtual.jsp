<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/importer.jsp"%>

<!doctype html>
<html>

<head>
	<link href="${ctx}/static/css/order_style.css" rel="stylesheet" type="text/css" />
    <ht:head/>
    <script type="text/javascript">
    $(function(){
		
		
		
		$("#cashUpdatePayStatus").click(function(){
     			selectCodeStockCallBack(function(updateTime,updateUser,reasonId,reasonName){
     				var orderId = ${order.id };
     				var currency = 'cash';
     				if(null != reasonId){
     					$.ajax({
         					url: G_CTX_ROOT + '/order/updatePayStatusGo?updateTime='+updateTime+'&reasonId='+reasonId+'&orderId='+orderId+'&currency='+currency,
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
     			var orderId = ${order.id };
     			var param = $.extend({	
     				ShowMessageRow:false,
     				Height: 200,
     				Width : 500},opts||{});
     			
     			var url = G_CTX_ROOT + '/order/updatePayStatus/' + orderId ;
     			
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
     		
     		$("#coinUpdatePayStatus").click(function(){
     			selectCodeStockCallBack(function(updateTime,updateUser,reasonId,reasonName){
     				var orderId = ${order.id };
     				var currency = 'coin';
     				if(null != reasonId){
     					$.ajax({
         					url: G_CTX_ROOT + '/order/updatePayStatusGo?updateTime='+updateTime+'&reasonId='+reasonId+'&orderId='+orderId+'&currency='+currency,
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
     		
     		
     		
     		$("#scoreUpdatePayStatus").click(function(){
     			selectCodeStockCallBack(function(updateTime,updateUser,reasonId,reasonName){
     				var orderId = ${order.id };
     				var currency = 'score';
     				if(null != reasonId){
     					$.ajax({
         					url: G_CTX_ROOT + '/order/updatePayStatusGo?updateTime='+updateTime+'&reasonId='+reasonId+'&orderId='+orderId+'&currency='+currency,
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
     		
     		
     		$("#balanceUpdatePayStatus").click(function(){
     			selectCodeStockCallBack(function(updateTime,updateUser,reasonId,reasonName){
     				var orderId = ${order.id };
     				var currency = 'balance';
     				if(null != reasonId){
     					$.ajax({
         					url: G_CTX_ROOT + '/order/updatePayStatusGo?updateTime='+updateTime+'&reasonId='+reasonId+'&orderId='+orderId+'&currency='+currency,
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
     		
		
    });
    </script>
</head>
<body>
<div id="content">
	<div class="r-page">
	
		<!-- 订单信息 -->
    	<div class="model-block">
        	<div class="block-head">
            	<h4>订单信息</h4>
            </div>
            <div class="block-body">
            	<div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">订单编号：</div>
                        <div class="info-list-desc">${order.id }</div>
                    </div>
                </div>
                <div class="info-list">
                    <div class="info-list-widght">
                    <c:if test="${not empty userMap['nickName'] }">
                     	<div class="info-list-title">下单昵称：</div>
                        <div class="info-list-desc">${userMap['nickName'] }</div>
                    </c:if>
                    <c:if test="${empty userMap['nickName'] }">
                    	<c:if test="${not empty userMap['terminalId'] }">
                     	<div class="info-list-title">下单手机号：</div>
                        <div class="info-list-desc">${userMap['terminalId'] }</div>
                        </c:if>
                        <c:if test="${empty userMap['terminalId'] }">
                     	<div class="info-list-title">下单邮箱：</div>
                        <div class="info-list-desc">${userMap['email'] }</div>
                        </c:if>
                    </c:if>
                    </div>
                </div>
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">订单类型：</div>
                        <div class="info-list-desc">
                        <c:if test="${order.actType==20}">短信购</c:if>
                        <c:if test="${order.actType==40}">普通购买</c:if>
                        </div>
                    </div>
                </div>
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">订单状态：</div>
                        <div class="info-list-desc">
                        ${orderStatus }
                        </div>
                    </div>
                </div>
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">订单总金额：</div>
                        <div class="info-list-desc">
                        	商品金额<fmt:formatNumber value="${totalPrice }" pattern="0.00"/>元
                        	-优惠金额<fmt:formatNumber value="${discount-ifCardPay }" pattern="0.00"/>元
                        	<c:if test="${ifCardPay > 0 }">-礼金券抵扣金额<fmt:formatNumber value="${ifCardPay }" pattern="0.00"/>元</c:if>
                        	=
                        	<span class="big-highlight"><fmt:formatNumber value="${payPrice}" pattern="0.00"/>元</span>
                        </div>
                    </div>
                </div>
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">下单时间：</div>
                        <div class="info-list-desc">
	                        <c:if test="${empty order.createTime}"></c:if>
							<c:if test="${!empty order.createTime}">
								<fmt:parseDate value="${order.createTime}" pattern="yyyyMMddHHmmss" var="test"/>
								<fmt:formatDate value="${test}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 商品信息 -->
        <div class="model-block">
        	<div class="block-head">
            	<h4>商品信息</h4>
            </div>
            <div class="block-body">
            	<div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">商户ID：</div>
                        <div class="info-list-desc">${order.shopId  }</div>
                    </div>
                </div>
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">商户名称：</div>
                        <div class="info-list-desc">${userMap['storeName']  }</div>
                    </div>
                </div>
                <c:if test="${itemMap['itemMode'] == 1}">
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">商品名称：</div>
                        <div class="info-list-desc">
                        ${goodInfo.goodsSubject  }&nbsp;&nbsp;&nbsp;&nbsp;*${goodInfo.count  }
                        </div>
                    </div>
                </div>
                </c:if>
            	<table cellpadding="0" cellspacing="0">
            		<c:if test="${empty order.goodsInfos}">无商品</c:if>
                    <!-- 虚拟商品 -->
                    
            		<c:if test="${not empty order.goodsInfos && (itemMap['itemMode'] ==1)}">
            		
            		<c:if test="${empty codes }">无验证码
            		</c:if>
            		<c:if test="${not empty codes }">
                	<tr>
                    	<th>序号</th>
                        <th>验证码</th>
                        <th>状态</th>
                        <th>有效期至</th>
						<th>操作</th>
                    </tr>
                    </c:if>
                    <c:forEach items="${codes }" var="code" varStatus="status">
                    <tr>
                    	<td>${status.index+1 }</td>
                        <td class="center">${code.orderId}</td>
                        <td class="center">
                        	${code.statusName}
                        </td>
                        <td class="center"><ct:time source="${code.expireDate}" /></td>
						<td class="center">
<%-- 							<a  style="cursor: pointer;" onclick="dealInfo('确认补发？','reissued?id=${goodInfo.id  }&orderId=${order.id }');">补发</a> --%>
<%-- 							<c:if test="${code.codeStatus !=1}"> --%>
								<a  style="cursor: pointer;" onclick="dealInfo('确认重发？','resend?id=${code.orderId  }');">重发</a>
<%-- 							</c:if> --%>
						</td>
                    </tr>
                    </c:forEach>
                    </c:if>
                </table>
            </div>
        </div>
        
        <!-- 支付信息 -->
        <div class="model-block">
        	<div class="block-head">
            	<h4>支付信息</h4>
            </div>
            <div class="block-body">
            	<c:if test="${not empty cashAmount }">
            	<div class="info-list">
                	<div class="info-list-widght">
                        <div class="info-list-title">现金支付：</div>
                        <div class="info-list-desc">
                        	${ cashAmount/100}元&nbsp;&nbsp;
                        	<c:if test="${cashPayStatus ==2}">
                        	<span style="color:red">[已支付]</span>
                        	</c:if>
                        	<c:if test="${cashPayStatus !=2}">
                        	<span >[未支付]</span>
                        	</c:if>
                        	<c:if test="${order.payStatus == 1 && cashPayStatus !=2}">
                        		<input type="button" value="更改" id="cashUpdatePayStatus"/>
                        	</c:if>
                        	<span style="color:red">${ cashUpdateStatus}</span>
                        </div>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty coinAmount }">
            	<div class="info-list">
                	<div class="info-list-widght">
                        <div class="info-list-title">商城币支付：</div>
                        <div class="info-list-desc">
                        	${ coinAmount/100}元&nbsp;&nbsp;
                        	<c:if test="${coinPayStatus ==2}">
                        	<span style="color:red">[已支付]</span>
                        	</c:if>
                        	<c:if test="${coinPayStatus !=2}">
                        	<span >[未支付]</span>
                        	</c:if>
                        		<c:if test="${order.payStatus == 1 && coinPayStatus !=2 }">
                        		<input type="button" value="更改" id="coinUpdatePayStatus"/>
                        	</c:if>
                        	<span style="color:red">${coinUpdateStatus }</span>
                        </div>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty scoreAmount }">
            	<div class="info-list">
                	<div class="info-list-widght">
                        <div class="info-list-title">积分支付：</div>
                        <div class="info-list-desc">
                        	${ scoreAmount/100}元&nbsp;&nbsp;
                        	<c:if test="${scorePayStatus ==2}">
                        	<span style="color:red">[已支付]</span>
                        	</c:if>
                        	<c:if test="${scorePayStatus !=2}">
                        	<span >[未支付]</span>
                        	</c:if>
                        	<c:if test="${order.payStatus == 1 && scorePayStatus !=2 }">
                        		<input type="button" value="更改" id="scoreUpdatePayStatus"/>
                        	</c:if>
                        	<span style="color:red">${scoreUpdateStatus }</span>
                        </div>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty balanceAmount }">
            	<div class="info-list">
                	<div class="info-list-widght">
                        <div class="info-list-title">话费支付：</div>
                        <div class="info-list-desc">
                        	${ balanceAmount/100}元&nbsp;&nbsp;
                        	<c:if test="${balancePayStatus ==2}">
                        	<span style="color:red">[已支付]</span>
                        	</c:if>
                        	<c:if test="${balancePayStatus !=2}">
                        	<span >[未支付]</span>
                        	</c:if>
                        		<c:if test="${order.payStatus == 1 && balancePayStatus !=2 }">
                        		<input type="button" value="更改" id="balanceUpdatePayStatus"/>
                        	</c:if>
                        	<span style="color:red">${balanceUpdateStatus }</span>
                        </div>
                    </div>
                </div>
                </c:if>
                
                <c:if test="${empty cashAmount && empty scoreAmount && empty coinAmount && empty balanceAmount}">暂无支付信息</c:if>
             </div>
         </div>
         
        <!-- 日志信息 -->
        <div class="model-block">
        	<div class="block-head">
            	<h4>操作日志</h4>
            </div>
            <div class="block-body">
            
            	<table cellpadding="0" cellspacing="0">
            		<c:if test="${empty actOrderHistorys}">无日志</c:if>
            		<c:if test="${not empty actOrderHistorys}">
                	<tr>
                    	<th>时间</th>
                        <th>操作内容</th>
                    </tr>
                    </c:if>
                    <c:forEach items="${actOrderHistorys }" var="history">
                    <tr>
                    	<td class="center">
                    		<c:if test="${empty history.updateTime}"></c:if>
							<c:if test="${!empty history.updateTime}">
								<fmt:parseDate value="${history.updateTime}" pattern="yyyyMMddHHmmss" var="test"/>
								<fmt:formatDate value="${test}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</c:if>
                    	</td>
                        <td class="center">${history.updateDescription}
                        <c:if test="${ empty history.updateDescription}">
                        --------
                        </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
        <div class="form">
            <div class="fields">
                <div class="buttons" align="center">
                    <a href="javascript:history.back();" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>


</body>
</html>