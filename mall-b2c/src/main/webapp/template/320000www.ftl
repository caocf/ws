<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${WEB_ROOT}css/reset_new.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/import_new.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/base_new.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/home_b2c_new.css" rel="stylesheet" type="text/css" charset="utf-8" />

<script type="text/javascript" src="${WEB_ROOT}js/jquery.min.js"></script>
<script type="text/javascript" src="${WEB_ROOT}js/index_b2c.js" charset="utf-8"></script>
<script type="text/javascript" src="${WEB_ROOT}js/import.js"></script>

<style type="text/css">
.headpro_large {
	background:url(/img/activity/mall20131009big.jpg) top center no-repeat;
	height:400px;
	overflow:hidden;
}
.headpro_small {
	background:url(/img/activity/mall20131009small.jpg) top center no-repeat;
	height:90px;
	overflow:hidden;
}
#headpro_large a, #headpro_small a {
	display:block;
	height:100%;
}
#headpro_small a.close {
	border:4px solid #0079ca;
	position:absolute;
	top:5px;
	right:5px;
	z-index:9999;
	display:block;
	width:17px;
	height:17px;
	background:url(http://h.12580life.com/images/advertising/btn_icon_close.jpg) no-repeat no-repeat center center;
}
</style>
<title>首页-移动商城</title>
</head>
<body>
<div id="headpro_large" style=""> <a href="/act/samsung-note3.html" target="_blank"></a> </div>
<div id="headpro_small" style="display: none;" class="headpro_small"> <a href="/act/samsung-note3.html" target="_blank"></a> <a href="#" class="close"></a> </div>

<!--分类列-->
<div id="mosh_12580_header">
    <div id="top_menu">
        <div class="wrapper mauto">
            <p class="login-info"><a href="" class="icon_phone"><span></span>手机版</a> 您好，欢迎您来到移动商城！<a href=" ">登录</a> <a href="http://www.12580777.com/index/register.jsp?backURL=http%3A%2F%2Fwww.12580777.com">注册</a></p>
            <ul class="quick-menu">
                <li><a href=" ">我的订单</a> </li>
                |
                <li><a href=" ">购物车</a> </li>
                |
                <li><a href=" ">在线客户</a> </li>
                |
                <li><a href=" ">帮助中心</a></li>
                |
                <li class="last"><a target="_blank" href=" ">网站导航</a></li>
            </ul>
        </div>
    </div>
    <div id="header_nav">
        <div id="header">
            <div class="wrapper mauto"> <a id="logo" href=""><img src="${WEB_ROOT}img/new/logo.jpg" /></a>
                <div id="city"><a class="active" href="">苏州</a>
                    <div id="citylist">
                        <dl class="clearfix">
                            <dt>江苏：</dt>
                            <dd><a href="">城市1</a></dd>
                            <dd><a href="">城市1</a></dd>
                            <dd><a href="">城市1</a></dd>
                            <dd><a href="">城市1</a></dd>
                            <dd><a href="">城市1</a></dd>
                            <dd><a href="">城市1</a></dd>
                            <dd><a href="">城市1</a></dd>
                        </dl>
                        <dl class="clearfix">
                            <dt>其他：</dt>
                            <dd><a href="">城市1</a></dd>
                            <dd><a href="">城市1</a></dd>
                            <dd><a href="">城市1</a></dd>
                        </dl>
                    </div>
                </div>
                <div id="search_head">
                    <div class="search_wrapper "> <span class="icon_search"></span>
                        <form onsubmit=" " name="search" action=" " target="_top">
                            <div class="search_cat"> <a href="" class="hover">商品<span></span></a> <a href="">商户<span></span></a> <a href="">优惠券<span></span></a> <a href="">代金券<span></span></a> </div>
                            <input type="text" class="mod_search_txt no_cur" autocomplete="off" value="土豪金" />
                            <button class="search_submit"><span class="hide_clip">搜索</span></button>
                        </form>
                    </div>
                    <div class="search_keyword"> <a herf="">出油防晒</a> <a herf="">T恤</a> <a herf="">iPhone4S</a> </div>
                </div>
            </div>
        </div>
        <div id="nav">
            <div class="wrapper mauto">
                <ul class="nav_list">
                    <li class="curr"><a href="#">首页</a></li>
                    <li><a href="#">团购</a></li>
                    <li><a href="#">优惠券</a></li>
                    <li><a href="#">折扣商户</a></li>
                    <li><a href="#">代金券</a></li>
                    <li><a href="#">购物街</a></li>
                    <li><a href="#">G优惠</a></li>
                    <li><a href="#">汽车票</a></li>
                    <li><a href="#">电影票</a></li>
                    <li><a href="#">礼品卡</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--/分类列--> 
