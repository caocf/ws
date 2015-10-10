function customConfirm(title, icon, content, labelConfirm, labelCancel, fn1, fn2) {
    $.confirm({
        title: title,
        icon: icon,
        content: content,
        labConfirm: labelConfirm,
        labCancel: labelCancel,
        onConfirm: function($dialog) {
            if ($.isFunction(fn1)) {
                fn1.call(this, $dialog);
            }
            return false;
        },
        onCancel: function($dialog) {
            if ($.isFunction(fn2)) {
                fn2.call(this, $dialog);
            }
        }
    });
}

String.prototype.hashCode = function(){
    var hash = 0, i, char;
    if (this.length == 0) return hash;
    for (i = 0, l = this.length; i < l; i++) {
        char  = this.charCodeAt(i);
        hash  = ((hash<<5)-hash)+char;
        hash |= 0; // Convert to 32bit integer
    }
    return hash;
};

var requesting = [];

function jsonReq(url, data, fnSuccess, fnFail, fnFinish) {
    if ($.inArray(url.hashCode(), requesting)>-1) {
        return;
    }
    requesting.push(url.hashCode());
    $.ajax({
        url: G_CTX_ROOT + url,
        data: data,
        dataType: "json",
        cache: false,
        complete: function() {
            requesting.splice($.inArray(url.hashCode(), requesting), 1);
        },
        success: function(data) {
            if (data.success) {
                if (!fnSuccess || fnSuccess && fnSuccess.call(this, data)) {
                } else {
                    return;
                }
            } else {
                simpleAlert(data.msg);
            }
            if (fnFinish) fnFinish.call(this);
        },
        error:function(){
            if (!fnFail || fnFail && fnFail.call(this)) {
            } else {
                simpleWarn("服务暂不可用,请稍后再试");
                return;
            }
            if (fnFinish) fnFinish.call(this);
        }
    });
}


function customAlert(title, icon, content, fn) {
    $.alert({
        title: title,
        icon: icon,
        content: content,
        onClose: function($dialog) {
            if ($.isFunction(fn)) {
                fn.call(this, $dialog);
            }
        }
    });
}

function simpleConfirm(content, label, fn1,fn2) {
    customConfirm('提示', 'icon-question', content + '<br/><br/>', '确定', label, fn1, fn2);
}

function simpleWarn(msg, fn) {
    customAlert('出错了', 'icon-exclamation', msg + '<br/><br/>', fn);
}

function simpleAlert(content, fn) {
    customAlert('提示', 'icon-information', content + '<br/><br/>', fn);
}

function countPath(flag, id, extpath) {
    var l1 = Math.floor(id / (2000 * 2000));
    var l2 = Math.floor((id % (2000 * 2000)) / 2000);
    var l3 = id % 2000;
    switch (flag) {
        case 1:
            return G_CTX_ROOT + 'item/i_' + l1 + '_' + l2 + '_' + l3 + '.htm';
        case 3:
            return G_CTX_ROOT + 'shop/s_' + l1 + '_' + l2 + '_' + l3 + '.htm';
        case 4:
            return G_CTX_ROOT + 'shop/s2_' + l1 + '_' + l2 + '_' + l3 + '.htm';
        case 2:
            var path = G_IMG_ROOT + 'itemimg/' + l1 + '_' + l2 + '_' + l3 + '/';
            return path + 'N5/' + extpath;
    }
}


function toDecimal2(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return "0.00";
    }
    var f = Math.round(x*100)/100;
    if (isNaN(f)) return "0.00";
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return s;
}

function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return 0;
    }
    f = Math.round(x*100)/100;
    if (isNaN(f)) return 0;
    return f;
}

function toDecimal3(x) {
    var f = parseInt(x);
    if (isNaN(f)) {
        return 0;
    }
    return f;
}

function focusOnElm(elm) {
    $("html,body").animate({scrollTop: $(elm).offset().top - 20}, 0);
}


function hideWaiting() {
    $(window).unbind('scroll', waitingCenter);
    $(window).unbind('resize', waitingCenter);
    $('.waitbox').remove();
    $('.mask').remove();
}

