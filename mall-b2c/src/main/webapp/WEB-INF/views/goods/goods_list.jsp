<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="【mo生活优惠券】为您提供${regionName }代金券,包括美食、休闲娱乐、美容健身、购物、旅游酒店、教育培训等生活服务,可免费打印、短信下载、二维码扫描,代金券可直接购买到店消费。"/>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<link href="http://mall.12580life.com/css/base.css" rel="stylesheet" type="text/css" />
<link href="http://mall.12580life.com/css/layout.css" rel="stylesheet" type="text/css" />
<link href="http://mall.12580life.com/css/quan.css" rel="stylesheet" type="text/css" />
<link href="http://mall.12580life.com/css/sidebar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://mall.12580life.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://mall.12580life.com/js/jquery.calendar-widget.js"></script>
<script type="text/javascript" src="http://mall.12580life.com/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="http://mall.12580life.com/js/jquery.cookie.js"></script>

<script type="text/javascript">
$(function(){

	$(".quan_main_info_address").hover(function(){
			if($(this).siblings(".quan_main_info_address_content")){
				$(this).siblings(".quan_main_info_address_content").show();
				}
				return false
			},function(){
				if($(this).siblings(".quan_main_info_address_content")){
				$(this).siblings(".quan_main_info_address_content").hide();
				}
			});
			$(".quan_main_info_count i.icon-1,.quan_main_info_count i.icon-2,.quan_main_info_count i.icon-3,.quan_main_info_count i.icon-4").hover(function(){
					
				$(this).parent().siblings(".quan_main_info_tip").html($(this).children().html());
					
					},function(){
				$(this).parent().siblings(".quan_main_info_tip").html("");		
			})
})

/*
** 切换“只看新单”和三种支付方式跳转
*/
function checkBox(type){
   if (type == 'isNewOrder'){
       window.location.href="${checkNewOrderUrl}";
   }else if (type == 'allowScore'){
       window.location.href="${checkAllowScoreUrl}";
   }else if (type == 'allowBill'){
       window.location.href="${checkAllowBillUrl}";
   }else if (type == 'allowCoin'){
       window.location.href="${checkAllowCoinUrl}";
   }
}

/*
** 切换排序
*/
function checkOrder(type){
   if (type == '0'){
      window.location.href="${defaultOrderUrl}";
   }else if (type == '1'){
      if ('${sort}' == '1'){
         window.location.href="${descSaleNumUrl}";
      }else {
         window.location.href="${ascSaleNumUrl}";
      }
   } else if(type == '2'){
      if ('${sort}' == '2'){
         window.location.href="${descOnlineTimeUrl}";
      }else{
         window.location.href="${ascOnlineTimeUrl}";
      }
   } else if(type == '3'){
      if ('${sort}' == '3'){
         window.location.href="${descPriceUrl}";
      }else{
         window.location.href="${ascPriceUrl}";
      }
   }
}
</script>
<title>${regionName }最新打折代金券_电子代金券打印_免费短信下载-mo生活代金券</title>
</head>

<body id="quan">
<script type="text/javascript" src="http://www.12580life.com/common/head.chtml?naviCode=djq&regionCode=${regionCode }"></script>
<!-- <script type="text/javascript" src="../js/head.js"></script> -->

