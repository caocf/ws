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
        <h5>修改过滤字<h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="filterWord" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label">
                        <label for="word" class="req">过滤字：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="word" cssClass="small required" maxlength="25" />
                         <span class="error" id="advice-required-userPwd" style="display:none"></span>
                    </div>
                </div>

              
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/filterword/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


<script type="text/javascript">
    <!--
    ajaxFormSubmit('#fm');

    //-->
</script>



</body>
</html>