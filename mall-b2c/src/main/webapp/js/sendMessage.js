var Message = {
    flag:"",
    send: function () {
        jsonReq('../center/bind-mobile/sendCode.chtml', {
        		mobile:$('#terminalId').val(),
        		validBindMobileCode: $('#validCode3').val()
            },
            function (data) {
            	if(data.success == true){
            		if(data.msg){
            			alert(data.msg);
            		}else{
            			var secs = 30;
        				var timeout;
        				var btnCount = function() {
        					if (secs == 0) {
        						$("#getMessageCode").removeAttr("disabled").removeClass("disable");
        						$(".mo-resend-msg-time-panel").addClass("mo-hide-panel");
        						clearTimeout(timeout);
        					} else {
        						$(".mo-send-msg-panel").addClass("mo-hide-panel");
        						$(".mo-resend-msg-time-panel").removeClass("mo-hide-panel");
        						$("#getMessageCode").addClass("disable");
        						$("#getMessageCode").attr("disabled", "disabled");
        						secs--;
        						$(".mo-resend-msg-time").text("短信已发送，" + secs + "秒后可重新发送");
        						setTimeout(btnCount, 1000);
        					}
        				};
        				timeout = setTimeout(btnCount, 1000);
            		}
            	}
            });
    },
    _sendEditStep1: function () {
        jsonReq('../center/edit-mobile/step1/sendCode.chtml', {
        		mobile:$('#oldMobile').val(),
        		validEditMobileStep1Code: $('#validCode3').val()
            },
            function (data) {
            	if(data.success == true){
            		if(data.msg){
            			alert(data.msg);
            		}else{
            			var secs = 30;
        				var timeout;
        				var btnCount = function() {
        					if (secs == 0) {
        						$("#getMessageCode").removeAttr("disabled").removeClass("disable");
        						$(".mo-resend-msg-time-panel").addClass("mo-hide-panel");
        						clearTimeout(timeout);
        					} else {
        						$(".mo-send-msg-panel").addClass("mo-hide-panel");
        						$(".mo-resend-msg-time-panel").removeClass("mo-hide-panel");
        						$("#getMessageCode").addClass("disable");
        						$("#getMessageCode").attr("disabled", "disabled");
        						secs--;
        						$(".mo-resend-msg-time").text("短信已发送，" + secs + "秒后可重新发送");
        						setTimeout(btnCount, 1000);
        					}
        				}
        				timeout = setTimeout(btnCount, 1000);
            		}
            	}
            });
    },
    _sendEditStep2: function () {
        jsonReq('../center/edit-mobile/step2/sendCode.chtml', {
        		mobile:$('#terminalId').val(),
        		validEditMobileStep2Code: $('#validCode4').val()
            },
            function (data) {
            	if(data.success == true){
            		if(data.msg){
            			alert(data.msg);
            		}else{
            			var secs = 30;
        				var timeout;
        				var btnCount = function() {
        					if (secs == 0) {
        						$("#getMessageCode2").removeAttr("disabled").removeClass("disable");
        						$(".mo-resend-msg-time-panel2").addClass("mo-hide-panel");
        						clearTimeout(timeout);
        					} else {
        						$(".mo-send-msg-panel2").addClass("mo-hide-panel");
        						$(".mo-resend-msg-time-panel2").removeClass("mo-hide-panel");
        						$("#getMessageCode2").addClass("disable");
        						$("#getMessageCode2").attr("disabled", "disabled");
        						secs--;
        						$(".mo-resend-msg-time2").text("短信已发送，" + secs + "秒后可重新发送");
        						setTimeout(btnCount, 1000);
        					}
        				}
        				timeout = setTimeout(btnCount, 1000);
            		}
            	}
            });
    },
    _send_rest_bind_email:function(){
    	$.ajax({
	        url: "../center/rest-bindEmail/sendMail.chtml",
	        type: "POST",
	        dataType: 'json',
	        data: {},
	        success: function (data, stats) {
	        	if(data.success == true){
	         		if(data.msg){
	         			if(data.msg != '重新发送成功'){
	         				alert(data.msg);
	             		}
	         		}
	         	}
	        },
	        error: function (data) {
	            alert("请求失败");
	        },
	
		});		
    }, 
    _send_rest_edit_email:function(){
    	$.ajax({
	        url: "../center/rest-editEmail/sendMail.chtml",
	        type: "POST",
	        dataType: 'json',
	        data: {},
	        success: function (data, stats) {
	        	if(data.success == true){
	         		if(data.msg){
	         			if(data.msg != '重新发送成功'){
	         				alert(data.msg);
	             		}
	         		}
	         	}
	        },
	        error: function (data) {
	            alert("请求失败");
	        },
	
		});		
    },
};

