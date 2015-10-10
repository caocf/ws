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
  	<c:if test="${type eq 1 }">
    <title>我的竞拍</title>
    </c:if>
    <c:if test="${type eq 2 }">
    <title>我的秒杀</title>
    </c:if>
    <script>
    	var web_url = '我的订单';
    	var webRoot = '${webRoot}';
    	function sendOrder(itemId,business){
            var selProps = "";
            var quantity = 1;
            var type = '${type}';
            $.ajax({
                url: "${ctx}/center/checkOutTime.chtml",
                data:"businessId="+business+"&type="+'${type}',
                dataType: "json",
                success:function(data){
                    if (data.type === 'success') {
						if(!data.flag){
							 $.cookie('confirm_itemId', itemId, { path: '/' });
					         $.cookie('confirm_props', selProps, { path: '/' });
					         $.cookie('confirm_quantity', quantity, { path: '/' });
					         $.cookie('confirm_type', type, { path: '/' });
					         $.cookie('confirm_businessId', business, { path: '/' });

					         window.location.href ="/order/buynow.chtml";
						}else{
							alert("已经过期，无法下单，请刷新页面！");
						}
                    }
                }
            });
            
    	}
    </script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<form action="gCheapOrder.chtml?type=${type }" method="post" id="form1">
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="orderId" id="orderId" value=""/>
<input type="hidden" name="type" id="type" value="${type }"/>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./gCheapOrder.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;
    <c:if test="${type eq 1 }">我的竞拍</c:if>
    <c:if test="${type eq 2 }">我的秒杀</c:if>
    </span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                        	<c:if test="${type eq 1 }">
                			    <li class="curr"><a href="#info1">我的竞拍</a> <span class="des">请你在竞拍完成后48小时之内完成支付，否则系统将会自动取消您的购买资格，谢谢！</span></li>
                		    </c:if>
                           <c:if test="${type eq 2 }">
                			    <li class="curr"><a href="#info1">我的秒杀</a> <span class="des">请你在秒杀完成后30分钟之内完成支付，否则系统将会自动取消您的购买资格，谢谢！</span></li>
                			</c:if>
                        </ul>
                    </div>
                </div>
                <!-- <div class="order_type">
					<div class="order_call">交易提醒</div>
					<div class="order_waitpay">
						<a href="orderManager.chtml?status=1&flag=0">待付款<span>(<label class="order_numred">${unpay }</label>)</span></a>
					</div>
					<div class="order_waitget">
						<a href="orderManager.chtml?status=3&flag=0">待收货<span>(<label class="order_numred">${sent }</label>)</span></a>
					</div>
					<div style="clear:both"></div>
				</div> -->
				<div class="order_search">
					<div class="ordersearch_wrap">
	                    <label>关键词：</label>
	                    <input type="text" name="key" value="${param.key }"/>&nbsp;&nbsp;&nbsp;&nbsp;
	                    <input type="text" id="inputStartTime" name="inputStartTime" class="txt Wdate" value="${param.inputStartTime}"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    	<input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    	-
                    	<input type="text" id="inputEndTime" name="inputEndTime" class="txt Wdate" value="${param.inputEndTime}"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
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
								<li class="order_state">状态</li>
								<li class="order_operate">操作</li>
							</ul>
						</div>
						<div class="order_items">
						<c:forEach items="${list.DATA}" var="data">
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
										<c:if test="${data.status ne 0}">
											<li class="item_number">订单号：${data.orderId }</li>
										</c:if>
										<c:if test="${data.status eq 0}">
											<li class="item_number">订单号：未下单</li>
										</c:if>
										<li class="item_buydate">购买时间：${data.createTime}</li>
										<li class="item_commerce">商户：<a href="<ct:path flag='3' id='${data.storeId}'/>">${data.storeName}</a></li>
									</ul>
								</div>
								<div class="order_item_detail">
									<ul>
										<li class="item_img">
											<a href="<ct:path flag='1' id='${data.goodsId }'/>"><img class="item_image" width="50" height="50" src="<ct:path flag='2' id='${data.goodsId }'/>N5/${data.imgPath }" /></a>
											<div class="item_img_decp">
												<p>${data.goodsName }</p>
											</div>
										</li>
										<li class="item_price">${data.price}
				                        </li>
										<li class="item_state">
											<c:if test="${data.status eq 0}">未下单</c:if>
											<c:if test="${data.status eq 1}">待付款</c:if>
											<c:if test="${data.status eq 2}">已付款</c:if>
											<c:if test="${data.status eq 3}">待收货</c:if>
											<c:if test="${data.status eq 4}">已完成</c:if>
											<c:if test="${data.status eq 5}">已取消</c:if>
										</li>
										
											<c:if test="${data.status eq 0}">
											<li class="item_operate">
											<c:if test="${data.isExpires eq 1 }">
												<span>已过期</span>
											</c:if>
											<c:if test="${data.isExpires eq 0 }">
												<a id="addOrder" href="javascript:void(0);" onclick="javascript:sendOrder(${data.goodsId },${data.businessId })">付款</a>
											</c:if>
											
											<div class="time_cdowb" style="margin-top:-42px">
											过期时间：
											<span style="color:red;font-weight:bold;">${data.buyTime }</span>
											</div>
											</li>
											</c:if>
											<c:if test="${data.status eq 1}">
											<li class="item_operate"><a href="orderView.chtml?orderId=${data.orderId}">查看</a>&nbsp;<a href="../order/payment-confirm.chtml?id=${data.orderId}">付款</a>&nbsp;<a class="remove" href="javascript:void(0);"  onclick="getOrderId(${data.orderId});">取消</a>
											</li>
											</c:if>
											<c:if test="${type eq 1 }">
												<c:if test="${data.status eq 2}"><li class="item_operate"><a style="cursor: pointer;" onclick="weiboShare('${data.orderId}','${data.goodsName }','竞拍','${data.payTime }')">微博晒单</a></li></c:if>
											</c:if>
											<c:if test="${type eq 2 }">
												<c:if test="${data.status eq 2}"><li class="item_operate"><a style="cursor: pointer;" onclick="weiboShare('${data.orderId}','${data.goodsName }','秒杀','${data.payTime }')">微博晒单</a></li></c:if>
											</c:if>
											<c:if test="${data.status eq 3}">
												<li class="item_operate">
													<a href="delivery.chtml?orderId=${data.orderId}">确认收货</a>
													<c:if test="${type eq 1 }">
														<div class="time_cdowb" style="margin-top:-42px"><a style="cursor: pointer;" onclick="weiboShare('${data.orderId}','${data.goodsName }','竞拍','${data.payTime }')">微博晒单</a></div>
													</c:if>
													<c:if test="${type eq 2 }">
														<div class="time_cdowb" style="margin-top:-42px"><a style="cursor: pointer;" onclick="weiboShare('${data.orderId}','${data.goodsName }','秒杀','${data.payTime }')">微博晒单</a></div>
													</c:if>
												</li>
											</c:if>
											<c:if test="${data.status eq 4}">
												<li class="item_operate">
													<a href="orderView.chtml?orderId=${data.orderId}">查看</a>
													<c:if test="${type eq 1 }">
														<div class="time_cdowb" style="margin-top:-42px"><a style="cursor: pointer;" onclick="weiboShare('${data.orderId}','${data.goodsName }','竞拍','${data.payTime }')">微博晒单</a></div>
													</c:if>
													<c:if test="${type eq 2 }">
														<div class="time_cdowb" style="margin-top:-42px"><a style="cursor: pointer;" onclick="weiboShare('${data.orderId}','${data.goodsName }','秒杀','${data.payTime }')">微博晒单</a></div>
													</c:if>
												</li>
											</c:if>
											<c:if test="${data.status eq 5}"><li class="item_operate"><a href="orderView.chtml?orderId=${data.orderId}">查看</a></li></c:if>
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
        var type = '${type}';
        if(type==='2'){
            $("#spike").addClass("col_link");
        }else if(type==='1'){
            $("#cheap").addClass("col_link");
        }
    })
</script>
</body>
</html>