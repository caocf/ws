<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${WEB_ROOT}css/base.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/layout.css" rel="stylesheet" type="text/css" />
<link href="${WEB_ROOT}css/home_b2c.css" rel="stylesheet" type="text/css" charset="utf-8" />

<script type="text/javascript">
var GLOBAL_AREA_CODE = '${areaCode}';
<#if picMap['0'] ??>
var picList0_size = ${picMap['0']?size};
<#else>
var picList0_size = 0;
</#if>
</script>
<script type="text/javascript" src="${WEB_ROOT}js/jquery.min.js"></script>
<script type="text/javascript" src="${WEB_ROOT}js/index_b2c.js" charset="utf-8"></script>
<script type="text/javascript" src="${WEB_ROOT}js/jquery.cookie.js"></script>

<title></title>
</head>
<body>
<!--分类列-->
<script type="text/javascript" src="${WEB_ROOT}js/head.js"></script>

<!--/分类列-->
<!--分类列-->
<div class="wrapper">
	<!--分类树-->
	<div id="life-help" class="cate_tree" >
	    <ul class="life-help-nav">
	    <#list typeList as list1>
	    
	    <#if (list1_index >= 10) >
			<#break>
		</#if>
	    
	    	<li class="life-item">
	    		<h3><a href="${WEB_ROOT}search/gotoSearch.chtml?type_id=${list1['typeId']}&keyword=${list1['displayName']}">${list1['displayName']}</a></h3>
	    		<ul class="life-item-sub">
	    			 <#list list1['tSysTypeDTOList'] as list2>
		    		<li>
			    		<dl>
				    		<dt>${list2['name']}</dt>
				    		 <#list list2['typeList'] as list3>
				    		<dd><a href="${WEB_ROOT}search/gotoSearch.chtml?type_id=${list3['id']}&keyword=${list3['name']}"> ${list3['name']}  </a></dd>
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
		  	<li>
			  	<a href="${pic0['linkUrl']}"><img src="${imgSvrHost}${pic0['picPath']}" height="320px" width="580px"></a>
		  	</li>
		</#list>
		
	  	</ul>
	  	<div class="bor_slide" id="bor_slide">
		  	<ul>
		  	<#list picMap['0'] as pic0>
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
					<#if ids5 ??>
					<div class="mod_gb_hd">
								<span class="hide_clip">今日推荐</span>
					</div>
		  
					<div class="tab_con">
					<ul>
						<#list ids5 as id5>
						<#if (id5_index >= 4) >
							<#break>
						</#if>
						<li class="<#if id5_index = 0>curr</#if>">
							<p>
								<a href="${pathUtil.getPathById('1', '${id5}')}" target="_blank">
									<img alt="${productMap['${id5}']['GName']}" src="${pathUtil.getPathById('2', '${id5}')}N3/${productMap['${id5}']['GWebPath']}" width="160px;" height="160px;" />
								</a>
							</p> 
							<p class="price">￥<span>${productMap['${id5}']['GShopPrice']}</span></p>
							<h3>
								<a href="${pathUtil.getPathById('1', '${id5}')}" target="_blank">${productMap['${id5}']['GName']}</a>
							</h3>
						</li>
						</#list>
						
					 </ul>
				</div>
				 <#if (ids5?size > 1) >
				 <ul class="tab_handle">
					<#list ids5 as id5>
					<#if (id5_index >= 4) >
							<#break>
					</#if>
				  	<li class="<#if id5_index = 0>curr</#if>"><a href="javascript:void('0');">${id5_index + 1}</a></li>
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

