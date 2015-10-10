<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/v2" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="contextUrl" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<c:set var="res" value="${ctx}/static" />
<c:set var="loginuser" value="${sessionScope.session_dc_user_key__}" />