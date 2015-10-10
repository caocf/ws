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
        <h5>修改黑名单</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" action="/sys/black/update.do" id="fm" commandName="blackUser" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <form:hidden path="id" />
        <form:hidden path="inTag" />
        <form:hidden path="unitId" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">黑名单ID：</div>
                    <div class="input">${blackUser.id }</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="terminalId" class="req">号码：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="terminalId" cssClass="small required validate-mobile-phone"  maxlength="13"/>
                    </div>
                </div>
                   <div class="field">
                   <div class="label label-radio">
                        <label for="levTag" class="req">级别：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="levTag" class="validate-one-required"  ${blackUser.levTag == 0?"checked":"" } value="0"/><label for="radio-1">平台级（任何业务都不能参加）</label>
                            <input type="radio" id="radio-2" name="levTag" ${blackUser.levTag == 1?"checked":"" } value="1"/><label for="radio-2">不能接收群发信息</label>
                            <input type="radio" id="radio-3" name="levTag" ${blackUser.levTag == 2?"checked":"" } value="2"/><label for="radio-3">广告黑名单</label>
                            <input type="radio" id="radio-4" name="levTag" ${blackUser.levTag == 3?"checked":"" } value="3"/><label for="radio-4">本单位所有业务都不能参加</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-levTag" style="display:none"></span>
                         </div>
                    </div>                    
                    
                <div class="field">
                    <div class="label label-textarea">
                        <label for="remark">备注：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="remark" cols="50" rows="8" cssClass="max-length-100" />
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight"  value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/black/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>

</body>
</html>