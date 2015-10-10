<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<link href="/css/layout.css" rel="stylesheet" type="text/css" />
<link href="/css/product.css" rel="stylesheet" type="text/css" />
<link href="/css/sidebar.css" rel="stylesheet" type="text/css" />
<link href="/css/pro.css" rel="stylesheet" type="text/css" />


<script>
 var itemId = '${good.gId}';
 var itemName ='${good.gName}';
 var webRoot = '/';
 var storeId = '${good.gStoreId}';
</script>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.jqzoom.js"></script>
<link rel="stylesheet" type="text/css" href="/css/zoom.css" />
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/product.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/ad.js"></script>

<title>${good.gName}--移动商城</title>
</head>
<body id="product">
	<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto" >
<div class="alert_windows">
	<a href="javascript:;" onclick="$('.alert_windows').hide();" class="close"></a>
	<div class="content">
    	<p id="favoritetips">收藏成功！</p>
        <a class="btn" onclick="$('.alert_windows').hide();">确定ȷ��</a> 
    </div>
</div>
<div class="breadcrumb"></div>
<div id="product_container" class="w bt10">
	<div id="product_img">
        <div clstag="shangpin|keycount|product|spec-n1" class="jqzoom" id="spec-n1">
         <img width="320" height="320" jqimg="<ct:path flag='2' id='${good.gId }'/>N0/${good.gWebPath }" alt="${good.gName}" src="<ct:path flag='2' id='${good.gId }'/>N1/${good.gWebPath }"  data-img="1"/> </div>
        <div clstag="shangpin|keycount|product|spec-n5" id="spec-list"> <a id="spec-forward" class="spec-control disabled" href="javascript:;"></a> <a id="spec-backward" class="spec-control disabled" href="javascript:;"></a>
            <div class="spec-items" style="position: absolute; height: 54px; overflow: hidden;">
                <ul class="lh" style="position: absolute; left: 0px; top: 0px; width: 280px;">
                <c:if test="${!empty imgs}">
	                <c:forEach items="${imgs}" var="img">
					<li><img src="<ct:path flag='2' id='${good.gId }'/>N5/${img}" alt="${good.gName}" class="img-hover" data-img="1"></li>
	               </c:forEach>
               </c:if>
                </ul>
            </div>
        </div>
    </div>
    <div id="product_info">
        <div id="name">
            <h1>${good.gName}</h1>
            <strong id="marketContent"></strong> </div>
        <ul id="summary">
            <li id="summary-market">
                <div class="dt">商品编号：</div>
                <div class="dd"><span>${good.gId}</span></div>
            </li>
            <div id="memPrice">
            <li id="summary-market-price">
            <div class="dt">市场价：</div><div class="dd"><span class="breaktext">${good.gMarketPrice}</span></div>
            </li>
            <li id="summary-price">
            <div class="dt">商城价：</div><div class="dd"><strong class="p-price"><span class="col_link">${good.gShopPrice}</span></strong><em id="a-tips">&nbsp;</em>
            </div>
            </li>
            </div>
        	<li style="display list-item;">
                <div class="dt">物流运费：</div>
                <div class="dd" id="feeNum">${good.gLogisticsFee}</div>
            </li>        
            
            <li id="summary-grade">
                <div class="dt">支付方式：</div>
                <div class="dd">
                    <p class="pay_mth"> 
                    <c:if test="${good.gBillPay eq 1}">话费&nbsp;&nbsp;</c:if> 
                    <c:if test="${good.gCashIdgoods eq 1}">现金&nbsp;&nbsp;</c:if>
                    <c:if test="${good.gCoinIdgoods eq 1}">商城币&nbsp;&nbsp;</c:if>
                    <c:if test="${good.gScoreIdgoods eq 1}">积分&nbsp;&nbsp;</c:if>
                    </p>
                </div>
            </li>
            <li class="hide" id="summary-tips">
                <div class="dt">温馨提示：</div>
                <div class="dd red">${good.gMarketContent} </div>
            </li>
           
   			<div id="product_quickphoto">
           你现在查看的是 <strong>交易快照</strong> <br />
           <a href="<ct:path flag='1' id='${good.gId}'/>" class="col_link">点击查看最新商品详情</a>
            
            </div> 
            
            <span class="clr"></span>
        </ul>
        <span class="clr"></span> </div>
    <div id="product_main" class="fl">
    	<div id="rr"></div>
        <div class="product_detail">
            <div class="product_tab" id="tab_detail">
                <div class="product_tab_nav" id="product_menu">
                    <ul class="tab">
                        <li class="curr"><a href="#info1">商品介绍</a></li>
                    </ul>
                </div>
                <div class="product_tab_wrap">
                    <div class="product_tab_cont" style="display:block;" id="info1">
			${good.gRemark} 
				    </div>
                    <div class="product_tab_cont" id="info2">
                    	<table width="100%">
                    		<tbody>
					    	</tbody>
					    </table>
                    </div>
                    <div class="product_tab_cont" id="info4"> </div>
                    <div class="product_tab_cont" id="info5"> </div>
                    <div class="product_tab_cont" id="info6"> </div>
                </div>
            </div>
        </div>
    </div>

    <span class="clr"></span>
    </div>
	<div id="alert" style="display: none;">
		<div class="alert_wrapper">
       	   <div class="alert_title"><span onclick="$('#alert').hide()"></span>商品已添加到购物车</div>
           <div class="alert_conter">
                <div class="alert_btn clearfix">
                <a href="/cart/show.chtml" id="product_btn_cart" target="_blank"></a>
                 <a onclick="$('#alert').hide();" href="javascript:void(0);"></a>
                 </div>
           </div>
    	</div>
	</div>
	
	<div id="alert_mobile" style="display: none;" >
			<div class="alert_wrapper">
              	   <div class="alert_title">
              	   <span onclick="$('#alert_mobile').hide()"></span>
              	   	手机验证：请添加江苏移动手机号</div>
                  <div class="alert_conter">
                  	   <p class="alert_p1">手机号码：
                  	    	<input type="text" class="text" id="bind_mobile" maxlength="11"  
                  	    		onkeyup="this.value=this.value.replace(/\D/g,'')" 
                  	    		onafterpaste="this.value=this.value.replace(/\D/g,'')" />
                  	    </p>
                        <p class="alert_p1">验证码：
                         <input type="text" class="text" id="bind_code" maxlength="6" 
                         		onkeyup="this.value=this.value.replace(/\D/g,'')" 
                  	    		onafterpaste="this.value=this.value.replace(/\D/g,'')" /> 
                      <input id="bind_mobile_btn" class="btn" type="button" value="发送验证码" 
                       	 		onclick="sendBindSmsCode();" />
                       </p>
                       <div class="alert_btn clearfix">
                       		<input type="button" id="product_btn_sumbit" onclick="verifyBindCode();" />
                       </div>
                  </div>
    		</div>
	</div>
</div>
<script type="text/javascript" src="/js/foot.js"></script>
<script>
var src = "<script type='text/javascript' src='/ad/getAd.chtml?code=global&disfunction=displayAd240X320Pro&appendElement=pro&position=item&region_code="+getAreaCode()+"'><\/script>";
document.write(src);

</script>


</body>
</html>