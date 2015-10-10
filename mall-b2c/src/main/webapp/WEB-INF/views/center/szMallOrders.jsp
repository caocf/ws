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
    <title>历史订单</title>
    <script>
    	var web_url = '我的订单';
    	var webRoot = '${webRoot}';
    </script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<form action="szMallOrder.chtml" method="post" id="form1">
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="flag" id="flag" value="${flag }"/>
<input type="hidden" name="orderId" id="orderId" value=""/>

<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我购买的商品</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">历史订单</a></li>
                        </ul>
                    </div>
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
										<option value="01" ${status eq '01'?"selected":""}>待处理订单</option>
										<option value="02" ${status eq '02'?"selected":""}>已收款未发货</option>
										<option value="03" ${status eq '03'?"selected":""}>缺货订单</option>
										<option value="04" ${status eq '04'?"selected":""}>无效订单</option>
										<option value="05" ${status eq '05'?"selected":""}>应退款订单</option>
										<option value="06" ${status eq '06'?"selected":""}>已发货</option>
										<option value="07" ${status eq '07'?"selected":""}>已到货订单</option>
										<option value="08" ${status eq '08'?"selected":""}>已退款订单</option>
									</select>
								</li>
							</ul>
						</div>
						<div class="order_items">
						<c:forEach items="${list}" var="data">
							<div class="order_item">
								<div class="order_item_num">
									<ul>
										<li class="item_number">订单号：${data.orderId }</li>
										<li class="item_buydate">购买时间：<ct:time source="${data.orderTime}"/></li>
										<li class="item_commerce">商户：${data.storeName }</li>
									</ul>
								</div>
								<div class="order_item_detail">
									<ul>
										<li class="item_name">
										<span>${data.goodsName }</span>
										</li>
										<li class="item_price">${data.totalCost }</li>
										<li class="item_state">
											<c:if test="${data.status eq '01'}">待处理订单</c:if>
											<c:if test="${data.status eq '02'}">已收款未发货</c:if>
											<c:if test="${data.status eq '03'}">缺货订单</c:if>
											<c:if test="${data.status eq '04'}">无效订单</c:if>
											<c:if test="${data.status eq '05'}">应退款订单</c:if>
											<c:if test="${data.status eq '06'}">已发货</c:if>
											<c:if test="${data.status eq '07'}">已到货订单</c:if>
											<c:if test="${data.status eq '08'}">已退款订单</c:if>
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
     <jsp:include page="./left_menu.jsp">
     	<jsp:param name="menu" value="order" />
     </jsp:include>
    <span class="clr"></span></div>
</form>
<script type="text/javascript" src="../js/foot.js"></script>
</body>
</html>