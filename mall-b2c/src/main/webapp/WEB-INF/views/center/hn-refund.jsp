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
    <title>${pageTitle }</title>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="flag" id="flag" value="0"/>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我购买的商品</span></div>
    <div id="main" class="fr  w750">
		<div class="detail">
			<div class="tab">
				<div class="tab_nav">
					<ul class="tab">
						<li class="curr">退款单</li>
					</ul>
				</div>
				<div class="reback">
					<div class="tab">
						<div class="tab_nav">
							<ul class="tab">
							<c:forEach items="${list}" varStatus="status">
								<li ${status.count == 1?'class="curr"':'' }><a href="#info${status.count }">退款单${status.count }</a></li>
							</c:forEach> 
							</ul>
						</div>
					</div>
					<c:forEach items="${list}" var="data" varStatus="status">
					<div class="content" id="info${status.count }">
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
								<td colspan="3"><fmt:formatNumber type="number" value="${data.totalPrice*100 }" maxFractionDigits="0"/>个</td>
							</tr>
							<tr>
								<td class="dt">退款金额</td>
								<td colspan="3" class="col_link"><fmt:formatNumber type="number" value="${data.refundFee }" maxFractionDigits="0"/>个</td>
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
								<td class="dt">退款完成时间：</td>
								<td colspan="3"><ct:time source="${data.auditTime }"/></td>
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
<script type="text/javascript" src="http://www.12580life.com/henan/foot_for_henan.jsp"></script> 
<script>
	$(function(){
		var actionType = '${actionType}';
		$('li a .hn_li').each(function(){
			$(this).remvoeClass(col_link);
		});
		if(actionType == 'INTEGRAL'){
			$('#hn_orderManager').addClass("col_link");
		}else if(actionType == 'COUPON'){
			$('#hn_couponManager').addClass("col_link");
		}
	});
</script>
</body>
</html>