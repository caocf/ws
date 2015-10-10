<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/static/ui/login/login.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/static/ui/js/plugins/forms/uniform/uniform.default.css"/>" rel="stylesheet" type="text/css" />

    <script src="<c:url value="/static/global.js"/>"></script>
    <script src="<c:url value="/static/ui/js/jquery.1.8.3.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<c:url value="/static/ui/etc/jquery.form.js"/>"></script>
    <script src="<c:url value="/static/ui/js/plugins/forms/uniform/jquery.uniform.min.js"/>"></script>
    <script src="<c:url value="/static/ui/login/login.js"/>"></script>

    <!--[if IE 6]>
    <link rel="stylesheet" href="<c:url value="/static/ui/login/ie6.css"/>" type="text/css" />
    <script type="text/javascript" src="<c:url value="/static/ui/login/DD_belatedPNG.js"/>"></script>
    <![endif]-->
    <title>mo生活运维监控系统</title>
</head>
<body>
<div class="login-page">
    <div class="login-header">
        <a href="www.10086.cn" target="_blank"><img height="60px" src="<c:url value="/static/ui/login/ChinaMobile-logo.png"/>" alt="中国移动" /></a>
    </div>
    <div class="login-body pngFix">
        <div class="login-box">
            <form class="form-horizontal" id="frm_login" action="<c:url value="/login"/>" method="POST">
                <div class="form-valid-error-box" style="display:none;">
                    <div class="error-desc left"></div>
                    <div class="error-box-close right"><a href="javascript:void(0);" class="pngFix"></a></div>
                    <div class="clear"></div>
                </div>
                <div class="login-form-widget">
                    <div class="form-title">用户名</div>
                    <div class="form-input">
                        <input class="input-txt" name="username" id="f_username" autocomplete="off" type="text" maxlength="20" tabindex="1" />
                        <a href="javascript:void(0);" class="removeTxt" style="display: none;"></a>
                    </div>
                </div>
                <div class="login-form-widget">
                    <div class="form-title">密码</div>
                    <div class="form-input">
                        <input class="input-txt" name="password" id="f_password" type="password" maxlength="20" tabindex="2" />
                    </div>
                </div>
                <div class="login-form-widget">
                    <div class="form-title">验证码</div>
                    <div class="form-input left">
                        <input id='f_verifycode' name="verifyCode" type="text" class="input-txt" maxlength="4" tabindex="3" AUTOCOMPLETE="off" />
                    </div>
                    <div class="valid-code left">
                        <img style="cursor: pointer;width:100px;height:30px;" id="imgCode" src='<c:url value="/image/code"/>' />
                    </div>
                    <div class="change-pic left">
                        <div class="change-desc">看不清楚？</div>
                        <div class="change-href"><a id="change_verify_code" href="javascript:void(0);">换一张</a></div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="login-form-widget">
                    <div class="form-input">
                        <label class="checkbox inline">
                            <input type="checkbox" name="savelogin" id="f_savelogin" value="true" tabindex="4"/> 两周内免登录
                        </label>
                    </div>
                </div>
                <div class="login-form-widget">
                    <input type="submit" class="submit-btn" tabindex="5" value="登  录" />
                </div>
           <!-- <div class="login-form-widget">
                    <div class="register-href">
                        <a href="#">免费注册</a>
                    </div>
                </div> -->
            </form>
        </div>
    </div>
</div>
<div class="login-footer">
    <div class="copyright pngFix"></div>
</div>


<script type='text/javascript'>
    <!--
    /* var paramp = '${param.p}'; */
    //-->
   var paramp = '';
   var pp = location.href.split('p=')[1];
   if(pp != null && pp != undefined){
	   paramp = pp; 
   }
</script>
</body>
</html>