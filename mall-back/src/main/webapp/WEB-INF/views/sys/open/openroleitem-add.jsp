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
        <h5>添加权限项目</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="openRoleItem" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
	<input type="hidden" id="openRoleItemId" name="id" value="${openRoleItem.id }" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">自动生成</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="name" class="req">名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="name" cssClass="small required" maxlength="20"/>
                    </div>
                </div>
				
                 <div class="field">
                    <div class="label">
                        <label for="path" class="req">路径：</label>
                    </div>
                    <div class="input">
                    <form:textarea path="path" cssClass="small required" maxlength="200" ></form:textarea>
                    </div>
                </div>


                

                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/open/roleitem-list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
</div>


</body>
</html>