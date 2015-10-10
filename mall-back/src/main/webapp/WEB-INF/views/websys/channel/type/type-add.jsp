<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    	$(document).ready(function(){
    		selectRegion("#regionName","regionCode","regionName",'0',{maxItems:1});
    		selectItemType("#typeName",function(id,txt){
	    		$("#typeId").val(id);
	    		$("#typeName").val(txt);
	    		$("#displayName").val(txt);
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
        <h5><c:if test="${method == 'add'}">添加分类配置</c:if><c:if test="${method == 'edit'}">修改分类配置</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="channelType"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
        <form:hidden path="id"/>
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${channelType.id==null?"自动生成": channelType.id }</div>
                </div>
             
              <div class="field">
                    <div class="label">
                        <label for="channel" class="req">频道：</label>
                    </div>
                    <div class="select">
                     <form:select path="channel" items="${channelMap }"></form:select>
                    </div>
                </div>
                
               <div class="field"  id="typeIdDiv">
						<div class="label">
							<label for="typeId" class="req">分类：</label>
						</div>
						<div class="input">
							<form:hidden path="typeId" cssClass="small required" maxlength="100"/>
							<form:input path="typeName" maxlength="100" readonly="true" cssClass="small required" />
							<span class="error" id="advice-required-typeName" style="display:none"></span>
						</div>
				</div>
                
                 <div class="field">
                    <div class="label">
                        <label for="title" class="req">显示名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="displayName" cssClass="small required max-length-50" maxlength="50" />
                    </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="title" class="req">区域：</label>
                    </div>
                    <div class="input">
                        <form:hidden path="regionCode" cssClass="small  required" />
                        <form:input path="regionName" cssClass="small  required" maxlength="50"  readonly="true"/>
                    </div>
                </div>
                
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/channel/type/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>