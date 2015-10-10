<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<head>
<ht:head />
<script type="text/javascript">
	$().ready(function() {

		
		$(".view").click(function() {
			var id = $(this).attr("id");
			showDialog("拉手商品信息", "${ctx}/item/vitual/lsView?id="+id,function(doc){
			},{"Width":768,"Height":600,"ShowButtonRow" : true});			
	});
		

	});
</script>


</head>
<body>
	<br />

	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div class="title">
				<h5>商品信息查看</h5>
				<c:if test="${lsFlag == 1 }">
							<a href="#this" id="${sale.id}" class="view">拉手商品信息查看</a>
				</c:if>
			</div>
			<!-- end box / title -->
			<div class="form">
				<div class="fields">
				<h3>1.基本信息</h3>	
					
					<div class="field">
						<div class="label">
							<label for="shopId">所属商户：</label>
						</div>
						<div class="input">${store.name }&nbsp;</div>
					</div>

					<div class="field" id="typeIdDiv">
						<div class="label">
							<label for="typeId">商品分类：</label>
						</div>
						<div class="input">${sale.typeName }&nbsp;</div>
					</div>

				
						<div class="field">
							<div class="label">
								<label for="postArea" >是否营销商品:</label>
							</div>
							<div class="input">${iseckillMap[sale.iseckill]}&nbsp;
							</div>
						</div>
						
						<c:if test="${sale.iseckill eq 1 }">
							<div class="field">
							<div class="label">
								<label for="" >秒杀价:</label>
							</div>
							<div class="input">${sale.iseckillPrice}&nbsp;
							</div>
						</div>
						</c:if>
					
				
					<div class="field">
						<div class="label">
							<label for="name">商品名称：</label>
						</div>
						<div class="input">
							<!-- small medium large -->
							${sale.name}&nbsp;
						</div>
					</div>
					
					<div class="field">
							<div class="label">
								<label for="postArea" >是否发码:</label>
							</div>
							<div class="input"><c:if test="${sale.sendCodeMode != 0 }">是</c:if><c:if test="${sale.sendCodeMode == 0 }">否</c:if>&nbsp;
							</div>
					</div>
					
					<div <c:if test="${sale.sendCodeMode == 0 }">style="display:none"</c:if>>
						<div class="field">
							<div class="label">
								<label for="postArea" >发码渠道:</label>
							</div>
							<div class="input">
								${sendCodeChannelName }
								&nbsp;
							</div>
						</div>
						<div <c:if test="${sale.sendCodeChannel != 1 || sale.sendCodeMode == 0 }">style="display:none"</c:if> >	
							<div class="field" >
								<div class="label">
									<label for="postArea" >发送方式:</label>
								</div>
								<div class="input">
									<c:if test="${sale.verifyCodeType == 0 }">短信</c:if>
									<c:if test="${sale.verifyCodeType == 1 }">彩信</c:if>
									<c:if test="${sale.verifyCodeType == 2 }">短信，彩信</c:if>
									&nbsp;
								</div>
							</div>
							<div class="field" >
								<div class="label">
									<label for="postArea" >短信附加内容:</label>
								</div>
								<div class="input">
									${sale.verifyCodeDesc }
									&nbsp;
								</div>
							</div>
						</div>
						<div class="field" <c:if test="${sale.sendCodeSrc == null }">style="display:none"</c:if>>
							<div class="label">
								<label for="postArea" >所属码库:</label>
							</div>
							<div class="input">
								${sendCodeSrcName }
								&nbsp;
							</div>
						</div>
					
					</div>
					
					
					
					
					<div class="field" <c:if test="${sale.verifyStartTime eq null}">style="display:none" </c:if>>
							<div class="label">
								<label for="verifyStartTime"  >验证开始时间:</label>
							</div>
							<div class="input">
								<ct:time source="${sale.verifyStartTime}" />&nbsp;
							</div>
						</div>
						<div class="field" <c:if test="${sale.verifyStopTime eq null}">style="display:none" </c:if>>
							<div class="label">
								<label for="verifyStopTime"  >验证结束时间:</label>
							</div>
							<div class="input">
								<ct:time source="${sale.verifyStopTime}" />&nbsp;
							</div>
						</div>
						<div class="field" <c:if test="${sale.verifyDay eq null}">style="display:none" </c:if>>
							<div class="label">
								<label for="verifyDay"  >验证天数:</label>
							</div>
							<div class="input">${sale.verifyDay}&nbsp;</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="verifyShopName"   >验证门店:</label>
							</div>
							<div class="input">${sale.verifyShopName}&nbsp;</div>
						</div>

					
					
					<div class="field">
						<div class="label">
							<label for="brand">品牌：</label>
						</div>
						<div class="input">
							<!-- small medium large -->
							${sale.brand }&nbsp;
						</div>
					</div>




					<div class="field">
						<div class="label label-textarea">
							<label for="tag">标签（用";"隔开）：</label>
						</div>
						<div class="input">${sale.tag }&nbsp;</div>

					</div>

					<div class="field">
						<div class="label">
							<label for="marketContent">营销语：</label>
						</div>
						<div class="input">${sale.marketContent }&nbsp;</div>
					</div>

					<div class="field">
						<div class="label">
							<label for="warmPrompt">温馨提示：</label>
						</div>
						<div class="input">${sale.warmPrompt }&nbsp;</div>
					</div>
					<div class="field">
						<div class="label label-textarea">
							<label for="remark">商品介绍：</label>
						</div>
					
						<div class="input">${sale.remark }&nbsp;</div>
						
					</div>
				</div>
			</div>





			<div class="form">
				<div class="fields">


