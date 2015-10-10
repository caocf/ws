<%@ tag pageEncoding="UTF-8" %>
<%--attribute for input --%>
<%@ attribute name="title" required="false" %>
<%@ attribute name="selected" required="false" %>
<%--attribute for input --%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="res" value="${ctx}/static" />
<c:set var="loginuser" value="${sessionScope._dest_login_}" />


<!doctype html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta content="width=device-width, minimum-scale=1.0, maximum-scale=2.0" name="viewport">
    <title><c:if test="${not empty title}">${title} - </c:if>M+开放平台-小信息 大世界</title>
    <link type="text/css" rel="stylesheet" href="${res}/css/mplus.css">
    <link type="text/css" rel="stylesheet" href="${res}/js/chosen/chosen.css">
    <link type="text/css" rel="stylesheet" href="${res}/css/mplus-ext.css">

    <link type="text/css" rel="stylesheet" href="${res}/js/fileupload/fileuploader.css">
    <link type="text/css" rel="stylesheet" href="${res}/js/lab/default/dialog.css">
    <link type="text/css" rel="stylesheet" href="${res}/js/lab/default/ibutton.css">

    <script src="${res}/js/jquery-1.8.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${res}/js/jquery.form.js" type="text/javascript" charset="utf-8"></script>
    <script src="${res}/js/jquery.blockUI.js" type="text/javascript" charset="utf-8"></script>
    <script src="${res}/js/chosen/chosen.jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${res}/js/fileupload/fileuploader.js" type="text/javascript" charset="utf-8"></script>

    <script src="${ctx}/js/global.js" type="text/javascript" charset="utf-8"></script>
    <script src="${res}/js/mplus.js" type="text/javascript" charset="utf-8"></script>
    <script src="${res}/js/wp-scroll.js" type="text/javascript" charset="utf-8"></script>
    <script src="${res}/js/lab/jquery.dialog.js" type="text/javascript" charset="utf-8"></script>
    <script src="${res}/js/My97DatePicker/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<!-- template header.html -->
<div class="header">
    <div  class="w1000">
        <div class="logo"><a href="${ctx}/"><img src="${res}/images/logo.png"/></a></div>
        <small class="logbar">
            <c:if test="${loginuser == null}">
                <a href="${ctx}/login">登录</a> | <a href="${ctx}/profile/create">注册</a>
            </c:if>
            <c:if test="${loginuser != null}">
                <a href="${ctx}/profile">${loginuser.developerType == 1 ? loginuser.realName : loginuser.company}</a> | <a href="${ctx}/logout">退出</a>
            </c:if>
        </small>
        <nav>
            <ul class="nav">
                <li<c:if test="${selected eq 'home'}"> class="current"</c:if>><a href="${ctx}/">首页</a></li>
                <li<c:if test="${selected eq 'app'}"> class="current"</c:if>><a href="${ctx}/myapp">我的应用</a></li>
                <li<c:if test="${selected eq 'doc'}"> class="current"</c:if>><a href="${ctx}/doc">文档</a></li>
            </ul>
        </nav>
    </div>
</div>
<div class="main">
<c:if test="${selected ne 'home' && not empty app.appName}" >
<div class="w1000 gline" style="overflow:hidden;margin:15px auto;"><a href="${ctx}/">首页</a><em>&gt;</em><a href="${ctx}/myapp">我的应用</a><em>&gt;</em>${app.appName }</div>
</c:if>
<!-- end template header.html -->