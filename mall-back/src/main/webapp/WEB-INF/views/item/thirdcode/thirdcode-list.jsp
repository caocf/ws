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
			<ct:display model="third_code" btn="add_btn">
			<ht:menu-btn text="添加第三方码" url="/item/thirdcode/add" />
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer">
		<form name="queryForm" id="queryForm" action="?" method="get">
		        <table>
		            <tr>
		            	<td>验证码名称：</td>
		                <td width="200">
	                		<select name="id">
	                			<option value="">--请选择--</option>
								<c:forEach items="${codeNames}" var="item" varStatus="index">
									<option value="${item.id }" <c:if test="${item.id == param.id}">selected="selected"</c:if>>${item.name}</option>
								</c:forEach>
							</select>
		                </td>		 
		                <td>创建时间：</td>
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
		            </tr>
		            	<td colspan="4">
		                    <ct:btn type="search" />
		                </td>
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
						<th nowrap="nowrap" >编号</th>
						<th nowrap="nowrap" >商户名称</th>
						<th nowrap="nowrap" >验证码名称</th>
						<th nowrap="nowrap" >是否卡密</th>
						<th nowrap="nowrap" >累计数量</th>
						<th nowrap="nowrap" >创建时间</th>
						<th nowrap="nowrap">操作</th>
					</tr>

					<c:forEach items="${pageData.data}" var="item">
						<tr>
							<td nowrap="nowrap">${item.id}</td>
							<td nowrap="nowrap" class="ellipsis">${item.storeName}</td>
							<td nowrap="nowrap" class="ellipsis">${item.name}</td>
							<td nowrap="nowrap" >
							<c:if test="${item.codeType ==2 }">非卡密</c:if>
							<c:if test="${item.codeType ==3 }">卡密</c:if>
							</td>
							<td nowrap="nowrap">${item.num }</td>
							<td nowrap="nowrap"><ct:time source="${item.createTime}" /></td>
							<td nowrap="nowrap">
								<ct:display model="third_code" btn="view_btn">
								<a href="view?id=${item.id }&codeType=${item.codeType}">查看</a>
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