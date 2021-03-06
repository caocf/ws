<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
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
        <ct:display model="channel_floor_list" btn="add_btn">
        	<ht:menu-btn text="添加楼层配置" url="/websys/channel/floor/preAdd" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                
              <td>楼层标题：</td>
             <td><input type="text" name="floorTitle" value="${param.floorTitle}" class="txt" style="width:120px"/></td>
             
                <td width="70">楼层：</td>
                <td width="200">
                    <select name="floorId">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${groupMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.floorId == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
                <td width="70">频道：</td>
                <td width="200">
                    <select name="channel">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${channelMap}" var="item">
                        	    <option value="${item.key }" <c:if test="${param.channel == item.key}">selected="selected"</c:if>>${item.value }</option>
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
    <h3>楼层列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
        	<colgroup>
		       	<col width="80"></col>
		       	<col width="100"></col>
		       	<col width="80"></col>
		       	<col width="100"></col>
		       	<col width="80"></col>
		       	<col width="80"></col>
		       	<col width="80"></col>
		       	<col width="80"></col>
	    	</colgroup>
            <tr>
                <th nowrap="nowrap" width="80">楼层编号</th>
                <th nowrap="nowrap"  width="300">楼层标题</th>
                <th nowrap="nowrap"  width="100">楼层</th>
                <th nowrap="nowrap"  width="180">类别</th>
                <th nowrap="nowrap"  width="100">频道</th>
                <th nowrap="nowrap"  width="100">区域编号</th>
                <th nowrap="nowrap"  width="80">排序</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap" class="ellipsis">${item.floorTitle}</td>
                <td nowrap="nowrap">${item.floorName}</td>
                <td nowrap="nowrap">${item.typeName}</td>
                <td nowrap="nowrap">${item.channelName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.regionName}</td>
                <td nowrap="nowrap">${item.orderIndex}</td>
                <td nowrap="nowrap">
                <ct:display model="channel_floor_list" btn="mod_btn">
                 <a href="./preEdit?id=${item.id}">修改</a>
                 </ct:display>
                 <ct:display model="channel_floor_list" btn="del_btn">
                 <a href="#" onclick="deleteItem('./delete.do?id=${item.id}');">删除</a>
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