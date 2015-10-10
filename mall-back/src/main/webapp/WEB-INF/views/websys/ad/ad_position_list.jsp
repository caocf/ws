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
        <ct:display model="ad_position_list" btn="add_btn">
        	<ht:menu-btn text="添加广告位置" url="/websys/ad/position_add" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="position_list" method="get">
        <table>
            <tr>
                <td width="70">名称：</td>
                <td width="300"><input type="text" name="name" value="${adPosition.name}" class="txt" style="width:206px"/></td>
                <td width="70">识别码：</td>
                <td width="200"><input type="text" name="code" value="${adPosition.code}" class="txt" style="width:150px"/></td>
                <td width="70">类型：</td>
                <td width="200">
                    <select name="type">
                        <option value="">--请选择--</option>
                        <c:forEach items="${typeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${adPosition.type == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                <td >
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>广告位置列表</h3>

    <div class="mainbox">
        <c:if test="${not empty adPositionPage}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="60">位置编号</th>
                <th nowrap="nowrap" width="100">名称</th>
                <th nowrap="nowrap" width="100">识别码</th>
                <th nowrap="nowrap" width="90">宽</th>
                <th nowrap="nowrap" width="90">高</th>
                <th nowrap="nowrap" width="40">广告数量</th>
                <th nowrap="nowrap" width="40">位置</th>
                <th nowrap="nowrap" width="130">操作</th>
            </tr>

            <c:forEach items="${adPositionPage.data}" var="item">
            <tr>
                <td nowrap="nowrap" >${item.id}</td>
                <td nowrap="nowrap" class="ellipsis">${item.name}</td>
                <td nowrap="nowrap" class="ellipsis">${item.code}</td>
                <td nowrap="nowrap">${item.width}</td>
                <td nowrap="nowrap">${item.height}</td>
                <td nowrap="nowrap">${item.num}</td>
                <td nowrap="nowrap">${item.position}</td>
                <td nowrap="nowrap">
                <ct:display model="ad_position_list" btn="mod_btn">
                   	<a href="./position_edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                <ct:display model="ad_position_list" btn="add_btn">
                   	<a href="./ad_add?id=${item.id}">添加广告</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                <ct:display model="ad_position_list" btn="del_btn">
                   	<a href="#" onclick="deleteItem('./position_delete/${item.id}');">删除</a>
                </ct:display>   	
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${adPositionPage}" />

        </c:if>
        <c:if test="${empty adPositionPage}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>