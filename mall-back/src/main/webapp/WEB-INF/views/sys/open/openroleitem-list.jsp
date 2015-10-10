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
        <ct:display model="roleitem-list" btn="add_btn">
             <ht:menu-btn text="添加权限项目" url="/sys/open/roleitem-add" type="add"/>
        </ct:display>
       
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
             <tr>
                <td width="70">名称：</td>
                <td width="100"/>
                <input type="text" name="name" value="${param.name}" class="txt" style="width:150px"/>
                </td>
                 <td colspan="2">
                    <ct:btn type="search" />
                </td>
               
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>权限项目列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="80" >ID</th>
                <th nowrap="nowrap" width="100">名称</th>
                <th nowrap="nowrap" width="110">路径</th>
                <th nowrap="nowrap" width="100" >操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
           <tr>
                <td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap" class="ellipsis">${item.name}</td>
                <td nowrap="nowrap" class="ellipsis">${item.path}</td>
                <td nowrap="nowrap">
                  <ct:display model="roleitem-list" btn="view_btn">
                        <a href="roleitem-view.do?id=${item.id}">查看</a>
                    </ct:display>
                     &nbsp;&nbsp;
                    <ct:display model="roleitem-list" btn="mod_btn">
                        <a href="roleitem-edit?id=${item.id}">修改</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="roleitem-list" btn="del_btn">
                        <a href="#" onclick="deleteItem('roleitem-del?id=${item.id}');">删除</a>
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