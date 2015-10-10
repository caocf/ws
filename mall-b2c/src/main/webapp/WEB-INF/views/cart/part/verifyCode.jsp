<%@page contentType="text/html;charset=utf-8" %>
<p class="m15"><span style="padding-left:5.25em;">验证码：</span>
    <input class="input_code p5" autocomplete="off"
           onblur="if(this.value=='') this.value='';"
           onfocus="if(this.value=='') this.value='';" value="" type="text"
           name="verifyCode" id="verifyCode">
    <span id="timeb1" class="code col_link f12" style="cursor:pointer">免费获取</span>
</p>