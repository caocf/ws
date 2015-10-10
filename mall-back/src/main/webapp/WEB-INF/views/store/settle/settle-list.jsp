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
			showDialog("渠道商查看", 
			"storeView3?id="+id,function(doc){},
			{"Width":400,"Height":700,"ShowMessageRow":false,"ShowButtonRow":false});
			
	});
});
</script>
    </head>
<body>

<br/>
<div id="search-menu">
      <ul>
         <ct:display model="treaty_list" btn="add_btn">
          <ht:menu-btn text="添加结算信息" url="/store/storeSettle/settleAdd?storeId=${store.id }" type="add"/>
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>

<div class="container">
    <br/>
    <h3>商户结算信息列表</h3>
    <div class="mainbox">
        <c:if test="${not empty storeSettleList.data}">


        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap">编号</th>
                <th nowrap="nowrap">商户名称</th>
                <th nowrap="nowrap">生效日期</th>
                <th nowrap="nowrap">失效日期</th>
                <th nowrap="nowrap">银行名称</th>
                
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${storeSettleList.data}" var="item">
            <tr>
            	<td  nowrap="nowrap">${item.id}</td>
                <td  nowrap="nowrap">${item.name}</td>
               
               <td  nowrap="nowrap">
               <ct:time source="${item.effortDate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
                </td>
                <td  nowrap="nowrap">
               <ct:time source="${item.expiryDate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
                </td>
                
                <td  nowrap="nowrap">
               	${item.withdrawBankid}
                </td>
               
                <td width="100" nowrap="nowrap">
                  
	                <ct:display model="store_list" btn="view_btn">
	                        <a href="../settleView/${item.id}">查看</a>&nbsp;&nbsp;
	                        
	                </ct:display>
	                <ct:display model="store_list" btn="mod_btn">
	                        <a href="../settleEdit?id=${item.id}">修改</a>&nbsp;&nbsp;
	                        
	                </ct:display>
	                <ct:display model="store_list" btn="del_btn">
	                   <a href="#this" onclick="dealInfo('确认删除？','../settleDel/${item.id}');">删除</a>&nbsp;&nbsp;
	                        
	                </ct:display>
	             
                </td>
            </tr>
            </c:forEach>
      
        </table>
                 <input type="button" class="common_btn" onclick="history.back();" value="返回" />
        
        
       </c:if>
        <c:if test="${empty storeSettleList.data}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>