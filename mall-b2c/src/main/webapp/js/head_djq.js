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
	var areaCode;
	if(typeof(GLOBAL_AREA_CODE) != 'undefined'  && GLOBAL_AREA_CODE){
		areaCode = GLOBAL_AREA_CODE;
	}else{
		areaCode = jQuery.cookie('areaCode');
	}
	if(null == areaCode){
		areaCode = '0512';
		jQuery.cookie('areaCode',areaCode, {
			expires : 90,
			domain: '12580life.com',
			path:'/'
		});
	}
	return areaCode;
}

function initAreaCode(){
	if(typeof(GLOBAL_AREA_CODE) != 'undefined'  && GLOBAL_AREA_CODE){
		jQuery.cookie('areaCode',GLOBAL_AREA_CODE, {
			expires : 90,
			domain: '12580life.com',
			path:'/'
		});
	}
}
initAreaCode();

var tmp_navicode = 'djq';

document.write(" <script language=\"javascript\" src=\"http://www.12580life.com/common/head.chtml?coding=utf-8&naviCode="+tmp_navicode+"&areaCode="+getAreaCode()+"\" > <\/script>");