<!--分类列-->
<div class="wrapper" id="home_w"> 
    <!--分类树-->
    <div class="showad"></div>
    <div id="life-help" class="cate_tree" >
    	
        <ul class="life-help-nav">
        	<h2>商品服务分类</h2>
            <#list typeList as list1>
		    <#assign list1_typeId=list1['typeId']>
		    <#if (list1_index >= 10) >
				<#break>
			</#if>
	    
	    	<li class="life-item">
	    		<h3><a href="${WEB_ROOT}search/gotoSearch.chtml?type_id=${list1_typeId}&region_code=${regionCode}&keyword=${list1['displayName']}">${list1['displayName']}</a></h3>
	    		
	    		<ul style="width: 450px;" class="life-item-sub">	    		
		    		 <#list list1['tSysTypeDTOList'] as list2>
		    		<li>
			    		<dl>
				    		<dt><a href="${WEB_ROOT}search/gotoSearch.chtml?type_id=${list2['id']}&region_code=${regionCode}&keyword=${list2['name']}">${list2['name']}</a></dt>
				    		 <#list list2['typeList'] as list3>
				    		<dd><a href="${WEB_ROOT}search/gotoSearch.chtml?type_id=${list3['id']}&region_code=${regionCode}&keyword=${list3['name']}"> ${list3['name']}  </a></dd>
				    		 </#list>
			    		</dl>
		    		</li>
		    		</#list>
	    		</ul>
	    		
	    	</li>
	    	</#list>
            
        </ul>
    </div>
    <!--/分类树—->
	<!--焦点图-->
    <div class="index_slide" id="slider">
        <div class="main_slide">
            <ul class="body_slide" id="body_slide">
            <#list picMap['0'] as pic0>
		  		<#if (pic0_index >= 8)>
					<#break>
				</#if>
			  	<li>
				  	<a href="${pic0['linkUrl']}" target="_blank"><img src="${imgSvrHost}${channelPicPath}${pic0['picPath']}" height="280px" width="595px"></a>
			  	</li>
			</#list>
            </ul>
            
            <div class="bor_slide" id="bor_slide">
                <ul>
			  	<#list picMap['0'] as pic0>
			  	<#if (pic0_index >= 8)>
					<#break>
				</#if>
				  	<li class="<#if pic0_index = 0>curr</#if>"><a href="javascript:void('0');">${pic0_index + 1}</a></li>
				</#list>			  
			  	</ul>
                
            </div>
            
            
            <div class="main_slide_pro">
                <ul>
                    <li><a href=""><img src="${WEB_ROOT}img/new/explore_197_105.jpg" /></a></li>
                    <li><a href=""><img src="${WEB_ROOT}img/new/explore_197_105.jpg" /></a></li>
                    <li><a href=""><img src="${WEB_ROOT}img/new/explore_197_105.jpg" /></a></li>
                </ul>
            </div>
        </div>
    </div>
    <!--/焦点图—->
	<!--右-->
    <div class="row_sidebar"> 
        <!--新品-->
        
        <div class="mod_gb">
        	<a href="" class="mb10"><img src="${WEB_ROOT}img/new/home_news_pro.jpg" /></a>
            <div class="mod_gb_hd"> <span class="hide_clip">公告</span> </div>
            <ul class="homenews">
                <li><a href="">[开奖]良品家蓝罐曲奇特价活动…</a></li>
                <li><a href="">[升级]张飞牛肉免费试吃活动开…</a></li>
                <li><a href="">[活动]荃记甜品免费试吃活动开。</a></li>
            </ul>
           	<div id="home_menu">
            	<h2>更多服务</h2>
            	<ul>
                	<li><a href="" class="iocn1"><img src="${WEB_ROOT}img/new/iocn1.gif" /></a></li>
                	<li><a href="" class="iocn2"><img src="${WEB_ROOT}img/new/iocn2.gif" /></a></li>
                    <li><a href="" class="iocn3"><img src="${WEB_ROOT}img/new/iocn3.gif" /></a></li>
                    <li><a href="" class="iocn4"><img src="${WEB_ROOT}img/new/iocn4.gif" /></a></li>
                    <li><a href="" class="iocn5"><img src="${WEB_ROOT}img/new/iocn5.gif" /></a></li>
                </ul>
            </div>
        </div>
        <!--/新品-->
        <div class="clr"></div>
        <!--/分类列--> 
    </div>
    
    <!--/分类列-->
    
    <div id="home_pro_menu"> </div>
    <div id="home_content" class="clearfix">
        <div class="left">
            <div id="home_content_dzq">
                <div class="home_content_title">
                    <h2>电子卡券 <a href="">更多<i class="en">&gt;&gt; </i></a></h2>
                </div>
                <ul class="clearfix">
                    <li class="home_content_dzq_item"> <a href=""><span class="tip">团购</span><img src="${WEB_ROOT}img/new/explore_200_120.jpg" />
                        <h5>测试商品名称</h5>
                        <p><span class="price">￥19.99</span> <span class="oldprice">￥25.00</span></p>
                        </a> <a href=""><img src="${WEB_ROOT}img/new/explore_200_120.jpg" />
                        <h5>测试商品名称</h5>
                        <p><span class="price">￥19.99</span> <span class="oldprice">￥25.00</span></p>
                        </a> </li>
                    <li class="home_content_dzq_item b2"> <a href=""><span class="tip">特价</span><img src="${WEB_ROOT}img/new/explore_200_120.jpg" />
                        <h5>测试商品名称</h5>
                        <p><span class="price">￥19.99</span> <span class="oldprice">￥25.00</span></p>
                        </a> <a href=""><img src="${WEB_ROOT}img/new/explore_200_120.jpg" />
                        <h5>测试商品名称</h5>
                        <p><span class="price">￥19.99</span> <span class="oldprice">￥25.00</span></p>
                        </a> </li>
                    <li class="home_content_dzq_item"> <a href=""><img src="${WEB_ROOT}img/new/explore_200_120.jpg" />
                        <h5>测试商品名称</h5>
                        <p><span class="price">￥19.99</span> <span class="oldprice">￥25.00</span></p>
                        </a> <a href=""><img src="${WEB_ROOT}img/new/explore_200_120.jpg" />
                        <h5>测试商品名称</h5>
                        <p><span class="price">￥19.99</span> <span class="oldprice">￥25.00</span></p>
                        </a> </li>
                </ul>
            </div>
            <div id="home_content_yhq">
                <div class="home_content_title">
                    <h2>优惠券 <a href=""></a><a href="">更多<i class="en">&gt;&gt; </i></a></h2>
                </div>
                <ul class="clearfix">
                    <li> <a href="" class="bdashed1"><img src="${WEB_ROOT}img/new/explore_70.jpg" />
                        <h5>测试商品名称</h5>
                        <p>商品口号</p>
                        </a> <a href=""><img src="${WEB_ROOT}img/new/explore_70.jpg" />
                        <h5>测试商品名称</h5>
                        <p>商品口号</p>
                        </a> </li>
                    <li class="home_content_dzq_item b2"> <a href="" class="bdashed1"><img src="${WEB_ROOT}img/new/explore_70.jpg" />
                        <h5>测试商品名称</h5>
                        <p>商品口号</p>
                        </a> <a href=""><img src="${WEB_ROOT}img/new/explore_70.jpg" />
                        <h5>测试商品名称</h5>
                        <p>商品口号</p>
                        </a> </li>
                    <li> <a href="" class="bdashed1"><img src="${WEB_ROOT}img/new/explore_70.jpg" />
                        <h5>测试商品名称</h5>
                        <p>商品口号</p>
                        </a> <a href=""><img src="${WEB_ROOT}img/new/explore_70.jpg" />
                        <h5>测试商品名称</h5>
                        <p>商品口号</p>
                        </a> </li>
                </ul>
            </div>
            <div id="home_content_jxnr">
                <div class="home_content_title">
                    <h2>精选活动 <span><a href="">精打细算</a> <a href="">吃货之家</a> <a href="">探店晒单</a></span></h2>
                </div>
                <ul class="clearfix">
                    <a class="jxnr_pic" href=""><img src="${WEB_ROOT}img/new/explore_220_110.jpg" /></a>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                    <li><a href="">测试标题测试标题长度</a></li>
                </ul>
            </div>
        </div>
        <div class="right">
            <div class="home_content_title">
                <h2>精选商户 <a href="">更多<i class="en">&gt;&gt; </i></a></h2>
            </div>
            <div id="home_content_sh">
                <div class="home_content_sh_main">
                    <div class="home_content_sh_tab"> <a href="">餐饮美食</a> <a href="">休闲娱乐</a> </div>
                    <div class="home_content_sh_content">
                        <dl>
                            <dt> <a href=""><img src="${WEB_ROOT}img/new/explore_40.jpg" />
                                <h2>达人桌游君之缘店</h2>
                                <p>达人桌游君之缘店达人桌游君之缘</p>
                                </a></dt>
                            <dd><span>7.8</span>折</dd>
                        </dl>
                        <dl>
                            <dt> <a href=""><img src="${WEB_ROOT}img/new/explore_40.jpg" />
                                <h2>达人桌游君之缘店</h2>
                                <p>达人桌游君之缘店达人桌游君之缘</p>
                                </a></dt>
                            <dd><span>7.8</span>折</dd>
                        </dl>
                        <dl>
                            <dt> <a href=""><img src="${WEB_ROOT}img/new/explore_40.jpg" />
                                <h2>达人桌游君之缘店</h2>
                                <p>达人桌游君之缘店达人桌游君之缘</p>
                                </a></dt>
                            <dd><span>7.8</span>折</dd>
                        </dl>
                        <dl>
                            <dt> <a href=""><img src="${WEB_ROOT}img/new/explore_40.jpg" />
                                <h2>达人桌游君之缘店</h2>
                                <p>达人桌游君之缘店达人桌游君之缘</p>
                                </a></dt>
                            <dd><span>7.8</span>折</dd>
                        </dl>
                        <dl>
                            <dt> <a href=""><img src="${WEB_ROOT}img/new/explore_40.jpg" />
                                <h2>达人桌游君之缘店</h2>
                                <p>达人桌游君之缘店达人桌游君之缘</p>
                                </a></dt>
                            <dd><span>7.8</span>折</dd>
                        </dl>
                        <a href="" class="home_content_sh_pro"><img src="${WEB_ROOT}img/new/explore_pro_1.jpg" /></a> </div>
                </div>
                <div id="home_content_bd">
                    <div class="home_content_title">
                        <h2>人气榜单 <a href="">更多<i class="en">&gt;&gt; </i></a></h2>
                    </div>
                    <dl class="list1">
                        <span>1</span>
                        <dt>排队也吃不上的餐厅</dt>
                        <dd>这些餐厅门口永远都排满了人。究竟有多
                            好吃，能让人们趋之若鹜，即使排长…</dd>
                    </dl>
                    <dl class="list2">
                        <span>2</span>
                        <dt>排队也吃不上的餐厅</dt>
                        <dd>这些餐厅门口永远都排满了人。究竟有多
                            好吃，能让人们趋之若鹜，即使排长…</dd>
                    </dl>
                    <dl class="list3">
                        <span>3</span>
                        <dt>排队也吃不上的餐厅</dt>
                        <dd>这些餐厅门口永远都排满了人。究竟有多
                            好吃，能让人们趋之若鹜，即使排长…</dd>
                    </dl>
                    <dl class="list4">
                        <span>4</span>
                        <dt>排队也吃不上的餐厅</dt>
                        <dd>这些餐厅门口永远都排满了人。究竟有多
                            好吃，能让人们趋之若鹜，即使排长…</dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
    <div id="home_movie">
        <div class="home_content_title">
            <h2>电影资讯 <a href="">更多<i class="en">&gt;&gt; </i></a></h2>
        </div>
        <div class="home_content_content">
            <div class="movie_pro">
            	<div class="movie_pro_item">
    				<span class="rog">8.3</span>
                	<img class="movie_pro_item_screenshot" src="${WEB_ROOT}img/new/explore_90_115.jpg" />
                    <h5><a href="">影片名称</a></h5>
                    <p>101分钟</p>
                    <a href="" class="movie_pro_item_btn"><img src="${WEB_ROOT}img/new/btn_movie.jpg" /></a>
                </div>
                <div class="movie_pro_item">
	    			<span class="rog">8.3</span>
                	<img class="movie_pro_item_screenshot" src="${WEB_ROOT}img/new/explore_90_115.jpg" />
                    <h5><a href="">影片名称</a></h5>
                    <p>101分钟</p>
                    <a href="" class="movie_pro_item_btn"><img src="${WEB_ROOT}img/new/btn_movie.jpg" /></a>
                </div>
                <div class="movie_pro_item">
	                <span class="rog">8.3</span>
                	<img class="movie_pro_item_screenshot" src="${WEB_ROOT}img/new/explore_90_115.jpg" />
                	<h5><a href="">影片名称</a></h5>
                    <p>101分钟</p>
                    <a href="" class="movie_pro_item_btn"><img src="${WEB_ROOT}img/new/btn_movie.jpg" /></a>
                </div>
                <div class="movie_pro_item">
                	<span class="rog">8.3</span>
                	<img class="movie_pro_item_screenshot" src="${WEB_ROOT}img/new/explore_90_115.jpg" />
                	<h5><a href="">影片名称</a></h5>
                    <p>101分钟</p>
                    <a href="" class="movie_pro_item_btn"><img src="${WEB_ROOT}img/new/btn_movie.jpg" /></a>
                </div> 
            </div>
            <ul class="">
            	<li><img src="${WEB_ROOT}img/new/explore_150_90.jpg"  />
                景甜着紧身裙露底浑然不
