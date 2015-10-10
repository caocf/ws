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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>过大年</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/base.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script src="../js/DD_belatedPNG_0.0.8a-min.js"></script>
<!--[if IE 6]>
<script src="../js/DD_belatedPNG.js"></script>
<script>
  /* EXAMPLE */
  DD_belatedPNG.fix('.product_dis');
  
  /* string argument can be any CSS selector */
  /* .png_bg example is unnecessary */
  /* change it to what suits you! */
</script>
<![endif]-->
<script language="javascript" type="text/javascript">
$(function(){ 	
	var $body_height = $(document).height();
	var $scroll_height = $(window).scrollTop(); 
	$("shadow").height($body_height);
	$("#mqj_top").hover(function(){
			$("#mqj_top").fadeTo("fast",1);	
		},function(){
			$("#mqj_top").fadeTo("fast",0.5);
	});
	$("#mqj_note").hover(function(){
			$("#mqj_note").fadeTo("fast",1);	
		},function(){
			$("#mqj_note").fadeTo("fast",0.5);
	});
	
	$(window).scroll(function(){
		var $scroll_height = $(window).scrollTop(); 
		$("#mqj_note").css({top:$scroll_height+200+"px"});
	})
	$(window).scroll(function(){
		var $scroll_height2 = $(window).scrollTop(); 
		$("#mqj_top").css({top:$scroll_height2+200+"px"});
	})

	
})

function goNext(page){
	window.location.href = '${ctx}/yearActivity/list.chtml?page='+page;
}

</script>
<style>
#bg {
	background:url(../img/year/bg.jpg) top center no-repeat #D00505;
	min-height:500px;
}
.clear {
	clear:both;
}
#mqj_top {
	filter:alpha(opacity=80); /*IE滤镜，透明度50%*/
	-moz-opacity:0.8; /*Firefox私有，透明度50%*/
	opacity:0.8;/*其他，透明度50%*/
	position: absolute;
	right: 0;
	top: 200px;
	width: 140px;
	z-index: 100;
	background:#ffdd14;
}
#mqj_note {
	filter:alpha(opacity=80); /*IE滤镜，透明度50%*/
	-moz-opacity:0.8; /*Firefox私有，透明度50%*/
	opacity:0.8;/*其他，透明度50%*/
	position: absolute;
	left: 0;
	top: 100px;
	width: 140px;
	z-index: 200;
	background:#ffdd14;
}
#mqj_top a, #mqj_note a {
	display:block;
	color:#F69;
	font-size:16px;
	padding:10px 10px;
	background:#ffdd14;
	text-align:center;
	font-weight:700;
	color:#F33;
}
#mqj_top a:hover, #mqj_note a:hover {
	color:#FFF;
	background:#f90;
}
.line {
	margin-left:100px;
	margin-bottom:50px;
}
#content li {
	float:left;
	width:304px;
	margin:10px 12px;
	text-align:center;
	font-size:14px;
	color:#FFF;
	position:relative;
	background:#fff;
	border-radius: 8px;
}
#content li a {
	line-height:24px;
	color:#fff;
}
#content li:hover {
	  box-shadow: 0 0 8px rgba(0, 0, 0, 0.7);

    z-index: 8;
	-webkit-transform:translateY(-5px);
	-moz-transform:translateY(-5px);
	-o-transform:translateY(-5px);
	-ms-transform:translateY(-5px);
	transform:translateY(-5px);
	z-index: 8;
}
#content li a img {
	padding:20px;
	width:264px;
	height:168px;
	background:#fff;
	margin:10px 0;
}

.product_dis {
	background:#f8e704  center no-repeat;
	border-radius: 3px;
    color: #FFFFFF;
    font-size: 18px;
    height: 33px;
    line-height: 33px;
    margin: 10px 45px;
    text-align: center;
    width: 212px;
	font-weight:700;
}
#content li:hover .product_dis{ background:#e5d504;} 
.red {
	text-align:center;
	display:block;
	width:100%;
	color:#f30;
	text-align:center;
	font-size:16px;
	height:20px;
	line-height:20px;
	margin-bottom:10px; font-weight:700;
}
.gray{ font-size:14px; color:#bbb; margin-right:20px; text-decoration:line-through; font-weight: normal;}
.title_p {
	height:48px;
	font-size:16px;
	overflow:hidden;
	line-height:24px;
	color:#F00;
	margin:0 20px 15px;
}
.tips{ top:20px; left:20px; background:#F00; color:#fff; position:absolute; line-height:71px; 
	-webkit-transform: rotate(-15deg);
	-moz-transform:rotate(-15deg);
	-o-transform: rotate(-15deg);
	-ms-transform: rotate(-15deg);
	transform: rotate(-15deg); background:url(../img/year/tips.png) top center; height:71px; width:71px;}
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

.viptejia{
	cursor:pointer
}
</style>
</head>
<body id="help">

<script type="text/javascript" src="../js/head.js"></script>
<!-- 模板区域 开始 -->
<div id="bg">
    <div class="wrapper mauto"> <img src="../img/year/banner.jpg" />
        <div id="content"> <a href="http://mall.12580life.com/mall-market/groupbuy/" target="_blank"><img src="../img/year/bar.jpg" /></a>
            <ul>
              <c:forEach items="${pageData.data}"  var="item"  varStatus="status">
                <li>
                
                <a title="${item.goods_name}" target="_blank"  href="http://tuan.12580life.com/team.php?id=${item.sale_id}">
                <c:if test="${item.goods_area_type == 1}">
                    <div class="tips">全省</div>
                  </c:if> 
                  <c:if test="${item.goods_area_type == 0}">
                    <div class="tips">${item.city_name}</div>
                  </c:if>   
                    <img src="/itemimg/richfiles/year/${item.image}"/>
                                        <div class="title_p">${item.goods_name}</div>
                    <span class="red"><span class="gray">￥ <fmt:formatNumber value="${item.market_price}" type="currency" pattern="0.00"/></span>
                    	<c:if test="${item.price_type == 0}">
                    	￥ <fmt:formatNumber value="${item.goods_price}" type="currency" pattern="0.00"/>
                    	</c:if>
                    	<c:if test="${item.price_type == 1}">
                    	${item.goods_price}积分
                    	</c:if>
                    	
                    	</span>
                    	
                    <div class="product_dis" >立即购买</div>
                    </a>
                    
                    </li>
              </c:forEach>
                <div class="clear"></div>
            </ul>
             ${pageHtml}
        </div>
    </div>
</div>
<!-- 模板结束 开始 --> 

<script type="text/javascript" src="../js/foot.js"></script>
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
			top.location.href='http://mall.12580life.com/yearActivity/list.chtml';
		});
	  var burl = "http://mall.12580life.com/yearActivity/list.chtml";
	  jQuery("#toploginbtn").attr("href","http://ca.12580life.com/login?service="+burl);

	 
});

</script>
</html>