<div class="wrapper mauto">
    <div id="quan_menu">
        <c:if test="${not empty title }">
           <c:choose>
           <c:when test="${not empty goodsList }">
              <div id="quan_menu_search_res">恭喜!为您找到<span class="red">${totalCount }</span>个与<span class="red">"${title }"</span>相关的商品...</div>
           </c:when>
           <c:otherwise>
              <div id="quan_menu_search_res">
	        	很遗憾~~没有为您有找到与<span class="red">"${title }"</span>相关的商品...<br/>
	        	您可以更换检索条件或<a href="http://quan.12580life.com/goods/goodsList.chtml?regionCode=${regionCode }"><font color="red">返回上一步</font></a>
	           </div>
           </c:otherwise>
           </c:choose>
        </c:if>
        <c:if test="${empty title or not empty goodsList}">
        <div id="quan_menu_hot">
            <dl>
                <dt>热门：</dt>
                <dd>
                <c:if test="${not empty hotTopicList }">
                   <c:forEach items="${hotTopicList }" var="hotTopic">
                      <a href="${hotTopic['href'] }" target="_blank">${hotTopic['title'] }</a>
                   </c:forEach>
                </c:if>
                <span style="float:right; padding-right:10px; color:#E95098">使用礼金券购物请至<a href="http://quan.12580life.com/search/t0_r0_p0_c0_e0_o0_w.htm" style="float:none; padding:none;">旧版代金券频道</a></span>
                </dd>
            </dl>
        </div>
        <div id="quan_menu_area">
            <dl>
                <dt>区域：</dt>
                <dd>
                    ${regionHtml }
                </dd>
            </dl>
        </div>
        <div id="quan_menu_class" style="border-bottom:1px #ddd solid">
            <dl>
                <dt>分类：</dt>
                <dd>
                    ${goodsTypeHtml }
                </dd>
            </dl>
        </div>
        <div id="quan_menu_price">
            <dl>
                <dt>价格：</dt>
                <dd>
                    ${priceHtml }
                </dd>
            </dl>
        </div>
        <div id="quan_menu_array">
            <dl>
                <dt>序列：</dt>
                <dd>
                    <div class="quan_menu_content"> 
                    <span id="quan_menu_array_class"> 
                        <a href="#" onclick="checkOrder('0');" <c:if test="${sort eq '0'}">class="active"</c:if> id="quan_menu_btn_left">默认 <i></i></a>
                        <a href="#" onclick="checkOrder('1');" <c:if test="${sort eq '1' or sort eq '-1' }">class="active"</c:if> id="quan_menu_btn_left">销量 
                        <i <c:choose><c:when test="${sort eq '1' }">class="up"</c:when><c:otherwise>class="down"</c:otherwise></c:choose>></i></a>
                        <a href="#" onclick="checkOrder('3');" <c:if test="${sort eq '3' or sort eq '-3' }">class="active"</c:if> id="quan_menu_btn_left">价格 
                        <i <c:choose><c:when test="${sort eq '3' }">class="up"</c:when><c:otherwise>class="down"</c:otherwise></c:choose>></i></a>
                        <a href="#" onclick="checkOrder('2');" <c:if test="${sort eq '2' or sort eq '-2' }">class="active"</c:if> id="quan_menu_btn_right">时间
                        <i <c:choose><c:when test="${sort eq '2' }">class="up"</c:when><c:otherwise>class="down"</c:otherwise></c:choose>></i></a>
                     </span>
                     <span>
                        <input class="checkbox" type="checkbox" <c:if test="${isNewOrder eq '1' }">checked</c:if> onclick="checkBox('isNewOrder');" />
                        <label>只看新单</label> 
                     </span>
                     <span><label>支持支付方式</label>
                        <input class="checkbox" type="checkbox" <c:if test="${allowScore eq '1' }">checked</c:if> onclick="checkBox('allowScore');" />
                        <label>积分/M值</label>
                        <input class="checkbox" type="checkbox" <c:if test="${allowBill eq '1' }">checked</c:if> onclick="checkBox('allowBill');" />
                        <label>话费</label>
                        <input class="checkbox" type="checkbox" <c:if test="${allowCoin eq '1' }">checked</c:if> onclick="checkBox('allowCoin');" />
                        <label>商城币</label>
                      </span> 
                    </div>
                    ${topPageInfoHtml }
                </dd>
            </dl>
        </div>
        </c:if>
    </div>
    <div id="quan_main">
        <ul>
        <c:if test="${not empty goodsList }">
                  <c:forEach items="${goodsList }" var="item" varStatus="status">
                     <li <c:if test="${status.count % 3 == 0 }">class="nth-child-3n"</c:if>>
                     <c:if test="${item['isNewOrder']}">
                        <span class="quan_main_ico">
                           <i class="xindan"></i>
                        </span>
                     </c:if>
                     <a class="quan_main_img" href="${item['url'] }" target="_blank"><!-- <img src="../img/explore.jpg"> --><img width="286px" height="175px" src="${item['img'] }" /></a>
                     <a class="quan_main_title" href="${item['url'] }" target="_blank">${item['name'] }</a>
                     <div class="quan_main_btn"> <i><strong>
                     <c:choose>
                        <c:when test="${item['payCash'] }">￥<span>${item['price'] }</span></strong> 
                        <c:if test="${not empty item['priceOrigin'] }"><span class="oldprice">￥${item['priceOrigin'] }</span></c:if>
                        <c:if test="${not empty item['priceMember'] }"><span class="vipprice">VIP￥${item['priceMember'] }</span></c:if>
                        </c:when>
                        <c:when test="${item['payScore'] }">积分：<span>${item['priceScore'] }</span>个</strong>
                        </c:when>
                        <c:when test="${item['payCoin'] }">商城币：<span>
                        <c:choose>
                        <c:when test="${item['itemSource'] eq 'S' }">${item['priceCoin'] }</c:when>
                        <c:otherwise>${item['price'] }</c:otherwise>
                        </c:choose>
                        </span>个</strong>
                        </c:when>
                        <c:when test="${item['payBill'] }">话费：￥<span>${item['price'] }</span></strong>
                        </c:when>
                     </c:choose>
                     </i> <a href="${item['url'] }" target="_blank">查看详情</a> </div>
                     <div class="quan_main_info"> <span class="quan_main_info_count">
                     <c:if test="${item['payCash'] }"><i class="icon-4"><em>支持现金支付</em></i></c:if>
                     <c:if test="${item['payScore'] }"><i class="icon-2"><em>支持积分支付</em></i></c:if>
                     <c:if test="${item['payCoin'] }"><i class="icon-3"><em>支持商城币支付</em></i></c:if>
                     <c:if test="${item['payBill'] }"><i class="icon-1"><em>支持话费支付</em></i></c:if>
                     <i></i>已售<strong>${item['saleNum'] }</strong>份</span> 
                     <c:if test="${not empty item['blocks'] }">
                        <c:choose>
                        <c:when test="${fn:length(item['blocks']) > 1 }">
                           <!-- <span class="quan_main_info_address">多商圈 &or;</span>
                           <div class="quan_main_info_address_content">
                              <c:forEach items="${item['blocks'] }" var="block">
                                 ${block }
                                 <br/>
                              </c:forEach>
                           </div> -->
                        </c:when>
                        <c:otherwise>
                          <span class="quan_main_info_address">
                          <c:forEach items="${item['blocks'] }" var="block">
                             ${block }
                          </c:forEach>
                          </span>
                        </c:otherwise>
                        </c:choose>
                     </c:if>
                     <div class="quan_main_info_tip"></div>
                     </div>
                     </li>
                  </c:forEach>
            </c:if>
            <div class="cb"></div>
        </ul>
        <c:choose>
	        <c:when test="${totalPage > 1 }">
		        <div id="pagenav" class="cb">
		        	${pageInfoHtml }
		        </div>
	        </c:when>
	        <c:otherwise>
	            <div id="pagenav" class="cb">共 ${totalCount } 条</div>
	        </c:otherwise>
        </c:choose>
    </div>
</div>
<script type="text/javascript" src="http://mall.12580life.com/js/foot_djq.js"></script>
</body>
</html>