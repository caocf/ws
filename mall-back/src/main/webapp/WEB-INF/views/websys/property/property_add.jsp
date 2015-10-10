<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加属性</c:if><c:if test="${method == 'edit'}">修改属性</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="sysProperty" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
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
                    <div class="input">${sysProperty.id}</div>
                </div>
                </c:if>		
                <div class="field">
                    <div class="label">
                        <label for="type" class="req">选择方式:</label>
                    </div>
                    <div class="select">
                        <select id="type" name="type" class="validate-selection">
                            <option value="">--请选择--</option>
                            <c:forEach items="${typeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${sysProperty.type == item.key}">selected="selected"</c:if>>${item.value }</option>
                        	</c:forEach>
                        </select>
                        <span class="error" id="advice-validate-selection-type" style="display:none"></span>
                        <span class="error" id="advice-server-type" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="content" class="req">内容：</label>
                    </div>
                    <div class="input">
                        <textarea name="content" cols="50" rows="8" class="required max-length-25">${sysProperty.content}</textarea>
                    	<span class="error" id="advice-required-content" style="display:none"></span>
                        <span class="error" id="advice-max-length-content" style="display:none"></span>
                        <span class="error" id="advice-server-content" style="display:none"></span>
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>