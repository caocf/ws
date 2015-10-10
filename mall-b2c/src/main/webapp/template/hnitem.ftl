<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name='WT.shopping_n' content='${item_no}'>
<meta name='WT.shopping_x' content='1'>
<link href="${webRoot}css/base.css" rel="stylesheet" type="text/css" />
<link href="${webRoot}css/layout.css" rel="stylesheet" type="text/css" />
<link href="${webRoot}css/product.css" rel="stylesheet" type="text/css" />
<link href="${webRoot}css/sidebar.css" rel="stylesheet" type="text/css" />
<link href="${webRoot}css/pro.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webRoot}js/jquery.min.js"></script>
<script type="text/javascript" src="${webRoot}js/jquery.jqzoom.js"></script>
<script type="text/javascript" src="${webRoot}js/jquery.cookie.js"></script>
<script type="text/javascript" src="${webRoot}js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${webRoot}js/global.js"></script>
<link rel="stylesheet" type="text/css" href="${webRoot}css/zoom.css" />
<script type="text/javascript" src="${webRoot}js/producthn.js"></script>
<script>
function area_swap(areaVal,shopStr) {
	var mapdesc_str = "#mapdesc_"+areaVal;
	var titlearea_str = '#titlearea_'+areaVal;
	$(".mapdesc").css("display","none");
	$(".titlearea").css({"font-weight":"400","color":"#666"});
	$(mapdesc_str).css("display","block");
	$(titlearea_str).css({"font-weight":"bold","color":"#000"});
	$("#map_canvas_iframe").attr("src","http://mall.12580life.com/map/getMap.chtml?width=320&height=170&id="+shopStr);
	$("#map_canvas").css('display','block');
}
jQuery(document).ready(function() {
	});
 var itemId = '${item_no}';
 var itemName ='${itemNameforJs}';
 var webRoot = '${webRoot}';
 var storeId = '${shop_id}';
