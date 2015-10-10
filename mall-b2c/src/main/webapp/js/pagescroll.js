function NagivatePage(page){
	document.getElementById("curpage").value = page;
    document.getElementById("form1").submit();
}

function getRedirectURL(param,value,surl){
	if(!surl){
		surl=window.location.href;
	}
	var pos=surl.indexOf("#");
	if(pos!=-1){
		surl=surl.substring(0,pos);
	}
	pos=surl.indexOf("?");
	if(pos==-1||pos==surl.length-1){
		if(param!=null){
			surl+="?"+param+"="+(value?value:"");
		}
		return surl;
	}
	var sNewURL=surl.substring(0,pos)+"?";
	surl=surl.substring(pos+1);
	var aParam=surl.split("&");
	var bParamFound=false;
	var bFirstParam=true;
	for(var i=0;i<aParam.length;i++){
		if(aParam[i].length==0||aParam[i].indexOf("=")==-1){
			continue;
		}
		else if(param==null){
			sNewURL+=(bFirstParam?"":"&")+aParam[i];
		}
		else if(aParam[i].toUpperCase().indexOf(param.toUpperCase()+"=")==0){
			sNewURL+=(bFirstParam?"":"&")+param+"="+(value?value:"");
			bParamFound=true;
		}
		else{
			sNewURL+=(bFirstParam?"":"&")+aParam[i];
		}
		bFirstParam=false;
	}
	if(!bParamFound&&param){
		sNewURL+="&"+param+"="+(value?value:"");
	}
	return sNewURL;
}

// 去除url内的某个参数
function removeParam(name, url) {
    if (!url) {
        url = window.location.href;
    }

    var pos = url.indexOf("#");
	if (pos != -1) {
		url = url.substring(0, pos);
	}

    // 没有参数
    pos = url.indexOf("?");
    if (pos == -1) {
        return url;
    }

    // 问号前面的字符，包括问号
    var prefix = url.substring(0, pos + 1);
    var suffix = url.substring(pos + 1);

    var params = suffix.split("&");
    suffix = "";
    for (var i=0;i<params.length;i++) {
        var param = params[i];

        if (param != name && param.indexOf(name + "=") == -1) {
            if (suffix.length > 0) suffix += "&";

            suffix += param;
        }
    }

    return prefix + suffix;
}

function NagivatePageByInput() {
	var e = document.getElementById("PageTxxx");
	var n;
	try {
		n = parseInt(e.value);
		if (n>=1)
			NagivatePage(n);
	} catch (e) { }
}
