<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
		<ht:head/>
		<script type="text/javascript">
			function view(url){
				var backUrl=document.URL;
				location=url+"&backUrl="+backUrl;
			}
		</script>
	</head>
	<body>	
		<br/>
			<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer" >
		   <form name="queryForm" id="queryForm" action="?" method="get">
		        <table>
		            <tr>
		            	<td>批次号：</td>
		                <td><input type="text" name="batchNo" title="批次号" value="${param.batchNo}" class="txt validate-number" style="width:150px"/></td>
		                <td>卡型号：</td>
		                <td><input type="text" name="modelNo" value="${param.modelNo}" class="txt" style="width:150px"/></td>
		            	<td>客户名：</td>
		                <td><input type="text" name="requiredUser" value="${param.requiredUser}" class="txt" style="width:150px"/></td>	
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
		    <h3>礼品卡兑换记录列表</h3>
		    <div class="mainbox">
		        <c:if test="${not empty pageData.data}">
		        <table class="datalist fixwidth">
		            <tr>
		            	<th nowrap="nowrap" width="200">批次号</th>
		            	<th nowrap="nowrap" width="200">卡型号</th>
		            	<th nowrap="nowrap" width="200">客户名</th>
		            	<th nowrap="nowrap" width="150">礼品卡数量</th>
		            	<th nowrap="nowrap" width="150">已兑换数量</th>
						<th nowrap="nowrap" >操作</th>
		            </tr>
		
		            <c:forEach items="${pageData.data}" var="item">
		            <tr>
		            	<td  nowrap="nowrap">${item.batchNo}</td>
		            	<td  nowrap="nowrap" class="ellipsis">${item.modelNo}</td>
		            	<td  nowrap="nowrap" class="ellipsis">${item.requiredUser}</td>
		            	<td  nowrap="nowrap">${item.cardNum}</td>
		            	<td  nowrap="nowrap">${item.exchangeNum}</td>
		            	<td  nowrap="nowrap">
		            		<ct:display model="gift_exchange_list" btn="view_btn">
		            		<a style="cursor: pointer;" onclick="view('${ctx }/gift/exchange/list?batchNo=${item.batchNo}');">查看</a>
		            		
<%-- 							<a href="${ctx }/gift/exchange/list?batchNo=${item.batchNo}">查看</a> --%>
							</ct:display>
						</td>
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