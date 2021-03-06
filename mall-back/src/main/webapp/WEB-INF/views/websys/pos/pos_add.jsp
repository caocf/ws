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
        <h5><c:if test="${method == 'add'}">添加终端</c:if><c:if test="${method == 'edit'}">修改终端</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="sysPos" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <input type="hidden" id="oldName" name="oldName" value="${sysPos.name}" />
            <input type="hidden" id="backUrl" name="backUrl" />
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
                    <div class="input">${sysPos.id}</div>
                </div>
                </c:if>		
                <div class="field">
                    <div class="label">
                        <label for="name">名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="name" cssClass="small min-length-0 max-length-20" maxlength="20" />
                        <span class="error" id="advice-min-length-name" style="display:none"></span>
                        <span class="error" id="advice-max-length-name" style="display:none"></span>
                        <span class="error" id="advice-server-name" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="src" class="req">来源：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="src" cssClass="small required min-length-0 max-length-20" maxlength="20" />
                    	<span class="error" id="advice-required-src" style="display:none"></span>
                    	<span class="error" id="advice-min-length-src" style="display:none"></span>
                        <span class="error" id="advice-max-length-src" style="display:none"></span>
                        <span class="error" id="advice-server-src" style="display:none"></span>
                    </div>
                </div>
                <!-- 
                <div class="field">
                    <div class="label">
                        <label for="type" class="req">类型:</label>
                    </div>
                    <div class="select">
                        <select id="type" name="type" class="validate-selection">
                            <option value="">--请选择--</option>
                            <c:forEach items="${typeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${sysPos.type == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                        </select>
                    </div>
                </div>
                 -->
                <div class="field">
                    <div class="label">
                        <label for="factory" class="req">厂家：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="factory" cssClass="small required min-length-0 max-length-10" maxlength="10" />
                    	<span class="error" id="advice-required-factory" style="display:none"></span>
                    	<span class="error" id="advice-min-length-factory" style="display:none"></span>
                        <span class="error" id="advice-max-length-factory" style="display:none"></span>
                        <span class="error" id="advice-server-factory" style="display:none"></span>
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                 
                   <input class="common_btn" type="button" onclick="window.history.go(-1)"   value="返回">
                    
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


</body>
</html>