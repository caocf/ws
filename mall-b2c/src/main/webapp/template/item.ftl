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


<script>
 var itemId = '${item_no}';
 var itemName ='${itemNameforJs}';
 var webRoot = '${webRoot}';
 var storeId = '${shop_id}';
</script>
<script type="text/javascript" src="${webRoot}js/jquery.min.js"></script>
<script type="text/javascript" src="${webRoot}js/jquery.jqzoom.js"></script>
<link rel="stylesheet" type="text/css" href="${webRoot}css/zoom.css" />
<script type="text/javascript" src="${webRoot}js/jquery.cookie.js"></script>
<script type="text/javascript" src="${webRoot}js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${webRoot}js/product.js"></script>
<script type="text/javascript" src="${webRoot}js/global.js"></script>
<script type="text/javascript" src="${webRoot}js/ad.js"></script>

<title>${item_name}--移动商城</title>
</head>
<body id="product">
<script type="text/javascript" src="${webRoot}js/head.js"></script>
<div class="wrapper mauto" >
<div class="alert_windows">
	<a href="javascript:;" onclick="$('.alert_windows').hide();" class="close"></a>
	<div class="content">
    	<p id="favoritetips">收藏成功！</p>
        <a class="btn" onclick="$('.alert_windows').hide();">确定</a> 
    </div>
</div>
<div class="breadcrumb"></div>
<div id="product_container" class="w bt10">
	<div id="product_img">
        <div clstag="shangpin|keycount|product|spec-n1" class="jqzoom" id="spec-n1"> <img width="320" height="320" jqimg="${webRoot}${img_path}N0/${imgs[0]}" alt="${item_name}" src="${webRoot}${img_path}N1/${imgs[0]}" data-img="1"/> </div>
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
    <div id="product_info">
        <div id="name">
            <h1>${item_name}</h1>
            <strong id="marketContent"></strong> </div>
        <ul id="summary">
            <li id="summary-market">
                <div class="dt">商品编号：</div>
                <div class="dd"><span>${item_no}</span></div>
            </li>
            <div id="memPrice"></div>
        	<li style="display list-item;">
                <div class="dt">物流运费：</div>
                <div class="dd" id="feeNum"></div>
            </li>        
            <li id="summary-grade">
                <div class="dt">支付方式：</div>
                <div class="dd">
                    <p class="pay_mth">${pay_method} </p>
                </div>
            </li>
            <li class="hide" id="summary-tips">
                <div class="dt">温馨提示：</div>
                <div class="dd red">${pay_hint} </div>
            </li>
            <li class="hide" id="summary-gifts">
                <div class="dt">购买数量：</div>
                <div class="dd">
                    <div class="wrap-input"> <a onclick="downNum()" href="javascript:;" class="btn-reduce">减少数量</a> <a onclick="upNum()" href="javascript:;" class="btn-add">增加数量</a>
                        <input onkeyup="value=value.replace(/[^\d]/g,&#39;&#39;)" onbeforepaste="clipboardData.setData(&#39;text&#39;,clipboardData.getData(&#39;text&#39;).replace(/[^\d]/g,&#39;&#39;))" onblur="numcheck()" value="1" id="buy-num" class="text"/>
                        
                    </div><span class="count" id="stocknum"></span>
                    <div id="kucunTips"></div>
                </div>
            </li>
            <li id="choose-btns">
              </li>
            <span class="clr"></span>
        </ul>
        <span class="clr"></span> </div>
    <div id="product_main" class="fr  w750">
    	<div id="rr"></div>
        <div class="product_detail">
            <div class="product_tab" id="tab_detail">
                <div class="product_tab_nav" id="product_menu">
                    <ul class="tab">
                        <li class="curr"><a href="#info1">商品介绍</a></li>
                        <li class=""><a href="#info2">规格参数</a></li>
                        <li class=""><a href="#info4" onclick="getPurchaseRecords(1);">成交记录</a></li>
                        <li class="" id="comment"><a href="#info5" onclick="getItemComments(1)">用户评价</a></li>
                        <li class=""><a href="#info6" onclick="getConsults(1)">用户咨询</a></li>
                    </ul>
                </div>
                <div class="product_tab_wrap">
                    <div class="product_tab_cont" style="display:block;" id="info1">
					${item_intro}
				    </div>
                    <div class="product_tab_cont" id="info2">
                    	<table width="100%">
                    		<tbody>
                    	<#assign y=0>
					    <#list item_param?keys as param>
					    	<#if ((y % 2==0) && (y > 0)) >					    		
					    			</tr>
					    	</#if>
					    	<#if y % 2==0>	
					    		<tr>
					    	</#if>
					    	<td width="120" align="center"><span style="color:#AAAAAA;">${param}：</span></td>
					    	<td width="296">${item_param[param]}</td>
							<#assign y=y+1>
					    </#list>
					  
					    <#if (y % 2 !=1) >
					    	</tr>
					    </#if>
					    
					      <#if (y % 2 ==1) >
					    	<td></td><td></td></tr>
					    </#if>
					    
					    	</tbody>
					    </table>
                    </div>
                    <div class="product_tab_cont" id="info4"> </div>
                    <div class="product_tab_cont" id="info5"> </div>
                        <div class="product_tab_cont" id="info6">
                        </div>
                </div>
            </div>
        </div>
    </div>
    <div id="sidebar" class="w240 fl">
        <div id="sidebar_shop" class="mb10">
            <div class="related-title">
                <h3>${shop_name}</h3>
            </div>
            <div class="related-content">
                <a href="${shop_url}"><img src="${webRoot}img/dp.jpg" /></a>
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
        <div id="pro">
          
        </div>
    </div>
    <span class="clr"></span>
    </div>
	<div id="alert" style="display: none;">
		<div class="alert_wrapper">
       	   <div class="alert_title"><span onclick="$('#alert').hide()"></span>商品已添加到购物车</div>
           <div class="alert_conter">
           	    <!--<p class="alert_p1">购物车共<i id="productCountsAndTotoalIdx">1</i>种商品 <span id="saler">合计:<em>￥340.00</em></span></p>-->
                <div class="alert_btn clearfix">
                <a href="${webRoot}cart/show.chtml" id="product_btn_cart" target="_blank"></a>
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
<script type="text/javascript" src="${webRoot}js/foot.js"></script>
<script>
var src = "<script type='text/javascript' src='${webRoot}ad/getAd.chtml?code=global&disfunction=displayAd240X320Pro&appendElement=pro&position=item&region_code="+getAreaCode()+"'><\/script>";
document.write(src);

</script>


</body>
</html>
