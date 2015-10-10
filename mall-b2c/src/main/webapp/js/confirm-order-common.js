$(function () {

    var var_buynow = $('#buynow').val() == "true";

    $.ajax({
    	 url: G_CTX_ROOT + 'order/' + (var_buynow ? 'buynow-data.chtml' : 'confirm-data.chtml'),
        dataType: "json",
        cache: false,
        success: function (data) {
        	if(data && data.msg){
    		   simpleAlert(data.msg);
    		   return false;
         	}
            buildPage(data);
            confirmOrderProcess();
        },
        error: function () {
            simpleWarn("服务暂不可用,请稍后再试");
        }
    });


    function buildPage(data) {
        if (!$.isArray(data.cartItems) || ($.isArray(data.cartItems) && data.cartItems.length == 0)) {
            customConfirm('提示', 'icon-question', '抱歉，您选择的商品没有销售或者购物车为空，请选择其他商品' + '<br/><br/>',
                '返回购物车', '看看其他商品', function () {
                    window.location = '../cart/show.chtml';
                }, function () {
                    window.location = '../';
                });
            return;
        }

        if (data.invoiceContent && $.isArray(data.invoiceContent)) {
            for (var i = 0; i < data.invoiceContent.length; i++) {
                var invoice = data.invoiceContent[i];
                var content = '<li><input type="radio" value="' + invoice.ID + '" ' +
                    'class="hookbox" name="normal-normalContent" id="normal-invoice-content-' + invoice.ID +
                    '"><label for="normal-invoice-content-' + invoice.ID + '">' + invoice.NAME + '</label></li>';
                $(content).appendTo('.invoice-group ul');
            }
        }

        var cartItems = data.cartItems;
        if(data.source=='3'){//演出票
        	$("#shopName").html("提供商："+data.shopName);
        }else{
        	$('.shop_tag').attr('href', countPath(3, cartItems[0].shopId)).text(data.shopName);
        }
        

        for (var i = 0; i < cartItems.length; i++) {
            var cartItem = cartItems[i];
            var itemInfo = cartItem.itemInfo;
            var itemcontainer = $('#template #tmpl_item tr.order_item').clone().insertBefore('tr.discount_block').show();
            itemcontainer.attr('itemMode', itemInfo.item.itemMode);
            itemcontainer.attr('postFlag', itemInfo.item.postFlag);
            itemcontainer.find(':hidden[name="itemId"]').val(itemInfo.item.id);
            itemcontainer.find(':hidden[name="deliveryFee"]').val(cartItem.deliveryFee);
            itemcontainer.find(':hidden[name="deliveryType"]').val(cartItem.deliveryType);
            itemcontainer.find(':hidden[name="iseckill"]').val(itemInfo.item.iseckill);
            var itemShopPrice = itemInfo.item.shopPrice;
            if (data.groupItems) {
                for (var j = 0; j < data.groupItems.items.length; j++) {
                    var gi = data.groupItems.items[j];
                    var tmp = $('#template #tmpl_gitem ul').children().clone()
                        .appendTo(itemcontainer.find('td.tl ul')).show().parent();
                    tmp.find('.order_item_img:last a').attr('href', countPath(1, gi.id));
                    tmp.find('.order_item_img:last a img').attr('src', countPath(2, gi.id, gi.webPath));
                    tmp.find('.order_item_good:last a').attr('href', countPath(1, gi.id));
                    tmp.find('.order_item_good:last a').text(gi.name);
                }
            } else {
               var tmp = $('#template #tmpl_ngitem ul').children().clone()
                    .appendTo(itemcontainer.find('td.tl ul')).show().parent();
               if(data.source=='3'){
            	   tmp.find('.order_item_img a').attr('href', data.performTicketInfo.URL);
               	   tmp.find('.order_item_img a img').attr('src', data.performTicketInfo.IMG_PATH);
               	   tmp.find('.order_item_good a').attr('href', data.performTicketInfo.URL);       	 
               	   tmp.find('.order_item_good a').text(itemInfo.item.name);
               	   tmp.find('.order_item_good:last a').after("演出时间："+data.performTicketInfo.TICKET_TIME);
               	   $("#shop_leave_message").html("");
               	   $("#discount_block").hide();
                   $("#shop_total").html("总计：");
                   $(".order_c_alert").html("10分钟内");
                }else{
                	tmp.find('.order_item_img a').attr('href', countPath(1, itemInfo.item.id));
                	tmp.find('.order_item_img a img').attr('src', countPath(2, itemInfo.item.id, itemInfo.item.webPath));
                	tmp.find('.order_item_good a').attr('href', countPath(1, itemInfo.item.id));
                	tmp.find('.order_item_good a').text(itemInfo.item.name);
//                if (itemInfo.item.iseckill === "3" && itemInfo.marketGoodsProperty.marketPromotion != null) {
//                    var startTime = itemInfo.marketGoodsProperty.marketPromotion.promotionStartTime;
//                    var stopTime = itemInfo.marketGoodsProperty.marketPromotion.promotionEndTime;
//                    if (typeof startTime === "string" && typeof stopTime === "string") {
//                        tmp.find('.order_item_good div.panic_buy').text('特价时间:' + startTime + " - " + stopTime);
//                        var limit = itemInfo.marketGoodsProperty.numLimit;
//                        if (typeof limit === "number" && limit != 0) {
//                            $(".tpc_count_desc").text("限购" + limit + "件");
//                        }
//                        $(".cart_cheap_price").text(toDecimal2(itemInfo.marketGoodsProperty.promotionPrice)).show();
//                    }
//                }
                }
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

                var warmPrompt = $.trim(itemInfo.item.warmPrompt);
                var itemMode = itemInfo.item.itemMode;
                if (warmPrompt && warmPrompt.length > 0 && warmPrompt != 'null' && itemMode === "0") {
                    itemcontainer.find('.order_item_prompt').text('温馨提示：' + warmPrompt).show();
                }
                
                // 判断限时抢购商品信息
                if(itemInfo.item.iseckill == "9"){
            		//商品单价
            		itemShopPrice = itemInfo.item.marketPrice;
            		// 小计单价
            		var relationPrice = (itemInfo.item.shopPrice)?itemInfo.item.shopPrice:0.00;
            		// 优惠活动价格(单个商品优惠价)
            		var privilegePrice = toDecimal2(toDecimal(itemShopPrice - relationPrice));
            		itemcontainer.find("input[name='checpPrice']").val(privilegePrice);
            		itemcontainer.find("span.cart_cheap_price").text(toDecimal2(-privilegePrice)).show();
            		itemcontainer.find("a.cart_cheap_info").show();
            		// 温馨提示信息
            		var warmPrompt = $.trim(itemInfo.item.warmPrompt);
            		itemcontainer.find("div.car_cheap_info_cont").text(warmPrompt);
            		
            		// 限购数据量
                	var numLimit = itemInfo.item.userPerBuyNum;
                	itemcontainer.find("div.tpc_count_desc").text((numLimit)?'限购'+numLimit+'件':'');
                	// 设置提示信息
                	$('div.order_submit #order_submit_price').find('td.table_left').find('span.order_c_alert').text('1小时').parent().show();
                }else{
                    $('div.order_submit #order_submit_price').find('td.table_left').text('').show();
                }
            }
                        
//            if (itemInfo.item.iseckill === "3") {
//                itemShopPrice = toDecimal2(itemInfo.marketGoodsProperty.storePrice);
//            }
            
            itemcontainer.find('.order_item_price').text(itemShopPrice);
            if (var_buynow == true) {
                var tmp = $('#template #tmpl_buynow').children().clone()
                    .appendTo(itemcontainer.find('td.tpc div:first')).show().parent();
                var maxCount = itemInfo.item.stocknum;
                if (typeof data.confirm_type != undefined && data.confirm_type !== 'null' && data.confirm_type != null && data.confirm_type !== ''
                    && (data.confirm_type === "1" || data.confirm_type === "2")) {
                    maxCount = 1;
                }
                tmp.find('.order_item_quantity').attr('max', maxCount)
                    .val(cartItem.quantity);
            } else {
                var tmp = $('#template #tmpl_confirm').children().clone()
                    .appendTo(itemcontainer.find('td.tpc div:first')).show().parent();
                tmp.find('.order_item_quantity').text(cartItem.quantity);
            }
        }

        if (typeof data.isVipGoods != "undefined" && data.isVipGoods) {
            $('#order_submit_btn p').show();
        } else {
            $('#order_submit_btn p').hide();
        }

    }


    var confirmOrder = {
        ifshow: function () {
            var hasDelivery = false;
            var hasInvoice = false;
            $('.item_iter').each(function () {
                if ($(this).attr('postFlag') == '1') hasDelivery = true;
                if ($(this).attr('itemMode') == '0') hasInvoice = true;
                if ($(this).attr('postFlag') == '0' && $(this).attr('itemMode') == '0') {
                    var a = $(this).find('.order_item_good a');
                    a.after('【自提商品】');
                }
            })

            if (!hasDelivery) {
                $('.address-block').hide();
                $('.delivery_block').hide();
            } else {
                jQuery.getScript(G_CTX_ROOT + 'js/confirm-order-address.js');
            }
            if (!hasInvoice) {
                $('.invoice-block').hide();
            } else {
                jQuery.getScript(G_CTX_ROOT + 'js/confirm-order-invoice.js');
            }
        },
        _getOrderPriceAndItemIds: function (flag) {
            var deliveryFee = 0;
            var itemIds = [];

            $('.editproduct .order_item.item_iter').each(function () {
                var $this = $(this);
                var itemPrice = $this.find('.order_item_price');
                var cheapPrice = $this.find(".cart_cheap_price");
                var itemQuantity = $this.find('.order_item_quantity');
                var itemTotalPrice = $this.find('.order_item_aprice');
                var relationPrice;
                var q = getval(itemQuantity);
                if (typeof cheapPrice.text() === "string" && $.trim(cheapPrice.text()) !== ""){
                	if($this.find('input[name="iseckill"]').val() == '9' && ($this.find("input[name='checpPrice']").val())){
                		relationPrice = toDecimal(itemPrice.text()) - toDecimal($this.find("input[name='checpPrice']").val());
                		cheapPrice.text(toDecimal2(-toDecimal2(toDecimal($this.find("input[name='checpPrice']").val()) * q)));
                       
                	}else{
                		itemPrice = cheapPrice;
                	}
                }
                if($this.find('input[name="iseckill"]').val() == '9' && ($this.find("input[name='checpPrice']").val())){
                	itemTotalPrice.text(toDecimal2(toDecimal(relationPrice) * q));
                }else{
                	itemPrice.text(toDecimal2(itemPrice.text()));
                    itemTotalPrice.text(toDecimal2(toDecimal(itemPrice.text()) * q));
                }
                
                var itemid = $this.find('input[name="itemId"]').val();
                var dType = $this.find('input[name="deliveryType"]').val();
                var dFee = toDecimal($this.find('input[name="deliveryFee"]').val());
                if(flag){
          		  var curaddressId = $(".default-block input[name='aid']").val();
          		  if($('.address-block').is(":visible")){
          			if (!curaddressId) {
                        simpleAlert("请选择收货人地址信息");
                    }else{
                  	  showWaiting();
                        $.ajax({
                            type: "Post",
                            url: "logistics_fee.chtml",
                            async:false,
                            data: {quantity: q, itemId: itemid, addressId : $(".default-block input[name='aid']").val()},
                            dataType: "json",
                            success: function (data) {
                            	dFee = data;
                            	hideWaiting();
                            }
                        });
                    }	
          		  }
              	}
                deliveryFee += dFee;
                itemIds.push(itemid);
            });
     
    
            var shopProPrice = $('.shop_pro_price');
            shopProPrice.text("-" + toDecimal2(shopProPrice.text()));
      

            var deliveryPrice = $('.delivery_price');
            deliveryPrice.text(toDecimal2(deliveryFee));

            var total = 0;
            $('.order_item_aprice').each(function () {
                total += toDecimal($(this).text());
            });
            total -= toDecimal(shopProPrice.text());
            total += toDecimal(deliveryFee);
            var shopTotalPrice = $('.shop_total_price');
            shopTotalPrice.text(toDecimal2(total));

            return new Array(toDecimal2(total), itemIds);
        },
        plusPrice: function (event) {
            var count = event.data.count;
            var $item = $('.order_item');
            var qitem = $item.find(".order_item_quantity");
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
            qitem.val(count);
            confirmOrder.freshPrice(event.data.flag);
        },
        freshPrice: function (flag) {
            $('.total_price').text('￥' + confirmOrder._getOrderPriceAndItemIds(flag)[0]);
        },
        _getGiftCard: function () {
            var total = confirmOrder._getOrderPriceAndItemIds()[0];
            var ids = confirmOrder._getOrderPriceAndItemIds()[1];
            if (ids.length === 1) {
                $.ajax({
                    type: "get",
                    url: G_CTX_ROOT + "order/giftCard.chtml",
                    data: {totalCost: total, itemIds: ids.join(",")},
                    dataType: "json",
                    success: function (data) {
                        if (data.flag === "0") {
                            var html = "";
                            $.each(data.data, function () {
                                html += "<li><input type='radio' name='ljq' value='" + this.giftId + "'>" + this.cardName + "</li>"
                            });
                            $(".lj_ticket_info_hide ul").append(html);
                        }
                    }
                })
            }
        }
    };


    var confirmOrderProcess = function () {

        confirmOrder.ifshow();
        confirmOrder.freshPrice();
        confirmOrder._getGiftCard();
        $('.order_item').find("[func='minus']").bind('click', {count: -1, flag : true}, confirmOrder.plusPrice);
        $('.order_item').find("[func='plus']").bind('click', {count: 1, flag : true}, confirmOrder.plusPrice);
        $('.order_item').find(".order_item_quantity").bind('blur', {count: 0, flag : true}, confirmOrder.plusPrice);
        $('.order_item').find(".order_item_quantity").bind('change', {count: 0, flag : true}, confirmOrder.plusPrice);

    }

    var lock = false;
    $('#order_submit_btn a').click(function () {
    	//插码：“提交订单”事件码
    	 var WTids = [];
        $('.editproduct .order_item.item_iter').each(function () {
            var $this = $(this);
            var itemid = $this.find('input[name="itemId"]').val();
            WTids.push(itemid);
        });
        var n=WTids.join(';');
    	if(typeof(_tag)!='undefined'){
    		_tag.dcsMultiTrack('WT.shopping_n',n, 'WT.shopping_x','50');
    	}
        if (lock) {
            simpleAlert("正在提交数据，请稍后");
            return;
        }
        var addressId = '';
        var invoiceId = '';
        var invoiceContentId = '';
        var giftId = $(":radio[name='ljq']:checked").val();
        if ($('.address-block').is(":visible")) {
            addressId = $(".default-block input[name='aid']").val();
            if (!addressId) {
                simpleAlert("请选择收货人地址信息");
                focusOnElm('.address-block');
                return;
            }
        }
        if ($('.invoice-block').is(":visible")) {
            invoiceId = $(':radio[name="invoiceChoose"]:checked').val();
            if (invoiceId == 'new') {
                simpleAlert("请选择发票信息");
                focusOnElm('.invoice-block');
                return;
            }
            invoiceContentId = $(':radio[name="normal-normalContent"]:checked').val();
        }

        var verifyCode = '';
        if ($('#order_submit_btn p').is(':visible')) {
            verifyCode = $('#verifyCode').val();
            if (verifyCode == '') {
                simpleAlert("请输入验证码");
                return
            }
        }
        
        // 订单总价
        var totalPrice = $('.total_price').text().replace('￥', '');
        if(totalPrice && (totalPrice - 0 < 0)){
        	 simpleAlert("物流运费计算有误，请及时联系客服");
             return ;
        }
        
        var remark = $('#leave_message').val();

        var items = parseItemsString();

        showWaiting();
        jsonReq('order/purchase.chtml', {
            buynow: $('#buynow').val(),
            addressId: addressId,
            invoiceId: invoiceId,
            invoiceContentId: invoiceContentId,
            items: items,
            remark: remark,
            giftId: giftId,
            verifyCode: verifyCode
        }, function (data) {
            var orderId = data.orderId;
            $.removeCookie('confirm_items', {path: '/'});
            $.removeCookie('confirm_itemId', {path: '/'});
            $.removeCookie('confirm_quantity', {path: '/'});
            $.removeCookie('confirm_type', {path: '/'});
            $.removeCookie('confirm_businessId', {path: '/'});
            window.location = G_CTX_ROOT + 'order/payment-confirm.chtml?id=' + orderId;
            return false;
        }, function () {
            return true;
        }, function () {
            lock = false;
            hideWaiting();
        });
        lock = true;
    });


    function parseItemsString() {
        var items = [];
        $('.neworderBox .item_iter').each(function () {
            var quantity = getval($(this).find('.order_item_quantity'));
            if (quantity > 200) {
                simpleAlert("超出最大购买数量");
                return;
            }
            var item = $(this).find('input[name="itemId"]').val() + '_' + quantity;
            items.push(item);
        })
        return items.join(',');
    };
});

function getval(itemQuantity) {
    if (itemQuantity.is('div')) {
        return itemQuantity.text();
    } else if (itemQuantity.is('input')) {
        return itemQuantity.val();
    }
}

if (typeof String.prototype.startsWith != 'function') {
    String.prototype.startsWith = function (str) {
        return this.slice(0, str.length) == str;
    };
}

if (typeof String.prototype.endsWith != 'function') {
    String.prototype.endsWith = function (str) {
        return this.slice(-str.length) == str;
    };
}