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
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/pagescroll.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript">
    	var web_url = '我的咨询';
    </script>
    <title>我的咨询</title>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<form action="consults.chtml" method="post" id="form1">
<input type="hidden" name="curpage" id="curpage" value=""/>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的咨询</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">我的咨询</a></li>
                        </ul>
                    </div>
                </div>
                <div class="content">
                    <div class="rate_comments">
                        <c:forEach items="${list}" var="data">
                            <div class="item">
                                <div class="user">
                                    <div class="u-icon"> <a title="查看TA的全部评价" href="<ct:path flag='1' id='${data.sale_id }'/>" target="_blank"> <img width="50" height="50" upin="achen0212" src="<ct:path flag='2' id='${data.sale_id }'/>N5/${data.file_name }" alt="商品a"/> </a> </div>
                                    <div class="u-name"> <a href="<ct:path flag='1' id='${data.sale_id }'/>" target="_blank">${data.short_name }</a></div>
                                    <span class="u-level"><c:if test="${data.status eq 0}"><img src="../img/icon_yel.jpg" /></c:if><c:if test="${data.status eq 2}"><img src="../img/icon_red.jpg" /></c:if></span>
                                </div>
                                <div class="i-item" data-guid="9ac660f0-744d-48fc-8709-cea0e0419450"><div class="o-topic"><strong>${data.question_type }</strong><span class="date-comment">${data.commentTime }</span> </div>
                                    <div class="comment-content">
                                        <p>${data.commentContent }</p>
                                    </div>
                                    <c:if test="${!empty data.replyContent }">
                                    <div class="comment-reply">
                                        <p><strong>${data.nickname }: </strong>${data.replyContent }</p>
                                        <span class="date-comment">${data.replyTime }</span></div>
                                    </c:if>
                                </div>
                                <div class="corner tl"></div>
                            </div>
                        </c:forEach>
                        ${pageInfos }
                    </div>
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
        $("#consults").addClass("col_link");
    })
</script>
</body>
</html>