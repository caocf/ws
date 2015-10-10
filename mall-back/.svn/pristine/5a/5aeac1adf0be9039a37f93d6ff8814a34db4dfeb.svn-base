<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />

</head>
<body>
	<br />
	<div id="search-menu">
		<ul>
			<ht:menu-btn type="search" />
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="codelist" method="post">
		<input type="hidden" name="storeId" value="${storeId }">
		<input type="hidden" name="itemId" value="${itemId }">
			<table>
				<tr>
					<td>验证码：</td>
					<td><input type="text" name="code" value="${param.code}"
						class="txt" style="width:120px" />
					</td>
					
					
					<td width="70">有效日期：</td>
					<td width="100"><input type="text" id="validDate"
						name="validDate" value="${param.validDate}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'validDate',realDateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{\'2050-01-01\'}'})" />
						
					
					
					<td width="70">失效日期：</td>
					<td width="100"><input type="text" id="expireDate"
						name="expireDate" value="${param.expireDate}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'expireDate',realDateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{\'2050-01-01\'}'})" />
						
					
					</tr>
				<tr>
				<td>码状态：</td>
					<td><select name="status">
							<option value="">--请选择--</option>
							<option value="0"
								<c:if test="${param.status == 0}">selected="selected"</c:if>>已制码，未验证</option>
							<option value="1"
								<c:if test="${param.status == 1}">selected="selected"</c:if>>已撤销</option>
							<option value="2"
								<c:if test="${param.status == 2}">selected="selected"</c:if>>使用中</option>
							<option value="3"
								<c:if test="${param.status == 3}">selected="selected"</c:if>>已使用</option>
							<option value="4"
								<c:if test="${param.status == 4}">selected="selected"</c:if>>已过期</option>
							<option value="100"
								<c:if test="${param.status == 100}">selected="selected"</c:if>>初始化，未使用</option>
					</select></td>
					
						
					<td><ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div class="container">
		<br />
		<h3>码列表</h3>

		<div class="mainbox">
			<c:if test="${not empty pageData}">

				<table class="datalist fixwidth">
					<tr>
						<th nowrap="nowrap">验证码</th>
						<th nowrap="nowrap">创建时间</th>
						<th nowrap="nowrap">有效日期</th>
						<th nowrap="nowrap">失效日期</th>
						<th nowrap="nowrap">码状态</th>
						<th nowrap="nowrap">可验次数</th>
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item" varStatus="i">
						<tr>
							<td nowrap="nowrap" width="100">${item.code}</td>
							<td nowrap="nowrap" width="100">
							<ct:time source="${item.createDate}" />
							</td>
							<td nowrap="nowrap" width="100">
							<ct:time source="${item.validDate}" />
							</td>
							<td nowrap="nowrap" width="100">
							<ct:time source="${item.expireDate}" />
							</td>
							<td nowrap="nowrap" width="100">${item.statusName }</td>
							<td nowrap="nowrap" width="100">${item.validTimes}</td>
							
							<td width="100" nowrap="nowrap">
						
							    <ct:display	model="saleitem_list" btn="view_btn">
									<a href="viewCode?itemId=${item.itemId}&code=${item.code}&storeId=${storeId}">查看</a>&nbsp;&nbsp;
								</ct:display> 
							
								
								 <ct:display	model="saleitem_list" btn="add_btn">
									<a href="editCode?itemId=${item.itemId}&storecode=${item.code}&storeId=${storeId}">编辑</a>&nbsp;&nbsp;
							
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