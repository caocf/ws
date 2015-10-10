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
        <ct:display model="floorType_list" btn="add_btn">
        	<ht:menu-btn text="添加楼层商品配置" url="/websys/channel/catalog/preAdd" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
            	 <td width="80">商品名称：</td>
             <td width="200"><input type="text" name="itemOrgName" value="${param.itemOrgName}" class="txt"/></td>
                <td width="70">频道：</td>
                <td width="200">
                    <select name="channel">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${channelMap}" var="item">
                        	    <option value="${item.key }" <c:if test="${param.channel == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
                <td width="70">楼层：</td>
                <td width="200">
                    <select name="groupId">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${groupMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.groupId == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
                 
           
                
                 <td width="70">状态：</td>
                <td width="200">
                    <select name="status">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${statusMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
            </tr>
            <tr>
                <td colspan="5">
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>楼层商品列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth"  >
        	<ct:display model="floorType_list" btn="del_btn">
        	<tr>
        		<td colspan="9" align="left"><ht:table-action-btn text="删除" url="/websys/channel/catalog/delete"/></td>
        	</tr>
        	</ct:display>
        	<colgroup>
			       	<col width="90"></col>
			       	<col width="150"></col>
			       	<col width="50"></col>
			       	<col width="120"></col>
			       	<col width="80"></col>
			       	<col width="80"></col>
			       	<col width="80"></col>
			       	<col width="80"></col>
			       	<col width="80"></col>
		    	</colgroup>
            <tr>
                <th nowrap="nowrap" width="50px"><input type="checkbox" class="checkall"/>楼层分类编号</th>
                <th nowrap="nowrap"  width="300px">商品名称</th>
                <th nowrap="nowrap"  width="90px">分类</th>
                <th nowrap="nowrap"   width="150px">修改时间</th>
                <th nowrap="nowrap"  width="50px" >区域</th>
                <th nowrap="nowrap"  width="50px">排序</th>
                <th nowrap="nowrap"  width="50px">状态</th>
                <th nowrap="nowrap"  width="80px">频道</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap"><input type="checkbox"  name="ids" value="${item.id }"/>${item.id}</td>
                <td nowrap="nowrap" class="ellipsis">${item.itemOrgName}</td>
                <td nowrap="nowrap">${item.groupName}</td>
                <td nowrap="nowrap"><ct:time source="${item.updateTime}"/></td>
                <td nowrap="nowrap" class="ellipsis" >${item.regionName}</td>
                <td nowrap="nowrap" >${item.orderIndex}</td>
                <td nowrap="nowrap">${item.statusName}</td>
                <td nowrap="nowrap">${item.channelName}</td>
                <td nowrap="nowrap">
                	<c:if test="${item.status==0}">
                	<ct:display model="floorType_list" btn="mod_btn">
                   	<a href="./preEdit?id=${item.id}">修改</a>
                   	</ct:display>
                   	
                   	<ct:display model="floorType_list" btn="start_btn">
                   	<a href="#this" onclick="dealInfo('确认启用？','turnOn?id=${item.id}&isValid=${item.isValid}');">启用</a>
                   	</ct:display>
                   	</c:if>
                   	<c:if test="${item.status==1}">
                   	<ct:display model="floorType_list" btn="pause_btn">
                   	<a href="#this" onclick="dealInfo('确认禁用？','turnOff?id=${item.id}');">禁用</a>
                   	</ct:display>
                   	</c:if>
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