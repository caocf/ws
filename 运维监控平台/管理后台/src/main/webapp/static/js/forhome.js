$(function(){ 
	$("#sidebar .menu .hover").parent().parent().parent().addClass("active");
	$("#sidebar .menu .hover").parent().parent().parent().find("span").addClass("down");
	
	$("#sidebar .menu").each(function(){
		if($(this).hasClass("active")){
		$(this).children("ul").show();
		}else{
		$(this).children("ul").hide();	
			}})

	$(".menu li a.hover").parent("li").addClass("li_hover");
	$(".menu h2 a").click(function(){
		if($(this).parents(".menu").hasClass("active")){	
			$(this).parents(".menu").removeClass("active");
			$(this).parents("h2").next("ul").hide();
			$(this).children("span").removeClass("down");		
		
		}else{
			$(".menu").removeClass("active").children("ul").hide();	
			$(this).parents(".menu").addClass("active");
			$(this).parents("h2").next("ul").show();
			$(this).children("span").addClass("down");	
		}
	
		return false;
	});
	$(".menu li a").click(function(){
		$(".menu li a").removeClass("hover").filter($(this)).addClass("hover");						   
		$(".menu").removeClass("active").filter($(this).parents(".menu")).addClass("active");	
	})
	
	$('input.default-value').each(function() {
 
       var default_value = this.value;
 
       $(this).focus(function(){
               if(this.value == default_value) {
                       this.value = '';
               }
       });
 
       $(this).blur(function(){
               if(this.value == '') {
                       this.value = default_value;
               }
       });
	   
	});
	$(".ordermore").toggle(function(){
		$(this).val("返回").parent(".line").next(".operate_his").show();
		return false;
	},function(){
		$(this).val("查看详情").parent(".line").next(".operate_his").hide();
		return false;
	});
		
	$(".link_product").click(function(){
		$("#link_product").show();
		$(".alert").css("top",$(document).scrollTop()-100);
		return false;
	});
	$(".btn_close").click(function(){
		$(this).parent(".alert").hide();							   
	});

	$(".btn").click(function(){
			$(this).parent().parent(".alert").hide();							   
	
	});
	$("input:checkbox").addClass("checkbox");


	(new SidebarFollow()).init({   
		element: jQuery('#sidebar-follow'),   
		prevElement: jQuery('#recent-comments'),   
		distanceToTop: 15  
	});   

});		