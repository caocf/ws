function del() {
    var checks = $(this).find('#favorite_id:checked');
    if (checks.length == 0) {
    	checks = $('#favorite_id:checked');    	
    } 
    if(checks.length == 0){
        alert('请选择要删除的收藏');
        return;
    }else {
        var items = "";
        checks.each(function(){
            items += $(this).val() + ",";
        });
        items = items.substring(0,items.length-1);
        $("#items").val(items);
        $('#form1').attr('action','delFavorite.chtml');
        $('#form1').submit();
    }
}

function delSingle() {
    $("#items").val(arguments[0]);
    $('#form1').attr('action','delFavorite.chtml');
    $('#form1').submit();
}

$(function () {
    $(this).find('#checkbox_all').click(function() {
        var checked = $('#checkbox_all').attr('checked') != undefined;
        $("input[name='favorite_id']").attr('checked', checked);
        $('#checkbox_all1').attr('checked', checked);
    });
    $(this).find('#checkbox_all1').click(function() {
        var checked = $('#checkbox_all1').attr('checked') != undefined;
        $("input[name='favorite_id']").attr('checked', checked);
         $('#checkbox_all').attr('checked', checked);
    });

    $("input[name='favorite_id']").click(function() {
        var notsel = $("input[name='favorite_id']").not(":checked");
        $('#checkbox_all').attr('checked', (notsel.length == 0));
        $('#checkbox_all1').attr('checked', (notsel.length == 0));
    });
});

function buyNow() { 
	var quantity = 1;
    var itemId1 = arguments[0];

    $.ajax({
        url : "../cart/buyitem.chtml",
        type : "POST",
        dataType : 'json',
        data : {itemId:itemId1, quantity:quantity},
        success : function(data, stats) {
            if (data.success) {
                $.cookie('confirm_itemId', itemId1, { path: '/' });
                $.cookie('confirm_quantity', quantity, { path: '/' });
                window.location = '../order/buynow.chtml';
            } else {
                alert(data.msg);
            }
        },
        error : function(data) {
            alert("请求失败");
        }
    });
}