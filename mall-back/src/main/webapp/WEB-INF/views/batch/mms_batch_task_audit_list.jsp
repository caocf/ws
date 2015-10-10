<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
        <script type="text/javascript">
	$().ready(function() {
		$(".item_audit").click(function() {
			var id = $(this).attr("id");
			showDialog("MMS群发任务审核", "${ctx}/batch/auditMms/"+id,null,{"Width":700,"Height":600,"ShowButtonRow" : false});
		});
	});
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
    <form name="queryForm" id="queryForm" action="mmsauditlist" method="get">
        <table>
            <tr>
                
                <td>任务名称：</td>
                <td><input type="text" name="taskName" value="${param.taskName}" class="txt" style="width:206px"/></td>
               
                <td>任务状态：</td>
                <td>
	               <select name="status">
                        <option value="">--请选择--</option>
                        <option value="0" <c:if test="${param.status == 0}">selected="selected"</c:if>>未审核</option>
                        <option value="1" <c:if test="${param.status == 1}">selected="selected"</c:if>>已审核</option>
                        <option value="2" <c:if test="${param.status == 2}">selected="selected"</c:if>>审核不通过</option>
                        <option value="3" <c:if test="${param.status == 3}">selected="selected"</c:if>>暂停</option>
                        <option value="4" <c:if test="${param.status == 4}">selected="selected"</c:if>>发送中</option>
                        <option value="5" <c:if test="${param.status == 5}">selected="selected"</c:if>>发送完毕</option>
                        <option value="6" <c:if test="${param.status == 6}">selected="selected"</c:if>>发送失败</option>
                        <option value="7" <c:if test="${param.status == 7}">selected="selected"</c:if>>任务预处理</option>
                        <option value="8" <c:if test="${param.status == 8}">selected="selected"</c:if>>预处理完成，等待发送</option>
                        <option value="9" <c:if test="${param.status == 9}">selected="selected"</c:if>>取消</option>
                    </select>
                </td>
                 </tr>
                <tr>
                <td>彩信标题：</td>
                <td><input type="text" name="title" value="${param.title}" class="txt" style="width:206px"/></td>
                <td width="70">创建时间：</td>
                <td width="300"/>
                     <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime == null ? inputStartTime : param.inputStartTime}"  class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime == null ? inputStartTime : param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime == null ? inputEndTime : param.inputEndTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime == null ? inputEndTime : param.endTime}" />
                </td>
                 </tr>
                <tr>
                <td>申请人：</td>
                <td><input type="text" name="userName" value="${param.userName}" class="txt" style="width:206px"/></td>
                
                <td colspan="4">&nbsp;
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>MMS群发任务审核列表</h3>
    <div class="mainbox">
        <c:if test="${not empty taskPage}">


        <table class="datalist fixwidth">
            <tr>
                 <th nowrap="nowrap" width="80">群发任务编号</th>
                <th nowrap="nowrap"  width="200">任务名称</th>
                <th nowrap="nowrap" width="200">彩信标题</th>
                <th nowrap="nowrap" width="150">创建时间</th>
                <th nowrap="nowrap" width="100">创建人</th>
                <th nowrap="nowrap" width="100">任务状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${taskPage.data}" var="item">
            <tr>
               <td  nowrap="nowrap">${item.id}</td>
                <td  nowrap="nowrap"  class="ellipsis">${item.taskName}</td>
                <td nowrap="nowrap"  class="ellipsis">${item.title} </td>
                <td nowrap="nowrap" width="100"><ct:time source="${item.createTime}"/></td>
                <td width="50" nowrap="nowrap">${item.userName}</td>
                <td width="50" nowrap="nowrap">${item.statusName}</td>
                <td width="100" nowrap="nowrap">
                <ct:display model="mms_audit_list" btn="view_btn">
                        <a href="viewMms/auditList/${item.id}">查看</a>
                    </ct:display>
                    &nbsp;&nbsp;
                 <c:if test="${item.status==0}">
                    <ct:display model="mms_audit_list" btn="audit_btn">
                        <a href="#" id="${item.id}" class="item_audit">审核</a>&nbsp;&nbsp;
                    </ct:display>
                    &nbsp;&nbsp;
                   </c:if> 
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${taskPage}" />

        </c:if>
        <c:if test="${empty taskPage}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>