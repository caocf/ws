<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>新春特惠集市马上“七五”折</title>
<link href="../css/reset3.css" rel="stylesheet" type="text/css" />
	<script src="../js/jquery.min.js"></script>
	<script src="../js/red-package.js"></script>
	<script type="text/javascript" src="../js/jquery.cookie.js"></script>
	<script type="text/javascript" src="../js/jquery.jqzoom.js"></script>
<script language="javascript" type="text/javascript">
var active ={"imgUrl":"/data/lotteryimg/","activeId":141,"confs":[{"activeId":141,"id":141,"key":"prizeNum","value":"8"}],"prizes":[{"position":"1","hitLevel":1,"activeId":141,"name":"Iphone 5S ","hitMsg":"人品大爆发！您抽中了Iphone 5S一台"},{"position":"2","hitLevel":2,"activeId":141,"name":"羽博YB631移动电源","hitMsg":"哇！运气太好了！您抽中了羽博YB631移动电源一只 "},{"position":"3","hitLevel":3,"activeId":141,"name":"2个商城币","hitMsg":"哇！运气太好了！您抢到了2个商城币 "}]};
$(function(){ 	
	$("#myunion").hover(function() {
        $(".union-box").show();
        $("#myunionhandle").addClass("myunionhandle-active")
    },function() {
        $(".union-box").hide();
        $("#myunionhandle").removeClass("myunionhandle-active")
    });

	var $body_height = $(document).height();
	var $scroll_height = $(window).scrollTop(); 
	$("shadow").height($body_height);
	 
	//$("#mqj_top").hover(function(){
	//		$("#mqj_top").fadeTo("fast",1);	
	//	},function(){
	//		$("#mqj_top").fadeTo("fast",0.5);
	//});
	//$("#mqj_note").hover(function(){
	//		$("#mqj_note").fadeTo("fast",1);	
	//	},function(){
	//		$("#mqj_note").fadeTo("fast",0.5);
	//});

	$(window).scroll(function(){
		var $scroll_height = $(window).scrollTop(); 
		$("#mqj_note").css({top:$scroll_height+200+"px"});
	})
	$(window).scroll(function(){
		var $scroll_height2 = $(window).scrollTop(); 
		$("#mqj_top").css({top:$scroll_height2+200+"px"});
	})
	/* $("#choujiang a").click(function(){
		$("#alert").show();	
		return false;
	})
	$("#alert").click(function(){
		$("#alert").hide();	
	})	 */
	
	
	
	
	
})
</script>
<style>
body {
	background: repeat #f5beb9 30px 0;
	text-align:center;
	font-family: Microsoft YaHei, Simsum, serif;
}
#bg {
	background:url(../img/choujiang/abg.jpg) no-repeat center top;
}
.wrapper {
	width:1000px;
	margin:0 auto;
	text-align:left;
}
#banner {
	width:1000px;
	height:450px;
	background:url(../img/choujiang/abanner.jpg) no-repeat top center;
	overflow:hidden;
	position:relative;
}
#banner a{
 	width:1000px;
	height:600px;
	display:block;
	cursor:pointer;
}
#choujiang{ background:url(../img/choujiang/choujiang.jpg) top center no-repeat; width:1000px;
	height:350px; position:relative; }
	#choujiang a{ position:absolute; left:569px; top:148px; width:96px; height:60px; cursor:pointer; display:block; }
	#choujiang #mingdan{ width:240px; height:160px; overflow:hidden; position:absolute; right:29px; top:70px;}
	#choujiang #mingdan strong,#alert strong{ color:#F30;}	
		#alert{ background:url(../img/choujiang/bg_choujiang.png); padding:30px 30px 85px; width:295px; height:50px; line-height:25px; text-align:center; font-size:14px; display:none; cursor:pointer; top:100px; left:300px; position:absolute; z-index:999; }
	.bar{ background:url(../img/choujiang/bar.jpg) no-repeat #f5beb9 center  top;  height:95px; }
#info {
	width:1000px;
	position:relative;
}

