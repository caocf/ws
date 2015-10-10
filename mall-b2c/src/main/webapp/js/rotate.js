	var prize;   //奖品信息的集合
	var prizenum;  //转盘的格数
	var pr;
	var rotatestate=0;
	
	$(function(){
			var activeId = active.activeId;
			var imgs = active.confs;
			prize = active.prizes;    //将奖品信息 存入
			var imgUrl = active.imgUrl;
			for(var i=0; i < imgs.length;i++){
				if(imgs[i].key=="hitImg"){
					$("#alert_window").css("background","url("+imgUrl+imgs[i].value+")");
				}
				 if(imgs[i].key=="pointerImg"){
					
					$("#lotteryBtn").attr("src",imgUrl+imgs[i].value);
					
				} 
				if(imgs[i].key=="bgImg"){
					$(".rotate-bg").css("background","url("+imgUrl+imgs[i].value+")");
				}
				if(imgs[i].key=="prizeNum"){
					prizenum= imgs[i].value;   //奖品数量
				}
			}
			
				
				$("#lotteryBtn").rotate({ 
				   bind: 
					 { 
						click: function run(){
					        //转盘旋转中无法再次点击.
							if(rotatestate == 1){
								return;
							}
							  $.ajax({
									type:'POST',
									url:G_CTX_ROOT+'/active/prize.chtml',
									dataType:'text',
									data:{"act_id":activeId},
									success:function(data){
										if(data == "login"){
											rotateFunc(prizenum,noprize(),"未登录，请先登录！");
											return;
										}
										var obj = eval('(' +data + ')'); 
										var result = obj.result;
										var average  = prizenum; //转盘格数
										
										if(obj.code == 0){
											if(result != 0){
												rotateFunc(average, haveprize(result), pr.hitMsg);
											}else{
												rotateFunc(average, noprize(),"没有中奖");
											}
										}else{
											rotateFunc(average, noprize(), obj.message);
										}
									},
									error:function(){
										rotateFunc(prizenum, noprize(),"连接超时");
									}
									
								});   
						}
					 }
			
				});
			
			//r();
			
			var rotateFunc = function(awards,angle,text){  //awards:奖项，angle:奖项对应的角度
				rotatestate = 1;//设置转盘全局状态.
				$('#lotteryBtn').stopRotate();
				$("#lotteryBtn").rotate({
					angle:0, 
					duration: 4000, 
					animateTo: angle+180+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
					callback:function(){
						$("#alert_window").show().text(text);
					}
				}); 
			};
			
			$("#alert_window").click(function(){
				$(this).hide();
				rotatestate = 0;
			});
			
	})
	
	//未中奖 或者 超时 返回 指针的指向（随机）
	function noprize(){
		var weizhi = new Array();
		for(var j=0;j<prizenum;j++){
			weizhi.push(j+1);
		}
		for(var i=0;i<prize.length;i++){
			if(prize[i].hitLevel!=0){
				var p =  prize[i].position;
				if (p.indexOf(',')>0){
					var arr=p.split(",");
					for(var j=0;j<arr.length;j++){
						weizhi.splice($.inArray(parseInt(arr[j]),weizhi),1);
					}
				}else{
					weizhi.splice($.inArray(parseInt(p),weizhi),1);
				}
			}
		}
		//特殊情况, 中奖率100%
		if(weizhi.length == 0){
			return 180;
		}
		var n = Math.floor(Math.random() * weizhi.length + 1)-1;  
		var sensing = 360/prizenum/2 + 360/prizenum*(weizhi[n]-1);
		return sensing;
	}
	
	
	//中奖位置
	function haveprize(result){
		for(var i=0;i<prize.length;i++){
			if(prize[i].hitLevel==result){
				pr = prize[i];
				var p =  prize[i].position;
				
				if (p.indexOf(',')>0){
					var arr=p.split(",");
					var n = Math.floor(Math.random() * arr.length + 1)-1;  
					var address =parseInt(arr[n]);
					var sensing =  360/prizenum/2+360/prizenum*(address-1);
					return sensing;
				}else{
					var address =parseInt(p);
					var sensing =  360/prizenum/2+360/prizenum*(address-1);
					return sensing;
				}
				 
			}
		}
	}
	

	