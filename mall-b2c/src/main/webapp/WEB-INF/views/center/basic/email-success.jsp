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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.form.js"></script>
<script type="text/javascript" src="../../js/static.chtml"></script>
<script type="text/javascript" src="../../js/cart-common.js"></script>
<script type="text/javascript" src="../../js/sendMessage.js"></script>

<title>mo生活，精彩无限！</title>
</head>

<body>
	<script type="text/javascript" src="../js/head.js"></script>
	<div class="wrapper mauto">
		<div class="breadcrumb">
			<strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a
				href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我购买的商品</span>
		</div>
		<div id="main" class="fr  w750">
			<div class="body clearfix">

				<h1 class="pagelocal">我的账户</h1>
				<div class="block withoutborder" style="margin-top: 10px;">
					<div class="detail">
						<div class="mo-page">
							<div class="mo-header">
								<a href="#"><img src="../../img/logo-center.jpg" /></a>
							</div>
							<div class="mo-content-box">
								<div class="mo-short-page">
									<div class="mo-col-desc mo-word-center"
										style="margin-bottom: 100px;">验证邮件已经发出，请在30分钟内登录邮箱按照邮件提示完成重置密码。</div>
									<div class="mo-bind-form">
										<div class="mo-form-widght">
											<div class="mo-left mo-form-notitle"></div>
											<div class="mo-left mo-form-input mo-form-input-btn">
												<c:if test="${not empty mailWeb}">
													<input type="button" onclick="javascript:window.location = '${mailWeb}';" value="前往邮箱" />
												</c:if>
												<c:if test="${empty mailWeb}">
													<input type="button" onclick="javascript:window.location = 'mailto:';" value="前往邮箱" />
												</c:if>
											</div>
											<div
												class="mo-right mo-right mo-form-input mo-form-valid-btn"
												style="margin-right: 70px;">
												<input class="mo-font-bold" onclick="javascript:window.location = 'info.chtml';" type="button" value="返回上一步" />
											</div>
											<div class="mo-clear"></div>
										</div>
									</div>
									<div class="mo-col-desc" style="padding-left: 70px;">
										<p>1、若您长时间未收到邮件，建议您检查垃圾箱或广告邮件。</p>
										<p>
											2、<span id="rest-send-emial-p">您还可以<c:if test="${type == 'BIND_EMAIL' }"><a id="rest-send" href="javascript:;" ></c:if><c:if test="${type == 'EDIT_EMAIL' }"><a id="rest-send" href="javascript:;"></c:if>重发验证邮件。</a></span>
											<span class="rest-send-message" style="font-size:12px;color:#666666;"></span>
										</p>
										<p>
											3、若您有mo通行证账号和密码，可直接<a href="http://catest.12580life.com/login?service=http://mall.12580life.com">登录</a>。
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<span class="clr"></span>
			</div>
		</div><%@include file="../left_menu.jsp"%>
	</div>
	<script type="text/javascript" src="../js/foot.js"></script>
	<script type="text/javascript">
		$('a[id=rest-send]').click(function(){
			
			//重新发送邮件
			if('${type}' == 'BIND_EMAIL'){
				Message._send_rest_bind_email();
			}else if('${type}' == 'EDIT_EMAIL'){
				Message._send_rest_edit_email();
			}
			
			var secs = 30;
			var timeout;
			var btnCount = function() {
				if (secs == 0) {
					$('a[id=rest-send]').removeAttr("disabled").removeClass("disable");
					$('span[id=rest-send-emial-p]').show();
					$(".rest-send-message").addClass("mo-hide-panel");
					clearTimeout(timeout);
				} else {
					$(".rest-send-message").removeClass("mo-hide-panel");
					$('span[id=rest-send-emial-p]').hide();
					$('a[id=rest-send]').addClass("disable");
					$('a[id=rest-send]').attr("disabled", "disabled");
					secs--;
					$(".rest-send-message").text("邮件 已发送，" + secs + "秒后可重新发送");
					setTimeout(btnCount, 1000);
				}
			};
			timeout = setTimeout(btnCount, 1000);
		});
	</script>

</body>
</html>
