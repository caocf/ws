<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
	    <ht:head/>
	    <script type="text/javascript">
	    //校验退款金额及商城币
	    function getThisRefundAmount(cashLimit, coinLimit){
		    if(parseFloat($("#cash").val())<0){
		    	alert("退款金额不能为负数，请重新输入！");
		    	$("#cash").val(parseFloat(cashLimit));
	    		return;
		    }
		    if(parseFloat($("#coin").val())<0){
		    	alert("退款商城币不能为负数，请重新输入！");
		    	$("#coin").val(parseFloat(coinLimit));
		    	return;
		    }
		    if(parseFloat(cashLimit) < parseFloat($("#cash").val())){
	    		alert("已超过最高退款金额，请重新输入！");
	    		$("#cash").focus();
	    		$("#cash").val(parseFloat(cashLimit));
	    		return ;
		    }
		    if(parseFloat(coinLimit) < parseFloat($("#coin").val())){
	    		alert("已超过最高退款商城币，请重新输入！");
	    		$("#coin").focus();
	    		$("#coin").val(parseFloat(coinLimit));
	    		return ;
		    }
	    }
	    $().ready(function() {
			//获取来源地址
	 		var url = document.referrer;
	 		$("#backUrl").val(url);
	 		
	 		//计算选择商品数量
	 		$("input[name='actOrderGoodsCheck']").click(function(){
	 			var goods=$("input[name='actOrderGoodsCheck']:checked");
	 			$("#backNumber").val(goods.length);
	 			$("#totalCode").val(goods.length);
	 			$("#codeCount").text(goods.length);
	 		});
		});
	    </script>
	</head>
	<body>
		<div id="content">
			<div class="box">
			    <div class="title">
			        <h5>订单统计列表</h5>
			    </div>
			    <form:form method="post" id="fm"  commandName="orderRefund" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
			        <div class="form">
			            <form:hidden path="orderId"/>
			            <form:hidden path="storeId"/>
			            <input type="hidden" id="refundType" name="refundType" value="${refundType }"/>
			            <input type="hidden" name="itemMode" value="${itemMode }"/>
			            <input type="hidden" name="actOrderGoodsId" value="${itemId }"/>
			            <input type="hidden" name="goodsId" value="${goodsId }"/>
			            <input type="hidden" id="backNumber" name="backNumber" />
			            <input type="hidden" id="totalCode" name="totalCode" />
			            <input type="hidden" id="backUrl" name="backUrl" />
			            <div class="fields">
			                <div class="field">
			                    <div class="label noinput">订单编号：</div>
			                    <div class="input">${order.id}</div>
			                </div>
			                <c:if test="${not empty store }">
			                <div class="field">
			                    <div class="label noinput">商户名称：</div>
			                    <div class="input">${store.name}</div>
			                </div>
			                </c:if>
			                <div class="field">
			                    <div class="label noinput">退款单号：</div>
			                    <div class="input">自动生成</div>
			                </div>
			                <div class="field">
			                    <div class="label noinput">订单类型：</div>
			                    <div class="input">
			                    <c:if test="${order.actType ==20 }">短信购</c:if>
			                    <c:if test="${order.actType ==40 }">普通购买</c:if>
			                    </div>
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
<!-- 											<th nowrap="nowrap" width="100">商品名称</th> -->
											<th nowrap="nowrap" width="100">码编号</th>
											<th nowrap="nowrap" width="100">状态</th>
											<th nowrap="nowrap" width="150">有效期至</th>
