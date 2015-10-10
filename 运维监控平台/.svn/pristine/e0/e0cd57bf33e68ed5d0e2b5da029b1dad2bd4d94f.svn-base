$.fn.datepicker.defaults.autoclose = true;

$(function(){
    // attachAjaxBlock();
    //初始化
    Validation.autoBind();

    // default ajax form bind
    autoBindAjaxForm();

    $('.multiselect').multiselect();

    // 跳转后的提示
    var formHint = localStorage.getItem("form_hint");
    if (formHint) {
        growlSuccess(formHint);
        localStorage.removeItem("form_hint");
    }


    $.jGrowl.defaults = {
        closerTemplate : '<div>[ 关闭所有提示]</div>',
        closeTemplate : '<i class="icon16 i-close-2"></i>'
    };

    $.fn.select2.defaults.formatNoMatches = function() { return "没有找到匹配项"; };
    $.uniform.defaults.fileDefaultHtml = "请选择文件";
    $.fn.button.defaults = {
        loadingText: '提交中...'
    }

    // query block 显示、隐藏
    // $('.query-box').addClass("closed");
    var localurl = window.location.toString();
    var params = new Uri(localurl).deleteQueryParam('page').queryPairs;
    $.each(params, function(i, n) {
            if (n[1].length == 0) return;
            var k = $('#queryForm').find('[name="' + n[0] + '"]');
            if (k.length > 0) {
                $(".hide-tab-panel").show();
                $('.slide-tab li.active').addClass("selected");
            }
    });

    $(".slide-tab li.active").click(function(){
        $(".hide-tab-panel").slideToggle("fast");
        $(this).toggleClass("selected");
    });
    
    //$('.vcode').mask('AAAA AAAA AAAA AAAA');
    //$('.phone').mask('999 9999 9999');
    
    $(".price-am-chooser .add-on").click(function(){
    	if($(this).text() == "+") {
    		$(this).text("-");
    	} else {
    		$(this).text("+");
    	}
    });
    
});



/* 操作确认提示组件 */
jQuery(function() {
    var isVisible = false;
    var isProccess = false;

    var hideAllPopovers = function() {
        $('[data-confirm]').each(function() {
            $(this).parent().undelegate('click');
            $(this).popover('destroy');
        });
        isVisible = false;
    };

    function confirmPopBtnWait($this) {
        isProccess = true;
        $($this).button('loading');
    }

    $(document).on('click', '[data-confirm]',
        function(e) {
            e.stopPropagation();
            e.preventDefault();

            if (isProccess) {
                growlNotice('还有未执行完的操作，请等待其完成!');
                return;
            }
            if (isVisible) {
                hideAllPopovers();
            }
            var $this = $(this);
            var placement = 'bottom';
            var offset = $this.offset(); var dw = $('.main').width(); var dh = $('.main').height();
            //console.log('this:' + offset + ", document:" + dw + "|" + dh + ", main:" + $('.main').width() + $('.main').height());
            //console.log('left' + (dw - offset.left) + ':top' + (dh - offset.top));
            if (dw - offset.left < 150) {
                placement = 'left';
            } else if (dh - offset.top < 150) {
                placement = 'top';
            }

            $this.popover({
                title:'行动确认', content:'<div class="text-center"><p>' + $this.attr('data-confirm') + '</p></div><div class="text-center">' +
                    '<button type="button" class="btn btn-primary confirm-btn" data-loading-text="稍候">确定</button>' +
                    '&nbsp;&nbsp;<button type="button" class="btn cancel-btn">取消</button></div>'
                , placement:placement, html:true
            }).parent().delegate('button.cancel-btn', 'click', function(e) {
                    hideAllPopovers();
                    e.stopPropagation();
                })
                .delegate('button.confirm-btn', 'click', function(e) {
                    e.stopPropagation();
                    confirmPopBtnWait(this);
                    var $href = $this.attr('href');
                    if ($href=='#') return;
                    if ($this.attr('data-confirm-jump')) {
                        window.location = $href;
                        return;
                    }
                    jQuery.ajax({ type : "get", url : $href, dataType:  'json', cache:false, complete: function() {
                        hideAllPopovers();
                    }, success : function(data, textStatus, xhr) {
                        defaultProcResp(data, textStatus, xhr);
                        isProccess = false;
                    }, error: function(x, textStatus) {
                        defaultErrorHandler(x, textStatus);
                        isProccess = false;
                    }
                    });
                });
            $this.popover('show');
            isVisible = true;
        }
    );

    $(document).on('click', function(e) {
        hideAllPopovers();
    });
});

