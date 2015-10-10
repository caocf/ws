(function($){
    $.extend( 
    {
        urlGet:function() 
        { 
            var aQuery = window.location.href.split("#");//取得Get参数 
            var aGET = new Array(); 
            if(aQuery.length > 1) 
            {
                var aBuf = aQuery[1].split("&"); 
                for(var i=0, iLoop = aBuf.length; i<iLoop; i++) 
                { 
                    var aTmp = aBuf[i].split("=");//分离key与Value 
                    aGET[aTmp[0]] = aTmp[1]; 
                } 
            } 
            return aGET; 
        }
    });
    
})(jQuery);


$(document).ready(function(){
	  $("#s_price_low, #s_price_high").bind("blur afterpaste", function(){
	    	checkMoney();
	   });
});

function isMoney(param) {
	if ((isNaN(param)) || (parseFloat(param) < 0) || (param.indexOf('.') == 0) || (param.lastIndexOf('.') == param.length - 1)) {
	   return false;
	}
	//不支持科学计数
	if(param.indexOf('e') > 0) {
	   return false;
	}
	//小数点后最多只允许保留两位有效数字
	var docIndex = $.trim(param).lastIndexOf('.');
	if ((docIndex < parseInt($.trim(param).length) - 3) && (docIndex >= 0)) {
	   return false;
	}
	if (param.charAt(0)=="0" && param !="0")
	{      
	   if (!(param.charAt(1)=="."))
	   {  
	     return false;
	   }      
	}
	return true;
}

function checkMoney(){
	var low = $("#s_price_low").val();
	var high = $("#s_price_high").val();
	if( (low && !isMoney(low)) || (high && !isMoney(high))  ){
		alert("金额格式输入有误(正确如：3.14)");
		return false;
	}
	if(low && high){
		if(parseFloat(high) < parseFloat(low)){
			alert("请填写正确的价格范围");
			return false;
		}
	}
	
	return true;
}

function redirect(url){
	window.location.href=url;
	if(typeof(s2_flag) != 'undefined' && s2_flag){
		window.location.reload();
	}
}

// 初始化分类数据
function initSorts(url, shopId){
	$.ajax({
		url:url, 
		data:"shopId="+shopId,
		type:"post",
		dataType:"text", 
		cache: false,
		beforeSend:function(XMLHttpRequest){ 
			$("#hjfl_id").after('<img id="sorts_loading" src="../img/loading.gif"  style="padding: 70px;">');
		},
		success:function(d){
		var html = getChildSort(d, '0', false);
			$("#sorts_loading").remove();
			if(html){
				$("#hjfl_id").after(html);
			}else{
				$("#hjfl_id").hide();
			}
		}
	});
}

// 初始化商品列表
function initFloors(floorClsName, url){
	$("."+floorClsName).each(function(i){
		var obj = $(this);
		var _floorid = $(this).attr("_floorid");
		var _goodNum = $(this).attr("_goodNum");
		var tmp = _floorid.split("_");
		var fid = tmp[1];
		$.ajax({
			url:url, 
			data:"floorId="+fid+"&goodNum="+_goodNum+"&storeId="+shopId,
			type:"post",
			dataType:"text", 
			cache: false,
			success:function(d){
				eval("var list=" + d);
				var html = '';
				html += '<div class="shop_tab_wrap">';
				html += '<div class="shop_content">';
				html += '   <div class="item_wrap">';
				for(var i in list){
					html += getProductHtml(list[i].img, list[i].marketPrice, list[i].price, list[i].redPrice, list[i].title, list[i].discountFlag, list[i].path, list[i].createTime,list[i].marketContent, list[i].source, list[i].iseckill);
				}
				html += '</div></div></div>';
				obj.after(html);
			}
		})
		
	});
}

function NagivatePage(curpage){
	initOnePage(curpage);
}

function goSearch(){
	if(!checkMoney()){
		return ;
	}
	var locStr = SHOP2_URL;
	locStr += "#name=" + encodeURIComponent($("#s_name").val());
	locStr += "&priceLow=" + encodeURIComponent($("#s_price_low").val());
	locStr += "&priceHigh=" + encodeURIComponent($("#s_price_high").val());
	
	window.location.href = locStr;
}

