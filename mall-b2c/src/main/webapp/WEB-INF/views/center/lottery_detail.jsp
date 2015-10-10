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
    <script type="text/javascript" src="../js/date-picker/WdatePicker.js" ></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/pagescroll.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <title>我购买的商品</title>
    <script>
    	var web_url = '我的票务';
    	var webRoot = '${webRoot}';
    </script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<style>
body{font-size:12px;line-height:150%; color:#333;font-family: "microsoft yahei"; background:#fff;}
</style>
<form action="lottery.chtml" method="post" id="form1">
<input type="hidden" name="curpage" id="curpage" value="" />

<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./lottery.chtml">个人中心</a></span>
    	<span>&nbsp;&gt;&nbsp;我的彩票</span>
    </div>
    
    <div id="main" class="fr  w750">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr">
                            	<a href="#info1">我的彩票</a>
                            </li>
                        </ul>
                    </div>
                </div>
              </div>
              
              
              <div class="content">
		<dl class="detail_dl">
       	  <dt>订单编号：</dt>
       	  <dd>${orderNo} </dd>
        </dl>       
	  	<dl class="detail_dl">
        	<dt>下单时间：</dt>
        	<dd>${createTime }</dd>
        </dl>  
        <dl class="detail_dl">
        	<dt>购彩详情：</dt>
        	<dd>
           	  <table width="100%" border="0">
            	  <tbody><tr>
            	   <th scope="col">彩种</th>
            	    <th scope="col">购彩期号</th>
            	    <th scope="col">投注号码</th>
                    <th scope="col">投注注数</th>
            	    <th scope="col">投注状态</th>
            	    <th scope="col">投注金额</th>
            	    <th scope="col">中奖金额</th>
          	    </tr>
          	    <!-- 13759655855519350476@2013092@23529@06 27 28 30 33-04 12@1@3@3@20130808204544@^137596558555547202@2013092@23529@19 20 29 34 35-04 05@1@3@3@20130808204546@ -->
          	    <!-- 票ID@期号@彩种@票信息@单票注数@单票金额@票状态@出票时间^订单编号 -->
          	    <c:forEach items="${infoList}" var="data">
            	  <tr>
            	  	 <td>
            	    <c:choose>
            	    <c:when test="${data[2] == '01'}">双色球</c:when>
            	    <c:when test="${data[2] == '05'}">福彩3D</c:when>
            	    <c:when test="${data[2] == '23528'}">七乐彩</c:when>
            	    <c:when test="${data[2] == '23529'}">超级大乐透</c:when>
            	    <c:when test="${data[2] == '33'}">排列三</c:when>
            	    </c:choose>
            	    </td>
            	    
            	    
            	    <td>${data[1]} </td>
            	    <td>${data[3]}</td>
            	    <td>${data[4]}</td>
            	    <td>
            	    <c:choose>
            	    <c:when test="${data[6] == '1'}">未出票</c:when>
            	    <c:when test="${data[6] == '2'}">出票中</c:when>
            	    <c:when test="${data[6] == '3'}">出票成功</c:when>
            	    <c:when test="${data[6] == '4'}">出票失败</c:when>
            	    </c:choose>
            	    </td>
            	    <td>${data[5]}元</td>
                    <td>
                   <c:if test="${data[8] != null}">
                    	${data[8]}元
                    </c:if>
                     <c:if test="${data[8] == null}">
                    	未中奖
                    </c:if>
                    </td>
          	    </tr>
            	 </c:forEach>
          	  </tbody></table>
            </dd>
        </dl>          
    </div>
    
    
              
        </div>

    <%@include file="left_menu.jsp"%>
    <span class="clr"></span>
    
    </div>
</form>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        $("#lottery").addClass("col_link");
    })
</script>
</body>
</html>