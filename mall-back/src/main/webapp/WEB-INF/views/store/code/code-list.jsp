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
         <ct:display model="store_code" btn="add_btn">
          <ht:menu-btn text="添加" url="/store/code/add" type="add"/>
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>

<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="list" method="get">
        <table>
            <tr>
                <td width="100">商户名称：</td>
                <td width="150"><input type="text" name="storeName" value="${param.storeName}" class="txt" style="width:150px"/></td>
                <td width="100">商户编号：</td>
			    <td width="150"><input id="ordernumber" type="text" name="storeId" value="${param.storeId}"	 class="txt validate-number"  style="width:100px" />
                <td width="70">生效时间：</td>
                <td>
		             <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate" readOnly 
		             	onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2050-10-01\'}'})" />
                   		<input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                   	至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}"  class="txt Wdate" readOnly 
             			onfocus="WdatePicker({vel:'stopTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2050-10-01'})" />
                    	<input type="hidden" name="stopTime" id="stopTime" value="${param.stopTime}" />
                </td>
                </tr>
                <tr>
                <td>发码渠道：</td>
                <td colspan="2" width="300">
               		<select name="sendChannelId" id="sendChannelId">
                        <option value="">--请选择--</option>
                        <c:forEach items="${configList}" var="channel">
                        	<option value="${channel.sendChannel }" 	<c:if test="${param.sendChannelId == channel.sendChannel}">selected="selected"</c:if>>${sendChannelIdMap[channel.sendChannel]}</option>
                        </c:forEach>
                    </select>
                </td>
                 </tr>
                <tr>
                <td colspan="1">&nbsp;
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>商户发码方式管理</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData.data}">
        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap">商户编号</th>
                <th nowrap="nowrap">商户名称</th>
                <th nowrap="nowrap">地市</th>
                <th nowrap="nowrap">发码渠道</th>
                <th nowrap="nowrap">生效日期</th>
                <th nowrap="nowrap">失效日期</th>
                <th nowrap="nowrap">操作</th>
            </tr>
            <c:forEach items="${pageData.data}" var="store">
            <tr>
            	<td  nowrap="nowrap">${store.storeId}</td>
                <td  nowrap="nowrap">${store.storeName}</td>
                <td nowrap="nowrap">${store.areaName}</td>
                <td  nowrap="nowrap">
                ${sendChannelIdMap[store.sendChannelId]}-
                <c:if test="${store.sendTypeId eq 1}">${sendTypeIdMap['1']}</c:if>
                <c:if test="${store.sendTypeId eq 2}">${sendTypeIdMap['2']}</c:if>
                </td>
                <td  nowrap="nowrap">
               <ct:time source="${store.startTime }"  />
                </td>
                <td  nowrap="nowrap">
               <ct:time source="${store.stopTime }"/>
                </td>
                <td width="100" nowrap="nowrap">
	                <ct:display model="store_list" btn="mod_btn">
	                        <a href="edit/${store.id}">编辑</a>&nbsp;&nbsp;
	                </ct:display>
	                <ct:display model="store_list" btn="del_btn">
	                		<a href="#" onclick="deleteItem('delete/${store.id}');">删除</a>&nbsp;&nbsp;
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