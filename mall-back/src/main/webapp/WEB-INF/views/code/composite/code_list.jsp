<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>

  <head>
 <ht:head/>

<script type="text/javascript">

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
                <td width="70">验证码：</td>
                <td width="300"><input type="text" name="code" value="${param.code}" class="txt" style="width:206px"/></td>
                <td width="70">手机号码：</td>
                <td width="300"><input type="text" name="terminalId" value="${param.terminalId}" class="txt" style="width:206px"/></td>
                <td width="70">订单编号：</td>
                <td width="300"><input type="text" name="actOrderId" value="${param.actOrderId}"  class="txt validate-number"   style="width:206px"/></td>
            </tr>
                 <tr>
                   <td width="70">下发时间：</td>
                <td width="300" >
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTimeBegin" id="startTime" value="${param.startTimeBegin}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="startTimeEnd" id="endTime" value="${param.startTimeEnd}" />
                </td>
                
                  <td width="70">发码渠道：</td>
                		  <td colspan="2" width="300">
               <select name="platformId">
                        <option value="">--请选择--</option>
                        <option value="cplatform" 	<c:if test="${param.platformId =='cplatform'}"> selected="selected"</c:if>>商城码</option>
                         <option value="founder" <c:if test="${param.platformId == 'founder'}"> selected="selected"</c:if>>方正码</option>
                         <option value="score" <c:if test="${param.platformId == 'store'}"> selected="selected"</c:if>>商户码</option>
                    </select>
                </td>
                </tr>
<tr>

       <td width="70">过期时间：</td>
                <td width="300">
                    <input type="text" id="inputStartTimeE" name="inputStartTimeE" value="${param.inputStartTimeE}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="pastTimeBegin" id="startTime" value="${param.pastTimeBegin}"/>
                    至
                    <input type="text" id="inputEndTimeE" name="inputEndTimeE" value="${param.inputEndTimeE}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="pastTimeEnd" id="endTime" value="${param.pastTimeEnd}" />
                </td>

                <td colspan="2">
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>验证码列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData.data}">
        <table class="datalist fixwidth">
            <tr>
      
                <th nowrap="nowrap">订单编号</th>
                <th nowrap="nowrap">商品名称</th>
                <th nowrap="nowrap">验证码</th>
                <th nowrap="nowrap">下发手机号码</th>
                <th nowrap="nowrap">下发渠道</th> 
                <th nowrap="nowrap">下发时间</th>
                <th nowrap="nowrap">过期时间</th>
                <th nowrap="nowrap">当前状态</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
            	<td > ${item.actOrderId }</td>
                <td  >${item.itemName}</td>
                <td  >*******************</td>
                 <td >${item.terminalId}</td>
               
                 <td >
                 	<c:if test="${item.platformId =='founder'}">
									方正码
									</c:if>
										<c:if test="${item.platformId == 'store'}">
									商户码
									</c:if>
										<c:if test="${item.platformId == 'cplatform'}">
									商城码
									</c:if>
                 </td>
                 
          <td><ct:time source="${item.transDate}"/></td>
           <td><ct:time source="${item.expireDate}"/></td>
           <td>  ${ item.statusName} </td>
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