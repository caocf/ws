<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/t.jsp"%>

<div class="row-fluid">
    <div class="span12">
        <form:form method="post" id="fm" commandName="user" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate form-horizontal">
            <div class="row-fluid">
                <div class="control-group group-search">
                    <label class="control-label req" for="name">用户姓名：</label>
                    <div class="controls controls-row">
                        <form:input path="name" cssClass="span6 required max-length-10" maxlength="10" disabled="true" />
                    </div>
                </div>

                <div class="control-group group-search">
                    <label class="control-label req" for="AREA_CODE">地区：</label>
                    <div class="controls controls-row" style="width: 44.2%">
                        <form:select path="area" id="area" cssClass="multiselect" multiple="multiple" style="height:200px;width:300px;">
                            <c:forEach items="${listArea}" var="areas">
                                <c:if test="${areas.exist == true}">
                                    <form:option value="${areas.area_code}" selected="selected">${areas.area_name}</form:option>
                                </c:if>
                                <c:if test="${areas.exist != true}">
                                    <form:option value="${areas.area_code}">${areas.area_name}</form:option>
                                </c:if>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>

                <div class="control-group group-search">
                    <label class="control-label"></label>
                    <div class="controls controls-row">
                        <button type="submit" class="btn btn-primary" id="sub">
                            <i class="icon16 i-disk"></i>
                            保 存
                        </button>
<!--                         <button type="reset" class="btn"> -->
<!--                             <i class="icon16 i-loop"></i> -->
<!--                             重 置 -->
<!--                         </button> -->
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