<!--楼层1-->
 <div class="foor f1 mt20">
    <div class="floor_hd">
	    <div class="floor_title">
		    <span>手机数码</span>
	    </div>
	    <ul class="cate_r">
	    	<#list rcmdMap['1_1'] as rcmd1>
		    <li><a href="${rcmd1['linkUrl']}" title="${rcmd1['displayName']}" target="_blank">${rcmd1['displayName']}</a>
		    <#if rcmd1_has_next> | </#if></li>
		    </#list>	   
	    </ul>
    </div>
	<div class="floor_bd" id="tf_300003">
			<!--中-->
			<div class="goods_wrap">
				<div class="goods_list">
					<ul id="POS_1_300003">
						<#assign list_max1=8>
						<#if picMap['1'] ??>
							<#assign list_max1=5>
						</#if>
						<#if ids1 ??>
						<#list ids1 as id1>
						<#if (id1_index >= list_max1) >
							<#break>
						</#if>
						<li class="goods">							
							<div class="pic_wrap">							
								<a href="${pathUtil.getPathById('1', '${id1}')}" >
									<img src="${pathUtil.getPathById('2', '${id1}')}N3/${productMap['${id1}']['GWebPath']}" alt="" title="${productMap['${id1}']['GMarketContent']}">
									<i class=""></i>
								</a>							
							</div>							
							<div class="title_wrap">
								<a href="${pathUtil.getPathById('1', '${id1}')}" target="_blank" >${productMap['${id1}']['GName']}
								<span class="sum_wrap">${productMap['${id1}']['GMarketContent']}</span>
								</a>
							</div>	
							<div class="price_wrap">
								<p class="price"><span>￥</span>${productMap['${id1}']['GShopPrice']}</p>
							</div>	
						</li>
						
						<#if (list_max1 == 5 && id1_index == 0)>
						<li class="goods_ad">							
							<ul class="tab_con">
								<#list picMap['1'] as pic1>
								<li class="<#if pic1_index = 0>curr</#if>">						
									<a href="#" >
										<img src="${imgSvrHost}${pic1['picPath']}" width="582px" height="238px;"  alt="${pic1['picAlt']}">
									</a>
								</li>
								</#list>
							</ul>
							<ul class="tab_handle">
						       <#list picMap['1'] as pic1>
								  	<li class="<#if pic1_index == 0>curr</#if>"><a href="javascript:void('0');">${pic1_index + 1}</a></li>
								</#list>
							</ul>		
						</li>
						</#if>
						
					    </#list>
						</#if>
						
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
							<#list hotList1 as hot1>
							<li>
								<div class="pic_wrap">
									<a href="${pathUtil.getPathById('1', '${hot1[\'id\'][\'GId\']}')}">
										<img src="${pathUtil.getPathById('2', '${hot1[\'id\'][\'GId\']}')}N4/${hot1['GWebPath']}" alt="${hot1['GName']}" width="80" height="80">
									</a>
									<i class="dot_first"><span class="hide_clip">${hot1_index + 1}</span></i>
								</div>							
								<div class="txt_wrap">								
									<p class="price"><span>¥</span>${hot1['GShopPrice']}</p>								
									<p class="name"><a target="_blank" href="${pathUtil.getPathById('1', '${hot1[\'id\'][\'GId\']}')}">${hot1['GName']}</a></p>			
									<!-- 
									<p class="feedback">
										<span class="rank">
										<span class="rank_inner"></span></span>
										<a href="http://item.51buy.com/item-504259.html?cp-ptss=1302-1-504259-t#review_box" target="_blank">42</a>条
									</p> -->						
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
					<#list rcmdMap['2_1'] as rcmd21>
						<li>
						<a href="${rcmd21['linkUrl']}" target="_blank" ytag="20300">
						<img alt="${rcmd21['displayName']}" title="${rcmd21['displayName']}" width="200" height="89" src="${imgSvrHost}${rcmd21['imagePath']}"></a>
						</li>
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

