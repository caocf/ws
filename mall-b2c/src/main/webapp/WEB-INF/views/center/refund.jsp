<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    var web_url = '我的退款';
    </script>
    <title>我购买的商品</title>
</head>
<script>
	$(function(){
		var li = $("#main .reback .curr");
		var id = li.prop("id");
		$(".content").hide();
		$("#info"+id).show();
	})

</script>
<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="flag" id="flag" value="0"/>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的退款</span></div>
    <div id="main" class="fr  w750">
		<div class="detail">
			<div class="tab">
				<div class="tab_nav">
					<ul class="tab">
						<li class="curr1">退款单</li>
					</ul>
				</div>
				<div class="reback">
					<div class="tab">
						<div class="tab_nav">
							<ul class="tab">
							<c:forEach items="${list}" varStatus="status" var="data">
								<li ${data.id == refundId?'class="curr"':'' } id="${data.id}"><a href="#info${data.id}">退款单${status.count }</a></li>
							</c:forEach> 
							</ul>
						</div>
					</div>
					<c:forEach items="${list}" var="data" varStatus="status">
					<div class="content" id="info${data.id}">
						<table cellpadding="0" cellspacing="0" border="0" width="708" >
							<tr>
								<td class="dt" width="120">订单号：</td>
								<td colspan="3">${data.orderId }</td>
							</tr>
							<tr>
								<td class="dt">退款单号：</td>
								<td colspan="3">${data.id }</td>
							</tr>
							<tr>
								<td class="dt">退款商品：</td>
								<td colspan="3">
										<c:forEach items="${data.refundGoods}" var="good">
										<div class="reback_product clearfix">
											<div class="reback_item"><p>${good[4] }</p></div>
											<div class="reback_item_info"><p>购买 <a class="col_link">${good[5] }</a> 件</p></div>
											<div class="reback_item_info"><p>退 <a class="col_link">${good[1] }</a> 件</p></div>
										</div>
										</c:forEach>
								</td>
							</tr>
							<tr>
								<td class="dt">订单原价</td>
								<td colspan="3">￥${data.totalPrice }</td>
							</tr>
							<tr>
								<td class="dt">退款金额</td>
								<td colspan="3" class="col_link"><fmt:formatNumber type="currency" pattern="￥0.00" value="${data.refundFee/100 }" /></td>
							</tr>
							<tr>
								<td class="dt">退款发起时间：</td>
								<td colspan="3"><ct:time source="${data.createTime }"/></td>
							</tr>
							<tr>
								<td class="dt">商户确认时间：</td>
								<td colspan="3"><ct:time source="${data.updateTime }"/></td>
							</tr>
							<tr>
								<td class="dt">退款审核时间：</td>
								<td colspan="3"><br /><br /></td>
							</tr>
							<tr>
								<td class="dt">退款状态：</td>
								<td colspan="3" class="red"><strong>${data.flag }</strong></td>
							</tr>
						</table>
						<a class="btn" onclick="javascript:history.go(-1);">返回</a> 
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
    <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        $("#myrefund").addClass("col_link");
    })
</script>
</body>
</html>