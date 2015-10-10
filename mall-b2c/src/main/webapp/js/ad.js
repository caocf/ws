/*
$(function(){ 
	function tclick(){
		$(this).children("a.g_next").trigger("click");	
		setTimeout("tclick()",5000);
	}
	$(".pro_gallery").each(function(){
		var page = 1;								
		var i = $(this).children(".gallery_warp").children("img").length;
		var imgwidth = 240;
		$(this).children(".gallery_warp").width(imgwidth*i);
		var $cont_div = $(this).children(".gallery_warp");
		$(this).children("a.g_next").click(function(){
			if(!$cont_div.is(":animated")){
				if( page == i){
					$cont_div.animate({left:"0px"},"slow");
					page = 1;
					
				}else{
					$cont_div.animate({left: '-='+imgwidth+'px'},"slow");
					page++;		
				}
			}
		});
		tclick();
	});

});
*/
displayAd240X320Pro = function(json,id,appendObj){
	var dataArray = new Array();
	if(json.t_img!=null && json.t_img!="undefined"){
		dataArray = json.t_img;
	}
	var str;
	if(dataArray.length<=0){
		str = "<div class='pro_gallery mt10'></div>";
		$("#pro").append(str);
	}else{
		for(var i=0;i<dataArray.length;i++){
			str = "<div class='pro_gallery mt10'><div class='gallery_warp'><a target='_blank' href='"+dataArray[i].url+"'><img width='240px' height='320px' alt='"+dataArray[i].text+"' src='"+dataArray[i].picurl+"'></a></div></div>";
			$("#"+appendObj).append(str);
		}
	}
	
}

/* 首页广告js*/
displayAdHomePro = function(json,id,appendObj){
	var imgsArray = new Array();
	var textArray = new Array();
	if(json.t_img!=null && json.t_img!="undefined"){
		imgsArray = json.t_img;
	}
	if(json.t_txt!=null && json.t_txt!="undefined"){
		textArray = json.t_txt;
	}
	var imgStr = "";
	var texStr = "";
	var str = "<div class='life-item-sub-pro'>";
	//图片内容
	if(imgsArray!="undefined" && imgsArray.length>0){
		for(var i=0;i<imgsArray.length;i++){
			imgStr+= "<a target='_blank' href='"+imgsArray[i].url+"'><img width='180' height='80' alt='"+imgsArray[i].text+"' src='"+imgsArray[i].picurl+"'></a>";
		}
		str+=imgStr;
	}
	//文字内容
	if(textArray!="undefined" && textArray.length>0){
		texStr+="<h2>推荐：</h2>";
		if(textArray[0].picurl.length>6){
			for(var j=0;j<textArray.length;j++){
				texStr+= "<p><a target='_blank' href='"+textArray[j].url+"'>"+textArray[j].picurl+"</a></p>";
			}
		}else{
			texStr+="<div>";
			for(var j=0;j<textArray.length;j++){
				texStr+= "<a target='_blank' href='"+textArray[j].url+"'>"+textArray[j].picurl+"</a>";
			}
			texStr+="</div>";
		}
		str+=texStr;
	}
	str+="</div>";
	$("#"+appendObj).append(str);
}
