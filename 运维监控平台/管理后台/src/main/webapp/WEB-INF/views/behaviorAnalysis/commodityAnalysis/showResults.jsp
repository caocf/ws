<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
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
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/artDialog.js?skin=blue"></script>
		<script type="text/javascript">
		function operator(id,type){
		  	if(type==1){
				art.dialog({
				    content: '确定要执行取消操作？',
				    ok: function () {
						$.ajax({
						    url : '${ctx}/analysis/commodity/doOperator',
						    dataType : "json",
						    data:{
						    	id:id,
						    	type:1
							},
						    success:function(msg) {
						        if (msg=='1') {
						        	 $("#status_"+id).text("");
								     $("#status_"+id).text("用户已取消");
								     $("#show_"+id).hide();
								     $("#hidden_"+id).show();
								     $("#curr_"+id).hide();
						        	var dialog = art.dialog({
						        	    content: '操作成功',
						        	    icon: 'succeed',
						        	    ok: function(){
						        	        return true;
						        	    }
						        	});
						        }else {
						        	var dialog = art.dialog({
						        	    content: '操作失败',
						        	    icon: 'error',
						        	    ok: function(){
						        	        return true;
						        	    }
						        	});
						        }
						    }
						});
				    	return true;
				    },
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
			}else if(type==2){
				art.dialog({
				    content: '确定要删除该条记录？',
				    ok: function () {
						$.ajax({
							 url : '${ctx}/analysis/commodity/doOperator',
							 dataType : "json",
							 data:{
							    id:id,
							    type:2
							 },
						    success:function(msg) {
						        if (msg=='1') {
						        	$("tr[id="+id+"]").remove();
						        	var dialog = art.dialog({
						        	    content: '删除成功',
						        	    icon: 'succeed',
						        	    ok: function(){
						        	        return true;
						        	    }
						        	});
						        }else {
						        	var dialog = art.dialog({
						        	    content: '删除失败',
						        	    icon: 'error',
						        	    ok: function(){
						        	        return true;
						        	    }
						        	});
						        }
						    }
						});
				    	return true;
				    },
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
			}else if(type==3){
				var myDate = new Date();
				var year = myDate.getFullYear();    
				var mon = myDate.getMonth();   
				if(mon+1<10){
					mon='0'+(mon+1);
				}else{
					mon=mon+1;
				}
				day = myDate.getDate();
				var mytime=myDate.toLocaleTimeString(); 
				var modTime = year+"-"+mon+"-"+day+" "+mytime;
				$.ajax({
				    url : '${ctx}/analysis/commodity/doOperator',
				    dataType : "json",
				    data:{
				    	id:id,
				    	type:3,
				    	modTime:modTime
					},
				    success:function(msg) {
				        if (msg=='1') {
				        	$("#status_"+id).text("");
						     $("#status_"+id).text("提取中");
						     $("#show_"+id).hide();
						     $("#hidden_"+id).hide();
						     $("#curr_"+id).show();
						     $("#time_"+id).text("");
						     $("#time_"+id).text(modTime);
				        	var dialog = art.dialog({
				        	    content: '操作成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        }else {
				        	var dialog = art.dialog({
				        	    content: '操作失败',
				        	    icon: 'error',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        }
				    }
				});
			}else if(type==4){
				$("#td_"+id).show();
				$("#t_"+id).hide();
			}
<%--			else if(type==0){--%>
<%--				var good_title = $(".class_"+id).val();--%>
<%--				window.location.href = "${ctx}/analysis/commodity/getItemList?id="+id+"&type="+type+"&good_title="+good_title+"&page=${page}";--%>
<%--			}--%>
		}

		function doOperator(id,name){
			var type = 0;
			window.parent.document.getElementById("good_title").value = name;
			window.parent.document.getElementById('busiFrame').src = "${ctx}/analysis/commodity/getItemList?id="+id+"&type="+type+"&good_title="+name+"&page=${page}";
		}
		
		function doBlur(obj,id){
			var itemName = obj.value;
			$("#t_"+id).text("");
			$("#t_"+id).text(obj.value);
			$("#td_"+id).hide();
			$("#t_"+id).show();
			$.ajax({
			    url : '${ctx}/analysis/commodity/doOperator',
			    dataType : "json",
			    data:{
			    	id:id,
			    	type:4,
			    	itemName:itemName
				},
			    success:function(msg) {
			        if (msg=='1') {
				        
			        	var dialog = art.dialog({
			        	    content: '操作成功',
			        	    icon: 'succeed',
			        	    ok: function(){
			        	        return true;
			        	    }
			        	});
			        }else {
			        	var dialog = art.dialog({
			        	    content: '操作失败',
			        	    icon: 'error',
			        	    ok: function(){
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
		

		
		<c:if test="${empty itemList.data}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>
		<c:if test="${not empty itemList.data}">
				<div class="tab-pane fade in active" id="home">
			<form action="" name="itemForm" id="itemForm"  method="get">
				<input type="hidden" value="${page}" name="page">
			 <table id="tableId" class="table table-bordered table-hover table-striped">
			 	
				<thead>
				<tr>
					<th>
						ID
					</th>
					<th>
						名称
					</th>
					<th>
						更新日期
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
			 </thead>
			<tbody>
				<c:forEach items="${itemList.data}" var="item">
					<tr  id="${item[0] }">
						<td class="center vcenter" >
							${item[0]}
						</td>
						<td class="center vcenter" id="td_${item[0] }" style="display: none;">
							<input type="text" class="class_${item[0] }" id="itemName" value="${item[1]}"  name= "itemName" onblur="doBlur(this,'${item[0]}');" style="color: red"/>
						</td>
						<td class="center vcenter" id="t_${item[0] }" >
							${item[1]}
						</td>
						<td class="center vcenter">
							${item[2]}
						</td>
						<td class="center vcenter" id="status_${item[0] }">
							<c:if test="${item[3]=='0'||item[3]=='1'}">提取中</c:if>
							<c:if test="${item[3]=='4'}">用户已取消</c:if>
							<c:if test="${item[3]=='2'}">完成</c:if>
						</td>
						<td class="center vcenter" id="show_${item[0] }">
							<c:if test="${item[3]=='0'||item[3]=='1'}">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',1)">取消</a>
								|
								<a href="javascript:operator('${item[0]}',2)">删除</a>
							</c:if>
							<c:if test="${item[3]=='4'}">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',3)">再次查询</a>
								|
								<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
								|
								<a href="javascript:operator('${item[0]}',2)">删除</a>
							</c:if>
							<c:if test="${item[3]=='2'}">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',3)">再次查询</a>
								|
								<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
								|
								<a target="_blank" href="<%=request.getContextPath()%>/downhdfs.zip?filePath=/user/hive/ExportUserTask/${item[0]}"+"&symbol=2">导出</a>
								|
								<a href="javascript:operator('${item[0]}',2)">删除</a>
							</c:if>
						</td>
						<td class="center vcenter" id="hidden_${item[0] }" style="display: none;">
							<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
							|
							<a href="javascript:operator('${item[0]}',3)">再次查询</a>
							|
							<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
							|
							<a href="javascript:operator('${item[0]}',2)">删除</a>
						</td>
						<td class="center vcenter" id="curr_${item[0] }" style="display: none;">
							<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
							|
							<a href="javascript:operator('${item[0]}',1)">取消</a>
							|
							<a href="javascript:operator('${item[0]}',2)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
		</div>
			<ht:page pageData="${itemList}" />
	</c:if>
	 

	</body>
</html>
