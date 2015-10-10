<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="res" value="${ctx}/static" />
<script>
function updateFrame() {
  //document.getElementById('result').contentWindow.document.innerHTML = "update...";
  var   d   =   window.frames[0]; 
  d.document.write('update...'); 
}

</script>

<a href="${ctx}/reset/cache/all" target="result" onclick="updateFrame();">更新缓存</a>
<br/>

<iframe id="result" name="result" style="width:100%;height:300px;"></iframe>