.content{

}
.content:after {
	visibility: hidden;
	display: block;
	font-size: 0;
	content: " ";
	clear: both;
	height: 0;
}

.content li a {
	display:block;
	text-align:center;
}
.content li .ad {
	position:absolute;
	background:#F30;
	color:#fff;
	top:0;
	left:10px;
	padding:5px 10px;
}
.content li p {
	text-align:left;
	padding:0 10px;
	font-size:12px;
	margin-top:5px;
	line-height:18px;
	overflow:hidden;
	height:36px;
	display:block;
}
.content li p a {
	color:#fff;
}
.old {
	font-size:12px;
	display:block;
	line-height:16px;
	text-align:right;
	color:#FF9;
	padding:0 10px;
}
.old em {
	text-decoration:line-through;
}
.now {
	font-size:16px;
	display:block;
	line-height:24px;
	text-align:right;
	color:#F33;
	font-weight:700;
	padding:0 10px;
}
.btn_buy {
	display:block;
	height:30px;
	width:220px;
	cursor:pointer;
	position:absolute;
	bottom:25px;
}
/* footer */
		#footer-warp {
	background: url("http://www.12580777.com/img/home_bg.png") repeat-x scroll left -790px #fff;
}
.footer {
	clear: both;
	margin: auto;
	padding-bottom: 20px;
	padding-top: 15px;
	position: relative;
	width: 1000px;
}
.f-logo {
	float: left;
	margin-right: 80px;
}
.help-box {
	float: left;
	margin-right: 40px;
	padding-bottom: 20px;
	width: 140px;
}
.help-box li {
	line-height: 22px;
	padding-left: 5px;
}
.help-box h5 {
	border-bottom: 1px solid #E6E6E6;
	color: #666666;
	font-size: 14px;
	padding-left: 5px;
}
.phone {
	background: url("http://www.12580777.com/img/home_bg.png") no-repeat scroll left -715px transparent;
	height: 60px;
	position: absolute;
	right: 30px;
	top: 15px;
	width: 80px;
}
.city-nav {
	border-bottom: 1px solid #E6E6E6;
	border-top: 1px solid #E6E6E6;
	clear: both;
	padding: 4px 0;
	text-align: center;
}
.city-nav strong {
	color: #777777;
	display: inline;
}
.city-nav li, .city-nav ul {
	display: inline;
}
.city-nav li {
	padding: 0 8px;
}
.footer-nav {
	padding-top: 5px;
}
.footer-nav a {
	padding: 0 2px;
}
.more a:link {
	color: #999999;
}
.mt10 {
	margin-top: 10px;
}
.copyright {
	color: #777777;
}
.r2-box .r-piclist li {
	border: 1px solid #DDDDDD;
	height: 90px;
	margin-bottom: 5px;
}
.r2-box .r-piclist li img {
	height: 90px;
	width: 208px;
}
.addnew {
	background: url("http://www.12580777.com/img/slide-bar-new.gif") no-repeat scroll right -5px transparent;
	color: red;
	display: block;
}
.addnew a:link, .addnew a:visited {
	color: red;
}

#mqj_top {
	/***filter:alpha(opacity=50);***/ /*IE滤镜，透明度50%*/
	/***-moz-opacity:0.5;***/ /*Firefox私有，透明度50%*/
	/***opacity:0.5;***//*其他，透明度50%*/
	position: absolute;
	right: 0;
	top: 250px;
	width: 120px;
	z-index: 100;;
	background:#7c250a;
}
#mqj_note {
	/***filter:alpha(opacity=50);***/ /*IE滤镜，透明度50%*/
	/***-moz-opacity:0.5;***/ /*Firefox私有，透明度50%*/
	/***opacity:0.5;***//*其他，透明度50%*/	
	position: absolute;
	left: 0;
	top: 250px;
	width: 120px;
	z-index: 100;
}
#mqj_top a, #mqj_note a {
	display:block;
}
.wrapper img{ display:block;}
.content { padding:0 4px;}
.content a{ float:left; padding:4px;}
.content a.big{ padding:4px 5px;}
.content a:hover{transform:scale(1.02,1.02);	
	-webkit-transform:scale(1.02,1.02);
	-moz-transform:scale(1.02,1.02);
	-o-transform:scale(1.02,1.02);
	-ms-transform:scale(1.02,1.02);}
	#c4{ background:url(../img/choujiang/ac4.jpg) top left no-repeat; width:1000px; height:486px; position:relative;}
	#c4 #pro_product{ position:absolute; height:308px; width:500px; top:52px; left:64px; display:block;}
	#c4 #gg{ display:block; width:345px;height:374px; left:613px; top:15px; position:absolute;}
	#c4 h6{ position:absolute; top:432px; left:230px; font-size:18px; color:#FFF; line-height:18px;}

