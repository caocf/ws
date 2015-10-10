<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
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
		<script type="text/javascript" src="../js/date-picker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/usercenter.js"></script>
		<script type="text/javascript" src="../js/ad.js"></script>
		<script type="text/javascript" src="../js/global.js"></script>
		<script type="text/javascript" src="../js/base.js"></script>
		<script type="text/javascript" src="../js/pagescroll.js"></script>
		<script type="text/javascript" src="../js/jquery.cookie.js"></script>
		<title>我的代金券</title>
		<script>
		var web_url = '我的订单';
        var webRoot = '${webRoot}';
        function goUnions(url,name){
            window.location.href="../center/unions.chtml?url="+encodeURIComponent(name+","+url);
        }
    </script>
	</head>

	<body id="usercenter">
		<script type="text/javascript" src="../js/head.js"></script>
		<form action="goodsOrderList.chtml" method="post" id="form1">
			<input type="hidden" name="curpage" id="curpage" value="" />
			<input type="hidden" name="flag" id="flag" value="${flag }" />
			<input type="hidden" name="orderId" id="orderId" value="" />

			<div class="wrapper mauto">
				<div class="breadcrumb">
					<strong><a href="../">首页</a> </strong><span>&nbsp;&gt;&nbsp;<a
						href="./orderManager.chtml">个人中心</a> </span><span>&nbsp;&gt;&nbsp;我的代金券</span>
				</div>
				<div id="main" class="fr  w750">
					<div class="detail">
						<div class="detail">
							<div class="tab">
								<div class="tab_nav">
									<ul class="tab">
										<li class="curr">
											<a href="#info1">我的代金券(默认显示您最近一个月的订单)</a><span class="des"> 使用时间筛选可以查看更多历史订单</span>
										</li>
									</ul>
								</div>
							</div>
							<div class="order_search">
								<div class="ordersearch_wrap">
									<label>
										关键词：
									</label>
									<input type="text" name="key" value="${param.key }" />
									<label>
										时间：
									</label>
									<input type="text" id="inputStartTime" name="inputStartTime"
										class="txt Wdate" value="${startTime}" readOnly
										onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
									<input type="hidden" name="startTime" id="startTime"
										value="${param.startTime}" />
									-
									<input type="text" id="inputEndTime" name="inputEndTime"
										class="txt Wdate" value="${endTime}" readOnly
										onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
									<input type="hidden" name="endTime" id="endTime"
										value="${param.endTime}" />
									<select class="order_select" name="source"
										onchange="$('#form1').submit();">
										<option value="0" ${source eq '0'?"selected":""}>
											全部来源
										</option>
										<option value="1" ${source eq '1'?"selected":""}>
											新商城
										</option>
										<option value="2" ${source eq '2'?"selected":""}>
											商盟
										</option>
										<option value="3" ${source eq '3'?"selected":""}>
											联动
										</option>
									</select>
									<input type="button" class="btn_search"
										onclick="$('#form1').submit();" value="搜索" />
								</div>
							</div>
							<div class="order_content">
								<div class="rate_comments">
									<div class="order_itemsnav">
										<ul>
											<li class="order_name">
												商品
											</li>
											<li class="order_price">
												订单金额（单位：元）
											</li>
											<li class="order_state">
												状态
											</li>
											<li class="order_operate">
												操作
											</li>
										</ul>
									</div>
									<div class="order_items">
										<c:choose>

											<c:when test="${not empty list}">

												<c:forEach items="${list}" var="data">
												   <c:if test="${!fn:contains(data.goodsName,'汽车票')}">
													<div class="order_item">
														<div class="alert_windows" id="reback">
															<a class="close" id="close" href="javascript:void(0);"
																onclick="undo();"></a>
															<div class="content">

																<p>
																	我们想知道您为何取消此订单，以便改进我们的服务：
																</p>
																<p>
																	<select id="selectId" onchange=displayTextare();>
																		<option onfocus="" class="col999">
																			请选择你的理由
																		</option>
																		<option value="现在不想买了">
																			现在不想买了
																		</option>
																		<option value="支付不成功">
																			支付不成功
																		</option>
																		<option value="商品价格较贵">
																			商品价格较贵
																		</option>
																		<option value="等待时间过长">
																			等待时间过长
																		</option>
																		<option value="有商品缺货">
																			有商品缺货
																		</option>
																		<option value="更换或添加新商品">
																			更换或添加新商品
																		</option>
																		<option value="错误或重复下单">
																			错误或重复下单
																		</option>
																		<option value="其他原因">
																			其他原因
																		</option>
																	</select>
																</p>
																<p class="bodbccc" style="display: none;">
																	<textarea id="textareaId" name="textareaId"
																		onkeyup="subContent(this)"
																		onkeydown="subContent(this);"
																		onfocus="if(value=='请在此处填写您的原因'){value=''}"
																		onblur="if(value==''){value='请在此处填写您的原因'}"
																		title="50字以内">请在此处填写您的原因</textarea>
																	<br />
																	<font color="red" size="2">内容在50字以内；</font>
																</p>
																<%--<p><span>本单应支付 <strong id='total'></strong><span id='coin'></span><br /></span></p>
                                        --%>
																<a class="btn" href="javascript:void(0);"
																	onclick="cancelOrder();">确定</a>
																<a class="btn goback" href="javascript:void(0);"
																	onclick="undo();">取消</a>
															</div>
														</div>
	                                     
														<div class="order_item_num">
															<ul>
																<li class="item_number">
																	订单号：${data.id }
																	<c:if test="${data.payOnDelivery eq 1 }">
																		<p class="item_hdfk"></p>
																	</c:if>
																</li>
																<li class="item_buydate" style="width: 280px;">
																	购买时间：
																	<ct:time source="${data.createTime}"  sfmt="yyyyMMddhhmmss" tfmt="yyyy-MM-dd HH:mm"/>
																</li>
															</ul>
														</div>
												
														<div class="order_item_detail">
															<ul>
																<c:if test="${data.source ne 1 and  data.source ne 2}">
																	<li class="item_img">
																		<c:forEach items="${data.goodsInfos}" var="good">
																			<a href="<ct:path flag='1' id='${good.goodsId }'/>">
																				<img class="item_image" width="50" height="50"
																					src="<ct:path flag='2' id='${good.goodsId }'/>N5/${good.fileName }" />
																			</a>
																			<div class="item_img_djq" >
																				<p>
																					${good.goodsSubject }
																				</p>
																			</div>
																		</c:forEach>
																	</li>
																	<li class="item_price">
																		${data.payAmount }
																		<c:if
																			test="${data.status eq 1 && (!empty data.payCoin || !empty data.payCash)}">
																			<a href="" class="tip tip_scb"> </a>
																			<div class="item_price_decp"
																				style="display: none; background: #FFFF99">
																				<p>
																					已支付
																					<c:if test="${!empty data.payCoin}">${data.payCoin }商城币</c:if>
																					<c:if test="${!empty data.payCash}">${data.payCash }现金</c:if>
																				</p>
																			</div>
																		</c:if>
																	</li>
																	<li class="item_state">
																		<c:if test="${data.payOnDelivery eq 0 }">
																			<c:if test="${data.status eq 1}"><p class="status_nopay">待付款</p></c:if>
																			<c:if test="${data.status eq 2}">已付款</c:if>
																			<c:if test="${data.status eq 3}">待收货</c:if>
																			<c:if test="${data.status eq 4}">已完成</c:if>
																			<c:if test="${data.status eq 5}"><p class="status_cancel">已取消</p></c:if>
																		</c:if>
																		<c:if test="${data.payOnDelivery eq 1 }">
																			<c:if
																				test="${data.status ne 3 && data.status ne 4 && data.status ne 5}">未发货</c:if>
																			<c:if test="${data.status eq 3}">待收货</c:if>
																			<c:if test="${data.status eq 4}">已完成</c:if>
																			<c:if test="${data.status eq 5}"><p class="status_cancel">已取消</p></c:if>
																		</c:if>
																	</li>
																	<li class="item_operate">
																		<a href="orderView.chtml?orderId=${data.id }" class="col_link">查看</a>
																		<c:if test="${data.payOnDelivery eq 0 }">
																			<c:if test="${data.status eq 1}">
																				<a
																					href="../order/payment-confirm.chtml?id=${data.id }">付款</a>&nbsp;<a
																					class="remove" href="javascript:void(0);"
																					onclick="getOrderId(${data.id });">取消</a>
																			</c:if>
																			<c:if test="${data.status eq 3}">
																				<a href="delivery.chtml?orderId=${data.id }">确认收货</a>
																			</c:if>
																			<c:if
																				test="${data.status eq 4 && data.isComment eq '0'}">
																				<a
																					href="javascript:comment('${data.id }','1','${data.shopId }');">评价</a>
																			</c:if>
																			<c:if test="${data.status eq 5}">
																				<a href="delOrder.chtml?orderId=${data.id }"
																					class="deleteOrder">删除</a>
																			</c:if>
																			<c:if test="${data.isRefund eq 1}">
																				<a href="refundList.chtml?orderId=${data.id }">退款单</a>
																			</c:if>
																			<c:if
																				test="${data.status eq 2 || data.status eq 3 || data.status eq 4}">
																				<div class="time_cdowb" style="margin-top: -42px">
																					<a  class="col_link" style="cursor: pointer;"
																						onclick="weiboShare('${data.id}','${data.goodsInfos[0].goodsSubject}','我购买的商品','${data.payTime }')">微博晒单</a>
																				</div>
																			</c:if>
																		</c:if>
																		<c:if test="${data.payOnDelivery eq 1 }">
																			<c:if
																				test="${data.status ne 3 && data.status ne 4 && data.status ne 5}">
																				<a class="remove" href="javascript:void(0);"
																					onclick="getOrderId(${data.id });">取消</a>
																			</c:if>
																			<c:if
																				test="${data.status eq 4 && data.isComment eq '0'}">
																				<a
																					href="javascript:comment('${data.id }','1','${data.shopId }');">评价</a>
																			</c:if>
																			<c:if test="${data.status eq 5}">
																				<a href="delOrder.chtml?orderId=${data.id }"
																					class="deleteOrder">删除</a>
																			</c:if>
																			<c:if test="${data.status eq 3 || data.status eq 4}">
																				<div class="time_cdowb" style="margin-top: -42px">
																					<a class="col_link" style="cursor: pointer;" 
																						onclick="weiboShare('${data.id}','${data.goodsInfos[0].goodsSubject}','我购买的商品','${data.payTime }')">微博晒单</a>
																				</div>
																			</c:if>
																		</c:if>
																	</li>

																</c:if>

																<c:if test="${data.source eq 1}">
																	<li class="item_img">
															
																			<a
																				href="http://quan.12580life.com/product/i${data.goodsInfos[0].goodsId }.htm"
																				target="_blank"> <img class="item_image"
																					width="50" height="50"
																					src="http://quan.12580life.com/shlm/upload/o2o/${data.goodsInfos[0].fileName }" />
																			</a>

																	<div class="item_img_djq" >
																			<p>
																				${data.goodsName }
																			</p>
																		</div>
																	</li>
																	<li class="item_price">
																		${data.payAmount }
																	</li>
																	<li class="item_state" >
																		<%--订单状态0--未付款 1--已付款 2--失效  3--退款处理中  4--退款成功 5--支付超时--%>
																		<c:if test="${data.status eq 0}"><p class="status_nopay">待付款</P></c:if>
																		<c:if test="${data.status eq 1}">已付款</c:if>
																		<c:if test="${data.status eq 2}"><p class="status_cancel">失效</p></c:if>
																		<c:if test="${data.status eq 3}">退款处理中</c:if>
																		<c:if test="${data.status eq 4}">退款成功</c:if>
																		<c:if test="${data.status eq 5}">支付超时</c:if>
																	</li>
																	<li class="item_operate">
																		<c:if test="${data.status eq 0}">
																			<button
																				onclick="window.open('http://e.12580life.com/order-center/ordercenter/pay.php?orderId=${data.id}')">
																				付款
																			</button>
																		</c:if>
																		<c:if test="${data.status eq 1}">
																	
																				<span> <a href="javascript:void(0)" class="col_link"
																					onclick="sendSMS('${data.subject}','${data.remark }','${data.goodsInfos[0].goodsId }');">短信补发</a>
																				</span>
																
																			<span><a href="javascript:void(0)" class="col_link"
																				onclick="weiboShare('${data.id}','${data.goodsName }','${goodsType }','${data.createTime }');">微博晒单</a>
																			</span>
																			<span>
																			<a href="javascript:void(0)"  class="col_link" onclick="openDetail('${data.subject}','${data.goodsInfos[0].goodsId }','${data.goodsName }');">验证状态</a>
																			</span>
																		</c:if>
																	</li>
																</c:if>
																<c:if test="${data.source eq 2}">
																	<li
																		style="line-height: 60px; text-align: left; width: 300px;">
																		<div class="item_img_djq" >
																		<p>
																			${data.goodsName }
																		</p>
																		</div>
																	</li>
																	<li class="item_price">
																		${data.payAmount}
																	</li>
																	<%---99：初始状态 0：初始状态 2：支付成功 4：支付失败 6：退款 7：支付超时--%>
																	<li class="item_state">
																		<c:if test="${data.status eq -99}">初始状态</c:if>
																		<c:if test="${data.status eq 0}">初始状态</c:if>
																		<c:if test="${data.status eq 2}">支付成功</c:if>
																		<c:if test="${data.status eq 4}">支付失败</c:if>
																		<c:if test="${data.status eq 6}">退款</c:if>
																		<c:if test="${data.status eq 7}">支付超时</c:if>
																	</li>
																	
																</c:if>


															</ul>
														</div>
													</div>
												</c:if>
												</c:forEach>
												
											</c:when>

											<c:otherwise>
												<p
													style="color: #E95098; font-weight: bold; height: 100px; line-height: 100px; text-align: center;">
													暂无订单!
												</p>
											</c:otherwise>
										</c:choose>

									</div>
									
								</div>
							</div>
							<c:if test="${!empty list}"><div class="b2c_total_area">共<span>${size }</span>条</div></c:if>
							
						</div>
					</div>
				</div>
				<%@include file="left_menu.jsp"%>
				<span class="clr"></span>
			</div>
		</form>
		<script type="text/javascript" src="../js/foot.js"></script>
		<script>
    $(function(){
        $("#goodsOrderList").addClass("col_link");
        
        jQuery.ajax({
           url:webRoot + 'recommend/zxdj.chtml',
           dataType: "json",
           cache:false,
           success:function(data){
              if(jQuery.isArray(data) && data.length > 0){
                 var tujian = "<div class='tijian'><div class='nav_kt nav_kt_748'><span><em><a href='http://quan.12580life.com/"+jQuery("#mosh_12580_header").attr("spell")+"' target='_blank'>更多代金券</a></em>今日代金券推荐</span></div>";
                 tujian += "<div class='content_a' attr='cnt' style='display:block;'><div id='slider' class='slider'><ul id='slides' class='clearfix'>";
                 for (var i = 0; i < data.length; i++){
                     tujian += "<li><a href='http://quan.12580life.com/product/i"+ data[i].ID +".htm?referer="+data[i].REFERER+"' title='"+ data[i].GOOD_TITLE +"' target='_blank'>";
                     tujian += "<img src='http://quan.12580life.com/shlm/upload/o2o" + data[i].GOODS_PIC_PATH + "'>";
                     tujian += "<em>" + data[i].GOOD_TITLE + "<span><b class='price'>￥" + data[i].SALES_PRICE + "</b></span></em></a></li>";
                 }
                 tujian += "</ul></div></div></div>";
                 var totalArea = jQuery(".b2c_total_area");
                 if (typeof(totalArea) != 'undefined'){
                    jQuery(tujian).insertAfter(".b2c_total_area");
                 }else{
                    jQuery(tujian).insertAfter(".order_content");
                 }
              }
           }
        });
    })	
		function sendSMS(order_id,mobile,good_id){
		jQuery.ajax({
			url:webRoot+'center/sendSMSCode.chtml',
			method:'POST',
			data:{'id':good_id,'orderId':order_id,'mobile':mobile},
			cache:false,
			success:function(data){
			alert(jQuery.trim(data.replace(/\r|\n/ig,"")));
			}
		});
	}
	
	function openDetail(orderId,goodId,goodName){
	   goodName =encodeURI(goodName);
	     goodName =encodeURI(goodName);
	   window.showModalDialog(webRoot+"center/verifyCodeDetail.chtml?orderId="+orderId+"&goodId="+goodId+"&goodName="+goodName,window,"dialogWidth:800px;status:no;dialogHeight:350px;help:no");
	}
</script>
	</body>
</html>