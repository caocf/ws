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
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="list" method="get">
        <table>
            <tr>
                <td width="100">商户名称：</td>
                <td width="150"><input type="text" name="name" value="${param.name}" class="txt" style="width:150px"/></td>
                 <td width="100">商户编号：</td>
			    <td width="300"><input type="text" name="id" value="${param.id}"	 class="txt validate-number"  style="width:100px" />
                 <td width="80px">&nbsp;</td>
                </tr>
                <tr>
                <td>商户类型：</td>
                <td>
	              <select name="shopClass">
                        <option value="">--请选择--</option>
                        <c:forEach items="${storeClassMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.shopClass == item.key }">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>清算状态：</td>
                <td>
	               <select name="syncGyFlag">
                        <option value="">--请选择--</option>
                        <c:forEach items="${syncGyFlagMap }" var="item">
                         <option value="${item.key }" <c:if test="${param.syncGyFlag == item.key }">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                    <td></td>
                 </tr>
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
                <td width="70">创建时间：</td>
                <td />
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly
             onfocus="WdatePicker({vel:'beginTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2050-10-01\'}'})" />
                    <input type="hidden" name="beginTime" id="beginTime" value="${param.beginTime}"/>
                   	至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}"  class="txt Wdate"
                           readOnly 
             onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2050-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
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
    <h3>商户列表</h3><br />
    <div class="mainbox">
        <c:if test="${not empty pageData}">
        <table class="datalist fixwidth">
        <colgroup>
        	<col width="90"></col>
        	<col width="350"></col>
        	<col width="70"></col>
        	<col width="100"></col>
        	<col width="80"></col>
        	<col width="80"></col>
        	<col width=""></col>
        </colgroup>
        <tr>
        	<td colspan="7" align="left">
        	 <ct:display model="treaty_list" btn="sync_btn">
        		<ht:table-action-btn text="同步清算系统" url="/store/treaty/sync"/>
        	 </ct:display>
        	 <ct:display model="treaty_list" btn="view_sync_btn">
        	<ht:table-action-btn text="查询清算系统" url="/store/treaty/syncQuery"/>
        	 </ct:display>
        	</td>
        </tr>
            <tr>
             	<th nowrap="nowrap" width="80"><input type="checkbox" id="all" />商户编号</th>
                <th nowrap="nowrap" width="400">商户名称</th>
                <th nowrap="nowrap"  width="70">商户类型</th>
                <th nowrap="nowrap"  width="70">区域</th>
				<th nowrap="nowrap"  width="70">审核状态</th>
				<th nowrap="nowrap"  width="70">清算状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
               				<td nowrap="nowrap" >
               				<c:if test="${item.status == 3 }" var="sta">
               				<input type="checkbox"  name="ids" value="${item.id }"/>
               				</c:if>
               				<c:if test="${!sta}">
               				<input type="checkbox"  name="ids" value="${item.id }" disabled="disabled" />
               				</c:if>
               				${item.id}</td>
               				<td nowrap="nowrap" class="ellipsis"> <a href="${ctx }/store/store/storeView/${item.id}">${item.name }</a></td>
							<td nowrap="nowrap" >${item.shopClassName}</td>
							<td nowrap="nowrap" >${item.areaName}</td>
							<td nowrap="nowrap" >${item.statusName }</td>
							<td nowrap="nowrap" >${item.syncGyFlagName }</td>
                <td nowrap="nowrap">
                 <ct:display model="treaty_list" btn="settel_manage">
                 <a href="${ctx }/store/storeSettle/settleList/${item.id}">结算管理</a>&nbsp;&nbsp;
                 </ct:display>
                 <ct:display model="treaty_list" btn="fee_manage">
                 <a href="${ctx }/store/storeFee/feeList/${item.id}">费率管理</a>&nbsp;&nbsp;
                 </ct:display>
                   
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