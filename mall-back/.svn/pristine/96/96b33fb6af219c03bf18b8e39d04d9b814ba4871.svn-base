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
        <h5>审核第三方应用</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="openCustomer" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
	<input type="hidden" id="openCustomerId" name="appId" value="${openCustomer.appId }" />
        <div class="form">
            <div class="fields">
              <div class="field">
                    <div class="label label-radio">
                        <label>审核:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="audit"  value="2" checked/><label for="radio-1">通过</label>
                            <input type="radio" id="radio-2" name="audit"  value="1"/><label for="radio-2">驳回</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>

                

                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <a href="${ctx}/sys/open/customer-list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
</div>


</body>
</html>