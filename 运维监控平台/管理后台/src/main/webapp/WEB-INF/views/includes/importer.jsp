<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ht" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="contextUrl" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}" />

<c:set var="res" value="${ctx}/static" />

<c:set var="loginuser" value="${sessionScope.session_dc_user_key__}" />