知,撩发微笑秀女神风采</li>
                <li><img src="${WEB_ROOT}img/new/explore_150_90.jpg"  />
                景甜着紧身裙露底浑然不
知,撩发微笑秀女神风采</li>
                <li><img src="${WEB_ROOT}img/new/explore_150_90.jpg"  />
                景甜着紧身裙露底浑然不
知,撩发微笑秀女神风采</li>
            </ul>
        </div>
    </div>

<!--楼层1-->
<#list floorList as floor>
<#assign floorId=floor['floorId']>
<#if (floorId > 0) >
<!--楼层1-->
 <div class="foor f${floorId} mt20">
    <div class="floor_hd">
	    <div class="floor_title">
		    <span>${floor['title']}</span>
	    </div>
	    <ul class="cate_r">
	    	<#list rcmdMap['1_${floorId}'] as rcmd>
	 		<#if (rcmd_index >= 5) >
					<#break>
			</#if>
		    <li><a href="${rcmd['linkUrl']}" title="${rcmd['displayName']}" target="_blank">${rcmd['displayName']}</a>
		    <#if rcmd_has_next> | </#if></li>
		    </#list>	   
	    </ul>
    </div>
	<div class="floor_bd" id="tf_300003">
			<!--中-->
			<div class="goods_wrap">
				<div class="goods_list">
					<ul id="POS_1_300003">
						<#assign list_max=8>
						<#if picMap['${floorId}'] ??>
							<#assign list_max=5>
						</#if>
						
						<#list idMap['${floorId}'] as id>
						<#if (id_index >= list_max) >
							<#break>
						</#if>
						<li class="goods">
						<#if (productMap['${id}']['GIseckill'] == '3')>
								<div class="icon_xsg"><img src="/img/icon_xsg.png"></div>
							</#if>
							<div class="pic_wrap">							
								<a href="${pathUtil.getPathById('1', '${id}')}"  target="_blank">
									<img src="${pathUtil.getPathById('2', '${id}')}N3/${productMap['${id}']['GWebPath']}" alt="" title="${productMap['${id}']['GMarketContent']}">
									<i class=""></i>
								</a>							
							</div>							
							<div class="title_wrap">
								<a href="${pathUtil.getPathById('1', '${id}')}" target="_blank" >${productMap['${id}']['GName']}
								<span class="sum_wrap">${productMap['${id}']['GMarketContent']}</span>
								</a>
							</div>	
							<div class="price_wrap">
								<p class="price"><span>￥</span>${productMap['${id}']['GShopPrice']}</p>
							</div>	
						</li>
						
						<#if (list_max == 5 && id_index == 0)>
						<li class="goods_ad">							
							<ul class="tab_con">
								<#list picMap['${floorId}'] as pic>
								<#if (pic_index >= 8)>
								<#break>
								</#if>
								<li class="<#if pic_index = 0>curr</#if>">						
									<a href="${pic['linkUrl']}"  target="_blank">
										<img src="${imgSvrHost}${channelPicPath}${pic['picPath']}" width="582px" height="238px;"  alt="${pic['picAlt']}">
									</a>
								</li>
								</#list>
							</ul>
							<ul class="tab_handle tab_handle_${picMap['${floorId}']?size}">
						       <#list picMap['${floorId}'] as pic>
							       <#if (pic_index >= 8)>
									<#break>
									</#if>
								  	<li class="<#if pic_index == 0>curr</#if>"><a href="javascript:void('0');">${pic_index + 1}</a></li>
								</#list>
							</ul>		
						</li>
						</#if>
						
					    </#list>
						
						
						</ul>
				</div>
			</div>
			<!--/中-->

			<!--右-->
			<div class="col_right">
				<!--选项卡模块-->
				<div class="mod_tab" id="hideTab1">
					<div class="tab_hd">
						<ul>
							<li class="current">热销榜</li>
						</ul>
					</div>
					<div class="tab_bd">
						<ul class="sale_hot">
							<#list hotMap['${floorId}'] as hot>
							<li>
								<div class="pic_wrap">
									<a href="${pathUtil.getPathById('1', '${hot[\'id\'][\'GId\']}')}">
										<img src="${pathUtil.getPathById('2', '${hot[\'id\'][\'GId\']}')}N4/${hot['GWebPath']}" alt="${hot['GName']}" width="80" height="80">
									</a>
									<i class="dot_first"><span class="hide_clip">${hot_index + 1}</span></i>
								</div>							
								<div class="txt_wrap">								
									<p class="price"><span>¥</span>${hot['GShopPrice']}</p>								
									<p class="name"><a target="_blank" href="${pathUtil.getPathById('1', '${hot[\'id\'][\'GId\']}')}">${hot['GName']}</a></p>			
															
								</div>										
							</li>
							</#list>
							</ul>			
					</div>
				</div>
				<!--/选项卡模块-->
				<!--广告推荐-->
				<div class="mod_recon">
					<ul>
					<#list rcmdMap['2_${floorId}'] as rcmd2>
						<li>
						<a href="${rcmd2['linkUrl']}" target="_blank" ytag="20300">
						<img alt="${rcmd2['displayName']}" title="${rcmd2['displayName']}" width="200" height="89" src="${imgSvrHost}${channelPicPath}${rcmd2['imagePath']}"></a>
						</li>
						<#break>
					</#list>						
					 </ul>
				</div>
				<!--/广告推荐-->
			</div>
			<!--/右-->			
		</div>
		<div class="clr mt20"></div>
 </div>
<!-- /楼层1-->
</#if>
</#list>
<!-- /楼层1-->

</div>

<script type="text/javascript">
jQuery(document).ready(function(){
    lifeHelper();
    slideShow();   
    eachAd();
	if(jQuery.cookie('mallIndexVisit') == '1'){
		$("#headpro_large").hide();
		$("#headpro_small").show();	
	}else{
		jQuery.cookie('mallIndexVisit', 1, {
			domain: '12580life.com',
			path:'/'
		});
		jQuery("#headpro_large").addClass("headpro_large");
		setTimeout(function(){jQuery("#headpro_large").slideUp(3000,function(){jQuery("#headpro_small").slideDown(2000)})}, 4000);
	}
	 $("#headpro_small a.close").click(function(){ 
			$("#headpro_small").remove();
			return false;
		})
});

</script>

<script type="text/javascript" src="${WEB_ROOT}js/foot.js"></script>

</body>
</html>