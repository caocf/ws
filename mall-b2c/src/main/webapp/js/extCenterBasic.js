
$(function(){
    // attachAjaxBlock();

    // default ajax form bind
    autoBindAjaxForm();

});



/********************
 * AJAX Form 扩展方法
 **************************************/


// 默认的ajax form submit success 回调， 执行预定义的 成功跳转、提示、错误提示等
function formPostCallback(resp, textStatus, xhr, $form) {

    if (!defaultProcResp(resp, textStatus, xhr, $form)) {
        var errors = resp.errors;
        var first;
        for (var key in errors) {
            var val = errors[key];
            if (!first) {
                first = true;
                var el = 'form:has(#' + key + ')';
                $("html,body").animate({scrollTop: $(el).offset().top - 80}, 0);
                $('#' + key).focus();
            }
            Validation.showErrorMsg('server',$('#' + key), val);
        }
    }
}


function defaultProcResp(data, textStatus, xhr, $form) {
    if (data.needLogin) {
        window.location = data.url;
        return true;
    }

    if (data.success) {
        var msg = data.msg;
        if(!msg){
        	msg = data.message;
        }
        var url = data.url;
        if (url && url.indexOf('http') != 0) {
        	if(url.indexOf('.chtml') > -1){
        		 url = url;
        	}else{
        		 url = url + '.chtml';
        	}
        	//url = G_CTX_ROOT + url + '.chtml';
        }

        if (msg) {
            if (url) {
                alert(msg);
                eval(parent.location=url);
            } else {
            	alert(msg);
            }
        }
        if (url) {
        	if(window.parent.mobileObj){
        		$.removeCookie('mobileUrl');
        		$.cookie("mobileUrl", url);
        		window.parent.mobileObj.close();
        	}else if(window.parent.emailObj){
        		$.removeCookie('emailUrl');
        		$.cookie("emailUrl", url);
        		window.parent.emailObj.close();
        	}else{
                location.href = url;
        	}
        }
        return true;
    } else {
        var msg = data.msg;
        if (msg) {
        	alert(msg);
        }
        return false;
    }
}


/**
 * 绑定表单的ajax form提交方法，
 * 默认成功后，执行成功提示、跳转、错误提示，等操作。
 * 还可以在customCallBack中执行一些后续操作
 * @param formName 绑定的表单名或对象
 * @param customCallBack success后的回调，参数为responseObject
 * @param beforeSubmit 提交回调
 */
var ajaxFormSubmit = function(formName, customCallBack, beforeSubmit) {

    $(formName).ajaxForm({
        dataType:  'json',
        beforeSubmit: function(arr, $form, options) {
            if ($.isFunction(beforeSubmit)) {
                return beforeSubmit.call(this, arr, $form, options);
            }
        },
        error: function(resp, statusText, c, $form) {
            defaultErrorHandler(resp, statusText);
        },
        success: function(resp, statusText, xhr, $form) {
            if (!customCallBack || customCallBack && customCallBack.call(this, resp, $form)) {
                formPostCallback(resp, statusText, xhr, $form);
            }
        }
    });
};

var autoBindAjaxForm = function() {
    var forms = $('.center_basic');
    for(var i = 0; i < forms.length; i++) {
        ajaxFormSubmit(forms[i], null, null);
    }
};

function defaultErrorHandler(x, textStatus) {
    var message;
    var statusErrorMap = {
        '400' : "(400)错误的请求.",
        '401' : "(401)未授权的访问请求.",
        '403' : "(403)服务器拒绝了此次请求.",
        '500' : "(500)服务器处理错误.",
        '503' : "(503)服务不可用."
    };
    if (x.status && x.status > 399) {
        message =statusErrorMap[x.status];
        if(!message){
            message="未知错误，请联系管理员.";
        }
    }else if(textStatus=='parsererror'){
        message="邮箱或手机号不存在.";
    }else if(textStatus=='timeout'){
        message="在处理请求时超时.";
    }else if(textStatus=='abort'){
        message="在处理请求时意外终止";
    }else {
        message="未知错误，请联系管理员.";
    }
    alert(message);
}




