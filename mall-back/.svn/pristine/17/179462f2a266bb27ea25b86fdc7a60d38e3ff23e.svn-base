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
        <h5>添加黑名单</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" action="/sys/black/add.do" id="fm" commandName="blackUser" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">黑名单ID：</div>
                    <div class="input">自动生成</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="terminalId" class="req">号码：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="terminalId" cssClass="small required validate-mobile-phone" maxlength="13"/>
                    </div>
                </div>
                
                   <div class="field">
                   <div class="label label-radio">
                        <label for="levTag" class="req">级别：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        <c:forEach items="${levTapMap}" var="item" varStatus="index">
                            <input type="radio" id="radio-${index.count}" <c:if test="${index.count==1}">checked="checked"</c:if> name="levTag" class="validate-one-required"  value="${item.key }"/><label for="radio-${index.count }">${item.value }</label>
                            </c:forEach>
                            
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