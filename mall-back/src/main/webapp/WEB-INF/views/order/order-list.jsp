<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>

	<head>
		<ht:head />

		<script type="text/javascript">
		function isDigit(value){ 
		  	var patrn=/^[0-9]{0,20}$/; 
		    if (!patrn.exec(value)){ 
		        alert("请输入正确的订单号");
		    	return false;
		    }else{ 
		  		return true;
		  	}
		} 

		$(function (){
			$("#queryForm").submit(function(){
	    		var isPass = true;
	    		isPass = isDigit($("#ordernumber").val());
	    		return isPass;
	    	});
		});



</script>
	</head>
	<body>

		<br />
		<div id="search-menu">
			<ul>
				<ht:menu-btn type="search" />
				<ct:display model="order_list" btn="add_btn">
				</ct:display>
			</ul>
			<br style="clear: left" />
		</div>
		<div class="queryContainer">
			<form name="queryForm" id="queryForm" action="list" method="get">
				<table>
					<tr>
						<td>
							订单编号：
						</td>
						<td>
							<input id="ordernumber" onKeyPress="event.returnValue=IsDigit();"
								type="text" name="id" value="${param.id}" class="txt"
								style="width: 150px" />
						</td>

						<td>
							业务类型：
						</td>
						<td>
							<select name="actType">
								<option value="">
									--请选择--
								</option>
								<option value="1"
									<c:if test="${param.actType == 1}">selected="selected"</c:if>>
									类型1
								</option>
								<option value="2"
									<c:if test="${param.actType == 2}">selected="selected"</c:if>>
									类型2
								</option>
								<option value="3"
									<c:if test="${param.actType == 3}">selected="selected"</c:if>>
									类型3
								</option>
							</select>
						</td>


						<td>
							支付状态：
						</td>
						<td>
							<select name="payStatus">
								<option value="">
									--请选择--
								</option>
								<option value="0"
									<c:if test="${param.payStatus == 0}">selected="selected"</c:if>>
									状态1
								</option>
								<option value="1"
									<c:if test="${param.payStatus == 1}">selected="selected"</c:if>>
									状态2
								</option>
							</select>
						</td>

						<td colspan="4">
							<ct:btn type="search" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="container">
			<br />
			<h3>
				订单列表
			</h3>
			<div class="mainbox">
				<c:if test="${not empty pageData}">


					<table class="datalist fixwidth">
						<tr>
							<th nowrap="nowrap" width="150">
								订单号
							</th>
							<th nowrap="nowrap"  width="300">
								商户标题
							</th>
							<th nowrap="nowrap" width="150">
								创建时间
							</th>
							<th nowrap="nowrap">
								订单类型
							</th>
							<th nowrap="nowrap">
								业务类型
							</th>
							<th nowrap="nowrap">
								支付状态
							</th>
							<th nowrap="nowrap">
								收货人
							</th>

							<th nowrap="nowrap">
								操作
							</th>
						</tr>

						<c:forEach items="${pageData.data}" var="item">
							<tr>
								<td nowrap="nowrap">
									${item.id}
								</td>
								<td nowrap="nowrap" >
									${item.shopSubject}
								</td>
								<td nowrap="nowrap">
									<ct:time source="${item.createTime}" />
								</td>
								<td nowrap="nowrap">
									${item.actTypeName}
								</td>
								<td nowrap="nowrap">
									${item.closeStatusName}
								</td>
								<td nowrap="nowrap">
									${item.payStatusName}
								</td>


								<td nowrap="nowrap">
									${item.userName}
								</td>
								<td width="100" nowrap="nowrap">
									<ct:display model="order_list" btn="view_btn">
										<a href="view?id=${item.id}">查看</a>&nbsp;&nbsp;
	                </ct:display>

								</td>
							</tr>
						</c:forEach>
					</table>

					<ht:page pageData="${pageData}" />

				</c:if>
				<c:if test="${empty pageData.data}">
					<div class="note">
						<p class="i">
							目前没有相关记录!
						</p>
					</div>
				</c:if>
			</div>

		</div>

	</body>
</html>