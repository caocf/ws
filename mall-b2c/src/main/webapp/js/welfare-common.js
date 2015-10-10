try {if(document.charset.indexOf('gb')==0){document.charset='utf-8';window.location.reload();}} catch(e){};
$(function() {

    $('.price_rmb').each(function() {
        var v = toDecimal2( toDecimal($(this).text()) / 100 );
        $(this).text(v);
    });

    var lock = false;
    $('.btn_pay > a').click(function() {
        var payForm = $('#payForm').val();
        if(payForm!=null &&payForm!=""){
            var checkedNum = 0;
            $(':checkbox').each(function(){
                if($(this).attr("checked")){
                    checkedNum++;
                }
            });
            if(checkedNum ==0){
                simpleAlert("请支付方式");
                return;
            }

            $(':checkbox').each(function(){
                if($(this).attr("checked")){
                    payForm = "only_" + $(this).attr("value").toString();
                }
            });
            if($("#paynew_cash").attr("checked")){
                $(':checkbox').each(function(){
                    if($(this).not('#paynew_cash').attr("checked")){
                        payForm = "cash_and_" + $(this).attr("value").toString();
                    }
                });
            }
        }
        
        //红包支付价
        var useRedpackage=$(".pay_redpackage").text();
        var useCash = $('.pay_cash').text();
        var totalPrice = toDecimal($("#total_price").text());
        if((useRedpackage - totalPrice) >= 0){
        	useCash = 0;
        }

        var choosePayType = $(':radio:[name="paynew"]:checked').val();
        if(choosePayType==="redpackage"){
            payForm = "only_" + choosePayType;
        }
        if(choosePayType==="cash"){
            payForm = "only_" + choosePayType;
            useRedpackage = 0;
        }

        var payType = $(':radio[name="payType"]:checked').val();

        if(payForm==="only_redpackage"){
            payType = "";
            if(toDecimal(useRedpackage) != totalPrice){
                simpleAlert("支付金额不足");
                return;
            }
        }

        if (lock) { simpleAlert("正在提交数据，请稍后"); return; }
        showWaiting();
        jsonReq('../order/welfare/pay.chtml', {id: $('#order_id').text(), payType: payType, redPack: useRedpackage, cash : useCash, payForm:payForm},
        function(data) {
            if (data.type == 'success') {
                var url = G_CTX_ROOT + 'order/payment-success.chtml?orderId=' + data.orderId;
                if (data.redpack) {
                    url += '&redpack=' + data.redpack;
                }
                if (data.cash) {
                    url += '&redpack=' + data.redpack;
                }
                window.location = url;
            }
            if (data.type == 'redirect') {
                try {document.charset='gbk';} catch(e){};
                window.location = data.url;
            }
            if (data.type == 'form') {
                var form = $('<form method="POST" action=' + data.action + '"></form>');
                $('body').append(data.input);
                form.submit();
            }
            return false;
        }, function() {
            return true;
        }, function() {
            lock = false;
            hideWaiting();
        });
        lock = true;
    });
});


