<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="includes/top.jsp" %>
<c:url var="url" value="/login">
    <c:param name="service" value="${param.service}" />
    <c:param name="renew" value="true" />
</c:url>
<div class="alert alert-error">
    <h2><spring:message code="screen.service.sso.error.header" /></h2>
    <spring:message code="screen.service.sso.error.message"  arguments="${url}" />
</div>
<%@ include file="includes/bottom.jsp" %>
