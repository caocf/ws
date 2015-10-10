<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/cart.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <link href="../js/lab/default/dialog.css" rel="stylesheet" type="text/css" />
    <link href="../js/lab/default/ibutton.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/static.chtml"></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/jquery.json-2.4.js"></script>
    <script type="text/javascript" src="../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../js/cart-common.js"></script>
    <script type="text/javascript" src="../js/area_data.chtml"></script>
    <script type="text/javascript" src="../js/area.js"></script>
    <script type="text/javascript" src="../js/confirm-hn-order-common.js?v=1.01"></script>
    <script type="text/javascript" src="${ctx}/js/verifyCode.js?v=2.2"></script>
    <title>确认订单</title>
    <style>
        #cart .lj_ticket{
            border: 1px solid #DEDEDE;
            margin-bottom: 10px;
        }
        #cart .lj_ticket ul{ margin:5px 15px; border-top:1px #eee solid;}
        #cart .lj_ticket h2 a{ color:#007BC7;}
        .lj_ticket_info_hide{ display:none;}
        .tpc div >img{
            vertical-align: middle!important;
            display: inline!important;
        }
    </style>
    <script>
        $(function(){
            $(".lj_ticket_info_show h2 a").click(function(){
                $(".lj_ticket_info_show").hide();
                $(".lj_ticket_info_hide").show();
                return false;
            })
            $(".lj_ticket_info_hide h2 a").click(function(){
                $(".lj_ticket_info_hide").hide();
                $(".lj_ticket_info_show").show();
                return false;
            })
        })

    </script>
</head>

<body id="cart">
<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto">
<div id="cart_container" class="w bt10">
<div class="flow-steps">
    <ol class="flow_step_no2 ">
        <li class="step_1">查看购物车</li>
        <li class="step_2">核对订单</li>
        <li class="step_3">支付订单</li>
    </ol>
</div>

<%-- postFlag: 0不需要物流 1需要物流 itemMode: 0实物 1虚拟物
all postFlag == 0 then address not show
all itemMode == 1 then invoice not show
--%>
<div class="neworderBox address-block">
    <div class="shouhuoInfo2">
        <h2 class="title">收货人地址 <a class="f12 col_link fn" id="address_show" href="javascript:;" style="display: none;">修改</a></h2>
        <div class="m10 oddaddress fix default-block"></div>
        <div class="addaddresBox detail-block" style="display:none;"></div>
    </div>
</div>

<div class="neworderBox invoice-block">
    <div class="shouhuoInfo2">
        <h2 class="title">发票信息 <a class="f12 col_link fn" id="invoice-show" href="javascript:;" style="display:none;"> 修改 </a></h2>
        <div class="sbox-wrap" id="part-invoice" style="display:none;">
            <div class="sbox">
                <div class="form">
                    <div id="invoice-list">
                        <div class="invoice-form">
                            <div class="item" id="useNewInvoice">
                                <input type="radio" name="invoiceChoose" value="new" id="invoiceChooseNew" class="hookbox">
                                <label for="invoiceChooseNew">使用新的发票信息</label>
                            </div>
                            <div style="padding-bottom: 0px;" id="invoice_type" class="list clearfix"> <span class="label">发票类型：</span>
                                <div class="field">
                                    <ul class="hookbox-list group">
                                        <li>
                                            <input type="radio" checked="checked" value="1" name="invoiceType" class="hookbox" id="invoiceType1">
                                            <label for="invoiceType1">普通发票</label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div id="normal-form">
                                <div id="invoice_title" class="list"> <span class="label">发票抬头：</span>
                                    <div class="field">
                                        <ul id="invoice_title_radio" class="hookbox-list group">
                                            <li>
                                                <input type="radio" checked="checked" value="1" class="hookbox" name="invoiceTitleType" id="invoiceTitleType1">
                                                <label for="invoiceTitleType1">个人</label>
                                            </li>
                                            <li>
                                                <input type="radio" checked="checked" value="2" class="hookbox" name="invoiceTitleType" id="invoiceTitleType2">
                                                <label for="invoiceTitleType2">单位</label>
                                            </li>
                                            <li>
                                                <input type="text" class="hookbox company-textbox textbox" name="invoiceTitle" id="invoiceTitle" maxlength="20">
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div style="clear:both;"></div>
                            </div>
                            <div class="editbtn"> <a class="btn-submit" id="confirminvoice"><span>确认发票信息</span></a> </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="invoice_btn_wrap" class="list">
            <div class="form">
            <div class="step-stitle"><strong>发票内容</strong></div>
            <div class="field">
                <div class="invoice-group">
                    <ul id="normal_content_radio" class="hookbox-list group">
                        <li>
                            <input type="radio" checked="checked" value="0" class="hookbox" name="normal-normalContent" id="normal-invoice-content-0">
                            <label for="normal-invoice-content-0">商品明细</label>
                        </li>
                    </ul>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>


