<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="mo生活商城支持【移动积分/m值/商城币/手机支付/话费支付】，提供居家生活、美容护肤、手表饰品、手机数码、家用电器、服装箱包、食品保健、运动户外、母婴用品等商品。" />
<link href="${WEB_ROOT}css/base.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/layout.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/home_b2c.css?v0123_2" rel="stylesheet" type="text/css" charset="utf-8" />
<style type="text/css">
#guangshangcheng_nav{background: url(${WEB_ROOT}img/bg_guangshangcheng_nav.gif) repeat scroll 0 0 #f0f0f0;height: 35px;}
#guangshangcheng_nav  li {display: block;float: left;font-size: 12px;font-weight: bold;height: 35px;margin: 0;overflow: visible;padding: 0;position: relative;z-index: 998;}
#guangshangcheng_nav li a {  display: inline-block;height: 25px;line-height: 25px;margin: 0;overflow: hidden;padding: 5px 25px;position: relative;width: auto;z-index: 998;color:#000;}
#guangshangcheng_nav li a.active,#guangshangcheng_nav li a:hover{background:url(${WEB_ROOT}img/bg_guangshangcheng_navhover.gif) center center no-repeat; color:#E95098;} 
#guangshangcheng_nav li .line{ width:2px; display: inline-block;height: 35px;line-height: 35px;overflow: hidden; padding:0; background: url(${WEB_ROOT}img/bg_guangshangcheng_navline.gif) no-repeat scroll 0 0;}
</style>
<script type="text/javascript">
var GLOBAL_AREA_CODE = '${areaCode}';
var GLOBAL_REGION_CODE = '${regionCode}';
<#if picMap['0'] ??>
var picList0_size = ${picMap['0']?size};
<#else>
var picList0_size = 0;
</#if>
if(picList0_size > 8){
  picList0_size = 8;
}
</script>
<script type="text/javascript" src="${WEB_ROOT}js/jquery.min.js"></script>
<script type="text/javascript" src="${WEB_ROOT}js/index_b2c.js" charset="utf-8"></script>
<script type="text/javascript" src="${WEB_ROOT}js/jquery.cookie.js"></script>

<title>mo生活商城-中国移动通信集团旗下的购物平台</title>
</head>
<body>

<!--分类列-->
${headerHtml}

<!--/分类列-->
<!--分类列-->
   <ul id="guangshangcheng_nav">
        <div class="wrapper">
    	    <li><a class="active" href="/">逛商城首页</a></li>
            <li><span class="line"></span></li>
            <li><a href="http://mall.12580life.com/mall-market/act/mobile/1121.html" target="_blank">话费专区</a></li>
        </div>
    </ul>
<div class="wrapper">



	<!--分类树-->
	<div id="life-help" class="cate_tree" >
	    <ul class="life-help-nav">
	    <#list typeList as list1>
	    <#assign list1_typeId=list1['typeId']>
	    <#if (list1_index >= 10) >
			<#break>
		</#if>
	    
	    	<li class="life-item">
	    		<h3><a href="${WEB_ROOT}search/gotoSearch.chtml?type_id=${list1_typeId}&region_code=${regionCode}&keyword=${list1['displayName']}">${list1['displayName']}</a></h3>
	    		<#assign adList=adMap['${list1_typeId}']>
    		    <#assign adWidth='450px;'>
    			<#if adList>
    				
    			<#else>
    				<#if adMap['global']??>
    				<#assign adList=adMap['global']>
    				</#if>
    			</#if>
    			
    			<#if adList>
    				<#assign adWidth='580px;'>
    			</#if>
    			
	    		<ul class="life-item-sub" style="width: ${adWidth}">	    		
	    			<#if adList>
	    			<div class="life-item-sub-pro">
	    			<#assign ad2_exist=0>
	    			<#list adList as ad>
	                    <#if (ad[2] == '1')>
	                    <a href="${ad[3]}">
	                    	<img src="${channelAdPath}${ad[4]}" width="160px" height="80px">
	                    </a>
	                    </#if>
	                    
	                    <#if (ad[2] == '2')>
	                    	<#if ad2_exist!= 1><h2>推荐：</h2></#if>
	                    	<p><a href="${ad[3]}" target="_blank">${ad[4]}</a></p>
	                    	<#assign ad2_exist=1>
	                    </#if>
	                    
                    </#list>
                    </div>
                    </#if>
                
	    		
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
			  	<a href="${pic0['linkUrl']}" target="_blank"><img src="${imgSvrHost}${channelPicPath}${pic0['picPath']}" height="320px" width="580px"></a>
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
	  	
	  </div>
	</div>
	<!--/焦点图—->
	<!--右-->
	<div class="row_sidebar">
		<!--新品-->
		<div class="mod_gb">
					<#if idMap['0'] ??>
					<div class="mod_gb_hd">
								<span class="hide_clip">今日推荐</span>
					</div>
		  
					<div class="tab_con">
					<ul>
						<#list idMap['0'] as id0>
						<#if (id0_index >= 3) >
							<#break>
						</#if>
						<li class="<#if id0_index = 0>curr</#if>">
							<p>
								<a href="${pathUtil.getPathById('1', '${id0}')}" target="_blank">
									<img alt="${productMap['${id0}']['GName']}" src="${pathUtil.getPathById('2', '${id0}')}N3/${productMap['${id0}']['GWebPath']}" width="160px;" height="160px;" />
								</a>
							</p>
							<#assign now_price0=productMap['${id0}']['GRedPrice']!productMap['${id0}']['GShopPrice']>
							<p class="price">￥<span>${now_price0}</span>
							<#if productMap['${id0}']['GMarketPrice'] != now_price0>
							<span class="oldprice">￥${productMap['${id0}']['GMarketPrice']}</span>
							</#if>
							</p>
							<h3>
								<a href="${pathUtil.getPathById('1', '${id0}')}" target="_blank">${productMap['${id0}']['GName']}</a>
							</h3>
						</li>
						</#list>
						
					 </ul>
				</div>
				 <#if (idMap['0']?size > 1) >
				 <ul class="tab_handle">
					<#list idMap['0'] as id0>
					<#if (id0_index >= 3) >
							<#break>
					</#if>
				  	<li class="<#if id0_index = 0>curr</#if>"><a href="javascript:void('0');">${id0_index + 1}</a></li>
				    </#list>	
				 </ul>
				 </#if>
				 
				</#if>	 			
								
		</div>
		<!--/新品-->
    <div class="clr"></div>
	<!--/分类列-->
</div>

<!--/分类列-->

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
							<#if (productMap['${id}']['GIseckill'] == '9')>								
								<div class="goods-discount-tag"></div>		
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
								<p class="price">
									<#assign now_price=productMap['${id}']['GRedPrice']!productMap['${id}']['GShopPrice']>
									<span class="now_price">￥${now_price}</span>
									<#if productMap['${id}']['GMarketPrice'] != now_price>
									<span class="old_price">￥${productMap['${id}']['GMarketPrice']}</span>
									</#if>
								</p>
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
								     <#assign hot_now_price=hot['GRedPrice']!hot['GShopPrice']>							
									<p class="price">
										<span>¥</span>${hot_now_price}
										<#if hot['GMarketPrice'] != hot_now_price>
										<span class="oldprice">￥${hot['GMarketPrice']}</span>
										</#if>
									</p>								
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

</div>
<script type="text/javascript">

jQuery(document).ready(function(){
    
    lifeHelper();
    slideShow();   
    eachAd();

});

</script>

<script type="text/javascript" src="${WEB_ROOT}js/foot.js?v1112"></script>

</body>
</html>