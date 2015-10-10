<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
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
        <ht:menu-btn text="添加导航栏" url="/websys/channel/navipage/preAdd"/>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form:form name="queryForm" id="queryForm"  action="list" method="get" commandName="channelNaviPage">
        <table >
            <tr>
                <td width="70">关键字:</td>
                <td width="130"><input type="text" name="title" value="${channelNaviPage.title}" class="txt" style="width:120px"/></td>
            <td colspan="2">&nbsp;
                    <ct:btn type="search" />
                </td>
             </tr>
        </table>
    </form:form>
</div>
<div class="container">
    <br/>
    <h3>导航栏列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">
        <table class="datalist fixwidth" >
 
            <tr>
            	<th nowrap="nowrap" width="150">编号</th>
                <th nowrap="nowrap" width="250">名称</th>
                <th nowrap="nowrap" width="150">编码</th>
                <th nowrap="nowrap" width="300">位置描述</th>
                <th nowrap="nowrap" width="150">创建时间</th>
                <th nowrap="nowrap" width="2000">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td  nowrap="nowrap" class="ellipsis">${item.id }</td>
                <td  nowrap="nowrap" class="ellipsis">	${item.title } </td>
                <td  nowrap="nowrap" class="ellipsis">${item.code}</td>
                <td  nowrap="nowrap" class="ellipsis">${item.des}</td>
                <td  nowrap="nowrap" class="ellipsis">${item.createTime}</td>
                <td  nowrap="nowrap"> 
                <ct:display model="channel_navipage_list" btn="mod_btn">
                 <a href="./preEdit?id=${item.id}">修改</a>&nbsp;&nbsp;
				</ct:display> 
				<ct:display model="channel_navipage_list" btn="del_btn">
					<a href="#" onclick="deleteItem('./delete.do?id=${item.id}');">删除</a>&nbsp;&nbsp;
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