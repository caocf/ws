<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<%@ include file="../../includes/importer.jsp"%>
<html>
    <head>
        <ht:head/>
        
   <script type="text/javascript">
	$().ready(function() {
		$(".view").click(function() {
			var id = $(this).attr("id");
			showDialog("商品内容详细信息", "${ctx}/item/item/view?id="+id,function(doc){
			},{"Width":768,"Height":600,"ShowButtonRow" : true});			
	});
		$(".item_audit").click(function() {
			var id = $(this).attr("id");
			showDialog("发布商品审核", "${ctx}/item/item/auditing/"+id,null,{"Width":700,"Height":600,"ShowButtonRow" : false});
		});
		$(".item_view").click(function() {
			var id = $(this).attr("id");
			window.location = 'view/' +id;

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
    <form name="queryForm" id="queryForm" action="?" method="get">
    <input type="hidden" name="iseckillFlag" value="${iseckillFlag}" />
        <table>
            <tr>
            <td width="60">商品名称：</td>
             <td width="120"><input type="text" name="name" value="${param.name}" class="txt" style="width:100px"/></td>
            <td width="60">商品编号：</td>
             <td width="120"><input id="ordernumber" type="text" title="商品编号" name="id" value="${param.id}" class="txt validate-number" style="width:100px"/></td>
             
             <td width="60">创建时间：</td>
                <td >
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'saleStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="saleStartTime" id="saleStartTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'saleStopTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="saleStopTime" id="saleStopTime" value="${param.endTime}" />
                </td>
          </tr>
          <tr>
          <td width="60">商户编号：</td>
             <td width="120"><input type="text" title="商户编号" name="storeId" value="${param.storeId}" class="txt validate-number" style="width:100px"/></td>
             <td width="60">商户名称：</td>
             <td width="120"><input  type="text" name="storeName" value="${param.storeName}" class="txt" style="width:100px"/></td>
          
          
              
                <td colspan="2">&nbsp;
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>商品列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
               <th nowrap="nowrap">商品编号</th>
               <th nowrap="nowrap" width="200">名称</th>
                <th nowrap="nowrap" width="200">商户名称</th>
                <th nowrap="nowrap" width="80">商品类型</th>
                 <th nowrap="nowrap" width="80">商品分类</th>
                 <th nowrap="nowrap" width="80">品牌</th>
                 <th nowrap="nowrap" width="60">状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap" >${item.id}</td>
                <td nowrap="nowrap" class="ellipsis" >${item.name}</td>
                 <td  class="ellipsis">${item.storeName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.itemModeName}</td>
                
              	<td nowrap="nowrap" class="ellipsis">${item.typeName}</td>
              	<td nowrap="nowrap" class="ellipsis" >${item.brand}</td>
              	<td nowrap="nowrap" class="ellipsis">${item.statusName}</td>
              
                <td width="100" nowrap="nowrap">
                   
                   <ct:display model="audit_list" btn="view_btn">
                        <a href="view/${item.id}">查看</a>&nbsp;&nbsp;
                    </ct:display>
                    <ct:display model="audit_list" btn="preview_btn">
						<a href="#this" id="${item.id}" class="view">预览</a>&nbsp; &nbsp;
					</ct:display>
                      <c:if test="${item.status==1}">
                       <ct:display model="audit_list" btn="audit_btn">
                        <a href="${ctx}/item/item/auditing/${item.id}">审核</a>&nbsp;&nbsp;
	                   </ct:display>
                     </c:if>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${pageData}"/>
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