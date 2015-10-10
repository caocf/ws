
$(function(){
	//点击显示隐藏层
	$(".reback .content").hide().filter($(".reback .content:first")).show();
	$(".reback .tab li").click(function(){
		$(".reback .tab li").removeClass("curr");
		$(this).addClass("curr");
		$(".reback .content").hide().filter($(this).children("a").attr("href")).show();
		return false;
	});
	/* 展开 收缩功能 */	
	$(".slide-title h3 a.slide_down").toggle(function(){
		$(this).removeClass().parents(".slide-title").next(".related-content").addClass("none");					
		},function(){
		$(this).addClass("slide_down").parents(".slide-title").next(".related-content").removeClass("none");
	})
	/* 通信地址：变色功能 */
	$(".tab-list tr").hover(function(){
		if($(this).attr("class")!="active"){
			$(this).addClass("hover").find(".none").removeClass("none").addClass("col_link");	
		}
	}, function(){
		if($(this).attr("class")!="active"){
			$(this).removeClass("hover").find(".col_link").removeClass("col_link").addClass("none");										
		}
	})
	/* 通信地址：默认地址功能 */
	$(".tab-list tr .thead-tbl-status a").click(function(){
		var obj = $(this);
		var id = obj.attr('id');
		$.get(webRoot+"center/setAddress.chtml?id="+id+"&ramdom="+Math.random(),function(data){
				if(data.flag == 1){					
					$(".tab-list tr").removeClass("hover");	
					$(".tab-list tr.active").removeClass("active").find(".col_link").removeClass("col_link").text("设为默认地址").addClass("none");
					obj.addClass("col_link").html("当前默认地址").parents(".tab-list tr").addClass("active");
				}
			}
		);
		return false;
	})
	/* 订单查询 */
	$(".ordersearch_wrap a").click(function(){
		var obj = $(this);
		var id = obj.attr('id');		
		$(".ordersearch_wrap a").removeClass("hover");	
		obj.addClass("hover");
		$("#flag").val(id);
		$("#startTime").val('');
		$("#endTime").val('');
		$("#inputStartTime").val('');
		$("#inputEndTime").val('');
		$("#form1").submit();
	})
	$(".deleteOrder").click(function(){
		if(confirm("是否删除该订单?")){
		 	return true;
		}else {
			return false;
		} 					 
	})
	$(".confirm_btn").click(function(){
		if(confirm("是否确认收货?")){
			$("#form1").submit();
		 	return true;
		}else {
			return false;
		} 					 
	})
	$(".item_img a").each(function() {
		$(this).hover(
			function () {
			    $(this).next(".item_img_decp").css("display","inline");
			},
		    function () {
			   $(this).next(".item_img_decp").css("display","none");
		    }
		);
	});
	$(".tip_scb").hover(
			function () {
			    $(this).next(".item_price_decp").css("display","inline");
			},
		    function () {
			   $(this).next(".item_price_decp").css("display","none");
	});
	
	$("#remove").click(function(){
		$("#reback").show();
		return false;
	});
	
	$(".date_range").click(function(){
		var date_start,
		    date_end,
		    date_flag;
		if($(this).hasClass("date_three_day")){
			date_start=GLOBAL.dateUtil.getNDay(-3,"yyyy-MM-dd");
			date_end=GLOBAL.dateUtil.getNDay(0,"yyyy-MM-dd");
			date_flag='date_three_day_flag';
			
		}else if($(this).hasClass("date_week")){
			date_start=GLOBAL.dateUtil.getNDay(-7,"yyyy-MM-dd");
			date_end=GLOBAL.dateUtil.getNDay(0,"yyyy-MM-dd");
			date_flag='date_week_flag';
		}else if($(this).hasClass("date_tree_month")){
			date_start=GLOBAL.dateUtil.getNDay(-90,"yyyy-MM-dd");
			date_end=GLOBAL.dateUtil.getNDay(0,"yyyy-MM-dd");
			date_flag='date_tree_month_flag';
		}
		$('[name="date_flag"]').val(date_flag);
		$("#inputStartTime").val(date_start);
		$("#inputEndTime").val(date_end);
		$("#startTime").val(date_start.replace("-","","g"));
		$("#endTime").val(date_end.replace("-","","g"));
		//生成其他条件
		$(".condition_btn").each(function(){
			if($(this).hasClass("active")){
				var _hidden_data="";
				var $pro=$($(this).attr("property").split("&"));
				$pro.each(function(){
				   var pro_item=String(this).split("=");
				   _hidden_data+='<input type="hidden" value="'+pro_item[1]+'" name="'+pro_item[0]+'">';
				});
				$("#form1").append(_hidden_data);
			}
		});
		
		
		$("#form1").submit();
		
	});
	
	$(".condition_btn").click(function(){
		var _hidden_data="";
		var $pro=$($(this).attr("property").split("&"));
		$pro.each(function(){
		   var pro_item=String(this).split("=");
		   _hidden_data+='<input type="hidden" value="'+pro_item[1]+'" name="'+pro_item[0]+'">';
		});
		$("#form1").append(_hidden_data);
		$("#form1").submit();
	});
	
	$(".search_order_btn").click(function(){
		//生成其他条件
		$(".condition_btn").each(function(){
			if($(this).hasClass("active")){
				var _hidden_data="";
				var $pro=$($(this).attr("property").split("&"));
				$pro.each(function(){
				   var pro_item=String(this).split("=");
				   _hidden_data+='<input type="hidden" value="'+pro_item[1]+'" name="'+pro_item[0]+'">';
				});
				$("#form1").append(_hidden_data);
			}
		});
		
		$('#form1').submit();
	});
	
	
	
	 	
	$("#reback_sure_btn").click(function(){
		var _url='../center/orderManager.chtml?'+getOrderManagerUrl();
		if($(this).attr("orderType")=="s_order"){
			cancelOrder(_url);
		}else{
			commonOrder(_url);
		}
	});
	
});

