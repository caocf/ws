<%@ page language="java" pageEncoding="GBK"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户统计" />
<%@ include file="../includes/t.jsp"%>
<body>
<%
	List<String> types = (List<String>)request.getAttribute("interType");
	Map<String, List<String[]>> nameMap = (Map<String, List<String[]>>)request.getAttribute("interValues");
%>
<div>
<table border="1" width="970">
<tr><td><h2>接口分类</h2></td><td><h2>接口名</h2></td><td><h2>当前状态</h2></td><td><h2>历史状态</h2></td></tr>
<% 
	for(int i=0;i<types.size();i++){
		String type = types.get(i);
		List<String[]> values = nameMap.get(type);
		int length = values.size();
		if(length>0){
		%>
		<tr>
			<td rowspan="<%=length %>">
				<h3><%=type %></h3>
			</td>
		<%
			for(int j=0;j<length;j++){
				String[] value = values.get(j);
				%>
			<td>
				<%=value[0] %>
			</td>	
			<td>
				<%if(value[1].equals("0")){
					%>
					正常
					<%
				} else if(value[1].equals("1")){
					%>
					<span style="color:red;">不正常</span>
					<%} %>
			</td>
			<td><a href="${ctx}/inter/getDetail?name=<%=value[0] %>&type=<%=type %>">点击进入</a></td>
			</tr><tr>
				<%
			}
		%>
		
		</tr>
		<%
		}
	}
%>
</table>
</div>
</body>