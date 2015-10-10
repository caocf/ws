<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户统计" />
<%@ include file="../includes/t.jsp"%>
 
<body>
<table border="1" width="980">
	<tr>
		<th>平台名称</th><th>备份类型</th><th>备份路径</th><th>备份大小</th>
	</tr>
	<c:if test="${!empty backupList}"> 
	<c:forEach var="backUpInfo" items="${backupList }" varStatus="status">
		<tr <c:if test="${backUpInfo.fileSize=='0' }" >style="background-color:red;"</c:if>
		<c:if test="${backUpInfo.fileSize!='0'&&status.index%2==0 }" >style="background-color:#DDDDDD;"</c:if>>
			<td>${backUpInfo.platName }</td>
			<td>${backUpInfo.type }</td>
			<td>${backUpInfo.backFile }</td>
			<td>${backUpInfo.fileSize }</td>
		</tr>
	</c:forEach>
	</c:if>
</table>

<div id="container"></div>
</body>