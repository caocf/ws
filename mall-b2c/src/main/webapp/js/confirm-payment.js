try {if(document.charset.indexOf('gb')==0){document.charset='utf-8';window.location.reload();}} catch(e){};
$(function() {

    $('.price_rmb').each(function() {
        var v = toDecimal2( toDecimal($(this).text()) / 100 );
        $(this).text(v);
    });

    $('.price_coin').each(function() {
        var v = toDecimal2( toDecimal($(this).text()) / 100 );
        $(this).text(v);
    })

    var lock = false;
    $('.btn_pay > a').click(function() {
        var payForm = $('#payForm').val();
        if(payForm!=null && payForm!="" && $("input[name='paynew']:checkbox").length > 0){
            var checkedNum = 0;
            $(':checkbox').each(function(){
                if($(this).attr("checked")){
                    checkedNum++;
                }
            });
            if(checkedNum ==0){
                simpleAlert("请选择支付方式");
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
            }else{
                if($("#paynew_coin").attr("checked")&&$("#paynew_balance").attr("checked")){
                    payForm = "coin_and_balance";
                }
                if($("#paynew_score").attr("checked")&&$("#paynew_balance").attr("checked")){
                    payForm = "score_and_balance";
                }
            }
        }

        var verfiyCode=$("#verifyCode").val();
        var useCoin=$(".pay_coin").text();
        var useScore=$(".pay_score").text();
        var useBalance=$(".pay_bill").text();
        var totalPrice = toDecimal($("#total_price").text());

        var choosePayType = $(':radio:[name="paynew"]:checked').val();
        if(choosePayType==="coin"){
            payForm = "only_" + choosePayType;
            useScore = 0;
            useBalance = 0;
        }else if(choosePayType==="score"){
            payForm = "only_" + choosePayType;
            useCoin = 0;
            useBalance = 0;
        }else if(choosePayType==="balance"){
            payForm = "only_" + choosePayType;
            useCoin = 0;
            useScore = 0;
        }if(choosePayType==="cash"){
            payForm = "only_" + choosePayType;
            useCoin = 0;
            useScore = 0;
            useBalance = 0;
        }

        var payType = $(':radio[name="payType"]:checked').val();

        if(payForm==="only_coin" || payForm==="only_balance" || payForm==="only_score"){
            payType = "";
            if(toDecimal(useCoin) + toDecimal(useScore) + toDecimal(useBalance) != totalPrice){
                simpleAlert("支付金额不足");
                return;
            }
        }

        if(payForm==="only_score"){
            if(toDecimal(useScore)<0.1){
                simpleAlert("积分支付金额不能小于0.1元");
                return;
            }
        }else if(payForm==="cash_and_score"){
            if(toDecimal(useScore) > 0 && toDecimal(useScore)<0.1){
                simpleAlert("积分支付金额不能小于0.1元");
                return;
            }
        }else if(payForm==="score_and_balance"){
            payType = "";
            if(toDecimal(useScore) > 0 && toDecimal(useScore)<0.1){
                simpleAlert("积分支付金额不能小于0.1元");
                return;
            }
            if(toDecimal(toDecimal(useScore) + toDecimal(useBalance)) != totalPrice){
                simpleAlert("支付金额不足");
                return;
            }
        }else if(payForm==="coin_and_balance"){
            payType = "";
            if(toDecimal(toDecimal(useCoin) + toDecimal(useBalance)) != totalPrice){
                simpleAlert("支付金额不足");
                return;
            }
        }

        if(toDecimal(useCoin) + toDecimal(useScore) + toDecimal(useBalance) != 0){
            if(verfiyCode==''){
                simpleAlert("请输入验证码");
                return;
            }
        }
        

        if (lock) { simpleAlert("正在提交数据，请稍后"); return; }
        showWaiting();
        jsonReq('order/pay.chtml', {id: $('#order_id').text(), payType: payType,useCoin: useCoin,useScore:useScore,useBalance:useBalance,verifyCode:$('#verifyCode').val()
               ,payForm:payForm},
        function(data) {
            if (data.type == 'success') {
                var url = G_CTX_ROOT + 'order/payment-success.chtml?orderId=' + data.orderId;
                if (data.coin) {
                    url += '&coin=' + data.coin;
                }
                if (data.cash) {
                    url += '&cash=' + data.cash;
                }
                window.location = url;
            }
            if (data.type == 'redirect') {
                try {document.charset='gbk';} catch(e){};
                window.location = data.url;
                //var form = $('<form method="POST" action="pay-redirect.chtml"><input type="hidden" name="url"/></form>');
                //form.find(':hidden[name="url"]').val(data.url);
                //form.appendTo('body').submit();
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
    })
})

