<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>

<head>
<ht:head />
</head>
<body>
	<br>
	<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
		        <table>
		            <tr>
		            	<td>批次号：</td>
		                <td><input type="text" name="batchNo" title="批次号" value="${param.batchNo}" class="txt validate-number" style="width:150px"/></td>		 
		                <td>卡型号：</td>
		                <td><input type="text" name="modelNo" value="${param.modelNo}" class="txt" style="width:150px"/></td>
		            </tr>
		             <tr>
		            	<td>客户名：</td>
		                <td><input type="text" name="requiredUser" value="${param.requiredUser}" class="txt" style="width:150px"/></td>	
<!-- 		                <td width="100">需要礼品卡数量：</td> -->
<%-- 		                <td><input type="text" name="cardNum" title="需要礼品卡数量" value="${param.cardNum}" class="txt validate-number" style="width:150px"/></td>		 	  --%>
		            	<td>绑定状态：</td>
						<td width="200"><select name="bindItemStatus">
							<option value="">--请选择--</option>
							<c:forEach items="${bindItemStatusMap}" var="item" varStatus="index">
								<option value="${item.key }"
									<c:if test="${item.key == param.bindItemStatus}">selected="selected"</c:if>>${item.value}</option>
							</c:forEach>
							</select>
						</td>
						<td>绑定商品状态：</td>
						<td width="200"><select name="bindStatus">
							<option value="">--请选择--</option>
							<c:forEach items="${bindStatusMap}" var="item" varStatus="index">
								<option value="${item.key }"
									<c:if test="${item.key == param.bindStatus}">selected="selected"</c:if>>${item.value}</option>
							</c:forEach>
							</select>
						</td>
		            </tr>
		            <tr>
						
		            	 <td width="90">期望发卡时间：</td>
                <td >
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly
             onfocus="WdatePicker({vel:'beginTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2050-10-01\'}'})" />
                    <input type="hidden" name="beginTime" id="beginTime" value="${param.beginTime}"/>
                   	至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}"  class="txt Wdate"
                           readOnly 
             onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2050-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
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
		<h3>礼品卡绑定商品列表</h3>
		<div class="mainbox">
			<c:if test="${not empty pageData}">
				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap" width="80">批次号</th>
						<th nowrap="nowrap" width="100">卡型号</th>
						<th nowrap="nowrap" width="100">客户名</th>
						<th nowrap="nowrap" width="100">需要礼品卡数量</th>
						<th nowrap="nowrap" width="100">期望发卡时间</th>
						<th nowrap="nowrap" width="100">需求提交时间</th>
						<th nowrap="nowrap" width="80">礼品兑换方式</th>
						<th nowrap="nowrap" width="100">是否已绑定商品</th>
						<th nowrap="nowrap" width="150">礼品卡绑定商品审核状态</th>
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap">${item.batchNo}</td>
							<td nowrap="nowrap" class="ellipsis">${item.modelNo }</td>
							<td nowrap="nowrap" class="ellipsis">${item.requiredUser }</td>
							<td nowrap="nowrap">${item.cardNum }</td>
							<td nowrap="nowrap"><ct:time source="${item.issuingTime}" tfmt="yyyy-MM-dd"/></td>
							<td nowrap="nowrap"><ct:time source="${item.createdTime}" tfmt="yyyy-MM-dd"/></td>
							<td nowrap="nowrap">${item.exchangeModeName}</td>
							<td nowrap="nowrap">${item.bindItemStatusName}</td>
							<td nowrap="nowrap">
							<c:if test="${empty item.bindStatusName}">无</c:if>
							${item.bindStatusName}
							</td>
							<td nowrap="nowrap">
								<ct:display model="gift_relateItem" btn="add_btn">
									<c:if test="${item.bindItemStatus == 0 and item.status ==2}">
									<a href="add?batchNo=${item.batchNo }">添加</a>
									</c:if>
								</ct:display>
								<ct:display model="gift_relateItem" btn="view_btn">
									<c:if test="${not empty item.bindStatus and item.bindStatus !=-1  }">
									<a href="view?batchNo=${item.batchNo }">查看</a>
									</c:if>
								</ct:display>
								<c:if test="${item.bindStatus eq 0 or item.bindStatus eq 3 }">
									<ct:display model="gift_relateItem" btn="mod_btn">
									<a href="edit?batchNo=${item.batchNo }">修改</a>
									</ct:display>
									<ct:display model="gift_relateItem" btn="del_btn">
									<a href="#" onclick="dealInfo('确定要删除绑定吗？','delete/${item.batchNo }');">删除</a>
									</ct:display>
									<ct:display model="gift_relateItem" btn="send_audit_btn">
									<a href="#" onclick="dealInfo('确定要送审吗？','firstAudit/${item.batchNo }');">送审</a>
									</ct:display>
								</c:if>
								<c:if test="${item.bindStatus eq 2 }">
									<ct:display model="gift_relateItem" btn="back_upload_btn">
									<a href="#" onclick="dealInfo('确定要回传吗？','backUpload/${item.batchNo }');">回传文件</a>
									</ct:display>
								</c:if>
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