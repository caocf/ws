<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${ctx}/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/cart.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/pro.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/js/lab/default/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/js/lab/default/ibutton.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/static.chtml"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.json-2.4.js"></script>
    <script type="text/javascript" src="${ctx}/js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="${ctx}/js/cart-common.js?v=1.0"></script>
    <script type="text/javascript" src="${ctx}/js/welfare-common.js?v=1.0"></script>
    <title>支付订单</title>
    <style>
        #cart .neworderBox #newpay h1 {
            position: relative;
            margin: 0px 0px 0px;
            background: #fef7ee;
            border-bottom: 0;
            padding: 10px;
        }

        #cart .neworderBox #newpay h1 {
            background: none repeat scroll 0 0 #fff;
            border: 1px solid #eee;
            margin-bottom: 10px;
        }

        #cart .neworderBox #newpay h1 a {
            font-weight: normal;
        }

        #cart .neworderBox #newpay h1.hover {
            background: none repeat scroll 0 0 #EEF7FF;
            border: 1px solid #D4EBFD;
            border-bottom: none;
            margin-bottom: 0;
        }

        #cart .neworderBox #newpay .pay_info_content a {
            color: #06C;
        }

        #cart .neworderBox #newpay h1.hover a {
            font-weight: 700;
        }

        #cart .neworderBox #newpay h1 .abs-right {
            right: 20px;
            display: none;
        }

        #cart .neworderBox #newpay h1.hover .abs-right {
            display: block;
            right: 20px;
            top: 10px;
        }

        #cart #newpay .pay_info {
            font-size: 14px;
            background: none repeat scroll 0 0 #EEF7FF;
            border-top: 1px solid #D4EBFD;
        }

        #cart #newpay .pay_info_content {
            background: #EEF7FF;
            font-size: 14px;
            margin: 0px 0px 10px;
            border: 1px solid #D4EBFD;
            border-top: 0;
            padding: 5px 15px 5px;
        }

        #cart .neworderBox #newpay .pay_info {
            background: none repeat scroll 0 0 #fff;
            border-top: 1px dotted #BBBBBB;
        }

        #cart #newpay .pay_meth li {
            background: none repeat scroll 0 0 #fff;
            border-top: 1px solid #eee;
            padding: 0px 15px;
        }

        #cart #newpay .pay_meth {
            border-bottom: 1px solid #eee;
            font-size: 14px;
            margin: 0 20px 10px;
            border-left: 1px solid #eee;
            border-right: 1px solid #eee;
        }

        #cart #newpay input.p5 {
            font-size: 16px;
        }
    </style>
</head>
<body id="cart">
<script type="text/javascript" src="${ctx}/js/head.js"></script>
<div class="wrapper mauto">
    <input type="hidden" id="payForm" value="only_redpack">
    <div id="cart_container" class="w bt10">
        <!-- 新的order page -->
        <div class="neworderBox">
            <div class="shouhuoInfo2">
                <h2 class="title">支付订单<span></span></h2>

                <div id="newpay">
                    <ul class="pay_info">
                        <li>下单成功。 <strong>订单号：</strong><span class="col_link" id="order_id">${orderId}</span>，
                            <strong>总金额：</strong> <span id="total_price" class="col_link">${totalPrice}</span>元，等待您的付款。
                        </li>
                    </ul>

                    <div class="pay_info">
 
                        <h1><input type="checkbox" name="paynew" id="paynew_redpackage" value="redpack" checked="checked" onclick="return false;"/> <a href="#pay_meth_tab2" class="pay_meth_tab tab_redpackage"><label for="paynew_redpackage">红包支付</label></a>
                            <span class="pa abs-right"><strong>支付</strong> <span
                                    class="f18 colF60 price_rmb redpackage_pay pay_redpackage">0.00</span>元</span></h1>

                        <div class="pay_info_content" id="pay_meth_tab2">
                            <ul>
                                <li class="pr">您有可用红包  <span class="colF60 have_redpackage">${totalWelfare}</span>元，本次消费最多可用 <span
                                        class="colF60 allow_redpackage">${welfarePay}</span>元。<br/>
                                    
                                    <!-- <p style="padding-left:30px;">本次使用 --><input type="hidden" id="use_redpackage" class="p5"/>
                               <!--          元
                                    </p>-->
                                </li>
                            </ul>
                        </div>

                        <c:if test="${((totalWelfare - totalPrice) < 0) || ((welfarePay - totalPrice) < 0)}">
                         <h1 class="hover"><input type="checkbox" name="paynew" id="paynew_cash" value="cash"  checked="checked" onclick="return false;" /> <a
                               href="#pay_meth_tab12" class="pay_meth_tab_hover"><label for="paynew_cash" >现金（网银）支付</label></a> <span
                               class="pa abs-right"><strong>支付</strong> <span
                               class="f18 colF60 price_rmb pay_cash">0.00</span>元</span></h1>
                       <div class="pay_info_content1" id="pay_meth_tab12">
                           <ul class="pay_meth">
                               <li>
                                   <label for="alipay">
                                       <input type="radio" checked="checked" name="payType" id="alipay"
                                              value="alipay"/>
                                       &nbsp; <img border="0" align="absmiddle" src="${ctx}/img/zfb_tb.gif">&nbsp;
                                       无支付宝帐号也可使用该支付方式进行网上银行支付。 </label>
                               </li>
                               <li>
                                   <label for="sjzf">
                                       <input type="radio" name="payType" id="sjzf" value="cmpay">
                                       &nbsp; <img border="0" align="absmiddle" src="${ctx}/img/96_30_sjzf.jpg">&nbsp;
                                       移动用户专属支付通道，无手机支付帐号也可使用该支付方式进行网上银行支付。 </label>
                               </li>
                           </ul>
                       </div>
                       </c:if>       
                    
                    </div>
                      <p class="colF00 pl20">温馨提示：提交后，红包将被扣减。</p>
                      <div class="btn_pay">
                          <a href="javascript:void(0);"><img border="0" src="${ctx}/img/space.gif" id="order_img_id"></a>
                      </div>
                </div>
            </div>
        </div>
        </form>
        <span class="clr"></span></div>
