<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
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
                
             <td>活动名称：</td>
             <td><input type="text" name="actName" value="${param.actName}" class="txt" style="width:120px"/></td>
             
             <td>指令内容：</td>
             <td><input type="text" name="command" value="${param.command}" class="txt" style="width:120px"/></td>
             
             
                <td width="70">指令类型：</td>
                <td width="200">
                    <select name="cmdOptType">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${cmdOptTypeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.cmdOptType == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
            </tr>
            <tr>
                <td colspan="4">
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>商品指令列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">归属活动</th>
                <th nowrap="nowrap">商品名称</th>
                <th nowrap="nowrap">特服号</th>
                <th nowrap="nowrap">指令类型</th>
                <th nowrap="nowrap">指令内容</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.actName}</td>
                <td nowrap="nowrap">${item.itemName}</td>
                <td nowrap="nowrap">${item.spCode}</td>
                <td nowrap="nowrap">${item.cmdOptTypeName}</td>
                <td nowrap="nowrap">${item.command}</td>
                <td nowrap="nowrap">
                <ct:display model="smsbuy_act_router_list" btn="view_btn">
                <a href="./viewRouter?id=${item.id}">查看</a>
                </ct:display>
                <ct:display model="smsbuy_act_router_list" btn="mod_btn">
                <a href="./preEditRouter?id=${item.id}">修改</a>
                </ct:display>
                 <ct:display model="smsbuy_act_router_list" btn="del_btn">
                <a href="#" onclick="deleteItem('./deleteRouter.do?id=${item.id}');">删除</a>
                </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${pageData}" />

        </c:if>
        <c:if test="${empty pageData}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>