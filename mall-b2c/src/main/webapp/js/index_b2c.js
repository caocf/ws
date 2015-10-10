/*左侧导航*/
function lifeHelper() {
    var a = jQuery("#life-help .life-item");
    jQuery.each(a, 
    function(b, c) {
        jQuery(c).hover(function(){
            jQuery(this).addClass("lh-hover");
			
			var d=jQuery(this).offset().top-$(document).scrollTop()+jQuery(this).children(".life-item-sub").height()-$(window).height();
			
			e=d+20;
			f=jQuery(this).offset().top-275;
			if(e>0){				
				if(e>f&&f>0){
					jQuery(this).children(".life-item-sub").css("top",-f);
					}else{
					jQuery(this).children(".life-item-sub").css("top",-e);	
						}
			}else{
				return false;
				}
        },
        function() {
            jQuery(c).removeClass("lh-hover");
        })
    })
}

/*焦点图轮播*/
var sliderIndex = 0;
var timer;
function slideTo(a) {
    var b = 580;
    jQuery("#body_slide").stop(true, false).animate({
        marginLeft: -(b * a)
    },
    {
        duration: 500
        //easing: "easeInOutQuad"
    });
    var c = document.getElementById("bor_slide").getElementsByTagName("li");
    jQuery.each(c, 
    function(d, e) {
        e.className = "";
        if (d == a) {
            e.className = "curr";
            sliderIndex = d
        }
    })
}
function slideShift() {
    slideTo(sliderIndex);
    sliderIndex++;
    if (sliderIndex >= picList0_size) {
        sliderIndex = 0
    }
    timer = setTimeout("slideShift()", 5000)
}

function slideShow() {
    slideTo(0);
    var a = document.getElementById("bor_slide").getElementsByTagName("li");
    jQuery.each(a, 
    function(b, c) {
        c.onmouseover = function() {
            slideTo(b)
        }
    });
    slideShift();
    jQuery("#slider").hover(function() {
        clearTimeout(timer)
    },
    function() {
        slideShift()
    })
}
/*切换内容*/
function eachAd(){
	jQuery(".tab_handle li").mouseover(function(){
	    jQuery(this).parent().children("li").removeClass("curr")
		jQuery(this).addClass("curr");
		var a=($(this).index());
		jQuery(this).parent().prev().find("li").hide();
		jQuery(this).parent().prev().find("li").eq(a).show();
	});
}

