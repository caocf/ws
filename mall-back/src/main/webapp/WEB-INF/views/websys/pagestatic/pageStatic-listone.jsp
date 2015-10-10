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
	</ul>
    <br style="clear: left" />
</div>

<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td>商品编号：</td>
				<td width="200"><input id="ordernumber" type="text" name="resourceId" value="${param.resourceId}"	class="txt validate-number" style="width:120px" /></td>
            	<td>商品名称：</td>
				<td><input type="text" name="itemName" value="${param.itemName}" class="txt" style="width:120px" /></td>
				
				
				<td width="70">时间段：</td>
			                <td width="300">
			                <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'stopTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="stopTime" id="stopTime" value="${param.endTime}" />
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
    <h3>商品静态化列表</h3> 
    <div class="mainbox">
        <c:if test="${not empty pageData.data}">
        <table class="datalist fixwidth" >
       		<tr>
        		<td colspan="6" align="left">
        		   <ct:display model="item_pageStatic_list" btn="itemStatic_btn">
        		<ht:table-action-btn  text="执行商品静态化" url="/websys/pageStatic/staticAll"/>
<!--        		<ht:table-action-btn  text="执行全部静态化" url="/websys/pageStatic/batchSataticAll"/>-->
 				</ct:display>
        		</td>
        	</tr>
            <tr>
            	<th nowrap="nowrap" width="80"><input type="checkbox" class="checkall"/>编号</th>
                <th nowrap="nowrap" width="60">商品名称</th>
                <th nowrap="nowrap" width="80">商品编号</th>
                <th nowrap="nowrap" width="200">页面是否静态化</th>
                <th nowrap="nowrap" width="150">时间</th>
                <th nowrap="nowrap">操作</th>
            </tr>
 
            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td  nowrap="nowrap"><input type="checkbox"  name="id" value="${item.id }"/>${item.id }</td>
                <td  nowrap="nowrap">${item.itemName}</td>
                <td  nowrap="nowrap" class="ellipsis">	${item.resourceId } </td>
                <td  nowrap="nowrap" class="ellipsis"><c:if test="${item.status eq 0}">否</c:if><c:if test="${item.status eq 1}">是</c:if> </td>
                <td><ct:time source="${item.createTime}" tfmt="yyyy-MM-dd HH:mm:ss"/></td>
                <td  nowrap="nowrap"> 
	                <ct:display model="item_pageStatic_list" btn="static_btn">
	                 <a href="#" onclick="dealInfo('确认静态化？','staticOne?id=${item.id}')">静态化</a>&nbsp;&nbsp;
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