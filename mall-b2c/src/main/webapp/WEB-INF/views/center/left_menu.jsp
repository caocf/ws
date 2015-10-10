<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/css/cart.css" rel="stylesheet" type="text/css" />
<script>
	$(function() {
	

	
	$(".m_union").live("click",function(){
		 window.location.href="../center/unions.chtml?url="+encodeURIComponent($(this).attr("url"));
      });
	
		$.ajax({
			url : '../user/getMenus.chtml',
			dataType : "json",
			cache : false,
			success : function(data) {
				//判断后台返回的值
				if (G.isEmpty(data) || !data.success) {
					return;
				}
				var menuDTOs = data.menuDTOs;
				if (G.isEmpty(menuDTOs)) {
					return;
				}
				var _html = '';
				for ( var i = 0; i < menuDTOs.length; i++) {
					var menuDTO = menuDTOs[i];
					var name = G.normalize(menuDTO.name);
					_html += '<div class="related-title">' + '<h3 class="nbd">'
							+ name + '</h3>' + '</div>';
					_html += '<div class="side_slide mb10">'
							+ '<div class="related-content"><ul>';
					var cMenuDTOs=menuDTO.children;		
					if(!G.isEmpty(cMenuDTOs)){
					     for(var j=0;j<cMenuDTOs.length;j++){
					        var cMenuDTO=cMenuDTOs[j];
					        var name=G.normalize(cMenuDTO.name);
					        var href=cMenuDTO.href;
					       if(cMenuDTO.type=="union"){
					          _html+='<li ';
							  if(name==web_url){
								_html+=' class="active" ';
							  }
							  _html+='><a class="m_union" url="'+href+'">'+name+'</a></li>';
					        }else if(cMenuDTO.type=="go_url"){
							   _html+='<li><a target="_blank" href="'+href+'">'+name+'</a></li>';	
							}else{		        
								_html+='<li ';
								if(name==web_url){
								_html+=' class="active" ';
								}
								_html+='><a href="'+href+'">'+name+'</a></li>';
							} 
					     }
					     }
					_html += '</ul></div></div>';

				}
				$("#sidebar_item").append(_html);

			},
			error : function() {

			}
		});
		
		$.ajax({
			url : '../center/getMemberInfo.chtml',
			dataType : "json",
			cache : false,
			success : function(data) {
				if (G.isEmpty(data)) {
					return;
				}
				var name=data.nickName;
				var avatar=data.avatar;
				//控制会员等级显示
				$(".sidebar_menu .user_name").text(G.normalize(name));
				if(data.isUnionMember){
				  $(".union_member_img").show();
				}
				 if(data.isRedMember){
				   $(".red_member_img").show();
				}
				if(!data.isUnionMember&&!data.isRedMember){
				  $(".member_link").show();
				}
				
				
				avatar=G.isEmpty(avatar)?"../img/perfile.png":("../"+avatar);
			    $(".profileimg").attr("src",avatar);
			},
			error : function(e) {
               console.debug(e);
			}
		});
		
	  

	});
	
</script>
<div class="w180 fl" id="sidebar">
	<div class="sidebar_profile sidebar_menu">
		<img class="profileimg" />
		<p>
			<a class="user_name"></a>
		</p>
		<p>
			<img src="../img/usercenter_vip.png" class="disinline union_member_img" style="display:none"/> 
			<img src="../img/usercenter_red.png" class="disinline red_member_img" style="display:none"/> 
			<a href="http://e.12580life.com/kthy/index.jsp" target="_blank" class="member_link" style="display:none">开通会员</a>
		</p>
	</div>
	<div id="sidebar_item"></div>
</div>
