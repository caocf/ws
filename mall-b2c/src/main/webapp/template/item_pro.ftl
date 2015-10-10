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
            <#assign item_properties_no=1>
            <#list item_properties?keys as param>
            <li id="item-properties${item_properties_no}">
            	<div class="dt">${param}：</div>
				<div class="dd">
				<#assign pros=item_properties[param]>
				
				
				<#if item_properties_no==1>
						<#assign pros1=item_properties[param]>
					</#if>
					<#if item_properties_no==2>
						<#assign pros2=item_properties[param]>
					</#if>
				
				
            	<#list pros as pro>
                <a href="javascript:;" class="item_lnk <#if item_properties_no==1>m<#else>s</#if>_lnk" id="<#if item_properties_no==1>m<#else>s</#if>_${pro}">${pro}</a>
                </#list>
	            </div>
	            <#assign item_properties_no=item_properties_no+1>
	            <div style="clear:both; height:0; line-height:0; font-size:0"></div>
            </li>
            </#list>
			<script type="text/javascript">
				var item_properties = new Array();
				var def_m,def_s; 
				function init_properties(){
					<#list item_properties_data?keys as param>
					item_properties.push("${param}");
					item_properties["${param}"] = {};
					<#assign pros=item_properties_data[param]>
					<#list pros as pro>
					<#list pro?keys as p>
					item_properties["${param}"]["${p}"]="${pro[p]}";
					<#if pro[p]==item_no>
					def_m = "${param}";
					def_s = "${p}";
					<#assign def_param=param>
					<#assign def_p=p>
					document.getElementById("m_${param}").className = document.getElementById("m_${param}").className+" curr"; 
					//alert(document.getElementById("#m_${param}").className);
					//$('#s_${p}').addClass('curr');
					document.getElementById("s_${p}").className = document.getElementById("s_${p}").className+" curr";
					</#if>
					</#list>
					</#list>
					</#list>
					
					<#list pros1 as pro1>
							<#list pros2 as pro2>
								if(item_properties["${pro1}"]["${pro2}"]==undefined){
									item_properties["${pro1}"]["${pro2}"]='0';
								}
		    				</#list>
	                	</#list>
					
					
					
					
					disab_properties();
				}
				function disab_properties(){
					return;
                    //隐藏主
                    for(var i in item_properties){
						if(item_properties[i][def_s] == '0'){
							$('#m_'+i).addClass('disab');
						}
                    }
                    //隐藏子
                    for(var i in item_properties[def_m]){
						if(item_properties[def_m][i] == '0'){
							$('#s_'+i).addClass('disab');
						}
					}
				}
				init_properties();
				$(".item_lnk").click(function(){
			        var thisid = $(this).attr("id");
					var pre = thisid.substring(0,2);
					var tmp = thisid.substring(2);
			        var shopId;
					if(pre=='m_'){
			            $('.m_lnk').removeClass('curr');
			            $(this).addClass('curr');
			            if(def_m==tmp){
			            	return;
			            }
			            def_m = tmp;
			            $(".s_lnk").removeClass('curr disab');
			            for(var i in item_properties[tmp]){
			                if(item_properties[tmp][i] == '0'){
			                    $('#s_'+i).addClass('disab');
			                }
			            }
			            shopId = item_properties[tmp][def_s];
			            if(shopId!='0'){
			                $('#s_'+def_s).addClass('curr');
			                location.href='/tools/item.chtml?saleId='+shopId;
			                return;
			            }else{
			            	for(var j in item_properties[tmp]){
			            		if(item_properties[tmp][j] != ""){
			            			$("#s_"+j).addClass("curr");
			            			$("#summary-choose-info").html("已选择：\"<b>"+tmp+"</b>\" , \"<b>"+j+"</b>\"");
			            			location.href='/tools/item.chtml?saleId='+item_properties[tmp][j];
			            			return;
			            		}
			            	}
			            }
					}else if(pre=='s_'){
			            shopId = item_properties[def_m][tmp];
						if(shopId=='0'){
			                return;//置灰
			            }else if(tmp==def_s){
			                return;//默认
			            }else{
			                $(".s_lnk").removeClass('curr');
			                $(this).addClass('curr');
			                def_s = tmp;
			                location.href='/tools/item.chtml?saleId='+shopId;
			                return;
			            }
					}
				});
			</script>
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
            <li class="hide" id="summary-choose-info">
			已选择："<b>${def_param}</b>" , "<b>${def_p}</b>"
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
