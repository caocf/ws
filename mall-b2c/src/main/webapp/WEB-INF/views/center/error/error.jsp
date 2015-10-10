<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../../css/pro.css" rel="stylesheet" type="text/css" />
    <link href="../../js/lab/default/dialog.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../../js/date-picker/WdatePicker.js" ></script>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../../js/cart-common.js"></script>
    <script type="text/javascript" src="../../js/ext.js"></script>
    <script type="text/javascript" src="../../js/usercenter.js"></script>
    <script type="text/javascript" src="../../js/ad.js"></script>
    <script type="text/javascript" src="../../js/global.js"></script>
    <script type="text/javascript" src="../../js/pagescroll.js"></script>
    <script type="text/javascript" src="../../js/jquery.cookie.js"></script>
    <title>我购买的商品</title>
</head>

<body id="usercenter">
<script type="text/javascript" src="../../js/head.js"></script>
<div class="wrapper mauto" >
<div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span class="_top_span">&nbsp;&gt;&nbsp;我购买的商品</span></div>
    <div id="" class="fr  w750">
        <div class="detail">
            <div class="tab">
                <div id="" class="tab_nav">
                    <ul class="tab">
                        <li class="curr" id="li_id">我购买的商品</li>
                    </ul>
                </div>
            </div>
            
             <div class="content">
            	<p style="height: 100px;margin-top: 50px;text-align: center;">${msg }</p>
            </div>
        </div>
    </div>
    <%@include file="../left_menu.jsp"%>
    <span class="clr"></span>
</div>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
    	var menu_value = '${menuValue}';
    	if(menu_value){
    		$('#'+menu_value.split('_')[0]).addClass("col_link");
    		$('._top_span').html(">&nbsp;&gt;&nbsp;"+menu_value.split('_')[1]);
    		$('li#li_id').html(menu_value.split('_')[1]);
    	}else{
	    	$("#orderManager").addClass("col_link");
    	}
    });
</script>
</body>
</html>