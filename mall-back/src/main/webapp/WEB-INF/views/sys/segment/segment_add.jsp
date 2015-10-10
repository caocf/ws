<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
<!--
	$(function(){
		selectRegionCallBack("#areaCode",function(saleAreaCode,saleAreaName){
			$("#areaCode").val(saleAreaCode);
			$("#areaCode").focus();
			$("#areaCode").blur();
		},0,{index:1,maxItems : 1});
	});
//-->
</script>
</head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加号段</c:if><c:if test="${method == 'edit'}">修改号段</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="sysSegment" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <input type="hidden" name="oldSegmentCode" value="${sysSegment.segmentCode}"/>
        <div class="form">
            <div class="fields">            
            	<c:if test="${method == 'add'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">自动生成</div>
                </div>
                </c:if>
                <c:if test="${method == 'edit'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${sysSegment.id}</div>
                </div>
                </c:if>	
                <div class="field">
                    <div class="label">
                        <label for="segmentCode" class="req">号段：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="segmentCode" cssClass="small required validate-digits min-length-7 max-length-7" maxlength="7" />
                        <span class="error" id="advice-required-segmentCode" style="display:none"></span>
                        <span class="error" id="advice-validate-digits-segmentCode" style="display:none"></span>
                        <span class="error" id="advice-min-length-segmentCode" style="display:none"></span>
                        <span class="error" id="advice-max-length-segmentCode" style="display:none"></span>
                        <span class="error" id="advice-server-segmentCode" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="operatorCode" class="req">操作码：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="operatorCode" cssClass="small required validate-alpha min-length-0 max-length-50" maxlength="50" />
                    	<span class="error" id="advice-required-operatorCode" style="display:none"></span>
                        <span class="error" id="advice-validate-alpha-operatorCode" style="display:none"></span>
                        <span class="error" id="advice-min-length-operatorCode" style="display:none"></span>
                        <span class="error" id="advice-max-length-operatorCode" style="display:none"></span>
                        <span class="error" id="advice-server-operatorCode" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="areaCode" class="req">地市：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                    	<form:hidden path="" cssClass="small  required" />
                        <form:input path="areaCode" cssClass="small required validate-digits min-length-0 max-length-8" maxlength="8" readonly="true"/>
                        <span class="error" id="advice-required-areaCode" style="display:none"></span>
                        <span class="error" id="advice-validate-digits-areaCode" style="display:none"></span>
                        <span class="error" id="advice-min-length-areaCode" style="display:none"></span>
                        <span class="error" id="advice-max-length-areaCode" style="display:none"></span>
                        <span class="error" id="advice-server-areaCode" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="mmscId" class="req">彩信中心编号：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="mmscId" cssClass="small required validate-digits min-length-0 max-length-20" maxlength="20" />
                    	<span class="error" id="advice-required-mmscId" style="display:none"></span>
                        <span class="error" id="advice-validate-digits-mmscId" style="display:none"></span>
                        <span class="error" id="advice-min-length-mmscId" style="display:none"></span>
                        <span class="error" id="advice-max-length-mmscId" style="display:none"></span>
                        <span class="error" id="advice-server-mmscId" style="display:none"></span>
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


</body>
</html>