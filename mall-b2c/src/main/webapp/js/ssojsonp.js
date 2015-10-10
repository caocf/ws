// jquery.ssocookie 1.0 (c)2012 ebug
function ssocookie(name, value, options) {
    if (typeof value != 'undefined') {
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString();
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', value, expires, path, domain, secure].join('');
    } else { 
        var cookieValue = null;
        if (document.cookie && document.cookie !== '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = cookie.substring(name.length + 1);
                    break;
                }
            }
        }
        return cookieValue;
    }
};
/**
 * 在使用sso认证前，请先调用此方法以生成本域cookie，并重写集团安全通道cookie
 * 一般在用户可能首次访问到的页面加载事件中调用
 * 请调用之前在页面上引入jQuery1.4或以上版本,并引用jquery.jsonp-2.3.1.min.js
 * 参数说明
 * callback：自定义的回调函数，可为空
 **/
function initLocalCookie(callback){
	jQuery.ajax({
		url:"https://js.ac.10086.cn/jsauth/ssoCookieJsonp",
		dataType:"jsonp",
		timeout: 3000,
		async:false,
		complete: function(xOptions, textStatus){
			if (jQuery.isFunction(callback)) {
				callback.call(this, xOptions, textStatus);
			}
		}
	});
}

/**
 * 在使用sso登录后，请先调用此方法以生成10086.cn域cookie
 * 一般在完成登录接口后前台响应用户认证信息后调用
 * 请调用之前在页面上引入jQuery1.4或以上版本,并引用jquery.jsonp-2.3.1.min.js
 * 参数说明
 * callback：自定义的回调函数，可为空
 * cookie：sso登录后实体中返回的用户cookie值
 **/
function init10086cookie(callback, cookie){
	//先向本地写入cookie
	jQuery.ssocookie('SSOCookie',cookie,{path:'/'});
	jQuery.jsonp({
  		url: "http://www.js.10086.cn/jssso/ssoCookieJsonp?co="+cookie,
  		timeout: 3000,
  		complete: function(xOptions, textStatus){
  			if (callback!==null && callback!==""){window[callback]();}
  		}
	});
}

/**
 * 处理接口返回结果，如果设定了自定义回调函数，则调用
 **/
function dealResult(res){
	var v=res[0].res;
	var localToken = getCookies('LMSH_SID');
	var localTokenBak = getCookies('LMSH_SID_BAK');
	var remoteToken = "";
	var patrn=/^[0-9A-Z]{32}$/; 
	if(v.indexOf("@")!=-1){
		remoteToken = v.substring(0,v.indexOf("@"));
	}
	//alert("localToken:"+localToken+";"+patrn.test(localToken));
    //alert("remoteToken:"+remoteToken+";"+patrn.test(remoteToken));
	//通过jsonp跨域读取10086.cn域下cookie值成功，开始创建本域cookie
	if (patrn.exec(localToken)&&!patrn.exec(remoteToken)){
		ssocookie('LMSH_SID','',{path:'/',domain:'12580life.com'});
		ssocookie('szcmtokenid','',{path:'/',domain:'12580life.com'});
		if(window.location.href.indexOf("pro_sso_sync")==-1){
			window.location=mallDomain+"/login/pro_sso_sync.jsp?mode=logout&backURL="+escape(window.location);//当前页面
			return;
		}
	}
	if(patrn.exec(remoteToken)&&remoteToken!=localTokenBak){
		ssocookie('LMSH_SID_BAK',remoteToken,{path:'/',domain:'12580life.com'});
		ssocookie('LMSH_SID',remoteToken,{path:'/',domain:'12580life.com'});
		ssocookie('szcmtokenid',v,{path:'/',domain:'12580life.com'});
		if(window.location.href.indexOf("pro_sso_sync")==-1){
			window.location=mallDomain+"/login/pro_sso_sync.jsp?mode=change&backURL="+escape(window.location);//当前页面
			return;
		}	
	}
	if(window.location.href.indexOf("pro_sso_sync")!=-1){
		window.location=proBackUrl;
	}
}

function getCookies(name){
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(arr != null)
		return unescape(arr[2]);
	return null;
}

initLocalCookie();