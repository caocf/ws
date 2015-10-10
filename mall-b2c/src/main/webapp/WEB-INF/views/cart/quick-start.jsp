<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/cart.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<c:url value="/js/static.chtml"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/cart.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.json-2.4.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.cookie.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/cart-common.js"/>"></script>
    <title></title>
</head>
<style>




</style>
<body id="cart">

<script type="text/javascript">
$(function() {
    $('#test').click(function(evt) {
        var callback;
        var doptions = {
            html: "确定执行此操作？",
            extra_class: 'confirm',
            yes_callback: null,
            yes_text: "确定",
            show_no: true,
            no_text: "取消"
        }

            doptions.event = evt;

        var $dialog = show_dialog(doptions);
    });
});




    var buyNow = function() {

        $.cookie('backurl', location.href);

        var itemId = $('#itemId').val();
        var quantity = $('#quantity').val();

        $.cookie('confirm_itemId', itemId, { path: '/' });
        $.cookie('confirm_quantity', quantity, { path: '/' });
        /*
        var form = $('<form id="buynowform" method="POST" action="' + G_CTX_ROOT  + '/order/buynow.chtml' + '"></form>');
        var itemId = $('<input type="hidden" name="itemId"/>').appendTo(form).val(itemId);
        var ys = selProps.split(',');
        for (var i = 0; i < ys.length; i++) {
            var selProps = $('<input type="hidden" name="selProps"/>').appendTo(form).val(ys[i]);
        }
        var quantity = $('<input type="hidden" name="quantity"/>').appendTo(form).val(quantity);

        $('body').append(form);
        form.submit();
         */
        window.location = G_CTX_ROOT + 'order/buynow.chtml';
    }

    var login = function() {
        $.ajax({
            url : G_CTX_ROOT + "cart/login.chtml",
            type : "POST",
            dataType : 'json',
            data : {username: $('#username').val()},
            success : function(data, stats) {
                if (data.success) $('#alertTip').text('登录成功');
                else $('#alertTip').text(data.msg);
            },
            error : function(data) {
                $('#alertTip').text("请求失败");
            }
        });
    };

    var logout = function() {
        $.ajax({
            url : G_CTX_ROOT + "cart/logout.chtml",
            type : "POST",
            dataType : 'json',
            success : function(data, stats) {
                if (data.success) $('#alertTip').text('登出成功');
                else $('#alertTip').text(data.msg);
            },
            error : function(data) {
                $('#alertTip').text("请求失败");
            }
        });
    };

    var addToCart = function() {


        var info = {
            "itemId": $('#itemId').val(),
            "quantity": $('#quantity').val()};
        $.cookie('backurl', location.href);
        $.ajax({
            url : G_CTX_ROOT + "cart/additem.chtml",
            type : "POST",
            dataType : 'json',
            data : $.param(info, true),
            success : function(data, stats) {
                if (data.success) $('#alertTip2').text('已成功加入购物车');
                else $('#alertTip2').text(data.msg);
            },
            error : function(data) {
                $('#alertTip2').text("请求失败");
            }
        });
    };

</script>


商品ID
<input type="text" id="itemId" name="itemId" value=""/>
<br/>商品数量
<input type="text" id="quantity" name="quantity" value=""/>


<button onclick="addToCart();">加入购物车</button>

<button onclick="buyNow();">直接购买</button>
<span id="alertTip2"></span>

<br/>
<button onclick="window.location='./show.chtml'">查看购物车</button>



<button onclick="$.removeCookie('cartuuid');">删除</button>


<br/><br/>
<input type="text" id="username" name="username" />

<button onclick="login()">登录</button>
<button onclick="logout()">退出</button>
<span id="alertTip"></span>





<pre>
235 729 95 96 97
381 729
351
607
333  729 97 95 96
581  202 294 295 296
</pre>
333  729 97 95 96
581  202 294 295 296333  729 97 95 96
581  202 294 295 296<button id="test">这是商品</button>

</body>
</html>
