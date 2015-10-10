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
			window.location = "${ctx}/store/channel/channelView?id="+id;
			
	});
		

		$(".item_audit").click(function() {
			var id = $(this).attr("id");
			showDialog("审核结算信息", "./auditSettlePage?id="+id,function(doc){
				if(doc.getElementById('fm')!=null){
				commonSubmit(doc.getElementById('fm'));
				//doc.getElementById('fm').submit();
				//doc.submited=true;
				simpleAlert('操作成功',function() {
					window.location.reload();
				});
			}
			},{"Width":800,"Height":700,"ShowMessageRow":false,"ShowButtonRow":false});
			

		});
		
		
});
</script>
    </head>
<body>

<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
         <ct:display model="store_list" btn="add_btn">
          <ht:menu-btn text="添加代理结算商户" url="/store/store/agentAdd?storeId=${storeId}" type="add"/>
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="" method="get">
    <input type="hidden" name="storeId" value="${storeId }" />
        <table>
            
                <tr>
                <td>审核状态：</td>
                <td>
	               <select name="status">
                        <option value="">--请选择--</option>
                       <c:forEach items="${statusMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.status == item.key }">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
                <td colspan="1">&nbsp;
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>代理结算商户列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">


        <table class="datalist fixwidth">
            <tr>
             	<th nowrap="nowrap" width="180">渠道商名称</th>
                <th nowrap="nowrap" width="180">结算商户名称</th>
              	<th nowrap="nowrap" width="150">开始时间</th>
               	<th nowrap="nowrap" width="150">结束时间</th>
               	<th nowrap="nowrap" width="80">折扣商户代理</th>
               	<th nowrap="nowrap" width="80">商品销售代理</th>
				<th nowrap="nowrap"  width="100">状态</th>
				
				
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
               				<td nowrap="nowrap">${item.channelName}</td>
               				<td nowrap="nowrap" >${item.storeName}</td>
							<td nowrap="nowrap" ><ct:time source="${item.startTime}"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd HH:mm:ss"/></td>
							<td nowrap="nowrap" ><ct:time source="${item.stopTime} "  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd HH:mm:ss"/></td>
							<td nowrap="nowrap" >${item.shopAgentFlagName }</td>
							<td nowrap="nowrap" >${item.goodsAgentFlagName }</td>
							<td nowrap="nowrap" >${item.statusName }</td>
                <td nowrap="nowrap">
                
	                 <ct:display model="store_list" btn="del_btn">
	                      <a href="#this" onclick="deleteItem('agentDel/${item.id}');">删除</a>
	                </ct:display>
	               <c:if test="${item.status == 0 }">
	                 <ct:display model="store_list" btn="audit_btn">
	                <a href="#" onclick="dealInfo('确认审核通过？','agentAudit/${item.id}/pass');">审核通过</a>
	                </ct:display>
	                </c:if>
	                <c:if test="${item.status == 0 }">
	                 <ct:display model="store_list" btn="audit_btn">
	                <a href="#" onclick="dealInfo('确认审核驳回？','agentAudit/${item.id}/back');">审核驳回</a>
	                </ct:display>
	                </c:if>
	               
	              
					  
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