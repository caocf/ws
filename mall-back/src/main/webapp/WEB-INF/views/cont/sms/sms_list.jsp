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
        <ct:display model="cont_sms_list" btn="add_btn">
        <ht:menu-btn text="录入短信内容" url="/cont/sms/add" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td width="70">内容源：</td>
                <td width="300"><input type="text" name="srcName" value="${param.srcName}" class="txt" style="width:206px"/></td>
                <td width="70">录入单位：</td>
                <td width="300"><input type="text" name="unitName" value="${param.unitName}" class="txt" style="width:206px"/></td>
            </tr>
            <tr>
                <td>关键字：</td>
                <td><input type="text" name="keyword" value="${param.keyword}" class="txt" style="width:206px"/></td>
                <td>信息内容：</td>
                <td><input type="text" name="content" value="${param.content}" class="txt" style="width:206px"/></td>
            </tr>
            <tr>
            	<td width="70">状态：</td>
                <td width="300">
                   <select name="status">
                        <option value="">--请选择--</option>
                        <c:forEach items="${statusMap}" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
            	<td>有效期：</td> 	
				<td>
                    <input type="text" id="inputStartTimeS" name="inputStartTimeS" value="${param.inputStartTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTimeS\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                   	 至
                    <input type="text" id="inputEndTimeS" name="inputEndTimeS" value="${param.inputEndTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTimeS\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
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
    <h3>短信业务列表</h3>

    <div class="mainbox">
        <c:if test="${not empty contSmsPage}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="50">短信编号</th>
                <th nowrap="nowrap" width="60">内容源</th>
                <th nowrap="nowrap" width="150">内容</th>
                <th nowrap="nowrap" width="160">有效期</th>
                <th nowrap="nowrap" width="120">录入单位</th>
                <th nowrap="nowrap" width="80">状态</th>
                <th nowrap="nowrap" width="100">操作</th>
            </tr>
			
            <c:forEach items="${contSmsPage.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap">${item.srcName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.content}</td>
                <td nowrap="nowrap"><ct:time source="${item.startTime}"/>~<ct:time source="${item.endTime}"/></td>
                <td nowrap="nowrap" class="ellipsis">${item.unitName}</td>
                <td nowrap="nowrap"><span <c:if test="${item.status eq 2}">title="${item.advice }"</c:if>>${item.statusName}</span></td>
                <td nowrap="nowrap">
                	<ct:display model="cont_sms_list" btn="view_btn">
                	<a href="#this" id="${item.id}" class="view">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                	</ct:display>
                   	<c:if test="${item.status == '0'}">
                   	<ct:display model="cont_sms_list" btn="mod_btn">
                   	<a href="./edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                   	</ct:display>
                   	<ct:display model="cont_sms_list" btn="audit_btn">
                   	<a href="./sms_auditing?id=${item.id}">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;
                   	</ct:display>
                   	<ct:display model="cont_sms_list" btn="del_btn">
                   	<a href="#" onclick="deleteItem('./delete/${item.id}');">删除</a>
                   	</ct:display>
                   	</c:if>
                   	<c:if test="${item.status == '2'}">
                   	<ct:display model="cont_sms_list" btn="mod_btn">
                   	<a href="./edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                   	</ct:display>
<%--                    	<ct:display model="cont_sms_list" btn="audit_btn"> --%>
<%--                    	<a href="./sms_auditing?id=${item.id}">审核</a>&nbsp;&nbsp;&nbsp;&nbsp; --%>
<%--                    	</ct:display> --%>
                   	<ct:display model="cont_sms_list" btn="del_btn">
                   	<a href="#" onclick="deleteItem('./delete/${item.id}');">删除</a>
                   	</ct:display>
                   	</c:if>
                </td>
            </tr>
            </c:forEach>            
        </table>

        <ht:page pageData="${contSmsPage}" />

        </c:if>
        <c:if test="${empty contSmsPage}">
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
			location.href ="${ctx}/cont/sms/view?id="+id;
			//showDialog("短信内容详细信息", "${ctx}/cont/sms/view?id="+id,function(doc){
			//},{"Width":400,"Height":500,"ShowButtonRow" : false});			
	});
});
</script>
</body>
</html>