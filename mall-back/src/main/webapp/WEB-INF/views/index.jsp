<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="includes/importer.jsp"%>

<%
response.setHeader("P3P","CP=CAO PSA OUR");//防止ie中使用IFrame或Frameset导致session丢失
session.setMaxInactiveInterval(60 * 60 * 8);
String path  = request.getContextPath();
String flag =  request.getParameter("flag");
if(flag == null){
	flag = "0";
	session.setAttribute("flag",flag);	
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>商户联盟管理系统</title>
<!-- js 以及 css 加载 -->
<!--common js  -->
 <script src="<spring:url value="/static/js/jquery-1.8.3.js"/>"  type="text/javascript" charset="utf-8"></script>
<link href="<spring:url value="/static/css/index_style.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript">
if((self.name =="" || self.name == "menu") && self.name != "menu1"){
	if (top.location != self.location){
	 	top.location=self.location; 
	 }
}else{
	if(self.name == "content-iframe"){
		top.location=self.location;
	}
	
}
//定义异常信息数组
var arrTips = [
'正在登录...', //0
'您需要登录后才能继续浏览或操作！', //1
'请输入用户账号！', //2
'请输入密码!', //3
'请输入验证码!', //4
'验证码输入错误!', //5
'账号或密码不正确!请重新输入!', //6
'账号或密码不正确!请重新输入!', //7
'账号已经被锁定，请联系管理员!', //8
'账号已注销，请联系管理员!', //9
'异常登录!', //10
'您的帐户已过有效期!', //11
'你的密码超过30天未修改，已经被锁定！', //12
'对不起，你的输入数据格式不合法！', //13
'', //14
'对不起，您的密码输错五次，已经被锁定！', //15
'对不起，您的商户信息未找到！', //16
'对不起，您商户的合同已到期！' //17
];

//加载登录提示信息
var loadTip = function() {
  $('#userCode').focus();
};

//移除提示div的class name
var removePromptClass = function(){
	//默认移除所有提示div的class
	$('#prompt').removeClass('correctmsg');
	$('#prompt').removeClass('loginmsg');
	$('#prompt').removeClass('errormsg');
}

var login = function(){
 
	removePromptClass();

	$('#loginBtn').attr("disabled",true);
	$('#prompt').addClass('correctmsg loginmsg');
	$('#prompt').show();
	$('#prompt').html('正在登录，请等待...');
				
	//form ajax提交验证
	$('#loginForm').action = './login';
	
	$.post("./login",$('#loginForm').serialize(),function(respData){
		var ret = respData.replace(/^\s+|\s+$/g, "");
		//alert(ret);
		$('#loginForm').attr("disabled",false);
		$('#loginBtn').attr("disabled",false);
		
		removePromptClass();
		
		if(!jQuery.isNumeric(ret)){
			$('#prompt').addClass('errormsg loginmsg');
			$('#prompt').show();
			$('#prompt').html(arrTips[10]);
			return false;
		}
		if(ret == '0'){
			$('loginForm').attr("disabled",true);
			$('prompt').addClass('correctmsg loginmsg');
			$('prompt').show();
			$('prompt').html(arrTips[ret]);
			window.location='./menu';
		}else if(ret == '14' ){
			window.location='./common/mobileSend.jsp';
		}else if(ret == '18' ){
			$('loginForm').attr("disabled",true);
			$('prompt').addClass('correctmsg loginmsg');
			$('prompt').show();
			$('prompt').html(arrTips[0]); 
			window.location='./shoparea/index.jsp';
		}else{
			$('prompt').addClass('errormsg loginmsg');
			$('prompt').show();
			$('prompt').html(arrTips[ret]);					
		}
	});

}

//body onload事件处理
$(function(){
	/**
	初始化数据
	*/
	$('#imgCode').attr("title","点击重新获得验证码!");
	$('#imgCode').attr("src","vdimg.servlet");

	/**
	初始化处理
	*/
	loadTip();
	
	/**
	初始化事件
	*/
	//点击验证码图片
	$("#imgCode").bind("click", function(){
		$(this).attr("src", "vdimg.servlet?r=" + Math.random());
	});
	
	
	
	//初始化回车按键
	$(window).keydown(function(event){
		if(event.keyCode==13 
				&& event.srcElement.type!='button' 
				&& event.srcElement.type!='submit' 
				&& event.srcElement.type!='reset' 
				&& event.srcElement.type!='textarea' 
				&& event.srcElement.type!=''){
				//敲回车登录
				login();
			}
	});
	//忘记密码
	$("#forgetPwdBtn").bind("click", function(){
		window.location="./common/forget_pwd.jsp";
	});
	
});  

</script>
</head>

<body>
<form action="./login_control.jsp" method="post" id="loginForm" name="loginForm" >
<input type="hidden" name="method" value="login" />
<input type="hidden" name="curent" value="" />
<input type="hidden" name="nowtime" value="" />

<div id="midmain">
	<div id="top"></div>
	<div id="mainbox">
		<div class="leftpic"></div>
			<div class="rightlog">
				
				   	<div id="alertDiv" class="alertDiv">
				   	   <div id="prompt" style="display:none" class="errormsg loginmsg"><p></p></div>
				   	</div>
			   	 
			   
				<div class="alignmid">		   	
					<br/>	
					<table width="100%" border="0" cellspacing="6" cellpadding="0">
						<tr>
					    	<td width="28%"><div align="center" class="fontwhite">用户名</div></td>
					    	<td width="72%">
					    		<input type="text" name="userCode" class="txt" tabindex="1" id="userCode" value="system" maxlength="10" />
					    	</td>
					  	</tr>
					  	<tr>
					    	<td><div align="center" class="fontwhite">密&nbsp;&nbsp;&nbsp;码</div></td>
					    	<td>
					    		<input type="password" name="userPwd" class="txt" tabindex="2" id="userPwd"  value="abc123!@" maxlength="10" />
					    	</td>
					  	</tr>
					  	<tr>
					    	<td><div align="center" class="fontwhite">验证码</div></td>
					    	<td>
					    		<input type="text" name="verfyCode" class="txt" maxlength="4" tabindex="2" AUTOCOMPLETE="off" id="verfyCode" value="" style="margin-right:5px;width:66px;" />
					      		<!-- <img style="cursor: hand;" width="58" id="imgCode" height="21" /> -->
					      		<img style="cursor: hand;"  width="58" height="21" id="imgCode"/>
					    	</td>
					  	</tr>
					  	<tr>
					  		<td>&nbsp;</td>
					  		<td><font color="white">点击图片重新获取验证码！</font></td>
					  	</tr>
					  	<tr>
					    	<td><div align="right"></div></td>
					    	<td height="50"><input name="loginBtn" id="loginBtn" type="button" class="btn_bg2" value="登录" onclick="login();" />
					      		<input type="button" name="forgetPwdBtn" id="forgetPwdBtn" value="忘记密码" class="btn_bg2" title="请拨打客服电话400******获取新密码" /></td>
					  	</tr>
					  	
					</table>
				 </div>
			</div>
		</div>
		<div id="logfoot">北京宽连十方数字技术有限公司 平台技术支持</div>
	</div>
</form>
</body>
</html>