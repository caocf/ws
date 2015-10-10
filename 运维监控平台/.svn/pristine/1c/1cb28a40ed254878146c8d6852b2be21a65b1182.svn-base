<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="非电商用户筛选" />
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
		<meta http-equiv="cache-control" content="no-store, must-revalidate"/>
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
			$(function() {
				$("select[name='task_select']").change(
						function() {
							var id = $(this).val();
								jQuery("#busiFrame").attr(
										"src",
										"${ctx}/analysis/screening/getNonTaskList?taskId="
												+ id);
						});
			});
		
			
		
			function getFilter(){
				window.location.href = "${ctx}/analysis/screening/nonList";
			}
		
			function loadPage(){
				window.parent.location.reload();
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
			
		</script>

	</head>

	<body>
		<div id="bg" style="display: none;"></div>
		<div class="daily" id="daily_dxqf">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="tab-pane fade in active" id="home">
					<div class="tab-pane fade in active" id="home">
						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									请选择自定义方案：
								</th>
								<select id="task_select" name="task_select">
									<option value="NO">
										请选择自定义方案
									</option>
									<c:if test="${not empty noList}">
										<c:forEach var="task" items="${noList }">
											<option value="${task.id}">
												${task.name}
											</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		<div>
			<iframe
				src="<%=path%>/analysis/screening/getNonTaskList?task_id=${task.Task_id}"
				id="busiFrame" name="busiFrame" marginwidth="0" marginheight="0"
				frameborder="0" width="100%" height="1200" scrolling="no">
			</iframe>
		</div>
	</body>
</html>
