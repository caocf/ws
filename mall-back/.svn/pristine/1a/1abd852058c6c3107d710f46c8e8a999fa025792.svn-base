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
                <td width="150"><input type="text" name="storeId" value="${param.storeId}"  class="txt validate-number" style="width:150px"/></td>
                
                <td width="100">商户费率编号：</td>
			    <td width="100"><input type="text" name="id" value="${param.id}"	class="txt validate-number"  style="width:100px" />
                </tr>
                
                <tr>
                <td>现金清算状态：</td>
                <td>
	               <select name="syncGyFlag1">
                        <option value="">--请选择--</option>
                       <c:forEach items="${syncGyFlagMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.syncGyFlag1 == item.key }">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>商城币清算状态：</td>
                <td>
	               <select name="syncGyFlag2">
                       <option value="">--请选择--</option>
                       <c:forEach items="${syncGyFlagMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.syncGyFlag2 == item.key }">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>积分清算状态：</td>
                <td>
	               <select name="syncGyFlag3">
                        <option value="">--请选择--</option>
                       <c:forEach items="${syncGyFlagMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.syncGyFlag3 == item.key }">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
            	</tr>
                <tr>
                <td>话费清算状态：</td>
                <td>
	               <select name="syncGyFlag4">
                        <option value="">--请选择--</option>
                       <c:forEach items="${syncGyFlagMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.syncGyFlag4 == item.key }">selected="selected"</c:if>>${item.value }</option>
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
        	<col width="100"></col>
        	<col width="100"></col>
        	<col width="100"></col>
        	<col width="90"></col>
        	<col width="90"></col>
        	<col width="100"></col>
        	<col width="100"></col>
        	<col width="100"></col>
        	<col width="100"></col>
        	<col width="150"></col>
        </colgroup>
        
         <tr>
        	<td colspan="11" align="left">
        	<ct:display model="store_fee_list" btn="sync_btn">
        		<ht:table-action-btn text="同步清算系统" url="/store/storeFee/sync"/>
        	</ct:display>
        	<ct:display model="store_fee_list" btn="view_sync_btn">
        		<ht:table-action-btn text="查询清算系统" url="/store/storeFee/syncQuery"/>
        	</ct:display>
        	</td>
        </tr>
            <tr>
            	<th nowrap="nowrap"><input type="checkbox" id="all" />商户费率编号</th>
                <th nowrap="nowrap" >商户名称</th>
                <th nowrap="nowrap" >费率名称</th>
                <th nowrap="nowrap">生效日期</th>
                <th nowrap="nowrap">失效日期</th>
                <th nowrap="nowrap">审核状态</th>
                <th nowrap="nowrap">现金清算状态</th>
                <th nowrap="nowrap">商城币清算状态</th>
                <th nowrap="nowrap">积分清算状态</th>
                <th nowrap="nowrap">话费清算状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td >
            	<c:if test="${item.status == 3 }" var="sta">
   					<input type="checkbox"  name="ids" value="${item.id }"/>
   				</c:if>
   				<c:if test="${!sta}">
   					<input type="checkbox"  name="ids" value="${item.id }" disabled="disabled" />
   				</c:if>
            	${item.id}</td>
                <td class="ellipsis">${item.name}</td>
                <td class="ellipsis">${item.productionTypeName}</td>
               <td  >
               <ct:time source="${item.effortdate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
                </td>
                <td >
               <ct:time source="${item.expirydate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
                </td>
                
                <td  >${item.statusName}</td>
                <td  >${item.syncGyFlag1Name}</td>
                <td  >${item.syncGyFlag2Name}</td>
                <td  >${item.syncGyFlag3Name}</td>
                <td  >${item.syncGyFlag4Name}</td>
                <td >
                  
	                <ct:display model="store_fee_list" btn="view_btn">
	                        <a href="${ctx }/store/storeFee/feeView/${item.id}">查看</a>&nbsp;&nbsp;
	                        
	                </ct:display>
	                <c:if test="${item.status == 0 }">
	                <ct:display model="store_fee_list" btn="audit_btn">
	                        <a href="${ctx }/store/storeFee/auditPage/${item.id}">审核</a>&nbsp;&nbsp;
	                        
	                </ct:display>
	                </c:if>
	                <c:if test="${item.capitalType4 == 1 && item.phoneEffortdate != null && item.status == 3}">
	                <ct:display model="store_fee_list" btn="sync_btn">
	                 	<a href="#" onclick="dealInfo('确认同步话费？','${ctx }/store/storeFee/syncPhoneCharge/${item.id}');">同步话费</a> &nbsp;&nbsp;
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