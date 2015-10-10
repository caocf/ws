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
        <h5>添加内容源</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" action="/cont/contentCode/add.do" id="fm" commandName="contentCode" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">内容源ID：</div>
                    <div class="input">自动生成</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="code" class="req">标识：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="code" class="small required  max-length-10" maxlength="10"/>
                         <span class="error" id="advice-validate-one-required-code" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="name" class="req">名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="name" class="small required  max-length-50" maxlength="50"/>
                         <span class="error" id="advice-validate-one-required-name" style="display:none"></span>
                    </div>
                </div>
                             
                <div class="field">
                   <div class="label label-radio">
                        <label for="contType" class="req">内容源信息类型：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${contTypeMap }" var="item" varStatus="index">
                            <input type="radio" id="radio1-${index.count}" name="contType" class="validate-one-required" <c:if test="${index.count==1}">checked="checked" </c:if> value="${item.key}"/><label for="radio1-${index.count}">${item.value}</label>
                            </c:forEach>
                        </div>
                        <span class="error" id="advice-validate-one-required-contType" style="display:none"></span>
                         </div>
                    </div>      
                
                   <div class="field">
                   <div class="label label-radio">
                        <label for="codeType" class="req">内容源使用类型：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${codeTypeMap }" var="item" varStatus="index">
                            <input type="radio" id="radio2-${index.count}" name="codeType" class="validate-one-required" <c:if test="${index.count==1}">checked="checked" </c:if>  value="${item.key}"/><label for="radio2-${index.count}">${item.value }</label>
                            </c:forEach>
                        </div>
                        <span class="error" id="advice-validate-one-required-codeType" style="display:none"></span>
                         </div>
                    </div>      
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight"  value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/cont/contentCode/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>

</body>
</html>