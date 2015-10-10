var keyword = "";
var store_id;
var region_code;
var type_id;
var curpage;
var brand;
var sort;
var _webroot;
var myparams = "";
var hideType = "";
var searchSource = "";
getSearchParam = function(){
	 
	 store_id = $("#store_id").val();
	 region_code = $("#region_code").val();
	 type_id = $("#type_id").val();
	 curpage = $("#curpage").val();
	 brand = $("#brand").val();
	 sort = $("#sort").val();
	 searchSource = $("#searchSource").val();
	if(sort == ""){
		sort = "0";
	}
	myparams = $("#myparams").val();
	hideType = $("#hideType").val();
	_webroot = $("#webroot").val();
	var str=window.location.href;
	var es=/keyword=/;
	es.exec(str);
	var str = RegExp.rightContext;
	var array = new Array();
	array = str.split("&");
	keyword =array[0];
}
makeUrl = function(webroot,keyword,store_id,region_code,type_id,brand,sort,curpage,myparams,hideType,searchSource){
	_webroot = webroot;
	var url = webroot+"search/gotoSearch.chtml?";
	if(sort!="" && sort!=null){
		url+="sort="+sort;
	}
	if(keyword!="" && keyword!=null){
		url+="&keyword="+keyword;
	}
	if(store_id!="" && store_id!=null){
		url+="&store_id="+store_id;
	}
	if(region_code!="" && region_code!=null){
		url+="&region_code="+region_code;
	}
	if(type_id!="" && type_id!=null){
		url+="&type_id="+type_id;
	}
	if(brand!="" && brand!=null){
		url+="&brand="+brand;
	}
	if(curpage!="" && curpage!=null){
		url+="&curpage="+curpage;
	}
	if(myparams!="" && myparams!=null){
		url+="&selectparams="+myparams;
	}
	if(hideType!="" && hideType!=null){
		url+="&selecttype="+hideType;
	}else if(parseInt(hideType)==0){
		url+="&selecttype=0";
	}
	if(searchSource!="" && searchSource!=null){
		url+="&searchSource="+searchSource;
	}
	
	return url;
}
ss_sort_html = function(type) {
	$("#sort").val(type);
	var vsort = "0";
  var b = "<dt>排序：</dt>",
    a = '<dd class="#class#"><a href="#s#" onclick="#click#">#name#</a><b></b></dd>',
    d = class_name = "";
    if(type == "1"){
    	class_name = "curr";
        d = "ss_sort('1')";
    }else{
    	class_name = "";
    	d = "ss_sort('1')";
    }
    vsort  = "1";
    b += a.replace("#class#", class_name).replace("#click#", d).replace("#name#", "人气").replace("#s#", makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,vsort,curpage,myparams,hideType,searchSource));
    class_name = d = "";
    if (type == "" || type == "0" || type=="1" || type=="4" || type=="5") {
    	class_name = "";
        d = "ss_sort('2')";
        vsort  = "2";
    }else if(type == "2") {
        class_name = "price curr up";
        d = "ss_sort('3')";
        vsort  = "3";
    } else if (type == "3") {
            class_name = "price curr down";
            d = "ss_sort('2')";
            vsort  = "2";
    }
    b += a.replace("#class#", class_name).replace("#click#", d).replace("#name#", "价格").replace("#s#", makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,vsort,curpage,myparams,hideType,searchSource));
    class_name = d = "";
    
    if(type == "4") {
        class_name = "curr";
        d = "ss_sort('4')";
    }else{
    	class_name = "";
        d = "ss_sort('4')";
    }
    vsort  = "4";
    b += a.replace("#class#", class_name).replace("#click#", d).replace("#name#", "上架时间").replace("#s#", makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,vsort,curpage,myparams,hideType,searchSource));
    class_name = d = "";

    $("#filter .order").html(b)
};

ss_sort = function(b) {
	getSearchParam();
	ss_sort_html(b);
};

doPage=function(cur,total,type){
	var page;
	if(type==1){
		if(cur==1){
			page = 1;
		}else{
			page = cur-1;
		}
	}else{
		if(cur==total){
			page = total;
		}else{
			page = cur+1;
		}
	}
	
	getSearchParam();
	window.location.href=makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,sort,page,myparams,hideType,searchSource);
}

NagivatePage=function(page){
	getSearchParam();
	ss_sort_html(sort);
	window.location.href=makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,sort,page,myparams,hideType,searchSource);
};