function getOrderManagerUrl(){
	var _url='';	
    $(".condition_btn").each(function(){
		if($(this).hasClass("active")){
			var $pro=$($(this).attr("property").split("&"));
			$pro.each(function(){
			   var pro_item=String(this).split("=");
			   _url+=pro_item[0]+'='+pro_item[1]+'&';
			});
		}
	});
    
    _url+='select_flag='+G.normalize($('[name="select_flag"]').val());
    _url+='&orderTypes='+G.normalize($('[name="orderTypes"]').val());
    _url+='&date_flag='+G.normalize($('[name="date_flag"]').val());
    _url+='&curpage='+G.normalize($('[name="curpage"]').val());
    return _url;
}

function delAddress(){
	$("#addressId").val(arguments[0]);
	$("#form1").attr("action", "delAddress.chtml");
	$("#form1").submit();
}

function editAddress(){
	var id = arguments[0];
	$('.title').html("修改收货地址");
	$.get(webRoot+"center/getAddress.chtml?id="+id+"&ramdom="+Math.random(),function(data){
				var address = data.address;
				adrsInit($('#cmbProvince'), $('#cmbCity'), $('#cmbArea'), address.region);
				$("#dregion").val(address.zipcode);
				$("#dstreet").val(address.address);
				$("#dname").val(address.name);
				$("#dmobileno").val(address.mobile);
				$("#dcallno").val(address.phone);
				$("#addressId").val(address.id);
			}
		);
}

function displayTextare(){
	var selectValue = $("#selectId").val();
	if(selectValue == "其他原因"){
		$(".bodbccc").css("display","");
	}else{
		$(".bodbccc").css("display","none");
	}	
	$('#textareaId').focus();
 }
	
function getOrderId(id){
	$("#orderId").val(id);
}

function undo(){
	$("#orderId").val("");
	var select = $("#selectId").val("请选择你的理由");
	$(".bodbccc").css("display","none");
	$("#textareaId").val("");
	$("#reback").hide();	
}

function subContent(){
	var obj = arguments[0];
	if(obj.value.length>50){
		obj.value=obj.value.substring(0,50);
	}
}

function cancelOrder(url){
	var orderId = $("#orderId").val();
	var select = $("#selectId").val();
	if(select == "请选择你的理由"){
	alert("请选择你的理由");
		return false;
	}
	var selectValue = "";
	if(select != "其他原因"){
		selectValue = "用户取消,"+select;
	}else if(select == "其他原因"){	
		var content = $("#textareaId").val();
		if(content.replace(/[ ]/g,"") == ""  || content == "请在此处填写您的原因"){
				alert("请填写原因！");
				return false;
		}else {
			if(content.length > 50){
				alert('原因内容过长！');
				return false;
			}
		}
		selectValue = "用户取消,"+content;
	}
    $.ajax({
		url : '../center/cancelOrder.chtml',
		dataType : "json",
		cache : false,
		data:{
			orderIdText:orderId,
			selectValueText:selectValue,
		},
		success : function(data) {
			window.location.href=url;
		},
		error:function(e){
			console.debug(e);
		}
	});
    
}

