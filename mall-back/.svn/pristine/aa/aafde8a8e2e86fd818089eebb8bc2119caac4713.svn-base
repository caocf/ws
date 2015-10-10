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
        <ct:display model="filterword_list" btn="add_btn">
            <ht:menu-btn text="添加过滤字" url="/sys/filterword/add" type="add"/>
        </ct:display>
        <ct:display model="filterword_list" btn="imp_btn">
            <ht:menu-btn text="批量导入" url="/sys/filterword/batchadd" />
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="list" method="get">
        <table>
            <tr>
                <td>过滤字：</td>
                <td><input type="text" name="word" value="${param.word}" class="txt" style="width:206px"/></td>
                <td colspan="4">&nbsp;
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>过滤字列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">


        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">过滤字</th>
                <th nowrap="nowrap">入库类型</th>
                <th nowrap="nowrap">创建时间</th>
                <th nowrap="nowrap">创建人</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td width="50" nowrap="nowrap">${item.word}</td>
                <td width="50" nowrap="nowrap">
                <c:if test="${item.inTag==0}">手工输入</c:if>
                <c:if test="${item.inTag==1}">批量导入</c:if>
                </td>
                <td nowrap="nowrap" width="100">${item.updateTime}</td>
                <td width="50" nowrap="nowrap">${item.userName}</td>
                <td width="100" nowrap="nowrap">
                    <ct:display model="filterword_list" btn="mod_btn">
                        <a href="edit/${item.id}">修改</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="filterword_list" btn="del_btn">
                        <a href="#" onclick="deleteItem('delete/${item.id}');">删除</a>
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