<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/if.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<meta http-equiv="cache-control" content="no-cache, must-revalidate"> 
	</head>

<body>
	<div class="row-fluid">
		<div class="span12">
			 <form:form method="post" id="fm" commandName="post" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate form-horizontal">
                        <div class="row-fluid">
                <div class="control-group group-search">
                	  <label class="control-label req" for="percent">推荐引擎： <input name="percent" value="groupBuy" id="groupBuy" type="checkbox" 
                      	  		<c:if test="${not empty groupBuy && groupBuy == 'groupBuy'}"> checked=checked </c:if> />团购
                      	  <input name="percent" value="cashVoucher" id="cashVoucher"  type="checkbox" <c:if test="${not empty cashVoucher && cashVoucher == 'cashVoucher'}">checked=checked</c:if> />代金券
        				  <input name="percent" value="coupon" id="coupon" type="checkbox" <c:if test="${not empty coupon && coupon == 'coupon'}">checked=checked</c:if> />优惠券
        				  <input name="percent" value="shop" id="shop" type="checkbox" <c:if test="${not empty shop && shop == 'shop'}"> checked=checked </c:if> />商户</label>
                      <div class="controls controls-row">
                      	 
                      </div>
                    <label class="control-label req"  for="post_name">营销位名称： <form:input path="post_name" id="post_name" maxlength="20" /></label>
                    <div class="controls controls-row">
                       
                    </div>
                    <label id="repeat-error" style="margin-left: 130px"> <span id="repeat-error2" style="color: #FFFFFF;"></span>
                    </label> <label class="control-label" for="post_desc">营销位描述：<form:textarea path="post_desc" /></label>
                    <div class="controls controls-row">
                        
                    </div>
                    <label class="control-label req" for="content_num">推荐营销位条数：<form:input path="content_num" id="content_num" maxlength="20" /></label>
                    <div class="controls controls-row">
                    	
                    </div>
                    <label class="control-label req" for="limit_site">限制种子网站：<form:select path="limit_site" items="${siteMap}" ></form:select></label>
                    </div>
                	<div>
							<form:checkbox path="audit_flag" id="audit_flag" value="1" />是否只推荐人工审核产品
							<form:checkbox path="filter_flag" id="filter_flag" value="1"/>是否过滤已推荐的物品
                	</div>
                </div>
				<form:hidden path="channel_id"/>
                <div class="control-group group-search" align="center">
                    <div class="controls controls-row">
                        <button type="button" class="btn btn-primary" id="sub" onclick="onSubmit();">
                            <i class="icon16 i-disk"></i> 保 存
                        </button>
                      
                        <button class="btn" type="button" onclick="doShut();">
                            <i class="icon16 i-exit"></i> 返 回
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
		</div>
	</div>
</body>
</html>
