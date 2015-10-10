<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/favorite.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/pagescroll.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script>
    	var web_url = '我的收藏';
    </script>
    <title>我的收藏</title>
</head>
<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<form action="favGoods.chtml" method="post" id="form1">
<input type="hidden" name="items" id="items" value=""/>
<input type="hidden" name="type" id="type" value="1"/>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的收藏</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
	        <div class="tab">
	            <div  class="tab_nav">
	                <ul class="tab">
	                    <li class="curr"><a href="#info1">我的收藏</a></li>
	                </ul>
	            </div>
	        </div>
            <div class="content">
	            <div id="fav_nav" class="tab_nav_c">
	                <ul class="tab">
	                    <li class="curr"><a href="favGoods.chtml">商品收藏</a></li>
	                    <li><a href="favStore.chtml">店铺收藏</a></li>
	                </ul>
	            </div>
                <div id="fav_content">
                    <div class="fav_content">
                       	<div class="grid list-controller">
                           	<input type="checkbox" class="g-u select-all J_SelectAll" value="" id="checkbox_all" />
                          		<label class="g-u">全选</label>
                           	<a data-spm-protocol="i" href="javascript:void(0);" onclick="del()" class="g-u fav-lbtn J_DelFavs">删除</a>
                           	${pageInfos }
                       	</div>
                       	<div class="fav_item_wrap">
                       		<c:if test="${empty list}" var="f">
                       			暂无收藏商品信息
                       		</c:if>
                       		<c:if test="${!f}">
                            <c:forEach items="${list}" var="data">
                            <div class="items"> <a href="<ct:path flag='1' id='${data[1] }'/>" class="items_img"><img alt="" width="160" height="160" src="<ct:path flag='2' id='${data[1] }'/>N3/${data[2] }" /></a>
                                <div class="price"><span class="old_price">￥<span>${data[3] }</span> </span><span class="now_price">￥<strong>${data[4] }</strong></span> </div>
                                <div class="procuts_shop"> <a href="<ct:path flag='3' id='${data[5] }'/>">${data[6] }</a> </div>
                                <div class="procuts_title">
                                    <input type="checkbox" name="favorite_id" id="favorite_id" value="${data[1] }" class="J_ItemSelect chk" />
                                    <a href="<ct:path flag='1' id='${data[1] }'/>">${data[7] }</a></div>
                                <div class="procuts_text"> <a href="javascript:void(0);" onclick="delSingle(${data[1] })" class="del">删除</a> <a href="javascript:;" onclick="buyNow(${data[1] });" class="buy">购买</a></div>
                            </div>
                            </c:forEach>
                            </c:if>
                            <!-- 收藏内容结束 -->
                            <div class="clr"></div>
                       	</div>
                       	<div class="grid list-controller">
                           	<input type="checkbox" class="g-u select-all J_SelectAll" value="" id="checkbox_all1" />
                           	<label class="g-u">全选</label>
                           	<a data-spm-protocol="i" href="javascript:void(0);" onclick="del()" class="g-u fav-lbtn J_DelFavs">删除</a>
                           	${pageInfos }
                       	</div>
                   	</div>
                   	<div class="fav_content"> </div>
                </div>
            </div>
        </div>
    </div>
   <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
</form>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        $("#favGoods").addClass("col_link");
    })
</script>
</body>
</html>
