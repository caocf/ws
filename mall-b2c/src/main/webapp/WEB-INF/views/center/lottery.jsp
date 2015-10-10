<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
 <c:forEach items="${list}" var="order">
	<tr>
		<th>订单编号：${order.ORDER_ID}</th>
		<th width="200" colspan="2">购买时间：<ct:time source="${order.DATE}" /></th>
		<th width="240" colspan="2"></th>
	</tr>
	<tr>
		<td>
			<dl>
				<dt>
					<img class="order_img" alt="${order.GOOD_NAME}" 
							<c:if test="${order.GOOD_ID eq 'SSQ' }">
								src="../img/lottery/cp_lottery_shuangseqiu.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'JQC' }">
								src="../img/lottery/cp_lottery_jinqiucai.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'QLC' }">
								src="../img/lottery/cp_lottery_qilecai.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'D3' }">
								src="../img/lottery/cp_lottery_fucai3d.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'DLT' }">
								src="../img/lottery/cp_lottery_chaojidaletou.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'PL3' }">
								src="../img/lottery/cp_lottery_pailie3.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'PL5' }">
								src="../img/lottery/cp_lottery_pailie5.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'SPF' }">
								src="../img/lottery/cp_lottery_shengfu.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'QB6' }">
								src="../img/lottery/cp_lottery_6.png"
						 	</c:if>
						 	<c:if test="${order.GOOD_ID eq 'RX9' }">
								src="../img/lottery/cp_lottery_9.png"
						 	</c:if>
						>
					<!-- 票种图片 -->
				</dt>
				<dd>
					<p>${order.GOOD_NAME}</p>
					<!-- 期号 -->
					<p>${order.ISSUE_NO}</p>
				</dd>
			</dl>
		</td>
		<td width="80">${order.COUNT }</td>
		<td width="120">
			<span class="price"> 
				${order.AMOUNT} 
			</span>
		</td>
		<td width="120">
			<c:if test="${order.STATUS eq '2' }">
				已付款
			</c:if>
			<c:if test="${order.STATUS eq '3' }">
				已退款
			</c:if>
			<c:if test="${order.STATUS eq '4' }">
				不需要付款
			</c:if>
			<c:if test="${order.STATUS eq '11' }">
				待付款
			</c:if>
			<c:if test="${order.STATUS eq '21' }">
				未中奖
			</c:if>
			<c:if test="${order.STATUS eq '22' }">
				已中奖
			</c:if>
		</td>
		<td width="120">
			<a href="lottery_detail.chtml?orderId=${order.ORDER_ID}" class="dis_block" >查看</a>
		    <a style="cursor: pointer;" onclick="weiboShare('${order.ORDER_ID}','${order.GOOD_NAME}','彩票','${order.DATE}')" class="dis_block">微博晒单</a>
		</td>
		</tr>
</c:forEach>                