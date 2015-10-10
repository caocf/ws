<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="includes/top.jsp" %>
<style type="text/css">
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
    }

    .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
        -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
        box-shadow: 0 1px 2px rgba(0,0,0,.05);
    }
    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }
    .form-signin input[type="text"],
    .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
    }

</style>


<form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true" acceptCharset="utf-8" cssClass="form-signin">
    <h2 class="form-signin-heading">请登录</h2>

    <form:errors path="*" id="msg" cssClass="alert alert-error" element="div" />

    <form:input cssClass="input-block-level" tabindex="1" accesskey="n" path="username" autocomplete="off" placeholder="帐号" htmlEscape="true" />

    <form:password cssClass="input-block-level" tabindex="2" path="password" accesskey="p" htmlEscape="true" autocomplete="off" placeholder="密码"/>

    <c:if test="${not empty sessionScope.error_count && sessionScope.error_count >= 3}">
        <input id="vcode" name="vcode" accesskey="v" type="text" tabindex="3" style="width:120px"/>
        <img id="vcodeImage" title="点击重新获得验证码!" src="<c:url value="/captcha.htm"/>" style="margin-left:10px; cursor: pointer"/>

    </c:if>
<%--
    <label class="checkbox">
        <input id="rememberMe" name="rememberMe" accesskey="r" tabindex="3" type="checkbox" value="true"> 记住我
    </label>

    <label class="checkbox">
        <input id="warn" name="warn" value="true" tabindex="4" accesskey="w" type="checkbox" /> 转向其他站点前提示我
    </label>
--%>

    <input type="hidden" name="lt" value="${loginTicket}" />
    <input type="hidden" name="execution" value="${flowExecutionKey}" />
    <input type="hidden" name="_eventId" value="submit" />

    <button class="btn btn-primary" id="submit1" type="submit" name="submit" accesskey="l" data-loading-text="登录中..." tabindex="5">登录</button>
    <button class="btn" type="reset" name="reset" accesskey="c" tabindex="6">重置</button>


</form:form>

<%@ include file="includes/bottom.jsp" %>

<script>

    $(function() {

        $('#vcodeImage').click(function(){

            $(this)[0].src = '<c:url value="/captcha.htm"/>?rnd=' + Math.random();;

        });

        $('#submit1').on('click', function () {
            $(this).button('loading')
        })
    })



</script>