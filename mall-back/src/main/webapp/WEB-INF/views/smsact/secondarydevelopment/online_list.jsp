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
        <ct:display model="sms_act_online_query" btn="add_btn">
        <ht:menu-btn text="配置短信业务" url="/smsact/seconddevelop/online_add" type="add"/>
		</ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td width="70">名称：</td>
                <td width="300"><input type="text" name="actName" value="${param.actName}" class="txt" style="width:206px"/></td>
                <td width="70">状态：</td>
                <td width="200">
                   <select name="status">
                        <option value="">--请选择--</option>
                        <c:forEach items="${statusMap}" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
            	<td width="70">有效期：</td> 	
				<td width="300">
                    <input type="text" id="inputStartTimeS" name="inputStartTimeS" value="${param.inputStartTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTimeS\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                   	 至
                    <input type="text" id="inputEndTimeS" name="inputEndTimeS" value="${param.inputEndTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTimeS\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
                </td>
                <td colspan="4">
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>短信业务列表</h3>

    <div class="mainbox">
        <c:if test="${not empty onlinePage}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="90">业务配置编号</th>
                <th nowrap="nowrap" width="80">名称</th>
                <th nowrap="nowrap" width="80">分类</th>
                <th nowrap="nowrap" width="240">有效期</th>
                <th nowrap="nowrap" width="50">状态</th>
                <th nowrap="nowrap" width="120">操作</th>
            </tr>

            <c:forEach items="${onlinePage.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.actId}</td>
                <td nowrap="nowrap">${item.actName}</td>
                <td nowrap="nowrap">${item.groupId}</td>
                <td nowrap="nowrap"><ct:time source="${item.startTime}"/>~<ct:time source="${item.endTime}"/></td>
                <td nowrap="nowrap">${item.statusName}</td>
                <td nowrap="nowrap">
                	<ct:display model="sms_act_online_query" btn="view_btn">
                	<a href="#this" id="${item.actId}" class="view">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                	</ct:display>
                	<c:if test="${item.status != '4'}">
                		<c:if test="${item.status != '2'}">
                			<ct:display model="sms_act_online_query" btn="mod_btn">
		                   	<a href="./online_edit?id=${item.actId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
		                	</ct:display>
                		</c:if>
	                   	<c:if test="${item.status == '0'}">
		               	 	<ct:display model="sms_act_online_query" btn="audit_btn">
		                   	<a href="#" onclick="dealInfo('确定要审核吗？','operate/1/${item.actId}/list');">审核</a>
		                	</ct:display>
	                   	</c:if>
	                   	<c:if test="${item.status == '1'}">
		                   	<ct:display model="sms_act_online_query" btn="publish_btn">
			                <a href="#" onclick="dealInfo('确定要发布吗？','operate/2/${item.actId}/list');">发布</a>
			                </ct:display>
		                </c:if>
		                <c:if test="${item.status == '2'}">
			                <ct:display model="sms_act_online_query" btn="pause_btn">
			                <a href="#" onclick="dealInfo('确定要暂停吗？','operate/3/${item.actId}/list');">暂停</a>&nbsp;&nbsp;&nbsp;&nbsp;
			                </ct:display>
			                <ct:display model="sms_act_online_query" btn="down_line_btn">
			                <a href="#" onclick="dealInfo('确定要下线吗？','operate/4/${item.actId}/list');">下线</a>
			                </ct:display>
		                </c:if>                	
	                   	<c:if test="${item.status == '3'}">
		                   	<ct:display model="sms_act_online_query" btn="up_line_btn">
			                <a href="#" onclick="dealInfo('确定要恢复吗？','operate/0/${item.actId}/list');">恢复</a>
			                </ct:display>
	                   	</c:if>
	            	</c:if>
                </td>
            </tr>
            </c:forEach>            
        </table>

        <ht:page pageData="${onlinePage}" />

        </c:if>
        <c:if test="${empty onlinePage}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>
</div>
<script type="text/javascript">
$().ready(function() {	
	$(".view").click(function() {
			var id = $(this).attr("id");
			 location.href ="${ctx}/smsact/seconddevelop/online_view?id="+id	
	});
});
</script>
</body>
</html>