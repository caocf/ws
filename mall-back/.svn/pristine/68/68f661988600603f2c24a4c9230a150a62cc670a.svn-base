<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
 <ht:head/>
<body>
<div class="container">
    <br/>
    <h3>索引全量更新列表</h3>
    <div class="mainbox">
    
    
    <form:form id="fm" method="post" acceptCharset="utf-8" action="updateIdx" htmlEscape="true" cssClass="required-validate">
        <ct:display model="idx-list" btn="update_btn">
    		<ct:btn type="submit" value="更新" name="btn<%=new java.util.Date().getTime() %>"/>
    	</ct:display>
    </form:form>
        <c:if test="${not empty searchIdxList.data}">
        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">类型</th>
                <th nowrap="nowrap">更新时间</th>
                <th nowrap="nowrap">操作人</th>
            </tr>
            <c:forEach items="${searchIdxList.data}" var="item">
            <tr>
                <td>${item.typename}</td>
                <td>
         			<ct:time source="${item.updatetime}" />
                </td>
                <td>${item.username}</td>
            </tr>
            </c:forEach>
        </table>
          <ht:page pageData="${searchIdxList}" />
       </c:if>
        <c:if test="${empty searchIdxList.data}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>
</div>

</body>
</html>