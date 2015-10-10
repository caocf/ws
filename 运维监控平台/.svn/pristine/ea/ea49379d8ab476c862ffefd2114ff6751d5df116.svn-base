<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="inner_title" value="修改密码"/>
<%@ include file="../includes/t.jsp" %>

<div class="row-fluid">
<div class="span12">
    <form:form method="post" id="fm" commandName="dcUser" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate form-horizontal">
    <div class="page-header">
        <h4>密码修改（不需修改则留空）</h4>
    </div>

    <div class="row-fluid">
        <div class="control-group group-search">
            <label class="control-label" for="mobile">原密码：</label>

            <div class="controls controls-row">
                <form:password path="oldPass" cssClass="span6" maxlength="20"/>
            </div>
        </div>
        <div class="control-group group-search">
            <label class="control-label" for="mobile">新密码：</label>

            <div class="controls controls-row">
                <form:password path="newPass" cssClass="span6" maxlength="20"/>
            </div>
        </div>
        <div class="control-group group-search">
            <label class="control-label" for="mobile">新密码确认：</label>

            <div class="controls controls-row">
                <form:password path="confirmPass" cssClass="span6 equals-newPass" title="两次输入密码不一致"
                               maxlength="20"/>
            </div>
        </div>
        <div class="control-group group-search">
            <label class="control-label"></label>
            <div class="controls controls-row">
                <button type="submit" class="btn btn-primary" data-loading-text="提交中 ..."><i class="icon16 i-disk"></i>保 存</button>
                <button type="reset" class="btn"><i class="icon16 i-loop"></i>重 置</button>
            </div>
        </div>
    </div>
    </form:form>
</div></div>

<%@ include file="../includes/b.jsp" %>