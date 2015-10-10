<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!doctype html>
<html>
<head>
    <ht:head/>
     <script type="text/javascript">
    		$(document).ready(function(){
    			
    		});	
    </script>
</head>
<body>
<br />
<div id="search-menu">
      <ul>
         <ct:display model="smsbuy_act_list" btn="add_btn">
         <ht:menu-btn text="新增商品指令" url="/smsbuy/act/addOrUpdateItemRouter?actId=${actId}" type="add"/>
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>

<div id="content">
<div class="container">
    <br/>
    <h3>商品指令列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData && pageData.data != '[]'}" var="ishave">
        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="150">归属活动</th>
                <th nowrap="nowrap" width="100">商品名称</th>
                <th nowrap="nowrap" width="100">特服号</th>
                <th nowrap="nowrap" width="100">指令内容</th>
                <th nowrap="nowrap" width="100">商品支付方式</th>
                <th nowrap="nowrap" width="100">商品购买价格(元)</th>
                <th nowrap="nowrap" width="100">审核状态</th>
                <th nowrap="nowrap"width="100">操作</th>
            </tr>
            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.actId}</td>
                <td nowrap="nowrap" class="ellipsis">${item.itemName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.spCode}</td>
                <td nowrap="nowrap" class="ellipsis">${item.command}</td>
                <td nowrap="nowrap">${item.payTypeName}</td>
                <td nowrap="nowrap">
                <fmt:formatNumber value="${item.itemPrice / 100}" pattern="0.00"/></td>
                <td nowrap="nowrap">${item.itemStatusName}</td>
                <td nowrap="nowrap">
                <ct:display model="smsbuy_act_list" btn="mod_btn">
                <a href="./addOrUpdateItemRouter?actId=${item.actId }&id=${item.id}">修改</a>
                </ct:display>
                 <ct:display model="smsbuy_act_list" btn="del_btn">
                <a href="#" onclick="deleteItem('./deleteRouter.do?id=${item.id}');">删除</a>
                </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>
         <ht:page pageData="${pageData}" />
        </c:if>
        <c:if test="${!ishave}">
        <div class="note">
            <p class="i">尚未配置商品指令!</p>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>