<!-- 											<th nowrap="nowrap" width="150">商品名称</th> -->
<!-- 							                <th nowrap="nowrap" width="100">购买数量</th> -->
<!-- 							                <th nowrap="nowrap" width="100">已退数量</th> -->
<!-- 							                <th nowrap="nowrap" width="100">现金</th> -->
<!-- 							                <th nowrap="nowrap" width="100">商城币</th> -->
							            </tr>
										<c:forEach items="${codes}" var="code" varStatus="status">
							            <tr>
							            	<td nowrap="nowrap">
							                	<input type="checkbox" <c:if test="${code.codeStatus ==1 || code.codeStatus ==3 || code.codeStatus ==4}">disabled</c:if> name="actOrderGoodsCheck" value="${code.orderId}"  />
							                </td>
							                <td nowrap="nowrap" >
							                	${status.index+1}
							                </td>
<!-- 							                <td nowrap="nowrap" class="ellipsis"> -->
<%-- 							                	${code.itemName} --%>
<!-- 							                </td> -->
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
			                    <div class="input"><span id="codeCount" style="color: red;">0</span></div>
			                </div>
			                <div class="field">
			                    <div class="label">
									<label  class="req">本次退款金额:</label>
								</div>
								<br/>
			                    <div>
			                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
			                    <label>现金：</label>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                <input <c:if test="${maxCash ==0 }">readonly</c:if> value="${maxCash }" id="cash" onblur="getThisRefundAmount('${maxCash }', '${maxCoin }')"  class="validate-number required" name="cashFee" type="text" style="width:100px;height:20px;"/>&nbsp; &nbsp; &nbsp;&nbsp;
				                          最多退<span style="color: red;">${maxCash}</span>元
				                <span id="advice-required-cash" class="error" style="display:none"></span>
				                <span id="advice-validate-number-cash" class="error" style="display:none"></span>
			                    </div>
			                    <br/>
			                    <div>
			                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
			                    <label>商城币：</label>&nbsp; &nbsp;&nbsp;
				                <input <c:if test="${maxCoin ==0 }">readonly</c:if> value="${maxCoin }" id="coin" onblur="getThisRefundAmount('${maxCash }', '${maxCoin }')"  class="validate-number required" name="coinFee" type="text" style="width:100px;height:20px;"/>&nbsp; &nbsp; &nbsp;&nbsp;
				                          最多退<span style="color: red;">${maxCoin}</span>元
				                <span id="advice-required-coin" class="error" style="display:none"></span>
				                <span id="advice-validate-number-coin" class="error" style="display:none"></span>
			                    </div>
			                    <br/>
			                    <div>
			                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
			                    <label>积分：</label>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                <input value="${maxScore }"   readonly class="validate-number required" name="scoreFee" type="text" style="width:100px;height:20px;"/>&nbsp; &nbsp; &nbsp;&nbsp;
				                          最多退<span style="color: red;">${maxScore}</span>元
				                </div>
			                    <br/>
			                    <div>
			                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
			                    <label>话费：</label>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                <input value="${maxPhone }"   readonly class="validate-number required" name="phoneFee" type="text" style="width:100px;height:20px;"/>&nbsp; &nbsp; &nbsp;&nbsp;
				                          最多退<span style="color: red;">${maxPhone}</span>元
				                </div>
			                </div>
			                <div class="field">
			                    <div class="label label-textarea">
			                        <label id="refundReason" class="req" for="reason">退款理由：</label>
			                    </div>
			                    <div class="input">
			                        <textarea id="reason" class="required" name="reason" cols="50" rows="8" placeholder="输入退款理由..." class="max-length-150">${sysProperty.content}</textarea>
			                        <span class="error" id="advice-required-reason" style="display:none"></span>
			                        <span class="error" id="advice-max-length-reason" style="display:none"></span>
			                        <span class="error" id="advice-server-reason" style="display:none"></span>
			                    </div>
			                </div>
			                <div class="buttons">
			                    <div class="highlight">
			                        <input type="submit" name="submit.highlight" value="提交" />
			                    </div>
			                    <input type="reset" name="reset" value="重置" />
			                    <a href="javascript:history.go(-1)" class="btnAnchor">返回</a>
			                </div>
			            </div>
			        </div>
			    </form:form>
			</div>
		</div>
	</body>
</html>