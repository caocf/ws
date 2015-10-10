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
        <ht:menu-btn text="操作日志"  type="search"/>
        <!-- 
        	todo:登录日志由sso记录，此处无法查询，需要后续根据sso处功能增加查询
        <ht:menu-btn  text="登录日志" url="/sys/log/login-list"/> -->
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="list" method="get">
        <table>
            <tr>
            	<td>编号：</td>
                <td><input type="text" name="id"  value="${logInfo.id}"  class="txt validate-number"  />
                </td>
                <td>操作用户：</td>
                <td><input type="text" name="userName" value="${logInfo.userName}" class="txt" style="width:206px"/></td>
                <td>操作类型：</td>
                <td width="200">
                    <select name="operType">
                        <option value="">--请选择--</option>
                        <c:forEach items="${operTypeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${logInfo.operType == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
             </tr>  
             <tr>
             	<td>操作模块：</td>
                <td><input type="text" name="module" value="${logInfo.module}" class="txt" /></td>
                <td>操作描述：</td>
                <td><input type="text" name="description" value="${logInfo.description}" class="txt" /></td>
              <td width="70"> 操作时间：</td>
                <td width="300" colspan="3">
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
                </td>
             </tr>
             <tr>   
                <td colspan="4">&nbsp;
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>操作日志列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap" width="50">编号</th>
                <th nowrap="nowrap" width="50">操作人</th>
                <th nowrap="nowrap" width="80">操作时间</th>
                <th nowrap="nowrap" width="50">操作类型</th>
                <th nowrap="nowrap" width="150">操作模块</th>
                <th nowrap="nowrap" width="200">操作描述</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td width="50" nowrap="nowrap">${item.id }</td>
                <td width="50" nowrap="nowrap" class="ellipsis">${item.userName}</td>
                <td width="100" nowrap="nowrap"><ct:time source="${item.operTime}"/></td>
                <td width="50" nowrap="nowrap">${item.operTypeName}</td>
                <td width="100" nowrap="nowrap" class="ellipsis"> ${item.module} </td>
                <td width="100" nowrap="nowrap" class="ellipsis"> ${item.description} </td>
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