function commonOrder(url){
	var orderId = $("#orderId").val();
	var select = $("#selectId").val();
	if(select == "请选择你的理由"){
	alert("请选择你的理由");
		return false;
	}
	var selectValue = "";
	if(select != "其他原因"){
		selectValue = "用户取消,"+select;
	}else if(select == "其他原因"){	
		var content = $("#textareaId").val();
		if(content.replace(/[ ]/g,"") == ""  || content == "请在此处填写您的原因"){
				alert("请填写原因！");
				return false;
		}else {
			if(content.length > 50){
				alert('原因内容过长！');
				return false;
			}
		}
		selectValue = "用户取消,"+content;
	}
	$.ajax({
		url : '../center/commonOrder.chtml',
		dataType : "json",
		cache : false,
		data:{
			orderIdText:orderId,
			selectValueText:selectValue,
		},
		success : function(data) {
			window.location.href=url;
		},
		error:function(e){
			console.debug(e);
		}
	});
}

function confirmAddress(){
	var cmbArea = $("#cmbArea").val();
	var dregion = $("#dregion").val();
	var dstreet = $("#dstreet").val();
	var dname = $("#dname").val();
	var dmobileno = $("#dmobileno").val();
	var dcallno = $("#dcallno").val();
	if(!cmbArea){
		simpleAlert('请选择配送地址！');
		$("#cmbArea").focus();
		return false;
	}
	if(dregion && !isNumber(dregion)){
		simpleAlert('邮政编码不正确，请重新填写！');
		$("#dregion").focus();
		return false;
	}
	if(!dstreet){
		simpleAlert('请填写街道地址！');
		$("#dstreet").focus();
		return false;
	}
	if(!dname){
		simpleAlert('请填写收货人姓名！');
		$("#dname").focus();
		return false;
	}
	if(!dmobileno){
    	simpleAlert('请填写收货人手机号码');
    	$("#dmobile").focus();
        return false;
    }else if(!isMobile(dmobileno)){
    	simpleAlert('手机号码不正确，请重新填写！');
    	$("#dmobile").focus();
        return false;
    }
    if(dcallno && !isNumber(dcallno)){
    	simpleAlert('固定电话不正确，请重新填写！');
        $("#dphone").focus();
        return false;
    }
    $('#form1').submit();
}


function isNumber(value){
	var str=new String(value);
	var NUM=new String("0123456789");
	for(var I=0;I<str.length;I++){
		if(NUM.indexOf(str.charAt(I))<0)
			return false;
	}
	return true;
}

function isMobile(mobile) {
	  if ( mobile.length!=11 ) {
		return false;
	  }
	  
	  if( !isNumber(mobile) ){
		return false;
	  }
      
      return true;
}

function comment(){
	var saleIds = arguments[0];
	var flag = arguments[1];
	var storeId = arguments[2];
	$.get(webRoot+"center/isComment.chtml?id="+saleIds+"&ramdom="+Math.random(),function(data){
				var flag = data.flag;
				if(flag == '1'){
					alert('您已评价过该订单！');
					return;
				}else{
					window.location.href = webRoot+'center/addComment.chtml?saleIds='+saleIds+'&flag=1&storeId='+storeId;
				}
			}
		);
}

function weiboShare(orderId,goodsName,goodsType,payTime,watchStr){
	  jQuery.post(webRoot+'center/weiboShare.chtml',{'orderId':orderId,'goodsName':goodsName,'goodsType':goodsType,'payTime':payTime},function(){
		  var shareContent;
		  if(null==watchStr || ""==watchStr) {
			  shareContent = '#我要晒单#我在mo生活购买了'+goodsName+'，订单号：'+orderId+'，网址：http://www.12580life.com ！伙伴们看过来  @12580商户联盟';  
		  }else{
			  shareContent = '#我要晒单#我在mo生活 购买了'+goodsName+'，'+watchStr+'的演出票，订单号：'+orderId+'，火热抢票继续中，网址：http://www.12580life.com ！伙伴们看过来 @12580商户联盟';  
		  }
		  (function(s,d,e,r,l,p,t,z,c) {
			  var f='http://service.weibo.com/share/share.php?appkey=962772401',
			  u=z||d.location,
			  p=['&url=',e(u),'& title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'& content=',c,'&pic=',e(p||'')].join('');
			  function a() {
				  if(!window.open([f,p].join(''),'mb', ['toolbar=0,status=0,resizable=1,width=600,height=500,left=',(s.width- 600)/2,',top=',(s.height-600)/2].join('')))
					  u.href=[f,p].join('');
				  };
				   if(/Firefox/.test(navigator.userAgent))
					   setTimeout(a,0);
				   else a();
				   })(screen,document,encodeURIComponent,'','','',shareContent,'http://www.12580life.com','gbk')
		 });

}


