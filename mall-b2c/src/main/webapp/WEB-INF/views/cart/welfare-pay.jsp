<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
<head>
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
    <script type="text/javascript" src="${ctx}/js/cart-common.js"></script>
    <title>支付订单</title>
</head>
<body id="cart">
<script type="text/javascript" src="${ctx}/js/head.js"></script>
<div class="wrapper mauto">
    <div id="cart_container" class="w bt10">
        <!-- 新的order page -->
        <div class="neworderBox">
            <div class="shouhuoInfo2">
                <h2 class="title">支付订单<span></span></h2>

                <div class="p10">
                    <ul class="pay_info">
                        <li>下单成功。 <strong>订单号：</strong><span class="col_link" id="order_id">${orderId}</span>，
                            <strong>总金额：</strong> <span class="col_link">${totalPrice}</span> ，等待您的付款。
                        </li>
                    </ul>

                    <div class="pay_info_content" id="pay_meth_tab1">
                        <ul class="pay_info">
                            <li class="pr"><span class="pa abs-right"><strong>支付</strong><span
                                    class="f18 colF60 price_rmb">${welfarePay}</span>元</span></span>
                                <strong>红包支付：</strong>您有可用红包<span class="colF60">${totalWelfare}</span>
                                ，本次消费最多可用 <span class="colF60 allow_coin">${welfarePay}</span> </a>。
                            </li>
                        </ul>
                        <c:if test="${needCash=='true'}">
                            <ul class="pay_info">
                                <li class="pr"><strong>现金支付</strong>： <span
                                        class="pa abs-right"><strong>支付</strong> <span
                                        class="f18 colF60 price_rmb">${cashPay}</span>元</span></li>
                                <ul class="pay_meth">
                                    <li>
                                        <label for="alipay">
                                            <input type="radio" checked="checked" name="channel" id="alipay"
                                                   value="alipay">
                                            &nbsp; <img border="0" align="absmiddle" src="${ctx}/img/zfb_tb.gif">&nbsp;
                                            无支付宝帐号也可使用该支付方式进行网上银行支付。 </label>
                                    </li>
                                    <li>
                                        <label for="sjzf">
                                            <input type="radio" name="channel" id="sjzf" value="sjzf">
                                            &nbsp; <img border="0" align="absmiddle" src="${ctx}/img/96_30_sjzf.jpg">&nbsp;
                                            移动用户专属支付通道，无手机支付帐号也可使用该支付方式进行网上银行支付。 </label>
                                    </li>
                                </ul>
                            </ul>
                        </c:if>

                    </div>


                    <p class="pl20" style="color:red;">温馨提示：提交后，红包将被扣减。</p>


                    <div class="btn_pay"><a href="javascript:void(0);"><img border="0" src="${ctx}/img/space.gif"
                                                                     id="order_img_id"></a></div>
                </div>
            </div>
        </div>
        </form>
        <span class="clr"></span></div>
</div>
<script type="text/javascript" src="${ctx}/js/foot.js"></script>
<div class="alertbox" style="display:none;">
    <div class="content">
        <div>支付完成前请不要关闭此窗口<br/>
            完成支付后根据情况点击下面的按钮
        </div>
        <a class="btn_worry"> </a> <a class="btn_end"> </a></div>
</div>
<script>
    $(function () {

        var lock = false;
        $('.btn_pay > a').click(function () {

            if (lock) {
                simpleAlert("正在提交数据，请稍后");
                return;
            }
            var payType = $(':radio[name="channel"]:checked').val();
            showWaiting();
            jsonReq('order/welfare/pay.chtml', {orderId: $('#order_id').text(), channel: payType},
                    function (data) {
                        if (data.type == 'success') {
                            var url = '${ctx}/order/welfare/payment-success.chtml?orderId=' + data.orderId;

                            window.location = url;
                        }
                        if (data.type == 'redirect') {
                            try {
                                document.charset = 'gbk';
                            } catch (e) {
                            }
                            ;
                            window.location = data.url;
                        }
                        if (data.type == 'form') {
                            var form = $('<form method="POST" action=' + data.action + '"></form>');
                            $('body').append(data.input);
                            form.submit();
                        }
                        return false;
                    }, function () {
                        return true;
                    }, function () {
                        lock = false;
                        hideWaiting();
                    });
            lock = true;
        })
    })

</script>
</body>
</html>
