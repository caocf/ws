$(function () {
    var G_CTX_ROOT = '';
    $('#submit_button').click(function(){
        var returnValue = confirmDate();
        if(!returnValue){
            return;
        }
        var phone = "";
        if($("#phone_pre").val() != ""){
            phone = $("#phone_pre").val() + "-" + $("#phone_middle").val();
        }
        if($("#phone_ext").val() != ""){
            phone += "-" + $("#phone_ext").val();
        }
        $('#submit_button').hide();
        $.ajax({
            type:"POST",
            url: G_CTX_ROOT + 'becomeProvider.chtml',
            data:{
                supplierName:$("#supplierName").val(),
                businessScope:$("#businessScope").val(),
                address:$("#address").val(),
                mobile:$("#mobile").val(),
                phone:phone,
                contact:$("#contact").val(),
                verifyCode:$("#verifyCode").val()
            },
            dataType: "json",
            cache: false,
            success: function (data) {
                if (data.type === 'success') {
                    alert("申请成功！");
                }else if (data.type === 'errorVerifyCode') {
                    $('#submit_button').show();
                    alert("验证码输入错误！");
                }else if (data.type === 'failure') {
                    $('#submit_button').show();
                    alert("申请失败！");
                }
            },
            error: function () {
                $('#submit_button').show();
                alert("服务暂不可用,请稍后再试！");
            }
        });
    })


    $('.change_verify_code p a').click(function () {
        $('#verifyCodeImg').attr("src","getVerifyCodeImg.chtml?random=" + Math.random());
    })

})

function confirmDate(){
    var supplierName = $("#supplierName").val();
    var businessScope = $("#businessScope").val();
    var address = $("#address").val();
    var mobile = $("#mobile").val();
    var phone_pre = $("#phone_pre").val();
    var phone_middle = $("#phone_middle").val();
    var phone_ext = $("#phone_ext").val();
    var contact = $("#contact").val();
    var verifyCode = $("#verifyCode").val();

    if(supplierName == ''){
        alert('请填写供应商名称！');
        $("#supplierName").focus();
        return false;
    }
    if(supplierName.length >100 || supplierName.length != supplierName.replace("%","").length){
        alert('供应商名称填写错误：字数大于100、或含有%号！');
        $("#supplierName").focus();
        return false;
    }
    if(businessScope == ''){
        alert('请填写经营范围！');
        $("#businessScope").focus();
        return false;
    }
    if(businessScope.length >100 || businessScope.length != businessScope.replace("%","").length){
        alert('经营范围填写错误：字数大于100、或含有%号！');
        $("#businessScope").focus();
        return false;
    }
    if(address == ''){
        alert('请填写商户所在地！');
        $("#address").focus();
        return false;
    }
    if(address.length >100 || address.length != address.replace("%","").length){
        alert('商户所在地填写错误：字数大于100、或含有%号！');
        $("#address").focus();
        return false;
    }
    if(mobile == '' && !(phone_middle != '' && phone_pre != '')){
        alert('手机、固定电话必填一项！');
        $("#mobile").focus();
        return false;
    }
    if(mobile != '' && !isMobile(mobile)){
        alert('手机号码不正确，请重新填写！');
        $("#mobile").focus();
        return false;
    }
    if(phone_pre != '' && (!isNumber(phone_pre) || phone_pre.length >4 || phone_pre.length <3)){
        alert('区号不正确，请重新填写！');
        $("#phone_pre").focus();
        return false;
    }
    if(phone_pre != '' && phone_middle == ''){
        alert('请填写电话号码！');
        $("#phone_middle").focus();
        return false;
    }
    if(phone_middle != '' && (!isNumber(phone_middle) || phone_middle.length > 11)){
        alert('电话号码不正确，请重新填写！');
        $("#phone_middle").focus();
        return false;
    }
    if(phone_ext != '' && (!isNumber(phone_ext) || phone_ext.length > 11)){
        alert('分机不正确，请重新填写！');
        $("#phone_ext").focus();
        return false;
    }
    if(contact == ''){
        alert('请填写联系人！');
        $("#contact").focus();
        return false;
    }
    if(contact.length >25 || contact.length != contact.replace("%","").length){
        alert('联系人填写错误：名字太长、或含有%号！');
        $("#contact").focus();
        return false;
    }
    if(verifyCode == ''){
        alert('请填验证码！');
        $("#verifyCode").focus();
        return false;
    }
    return true;
}