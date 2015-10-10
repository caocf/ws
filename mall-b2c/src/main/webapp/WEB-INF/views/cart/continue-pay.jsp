<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${ctx}/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/cart.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/pro.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/js/lab/default/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/js/lab/default/ibutton.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/static.chtml"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.json-2.4.js"></script>
    <script type="text/javascript" src="${ctx}/js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="${ctx}/js/cart.js"></script>
    <script type="text/javascript" src="${ctx}/js/cart-common.js"></script>
    <script type="text/javascript" src="${ctx}/js/confirm-payment.js?v=1.22"></script>
    <script type="text/javascript" src="${ctx}/js/verifyCode.js"></script>
    <style>
        #timeb1.col_link {
            background: #007BC7;
            color: #fff;
            padding: 3px 10px;
        }
    </style>
    <title>支付订单</title>
</head>
<body id="cart">
<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto">
    <div id="cart_container" class="w bt10">
        <div class="flow-steps">
            <ol class="flow_step_no3 ">
                <li class="step_1">查看购物车</li>
                <li class="step_2">核对订单</li>
                <li class="step_3">支付订单</li>
            </ol>
        </div>
        <!-- 新的order page -->
        <div class="neworderBox">
            <div class="shouhuoInfo2">
                <h2 class="title">支付订单<span></span></h2>

                <div class="p10">
                    <ul class="pay_info">
                        <li><strong>订单号：</strong><span class="col_link" id="order_id">${payInfo.orderId}</span>， <strong>总金额：</strong>
                            <span class="col_link f18 colF60">${payInfo.payAmounts}</span>元，等待您的付款。
                        </li>
                    </ul>
                    
                    <p class="m15">请选择支付方式：
                    	<span class="col_link" style="font-weight:normal; ">[你选择的支付方式为
                    		<strong style="color:#f33">
                    			<c:forEach items="${paymentInfos}" var="payInfo" varStatus="payItem">
                    				<c:if test="${fn:length(paymentInfos) > 1 }">
                    					<c:if test="${payInfo.currency=='cash' && payItem.index eq 0}">
                    						现金（网银） + 
                    					</c:if>
                    					<c:if test="${payInfo.currency=='cash' && payItem.index ne 0}">
	                    					现金（网银）
	                    				</c:if>
                    					<c:if test="${payInfo.currency=='coin' && payItem.index eq 0}">
                    						商城币  + 
                    					</c:if><c:if test="${payInfo.currency=='coin' && payItem.index ne 0}">
	                    					商城币 
	                    				</c:if>
                    					<c:if test="${payInfo.currency=='score' && payItem.index eq 0}">
                    						积分  +
                    					</c:if>
                    					<c:if test="${payInfo.currency=='score' && payItem.index ne 0}">
	                    					积分
	                    				</c:if>
                    					<c:if test="${payInfo.currency=='balance' && fn:length(paymentInfos) > 1 && payItem.index eq 0}">
                    						话费  +
                    					</c:if>
                    					<c:if test="${payInfo.currency=='balance' && payItem.index ne 0}">
	                    					话费
	                    				</c:if>
                    				</c:if>
                    				<c:if test="${fn:length(paymentInfos) == 1 }">
	                    				<c:if test="${payInfo.currency=='cash'}">
                    						现金（网银）
                    					</c:if>
                    					<c:if test="${payInfo.currency=='coin'}">
                    						商城币
                    					</c:if>
                    					<c:if test="${payInfo.currency=='score'}">
                    						积分 
                    					</c:if>
                    					<c:if test="${payInfo.currency=='balance'}">
                    						话费
                    					</c:if>
                    				</c:if>
                    			</c:forEach>
                    		</strong><c:if test="${fn:length(paymentInfos) > 1 }">组合</c:if>，无法更改
 							<a href="../help/mul_pay.html"><img src="../img/tipicon_new.jpg" /></a> ]
 						</span>
 					</p>
 					
 					<c:forEach items="${paymentInfos}" var="payInfo">
 						<c:if test="${payInfo.currency != leftPays[0].currency }">
	 					  <c:if test="${payInfo.currency=='coin'}">
	                            <ul class="pay_info">
	                                <li class="pr">
	                                <span class="pa abs-right"><strong>支付</strong><span
	                                        class="f18 colF60 price_rmb"> ${payInfo.amount}</span>元</span>
	                                    <strong>商城币支付<span style="color:#093">(已支付)</span></strong>
	                                </li>
	                            </ul>
	                        </c:if>
	                        <c:if test="${payInfo.currency=='score'}">
	                            <ul class="pay_info">
	                                <li class="pr">
	                                <span class="pa abs-right"><strong>支付</strong><span
	                                        class="f18 colF60 price_rmb"> ${payInfo.amount}</span>元</span>
	                                    <strong>积分支付<span style="color:#093">(已支付)</span></strong>
	                                </li>
	                            </ul>
	                        </c:if>
	                        <c:if test="${payInfo.currency=='balance'}">
	                            <ul class="pay_info">
	                                <li class="pr">
	                                        <span class="pa abs-right"><strong>支付</strong><span
	                                                class="f18 colF60 price_rmb"> ${payInfo.amount}</span>元</span>
	                                    <strong>话费支付<span style="color:#093">(已支付)</span></strong>
	                                </li>
	                            </ul>
	                        </c:if>
	                        <c:if test="${payInfo.currency=='cash'}">
	                            <ul class="pay_info">
	                                <li class="pr"><strong>现金（网银）支付<span style="color:#093">(已支付)</span></strong><span
	                                        class="pa abs-right"><strong>支付</strong><span
	                                        class="f18 colF60 price_rmb"> ${payInfo.amount}</span>元</span></li>
	                            </ul>
	                        </c:if>
                        </c:if>
 					 
 					</c:forEach>
 					
                    <c:forEach items="${leftPays}" var="leftPay">

                        <c:if test="${leftPay.currency=='coin'}">
                            <ul class="pay_info">
                                <li class="pr">
                                <span class="pa abs-right"><strong>支付</strong><span
                                        class="f18 colF60 price_rmb"> ${leftPay.amount}</span>元</span>
                                    <strong>商城币支付<span style="color:#f33">(未支付)</span>：</strong> 本次需要支付 <span
                                        class="colF60 price_rmb">${leftPay.amount}</span>

                                    <input type="hidden" id="use_coin" value="${leftPay.amount}">

                                    <%@include file="part/verifyCode.jsp" %>
                                </li>
                            </ul>
                        </c:if>
                        <c:if test="${leftPay.currency=='score'}">
                            <ul class="pay_info">
                                <li class="pr">
                                <span class="pa abs-right"><strong>支付</strong><span
                                        class="f18 colF60 price_rmb"> ${leftPay.amount}</span>元</span>
                                    <strong>积分支付<span style="color:#f33">(未支付)</span>：</strong> 本次需要支付 <span
                                        class="colF60 price_rmb">${leftPay.amount}</span>

                                    <input type="hidden" id="use_coin" value="${leftPay.amount}">

                                    <%@include file="part/verifyCode.jsp" %>
                                </li>
                            </ul>
                        </c:if>
                        <c:if test="${leftPay.currency=='balance'}">
                            <ul class="pay_info">
                                <li class="pr">
                                        <span class="pa abs-right"><strong>支付</strong><span
                                                class="f18 colF60 price_rmb"> ${leftPay.amount}</span>元</span>
                                    <strong>话费支付<span style="color:#f33">(未支付)</span>：</strong> 本次需要支付 <span
                                        class="colF60 price_rmb">${leftPay.amount}</span>

                                    <%@include file="part/verifyCode.jsp" %>
                                </li>
                            </ul>
                            <input type="hidden" id="use_coin" value="${leftPay.amount}"/>
                        </c:if>
                        <c:if test="${leftPay.currency=='cash'}">
                            <ul class="pay_info">
                                <li class="pr"><strong>现金支付<span style="color:#f33">(未支付)</span></strong>： <span
                                        class="pa abs-right"><strong>支付</strong><span
                                        class="f18 colF60 price_rmb"> ${leftPay.amount}</span>元</span></li>
                                <ul class="pay_meth">

                                    <li>
                                        <label for="alipay">
                                            <input type="radio" checked="checked" name="payType" id="alipay"
                                                   value="alipay"/>
                                            &nbsp; <img border="0" align="absmiddle" src="../img/zfb_tb.gif"/>&nbsp;
                                            无支付宝帐号也可使用该支付方式进行网上银行支付。 </label>
                                    </li>

                                    <li>
                                        <label for="sjzf">
                                            <input type="radio" name="payType" id="sjzf" value="cmpay"/>
                                            &nbsp; <img border="0" align="absmiddle" src="../img/96_30_sjzf.jpg"/>&nbsp;
                                            移动用户专属支付通道，无手机支付帐号也可使用该支付方式进行网上银行支付。 </label>
                                    </li>

                                </ul>
                            </ul>
                        </c:if>

                    </c:forEach>

                </div>
                <div class="btn_pay"><a href="javascript:void(0);"><img border="0" src="../img/space.gif"
                                                                 id="order_img_id"/></a></div>
            </div>
        </div>
    </div>

    <span class="clr"></span></div>

<script type="text/javascript" src="../js/foot.js"></script>
<div class="alertbox" style="display:none;">
    <div class="content">
        <div>支付完成前请不要关闭此窗口<br/>
            完成支付后根据情况点击下面的按钮
        </div>
        <a class="btn_worry"> </a> <a class="btn_end"> </a></div>
</div>
</body>
</html>
