<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/if.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="${ctx }/static/ui/js/artDialog/plugins/iframeTools.js"></script>
	<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/artDialog.js?skin=blue"></script>
    <title>My JSP 'algorithmList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" >
		var setDialog;
		var updateDialog;
		var module;
		function operate(moduleId,type){
			module = moduleId;
			if(type==0){
				setDialog = art.dialog({title:'设置引擎',lock:true,width:'400px',height:300,background:'#CCFFFF'});
				jQuery.ajax({
				    url: '${ctx}/recommendMgmt/engineMgmt/addEngine',
				    cache:false,
				    success: function (data) {
						setDialog.content(data);// 填充对话框内容
				    }
				});
			}else if(type==1){
				updateDialog = art.dialog({title:'修改引擎',lock:true,width:'400px',height:300,background:'#CCFFFF'});
				jQuery.ajax({
				    url: '${ctx}/recommendMgmt/engineMgmt/modifyEngine',
				    data:{moduleId:moduleId},
				    cache:false,
				    success: function (data) {
				    	updateDialog.content(data);// 填充对话框内容
				    }
				});
			}
		}


		function doUpdate(){
			var j=0;
			var engineId="";
			var boxForName = document.getElementsByName("engine");
			var length = boxForName.length;
			  for(var i = 0;i < boxForName.length;i++){
			   	if(boxForName[i].type="checkbox"){
			    	if(boxForName[i].checked&&i<boxForName.length-1){
			    		engineId += (boxForName[i].value +',');
			    		j=j+1;
			    	}else if(boxForName[i].checked&&i==boxForName.length-1){
			    		engineId += (boxForName[i].value);
			    		j=j+1;
				    }
			   	}
			  }

			  $.ajax({
					url : '${ctx}/recommendMgmt/engineMgmt/setEngine',
				    dataType : "json",
				    data:{
						 engineId:engineId,
					     moduleId:module
					},
				    success : function(data){
				        if (data==j) {
				        	var dialog = art.dialog({
				        	    content: '设置成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        			setDialog.close();
						        	location.reload();
				        	        return true;
				        	    }
				        	});
				        } else {
				        	var dialog = art.dialog({
				        	    content: '设置失败',
				        	    icon: 'error',
				        	    ok: function(){
				        			setDialog.close();
				        	        return true;
				        	    }
				        	});
				        }
				    }
				});
		}

		function onCancel(){
			updateDialog.close();
		}

		function doModify(){
			var engineId="";
			var t=0;
			var boxForName = document.getElementsByName("engine");
			  for(var i = 0;i < boxForName.length;i++){
			   	if(boxForName[i].type="checkbox"){
			    	if(boxForName[i].checked&&i<boxForName.length-1){
			    		engineId += (boxForName[i].value +',');
			    		t=t+1;
			    	}else if(boxForName[i].checked&&i==boxForName.length-1){
			    		engineId += (boxForName[i].value);
			    		t=t+1;
				    }
			   	}
			  }

				$.ajax({
					url : '${ctx}/recommendMgmt/engineMgmt/saveModifyEngine',
				    dataType : "json",
				    data:{
						 engineId:engineId,
					     moduleId:module
					},
					cache:false,
				    success : function(data){
				        if (data==t) {
				        	var dialog = art.dialog({
				        	    content: '修改操作成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        			updateDialog.close();
						        	location.reload();
				        	        return true;
				        	    }
				        	});
				        } else {
				        	var dialog = art.dialog({
				        	    content: '修改操作失败',
				        	    icon: 'error',
				        	    ok: function(){
				        			updateDialog.close();
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
    <c:if test="${empty AlgorithmList}">
					<div class="alert alert-info">
						<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
					</div>
				</c:if>
				<c:if test="${not empty AlgorithmList}">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>
									模块类名
								</th>
								<th>
									模块算法名称
								</th>
								<th>
									描述
								</th>
<%--								<th>--%>
<%--									状态功能--%>
<%--								</th>--%>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${AlgorithmList}" var="ModuleInfo">
								<tr>
									<td class="center vcenter">
										${ModuleInfo.moduleId}
									</td>
									<td class="center vcenter">
										${ModuleInfo.moduleName}
									</td>
							
									<td class="center vcenter">
										${ModuleInfo.moduleDesc}
									</td>
<%--									<td class="center vcenter">--%>
<%--										<c:if var="result" test="${ModuleInfo.engineList[0]==null}">--%>
<%--								      		<a href="javascript:operate('${ModuleInfo.moduleId }','0');">未设置</a>--%>
<%--								      	</c:if>--%>
<%--								      	<c:if test="${ModuleInfo.engineList[0]!=null}">--%>
<%--								      		<a href="javascript:operate('${ModuleInfo.moduleId }','1');">修改</a>--%>
<%--								      	</c:if>--%>
<%--									</td>--%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
  </body>
</html>
