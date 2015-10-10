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


  function isDigit(value) 
  { 
  var patrn=/^[0-9]{0,20}$/; 
    if (!patrn.exec(value)){ 
        alert("请输入正确的编号");
    return false}{ 
  return true 
  }
} 


$(function (){$("#queryForm").submit(function(){
    		var isPass = true;
    		isPass = isDigit($("#ordernumber").val());
    		return isPass;
    	})});
</script>
    </head>
<body>

<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
         <ct:display model="non_store_list" btn="add_btn">
            <ht:menu-btn text="添加商户" url="/store/store/nonStoreAdd" />
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="" method="get">
    <input type="hidden" name="shopClass" value=${param.shopClass }>
        <table>
            <tr>
                <td width="100">商户名称：</td>
                <td width="150"><input type="text" name="name" value="${param.name}" class="txt" style="width:150px"/></td>
                 <td width="100">商户编号：</td>
			    <td width="150"><input id="ordernumber" type="text" name="id" value="${param.id}"	class="txt" style="width:150px" />
                 <td width="80px">&nbsp;</td>
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
    <h3>非结算商户列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">


        <table class="datalist fixwidth">
            <tr>
             	<th nowrap="nowrap" width="80">商户编号</th>
                <th nowrap="nowrap" width="300">商户名称</th>
                <th nowrap="nowrap"  width="100">区域</th>
				<th nowrap="nowrap"  width="100">商户状态</th>
				<th nowrap="nowrap"  width="150">创建时间</th>
				<th nowrap="nowrap"  width="150">审核时间</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td nowrap="nowrap">${item.id}</td>
            	<td nowrap="nowrap" class="ellipsis"> <a href="noneStoreView/${item.id}">${item.name }</a></td>
				<td nowrap="nowrap" >${item.areaName}</td>
				<td nowrap="nowrap" >${item.statusName }</td>
				<td nowrap="nowrap" >
				<ct:time source="${item.createTime}" tfmt="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td nowrap="nowrap" >
				<c:choose>
					<c:when test="${item.status ==3 or item.status==4 }">
					<ct:time source="${item.auditTime}" />
					</c:when>
					<c:otherwise>
					无
					</c:otherwise>
				</c:choose>
				</td>
                <td nowrap="nowrap">
	                <ct:display model="non_store_list" btn="mod_btn">
	                        <a href="nonStoreEdit?id=${item.id}">修改</a>
	                </ct:display>
	                <c:if test="${item.status == 0 || item.status == 4 }">
	                 <ct:display model="non_store_list" btn="del_btn">
	                      <a href="#this" onclick="deleteItem('nonStoreDel/${item.id}');">删除</a>
	                </ct:display>
	               <c:if test="${item.status == 0 }">
	                 <ct:display model="non_store_list" btn="audit_btn">
	                <a href="#" onclick="dealInfo('确认送审？','nonAudit/${item.id}');">送审</a>
	                </ct:display>
	                </c:if>
	                </c:if>
	               
	               
					  <c:if test="${item.status==3 }">
					 <ct:display model="non_store_list" btn="to_settle_store_btn">
					  <a href="storeEdit?id=${item.id}">转为结算商户</a>&nbsp;&nbsp;
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