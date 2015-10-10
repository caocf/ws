<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="ct" uri="/cplatform-tag" %>
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
        <ct:display model="task_mode" btn="add_btn">
        	<ht:menu-btn text="${menuBtn }" url="/giftCard/preAdd?type=${action_type}" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
              <td>卡号：</td>
              <td>
              	<input type="text" name="serialNo" title="卡号" value="${param.serialNo}" class="txt" style="width:150px"/>
              	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </td>  
              <td>批次号：</td>
              <td>
              	<input type="text" name="batchNo" title="批次号" value="${param.batchNo}" class="txt validate-number" style="width:150px"/>
              	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </td>
              <td><ct:btn type="search" /></td>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>${listTitle }</h3> 

    <div class="mainbox">
        <c:if test="${not empty giftCardList}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="180">编号</th>
                <th nowrap="nowrap"  width="100">批次号</th>
            <!--     <th nowrap="nowrap"  width="100">兑换用户</th>
                <th nowrap="nowrap"  width="100">兑换状态</th>
                <th nowrap="nowrap"  width="100">兑换时间</th> -->
                <th nowrap="nowrap"  width="100">状态</th>
                <th nowrap="nowrap"  width="180">领卡时间</th>
                <th nowrap="nowrap"  width="180">创建时间</th>
                <!-- <th nowrap="nowrap"  width="180">库存状态</th> -->
                <th nowrap="nowrap"  width="180">账期</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${giftCardList.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.serialNo}</td>
                <td nowrap="nowrap">${item.batchNo}<c:if test="${ empty item.batchNo }">-----</c:if></td>
                <td nowrap="nowrap"><c:if test="${item.status == -1}">异常</c:if><c:if test="${item.status == 0}">待激活</c:if><c:if test="${item.status == 1}">激活</c:if><c:if test="${item.status == 2}">冻结</c:if><c:if test="${item.status == 3}">挂失</c:if></td>
                <td nowrap="nowrap"><ct:time source="${item.receiveTime}" /></td>
                <td nowrap="nowrap"><ct:time source="${item.createdTime}" /></td>
               <%--  <td nowrap="nowrap"><c:if test="${item.storageStatus == 0}">未入库</c:if><c:if test="${item.storageStatus == 1}">已入库</c:if><c:if test="${item.storageStatus == 2}">已出库</c:if></td> --%>
                <td nowrap="nowrap"><ct:time source="${item.paymentDay}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd" /></td>
                <td nowrap="nowrap">
                <ct:display model="gift_${action_type }_mode" btn="view_btn">
                 	<a href="${ctx}/giftCard/detail?serialNo=${item.serialNo}&type=${action_type}">查看</a>
                 </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${giftCardList}" />

        </c:if>
        <c:if test="${empty giftCardList}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>