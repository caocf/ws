<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${ctx}/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/cart.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/pro.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/cart.js"></script>
    <script type="text/javascript" src="${ctx}/js/cart-common.js"></script>
    <title>支付成功</title>
</head>
<body id="cart">
<script type="text/javascript" src="${ctx}/js/head.js"></script>
<br/><br/>

<div class="wrapper mauto">
    <div id="cart_container" class="w bt10">
        <div class="neworderBox">
            <div class="shouhuoInfo2">
                <h2 class="title">支付提交成功<span></span></h2>

                <div class="p10">

                    您的订单已提交支付，请您稍后至个人中心——我的福利查看订单

                    <ul class="pay_info">
                        <li>
                            <strong>订 单 号：</strong><span id="order_id" class="col_link">${param.orderId}</span>，
                            此订单已成功提交支付。
                        </li>

                    </ul>


                </div>

                <div class="btn-group">
                    <a class="btn_pay_usercenter" href="${ctx}/center/orderManager.chtml">
                    </a>
                    <a class="btn_pay_home" href="${ctx}/">
                    </a>
                </div>
            </div>
        </div>
    </div>
    <span class="clr"></span></div>
</div>

<script type="text/javascript" src="${ctx}/js/foot.js"></script>

</body>
</html>