<div class="field"  id="sizeDivId" >
        	  <div class="label label-textarea">
                        <label >商品规格:</label>
                    </div>
        	  <div class="inputs" id="parameterId">
					<c:forEach items="${typeItemParamList}" var="item"
						varStatus="index">

						<c:if test="${item.paramType==1}">
							<div class="field">
								<div class="label">
									<label for="name"> ${item.paramKey}：&nbsp;</label>
								</div>

								<div class="radios">
									<div class="radio">
										<c:forEach items="${item.paramValueList}" var="str"
											varStatus="index1">

											<c:if test="${item.userParamValue==str}">${str}</c:if>

										</c:forEach>
									</div>
								</div>
							</div>
						</c:if>

						<c:if test="${item.paramType==2}">
							<div class="field">
								<div class="label">
									<label for="name"> ${item.paramKey}：&nbsp;</label>
								</div>

								<div class="checkboxes">
									<div class="checkboxe">
										<c:forEach items="${item.paramValueList}" var="str"
											varStatus="index1">

											<c:if test="${fn:contains(item.userParamValue,str)}"> ${str } ,&nbsp;</c:if>

										</c:forEach>
									</div>
								</div>
							</div>
						</c:if>

						<c:if test="${item.paramType==3}">
							<div class="field">
								<div class="label">
									<label for="name"> ${item.paramKey}：&nbsp;</label>
								</div>

								<div class="input">${item.userParamValue}&nbsp;</div>
							</div>
						</c:if>

					</c:forEach>
					<div class="field" style="display:none">
						<div class="label">
							<label for="name">自定义参数</label>
						</div>
						<div class="input">
							<div class="dl" id="divAddpara">
								<c:forEach items="${paralist }" var="item">
									<div class='dl'>
										<br /> <span style="float:left">参数名称：${item.paramKey }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span style="float:left">参数值:${item.paramValue } </span>
									</div>

								</c:forEach>
							</div>
						</div>
					</div>
					</div>
					
<!-- 
					<div class="field">
						<div class="label">
							<label for="name">商品属性</label>
						</div>
						<div class="input">
							<c:forEach items="${itemProperty}" var="itemP" varStatus="index">
								<div class="dl" id="fath">
									<div class="dt class="req">${itemP.propertyName}：</div>
									<div class="dd">
										${itemP.content}
										<div>
											<c:if test="${! empty itemP.imgPath}">
												<ht:image id="${itemP.itemId}" type="property" webpath="${itemP.imgPath}"/>
											</c:if>
										</div>
									</div>

								</div>
							</c:forEach>
						</div>
					</div>
 -->



				</div>
			</div>

			<div class="form">
				<div class="fields">
