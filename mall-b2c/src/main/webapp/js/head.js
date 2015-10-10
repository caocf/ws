function include_js(path, code) {
	var sobj = document.createElement('script');
	sobj.type = "text/javascript";
	sobj.src = path;
	sobj.charset = code;
	
	//var headobj = document.getElementsByTagName('head')[0];
	//headobj.appendChild(sobj);
	
	var bodyobj = document.getElementsByTagName('body')[0];
	bodyobj.insertBefore(sobj, bodyobj.nextSibling);
}
function include_css(path, code) {
	var fileref = document.createElement("link")
	fileref.rel = "stylesheet";
	fileref.type = "text/css";
	fileref.charset = code;
	fileref.href = path;
	var headobj = document.getElementsByTagName('head')[0];
	headobj.appendChild(fileref);
}

function getAreaCode(){
	var regionCode;
	if(typeof(GLOBAL_REGION_CODE) != 'undefined' && GLOBAL_REGION_CODE){
		regionCode = GLOBAL_REGION_CODE;
	}else{
		regionCode = jQuery.cookie('regionCode');
	}
	if(null == regionCode){
		regionCode = '320500';
		jQuery.cookie('regionCode',regionCode, {
			expires : 90,
			domain: '12580life.com',
			path:'/'
		});
	}
	return regionCode;
}

function getRegionCode(){
	var regionCode;
	if(typeof(GLOBAL_REGION_CODE) != 'undefined' && GLOBAL_REGION_CODE){
		regionCode = GLOBAL_REGION_CODE;
	}else{
		regionCode = jQuery.cookie('regionCode');
	}
	if(null == regionCode){
		regionCode = '320500';
		jQuery.cookie('regionCode',regionCode, {
			expires : 90,
			domain: '12580life.com',
			path:'/'
		});
	}
	return regionCode;
}

var tmp_href = window.location.href;
var tmp_navicode = 'gsc';
if(tmp_href.indexOf("keyword=G3%E5%A4%A9%E5%9C%B0")>0){
	tmp_navicode = 'g3';
}else if(tmp_href.indexOf("groupbuy")>0){
	tmp_navicode = 'tg';
}
if(tmp_href.indexOf("http://mall.12580life.com/search/gotoSearch.chtml") == 0 
		&& tmp_href.indexOf("searchSource=djq") > 0){
	tmp_navicode = 'djq';
}

var tmp_banner = false;
if(tmp_href.indexOf("http://mall.12580life.com/channel/") == 0){
	tmp_banner = true;
}
var navi="";
if(tmp_navicode=='gsc' || tmp_navicode=='tg'){
	navi=tmp_navicode;
}
$.ajax({
    type:'POST',     					
    url: 'http://mall.12580life.com/mall-market/groupbuy/getHeadHtml',
    dataType: 'text',
    data:"navi="+navi,
    cache: false,
    async: false,
    success:function(res){
		 document.write(res);
    },
    error:function(res){
    }
});	
