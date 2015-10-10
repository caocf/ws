<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
	    <ht:head/>
	    <script type="text/javascript">
	    //选择退款商品
 	    function chooseRefundGoods(obj, count){
 	    	if($(obj).attr("checked")){
 	    		var actOrderGoodsId = $(obj).attr("value");
 	    		$("#backNumber_" + actOrderGoodsId).val(count);
 	    		$("#backNumber_" + actOrderGoodsId).attr("readonly",false);
 	    	}else{
 	    		var actOrderGoodsId = $(obj).attr("value");
 	    		$("#backNumber_" + actOrderGoodsId).val("");
	    		$("#backNumber_" + actOrderGoodsId).attr("readonly",true);
 	    	}
 	    }
	    
	    //校验商品退款数量
	    function getThisRefundBackNumber(goodsId, backNumberLimit){
	    	$("#backNumber_" + goodsId + "_advice").css("color","#A9A9A9");	    		 
	    	if(parseFloat(backNumberLimit) < parseFloat($("#backNumber_" + goodsId).val())){
	    		alert("已超过最多可退数量，请重新输入！");
	    		$("#backNumber_" + goodsId + "_advice").css("color","red");
	    		$("#backNumber_" + goodsId).focus();
	    		return null;
	    	}
	    }
	    //校验退款金额及商城币
	    function getThisRefundAmount(goodsId, cashLimit, coinLimit){
	    	$("#cash_" + goodsId + "_advice").css("color","#A9A9A9");
	    	$("#coin_" + goodsId + "_advice").css("color","#A9A9A9");
	    	var cashs = $("input[name='cash']");
	    	var coins = $("input[name='coin']");
		    var sum = 0;
		    var cashSum = 0;
		    var coinSum = 0;
		    if(parseFloat($("#cash_" + goodsId).val())<0){
		    	alert("退款金额不能为负数，请重新输入！");
		    	$("#cash_" + goodsId).val(parseFloat(cashLimit));
	    		return;
		    }
		    if(parseFloat($("#coin_" + goodsId).val())<0){
		    	alert("退款商城币不能为负数，请重新输入！");
		    	$("#coin_" + goodsId).val(parseFloat(coinLimit));
		    	return;
		    }
		    if(parseFloat(cashLimit) < parseFloat($("#cash_" + goodsId).val())){
	    		alert("已超过最高退款金额，请重新输入！");
	    		$("#cash_" + goodsId + "_advice").css("color","red");
	    		$("#cash_" + goodsId).focus();
	    		$("#cash_" + goodsId).val(parseFloat(cashLimit));
	    		return null;
		    }
		    if(parseFloat(coinLimit) < parseFloat($("#coin_" + goodsId).val())){
	    		alert("已超过最高退款商城币，请重新输入！");
	    		$("#coin_" + goodsId + "_advice").css("color","red");
	    		$("#coin_" + goodsId).focus();
	    		$("#coin_" + goodsId).val(parseFloat(coinLimit));
	    		return null;
		    }
	    	for(var i = 0; i < cashs.length; i++){
		    	var cash = cashs[i].value;
		    	if("" == cash){
		    		cash = 0;
		    	}
	    		cashSum += parseFloat(cash);
	    	}
	    	for(var i = 0; i < coins.length; i++){
		    	var coin = coins[i].value;
		    	if("" == coin){
		    		coin = 0;
		    	} 	
	    		coinSum += parseFloat(coin);
	    	}
	    	sum = cashSum + coinSum;
	    	$("#refundFee").val(sum);	
	    	$("#thisRefundAmount").text("现金：" + cashSum + "元;商城币：" + coinSum + "元");
	    }
	    $().ready(function() {
			//获取来源地址
	 		var url = document.referrer;
	 		$("#backUrl").val(url);
		});
	    function valiBackNumber(id,totalBackNumber){
	    	var val = $("#"+id).val();
	    	if(val > totalBackNumber){
	    		alert('该商品最多可退' + totalBackNumber +'个,请重新输入!');
	    		 $("#"+id).val(totalBackNumber);
	    	}
	    	if(val < 0 ){
	    		alert('不能输入负数！请重新输入');
	    		$("#"+id).val(totalBackNumber);
	    	}
	    	
	    }
	    function valicashs(totalCash){
	    	var val = $("#valitotalcash").val();
	    	if(val <0){
	    		alert('退款金额不能为负数');
	    		$("#valitotalcash").val(totalCash);
	    	}
	    	if(val > totalCash){
	    		alert('退款金额最多可退' + totalCash +'元，请重新输入!');
	    		$("#valitotalcash").val(totalCash);
	    	}
	    }
	    function valicoins(totalCoin){
	    	var val = $("#valitotalcoin").val();
	    	if(val <0){
	    		alert('退款金额不能为负数');
	    		$("#valitotalcoin").val(totalCoin);
	    	}
	    	if(val > totalCoin){
	    		alert('退款金额最多可退' + totalCoin +'元，请重新输入!');
	    		$("#valitotalcoin").val(totalCoin);
	    	}
	    }
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
			            <form:hidden path="orderId"/>
			            <form:hidden path="storeId"/>
			         	<input type="hidden" id="refundType" name="refundType" value="${refundType }"/>
			            <input type="hidden" id="backUrl" name="backUrl" />
			            <input type="hidden" name="cashLimit" value="${totalCash }"/>
			            <input type="hidden" name="coinLimit" value="${totalCoin }"/>
			            <div class="fields">
			                <div class="field">
			                    <div class="label noinput">订单编号：</div>
			                    <div class="input">${orderRefund.orderId}</div>
			                </div>
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
				                    ${orderSum}元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(包含物流费：<c:choose><c:when test="${not empty expressCost}">${expressCost}</c:when><c:otherwise>0</c:otherwise></c:choose>元)
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
			                    <div class="label">
			                    	<label style="color: red;">可退款商品：</label>			                    
			                    </div>
								<div class="select">
									<c:choose>
									<c:when test="${not empty actOrderGoodsList}">
									<table class="datalist fixwidth">
										<tr>
											<th nowrap="nowrap" width="30">选择</th> 
											<th nowrap="nowrap" style="border-right: hidden;" width="200">商品</th>
							                <th nowrap="nowrap" style="border-right: hidden;border-left: hidden;" width="100">购买数量</th>
							                <th nowrap="nowrap" style="border-right: hidden;" width="100">已退数量</th>
							                <th nowrap="nowrap" style="border-left: hidden;" width="100">退货数量</th>
							                <th nowrap="nowrap" style="border-left: hidden;" width="100"></th>
							            </tr>
										<c:forEach items="${actOrderGoodsList}" var="goods">
							            <tr>
 							            	<td nowrap="nowrap"> 
 							                	<input type="checkbox" name="actOrderGoodsCheck" value="${goods.id}" onclick="chooseRefundGoods(this, '${goods.count - goods.backNumber}')"   /> 
 							                	<input type="hidden" name="actOrderGoodsId" value="${goods.id}"/>
 							                	<input type="hidden" name="goodsId" value="${goods.goods_id}"/>
 							                </td> 
							                <td nowrap="nowrap" class="ellipsis" style="border-right: hidden;">
							                	${goods.goodsName}
							                </td>
							                <td nowrap="nowrap" style="border-right: hidden;border-left: hidden;">
							                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${goods.count}
							                </td>
							                <td nowrap="nowrap" style="border-right: hidden;">
							                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${goods.backNumber }
							                </td>
							                 <td nowrap="nowrap" style="border-right: hidden;">
							                	<input class="validate-number" type="text" id="backNumber_${goods.id}" readonly name="backNumber" onblur="valiBackNumber(id,${goods.count - goods.backNumber});"  style="width:60px;height:20px;" maxlength="9" />
							                </td>
											<td nowrap="nowrap" style="border-left: hidden;">
							                	<font id="backNumber_${goods.id}_advice" style="font-style: italic;color:red;"><br/>最多可退${goods.count - goods.backNumber}个</font>
											</td>
							            </tr>
							            </c:forEach>
									</table>
									</c:when>
									<c:otherwise>
									无订单商品
									</c:otherwise>
									</c:choose>
								</div>
			                </div>
			              	<input type="hidden" name="itemMode" value="${itemMode }"/>
			                <div class="field">
			                    <div class="label">
									<label  class="req">本次退款金额:</label>
								</div>
			                  	<br/>
			                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<label>现金:</label>
			                    &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input <c:if test="${totalCash ==0 }">readonly</c:if> value="${totalCash }" class="validate-number required" type="text" id="valitotalcash"  name="cashFee" onblur="valicashs(${totalCash });"   style="width:100px;height:20px;" maxlength="9" />&nbsp; &nbsp; &nbsp;&nbsp;
			                                最多退<span style="color:red;">${totalCash }</span>元
			                    <span class="error" id="advice-required-valitotalcash" style="display:none"></span>
			                 	<span class="error" id="advice-validate-number-valitotalcash" style="display:none"></span>
			                    <br/><br/>
			                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<label>商城币:</label>
			                   &nbsp; &nbsp; <input <c:if test="${totalCoin ==0 }">readonly</c:if> value="${totalCoin }" class="validate-number required" type="text" id="valitotalcoin"  name="coinFee"  onblur="valicoins(${totalCoin })" style="width:100px;height:20px;" maxlength="9" />&nbsp; &nbsp; &nbsp;&nbsp;
			                               最多退<span style="color:red;">${totalCoin }</span>元
			                   <span class="error" id="advice-required-valitotalcoin" style="display:none"></span>
			                   <span class="error" id="advice-validate-number-valitotalcoin" style="display:none"></span>
			                    <br/><br/>
			                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<label>积分:</label>
			                   &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="validate-number required" type="text"  readonly name="scoreFee"  value="${totalScore }" style="width:100px;height:20px;" maxlength="9" />&nbsp; &nbsp; &nbsp;&nbsp;
			                               最多退<span style="color:red;">${totalScore }</span>元
			                   <br/><br/>         
			                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<label>话费:</label>
			                   &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="validate-number required" type="text"  readonly name="phoneFee"  value="${totalPhone }" style="width:100px;height:20px;" maxlength="9" />&nbsp; &nbsp; &nbsp;&nbsp;
			                               最多退<span style="color:red;">${totalPhone }</span>元
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