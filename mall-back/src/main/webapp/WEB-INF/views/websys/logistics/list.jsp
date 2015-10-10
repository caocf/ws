<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
<script type="text/javascript">
        
  $().ready(function() {
		
		$(".item_view").click(function() {
			var id = $(this).attr("id");
			showDialog("查看物流信息", 
			"view?id="+id,function(doc){},
			{"Width":400,"Height":700,"ShowMessageRow":false,"ShowButtonRow":false});
			
	});
});    
        
</script>

    </head>
<body>
<br/>
<div id="search-menu">
    <ul>
     <ht:menu-btn type="search"/>
     
   <ct:display model="logistics_list" btn="add_btn">
        <ht:menu-btn text="添加物流公司" url="/websys/logistics/add"/>
                	 </ct:display>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="list" method="get">
        <table>
            <tr>
                <td>物流名称：</td>
                <td><input type="text" name="name" value="${param.name}" class="txt" style="width:206px"/></td>
                <td>是否有效：</td>
                <td width="200">
                    <select name="isValid">
                        <option value="">--请选择--</option>
                        <option ${sysLogistics.isValid == 0?"selected":""} value="0">无效</option>
                        <option ${sysLogistics.isValid == 1?"selected":""} value="1">有效</option>
                    </select>
                </td>
                <td colspan="2">&nbsp;
                    <ct:btn type="search" />
                </td>
             </tr>  
             
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>物流列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">
        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap">物流公司编号</th>
                <th nowrap="nowrap">物流公司名称</th>
                <th nowrap="nowrap">英文名称</th>
                <th nowrap="nowrap">是否有效</th>
                <th nowrap="nowrap">接口地址</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td class="ellipsis" width="100" nowrap="nowrap">	${item.id} </td>
                <td class="ellipsis" width="100" nowrap="nowrap">	${item.name } </td>
                <td class="ellipsis" width="100" nowrap="nowrap">	${item.ename } </td>
                <td nowrap="nowrap" width="100">${item.isValidName}</td>
                <td class="ellipsis" width="50" nowrap="nowrap">${item.interFace}</td>
                <td width="100" nowrap="nowrap">
                 <ct:display model="logistics_list" btn="view_btn">
                         <a class="item_view" id="${item.id }" href="#this">查看</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="logistics_list" btn="mod_btn">
                        <a href="edit?id=${item.id}">修改</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="role_list" btn="del_btn">
                        <a href="#" onclick="deleteItem('del?id=${item.id}');">删除</a>
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