(function($) {

    $.fn.malladdress = function() {

        var $this = $(this);
        var address_show = $('#address_show');
        var edit_block = $('<div class="eidtaddress">' +
            '<h3 class="title">添加新的常用地址</h3>' +
            '<input type="hidden" id="did" />' +
            '<dl><dt><span class="red">*</span> 配送地址：</dt>' +
                '<dd><select id="dprov"></select><select id="dcity"></select><select id="darea"></select></dd></dl>' +
            '<dl><dt> 邮政编码：</dt><dd><input type="text" id="dzip" class="editInput" maxlength="6"></dd></dl>' +
            '<dl><dt><span class="red">*</span> 街道地址：</dt><dd><input type="text" id="daddress" style="width:550px;" class="editInput" maxlength="100"></dd></dl>' +
            '<dl><dt><span class="red">*</span> 收货人姓名：</dt><dd><input type="text" id="dname" class="editInput" maxlength="25"></dd></dl>' +
            '<dl><dt><span class="red">*</span> 手机：</dt><dd><input type="text" id="dmobile" class="editInput" maxlength="20">' +
            '</dd></dl>' +
            '<dl><dt> 电话：</dt><dd><input type="text" id="dphone" class="editInput" maxlength="20">' +
            '</dd></dl>' +
            '</div>' +
            '<div class="editbtn"> <a class="btn" id="confirmaddress"><span>确认收货人信息</span></a> </div>');

        var default_block = $('<table id="" class="" width="100%">' +
            '<tr><td></td><td class="cell-align-center"></td><td></td><td></td><td class="cell-align-center"></td><td></td>' +
            '<td><input type="hidden" name="aid"/></td>' +
            '</tr></table>');

        var exist_block = $('<div class="oddaddress fix"><h3 class="title">常用地址</h3>' +
            '<table id="cyAddress" class="tab-list" width="100%"><tr class="t-4">' +
            '<td></td>'+
            '<td  width="10%" class="cell-align-center">姓名</td>' +
            '<td  width="15%">所在地区</td>' +
            '<td>街道地址</td>' +
            '<td  width="12%" class="cell-align-center">邮编</td>' +
            '<td  width="12%">电话/手机</td>' +
            '<td  width="11%" class="thead-tbl-status">默认</td>' +
            '<td width="11%" class="cell-align-center"> 操作 </td>' +
            '</tr></table></div>');

        var exist_cell = '<tr><td><input type="radio" name="address"></td><td class="cell-align-center"></td><td></td><td></td><td class="cell-align-center"></td><td></td>' +
            '<td style="color:blue" class="thead-tbl-status"><input type="hidden" name="aid"/>' +
            '<a class="none" fun="default" href="javascript:void(0);">设为默认地址</a></td>' +
            '<td class="cell-align-center"><a href="javascript:void(0);" fun="mod">修改</a> | <a href="javascript:void(0);" fun="del">删除</a></td></tr>';

        var ADDRESS_DATA;

        address_show.click(function() {
            showedit();
        })

        adrsInit(edit_block.find('#dprov'), edit_block.find('#dcity'), edit_block.find('#darea'), '');


        jsonReq('order/addresses.chtml', {}, function(data) {
            ADDRESS_DATA = data.data;
            build();
        });

        function build() {
            // 有地址
            if (ADDRESS_DATA.length > 0) {

                var da = findDefaultAddress();
                if (da != null) {
                    showdefault(da);
                } else {
                    showedit();
                }
            // 无地址
            } else {
                showedit();
            }

        }




        function showdefault(address) {
            $this.find('.default-block').show().append(default_block);
            $this.find('.detail-block').hide().empty();
            fillExistCell(default_block, address);
            address_show.show();
            $('.order_item').find(".order_item_quantity").each(function(){
        		$(this).blur();
        	});
        }


        function showedit() {
            $this.find('.detail-block').show().append(edit_block);
            $this.find('.default-block').hide().empty();
            address_show.hide();
            edit_block.find('a#confirmaddress').click(function() {
                if (checkAddressForm()) {
                    submits(edit_block);
                }
            });
            refreshTable();
            setBlockToNew();
        }

        function refreshTable() {
            exist_block.find('tr').not(':first').remove();
            $this.find('.detail-block').prepend(exist_block);

            $.each(ADDRESS_DATA, function(index, val) {
                var ec = $(exist_cell);
                fillExistCell(ec, val);
                bindCellEvent(ec, val);
                exist_block.find('tr:last').after(ec);
            });
        }

        function submits(editBlock) {
        	jsonReq('order/address-mod.chtml', {
                id: editBlock.find('#did').val(),
                name: editBlock.find('#dname').val(),
                region: editBlock.find('#darea').val(),
                address: editBlock.find('#daddress').val(),
                zipcode: editBlock.find('#dzip').val(),
                mobile: editBlock.find('#dmobile').val(),
                phone: editBlock.find('#dphone').val()}, function(data) {
                    // simpleAlert('操作成功！', function() {
                    setBlockToNew();
                    ADDRESS_DATA = data.data;
                    refreshTable();
                //});
            });
        };

        function findDefaultAddress() {
            var result = null;
            $.each(ADDRESS_DATA, function(i, address){
                if (address.defaultShipping == '1') {
                    result = address;
                    return false;
                }
            });
            return result;
        }

        function findAddress(aid) {
            var result = null;
            $.each(ADDRESS_DATA, function(i, address){
                if (address.id == aid) {
                    result = address;
                    return false;
                }
            });
            return result;
        }


        function bindCellEvent(ec, val) {
            if (val.defaultShipping == '1') {
                ec.addClass("active").find("td:eq(5) > a").removeClass("none").addClass("col_link").text("当前默认地址");
                ec.find("td:eq(0) > input").attr("checked",true);
            } else {
                ec.removeClass("active").find(".col_link").removeClass("col_link").text("设为默认地址").addClass("none");
            }

            ec.hover(function(){
                if($(this).attr("class")!="active"){
                    $(this).addClass("hover").find(".none").removeClass("none").addClass("col_link");
                }
            }, function(){
                if($(this).attr("class")!="active"){
                    $(this).removeClass("hover").find(".col_link").removeClass("col_link").addClass("none");
                }
            });

            ec.find(".thead-tbl-status a").click(function(){
                ec.find("td:eq(0) > input").attr("checked",true);
                ec.removeClass("hover");
                $(".tab-list tr.active").removeClass("active").find(".col_link").removeClass("col_link").text("设为默认地址").addClass("none");
                $(this).addClass("col_link").removeClass("none").text("当前默认地址").parents(".tab-list tr").addClass("active");
                jsonReq('order/address-active.chtml', {id: val.id});
                return false;
            });

            ec.find('[fun="del"]').click(function(event) {
                del_confirm(event, "确定要删除该地址吗？", function() {
                    jsonReq('order/address-del.chtml', {id: val.id}, function() {
                        ec.remove();
                    });
                });
                return false;
            });

            ec.find('td').click(function() {
                var address = findAddress(val.id);
                showdefault(address);
            });

            ec.find('[fun="mod"]').click(function(){
                var eb = $('.eidtaddress');
                eb.find('#did').val(val.id);
                eb.find('#dname').val(val.name);
                eb.find('#darea').val(val.region);
                eb.find('#daddress').val(val.address);
                eb.find('#dzip').val(val.zipcode);
                eb.find('#dmobile').val(val.mobile);
                eb.find('#dphone').val(val.phone);
                adrsInit(eb.find('#dprov'), eb.find('#dcity'), eb.find('#darea'), val.region);

                var cancelBtn = $('<a id="canceladdress" class="btn"><span>取消</span></a>');
                cancelBtn.click(function() {
                    setBlockToNew();
                });
                edit_block.find('#canceladdress').remove();
                edit_block.find('#confirmaddress').after(cancelBtn);
                edit_block.find('.title').text('修改地址');
                focusOnElm(edit_block);
                return false;
            });

        }


        function setBlockToNew() {
            var eb = $('.eidtaddress');
            eb.find('#did').val('');
            eb.find('#dname').val('');
            eb.find('#darea').val('');
            eb.find('#daddress').val('');
            eb.find('#dzip').val('');
            eb.find('#dmobile').val('');
            eb.find('#dphone').val('');
            adrsInit(eb.find('#dprov'), eb.find('#dcity'), eb.find('#darea'), '');
            edit_block.find('#canceladdress').remove();
            eb.find('.title').text('添加新的常用地址');
            focusOnElm('.detail-block');
        };
    };

    function fillExistCell(existCell, address) {
        existCell.find("td:eq(1)").text(address.name);
        existCell.find("td:eq(2)").text(fullAreaName(address.region));
        existCell.find("td:eq(3)").text(address.address);
        existCell.find("td:eq(4)").text(wrapnone(address.zipcode));
        existCell.find("td:eq(5)").text(wrapnone(address.mobile) + " " + wrapnone(address.phone));
        existCell.find("input[name='aid']").val(address.id);
    }

    function checkAddressForm() {
        var darea = $("#darea").val();
        var dzip = $("#dzip").val();
        var daddress = $("#daddress").val();
        var dname = $("#dname").val();
        var dmobile = $("#dmobile").val();
        var dphone = $("#dphone").val();
        if (!darea){
        	simpleAlert('请选择配送地址！');
            $("#darea").focus();
            return false;
        }
        if(dzip && !isNumber(dzip)){
        	simpleAlert('邮政编码不正确，请重新填写！');
            $("#dzip").focus();
            return false;
        }
        if(!daddress){
        	simpleAlert('请填写街道地址！');
            $("#daddress").focus();
            return false;
        }
        if(!dname){
        	simpleAlert('请填写收货人姓名！');
            $("#dname").focus();
            return false;
        }
        if(!dmobile){
        	simpleAlert('请填写收货人手机号码');
        	$("#dmobile").focus();
            return false;
        }else if(!isMobile(dmobile)){
        	simpleAlert('手机号码不正确，请重新填写！');
        	$("#dmobile").focus();
            return false;
        }
        if(dphone && !isNumber(dphone)){
        	simpleAlert('固定电话不正确，请重新填写！');
            $("#dphone").focus();
            return false;
        }
        return true;
    }

    function isNumber(value){
        var str=new String(value);
        var NUM=new String("0123456789");
        for(var I=0;I<str.length;I++){
            if(NUM.indexOf(str.charAt(I))<0)
                return false;
        }
        return true;
    }

    function isMobile(mobile) {
        if ( mobile.length!=11 ) {
            return false;
        }

        if( !isNumber(mobile) ){
            return false;
        }

        return true;
    }

    function wrapnone(str) {
        if (str == null) return ''; else return str;
    }

})(jQuery);

$(function() {
    $('.address-block').malladdress();
});