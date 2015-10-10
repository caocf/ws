<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
    </head>
<body>
<br/>
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
                <td width="100">商品名称</td>
                <td width="300"><input type="text" name="productionname" value="${param.productionname}" class="txt" style="width:206px"/></td>
                <td>协议类型：</td>
                <td>
                    <select name="type">
                        <option value="">--请选择--</option>
                        <c:forEach items="${typeMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${param.type == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>产品有效期：</td> 	
				<td>
                    <input type="text" id="inputStartTimeS" name="inputStartTimeS" value="${param.inputStartTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'productionefftime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'productionexptime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="productionefftime" id="productionefftime" value="${param.productionefftime}"/>
                   	 至
                    <input type="text" id="inputEndTimeE" name="inputEndTimeE" value="${param.inputEndTimeE}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'productionexptime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'productionefftime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="productionexptime" id="productionexptime" value="${param.productionexptime}" />
                </td>
                <td colspan="4">
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>商品协议资料列表</h3>

    <div class="mainbox">
        <c:if test="${not empty settlePage.data}">
        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap" width="80">商品协议编号</th>
            	<th nowrap="nowrap" >商品名称</th>
            	<th nowrap="nowrap" width="145">产品有效期</th>
            	<th nowrap="nowrap" width="70">计费分类</th>
            	<th nowrap="nowrap" width="80">协议类型</th>
            	<th nowrap="nowrap" width="70">状态</th>
                <th nowrap="nowrap" width="150">操作</th>
            </tr>

            <c:forEach items="${settlePage.data}" var="item">
            <tr>
            	<td >${item.id}</td>
            	<td class="ellipsis">${item.productionname}</td>
                <td ><c:if test="${not empty item.productionefftime}"><ct:time source="${item.productionefftime}" tfmt="yyyy-MM-dd"/>~<ct:time source="${item.productionexptime}" tfmt="yyyy-MM-dd"/></c:if></td>
                <td class="ellipsis">${item.productiontypeName}</td>
                <td>${item.typeName}</td>
                <td>${item.statusName}</td>
                <td>
                	<ct:display model="production_settle_audit" btn="view_btn">
                		<a href="settleView?id=${item.id}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                	</ct:display>
                	<c:if test="${item.status == 0}">
                	<ct:display model="production_settle_audit" btn="audit_btn">
                		<a href="#this" onclick="dealInfo('确定要审核通过吗？','process/${item.status}/${item.id}/list');">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;
                	</ct:display>
                	</c:if>
                </td>
            </tr>
            </c:forEach>
        </table>
        <ht:page pageData="${settlePage}" />
        </c:if>
        <c:if test="${empty settlePage.data}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>