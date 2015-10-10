<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>

  <head>
 <ht:head/>
 <script type="text/javascript">
	$().ready(function() {
		$("#all").click(function() {
			if($("#all").attr("checked")){
				$("input[type=checkbox]").attr("checked","checked");
				$("input[type=checkbox]:checked").each(function(){
			        if($(this).attr("disabled") == "disabled") {
			        	$(this).removeAttr("checked");
			        }
			    })
			}else{
				$("input[type=checkbox]").removeAttr("checked");
			}
		});
});
</script>
    </head>
<body>

<br/>
<div id="search-menu">
      <ul>
         <ht:menu-btn type="search"/>
         <ct:display model="treaty_list" btn="add_btn">
         </ct:display>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="" method="get">
        <table>
        
            <tr>
                <td width="100">商户名称：</td>
                <td width="150"><input type="text" name="name" value="${param.name}" class="txt" style="width:150px"/></td>
                
                 <td width="100">商户编号：</td>
                <td width="150"><input type="text" name="storeId" value="${param.storeId}"  class="txt validate-number"  style="width:150px"/></td>
                
                <td width="100">商户结算编号：</td>
			    <td width="100"><input type="text" name="id" value="${param.id}"	class="txt validate-number"  style="width:100px" />
                <td></td>
                <td></td>
                </tr>
                <tr>
                
                <td>清算状态：</td>
                <td>
	               <select name="syncGyFlag">
                        <option value="">--请选择--</option>
                        <c:forEach items="${syncGyFlagMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.syncGyFlag == item.key }">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
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
    <h3>商户列表</h3>
    
    <div class="mainbox">
        <c:if test="${not empty pageData.data}">


        <table class="datalist fixwidth">
         <colgroup>
        	<col width="120"></col>
        	<col width="200"></col>
        	<col width="100"></col>
        	<col width="100"></col>
        	<col width="80"></col>
        	<col width="80"></col>
        	<col width="80"></col>
        </colgroup>
        <tr>
        	<td colspan="7" align="left">
        	<ct:display model="store_settle_list" btn="sync_btn">
        		<ht:table-action-btn text="同步清算系统" url="/store/storeSettle/sync"/>
        	</ct:display>
        	<ct:display model="store_settle_list" btn="view_sync_btn">
        		<ht:table-action-btn text="查询清算系统" url="/store/storeSettle/syncQuery"/>
        	</ct:display>
        	</td>
        </tr>
            <tr>
            	<th nowrap="nowrap"><input type="checkbox" id="all" />商户结算编号</th>
                <th nowrap="nowrap">商户名称</th>
                <th nowrap="nowrap">生效日期</th>
                <th nowrap="nowrap">失效日期</th>
                <th nowrap="nowrap">审核状态</th>
                <th nowrap="nowrap">清算状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td  nowrap="nowrap">
            	<c:if test="${item.status == 3 }" var="sta">
   					<input type="checkbox"  name="ids" value="${item.id }"/>
   				</c:if>
   				<c:if test="${!sta}">
   					<input type="checkbox"  name="ids" value="${item.id }" disabled="disabled" />
   				</c:if>
            	${item.id}</td>
                <td  nowrap="nowrap" class="ellipsis">${item.name}</td>
               
               <td  nowrap="nowrap">
               <ct:time source="${item.effortDate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
                </td>
                <td  nowrap="nowrap">
               <ct:time source="${item.expiryDate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
                </td>
                
                <td  nowrap="nowrap">${item.statusName}</td>
                <td  nowrap="nowrap">${item.syncGyFlagName}</td>
               
                <td width="100" nowrap="nowrap">
                  
	                <ct:display model="store_settle_list" btn="view_btn">
	                        <a href="${ctx }/store/storeSettle/settleView/${item.id}">查看</a>&nbsp;&nbsp;
	                        
	                </ct:display>
	                <c:if test="${item.status == 0 }">
	                <ct:display model="store_settle_list" btn="audit_btn">
	                        <a href="${ctx }/store/storeSettle/auditPage/${item.id}">审核</a>&nbsp;&nbsp;
	                        
	                </ct:display>
	                </c:if>
	             
                </td>
            </tr>
            </c:forEach>
        </table>
          <ht:page pageData="${shopPage}" />
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