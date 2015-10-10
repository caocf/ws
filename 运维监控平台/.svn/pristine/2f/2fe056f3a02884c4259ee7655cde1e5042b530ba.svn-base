<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/if.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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

		<title>My JSP 'userInfo.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path %>/static/ui/Charts/FusionCharts.js" ></script>
	
	<script type="text/javascript">
		function createChart(xml,swf,id,width,height) {
		    var chart = new FusionCharts('<%=path %>/static/ui/Charts/'+swf, 'ChartId_'+id, width, height, '0', '0');
		  	chart.setDataXML(xml);
		  	chart.render(id);
	    }
	    
		$(document).ready(function(){
			$("#back-to-top").hide();
		})
		
	    
	</script>
	
	</head>

	<body>
		<%
			if (request.getAttribute("groupImage") == null) {
		%>
		<div class="alert alert-info">
			<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
		</div>
		<%
			} else {
		%>

			<table>
	  	<tr>
	  		<td>
		  		<div id="groupImage" style="float: left;">
			  		<script type="text/javascript">
						createChart("${xmlStr}",'Pie3D.swf',"groupImage",'1050','300');  
					</script>
				</div>
	  		</td>
				
		</tr>
		</table>	
		<table border="0px"
			class="table table-bordered table-hover table-striped">
			<thread>
			<tr>
				<th rowspan="3">
					一级标签
				</th>
				<th>
					二级标签
				</th>
				<th>
					浏览商品数
				</th>
				<th>
					浏览商户数
				</th>
				<th>
					兴趣占比
				</th>
			</tr>
			</thread>
			<tbody>
			<%
			float sum = Float.parseFloat(request.getAttribute("sum").toString());
				List<String[]> labelList = (List<String[]>) request
							.getAttribute("labelList");
					for (int i = 0; i < labelList.size(); i++) {
						String str[] = labelList.get(i);
						int length = str[0].split(",").length;
						for (int j = 0; j < length; j++) {
			%>


				<tr>
					<%
						if(j==0){
					%>	
					<td rowspan="<%=length%>" align="center" style="text-align: center; padding-bottom: 20"><%=str[3]%></td>
					<%
						}
					%>
					<td><%=str[2].split(",")[j].toString()%></td>
					<td><%=str[0].split(",")[j].toString()%></td>
					<%
						int goods = Integer.parseInt(str[0].split(",")[j]);
					%>
					<td><%=str[1].split(",")[j]%></td>
					<td>
						<fmt:formatNumber pattern="#.##%" value="<%=goods/sum%>"></fmt:formatNumber>

					</td>
				</tr>
			<%
				}
			%>
			<%
				}
			%>
			</tbody>
		</table>
		<%
			}
		%>
	</body>
</html>
