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
<#if action!='preview'>
<script type="text/javascript" src="${WEB_ROOT}js/head.js"></script>
</#if>
<div class="wrapper mauto" >
    <div id="shop_container" class="w bt10">
        <div id="shop_banner"> <#if shopSet['shopPicUrl'] ??><img height="200" width="1000" src="${imgSvrHost}${storeLogoPath}${shopSet['shopPicUrl']}" /><#else><img height="200" width="1000" src="${imgSvrHost}${storeLogoPath}/shop_defalut/shop_defalut.jpg" /></#if> <span class="clr"></span> </div>
    </div>
    <div id="shop_nav">
        <h3>${store["name"]}</h3>
        <div id="shop_nav_menu">
        	<#if action=='preview'>
        	<a class="active" href="#">首页</a>
        	<#else>
        	<a class="active" href="${shop_url}">首页</a>
        	<a href="${shop2_url}">全部商品</a>
        	</#if>
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
        <div id="shop_pro_banner">${shopSet['homepageWord']}<span class="clr"></span> </div>
        
        <div class="shop_search" <#if action=='preview'>style="display:none;"</#if> >
     		<p>商品筛选: <input class="text" type="text" placeholder="搜索内容" id="s_name" /> 
			       价格区间: <input  class="text" typetype="text" placeholder="价格低" id="s_price_low" /> - <input  class="text" typetype="text" placeholder="价格高" id="s_price_high" /> 
			            <input src="${WEB_ROOT}img/btn_search.jpg" alt="" type="image" onclick="goSearch();" />
			</p>
       	</div>
        <div class="shop_detail">
        <#if (floorList?size > 0)>
        
        <#list floorList as fl>
            <div class="shop_tab">
                <div class="shop_tab_nav" id="all_product" _floorid="floorid_${fl['shelfId']}" _goodNum="${fl['goodNum']}" >
                    <ul class="tab">
                        <li class="curr"><a href="#info1">${fl['title']}</a></li>
                    </ul>
                </div>
            </div>
        </#list>
        <#else>
			<div class="shop_tab">
                <div class="shop_tab_nav" id="all_product" _floorid="floorid_0" _goodNum="12" >
                    <ul class="tab">
                        <li class="curr"><a href="#info1">推荐商品</a></li>
                    </ul>
                </div>
            </div>
        </#if>
            
        </div>
    </div>
    <div id="sidebar" class="w240 fl">
    <#if action!='preview'>
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
                <a class="btn_fav" href="javascript:void(0);" onclick="mark();"><img src="${WEB_ROOT}img/btn_fav.jpg" /></a>
            </div>
        </div>
      </#if>
        <div class="related-title" id="hjfl_id">
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

// 初始化商品列表
var floorUrl = '${WEB_ROOT}shop/floor.chtml';
initFloors("shop_tab_nav", floorUrl);

//初始化飞信列表
initFeixinList(shopId, 2);
</script>
<#if action!='preview'>
<script type="text/javascript" src="${WEB_ROOT}js/foot.js"></script>
</#if>
</body>
</html>