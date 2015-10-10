<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../../css/dealList.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.form.js"></script>
<script type="text/javascript" src="../../js/imgCode.js"></script>
<script type="text/javascript" src="../../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../../js/extCenterBasic.js"></script>


<!--[if lt IE 9]>
<script type="text/javascript" src="js/jquery.placeholder.js"></script>
<script type="text/javascript" src="js/ie.js"></script>
<![endif]-->
<style type="text/css">
	body,ul,li {
		margin:0;
		padding:0;
		color:#666666;
	}
	li {
		list-style: none;
	}
	button, input, select, textarea {
		border: 1px solid #DDDDDD;
		font-size: 12px;
		padding: 5px;
	}
</style>
<title>mo生活，精彩无限！</title>
</head>

<body>
	<div class="mo-float-box">
		<div class="mo-float-box-cont">
        	<div class="mo-col-desc">快绑定邮箱账号，享受mo生活的各种邮件服务吧！</div>
			<div class="mo-float-form">
				<form name="bindEmailForm" action="bindEmail/sendMail.chtml" id="bindEmailForm" method="post" class="center_basic">
					<div class="mo-bind-form">
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title">邮箱：</div>
							<div class="mo-left mo-form-input mo-form-input-txt"><input type="text" name="email" id="email" maxlength="50"/></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title">校验码</div>
							<div class="mo-left mo-form-input mo-form-input-txt"><input class="small-text" type="text" name="genValidCode" id="genValidCode" maxlength="4" /></div>
							<div class="mo-left mo-form-valid-code"><img id="imgCode" src="<c:url value="/image/bindEmail/code.chtml"/>" /></div>
							<div class="mo-left mo-form-vc-refresh"><a href="javascript:;" title="换一张" id="change_verify_code"></a></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-notitle"></div>
							<div class="mo-left mo-form-input mo-form-input-btn"><input id="sendAction" type="button" value="前往邮箱鉴定" /></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-float-desc">请在30分钟内完成邮箱校验，否则将邮箱绑定失败。</div>
						</div>
					</div>
				</form>
			</div>
            <div class="mo-col-desc">绑定邮箱后，您不仅可以用邮箱账号登录，还可以通过绑定的邮箱享受密码找回等服务。</div>
		</div>
	</div>
	<script type="text/javascript" src="../../js/cplatform.core.js"></script>
	<script type="text/javascript" src="../../js/cplatform.validator.js"></script>
	<script type="text/javascript">
		var valid = new Validator({
			id: 'bindEmailForm',
			infoElement: 'span',
			errorClass: 'error',
			validClass: 'valid',
			rules: {
				email: {required: true,email: true},
				genValidCode: {required: true}
			}
		});
		$(function() {
			$("#sendAction").click(function(){
				if(valid.checkForm()){
					var reg = /^[0-9a-zA-Z]+$/;
					if(!reg.test($('#genValidCode').val())){
						alert('验证码有误，请重新输入');
						return false;
					}
					$('#bindEmailForm').submit();
				}
			});
		});
</script>
</body>
</html>