//初始化分页商品数据
function initOnePage( curpage ){
	if(!checkMoney()){
		return ;
	}
	$(".shop_tab_nav").each(function(i){
		var obj = $(this);
		var dataStr = "curpage=" + curpage;
		dataStr += "&storeId=" + shopId;
		dataStr += "&name=" + $("#s_name").val();
		dataStr += "&priceLow=" + $("#s_price_low").val();
		dataStr += "&priceHigh=" + $("#s_price_high").val();
		dataStr += "&shelfId=" + shelfId;
		dataStr += "&storeOrder=" + $("#storeOrder").val();
		$.ajax({
			url:onePageUrl, 
			data:dataStr,
			type:"POST",
			dataType:"json",
			cache: false,
			beforeSend:function(XMLHttpRequest){ 
				$(".item_wrap").html('<img src="../img/loading.gif"  style="padding: 100px 300px;">');
			},
			success:function(d){
				var pageStr = d.pageStr;
				var list = d.productList;
				var html = '';
				
				for(var i in list){
					html += getProductHtml(list[i].img, list[i].marketPrice, list[i].price, list[i].redPrice, list[i].title, list[i].discountFlag, list[i].path, list[i].createTime,list[i].marketContent, list[i].source, list[i].iseckill);
				}
				
				$(".item_wrap").html(html);
				$(".page_numbers").remove();
				$("#shop_main").append(d.pageStr);
			}
		})
		
	});
}



// 获取单个商品展示html代码
function getProductHtml(img, marketPrice, price, redPrice, title, discountFlag, path, createTime,marketContent, source, iseckill){
	var html = ''; 
	if(!price){
		price = '0';
	}
	if(!createTime){
		createTime = '0';
	}
	if(redPrice == null){
		redPrice = price;
	}
	var tmpCurrency = '￥';
	if(source == '4'){
		tmpCurrency = '积分';
		price = parseInt(price * 100);
	}
	
	
	html += '<div class="items" price="'+price+'" createTime="'+createTime+'">';
	if(iseckill == '9'){
		html += '<div class="goods-discount-tag" style="background: url(../img/discount-tag3.png) no-repeat;height: 80px; position: absolute; width: 80px;z-index: 101;"></div>';
	}
	html +=' <a class="items_img" target="_blank" href="'+path+'"><img width="160" height="160" src="'+img+'" alt="" /></a>';
	//不是优惠券
	if(source != '5'){
		html += '  <div class="price"><span class="old_price">'+marketPrice+'</span> <span class="now_price">'+ tmpCurrency +'<strong>'+price+'</strong></span> </div>';
		if(source != '4'){//积分类型不显示
			html += '   <div class="vip_price"><span class="vip_price_tips">会员最低价</span> <span class="vip_now_price">￥<strong>'+redPrice+'</strong></span> </div>';
		}
	}
	
	html += '  <div class="procuts_title"><a target="_blank" href="'+path+'">'+title+'</a></div>';
	if(marketContent && $.trim(marketContent) != ''){
		html += '  <div class="procuts_text"><span>'+marketContent+'</span></div>';
	}
	html += '</div>';
	return html;
}

function sortProductList(){
	initOnePage("1");
	return ;
	if(val == ''){
		return;
	}
	var tmpArr = val.split('_');
	var field = tmpArr[0];
	var order = tmpArr[1];
	var div;
	if(order == 'asc'){
	   div = $('.items').toArray().sort(function(a,b){
          return parseInt($(a).attr(field)) - parseInt($(b).attr(field))
	  });
	}else{
	   div = $('.items').toArray().sort(function(a,b){
	          return parseInt($(b).attr(field)) - parseInt($(a).attr(field))
		});
	}
	//$('.items').remove;
	$(".item_wrap").html(div);
}

function getSortPic(json_str, sortId){
	eval("var obj ="+ json_str +";");
	for(var i in obj.imageMap){
		if(i == sortId){
			return $.trim(obj.imageMap[i]);
		}
	}
	return '';
}

