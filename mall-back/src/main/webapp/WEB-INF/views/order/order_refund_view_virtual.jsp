<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
	    <ht:head/>
	    <script type="text/javascript">
	    $().ready(function() {
			//获取来源地址
	 		var url = document.referrer;
	 		$("#backUrl").val(url);
	 		var id='${orderRefund.id}';
	 		var orderId='${orderRefund.orderId}';
	 		
	 		//退码按钮事件
	 		$("#edit").click(function(){
	 			location="edit?orderId="+orderId+"&refundId="+id+"&preUrl="+'${preUrl}';
	 		});
	 		
	 		$("#updateRefundStatus").click(function(){
				selectCodeStockCallBack(function(updateTime,updateUser,reasonId,reasonName){
					var orderId = ${orderRefund.orderId};
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
				var orderId = ${orderRefund.orderId };
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
			    <form:form method="post" id="fm"  commandName="orderRefund" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
			        <div class="form">
			        	<form:hidden path="id"/>
			            <form:hidden path="orderId"/>
			            <input type="hidden" name="itemMode" value="${itemMode }"/>
			            <input type="hidden" name="actOrderGoodsId" value="${itemId }"/>
			            <input type="hidden" name="goodsId" value="${goodsId }"/>
			            <input type="hidden" id="preUrl" name="preUrl" value="${preUrl }"/>
			            <input type="hidden" id="backUrl" name="backUrl" />
			            <div class="fields">
			                <div class="field">
			                    <div class="label noinput">订单编号：</div>
			                    <div class="input">${orderRefund.orderId}</div>
			                </div>
			                <div class="field">
			                    <div class="label noinput">退款单号：</div>
			                    <div class="input">${orderRefund.id }</div>
			                </div>
			                <div class="field">
			                    <div class="label noinput">订单价格：</div>
			                    <div class="input">
			                    <c:choose>
				                    <c:when test="${not empty orderSum}">
				                    ${orderSum}元
				                    </c:when>
				                    <c:otherwise>0元</c:otherwise>
			                    </c:choose>
								</div>
			                </div>
			                <div class="field">
			                    <div class="label noinput">已退金额：</div>
			                    <div class="input">
			                    <c:choose>
				                    <c:when test="${not empty oldRefundAmount}">${oldRefundAmount}</c:when>
				                    <c:otherwise>0</c:otherwise>
			                    </c:choose>元			                    	
			                    </div>
			                </div>
			                <div class="field">
			                    <div class="label noinput">购买商品：</div>
			                    <div class="input">
			                    ${goodsName } &nbsp;&nbsp;&nbsp;&nbsp;*${count }
			                    </div>
			                </div>
			                <div class="field">
			                    <div class="label">
			                    	<label style="color: red;">验证码：</label>			                    
			                    </div>
								<div class="select">
									<c:choose>
									<c:when test="${not empty codes}">
									<table class="datalist fixwidth">
										<tr>
											<th nowrap="nowrap" width="30">选择</th>
											<th nowrap="nowrap" width="50">序号</th>
											<th nowrap="nowrap" width="100">码编号</th>
											<th nowrap="nowrap" width="100">状态</th>
											<th nowrap="nowrap" width="150">有效期至</th>
							            </tr>
										<c:forEach items="${codes}" var="code" varStatus="status">
							            <tr>
							            	<td nowrap="nowrap">
							                	<input type="checkbox" <c:if test="${code.codeStatus ==1 || code.codeStatus ==3 || code.codeStatus ==4}">disabled</c:if> name="actOrderGoodsCheck" value="${code.orderId}"  />
							                </td>
							                <td nowrap="nowrap" >
							                	${status.index+1}
							                </td>
							                <td nowrap="nowrap" >
							                	${code.orderId}
							                </td>
							                <td nowrap="nowrap" >
							                	${code.statusName}
							                </td>
							                <td nowrap="nowrap" >
							                	<ct:time source="${code.expireDate}"/>
							                </td>
							            </tr>
							            </c:forEach>
									</table>
									</c:when>
									<c:otherwise>
									无验证码
									</c:otherwise>
									</c:choose>
								</div>
			                </div>
			                <div class="field">
			                    <div class="label noinput">拟退码数量：</div>
			                    <div class="input">
			                    <span id="codeCount" style="color: red;">${orderRefund.totalCode }</span>
			                    	&nbsp;&nbsp;&nbsp;&nbsp;(成功<span>${orderRefund.successCode }</span>个,失败<span id="countLimit">${orderRefund.totalCode-orderRefund.successCode }</span>个)
			                    </div>
			                </div>
			                <div class="field">
			                    <div class="label noinput">退款金额：</div>
			                    <div class="input">
			                   	 	现金:${orderRefund.cashFee/100 }元;
			                   	 	商城币:${orderRefund.coinFee/100 }元;
			                   	 	积分:${orderRefund.scoreFee/100 }元;
			                   	 	话费:${orderRefund.phoneFee/100 }元
			                    </div>
			                </div>
			                <div class="field">
			                    <div class="label noinput">
			                        <label for="reason" >退款理由:</label>
			                    </div>
			                    <div class="input">
			                    ${orderRefund.reason}
			                    </div>
			                </div>
			                <div class="info-list">
                    <div  class="info-list-title">
                  		<span style="font-weight: bold;"> 退款状态:</span>
                    </div>
                    <div class="info-list-desc" >
                    	<span style="color:red">${orderRefund.statusName} </span>
                   		<c:if test="${orderRefund.status == 7 }">
                    		<input type="button" value="更改" id="updateRefundStatus"/>
                   		 </c:if> 
                   		 <span style="color:red">${orderRefundException }</span>
                    </div>
                  
<%--                     <input  type="button"   id=" ${refund.orderId}" class="auditing"  value="更新"> --%>
                    
                </div>
			                <div class="buttons">
			                	<c:if test="${(orderRefund.status != 3) and (orderRefund.status != 5) and (orderRefund.status != 6) and (orderRefund.status != 7) and (orderRefund.refundType ==1)}">
			                	<a id="edit" class="btnAnchor">退码</a>
			                	</c:if>
<!-- 				                <a href="javascript:history.go(-1)" class="btnAnchor">返回</a> -->
			                    <a href="${preUrl }" class="btnAnchor">返回</a>
			                </div>
			            </div>
			        </div>
			    </form:form>
			</div>
		</div>
	</body>
</html>