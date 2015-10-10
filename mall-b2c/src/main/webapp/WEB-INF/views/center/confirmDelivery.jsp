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
    <script type="text/javascript">
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
                            <li class="curr"><a href="#info1">确认收货成功页面</a></li>
                        </ul>
                    </div>
                </div>
                <div class="content">
					<div class="order_success">
						<div class="success_decp">
	
							<h2>您已成功确认该订单！</h2>
							<p>您可以&nbsp;<a href="addComment.chtml?saleIds=${orderId }&flag=1&storeId=${storeId }">评价</a>&nbsp;商品。欢迎购买&nbsp;<a href="${webRoot }/">更多</a>&nbsp;商品。</p>
	
						</div>
					</div>
            	</div>
            </div>
        </div>
    </div>
    <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
<script type="text/javascript" src="../js/foot.js"></script>
</body>
</html>