</div>
<script type="text/javascript" src="${ctx}/js/foot.js"></script>
<div class="alertbox" style="display:none;">
    <div class="content">
        <div>支付完成前请不要关闭此窗口<br/>
            完成支付后根据情况点击下面的按钮
        </div>
        <a class="btn_worry"> </a> <a class="btn_end"> </a></div>
</div>
</body>
<script>
    $(function () {
        var payAmounts="${totalPrice}";
        var allowRedpage = "${welfarePay}";
        var totalWelfare = "${totalWelfare}";
        if(toDecimal(allowRedpage)<0){
            allowRedpage=0;
        }

       // $(':checkbox').removeAttr("checked");
        $(':checkbox:first').attr("checked",true).parent().addClass("hover");
        //$(':checkbox').not(':first').parent().next().hide();
        $('.pay_cash').text(toDecimal2(payAmounts));
        $('.p5').val("");
        if($('#paynew_cash').attr("checked")){
            $('.tc').hide();
        }else{
            $('.tc').show();
        }

        var checkedNum = 1;

        var checkedbox = function(){
        	
            checkedNum = 0;
            $(':checkbox').each(function(){
                if($(this).attr("checked")){
                    checkedNum++;
                }
            });
        };

        var coinHandler = function(obj){
            var allowPrice = toDecimal(obj.data.allowPay);
            var className = this.id.toString().replace("use","pay");
            if($("input[name='paynew']:checkbox[checked]").length == 1 ){
                if((allowPrice - toDecimal(payAmounts)) >= 0){
                	if((toDecimal(payAmounts) - totalWelfare) <= 0){
                		$(this).val(toDecimal2(payAmounts));
                        $('.'+className).text(toDecimal2(payAmounts));
                	}else{
                		$(this).val(toDecimal2(totalWelfare));
                        $('.'+className).text(toDecimal2(totalWelfare));
                	}  
                }else{
                	if((allowPrice - totalWelfare) <= 0){
                		$(this).val(toDecimal2(allowPrice));
                        $('.'+className).text(toDecimal2(allowPrice));
                	}else{
                		$(this).val(toDecimal2(totalWelfare));
                        $('.'+className).text(toDecimal2(totalWelfare));
                	} 
                }
            }else if($("input[name='paynew']:checkbox[checked]").length == 2){
            	 var payMoney = toDecimal($(this).val());
            	 if((allowPrice - totalWelfare) >= 0){
            		 if((totalWelfare - toDecimal2(payAmounts)) >= 0){
            			 payMoney = toDecimal2(payAmounts);
            		 }else{
            			 payMoney = totalWelfare;
            		 }
            	 }else{
            		 if((allowPrice - toDecimal2(payAmounts)) >= 0){
            			 payMoney = toDecimal2(payAmounts);
            		 }else{
            			 payMoney = allowPrice;
            		 }
            	 }

                if($('#paynew_cash').attr("checked")){
                    $('.pay_cash').text(toDecimal2(toDecimal(payAmounts)-payMoney));
                    $('.'+className).text(toDecimal2(payMoney));
                    $(this).val(toDecimal2(payMoney));
                }
            }
        };
        $('#use_redpackage').bind("blur",{allowPay:allowRedpage},coinHandler);
        
	    $('#use_redpackage').blur();
    });
</script>
</html>