<!--楼层2-->
 <div class="foor f2 mt20">
    <div class="floor_hd">
	    <div class="floor_title">
		    <span>生活家居</span>
	    </div>
	    <ul class="cate_r">		     
		    <#list rcmdMap['1_2'] as rcmd2>
		    <li><a href="${rcmd2['linkUrl']}" title="${rcmd2['displayName']}" target="_blank">${rcmd2['displayName']}</a>
		    <#if rcmd2_has_next> | </#if></li>
		    </#list>	
	    </ul>
    </div>
	<div class="floor_bd" id="tf_300003">
			<!--中-->
			<div class="goods_wrap">
				<div class="goods_list">
					<ul id="POS_1_300003">
					<#assign list_max2=8>
					<#if picMap['2'] ??>
						<#assign list_max2=5>
					</#if>
					<#if ids2 ??>
						<#list ids2 as id2>
						
						<#if (id2_index >= list_max2) >
							<#break>
						</#if>
						<li class="goods">						
							<div class="pic_wrap">							
								<a href="${pathUtil.getPathById('1', '${id2}')}" >
									<img src="${pathUtil.getPathById('2', '${id2}')}N3/${productMap['${id2}']['GWebPath']}" alt="" title="${productMap['${id2}']['GMarketContent']}"><i class=""></i></a>							
							</div>							
							<div class="title_wrap">
								<a href="${pathUtil.getPathById('1', '${id2}')}" target="_blank" >${productMap['${id2}']['GName']}
								<span class="sum_wrap">${productMap['${id2}']['GMarketContent']}</span></a></div>	
								<div class="price_wrap">
								<p class="price"><span>¥</span>${productMap['${id2}']['GShopPrice']}</p></div>	
						</li>
						<#if (list_max2 == 5 && id2_index == 0)>
						<li class="goods_ad">							
							<ul class="tab_con">
								<#list picMap['2'] as pic2>
								<li class="<#if pic2_index = 0>curr</#if>">						
									<a href="#" >
										<img src="${imgSvrHost}${pic2['picPath']}" width="582px" height="238px;"  alt="${pic2['picAlt']}">
									</a>
								</li>
								</#list>
							</ul>
							<ul class="tab_handle">
						       <#list picMap['2'] as pic2>
								  	<li class="<#if pic2_index == 0>curr</#if>"><a href="javascript:void('0');">${pic2_index + 1}</a></li>
								</#list>
							</ul>		
						</li>
						</#if>
						
					
						</#list>
						
					   </#if>
					
						
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
							
							<#list hotList2 as hot2>
							<li>
								<div class="pic_wrap">
									<a href="${pathUtil.getPathById('1', '${hot2[\'id\'][\'GId\']}')}">
										<img src="${pathUtil.getPathById('2', '${hot2[\'id\'][\'GId\']}')}N4/${hot2['GWebPath']}" alt="${hot2['GName']}" width="80" height="80">
									</a>
									<i class="dot_first"><span class="hide_clip">${hot2_index + 1}</span></i>
								</div>							
								<div class="txt_wrap">								
									<p class="price"><span>¥</span>${hot2['GShopPrice']}</p>								
									<p class="name"><a target="_blank" href="${pathUtil.getPathById('1', '${hot2[\'id\'][\'GId\']}')}">${hot2['GName']}</a></p>			
									<!-- 
									<p class="feedback">
										<span class="rank">
										<span class="rank_inner"></span></span>
										<a href="http://item.51buy.com/item-504259.html?cp-ptss=1302-1-504259-t#review_box" target="_blank">42</a>条
									</p> -->						
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
						<#list rcmdMap['2_2'] as rcmd22>
						<li>
						<a href="${rcmd22['linkUrl']}" target="_blank" ytag="20300">
						<img alt="${rcmd22['displayName']}" title="${rcmd22['displayName']}" width="200" height="89" src="${imgSvrHost}${rcmd22['imagePath']}"></a>
						</li>
					</#list>					
						</ul>
				</div>
				<!--/广告推荐-->
			</div>
			<!--/右-->			
		</div>
		<div class="clr mt20"></div>
 </div>
