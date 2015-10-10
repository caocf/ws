<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/cart.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <link href="../js/lab/default/dialog.css" rel="stylesheet" type="text/css" />
    <link href="../js/lab/default/ibutton.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/static.chtml"></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/jquery.json-2.4.js"></script>
    <script type="text/javascript" src="../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../js/cart-common.js"></script>
    <script type="text/javascript" src="../js/cart.js"></script>
    <title>购物车信息</title>
    <style>
        .numchange img{
            vertical-align: middle!important;
            display: inline!important;
        }
    </style>
</head>
<body id="cart">


<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto">

    <div id="cart_container" class="w bt10">
        <div class="flow-steps">
            <ol class="flow_step_no1 ">
                <li class="step_1">查看购物车</li>
                <li class="step_2">核对订单</li>
                <li class="step_3">支付订单</li>
            </ol>
        </div>
        <!-- 新的order page -->
        <div class="editproductBox">

            <h2 class="title">购物车信息 <a style="display:none;" class="removeall" href="javascript:void(0);">
                <img src="../img/removeall.jpg"> 清空购物车</a>
            </h2>

            <div class="preload"><img src="<c:url value="/img/preload.gif"/>"/></div>

            <div class="editproduct emptycontainer" style="display:none;">
                <div id="no_product">
                    <p>您的购物车暂时无任何商品，您可以<a href='<c:url value="http://www.12580life.com/"/>'>去首页</a>挑选喜欢的商品。
                    </p>
                </div>
            </div>

            <!-- for cart data -->

            <span class="clr"></span>
        </div>
    </div>
</div>
<script type="text/javascript" src="http://www.12580life.com/henan/foot_for_henan.jsp"></script> 

<!-- template start -->
<div id="template" style="display:none;">
    <div class="editproduct shopcontainer" style="display:none;">
        <table cellspacing="0" cellpadding="0" border="0" width="964">
            <tbody>
            <tr>
                <th style="width: 75px;"><input type="checkbox" class="checkbox_all" /> 全选</th>
                <th>商品</th>
                <th width="10%">单价（元）</th>
                <th width="10%">数量</th>
                <th width="10%">小计（元）</th>
                <th width="10%">操作</th>
            </tr>
            <tr class="bt1px">
                <td style="width: 75px;" class="tc"> 商家</td>
                <td class="tl" colspan="6"><a target="_blank"></a></td>
            </tr>
            </tbody>
        </table>
        <div class="order_submit2">
            <div id="order_submit_price">
                <table width="100%" cellspacing="0" cellpadding="0" border="0">
                    <tbody>
                    <tr>
                        <td></td>
                        <td width="120">总计(不含运费): </td>
                        <td width="180"><strong class="shopallprice"></strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <form action="../order/confirm.chtml" class="postv" method="post">
            <input type="hidden" name="items" value="">
            <a href="javascript:void(0);" class="confirmpost">
                <img border="0" id="order_img_id" lock_flag="0" onmouseover="this.style.cursor='pointer'" src="../img/space.gif" style="cursor: pointer;">
            </a>
        </form>
    </div>
    <table>
        <tr class="order_item">
            <td class="tc">
                <input type="checkbox" class="checkbox_item"/></td>
            <td class="tl"><ul>
                <li class="order_item_img">
                    <a target="_blank">
                        <img height="50" border="0" wdith="50" target="_blank" />
                    </a>
                </li>
                <li class="order_item_good">
                    <a target="_blank"></a>
                </li>
                <span class="order_item_prop" style="color:#a9a9a9;display:none;"></span>
                <span class="order_item_prompt" style="color:#a9a9a9;display:none;"></span>
                <span class="order_item_payType" style="color:#a9a9a9;display:none;"></span>
                <div class="clear"></div>
            </ul></td>
            <td><p class="price"><span class="itemprice"></span></p></td>
            <td><div class="numchange">
                <img style="position:relative;cursor:pointer;" src="../img/nbtn_remove.jpg" func="minus" />
                <input type="text" value="" max="" name="quantity" style="width:36px; text-align:center;" func="cal_price" />
                <img style="position:relative;cursor:pointer;" src="../img/nbtn_add.jpg" func="plus" />
            </div></td>
            <td><p class="price"><span class="itemallprice"></span></p></td>
            <td><a href="javascript:void(0);" onclick="return false;" func="del">删除</a></td>
        </tr></table>
</div>
<!-- template end -->
</body>
</html>
