// JavaScript Document


function showAuto3(){
			p++;
			if(p>jQuery(".banner_fix").children("li").length){p=0}
			else{
				
				var j=p-1
				jQuery(".banner_fix").children("li:eq("+j+")").animate({opacity:1},800).show().siblings("li").animate({opacity:0},800).hide()
				jQuery(".banner_item:eq("+j+")").addClass("banner_item_hover").siblings(".banner_item").removeClass("banner_item_hover")
				}
				
			
	}
	
	function showAuto4(us){
		
					var m=us.val()
					var s=m-1
					jQuery(us).addClass("banner_item_hover").siblings(".banner_item").removeClass("banner_item_hover")
					jQuery(".banner_fix").children("li:eq("+s+")").animate({opacity:1},800).show().siblings("li").animate({opacity:0},800).hide()
					
	}