<!-- /楼层2-->
<!--楼层3-->
 <div class="foor f3 mt20">
    <div class="floor_hd">
	    <div class="floor_title">
		    <span>品牌电器</span>
	    </div>
	    <ul class="cate_r">
		    <#list rcmdMap['1_3'] as rcmd3>
		    <li><a href="${rcmd3['linkUrl']}" title="${rcmd3['displayName']}" target="_blank">${rcmd3['displayName']}</a>
		    <#if rcmd3_has_next> | </#if></li>
		    </#list>
	    </ul>
    </div>
	<div class="floor_bd" id="tf_300003">
			<!--中-->
			<div class="goods_wrap">
				<div class="goods_list">
					<ul id="POS_1_300003">
						
					<#assign list_max3=8>
					<#if picMap['3'] ??>
						<#assign list_max3=5>
					</#if>
					<#if ids3 ??>
						<#list ids3 as id3>
						
						<#if (id3_index >= list_max3) >
							<#break>
						</#if>
						<li class="goods">						
							<div class="pic_wrap">							
								<a href="${pathUtil.getPathById('1', '${id3}')}" >
									<img src="${pathUtil.getPathById('2', '${id3}')}N3/${productMap['${id3}']['GWebPath']}" alt="" title="${productMap['${id3}']['GMarketContent']}"><i class=""></i></a>							
							</div>							
							<div class="title_wrap">
								<a href="${pathUtil.getPathById('1', '${id3}')}" target="_blank" >${productMap['${id3}']['GName']}
								<span class="sum_wrap">${productMap['${id3}']['GMarketContent']}</span></a></div>	
								<div class="price_wrap">
								<p class="price"><span>¥</span>${productMap['${id3}']['GShopPrice']}</p></div>	
						</li>
						<#if (list_max3 == 5 && id3_index == 0)>
						<li class="goods_ad">							
							<ul class="tab_con">
								<#list picMap['3'] as pic3>
								<li class="<#if pic3_index = 0>curr</#if>">						
									<a href="#" >
										<img src="${imgSvrHost}${pic3['picPath']}" width="582px" height="238px;"  alt="${pic3['picAlt']}">
									</a>
								</li>
								</#list>
							</ul>
							<ul class="tab_handle">
						       <#list picMap['3'] as pic3>
								  	<li class="<#if pic3_index == 0>curr</#if>"><a href="javascript:void('0');">${pic3_index + 1}</a></li>
								</#list>
							</ul>		
						</li>
						</#if>
						
					
						</#list>
						
					   </#if>	
						
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
							<#list hotList3 as hot3>
							<li>
								<div class="pic_wrap">
									<a href="${pathUtil.getPathById('1', '${hot3[\'id\'][\'GId\']}')}">
										<img src="${pathUtil.getPathById('2', '${hot3[\'id\'][\'GId\']}')}N4/${hot3['GWebPath']}" alt="${hot3['GName']}" width="80" height="80">
									</a>
									<i class="dot_first"><span class="hide_clip">${hot3_index + 1}</span></i>
								</div>							
								<div class="txt_wrap">								
									<p class="price"><span>¥</span>${hot3['GShopPrice']}</p>								
									<p class="name"><a target="_blank" href="${pathUtil.getPathById('1', '${hot3[\'id\'][\'GId\']}')}">${hot3['GName']}</a></p>			
									<!-- 
									<p class="feedback">
										<span class="rank">
										<span class="rank_inner"></span></span>
										<a href="http://item.51buy.com/item-504259.html?cp-ptss=1302-1-504259-t#review_box" target="_blank">42</a>条
									</p> -->						
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
						<#list rcmdMap['2_3'] as rcmd23>
						<li>
						<a href="${rcmd23['linkUrl']}" target="_blank" ytag="20300">
						<img alt="${rcmd23['displayName']}" title="${rcmd23['displayName']}" width="200" height="89" src="${imgSvrHost}${rcmd23['imagePath']}"></a>
						</li>
						</#list>					
					</ul>
				</div>
				<!--/广告推荐-->
			</div>
			<!--/右-->			
		</div>
		<div class="clr mt20"></div>
 </div>
<!-- /楼层3-->

