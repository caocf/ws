<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
<script type="text/javascript">
$(function(){
	$("#appTypeSelect").change(function(){
		$("#storeId").val("");
		$("#storeName").val("");
		$("#shopId").val("");
		$("#shopName").val("");
		if($("#appTypeSelect").val()==1){
			shopClassDiv.style.display="none";
			shopDiv.style.display="none";
			storeDiv.style.display="none";
			
		}else{
			shopClassDiv.style.display="block";
			if($("#shopClassSelect").val()==1){
				shopDiv.style.display="block";
				storeDiv.style.display="none";
			}else{
				shopDiv.style.display="none";
				storeDiv.style.display="block";
			}
		}
	});
	$("#shopClassSelect").change(function(){
		$("#storeId").val("");
		$("#storeName").val("");
		$("#shopId").val("");
		$("#shopName").val("");
		if($("#shopClassSelect").val()==1){
			shopDiv.style.display="block";
			storeDiv.style.display="none";
		}else{
			$("#shopId").val("0");
			shopDiv.style.display="none";
			storeDiv.style.display="block";
		}
	});
	//选择商户
   	$("#storeName").click(function(){
   		var shopClass = $("#shopClassSelect").val();
		selectStore("storeId","storeName",'',{shopClass:shopClass,status:'3'});
	});
	$("#shopName").click(function(){
		selectAllShop("shopId","shopName","storeId","storeName",{});
	});
});
</script>


</head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>添加第三方应用</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="openCustomer" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
	<input type="hidden" id="openCustomerId" name="appId" value="${openCustomer.appId }" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">应用ID：</div>
                    <div class="input">自动生成</div>
                </div>
               
                <div class="field">
                    <div class="label">
                        <label for="name" class="req">应用名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="name" cssClass="small required" maxlength="100"/>
                    </div>
                </div>
				
                 <div class="field">
                    <div class="label">
                        <label for="remark" class="req">应用详情：</label>
                    </div>
                    <div class="input">
                    <form:textarea path="remark" cssClass="small required" maxlength="2000" ></form:textarea>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="ips" >应用IP：</label>
                    </div>
                    <div class="input">
                    <form:textarea path="ips"  maxlength="2000" ></form:textarea>
                    <span class="error">提示：最多20个有效IP地址,以逗号为分割,为空则表示不限制IP地址</span>
                    </div>
                </div>
				
				<div class="field">
                    <div class="label">
                        <label for="payNotify">支付通知接口：</label>
                    </div>
                    <div class="input">
                        <form:input path="payNotify" cssClass="small" maxlength="100"/>
                        <span class="error">提示：对于有支付功能的应用该处需要填写</span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="appType" class="req">应用类型：</label>
                    </div>
                    <div class="input">
                        <select name="appType" id="appTypeSelect">
                    	<c:forEach items="${appTypeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${item.key == 1}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>
                <div class="field" id="shopClassDiv" style="display:none">
                    <div class="label">
                        <label for="shopClass" class="req">商户类型：</label>
                    </div>
                    <div class="input">
                        <select name="shopClass" id="shopClassSelect">
                    	<c:forEach items="${shopClassMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${item.key == 1}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>
                <div class="field" id="shopDiv" style="display:none">
                    <div class="label">
                        <label for="shopId" >门店：</label>
                    </div>
                    <div class="input">
                        <form:hidden path="shopId" />
						<form:input path="shopName" id="shopName"  cssClass="small" maxlength="100" readonly="true"/>
                    </div>
                </div>
                <div class="field" id="storeDiv" style="display:none">
                    <div class="label">
                        <label for="storeId" >商户：</label>
                    </div>
                    <div class="input">
                        <form:hidden path="storeId" />
						<form:input path="storeName" id="storeName" cssClass="small" maxlength="100" readonly="true"/>
                    </div>
                </div>
                
                
                <div class="field">
                    <div class="label label-textarea">
                        <label for="role">选择权限项目：</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox1">
                         <c:forEach items="${listOpenRoleItem}" var="item">
                         <input type="checkbox" name="role" value="${item.id }" />
                         <label >${item.name }</label>
                         
                         </c:forEach>
                         </div>
                         
                    </div>
                    
                </div>

                

                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/open/customer-list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
</div>


</body>
</html>