<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
         <script src="<spring:url value="/static/js/jquery.ztree.all-3.5.js"/>" type="text/javascript" charset="utf-8"></script>
         <link href="<spring:url value="/static/js/zTreeStyle/zTreeStyle.css"/>" rel="stylesheet" type="text/css" />
         <script type="text/javascript">
         var setting = {
			check: {
				enable: true,
				<c:if test="${isone}">
					chkStyle: "radio",
					radioType: "all"
				</c:if>
				<c:if test="${not isone}">
					chkStyle: "checkbox",
					chkboxType: { "Y": "", "N": "" } //是否影响父子节点的选择
				</c:if>
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			view:{
					expandSpeed: ""
			}
			
		};
		var zNodes =[
			<c:forEach items="${typeList }" var="item" varStatus="idx">
				{ id:${item.id }, pId:'${item.pId }' ,name:"${item.name }" 
				<c:forTokens items="${selected }" delims=","  var="selItem">
					<c:if test="${item.id eq selItem }">, checked:true</c:if>
				</c:forTokens>}
			<c:if test="${not idx.last}">,</c:if>
			</c:forEach>
		];
         
         $(function(){
         	$.fn.zTree.init($("#treeContainer"), setting, zNodes);
         });
         
         </script>
    </head>
<body>
<div id="treeContainer" class="ztree">
	
</div>
</body>
</html>