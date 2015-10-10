<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="用户分析" />
<%@ include file="../../includes/t.jsp"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	</head>
	<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/plugins/iframeTools.js"></script>
	<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/artDialog.js?skin=blue"></script>
	<script type="text/javascript">

		function checkNum(uid){
			if(isNaN(uid)||(uid.length!=11)){
				art.dialog({
					time:2,
					content:'手机号码为11位数字！请正确填写！'
				});
	            return false;
	        }
	        var reg =/^1(3[4-9]|4[7]|5[012789]|8[23478])\d{8}$/;
	        if(!reg.test(uid))
	        {
	            art.dialog({
					time:2,
					content:'您的手机号码不是移动号码，请重新输入！'
				});
	            return false;
	        }else{
				return true;
		    }
		}
		var uid ;
		//筛选操作
		function getFilter(){
			uid = jQuery("#uid").val();
			if(checkNum(uid)){
				var frm = document.getElementById("queryForm");
				frm.action = "${ctx}/analysis/user/getFilter";
				frm.submit();
			}else{
				return false;
			}
		
		}

		//点击基本信息，画像信息，推荐建议触发的事件
		function changePage(moduel) {
			var id = $("#uid").val();
			if(checkNum(id)){
				var divs = [ "BTN_INFO", "BTN_IMAGE", "BTN_RECOMMEND"];
				for (var i = 0; i < divs.length; i++) {
					if (divs[i] == moduel) {
						document.getElementById(divs[i]).className = "btn btn-primary";
						jQuery("#busiFrame").attr("src","${ctx}/analysis/user/userInfo?uid="+id +"&moduel="+moduel);
					}else{
						document.getElementById(divs[i]).className = "btn";
					}
				}
			}
		}

	
		
	</script>
	<body>
		<div class="daily" id="daily_dxqf">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="tab-pane fade in active" id="home">
					<form class="form-horizontal" action="" name="queryForm"
						id="queryForm" method="post">
						
            			<div class="tab-pane fade in active" id="home">
              			 <div class="control-group group-search" style="margin-left: 0px;">
                 		 <label class="control-label">用户号码：</label>
                  
                  		<div class="controls controls-row">
                    
                  			<div>
								<input type="text" id="uid" name="uid" value="${uid}"/>
								<button class="btn btn-primary" onclick="getFilter();" type="button">
                                    <i class="icon16 i-search"></i> 筛选
                                </button>
							</div>
						</div>
					</div>
				</div>
					</form>
				</div>
			</div>
		</div>
		<div style="margin: 5px 0px">
			<a href="#" style="padding-right: 10px"
				onclick="changePage('BTN_INFO');"> <font id="BTN_INFO"
				style="font-weight: bold;" class="btn btn-primary">基本信息</font> </a>
			<a href="#" style="padding-right: 10px"
				onclick="changePage('BTN_IMAGE');"> <font id="BTN_IMAGE" 
				style="font-weight: bold;" class="btn">用户画像</font> </a>
			<a href="#" style="padding-right: 10px"
				onclick="changePage('BTN_RECOMMEND');"> <font id="BTN_RECOMMEND"
				style="font-weight: bold;" class="btn">推荐建议</font> </a>

		</div>
		<div>
			<iframe src="<%=path %>/analysis/user/userInfo?uid=${uid}" id="busiFrame" name="busiFrame" marginwidth="0"
				marginheight="0" frameborder="0" width="100%" height="700"
				scrolling="no"></iframe>
		</div>
	</body>
</html>
