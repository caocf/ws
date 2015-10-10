<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/if.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="${ctx }/static/ui/js/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/artDialog.js?skin=blue"></script>
    <base href="<%=basePath%>">
	<script type="text/javascript">
		var myDialog;
		var engine;
		function operate(engineId){
			engine = engineId;
			myDialog = art.dialog({title:'修改引擎',lock:true,width:'400px',height:300,background:'#CCFFFF'});
			jQuery.ajax({
			    url: '${ctx}/recommendMgmt/engineMgmt/updateEngine',
			    data:{
					engineId:engineId
				},
				cache:false,
			    success: function (data) {
			        myDialog.content(data);// 填充对话框内容
			    }
			});
		}

		function onCancel(){
			myDialog.close();
		}
		
		function doUpdate(){
			var moduleId="";
			var boxForName = document.getElementsByName("module");
			  for(var i = 0;i < boxForName.length;i++){
			   	if(boxForName[i].type="checkbox"){
			    	if(boxForName[i].checked&&i<boxForName.length-1){
			    		moduleId += (boxForName[i].value +',');
			    	}else if(boxForName[i].checked&&i==boxForName.length-1){
			    		moduleId += (boxForName[i].value);
				    }
			   	}
			  }

				$.ajax({
					url : '${ctx}/recommendMgmt/engineMgmt/saveEngine',
				    dataType : "json",
				    data:{
						 engineId:engine,
					     moduleId:moduleId
					},
					cache:false,
				    success : function(data){
				        if (data=='1') {
				        	var dialog = art.dialog({
				        	    content: '修改操作成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        			myDialog.close();
						        	location.reload();
				        	        return true;
				        	    }
				        	});
				        } else {
				        	var dialog = art.dialog({
				        	    content: '修改操作失败',
				        	    icon: 'error',
				        	    ok: function(){
				        			myDialog.close();
				        	        return true;
				        	    }
				        	});
				        }
				    }
				});
		}
		
	</script>

  </head>
  
  <body>
    <c:if test="${empty engineList}">
					<div class="alert alert-info">
						<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
					</div>
				</c:if>
				<c:if test="${not empty engineList}">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>
									引擎ID
								</th>
								<th>
									引擎分类
								</th>
								<th>
									拥有算法
								</th>
<%--								<th>--%>
<%--									状态功能--%>
<%--								</th>--%>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${engineList}" var="engine">
								<tr>
									<td class="center vcenter">
										${engine.engineId}
									</td>
									<td class="center vcenter">
										${engine.engineName}
									</td>
							
									<td class="center vcenter">
										<c:forEach var="module" items="${engine.moduleList}" varStatus="info">
								      		 <c:if var="flag" test="${info.last}">
								      		 	${module.moduleName }
								      		 </c:if>
								      		 <c:if test="${!flag}">
								      		 	${module.moduleName }、
								      		 </c:if>
							      	   </c:forEach>
									</td>
<%--									<td class="center vcenter">--%>
<%--										<c:if test="${engine.moduleList[0] != null}">--%>
<%--										<a href="javascript:operate('${engine.engineId }');">修改</a>--%>
<%--										</c:if>--%>
<%--									</td>--%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
  </body>
</html>
