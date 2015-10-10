<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/static.chtml"></script>
    <script type="text/javascript" src="../js/date-picker/WdatePicker.js" ></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/cart-common.js"></script>
    <script type="text/javascript" src="../js/ext.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/pagescroll.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <title>我购买的商品</title>
    <script>
    	var web_url = '我的订单';
    	var webRoot = '${webRoot}';
    	function goUnions(url,name){
    		window.location.href="../center/unions.chtml?url="+encodeURIComponent(name+","+url);
    	}
    </script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<form action="orderManager.chtml" method="post" id="form1">
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="flag" id="flag" value="${flag }"/>
<input type="hidden" name="isdelivery" id="isdelivery" value="${isdelivery }"/>
<input type="hidden" name="orderId" id="orderId" value=""/>

<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我购买的商品</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">我购买的商品</a><span class="des">找不到订单，请点击右边的分类查询<a href="szMallOrder.chtml"><span style="color:#FF0000">老商城订单</span></a>&nbsp;&nbsp;  
                            	<a id="iframe" href="javascript:void(0)" onclick="goUnions('http://i.12580life.com/userCenter/userCenter/myOrder/tuanCouponList.jsp?common_target=1',1)" ><span style="color:#FF0000">12580团订单</span></a></span>
                          
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="order_type">
					<div class="order_call">交易提醒</div>
					<div class="order_waitpay">
						<a href="orderManager.chtml?status=1&flag=0">待付款<span>(<label class="order_numred">${unpay }</label>)</span></a>
					</div>
					<div class="order_waitget">
						<a href="orderManager.chtml?status=3&flag=0">待收货<span>(<label class="order_numred">${sent }</label>)</span></a>
					</div>
					<div class="order_waitget">
						<a href="orderManager.chtml?isdelivery=1&flag=0">货到付款<span>(<label class="order_numred">${delivery }</label>)</span></a>
					</div>
					<div style="clear:both"></div>
				</div>
				<div class="order_search">
					<div class="ordersearch_wrap">
	                    <label>关键词：</label>
	                    <input type="text" name="key" value="${param.key }"/>
	                    <label>时间：</label>
	                    <input type="text" id="inputStartTime" name="inputStartTime" class="txt Wdate" value="${param.inputStartTime}"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    	<input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    	-
                    	<input type="text" id="inputEndTime" name="inputEndTime" class="txt Wdate" value="${param.inputEndTime}"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    	<input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
	                    <input type="button" class="btn_search" onclick="$('#form1').submit();" value="搜索" />
	                </div>
				</div>
                <div class="order_content">
                	<div class="rate_comments">
						<div class="order_itemsnav">
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
						
						<div class="order_items">
						<c:forEach items="${list}" var="data">
							<div class="order_item">
							
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
                                        --%><a class="btn" href="javascript:void(0);" onclick="cancelOrder();" >确定</a> <a class="btn goback" href="javascript:void(0);" onclick="undo();">取消</a>
                                    </div>
                                </div>
                                
                                
								<div class="order_item_num">
									<ul>
										<li class="item_number">订单号：${data.id } <c:if test="${data.payOnDelivery eq 1 }"> <p class="item_hdfk"></p></c:if></li>
										<li class="item_buydate">购买时间：<ct:time source="${data.createTime}"/></li>
										<li class="item_commerce">商户：<a href="<ct:path flag='3' id='${data.shopId }'/>">${data.shopSubject }</a></li>
									</ul>
								</div>
								<div class="order_item_detail">
									<ul>
										<li class="item_img">
										<c:forEach items="${data.goodsInfos}" var="good">
											<a href="<ct:path flag='1' id='${good.goodsId }'/>"><img class="item_image" width="50" height="50" src="<ct:path flag='2' id='${good.goodsId }'/>N5/${good.fileName }" /></a>
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
										<c:if test="${data.payOnDelivery eq 0 }">
											<c:if test="${data.status eq 1}">待付款</c:if>
											<c:if test="${data.status eq 2}">已付款</c:if>
											<c:if test="${data.status eq 3}">待收货</c:if>
											<c:if test="${data.status eq 4}">已完成</c:if>
											<c:if test="${data.status eq 5}">已取消</c:if>
										</c:if>
										<c:if test="${data.payOnDelivery eq 1 }">
											<c:if test="${data.status ne 3 && data.status ne 4 && data.status ne 5}">未发货</c:if>
											<c:if test="${data.status eq 3}">待收货</c:if>
											<c:if test="${data.status eq 4}">已完成</c:if>
											<c:if test="${data.status eq 5}">已取消</c:if>
										</c:if>
										</li>
										<li class="item_operate">
											<a href="orderView.chtml?orderId=${data.id }">查看</a>
											<c:if test="${data.payOnDelivery eq 0 }">
											<c:if test="${data.status eq 1}"><a href="../order/payment-confirm.chtml?id=${data.id }">付款</a>&nbsp;<a class="remove" href="javascript:void(0);"  onclick="getOrderId(${data.id });">取消</a></c:if>
											<c:if test="${data.status eq 3}"><a href="delivery.chtml?orderId=${data.id }">确认收货</a></c:if>
											<c:if test="${data.status eq 4 || data.status eq 2}"><a class="center_applyRefund" value="${data.id }" href="javascript:void(0);">申请退款</a></c:if>
											<c:if test="${data.status eq 4 && data.isComment eq '0'}"><a href="javascript:comment('${data.id }','1','${data.shopId }');">评价</a></c:if>
											<c:if test="${data.status eq 5}"><a href="delOrder.chtml?orderId=${data.id }" class="deleteOrder">删除</a></c:if>
											<c:if test="${data.isRefund eq 1}"><a href="refundList.chtml?orderId=${data.id }">退款单</a></c:if>	
											<c:if test="${data.status eq 2 || data.status eq 3 || data.status eq 4}"><div class="time_cdowb" style="margin-top:-42px"><a style="cursor: pointer;" onclick="weiboShare('${data.id}','${data.goodsInfos[0].goodsSubject}','我购买的商品','${data.payTime }')">微博晒单</a></div></c:if>	
											</c:if>
											<c:if test="${data.payOnDelivery eq 1 }">
											<c:if test="${data.status ne 3 && data.status ne 4 && data.status ne 5}"><a class="remove" href="javascript:void(0);"  onclick="getOrderId(${data.id });">取消</a></c:if>
											<c:if test="${data.status eq 4 && data.isComment eq '0'}"><a href="javascript:comment('${data.id }','1','${data.shopId }');">评价</a></c:if>
											<c:if test="${data.status eq 5}"><a href="delOrder.chtml?orderId=${data.id }" class="deleteOrder">删除</a></c:if>
											<c:if test="${data.status eq 3 || data.status eq 4}"><div class="time_cdowb" style="margin-top:-42px"><a style="cursor: pointer;" onclick="weiboShare('${data.id}','${data.goodsInfos[0].goodsSubject}','我购买的商品','${data.payTime }')">微博晒单</a></div></c:if>
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
 <form method="get" id="fm" action="/center/applyRefund.chtml" class="center_applyRefund">
 	<input type="hidden" value="" name="orderId" id="hidden_orderId"/>
 </form>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        $("#orderManager").addClass("col_link");
        
        $('a.center_applyRefund').click(function(){
        	$('#hidden_orderId').val($(this).attr('value'));
        	$('#fm').submit();
        });
    });
</script>
</body>
</html>