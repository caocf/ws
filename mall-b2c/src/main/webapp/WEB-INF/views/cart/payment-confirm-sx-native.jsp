<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="../css/base.css" rel="stylesheet" type="text/css"/>
    <link href="../css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="../css/cart.css" rel="stylesheet" type="text/css"/>
    <link href="../css/pro.css" rel="stylesheet" type="text/css"/>
    <link href="../js/lab/default/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="../js/lab/default/ibutton.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/static.chtml"></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/jquery.json-2.4.js"></script>
    <script type="text/javascript" src="../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../js/cart.js"></script>
    <script type="text/javascript" src="../js/cart-common.js?v=1.0"></script>
    <script type="text/javascript" src="../js/confirm-payment.js?v=1.22"></script>
    <script type="text/javascript" src="${ctx}/js/verifyCode.js?v=2.2"></script>
    <title>支付订单</title>
    <style>
        #cart .neworderBox #newpay h1 {
            position: relative;
            margin: 0px 0px 0px;
            background: #fef7ee;
            border-bottom: 0;
            padding: 10px;
        }

        #cart .neworderBox #newpay h1 {
            background: none repeat scroll 0 0 #fff;
            border: 1px solid #eee;
            margin-bottom: 10px;
        }

        #cart .neworderBox #newpay h1 a {
            font-weight: normal;
        }

        #cart .neworderBox #newpay h1.hover {
            background: none repeat scroll 0 0 #EEF7FF;
            border: 1px solid #D4EBFD;
            border-bottom: none;
            margin-bottom: 0;
        }

        #cart .neworderBox #newpay .pay_info_content a {
            color: #06C;
        }

        #cart .neworderBox #newpay h1.hover a {
            font-weight: 700;
        }

        #cart .neworderBox #newpay h1 .abs-right {
            right: 20px;
            display: none;
        }

        #cart .neworderBox #newpay h1.hover .abs-right {
            display: block;
            right: 20px;
            top: 10px;
        }

        #cart #newpay .pay_info {
            font-size: 14px;
            background: none repeat scroll 0 0 #EEF7FF;
            border-top: 1px solid #D4EBFD;
        }

        #cart #newpay .pay_info_content {
            background: #EEF7FF;
            font-size: 14px;
            margin: 0px 0px 10px;
            border: 1px solid #D4EBFD;
            border-top: 0;
            padding: 5px 15px 5px;
        }

        #cart .neworderBox #newpay .pay_info {
            background: none repeat scroll 0 0 #fff;
            border-top: 1px dotted #BBBBBB;
        }

        #cart #newpay .pay_meth li {
            background: none repeat scroll 0 0 #fff;
            border-top: 1px solid #eee;
            padding: 0px 15px;
        }

        #cart #newpay .pay_meth {
            border-bottom: 1px solid #eee;
            font-size: 14px;
            margin: 0 20px 10px;
            border-left: 1px solid #eee;
            border-right: 1px solid #eee;
        }

        #cart #newpay input.p5 {
            font-size: 16px;
        }

        #timeb1.col_link {
            background: #007BC7;
            color: #fff;
            padding: 3px 10px;
        }
    </style>
</head>
<body id="cart">
<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto">
    <input type="hidden" id="payForm" value="only_cash">
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

                <div id="newpay">
                    <ul class="pay_info">
                        <li>下单成功。 <strong>订单号：</strong><span class="col_link" id="order_id">${payInfo.orderId}</span>，
                            <strong>总金额：</strong> <span id="total_price" class="col_link">${payInfo.payAmounts}</span>元，等待您的付款。
                        </li>
                    </ul>
                    <h2 class="p10">请选择支付方式：<span class="col_link" style="font-weight:normal">[最多同时支持两种支付方式组合，支付方式一旦确认将无法更改	<a href="../help/order_pay.html"><img style="display:inline;" src="../img/tipicon_new.jpg" /></a>]</span></h2>

                    <div class="pay_info">
                        <c:if test="${payInfo.cashPay=='1'}">
                            <h1 class="hover"><input type="checkbox" name="paynew" id="paynew_cash" value="cash"/> <a
                                    href="#pay_meth_tab1" class="pay_meth_tab_hover"><label for="paynew_cash" >现金（网银）支付</label></a> <span
                                    class="pa abs-right"><strong>支付</strong> <span
                                    class="f18 colF60 price_rmb pay_cash">0.00</span>元</span></h1>
                            <div class="pay_info_content" id="pay_meth_tab1">
                                <ul class="pay_meth">
                                    <li>
                                        <label for="alipay">
                                            <input type="radio" checked="checked" name="payType" id="alipay"
                                                   value="alipay"/>
                                            &nbsp; <img border="0" align="absmiddle" src="../img/zfb_tb.gif" style="display:inline;" >&nbsp;
                                            无支付宝帐号也可使用该支付方式进行网上银行支付。 </label>
                                    </li>
                                    <li>
                                        <label for="sjzf">
                                            <input type="radio" name="payType" id="sjzf" value="cmpay">
                                            &nbsp; <img style="display:inline;"  border="0" align="absmiddle" src="../img/96_30_sjzf.jpg">&nbsp;
                                            移动用户专属支付通道，无手机支付帐号也可使用该支付方式进行网上银行支付。 </label>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${payInfo.scorePay=='1'}">
                            <h1><input type="checkbox" name="paynew" id="paynew_score" value="score"/> <a href="#pay_meth_tab3" class="pay_meth_tab tab_score"><label for="paynew_score" >积分支付</label></a>
                                <span class="pa abs-right"><strong>支付</strong> <span
                                        class="f18 colF60 price_rmb virtual_pay pay_score">0.00</span>元</span></h1>
                            <div class="pay_info_content" id="pay_meth_tab3">
                                <ul>
                                    <li class="pr">您有可用积分 <span class="colF60 have_score">${payInfo.score}</span>，本次消费 <span
                                            class="colF60 use_scores">0</span>。
                                        <p style="padding-left:30px;">本次使用 <input type="text" id="use_score" class="p5"/>
                                            元
                                        </p>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                       <div class="tc" style="display: none">
							<table>
								<tr>
									<td width="20%"></td>
									<td width="20%"><span style="">随机验证码：</span></td>
									<td width="20%"> <input class="input_code p5" autocomplete="off" onblur="if(this.value=='') this.value='';"
										   onfocus="if(this.value=='') this.value='';" value="" type="text" name="verifyCode"
										   id="verifyCode"/></td>
									<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;<span class="code col_link f12" id="timeb1" style="cursor:pointer">免费获取</span></td>
									<td width="20%"></td>
								</tr>
							</table>
                        </div>
                        
                    </div>

                    <c:choose>
                        <c:when test="${empty tips}">
                            <p class="colF00 pl20">温馨提示：根据您选择的非现金支付方式，和您输入的积分的金额，提交后将被直接扣减。</p>
                            <div class="btn_pay">
                                <a href="javascript:void(0);"><img border="0" src="../img/space.gif" id="order_img_id"></a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="btn_pay">${tips}</div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
        </form>
        <span class="clr"></span></div>
