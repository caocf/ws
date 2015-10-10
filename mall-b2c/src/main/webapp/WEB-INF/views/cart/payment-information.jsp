<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/cart.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/cart.js"></script>
    <title>支付订单</title>
</head>
<body id="cart">
<script type="text/javascript" src="../js/head.js"></script>

<br/><br/>

<div class="wrapper mauto" >
    <div id="cart_container" class="w bt10">
        <!-- 新的order page -->
        <div class="neworderBox">
            <div class="shouhuoInfo2">
                <h2 class="title">${title}<span></span></h2>
                <div class="p10">
                    <ul class="pay_info">
                        <li><img src="../img/warning_middle.jpg"> ${information}</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <span class="clr"></span> </div>
</div>

<script type="text/javascript" src="../js/foot.js"></script>
</body>
</html>
