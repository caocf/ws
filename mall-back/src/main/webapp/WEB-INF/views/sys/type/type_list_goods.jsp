<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
     
        <ht:head/>
        <script type="text/javascript">
 
        
        </script>
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
                <td><input type="text" name="name" value="${param.name}" class="txt" style="width:206px"/></td>
                <td>是否显示：</td>
                <td>
                <select name="isValid">
	                <option value="">--请选择--</option>
	                <c:forEach items="${isValidMap }" var="item">
	                	    <option value="${item.key }" <c:if test="${param.isValid == item.key}">selected="selected"</c:if>>${item.value }</option>
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
    <c:if test="${not empty type}"><h3>分类列表管理</h3></c:if>
    <br />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="mainbox">
        <c:if test="${not empty sysType}">

        <table class="datalist fixwidth">
<tr>
        <td colspan="5" align="left">
           <ct:display model="type_list_goods" btn="noShow_btn">
    <ht:table-action-btn text="不显示" url="/sys/type/ModifyportalIsNo"/>
         </ct:display>
         <ct:display model="type_list_goods" btn="show_btn">
      <ht:table-action-btn text="显示" url="/sys/type/Modifyportal"/>
       </ct:display>
           </td>
  
    </tr>
            <tr>
            <th nowrap="nowrap" width="50"><input type="checkbox" class="checkall">全选 </th>
                <th nowrap="nowrap" width="180">名称</th>
                <th nowrap="nowrap" width="90">类型</th>
                <th nowrap="nowrap" width="90">是否显示</th>
                <th nowrap="nowrap" width="130">操作</th>
            </tr>

            <c:forEach items="${sysType.data}" var="item">
            <tr>
            	<td ><input type="checkbox" id="${item.id}" name="ids" value="${item.id}">${item.id}</td>
                <td >${item.name}</td>
                <td>${item.typeName}</td>
                <td>${item.isValidName}</td>
                <td>
                <ct:display model="type_list_goods" btn="mod_btn">
                   	<a href="${ctx}/sys/type/edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                <!--  
                   	<c:if test="${item.type == 2}">
                <ct:display model="type_list_goods" btn="add_btn">
                   	<a href="${ctx}/websys/typeitemparam/add?id=${item.id}&typeName=${item.name}">添加参数</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                   	</c:if>
                 -->
                <ct:display model="type_list_goods" btn="del_btn">
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
		window.parent.goodsTreeFrame.location.reload();
	}
});
</script>
</body>
</html>