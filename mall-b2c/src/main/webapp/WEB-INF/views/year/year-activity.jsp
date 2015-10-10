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
<title>优惠在冬季</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/base.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script src="../js/DD_belatedPNG_0.0.8a-min.js"></script>
<!--[if IE 6]>
<script src="../js/DD_belatedPNG.js"></script>
<script>
  /* EXAMPLE */
  DD_belatedPNG.fix('.line,.more');
  
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
	$("#content li a").hover(function(){
		$(this).children(".product_dis").show();
		},function(){
		$(this).children(".product_dis").hide();	
		})
	
})

function goNext(page){
	window.location.href = '${ctx}/yearActivity/list.chtml?page='+page;
}

</script>
<style>
#bg {
	background:url(../img/year/bg1.jpg) top center no-repeat #a90101;
	min-height:500px;
}



.clear{ clear:both; }

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
	background:#ffdd14; text-align:center;
	font-weight:700; color:#F33;}
#mqj_top a:hover, #mqj_note a:hover{
	color:#FFF;
	background:#f90;
	}
	.line{ margin-left:100px; margin-bottom:50px;}
	#content li{ float:left; width:304px; margin:0 12px; text-align:center; font-size:14px; color:#FFF; position:relative; margin-bottom:10px; border-bottom:1px dashed #900;}
		#content li a{ line-height:24px; color:#fff;}
		#content li:hover{
	-webkit-transform:scale(1.02);
	-moz-transform:scale(1.02);
	-o-transform:scale(1.02);
	-ms-transform:scale(1.02);
	transform: scale(1.02);
    z-index: 8;
}

		#content li a img{ padding:20px; width:264px; height:168px; background:#fff; margin-bottom:30px; }
		.product_dis{ background:url(../img/year/btn.png) top center; position:absolute; bottom:10px; left:50%; margin-left:-100px; height:60px; width:200px; display:none; }
		.red{ text-align:center; display:block; width:100%; color:#FF3;  text-align:center;  font-size:12px;}
		.title_p{ height:24px; font-size:16px; overflow:hidden; line-height:24px; 
		width:300px;white-space :nowrap;text-overflow : ellipsis
		}
		
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
<body id="help">
<div id="mqj_top" style="background:#722a92;"> <a href="http://mall.12580life.com/groupbuy/theme.html">1.团购盛宴活动</a> <a href="#content_5">2.演出票活动</a> <a href="#content_3">3.订餐有礼活动</a> <a href="#">返回顶部</a> </div>


<script src="http://www.12580life.com/index_inc_v20120516/head_inc_v20120516" type="text/javascript"></script> 
<!-- 模板区域 开始 -->
<div id="bg">
    <div class="wrapper mauto">
	<img src="../img/year/banner.jpg" />
    	<div id="content">
        <a href="http://mall.12580life.com/mall-market/groupbuy/" target="_blank"><img src="../img/year/bar.jpg" /></a> 
			<ul>
			<c:forEach items="${pageData.data}"  var="item"  varStatus="status">
            	<li>
            	<c:choose>
            		<c:when test="${item.source == 0}">
            			<a target="_blank" href="http://tuan.12580life.com/team.php?id=${item.sale_id}">
            		</c:when>
            		<c:otherwise>
            			<a target="_blank" href="<ct:path flag='1' id='${item.sale_id}'/>">
            		</c:otherwise>
            	</c:choose>
                <div class="title_p" title="${item.goods_name}">${item.goods_name}</div>
                <span class="red">￥ <fmt:formatNumber value="${item.goods_price}" type="currency" pattern="0.00"/></span>
                 <div class="product_dis" ></div>
                 
                 <c:choose>
            		<c:when test="${item.source == 0}">
            			<img src="http://tuan.12580life.com/static/team/${item.image}" />
            		</c:when>
            		<c:otherwise>
            			<img src="<ct:path flag='2' id='${item.sale_id}'/>N0/${item.image}" />
            		</c:otherwise>
            	</c:choose>
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

<script src="http://www.12580life.com/foot_inc_v20120516.jsp" type="text/javascript"></script>
</body>
</html>
