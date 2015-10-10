<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
</head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>查看第三方应用</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="openCustomer" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
	 <form:hidden path="appId" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">应用ID：</div>
                    <div class="input">${openCustomer.appId}</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="appKey" >应用KEY：</label>
                    </div>
                    <div class="input">${openCustomer.appKey }</div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="name" >应用名称：</label>
                    </div>
                    <div class="input">${openCustomer.name }</div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="remark" >应用详情：</label>
                    </div>
                    <div class="input">${openCustomer.remark }</div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="ips" >应用ip：</label>
                    </div>
                    <div class="input">${openCustomer.ips }</div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="status" >应用状态：</label>
                    </div>
                    <div class="input">${openCustomer.status }</div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="payNotify" >支付通知接口：</label>
                    </div>
                    <div class="input">${openCustomer.payNotify }</div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="payNotify" >应用类型：</label>
                    </div>
                    <div class="input">${openCustomer.appTypeName }</div>
                </div>
                <div class="field" <c:if test="${1 == openCustomer.appType}">style="display:none"</c:if>>
                    <div class="label">
                        <label for="payNotify" >商户类型：</label>
                    </div>
                    <div class="input">${openCustomer.shopClassName }</div>
                </div>
                <div class="field" <c:if test="${empty openCustomer.shopClass || 1==openCustomer.shopClass}">style="display:none"</c:if>>
                    <div class="label">
                        <label for="payNotify" >商户名称：</label>
                    </div>
                    <div class="input">${openCustomer.storeName }</div>
                </div>
                <div class="field" <c:if test="${1!=openCustomer.shopClass}">style="display:none"</c:if>>
                    <div class="label">
                        <label for="payNotify" >门店名称：</label>
                    </div>
                    <div class="input">${openCustomer.shopName }</div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="role" >选择权限项目：</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox1">
                         <c:forEach items="${listOpenRoleItem}" var="item">
                         <input type="checkbox" id="role${item.id}" name="role" value="${item.id }" disabled="disabled"/>
                         <label >${item.name }</label>
                         	 <c:forEach items="${listRoleItemMap}" var="roleMap">
	                         <c:if test="${item.id==roleMap.ROLE_ID }" >
	                         <script>
	                         	$('#role${item.id}').attr("checked",true);
	                         </script>
	                         </c:if>
	                         </c:forEach>
                         </c:forEach>
                         </div>
                         
                    </div>
                    
                </div>

                <div class="buttons">
                    <a href="${ctx}/sys/open/customer-list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>






</body>
</html>