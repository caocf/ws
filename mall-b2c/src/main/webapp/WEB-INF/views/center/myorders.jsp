<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/base.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
<link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
<link href="../css/pro.css" rel="stylesheet" type="text/css" />
<link href="../js/lab/default/dialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/date-picker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.form.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/lab/jquery.dialog.js"></script>
<script type="text/javascript" src="../js/cart-common.js"></script>
<script type="text/javascript" src="../js/ext.js"></script>
<script type="text/javascript" src="../js/usercenter.js"></script>
<script type="text/javascript" src="../js/ad.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/base.js"></script>
<script type="text/javascript" src="../js/pagescroll.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<title>我购买的商品</title>
<script>
	var webRoot = '${webRoot}';
	
	var  web_url='我的订单';
	
	$(function(){
		$(".del_order_btn").click(function(){
			var orderId=$(this).attr("orderId");
			 $.ajax({
					url : '../center/delOrder.chtml',
					dataType : "json",
					cache : false,
					data:{
					  orderId:orderId
					},
					success : function(data) {
			              window.location.href = '../center/orderManager.chtml?'+getOrderManagerUrl();
					},
					error:function(e){
						console.debug(e);
					}
				});
		});
		
		
		$(".order_select").change(function(){
			var _hidden_data='<input type="hidden" name="status" value="'+$(this).val()+'">';
			_hidden_data+='<input type="hidden" name="flag" value="0">';
			$('#form1').append(_hidden_data);
			$('#form1').submit();
		});
		
		$(".cancel_order_btn").click(function(){
			$("#reback_sure_btn").attr("orderType",$(this).attr("orderType"));
			$("#reback").show();
			return false;
		});
		
		$.ajax({
			url : '../center/logUserCenterVisit.chtml',
			dataType : "json",
			cache : false,
			success : function(data) {
				console.debug(data);
			if (data!="visited") {
				var _guide_html='<a href="" id="welcome1"><img src="../img/welcome1.png"></a>'+
								'<a href="" id="welcome2"><img src="../img/welcome2.png"></a>'+
								'<a href="" id="welcome3"><img src="../img/welcome3.png"></a>';
				$(".lightbox-shadow").show();
				$(".guide_div").append(_guide_html);
				 $(".lightbox-shadow").height($(document).height());
				 $(".lightbox-welcome a#welcome1,.lightbox-welcome a#welcome2").click(function(){
				    $(this).hide().next().show();
				    return false;
				 })
				 $(".lightbox-welcome a#welcome3").click(function(){
				    $(".lightbox-welcome,.lightbox-shadow").hide();
				    return false;
				   })
				
				}
				
			},
			error:function(e){
				console.debug(e);
			}
		});
		
	});
	
	function doSubmitOnTimeChange(){
		//生成其他条件
		$(".condition_btn").each(function(){
			if($(this).hasClass("active")){
				var _hidden_data="";
				var $pro=$($(this).attr("property").split("&"));
				$pro.each(function(){
				   var pro_item=String(this).split("=");
				   _hidden_data+='<input type="hidden" value="'+pro_item[1]+'" name="'+pro_item[0]+'">';
				});
				$("#form1").append(_hidden_data);
			}
		});
		$('#form1').submit();
	}
	
	function doChangeType(){ 
			$("#unions").show();
			var url="http://i.12580life.com/userCenter/userCenter/myOrder/tuanCouponList.jsp?common_target=1";
			$("#win").attr("src",url); 	
			$(".usecenter_content").hide();
			$(".condition_time").hide();
			$(".condition_search").hide();
			$(".groupbuy_source_life").removeClass("active");
			$(this).addClass("active");
	}
