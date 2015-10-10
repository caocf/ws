<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/comment.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <title>我的评价</title>
    <script type="text/javascript">
    	var web_url = '我的评价';
		var saleId = '${saleIds}';		
	</script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的评价</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">我的评价</a></li>
                        </ul>
                    </div>
                </div>
                <form action="saveComment.chtml" method="post" name="form1" id="form1" >
                    <input type="hidden" id="saleIds" name="saleIds" value="${saleIds }"/>
                    <input type="hidden" id="orderId" name="orderId" value="${orderId }"/>
                    <input type="hidden" id="storeId" name="storeId" value="${storeId }"/>
                    <div class="content">
                    <c:forEach items="${list}" var="data">
                        <div class="rate_comments">
                            <h3 class="rate_title_products">商品： <a href="<ct:path flag='1' id='${data.item.id }'/>">${data.item.shortName }</a></h3>
                            <div class="reply_product">
                                <label>对商品评价：</label>
	                            <span class="rate-stars" data-type="description" style="position: absolute; left: -9999px;">
	                            <label>
	                                <input type="radio" name="description" value="1" title="很不满意，差得太离谱，与卖家描述的严重不符，非常不满"/>
	                                一星</label>
	                            <label>
	                                <input type="radio" name="description" value="2" title="不满意，部分有破损，与卖家描述的不符，不满意"/>
	                                二星</label>
	                            <label>
	                                <input type="radio" name="description" value="3" title="一般，质量一般，没有卖家描述的那么好"/>
	                                三星</label>
	                            <label>
	                                <input type="radio" name="description" value="4" title="满意，质量不错，与卖家描述的基本一致，还是挺满意的"/>
	                                四星</label>
	                            <label>
	                                <input type="radio" name="description" value="5" title="很满意，质量非常好，与卖家描述的完全一致，非常满意"/>
	                                五星</label>
	                            </span>
								<span class="ks-simplestar">
									<div class="ks-starwrapper ks-starwrapper_${data.item.id }">
	                                    <a class="ks-star ks-star1_${data.item.id }" href="javascript:void(0)"><img src="../img/touming.png"/></a>
	                                    <a class="ks-star ks-star2_${data.item.id }" href="javascript:void(0)"><img src="../img/touming.png"/></a>
	                                    <a class="ks-star ks-star3_${data.item.id }" href="javascript:void(0)"><img src="../img/touming.png"/></a>
	                                    <a class="ks-star ks-star4_${data.item.id }" href="javascript:void(0)"><img src="../img/touming.png"/></a>
	                                    <a class="ks-star ks-star5_${data.item.id }" href="javascript:void(0)"><img src="../img/touming.png"/></a>
	                                </div>
									<input type="hidden" class="starValue" id="star_${data.item.id }" name="star_${data.item.id }" value="" />
								</span><span class="ks-ss-tips"></span>
                            </div>
                            <textarea onkeyup="limitContent(this)" onkeydown="limitContent(this);" onfocus="if(value=='请在此处填写您要评价的内容'){value=''}" class="message" onblur="if(value==''){value='请在此处填写您要评价的内容'}" name="message_${data.item.id }" id="message_${data.item.id }">请在此处填写您要评价的内容</textarea>
                            <br/><font color="red">评价内容最多100个字</font>
                        </div>
                    </c:forEach>
                    <c:if test="${not empty list}">
                            <input type="button" id="insertMessageBtn" onclick="checkComment();" class="dBtn-btn dBtn-blue" title="提交" value="提交"/>
                    </c:if>
                    <c:if test="${empty list}">
                    	该商品已下架或已售完，无法评论！
                    </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        $("#comments").addClass("col_link");
    })
</script>
</body>
</html>