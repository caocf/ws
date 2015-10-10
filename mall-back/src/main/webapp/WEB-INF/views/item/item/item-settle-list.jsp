<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
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
    <form name="queryForm" id="queryForm" action="?" method="get" class="noQueryBackForm">
        <table>
            <tr>
                <td width="80">商品名称</td>
                <input type="hidden" name="itemId" value="${itemSale.id}"/>
                <td width="300"><input type="text" name="productionname" value="${param.productionname}" class="txt" style="width:206px"/></td>
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
  <c:if test="${not empty linkedSettle}">
    <h3>已关联商品协议</h3>
    <table class="datalist fixwidth">
        	<colgroup>
        	<col width="100"></col>
        	<col width="200"></col>
        	<col width="150"></col>
        	<col width="70"></col>
        	<col width="80"></col>
        	<col width="70"></col>
        	<col width="70"></col>        	
        	<col width=""></col>
       		</colgroup>
         
            <tr>
            	<th nowrap="nowrap" width="100">商品协议编号</th>
            	<th nowrap="nowrap" width="200">商品名称</th>
            	<th nowrap="nowrap" width="150">产品有效期</th>
            	<th nowrap="nowrap" width="70">计费分类</th>
            	<th nowrap="nowrap" width="80">协议类型</th>
            	<th nowrap="nowrap" width="70">状态</th>
            	<th nowrap="nowrap" width="70">清算状态</th>
                <th nowrap="nowrap" width="">操作</th>
            </tr>
			
            <tr>
            	<td  nowrap="nowrap"  >
            	${linkedSettle.id}
            	</td>
            	<td >
            		${linkedSettle.productionname}
            	</td>
                <td ><c:if test="${not empty linkedSettle.productionefftime}"><ct:time source="${linkedSettle.productionefftime}" tfmt="yyyy-MM-dd"/>~<ct:time source="${linkedSettle.productionexptime}" tfmt="yyyy-MM-dd"/></c:if></td>
                <td >${linkedSettle.productiontypeName}</td>
                <td >${linkedSettle.typeName}</td>
                <td >${linkedSettle.statusName}</td>
                <td >${linkedSettle.syncGyStatus1Name}</td>
                <td nowrap="nowrap">
                	<ct:display model="production_settle_list" btn="mod_btn">
                		 <a href="#"
                		 onclick="dealInfo('取消关联?', 'getSettles/cancelLinkSettle?settleId=${linkedSettle.id}&itemId=${itemSale.id}');">取消关联</a>
                	</ct:display>
                </td>
            </tr>
          
          
        </table>
       </c:if> 
 
    <br/>
    <h3>商品协议资料列表</h3>

    <div class="mainbox">
        <c:if test="${not empty settlePage.data}">

        <table class="datalist fixwidth">
        	<colgroup>
        	<col width="100"></col>
        	<col width="200"></col>
        	<col width="150"></col>
        	<col width="70"></col>
        	<col width="80"></col>
        	<col width="70"></col>
        	<col width="70"></col>        	
        	<col width="70"></col>
        	<col width="70"></col>
        	<col width="70"></col>
        	<col width="120"></col>
       		</colgroup>
         
            <tr>
            	<th nowrap="nowrap" width="100">商品协议编号</th>
            	<th nowrap="nowrap" width="100">商品名称</th>
            	<th nowrap="nowrap" width="150">产品有效期</th>
            	<th nowrap="nowrap" width="70">计费分类</th>
            	<th nowrap="nowrap" width="80">协议类型</th>
            	<th nowrap="nowrap" width="70">状态</th>
            	<th nowrap="nowrap" width="70">现金同步</th>
            	<th nowrap="nowrap" width="70">商城币同步</th>
            	<th nowrap="nowrap" width="70">积分同步</th>
            	<th nowrap="nowrap" width="70">话费同步</th>
                <th nowrap="nowrap" width="270">操作</th>
            </tr>

            <c:forEach items="${settlePage.data}" var="settle">
            <tr>
            	<td  nowrap="nowrap"  >
            	${settle.id}
            	</td>
            	<td >
            		${settle.productionname}
            	</td>
                <td ><c:if test="${not empty settle.productionefftime}"><ct:time source="${settle.productionefftime}" tfmt="yyyy-MM-dd"/>~<ct:time source="${settle.productionexptime}" tfmt="yyyy-MM-dd"/></c:if></td>
                <td >${settle.productiontypeName}</td>
                <td >${settle.typeName}</td>
                <td >${settle.statusName}</td>
                <td >${settle.syncGyStatus1Name}</td>
                <td >${settle.syncGyStatus2Name}</td>
                <td >${settle.syncGyStatus3Name}</td>
                <td >${settle.syncGyStatus4Name}</td>
                <td nowrap="nowrap">
                	<ct:display model="production_settle_list" btn="mod_btn">
                		 <a href="#"
                		 onclick="dealInfo('确认关联?', 'getSettles/linkSettle?id=${settle.id}&itemId=${itemSale.id}');">关联协议</a>
                	</ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${settlePage}" />

        </c:if>
        <c:if test="${empty settlePage.data}">
        <div class="note">
            <p class="i">该商户目前没有商品协议记录!</p>
        </div>
        </c:if>
        
    </div>
     <input type="button" class="common_btn" onClick="history.back();" value="返回" />
</div>
</body>
</html>