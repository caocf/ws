

function dealInfo(msg,url,customCallBack){
	if(!confirm(msg)) {
		return; 
	}
	waitBlock();
    jQuery.ajax({
    	type : "get",
    	url : url,
    	dataType:  'json',
    	cache:false,
    	success : function(resp){	
		  if ($.isFunction(customCallBack)) {
              customCallBack.call(this, resp);
              return;
          }
		  if (resp.success) {
		        var msg = resp.message;
		        var url = resp.url;
		        if (url && url.indexOf('http') != 0) {
		            url = G_CTX_ROOT + url;
		        }
		        if (msg) {
		            if (url) {
		                localStorage.setItem("form_hint", msg);
		            } else {
		                growlSuccess(msg);
		            }
		        }
		        if (url) {
		            location.href = url;
		        }
		    } else {
		    	 var msg = resp.errorMessage;
		         var errors = resp.errors;
		         if (msg) {
		             growlError(msg);
		         }
		    }
    	}
    });
}

function deleteItem(url,customCallBack){ 
	dealInfo("确定要删除此条记录吗？",url,customCallBack);
}


/**
 * 弹出dialog对话框，可以指定地址
 * @param title 标题
 * @param url 要打开的地址
 * @param fn 要回调的函数 函数会传入参数是打开窗口的document
 * @param opts 要修改dialog的一些打开参数，可以修改，不是必填
 */
function showDialog(title,url,fn,opts){
	var param = $.extend({
		id    : "custom-dialog",
		Title :  title,
		Height: 350,
		Width : 550,
		ShowButtonRow : true,
		URL : url	
	},opts||{});
	
	var diag = new Dialog(param);
	diag.CancelEvent=function(){diag.close();};
	diag.OKEvent = function(){
		if(jQuery.isFunction(fn)){
			 fn(diag.innerDoc,diag.innerWin);
		}
		 diag.close();
	};
	
	if(param.ShowMessageRow){
		diag.Message = title;
	}
	diag.show();
	
	diag.okButton.value="确 定";
	diag.cancelButton.value="关 闭";
	
	
	if(opts && opts.ShowButtonRow !=undefined && !opts.ShowButtonRow){
	    diag.okButton.style.display = "none";
	}
}


/**
 * 选择区域方法
 * @param selector jquery选择器，要弹出地区选择的元素 选择器
 * @param returnValueId  返回的id列表，要匹配的元素id（要input元素），
 * @param returnTxtId 返回的文字列表
 * @param pid 要查询的父区域id，默认为0
 * @param opts 可选。指定弹出选择的参数{} ，具体参考jquery-levelSelect-ajax.js 中的参数说明
 */
function selectRegion(selector,returnValueId,returnTxtId,pid,opts){
	$( selector).click(function(){
		var param = $.extend({
			maxItems : 20,
			pid : pid,
			title:'区域选择',
			url: G_CTX_ROOT + '/base/selectRegion',
			returnText : returnTxtId,
			returnValue : returnValueId,
			span_width : {d1:120,d2:120,d3:120},
			index :$(selector).index(),
			regionElement:$(this)
		},opts||{});
		$.openLayer(param);
	});
}
/**
 * 选择区域方法
 * @param selector jquery选择器，要弹出地区选择的元素 选择器
 * @param callback 回调函数，点击“确定” 按钮时会调用，传入2个参数， returnValueStr 返回的id列表str，eturnTxtStr 返回的文字列表str
 * @param pid 要查询的父区域id，默认为0
 * @param opts 可选。指定弹出选择的参数{} ，具体参考jquery-levelSelect-ajax.js 中的参数说明
 */
function selectRegionCallBack(selector,callback,pid,opts){
	var param = $.extend({
		callback:callback
	},opts||{});
	selectRegion(selector,'','',pid,param);
}

/**
 * 
 * @param type 1-shop 2-goods
 * @param returnValueId
 * @param returnTxtId
 * @param opts {selected:'选中的id',one:true(是否单选)}
 */
