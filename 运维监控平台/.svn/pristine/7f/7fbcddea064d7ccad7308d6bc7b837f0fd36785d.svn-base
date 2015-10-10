<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="inner_title" value="验证码及会员验证"/>
<%@ include file="../includes/t.jsp" %>

    <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#t1" data-toggle="tab"><i class="icon24 i-barcode-2 blue"></i>验证码验证</a></li>
        <li><a href="#t2" data-toggle="tab"><i class="icon24 i-accessibility blue"></i>会员身份验证</a></li>
    </ul>

    <div class="tab-content">
        <div class="tab-pane fade in active" id="t1">
            <p>验证码验证的帮助说明文字.</p>
            <form id="fm1" class="form-horizontal" action="<spring:url value="/cert/code"/>" method="post" accept-charset="utf-8">
                <fieldset>
                    <div class="input-append">
                        <input type="text" tabindex="1" class="searchfield required vcode" id="vcode" name="vcode" placeholder="请输入验证码">
                        <button class="btn valid-btn" type="submit" tabindex="-1"><i class="icon16 i-pointer"></i>验证</button>
                        <label class="error" id="advice-required-vcode" style="display:none;"></label>
                        <label class="error" id="advice-server-vcode" style="display:none;"></label>
                    </div>

                </fieldset>
            </form>
        </div>
        <div class="tab-pane fade" id="t2">
            <form id="fm2" class="form-horizontal" action="<spring:url value="/cert/member"/>" method="post" accept-charset="utf-8">
                <fieldset>
                    <p>会员验证的帮助说明文字.</p>
                    <div class="input-append">
                        <input type="text" tabindex="1" class="searchfield required phone" id="mobile" name="mobile" placeholder="请输入会员编号或手机号码">
                        <button class="btn valid-btn" type="submit" tabindex="-1"><i class="icon16 i-pointer"></i>验证</button>
                        <label class="error" id="advice-mobile-phone-mobile" style="display:none;"></label>
                        <label class="error" id="advice-required-mobile" style="display:none;"></label>
                        <label class="error" id="advice-server-mobile" style="display:none;"></label>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>


<script>
    $(function() {
        $('#vcode').focus();

        $('a[data-toggle="tab"]:first').on('shown', function (e) {
            $('#vcode').focus();
        });
        $('a[data-toggle="tab"]:last').on('shown', function (e) {
            $('#mobile').focus();
        })

        var forms = $('form');
        for(var i = 0; i < forms.length; i++) {
            var form = $(forms[i]);
            var validation = new Validation(form,{});
            ajaxFormSubmit(form, function(resp, f) {
                if (resp.success) {
                    var alert = $('<div class="alert alert-success"><button type="button" class="close" data-dismiss="alert">x</button>' +
                            '<strong><i class="icon24 i-checkmark-circle"></i><span></span></strong><br/><br/><p></p></div>');
                    alert.find('p').html(resp.msg);
                    if (resp.vcode) {
                        alert.find('span').html('验证成功！ 码号： ' + resp.vcode);
                    } else if (resp.mobile) {
                        alert.find('span').html('验证成功！ 号码： ' + resp.mobile);
                    } else if (resp.id) {
                        alert.find('span').html('验证成功！ 编号： ' + resp.id);
                    } else {
                        alert.find('span').html('验证成功！');
                    }
                    alert.insertAfter(f);
                    return false;
                }
                return true;
            }, function() {
                $('.alert').remove();
            });
        }
    });
</script>
<%@ include file="../includes/b.jsp" %>