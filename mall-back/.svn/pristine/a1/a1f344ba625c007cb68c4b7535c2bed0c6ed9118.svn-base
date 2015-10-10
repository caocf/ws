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
	
	var status = "${param.itemStatus}";
	if (status == "") {
		$("#online").attr("SELECTED","SELECTED");
	}
}); 
		
		function peizhi(el){
			 var str=""; 
			 $("input[name='id']:checked").each(function(){  
			 	str+=$(this).val();  
			 })
			 window.location.href="${ctx }/batch/smsbuy/add?routerId="+str;
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
             <td><input type="text" name="actId" value="${param.actId}" class="txt" style="width:120px"/></td>
                
             <td>活动名称：</td>
             <td><input type="text" name="actName" value="${param.actName}" class="txt" style="width:120px"/></td>
            
             
            <td width="100">商品审核状态：</td>
           	<td width="100"><select name="itemStatus" id="itemStatus">
							<option value="0">--请选择--</option>
							<c:forEach items="${statusMap}" var="item" varStatus="index">
								<option value="${item.key}" id="${item.key}"
									<c:if test="${item.key == param.itemStatus}">selected="selected"</c:if>>${item.value}
								</option>
							</c:forEach>
					</select>
					</td>
            </tr>
            
            <tr>
           <td>商品编号：</td>
             <td><input type="text" name="itemId" value="${param.itemId}" class="txt" style="width:120px"/></td>
             
             
             	<td align="right">
							商品名称：
						</td>
						<td>
							<input type="text" name="itemName" value="${param.itemName}"
								class="txt" style="width: 120px" />
						</td>
						<td width="70">有效时间：</td>
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
    <h3>短信购群发列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
        
        
          <tr>
               	
		       	<td colspan="10" align="left">
		       	 <ct:display model="act-batch-task" btn="massTask_btn">
		       		<ht:table-action-btn text="配置群发任务" url="/batch/smsbuy/add" onAction="peizhi" />
		       	 </ct:display>
		       	</td>
		       	
		    </tr>
		     <colgroup>
			        	<col width="80"></col>
			        	<col width="100"></col>
			        	<col width="80"></col>
			        	<col width="80"></col>
			        	<col width="80"></col>
			        	<col width="120"></col>
			        	<col width="60"></col>
			        	<col width="60"></col>
			        	<col width="60"></col>
			        	<col width="80"></col>
        			</colgroup>
            <tr>
            	<th nowrap="nowrap">商品编号</th>
                <th nowrap="nowrap">商品名称</th>
                <th nowrap="nowrap">活动编号</th>
                <th nowrap="nowrap">活动名称</th>
                <th nowrap="nowrap">指令内容</th>
                <th nowrap="nowrap">特服号</th>
                <th nowrap="nowrap" width="60">支付方式</th>
                <th nowrap="nowrap" width="60">状态</th>
                <th nowrap="nowrap" width="60">群发任务数</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td nowrap="nowrap">
            	<c:if test="${item.itemStatus == 'online'}" var="flg"><input type="radio" name="id" value="${item.id }" /></c:if>
            	<c:if test="${!flg}"><input type="radio" name="id" value="" disabled="disabled" /></c:if>
            	${item.itemId}
            	</td>
                <td  class="ellipsis">${item.itemName}</td>
             	<td>${item.actId}</td>
             	<td nowrap="nowrap">${item.actName}</td>
                <td class="ellipsis">${item.command}</td>
                <td nowrap="nowrap">${item.spCode}</td>
	             <td nowrap="nowrap">${item.payTypeName}</td>
	             <td nowrap="nowrap">${item.itemStatusName}</td>
	             <td nowrap="nowrap">${item.taskCount}</td>
               <td nowrap="nowrap">
               <ct:display model="act-batch-task" btn="view_btn">
                	 <a href="#this" id="actId=${item.actId}&itemId=${item.itemId}&routerId=${item.id}" class="item_view">查看</a>
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