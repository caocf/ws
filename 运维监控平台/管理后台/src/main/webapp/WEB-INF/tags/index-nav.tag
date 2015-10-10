<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="loginuser" value="${sessionScope.session_shop_user_key__}" />

<div id="header">
    <div class="wrapper">
        <img src="<spring:url value="/static/images/head_pro.jpg"/>" style="width:960px;height:90px;"/>
    </div>
</div>
<div id="nav">
    <div class="wrapper">
        <h1>${empty loginuser.shop ? loginuser.store.name : loginuser.shop.name}</h1>
        <div class="menu">
            <a href="<spring:url value="/logout"/>">退出</a>
            <a href="">设置</a>
            <a href="">消息</a>
            <a href="">帮助 </a></div>
    </div>
</div>