<!--楼层4-->
 <div class="foor f4 mt20">
    <div class="floor_hd">
	    <div class="floor_title">
		    <span>食品保健</span>
	    </div>
	    <ul class="cate_r">
		    <#list rcmdMap['1_4'] as rcmd4>
		    <li><a href="${rcmd4['linkUrl']}" title="${rcmd4['displayName']}" target="_blank">${rcmd4['displayName']}</a>
		    <#if rcmd4_has_next> | </#if></li>
		    </#list>
	    </ul>
    </div>
	<div class="floor_bd" id="tf_300003">
			<!--中-->
			<div class="goods_wrap">
				<div class="goods_list">
					<ul id="POS_1_300003">
					<#assign list_max4=8>
					<#if picMap['4'] ??>
						<#assign list_max4=5>
					</#if>
					
					<#if ids4 ??>
						<#list ids4 as id4>
						
						<#if (id4_index >= list_max4) >
							<#break>
						</#if>
						<li class="goods">						
							<div class="pic_wrap">							
								<a href="${pathUtil.getPathById('1', '${id4}')}" >
									<img src="${pathUtil.getPathById('2', '${id4}')}N3/${productMap['${id4}']['GWebPath']}" alt="" title="${productMap['${id4}']['GMarketContent']}"><i class=""></i></a>							
							</div>							
							<div class="title_wrap">
								<a href="${pathUtil.getPathById('1', '${id4}')}" target="_blank" >${productMap['${id4}']['GName']}
								<span class="sum_wrap">${productMap['${id4}']['GMarketContent']}</span></a></div>	
								<div class="price_wrap">
								<p class="price"><span>¥</span>${productMap['${id4}']['GShopPrice']}</p></div>	
						</li>
						<#if (list_max4 == 5 && id4_index == 0)>
						<li class="goods_ad">							
							<ul class="tab_con">
								<#list picMap['4'] as pic4>
								<li class="<#if pic4_index = 0>curr</#if>">						
									<a href="#" >
										<img src="${imgSvrHost}${pic4['picPath']}" width="582px" height="238px;"  alt="${pic4['picAlt']}">
									</a>
								</li>
								</#list>
							</ul>
							<ul class="tab_handle">
						       <#list picMap['4'] as pic4>
								  	<li class="<#if pic4_index == 0>curr</#if>"><a href="javascript:void('0');">${pic4_index + 1}</a></li>
								</#list>
							</ul>		
						</li>
						</#if>
						
					
						</#list>
						
					   </#if>
						
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
							<#list hotList4 as hot4>
							<li>
								<div class="pic_wrap">
									<a href="${pathUtil.getPathById('1', '${hot4[\'id\'][\'GId\']}')}">
										<img src="${pathUtil.getPathById('2', '${hot4[\'id\'][\'GId\']}')}N4/${hot4['GWebPath']}" alt="${hot4['GName']}" width="80" height="80">
									</a>
									<i class="dot_first"><span class="hide_clip">${hot4_index + 1}</span></i>
								</div>							
								<div class="txt_wrap">								
									<p class="price"><span>¥</span>${hot4['GShopPrice']}</p>								
									<p class="name"><a target="_blank" href="${pathUtil.getPathById('1', '${hot4[\'id\'][\'GId\']}')}">${hot4['GName']}</a></p>			
									<!-- 
									<p class="feedback">
										<span class="rank">
										<span class="rank_inner"></span></span>
										<a href="http://item.51buy.com/item-504259.html?cp-ptss=1302-1-504259-t#review_box" target="_blank">42</a>条
									</p> -->						
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
						<#list rcmdMap['2_4'] as rcmd24>
						<li>
						<a href="${rcmd24['linkUrl']}" target="_blank" ytag="20300">
						<img alt="${rcmd24['displayName']}" title="${rcmd24['displayName']}" width="200" height="89" src="${imgSvrHost}${rcmd24['imagePath']}"></a>
						</li>
					</#list>					
					</ul>
				</div>
				<!--/广告推荐-->
			</div>
			<!--/右-->			
		</div>
 </div>
<!-- /楼层4-->

</div>
<script type="text/javascript">

jQuery(document).ready(function(){
    
    lifeHelper();
    slideShow();   
    eachAd()
});

</script>

<script type="text/javascript" src="${WEB_ROOT}js/foot.js"></script>

</body>
</html>