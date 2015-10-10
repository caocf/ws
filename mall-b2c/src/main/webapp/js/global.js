/*左侧导航*/

$(function(){ 
			$(".mod_search_txt").blur(function () { // 地址框失去鼠标焦点
				var txt_value = $(this).val(); // 得到当前文本框的值
				$(this).addClass("no_cur");
				if (txt_value ==""){
					$(this).val(this.defaultValue); // 如果符合条件，则设置内容
				}
			});
			
			$(window).scroll(function(){
				var s_top = $(document).scrollTop();
				$(".alert_windows").css("top",s_top+120);					  
			});
	
});