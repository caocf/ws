<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
    <script type="text/javascript">	
	var demoIframe;
	
	var setting = {
			view: {
				dblClickExpand: false,
				expandSpeed: ""
			},
			data: {
				simpleData: {
					enable: true,
					 idKey: "id",
                     pIdKey: "pid",
                     rootPId: 0
				}
			},
			async: {
				enable: true,
				url:  G_CTX_ROOT + '/sys/type/selectItemType',
				dataType : "json",
				autoParam: ["id"],
				otherParam: ["type", 2],
				dataFilter : function(treeId, parentNode, responseData){
					return responseData;
				}
			},
			callback: {
				beforeClick: function(treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("TreeView");
					zTree.expandNode(treeNode);
					demoIframe.attr("src","jumpGoods/" + treeNode.id + "/0");
					return true;
				}
			}
		};		
		
		$(document).ready(function(){
			var t = $("#TreeView");
			t = $.fn.zTree.init(t, setting);
			demoIframe = $("#goodsListFrame",window.parent.document);
		});
    </script>
</head>
<body id="TreeView" class="ztree">
</body>
</html>