</div>
<script type="text/javascript" src="../js/foot.js"></script>
<div class="alertbox" style="display:none;">
    <div class="content">
        <div>支付完成前请不要关闭此窗口<br/>
            完成支付后根据情况点击下面的按钮
        </div>
        <a class="btn_worry"> </a> <a class="btn_end"> </a></div>
</div>
</body>
<script>
    $(function () {
        var payAmounts="${payInfo.payAmounts}";
        var allowScore = "${payInfo.score}";
        if(toDecimal(allowScore)<0){
            allowScore=0;
        }

        $(':checkbox').removeAttr("checked");
        $(':checkbox:first').attr("checked",true).parent().addClass("hover");
        $(':checkbox').not(':first').parent().next().hide();
        $('.pay_cash').text(toDecimal2(payAmounts));
        $('.p5').val("");
        if($('#paynew_cash').attr("checked")){
            $('.tc').hide();
        }else{
            $('.tc').show();
        }

        var checkedNum = 1;
        var maxChecked = 2;
        $("input[name='paynew']").click(function(){
            checkedNum++;
            if($(this).attr("checked")){
                if(checkedNum <= maxChecked){
                    $('.p5').val("");
                    $('.virtual_pay').text("0.00");
                    $('.use_scores').text("0");
                    $(this).parent().addClass("hover");
                    $($(this).next().attr("href")).show();
                    if(this.id==="paynew_cash"){
                        $('.pay_cash').text(toDecimal2(payAmounts));
                    }
                }else{
                    $(this).attr("checked",false);
                    simpleAlert("最多同时支持两种支付方式组合");
                }
            }else{
                $('.p5').val("");
                $('.virtual_pay').text("0.00");
                $('.use_scores').text("0");
                $('.pay_cash').text(toDecimal2(payAmounts));
                $(this).parent().removeClass("hover");
                $($(this).next().attr("href")).hide();
            }
            if($('#paynew_coin').attr("checked") || $('#paynew_score').attr("checked") || $('#paynew_balance').attr("checked")){
                $('.tc').show();
            }else{
                $('.tc').hide();
            }
        });

        var checkedbox = function(){
            checkedNum = 0;
            $(':checkbox').each(function(){
                if($(this).attr("checked")){
                    checkedNum++;
                }
            });
        };

        $(":checkbox").bind("click",checkedbox);

        var coinHandler = function(obj){
            checkedbox;
            var allowPrice = toDecimal(obj.data.allowPay);
            var className = this.id.toString().replace("use","pay");
            if(checkedNum==1){
                if(allowPrice >= toDecimal(payAmounts)){
                    $(this).val(toDecimal2(payAmounts));
                    $('.'+className).text(toDecimal2(payAmounts));
                    $('.use_scores').text(toDecimal3(toDecimal(payAmounts)/0.015));
                }else{
                    $(this).val(toDecimal2(allowPrice));
                    $('.'+className).text(toDecimal2(allowPrice));
                    $('.use_scores').text(allowScore);
                }
            }else if(checkedNum==2){
                var payMoney = toDecimal($(this).val());
                if (payMoney > allowPrice) {
                    payMoney = allowPrice;
                }
                if(payMoney > payAmounts){
                    payMoney = payAmounts;
                }
                if (payMoney < 0) {
                    payMoney = 0;
                }
                if($('#paynew_cash').attr("checked")){
                    $('.pay_cash').text(toDecimal2(toDecimal(payAmounts)-payMoney));
                    $('.'+className).text(toDecimal2(payMoney));
                    $(this).val(toDecimal2(payMoney));
                    $('.use_scores').text(toDecimal3(toDecimal(payMoney)/0.015));
                }
            }
        };
        $('#use_score').bind("blur",{allowPay:toDecimal2(toDecimal(allowScore)*0.015)},coinHandler);

        $("#timeb1").click(function () {
            Message.send();
        });
    })
</script>
</html>
