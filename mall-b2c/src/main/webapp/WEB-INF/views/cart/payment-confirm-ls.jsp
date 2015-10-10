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
    <link href="../css/balance_pay_hint.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/static.chtml"></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/jquery.json-2.4.js"></script>
    <script type="text/javascript" src="../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../js/cart.js"></script>
    <script type="text/javascript" src="../js/cart-common.js?v=1.0"></script>
    <script type="text/javascript" src="../js/confirm-payment.js?v=1.22"></script>
    <script type="text/javascript" src="${ctx}/js/verifyCode.js?v=2.2"></script>
    <script type="text/javascript" src="../js/balance-pay-hint.js?v=1.0"></script>
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
                    <h2 class="p10">请选择支付方式：<span style="font-weight:normal">[该商品仅支持单一支付]</span></h2>

                    <div class="pay_info">
                        <c:if test="${payInfo.cashPay=='1'}">
                            <h1 class="hover"><input type="radio" name="paynew" id="paynew_cash" value="cash"/> <a
                                    href="#pay_meth_tab1" class="pay_meth_tab_hover"><label for="paynew_cash">现金（网银）支付</label></a> <span
                                    class="pa abs-right"><strong>支付</strong> <span
                                    class="f18 colF60 pay_cash">0.00</span>元</span></h1>
                            <div class="pay_info_content" id="pay_meth_tab1">
                                <ul class="pay_meth">
                                    <li>
                                        <label for="alipay">
                                            <input type="radio" checked="checked" name="payType" id="alipay"
                                                   value="alipay"/>
                                            &nbsp; <img border="0" align="absmiddle" src="../img/zfb_tb.gif">&nbsp;
                                            无支付宝帐号也可使用该支付方式进行网上银行支付。 </label>
                                    </li>
                                    <li>
                                        <label for="sjzf">
                                            <input type="radio" name="payType" id="sjzf" value="cmpay">
                                            &nbsp; <img border="0" align="absmiddle" src="../img/96_30_sjzf.jpg">&nbsp;
                                            移动用户专属支付通道，无手机支付帐号也可使用该支付方式进行网上银行支付。 </label>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${payInfo.coinPay=='1'}">
                            <h1><input type="radio" name="paynew" id="paynew_coin" value="coin"/> <a href="#pay_meth_tab2" class="pay_meth_tab tab_coin"><label for="paynew_coin">商城币支付</label></a>
                                <span class="pa abs-right coin_used"><strong>支付</strong> <span
                                        class="f18 colF60 virtual_pay pay_coin">0.00</span>个</span></h1>
                            <div class="pay_info_content" id="pay_meth_tab2">
                                <ul>
                                    <li class="pr">您有可用商城币 <span class="colF60 have_coin">${payInfo.allowCoin}</span>个，
                                        <span class="enough_coin">本次使用 <span class="colF60 use_coin"></span>个，</span>
                                        <span class="not_enough_coin">不足支付订单，请重新选择商品或选择其他支付方式！</span>
                                        选择非现金支付方式时支付价格可能有所变动。
                                        <a href="../center/goExchange.chtml" target="_blank">[您可以用积分兑换商城币]</a>。<br/>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${payInfo.scorePay=='1'}">
                            <h1><input type="radio" name="paynew" id="paynew_score" value="score"/> <a href="#pay_meth_tab3" class="pay_meth_tab tab_score"><label for="paynew_score">积分支付</label></a>
                                <span class="pa abs-right score_used"><strong>支付</strong> <span
                                        class="f18 colF60 virtual_pay use_score">0</span>个</span>
                                <span class="pay_score" style="display: none"></span>
                            </h1>
                            <div class="pay_info_content" id="pay_meth_tab3">
                                <ul>
                                    <li class="pr">您有可用积分 <span class="colF60 have_score">${payInfo.score}</span>个，
                                        <span class="enough_score">本次消费需扣除积分 <span class="colF60 use_score"></span>个，</span>
                                        <span class="not_enough_score">不足支付订单，请重新选择商品或选择其他支付方式！</span>
                                        选择非现金支付方式时支付价格可能有所变动。
                                        <a href="../center/goExchange.chtml" target="_blank">[您可以用积分兑换商城币]</a>。<br/>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${payInfo.billPay=='1'}">
                            <h1><input type="radio" name="paynew" id="paynew_balance" value="balance"/> <a href="#pay_meth_tab4" class="pay_meth_tab tab_bill"><label for="paynew_balance">小额话费支付</label></a>
                                <span class="pa abs-right bill_used"><strong>支付</strong> <span
                                        class="f18 colF60 virtual_pay pay_bill">0.00</span>元</span></h1>
                            <div class="pay_info_content" id="pay_meth_tab4">
                                <ul class="pr">
                                    <li>您有可用话费 <span class="colF60 allow_bill">${payInfo.balance}</span>元，
                                        <span class="enough_bill">每月话费支付累计上限为<span class="colF60">${payInfo.allowBalance}</span>元，本次消费需扣除话费 <span class="colF60 use_bill"></span>元，</span>
                                        <span class="not_enough_bill">不足支付订单，请重新选择商品或选择其他支付方式！</span>
                                        选择非现金支付方式时支付价格可能有所变动。
                                        <a href="../center/myBalance.chtml" target="_blank">[我的余额]</a>。
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                        <p class="tc" style="display: none"><span style="">随机验证码：</span>
                            <input class="input_code p5" autocomplete="off" onblur="if(this.value=='') this.value='';"
                                   onfocus="if(this.value=='') this.value='';" value="" type="text" name="verifyCode"
                                   id="verifyCode"/>
                            <span class="code col_link f12" id="timeb1" style="cursor:pointer">免费获取</span>
                        </p>
                    </div>
                    <p class="colF00 pl20">温馨提示：根据您选择的非现金支付方式，和您输入的商城币、积分或小额话费的金额，提交后将被直接扣减。</p>
                    <div class="btn_pay">
                        <a href="javascript:void(0);"><img border="0" src="../img/space.gif" id="order_img_id"></a>
                    </div>
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
        var lsPayAmounts="${payInfo.lsPayAmounts}";
        var allowCoin = "${payInfo.allowCoin}";
        var balance = "${payInfo.balance}";
        var allowScore = "${payInfo.score}";

        if(toDecimal(allowCoin)<0){
            allowCoin=0;
        }
        if(toDecimal(balance)<0){
            balance=0;
        }
        if(toDecimal(allowScore)<0){
            allowScore=0;
        }
        if(toDecimal(allowCoin) >= toDecimal(lsPayAmounts)){
            $('.use_coin,.pay_coin').text(toDecimal2(lsPayAmounts));
            $('.enough_coin').show();
            $('.not_enough_coin').hide();
        }else{
            $('.coin_used').hide();
            $('.enough_coin').hide();
            $('.not_enough_coin').show();
        }
        if(toDecimal(toDecimal(allowScore)*0.015) >= toDecimal(lsPayAmounts)){
            $('.use_score').text(toDecimal3(toDecimal(lsPayAmounts)/0.015));
            $('.pay_score').text(toDecimal2(lsPayAmounts));
            $('.enough_score').show();
            $('.not_enough_score').hide();
        }else{
            $('.score_used').hide();
            $('.enough_score').hide();
            $('.not_enough_score').show();
        }
        if(toDecimal(balance) >= toDecimal(lsPayAmounts)){
            $('.use_bill,.pay_bill').text(toDecimal2(lsPayAmounts));
            $('.enough_bill').show();
            $('.not_enough_bill').hide();
        }else{
            $('.bill_used').hide();
            $('.enough_bill').hide();
            $('.not_enough_bill').show();
        }

        $(':radio[name="paynew"]').removeAttr("checked");
        $(':radio[name="paynew"]:first').attr("checked",true).parent().addClass("hover");
        $(':radio[name="paynew"]').not(':first').parent().next().hide();
        $('.pay_cash').text(toDecimal2(payAmounts));

        if(!$('#paynew_cash').attr("checked")){
            $('#total_price').text(toDecimal2(lsPayAmounts));
            $('.tc').show();
        }else{
            $('.tc').hide();
        }

        $(':radio[name="paynew"]').click(function(){
            $('.pay_info_content').hide();
            $('.hover').removeClass("hover");
            $(this).parent().addClass("hover");
            $($(this).next().attr("href")).show();
            if(this.id==="paynew_cash"){
                $('#total_price').text(toDecimal2(payAmounts));
                $('.tc').hide();
            }else{
                $('#total_price').text(toDecimal2(lsPayAmounts));
                $('.tc').show();
            }
        });

        $("#timeb1").click(function () {
            Message.send();
        });
    })
</script>
</html>
