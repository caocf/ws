<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="用户筛选" />
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
		<base href="<%=basePath%>">

		<title>My JSP 'list.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


		<script type="text/javascript">
	$(function() {
		$("select[name='task_select']").change(
				function() {
					var task_id = $(this).val();
					if (task_id == 'NO') {
						jQuery("#busiFrame").attr("src",
								"${ctx}/analysis/screening/showResults");
					} else {
						jQuery("#busiFrame").attr(
								"src",
								"${ctx}/analysis/screening/getTaskList?task_id="
										+ task_id);
					}

				});
	});

	function getFilter(){
<%--		jQuery("#busiFrame").attr(--%>
<%--				"src",--%>
<%--				"${ctx}/analysis/screening/getNonTaskList");--%>
		window.location.href = "${ctx}/analysis/screening/nonList";
	}

	function delTask(id){
		 jQuery("#task_select option[value='"+id+"']").remove();
	}

	function modifyTask(id,name){
		  var objSelect = document.getElementById("task_select");
		  for(var i=0;i<objSelect.options.length;i++){
	             if(objSelect.options[i].value == id){
	                 objSelect.options[i].text = name;
	                 break;
	             }
	         }  
	}

	function loadArea(){
		window.location.href = "${ctx}/analysis/screening/loadArea";
	}
</script>

	</head>

	<body>
		<div class="daily" id="daily_dxqf">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="tab-pane fade in active" id="home">
					<div class="tab-pane fade in active" id="home">
						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									请选择方案：
								</th>
								<select id="task_select" name="task_select" >
									<option value="NO">
										&nbsp;&nbsp;&nbsp;&nbsp;请选择方案
									</option>
									<c:if test="${not empty taskList}">
										<c:forEach var="task" items="${taskList }">
											<option value="${task.Task_id}">
												${task.Task_name}
											</option>
										</c:forEach>
									</c:if>
								</select>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<th>
								</th>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<th>
									<button class="btn btn-primary" type="button"
										style="float: inherit;" onclick=getFilter();>
										<i class="icon16 i-search"></i>非电商用户筛选
									</button>
								</th>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div>
			<iframe
				src="<%=path%>/analysis/screening/getTaskList?task_id=${task.Task_id}"
				id="busiFrame" name="busiFrame" marginwidth="0" marginheight="0"
				frameborder="0" width="100%" height="760" scrolling="no">
			</iframe>
		</div>
	</body>
</html>
