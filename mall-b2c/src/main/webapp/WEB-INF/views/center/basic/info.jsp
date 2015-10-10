<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>基本资料</title>
         
    <link href="../../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../../css/pro.css" rel="stylesheet" type="text/css" />
    <link href="../../js/lab/default/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="../../css/dealList.css" rel="stylesheet" type="text/css" />
	<link href="../../css/index_v20120705.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../../js/date-picker/WdatePicker.js" ></script>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../../js/cart-common.js"></script>
    <script type="text/javascript" src="../../js/usercenter.js"></script>
    <script type="text/javascript" src="../../js/ad.js"></script>
    <script type="text/javascript" src="../../js/global.js"></script>
    <script type="text/javascript" src="../../js/base.js"></script>
    <script type="text/javascript" src="../../js/pagescroll.js"></script>
    <script type="text/javascript" src="../../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../../js/jquery.simplemodal.js"></script>
    <script type="text/javascript" src="../../js/prototype-1.6.1.js"></script>
    
    
<style type="text/css">
    .txt {
        margin-right:10px;
        width:110px;
        border-color:#666666 #EEEEEE #EEEEEE #666666;
        border-style:solid;
        border-width:1px;
        padding:2px;
    }
    </style>
  <script type="text/javascript">
  	var web_url = '账户管理';
  	
    function getValueIE8(id){ 
        var ip = document.getElementById(id); 
        ip.select(); 
        return document.selection.createRange().text; 
    }
    
    function checkFileExt(ext)
    {
        
        if (!ext.match(/.jpg|.gif/i)) {
            return false;
        }
        return true;
    }
    

//计算字符串长度   
function strlen(str) {   
   var len = 0;   
   for (var i = 0; i < str.length; i++) {   
      if (str.charCodeAt(i) > 255 || str.charCodeAt(i)<0){
            len += 2; 
          } else {
            len ++;
          } 
   }   
    return len;   
}

/**
 * 验证修改资料信息是否合法
 * @return true:合法 false：不合法
 */
function validateForm()
{

    if(!protF("qq").blank() && !isNumber(protF("qq")))
    {
        alert("您的QQ号码输入不正确！");
        $("#qq").focus();
        return false;
    }else if(!protF("userPhoto").blank() && !protF("userPhoto").endsWith('jpg')&&!protF("userPhoto").endsWith('JPG')
            && !protF("userPhoto").endsWith('gif') && !protF("userPhoto").endsWith('GIF')){
        alert("对不起，您上传的头像必须是JPG或GIF格式的文件！");
        $("#userPhoto").focus();
        return false;
    }
    
    return true;
}

$(function() {
	//顶部HOVER下拉菜单
	$("*[func=dropmenu]").hover(
		function(){
			$(this).find(".menuitens").show();
			$(this).find(".handle").addClass("current");
		},
		function(){
			$(this).find(".menuitens").hide();
			$(this).find(".handle").removeClass("current");
		}
	);
	
	//侧边栏手风琴菜单
	$("[func=folder]").find("h3").toggle(
	function(){
		$(this).next().slideUp('fast');
		$(this).find("b").addClass("fold");
	},
	function(){
		$(this).next().slideDown('fast');
		$(this).find("b").removeClass("fold");
	});

});

$(function(){ 
	$('#tijiao').click(function(){
		if(!validateForm()){
	        return false;
	    }else{
			$("#userForm").submit();
	    }
	});
});
 
