<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head/> 
<script type="text/javascript">
 function browse (url,shopId){
       window.open(url+shopId);
 }
</script>
</head>
<body>
</br>
<div id="search-menu">
<ul>
  <ht:menu-btn type="search"/>
</ul>
  <br style="clear: left" />
</div>
<div class="queryContainer" >

    <form name="queryForm" id="queryForm" action="?" method="get">
        
        <table>
            <tr>
              <td >商户名称：</td>
              <td ><input type="text" name="name" value="${param.name}" class="txt" style="width:150px"/></td>	
              
              <td >商户编号：</td>
              <td ><input type="text" name="shopId" title="商户编号" value="${param.shopId}" class="txt validate-number" style="width:150px"/></td>	
              <td >发布状态：</td>
                	<td>
	                    <select name="pubTag">
	                        <option value="">--请选择--</option>
	                    	<c:forEach items="${pubTagMap}" var="item">
	                        	    <option value="${item.key }" <c:if test="${param.pubTag == item.key}">selected="selected"</c:if>>${item.value }</option>
	                        </c:forEach>
	                    </select>
             </td>
             <td><ct:btn type="search" /></td>
            </tr>
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>商户列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData.data}">
       

        <table class="datalist fixwidth">

            <tr>
                <th nowrap="nowrap">商户编号</th>
                  <th nowrap="nowrap">商户名称</th>
                 <th nowrap="nowrap">发布状态</th>
                 <th nowrap="nowrap">最后更新时间</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td  nowrap="nowrap">${item.shopId}</td>
            	<td nowrap="nowrap">${item.name}</td>
                <td nowrap="nowrap">${item.pubTagName}</td>
                <td nowrap="nowrap"><ct:time source="${item.operateEndTime}" /></td>
                <td >
	                  <ct:display model="andit_list" btn="view_btn">
	                        <a href="settingsView/${item.id}">查看</a>&nbsp;
	                </ct:display>       
	                    <c:if test="${item.pubTag eq 0 || item.pubTag eq 2}">
	                <ct:display model="andit_list" btn="audit_btn">&nbsp;
                		<a href="preAudit/${item.id}">审核</a>&nbsp;&nbsp;
	                </ct:display>
	                </c:if>
	           
	                  <ct:display model="andit_list" btn="preview_btn">
	                        <a  href="#this" onclick="browse('${viewUrl}','${item.shopId}')">预览</a>&nbsp;&nbsp;
	                </ct:display>      
                </td>
            </tr>
            </c:forEach>
        </table>
          <ht:page pageData="${pageData}" />
       </c:if>
        <c:if test="${empty pageData.data}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>