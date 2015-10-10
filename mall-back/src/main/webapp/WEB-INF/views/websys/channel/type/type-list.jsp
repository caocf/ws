<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
	    <script type="text/javascript">
    	$(document).ready(function(){
    		/*
    		selectItemType("#typeName",function(id,txt){
	    		$("#typeId").val(id);
	    		$("#typeName").val(txt);
	    	});
	    	*/
    	});
    
    </script>
</head>
<body>
<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
        <ct:display model="channel_type_list" btn="add_btn">
        	<ht:menu-btn text="添加频道分类配置" url="/websys/channel/type/add" type="add"/>
         </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                
                <td width="70">分类：</td>
                <td width="200">
                <input type="input" name="typeName" id="typeName" value="${ param.typeName}"  class="txt"/>
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
                
                 <td width="70">区域：</td>
                <td width="200">
                
                    <input type="input" name="regionName" value="${ param.regionName}" class="txt"/>
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
    <h3>频道楼层分类列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth"  action="/websys/channel/catalog">
        <colgroup>
			       	<col width="90"></col>
			       	<col width="150"></col>
			       	<col width="150"></col>
			       	<col width="50"></col>
			       	<col width="50"></col>
			       	<col width="50"></col>
		    	</colgroup>
            <tr>
                <th nowrap="nowrap" width="50px">编号</th>
                <th nowrap="nowrap"  width="250px">分类名称</th>
                <th nowrap="nowrap"  width="300px">显示名称</th>
                <th nowrap="nowrap"   width="150px">地区</th>
                <th nowrap="nowrap"  width="50px">频道</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap">${item.typeName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.displayName}</td>
                <td nowrap="nowrap" >${item.regionName}</td>
                <td nowrap="nowrap">${item.channelName}</td>
                <td nowrap="nowrap">
                	<ct:display model="channel_type_list" btn="mod_btn">
                   	<a href="./edit?id=${item.id}">修改</a>
                   	</ct:display>
                   	<ct:display model="channel_type_list" btn="del_btn">
                   	<a href="#" onclick="deleteItem('./del.do?id=${item.id}');">删除</a>
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