<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		<link href="../css/base.css" rel="stylesheet" type="text/css" />
		<link href="../css/layout.css" rel="stylesheet" type="text/css" />
		<link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
		<link href="../css/money.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/jquery.jqzoom.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/zoom.css" />
		<script type="text/javascript" src="../js/money.js"></script>
		<link href="../css/global_v20120711.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="../js/jquery.cookie.js"></script>
		<title>商城币兑换</title>
		<script type="text/javascript">
		function doExchangeFun(){
			var userscore = $("#userscore").val();
			var mallpoint = $("#mallpoint").val();
			var needscore = $("#needscore").val();
			var verifyCode = $("#verifyCode").val();
			$("#sure").attr('disabled',"true");
			doExchange({userscore:userscore,mallpoint:mallpoint,needscore:needscore,verifyCode:verifyCode},'${webroot}');
		}

        $(function(){
            $("#timeb1").click(function(){
                doclick();
            });

            var doclick = function(){
                getExchangeCode();
                $('#timeb1').removeClass("col_link").addClass("col999");
                $('#timeb1').html('<span id="timeb2" class="code">60</span>秒后重新获取');
                $('#timeb1').unbind("click");
                timer = self.setInterval(addsec,1000);
            }

            var getExchangeCode=function(){
                $.ajax({
                    url: "${ctx}/center/sendMessage.chtml",
                    dataType: "json",
                    success:function(data){
                        if (data.type === 'success') {

                        }
                    }
                });
            }

            var addsec=function(){

                var t = $('#timeb2').html();
                //alert(t);
                if(t > 0){

                    $('#timeb2').html(t-1);
                    //alert(t);
                }else{

                    window.clearInterval(timer);
                    $('#timeb1').removeClass("col999").addClass("col_link");
                    $('#timeb1').html('<span id="timeb2"></span>重新获取');
                    $('#timeb1').bind("click",function(){
                        doclick();
                    });
                }

            }
        });
		</script>
</head>

<body id="money">

	<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto" >
	<a target="_blank" href="/" style="margin-bottom:10px; display:block;"><img alt="" src="../img/exchange.jpg"></a>
    <div id="" class="fr  w750">
        <div class="detail">
            <div class="tab">
                <div id="" class="tab_nav">
                    <ul class="tab">
                        <li class="curr">移动商城币兑换平台</li>
                    </ul>
                </div>
            </div>
            <c:if test="${noPhone eq 1 }">
            <form action="" method="post" name="form1" id="form1">
            <div class="content">
            	<dl>
                	<dt>兑换类别:</dt>
                    <dd>${userScore.brand } </dd>
				</dl>
                <dl>
                	<dt>您的积分:</dt>
                    <dd><span class="col_link userscore">${userScore.score }</span></dd>
                    <input type="hidden" id="userscore" name="userscore" value="${userScore.score }"/>
				</dl>
                <dl>
                	<dt>兑换比率:</dt>
                    <dd><span class="col_link pencents">${userScore.percent }:1</span></dd>
				</dl>                
                <dl>
                	<dt>您希望兑换的商城币:</dt>
                    <dd><input type="text" value="0" onkeyup="changePoints()" id="mallpoint" name="mallpoint" class="comminput"> 请输入整数的商城币，并确保您有足够的积分进行兑换</dd>
				</dl> 
                <dl>
                	<dt>本次兑换所需积分:</dt>
                    <dd><input type="text" value="" readonly  id="needscore" name="needscore" class="comminput"><span style="color:red;" id="errorMsg"></span></dd>
				</dl>
				<dl>
                	<dt>短信验证码:</dt>         
                    <dd>
                        <input class="input_code p5" autocomplete="off" onblur="if(this.value=='') this.value='';" onfocus="if(this.value=='') this.value='';" value="" type="text"  name="verifyCode"  id="verifyCode" />
                        <span class="code col_link f12" id="timeb1" style="cursor:pointer">免费获取</span>
                        <input type="hidden" id="randomCode">
                    </dd>
                </dl>         
                <div class="btn_line">
                  	<input type="button" value="确认兑换" class="money_btn_small" id="sure" name="sure" onclick="doExchangeFun()">&nbsp;&nbsp;
                    <input type="button" onclick="gotoExchangeLog()" value="兑换记录" class="money_btn_small" name="">&nbsp;&nbsp;
                    <input type="button" onclick="javaScript:top.location='${webroot}';" value="返回商城" class="money_btn_small" name="backshangc">
                 </div>
                
            </div>
            </form>
            </c:if>
            <c:if test="${noPhone eq 0 }">
             <div class="content">
            	<p style="height: 100px;margin-top: 50px;text-align: center;">数据获取错误，如有任何问题，请及时联系客服4001511511！</p>
            </div>
            </c:if>
        </div>
    </div>
    <div id="sidebar" class="w240 fl">
    	<div class="related-title">
			<h3>什么是商城币？</h3>
        </div>
		<div class="related-content">12580移动商城（http://mall.12580life.com）所使用的商城币，是由中国移动各种品牌业务的积分，经移动积分统一兑换平台兑换而来。 您可以登录此平台，将自己的全球通积分、动感地带M值等，兑换成商城币。 商城币的价值与等额现金相同，若您有足够的商城币，即可在商城内全额使用商城币购买喜爱的商品；若商城币不足，剩余款项可以通过其他支付渠道组合支付（支付宝、手机支付等）。</div>
		<div class="related-title">
	        <h3>如何获取商城币？</h3>
    	</div>
		<div class="related-content">移动全球通积分、动感地带M值兑换商城币；
            全球通积分:商城币 = 67:1
            M值:商城币=67:1
            生活网来福币兑换商城币；
            暂停下线，恢复时间将另行通知。
            移动体验100勋章兑换商城币；
            体验100勋章： 
		</div>
    </div>
    <span class="clr"></span>
</div>

	<script type="text/javascript" src="../js/foot.js"></script>
</body>
</html>