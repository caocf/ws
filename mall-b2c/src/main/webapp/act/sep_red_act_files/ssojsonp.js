/**
 * 自定义的回调函数，如果指定了自定义回调函数，请为该回调函数设定一个入参，该入参为接口响应状态
 **/
var cb = null;
var logoutcb = null;

/**
 * 在使用sso认证前，请先调用此方法以生成本域cookie
 * 一般在用户可能首次访问到的页面加载事件中调用
 * 请调用之前在页面上引入jQuery1.2或以上版本
 * 参数说明
 * callback：自定义的回调函数
 **/
function initLocalCookie(callback){
	cb = callback;
	var url = "http://u.12580life.com/ssoCookieJsonp";//统一认证中心地址
	$.getScript(url); 
	//此后会执行dealResult
}

function ssoLogout(logoutCallBack)
{	
	logoutcb = logoutCallBack;
	//$.cookie('szcmjsSSOCookie',null,{path:'/'});
	//$.cookie('szcmtokenid',null,{path:'/'});
	var url = "http://u.12580life.com/ssoLogoutJsonp";//统一认证中心退出登录jsonp
	$.getScript(url,function(){
		init10086SSOcookie(null,"");    // 退出省中心
	}); 
	//此后会执行dealLogoutResult
}

/**
 * 在使用sso登录后，请先调用此方法以生成省认证中心域cookie
 * 一般在完成登录接口后前台响应用户认证信息后调用
 * 请调用之前在页面上引入jQuery1.2或以上版本
 * 参数说明
 * callback：自定义的回调函数，可为空
 * cookie：sso登录后实体中返回的用户cookie值
 **/

function init10086SSOcookie(callback, cookie){
	cb = callback;
	//先向本地写入cookie
	//$.cookie('szcmjsSSOCookie',cookie,{path:'/'});
	//$.cookie('szcmtokenid',cookie,{path:'/'});
	var url = "http://www.js.10086.cn/jssso/ssoCookieJsonp?co="+cookie;
	$.getScript(url); 
}


/**
 * 处理接口返回结果，如果设定了自定义回调函数，则调用
 * 请调用之前在页面上引入jQuery1.2或以上版本
 **/
function dealResult(res){
	var v=res[0].res;//v为认证中心返回的cookie值
	var logic=false;
	if(v=="0"){
		//alert("sso接口执行失败！");
	}else if (v=="2"){
		alert("sso接口调用失败，错误的入参！");
	}else if (v=="1"){
		//通过jsonp跨越写入认证中心域下cookie成功
		logic=true;
	}else{
		//通过jsonp跨域读取认证中心域下cookie值成功，开始创建本域cookie，此处可以自己调用后台写cookie方法，也可以用js写
		//$.cookie('szcmjsSSOCookie',v,{path:'/'});
		//$.cookie('szcmtokenid',v,{path:'/'});
		logic=true;
	}

	//如果自定义回调函数不为空，则调用
	if (cb!=null && cb!="") {
		//window[cb](logic);
		eval(cb+"(logic)") ;
	}
}


function dealLogoutResult(res){
	var v=res[0].res;
	var logic=false;
	
	if(v=="0")
	{
		logic=true;
	}

	//如果自定义回调函数不为空，则调用
	if (logoutcb!=null && logoutcb!="") 
	{
		eval(logoutcb+"(logic)") ;
	}
}

/**
 * jquery.cookie
 **/
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
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
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', value, expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = cookie.substring(name.length + 1);
                    break;
                }
            }
        }
        return cookieValue;
    }
};