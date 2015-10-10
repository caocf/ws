<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <link href="../js/lab/default/dialog.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/date-picker/WdatePicker.js" ></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../js/cart-common.js"></script>
    <script type="text/javascript" src="../js/ext.js"></script>
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/pagescroll.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <title>我的抽奖</title>
    <style>
	    .detail_cz{ padding:20px; border:1px #ddd solid; border-top:0; line-height:24px;}
		.detail_cz td{ padding:0; border-right:1px #ddd solid; border-bottom:1px #ddd solid;}
		.detail_cz th{ border-right:1px #ddd solid; border-bottom:1px #ddd solid; background:#eee;}
		.detail_cz tr{border-left:1px #ddd solid; border-top:1px #ddd solid;}
	</style>
    <script>
        var web_url = '我的抽奖';
    	var webRoot = '${webRoot}';
    </script>
    
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<form method="post" id="form1">
	<input type="hidden" name="curpage" id="curpage" value=""/>
	<input type="hidden" name="flag" id="flag" value="${flag }"/>
	<input type="hidden" name="isdelivery" id="isdelivery" value=""/>
	<input type="hidden" name="orderId" id="orderId" value=""/>
	<div class="wrapper mauto" >
	    <div class="breadcrumb"><strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的抽奖</span></div>
	    <div id="main" class="fr  w750">
	        <div class="detail">
	            <div class="detail">
	                <div class="tab">
	                    <div  class="tab_nav">
	                        <ul class="tab">
	                            <li class="curr"><a href="#info1">我的抽奖</a>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	                <div class="detail_cz">
					    <table width="700px" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
		                        <th>活动名称</th>
		                        <th>抽奖时间</th>
		                        <th>奖品</th>
		                    </tr>
		                    <c:if test="${!empty msg }">
		                    	<tr>
		                    		<td colspan="10" style="color:red">${msg }</td>
		                    	</tr>
		                    </c:if>
		                    <c:forEach items="${list}" var="data">
		                    	<tr>
		                        	<td>${fn:substring(data.activeName,0,15)}
		                        		<c:if test="${fn:length(data.activeName)> 16}"> 
		                        			...
		                        		</c:if>
		                        	</td>
		                        	<td><fmt:formatDate value="${data.hitTime }" pattern="yyyy-MM-dd HH:mm" /></td>
			                        <td class="col_link">${fn:substring(data.lotteryInfo,0,15)}
		                        		<c:if test="${fn:length(data.lotteryInfo)> 16}"> 
		                        			...
		                        		</c:if>
			                        </td>	
		                    	</tr>   
		                    </c:forEach>              
		                </table>
	                	${pageInfos }	
	                </div>
	            </div>
	        </div>
	    </div>
		<%@include file="left_menu.jsp"%>
	    <span class="clr"></span>
	  </div>
	</form>
	<script type="text/javascript" src="../js/foot.js"></script>
	</body>
</html>