</style>
<script type="text/javascript">


$(function(){
	var length = $(".choujiangCount").length;
	
	if(length >= 7 ){
		addEventSimple(window,'load',initScrolling);
	}
	
})


function addEventSimple(obj,evt,fn){
	if(obj.addEventListener){
		obj.addEventListener(evt,fn,false);
	}else if(obj.attachEvent){
		obj.attachEvent('on'+evt,fn);
	}
}



var scrollingBox;
var scrollingInterval;
var reachedBottom=false;
var bottom;

function initScrolling(){
	scrollingBox = document.getElementById('mingdan');
	scrollingBox.style.overflow = "hidden";
	scrollingInterval = setInterval("scrolling()",50);
	scrollingBox.onmouseover = over;
	scrollingBox.onmouseout = out; 
}

function scrolling(){
	var origin = scrollingBox.scrollTop++;
	if(origin == scrollingBox.scrollTop){
		if(!reachedBottom){
			scrollingBox.innerHTML+=scrollingBox.innerHTML;
			reachedBottom=true;
			bottom=origin;
		}else{
			scrollingBox.scrollTop=bottom;
		}
	}
}

function over(){
	clearInterval(scrollingInterval);
}
function out(){
	scrollingInterval = setInterval("scrolling()",50);
}
</script>
</head>
<body id=" ">
<script type="text/javascript" src="../js/head.js"></script>
<div id="mqj_note" style="background:#722a92;"><a ><img src="../img/choujiang/aleft.jpg" /></a> </div>
<div id="mqj_top" style="background:#722a92;">
<a href="# "><img src="../img/choujiang/aright1.jpg" /></a>
<a href="#banner"><img src="../img/choujiang/aright2.jpg" /></a>
<a href="#bar1"><img src="../img/choujiang/aright3.jpg" /></a>
<a href="#bar2"><img src="../img/choujiang/aright4.jpg" /></a>
<a href="#bar3"><img src="../img/choujiang/aright5.jpg" /></a>
<a href="#"><img src="../img/choujiang/aright6.jpg" /></a>
 </div>
