<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		<link href="../css/base.css" rel="stylesheet" type="text/css" />
		<link href="../css/layout.css" rel="stylesheet" type="text/css" />
		<link href="../css/money.css" rel="stylesheet" type="text/css" />
		<link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/jquery.jqzoom.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/zoom.css" />
		<script type="text/javascript" src="../js/money.js"></script>
		<script type="text/javascript" src="../js/jquery.cookie.js"></script>
		<link href="../css/global_v20120711.css" type="text/css" rel="stylesheet">
		<title>商城币兑换</title>
</head>

<body id="money">

	<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto" >
	<a target="_blank" href="/" style="margin-bottom:10px; display:block;"><img alt="" src="../img/exchange.jpg"></a>
    <div id="" class="fr  w750">
        <div class="detail">
            <div class="tab">
                <div id="" class="tab_nav">
                    <ul class="tab">
                        <li class="curr">移动商城币兑换平台</li>
                    </ul>
                </div>
            </div>
            <div class="content">
            	 <!-- <div class="search_wrap">
                    <label class="comment_text">开始日期：</label>
                    <input type="text">
           			<label class="comment_text">结束日期：</label>
                    <input type="text">
                    <input type="button" value="搜索" class="btn_search">
                </div>  -->
                <table cellspacing="0" cellpadding="0" width="100%">
        	<tbody><tr class="title">
            	<td width="20%">日期</td>
                <td width="15%">兑换种类</td>
                <td width="15%">积分数</td>
                <td width="15%">兑换比率</td>
                <td>商城币</td>
            </tr>
            <c:if test="${!empty exchangeLogs }"  var="f">
            <c:forEach items="${exchangeLogs}" var="data">
				<tr class="col_link">
	            	<td>${data.excDate}</td>
	                <td>兑换商城币</td>
	                <td>${data.amount*data.excPercent}</td>
	                <td>${data.excPercent }:1</td>
	                <td>${data.amount}</td>
	            </tr>
	        </c:forEach>
            </c:if>
			<c:if test="${empty exchangeLogs }"  var="f">
			<tr>
				<td colspan="5"><font color="red">数据为空！</font></td>
			</tr>
			</c:if>
		
		

</tbody></table>
              <!--   <div class="page_numbers">
                        <ul>
                            <li class="no_link">&lt;</li>
                            <li class="active_page">1</li>
                            <li><a href="javascript:np(2);">2</a></li>
                            <li><a href="javascript:np(3);">3</a></li>
                            <li><a href="javascript:np(4);">4</a></li>
                            <li><a href="javascript:np(5);">5</a></li>
                            <li>...</li>
                            <li><a href="javascript:np(6);">16</a></li>
                            <li><a href="javascript:np(7);">17</a></li>
                            <li><a href="javascript:np(8);">18</a></li>
                            <li><a href="javascript:np(9);">19</a></li>
                            <li><a href="javascript:np(10);">20</a></li>
                            <li><a href="javascript:np(2);">&gt;</a></li>
                        </ul>
                        <div style="float: none; clear: both;"></div>
                    </div> -->
                <div class="btn_line">
                    <input type="button" style="margin-top:0; float:right;" onclick="javaScript:top.location='${webroot}';" value="返回商城" class="money_btn_small" name="backshangc">
                 </div>            </div>
        </div>
    </div>
    <div id="sidebar" class="w240 fl">
    	<div class="related-title">
			<h3>什么是商城币？</h3>
        </div>
		<div class="related-content">12580移动商城（http://mall.12580life.com）所使用的商城币，是由中国移动各种品牌业务的积分，经移动积分统一兑换平台兑换而来。 您可以登录此平台，将自己的全球通积分、动感地带M值等，兑换成商城币。 商城币的价值与等额现金相同，若您有足够的商城币，即可在商城内全额使用商城币购买喜爱的商品；若商城币不足，剩余款项可以通过其他支付渠道组合支付（支付宝、手机支付等）。</div>
		<div class="related-title">
	        <h3>如何获取商城币？</h3>
    	</div>
		<div class="related-content">移动全球通积分、动感地带M值兑换商城币；
            全球通积分:商城币 = 67:1
            M值:商城币=67:1
            生活网来福币兑换商城币；
            暂停下线，恢复时间将另行通知。
            移动体验100勋章兑换商城币；
            体验100勋章： 
		</div>
    </div>
    <span class="clr"></span>
</div>
	<script type="text/javascript" src="../js/foot.js"></script>
</body>
</html>