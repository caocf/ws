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
        <c:choose>
        <c:when test="${type == 1}">
        <ct:display model="type_list_store" btn="add_btn">
        	<ht:menu-btn text="添加商户分类" url="/sys/type/add?pId=${pId}&type=${type}" type="add"/>
        </ct:display>
        </c:when>
        <c:when test="${type == 2}">
        <ct:display model="type_list_goods" btn="add_btn">
        	<ht:menu-btn text="添加商品分类" url="/sys/type/add?pId=${pId}&type=${type}" type="add"/>
        </ct:display>
        </c:when>
        </c:choose>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
<c:if test="${empty type}"><font color="red">选择左侧菜单栏分类，添加分类</font></c:if>
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
            	<td>名称：</td>
                <td><input type="text" id="name" name="name" title="名称" value="${param.name}" class="txt required" style="width:206px"/>
                <span class="error" id="advice-required-name" style="display:none"></span></td>
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
    <c:if test="${not empty type}"><h3>${name}列表</h3></c:if>
	<div class="mainbox">
        <c:if test="${not empty sysType}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="100">名称</th>
                <th nowrap="nowrap" width="90">类型</th>
                  <th nowrap="nowrap" width="90">是否有效</th>
                <th nowrap="nowrap" width="100">操作</th>
            </tr>

            <c:forEach items="${sysType.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.name}</td>
                <td nowrap="nowrap">${item.typeName}</td>
                <td>${item.isValidName}</td>
                <td nowrap="nowrap">
                <ct:display model="type_list_store" btn="mod_btn">
                   	<a href="${ctx}/sys/type/edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                   	<c:if test="${item.type == 2}">
                <ct:display model="type_list_goods" btn="add_btn">
                   	<a href="../../../websys/typeitemparam/add?id=${item.id}&typeName=${item.name}">添加参数</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                   	</c:if>
                <ct:display model="type_list_store" btn="del_btn">
                   	<a href="#" onclick="deleteItem('${ctx}/sys/type/delete/${item.id}');">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${sysType}" />

        </c:if>
        <c:if test="${empty sysType}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>
<script type="text/javascript">
$().ready(function() {
	var refresh = ${refresh};
	if("1" == refresh){
		window.parent.storeTreeFrame.location.reload();
	}
});
</script>
</body>
</html>