//跟踪单下拉
function followPanelDown(obj) {
	var dropPanel = $(obj).next(".follow-drop-panel");
	var objWidth = $(obj).width();
	dropPanel.css("left", $(obj).position().left - 300);
	$(".follow-drop-panel .arrow").css("left", 300);
	if(dropPanel.css("display") == "none") {
		dropPanel.show();
	} else {
		dropPanel.hide();
	}
//	$(obj).next(".follow-drop-panel").slideToggle("fast", function(){
//		var objPosition = $(obj).position();
//		var objWidth = $(obj).width();
//		$(".follow-drop-panel .arrow").css("left", (objPosition.left - objWidth / 2 + 10));
//		$(".follow-drop-panel-cont").css("left", (objPosition.left * 2 - 500));
//	});
}


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
        var msg = data.message;
        var url = data.url;
        if (url && url.indexOf('http') != 0) {
            url = G_CTX_ROOT + url;
        }

        if (msg) {
            if (url) {
                localStorage.setItem("form_hint", msg);
            } else {
                growlSuccess(msg);
            }
        }
        if (url) {
            if ($form) formBtnWait($form);
            location.href = url;
        }
        return true;
    } else {
        var msg = data.errorMessage;
        if (msg) {
            growlError(msg);
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
            formBtnWait($form);
            if ($.isFunction(beforeSubmit)) {
                return beforeSubmit.call(this, arr, $form, options);
            }
        },
        error: function(resp, statusText, c, $form) {
            formBtnNormal($form);
            defaultErrorHandler(resp, statusText);
        },
        success: function(resp, statusText, xhr, $form) {
            formBtnNormal($form);
            if (!customCallBack || customCallBack && customCallBack.call(this, resp, $form)) {
                formPostCallback(resp, statusText, xhr, $form);
            }
        }
    });
};

var autoBindAjaxForm = function() {
    var forms = $('.required-validate');
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
        message="处理结果解析异常.";
    }else if(textStatus=='timeout'){
        message="在处理请求时超时.";
    }else if(textStatus=='abort'){
        message="在处理请求时意外终止";
    }else {
        message="未知错误，请联系管理员.";
    }
    growlError(message);
}

function formBtnNormal($form) {
    var btn = $form.find(':submit');
    $(btn).button('reset');
}

function formBtnWait($form) {
    var elements = ValidationUtils.getFormElements($form);
    for(var i = 0; i < elements.length; i++)
        Validation.reset($(elements[i]));

    var btn = $form.find(':submit');
    $(btn).button('loading');
}


/********************
 * Block UI 相关
 **************************************/

var attachAjaxBlock = function() {
    $(document).ajaxStart(waitBlock).ajaxStop($.unblockUI);
}


var waitBlock = function() {
    // Console.log("waitBlock");
    $.blockUI({
        fadeIn: 0,
        fadeOut: 0,
        showOverlay: false,
        centerY: false,
        overlayCSS:  {
            backgroundColor: '#000',
            opacity:         10,
            cursor:          'wait'
        },
        message:  '<h1>请稍候...</h1>',
        css: {
            top:'10px',
            left:'',
            right:'10px',
            border: 'none',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity:.5,
            color: '#fff'
        } });
}




function growlNotice(title) {
    $.jGrowl(title, {
        group: 'notice', position :'center'
    });
};

function growlInfo(title) {
    $.jGrowl("<i class='icon16 i-info'></i> " + title, {
        group: 'info', position :'center'
    });
}

function growlError(title) {
    $.jGrowl("<i class='icon16 i-cancel-circle'></i> " + title, {
        group: 'error', position :'center'
    });
}

function growlSuccess(title) {
    $.jGrowl("<i class='icon16 i-checkmark-3'></i> " + title, {
        group: 'success', position :'center'
    });
}