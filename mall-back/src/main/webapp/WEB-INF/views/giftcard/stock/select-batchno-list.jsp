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
                
              <td>卡批次号：</td>
             <td><input type="text" name="batchNo" value="${param.batchNo}" class="txt validate-number" style="width:120px"/></td>
                <td>
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>成品卡批次列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap" width="80px">选择</th>
                <th nowrap="nowrap" width="80">卡批次号</th>
                <th nowrap="nowrap"  width="50">需求数量</th>
                <th nowrap="nowrap"  width="50">入库量</th>
                <th nowrap="nowrap"  width="50">出库量</th>
                <th nowrap="nowrap"  width="50">库存量</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item" varStatus="index">
            <tr>
            	<td nowrap="nowrap"><input type="radio"  id="radio-${index.count}" name="batchNoSelector"   batchNo="${item.batchNo}" stocks="${item.stocks}"  value="${item.batchNo}"   class="validate-one-required"/></td>
                <td nowrap="nowrap">${item.batchNo}</td>
                <td nowrap="nowrap">${item.cardNum}</td>
                <td nowrap="nowrap">${item.stocksIn}</td>
                <td nowrap="nowrap">${item.stocksOut}</td>
                <td nowrap="nowrap">${item.stocks}</td>
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