function showWaiting(){
    var obj = $('<div class="waitbox"><div class="content"><div>正在处理,请稍候...</div></div></div>');
    obj.appendTo('body').show();
    $("<div class='mask'></div>").appendTo("body").show();
    waitingCenter();
    $(window).scroll(waitingCenter);
    $(window).resize(waitingCenter);
}

var waitingCenter = function() {
    var obj = $('.waitbox');
    $(obj).css({
        "position": "absolute",
        "top": ($(window).height() - obj.height()) / 2 + $(document).scrollTop(),
        "left": ($(window).width() - obj.width()) / 2
    });
}




function show_dialog(extern) {
    var default_close_function = function($diag) {
        $diag.remove();
    }

    var options = {
        extra_class: '',
        w: false,
        h: false,
        yes_text: "确定",
        yes_callback: default_close_function,
        no_text: "取消"
    }

    $.extend(options, extern);

    options.event.stopPropagation();

    var html = '<div class="i_msg ' + options.extra_class + '" >'
        + '<div class="i_msg_content">' + options.html + '</div><div class="i_msg_btns">';

    html += '<a href="#" onclick="return false;" class="i_msg_btn_hl">' + options.yes_text + '</a>';

    html += '<a href="#" onclick="return false;" class="i_msg_btn_normal">' + options.no_text + '</a>';

    html += '<span class="i_msg_arrow">◆<span class="i_msg_inner">◆</span></span></div></div>';

    var $dialog = $(html);
    $('body').append($dialog);

    if (options.w !== false) {
        $dialog.css('width', options.w);
    }
    if (options.h != false) {
        $dialog.css('height', options.h);
    }

    var l = 10;
    var j = $.browser.mozilla ? 12 : ($.browser.webkit ? 12 : 13);
    var m = $.browser.mozilla ? 10 : ($.browser.webkit ? 10 : 10);
    var o = {top: -1 * m, right: l};
    var y = {top: 1};

    var elmArrow = $dialog.find('.i_msg_arrow');
    elmArrow.css(o);
    $dialog.find('.i_msg_inner').css(y);

    var elmTarget = $(options.event.target);
    var offsetArrow = elmArrow.offset(), offsetTarget = elmTarget.offset();
    var k = {x: parseInt(offsetArrow.left, 10) + parseInt(elmArrow.width(), 10) / 2, y: parseInt(offsetArrow.top, 10)};
    var g = {x: parseInt(offsetTarget.left, 10) + parseInt(elmTarget.width(), 10) / 2, y: parseInt(offsetTarget.top, 10) + parseInt(elmTarget.height(), 10)};
    var thisposition = $dialog.position();
    $dialog.css({left: parseInt(thisposition.left) - k.x + g.x, top: parseInt(thisposition.top) - k.y + g.y});

    $dialog.find('.i_msg_btn_normal').click(function() {
        default_close_function($dialog);
    });
    $dialog.find('.i_msg_btn_hl').click(function() {
        options.yes_callback($dialog);
    });
    $(window).bind("resize", function() {
        default_close_function($dialog);
    });
    $dialog.one('clickoutside', function(e) {
        default_close_function($dialog);
    });
    return $dialog;
}

function del_confirm(event, msg, callback) {
    $('.i_msg').remove();
    var $dialog = show_dialog({
        html: msg,
        event: event,
        yes_callback: function() {
            $dialog.remove();
            if (callback) {
                callback();
            }
        }
    });
}



/*
 * jQuery outside events - v1.1 - 3/16/2010
 * http://benalman.com/projects/jquery-outside-events-plugin/
 *
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */
(function($,c,b){$.map("click dblclick mousemove mousedown mouseup mouseover mouseout change select submit keydown keypress keyup".split(" "),function(d){a(d)});a("focusin","focus"+b);a("focusout","blur"+b);$.addOutsideEvent=a;function a(g,e){e=e||g+b;var d=$(),h=g+"."+e+"-special-event";$.event.special[e]={setup:function(){d=d.add(this);if(d.length===1){$(c).bind(h,f)}},teardown:function(){d=d.not(this);if(d.length===0){$(c).unbind(h)}},add:function(i){var j=i.handler;i.handler=function(l,k){l.target=k;j.apply(this,arguments)}}};function f(i){$(d).each(function(){var j=$(this);if(this!==i.target&&!j.has(i.target).length){j.triggerHandler(e,[i.target])}})}}})(jQuery,document,"outside");