<h3>2.销售信息</h3>	

					<div class="field">
						<div class="label">
							<label for="saleStartTime" >销售开始时间:</label>
						</div>
						<div class="input">
							<ct:time source="${sale.saleStartTime}" />&nbsp;
						</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="saleStopTime" >销售结束时间:</label>
						</div>
						<div class="input">
							<ct:time source="${sale.saleStopTime}" />&nbsp;
						</div>
					</div>



					<div class="field">
						<div class="label">
							<label for="saleAreaName" >显示区域:</label>
						</div>
						<div class="input">${sale.saleAreaName}&nbsp;</div>
					</div>


					<div class="field">
						<div class="label label-checkbox">
							<label>购买用户限制:</label>
						</div>
						<div class="checkboxes">
							<div class="checkbox">
								<c:if test="${fn:contains(sale.buyLimit,'0')}">会员级别限制</c:if>
								&nbsp;&nbsp;&nbsp;
								<c:if test="${fn:contains(sale.buyLimit,'1')}">购买用户地市限制</c:if>
								&nbsp;&nbsp;&nbsp;
								<c:if test="${fn:contains(sale.buyLimit,'2')}">单个用户购买数量</c:if>
							</div>
						</div>
					</div>


					<div class="field" id="userLimit">
						<div class="label label-checkbox">
							<label>会员级别限制:</label>
						</div>
						<div class="checkboxes">
							<div class="checkbox">
							<c:forEach items="${pricetypeList }" var="item">
								<c:if test="${fn:contains(sale.userLimitCode,item.priceTypeCode)}"> ${item.priceType } </c:if>
							</c:forEach>
							</div>
						</div>
					</div>


					<div class="field" id="areaLimit"
						<c:if test="${!fn:contains(sale.buyLimit,'1')}"> style="display: none;" </c:if>>
						<div class="label">
							<label for="areaLimitName" >购买用户地市限制:</label>
						</div>
						<div class="input">${sale.areaLimitName}&nbsp;</div>
					</div>

					<div class="field" id="numLimit"
						<c:if test="${!fn:contains(sale.buyLimit,'2')}"> </c:if>>
						<div class="label">
							<label for="userPerBuyNum">单个用户购买数量:</label>
						</div>
						<div class="input">${sale.userPerBuyNum }&nbsp;</div>
					</div>

					<div class="field">
						<div class="label">
							<label for="stockNum" >商品库存:</label>
						</div>
						<div class="input">
							<c:choose>
								<c:when test="${storeNum eq initStoreNum}">不限</c:when>
								<c:otherwise>${sale.stockNum}</c:otherwise>
							</c:choose>&nbsp;
						</div>
					</div>
					<div class="field">
						<div class="label label-radio">
							<label>选择费率:</label>
						</div>

						<div class="select">
							<c:forEach items="${feeList }" var="item">
								<c:if test="${item.id==sale.feeType }">${item.name }</c:if>
							</c:forEach>&nbsp;
						</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="marketPrice" >市场价:</label>
						</div>
						<div class="input">${sale.marketPrice} &nbsp;</div>
					</div>

					<div class="field">
						<div class="label">
							<label for="marketPrice" >商城价:</label>
						</div>
						<div class="input">${sale.shopPrice} &nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="settlePrice">结算价:</label>
						</div>
						<div class="input">${sale.settlePrice} &nbsp;</div>
					</div>
					<div class="field" <c:if test="${empty priceList}">style="display:none"</c:if>>
						<div class="label">
							<label for="payPrice" >支付价格配置:</label>
						</div>
						<div class="input" id="priceId" >
							<table border="0" style="width:100px"  id="TbData" name="TbData" cellpadding="0" cellspacing="0">
								<c:forEach items="${priceList}" var="item">
									<tr>
										<td nowrap="nowrap" width="80">
										    <c:choose>
											   <c:when test="${empty  item.priceType }">
										         ${map[item.priceTypeCode] }:
										       </c:when>
												<c:otherwise>
												          ${item.priceType }价:
											    </c:otherwise>
										    </c:choose>
										</td>
										<td nowrap="nowrap" width="80">${item.price }元</td>
									</tr>
								</c:forEach>

							</table>
							&nbsp;
						</div>
					</div>
					
												<div class="field">
						<div class="label">
							<label for="cashIdgoods">支付方式：</label>
						</div>
						<c:if test="${itemSalePayment == null || itemSalePayment.payType == 0}" var="payTypeFg">
							<div class="input">单一支付：</div>
						</c:if>
						<c:if test="${!payTypeFg }">
							<div class="input">组合支付：</div>
						</c:if>
						<c:if test="${sale.cashIdgoods eq 1}">
							<div class="input">${cashIdgoodsMap[sale.cashIdgoods]}&nbsp;</div>
						</c:if>
						<c:if test="${sale.coinIdgoods eq 1}">
							<div class="input">${coinIdgoodsMap[sale.coinIdgoods]}&nbsp;</div>
						</c:if>
						<c:if test="${sale.scoreIdgoods eq 1}">
							<div class="input">${scoreIdgoodsMap[sale.scoreIdgoods]}&nbsp;</div>
						</c:if>
						<c:if test="${itemSalePayment.billPay eq 1}">
							<br />
							<div class="input">支持话费支付</div>
						</c:if>
					</div>

					
					<c:if test="${!empty auditStepList}">
					 <div class="field">
							<div class="label">
								<label for="remarkL" >审核意见:</label>
							</div>
							<div class="input">
								<c:forEach items="${auditStepList}" var="item">
			                         <label >${item.remarkL}</label></br>
                       			</c:forEach>
							</div>
					</div>
                	</c:if>
                 <div class="field">
							<div class="label">
								<label for=statusName >审核状态:</label>
							</div>
							<div class="input">
								${sale.statusName }&nbsp;
							</div>
				</div>
				
				
			
				
				
				
				
							
					
				
					<div class="buttons">
						
						<input type="button" class="common_btn" onclick="history.back();" value="返回" />
					</div>
				</div>
			</div>
		</div>
		<!-- end forms -->
	</div>
</body>
</html>