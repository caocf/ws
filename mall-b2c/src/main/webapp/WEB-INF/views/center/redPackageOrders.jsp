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
    <link href="../js/lab/default/dialog.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/date-picker/WdatePicker.js" ></script>
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
    <title>我的福利</title>
    <script type="text/javascript">
    var web_url = '我的订单';
    var webRoot = '${webRoot}';
    function queryRedMoney(){
    	var url = '../center/queryRedPackage.chtml';
 			$.ajax({
 			      url: url,
 			      dataType: 'text',
 			      cache: false,
 			      async: false,
 			      success:function(res){
 				  	if(null!=res && ""!=res ){
 				  		if(res == 'nomoney'){
 				  		 $("#redMoney").html("系统忙，请稍后！");
 				  		}else{
 				  		 $("#redMoney").html("红包余额："+res+"元");
 				  		}
 					  }else{
 						 $("#redMoney").html("红包余额：0元");
 					  }
 			      },
 			      error:function(res){
 			    	 $("#redMoney").html("红包余额：0元");
 			      }
 			 });
    		
    }
    </script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<form action="redPackage.chtml" method="post" id="form1">
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="flag" id="flag" value="${flag }"/>
<input type="hidden" name="orderId" id="orderId" value=""/>

<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span class="_top_span">&nbsp;&gt;&nbsp;我的福利</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">我的福利</a><%--&nbsp;&nbsp;&nbsp;&nbsp;
                             <c:if test="${red eq 1}"><a style="text-decoration: underline;color:#0000ff;" href="javascript:void(0)" onclick="queryRedMoney()">红包余额查询</a>&nbsp;&nbsp;&nbsp;&nbsp;<span id="redMoney"></span></c:if></span>
                            --%></li>
                        </ul>
                    </div>
                </div>
                <div class="order_search">
				</div>
                <c:if test="${red eq 1}">
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
                                        --%><a class="btn" href="javascript:void(0);" onclick="cancelOrder('MENU_CODE_ORDER_WELFARE');" >确定</a> <a class="btn goback" href="javascript:void(0);" onclick="undo();">取消</a>
                                    </div>
                                </div>
                                
                                
								<div class="order_item_num">
									<ul>
										<li class="item_number">订单号：${data.id }</li>
										<li class="item_buydate">购买时间：<ct:time source="${data.createTime}"/></li>
										<li class="item_commerce">商户：<a href="<ct:path flag='3' id='${data.shopId }'/>">${data.shopSubject }</a></li>
									</ul>
								</div>
								<div class="order_item_detail">
									<ul>
										<li class="item_img">
										<c:forEach items="${data.goodsInfos}" var="good">
											<a href="javascript:void(0)"><img class="item_image" width="50" height="50" src="<ct:path flag='2' id='${good.goodsId }'/>N5/${good.fileName }" /></a>
											<div class="item_img_decp">
												<p>${good.goodsSubject }</p>
											</div>
										</c:forEach>
										</li>
										<li class="item_price">${data.payAmount }</li>
										<li class="item_state">
											<c:if test="${data.orderStatus.hasRefund eq 1}">有退款申请</c:if>
												<c:if test="${data.orderStatus.hasRefund ne 1}">
													<c:if test="${data.orderStatus.actOrderStatus eq 1}">待付款</c:if>
													<c:if test="${data.orderStatus.actOrderStatus eq 2}">已付款</c:if>
													<c:if test="${data.orderStatus.actOrderStatus eq 3}">待收货</c:if>
													<c:if test="${data.orderStatus.actOrderStatus eq 4}">已完成</c:if>
													<c:if test="${data.orderStatus.actOrderStatus eq 5}">已取消</c:if>
													<c:if test="${data.orderStatus.actOrderStatus eq 6}">支付中</c:if>
												</c:if>
										</li>
										<li class="item_operate">
											<a href="orderView.chtml?orderId=${data.id }&menucode=MENU_CODE_ORDER_WELFARE">查看</a>
											<c:if test="${data.orderStatus.actOrderStatus eq 1 || data.orderStatus.actOrderStatus eq 6}"><a href="../order/payment-confirm.chtml?id=${data.id }">付款</a>&nbsp;<a class="remove" href="javascript:void(0);"  onclick="getOrderId(${data.id });">取消</a></c:if>
											<c:if test="${data.orderStatus.actOrderStatus eq 3}"><a href="delivery.chtml?orderId=${data.id }">确认收货</a></c:if>
											<c:if test="${data.orderStatus.hasRefund ne 1}">
												<c:if test="${data.orderStatus.actOrderStatus eq 2 }"><a class="center_applyRefund" value="${data.id }" href="javascript:void(0);">退款申请</a>&nbsp;</c:if>
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
                </c:if>
				 <c:if test="${red eq 2}">
                 <div class="content">
            	<p style="height: 100px;margin-top: 50px;text-align: center;">数据获取错误，如有任何问题，请及时联系客服4001511511！</p>
           	 </div>
				</c:if>
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
        $("#redpackage").addClass("col_link");
        $('a.center_applyRefund').click(function(){
        	$('#hidden_orderId').val($(this).attr('value'));
        	var obj = $('div#sidebar').find('ul li a.col_link');
        	$.removeCookie('backLeftId');
 	        $.removeCookie('backLeftHref');
        	$.cookie("backLeftId", obj.attr('id'));
        	$.cookie("backLeftHref", obj.attr('href'));
        	$('#fm').submit();
        });
    });
</script>
</body>
</html>