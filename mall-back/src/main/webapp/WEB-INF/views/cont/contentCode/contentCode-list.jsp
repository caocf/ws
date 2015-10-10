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
        <ct:display model="contentCode_list" btn="add_btn">
        	<ht:menu-btn text="添加内容源" url="/cont/contentCode/preAdd.do"/>
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td>内容源标识：</td>
                <td><input type="text" name="code" value="${param.code}" class="txt" style="width:120px"/></td>
                
                 <td>内容源名称：</td>
                <td><input type="text" name="name" value="${param.name}" class="txt" style="width:120px"/></td>
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
    <h3>内容源列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">内容源编号</th>
                <th nowrap="nowrap" width="150">标识</th>
                <th nowrap="nowrap" width="250">名称</th>
                <!-- <th nowrap="nowrap">地市</th>-->
                <th nowrap="nowrap" width="120">信息类型</th>
                <th nowrap="nowrap" width="120">使用类型</th>
                <th nowrap="nowrap" >操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td width="50" nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap" >${item.code}</td>
                <td nowrap="nowrap" class="ellipsis">${item.name}</td>
                <!-- <td nowrap="nowrap" width="100">${item.areaCode}</td> -->
                <td nowrap="nowrap" >${item.contTypeName}</td>
                 <td nowrap="nowrap" >${item.codeTypeName}</td>
                <td  nowrap="nowrap">
                    <ct:display model="contentCode_list" btn="mod_btn">
                        <a href="preUpdate.do?id=${item.id}">修改</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="contentCode_list" btn="del_btn">
                        <a href="#" onclick="deleteItem('delete.do?id=${item.id}');">删除</a>
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