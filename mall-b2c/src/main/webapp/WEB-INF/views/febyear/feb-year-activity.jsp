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
<title>新春特惠集市马上有惊喜</title>
<link href="../css/reset3.css" rel="stylesheet" type="text/css" />
<link href="../css/base.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript">
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
	$("#choujiang a").click(function(){
		$("#alert").show();	
		return false;
	})
	$("#alert").click(function(){
		$("#alert").hide();	
	})	
	
})
function goNext(page){
	window.location.href = '${ctx}/febYearActivity/list.chtml?page='+page;
}
</script>
<style>
body {
	background: repeat #a41d23 30px 0;
	text-align:center;
	font-family: Microsoft YaHei, Simsum, serif;
}
#bg {
	background:url(../img/febgroupbuy/bg.jpg) #a41d23 no-repeat center top;
}
.wrapper {
	width:1000px;
	margin:0 auto;
	text-align:left;
}
#banner {
	width:1000px;
	height:450px;
	background:url(../img/febgroupbuy/banner.jpg) no-repeat top center;
	overflow:hidden;
	position:relative;
}
#banner a{
 	width:1000px;
	height:600px;
	display:block;
	cursor:pointer;
}

	.bar{ }
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
.content { padding:0 25px; background:url(../img/febgroupbuy/content.jpg) repeat-y ;}
.content a{ float:left; margin:6px; padding:12px 7px 7px 8px ; position:relative; display:block; background:url(../img/febgroupbuy/product.jpg) top center no-repeat; width:210px; height:250px;}
.content a:hover{transform:scale(1.02,1.02);	
	-webkit-transform:scale(1.02,1.02);
	-moz-transform:scale(1.02,1.02);
	-o-transform:scale(1.02,1.02);
	-ms-transform:scale(1.02,1.02); 
	text-decoration:none;
	color:#F60;
	}
.content a i{ display:block; position:absolute; background:url(../img/febgroupbuy/tip.png) no-repeat top left; width:73px; height:28px; line-height:24px; text-align:center; color:#fff; font-style:normal;top:0; left:12px;}
	.content a h1{ font-size:14px; line-height:18px; height:36px; overflow:hidden; color:#F00; margin:5px 0;}
	.content a:hover h1{ color:#F60; }
	.content a p{ color:#900; font-size:16px; text-align:center;}
	.content a p span{ font-size:12px; color:#999; text-decoration:line-through; padding:0 5px;}
	#c4{ background:url(../img/febgroupbuy/ac4.jpg) top left no-repeat; width:1000px; height:486px; position:relative;}
	#c4 #pro_product{ position:absolute; height:308px; width:500px; top:52px; left:64px; display:block;}
	#c4 #gg{ display:block; width:345px;height:374px; left:613px; top:15px; position:absolute;}
	#c4 h6{ position:absolute; top:432px; left:230px; font-size:18px; color:#FFF; line-height:18px;}
	.barnav{height:100px; background:url(../img/febgroupbuy/pagenav.jpg) center center no-repeat; overflow:hidden; text-align:center;}
	
	
	#pagenav {
	line-height: 36px;
	margin: 10px 0;
	text-align: center;
	color:#fff;
}
#pagenav a {
	background: none repeat scroll 0 0 #BBBBBB;
	color: #FFFFFF;
	display: inline;
	font-size: 16px;
	font-weight: normal;
	height: 30px;
	line-height: 30px;
	margin: 2px;
	min-width: 30px;
	padding: 5px 10px;
	text-align: center;
}
#pagenav a.hover, #pagenav a:hover {
	background: none repeat scroll 0 0 #f30;
}
</style>

</head>
<body id=" ">
<script type="text/javascript" src="../js/head.js"></script>
<div id="mqj_top" style="background:#a41d23;">
<a href="http://mall.12580life.com/active/prizeMessage.chtml?actId=141#banner " target="_blank" id="hongbao"><img src="../img/febgroupbuy/right.jpg" /></a>
 </div>
<div id="bg">
    <div class="wrapper">
        <div id="banner">

        </div>

     </div>
     <div class="bar">
     	<div class="wrapper">
     	<a href="http://tuan.12580life.com/">
        	<img src="../img/febgroupbuy/bar.jpg" id="bar1" /></a>
        </div>
     </div>   
     <div class="wrapper">
        <div class="content">
         <c:forEach items="${pageData.data}"  var="item"  varStatus="status">
			<a  title="${item.goods_name}" target="_blank" href="http://tuan.12580life.com/team.php?id=${item.sale_id}">
				<c:if test="${item.goods_area_type == 1}">
                    <i>全省</i>
                  </c:if> 
                   <c:if test="${item.goods_area_type == 0}">
                     <i>${item.city_name}</i>
                  </c:if>   
                <img src="/itemimg/richfiles/year/${item.image}" width="210" height="134"/>
            	<h1>
            		${item.goods_name}
            	</h1>
            	
                <p><c:if test="${item.price_type == 0}">
                    	￥ <fmt:formatNumber value="${item.goods_price}" type="currency" pattern="0.00"/>
                    	</c:if>
                    	<c:if test="${item.price_type == 1}">
                    	${item.goods_price}积分
                    	</c:if> <span>￥ <fmt:formatNumber value="${item.market_price}" type="currency" pattern="0.00"/></span></p>
            </a>
            </c:forEach>
        </div>
        
    </div>

    <div class="barnav" >
     	<div class="wrapper">
 		 ${pageHtml}
        </div>
    </div>

    <div class="wrapper">
    	<img src="../img/febgroupbuy/gz.jpg" />
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

<script>
jQuery(document).ready(function(){
	
	  jQuery("#mosh_12580_header #city #citylist a").click(function(){
			jQuery.cookie('areaCode',jQuery(this).attr("acode"), {
				expires : 90,
				domain: '12580life.com',
				path:'/'
			});
			jQuery.cookie('regionCode',jQuery(this).attr("rcode"), {
				expires : 90,
				domain: '12580life.com',
				path:'/'
			});
			top.location.href='http://mall.12580life.com/febYearActivity/list.chtml';
		});
	  var burl = "http://mall.12580life.com/febYearActivity/list.chtml";
	  jQuery("#toploginbtn").attr("href","http://ca.12580life.com/login?service="+burl);
	  
});

</script>
</html>
