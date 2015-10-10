<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />


</head>
<body>
	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div>
				<h5>
					发布商品查看
					<h5>
			</div>
			<!-- end box / title -->
				<div class="form">
					<div class="fields">

						<div class="field">
							<div class="label">
								<label for="name" >商品名称：</label>
							</div>
							<div class="input">
								${itemSale.name }&nbsp;
							</div>
						</div>
						
						
						
						
						<div class="field">
							<div class="label label-radio">
								<label>发码方式:</label>
							</div>
							<div class="radios">
								<div class="radio">
									 <c:if test="${itemSale.sendCodeMode eq 0 }">不发码</c:if> 
									 <c:if test="${itemSale.sendCodeMode eq 1 }">按照订单发码</c:if>
									 <c:if test="${itemSale.sendCodeMode eq 2 }">按照商品个数发码</c:if>&nbsp;
								</div>
								<span class="error" id="advice-validate-one-required-radioex1"
									style="display:none"></span>
							</div>
						</div>
						
						<!-- 需要发码此div显示begin -->
					<div id="selSendCode" <c:if test="${itemSale.sendCodeMode eq 0 }">style="display: none;"</c:if> >
					<div class="field">
							<div class="label label-radio">
								<label>发码类型设置:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<c:if test="${itemSale.verifyCodeType eq 1 }">一维码</c:if>
									  <c:if test="${itemSale.verifyCodeType eq 2 }">二维码</c:if> &nbsp;
								</div>
								<span class="error" id="advice-validate-one-required-radioex1"
									style="display:none"></span>
							</div>
						</div>
						<div class="field">
							<div class="label label-radio">
								<label>发码方设置:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<c:if test="${itemSale.sendCodeChannel eq 0 }">平台自己</c:if> 
									<c:if test="${itemSale.sendCodeChannel eq 1 }">方正码平台</c:if>
									<c:if test="${itemSale.sendCodeChannel eq 2 }">第三方应用</c:if>&nbsp;
								</div>
								<span class="error" id="advice-validate-one-required-sendCodeChannel"
									style="display:none"></span>
							</div>
						</div>
						<!-- 选择第三方应用此div显示begin -->
						<div class="field" id="sendCodeSrcId" <c:if test="${itemSale.sendCodeChannel ne 2 }">style="display: none;"</c:if> >
							<div class="label">
								<label for="select">制码渠道:${itemSale.sendCodeSrc}</label>
							</div>
							<div class="select">
								 <c:if test="${itemSale.sendCodeSrc eq 10 }"> 85度C </c:if>
								<c:if test="${itemSale.sendCodeSrc eq 11 }"> 鲜芋仙 </c:if>&nbsp;
							</div>
						</div>
						<!-- 选择第三方应用此div显示 end-->
						<div class="field">
							<div class="label">
								<label for="verifyStartTime" >验证有效开始时间:</label>
							</div>
							<div class="input">
								<ct:time source="${itemSale.verifyStartTime}" />&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="verifyStopTime" >验证有效结束时间:</label>
							</div>
							<div class="input">
								<ct:time source="${itemSale.verifyStopTime}" />&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="verifyStopTime" >验证天数:</label>
							</div>
							<div class="input">
								${itemSale.verifyDay}"&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="verifyShopName" >验证门店:</label>
							</div>
							<div class="input">${itemSale.verifyShopName}&nbsp;
							</div>
						</div>
						
						</div>
					<!-- 需要发码此div显示end -->

						<!-- 不需要发码此div显示 begin -->
                        <div id="selLogistics" <c:if test="${itemSale.sendCodeMode ne 0}">style="display: none;"</c:if> >

						<!-- 不发码显示是否需要物流 -->
						<div class="field">
							<div class="label label-radio">
								<label>是否需要物流:</label>
							</div>
							<div class="radios">
								<div class="radio">
									 <c:if test="${itemSale.postFlag eq 0}">不需要物流配送</c:if>
									 <c:if test="${itemSale.postFlag eq 1}">需要物流配送</c:if>&nbsp;
								</div>
								<span class="error" id="advice-validate-one-required-radioex1"
									style="display:none"></span>
							</div>
						</div>
						
						<!-- 需要物流配送此div显示 begin-->
						<div id="isPost" <c:if test="${itemSale.postFlag eq 0}">style="display: none;"</c:if>>
						<div class="field">
							<div class="label">
								<label for="postArea" >配送区域选择:</label>
							</div>
							<div class="input">${itemSale.postArea}&nbsp;
							</div>
						</div>
						
						
					
						
						
						<div class="field">	
							<div class="label">
								<label for="logisticsFee" >物流运费:</label>
							</div>
							<div class="input">${itemSale.logisticsFee}
							<c:if test="${itemSale.logisticsType==0 }">&nbsp;&nbsp;&nbsp;√不累计</c:if>
                           <c:if test="${itemSale.logisticsType==1 }">&nbsp;&nbsp;&nbsp;√累计</c:if>
							</div>
							
						</div>
						
						</div>
						
						
						<!-- 需要物流配送此div显示 end -->
						</div>
						<!-- 不需要发码此div显示  end-->
						
						
						
						<div class="field">
							<div class="label">
								<label for="saleStartTime" >销售有效开始时间:</label>
							</div>
							<div class="input">
								<ct:time source="${itemSale.saleStartTime}" />&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="saleStopTime" >销售有效结束时间:</label>
							</div>
							<div class="input">
								<ct:time source="${itemSale.saleStopTime}" />&nbsp;
							</div>
						</div>
						
						
						
						<div class="field">
							<div class="label">
								<label for="saleAreaName" >销售区域:</label>
							</div>
							<div class="input">${itemSale.saleAreaName}&nbsp;
							</div>
						</div>
						
						
						
						
						<div class="field">
							<div class="label">
								<label for="saleShopName" >销售门店:</label>
							</div>
							<div class="input">${itemSale.saleShopName}&nbsp;
							</div>
						</div>
						
						
				<div class="field">
                    <div class="label label-checkbox">
                        <label>购买用户限制:</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox">
                            <c:if test="${fn:contains(itemSale.buyLimit,'0')}"> 会员级别限制 </c:if>&nbsp;&nbsp;
							 <c:if test="${fn:contains(itemSale.buyLimit,'1')}"> 购买用户地市限制</c:if>&nbsp;&nbsp;
							<c:if test="${fn:contains(itemSale.buyLimit,'2')}"> 单个用户购买数量 </c:if>&nbsp;
                        </div>
                        <span class="error" id="advice-validate-one-required-buyLimit" style="display:none"></span>
                    </div>
                </div>
						
						
				<div class="field" id="userLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'0')}"> style="display: none;" </c:if>>
                    <div class="label label-checkbox">
                        <label>会员级别限制:</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox">
                             <c:if test="${fn:contains(itemSale.userLimitCode,'0')}"> 金会员 </c:if> &nbsp;&nbsp;
							 <c:if test="${fn:contains(itemSale.userLimitCode,'1')}"> 铜会员 </c:if> &nbsp;&nbsp;
							<c:if test="${fn:contains(itemSale.userLimitCode,'2')}"> 银会员 </c:if>  &nbsp;
                        </div>
                        <span class="error" id="advice-validate-one-required-userLimitCode" style="display:none"></span>
                    </div>
                </div>
						
						
						<div class="field" id="areaLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'1')}"> style="display: none;" </c:if>>
							<div class="label">
								<label for="areaLimitName" >购买用户地市限制:</label>
							</div>
							<div class="input">${itemSale.areaLimitName}&nbsp;
							</div>
						</div>
						
						<div class="field" id="numLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'2')}"> style="display: none;" </c:if>>
							<div class="label">
								<label for="userPerBuyNum" >单个用户购买数量:</label>
							</div>
							<div class="input">${itemSale.userPerBuyNum}&nbsp;
							</div>
						</div>
						
						<div class="field" >
							<div class="label">
								<label for="stockNum" >商品库存:</label>
							</div>
							<div class="input">
							<c:choose>
               				 <c:when test="${storeNum eq initStoreNum}">不限</c:when>
               				 <c:otherwise>${itemSale.stockNum}</c:otherwise>
                             </c:choose>
							</div>
						</div>
						<div class="field" >
							<div class="label">
								<label for="marketPrice" >市场价:</label>
							</div>
							<div class="input">${itemSale.marketPrice} &nbsp; 
							</div>
						</div>
						<div class="field" >
							<div class="label">
								<label for="marketPrice" >商城价:</label>
							</div>
							<div class="input">${itemSale.shopPrice} &nbsp; 
							</div>
						</div>
						<div class="field" >
							<div class="label">
								<label for="settlePrice" >结算价:</label>
							</div>
							<div class="input">${itemSale.settlePrice} &nbsp; 
							</div>
						</div>
						<div class="field" >
							<div class="label">
								<label for="stockNum" >支付价格配置:</label>
							</div>
							<div class="input" id="priceId">
								 <table   id="TbData" name="TbData"  cellpadding="0" cellspacing="0" >
								  <c:forEach items="${priceList}" var="item">
							     <tr>
							     <td align='right'>
							     <c:choose>
							       <c:when test="${empty  item.priceType }">
							         ${map[item.priceTypeCode] }:
							       </c:when>
							       <c:otherwise>
							          ${item.priceType }:
							       </c:otherwise>
							     </c:choose>
							     </td>
							      <td>
							       ${item.price }元
								  </td>
								</tr>
								 </c:forEach>
								
							  </table>&nbsp;
							</div>
						</div>
						<div class="buttons">
							<div class="highlight">
						<c:if test="${itemSale.status==2 }">
							<ct:display model="audit_list" btn="audit_btn">
								<a class="btn_blue" id="serche" name="serche" href="${ctx}/item/saleitem/auditing/${itemSale.id }"><span>审核</span></a>
							</ct:display>	
                    	  </c:if>
                    	    <input type="button" class="common_btn" onclick="history.back();" value="返回" />
							</div>
						</div>
					</div>
				</div>
		</div>
		<!-- end forms -->
	</div>
</body>
</html>