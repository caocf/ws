<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<style>
<!--
ul.brandList li{
	list-style-type: none;
	float:left;
	display: block;
	border:1px ridge #cccccc;
	padding: 5px;
	margin-left:5px;
	cursor: pointer;
}

ul.brandList li.selected{
	list-style-type: none;
	background: #2f7dc6;
	color:#000000;
	border:1px ridge #cccccc;
	cursor: pointer;
	padding: 5px;
	display: block;
}
-->
</style>
<script type="text/javascript">
<!--
$(function(){
	$("ul.brandList li.brandBox").click(function(idx,el){
		if($(this).hasClass("selected")){
			return;
		}
		$("ul.brandList li.brandBox").removeClass("selected");
		
		$(this).addClass("selected");
	});
});

//-->
</script>
</head>
<body>
	<div class="queryContainer" style="margin: 0px;">
		<form name="queryForm" id="queryForm" action="selectBrand" method="post">
			<table>
				<tr>
					<td>品牌名称：</td>
					<td><input type="text" name="name" value="${param.name}"	class="txt" style="width:100px" />
					</td>
					<td><ct:btn type="search" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="container">
		<h3>品牌列表</h3>
		<div class="mainbox">
			<c:if test="${not empty brandList}">
				<ul class="brandList">
					<c:forEach items="${brandList}" var="item">
					<li class="brandBox">${item.name}</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty brandList}">
				<div class="note">
					<p class="i">目前没有相关记录!</p>
				</div>
			</c:if>
		</div>

	</div>

</body>
</html>