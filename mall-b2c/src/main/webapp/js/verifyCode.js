var Message = {
    flag:"",
    send: function () {
        jsonReq('order/sendMessage.chtml', {
                SMSFrom:$('#SMSFrom').val()
            },
            function (data) {
                if (data.type === 'success') {

                }
            });
        var me = this;
        $('#timeb1').removeClass("col_link").addClass("col999");
        $('#timeb1').html('<span id="timeb2" class="code">60</span>秒后重新获取');
        $('#timeb1').unbind("click");
        if (this.flag !== "1") {
            timer = self.setInterval(me.addsec, 1000);
            this.flag = "1";
        }
    },

    _clearInterval: function () {
        if (this.flag === "1") {
            window.clearInterval(timer);
            this.flag = "";
        }
    },

    addsec: function () {

        var t = $('#timeb2').html();

        if (t > 0) {
            $('#timeb2').html(t - 1);
        } else {
            Message._clearInterval();
            $('#timeb1').removeClass("col999").addClass("col_link");
            $('#timeb1').html('<span id="timeb2"></span>重新获取');
            $('#timeb1').bind("click", function () {
                Message.send();
            });
        }
    }
}

$(function(){
    $("#timeb1").click(function () {

        Message.send();

    });
})

