<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
    </head>
<body>
<br/>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="selectSingleItem" method="post">
        <input type="hidden" name="storeId" value="${param.storeId}"  />
    	<input type="hidden" name="itemMode" value="${param.itemMode}"  />
    	<input type="hidden" name="saleAreaCode" value="${param.saleAreaCode}"  />
        <table>
            <tr>
                <td>商品编号：</td>
					<td><input type="text" name="id" value="${param.id}" class="txt validate-number" style="width:60px" /></td>
                <td>商品名称：</td>
					<td><input type="text" name="name" id="name" value="${param.name}"
						class="txt" style="width:120px" /></td>
					<td width="70">创建时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.saleStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'saleStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="saleStartTime" id="saleStartTime"
						value="${param.saleStartTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.saleStopTime}" class="txt Wdate"
						readOnly
						onclick="WdatePicker({vel:'saleStopTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
						<input type="hidden" name="saleStopTime" id="saleStopTime"
						value="${param.saleStopTime}" />
					</td>
               	 <td >
                      <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="container">
		<br />
		<h3>商户列表</h3>
		<div class="mainbox">
			<c:if test="${not empty itemPage}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80">编号</th>
						<th nowrap="nowrap" >商品名称</th>
						<th nowrap="nowrap" width="80">是否发码</th>
						<th nowrap="nowrap" width="80">库存</th>
						<th nowrap="nowrap" width="80">商城价</th>
					</tr>

					  <c:forEach items="${itemPage.data}" var="item" varStatus="index">
            			<tr style="word-spacing: normal">				
            			<td nowrap="nowrap"><input type="radio"  id="radio-${index.count}" name="itemSelector"  itmeName="${item.name }"  value="${item.id}"  class="validate-one-required"/>${item.id}</td>
							<td class="ellipsis">${item.name}</td>
							<td >${item.sendCodeModeName}</td>
							<td>${item.stockNum }</td>
							<td>${item.shopPrice }</td>
						</tr>
					</c:forEach>
				</table>
				<ht:page pageData="${itemPage}" />

			</c:if>
			<c:if test="${empty itemPage.data}">
				<div class="note">
					<p class="i">目前没有相关记录!</p>
				</div>
			</c:if>
		</div>
</div>
      	 
</body>
</html>