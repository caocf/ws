$(function() {

    $.ajax({
        url: G_CTX_ROOT + "cart/show-data.chtml",
        dataType: "json",
        cache: false,
        success: function(data) {
            buildPage(data);
            $('.checkbox_all').attr('checked', true);
            $('.checkbox_item').attr('checked', true);
            $('.shopcontainer').mallcart();
            $('.removeall').click($.fn.mallcart.delall);
        },
        error:function(){
            simpleWarn("服务暂不可用,请稍后再试");
        }
    });


    function buildPage(data) {
        $('.preload').hide();
        if (!$.isArray(data.cartGroups) || ($.isArray(data.cartGroups) && data.cartGroups.length == 0)) {
            $('.emptycontainer').show();
            return;
        }

        $('.removeall').show();

        var cartGroups = data.cartGroups;
        for (var i = 0; i < cartGroups.length; i++) {
            var cartGroup = cartGroups[i];
            var shopcontainer = $('#template .shopcontainer').clone().insertBefore(".editproductBox > span").show();
            shopcontainer.find('tr.bt1px a')
                .attr('href', countPath(3, cartGroup.groupKey.shopId))
                .text(cartGroup.shopName);

            var cartItems = cartGroup.cartItems;
            for (var j = 0; j < cartItems.length; j++) {
                var cartItem = cartItems[j]; var itemInfo = cartItem.itemInfo;
                var itemcontainer = $('#template .order_item').clone().appendTo(shopcontainer.find('table:first tbody')).show();

                itemcontainer.find('.checkbox_item').attr('name', 'xs_' + j).val(cartItem.itemKey);

                itemcontainer.find('.order_item_img a')
                    .attr('href', countPath(1, itemInfo.item.id))
                    .find('img').attr('src', countPath(2, itemInfo.item.id, itemInfo.item.webPath));
                itemcontainer.find('.order_item_good a')
                    .attr('href', countPath(1, itemInfo.item.id))
                    .text(itemInfo.item.name);

                // 商品属性
                var propsText = "";
                var props = itemInfo.properties;
                if ($.isArray(props)) {
                    $.each(props, function(i, n){
                        if (n.priceRule == '1')
                            propsText += n.propertyName + ': ' + n.propertyValue + ", ";
                    });
                }
                if (propsText.length > 0) itemcontainer.find('.order_item_prop').text(propsText.substr(0, propsText.length - 2)).show();

                // 温馨提示
//                var warmPrompt = $.trim(itemInfo.item.warmPrompt);
//                if (warmPrompt && warmPrompt.length > 0 && warmPrompt != 'null') {
//                    itemcontainer.find('.order_item_prompt').text('温馨提示：' + warmPrompt).show();
//                }
//
//                var cash_idgoods=itemInfo.item.cashIdgoods;
//                var coin_idgoods=itemInfo.item.coinIdgoods;
//
//                var payString="";
//                if(cash_idgoods===1&&coin_idgoods!==1){
//                    payString ="仅限现金支付";
//                }
//                if(coin_idgoods===1&&cash_idgoods!==1){
//                    payString ="仅限商城币支付";
//                }
//                if(payString.length>0){
//                    itemcontainer.find('.order_item_payType').text('支付方式：' + payString).show();
//                }

                itemcontainer.find('.itemprice').text(itemInfo.item.shopPrice);
                itemcontainer.find('input[name="quantity"]')
                    .attr('max', itemInfo.item.stocknum)
                    .val(cartItem.quantity);

            }
        }
    }

});



