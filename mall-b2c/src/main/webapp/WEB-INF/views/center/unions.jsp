<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <link href="../css/dealList.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/area_data.chtml"></script>
    <script type="text/javascript" src="../js/area.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script>
    	var web_url = '${web_url}';
    	var webRoot = '${webRoot}';
    </script>
    <title>${chname }</title>
</head>
<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto" >
   
     <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;${chname }</span></div>
    <div id="main" class="fr  w750">
       <iframe style="width: 810px;height:1180px;" id="win" name="win"  frameborder="0" scrolling="no" src="${url }"></iframe>
       <div id="tijian" class="tijian yhq-tuijian">
       </div>
    </div>
     <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        jQuery.ajax({
           url:'http://mall.12580life.com/recommend/zxyh.chtml',
           dataType: "json",
           cache:false,
           success:function(data){
              if(jQuery.isArray(data) && data.length > 0){
                 var tujian = "<div class='nav_kt nav_kt_808'><span><em><a href='http://http://youhui.12580life.com/szkq.html' target='_blank'>更多优惠券</a></em>今日优惠券推荐</span></div>";
                 tujian += "<div class='content_a' attr='cnt' style='display:block;'><div id='slider' class='slider'><ul id='slides' class='clearfix'>";
                 for (var i = 0; i < data.length; i++){
                     tujian += "<li><a href='http://youhui.12580life.com/yhq_info/"+ data[i].ID +"?referer="+data[i].REFERER+"' title='"+ data[i].TITLE +"' target='_blank'>";
                     tujian += "<img src='http://youhui.12580life.com/shlm/mms/smil/" + data[i].PIC_PATH + "'>";
                     tujian += "<em><span class='yhq-title'>" + data[i].YHQ_NAME + "</span>优惠：" + data[i].REMARK + "</span><span>截止日期："+data[i].END_TIME+"</span></em></a></li>";
                 }
                 tujian += "</ul></div></div>";
                 jQuery("#tijian").html(tujian);
              }
           }
        });
    })
</script>
</body>
</html>
