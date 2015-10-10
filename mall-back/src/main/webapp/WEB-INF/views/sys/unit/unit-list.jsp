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
        <ct:display model="unit_list" btn="add_btn">
             <ht:menu-btn text="添加单位" url="/sys/unit/unitAdd" type="add"/>
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
             <tr>
                <td width="70">单位名称：</td>
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
    <h3>单位列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="80" >单位编号</th>
                <th nowrap="nowrap">名称</th>
                <th nowrap="nowrap" width="110">区域</th>
                <th nowrap="nowrap" width="100">单位分类</th>
                <th nowrap="nowrap" width="100">状态</th>
                <th nowrap="nowrap" width="180">备注</th>
                <th nowrap="nowrap" width="100" >操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
           <tr>
                <td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap" class="ellipsis">${item.name}</td>
                <td nowrap="nowrap" >${item.regionName}</td>
                <td nowrap="nowrap" >${item.unitTypeName}</td>
                <td nowrap="nowrap" >${item.flagTyprName}</td>
               <td nowrap="nowrap" class="ellipsis">${item.remark}</td>
                <td nowrap="nowrap">
                  <ct:display model="unit_list" btn="view_btn">
                        <a href="unitView.do?id=${item.id}">查看</a>
                    </ct:display>
                     &nbsp;&nbsp;
                    <ct:display model="unit_list" btn="mod_btn">
                        <a href="unitEdit?id=${item.id}">修改</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <c:if test="${item.flag eq 0 }">
	                    <ct:display model="unit_list" btn="del_btn">
	                        <a href="#" onclick="deleteItem('unitDel?id=${item.id}');">删除</a>
	                    </ct:display>
                    </c:if>
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