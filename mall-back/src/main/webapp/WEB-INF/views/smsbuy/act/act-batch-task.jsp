<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
	<script type="text/javascript">
  $().ready(function() {
		$(".item_view").click(function() {
			var id = $(this).attr("id");
			showDialog("短信群发配置", 
			"${ctx }/batch/smsmanagerlist?batchType=2&"+id,function(doc){},
			{"Width":1000,"Height":1000,"ShowMessageRow":false,"ShowButtonRow":false});
	});
	
	//var status = "${param.itemStatus}";
	//if (status == "") {
		//$("#online").attr("SELECTED","SELECTED");
	//}
}); 
		
		function peizhi(el){
			 var str=""; 
			 $("input[name='id']:checked").each(function(){  
			 	str+=$(this).val();  
			 })
			 window.location.href="${ctx }/smsbuy/act/actTaskAdd?actId="+str;
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
	          	 <td>活动编号：</td>
             	 <td><input type="text" name="actId" value="${param.actId}" class="txt validate-number" style="width:120px"/></td>
             	 
	             <td>活动名称：</td>
	             <td><input type="text" name="actName" value="${param.actName}" class="txt" style="width:120px"/></td>
	         </tr>
	         <tr>
	             <td>有效时间：</td>
	             <td>
	                 <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
	                        readOnly onclick="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
	                 <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
	                 至
	                 <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
	                        readOnly onclick="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
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
    <h3>活动群发管理</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">
        <table class="datalist fixwidth">
        	<ct:display model="act-batch-task" btn="massTask_btn">
	          	<tr>
			       	<td colspan="7" align="left">
			       	<ht:table-action-btn text="配置群发任务" url="/smsbuy/act/actTaskAdd" onAction="peizhi" />
			       	</td>
			    </tr>
		    </ct:display>
		     <colgroup>
			        	<col width="80"></col>
			        	<col width="120"></col>
			        	<col width="60"></col>
			        	<col width="80"></col>
			        	<col width="80"></col>
			        	<col width="50"></col>
			        	<col width="50"></col>
        			</colgroup>
            <tr>
                <th nowrap="nowrap">活动编号</th>
                <th nowrap="nowrap">活动名称</th>
                <th nowrap="nowrap">特服号</th>
                <th nowrap="nowrap" width="60">活动开始时间</th>
                 <th nowrap="nowrap" width="60">活动结束时间</th>
                <th nowrap="nowrap" width="60">群发任务数</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="act">
            <tr>
            	<td nowrap="nowrap">
            	<c:if test="${act.status == 'online'}" var="flg"><input type="radio" name="id" value="${act.actId }" /></c:if>
            	<c:if test="${!flg}"><input type="radio" name="id" value="" disabled="disabled" /></c:if>
            	${act.actId}
            	</td>
                <td  class="ellipsis">${act.actName}</td>
             	<td>${act.spCode}</td>
             	<td nowrap="nowrap"><ct:time source="${act.startTime}"/></td>
                <td nowrap="nowrap"><ct:time source="${act.endTime}"/></td>
                <td nowrap="nowrap">${act.taskCount}</td>
                <td nowrap="nowrap">
                 <ct:display model="act-batch-task" btn="view_btn">
                	 <a href="#this" id="actId=${act.actId}" class="item_view">查看</a>
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