(function($) {

    $.fn.mallcart = function() {

        return this.each(function() {
            var $box = $(this);
            bindAllCheckBox($box);
            bindFormSubmit($box);

            var orderItems = $box.find('.order_item');

            orderItems.each(function() {
                var $item = $(this);
                $item.find("[func='minus']").bind('click', {box: $box, item: $item, count: -1}, plus);
                $item.find("[func='plus']").bind('click', {box: $box, item: $item, count: 1}, plus);
                $item.find("[func='cal_price']").bind('blur', {box: $box, item: $item, count: 0}, plus);
                $item.find("[func='cal_price']").bind('change', {box: $box, item: $item, count: 0}, plus);
                $item.find("[func='cal_price']").bind('change', {box:$box}, calPriceEvent);
                $item.find("[func='del']").bind('click', {box:$box, item: $item}, del);
                $item.find("[func='cal_price']").change();

                var itemprice = $item.find('.itemprice');
                itemprice.text( toDecimal2(itemprice.text()) );
            });

        });

        function del(event) {
            var $item = event.data.item;
            var $box = event.data.box;
            var itemKey = $item.find('.checkbox_item').val();
            del_confirm(event, "确认要删除该商品吗？", function() {
                jsonReq('cart/del.chtml', {itemKey: itemKey}, function() {
                    $item.remove();
                    if ($box.find('.order_item').length == 0) {
                        $box.remove();
                    } else {
                        $box.find('.order_item').find("[func='cal_price']").change();
                    }
                    if ($('.editproductBox .shopcontainer').length == 0) {
                        $('.emptycontainer').show();
                        $('.removeall').hide();
                    }
                });
            });
        }


        function calPrice($box) {
            var shopallprice = $box.find('.shopallprice');
            $box.find('.order_item').each(function() {
                var itemallprice = $(this).find('.itemallprice');
                var itemquantity = $(this).find('input[name=quantity]');
                var itemprice = $(this).find('.itemprice');
                itemallprice.text( toDecimal2(toDecimal(itemquantity.val()) * toDecimal(itemprice.text())) );
            });

            var total = 0;
            $box.find('.itemallprice').each(function() {
                var checked = $(this).closest('tr').find('.checkbox_item').attr('checked') != undefined;
                if (checked) total += toDecimal($(this).text());
            });
            shopallprice.text('￥' + toDecimal2(total));
        }


        function calPriceEvent(event) {
            var $box = event.data.box;
            calPrice($box);
        }

        function plus(event) {
            var count = event.data.count;
            var $item = event.data.item;
            var itemKey = $item.find('.checkbox_item').val();
            var qitem = $item.find("[name='quantity']");
            var max = parseInt(qitem.attr('max'));
            var qitemnum = parseInt(qitem.val());
            count = qitemnum + count;
            if (count <= 0) {
                count = 1;
            }
            if (max > 0 && count > max) {
                count = max;
            }
            if (count > 200) {
                count = 200;
            }

            jsonReq('cart/changeCount.chtml', {itemKey:itemKey, count:count}, function() {
                $item.find("[name='quantity']").val(count);
                $item.find("[name='quantity']").change();
            });

        }

        function bindAllCheckBox($this) {
            $this.find('.checkbox_all').click(function() {
                var checked = $(this).attr('checked') != undefined;
                $this.find('.checkbox_item').attr('checked', checked);
                calPrice($this.closest('.shopcontainer'));
            });

            $this.find('.checkbox_item').click(function() {
                var notsel = $this.find('.checkbox_item').not(":checked");
                $this.find('.checkbox_all').attr('checked', (notsel.length == 0));
                calPrice($this.closest('.shopcontainer'));
            });
        };

    };

    function bindFormSubmit($this) {
        var lock = false;
        $this.find('.confirmpost').find('img').click(function() {
            if(isNaN($("input[name='quantity']").val())){
                simpleAlert("商品数量，请输入合法的数字");
                return;
            }
            if (lock) { simpleAlert("正在提交数据，请稍等"); return; }
            var checks = $this.find('.checkbox_item:checked');
            if (checks.length == 0) {
                simpleAlert('请至少选择一件商品');
                lock=false;
                return;
            } else {
                var items = "";
                checks.each(function(){
                    items += $(this).val() + ",";
                });
                items = items.substring(0,items.length-1);
                // $this.find("[name='items']").val(items);
                // $this.find('.postv').submit();
                $.cookie('confirm_items', items, { path: '/' });
                window.location = '../order/confirm.chtml';
                lock = true;
            }
        });
    }


    $.fn.mallcart.delall = function() {
        simpleConfirm("您确定清空购物车里面的所有物品吗？", "取消", function() {
            jsonReq('cart/delall.chtml', {}, function() {
                $('.shopcontainer').remove();
                $('.emptycontainer').show();
                $('.removeall').hide();
            });
        });
    };



})(jQuery);

