$(function(){ 
	$(".related-title h3,.related-title-hide h3").click(function(){
		if($(this).attr("class")=="slide_down"){
			$(this).removeClass().parents(".related-title-hide").removeClass().addClass("related-title").next(".side_slide").removeClass("none");					
			}else{
			$(this).addClass("slide_down").parents(".related-title").removeClass().addClass("related-title-hide").next(".side_slide").addClass("none");
		}	  
	})
})