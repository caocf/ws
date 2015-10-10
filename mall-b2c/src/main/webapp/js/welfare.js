$(function(){
	$('.float-ul li a').live('mouseover',function(){
		 
	});
		$('.float-ul li a').live('mouseout',function(){
		/*$(this).css({
		'background-color':'rgb(238,238,238)',
		'background': 'none',
			'color': '#333'		
		});*/
	});
		
		
	if($('#isLogin').val()==2){
	 $this.changeLoginUrl();
	}else if($('#isLogin').val()==3){
		$.XYTipsWindow({
			___title:"对话框",
			___showTitle:false,
			___content:"text:<div style='width:260px;text-align:left;padding:15px 15px;line-height:25px;'>很抱歉，该活动仅限特定用户购买，还有更多优质商品等着您，快去看看吧！</div>",
			___width:"300",
			___height:"100",
			___drag:"___boxTitle",
			___button:["确定"],
			___showbg:true,
			___boxWrapBdColor:'red',
			___fns:function(val){
				$('#_main_weindow_idparent').css({'z-index':'1100'});
				$('div[id=XYTipsWindowBg]').eq(0).css({'z-index':'1000'});
				$('div[id=XYTipsWindowBg]').eq(1).css({'z-index':'8000'});
			},
			___callback:function(val){
				window.location.href='http://mall.12580life.com';
			}
	});
	}
	
	 
	
});

