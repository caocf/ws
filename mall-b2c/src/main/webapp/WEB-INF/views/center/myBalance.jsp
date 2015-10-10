<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		 <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/jquery.jqzoom.js"></script>
		<script type="text/javascript" src="../js/base.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/zoom.css" />
		<link href="../css/global_v20120711.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="../js/jquery.cookie.js"></script>
		<title>我的余额</title>
		<script>
  			var  web_url='我的余额';
		</script>
</head>

<body id="usercenter">

	<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto" >
<div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的余额</span></div>
    <div id="" class="fr  w750">
        <div class="detail">
            <div class="tab">
                <div id="" class="tab_nav">
                    <ul class="tab">
                        <li class="curr">我的余额</li>
                    </ul>
                </div>
            </div>
            <c:if test="${noPhone eq 1 }">
            <div class="content" style="height: 180px;">
                <div style="height:50px;border-bottom: 1px dotted #ddd;">
	                <span style="font-size: 10px;font-weight: bold;line-height: 20px;">&nbsp;&nbsp;积分余额:</span>
	                <span style="display: block;font-size: 18px;margin-left: 10px;" class="col_link">${userScore.score }</span>
	            </div>
                <div style="height:50px;border-bottom: 1px dotted #ddd;margin-top: 8px;">
                	<span style="font-size: 10px;font-weight: bold;line-height: 20px;">&nbsp;&nbsp;商城币余额:</span>
                	<span style="display: block;font-size: 18px;margin-left: 10px;" class="col_link">${userCoin.coin }&nbsp;&nbsp;&nbsp;元</span></div>
                <div style="height:50px;margin-top: 16px;">
                	<span style="font-size: 10px;font-weight: bold;line-height: 20px;">&nbsp;&nbsp;话费余额:</span>
                	<span style="display: block;font-size: 18px;margin-left: 10px;" class="col_link">${balance}&nbsp;&nbsp;&nbsp;元</span>
                </div>
                <div style="height:50px;margin-left: 150px;margin-top: -48px;">
                	<span style="font-size: 10px;font-weight: bold;">每月消费限额:</span>
                	<span  style="display: block;font-size: 18px;margin-left: 10px;" class="col_link">${limitFee}&nbsp;&nbsp;&nbsp;元</span>
                </div>
                <div style="height:50px;margin-left: 300px;margin-top: -50px;">
                	<span style="font-size: 10px;font-weight: bold;">本月已消费:</span>
                	<span style="display: block;font-size: 18px;margin-left: 10px;" class="col_link">${useFee}&nbsp;&nbsp;&nbsp;元</span>
                </div>
            </div>
            </c:if>
            <c:if test="${noPhone eq 0 }">
             <div class="content">
            	<p style="height: 100px;margin-top: 50px;text-align: center;">数据获取错误，如有任何问题，请及时联系客服4001511511！</p>
            </div>
            </c:if>
        </div>
    </div>
    <%@include file="left_menu.jsp"%>
    <span class="clr"></span>
</div>

	<script type="text/javascript" src="../js/foot.js"></script>
	<script>
    $(function(){
        $("#myBalance").addClass("col_link");
    })
</script>
</body>
</html>