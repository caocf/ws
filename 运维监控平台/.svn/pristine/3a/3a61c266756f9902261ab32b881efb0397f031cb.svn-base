<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ attribute name="title" required="false" %>
<c:set var="menus" value="${ct:getPathMenus()}" />
<div class="crumb">
    <ul class="breadcrumb">
        <li><a href="<c:url value="/home"/>"><i class="icon16 i-home-4"></i>首页</a></li>
        <c:forEach items="${menus}" var="menu" varStatus="status">
            <c:if test="${status.last}">
                <c:if test="${not empty menu.menuName or not empty title}">
                    <li><span class="divider">/</span> ${empty menu.menuName ? title : menu.menuName} </li>
                </c:if>
            </c:if>
            <c:if test="${not status.last}">
                <li><span class="divider">/</span> <a href="<c:url value="${menu.menuUrl}"/>">${menu.menuName}</a> </li>
            </c:if>
        </c:forEach>
    </ul>
</div>