</script>
<title></title>
</head>
<body id="product">
<script type="text/javascript" src="http://www.12580life.com/common/head.chtml"></script>
<div class="wrapper mauto" >
    <div class="alert_windows">
		<a href="javascript:void(0);" onclick="$('.alert_windows').hide();" class="close"></a>
		<div class="content">
    	    <p id="favoritetips">收藏成功！</p>
            <a class="btn" onclick="$('.alert_windows').hide();">确定</a> 
        </div>
    </div>
    <div class="breadcrumb"></div>
    <div id="product_container" class="w bt10">
        <div id="product_info" class="xnsp">
            <div id="name">
                <h1>${item_name}</h1>
                <strong id="marketContent"></strong>
            </div>
            <ul id="summary">
                <li id="summary-market">
                    <div class="dt">商品编号：</div>
                    <div class="dd"><span>${item_no}</span></div>
                </li>
                <div id="memPrice"></div>
                <li id="summary-grade" style="display list-item;">
                    <div class="dt">支付方式：</div>
                    <div class="dd">
                        <p class="pay_mth">${pay_method} </p>
                    </div>
                </li>
                <!-- 商品评分-->

                <li class="hide" id="summary-gifts">
                    <div class="dt">购买数量：</div>
                    <div class="dd">
                        <div class="wrap-input"> <a onclick="downNum()" href="javascript:void(0);" class="btn-reduce">减少数量</a> <a onclick="upNum()" href="javascript:void(0);" class="btn-add">增加数量</a>
                        <input onkeyup="value=value.replace(/[^\d]/g,&#39;&#39;)" onbeforepaste="clipboardData.setData(&#39;text&#39;,clipboardData.getData(&#39;text&#39;).replace(/[^\d]/g,&#39;&#39;))" onblur="numcheck()" value="1" id="buy-num" class="text"/>
						</div>
						<span class="count" id="stocknum"></span>
						<div id="kucunTips"></div>
					</div>
                </li>
                <li>
                    <div class="dt">累计销售：</div>
                    <div class="dd" id="saleNum"></div>
                </li>
                <li id="choose-btns"></li>
                <span class="clr"></span>
            </ul>
            <span class="clr"></span>
		</div>
        <div id="product_img">
            <div clstag="shangpin|keycount|product|spec-n1" class="jqzoom" id="spec-n1">
				<img width="320" height="320" jqimg="${webRoot}${img_path}N0/${imgs[0]}" alt="${item_name}" src="${webRoot}${img_path}N1/${imgs[0]}" data-img="1"/>
			</div>
		</div>
		<div id="product_main" class="fr  w750">
			<div class="block">
				<h2 class="title">温馨提示</h2>
				<div class="content"><textarea rows="6" style="width:100%; border:0; outline:0; line-height:1.5em;  ">${pay_hint}</textarea></div>
			</div>
			<div class="block">
				<h2 class="title">
					商家位置&nbsp;&nbsp;&nbsp;&nbsp;
					<#list addresses?keys as param>
						<#assign y="">
						<#assign areaCode="">
						<#assign isChRe="font-weight: 400; color: rgb(102, 102, 102);">
						<#assign addre=addresses[param]>
						<#list addre as addr>
							<#assign areaCode="${addr[1]}">
							<#assign y=y+"${addr[0]}"+",">
						</#list>
						<#if areaCode == '0512'>
							<#assign isChRe="font-weight: bold; color: rgb(0, 0, 0);">
						</#if>
						<span onclick="area_swap('${areaCode}','${y}')" shopval="${y}" aval="${areaCode}" id="titlearea_${areaCode}" class="titlearea" style="${isChRe}">${param}</span>
					</#list>
				</h2>
				<div class="content location clearfix">
					<#assign sid="">
					<#assign ace="">
					<#list addresses?keys as param>
						<#if param == '苏州'>
							<#assign addre=addresses[param]>
							<#list addre as addr>
								<#assign sid= sid+"${addr[0]}"+",">
							</#list>
							<#assign ace='0512'>
						</#if>
					</#list>
					<#if ace != '0512'>
						<#list addresses?keys as param>
							<#list addre as addr>
								<#assign sid= sid+"${addr[0]}"+",">
							</#list>
						<#if ace == ''>
							<#assign ace="${addresses[param][0][1]}">
						</#if>
					</#list>
					</#if>
					<div class="map">
						<div style="display: block;" class="map_canvas" id="map_canvas">
							<iframe width="100%" scrolling="no" height="100%" frameborder="0" src="http://mall.12580life.com/map/getMap.chtml?width=320&height=170&id=${sid}" id="map_canvas_iframe"></iframe>
						</div>
					</div>
					<#assign acu=0>
					<#list addresses?keys as param>
						<#if param == '苏州'>
							<#assign addre=addresses[param]>
							<dl style="display: block;" id="mapdesc_0512" class="mapdesc">
								<#list addre as addr>
									<dt>${addr[2]}</dt>
									<dd><strong>地址：</strong>${addr[3]}</dd>
									<dd><strong>电话：</strong>${addr[4]}</dd>
								</#list>
							</dl>
							<#assign ace='0512'>
						</#if>
					</#list>
					<#assign sty = "">
						<#list addresses?keys as param>
							<#if ace == '0512' >
								<#assign sty = "display:none;">
							<#else>
								<#if acu == 0>
									<#assign sty = "display:block;">
								<#else>
									<#assign sty = "display:none;">
								</#if>
							</#if>
							
							<#assign addre=addresses[param]>
						    <#assign ace='${addre[0][1]}'>
							<#if ace != '0512' >
								<dl style="${sty};" id="mapdesc_${addre[0][1]}" class="mapdesc">
							</#if>
								<#list addre as addr>
									<#if ace != '0512' >
										<dt>${addr[2]}</dt>
										<dd><strong>地址：</strong>${addr[3]}</dd>
										<dd><strong>电话：</strong>${addr[4]}</dd>
									</#if>
									<#assign ace='${addr[1]}'>
								</#list>
							<#if ace != '0512' >
								</dl>
							</#if>
					</#list>
				</div>
			</div>
			<div class="product_detail">
				<div class="product_tab" id="tab_detail">
					<div class="product_tab_nav" id="product_menu">
						<ul class="tab">
							<li class="curr"><a href="#info1">商品介绍</a></li>
							<li class=""><a href="#info4" onclick="getPurchaseRecords(1);">成交记录</a></li>
							<li class="" id="comment"><a href="#info5" onclick="getItemComments(1)">用户评价</a></li>
							<li class=""><a href="#info6" onclick="getConsults(1)">用户咨询</a></li>
						</ul>
					</div>
					<div class="product_tab_wrap">
						<div id="info1" class="product_tab_cont" style="display: block;">
							${item_intro}
						</div>
						<div class="product_tab_cont" id="info4"> </div>
						<div class="product_tab_cont" id="info5"> </div>
						<div class="product_tab_cont" id="info6"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="sidebar" class="w240 fl">
			<div class="mb10" id="sidebar_shop">
				<div class="related-title">
					<h3>${shop_name}</h3>
				</div>
				<div class="related-content"><a href="${shop_url}"><img src="${webRoot}img/dp.jpg" /></a> </div>
			</div>
	
			<div id="sidebar_history" class="mb10">
				<div class="related-title">
					<h3>浏览过的历史记录</h3>
				</div>
				<div class="related-content">
					<ul class="" id="related_his">
					</ul>
				</div>
			</div>
		</div>
		<span class="clr"></span>
	</div>
	<div id="alert" style="display: none;">
		<div class="alert_wrapper">
			<div class="alert_title"><span onclick="$('#alert').hide()"></span>商品已添加到购物车</div>
			<div class="alert_conter">
				<p class="alert_p1">购物车共<i id="productCountsAndTotoalIdx">1</i>种商品 <span id="saler">合计:<em>￥340.00</em></span></p>
				<div class="alert_btn clearfix">
					<input type="button" />
					 <a onclick="$('#alert').hide();" href="javascript:void(0);"></a>
				</div>
			</div>
		</div>
	</div>
	<div id="alert_mobile" style="display: none;" >
		<div class="alert_wrapper">
			<div class="alert_title">
				<span onclick="$('#alert_mobile').hide()"></span>
				手机验证：请添加江苏移动手机号
			</div>
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
<script type="text/javascript" src="http://www.12580life.com/henan/foot_for_henan.jsp"></script>
</body>
</html>
