var setting = {
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		addDiyDom: addDiyDom,
		selectedMulti: false,
		showIcon: false
	},
	edit: {
		enable: true,
		editNameSelectAll: true,
		drag: {
			inner: false
		}
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		beforeDrag: beforeDrag,
		beforeDrop: beforeDrop,
		beforeEditName: beforeEditName,
		beforeRemove: beforeRemove,
		beforeRename: beforeRename,
		onRemove: onRemove,
		onRename: onRename
	}
};

var className = "dark";
function beforeDrag(treeId, treeNodes) {
	for (var i=0,l=treeNodes.length; i<l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		}
	}
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	return targetNode ? targetNode.drop !== false : true;
}
function beforeEditName(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	var zTree = $.fn.zTree.getZTreeObj("treeList");
	zTree.selectNode(treeNode);
	return true;
}
function beforeRemove(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	var zTree = $.fn.zTree.getZTreeObj("treeList");
	zTree.selectNode(treeNode);
	return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}
function onRemove(e, treeId, treeNode) {
   var pars = 'name='+treeNode.name; 
   var url = G_CTX_ROOT + "/shop-set/delGoodShelf/"+treeNode.id;
   jQuery.ajax({
	   	type : "get",
	   	data : pars,
	   	url : url,
	   	dataType:  'json',
	   	success : function(resp){	
	   	}
   });
}
function beforeRename(treeId, treeNode, newName) {
	className = (className === "dark" ? "":"dark");
	if (newName.length == 0) {
		alert("节点名称不能为空.");
		var zTree = $.fn.zTree.getZTreeObj("treeList");
		setTimeout(function(){zTree.editName(treeNode)}, 10);
		return false;
	}
	return true;
}

function onRename(e, treeId, treeNode) {
   var pars = 'name='+treeNode.name; 
   var url = G_CTX_ROOT + "/shop-set/fenlei/rename/"+treeNode.id;
   jQuery.ajax({
	   	type : "get",
	   	data : pars,
	   	url : url,
	   	dataType:  'json',
	   	success : function(resp){	
	   	}
   });
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
    if (treeNode.level > 1){
       return;
    }
	var sObj = $("#" + treeNode.tId + "_span");
	var diyObj = $("#diyBtn_space_"+treeNode.id);
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.id
		+ "' title='add node' onfocus='this.blur();'></span>";
	if($("#diyBtn_space_"+treeNode.id).length > 0) {
		diyObj.after(addStr);
	} else {
		sObj.after(addStr);
	}
	var btn = $("#addBtn_"+treeNode.id);
	if (btn) btn.bind("click", function(){
	    var Ppname = prompt("请输入货架分类名称:");
	    if (Ppname == null){
	       //return;
	    }else if (Ppname == ""){
	       alert("货架分类名称不能为空！");
	    }else{
	       var zTree = $.fn.zTree.getZTreeObj("treeList");
	       var pars = "pid="+treeNode.id+"&title="+Ppname+"&orderIndex=last"; 
		   var url = G_CTX_ROOT + "/shop-set/addSubGoodShelf";
		   jQuery.ajax({
			   	type : "post",
			   	data : pars,
			   	url : url,
			   	dataType:  'json',
			   	success : function(resp){
			   	   zTree.addNodes(treeNode, {id:resp.id, pId:treeNode.id, name:Ppname});
			   	}
		   });
	    }
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.id).unbind().remove();
};
function addDiyDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if ($("#diyBtn_"+treeNode.id).length>0) return;
	if(treeNode.pId == null) {
		var editStr = "<a href='#myModal_" + treeNode.id + "' data-toggle='modal' class='button add-pic' id='diyBtn_space_" +treeNode.id+ "' ></a>";
		sObj.after(editStr);
	}
};
function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("treeList");
	zTree.setting.edit.editNameSelectAll = true;
}

