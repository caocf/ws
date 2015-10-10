<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="群体用户分析" />
<%@ include file="../../includes/t.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
	<script type="text/javascript"
			src="/webapp/static/js/jquery-1.8.3.js"></script>
	
<script type="text/javascript" >
	
	//点击群体用户画像信息，推荐建议触发的事件
		function changePage(moduel) {
			var Task_id = $('#task_select').find('option:selected').val();
			var divs = ["BTN_IMAGE", "BTN_RECOMMEND"];
			for (var i = 0; i < divs.length; i++) {
				if (divs[i] == moduel) {
					document.getElementById(divs[i]).className = "btn btn-primary";
					jQuery("#busiFrame").attr("src","${ctx}/analysis/group/groupInfo?Task_id="+ Task_id + "&moduel="+moduel);
				}else{
					document.getElementById(divs[i]).className = "btn";
				}
				
			}
		}

		$(function() {
			$("select[name='task_select']").change(
					function() {
						var Task_id = $(this).val();
						jQuery("#busiFrame").attr("src","${ctx}/analysis/group/groupInfo?Task_id="+ Task_id + "&moduel=BTN_IMAGE");
					});
		});
		
</script>
	</head>

	<body>
	 <div class="daily" id="daily_dxqf">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="tab-pane fade in active" id="home">
					<form class="form-horizontal" action="" name="queryForm"
						id="queryForm" method="post">
						
            			<div class="tab-pane fade in active" id="home">
              			 <div class="control-group group-search" style="margin-left: 0px;">
                 		 <label class="control-label">请选择筛选方案：</label>
                  		 <div class="controls controls-row">
                  			<div>
									<select id="task_select" name="task_select">
							<option value="">请选择筛选方案</option>
							<c:if test="${not empty taskList}">
								<c:forEach var="task" items="${taskList }">
									<option value="${task.Task_id}">${task.Task_name}</option>
								</c:forEach>
							</c:if>
						</select>
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
				onclick="changePage('BTN_IMAGE');"> <font id="BTN_IMAGE" 
				style="font-weight: bold;" class="btn btn-primary">用户画像</font> </a>
			<a href="#" style="padding-right: 10px"
				onclick="changePage('BTN_RECOMMEND');"> <font id="BTN_RECOMMEND"
				style="font-weight: bold;" class="btn">推荐建议</font> </a>

		</div>
		<div>
			<iframe  src="<%=path %>/analysis/group/groupInfo?Task_id=${task.Task_id}" id="busiFrame" name="busiFrame" marginwidth="0"
				marginheight="0" frameborder="0" width="100%" height="1500"
				scrolling="no"></iframe>
		</div>
		
	</body>
</html>
