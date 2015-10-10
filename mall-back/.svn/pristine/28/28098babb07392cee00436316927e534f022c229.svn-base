<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>

    
</head>
<body>
<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5> 编辑码<h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="code" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" >
     <input type="hidden" name="storeId" value="${storeId }" />
        <div class="form">
              <div class="fields">
                <div class="field">
                    <div class="label">
                        <label for="code" class="req">验证码:</label>
                    </div>
                    <div class="input ">
                        <form:input path="code" readonly="true"/>
                    </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="startTime" class="req">有效日期:</label>
                    </div>
                    <div class="input">
                     <form:input path="validDate" readonly="true"/>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="stopTime" class="req">失效日期:</label>
                    </div>
                    <div class="input">
                       <form:input path="expireDate" readonly="true"/>
                    </div>
                </div>
               
                <div class="field">
                    <div class="label">
                        <label for="areaId" class="req">可验次数:</label>
                    </div>
                    <div class="input">
                     <form:input path="validTimes" cssClass="small required validate-number" readonly="true" maxlength="3" />
                    </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="startTime" class="req">状态:</label>
                    </div>
                    <div class="input">
                   		<select name="status">
							<option value="0"
								<c:if test="${code.status == 0}">selected="selected"</c:if>>已制码，未验证</option>
							<option value="1"
								<c:if test="${code.status == 1}">selected="selected"</c:if>>已撤销</option>
							<option value="2"
								<c:if test="${code.status == 2}">selected="selected"</c:if>>使用中</option>
							<option value="3"
								<c:if test="${code.status == 3}">selected="selected"</c:if>>已使用</option>
							<option value="4"
								<c:if test="${code.status == 4}">selected="selected"</c:if>>已过期</option>
							<option value="100"
								<c:if test="${code.status == 100}">selected="selected"</c:if>>初始化，未使用</option>
					</select>
                    </div>
                </div>
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    
                    <a href="javascript:history.back();" class="btnAnchor">返回</a>
                </div>
            </div>
      </div>
    </form:form>
      </div>
</div>
<!-- end forms -->







</body>
</html>