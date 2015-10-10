<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/login/login.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/login/uniform/uniform.default.css"/>" rel="stylesheet" type="text/css" />

    <script src="<c:url value="/ui/js/jquery-1.8.3.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<c:url value="/login/uniform/jquery.uniform.min.js"/>"></script>
    <script src="<c:url value="/login/login.js"/>"></script>

    <!--[if IE 6]>
    <link rel="stylesheet" href="<c:url value="/login/ie6.css"/>" type="text/css" />
    <script type="text/javascript" src="<c:url value="/login/DD_belatedPNG.js"/>"></script>
    <![endif]-->
    <title>移动商城商户管理平台</title>
</head>
<body>


    <div class="login-page">
        <div class="login-header">
            <a href="www.10086.cn" target="_blank"><img height="60px" src="<c:url value="/login/ChinaMobile-logo.png"/>" alt="中国移动" /></a>
        </div>
        <div class="login-body pngFix">
            <div class="login-box">
<form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true" acceptCharset="utf-8" cssClass="form-horizontal">

    <form:errors path="*" id="msg" cssClass="form-valid-error-box" delimiter=" " element="div" />

                    <div class="login-form-widget">
                        <div class="form-title">用户名</div>
                        <div class="form-input">
                            <form:input cssClass="input-txt" tabindex="1" accesskey="n" maxlength="20" path="username" autocomplete="off" htmlEscape="true" />
                        </div>
                    </div>
                    <div class="login-form-widget">
                        <div class="form-title">密码</div>
                        <div class="form-input">
                            <form:password cssClass="input-txt" tabindex="2" path="password" maxlength="20" accesskey="p" htmlEscape="true" autocomplete="off" />
                        </div>
                    </div>
    <c:if test="${true || (not empty sessionScope.error_count && sessionScope.error_count >= 0)}">
                    <div class="login-form-widget">
                        <div class="form-title">验证码</div>
                        <div class="form-input left">
                            <input id='vcode' name="vcode" accesskey="v" type="text" class="input-txt" maxlength="4" tabindex="3" AUTOCOMPLETE="off" />
                        </div>
                        <div class="valid-code left">
                            <img style="cursor: pointer;width:100px;height:30px;" id="imgCode" src='<c:url value="/captcha.htm"/>' />
                        </div>
                        <div class="change-pic left">
                            <div class="change-desc">看不清楚？</div>
                            <div class="change-href"><a id="change_verify_code" href="javascript:void(0);">换一张</a></div>
                        </div>
                        <div class="clear"></div>
                    </div>
    </c:if>
                    <div class="login-form-widget">
                        <div class="form-input">
                            <label class="checkbox inline">
                                <input type="checkbox" name="rememberMe" id="rememberMe" accesskey="r" value="true" tabindex="4"/> 一周内免登录
                            </label>
                        </div>
                    </div>
                    <div class="login-form-widget">
                        <input type="hidden" name="lt" value="${loginTicket}" />
                        <input type="hidden" name="execution" value="${flowExecutionKey}" />
                        <input type="hidden" name="_eventId" value="submit" />
                        <input type="button" class="submit-btn" tabindex="5" value="登  录" />
                    </div>
                     <div class="login-form-widget origin-login-btn">
                        <a class="origin-login" target='_blank' href='http://183.213.31.8:5002/store/'>原商城商户登录入口>></a>
                    </div>
</form:form>
            </div>
        </div>
    </div>
    <div class="login-footer">
        <div class="copyright pngFix">
            京ICP备05002571号&nbsp;|&nbsp;&nbsp;2013 中国移动 版权所有.
        </div>
        <div class="ie8div"><a href="/data/chrome/ChromeStandaloneSetup.exe">点这里获取快速、免费的网络浏览器Chrome以达到最好的显示效果</a></div>
    </div>


<script>
    $(function() {

        function checkBrowser() {
            if(!window.localStorage){
                alert('您的浏览器版本过低，请升级！');
                return false;
            }
            return true;
        }

        $('#imgCode,#change_verify_code').click(function(){
            resetVcode();
        });

        function resetVcode() {
            $('#imgCode').attr("src", '<c:url value="/captcha.htm"/>?rnd=' + Math.random());
        }

        var submitting = false;

        $('.submit-btn').on('click', function () {
            if (!submitting) {
                submitting = true;
                if (!checkBrowser()) {submitting = false; return};
                $('#fm1').submit();
            }
        });

        $(document).keyup(function(event){
            if(event.keyCode ==13){
                $(".submit-btn").trigger("click");
            }
        });

        $('#username').focus();
    })
</script>

</body>
</html>

