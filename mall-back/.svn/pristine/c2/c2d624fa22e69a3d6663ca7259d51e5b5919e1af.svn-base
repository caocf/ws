<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
	<script type="text/javascript">
	function rd(){
		window.location.href = "${ctx}/giftCard/${action_type }/list";
	}
	</script>
</head>
<body>
<br/>

<div class="container">
    <br/>
    <h3>任务详情列表</h3>

    <div class="mainbox">
        <c:if test="${not empty giftCardModel}">

         <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="80">编号</th>
                <th nowrap="nowrap"  width="100">批次号</th>
            <!--     <th nowrap="nowrap"  width="100">兑换用户</th>
                <th nowrap="nowrap"  width="100">兑换状态</th>
                <th nowrap="nowrap"  width="100">兑换时间</th> -->
                <th nowrap="nowrap"  width="100">激活状态</th>
                <th nowrap="nowrap"  width="100">领卡时间</th>
                <th nowrap="nowrap"  width="180">创建时间</th>
                <th nowrap="nowrap"  width="180">库存状态</th>
                <th nowrap="nowrap"  width="180">账期</th>
            </tr>
            <tr>
                <td nowrap="nowrap">${giftCardModel.serialNo}</td>
                <td nowrap="nowrap">${giftCardModel.batchNo}<c:if test="${ empty giftCardModel.batchNo }">-----</c:if></td>
               <%--  <td nowrap="nowrap">${user_name}</td>
                <td nowrap="nowrap">${giftCardModel.exchangeStatus}</td>
                <td nowrap="nowrap">${giftCardModel.exchangeTime}</td> --%>
                <td nowrap="nowrap"><c:if test="${giftCardModel.status == -1}">异常</c:if><c:if test="${giftCardModel.status == 0}">待激活</c:if><c:if test="${giftCardModel.status == 1}">激活</c:if><c:if test="${giftCardModel.status == 2}">冻结</c:if><c:if test="${giftCardModel.status == 3}">挂失</c:if></td>
                <td nowrap="nowrap"><ct:time source="${giftCardModel.receiveTime}" /></td>
                <td nowrap="nowrap"><ct:time source="${giftCardModel.createdTime}" /></td>
                <td nowrap="nowrap"><c:if test="${giftCardModel.storageStatus == 0}">未入库</c:if><c:if test="${giftCardModel.storageStatus == 1}">已入库</c:if><c:if test="${giftCardModel.storageStatus == 2}">已出库</c:if></td>
                <td nowrap="nowrap"><ct:time source="${giftCardModel.paymentDay}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd" /></td>
            </tr>

        </table>

        </c:if>
    </div>
	 	<div class="buttons">
			<input type="button" class="common_btn" onclick="rd();" value="返回"/>
		</div>
</div>

</body>
</html>