var paramsload = "";
var indexload = 1;
pageLoad = function(param,type) {
	getSearchParam();
	
	var paramArray = param.split(":");
	if(indexload==1){
		paramsload=paramArray[1];
	}else{
		paramsload+= ","+paramArray[1];
	}
	indexload++;
	$("#clickName"+type).hide();
	$("#selected_attrs").show();
	var url = makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,sort,curpage,myparams,hideType,searchSource);
	$("#selected_attrs").children("dd:last").append("<div><a class='remove' title='"+type+"' href='javascript:void(0)' params='"+paramArray[1]+"'>"+param+"<b></b></a></div>");
}

var params = "";
var index = 1;
attr_click = function(param,type) {
	getSearchParam();
	
	var paramArray = param.split(":");
	if(index==1){
		params=paramArray[1];
		myparams=param;
		hideType=type;
	}else{
		params+= ","+paramArray[1];
		myparams+=","+param;
		hideType+=","+type;
	}
	index++;
	//$(g).parent().parent().parent().hide();
	$("#clickName"+type).hide();
	$("#selected_attrs").show();
	var url = makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,sort,curpage,myparams,hideType,searchSource); 
	$("#selected_attrs").children("dd:last").append("<div><a class='remove' title='"+type+"' href='javascript:void(0)' params='"+param+"'>"+param+"<b></b></a></div>");
	window.location.href = url;
}

$(document).ready(function(){	
	getSearchParam();
	ss_sort_html(sort);
	topNagivatePage();
	if(myparams!="" && myparams!=null){
		if(myparams.indexOf(",")>0){
			var array = myparams.split(",");
			var hideArray = hideType.split(",");
			for(var i=0;i<array.length;i++){
				pageLoad(array[i],hideArray[i]);
			}
		}else{
			pageLoad(myparams,hideType);
		}
		index++;
	}
	
	
	
});

$('.remove').live('click', function() {
	var type=$(this).attr("title");
	$("#select dl").eq(parseInt(type)+1).show();
	$(this).parent().remove();
	if($("#selected_attrs dd").has("div").length==0){
		$("#selected_attrs").hide();	
	}
	var removeParams = $(this).attr("params");
	var myparams = $("#myparams").val();
	var hideType = $("#hideType").val();
	if(myparams!="" && myparams!=null){
		if(myparams.indexOf(",")>0){
			var array = myparams.split(",");
			var hideArray = hideType.split(",");
			for(var i=0;i<array.length;i++){
				if(array[i].indexOf(removeParams)>0){
					array.splice(i,1);
					hideArray.splice(i,1);
				}else{
					continue;
				}
			}
			getSearchParam();
			window.location.href = makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,sort,curpage,array.join(","),hideArray.join(","),searchSource);
		}else{
			reset_all();
		}
		
	}
});	
reset_all=function(){
	 $("#selected_attrs div").remove();
     $("#selected_attrs").hide();
	 $("#select dl").not($("#selected_attrs")).show();
	 
	 getSearchParam();
	 window.location.href = makeUrl(_webroot,keyword,store_id,region_code,type_id,brand,sort,curpage,"",null,searchSource);
}
function jsonMenu(url, data) {
    $.ajax({
        url:  url,
        data: data,
        dataType: "json",
        cache: false,
        success: function(data) {
            if (data.success) {
            	var child = (data.data.child);
            	var childsize = child.length;
            	var parent = (data.data.parent);
            	var parentsize = parent.length;
            	var str = "<ul>";
            	if(parent!=null && parent.length>0){
            		for(var i=parentsize-1; i>=0; i--){
            			if(i==0){
                			str+="<li class='tb_suojin0"+parentsize+" tb_list_menu_xz'><a href='"+makeUrl(_webroot,'',store_id,region_code,parent[i].id,brand,sort,1,"",null,searchSource)+"'>·"+parent[i].name+"</a></li>";
                		}else{
                			str+="<li class='tb_suojin0"+parseInt(parseInt(parentsize)-parseInt(i))+"'><a href='"+makeUrl(_webroot,'',store_id,region_code,parent[i].id,brand,sort,1,"",null,searchSource)+"'>·"+parent[i].name+"</a></li>";
                		}
                	}
            	}
            	
            	$.each(child, function(index, val) {
            		str+="<li class='tb_suojin0"+parseInt(parseInt(parentsize)+1)+"'><a href='"+makeUrl(_webroot,'',store_id,region_code,val.id,brand,sort,1,"",null,searchSource)+"'>·"+val.name+"</a></li>";
            		
            	});
            	str+="</ul>";
            	$(".tb_list_menu").append(str);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        } 
    });
}
function noDataSearch(){
	var searchCon =jQuery('#keyword_2').val();
	getSearchParam();
	window.location.href=_webroot+'search/gotoSearch.chtml?sort=0&keyword='+searchCon+'&region_code='+region_code+"&searchSource="+searchSource+'&curpage=1';
	return false;
} 


              
              