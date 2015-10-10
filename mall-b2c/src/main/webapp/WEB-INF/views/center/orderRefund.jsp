<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
     <link href="../css/main.css" rel="stylesheet" type="text/css" />
     <script type="text/javascript" src="../js/date-picker/WdatePicker.js" ></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
     <script type="text/javascript" src="../js/wp-scroll.js"></script>
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/area_data.chtml"></script>
    <script type="text/javascript" src="../js/area.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script>
    	var web_url = '我的退款';
    	var webRoot = '';
    </script>
    <title>我的退款</title>
    <style>
	#usercenter .tk_goods_info { margin-bottom:10px;}
    #usercenter .tk_goods_info dl{ padding-left:100px; position:relative; line-height:30px; }
    	#usercenter .tk_goods_info dt{ position:absolute;top:0; left:0; color:#999;
		}
		#usercenter .tk_goods_info dd table { border-right:1px #ddd solid; border-left:1px #ddd solid; line-height:24px;	 }
		#usercenter .tk_goods_info dd table th,.tk_goods_info dd table td{border-top:1px #ddd solid;border-bottom:1px #ddd solid;  color:#666;}
		#usercenter .tk_goods_info dd table td{ background:#FFF;}
		#usercenter .disinput{ border:1px solid #ddd; padding:0 5px; font-size:16px; width:80px; text-align:right; background:#eee; height:24px; }
		#usercenter #table_tk{border: 1px solid #DEDEDE;}
		#usercenter #table_tk td,#table_tk th{ border-bottom: 1px solid #DEDEDE;padding: 5px;}
		#usercenter #table_tk th{background:#F3F3F3;}
		#usercenter #table_tk td{ color:#333; border-top:1px solid #eee;}
		#usercenter #table_tk td.colF30{ color:#f30;}
		#usercenter #tk_search input { line-height:24px; height:24px;}
		
		#usercenter #tk_search select{ padding:3px 0; height:24px; display:inline}
		#usercenter #tk_search .Wdate{ width:200px;}
		.sheng{white-space :nowrap;overflow : hidden;text-overflow : ellipsis;}
    </style>
</head>
<body id="usercenter">
<script type="text/javascript" src="http://mall.12580life.com/js/head.js"></script>
<form action="refundList.chtml"  id="form1">
<div class="wrapper mauto" >
   
     <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的退款</span></div>
    <div id="main" class="fr  w750">
 		<div class="detail">
                    <div class="tab">
                        <div class="tab_nav">
                            <ul class="tab">
                                <li class="curr"><a href="#info1">我的退款</a><span class="des">
                            	</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="order_search">
                        <div class="ordersearch_wrap" id="tk_search">
                            <label class="mb10" >订单编号：</label>
                            <input class="mb10 mr20"  type="text" value="${param.orderId}" name="orderId">
                            <label class="mb10" >时间：</label>
                            <input class="mb10 txt Wdate" type="text" onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" 
                            readonly="" value="${param.startTime}" name="startTime" id="inputStartTime">
                            -
                            <input type="text" onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" 
                            readonly="" value="${param.startTime}" class="txt Wdate mb10 mr20" name="endTime" id="inputEndTime">

							<label>退款单编号：</label><input class="mr20" type="text" value="${param.refundId}" name="refundId">


							<label>退款单状态：</label>
                            <select  name="status" class="order_select" id="status">
								<option value="" selected="">全部</option>
								<option value="1">等待商户确认</option>
								<option value="2">等待审核</option>
								<option value="3">商户拒绝确认</option>
								<option value="4">等待系统退款</option>
								<option value="5">审核失败</option>
								<option value="6">退款成功</option>
								<option value="7">退款失败</option>
								<option value="8">退款已通知</option>
								</select>                          
                          <script type="text/javascript">
                		var status='${param.status}';
                		if(status!=null)
                			$("#status").val(status);
                	</script>
                            <input type="button" value="搜索" onclick="$('#form1').submit();" class="btn_search">
                        </div>
                    </div>
                    
                    <div class="tab mt10">
                        <div class="tab_nav">
                            <ul class="tab">
                                <li class="curr"><a href="#info1">退款记录</a><span class="des">
                            	</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="order_search">
                        <div>
                        <c:if test="${not empty pageData}">
                            <table id="table_tk" width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <th class="col666" scope="col">订单编号</th>
                                    <th class="col666" scope="col">退款单编号</th>
                                    <th class="col666" scope="col">商户</th>                                    
                                    <th class="col666" scope="col">申请退款金额</th>
                                    <th class="col666" scope="col">申请时间</th>
                                    <th class="col666" scope="col">退款状态</th>
                                    <th class="col666" scope="col">操作</th>
                                </tr>
                                  <c:forEach items="${pageData.data}" var="item" >
                                <tr>
                                    <td>${item.orderId}</td>
                                    <td>${item.refundId}</td>
                                    <td title="${item.shop_subject}" >${item.shop_subject}</td>
                                   
                                    <td class="colF30">${item.refund_fee}</td>
                                    <td><ct:time source="${item.create_time}" tfmt="yyyy-MM-dd HH:mm" sfmt="yyyyMMddhhmmss"/></td>
                                    <td>
                                    <c:if test="${item.status==1}">
                                   		等待商户确认
                                    </c:if>
                                    <c:if test="${item.status==2}">
                                	   等待审核
                                    </c:if>
                                    <c:if test="${item.status==3}">
                              	              商户拒绝确认
                                    </c:if>
                                    <c:if test="${item.status==4}">
                                	   等待系统退款
                                    </c:if>
                                    <c:if test="${item.status==5}">
                                   	审核失败
                                    </c:if>
                                    <c:if test="${item.status == 6}">
                                 	  退款成功
                                    </c:if>
                                     <c:if test="${item.status==7}">
                                   	退款失败
                                    </c:if>
                                     <c:if test="${item.status==8}">
                                 	 退款已通知
                                    </c:if>
                                     <c:if test="${item.status==9}">
                                 	 系统退款中
                                    </c:if>
                                    
                                    </td>
                                    <td><input class="btn" type="button" value="查看" onclick="javascript:window.location.href='refund.chtml?orderId=${item.orderId}&refundId=${item.refundId}'"/></td>
                                </tr>
                              </c:forEach>
							</table>
							 <ht:page pageData="${pageData}" />
							</c:if>
							<c:if test="${empty pageData.data}">
							<div class="note">
								<p class="i">目前没有相关记录!</p>
							</div>
						</c:if>
                        	</div>
                   		</div>
                       
                   </div>
    </div>
 
 <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
    </form>
<script type="text/javascript" src="http://mall.12580life.com/js/foot.js"></script>
<script>
    $(function(){
        $("#myrefund").addClass("col_link");
    })
</script>
</body>
</html>