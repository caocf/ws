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
		<div class="mo-float-box-title">您正在进行邮箱帐号修改</div>
		<div class="mo-float-box-cont">
			<div class="mo-action-step">
				<ul class="mo-two-step">
					<li class="mo-step-one current">确认新邮箱</li>
					<li class="mo-step-two">修改成功</li>
				</ul>
			</div>
			<div class="mo-float-form">
				<form name="editEmailForm" action="editEmail/sendMail.chtml" id="editEmailForm" method="post" class="center_basic">
					<div class="mo-bind-form">
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title">新邮箱号：</div>
							<div class="mo-left mo-form-input mo-form-input-txt"><input type="text" name="email" id="email" maxlength="50" /></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-notitle"></div>
							<div class="mo-left mo-form-input mo-form-input-btn"><input id="sendAction" type="button" value="发送校验邮件" /></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-float-desc">请在30分钟内完成邮箱校验，否则将邮箱绑定失败。</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../../js/cplatform.core.js"></script>
	<script type="text/javascript" src="../../js/cplatform.validator.js"></script>
	<script type="text/javascript">
		var valid = new Validator({
			id: 'editEmailForm',
			infoElement: 'span',
			errorClass: 'error',
			validClass: 'valid',
			rules: {
				email: {required: true,email: true}
			}
		});
		$(function() {
			$("#sendAction").click(function(){
				if(valid.checkForm()){
					$('#editEmailForm').submit();
				}
			});
		});
	</script>
</body>
</html>
