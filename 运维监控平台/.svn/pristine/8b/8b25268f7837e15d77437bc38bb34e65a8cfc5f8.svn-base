<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/t.jsp"%>

<div class="row-fluid">
    <div class="span12">
        <form:form method="post" id="fm" commandName="role" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate form-horizontal">
            <div class="row-fluid">
                <div class="control-group group-search">
                    <label class="control-label req" for="roleName">角色名：</label>
                    <div class="controls controls-row">
                        <form:input path="roleName" cssClass="span6 required max-length-10" maxlength="10" disabled="true" />
                    </div>
                    <label class="control-label req" for="remark">描述：</label>
                    <div class="controls controls-row">
                        <form:input path="remark" cssClass="span6 required max-length-10" maxlength="10" disabled="true" />
                    </div>
                </div>

                <div class="control-group group-search">
                    <label class="control-label req" for="menu_code">菜单：</label>
                    <div class="controls controls-row" style="width: 44.2%">
                        <form:select path="menus" id="menus" style="height:200px;width:300px;" disabled="true">
                            <c:forEach items="${menus}" var="menu">
                                <c:if test="${menu.exist == true}">
                                    <form:option value="${menu.menu_code}">${menu.menu_name}</form:option>
                                </c:if>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>

                <div class="control-group group-search">
                    <label class="control-label"></label>
                    <div class="controls controls-row">
                        <button class="btn" type="button" onclick='history.back();'>
                            <i class="icon16 i-exit"></i>
                            返 回
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

<%@ include file="../../includes/b.jsp"%>
