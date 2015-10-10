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
        <ht:menu-btn text="添加栏目" url="/websys/channel/navioper/preAdd"/> 
       
       
    </ul>
 
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form:form name="queryForm" id="queryForm"  action="list" method="get" commandName="channelNaviOper">
        <table >
            <tr>
                <td width="70">关键字:</td>
                <td width="130"><input type="text" name="title" value="${channelNaviOper.title}" class="txt" style="width:120px"/></td>
            <td colspan="2">&nbsp;
                    <ct:btn type="search" />
                </td>
             </tr>
        </table>
    </form:form>
</div>
<div class="container">
    <br/>
    <h3>首页导航栏栏目列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">
        <table class="datalist fixwidth" >
 
            <tr>
            	<th nowrap="nowrap" width="100">编号</th>
                <th nowrap="nowrap" width="130">名称</th>
                <th nowrap="nowrap" width="200">链接</th>
                <th nowrap="nowrap" width="80">编码</th>
                <th nowrap="nowrap" width="50">排序</th>
                <th nowrap="nowrap" width="100">地区</th>
                <th nowrap="nowrap" width="130">所属网页</th>
                <th nowrap="nowrap" width="100">创建时间</th>
                <th nowrap="nowrap" width="200">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td  nowrap="nowrap" class="ellipsis">${item.id }</td>
                <td  nowrap="nowrap" class="ellipsis">	${item.title } </td>
                <td  nowrap="nowrap" class="ellipsis">	${item.href } </td>
                <td  nowrap="nowrap" class="ellipsis">${item.code}</td>
                <td  nowrap="nowrap" class="ellipsis">${item.sortNo}</td>
                <c:if test="${item.regionCode == 0}"><td  nowrap="nowrap" class="ellipsis">全国</td></c:if>
                <c:if test="${item.regionCode != 0}"><td  nowrap="nowrap" class="ellipsis">${item.regionName}</td></c:if>
                <td  nowrap="nowrap" class="ellipsis">${item.pageCodeTitle}</td>
                <td  nowrap="nowrap" class="ellipsis">${item.createTime}</td>
                <td  nowrap="nowrap"> 
                <ct:display model="channel_naviconfig_list" btn="mod_btn">
                 <a href="./preEdit?id=${item.id}">修改</a>&nbsp;&nbsp;
				</ct:display> 
				<ct:display model="channel_naviconfig_list" btn="del_btn">
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