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
        <ct:display model="user_list" btn="add_btn">
            <ht:menu-btn text="添加用户" url="/sys/user/userAdd" type="add"/>
        </ct:display>
       
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td width="70">创建时间：</td>
                <td width="300"/>
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
                </td>
                <td width="70">用户标识：</td>
                <td width="200">
                    <select name="flag">
                        <option value="">--请选择--</option>
                          <c:forEach items="${userTypeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.flag == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>帐号：</td>
                <td><input type="text" name="userCode" value="${param.userCode}" class="txt" style="width:206px"/></td>
                <td>姓名：</td>
                <td><input type="text" name="userName" value="${param.userName}" class="txt" style="width:206px"/></td>
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
    <h3>用户列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">用户编号</th>
                <th nowrap="nowrap">登录帐号</th>
                <th nowrap="nowrap">姓名</th>
                <th nowrap="nowrap">创建时间</th>
                <th nowrap="nowrap">状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td width="50" nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap" width="100">${item.userCode}</td>
                <td nowrap="nowrap" width="100">${item.userName}</td>
                <td nowrap="nowrap" width="100"><ct:time source="${item.createTime}"/></td>
                 <td nowrap="nowrap" width="100">${item.statusTypeName}</td>
                <td width="100" nowrap="nowrap">
                	<c:if test="${item.id != sessionUserId}">
	                    <ct:display model="user_list" btn="mod_btn">
	                        <a href="userEdit?id=${item.id}">修改</a>
	                    </ct:display>
	                    &nbsp;&nbsp;
	                    <ct:display model="user_list" btn="del_btn">
	                        <a href="#" onclick="deleteItem('userDel?id=${item.id}');">删除</a>
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