// 获取子分类
function getChildSort(json_str, pcode, sec_flag){
	eval("var obj ="+ json_str +";");
	
	var html = '';
	var child_html = '';
	if(sec_flag){
		html = '<div class="ul_slide_content"><ul>';
	}else if(pcode != 0){
		html = '<div class="ul_cls"><ul>';
	}
	
	var tmp;
	var flag = false;
	var sortPic ; 
	var tmpHref = '';
	var tmpHref2 = '<a class="slide_img" href="javascript:void(0);"></a>';
	for(var i in obj.allSort){
		if(obj.allSort[i] == pcode){
			flag = true;
			tmp = i.split("_");
			tmpHref = 'href="javascript:redirect(\''+SHOP2_URL+'#shelfId='+tmp[1]+'\')"';
			if(pcode == '0'){
				sortPic = getSortPic(json_str, tmp[1]);
				child_html = getChildSort(json_str, tmp[1], false);
				
				var slide_cls = "slide_u";
				if(!child_html){
					slide_cls = "slide_d";
				}
				
				if(sortPic){
					html += '<div class="side_slide"><div class="slide-title" child_flag="'+slide_cls+'"><h3 class="'+slide_cls+'">'+tmpHref2+'<a class="slide_title" ' + tmpHref + '><img width="210" height="60" src="'+sortPic+'" /></a></h3></div>';
				}else{
					html += '<div class="side_slide"><div class="slide-title" child_flag="'+slide_cls+'"><h3 class="'+slide_cls+'">'+tmpHref2+'<a class="slide_title" ' + tmpHref + '>'+tmp[2]+'</a></h3></div>';
				}
				html += child_html;
			}else{
				child_html = getChildSort(json_str, tmp[1], true);
				if(child_html){
					html += '<li class="slide_li_c"><div class="slide_down">'+tmpHref2+'<a class="slide_down_title" '+tmpHref+'>'+tmp[2]+'</a></div>'+child_html+'</li>';
				}else{
					html += '<li class="slide_li"><a onclick="return true;" '+tmpHref+'>'+tmp[2]+'</a></li>';
				}
			}
			if(pcode== '0'){
				html += '</div>';
			}
		}
	}
	
	if(flag == false){
		return '';
	}
	
	if(pcode != '0'){
		html += '</ul></div>';
	}
	return html;
}

$(function(){
	$(".slide-title[child_flag=slide_u] h3.slide_d .slide_img").live("click", function(){
		$(this).parent("h3").removeClass("slide_d").addClass("slide_u").parent().parent().find(".ul_cls:first").hide();
		return false;
	});
	
	$(".slide-title[child_flag=slide_u] h3.slide_u .slide_img").live("click", function(){
		$(this).parent("h3").removeClass("slide_u").addClass("slide_d").parent().parent().find(".ul_cls:first").show();
		return false;
	});


	$(".slide_li_c .slide_down .slide_img").live("click", function(){
		$(this).parent("div").removeClass("slide_down").addClass("slide_up").next("").hide();
		return false;
	});
	
	$(".slide_li_c .slide_up .slide_img").live("click", function(){
		$(this).parent("div").removeClass("slide_up").addClass("slide_down").next().show();
		return false;
	});
	
});

function mark(){
    $.get(WEB_ROOT+"item/favStore.chtml?shopId="+shopId+"&ramdom="+Math.random(),function(data){
            if(data.flag == '1'){
                $("#favoritetips").html("收藏成功！");                
            }else if(data.flag == '2'){
            	$("#favoritetips").html("您已收藏过该商户！");       
            }else if(data.flag == '3'){
            	$("#favoritetips").html("请先登录再收藏！");       
            }else{
            	$("#favoritetips").html("收藏失败，请稍候再试！");       
            }
            $('.alert_windows').show();
        }
    );
}


//初始化商品列表
function initFeixinList(shopId, shopClass){
	$.ajax({
		url:WEB_ROOT+"shop/getfexinlist.chtml", 
		data:"shopId="+shopId+"&shopClass="+shopClass,
		type:"post",
		dataType:"json", 
		cache: false,
		success:function(r){
			var html = '<div class="side_slide mb10">';
			html += '<div class="related-title">';
			html += '    <h3><a class="slide_up">飞信客服</a></h3>';
			html += '</div>';
			html += '<div class="related-content ">';
			html += '	<ul>';
			var hasFlag = false;
			for(var i in r.data){
				hasFlag = true;
				html += '<li class="mb5">'+r.data[i].nickName+'：'+r.data[i].code+'</li>';
			}
			html += '</ul></div></div>';
			if(hasFlag == true){
				$("#sidebar_shop").after(html);
			}
		}
	})
}