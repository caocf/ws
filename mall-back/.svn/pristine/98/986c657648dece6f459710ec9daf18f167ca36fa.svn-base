<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
$(function(){
	
	var url = document.referrer;
	$("#backUrl").val(url);
	
	selectRegionCallBack("#regionName",function(saleAreaCode,saleAreaName){
		$("#regionCode").val(saleAreaCode);
		$("#regionName").val(saleAreaName);
		
		$("#regionName").focus();
		$("#regionName").blur();

	},0,{index:3});
	
	selectItemType("#typeName",function(id,txt){
		$("#typeId").val(id);
		$("#typeName").val(txt);
		$("#typeName").focus();
		$("#typeName").blur();
	},{leaf:false});
});
</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加楼层配置</c:if><c:if test="${method == 'edit'}">修改楼层配置</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add" commandName="channelFloor"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <div class="form">
        <input type="hidden" id="backUrl" name="backUrl" />
        <form:hidden path="id"/>
            <div class="fields">
            	<c:if test="${method == 'add'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自动生成</div>
                </div>
                </c:if>
                
                <c:if test="${method == 'edit'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${channelFloor.id}</div>
                </div>
                </c:if>		
                                
                <div class="field">
                    <div class="label">
                        <label for="channel" class="req">频道：</label>
                    </div>
             <div class="input">
                     <form:select path="channel" items="${channelMap }"></form:select>
                 </div>
                </div>
                  <div class="field">
                    <div class="label">
                        <label for="linkUrl" class="req">楼层：</label>
                    </div>
                    <div class="input">
						<form:select path="floorId" items="${groupMap }"></form:select>
                    </div>
                </div>

               <div class="field" >
						<div class="label">
							<label for=typeId class="req">分类：</label>
						</div>
						<div class="input">
							<form:hidden path="typeId" cssClass="small required" maxlength="100"/>
							<form:input path="typeName" maxlength="100" readonly="true" cssClass="small required" value="${sysTypeName }" />
							<span class="error" id="advice-required-typeName" style="display:none"></span>
						</div>
				</div>
              
                 <div class="field">
                    <div class="label">
                        <label for="title" class="req">楼层标题：</label>
                    </div>
                    <div class="input">
                        <form:input path="floorTitle" cssClass="small required max-length-50" maxlength="50" />
                    </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="title" class="req">区域：</label>
                    </div>
                    <div class="input">
                        <form:hidden path="regionCode" cssClass="small  required" />
                        <form:input path="regionName" cssClass="small  required" maxlength="50"  readonly="true" value="${regionCodeName }"/>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="orderIndex">排序：</label>
                    </div>
                    <div class="input">
                        <form:input path="orderIndex" cssClass="validate-integer" maxlength="5" />
                    </div>
                </div>
                
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/channel/floor/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>