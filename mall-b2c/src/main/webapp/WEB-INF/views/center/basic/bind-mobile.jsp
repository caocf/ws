<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../../css/dealList.css" rel="stylesheet" type="text/css" />
<link href="../../css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.form.js"></script>
<script type="text/javascript" src="../../js/static.chtml"></script>
<script type="text/javascript" src="../../js/imgCode.js"></script>
<script type="text/javascript" src="../../js/cart-common.js"></script>
<script type="text/javascript" src="../../js/sendMessage.js"></script>
<script type="text/javascript" src="../../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../../js/extCenterBasic.js"></script>
<script type="text/javascript" src="../../js/jquery.simplemodal.js"></script>
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
			<div class="mo-float-form">
				<form name="bindMobileForm" action="bindMobile.chtml" id="bindMobileForm" method="post" class="center_basic">
					<div class="mo-bind-form">
						<div class="mo-form-widght">
							<div>快绑定手机号，享受mo生活的各种手机类服务吧！</div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title">手机号：</div>
							<div class="mo-left mo-form-input mo-form-input-txt"><input type="text" name="terminalId" id="terminalId" maxlength="20" value=""/>
							</div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
                            <div class="mo-left mo-form-notitle"></div>
                            <div class="mo-left mo-form-input mo-form-valid-btn"><input id="getMessageCode" type="button" value="获取校验码" /></div>
                            <div class="mo-clear"></div>
                        </div>
                        <div class="mo-form-widght mo-hide-panel mo-send-msg-panel">
                            <div class="mo-left mo-form-notitle"></div>
                            <div class="mo-left mo-form-input mo-form-input-txt"><input class="small-text" type="text" id="validCode3" name="validBindMobileCode" maxlength="4"/></div>
                            <div class="mo-left mo-form-valid-code"><img style="cursor:pointer;" id="imgBindMoblieCode" src="<c:url value="/image/bindMoblie/code.chtml"/>" /></div>
                            <div class="mo-left mo-form-sendmsg-btn"><input id="sendMessageCode" type="button" value="确 认" /></div>
                            <div class="mo-clear"></div>
                        </div>
                        <div class="mo-form-widght mo-hide-panel mo-resend-msg-time-panel">
                            <div class="mo-left mo-form-notitle"></div>
                            <div class="mo-left mo-resend-msg-time mo-col-desc"></div>
                            <div class="mo-clear"></div>
                        </div>
                        <div class="mo-form-widght">
                            <div class="mo-left mo-form-title">校验码：</div>
                            <div class="mo-left mo-form-input mo-form-input-txt"><input type="text" class="required" name="validCode" id="validCode" maxlength="6"/></div>
                            <div class="mo-clear"></div>
                        </div>
						<div class="mo-form-widght">
							<input type="hidden" id="backUrl" name="backUrl" value="${backUrl }"/>
							<div class="mo-left mo-form-notitle"></div>
							<div class="mo-left mo-form-input mo-form-input-btn"><input id="sendAction" type="button" value="确 认" /></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div>绑定手机号后，您不仅可以直接用手机号登录，还可以购买mo生活的各类优惠商品，享受mo生活的的贴心短、彩信服务。</div>
						</div>
						<div class="mo-form-widght">
							<div style="font-weight: bold;">注：现仅支持江苏移动号码</div>
							<div class="mo-clear"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="foo" style="display:none;">
		<span class="simplemodal-close">Close</span>
	</div>
	<script type="text/javascript" src="../../js/cplatform.core.js"></script>
	<script type="text/javascript" src="../../js/cplatform.validator.js"></script>
	<script type="text/javascript">
		var valid = new Validator({
			id: 'bindMobileForm',
			infoElement: 'span',
			errorClass: 'error',
			validClass: 'valid',
			rules: {
				terminalId: {required:true,mobileno: true},
				validCode: {required:true}
			}
		});
		$(function() {
			$("#getMessageCode").click(function() {
				$('#validCode3').val('');
				resetVcode("imgBindMoblieCode", "/image/bindMoblie/code.chtml?r=");
				if(valid.check(document.getElementById("terminalId"))){
					$(".mo-send-msg-panel").removeClass("mo-hide-panel");
				}else{
					$(".mo-send-msg-panel").addClass("mo-hide-panel");
				}
			});
			
			
			$("#getMessageCode").removeAttr("disabled");
			$("#sendMessageCode").click(function() {
				if(!$('#validCode3').val() || $.trim($('#validCode3').val()).length != 4){
					return ;
				}else{
					$('#validCode3').blur();
				}
				//发送短信码请求
				Message.send();
			});
			
			$('#sendAction').click(function(){
				if(valid.checkForm()){
					var reg = /^[0-9a-zA-Z]+$/;
					if(!reg.test($('#validCode3').val())){
						alert('验证码有误，请重新输入');
						return false;
					}
					$('#bindMobileForm').submit();
				}
			});
		});
	</script>
</body>
</html>
