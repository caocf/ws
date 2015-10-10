$(document).ready(function() {
	$("[type='checkbox'], [type='radio'], [type='file'], select").not('.toggle, .select2, .multiselect').uniform();
	if($(".login-form-widget #f_username").val() != "") {
		$(".removeTxt").css("display","block");
	}
	$(".login-form-widget #f_username").keyup(function(){
		if($(this).val() != "") {
			$(".removeTxt").css("display","block");
		} else {
			$(".removeTxt").css("display","none");
		}
	});
	$(".removeTxt").click(function(){
		$(".login-form-widget #f_username").val("");
		$(this).css("display","none");
	});

    var osObject = ieTab();
    if(osObject != null) {
        if(osObject == "IE6" || osObject == "IE7") {
            $("body").prepend("<div class='explore-upgrade-box'><div class='explore-upgrade-word'>您使用的是"+osObject+"浏览器，版本过低，不能使用我们为您提供的服务，请<a href='http://www.microsoft.com/zh-cn/download/internet-explorer-8-details.aspx'>点击升级</a>，开始您的完美体验之旅吧！</div></div>");
            return;
        }
    }

    $(".error-box-close").click(function(){
        $(".form-valid-error-box").hide();
    });

    $("#frm_login").keydown(function(e) {
        if (e.which == 13) {
            $('#frm_login').submit();
            return false;
        }
    });

    $('#f_username').focus();
    $('#frm_login').ajaxForm({
        beforeSubmit: function(a,f,o) {
            hideError();
            if($('#f_username').val().length == 0){
                $('#f_username').focus();
                showError('请输入登录用户名');
                return false;
            }
            if($('#f_password').val().length == 0){
                $('#f_password').focus();
                showError('请输入登录密码');
                return false;
            }
            if($('#f_verifycode').val().length == 0 || $('#f_verifycode').val()=='请输入验证码'){
                $('#f_verifycode').focus();
                showError('请输入验证码');
                return false;
            }
            disableSubmitBtn('登录中...');
        },
        error: function(data, statusText) {
            resetVcode();
            resetSubmitBtn('登  录');
            var message;
            var statusErrorMap = {
                '400' : "(400)错误的请求",
                '401' : "(401)未授权的访问请求",
                '403' : "(403)服务器拒绝了此次请求",
                '500' : "(500)服务器处理错误",
                '503' : "(503)服务不可用"
            };
            if (data.status && data.status > 399) {
                message =statusErrorMap[data.status];
                if(!message){
                    message="由于网络或服务器原因，导致登录失败，请稍候再试！";
                } else {
                    message += '，请稍候再试！';
                }
            }else if(statusText=='parsererror'){
                message="处理登录结果解析异常，请稍候再试！";
            }else if(statusText=='timeout'){
                message="在处理登录请求时超时，请稍候再试！";
            }else if(statusText=='abort'){
                message="在处理登录请求时意外终止，请稍候再试！";
            }else {
                message="由于网络或服务器原因，导致登录失败，请稍候再试！";
            }
            showError(message);

        },
        success: function(html) {
            if(html.length > 0){
                showError(html);
                resetSubmitBtn('登  录');
                resetVcode();
            }else{
                disableSubmitBtn('登录成功,正在转到首页...');
                if (paramp.length > 0) {
                    location.href = paramp;
                } else {
                    location.href = G_CTX_ROOT + "/";
                }
            }
        }});

    function disableSubmitBtn(txt) {
        $('input[type="submit"]').attr('disabled','disabled').val(txt).addClass("disabled");
    }

    function resetSubmitBtn(txt) {
        $('input[type="submit"]').attr('disabled', null).val(txt).removeClass("disabled");
    }

    $('#imgCode,#change_verify_code').click(function(){
        resetVcode();
    });

    function showError(text) {
        $(".form-valid-error-box .error-desc").html(text);
        $(".form-valid-error-box").show();
    }

    function hideError() {
        $(".form-valid-error-box").hide();
    }

    function resetVcode() {
        $('#imgCode').attr("src", G_CTX_ROOT + "/image/code?r=" + Math.random());
    }
    
    /**
     * 获取IE版本
     */
    function ieTab(){
    	var bs = "非IE内核";
		user_agent = navigator.userAgent.toLowerCase();

		if (user_agent.indexOf("msie 10.0")>-1&&user_agent.indexOf("trident/6.0")>-1){
			bs = "IE10";
		}else if (user_agent.indexOf("msie 9.0")>-1&&user_agent.indexOf("trident/6.0")>-1){
			bs = "IE10（兼容模式）";
		}else if (user_agent.indexOf("msie 8.0")>-1&&user_agent.indexOf("trident/6.0")>-1){
			bs = "IE10（兼容模式）";
		}else if (user_agent.indexOf("msie 7.0")>-1&&user_agent.indexOf("trident/6.0")>-1){
			bs = "IE10（兼容模式）";
		}else if(user_agent.indexOf("msie 9.0")>-1) {
			bs = "IE9";
		}else if (user_agent.indexOf("msie 7.0")>-1&&user_agent.indexOf("trident/5.0")>-1){
			bs = "IE9（兼容模式）";
		}else if (user_agent.indexOf("msie 8.0")>-1&&user_agent.indexOf("trident/5.0")>-1){
			bs = "IE9（兼容模式）";
		}else if(user_agent.indexOf("msie 8.0")>-1) {
			bs = "IE8";
		}else if(user_agent.indexOf("msie 7.0")>-1&&user_agent.indexOf("trident/4.0")>-1){
			bs = "IE8（兼容模式）";
		}else if(user_agent.indexOf("msie 7.0")>-1){
			bs = "IE7";
		}else if(user_agent.indexOf("msie 6.0")>-1){
			bs = "IE6";
		}
		return bs;
    }

});