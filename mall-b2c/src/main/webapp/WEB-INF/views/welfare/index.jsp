<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head> 
		<title>移动商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="../css/base.css">
		<link href="../css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="../css/welfare.css">
		<link rel="stylesheet" type="text/css" href="../css/windowStyle.css">
		<link href="../css/reset2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/actionWindow.js"></script>
		 <script type="text/javascript" src="../js/wp-scroll.js"></script>
		<script type="text/javascript" src="../js/welfare.js" charset="utf-8"></script>
	</head>
	<style>
	body{
 	 background-color: #ffdebf;
  	 font-family: "microsoft yahei"; text-align:center; line-height:1.5em; font-size:12px;
}
	
	</style>
	<body>
		<div id="g-header">
			<script src="http://mall.12580life.com/js/jquery.cookie.js" type="text/javascript"></script>
			<script src="http://mall.12580life.com/js/head.js" type="text/javascript"></script>
		</div>
	<input type="hidden" id="isLogin" value="${isLogin}" name="isLogin" >
		<div   class="g_img_total g_top_img">
		 
		</div>
		<div id="main">
			<div id="leftNav">
				<ul>
					 <c:forEach items="${goodsTypes}" var="goodstype" >
					<li>
						<a
						<c:if test="${goodstype.id eq itemType}"> class='active-left-a' </c:if>
						 href="main.chtml?itemType=${goodstype.id}">${goodstype.name}</a>
					</li>
					</c:forEach>
					<%-- <li>
						<a
						<c:if test="${1585753 eq itemType}"> class='active-left-a' </c:if>
						 href="main.chtml?itemType=1585753">女鞋</a>
					</li>
					<li>
						<a
						<c:if test="${1585756 eq itemType}"> class='active-left-a' </c:if>
						 href="main.chtml?itemType=1585756">男装</a>
					</li>
					<li>
						<a
						<c:if test="${1585760 eq itemType}"> class='active-left-a' </c:if>
						 href="main.chtml?itemType=1585760"> 女装</a>
					</li> --%>
				
				</ul>
			</div>
			<form action="main.chtml" style="display: none;" id='welfareForm'>
			<input name='item1'  id='input_item1' value="${item1 }" >
			<input name='item2' id='input_item2'  value="${item2 }">
			<input name='orderType' id='input_orderType' value="${orderType }"> 
			<input name='brand' id='input_brand' value="${brandEncode }"> 
			<input name='itemType' id='input_itemType' value="${itemType }"> 
			</form>
			<div id="content">
			<div class="content-nav">
				<div class="b-bottom content-nav-style" >
				<div class="content-nav-left">品牌：</div>
				<div class="content-nav-right">
				<ul class="float-ul">
						<li><a
						<c:if test="${'' eq brand}"> class='active-a' </c:if>
						 href="###"  onclick="$this.refresh('input_brand','',this)" >不限</a></li>
						<c:if test="${not empty brands}">
						<c:forEach items="${brands }" var="item" >
						
							<li><a 
							<c:if test="${item eq brand}"> class='active-a'</c:if>
							
							href="###"  onclick="$this.refresh('input_brand','${item }',this)" >${item }</a></li>
						</c:forEach>
						</c:if>
					</ul>
					</div>
					<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
				</div>
				<c:forEach items="${types}" var="type" varStatus="status">
					<div class="b-bottom content-nav-style" >
				<div class="content-nav-left">${type.PARAM_KEY}：</div>
				<div class="content-nav-right">
				<ul class="float-ul">
						<li><a
							<c:if test="${'' eq type.on}"> class='active-a'</c:if>
						 href="###" onclick="$this.refresh('input_item${status.count}','',this)"  >不限</a></li>
							<c:if test="${not empty type.details}">
						<c:forEach items="${type.details}" var="detail" >
							<li><a
							<c:if test="${detail eq type.on}"> class='active-a'</c:if>
							 href="###" onclick="$this.refresh('input_item${status.count}','${detail }',this)"  ><c:out value="${detail }" escapeXml="true" ></c:out></a></li>
						</c:forEach>
						</c:if>
					</ul>
					</div>
					<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
				</div>
				</c:forEach>
			
			<div class="b-bottom content-nav-style" >
				<div class="content-nav-left">已选：</div>
				<div class="content-nav-right">
				<ul class="float-ul">
				<li class="active-a" style="padding: 0px 10px 0px 10px">${brand}</li>
				<li class="active-a" style="padding: 0px 10px 0px 10px">${item1}</li>
				<li class="active-a" style="padding: 0px 10px 0px 10px">${item2}</li>
					</ul>
					</div>
					<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
				</div>
				<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
				</div>
			
			<div class="goods-content">
				<ul class="list-h">
					<c:if test="${not empty pageData}">
						 <c:forEach items="${pageData.data}" var="item" >
						 <li class="item">
                    <a class="product_more" title="${item.name }" target="_blank" href="http://mall.12580life.com/tools/item.chtml?saleId=${item.item_id}">
                    
                    
                    <img width="200px;" height="200px;" alt="${item.name }" src="<ct:path flag='2' id='${item.item_id}'/>N3/${item.img_path}">
                        <h5 style="font-family: microsoft yahei"><c:choose>
							<c:when test="${fn:length(item.name) > 32}">
								<c:out value="${fn:substring(item.name, 0, 31)}..." escapeXml="true" ></c:out>
							</c:when>
							<c:otherwise>
							<c:out value="${item.name }" escapeXml="true" ></c:out>
							</c:otherwise>
						</c:choose></h5>
                        <p style="margin-top: 20px">
                        	<span class="price" style="font-family: microsoft yahei">￥${item.shop_price}</span> 
                        	<span class="oldprice" style="font-family: microsoft yahei">￥${item.market_price}</span>
                        </p>
                        </a> 
                        <a class="btn" href="http://mall.12580life.com/tools/item.chtml?saleId=${item.item_id}">立即抢购</a>
                    </li>
						</c:forEach>
					</c:if>
				</ul>
				 <ht:page pageData="${pageData}" />
				<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
						<c:if test="${empty pageData.data}">
							<div class="note">
								<p class="i">目前没有相关记录!</p>
							</div>
						</c:if>
			</div>
			</div>
				<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
			</div>
			<div style="display: block;height: 0px;clear: both;visibility: hidden;"></div>
		<div   class="g_img_total g_foot_img" >
			 
		</div>
		<div id="g-footer">
			<script src="http://mall.12580life.com/js/foot.js"
				type="text/javascript"></script>
		</div>
	</body>
</html>