function selectType(type,returnValueId,returnTxtId,opts){
	var set_returnVals  = function(id,vals) {	//按"确定"按钮时处理、设置返回值
		if(id != ""){
			var Container = $("#" + id);
			if(Container.length > 0){
				if(Container.is("input")){
					Container.val(vals.substring(1));
				}else{
					Container.text(vals.substring(1));
				}
			}
		}	
	};
	
	var param = $.extend({
		one : false,//是否单选
		selected:"",//选中的id，逗号分隔
		callback:""//回调方法
	},opts||{});
	
	var url = G_CTX_ROOT + '/base/selectType?type=' + type;
	if(param.one){
		url += "&isone=true"
	}
	if(param.selected){
		url += "&selected="+param.selected;
	}
	
	showDialog("选择类别",url, function(doc,win){
		var zTree = win.$.fn.zTree.getZTreeObj("treeContainer");
		var nodes = zTree.getCheckedNodes(true);
		var vals = "";
		var txts = "";
		var tmp = "";
		for(var i =0;i<nodes.length;i++){
			var node = nodes[i];
			vals += ("," +node.id);
			tmp = node.name;
			node =  node.getParentNode();
			while(!jQuery.isEmptyObject(node) ){
				tmp = (node.name) +"/" +tmp ;
				node = node.getParentNode();
			}
			txts += ("," +tmp);
		}
		
		if(jQuery.isFunction(param.callback)){
			param.callback(vals,txts);
		}else{
			set_returnVals(returnValueId,vals);
			set_returnVals(returnTxtId,txts);
		}
		
	},{ShowMessageRow:true,
		Height: 350,
		Width : 300});
}


/**
 * 
 */
function selectItemType(selector,callback,opts){
	//判断容器是否存在
	var typeBox = $("#typeContentBox");
	if(typeBox.length == 0){
		typeBox = $('<div id="typeContentBox"  style="border: 1px solid rgb(127, 157, 185);background:#ffffff;display:none; position: absolute;"><ul id="typeTreeBox" class="ztree" style="margin-top:0; width:280px;"></ul></div>');
		$("body").append(typeBox);
	}
	var param = $.extend({
		type:2
	},opts||{});
	
	var setting = {
			view: {
				dblClickExpand: false,
				expandSpeed: ""
			},
			async: {
				enable: true,
				url:  G_CTX_ROOT + '/base/selectType/selectItemType',
				dataType : "json",
				autoParam: ["id"],
				otherParam: ["type", param.type],
				dataFilter : function(treeId, parentNode, responseData){
					if(!responseData) return null;
					 if(parentNode){
					 }
					 for(var i =0; i < responseData.length; i++) {
						 if(!parentNode || (parentNode && parentNode.level < 1)){//只显示3层分类
					        responseData[i].isParent=true;
						 }
					 }
					return responseData;
				}
			},
			callback: {
				beforeClick: function beforeClick(treeId, treeNode) {
					var check = (treeNode && !treeNode.isParent);
					return check;
				},
				onClick: function onClick(e, treeId, treeNode) { //判断只能选择子节点
					callback(treeNode.id,treeNode.name);
					$("#typeContentBox").fadeOut("fast");
					$("body").unbind("mousedown", onBodyDown);
				}				
			}
		};
		
		$(selector).click(function(){
			var treeObj = $.fn.zTree.getZTreeObj("typeTreeBox");
			if(!treeObj){
				$.fn.zTree.init($("#typeTreeBox",typeBox), setting);
			}
			var thisOffset = $(this).offset();
			typeBox.css({left:thisOffset.left + "px", top:thisOffset.top + $(this).outerHeight() + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		});
		
		
		function onBodyDown(event) {
			if (!($(event.target).attr("id")== $(selector).attr("id") || event.target.id == "typeContentBox" || $(event.target).parents("#typeContentBox").length>0)) {
				$("#typeContentBox").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown);
			}
		}
}

/**
 * 兼容chrome浏览器的form submit提交
 * @param jqueryForm
 */
function commonSubmit(form){
	//获取浏览器参数  
	var browserName=navigator.userAgent.toLowerCase();  
	if(/chrome/i.test(browserName)&&/webkit/i.test(browserName)&&/mozilla/i.test(browserName)){  
	    //如果是chrome浏览器  
		var jqueryForm = $(form);
	    var tmp=jqueryForm.attr('action');  
	    $.ajaxSetup({ //同步提交表单
	    	async:false
	    });
	    $.post(tmp,jqueryForm.serialize());  
	    $.ajaxSetup({
	    	async:true
	    });
	}else{  
	    //执行SUBMIT  
		form.submit();  
	}  
}

