<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name='WT.shopping_n' content='${item_no}'>
<meta name='WT.shopping_x' content='1'>
<link href="${webRoot}css/base.css" rel="stylesheet" type="text/css" />
<link href="${webRoot}css/layout.css" rel="stylesheet" type="text/css" />
<link href="${webRoot}css/product.css?ver=20140314" rel="stylesheet" type="text/css" />
<link href="${webRoot}css/sidebar.css" rel="stylesheet" type="text/css" />
<link href="${webRoot}css/pro.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webRoot}js/jquery.min.js"></script>
<script type="text/javascript" src="${webRoot}js/jquery.jqzoom.js"></script>
<script type="text/javascript" src="${webRoot}js/jquery.cookie.js"></script>
<script type="text/javascript" src="${webRoot}js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${webRoot}js/global.js"></script>
<link rel="stylesheet" type="text/css" href="${webRoot}css/zoom.css" />
<script type="text/javascript" src="${webRoot}js/djq.js"></script>
<script>
var itemId = '${item_no}'; var itemName ='${itemNameforJs}';
var webRoot = '${webRoot}'; var storeId = '${shop_id}';
</script>
<style>
#product_menu .tab .wrap-input{ width:40px; float:right; position:relative; margin:2px 5px 0 0;}
#product_menu .tab .wrap-input span {float: none; height: 15px; line-height: 50px; overflow: hidden; position: absolute; top: 3px; width: 15px; padding:0; cursor:pointer;}
#product_menu .tab .btn{ float:right; background:#007BC7; color:#fff; margin:2px 5px 2px 2px; line-height:24px; width:50px; height:24px; cursor:pointer;}
.product_tab_wrap{padding:0;}
.product_tab_cont{border-bottom:1px solid #DEDFDE; padding:8px;}
.product_tab_cont h2 {font-size:16px; line-height:24px; padding:0 10px; border-bottom:1px #ddd dashed; margin-bottom:10px;}
#info6 h2{color:#000;}
</style>

<title></title>
</head>
<body id="product">
<script type="text/javascript" src="${webRoot}js/head_djq.js"></script>
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
        <div id="product_info" class="xnsp" style="min-height: 250px;">
            <div id="name">
                <h1>${item_name}</h1>
                <strong id="marketContent"></strong>
            </div>
            <ul id="summary">
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
                        <input onkeyup="value=value.replace(/[^\d]/g,&#39;&#39;)" onbeforepaste="clipboardData.setData(&#39;text&#39;,clipboardData.getData(&#39;text&#39;).replace(/[^\d]/g,&#39;&#39;))" onblur="numcheck();$('#buy-num2').val($('#buy-num').val())" value="1" id="buy-num" class="text"/>
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
            <div clstag="shangpin|keycount|product|spec-n1" class="jqzoom" id="spec-n1"><img width="320" height="175" jqimg="${webRoot}${img_path}N0/${imgs[0]}" alt="${item_name}" src="${webRoot}${img_path}N1/${imgs[0]}" data-img="1"/></div>
			<div clstag="shangpin|keycount|product|spec-n5" id="spec-list"> <a id="spec-forward" class="spec-control disabled" href="javascript:;"></a> <a id="spec-backward" class="spec-control disabled" href="javascript:;"></a>
	            <div class="spec-items" style="position: absolute; height: 54px; overflow: hidden;">
	                <ul class="lh" style="position: absolute; left: 0px; top: 0px; width: 280px;">
			    <#assign x=0>
			    <#list imgs as oimg>
					<li><img src="${webRoot}${img_path}N5/${oimg}" alt="${item_name}" class="<#if x==0>img-hover</#if>" data-img="1"></li>
				<#assign x=x+1>
			    </#list>
	                </ul>
	            </div>
       		</div>
		</div>
		
		<div class="other_djq">
        	<div class="djq_box_title">该商家其他商品</div>
            <div class="djq_box_cont"> </div>
        </div>
        
		<div id="product_main" class="fr w750">
			<div class="product_detail">
				<div class="product_tab" id="tab_detail">
					<div class="product_tab_nav" id="product_menu">
						<ul class="tab">
							<li class="curr"><a href="#info1">温馨提示</a></li>
							<li><a href="#info8">商家位置</a></li>
							<li><a href="#info7">商品介绍</a></li>
							<li><a href="#info4">成交记录</a></li>
							<li id="comment"><a href="#info5">用户评价</a></li>
							<li><a href="#info6">用户咨询</a></li>
							<li style="float:right; width:auto; height:auto;">
								<span class="btn" onclick="buyNow();return false;">购买</span>
								<div class="wrap-input">
									<span onclick="downNum()" style="cursor:pointer;" class="btn-reduce">减少数量</span>
									<span onclick="upNum()" style="cursor:pointer;" class="btn-add">增加数量</span>
                        			<input onkeyup="value=value.replace(/[^\d]/g,&#39;&#39;)" onbeforepaste="clipboardData.setData(&#39;text&#39;,clipboardData.getData(&#39;text&#39;).replace(/[^\d]/g,&#39;&#39;))" onblur="numcheck();$('#buy-num').val($('#buy-num2').val())" value="1" id="buy-num2" class="text"/>
								</div>
							</li>
						</ul>
					</div>
					<div class="product_tab_wrap">
						<div id="info1" class="product_tab_cont" style="display:block">
							<h2>温馨提示</h2>
							<textarea rows=1 id="pdtwxts" readonly="readonly"
								style="overflow:scroll; width:730px; border:0; overflow-y:hidden; overflow-x:hidden;"
								onblur="this.style.height=this.scrollHeight+'px';">${pay_hint}</textarea>
							<script>$('#pdtwxts').focus();$('#pdtwxts').blur();</script>
						</div>
						<div class="product_tab_cont" id="info8">
							<h2>商家位置&nbsp;&nbsp;&nbsp;&nbsp;
							<span style="font-size:14px; padding:10px;"><#list addresses?keys as param>
								<#assign y="">
								<#assign areaCode="">
								<#assign isChRe="font-weight: 400; color: rgb(102, 102, 102);">
								<#assign addre=addresses[param]>
								<#list addre as addr>
									<#assign areaCode="${addr[1]}">
									<#assign y=y+"${addr[0]}"+",">
								</#list>
								<span onclick="area_swap('${areaCode}','${y}')" shopval="${y}" aval="${areaCode}" id="titlearea_${areaCode}" class="titlearea" style="${isChRe} cursor:pointer;">${param}</span>
							</#list></span>
							<!-------------------------------------------->
							<#assign sid="">
							<#assign ace="">
							<#list addresses?keys as param>
							<#assign addre=addresses[param]>
								<#list addre as addr>
									<#assign areaCode="${addr[1]}">
									<#assign y=y+"${addr[0]}"+",">
								</#list>
							</#list>
							<#list addresses?keys as param>
								<#list addre as addr>
									<#assign sid= sid+"${addr[0]}"+",">
								</#list>
								<#if ace == ''>
									<#assign ace="${addresses[param][0][1]}">
								</#if>
							</#list>
							</h2>
							<div class="content location clearfix" style="border:0;">
							<div class="map">
								<div style="display: block;" class="map_canvas" id="map_canvas">
									<iframe width="100%" scrolling="no" height="100%" frameborder="0" src="http://mall.12580life.com/map/getMap.chtml?width=320&height=170&id=${sid}" id="map_canvas_iframe"></iframe>
								</div>
							</div>
							<#assign acu=0>
							<#list addresses?keys as param>
								<#list addre as addr>
									<#assign areaCode="${addr[1]}">
									<#assign y=y+"${addr[0]}"+",">
								</#list>
							</#list>
							<#assign sty = "">
								<#list addresses?keys as param>
									<#assign sty = "display:none;">
									<#assign addre=addresses[param]>
								    <#assign ace='${addre[0][1]}'>
									<dl style="${sty};" id="mapdesc_${addre[0][1]}" class="mapdesc">
										<#list addre as addr>
											<dt>${addr[2]}</dt>
											<dd><strong>地址：</strong>${addr[3]}</dd>
											<dd><strong>电话：</strong>${addr[4]}</dd>
											<#assign ace='${addr[1]}'>
										</#list>
									</dl>
								</#list>
							</div>
						</div>
						<div class="product_tab_cont" id="info7">
							<h2>商品介绍</h2>
							${item_intro}
						</div>
						<div class="product_tab_cont" id="info4"></div>
						<div class="product_tab_cont" id="info6"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="sidebar" class="w240 fl">
		    <div id="sidebar_recomment" class="mb10">
				<div class="related-title">
					<h3>您可能感兴趣的代金券</h3>
				</div>
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
<input type="hidden" value="djq" id="page_type" />
<script type="text/javascript" src="${webRoot}js/foot_djq.js"></script>
</body>
</html>
