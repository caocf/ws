<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>

	<head>
		<ht:head />



	 <script type="text/javascript">
	 $().ready(function() {
  		var id = $("#id").val();
    	if (id == 0) {
    		$("#id").val("");
    	}
    });
    
    function sub() {
    	var id = $("#id").val();
    	if (id == null || id == "") {
    		$("#id").val(0);
    	}
    }
    </script>

		
	</head>
	<body>

		<br />
		<div id="search-menu">
			<ul>
				<ht:menu-btn type="search" />
				<ct:display model="smsbuy_act_list" btn="add_btn">
				</ct:display>
			</ul>
			<br style="clear: left" />
		</div>
		<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get" onsubmit="return sub()">
				<table>
					<tr>
						<td>
							订单编号：
						</td>
					
							
								
								<td><input type="text" name="id" id="id" value="${param.id}"
						class="txt" style="width:120px" /></td>
					
   
        

						<td>
							手机号码：
						</td>
						<td>
							<input
								type="text" name="terminalId" value="${param.terminalId}" class="txt"
								style="width: 150px" />
						</td>
						
						
							<td>
							订单状态：
						</td>
						
					  <td>
                    <select name="payStatus">
                        <option value="5">--请选择--</option>
                        <c:forEach items="${PayStatusMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${param.payStatus == item.key}"> selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
          <td>
							指令：
						</td>
						<td>
							<input
								type="text" name="command" value="${param.command}" class="txt"
								style="width: 150px" />
						</td>
                 </tr>
                 <tr>
						<td>
							支付方式：
						</td>
					  <td>
                    <select name="currency">
                        <option value="">--请选择--</option>
                        <option value="cash" 	<c:if test="${item.currency =='cash'}"> selected="selected"</c:if>>现金</option>
                         <option value="coin" <c:if test="${item.currency == 'coin'}">selected="selected"</c:if>>商城币</option>
                         <option value="score" <c:if test="${item.currency == 'score'}">selected="selected"</c:if>>积分</option>
                    </select>
                </td>
					
					
					
					 <td width="70">有效时间：</td> 	
				<td width="" colspan="3">
                    <input type="text" id="createTime" name="createTime" value="${param.createTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTimeBegin',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'stopTimeEnd\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTimeBegin" id="startTimeBegin" value="${param.startTimeBegin}"/>
                   	 至
                    <input type="text" id="closeTime" name="closeTime" value="${param.closeTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'stopTimeEnd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startTimeBegin\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="stopTimeEnd" id="stopTimeEnd" value="${param.stopTimeEnd}" />
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
				订单统计列表
			</h3>
			<div class="mainbox">
				<c:if test="${not empty pageData}">


					<table class="datalist fixwidth">
						<tr>
							<th nowrap="nowrap" width="150">
								订单编号
							</th>
							<th nowrap="nowrap"  width="300">
								活动编号
							</th>
							
								<th nowrap="nowrap"  width="150">
								指令
							</th>
							<th nowrap="nowrap" width="150">
								支付时间
							</th>
							<th nowrap="nowrap">
								手机号码
							</th>
							<th nowrap="nowrap">
								支付方式
							</th>
							<th nowrap="nowrap">
								订单状态
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
									${item.extInfo}
								</td>
								
								
									<td nowrap="nowrap" >
									${item.command}
								</td>
								<td nowrap="nowrap">
									<ct:time source="${item.createTime}" />
								</td>
								<td nowrap="nowrap">
									${item.terminalId}
								</td>
								<td nowrap="nowrap">
							
									<c:if test="${item.currency =='cash'}">
									现金
									</c:if>
										<c:if test="${item.currency == 'coin'}">
									商城币
									</c:if>
										<c:if test="${item.currency == 'score'}">
									积分
									</c:if>
								</td>
								<td nowrap="nowrap">
									${item.payStatusName}
								</td>

								<td width="100" nowrap="nowrap">
									<ct:display model="smsbuy_act_list" btn="view_btn">
										<a href="${ctx}/order/statistView?id=${item.id}">查看</a>&nbsp;&nbsp;
	                </ct:display>

								</td>
							</tr>
						</c:forEach>
						
						    
					</table>
  <input type="button" class="common_btn" onclick="history.back();" value="返回" />
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