<div id="bg">
    <div class="wrapper">
        <div id="banner">

        </div>
        <div id="choujiang">
        <input type="hidden" id="actId" value="${param.actId}"/>
        	<div id="alert">
            </div>
        	<a id="win"></a>
            <div id="mingdan">
            <c:forEach items="${messages}" var="item">
				<p class="choujiangCount">${item.target_id} 获得 <strong>${item.name}</strong></p>
                </c:forEach>
            </div>
        </div>
     </div>
     <div class="bar">
     	<div class="wrapper">
        	<img src="../img/choujiang/bar1.jpg" id="bar1" />
        </div>
     </div>   
     <div class="wrapper">
        <div class="content">
            <a target="_blank" title="朗科U盘16g 高速稳定 马年纪念版创意生肖" href="http://mall.12580life.com/item/i_2_522_253.htm "><img src="../img/choujiang/ai1.jpg" alt="朗科U盘16g 高速稳定 马年纪念版创意生肖" /></a>
            <a target="_blank" title="东芝隼系列 THUHYBS-016盘 16G" href="http://mall.12580life.com/item/i_0_1140_1841.htm"><img src="../img/choujiang/ai2.jpg" alt="东芝隼系列 THUHYBS-016盘 16G" /></a>
            <a target="_blank" title="艾德加 迷你移动电源 5200毫安月光宝盒" href="http://mall.12580life.com/item/i_1_1576_1075.htm"><img src="../img/choujiang/ai3.jpg" alt="艾德加 迷你移动电源 5200毫安月光宝盒" /></a>
            <a target="_blank" title="专柜正品原装 瑞士军刀0.6123(红)" href="http://mall.12580life.com/item/i_0_100_717.htm"><img src="../img/choujiang/ai4.jpg" alt="专柜正品原装 瑞士军刀0.6123(红)" /></a>
            <a target="_blank" title="清风超质感纸手帕 3层5提/50包装" href="http://mall.12580life.com/item/i_1_201_1885.htm"><img src="../img/choujiang/ai5.jpg" alt="清风超质感纸手帕 3层5提/50包装" /></a>
            <a target="_blank" title="清风卷筒纸 3层270段 2提装" href="http://mall.12580life.com/item/i_1_1507_1143.htm"><img src="../img/choujiang/ai6.jpg" alt="清风卷筒纸 3层270段 2提装" /></a>
            <a target="_blank" title="史努比 粉彩淑女真空水瓶" href="http://mall.12580life.com/item/i_0_105_1959.htm"><img src="../img/choujiang/ai7.jpg" alt="史努比 粉彩淑女真空水瓶" /></a>
            <a target="_blank" title="泰福高 耐热玻璃保鲜盒3件套" href="http://mall.12580life.com/item/i_0_104_1879.htm"><img src="../img/choujiang/ai8.jpg" alt="泰福高 耐热玻璃保鲜盒3件套" /></a>
            <a target="_blank" title="新闻香堂 不锈钢厨房七件套" href="http://mall.12580life.com/item/i_1_239_432.htm"><img src="../img/choujiang/ai9.jpg" alt="新闻香堂 不锈钢厨房七件套" /></a>
            <a target="_blank" title="瑞鹊 换季棉被收纳袋 " href="http://mall.12580life.com/item/i_0_117_264.htm"><img src="../img/choujiang/ai10.jpg" alt="瑞鹊 换季棉被收纳袋 " /></a> 
            <a target="_blank" title="瑞鹊 无纺布收纳2件套" href="http://mall.12580life.com/item/i_0_1394_882.htm"><img src="../img/choujiang/ai11.jpg" alt="瑞鹊 无纺布收纳2件套" /></a>
            <a target="_blank" title="慢回弹记忆绵 垫椅垫腰靠两用" href="http://mall.12580life.com/item/i_0_1579_292.htm

"><img src="../img/choujiang/ai12.jpg" alt="慢回弹记忆绵 垫椅垫腰靠两用" /></a>
            <a target="_blank" title="U型慢回弹记忆绵 美臀坐垫" href="http://mall.12580life.com/item/i_1_543_379.htm"><img src="../img/choujiang/ai13.jpg" alt="U型慢回弹记忆绵 美臀坐垫" /></a>
            <a target="_blank" title="凹形腰靠 U型枕记忆枕 办公室组合装" href="http://mall.12580life.com/item/i_0_117_686.htm"><img src="../img/choujiang/ai14.jpg" alt="凹形腰靠 U型枕记忆枕 办公室组合装" /></a>
            <a target="_blank" title="堂皇 双人棉被芯" href="http://mall.12580life.com/item/i_0_109_876.htm"><img src="../img/choujiang/ai15.jpg" alt="堂皇 双人棉被芯" /></a>
            <a target="_blank" title="凯诗风尚 全棉活性高支高密四件套" href="http://mall.12580life.com/item/i_0_107_685.htm

