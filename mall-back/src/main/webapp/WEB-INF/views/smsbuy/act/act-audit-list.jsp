<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>	
<html>
<head>
	<ht:head/>
	<script type="text/javascript">
		function deleteAct(url,customCallBack){ 
			dealInfo("确定要删除活动以及活动下的所有指令吗？",url,customCallBack);
		}
	</script>
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
                
             <td>活动名称：</td>
             <td><input type="text" name="actName" value="${param.actName}" class="txt" style="width:120px"/></td>
             
                <td width="70">状态：</td>
                <td width="200">
                
                    <select name="status">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${statusMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
                <td width="70">有效时间：</td>
                <td width="300">
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
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
    <h3>短信购活动列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">活动编号</th>
                <th nowrap="nowrap">活动名称</th>
                <th nowrap="nowrap">特服号</th>
                <th nowrap="nowrap">活动开始时间</th>
                <th nowrap="nowrap">活动结束时间</th>
                <th nowrap="nowrap">活动状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.actId}</td>
                <td nowrap="nowrap" class="ellipsis">${item.actName}</td>
                <td>${item.spCode}</td>
                <td nowrap="nowrap"><ct:time source="${item.startTime}"/></td>
                <td nowrap="nowrap"><ct:time source="${item.endTime}"/></td>
                <td nowrap="nowrap">${item.statusName}</td>
                <td nowrap="nowrap">
	                <ct:display model="smsbuy_act_audit_list" btn="view_btn">
	                	<a href="./viewAct?actId=${item.actId}">查看</a>
	                </ct:display>
	                <c:if test="${item.status == null || item.status == 'audit'}">
		                <ct:display model="smsbuy_act_audit_list" btn="audit_btn">
		                  <a href="#" onclick="dealInfo('确认审核通过？','auditAct?actId=${item.actId}');">审核通过</a>
		                  <a href="#" onclick="dealInfo('确认审核驳回？','rebutAudit?actId=${item.actId}');">驳回</a>
		                </ct:display>
	                </c:if>
	                <c:if test="${item.status != 'audit'}">
		                <ct:display model="smsbuy_act_audit_list" btn="down_line_btn">
		                  <a href="#" onclick="dealInfo('确认下线？','downAudit?actId=${item.actId}');">下线</a>
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