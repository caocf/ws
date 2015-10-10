(function($) {

    $.fn.mallinvoice = function() {

        var $this = $(this);
        var invoice_edit_btn = $('#invoice-show');
        var invoice_form = $('.invoice-form');
        var invoice_submit_btn = invoice_form.find('.btn-submit');
        var invoices;

        $(':radio[name="invoiceChoose"]').live('click', function() {
            newShow();
        });

        invoice_edit_btn.click(function() {
            showedit();
        });


        invoice_submit_btn.click(function() {
            var choosen = $('input[name="invoiceChoose"]:checked').val();
            if (choosen == 'new') {
                submitNew();
            } else {
                showdefault(choosen);
            }
        });

        jsonReq("order/invoices.chtml", {}, function(data) {
            invoices = data.data;
            build();
        })

        function build() {
            $('#part-invoice').show();
            showdefault('0');
        }

        function newShow() {
            var checked = $(':radio[name="invoiceChoose"]:checked').val();
            if ("new" == checked) {
                $('#invoice_type').show();$('#normal-form').show();
            } else {
                $('#invoice_type').hide();$('#normal-form').hide();
            }
        }


        function showdefault(id) {
            refreshInvoicesList();
            resetInvoiceForm();
            var checkline = $this.find('.invoice-item').first();
            if (id) {
                checkline = $this.find('.invoice-item[index="' + id + '"]');
            }
            var checklineRadio = checkline.find('input:radio');
            checklineRadio.attr('checked', true);
            checklineRadio.addClass("none");
            $this.find('.invoice-item').not(checkline).hide();
            $this.find('.invoice-item').find('[fun=del]').remove();
            invoice_edit_btn.show();
            invoice_form.hide();

            if (id == '0') {
                $('#invoice_btn_wrap').hide();
            } else {
                $('#invoice_btn_wrap').show();
            }
        }

        function showedit() {
            refreshInvoicesList();
            resetInvoiceForm();
            invoice_form.show();
            invoice_edit_btn.hide();
        }

        function submitNew() {
            if ($('input[name="invoiceTitleType"]:checked').val() == '2' && $('input[name="invoiceTitle"]').val() == '') {
                simpleAlert('请填写单位抬头');
                $('input[name="invoiceTitle"]').focus();
                return;
            }
            jsonReq("order/invoice-add.chtml", {
                invoiceType: $('input[name="invoiceType"]:checked').val(),
                invoiceTitleType: $('input[name="invoiceTitleType"]:checked').val(),
                invoiceTitle: $('input[name="invoiceTitle"]').val()}, function(data) {
                invoices = data.data;
                showdefault();
            })
        }

        function refreshInvoicesList() {
            $this.find('.invoice-item').remove();
            var invoice_list = $this.find('#invoice-list');

            var cell = $('<div index="0" class="item item-selected invoice-item">' +
                '<input type="radio" class="hookbox" name="invoiceChoose" id="invoiceChoose0" value="0"/>' +
                '<label for="invoiceChoose0">不开发票</label></div>');
            invoice_list.prepend(cell);

            $.each(invoices, function(index, val) {
                var cell = $('<div index="' + val.id + '" class="item item-selected invoice-item">' +
                    '<input type="radio" class="hookbox" name="invoiceChoose" id="invoiceChoose' + val.id + '" value="' + val.id + '"/>' +
                    '<label for="invoiceChoose' + val.id + '">' + buildText4Invoice(val) + '</label>' +
                    '&nbsp; <a class="f12 col_link fn" href="javascript:void(0);" fun="del">删除</a></div>');
                invoice_list.prepend(cell);
                cell.find('[fun=del]').click(function(event) {
                    $('input[name="invoiceChoose"][value="new"]').attr('checked', true);

                    del_confirm(event, "确定要删除吗？", function() {
                    jsonReq('order/invoice-del.chtml', {id: val.id}, function(){
                        cell.remove();newShow();
                        invoices = $.grep(invoices, function(n,i){
                            return (n.id != val.id);
                        });
                    })
                    });
                });
            });
        }

        function resetInvoiceForm() {
            $('input[name="invoiceChoose"][value="new"]').attr('checked', true);
            $('input[name="invoiceType"][value="1"]').attr('checked', true);
            $('input[name="invoiceTitleType"][value="1"]').attr('checked', true);
            $('input[name="invoiceTitle"]').val('');
            newShow();
        }
    }

    function buildText4Invoice(invoice) {
        var invoiceTypeName = "";
        if (invoice.invoiceType == '1') invoiceTypeName = "普通发票";

        var invoiceTitleTypeName = "";
        if (invoice.invoiceTitleType == '1') invoiceTitleTypeName = "个人";
        if (invoice.invoiceTitleType == '2') invoiceTitleTypeName = "单位";

        var invoiceTitleName = invoice.invoiceTitle == null ? '' : invoice.invoiceTitle;

        var result = "";
        if (invoiceTitleName == '') {
            result = invoiceTitleTypeName;
        } else {
            result = invoiceTitleName + "(" + invoiceTitleTypeName + ")";
        }
        result += " - " + invoiceTypeName;
        return result;
    }

})(jQuery);

$(function() {
    $('.invoice-block').mallinvoice();
});