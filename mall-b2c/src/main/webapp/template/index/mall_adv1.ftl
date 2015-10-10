<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/base.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/shop.css" rel="stylesheet" type="text/css" />
<link href="../css/shop_sidebar.css" rel="stylesheet" type="text/css" />
<link href="../css/pro.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var shopId = '${shopId}';
</script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.jqzoom.js"></script>
<link rel="stylesheet" type="text/css" href="../css/zoom.css" />
<script type="text/javascript" src="../js/shop.js"></script>
<script type="text/javascript" src="../js/pro.js"></script>
<title></title>
</head>
<body id="product">
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href=" ">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href=" ">商铺</a>&nbsp;&gt;&nbsp;<a href=" ">商铺详情</a></div>
    <div id="shop_container" class="w bt10">
        <div id="shop_banner"> <img src="../img/shop_test/ex2.jpg" /> <span class="clr"></span> </div>
    </div>
    <div id="shop_nav">
        <h3>店铺名称黄天源</h3>
        <div id="shop_nav_menu">
        	<a class="active" href="/mall-b2c/shop/s_${shopId}.htm">首页</a>
        	<a href="/mall-b2c/shop/s2_${shopId}.htm">全部商品</a>
        </div>
    </div>
    <div id="shop_main" class="fr  w750">
        <div id="shop_pro_banner"> <img src="../img/shop_test/ex2.jpg" /> <span class="clr"></span> </div>
        <div class="shop_search" >
     		<p>商品筛选: <input class="text" type="text" placeholder="搜索内容" /> 
			             <input  class="text" typetype="text" placeholder="价格低"  /> - <input  class="text" typetype="text" placeholder="价格高"  /> <input src="../img/btn_search.jpg" alt=" " type="image" />
			</p>
       	</div>
        <div class="shop_detail">
        
        <#list floorMap as x>
            <div class="shop_tab">
                <div class="shop_tab_nav" id="all_product" _floorid="floorid_${x_index + 1}">
                    <ul class="tab">
                        <li class="curr"><a href="#info1">${x}</a></li>
                    </ul>
                </div>
            </div>      
        </#list>
            
        </div>
    </div>
    <div id="sidebar" class="w240 fl">
        <div id="sidebar_shop" class="mb10">
            <div class="related-content">
                <dl>
                    <dt>商户名称</dt>
                    <dd>${shop["name"]}</dd>
                </dl>
                <dl>
                    <dt>公司名称</dt>
                    <dd>${shop["companyName"]}</dd>
                </dl>
                <dl>
                    <dt>所在地</dt>
                    <dd>${shop["address"]}</dd>
                </dl>
                <a class="btn_fav" href="javascript:void(0);"><img src="../img/btn_fav.jpg" /></a>
            </div>
        </div>
        <div class="related-title">
            <h3>货架分类</h3>
        </div>
		<!-- 此处初始化分类数据 -->
    </div>
    <span class="clr"></span> </div>
</div>

<script type="text/javascript">
// 初始化分类
var sortUrl ='/mall-b2c/shop/sorts';
initSorts(sortUrl, shopId);

// 初始化商品列表
var floorUrl = "/mall-b2c/shop/floor";
initFloors("shop_tab_nav", floorUrl);

</script>
</body>
</html>