</script>
<style type="text/css">
  #rRightDown,#rLeftDown,#rLeftUp,#rRightUp,#rRight,#rLeft,#rUp,#rDown{
      position:absolute;
      background:#FFF;
      border: 1px solid #333;
      width: 6px;
      height: 6px;
      z-index:500;
      font-size:0;
      opacity: 0.5;
      filter:alpha(opacity=50);
  }
  
  #rLeftDown,#rRightUp{cursor:ne-resize;}
  #rRightDown,#rLeftUp{cursor:nw-resize;}
  #rRight,#rLeft{cursor:e-resize;}
  #rUp,#rDown{cursor:n-resize;}
  #rLeftDown{left:-4px;bottom:-4px;}
  #rRightUp{right:-4px;top:-4px;}
  #rRightDown{right:-4px;bottom:-4px;background-color:#00F;}
  #rLeftUp{left:-4px;top:-4px;}
  #rRight{right:-4px;top:50%;margin-top:-4px;}
  #rLeft{left:-4px;top:50%;margin-top:-4px;}
  #rUp{top:-4px;left:50%;margin-left:-4px;}
  #rDown{bottom:-4px;left:50%;margin-left:-4px;}
  
  #bgDiv{width:300px; height:300px; position:relative;}
  #dragDiv{border:1px dashed #fff; width:100px; height:100px; top:0px; left:0px; cursor:move; }
  </style>
    </head>
    <body id="usercenter">
    <script type="text/javascript" src="../../js/head.js"></script>
       <div class="wrapper mauto" >
 
     <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;基本信息</span></div>
    <div id="main" class="fr  w750">
		<div class="body clearfix">

				<h1 class="pagelocal">我的账户</h1>
				<div class="block withoutborder" style="margin-top: 10px;">
					<div class="maintab">
						<ul class="tabs clearfix">
							<li attr="tab" class="current">
								<a href="info.chtml" name="tabSkip">基本资料</a>
							</li>
							<li attr="tab">
								<a href="editPwd.chtml" name="tabSkip">密码修改</a>
							</li>			
						</ul>
						<form action="/center/info.chtml" name="userForm" id="userForm" enctype="multipart/form-data" method="post">
							<div class="content" attr="cnt" style="display: block;">
								<input type="hidden" id="imageX" name="imageX" value="0" /> 
								<input type="hidden" id="imageY" name="imageY" value="0" /> 
								<input type="hidden" id="imageWidth" name="imageWidth" value="100" />
								<input type="hidden" id="imageHeight" name="imageHeight" value="100" /> 
								<input type="hidden" id="scale" name="scale" value="1" />
								<ul class="form">

									<li><label class="title">昵称：</label>
										<div class="item">
											<input type="text" name="nickName" 
											<c:if test="${not empty tMember }">
											<c:choose>
												<c:when test="${not empty tMember.nickName }">value="${tMember.nickName }"</c:when>
												<c:when test="${not empty tMember.terminalId }">value="${tMember.terminalId }"</c:when>
												<c:when test="${not empty tMember.email }">value="${fn:substring(tMember.email, 0, fn:indexOf(tMember.email, '@'))}"</c:when>
												<c:otherwise>value=""</c:otherwise>
											</c:choose></c:if>/>
										</div></li>
									<li><label class="title"> 性别： </label>
										<div class="item">
											<label> <input type="radio" name="sex" value="2"
												<c:if test="${tMember.sex ne  1}">checked</c:if> />男
											</label> <label> <input type="radio" name="sex" value="1" <c:if test="${tMember.sex eq 1}">checked</c:if> />女&nbsp;
											</label>
										</div></li>

									<li><label class="title"> 生日： </label>
										<div class="item">
											<input type="text" id="birthDay" name="birthday" value="${tMember.birthday }"
												class="txt Wdate"
												onfocus="WdatePicker({realDateFmt:'yyyyMMdd'})" />
										</div></li>
									<li><label class="title"> 邮箱： </label>
										<div class="item">
											<c:if test="${empty tMember.email }"><label><a href="javascript:void(0);" style="color:blue" id="bindEmail">绑定邮箱</a></label></c:if>
											<c:if test="${not empty tMember.email }">
												<label>${tMember.email }<input type="hidden"
													name="email" id="email" value="${tMember.email }" /></label> <label><a
													href="javascript:void(0);" style="color: blue"
													id="editEmail">修改邮箱</a></label>
											</c:if>
										</div>
										<div id="editEmailDialog" class="basic-modal-content">
											<iframe frameborder="no" scrolling="no" <c:if test="${not empty tMember.email }">src="editEmail.chtml"</c:if><c:if test="${empty tMember.email }">src="bindEmail.chtml"</c:if>
												width="100%" height="100%"></iframe>
										</div></li>
									<li><label class="title"> 手机号： </label>
										<div class="item">
											<c:if test="${empty tMember.terminalId }"><label><a href="javascript:void(0);" style="color:blue" id="bindMobile">绑定手机号</a></label></c:if>
											<c:if test="${not empty tMember.terminalId }">
												<label>${tMember.terminalId }<input type="hidden"
												name="terminalId" id="phoneNum" value="${tMember.terminalId }" /></label><label><a
												href="javascript:void(0);" style="color: blue"
												id="editMobile">修改号码</a></label>
											</c:if>
										</div><!-- <c:if test="${not empty tMember.terminalId }">src="editMobile.chtml"</c:if> -->
										<div id="editMobileDialog" class="basic-modal-content">
											<iframe frameborder="no" scrolling="no"
												<c:if test="${empty tMember.terminalId }">src="bindMobile.chtml"</c:if><c:if test="${not empty tMember.terminalId }">src="editMobile.chtml"</c:if> width="100%" height="100%"></iframe>
										</div></li>
									<li><label class="title"> qq： </label>
										<div class="item">
											<input class="per_input" type="text" id="qq" name="qq"
												value="${tMember.qq }" onkeyup="this.value=this.value.replace(/\D/g,'')"
												onafterpaste="this.value=this.value.replace(/\D/g,'')" />
										</div></li>
									<li><label class="title"> 个人签名： </label>

										<div class="item">
											<textarea id="signature" name="signature" rows="5" cols="34"
												class="textv">${tMember.signature }</textarea>
										</div></li>
									<li><label class="title"> 头像上传： </label>
										<div class="item">
											<input size="42" type="file" id="userPhoto" name="userPhoto" />
										</div></li>
									<li><label class="title">&nbsp;</label>
										<div class="item">
											<font style="color: red;">支持jpg,gif格式的,小于2MB的图片</font>
										</div></li>

									<li><label class="title"> &nbsp; </label>
										<div class="item">
											<input border="0" type="button" value="确认保存" id="tijiao" style="cursor: pointer;" />
										</div></li>

									<li>
										<td colspan="2">
											<table>
												<tr>
													<td width="350">
														<div id="bgDiv" style="display: none;">
															<div id="dragDiv">
																<div id="rRightDown"></div>
																<div id="rLeftDown"></div>
																<div id="rRightUp"></div>
																<div id="rLeftUp"></div>
																<div id="rRight"></div>
																<div id="rLeft"></div>
																<div id="rUp"></div>
																<div id="rDown"></div>
															</div>
														</div>
													</td>
													<td><div id="viewDiv"
															style="width: 100px; height: 100px; display: none;">
														</div></td>
												</tr>
											</table>
									</td>
									</li>

								</ul>

							</div>
						</form>
					</div>

				</div>

			</div>

		</div>
		

		<%@include file="../left_menu.jsp"%>
    <span class="clr"></span></div>
		<script type="text/javascript" src="../js/foot.js"></script>
         
        <script type="text/javascript">
        var emailObj = "";
        var mobileObj = "";
		$(function() {
			$('#editEmail, #bindEmail').click(function(e) {
				$.removeCookie('emailUrl');
				emailObj = $('#editEmailDialog').modal({onClose: function (dialog) {
					$.modal.close();
				    if($.cookie("emailUrl")){
						location.href = $.cookie("emailUrl");
					}
				}});
				return false;
			});
			$('#editMobile, #bindMobile').click(function(e) {
				mobileObj = $('#editMobileDialog').modal({onClose: function (dialog) {
					$.modal.close();
					//location.href = $.cookie("mobileUrl");
				}});
				return false;
			});
		});
	</script>
        <script>
    $(function(){
        $("#myinfo").addClass("col_link");
    })
</script>
    </body>
</html>
