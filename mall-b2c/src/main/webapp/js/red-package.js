	var prize;   //奖品信息的集合
	var prizenum;  //转盘的格数
	var pr;
	var rotatestate=0;
	var G_CTX_ROOT = '';
	
	$(function(){
		
		var islogin = 0;
		prize = active.prizes; 
		var activeId = $("#actId").val();
		$("#win").click(function(){
			 $.ajax({
					type:'POST',
					url:G_CTX_ROOT+'/active/prize.chtml',
					dataType:'text',
					data:{"act_id":activeId},
					success:function(data){
						if(data == "login"){
							rotateFunc("未登录，请先登录！");
							islogin=1;
							return;
						}
						if(data == "noMobile"){
							rotateFunc("需要手机号登陆，才能抢红包哦！马上去登陆");
							return;
						}
						var obj = eval('(' +data + ')'); 
						var result = obj.result;
						
						if(obj.code == 0){
							if(result != 0){
								haveprize(result);
								rotateFunc(pr.hitMsg);
							}else{
								rotateFunc("亲，再接再厉，恭喜发财哟！");
							}
						}else{
							var alertContent;
							if(obj.message == '号码不合法'){
								alertContent = '没有购物记录哦，马上购物下月抽IPAD';
							}else if(obj.message == '活动ID为空'||obj.message == '活动ID无法处理'
								||obj.message == '根据活动ID获取不到活动信息'||obj.message == '活动已过期'){
								alertContent = '活动不在有效期内，请随时关注最新动态';
							}else if(obj.message == '抽奖次数已用完'){
								alertContent = '您今天的抽奖次数已用完，明天再来';
							}else{
								alertContent = obj.message;
							}
							
							rotateFunc( alertContent);
						}
					},
					error:function(){
						rotateFunc("连接超时");
					}
					
				});
		});
		
			
			var rotateFunc = function(text){  //awards:奖项，angle:奖项对应的角度
				$("#alert").show().text(text);
			};
			
			$("#alert").click(function(){
				$(this).hide();
				if(islogin == 1){
					 var burl = window.location.href;
				//	window.location.href = 'http://js.ac.10086.cn/jsauth/logon.html?channel=00800&backurl=http%3A%2F%2Fh.12580life.com%2Fulogin%2Fpro_sso_sync.jsp%3FbackURL%3D'+burl;
					 window.location.href = 'http://ca.12580life.com/login?service='+burl;
				}
				rotatestate = 0;
			});
			
	});
	
	function haveprize(result){
		for(var i=0;i<prize.length;i++){
			if(prize[i].hitLevel==result){
				pr = prize[i];
			}
		}
	}
	
	

	