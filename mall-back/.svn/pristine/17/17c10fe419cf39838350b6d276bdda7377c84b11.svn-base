<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>	
<html>
<head>
	<ht:head/>
	<script type="text/javascript">
		$().ready(function() {
		});
		function orderList(el){
			 if($("input[name='actId']:checked").length == 0 ){
				 alert("请选择查看的项");
				 return false;
			 }
			 var str=""; 
			 $("input[name='actId']:checked").each(function(){  
			 	str+=$(this).val();  
			 })
		//	 window.location.href="${ctx}/order/statisticaOrderList/"+str/+5;
			window.location.href="${ctx}/order/statisticaOrderList/"+str+"/5";
		}
		
	</script>
</head>
<body>
<br/>
<div id="search-menu">
    <ul>
    <ht:menu-btn type="search"/>
      <ct:display model="smsbuy_act_list" btn="add_btn">
        <ht:menu-btn text="创建活动配置" url="/smsbuy/act/preAdd" type="add"/>
      </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
             <td>活动名称：</td>
             <td><input type="text" name="actName" value="${param.actName}" class="txt" style="width:120px"/></td>
             
                <td width="70">状态：</td>
                <td width="200">
                    <select name="status">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${statusMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                
                <td width="70">有效时间：</td>
                <td width="300">
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
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
    <h3>短信购活动列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
        <colgroup>
			        	<col width="80"></col>
			        	<col width="140"></col>
			        	<col width="120"></col>
			        	<col width="140"></col>
			        	<col width="140"></col>
			        	<col width="80"></col>
			        	<col width="150"></col>
        			</colgroup>
        	<ct:display model="smsbuy_act_list" btn="order_btn">
			    <tr>
			       	<td colspan="7" align="left">
			       	<ht:table-action-btn text="查看订单" url="order/statisticaOrderList" onAction="orderList" />
			       	</td>
			    </tr>
		    </ct:display>
            <tr> 
                <th nowrap="nowrap">活动编号</th>
                <th nowrap="nowrap">活动名称</th>
                <th nowrap="nowrap">特服号</th>
                <th nowrap="nowrap">活动开始时间</th>
                <th nowrap="nowrap">活动结束时间</th>
                <th nowrap="nowrap">活动状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>
			
            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td><input type="radio" name="actId" value="${item.actId }" />${item.actId}</td>
                <td class="ellipsis"><a href="./viewAct?actId=${item.actId}">${item.actName}</a></td>
                <td>${item.spCode}</td>
                <td><ct:time source="${item.startTime}"/></td>
                <td><ct:time source="${item.endTime}"/></td>
                <td>${item.statusName}</td>
                <td>
                <c:if test="${item.status == 'online'}">
	                <ct:display model="smsbuy_act_list" btn="smsbuy_item_manage">
	                	<a href="./itemRouterList?actId=${item.actId}">商品管理</a>
	                </ct:display>
                </c:if>
                <ct:display model="smsbuy_act_list" btn="mod_btn">
                <a href="./preEditAct?actId=${item.actId}">修改</a>
                </ct:display>
                
                <ct:display model="smsbuy_act_list" btn="del_btn">
                	<a href="#" onclick="dealInfo('确定要删除活动以及活动下的所有指令吗？','deleteAct?actId=${item.actId }')">删除</a>
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