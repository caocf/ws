<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${WEB_ROOT}css/base.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/layout.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/shop.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/shop_sidebar.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/pro.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var shopId = '${shopId}';
var WEB_ROOT = '${WEB_ROOT}';
var s2_flag = true;
var SHOP_URL = '${shop_url}';
var SHOP2_URL = '${shop2_url}';
var imgSvrHost = '${imgSvrHost}';


</script>
<script type="text/javascript" src="${WEB_ROOT}js/jquery.min.js"></script>
<script type="text/javascript" src="${WEB_ROOT}js/jquery.jqzoom.js"></script>
<script type="text/javascript" src="${WEB_ROOT}js/jquery.cookie.js"></script>
<link rel="stylesheet" type="text/css" href="${WEB_ROOT}css/zoom.css" />
<script type="text/javascript" src="${WEB_ROOT}js/shop.js"></script>
<title>移动商城-${store["name"]}</title>
</head>
<body id="product">
<script type="text/javascript" src="${WEB_ROOT}js/head.js"></script>
<div class="wrapper mauto" >
    <div id="shop_container" class="w bt10">
        <div id="shop_banner"> <#if shopSet['shopPicUrl'] ??><img height="200" width="1000" src="${imgSvrHost}${storeLogoPath}${shopSet['shopPicUrl']}" /><#else><img height="200" width="1000" src="${imgSvrHost}${storeLogoPath}/shop_defalut/shop_defalut.jpg" /></#if> <span class="clr"></span> </div>
    </div>
    <div id="shop_nav">
        <h3>${store["name"]}</h3>
        <div id="shop_nav_menu">
        	<a href="${shop_url}">首页</a>
        	<a class="active" href="${shop2_url}">全部商品</a>
        </div>
    </div>
    </div>
   <div class="wrapper mauto" > 
  <div class="alert_windows" style="margin-top:-50px;">
	<a href="javascript:void(0);" onclick="$('.alert_windows').hide();" class="close"></a>
	<div class="content">
    	<p id="favoritetips">收藏成功！</p>
        <a class="btn" onclick="$('.alert_windows').hide();loginType();">确定</a> 
    </div>
</div> 
    <div id="shop_main" class="fr  w750">
        <div id="shop_pro_banner"> <span class="clr"></span> </div>
        <div class="shop_search" style="display: none;">
     		<p>商品筛选: <input class="text" type="text" placeholder="搜索内容" id="s_name" /> 
			        价格区间: <input class="text" type="text" placeholder="价格低" id="s_price_low" /> - <input  class="text" typetype="text" placeholder="价格高" id="s_price_high" /> 
			             <input src="${WEB_ROOT}img/btn_search.jpg" alt="搜索" type="image" onclick="initOnePage( '1' );" />
			</p>
       	</div>
        <div class="shop_detail">
        
            <div class="shop_tab">
                <div class="shop_tab_nav" id="all_product">
                    <ul class="tab">
                        <li class="curr"><a href="#info1">全部商品</a></li>
                        <select id="storeOrder" class="shop_tab_nav_select" onchange="javascript:sortProductList()" name="orderbyType">
                          <option selected="selected" value="">排序方式</option>
			              <#list storeOrderMap?keys as key>
			              <option value="${key}">${storeOrderMap['${key}']}</option>
			              </#list>
			             </select>
                    </ul>
                </div>
                
                <div class="shop_tab_wrap">
					<div class="shop_content">
						<div class="item_wrap">
						</div>
					</div>
				</div>
            </div>      
                        
        </div>
    </div>
    <div id="sidebar" class="w240 fl">
        <div id="sidebar_shop" class="mb10">
            <div class="related-content">
                <dl>
                    <dt>商户名称</dt>
                    <dd>${store["name"]}</dd>
                </dl>
                <dl>
                    <dt>公司名称</dt>
                    <dd>${store["name"]}</dd>
                </dl>
                <dl>
                    <dt>所在地</dt>
                    <dd>${areaFullName}</dd>
                </dl>
                <a class="btn_fav" href="javascript:void(0);" onclick="mark();">
                	<img src="${WEB_ROOT}img/btn_fav.jpg" />
                </a>
            </div>
        </div>
        <div class="related-title" id="hjfl_id" >
            <h3>货架分类</h3>
        </div>
		<!-- 此处初始化分类数据 -->
    </div>

    <span class="clr"></span> 
    </div>

<script type="text/javascript">
// 初始化分类
var sortUrl ='${WEB_ROOT}shop/sorts.chtml';
initSorts(sortUrl, shopId);
var _GET = $.urlGet(); 
var name = decodeURIComponent(_GET['name']?_GET['name']:''); 
var priceLow = decodeURIComponent(_GET['priceLow']?_GET['priceLow']:''); 
var priceHigh = decodeURIComponent(_GET['priceHigh']?_GET['priceHigh']:'');
var shelfId = _GET['shelfId']?_GET['shelfId']:'';
if(shelfId && ( shelfId == parseInt(shelfId)) ){
	shelfId = parseInt(shelfId);
	name = "";
	priceLow = "";
	priceHigh = "";
}

if(name || priceLow || priceHigh){
	$("#s_name").val(name);
	$("#s_price_low").val(priceLow);
	$("#s_price_high").val(priceHigh);
	$(".shop_search").show();
}else{
	$("#s_name").val('');
	$("#s_price_low").val('');
	$("#s_price_high").val('');
	$(".shop_search").hide();
}

// 初始化商品列表
var onePageUrl = "${WEB_ROOT}shop/page.chtml";
initOnePage( "1" );


//初始化飞信列表
initFeixinList(shopId, 2);
</script>

<script type="text/javascript" src="${WEB_ROOT}js/foot.js"></script>
</body>
</html>