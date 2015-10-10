<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="../../css/base.css" rel="stylesheet" type="text/css" />
   <link href="../../css/dealList.css" rel="stylesheet" type="text/css" />
   <link href="../../css/layout.css" rel="stylesheet" type="text/css" />
   <link href="../../css/usercenter.css" rel="stylesheet" type="text/css" />
   <link href="../../css/sidebar.css" rel="stylesheet" type="text/css" />
   <link href="../../css/pro.css" rel="stylesheet" type="text/css" />
   <link href="../../js/lab/default/dialog.css" rel="stylesheet" type="text/css"/>
   <link rel="stylesheet" type="text/css" href="../../css/login.css" />
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
   <script type="text/javascript" src="../../js/editPwd.js"></script>

<title>mo生活，精彩无限！</title>
<script>
  var  web_url='账户管理';
</script>
</head>

<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我购买的商品</span></div>
    <div id="main" class="fr  w750">
    <div class="body clearfix">

				<h1 class="pagelocal">我的账户</h1>
				<div class="block withoutborder" style="margin-top: 10px;">
					<div class="maintab">
						<ul class="tabs clearfix">
							<li attr="tab">
								<a href="info.chtml" name="tabSkip">基本资料</a>
							</li>
							<li attr="tab"  class="current">
								<a href="editPwd.chtml" name="tabSkip">密码修改</a>
							</li>			
						</ul>
        <div class="detail">
<div class="mo-page">
    <div class="mo-content-box">
		<div class="mo-short-page">
			
			<div class="mo-count-bind">
				<div class="mo-col-desc"><a href="#"></a>请重新设置您的密码。</div>
				<div class="mo-bind-form">
					<form name="resetPwdForm" action="editPwd.chtml" id="resetPwd" method="post" class="center_applyRefund">
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title mo-form-long-title">输入原密码</div>
							<div class="mo-left mo-form-input mo-form-input-txt"><input type="password" name="odlPwd" id="odlPwd" />
							</div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title mo-form-long-title">新密码设置</div>
							<div class="mo-left mo-form-input mo-form-input-txt"><input type="password" name="userPass" id="userPass" placeholder="输入6位以上含有特殊字符的密码" /></div>
							<div class="mo-clear"></div>
						</div>
                        <div class="mo-form-widght">
							<div class="mo-left mo-form-title mo-form-long-title">密码安全等级</div>
							<div class="mo-left mo-form-input" id="passstrength"></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-title mo-form-long-title">新密码确认</div>
							<div class="mo-left mo-form-input mo-form-input-txt"><input type="password" name="userPassAg" id="userPassAg" /></div>
							<div class="mo-clear"></div>
						</div>
						<div class="mo-form-widght">
							<div class="mo-left mo-form-notitle mo-form-long-title"></div>
							<div class="mo-left mo-form-input mo-form-login-btn"><input type="button" value="确认修改" id="submitBtn"/></div>
							<div class="mo-clear"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
    </div>
</div>
</div>
</div>
</div>

    <span class="clr"></span></div></div><%@include file="../left_menu.jsp"%>
</div>
<script type="text/javascript" src="../js/foot.js"></script>
<script type="text/javascript" src="../../js/cplatform.core.js"></script>
<script type="text/javascript" src="../../js/cplatform.validator.js"></script>
<script type="text/javascript">
    var valid = new Validator({
        id: 'resetPwd',
        infoElement: 'span',
        errorClass: 'error',
        validClass: 'valid',
        rules: {
        	odlPwd: {required: true},
        	userPass: {required: true, minLength: 6, hasLetter: true},
        	userPassAg: {required: true, minLength: 6, equalTo: 'userPass'}
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
	$(function(){
		$('#userPass').keyup(function(e) {
			var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
			var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
			var enoughRegex = new RegExp("(?=.{6,}).*", "g");
			if (false == enoughRegex.test($(this).val())) {
				$('#passstrength').removeClass("medium").removeClass("strong").removeClass("weak");
			} else if (strongRegex.test($(this).val())) {
				$('#passstrength').addClass("strong").removeClass("medium").removeClass("weak");
			} else if (mediumRegex.test($(this).val())) {
				$('#passstrength').addClass("medium").removeClass("strong").removeClass("weak");
			} else {
				$('#passstrength').addClass("weak").removeClass("medium").removeClass("strong");
			}
			return true;
		});
		
		$('#submitBtn').click(function(){
			if(valid.checkForm()){
				$('#resetPwd').submit();
			}
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
