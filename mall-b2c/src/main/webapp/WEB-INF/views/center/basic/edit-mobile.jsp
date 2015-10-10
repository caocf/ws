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
<script type="text/javascript" src="../../js/static.chtml"></script>
<script type="text/javascript" src="../../js/imgCode.js"></script>
<script type="text/javascript" src="../../js/sendMessage.js"></script>
<script type="text/javascript" src="../../js/cart-common.js"></script>
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
		<div class="mo-float-box-title">您正在进行手机号修改</div>
		<div class="mo-float-box-cont">
			<div class="mo-action-step">
				<ul class="mo-three-step">
					<li class="mo-step-one current">核实原手机号</li>
					<li class="mo-step-two">校验新手机号</li>
					<li class="mo-step-three">修改成功</li>
				</ul>
			</div>
			<div class="mo-float-form">
				<form name="editMobileFormStep1" action="" id="editMobileFormStep1" method="post">
					<div class="mo-bind-form">
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title">原手机号：</div>
							<div class="mo-left mo-form-input mo-form-input-txt"><input type="text" name="oldMobile" id="oldMobile" maxlength="20" /></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
                            <div class="mo-left mo-form-notitle"></div>
                            <div class="mo-left mo-form-input mo-form-valid-btn"><input id="getMessageCode" type="button" value="获取校验码" /></div>
                            <div class="mo-clear"></div>
                        </div>
                        <div class="mo-form-widght mo-hide-panel mo-send-msg-panel">
                            <div class="mo-left mo-form-notitle"></div>
                            <div class="mo-left mo-form-input mo-form-input-txt"><input class="small-text" type="text" id="validCode3" name="validEditMobileStep1Code" maxlength="4" /></div>
                            <div class="mo-left mo-form-valid-code"><img style="cursor:pointer;" id="img-edit-moblie-step1-Code" src="<c:url value="/image/editMoblie/step1/code.chtml"/>" /></div>
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
                            <div class="mo-left mo-form-input mo-form-input-txt"><input type="text" name="validCode" id="validCode" maxlength="6" /></div>
                            <div class="mo-clear"></div>
                        </div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-notitle"></div>
							<div class="mo-left mo-form-input mo-form-input-btn"><input id="nextAction" type="button" value="确 认" /></div>
							<div class="mo-clear"></div>
						</div>
					</div>
					</form>
					<form name="editMobileForm" action="editMobile/step2.chtml" id="editMobileForm" class="center_basic" method="post">
					<div class="mo-bind-form change-show mo-hide-panel">
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title">新手机号：</div>
							<div class="mo-left mo-form-input mo-form-input-txt">
								<input type="text" name="terminalId" id=terminalId maxlength="20" />
								<div id="terminalIdSpan" data-role="validateInfo" for="terminalId" style="display:none;color:#FF0000;"></div>
							</div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
                            <div class="mo-left mo-form-notitle"></div>
                            <div class="mo-left mo-form-input mo-form-valid-btn"><input id="getMessageCode2" type="button" value="获取校验码" /></div>
                            <div class="mo-clear"></div>
                        </div>
                        <div class="mo-form-widght mo-hide-panel mo-send-msg-panel2">
                            <div class="mo-left mo-form-notitle"></div>
                            <div class="mo-left mo-form-input mo-form-input-txt"><input class="small-text" type="text" id="validCode4" name="validEditMobileStep2Code" maxlength="4" /></div>
                            <div class="mo-left mo-form-valid-code"><img style="cursor:pointer;" id="img-edit-moblie-step2-Code" src="<c:url value="/image/editMoblie/step2/code.chtml"/>" /></div>
                            <div class="mo-left mo-form-sendmsg-btn"><input id="sendMessageCode2" type="button" value="确 认" /></div>
                            <div class="mo-clear"></div>
                        </div>
                        <div class="mo-form-widght mo-hide-panel mo-resend-msg-time-panel2">
                            <div class="mo-left mo-form-notitle"></div>
                            <div class="mo-left mo-resend-msg-time2 mo-col-desc"></div>
                            <div class="mo-clear"></div>
                        </div>
                        <div class="mo-form-widght">
                            <div class="mo-left mo-form-title">校验码：</div>
                            <div class="mo-left mo-form-input mo-form-input-txt">
                            	<input type="text" name="validCode2" id="validCode2" maxlength="6" />
                            	<div id="validCode2Span" data-role="validateInfo" for="validCode2"  class="error" style="display:none;color:#FF0000;"></div>
                            </div>
                            <div class="mo-clear"></div>
                        </div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-notitle"></div>
							<div class="mo-left mo-form-input mo-form-input-btn"><input id="sendAction" type="button" value="确 认" /></div>
							<div class="mo-clear"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../../js/cplatform.core.js"></script>
	<script type="text/javascript" src="../../js/cplatform.validator.js"></script>
	<script type="text/javascript">
		//验证手机号
		function checkTerminalId(){
			var mobile = $('#terminalId').val();
			$('#terminalIdSpan').hide();
			$('#terminalId').removeClass('error');
			$('#terminalIdSpan').text('');
			if(!mobile){
				$('#terminalIdSpan').text('此项不能为空');
				$('#terminalId').addClass('error');
				$('#terminalIdSpan').show();
				return false;
			}else if(!/^(((13)[5-9]{1})|((15)[0,1,2,7,8,9]{1})|(188))[0-9]{8}$|(^((134)[0-8]{1})[0-9]{7}$)/.test(mobile)){
				$('#terminalIdSpan').text('请输入合法的移动号码');
				$('#terminalId').addClass('error');
				$('#terminalIdSpan').show();
				return false;
			}
			return true;
		}
	
		// 验证验证码
		function checkValidCode(){
			var validCode = $('#validCode2').val();
			$('#validCode2Span').hide();
			$('#validCode2').removeClass('error');
			$('#validCode2Span').text('');
			if(!validCode){
				$('#validCode2Span').text('此项不能为空');
				$('#validCode2').addClass('error');
				$('#validCode2Span').show();
				return false;
			}else if(!/^[0-9a-zA-Z]+$/.test(validCode)){
				$('#validCode2Span').text('验证码有误, 请重新输入');
				$('#validCode2').addClass('error');
				$('#validCode2Span').show();
				return false;
			}
			return true;
		}
	
		var validStep1 = new Validator({
			id: 'editMobileFormStep1',
			infoElement: 'span',
			errorClass: 'error',
			validClass: 'valid',
			rules: {
				oldMobile: {required:true,mobileno: true},
				validCode: {required:true}
			}
		});
		$(function() {
			$("#getMessageCode").click(function() {
				$('#validCode3').val('');
				resetVcode("img-edit-moblie-step1-Code", "/image/editMoblie/step1/code.chtml?r=");
				if(validStep1.check(document.getElementById("oldMobile"))){
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
					var reg = /^[0-9a-zA-Z]+$/;
					if(!reg.test($('#validCode3').val())){
						alert('验证码有误，请重新输入');
						return false;
					}
					$('#validCode3').blur();
				}
				//发送短信码请求
				Message._sendEditStep1();
			});
			$("#getMessageCode2").click(function() {
				$('#validCode4').val('');
				resetVcode("img-edit-moblie-step2-Code", "/image/editMoblie/step2/code.chtml?r=");
				if(checkTerminalId()){
					$(".mo-send-msg-panel2").removeClass("mo-hide-panel");
				}else{
					$(".mo-send-msg-panel2").addClass("mo-hide-panel");
				}
			});
			$("#getMessageCode2").removeAttr("disabled");
			$("#sendMessageCode2").click(function() {
				if(!$('#validCode4').val() || $.trim($('#validCode4').val()).length != 4){
					return ;
				}else{
					var reg = /^[0-9a-zA-Z]+$/;
					if(!reg.test($('#validCode4').val())){
						alert('验证码有误，请重新输入');
						return false;
					}
					$('#validCode4').blur();
				}
				//发送短信码请求
				Message._sendEditStep2();
			});
			
			$("#nextAction").click(function(){
				if(validStep1.check(document.getElementById("oldMobile")) | validStep1.check(document.getElementById("validCode"))){
					var reg = /^[0-9a-zA-Z]+$/;
					if(!reg.test($('#validCode3').val())){
						alert('验证码有误，请重新输入');
						return false;
					}else{
						$.ajax({
					        url: "../center/editMobile/step1.chtml",
					        type: "POST",
					        dataType: 'json',
					        data: {terminalId:$('#oldMobile').val(), validCode: $('#validCode').val()},
					        success: function (data, stats) {
					        	if(data.success == true && data.editMobileStep1 == 'true'){
				            		$("#editMobileFormStep1").find(".mo-bind-form").addClass("mo-hide-panel");
									$(".change-show").removeClass("mo-hide-panel");
									$(".mo-step-one").removeClass("current");
									$(".mo-step-two").addClass("current");
				            	}
					        },
					        error: function (data) {
					            alert("请求失败");
					        },
					
						});		
					}
				}
			});
			$("#sendAction").click(function(){
				if(checkTerminalId() | checkValidCode()){
					var reg = /^[0-9a-zA-Z]+$/;
					if(!reg.test($('#validCode4').val())){
						alert('验证码有误，请重新输入');
						return false;
					}
					$('#editMobileForm').submit();
				}
			});
		});
	</script>
</body>
</html>
