$(function(){
	var balance_pay_hint = "";
	balance_pay_hint += "<div class='lightcontent'>"
					   +"<h2>小额话费支付使用须知：</h2>"
					   +"<p>使用话费支付的用户需遵循业务规定（如业务规则变更，以最新规则为准）:</p>"
					   	+"<ul>"
					   		+"<li>（1）每月每人累积消费金额限300元；当月使用情况，请查看<a href='/center/myBalance.chtml' class='col_link'>我的余额</a>。</li>"
					   		+"<li>（2）话费支付不能使用手机账户中的全部金额，仅限现金账本和充值卡账本。</li>"
						    +"<li>（3）目前无线公话、无线固话、家庭固话、行业卡、随e行暂不支持小额话费支付。</li>"
						    +"<li>（4）目前小额话费支付账本，暂不允许直接充值。客户如需充值，只需通过现金或充值卡直接进行话费充值即可。</li>"
						    +"<li>（5）已销户号码不允许任何交易；停机号码不能交易，若已产生交易，只能退款。</li>"
						    +"<li>（6）已在用户进行话费充值（现金或充值卡）时提供相应的通信费发票，话费支付消费时不再提供消费发票。如果出现需要退货情况，请拨打客服热线4001511511。</li>"
						    +"<li><strong class='col_link'>（7）支付完成后，话费余额不得低于20元。</strong></li>"
						    +"<li>（8）话费支付金额不计入每月最低消费。</li>"
						    +"<li>（9）家庭副号不限制使用小额话费支付，同时家庭副号只能使用本身号码帐户中金额，不能使用主号话费。</li>"
						    +"<li>（10）如有疑问，请致电10086。</li>"
						+"</ul>"
					   +"<input value='我知道了' type='button' />"
					   +"</div>"
					   +"<div class='lightshadow'></div>";
	
	// 页面中添加提示信息
	var payPageObj = $("body");
	if(payPageObj){
		payPageObj.prepend(balance_pay_hint);
	}
	
	$(".lightshadow").height(1200);
	
	$('#paynew_balance').change(function(){
		if($('#paynew_balance').is(':checked')){
			$('.lightshadow,.lightcontent').show();
		}
   });
   
   $('.lightcontent input').click(function(){
   		$('.lightshadow,.lightcontent').hide();
   });
   
});