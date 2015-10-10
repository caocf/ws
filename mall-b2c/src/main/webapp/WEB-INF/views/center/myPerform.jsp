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
    <script type="text/javascript" src="../js/pagescroll.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <title>我的演出票</title>
    <script>
    	var web_url = '我的票务';
    	var webRoot = '${webRoot}';
    	function doChangeType(type){
    		if(type==1){
    			$("#unions").show();
    			$(".rate_comments").hide();
    			$("#mocard").hide();
    			$("#userd").removeClass("btn_card");
       			$("#userd").addClass("btn");
       			$("#notUsed").removeClass("btn");
       			$("#notUsed").addClass("btn_card");
    			var url="http://i.12580life.com/userCenter/userCenter/myOrder/ticketOrderList.jsp?common_target=3";
    			$("#win").attr("src",url);
    			//window.location.href="../center/unions.chtml?url="+encodeURIComponent("我的礼金券"+","+url);
    		}else{
    			$("#win").attr("src","");
    			$("#unions").hide();
    			$(".rate_comments").show();
    			$("#userd").removeClass("btn_card");
       			$("#notUsed").addClass("btn");
       			$("#userd").removeClass("btn");
       			$("#notUsed").addClass("btn_card");
       			var src = "getPerformOrders.chtml";
        		$("#form1").attr("action", src);
        		$("#form1").submit();
    		}
   			
    	}
    </script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<form method="post" id="form1">
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="flag" id="flag" value="${flag }"/>
<input type="hidden" name="orderId" id="orderId" value=""/>

<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的演出票</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail" style="width: 812px;">
                <div class="tab">
                    <div  class="tab_nav" style="width: 810px;">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">我的演出票</a></li>
                        </ul>
                    </div>
                </div>
                 <div class="order_search">
				</div>
                <div class="order_content">
                	<div class="rate_comments">
						<div class="order_itemsnav" style="width:810px;">
							<ul>
								<li class="order_name">商品</li>
								<li class="order_price">订单金额（单位：元）</li>
								<li class="order_state">
									<select class="order_select" name="status" onchange="$('#form1').submit();">
										<option value="全部状态" ${status eq '全部状态'?"selected":""}>全部状态</option>
										<option value="1" ${status eq '1'?"selected":""}>待付款</option>
										<option value="2" ${status eq '2'?"selected":""}>已付款</option>
										<option value="3" ${status eq '3'?"selected":""}>待收货</option>
										<option value="4" ${status eq '4'?"selected":""}>已完成</option>
										<option value="5" ${status eq '5'?"selected":""}>已取消</option>
									</select>
								</li>
								<li class="order_operate">操作</li>
							</ul>
						</div>
						<div class="order_items" style="width:810px;">
						<c:forEach items="${list}" var="data">
							<div class="order_item" style="width:810px;">
							
								<div class="alert_windows" id="reback"> <a class="close" id ="close" href="javascript:void(0);" onclick="undo();"></a>
                                    <div class="content">
                                        
                              			<p>我们想知道您为何取消此订单，以便改进我们的服务：</p>
                                        <p><select id="selectId" onchange=displayTextare();>
                                        	<option onfocus="" class="col999">请选择你的理由</option>
                                        	<option value="现在不想买了">现在不想买了</option>
                                        	<option value="支付不成功">支付不成功</option>
                                            <option value="商品价格较贵">商品价格较贵</option>
                                            <option value="等待时间过长">等待时间过长</option>
                                            <option value="有商品缺货">有商品缺货</option>
                                            <option value="更换或添加新商品">更换或添加新商品</option>
                                            <option value="错误或重复下单">错误或重复下单</option>
                                            <option value="其他原因">其他原因 </option>
                                        </select></p>
                                        <p class="bodbccc" style="display:none;"> 
                                        <textarea id="textareaId" name="textareaId" onkeyup="subContent(this)" onkeydown="subContent(this);"  onfocus="if(value=='请在此处填写您的原因'){value=''}"  onblur="if(value==''){value='请在此处填写您的原因'}"  title="50字以内">请在此处填写您的原因</textarea><br />
                                      	<font color="red" size="2">内容在50字以内；</font>
                                        </p>
                                        <%--<p><span>本单应支付 <strong id='total'></strong><span id='coin'></span><br /></span></p>
                                        --%><a class="btn" href="javascript:void(0);" onclick="commonOrder('getPerformOrders');" >确定</a> <a class="btn goback" href="javascript:void(0);" onclick="undo();">取消</a>
                                    </div>
                                </div>
                                
                                
								<div class="order_item_num" style="width:810px;">
									<ul>
										<li class="item_number">订单号：${data.id }</li>
										<li class="item_buydate">购买时间：<ct:time source="${data.createTime}"/></li>
										<li class="item_commerce">提供商：${data.shopSubject }</li>
									</ul>
								</div>
								<div class="order_item_detail">
									<ul>
										<li class="item_img">
										<c:forEach items="${data.goodsInfos}" var="good">
											<a href="${good.url}"><img class="item_image" width="50" height="50" src="${good.imgPath}" /></a>&nbsp;演出时间：${good.ticketTime}
											<div class="item_img_decp">
												<p>${good.goodsSubject }</p>
											</div>
										</c:forEach>
										</li>
										<li class="item_price">${data.payAmount } <c:if test="${data.status eq 1 && (!empty data.payCoin || !empty data.payCash)}"><a href="" class="tip tip_scb"> </a>
				                        <div class="item_price_decp" style="display: none; background:#FFFF99">
				                          <p>已支付<c:if test="${!empty data.payCoin}">${data.payCoin }商城币</c:if><c:if test="${!empty data.payCash}">${data.payCash }现金</c:if></p>
				                        </div></c:if></li>
										<li class="item_state">
											<c:if test="${data.status eq 1}">待付款</c:if>
											<c:if test="${data.status eq 2}">已付款</c:if>
											<c:if test="${data.status eq 3}">待收货</c:if>
											<c:if test="${data.status eq 4}">已完成</c:if>
											<c:if test="${data.status eq 5}">已取消</c:if>
										</li>
										<li class="item_operate">
										<a href="orderView.chtml?orderId=${data.id }">查看</a>
											<c:if test="${data.status eq 1}"><a href="../order/payment-confirm.chtml?id=${data.id }">付款</a>&nbsp;<a class="remove" href="javascript:void(0);"  onclick="getOrderId(${data.id });">取消</a></c:if>
											<c:if test="${data.status eq 4 && data.isComment eq '0'}"><a href="javascript:comment('${data.id }','1','${data.shopId }');">评价</a></c:if>
											<c:if test="${data.status eq 5}"><a href="delOrder.chtml?orderId=${data.id }" class="deleteOrder">删除</a></c:if>
											<c:if test="${data.isRefund eq 1}"><a href="refundList.chtml?orderId=${data.id }">退款单</a></c:if>
											<c:if test="${data.status eq 2 || data.status eq 3 || data.status eq 4}">
											<div class="time_cdowb" style="margin-top:-42px"><a style="cursor: pointer;" onclick="weiboShare('${data.id}','${data.goodsInfos[0].goodsSubject}','我的演出票','${data.payTime }','${data.goodsInfos[0].ticketTime}')">微博晒单</a></div>
											</c:if>	
										</li>
									</ul>
								</div>
							</div>
						</c:forEach>
						${pageInfos }
						</div>
						
					    </div>
					    
					    </div>
            </div>
        </div>
    </div>
     <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
</form>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        $("#myPerform").addClass("col_link");
    })
</script>
</body>
</html>