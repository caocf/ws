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
        <ct:display model="property_list" btn="add_btn">
        <ht:menu-btn text="添加属性" url="/websys/property/add" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td width="70">属性：</td>
                <td width="300"><input type="text" name="content" value="${param.content}" class="txt" style="width:206px"/></td>
                <td width="70">选择方式：</td>
                <td width="200">
                    <select name="type">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${typeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.type == item.key}">selected="selected"</c:if>>${item.value }</option>
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
    <h3>属性配置列表</h3>

    <div class="mainbox">
        <c:if test="${not empty sysProperty}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="100">内容</th>
                <th nowrap="nowrap" width="100">选择方式</th>
                <th nowrap="nowrap" width="100">操作</th>
            </tr>

            <c:forEach items="${sysProperty.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.content}</td>
                <td nowrap="nowrap">${item.typeName}</td>
                <td nowrap="nowrap">
                <ct:display model="property_list" btn="mod_btn">
                   	<a href="./edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                <ct:display model="property_list" btn="del_btn">
                   	<a href="#" onclick="deleteItem('./delete/${item.id}');">删除</a>
                </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${sysProperty}" />

        </c:if>
        <c:if test="${empty sysProperty}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>