<div class="neworderBox">
    <div class="editproductBox">
        <h2 class="title">商品列表<span></span></h2>
        <div class="editproduct">
            <table cellspacing="0" cellpadding="0" border="0" width="964">
                <tbody>
                <tr>
                    <th style="width: 20px;"></th>
                    <th>商品</th>
                    <th class="tr" width="10%">单价（个）</th>
                    <th width="10%">数量</th>
                    <th width="10%">优惠活动</th>
                    <th class="tr" width="15%">小计（个）</th>
                </tr>
                <tr class="bt1px">
                    <td class="tc"></td>
                    <td class="tl" colspan="6">商户：<a class="shop_tag" target="_blank"></a></td>
                </tr>
                <tr class="order_item discount_block">
                    <td colspan="4" class="tr">店铺优惠：</td>
                    <td>无</td>
                    <td class="tr"><p class="d_price"><span class="shop_pro_price"></span></p></td>
                </tr>
                <tr class="order_item delivery_block">
                    <td colspan="4" class="tr">物流费用：</td>
                    <td>
                    </td>
                    <td class="tr"><p class="d_price"><span class="delivery_price"></span></p></td>
                </tr>
                <tr class="order_item">
                    <td colspan="2" class="tl">
                        <span class="pl20">给商家留言：</span> <input id="leave_message" type="text" maxlength="100" /></td>
                    <td colspan="2" class="tr"><span style="font-weight: bolder">店铺总计：</span></td>
                    <td colspan="2" class="tr"><p class="pr10"><span class="shop_total_price"></span></p></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="neworderBox lj_ticket">
        <div class="lj_ticket_info_show">
            <h2 class="title"><a href="#">（+）使用礼金券享受更大优惠</a></h2>
        </div>
        <div class="lj_ticket_info_hide">
            <h2 class="title"><a href="#">（-）使用礼金券享受更大优惠</a></h2>
            <form>
                <ul></ul>
            </form>

            <p class="tr pr10"><a class="col_link" href="../center/cardGifts.chtml">我的礼金券 </a></p>
        </div>
    </div>


    <div class="order_submit">
        <div id="order_submit_price">
            <table width="100%" cellspacing="0" height="30" cellpadding="0" border="0">
                <tbody>
                <tr>
                	<td width="700"></td><%--
                    <td width="700" class="table_left"><span class="order_b_alert">提示：</span>请务必在<span class="order_c_alert">15分钟内</span>完成支付，否则订单将自动取消。</td>
                    --%><td width="120"><span class="fb">总计:</span> </td>
                    <td width="180" style="text-align: right"><strong class="total_price"></strong></td>
                    <td width="25"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div id="order_submit_btn" class="btn_payment">
        <p style="display: none"><span style="">短信验证码：</span>
            <input type="hidden" id="SMSFrom" value="orderConfirm"/>
            <input type="text" id="verifyCode" name="verifyCode" value="" onfocus="if(this.value=='') this.value='';" onblur="if(this.value=='') this.value='';" autocomplete="off" class="input_code p5">
            <span style="cursor:pointer" id="timeb1" class="code col_link f12">免费获取</span>
        </p>
        <a href="javascript:;"><img border="0" src="../img/space.gif" id="order_img_id" /></a>
    </div>
    <input type="hidden" id="buynow" name="buynow" value="${buynow}"/>

</div>
<span class="clr"></span>
</div>
</div>
<script type="text/javascript" src="http://www.12580life.com/henan/foot_for_henan.jsp"></script> 

<div id="template" style="display:none;">

    <div id="tmpl_buynow">
        <img style="position:relative;cursor:pointer;" src="<c:url value="/img/nbtn_remove.jpg"/>" func="minus" />
        <input type="text" onkeyup="value=value.replace(/[^\d]/g,'');if(!value){value=1}" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" class="order_item_quantity" name="quantity" style="width:36px; text-align:center;" />
        <img style="position:relative;cursor:pointer;" src="<c:url value="/img/nbtn_add.jpg"/>" func="plus" />
    </div>

    <div id="tmpl_confirm">
        <div class="numchange order_item_quantity"></div>
    </div>

    <div id="tmpl_gitem">
        <ul>
            <li class="order_item_img">
                <a target="_blank">
                    <img height="50" border="0" wdith="50" />
                </a>
            </li>
            <li class="order_item_good">
                <a target="_blank"></a>
            </li>
            <div class="clear"></div>
\       </ul>
    </div>
    <div id="tmpl_ngitem">
        <ul>
            <li class="order_item_img">
                <a target="_blank">
                    <img height="50" border="0" wdith="50" />
                </a>
            </li>
            <li class="order_item_good">
                <div>
                    <a target="_blank" ></a>
                </div>
                <div class="panic_buy">
                    <!--特价时间：2013.12.12 0:00 - 2013.12.12 24:00-->
                </div>
            </li>
            <span class="order_item_prop" style="color:#a9a9a9;display:none;"></span>
            <span class="order_item_prompt" style="color:#a9a9a9;display:none;"></span>
            <div class="clear"></div>
        </ul>
    </div>

    <table id="tmpl_item">
        <tr class="order_item item_iter">
            <td class="tc">
                <input type="hidden" name="itemId"/>
                <input type="hidden" name="deliveryFee"/>
                <input type="hidden" name="deliveryType"/>
            </td>
            <td class="tl">
                <ul></ul>
            </td>
            <td class="tr"><p class="price"><span class="order_item_price"></span></p></td>
            <td class="tpc">
                <div></div>
                <div class="tpc_count_desc"><!--限购5件--></div>
            </td>
            <td>
                <p class="price"><span class="cart_cheap_price" style="display: none"></span></p>
            </td>
            <td class="tr"><p class="price"><span class="order_item_aprice"></span></p></td>
        </tr>
    </table>
</div>
</body>
</html>
