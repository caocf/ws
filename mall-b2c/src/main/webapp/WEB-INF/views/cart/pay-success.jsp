<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name='WT.shopping_n' content='${param.orderId}'/>
     <meta name='WT.shopping_x' content='60'/>
    <link href="../css/base.css" rel="stylesheet" type="text/css"/>
    <link href="../css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="../css/cart.css" rel="stylesheet" type="text/css"/>
    <link href="../css/pro.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/cart.js"></script>
    <script type="text/javascript" src="../js/cart-common.js"></script>
    <title>下单成功</title>
</head>
<body id="cart">
<script type="text/javascript" src="../js/head.js"></script>
<br/><br/>

<div class="wrapper mauto">
    <div id="cart_container" class="w bt10">
        <div class="neworderBox">
            <div class="shouhuoInfo2">
                <h2 class="title">下单成功<span></span></h2>

                <div class="p10">
                        <!-- JiaThis Button BEGIN -->
                        <div class="jiathis_style fr">
                            <span class="jiathis_txt">分享到：</span>
                            <a class="jiathis_button_qzone">QQ空间</a>
                            <a class="jiathis_button_tsina">新浪微博</a>
                            <a class="jiathis_button_tqq">腾讯微博</a>
                            <a class="jiathis_button_renren">人人网</a>
                            <a class="jiathis_button_douban">豆瓣</a>
                            <a class="jiathis_button_copy">复制网址</a>

                            <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
                            <a class="jiathis_counter_style"></a>
                        </div>
                        <script type="text/javascript" >
                            var jiathis_config={
                                siteNum:6,
                                sm:"qzone,tsina,tqq,renren,douban",
                                summary:"一键分享：“我正在mo生活买东西，你也来看看？“",
                                shortUrl:true,
                                hideMore:false,
                                url:"http://mall.12580life.com"
                            }
                        </script>
                        <script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
                        <!-- JiaThis Button END -->
                    <br>
                    <ul class="pay_info">
                        <li>
                            <c:if test="${param.orderId != null && param.orderId != ''}">
                                订单编号为：${param.orderId}已受理，请点击“前往个人中心”按钮查看付款结果。
                            </c:if>
                            <c:if test="${param.orderId == null || param.orderId == ''}">
                                下单成功，请点击“前往个人中心”按钮查看付款结果。
                            </c:if>
                        </li>
                    </ul>
                </div>
                <div class="btn-group">
                    <a class="btn_pay_usercenter" href="../center/orderManager.chtml">
                    </a>
                    <a class="btn_pay_home" href="../">
                    </a>
                </div>


            </div>
        </div>
    </div>
    <span class="clr"></span></div>
</div>

<script type="text/javascript" src="../js/foot.js"></script>

<script>
    $(function(){
        jQuery.ajax({
           url:'recommend/ddcg.chtml',
           dataType: "json",
           cache:false,
           success:function(data){
              if(jQuery.isArray(data) && data.length > 0){
                 var tujian = "<div class='recomment-box'><div class='recomment-box-title'>您可能感兴趣的商品</div>";
                 tujian += "<div class='recomment-content'><ul class='coupon'>";
                 for (var i = 0; i < data.length; i++){
                     tujian += "<li><div class='left recom-prod-icon'>";
                     tujian += "<a target='_blank' href='"+data[i].HREF+"'>";
                     tujian += "<img src='"+data[i].IMG_PATH+"'></a></div>";
                     tujian += "<div class='left recom-prod-info'><div class='recom-prod-title'>";
                     tujian += "<a target='_blank' href='"+data[i].HREF+"'>"+data[i].NAME+"</a></div>";
                     tujian += "<div class='recom-prod-info-w'><div class='prod-info-title prod-recom-old-price'>市场价：<span>￥"+data[i].MARKET_PRICE+"</span></div></div>";
                     tujian += "<div class='recom-prod-info-w'><div class='prod-info-title prod-recom-new-price'>商城价：<span>￥"+data[i].SHOP_PRICE+"</span></div></div>";
                     tujian += "<div class='recom-prod-info-w'><div class='left prod-info-title'>支付方式：</div><div class='left prod-info-cont'>";
                     if (data[i].CASH_IDGOODS == '1'){
                        tujian += "现金  ";
                     }
                     if (data[i].COIN_IDGOODS == '1'){
                        tujian += "商城币  ";
                     }
                     if (data[i].SCORE_IDGOODS == '1'){
                        tujian += "积分  ";
                     }
                     if (data[i].BILL_PAY == '1'){
                        tujian += "小额话费  ";
                     }
                     tujian += "</div><div class='clear'> </div></div></div><div class='clear'> </div></li>";
                 }
                 tujian += "<div class='clear'> </div></ul></div>";
                 jQuery(tujian).insertAfter("#cart_container");
              }
           }
        });
    })
</script>
</body>
</html>
