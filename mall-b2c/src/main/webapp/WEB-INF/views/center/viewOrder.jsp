<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <title>我购买的商品</title>
    <script type="text/javascript">
    var web_url = '${web_url}';
    $(document).ready(function(){
    	var url = '../bill/getWayBill.chtml';
    	var name = $.trim('${name }');
    	var no  = $.trim('${number }');
    	if(null!=name &&""!= name && null!=no && ""!=no){
    		$(".goods_info").show();
    		//不行虚拟产品，不是自提商品
    		if('${isXuNi}'!="1"){
    			$.ajax({
    			      url: url,
    			      dataType: 'text',
    			      data:"nu="+name+"&com="+encodeURI(no),
    			      cache: false,
    			      async: false,
    			      success:function(res){
    				  	  if(res){
    				  		 if(res.indexOf("没有查到相关信息")>0 || res.indexOf("公司参数不正确")>0){
    				  			 $("#track").append("未查询到相关信息，请前往物流官方网站查询。");
    				  		 }else{
    				  			 $("#track").append(res);
    				  		 }
    					  }else{
    						 $("#track").parent().hide();
    					  }
    			      },
    			      error:function(res){
    			    	  $("#track").append("未查询到相关信息，请前往物流官方网站查询。");
    			      }
    			 });
    		}else{
    			$("#track").append("未查询到相关信息，请前往物流官方网站查询。");
    		}
    	}else{
    		$(".goods_info").hide();
    	}
		 
	});
    </script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<input type="hidden" name="curpage" id="curpage" value=""/>
