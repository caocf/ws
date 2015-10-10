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
    //关联协议
function chackSettlesStatus(el){
  
	if(el.attr("status")!='3'){
		simpleAlert("审核通过，才能关联协议！");
		return false;
	}
	return true;
}

    </script>
    </head>
<body>
<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
        <ct:display model="production_settle_list" btn="add_btn">
        <ht:menu-btn text="添加商品协议" url="/item/hisunproduction/settleAdd?id=" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td width="100">商品名称</td>
                <td width="300"><input type="text" name="productionname" value="${param.productionname}" class="txt" style="width:206px"/></td>
                <td width="100">商户编号</td>
                <td colspan="2"><input type="text" name="storeId" title="商户编号" value="${param.storeId}" class="txt validate-number"  style="width:206px"/></td>
            </tr>
            <tr>
                <td>产品有效期：</td> 	
				<td>
                    <input type="text" id="inputStartTimeS" name="inputStartTimeS" value="${param.inputStartTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'productionefftime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'productionexptime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="productionefftime" id="productionefftime" value="${param.productionefftime}"/>
                   	 至
                    <input type="text" id="inputEndTimeE" name="inputEndTimeE" value="${param.inputEndTimeE}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'productionexptime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'productionefftime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="productionexptime" id="productionexptime" value="${param.productionexptime}" />
                </td>
                <td>协议类型：</td>
                <td>
                    <select name="type">
                        <option value="">--请选择--</option>
                        <c:forEach items="${typeMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${param.type == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
                 <td width="70">状态：</td>
                <td width="300">
                    <select name="status">
                        <option value="">--请选择--</option>
                        <c:forEach items="${statusMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>商品协议资料列表</h3>

    <div class="mainbox">
        <c:if test="${not empty settlePage.data}">

        <table class="datalist fixwidth">
        	<colgroup>
        	<col width="100"></col>
        	<col width="200"></col>
        	<col width="150"></col>
        	<col width="80"></col>
        	<col width="80"></col>
        	<col width="70"></col>
        	<col width="70"></col>        	
        	<col width="70"></col>
        	<col width="120"></col>
       		</colgroup>
        	<tr>
	        	<td colspan="9" align="left">
				<ct:display model="production_settle_list" btn="sync_btn">
	        		<ht:table-action-btn text="同步清算系统" url="/item/hisunproduction/syncGy"/>
	        	</ct:display>
	        	<ct:display model="production_settle_list" btn="view_sync_btn">
	        		<ht:table-action-btn text="查询清算系统状态" url="/item/hisunproduction/syncGyQuery"/>
	        	</ct:display>
	        	</td>
        	</tr>
            <tr>
            	<th nowrap="nowrap" width="100"><input type="checkbox" id="all" />商品协议编号</th>
            	<th nowrap="nowrap" width="100" >商品名称</th>
            	<th nowrap="nowrap" width="150">产品有效期</th>
            	<th nowrap="nowrap" width="80">计费分类</th>
            	<th nowrap="nowrap" width="80">协议类型</th>
            	<th nowrap="nowrap" width="70">状态</th>
            	<th nowrap="nowrap" width="70">关联商品</th>
            	<th nowrap="nowrap" width="70">清算状态</th>
                <th nowrap="nowrap" width="270">操作</th>
            </tr>

            <c:forEach items="${settlePage.data}" var="item">
            <tr>
            	<td  nowrap="nowrap">
            	<c:if test="${item.status == 1 }" var="sta">
   					<input type="checkbox"  name="ids" value="${item.id }"/>
   				</c:if>
   				<c:if test="${!sta}">
   					<input type="checkbox"  name="ids" value="${item.id }" disabled="disabled" />
   				</c:if>
            	${item.id}
            	</td>
            	<td class="ellipsis"><a href="settleView?id=${item.id}">${item.productionname}</a></td>
                <td ><c:if test="${not empty item.productionefftime}"><ct:time source="${item.productionefftime}" tfmt="yyyy-MM-dd"/>~<ct:time source="${item.productionexptime}" tfmt="yyyy-MM-dd"/></c:if></td>
                <td class="ellipsis">${item.productiontypeName}</td>
                <td >${item.typeName}</td>
                <td >${item.statusName}</td>
                <td >
                	<c:if test="${item.isLink eq 1 }"><ct:display model="production_settle_list" btn="view_btn"><a href="itemSettleLink?id=${item.id}">查看</a></ct:display></c:if>
                	<c:if test="${item.isLink eq 0 }">无关联商品</c:if>
                </td>
                <td >${item.syncGyStatus1Name}</td>
                <td nowrap="nowrap">
                
	               	<!-- 和高阳已同步或同步成功的，不允许修改 -->
	                <c:choose>
		               	 <c:when test="${item.syncGyStatus1 eq 1 or  item.syncGyStatus1 eq 3}">
		               	 	<ct:display model="production_settle_list" btn="mod_btn">
		                		修改&nbsp;&nbsp;
		                	</ct:display>
		               	 </c:when>
		                 <c:otherwise>
		                 	<ct:display model="production_settle_list" btn="mod_btn">
	                			<a href="settleEdit?id=${item.id}">修改</a>&nbsp;&nbsp;
	                		</ct:display>	                	
		                	<c:if test="${item.status == 0}">
			                	<ct:display model="production_settle_list" btn="del_btn">
			                		<a href="#this" onclick="deleteItem('settleDelete/${item.id}');">删除</a>&nbsp;&nbsp;
			                	</ct:display>
		                	</c:if>
		                 </c:otherwise>
	                </c:choose>
	                
                </td>
            </tr>
            </c:forEach>
        </table>
        <ht:page pageData="${settlePage}" />

        </c:if>
        <c:if test="${empty settlePage.data}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>