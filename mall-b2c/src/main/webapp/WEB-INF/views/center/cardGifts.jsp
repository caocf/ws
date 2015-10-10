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
    <title>我的礼金券</title>
    <script>
    	var web_url = '我的礼金券';
    
    	var webRoot = '${webRoot}';
    	function doChangeType(type){
    		if(type==1){
    			$("#unions").show();
    			$("#mocard").hide();
    			$("#userd").removeClass("btn_card");
       			$("#userd").addClass("btn");
       			$("#notUsed").removeClass("btn");
       			$("#notUsed").addClass("btn_card");
    			var url="http://i.12580life.com/userCenter/userCenter/myLife/myVoucher.jsp?common_target=16";
    			$("#win").attr("src",url);
    			//window.location.href="../center/unions.chtml?url="+encodeURIComponent("我的礼金券"+","+url);
    		}else{
    			$("#win").attr("src","");
    			$("#unions").hide();
    			$("#mocard").show();
    			$("#userd").removeClass("btn_card");
       			$("#notUsed").addClass("btn");
       			$("#userd").removeClass("btn");
       			$("#notUsed").addClass("btn_card");
       			var src = "cardGifts.chtml";
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
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的礼金券</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail" style="width: 812px;">
                <div class="tab">
                    <div  class="tab_nav" style="width: 810px;">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">我的礼金券</a></li>
                        </ul>
                    </div>
                </div>
                <div class="order_search">
					<div class="ordersearch_wrap">
	                   <input id="notUsed" type="button" class="btn" value="MO生活礼金券" onclick="doChangeType(0)"/>
	                   <input id="userd" type="button" class="btn_card" value="商盟礼金券" onclick="doChangeType(1)"/>
	                </div>
				</div>
				<div id="mocard">
				   <table width="810px" cellspacing="0" cellpadding="0" border="0" id="lottery_bettingRecords">
					        <thead>
					          <tr>
					            <th>礼金券名称</th>
					            <th>面值</th>
					            <th>描述</th>
				            	<th>有效期</th>
				            	<th>状态</th>
				            	<th>使用订单</th>
				            	<th>操作</th>
					          </tr>
					        </thead>
					          <tbody>
					        <c:forEach items="${list}" var="data">
								<tr>
									<td>${data.gift.cardName }</td>
									<td>${data.gift.cardMoney }</td>
									<td><div style="width: 160px;height: 20px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;word-break: break-all;" title="${data.gift.cardDesc }">${data.gift.cardDesc }</div></td>
									<td>${data.gift.endTime }</td>
									<c:if test="${data.status eq 1}">
										<td>已领取</td>
									</c:if>
									<c:if test="${data.status eq 2}">
										<td>未领取</td>
									</c:if>
									<c:if test="${data.status eq 3}">
										<td>已使用</td>
									</c:if>
									<c:if test="${data.status eq 4}">
										<td>已过期</td>
									</c:if>
						            <td><a style="text-decoration:underline" href="orderView.chtml?orderId=${data.orderId }">${data.orderId }</a></td>
									<td><a style="text-decoration: underline;" href="${data.cardLink }">立即使用</a></td>
								</tr>
							</c:forEach>
					        </tbody>
					      </table>
						
						${pageInfos }
						<ul style="margin: 10px 20px;line-height: 24px;">
				       		<h6 style="border-bottom: 1px solid #dedede;margin: 6px 0;">礼金券温馨提醒</h6>
					      	<li>1、礼金券限团购和代金券频道使用，只能在使用现金购买团购和代金券商品时抵扣商品金额；</li>
					        <li>2、礼金券使用前必须绑定手机，使用礼金券的订单超时未支付的，礼金券1小时内返还至账户，有效期不变；</li>
					        <li>3、礼金券金额大于订单商品金额，则差额不予退回；如礼金券金额小于订单商品金额，则需由客户支付差额；礼金券不能用来支付相关运费，不可兑现；</li>
					        <li>4、礼金券只能使用一次，如发生退货行为，已使用的礼金券将不再退还。</li>
				        </ul>
						</div>
						<div id="unions" class="fr  w750" style="display:none;">
					       <iframe style="width: 810px;height:1180px;margin-left: -62px;" id="win" name="win"  frameborder="0" scrolling="no" src=""></iframe> 
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
        $("#card").addClass("col_link");
    })
</script>
</body>
</html>