<input type="hidden" name="flag" id="flag" value="0"/>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我购买的商品</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">订单详情</a></li>
                        </ul>
                    </div>
                </div>
                <c:if test="${flag eq 1 }">
                <div class="content">
                <div class="order_full">
					<dl>
						<dt>订单编号：</dt><dd>${info.id }</dd>
					</dl>
                    <dl>
						<dt>订单类型：</dt><dd><c:if test="${info.payOnDelivery eq 1}">货到付款</c:if>
						<c:if test="${info.payOnDelivery ne 1}">${info.actType == 20 ?"短信购":"普通购买"}</c:if></dd>
					</dl>
                    <dl>	
                        <dt>配送方式：</dt><dd>${express }</dd>
					</dl>
                     <dl>
						<dt>订单金额：</dt><dd>${amount }</dd>
					</dl>
                     <dl>
						<dt>订单状态：</dt>
						<dd class="red">
							<c:if test="${info.payOnDelivery eq 0 }">
							<c:if test="${status eq 1}">待付款</c:if>
							<c:if test="${status eq 2}">已付款</c:if>
							<c:if test="${status eq 3}">待收货</c:if>
							<c:if test="${status eq 4}">已完成</c:if>
							<c:if test="${status eq 5}">已取消</c:if>
							<c:if test="${status eq 6}">支付中</c:if>
							</c:if>
							<c:if test="${info.payOnDelivery eq 1 }">
								<c:if test="${status ne 3 && status ne 4 && status ne 5}">未发货</c:if>
								<c:if test="${status eq 3}">已发货</c:if>
								<c:if test="${status eq 4}">已完成</c:if>
								<c:if test="${status eq 5}">已取消</c:if>
							</c:if>
							
						</dd>
					</dl>
                   	<c:if test="${status eq 1}">
                   		<dl><dt>下单时间：</dt><dd><ct:time source="${info.createTime }"/></dd></dl>
                   	</c:if>
					<c:if test="${status eq 2}">
						<dl><dt>下单时间：</dt><dd><ct:time source="${info.createTime }"/></dd></dl>
						<dl><dt>付款时间：</dt><dd><ct:time source="${info.payTime }"/></dd></dl>
					</c:if>
					<c:if test="${status eq 3}">
						<dl><dt>下单时间：</dt><dd><ct:time source="${info.createTime }"/></dd></dl>
						<dl><dt>付款时间：</dt><dd><ct:time source="${info.payTime }"/></dd></dl>
						<dl><dt>发货时间：</dt><dd><ct:time source="${info.expressInfo.sendTime }"/></dd></dl>
					</c:if>
					<c:if test="${status eq 4}">
						<dl><dt>下单时间：</dt><dd><ct:time source="${info.createTime }"/></dd></dl>
						<dl><dt>付款时间：</dt><dd><ct:time source="${info.payTime }"/></dd></dl>
						<dl><dt>发货时间：</dt><dd><ct:time source="${info.expressInfo.sendTime }"/></dd></dl>
						<dl><dt>收货时间：</dt><dd><ct:time source="${info.expressInfo.receiveTime }"/></dd></dl>
					</c:if>
					<c:if test="${status eq 5}">
						<dl><dt>下单时间：</dt><dd><ct:time source="${info.createTime }"/></dd></dl>
						<dl><dt>取消时间：</dt><dd><ct:time source="${info.closeTime }"/></dd></dl>
	                    <dl><dt>取消类型：</dt><dd>${info.closeDescription }</dd></dl>
					</c:if>
				</div>
				<div class="itemlist_info">
					<dl>
						<dt class="font_bold">
							商品清单
						</dt>
                    </dl>
                    <dl>
						<dt>商户：</dt><dd><a href="<ct:path flag='3' id='${info.shopId }'/>">${info.shopSubject }</a></dd>
					</dl>	
					
					  <div class="itemlist_code_table">
                        	<table border="0" width="100%" cellspacing="0" class="mauto" cellpadding="0">
                            	<tr>
                                	<td width="12%">商品编号</td>
                                    <td >商品名称</td>
                                    <td width="25%">商品属性</td>

                                    <td>数量</td>
                                </tr>
                                <c:forEach items="${goodsDetail}" var="data">
                                <tr class="itemlist_code_value">
                                	<td>${data.id}</td>
                                	<td >
                                	<c:if test="${info.orderType eq 1000 }">
                    				<a>${data.name} </a><br /></c:if>
                    				<c:if test="${info.orderType ne 1000 && info.orderType ne 0 }">
                    				<a href="<ct:path flag='1' id='${data.id }'/>" class="col_link" >${data.name } </a><br />
                    				</c:if>	
                    				<c:if test="${info.orderType eq 0 }">
                    				<a href="../quickphoto/item.chtml?item_id=${data.id }&verison_id=${data.versionId }" class="col_link" >${data.name } </a><br /></c:if>
                    				<div class="clear"></div></a></td>     
                                	<td style="text-align: left;"><c:forEach items="${data.parames}" var="item">
                    					${item.param_key}:
                    					<c:if test="${item.label !=null}">
                    					${item.label}
                    					</c:if>
                    					<c:if test="${item.label ==null}">
                    					${item.param_value}
                    					</c:if>
                    					<br/>
                    				</c:forEach></td>
                                	<td>${data.count}</td>
                                </tr>
                                </c:forEach>                              
                            </table>
					</div>
					<c:if test="${isXuNi eq 1 and !empty codes}">
					<dl>	
                    	<dt class="itemlist_code">验证码：</dt>
                        <dd class="itemlist_code_table">
                        	<table border="0" width="575" cellspacing="0" cellpadding="0">
                            	<tr>
                                	<td width="10%">序号</td>
                                	<c:if test="${info.orderType eq 20 }"><td width="20%">券号</td><td width="20%">密码</td></c:if>
                                	<c:if test="${info.orderType ne 20 }"><td width="40%">验证码</td></c:if>
                                    <td>状态</td>
                                    <td width="30%">有效期至</td>
                                </tr>
                                <c:forEach items="${codes}" var="data" varStatus="index">
                                <tr class="${index.count mod 2 eq 0 ? "itemlist_code_value2":"itemlist_code_value"}">
                                	<td >${index.count }</td>
                                	<c:if test="${info.orderType eq 20 }">
                                		<td >${data[1] }</td>
                                		<td >${data[3] }</td>
                                	</c:if>
                                    <c:if test="${info.orderType ne 20 }"><td >******</td></c:if>
                                    <td class="red">
                                    	<c:if test="${data[0] eq 0}">正常</c:if>
                                    	<c:if test="${data[0] eq 1}">已撤销</c:if>
                                    	<c:if test="${data[0] eq 2}">使用中</c:if>
                                    	<c:if test="${data[0] eq 3}">已使用</c:if>
                                    	<c:if test="${data[0] eq 4}">已过期</c:if>
                                    </td>
                                    <td><ct:time source="${data[2] }" sfmt="yyyyMMddhhmmss" tfmt="yyyy-MM-dd"/></td>
                                </tr>
                                </c:forEach>
                            </table>
                        </dd>
					</dl>
					</c:if>
				</div>
				<c:if test="${isXuNi ne 1}">
				<c:if test="${!empty info.expressInfo}">
				<div class="receive_info">
					<dl>
						<dt class="font_bold">收货人信息</dt>
                    </dl>
                    <dl>
						<dt>收货人姓名：</dt><dd>${info.expressInfo.receiverName }</dd>
					</dl>
                    <dl>
                    	<dt>地址：</dt><dd>${info.expressInfo.address }</dd>
					</dl>
                    <dl>
                    	<dt>邮编：</dt><dd>${info.expressInfo.zipCode }</dd>
					</dl>
                    <dl>
                    	<dt>固定电话：</dt><dd>${info.expressInfo.telephoneNumber }</dd>
					</dl>
                    <dl>
                    	<dt>手机号码：</dt><dd>${info.expressInfo.cellphoneNumber }</dd>
					</dl>
				</div>
				<c:if test="${(not empty info.expressInfo) && (isXuNi ne 1) && (info.payOnDelivery eq 0)}">
					<div class="goods_info" style="display:none;">
						<dl>
							<dt class="font_bold">物流信息</dt>
	                    </dl>
	                    <dl>    
							<dt>物流公司：</dt><dd>${info.expressInfo.expressCompanyName }</dd>
						</dl>
	                    <dl>
	                    	<dt>物流单号：</dt><dd>${info.expressInfo.expressNo }</dd>
						</dl>
						 <dl>
	                    	<dt>物流跟踪：</dt><dd id="track"></dd>
						</dl>
					</div>
				</c:if>
				</c:if>
				</c:if>
				<c:if test="${info.invoiceType != 0}">
				<div class="invoice_info">
					<dl>
						<dt class="font_bold">发票信息</dt>
                    </dl>
                    <dl>    
						<dt>发票类型：</dt><dd><c:if test="${info.invoiceType eq 1}" var="f">普通发票</c:if><c:if test="${!f }">增值税发票</c:if></dd>
					</dl>
					<dl>    
						<dt>发票抬头：</dt><dd>${info.invoiceSubject }</dd>
					</dl>
					<dl>    
						<dt>发票内容：</dt><dd>${info.invoiceContent }</dd>
					</dl>
				</div>
				</c:if>
				<div class="total_price">
					<dl>
					    <dt class="font_bold">支付信息</dt>
	                </dl>
	                <c:if test="${ !empty payOrderInfos}">
	                <dl>
	                <dt class="itemlist_code">支付信息：</dt>
	                <dd class="itemlist_code_table">
                        	<table border="0" width="575" cellspacing="0" cellpadding="0">
                            	<tr>
                                    <td width="20%">业务动作</td>
                                    <td width="20%">支付渠道</td>
                                    <td>状态</td>
                                    <td>金额</td>
                                    <td width="30%">操作时间</td>
                                </tr>
                                <c:forEach items="${payOrderInfos}" var="data" varStatus="index">
                                <c:if test="${data.status eq 2 }">
                                <tr class="${index.count mod 2 eq 0 ? "itemlist_code_value2":"itemlist_code_value"}">
                                	<c:if test="${data.operate eq 'Pay' }">
                                	 <td >支付</td>
                                	</c:if>
                                   <c:if test="${data.operate eq 'Refund' }">
                                	 <td >退款</td>
                                	</c:if>
                                	<c:if test="${data.operate eq 'Other' }">
                                	 <td >其他</td>
                                	</c:if>
                                	 <c:if test="${data.payChannel eq 'unify.balance' }">
                                	 <td>余额支付</td>
                                	</c:if>
                                	<c:if test="${data.payChannel eq 'unify.cart.apply.alipay' }">
                                	 <td>支付宝支付</td>
                                	</c:if>
                                	<c:if test="${data.payChannel eq 'unify.cart.apply.cmpay' }">
                                	 <td>手机支付</td>
                                	</c:if>
                                	<c:if test="${data.payChannel eq 'unify.cart.apply.score' || data.payChannel eq 'unify.cart.score' }">
                                	 <td>积分支付</td>
                                	</c:if>
                                	 <c:if test="${data.payChannel eq 'unify.cart.coin' }">
                                	 <td>商城币支付</td>
                                	</c:if>
                                	 <c:if test="${data.payChannel eq 'unify.wap.alipay' }">
                                	 <td>wap支付宝支付</td>
                                	</c:if>
                                	 <c:if test="${data.payChannel eq 'ems.pay_on_delivery' }">
                                	 <td>货到付款</td>
                                	</c:if>
                                	 <c:if test="${data.payChannel eq 'red.envelopes.pay' }">
                                	 <td>红包支付</td>
                                	</c:if>
                                    
                                    <c:if test="${data.status eq -1 }">
                                	 <td class="red">
                                    	异常
                                    </td>
                                	</c:if>
                                	<c:if test="${data.status eq 0 ||  data.status eq 1}">
                                	 <td class="red">
                                    	执行中
                                    </td>
                                	</c:if>
                                    <c:if test="${data.status eq 2 }">
                                	 <td class="red">
	                                  	  成功
                                    </td>
                                	</c:if>
                                	 <c:if test="${data.status eq 3 }">
                                	 <td class="red">
	                                  	  失败
                                    </td>
                                	</c:if>
                                    <td>${data.paymentAmount/100 }元</td>
                                    <td><ct:time source="${data.createTime }" sfmt="yyyyMMddhhmmss" tfmt="yyyy-MM-dd"/></td>
                                </tr>
                                </c:if>
                                </c:forEach>
                            </table>
                        </dd>
                        </dl>
	                </c:if>
	                <%--<c:forEach items="${info.paymentInfos}" var="data"> 
	                	<c:if test="${data.currency eq 'coin'}">
	                		<dl>
	                  			<dt>商城币支付：</dt>
	                  			<dd>
	                  				<label class="order_scb col_link"><fmt:formatNumber type="number" value="${data.amount/100 }" maxFractionDigits="2"/></label>
				      				<c:if test="${data.amount == payCoin}" var="flag">
				      				<a class="red"><strong>&nbsp;&nbsp;&nbsp;[已支付]</strong></a>
				      				</c:if>
				      				<c:if test="${!flag}">
				      				&nbsp;&nbsp;&nbsp;[未支付]
				      				</c:if>
	                  			</dd>
	                  		</dl>
	                  	</c:if>
	                  	<c:if test="${data.currency eq 'cash'}">
			    			<dl>
	                  			<dt>现金支付：</dt>
	                  			<dd>
	                  				<label class="order_scb col_link"><fmt:formatNumber type="number" value="${data.amount/100 }" maxFractionDigits="2"/>元</label>
				      				<c:if test="${data.amount == payCash}" var="flag">
				      				<a class="red"><strong>&nbsp;&nbsp;&nbsp;[已支付]</strong></a>
				      				</c:if>
				      				<c:if test="${!flag}">
				      				&nbsp;&nbsp;&nbsp;[未支付]
				      				</c:if>
	                  			</dd>
	                  		</dl>
			    		</c:if>
			    		<c:if test="${data.currency eq 'score'}">
			    			<dl>
	                  			<dt>积分支付：</dt>
	                  			<dd>
	                  				<label class="order_scb col_link"><fmt:formatNumber type="number" value="${data.amount/100 }" maxFractionDigits="2"/>元</label>
				      				<c:if test="${data.amount == payScore}" var="flag">
				      				<a class="red"><strong>&nbsp;&nbsp;&nbsp;[已支付]</strong></a>
				      				</c:if>
				      				<c:if test="${!flag}">
				      				&nbsp;&nbsp;&nbsp;[未支付]
				      				</c:if>
	                  			</dd>
	                  		</dl>
			    		</c:if>
			    		<c:if test="${data.currency eq 'balance'}">
			    			<dl>
	                  			<dt>手机话费支付：</dt>
	                  			<dd>
	                  				<label class="order_scb col_link"><fmt:formatNumber type="number" value="${data.amount/100 }" maxFractionDigits="2"/>元</label>
				      				<c:if test="${data.amount == payBalance}" var="flag">
				      				<a class="red"><strong>&nbsp;&nbsp;&nbsp;[已支付]</strong></a>
				      				</c:if>
				      				<c:if test="${!flag}">
				      				&nbsp;&nbsp;&nbsp;[未支付]
				      				</c:if>
	                  			</dd>
	                  		</dl>
			    		</c:if>
	                  </c:forEach>
				--%></div>
			</div>
            </c:if>
            <c:if test="${flag eq 0 }">
                <div class="content">
                <div class="order_full" style="height:100px;">
					<span>你无权查看该订单！谢谢</span>
				</div>
				</div>
			</c:if>
            </div>
        </div>
    </div>
    <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
    	$("#orderManager").addClass("col_link");
    });
</script>
</body>
</html>