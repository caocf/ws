<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/date-picker/WdatePicker.js" ></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script>
    	var web_url = '我的订单';
    </script>
    <title>我购买的商品</title>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="flag" id="flag" value="0"/>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我购买的商品</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">确认收货</a></li>
                        </ul>
                    </div>
                </div>
                <div class="content">
				<div class="itemlist_info">
					<ul>
						<li class="font_bold">
							商品清单
						</li>
						<li>${info.shopSubject }</li>
						<c:forEach items="${info.goodsInfos}" var="data">
							<li class="itemlist_single">${data.goodsSubject } * ${data.count }</li>
						</c:forEach>
					</ul>
				</div>
				<c:if test="${!empty info.expressInfo}">
				<div class="order_recline"></div>
				<div class="receive_info">
					<ul>
						<li class="font_bold">收货人信息</li>
						<li>收货人姓名：${info.expressInfo.receiverName }</li>
						<li>地址：${info.expressInfo.address }</li>
						<li>邮编：${info.expressInfo.zipCode }</li>
						<li>固定电话：${info.expressInfo.telephoneNumber }</li>
						<li>手机号码：${info.expressInfo.cellphoneNumber }</li>
					</ul>
				</div>
				<div class="order_recline"></div>
				<div class="goods_info">
					<ul>
						<li class="font_bold">物流信息</li>
						<li>物流公司：${info.expressInfo.expressCompanyName }</li>
						<li>物流单号：${info.expressInfo.expressNo }</li>
					</ul>
				</div>
				</c:if>
				<form action="orderDelivery.chtml" method="post" id="form1">
				<input type="hidden" name="orderId" value="${info.id }" />
				<div class="order_recline"></div>
				<div class="confirm_take">
					<div class="confirm_decp"><h2>我已确认收到商品</h2><p>我已确认收到商品</p></div>
					<input class="confirm_btn" type="button" value="确认收货" title="确认收货"/>
				</div>
				</form>
			</div>
            
            </div>
        </div>
    </div>
   <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        $("#orderManager").addClass("col_link");
    })
</script>
</body>
</html>