function doExchange(data,webroot) {
    $.ajax({
        url:  "/center/doExchange.chtml",
        data: data,
        dataType: "json",
        cache: false,
        success: function(data) {
            if (data.success) {
            	window.location.href=webroot+"center/exchangeLog.chtml";
            }else{
            	$("#errorMsg").text(data.msg);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("兑换商城币失败，请重新刷新对话！");
        } 
    });
}
gotoExchangeLog = function(){
	document.forms[0].action="/center/exchangeLog.chtml";
	document.forms[0].submit();
}

changePoints = function(){
	var userscore = $(".userscore").text();
	var point = $("#mallpoint").val();
	if(point==null || point==""){
		$("#needscore").val("");
		return false;
	}
	var r = /^\+?[1-9][0-9]*$/;//正整数 
	if(!r.test(point)){
		$("#errorMsg").text("请输入整数的商城币！");
		$("#needscore").val("");
		$("#sure").attr('disabled',"true");
		return false;
	}
	
	var pectnts = $(".pencents").text();
	var array = new Array();
	array = pectnts.split(":");
	var score = parseInt(point)*array[0];
	if(score<=userscore){
		$('#sure').removeAttr("disabled");
		$("#needscore").val(score);
		$("#errorMsg").text("");
	}else{
		$("#sure").attr('disabled',"true");
		$("#needscore").val(score);
		$("#errorMsg").text("您没有足够的积分兑换商城币！");
	}
	
}