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
        <ct:display model="blackUser_list" btn="add_btn">
     	   <ht:menu-btn text="添加黑名单" url="/sys/black/preAdd.do"/>
      	</ct:display>
      	        <ct:display model="blackUser_list" btn="imp_btn">
        <ht:menu-btn text="批量导入" url="/sys/black/preBatchadd.do" />
           	</ct:display>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form:form name="queryForm" id="queryForm" action="?" method="get" >
        <table>
            <tr>
                <td>号码：</td>
                <td><input type="text" name="terminalId" value="${param.terminalId}" class="txt" style="width:206px" class="validate-number"/></td>
                <td>级别：</td>
                 <td width="100">
                    <select name="levTag">
                        <option value="">--请选择--</option>
                        <c:forEach items="${levTapMap }" var="item">
                        <option value="${ item.key}" <c:if test="${param.levTag == item.key}">selected="selected"</c:if>>${ item.value}</option>
                        </c:forEach>
                    <select>
                </td>
                 </tr>
                <tr>
                 <td>导入类型：</td>
                <td width="100">
                    <select name="inTag">
                        <option value="">--请选择--</option>
                        <c:forEach items="${inTagMap}" var="item">
                        <option value="${item.key }" <c:if test="${param.inTag == item.key}">selected="selected"</c:if>>${item.value}</option>
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
    </form:form>
</div>

<div class="container">
    <br/>
    <h3>黑名单列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">
        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">黑名单编号</th>
                <th nowrap="nowrap">号码</th>
                <th nowrap="nowrap">级别</th>
                <th nowrap="nowrap">导入类型</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap">${item.terminalId}</td>
                <td nowrap="nowrap"><c:choose><c:when test="${item.levTag==0}">平台级</c:when><c:when test="${item.levTag==1}">不能接收群发信息</c:when><c:when test="${item.levTag==2}">广告黑名单</c:when><c:when test="${item.levTag==3}">本单位所有业务都不能参加</c:when></c:choose></td>
                <td nowrap="nowrap"><c:choose><c:when test="${item.inTag==0}">手工输入</c:when><c:when test="${item.inTag==1}">批量导入</c:when></c:choose></td>
                <td nowrap="nowrap">
                    <ct:display model="blackUser_list" btn="mod_btn">
                        <a href="preUpdate.do?id=${item.id}">修改</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="blackUser_list" btn="del_btn">
                        <a href="#" onclick="deleteItem('delete.do?id=${item.id}');">删除</a>
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