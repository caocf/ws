$(function(){ 
	var saleIds = saleId.split(',');
	for(var i=0;i<saleIds.length;i++){		
		/* 评价页面-星星 */	
		$(".ks-star1_"+saleIds[i]).hover(function(){
			$(this).parent(".ks-starwrapper").addClass("ks-starone");
			$(this).parent(".ks-starwrapper").removeClass("ks-startwo");
			$(this).parent(".ks-starwrapper").removeClass("ks-starthree");
			$(this).parent(".ks-starwrapper").removeClass("ks-starfour");
			$(this).parent(".ks-starwrapper").removeClass("ks-starfive");
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(1);
		});
		$(".ks-star2_"+saleIds[i]).hover(function(){
			$(this).parent(".ks-starwrapper").addClass("ks-startwo");
			$(this).parent(".ks-starwrapper").removeClass("ks-starone");
			$(this).parent(".ks-starwrapper").removeClass("ks-starthree");
			$(this).parent(".ks-starwrapper").removeClass("ks-starfour");
			$(this).parent(".ks-starwrapper").removeClass("ks-starfive");
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(2);
		});
		$(".ks-star3_"+saleIds[i]).hover(function(){
			$(this).parent(".ks-starwrapper").addClass("ks-starthree");
			$(this).parent(".ks-starwrapper").removeClass("ks-starone");
			$(this).parent(".ks-starwrapper").removeClass("ks-startwo");
			$(this).parent(".ks-starwrapper").removeClass("ks-starfour");
			$(this).parent(".ks-starwrapper").removeClass("ks-starfive");
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(3);
		});	
		$(".ks-star4_"+saleIds[i]).hover(function(){
			$(this).parent(".ks-starwrapper").addClass("ks-starfour");
			$(this).parent(".ks-starwrapper").removeClass("ks-starone");
			$(this).parent(".ks-starwrapper").removeClass("ks-startwo");
			$(this).parent(".ks-starwrapper").removeClass("ks-starthree");
			$(this).parent(".ks-starwrapper").removeClass("ks-starfive");
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(4);
		});	
		$(".ks-star5_"+saleIds[i]).hover(function(){
			$(this).parent(".ks-starwrapper").addClass("ks-starfive");
			$(this).parent(".ks-starwrapper").removeClass("ks-starone");
			$(this).parent(".ks-starwrapper").removeClass("ks-startwo");
			$(this).parent(".ks-starwrapper").removeClass("ks-starthree");
			$(this).parent(".ks-starwrapper").removeClass("ks-starfour");
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(5);
		});	
		/* 评价页面-星星 */
		$(".ks-star1_"+saleIds[i]).click(function(){
			if ( $(this).parent(".ks-starwrapper").hasClass("ks-starone") ){
				$(".ks-star").bind("hover");
			}else{
				$(".ks-star").unbind("hover");
			}
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(1);
		});
		$(".ks-star2_"+saleIds[i]).click(function(){
			if ( $(this).parent(".ks-starwrapper").hasClass("ks-startwo") ){
				$(".ks-star").bind("hover");
			}else{
				$(".ks-star").unbind("hover");
			}
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(2);
		});
		$(".ks-star3_"+saleIds[i]).click(function(){
			if ( $(this).parent(".ks-starwrapper").hasClass("ks-starthree") ){
				$(".ks-star").bind("hover");
			}else{
				$(".ks-star").unbind("hover");
			}
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(3);
		});
		$(".ks-star4_"+saleIds[i]).click(function(){
			if ( $(this).parent(".ks-starwrapper").hasClass("ks-starfour") ){
				$(".ks-star").bind("hover");
			}else{
				$(".ks-star").unbind("hover");
			}
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(4);
		});
		$(".ks-star5_"+saleIds[i]).click(function(){
			if ( $(this).parent(".ks-starwrapper").hasClass("ks-starfive") ){
				$(".ks-star").bind("hover");
			}else{
				$(".ks-star").unbind("hover");
			}
			$(this).parent(".ks-starwrapper").parent(".ks-simplestar").children(".starValue").val(5);
		});
	}
	
})

function checkComment(){
	var ids = saleId.split(',');;
	for(var j=0;j<ids.length;j++){
		var star = $("#star_"+ids[j]).val();
		var content = $("#message_"+ids[j]).val();
		if(star == ''){
			alert('请打分！');
			return false;
		}
		if(content == '' || content == '请在此处填写您要评价的内容'){
			alert('请填写评价！');
			$("#message_"+ids[j]).focus();
			return false;
		}else{
			if(content.length > 100){
				alert('评价内容过长！');
				$("#message_"+ids[j]).focus();
				return false;
			}
		}
	}
	$("#form1").submit();
}

function limitContent(){
	var obj = arguments[0];
	if(obj.value.length>100){
		obj.value=obj.value.substring(0,100);
	}
}
