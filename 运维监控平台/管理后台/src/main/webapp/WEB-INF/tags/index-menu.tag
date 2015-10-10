<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="loginuser" value="${sessionScope.session_shop_user_key__}" />
<c:set var="spath" value="${requestScope['javax.servlet.forward.servlet_path']}"/>

<%--attribute for input --%>
<%@ attribute name="selected" required="false" %>
<%--attribute for input --%>

<c:if test="${not empty selected}">
    <c:set var="spath" value="${selected}"/>
</c:if>

<div id="sidebar">
<Div id="sidebar-follow">
<div class="menu active">
    <h1><a href="<spring:url value="/home"/>">首页</a></h1>
</div>

<c:forEach items="${loginuser.menus}" var="level1" >
<div class="menu">
    <h2><a href="#">${level1.menuName}<span></span></a></h2>
    <ul>
        <c:forEach items="${level1.childMenus}" var="level2">
            <li><a <c:if test="${fn:startsWith(level2.menuUrl, spath)}">class="hover"</c:if> href="<spring:url value="${level2.menuUrl}"/>">${level2.menuName}</a></li>
        </c:forEach>
    </ul>
</div>
</c:forEach>
</div>
</div>