"><img src="../img/choujiang/ai16.jpg" alt="禾洱澌 生态百花蜂蜜450g" /></a>
            <a target="_blank" title="禾洱澌 生态百花蜂蜜450g" href="http://mall.12580life.com/item/i_0_112_1408.htm"><img src="../img/choujiang/ai17.jpg" alt="禾洱澌 生态百花蜂蜜450g" /></a>
            <a target="_blank" title="禾洱澌 生态益母草蜂蜜 450g" href="http://mall.12580life.com/item/i_0_111_854.htm"><img src="../img/choujiang/ai18.jpg" alt="禾洱澌 生态益母草蜂蜜 450g" /></a>
            <a target="_blank" title="三清山野生山葛粉375g 清热解脂" href="http://mall.12580life.com/item/i_0_105_747.htm"><img src="../img/choujiang/ai19.jpg" alt="三清山野生山葛粉375g 清热解脂" /></a>
            <a target="_blank" title="福建莆田特级5A大桂圆干  300g" href="http://mall.12580life.com/item/i_0_1179_1231.htm"><img src="../img/choujiang/ai20.jpg" alt="福建莆田特级5A大桂圆干  300g" /></a>
            <a target="_blank" title="威克多 2014最新签名款 专业拍包" href="http://mall.12580life.com/item/i_1_390_261.htm"><img src="../img/choujiang/ai21.jpg" alt="威克多 2014最新签名款 专业拍包" /></a>
            <a target="_blank" title="威克多 三号羽毛球12只 专业比赛级" href="http://mall.12580life.com/item/i_0_1263_780.htm"><img src="../img/choujiang/ai22.jpg" alt="威克多 三号羽毛球12只 专业比赛级" /></a>
            <a target="_blank" title="攀能 多功能洗漱包 户外出差" href="http://mall.12580life.com/item/i_0_115_250.htm"><img src="../img/choujiang/ai23.jpg" alt="攀能 多功能洗漱包 户外出差" /></a>
            <a target="_blank" title="攀能 绿野仙踪双人春游野餐包" href="http://mall.12580life.com/item/i_0_112_708.htm"><img src="../img/choujiang/ai24.jpg" alt="攀能 绿野仙踪双人春游野餐包" /></a>
            <a target="_blank" title="温碧泉 丝薄水嫩紧致面膜贴6送1" href="http://mall.12580life.com/item/i_0_111_103.htm"><img src="../img/choujiang/ai25.jpg" alt="温碧泉 丝薄水嫩紧致面膜贴6送1" /></a>
            <a target="_blank" title="德国司顿 安德诺卡炫彩靓丽指甲套装" href="http://mall.12580life.com//item/i_1_1959_569.htm"><img src="../img/choujiang/ai26.jpg" alt="德国司顿 安德诺卡炫彩靓丽指甲套装" /></a>
            <a target="_blank" title="滴露中国沐浴露 薄荷冰爽1KG" href="http://mall.12580life.com/item/i_0_1225_530.htm"><img src="../img/choujiang/ai27.jpg" alt="滴露中国沐浴露 薄荷冰爽1KG" /></a>
            <a target="_blank" title="依蔻丽 天然有机润唇膏 食用级" href="http://mall.12580life.com/item/i_0_107_66.htm"><img src="../img/choujiang/ai28.jpg" alt="依蔻丽 天然有机润唇膏 食用级" /></a>
            <a target="_blank" title="迪士尼 纹镂空米奇白面镶钻女表" href="http://mall.12580life.com/item/i_1_365_920.htm"><img src="../img/choujiang/ai29.jpg" alt="迪士尼 纹镂空米奇白面镶钻女表" /></a>
            <a target="_blank" title="毕加索 罗马情缘纯黑金铱金笔礼盒" href="http://mall.12580life.com/item/i_0_107_1890.htm"><img src="../img/choujiang/ai30.jpg" alt="毕加索 罗马情缘纯黑金铱金笔礼盒" /></a>
            <a target="_blank" title="ZIPPO经典复刻版纯铜1941B" href="http://mall.12580life.com/item/i_0_1128_479.htm"><img src="../img/choujiang/ai31.jpg" alt="ZIPPO经典复刻版纯铜1941B" /></a>
            <a target="_blank" title="梵道夫 牛皮真皮自动扣皮带" href="http://mall.12580life.com/item/i_1_275_329.htm"><img src="../img/choujiang/ai32.jpg" alt="梵道夫 牛皮真皮自动扣皮带" /></a>                 
        </div>
    </div>
    <div class="bar">
     	<div class="wrapper">
        	<a href="http://mall.12580life.com/febYearActivity/list.chtml" target="_blank"><img src="../img/choujiang/bar2.jpg" id="bar2" /></a>
        </div>
	</div> 
	
    <div class="wrapper">
    	<div class="content">
	 <a target="_blank" title="仅售90元，或6000积分/M值，或90个商城币，即可享原价200元的奥美康脑轻松按摩枕一个" href="http://tuan.12580life.com/team.php?id=23229"><img src="../img/choujiang/t1.jpg" alt="仅售90元，或6000积分/M值，或90个商城币，即可享原价200元的奥美康脑轻松按摩枕一个" /></a>
            <a target="_blank" title="仅售30元，或2000积分/M值，或30个商城币，即可享原价70元的哥伦比亚厨房刀具三件套" href="http://tuan.12580life.com/team.php?id=23255"><img src="../img/choujiang/t2.jpg" alt="仅售30元，或2000积分/M值，或30个商城币，即可享原价70元的哥伦比亚厨房刀具三件套" /></a>
            <a target="_blank" title="仅售55元，或3666积分/M值，或55个商城币，即可享原价109元的奔腾1201w电吹风一个" href="http://tuan.12580life.com/team.php?id=23242"><img src="../img/choujiang/t3.jpg" alt="仅售55元，或3666积分/M值，或55个商城币，即可享原价109元的奔腾1201w电吹风一个" /></a>
            <a target="_blank" title="仅售2元，原价7元的七号花园蛋糕坊经典奶茶一杯！天然茶饮，兼顾健康与口感，丝滑香醇，健康美味升级，纯手工制作，享受甜蜜的舌尖诱惑！" href="http://tuan.12580life.com/team.php?id=23267"><img src="../img/choujiang/t4.jpg" alt="仅售2元，原价7元的七号花园蛋糕坊经典奶茶一杯！天然茶饮，兼顾健康与口感，丝滑香醇，健康美味升级，纯手工制作，享受甜蜜的舌尖诱惑！" /></a>                 
         </div>
    </div>	
	
   <div class="bar">
     	<div class="wrapper">
        	<img src="../img/choujiang/bar3.jpg" id="bar3" />
        </div>
    </div>
    <div class="wrapper">
    	<div class="content">
        <a target="_blank" title="苏州电费卡200元" href="http://mall.12580life.com//item/i_0_200_215.htm"><img src="../img/choujiang/ai33.jpg" alt="苏州电费卡200元" /></a>
        <a target="_blank" title="苏州家乐福50元电子购物卡" href="http://quan.12580life.com/product/i2434.htm"><img src="../img/choujiang/ai34.jpg" alt="苏州家乐福50元电子购物卡 " /></a>
        <a target="_blank" title="苏州家乐福100元电子购物卡" href="http://quan.12580life.com/product/i2436.htm"><img src="../img/choujiang/ai35.jpg" alt="苏州家乐福100元电子购物卡" /></a>
        <a target="_blank" title="苏州家乐福100元电子购物卡" href="http://quan.12580life.com/product/i2432.htm"><img src="../img/choujiang/ai36.jpg" alt="苏州家乐福100元电子购物卡" /></a>
        </div>
    </div>
    <div class="wrapper">
    	<img src="../img/choujiang/gz.jpg" />
    </div>
    
</div>

<div style="height: 0; overflow: hidden;"> 
    <script type="text/javascript">var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-1274668-5']);
_gaq.push(['_setDomainName', '.12580777.com']);
_gaq.push(['_setAllowHash',false]);
_gaq.push(['_addOrganic','soso','w']);
_gaq.push(['_addOrganic','sogou','query']);
_gaq.push(['_addOrganic', 'yodao', 'q']);
_gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script> 
<script src="http://s17.cnzz.com/stat.php?id=3864417&web_id=3864417" language="JavaScript"></script> 
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F29fe495f94b887393db71f5f7709f811' type='text/javascript'%3E%3C/script%3E"));
</script> 
</div>
<script src="http://mall.12580life.com/js/foot.js" type="text/javascript"></script>
</body>
</html>
