<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
    </head>
<body>
<br/>
<div class="container">
    <br/>
    <h3>门店列表</h3>

    <div class="mainbox">
        <c:if test="${not empty shopList}">

        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap" width="70"><input type="checkbox" class="checkall"/>门店ID</th>
            	<th nowrap="nowrap" width="150">所属商户</th>
                <th nowrap="nowrap" width="150">名称</th>
                <th nowrap="nowrap" width="60">区域</th>
               	<th nowrap="nowrap" width="240">地址</th>
            </tr>

            <c:forEach items="${shopList}" var="item">
            	<c:set var="isselect" value="false" /> 
				<c:forEach var="sid"  items="${selectIds}">   
					<c:if test="${item.id eq sid}">     
						<c:set var="isselect" value="true" />  
					</c:if> 
				</c:forEach>
            <tr>
            	<td nowrap="nowrap"><input type="checkbox" name="id"  shopName="${item.name}"  value="${item.id}" <c:if test="${isselect }">checked="true"</c:if>  />${item.id}</td>
                 <td nowrap="nowrap">${item.storeName}</td>
                <td nowrap="nowrap">${item.name}</td>
                <td nowrap="nowrap">${item.regionName}</td>
                <td nowrap="nowrap">${item.address}</td>
            </tr>
            </c:forEach>
        </table>

        </c:if>
        <c:if test="${empty shopList}">
        <div class="note">
            <p class="i">该商户目前没有可用门店记录!</p>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>