</script>
</head>
<body id="usercenter">
	<script type="text/javascript" src="../js/head.js"></script>
	<form id="form1" method="post" action="orderManager.chtml">
		<input type="hidden" value="" id="curpage" name="curpage"> 
		<input type="hidden" value="" id="isdelivery" name="isdelivery">
		<input type="hidden" value="" id="orderId" name="orderId"> 
		<input type="hidden" value="${select_flag}" name="select_flag">
		<input type="hidden" value="${date_flag}" name="date_flag">
		<c:choose>
			<c:when test="${select_flag=='1'}">
				<input type="hidden" value="0,90" name="orderTypes">
			</c:when>
			<c:when test="${select_flag=='2'}">
				<input type="hidden" value="3,20" name="orderTypes">
			</c:when>
			<c:when test="${select_flag=='3'}">
				<input type="hidden" value="1" name="orderTypes">
			</c:when>
			<c:when test="${select_flag=='4'}">
				<input type="hidden" value="2" name="orderTypes">
			</c:when>
			<c:when test="${select_flag=='5'}">
				<input type="hidden" value="10" name="orderTypes">
			</c:when>
			<c:when test="${select_flag=='6'}">
				<input type="hidden" value="60" name="orderTypes">
			</c:when>
			<c:when test="${select_flag=='7'}">
				<input type="hidden" value="1000" name="orderTypes">
			</c:when>
		</c:choose>
						<div class="wrapper mauto">
							<div class="breadcrumb">
								<strong><a href="../">首页</a> </strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a> </span><span>&nbsp;&gt;&nbsp;我购买的商品</span>
							</div>
							<div class="lightbox-welcome guide_div">
							</div>
							<div class="fr w800" id="main">
								<h1 class="usecenter_title">我的订单</h1>
								<div id="usercenter_main">
									<div class="usercenter_menu">
										<dl>
											<dt>交易类型</dt>
											<dd>
												<a class="${select_flag=='0'?'active':''}" href="orderManager.chtml?select_flag=0">全部</a>| <a
													class="${select_flag=='1'?'active':''}" href="orderManager.chtml?orderTypes=0,90&select_flag=1">商品</a>| <a
													class="${select_flag=='2'?'active':''}" href="orderManager.chtml?orderTypes=3,20&select_flag=2">团购</a>| <a
													class="${select_flag=='3'?'active':''}" href="orderManager.chtml?orderTypes=1&select_flag=3">竞拍</a>| <a
													class="${select_flag=='4'?'active':''}" href="orderManager.chtml?orderTypes=2&select_flag=4">秒杀</a>| <a
													class="${select_flag=='5'?'active':''}" href="orderManager.chtml?orderTypes=10&select_flag=5">福利</a>| <a
													class="${select_flag=='6'?'active':''}" href="orderManager.chtml?orderTypes=60&select_flag=6">代金券</a>| <a
													class="${select_flag=='7'?'active':''}" href="orderManager.chtml?orderTypes=1000&select_flag=7">礼品卡</a>
											</dd>
										</dl>
										<c:if test="${select_flag=='0'||select_flag=='1'}">
											<dl>
												<dt>交易提醒</dt>
												<dd>
													<a class="condition_btn  ${status==1 && flag==0?'active':''}" property="status=1&flag=0">待付款 <span>${unpay}</span> </a> <a
														class="condition_btn ${status==3 && flag==0?'active':''}" property="status=3&flag=0">待收货<span>${sent}</span> </a>
												</dd>
											</dl>
										</c:if>
										<c:if test="${select_flag=='2'}">
											<dl>
												<dt>团购来源</dt>
												<dd>
													<a class="condition_btn groupbuy_source_life ${groupbuy_source_flag=='0'?'active':''}" property="groupbuy_source_flag=0">mo生活团购</a>
													<a onclick="doChangeType()">商户联盟团购</a>
												</dd>
											</dl>
										</c:if>
										<c:if test="${select_flag=='6'}">
											<dl>
												<dt>代金券来源</dt>
												<dd>
													<a class="condition_btn ${djq_source_flag=='0'?'active':''}" property="djq_source_flag=0">新商城</a> <a
														class="condition_btn ${djq_source_flag=='1'?'active':''}" property="djq_source_flag=1">商盟</a> <a
														class="condition_btn ${djq_source_flag=='2'?'active':''}" property="djq_source_flag=2">联动</a>
												</dd>
											</dl>
										</c:if>
										<dl class="condition_time">
											<dt>交易时间</dt>
											<dd>
												<a class="date_range date_three_day ${date_flag=='date_three_day_flag'?'active':''}">三天内</a> 
												<a class="date_range date_week ${date_flag=='date_week_flag'?'active':''}">一周内</a> 
												<a class="date_range date_tree_month ${date_flag=='date_tree_month_flag'?'active':''}">三个月内</a>
												
												<input type="text" id="inputStartTime" name="inputStartTime" onchange="doSubmitOnTimeChange()" class="txt Wdate"
													value="${param.inputStartTime}" readOnly
													onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'%y-%M-%d\'}'})" /> <input
													type="hidden" name="startTime" id="startTime" value="${param.startTime}" /> - <input type="text" id="inputEndTime"
													onchange="doSubmitOnTimeChange()" name="inputEndTime" class="txt Wdate" value="${param.inputEndTime}" readOnly
													onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'%y-%M-%d'})" /> <input
													type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
											</dd>
										</dl>
										<dl class="condition_search">
											<dt>订单编号及关键词</dt>
											<dd>
												<input type="text" name="key" class="txt" value="${param.key}" autocomplete="off"> <input class="search_order_btn"
													type="button" value="搜索" />
											</dd>
										</dl>
									</div>
								</div>
								<div class=" usecenter_content">
									<div class="usecenter_order_menu">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td>商品</td>
												<td width="80">数量</td>
												<td width="120">订单金额（元）</td>
												<td width="120"><select class="order_select">
														<option value="" ${status eq '交易状态'?"selected":""}>交易状态</option>
														<option value="1" ${status eq '1'?"selected":""}>待付款</option>
														<option value="6" ${status eq '6'?"selected":""}>支付中</option>
														<option value="2" ${status eq '2'?"selected":""}>已付款</option>
														<option value="3" ${status eq '3'?"selected":""}>待收货</option>
														<option value="4" ${status eq '4'?"selected":""}>已完成</option>
														<option value="5" ${status eq '5'?"selected":""}>已取消</option>
														<option value="7" ${status eq '7'?"selected":""}>有退款申请</option>
												</select></td>
												<td width="120">操作</td>
											</tr>
										</table>
									</div>
									<div class="usecenter_order_main">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<c:forEach items="${orderlist}" var="order">
												<tr>
													<th>订单编号：${order.id}</th>
													<th width="200" colspan="2">购买时间：<ct:time source="${order.createTime}" /></th>
													<th width="240" colspan="2">商户：<a href="..<ct:path flag='3' id='${order.shopId}'/>">${order.shopSubject}</a></th>
												</tr>
												<c:forEach items="${order.goodsInfos}" var="good" varStatus="status">
													<tr>
														<td>
															<dl>
																<dt>
																	<a href="../quickphoto/item.chtml?item_id=${good.goodsId }&verison_id=${good.goodsVersion }" title="${good.goodsSubject}">
																		<img class="order_img" alt="${good.goodsSubject}"
																		src="..<ct:path flag='2' id='${good.goodsId }'/>N5/${good.fileName }">
																	</a>
																</dt>
																<dd>
																	<a href="../quickphoto/item.chtml?item_id=${good.goodsId }&verison_id=${good.goodsVersion }" title="${good.goodsSubject}">${good.goodsSubject}</a>
																	<p>${good.goodsDescription}</p>
																</dd>
															</dl></td>
														<td width="80">${good.count}</td>
														<c:if test="${status.index==0}">
															<td width="120" rowspan="${fn:length(order.goodsInfos)}"><span class="price"> ${order.payAmount} <c:if
																		test="${order.orderStatus.actOrderStatus eq 1 && (!empty order.payCoin || !empty order.payCash)}">
																		<a href="" class="tip tip_scb"> </a>
																		<div class="item_price_decp" style="display: none; background:#FFFF99">
																			<p>
																				已支付
																				<c:if test="${!empty order.payCoin}">${order.payCoin }商城币</c:if>
																				<c:if test="${!empty order.payCash}">${order.payCash }现金</c:if>
																			</p>
																		</div>
																	</c:if> </span></td>
															<td width="120" rowspan="${fn:length(order.goodsInfos)}"><c:if
																	test="${order.orderType == 0 ||order.orderType == 90 ||order.orderType == 10}">
																	<c:if test="${order.payOnDelivery eq 0 }">
																		<c:if test="${order.orderStatus.hasRefund eq 1}">有退款申请</c:if>
																		<c:if test="${order.orderStatus.hasRefund ne 1}">
																			<c:if test="${order.orderStatus.actOrderStatus eq 1}">待付款</c:if>
																			<c:if test="${order.orderStatus.actOrderStatus eq 2}">已付款</c:if>
																			<c:if test="${order.orderStatus.actOrderStatus eq 3}">待收货</c:if>
																			<c:if test="${order.orderStatus.actOrderStatus eq 4}">已完成</c:if>
																			<c:if test="${order.orderStatus.actOrderStatus eq 5}">已取消</c:if>
																			<c:if test="${order.orderStatus.actOrderStatus eq 6}">支付中</c:if>
																		</c:if>
																	</c:if>
																	<c:if test="${order.payOnDelivery eq 1 }">
																		<c:if
																			test="${order.orderStatus.actOrderStatus ne 3 && order.orderStatus.actOrderStatus ne 4 && order.orderStatus.actOrderStatus ne 5}">未发货</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 3}">待收货</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 4}">已完成</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 5}">已取消</c:if>
																	</c:if>
																</c:if> <c:if
																	test="${order.orderType == 3 ||order.orderType == 20 ||order.orderType == 1||order.orderType == 2||order.orderType == 60}">
																	<c:if test="${order.payOnDelivery eq 0 }">
																		<c:if test="${order.orderStatus.actOrderStatus eq 1}">待付款</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 2}">已付款</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 3}">待收货</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 4}">已完成</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 5}">已取消</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 6}">支付中</c:if>
																	</c:if>
																	<c:if test="${order.payOnDelivery eq 1 }">
																		<c:if
																			test="${order.orderStatus.actOrderStatus ne 3 && order.orderStatus.actOrderStatus ne 4 && order.orderStatus.actOrderStatus ne 5}">未发货</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 3}">待收货</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 4}">已完成</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 5}">已取消</c:if>
																	</c:if>
																</c:if> <c:if test="${order.orderType == 1000 }">
																	<%-- 礼品卡--%>
																	<c:if test="${order.orderStatus.actOrderStatus eq 1}">待付款</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 2}">已兑换</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 3}">待收货</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 4}">已完成</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 5}">已取消</c:if>
																</c:if></td>
															<td width="120" rowspan="${fn:length(order.goodsInfos)}"><c:if test="${order.orderType == 0||order.orderType == 90 }">
																	<a class="dis_block" href="orderView.chtml?orderId=${order.id }">查看</a>
																	<c:if test="${order.payOnDelivery eq 0 }">
																		<c:if test="${order.orderStatus.actOrderStatus eq 1 || order.orderStatus.actOrderStatus eq 6}">
																			<a class="dis_block" href="../order/payment-confirm.chtml?id=${order.id }">付款</a>
																			<a orderType="s_order" class="dis_block cancel_order_btn" href="javascript:void(0);" onclick="getOrderId(${order.id });">取消</a>
																		</c:if>
																		<c:if test="${order.orderStatus.hasRefund ne 1}">
																			<c:if test="${order.orderStatus.actOrderStatus eq 2 || order.orderStatus.actOrderStatus eq 4}">
																				<a class="dis_block" value="${order.id }" href="javascript:void(0);">退款申请</a>&nbsp;</c:if>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 3}">
																			<a class="dis_block" href="delivery.chtml?orderId=${order.id }">确认收货</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 4 && order.isComment eq '0'}">
																			<a class="dis_block" href="javascript:comment('${order.id }','1','${order.shopId }');">评价</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 5}">
																			<a class="dis_block del_order_btn" class="deleteOrder" orderId="${order.id}">删除</a>
																		</c:if>
																		<c:if test="${order.orderStatus.hasRefund eq 1}">
																			<a class="dis_block" href="refundList.chtml?orderId=${order.id }">退款单</a>
																		</c:if>
																		<c:if
																			test="${order.orderStatus.actOrderStatus eq 2 || order.orderStatus.actOrderStatus eq 3 || order.orderStatus.actOrderStatus eq 4}">
																			<div class="time_cdowb" style="margin-top:-42px">
																				<a class="dis_block" style="cursor: pointer;"
																					onclick="weiboShare('${order.id}','${order.goodsInfos[0].goodsSubject}','我购买的商品','${order.payTime }')">微博晒单</a>
																			</div>
																		</c:if>
																	</c:if>
																	<c:if test="${order.payOnDelivery eq 1 }">
																		<c:if
																			test="${order.orderStatus.actOrderStatus ne 3 && order.orderStatus.actOrderStatus ne 4 && order.orderStatus.actOrderStatus ne 5}">
																			<a class="dis_block cancel_order_btn" href="javascript:void(0);" onclick="getOrderId(${order.id });">取消</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 4 && order.isComment eq '0'}">
																			<a class="dis_block" href="javascript:comment('${order.id }','1','${order.shopId }');">评价</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 5}">
																			<a class="dis_block del_order_btn" class="deleteOrder" orderId="${order.id}">删除</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 3 || order.orderStatus.actOrderStatus eq 4}">
																			<a class="dis_block" style="cursor: pointer;"
																				onclick="weiboShare('${order.id}','${order.goodsInfos[0].goodsSubject}','我购买的商品','${order.payTime }')">微博晒单</a>
																		</c:if>
																	</c:if>
																</c:if> <c:if test="${order.orderType == 3||order.orderType == 20 }">
																	<a class="dis_block" href="orderView.chtml?orderId=${order.id }">查看</a>
																	<c:if test="${order.orderStatus.actOrderStatus eq 1}">
																		<a class="dis_block" href="../order/payment-confirm.chtml?id=${order.id }">付款</a>
																   <a class="dis_block cancel_order_btn" href="javascript:void(0);" onclick="getOrderId(${order.id });">取消</a>
																	</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 3}">
																		<a class="dis_block" href="delivery.chtml?orderId=${order.id }">确认收货</a>
																	</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 4 && order.isComment eq '0'}">
																		<a class="dis_block" href="javascript:comment('${order.id }','1','${order.shopId }');">评价</a>
																	</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 5}">
																		<a class="dis_block del_order_btn" orderId="${order.id}" class="deleteOrder">删除</a>
																	</c:if>
																	<c:if test="${order.isRefund eq 1}">
																		<a class="dis_block" href="refundList.chtml?orderId=${order.id }">退款单</a>
																	</c:if>
																</c:if> <c:if test="${order.orderType == 1||order.orderType == 1}">
																	<c:if test="${order.orderStatus.actOrderStatus eq 0}">
																		<c:if test="${order.isExpires eq 1 }">
																			<span>已过期</span>
																		</c:if>
																		<c:if test="${order.isExpires eq 0 }">
																			<a id="addOrder" href="javascript:void(0);" onclick="javascript:sendOrder(${order.goodsId },${order.businessId })">付款</a>
																		</c:if>
																		<div class="time_cdowb" style="margin-top:-42px">
																			过期时间： <span style="color:red;font-weight:bold;">${order.buyTime }</span>
																		</div>
																	</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 1}">
																		<a class="dis_block" href="orderView.chtml?orderId=${order.orderId}">查看</a>
																   <a class="dis_block" href="../order/payment-confirm.chtml?id=${order.orderId}">付款</a>
																   <a class="dis_block cancel_order_btn" href="javascript:void(0);" onclick="getOrderId(${order.orderId});">取消</a>
																	</c:if>
																	<c:if test="${type eq 1 }">
																		<c:if test="${order.orderStatus.actOrderStatus eq 2}">
																			<a class="dis_block" style="cursor: pointer;"
																				onclick="weiboShare('${order.orderId}','${order.goodsName }','竞拍','${order.payTime }')">微博晒单</a>
																		</c:if>
																	</c:if>
																	<c:if test="${type eq 2 }">
																		<c:if test="${order.orderStatus.actOrderStatus eq 2}">
																			<a class="dis_block" style="cursor: pointer;"
																				onclick="weiboShare('${order.orderId}','${order.goodsName }','秒杀','${order.payTime }')">微博晒单</a>
																		</c:if>
																	</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 3}">
																		<a class="dis_block" href="delivery.chtml?orderId=${order.orderId}">确认收货</a>
																		<c:if test="${type eq 1 }">
																			<a class="dis_block" style="cursor: pointer;"
																				onclick="weiboShare('${order.orderId}','${order.goodsName }','竞拍','${order.payTime }')">微博晒单</a>
																		</c:if>
																		<c:if test="${type eq 2 }">
																			<a class="dis_block" style="cursor: pointer;"
																				onclick="weiboShare('${order.orderId}','${order.goodsName }','秒杀','${order.payTime }')">微博晒单</a>
																		</c:if>
																	</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 4}">
																		<a href="orderView.chtml?orderId=${order.orderId}">查看</a>
																		<c:if test="${type eq 1 }">
																			<a class="dis_block" style="cursor: pointer;"
																				onclick="weiboShare('${order.orderId}','${order.goodsName }','竞拍','${order.payTime }')">微博晒单</a>
																		</c:if>
																		<c:if test="${type eq 2 }">
																			<a class="dis_block" style="cursor: pointer;"
																				onclick="weiboShare('${order.orderId}','${order.goodsName }','秒杀','${order.payTime }')">微博晒单</a>
																		</c:if>
																	</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 5}">
																		<a class="dis_block" href="orderView.chtml?orderId=${order.orderId}">查看</a>
																	</c:if>
																</c:if> <c:if test="${order.orderType == 10}">
																	<a class="dis_block" href="orderView.chtml?orderId=${order.id }&menucode=MENU_CODE_ORDER_WELFARE">查看</a>
																	<c:if test="${order.orderStatus.actOrderStatus eq 1 || order.orderStatus.actOrderStatus eq 6}">
																		<a class="dis_block" href="../order/payment-confirm.chtml?id=${order.id }">付款</a>
																   <a  orderType="s_order" class="dis_block cancel_order_btn" href="javascript:void(0);" onclick="getOrderId(${order.id });">取消</a>
																	</c:if>
																	<c:if test="${order.orderStatus.actOrderStatus eq 3}">
																		<a class="dis_block" href="delivery.chtml?orderId=${order.id }">确认收货</a>
																	</c:if>
																	<c:if test="${order.orderStatus.hasRefund ne 1}">
																		<c:if test="${order.orderStatus.actOrderStatus eq 2 }">
																			<a class="dis_block center_applyRefund" value="${order.id }" href="javascript:void(0);">退款申请</a>&nbsp;
																	</c:if>
																	</c:if>
																</c:if> <c:if test="${order.orderType == 60}">
																	<a class="dis_block" href="orderView.chtml?orderId=${order.id }" class="col_link">查看</a>
																	<c:if test="${order.payOnDelivery eq 0 }">
																		<c:if test="${order.orderStatus.actOrderStatus eq 1}">
																			<a class="dis_block" href="../order/payment-confirm.chtml?id=${order.id }">付款</a>
																				<a class="dis_block" class="cancel_order_btn" href="javascript:void(0);" onclick="getOrderId(${order.id });">取消</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 3}">
																			<a class="dis_block" href="delivery.chtml?orderId=${order.id }">确认收货</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 4 && order.isComment eq '0'}">
																			<a class="dis_block" href="javascript:comment('${order.id }','1','${order.shopId }');">评价</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 5}">
																			<a class="deleteOrder dis_block del_order_btn" orderId="${order.id}">删除</a>
																		</c:if>
																		<c:if test="${order.isRefund eq 1}">
																			<a class="dis_block" href="refundList.chtml?orderId=${order.id }">退款单</a>
																		</c:if>
																		<c:if
																			test="${order.orderStatus.actOrderStatus eq 2 || order.orderStatus.actOrderStatus eq 3 || order.orderStatus.actOrderStatus eq 4}">
																			<div class="time_cdowb" style="margin-top: -42px">
																				<a class="dis_block col_link" style="cursor: pointer;"
																					onclick="weiboShare('${order.id}','${order.goodsInfos[0].goodsSubject}','我购买的商品','${order.payTime }')">微博晒单</a>
																			</div>
																		</c:if>
																	</c:if>
																	<c:if test="${order.payOnDelivery eq 1 }">
																		<c:if
																			test="${order.orderStatus.actOrderStatus ne 3 && order.orderStatus.actOrderStatus ne 4 && order.orderStatus.actOrderStatus ne 5}">
																			<a class="dis_block cancel_order_btn" href="javascript:void(0);" onclick="getOrderId(${order.id });">取消</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 4 && order.isComment eq '0'}">
																			<a class="dis_block" href="javascript:comment('${order.id }','1','${order.shopId }');">评价</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 5}">
																			<a class="deleteOrder dis_block del_order_btn" orderId="${order.id}">删除</a>
																		</c:if>
																		<c:if test="${order.orderStatus.actOrderStatus eq 3 || order.orderStatus.actOrderStatus eq 4}">
																			<div class="time_cdowb" style="margin-top: -42px">
																				<a class="col_link dis_block" style="cursor: pointer;"
																					onclick="weiboShare('${order.id}','${order.goodsInfos[0].goodsSubject}','我购买的商品','${order.payTime }')">微博晒单</a>
																			</div>
																		</c:if>
																	</c:if>
																</c:if> <c:if test="${order.orderType == 1000}">
																	<a class="dis_block" href="orderView.chtml?orderId=${order.id }">查看</a>
																	<c:if test="${order.status eq 3}">
																		<a class="dis_block" href="delivery.chtml?orderId=${order.id }">确认收货</a>
																	</c:if>
																	<c:if test="${order.status eq 4 && order.isComment eq '0'}">
																		<a class="dis_block" href="javascript:comment('${order.id }','1','${order.shopId }');">评价</a>
																	</c:if>
																</c:if></td>
														</c:if>
													</tr>
												</c:forEach>
											</c:forEach>
										</table>
									</div>
									${pageInfos}
								</div>
								<div id="unions" class="fr  w750" style="display:none;">
									<iframe style="width: 810px;height:1180px;margin-left: -62px;" id="win" name="win" frameborder="0" scrolling="no" src=""></iframe>
								</div>
							</div>
							<%@include file="left_menu.jsp"%>
							<span class="clr"></span>
						</div>
	</form>
	<script type="text/javascript" src="../js/foot.js"></script>
	<div class="alert_windows" id="reback">
		<a class="close" id="close" href="javascript:void(0);" onclick="undo();"></a>
		<div class="content">
			<p>我们想知道您为何取消此订单，以便改进我们的服务：</p>
			<p>
				<select id="selectId" onchange=displayTextare();>
					<option onfocus="" class="col999">请选择你的理由</option>
					<option value="现在不想买了">现在不想买了</option>
					<option value="支付不成功">支付不成功</option>
					<option value="商品价格较贵">商品价格较贵</option>
					<option value="等待时间过长">等待时间过长</option>
					<option value="有商品缺货">有商品缺货</option>
					<option value="更换或添加新商品">更换或添加新商品</option>
					<option value="错误或重复下单">错误或重复下单</option>
					<option value="其他原因">其他原因</option>
				</select>
			</p>
			<p class="bodbccc" style="display:none;">
				<textarea id="textareaId" name="textareaId" onkeyup="subContent(this)" onkeydown="subContent(this);"
					onfocus="if(value=='请在此处填写您的原因'){value=''}" onblur="if(value==''){value='请在此处填写您的原因'}" title="50字以内">请在此处填写您的原因</textarea>
				<br /> <font color="red" size="2">内容在50字以内；</font>
			</p>
			
			<a id="reback_sure_btn" class="btn" href="javascript:void(0);">确定</a> 
			<a class="btn goback" href="javascript:void(0);" onclick="undo();">取消</a>
		</div>
	</div>
	<div class="lightbox-shadow" style="height: 641px;display:none"></div>
</body>
</html>