<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>弹出层div</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.g_window_div {
	    display: none;    
	}
	#g_back_overlay{ 
    position:fixed; 
    top: 0%; 
    left: 0%; 
    width: 100%; 
    height: 100%; 
    background-color: #000; 
    z-index:999;
    text-align: center;
    	 -moz-opacity: 0.8; 
    opacity:.80; 
    filter:alpha(opacity=80);
	}
	#g_black_overlay{ 
    position:fixed; 
    top: 0%; 
    left: 0%; 
    width: 100%; 
    height: 100%; 
    z-index:1000;
    text-align: center;

	}
	
#g_div_msg {
position:relative;
	width: 640px;
	height: 280px;
	display: inline-block;
	margin-top: 14%;
	background-color: #fff;
	padding: 10px;
	border-radius:8px;
	-webkit-border-radius:8px;
	-o-border-radius:8px;
	-moz-border-radius:8px;
    border: 10px solid rgb(245, 50, 50);
}

#g_div_msg #g_div_close_a {
display: block;
height: 41px;
width: 41px;
overflow: hidden;
background-image: url(../images/close_product.jpg);
background-position: 0 0;
cursor: pointer;
text-indent:-9999px;
position: absolute;
top: 2px;
right: 2px;
z-index: 1;


}
 
</style>
<script type="text/javascript">

(function($){
	$(function(){
	$("#g_div_close_a").hover(
		  function () {
			    $(this).css({'background-position':'2 2 '});
			  },
			  function () {
				  $(this).css({'background-position':'0 0'});
			  }
			);

$('#g_div_close_a').click(function(){
	$("#g_black_overlay").hide();
	$("#g_back_overlay").hide();
	return false;
});
$('#g_div_msg,#g_back_overlay').click(function(event){
	event.stopPropagation();
	return false;
});
var height;
$(window).scroll( function() {
	if($.browser.msie && $.browser.version=="6.0")$("#g_black_overlay,#g_back_overlay").css("top",$(document).scrollTop());
} );

});
})(jQuery);

</script>

  </head>
  
  <body>
 <div id="g_back_overlay" class="g_window_div" ></div>
   	<div id="g_black_overlay" class="g_window_div">
   		<div id="g_div_msg">
	   		<a id="g_div_close_a" href="javascript:void(0);" title="关闭">关闭</a>
   			<div class="g_div_msg_content" >
   		 
   		 		<div class="_window_content">
			<div class="_window_img"><img alt="" src=""></div>
			<div class="_window_txt">
				<p class="_window_txt_title"  ></p>
				<div class="_window_intro">
					<span class="_window_chose_type _window_chose_total"></span>				
					<span class="_window_chose_color _window_chose_total"></span>				
					<span  class="_window_chose_size _window_chose_total"></span>	
					<span id='_window_info_kucun'></span>			
				</div>
				<div class="_window_chose">
					<div class="content-nav-left">尺码：</div>
					<div class="content-nav-right">
					<ul class="choose_state" id="_window_size_ul"  >
					</ul>
					</div>
					<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
				</div>
				<div class="_window_chose">
					<div class="content-nav-left">颜色：</div>
				<div class="content-nav-right">
				<ul class="choose_state" id="_window_color_ul">
				</ul>
				 </div>
					<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
				</div>
				<div>
					<form action="careteOrder" method="post" id="_window_form" >
						<input name='stockId' id='orderStockId' type="hidden"   >
						<input name='itemType' id='orderItemType' type="hidden" value="${itemType }"  >
						<input name='itemId' id='orderItemId' type="hidden"   >
						<input class="_window_input" type="hidden"   name="itemColor" id='orderItemColor' >					
						<input class="_window_input" type="hidden"  name="itemSize" id='orderItemSize' >					
					</form>
				</div>
				<div class="_window_money" ></div>
				 <a class="btn _window_buy" id='_window_buy_btn' href='javascript:void(0)' onclick="$this.submitBy()" >支付订单</a>
			<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
			</div>
				<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
			</div>
   		 
   			</div>
   		</div>
   	</div>
   	</div>
  </body>
</html>
