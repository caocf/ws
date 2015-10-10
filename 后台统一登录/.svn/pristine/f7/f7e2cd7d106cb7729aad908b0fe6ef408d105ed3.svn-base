<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商城管理平台登录</title>
    <link href="<c:url value="/new/reset.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/new/m.css"/>" rel="stylesheet" type="text/css" />
    <script src="<c:url value="/ui/js/jquery-1.8.3.js"/>"></script>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="<c:url value="/ui/js/html5shiv.js"/>"></script>
    <![endif]-->
    <script>
        if (window!=top) top.location.href = window.location.href;
    </script>
</head>
<body>
<form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true" acceptCharset="utf-8" cssClass="form-signin">
<div class="wrapper">
    <div id="logo" ><img src="<c:url value="/new/logo.jpg"/>" /></div>
    <div id="des">商城管理平台</div>
    <div id="login_menu">
        <form:errors path="*" id="msg" cssClass="alert alert-error" delimiter=" " element="div" />
        <div class="line">
            <label >用户名</label>
            <div class="line_input">
                <form:input cssStyle="width:320px" tabindex="1" accesskey="n" path="username" autocomplete="off" htmlEscape="true" />
            </div>
        </div>
        <div class="line">
            <label >密码</label>
            <div class="line_input">
                <form:password cssStyle="width:320px" tabindex="2" path="password" accesskey="p" htmlEscape="true" autocomplete="off" />
            </div>
        </div>
        <c:if test="${true || (not empty sessionScope.error_count && sessionScope.error_count >= 0)}">
        <div class="line">
            <label >验证码</label>
            <div class="line_input">
                <input id="vcode" name="vcode" accesskey="v" type="text" tabindex="3" style="width:120px" maxlength="4"/>
                <div class="vcodeDiv">
                    <img id="vcodeImage" title="点击重新获得验证码!" src="<c:url value="/captcha.htm"/>" />
                    <a href="javascript:;" id="vcodeRefresh">刷新验证码</a>
                </div>
            </div>
        </div>
        </c:if>
        <div class="btn">
            <input type="hidden" name="lt" value="${loginTicket}" />
            <input type="hidden" name="execution" value="${flowExecutionKey}" />
            <input type="hidden" name="_eventId" value="submit" />
            <a href="javascript:;" id="submit1"><img src="<c:url value="/new/btn_login.jpg"/>" /></a>
        </div>


    </div>
    <div id="copyright">
        中国移动江苏公司电子商务基地
    </div>
</div>
</form:form>
<script>
    $(function() {

        $('#vcodeImage').click(function(){

            $(this)[0].src = '<c:url value="/captcha.htm"/>?rnd=' + Math.random();

        });

        $('#vcodeRefresh').click(function() {
            $('#vcodeImage')[0].src = '<c:url value="/captcha.htm"/>?rnd=' + Math.random();
        });

        var submitting = false;

        $('#submit1').on('click', function () {
            if (!submitting) {
                submitting = true;
                $('#fm1').submit();
            }
        });

        $(document).keyup(function(event){
            if(event.keyCode ==13){
                $("#submit1").trigger("click");
            }
        });

        $('#username').focus();
    })
</script>

<%--
    <label class="checkbox">
        <input id="rememberMe" name="rememberMe" accesskey="r" tabindex="3" type="checkbox" value="true"> 记住我
    </label>

    <label class="checkbox">
        <input id="warn" name="warn" value="true" tabindex="4" accesskey="w" type="checkbox" /> 转向其他站点前提示我
    </label>
--%>
</body>
</html>