var $this = {
thisWindow:function(){
/*
 * $.XYTipsWindow({ ___title:"移动商盟", ___content:'id:_window', ___width:"600",
 * ___height:"300", ___boxID:'_main_weindow_id', ___dray:"___boxTitle",
 * ___drag:"___boxTitle", ___showbg:true, ___showTitle:true
 * //___button:["购买",["取消"]],//关闭窗口ID });
 */
$('.g_window_div').css('display','block');
},
closeWindow:function(){
	$.XYTipsWindow.removeBox();
},
getStock:function(){
	$('#_window_info_kucun').html('');
	var itemType = $('#orderItemType').val();
	var itemId = $('#orderItemId').val();
	var itemColor = $('#orderItemColor').val();
	var itemSize = $('#orderItemSize').val();
	itemColor = encodeURI(itemColor);
	itemSize = encodeURI(itemSize);
	var stockHtml='';
	if(itemType!=null&&itemType != ''&&itemId!=null&&itemId!=''&&itemColor!=null&&itemColor!=''&&itemSize!=null&&itemSize!=''){
	$.getJSON('getStock',{'itemType':itemType,'itemId':itemId,'itemColor':itemColor,'itemSize':itemSize},function(info){
		 
		if(itemType ==0||itemType==1||itemType==2){
			stockHtml= '(库存: '+ info.stockNum+'双)';
		}else if (itemType==3||itemType==4){
			stockHtml= '(库存: '+ info.stockNum+'件)';
		}else if (itemType==5){
			stockHtml= '(库存: '+ info.stockNum+'件)';
		}
		$('#_window_info_kucun').html(stockHtml);
		$('#orderStockId').val(info.id);
	});
	}
},
submitBy:function(){
	var itemId = $('#orderItemId').val();
	var itemColor = $('#orderItemColor').val();
	var itemSize = $('#orderItemSize').val();
	var stockId = $('#orderStockId').val();
	var itemType = $('#orderItemType').val();
	if(itemSize == null|| itemSize==''){
		  $this.alert('请选择商品尺码!')
	 }else if(itemColor==null||itemColor==''){
		 $this.alert('请选择商品颜色!')
	 }else{
		 var content = "";
		 content+="商品名称："+$('._window_txt_title').text();
		 content+="<br>";
		 content+="商品颜色："+itemColor;
		 content+="<br>";
		content+="商品尺码："+itemSize;
		content+="<br>";
		 content+="商品价格："+$('._window_money').html();
		 $.XYTipsWindow({
				___title:"确认信息",
				___content:"text:"+content,
				___width:"300",
				___showTitle:false,
				___height:"100",
				___showbg:true,
				___button:["确定","取消"],
			  
		 		___fns:function(val){
					$('#_main_weindow_idparent').css({'z-index':'1100'});
					$('div[id=XYTipsWindowBg]').eq(0).css({'z-index':'1000'});
					$('div[id=XYTipsWindowBg]').eq(1).css({'z-index':'8000'});
				},
				___callback:function(val){
					if(val=='确定'){
						$('#_window_buy_btn').attr('onclick','');
						itemColor = encodeURI(itemColor);
						itemSize = encodeURI(itemSize);
						var stamp = new Date().getTime();
						$.getJSON('createOrder',{'stockId':stockId,'itemId':itemId,'itemColor':itemColor,'itemSize':itemSize,'itemType':itemType,'timestamp':stamp},function(info){
							$('#_window_buy_btn').attr('onclick','$this.submitBy()');
							if(info!=null){
								if(info.info=='200'){
									$this.toPayInterface(info.orderId);
								}else if(info.info =='404'){
									$this.alert('无法获取您的手机号,请在个人中心设置您的手机号!');
								}else if(info.info =='000'){
									$this.alert('该商品库存不足!');
									
								}else if(info.info=='101'){
									$this.alert("抱歉,该商品已下架,请选择其它商品!");
								}else{
									$this.alert('服务器正忙,请稍后再试!');
								}
								
							}else{
								$this.alert('服务器正忙,请稍后再试!');
							}
						 })
					}
				}
			});
	 }
	
},
/**
 * 获取该商品的扩展数据
 */
	detail:function(no,itemType){
	//重置数据
	$('#_window_color_ul').html('');
	$('#_window_size_ul').html('');
	$('._window_money').html('');
	
	var itemColor = $('#orderItemColor').val('');
	var itemSize = $('#orderItemSize').val('');
	var stockId = $('#orderStockId').val('');
	
	//设置导航 
	//初始化
	$('#_window_info_kucun').html('');
	$('._window_chose_total').html('').css({'display':'none'});
	
	var typeName = "";
	if(itemType ==0){
		
		typeName="男鞋：";
	}else if(itemType==1){
		typeName="女鞋：";
	}else if (itemType==2){
		typeName="童鞋：";
	}else if (itemType==3){
		typeName="男装：";
	}else if (itemType==4){
		typeName="女装：";
	}else if (itemType==5){
		typeName="配件：";
	}
	$('._window_chose_type').text(typeName).show();
	
		$.getJSON('detail',{'itemId':no,'itemType':itemType},function(json){
			var info = json.info;
			if(info!=null && info == 'notLogin'){
					$this.gotoLogin();
				} else if (info=='success' ) {// 商品详情
					var item = json.item;
						if(item!= null){
							var colorLst = item.colorLst;
							var sizeLst = item.sizeLst;
							var model = item.model;
							
							var sizeLi='';
							var sizeA='';
							var colorLi='';
							var colorA='';
							// 设置参数
							$('._window_img img').attr('src',model.imgPath);
							
							$('#orderItemId').val(model.id);
							$('._window_money').html('￥ '+model.shopPrice);
							var name = model.name;
							if(name.length > 34){
								name=name.substr(0,33)+'...';
							}
							
							$('._window_txt_title').text(name);
							$.each(sizeLst,function(i,v){
								sizeLi = $('<li></li>');
								sizeA = $("<a href='javascript:void(0);' onclick='$this.checkedSize(this)'></a>");
								sizeLi.append(sizeA);
								sizeA.text(v);
								$('#_window_size_ul').append(sizeLi);
							});							
							$.each(colorLst,function(i,v){
								colorLi =$('<li></li>');
								colorA = $("<a href='javascript:void(0);' onclick='$this.checkedColor(this)'></a>");
								colorLi.append(colorA);
								colorA.append(v);
								$('#_window_color_ul').append(colorLi);
							});
						}
					$this.thisWindow();
				}else {
					$this.alert('服务器繁忙,请稍后再试!')
				}
		})
	},
	/**
	 * 跳转确认登录 窗口
	 */
	gotoLogin:function(){
		$.XYTipsWindow({
			___title:"对话框",
			___content:"text:您尚未登录,现在是否进行登录?",
			___width:"300",
			___height:"100",
			___showbg:true,
			___button:["确定","取消"],
			___drag:"___boxTitle",
		    ___callback:function(val){
				if(val=='确定'){
					$this.changeLoginUrl();
				}
			}
		});
	},
	changeLoginUrl:function(){
		var backurl=window.location.href;
		window.location.href = 'http://ca.12580life.com/login?service='+backurl;
		//window.location.href="http://js.ac.10086.cn/jsauth/logon.html?channel=00800&backurl=http%3A%2F%2Fh.12580life.com%2Fulogin%2Fpro_sso_sync.jsp%3FbackURL%3D"+backurl;
		
	},
	remark:function(itemId){
		$.XYTipsWindow({
			___title:"商品详情",
			___content:"url:get?remark?itemId="+itemId,
			___width:"780",
			___height:"500",
			___drag:"___boxTitle",
			 ___titleClass:"_window_detail_box_title",
			___boxBdColor:"#e02828",
			___boxID:"_window_detail_box",
			___showbg:true
		});
	},
	/**
	 * 根据选择的条件刷新页面
	 */
	refresh:function(id,value,obj){
		value = encodeURI(value);
		if($(obj).attr('class') == 'active-a'){
			value='';
		}
		$('#'+id).val(value);
		$('#welfareForm').submit();
		
	},
	/**
	 * 设定背景,根据选择的颜色获取尺码
	 */
	checkedColor:function(obj){
		$('#orderItemSize').val('');
		$('#orderItemColor').val('');
		$('._window_chose_color').html('').hide();
		$('._window_chose_size').html('').hide();
		var val = $(obj).text();
		//设置导航
		$('._window_chose_color').html(val).show();
		
		$('#_window_color_ul li a').attr('class','');
		
		$('#orderItemColor').val(val);
		//初始化
	
		$(obj).attr('class','_window_active')
		$this.getSizeByColor();
	},
	/**
	 * 设定背景, 根据选择的尺码获取颜色
	 */
	checkedSize:function(obj){
			$('#orderItemSize').val('');
			$('#orderItemColor').val('');
			$('._window_chose_color').html('').hide();
			$('._window_chose_size').html('').hide();
			var val = $(obj).text();
			$('._window_chose_size').text(val).show();
			$('#_window_size_ul li a').attr('class','');
			$('#orderItemSize').val(val);
			 
			$(obj).attr('class','_window_active');
			$this.getColorsBySize();
		},
		/**
		 * 设定背景, 获取库存量
		 */
		checkedColorGetStock:function(obj){
			var val = $(obj).text();
			$('._window_chose_color').text(val).show();
			$('#_window_color_ul li a').attr('class','');
			
			$('#orderItemColor').val(val);
			$(obj).attr('class','_window_active')
			$this.getStock();
		},
		/**
		 * 设定背景, 获取库存量
		 */
		checkedSizeGetStock:function(obj){
			var val = $(obj).text();
			$('._window_chose_size').text(val).show();
			$('#_window_size_ul li a').attr('class','');
			$('#orderItemSize').val(val);
			$(obj).attr('class','_window_active');
			$this.getStock();
		},
		/**
		 * 根据尺码获取颜色
		 */
		getColorsBySize:function(){
			
			$('#_window_info_kucun').html('');
			$('#_window_color_ul').html('');
			var itemType = $('#orderItemType').val();
			var itemId = $('#orderItemId').val();
			var itemSize = $('#orderItemSize').val();
			itemSize = encodeURI(itemSize);
			if(itemType!=null&&itemType != ''&&itemId!=null&&itemId!=''&&itemSize!=null&&itemSize!=''){
			$.getJSON('getColorsBySize',{'itemType':itemType,'itemId':itemId,'itemSize':itemSize},function(info){
				if(info!=null){
					var result= info.result;
					if(result == 'err'){
						$this.alert("服务器正忙,请稍后再试!");
						
					}else{
						var colorLi;
						var colorA;
						$.each(result,function(i,v){
							colorLi =$('<li></li>');
							colorA = $("<a href='javascript:void(0);' onclick='$this.checkedColorGetStock(this)'></a>");
							colorLi.append(colorA);
							colorA.append(v.itemColor);
							$('#_window_color_ul').append(colorLi);
						});
					}
				}
				
			});
			}
		},
		/**
		 * 根据颜色获取尺码
		 */
		getSizeByColor:function(){
			$('#_window_info_kucun').html('');
			$('#_window_size_ul').html('');
			var itemType = $('#orderItemType').val();
			var itemId = $('#orderItemId').val();
			var itemColor = $('#orderItemColor').val();
			itemColor = encodeURI(itemColor);
			if(itemType!=null&&itemType != ''&&itemId!=null&&itemId!=''&&itemColor!=null&&itemColor!=''){
				$.getJSON('getSizeByColor',{'itemType':itemType,'itemId':itemId,'itemColor':itemColor},function(info){
					if(info!=null){
						var result= info.result;
						if(result == 'err'){
							$this.alert("服务器正忙,请稍后再试!");
						}else{
							var sizeLi;
							var sizeA;
							$.each(result,function(i,v){
								sizeLi = $('<li></li>');
								sizeA = $("<a href='javascript:void(0);' onclick='$this.checkedSizeGetStock(this)'></a>");
								sizeLi.append(sizeA);
								sizeA.text(v.itemSize);
								$('#_window_size_ul').append(sizeLi);
							});
						}
					}
				});
			}
		},
		alert:function(content){
				$.XYTipsWindow({
					___title:"对话框",
					___showTitle:false,
					___content:"text:<div style='width:200px;text-align:left;padding:5px 15px;line-height:25px;'>"+content+"</div>",
					___width:"300",
					___height:"100",
					___drag:"___boxTitle",
					___button:["确定"],
					___showbg:true,
					___boxWrapBdColor:'red',
					___fns:function(val){
						$('#_main_weindow_idparent').css({'z-index':'1100'});
						$('div[id=XYTipsWindowBg]').eq(0).css({'z-index':'1000'});
						$('div[id=XYTipsWindowBg]').eq(1).css({'z-index':'8000'});
					}
			});
		},
		toPayInterface:function(orderId){
			window.location.href= 'http://mall.12580life.com/order/welfare/toPay.chtml?orderId='+orderId
		}
}