<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/importer.jsp"%>

<!doctype html>
<html>

<head>
	<link href="${ctx}/static/css/order_style.css" rel="stylesheet" type="text/css" />
    <ht:head/>
 
</head>
<body>
<div id="content">
	<div class="r-page">
	
		
    	<div class="model-block">
        	<div class="block-head">
            	<h4>短信购订单查看</h4>
            </div>
            <div class="block-body">
            	<div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">订单编号：</div>
                        <div class="info-list-desc">${order.id }</div>
                    </div>
                </div>
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">活动编号：</div>
                        <div class="info-list-desc">${order.actId}</div>
                    </div>
                </div>
               
         
                
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">活动名称：</div>
                         <div class="info-list-desc">${order.actName }</div>
                    </div>
                </div>
                
                
                <c:if test="${ItmeInfos !=null}">
                <c:forEach items="${ItmeInfos}" var="item">
                    <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">商品编号：</div>
                         <div class="info-list-desc">${order.id}</div>
                    </div>
                </div>
                
                
                  <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">购买数量：</div>
                         <div class="info-list-desc">${item.count}</div>
                    </div>
                </div>
                
                  <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">商品单价：</div>
                         <div class="info-list-desc">${item.price}</div>
                    </div>
                </div>
                </c:forEach>
               </c:if>
                      <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">支付状态：</div>
                         <div class="info-list-desc">${order.payStatusName}</div>
                    </div>
                </div>
               
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">下单时间：</div>
                        <div class="info-list-desc">
	                        <c:if test="${empty order.createTime}"></c:if>
							<c:if test="${!empty order.createTime}">
								<fmt:parseDate value="${order.createTime}" pattern="yyyyMMddHHmmss" var="test"/>
								<fmt:formatDate value="${test}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</c:if>
                        </div>
                    </div>
                </div>
                
                   <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">支付时间：</div>
                        <div class="info-list-desc">
	                        <c:if test="${empty order.payTime}"></c:if>
							<c:if test="${!empty order.payTime}">
								<fmt:parseDate value="${order.payTime}" pattern="yyyyMMddHHmmss" var="test"/>
								<fmt:formatDate value="${test}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</c:if>
                        </div>
                    </div>
                </div>
                
                
                <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">手机号码：</div>
                        <div class="info-list-desc">
                        ${order.terminalId}
                        </div>
                    </div>
                </div>
                
                  <div class="info-list">
                    <div class="info-list-widght">
                        <div class="info-list-title">支付方式：</div>
                        <div class="info-list-desc">
                        <c:if test="${order.currency =='cash'}">
									现金
									</c:if>
										<c:if test="${order.currency == 'coin'}">
									商城币
									</c:if>
										<c:if test="${order.currency == 'score'}">
									积分
									</c:if>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>

        <div class="form">
            <div class="fields">
                <div class="buttons" align="center">
                    <a href="javascript:history.back();" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>


</body>
</html>