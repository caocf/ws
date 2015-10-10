<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head/>
<script type="text/javascript">
   $().ready(function() {
	//获取来源地址
		var url = document.referrer;
		$("#backUrl").val(url);
	});
</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">录入短信内容</c:if><c:if test="${method == 'edit'}">编辑短信内容</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="contSms" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
        <c:if test="${method == 'edit'}">
        	<input type="hidden" id="backUrl" name="backUrl" />
        	<form:hidden path="unitId" />
        	<form:hidden path="status" />
        	<form:hidden path="auditorId" />
        	<form:hidden path="updateUserId" />
        	<form:hidden path="updateTime" />
        </c:if>
            <div class="fields">
            	<div class="field">
	                <div class="label">
	                 	<label for="contentSrc" class="req">内容源：</label>
	                 </div>
	                <div class="input">
		                <form:select path="contentSrc" cssClass="required validate-selection">
		                	<form:option value="">请选择</form:option>
		                <c:forEach items="${contentCodeList}" var="item">
		                	<form:option value="${item.code}">${item.name}</form:option>
		                </c:forEach>
		                </form:select>
		                <span class="error" id="advice-required-contentSrc" style="display:none"></span>
                        <span class="error" id="advice-validate-selection-contentSrc" style="display:none"></span>
                        <span class="error" id="advice-server-contentSrc" style="display:none"></span>
	                </div>
                </div>		
                <div class="field">
					<div class="label">
						<label for="startTime" class="req">开始时间:</label>
					</div>
					<div class="input">
						<input type="text" id="startTime" name="startTime" value="<ct:time source="${contSms.startTime}"/>" class="date required" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" />
						<span class="error" id="advice-required-startTime" style="display:none"></span>
                        <span class="error" id="advice-server-startTime" style="display:none"></span>
					</div>
				</div>
				<div class="field">
					<div class="label">
						<label for="stopTime" class="req">结束时间:</label>
					</div>
					<div class="input">
						<input type="text" id="endTime" name="endTime" value="<ct:time source="${contSms.endTime}"/>" class="date required" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" />
						<span class="error" id="advice-required-endTime" style="display:none"></span>
                        <span class="error" id="advice-server-endTime" style="display:none"></span>
					</div>
				</div>
                <div class="field">
                    <div class="label">
                        <label for="keyword" class="req">内容关键字：</label>
                    </div>
                    <div class="input">
                        <form:input path="keyword" cssClass="small required min-length-0 max-length-100" maxlength="100" title="短信关键字，多个短语用“;”分隔" />
                        <span class="error" id="advice-required-keyword" style="display:none"></span>
                        <span class="error" id="advice-min-length-keyword" style="display:none"></span>
                        <span class="error" id="advice-max-length-keyword" style="display:none"></span>
                        <span class="error" id="advice-server-keyword" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="content" class="req">内容：</label>
                    </div>
                    <div class="input">
                        <textarea name="content" cols="50" rows="8" class="required max-length-500" >${contSms.content}</textarea>
                    	<span class="error" id="advice-required-content" style="display:none"></span>
                        <span class="error" id="advice-max-length-content" style="display:none"></span>
                        <span class="error" id="advice-server-content" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="remark" class="req">内容描述：</label>
                    </div>
                    <div class="input">
                        <form:input path="remark" cssClass="small required min-length-0 max-length-100" maxlength="100" title="短信的简要说明" />
                    	<span class="error" id="advice-required-remark" style="display:none"></span>
                    	<span class="error" id="advice-min-length-remark" style="display:none"></span>
                        <span class="error" id="advice-max-length-remark" style="display:none"></span>
                        <span class="error" id="advice-server-remark" style="display:none"></span>
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="javascript:history.go(-1)" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>