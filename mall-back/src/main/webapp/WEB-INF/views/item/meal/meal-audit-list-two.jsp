<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<%@ include file="../../includes/importer.jsp"%>
<html>
    <head>
        <ht:head/>
        
   <script type="text/javascript">

	
	$().ready(function() {

		$(".item_audit").click(function() {
			var id = $(this).attr("id");
			showDialog("商品终审", "${ctx}/item/meal/twoAuditing/"+id,function(doc){
				commonSubmit(doc.getElementById('fm'));
				//doc.getElementById('fm').submit();
				//doc.submited=true;
				simpleAlert('操作成功',function() {
					window.location.reload();
				});
			},{"Width":600,"Height":270,"ShowMessageRow":false});
			

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
        <table>
            <tr>
           
            <td width="60">套餐名称：</td>
             <td width="120"><input type="text" name="name" value="${param.name}" class="txt" style="width:100px"/></td>
           <td width="60">套餐编号：</td>
             <td width="120"><input type="text" name="id" title="套餐编号" value="${param.id}" class="txt validate-number" style="width:100px"/></td>
          
			<td width="60">创建时间：</td>
                <td >
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'saleStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="saleStartTime" id="saleStartTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}"  class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'saleStopTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="saleStopTime" id="saleStopTime" value="${param.endTime}" />
                </td>
            
                <td>
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>套餐商品列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
               <th nowrap="nowrap" width="100">套餐编号</th>
               <th nowrap="nowrap" width="400">套餐名称</th>
                
                 <th nowrap="nowrap" width="60">状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap" >${item.id}</td>
                <td nowrap="nowrap" class="ellipsis">${item.name}</td>
               
              	<td nowrap="nowrap">${item.statusName}</td>
                <td width="100" nowrap="nowrap">
                   
                   <ct:display model="two_meal_audit" btn="view_btn">
                        <a href="mealView/${item.id}">查看</a>
                    </ct:display>
                      <c:if test="${item.status==2}">
                      &nbsp; &nbsp;
                       <ct:display model="two_meal_audit" btn="audit_btn">
                        <a href="${ctx}/item/meal/twoAuditing/${item.id}">审核</a>
	                	&nbsp;&nbsp;
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