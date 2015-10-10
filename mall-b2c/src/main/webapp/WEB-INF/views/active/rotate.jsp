<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	
	<title>抽奖活动</title>
	
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	 <script type="text/javascript" src="<c:url value="/js/static.chtml"/>"></script>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/jQueryRotate.js"></script>
	<script src="../js/jquery.easing.min.js"></script>
	<script src="../js/rotate.js"></script>
	<style>
	*{padding:0;margin:0}
	body{
		text-align:center;
				
	}
	.ly-plate{
		position:relative;
		width:509px;
		height:509px;
		margin: 50px auto;
	}
	.rotate-bg{
		width:509px;
		height:509px;
		background:url(ly-plate.png);
		position:absolute;
		top:0;
		left:0
	}
	.ly-plate div.lottery-star{
		width:214px;
		height:214px;
		position:absolute;
		top:150px;
		left:147px;
		/*text-indent:-999em;
		overflow:hidden;
		background:url(rotate-static.png);
		-webkit-transform:rotate(0deg);*/
		outline:none
	}
	.ly-plate div.lottery-star #lotteryBtn{
		cursor: pointer;
		position: absolute;
		top:0;
		left:0;
		*left:-107px
	}
	#alert_window{ position:absolute; left:50%;top:200px; cursor:pointer; width:350px; height:50px; padding:50px 25px 100px; font-size:18px; color:#000000; z-index:10000; margin-left:-200px; display:none; line-height:25px; }
	
</style>
	
	
</head>

<body>
	<div class="ly-plate">
    	<div id="alert_window"></div>
		<div class="rotate-bg"></div>
		
		<div class="lottery-star"><img src="#" id="lotteryBtn"></div>
	</div>
</body>




<script>

/* 	var prize;   //奖品信息的集合
	var prizenum;  //转盘的格数
	var pr;
	
$(function(){
	
			var imgs = ${active.confs};
			prize = ${active.prizes};    //将奖品信息 存入
			var imgUrl = '${active.imgUrl}';
			for(var i=0; i < imgs.length;i++){
				if(imgs[i].key=="bg-img"){
					$("#alert_window").css("background","url("+imgUrl+imgs[i].value+")");
				}
				 if(imgs[i].key=="pointer-img"){
					
					$("#lotteryBtn").attr("src",imgUrl+imgs[i].value);
					
				} 
				if(imgs[i].key=="hit-img"){
					$(".rotate-bg").css("background","url("+imgUrl+imgs[i].value+")");
				}
				if(imgs[i].key=="prizeNum"){
					prizenum= imgs[i].value;   //奖品数量
				}
			}
			
	})
	var rotateFunc = function(awards,angle,text){  //awards:奖项，angle:奖项对应的角度
		$('#lotteryBtn').unbind('click');   //禁用点击
		$('#lotteryBtn').stopRotate();
		$("#lotteryBtn").rotate({
			 angle:0, 
			duration: 4000, 
			animateTo: angle+180+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
			callback:function(){
				$("#alert_window").show().text(text);
				r();
			}
		}); 
	};
	
	
	var r = function(){
		$("#lotteryBtn").rotate({ 
	   bind: 
		 { 
				click: function run(){
					
					rotateFunc(prizenum,noprize(),"远程服务器连接失败");
					  $.ajax({
							type:'POST',
							url:'${ctx}/active/prize.chtml',
							data:{"act_id":1},
							success:function(data){
								var obj = JSON.parse(data);
								//var obj={code:0,message:"11",result:1}
								var result = obj.result;
								var average  = prizenum; //转盘格数
								
								if(obj.code ==0){
									if(result!=0){
										rotateFunc(average,haveprize(result),pr.hitMsg);
									}else{
										rotateFunc(average, noprize(),"没有中奖");
									}
								}else{
									rotateFunc(average,noprize(),obj.message);
								}
								
							},
							error:function(){
								rotateFunc(prizenum, noprize(),"连接超时");
								
								
							}
							
						});   
				
			}
		 } 
	
		});
	}
	
	r();
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
		var n = Math.floor(Math.random() * weizhi.length + 1)-1;  
		var sensing = 360/prizenum/2+360/prizenum*(weizhi[n]-1);
		return sensing;
		
	}
	
	//中奖位置
	function haveprize(result){
		for(var i=0;i<prize.length;i++){
			if(prize[i].hitLevel==result){
				pr = prize[i];
				var p =  prize[i].position;
				//alert(p);
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
	

	$("#alert_window").click(function(){
		$(this).hide();
	}); */

</script>
</html>