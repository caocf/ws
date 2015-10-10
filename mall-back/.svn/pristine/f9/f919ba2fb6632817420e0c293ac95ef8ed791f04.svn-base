//点击查询条件
var searchMenuClick = function(){
    var searchImg = $('#search-menu li:first img')[0];
    var queryContainer = $('.queryContainer');
   
 
    if (queryContainer.is(":visible")) {
        searchImg.src = searchImg.src.replace(/ico_search.gif/g, 'ico_searchup.gif');
        queryContainer.slideUp(200);
    }else {
        searchImg.src = searchImg.src.replace(/ico_searchup.gif/g, 'ico_search.gif');
        queryContainer.slideDown(200);
    }
};

$(function(){
    $('#search-menu .switch').click(function() {
        searchMenuClick();
    });
    if (window.name !="content-iframe") {
    	  $('.queryContainer').show()
    }else{
    	//商户管理、合同管理、商品管理、订单管理的查询条件，默认展开
    	var openUrl = window.location.href;
	    var pattern = /queryFormBackFlag=backForm/i;
	   if( pattern.test(window.location.search) 
			   || openUrl.indexOf("/item/meal/meallist")> 0
			   || openUrl.indexOf("/item/list")> 0 //商户管理页面 
			   || openUrl.indexOf("/store/list")> 0 //结算商户管理页面
			   || openUrl.indexOf("/store/nonList")> 0//非结算商户管理页面
			   || openUrl.indexOf("/sysfee/feelist")> 0 //费率分类管理页面
			   || openUrl.indexOf("/store/treaty/list")> 0 //商户协议管理页面
			   || openUrl.indexOf("/store/storeSettle/auditlist")> 0 //商户结算审核页面
			   || openUrl.indexOf("/store/storeFee/auditlist")> 0 //商户费率审核页面
			   || openUrl.indexOf("/item/hisunproduction/settleList")> 0 //商品协议管理页面
			   || openUrl.indexOf("/item/hisunproduction/settleAudit")> 0 //商品协议审核页面
			   || openUrl.indexOf("/order/refund/list")> 0 //订单管理页面
			   || openUrl.indexOf("/order/refund/audit")> 0 //订单退款审核页面
			   || openUrl.indexOf("/store/nonAuditList")> 0//非结算商户管理页面
			   || openUrl.indexOf("/store/auditList/firstAudit")> 0
			   || openUrl.indexOf("/store/auditList/secondAudit")> 0
			   || openUrl.indexOf("/store/shop/list")> 0
			   || openUrl.indexOf("/item/auditList")> 0
			   || openUrl.indexOf("/item/twoAuditList")> 0
			   || openUrl.indexOf("/item/vitual/list")> 0
			   || openUrl.indexOf("/item/vitual/auditList")> 0
			   || openUrl.indexOf("/code/composite/list")> 0
			   || openUrl.indexOf("/item/meal/mealTwoAuditList")> 0
			   
			   
			   
			   
			   || openUrl.indexOf("/store/code/list")> 0
			   
		
		
	   ){
		   $('.queryContainer').show();
	   }else{
		   $('.queryContainer').hide();
	   }
    }

});
