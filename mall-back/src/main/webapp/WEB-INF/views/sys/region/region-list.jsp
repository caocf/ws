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
        <ct:display model="region_list" btn="add_btn">
             <ht:menu-btn text="添加区域" url="/sys/region/regionAdd.do?parentRegion=${parentRegion}&regionLevel=${regionLevel}" type="add"/>
        </ct:display>
       
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
             <tr>
                <td width="70">选择区域：</td>
                <td width="100">
                <select name="parentRegion">
                    <option value="0">--请选择--</option>
                      <c:forEach items="${regionList}" var="item">
                      <option value="${item.regionCode }" ${item.regionCode eq parentRegion?"selected":"" }>${item.regionName }</option>
                      </c:forEach>
                </select>
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
    <h3>区域列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">名称</th>
                <th nowrap="nowrap">编码</th>
                <th nowrap="nowrap">简称</th>
                <th nowrap="nowrap">拼音</th>
                <th nowrap="nowrap">是否显示</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
           <tr>
                <td width="50" nowrap="nowrap"><a href="list.do?parentRegion=${item.regionCode}&regionLevel=${item.regionLevel+1}">${item.regionName}</a>
                <td nowrap="nowrap" width="100">${item.regionCode}</td>
                <td nowrap="nowrap" width="100">${item.shortName}</td>
                <td nowrap="nowrap" width="100">${item.regionSpell}</td>
                <td nowrap="nowrap" width="100">${item.isShow==1?"是":"否"}</td>
                <td width="100" nowrap="nowrap">
                    <ct:display model="region_list" btn="add_btn">
                        <a href="regionAdd.do?parentRegion=${item.regionCode}&regionLevel=${item.regionLevel+1}">添加</a>
                    </ct:display>
                     &nbsp;&nbsp;
                    <ct:display model="region_list" btn="mod_btn">
                        <a href="regionEdit?id=${item.id}">修改</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="region_list" btn="del_btn">
                        <a href="#" onclick="deleteItem('regionDel?id=${item.id}');">删除</a>
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