/**
 修改历史：Apr 20, 2010 16:18:29 PM majk@c-platform.com
 修改说明：添加js脚本注释
*/
var g_agt = navigator.userAgent.toLowerCase(); //获得浏览器的报头
var is_opera = (g_agt.indexOf("opera") != -1); //判断浏览器的是否为opera
var g_title = "";
var g_iframeno = 0;

//返回参数为s的对象
function exist(s){
	return document.getElementById(s)!=null;
}
//把html赋给参数idname
function myInnerHTML(idname, html){
	if (exist(idname)){
		document.getElementById(idname).innerHTML = html;
	}
}
//生成一个对话框
function dialog(v_w, v_h, v_title /*, v_needhead*/){
	var width = v_w;
	var height = v_h;
	var title = v_title;
	g_title = title;
	
	var sBox = '\
		<div id="dialogBox" style="display:none;z-index:19999;width:'+width+'px;">\
			<div class=ts460 style="position:absolute;top:0px;"><img src="../images/h460_t.gif" width="'+width+'" height="8" /></div>\
			<div style="position:absolute;height:'+height+'px;top:8px;" >\
			<table border="0" cellpadding="0" cellspacing="0">\
			<tr style="height:'+(height)+'px;"><td style="background:#000000;width:7px;filter:alpha(opacity=40); -moz-opacity:0.4;"></td>\
			<td style="width:'+(width-14)+'px;">\
				<div style="border:1px solid #565656;">\
				<table width="100%" border="0" cellpadding="0" cellspacing="0">\
				';
	if(typeof arguments[3] != "undefined"){
		var sClose = '<a href="###" onclick="javascript:new dialog().close();"><img src="../images/close.gif" width="10" alt="关闭"></a>';
		if(0 == arguments[3]){
			sBox +=	'\
					<tr valign="top">\
						<td id="dialogBody" style="height:' + (height-2) + 'px" bgcolor="#ffffff"></td>\
					</tr>\
			';
		}else{
			sBox +=	'\
						<tr height="24" bgcolor="#ffffff">\
							<td>\
								<div class="ts3" style="background:#ffffff;border-bottom:1px solid #ffffff;">\
									<div id="dialogBoxTitle" class="ts31" >'+title+'</div>\
									<div id="dialogClose" class="ts32" style="margin-top:5px;margin-right:5px;">' + sClose + '</div>\
								</div>\
							</td>\
						</tr>\
						<tr valign="top">\
							<td id="dialogBody" style="height:' + (height-28) + 'px" bgcolor="#ffffff"></td>\
						</tr>\
			';
		}
	}else{
		var sClose = '<a href="###" onclick="javascript:new dialog().close();"><b>×</b></a>';
		sBox +=	'\
					<tr height="24" bgcolor="#6795B4">\
						<td>\
							<div class="ts3">\
								<div id="dialogBoxTitle" class="ts31">'+title+'</div>\
								<div id="dialogClose" class="ts32">' + sClose + '</div>\
							</div>\
						</td>\
					</tr>\
					<tr valign="top">\
						<td id="dialogBody" style="height:' + (height-28) + 'px" bgcolor="#ffffff"></td>\
					</tr>\
		';
	}
		
	sBox += '\
				</table>\
				</div>\
			</td>\
			<td style="background:#000000;width:7px;filter:alpha(opacity=40); -moz-opacity:0.4;"></td></tr>\
			</table>\
			</div>\
			<div class=ts460 style="position:absolute;top:'+(parseInt(height)+8)+'px;"><img src="../images/h460_b.gif" width="'+width+'" height="8" /></div>\
		</div><div id="dialogBoxShadow" style="display:none;z-index:19998;">\
		<iframe style="position: absolute; z-index: -1; width: 100%; height: 100%; top: 0;left: 0; scrolling: no;filter:alpha(opacity=0);opacity:0.0"></iframe>\
		</div>\
	';
	var sIfram = '\
		<iframe id="dialogIframBG" name="dialogIframBG" frameborder="0" marginheight="0" marginwidth="0" hspace="0" vspace="0" scrolling="no" style="position:absolute;z-index:19997;display:none;"></iframe>\
	';
	
	var sBG = '\
		<div id="dialogBoxBG" style="border:solid 0px;position:absolute;top:0px;left:0px;width:100%;height:100%;"></div>\
	';
	
	this.init = function(){
		var dialogCase = document.getElementById("dialogCase");
		dialogCase ? dialogCase.parentNode.removeChild(dialogCase) : function(){};
		var oDiv = document.createElement('span');
		oDiv.id = "dialogCase";
		if (!is_opera){
			oDiv.innerHTML = sBG + sIfram + sBox;
		}else{
			oDiv.innerHTML = sBG + sBox;
		}
		document.body.appendChild(oDiv);
	}

	this.open = function(_sUrl){		
		this.show();
		var openIframe = "<iframe width='100%' height='100%' name='iframe_parent' id='iframe_parent' src='" + _sUrl + "' frameborder='0' scrolling='no'></iframe>";
		myInnerHTML('dialogBody', openIframe);
	}

	this.show = function(){
		this.middle('dialogBox');
		var dialogIframBG = document.getElementById("dialogIframBG");
		if (dialogIframBG){
			var dialogBox = document.getElementById("dialogBox");
			dialogIframBG.style.top = dialogBox.style.top;
			dialogIframBG.style.left = dialogBox.style.left;
			dialogIframBG.style.width = dialogBox.offsetWidth + "px";
			dialogIframBG.style.height = dialogBox.offsetHeight + "px";
			dialogIframBG.style.display = 'block';
		}
		if (!is_opera) {
			this.shadow();
		}
	}
	
	this.reset = function()
	{
		this.close();
	}

	this.close = function(){
		if (window.removeEventListener){
			window.removeEventListener('resize', this.event_b, false);
			window.removeEventListener('scroll', this.event_b, false);
		}else if (window.detachEvent){
			try {
				window.detachEvent('onresize', this.event_b);
				window.detachEvent('onscroll', this.event_b);
			} catch (e) {
			
			}
		}
		var dialogIframBG = document.getElementById("dialogIframBG");
		if (dialogIframBG) {
			dialogIframBG.style.display = 'none';
		}
		document.getElementById("dialogBox").style.display='none';
		document.getElementById("dialogBoxBG").style.display='none';
		document.getElementById("dialogBoxShadow").style.display = "none";
		if (typeof(parent.onDialogClose) == "function"){
			parent.onDialogClose(document.getElementById("dialogBoxTitle").innerHTML);
		}
	}

	this.shadow = function(){
		this.event_b_show();
		if (window.attachEvent){
			window.attachEvent('onresize', this.event_b);
			window.attachEvent('onscroll', this.event_b);
		}else{
			window.addEventListener('resize', this.event_b, false);
			window.addEventListener('scroll', this.event_b, false);
		}
	}
	
	this.event_b = function(){
		var oShadow = document.getElementById("dialogBoxShadow");
		if (oShadow.style.display != "none"){
			if (this.event_b_show){
				this.event_b_show();
			}
		}
	}
	
	this.event_b_show = function(){
		var oShadow = document.getElementById("dialogBoxShadow");
		oShadow['style']['position'] = "absolute";
		oShadow['style']['display']	= "";		
		oShadow['style']['opacity']	= "0.2";
		oShadow['style']['filter'] = "alpha(opacity=20)";
		oShadow['style']['background']	= "#000";
		var sClientWidth = parent ? parent.document.body.offsetWidth : document.body.offsetWidth;
		var sClientHeight = parent ? parent.document.body.offsetHeight : document.body.offsetHeight;
		var sScrollTop = parent ? (parent.document.body.scrollTop+parent.document.documentElement.scrollTop) : (document.body.scrollTop+document.documentElement.scrollTop);
		oShadow['style']['top'] = '0px';
		oShadow['style']['left'] = '0px';
		oShadow['style']['width'] = sClientWidth + "px";
		oShadow['style']['height'] = (sClientHeight + sScrollTop) + "px";
	}

	this.middle = function(_sId){
		var oSid = document.getElementById(_sId);
		oSid['style']['display'] = '';
		oSid['style']['position'] = "absolute";
		var sClientWidth = parent.document.body.clientWidth;
		var sClientHeight = parent.document.body.clientHeight;
		var sScrollTop = parent.document.body.scrollTop+parent.document.documentElement.scrollTop;
		var sleft = (sClientWidth - document.getElementById(_sId).offsetWidth) / 2;
		var iTop = sScrollTop + 200;
		var sTop = iTop > 0 ? iTop : 0;
		oSid['style']['left'] = sleft + "px";
		oSid['style']['top'] = sTop + "px";
	}
}
//打开一个对话框窗口
function openWindow(_sUrl, _sWidth, _sHeight, _sTitle /*,_sNeedHead*/){
	if(typeof arguments[4] != "undefined"){
		var oEdit = new dialog(_sWidth, _sHeight, _sTitle, arguments[4]);
	}else{
		var oEdit = new dialog(_sWidth, _sHeight, _sTitle);
	}
	oEdit.init();
	oEdit.open(_sUrl);
}
//生成一个iframe窗口
function openAlert(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction){
	return _openAlert(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction, "");
}

function openAlertBlue(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction){
	var excss = '.rbs1{border:1px solid #d7e7fe; float:left;}\n' +
'.rb1-12,.rb2-12{height:23px; color:#fff; font-size:12px; background:#355582; padding:3px 5px; border-left:1px solid #fff; border-top:1px solid #fff; border-right:1px solid #6a6a6a; border-bottom:1px solid #6a6a6a; cursor:pointer;}\n' +
'.rb2-12{background:#355582;}\n';
	return _openAlert(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction, excss);
}

function _openAlert(_sWord, _sButton , _sWidth, _sHeight, _sTitle , _sAction, _excss){
	var oEdit = new dialog(_sWidth, _sHeight, _sTitle);
	oEdit.init();
	oEdit.show();
	var framename = "iframe_parent_" + g_iframeno++;
	var openIframe = "<iframe width='100%' height='100%' name='"+framename+"' id='"+framename+"' src='' frameborder='0' scrolling='no'></iframe>";
	myInnerHTML('dialogBody', openIframe);
	var iframe = window.frames[framename];
	iframe.document.open();
	iframe.document.write('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">\n');
	iframe.document.write('<html xmlns="http://www.w3.org/1999/xhtml">\n');
	iframe.document.write('<head>\n');
	iframe.document.write('<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />\n');
	if (_excss && _excss.length){
		iframe.document.write('<style>\n');
		iframe.document.write(_excss + '\n');
		iframe.document.write('</style>\n');
	}
	iframe.document.write('</head>\n');
	iframe.document.write('<body>\n');
	if(_sAction == undefined){
		_sAction = "new parent.dialog().reset();";
	}else{
		document.getElementById("dialogClose").childNodes[0].onclick=function(){eval(_sAction)};
	}
	iframe.document.write(alertHtml(_sWord , _sButton , _sAction)+'\n');
	iframe.document.write('</body>\n');
	iframe.document.write('</html>\n');
	iframe.document.close();
}
//生成一个div
function alertHtml(_sWord , _sButton , _sAction){
	var html = "";
	var html = '<style>\
      div {background:#fff;font-family:\'lucida grande\',tahoma,helvetica,arial,\'bitstream vera sans\',sans-serif; font-size:12px;}\
      body {margin:0;padding:0;background-color:#FFF;}\
      .rb1-12,.rb2-12{height:23px; color:#fff; font-size:12px; background:#40A1E8; padding:3px 5px; border-left:1px solid #fff; border-top:1px solid #fff; border-right:1px solid #6a6a6a; border-bottom:1px solid #6a6a6a; cursor:pointer;}\
      .rb2-12{background:#40A1E8;}\
      </style>\
      <div style="margin-top:10px;padding:5px 5px;">\
			<div style="text-align:center;padding-top:0;border-top:1px solid #D8DFEA; border-bottom:1px solid #D8DFEA; padding:10px 0 5px 0; margin:0px 0;">\
				<div style="float:left;"><img src="../images/gt.gif"></div>\
					<div style="height:50px;">\
						<table style="height:100%;width:100%"><tr><td style="vertical-align:middle;">\
					 		'+_sWord+'\
					 	</td></tr></table>\
					</div>\
				<div style="clear:both;"></div>\
			</div>\
			<div style="text-align:center; padding:5px;float:right; clear:right;">\
				<div style="border:1px solid #f9a4b2; float:left;"><input type="button" value="'+_sButton+'" title="'+_sButton+'" class="rb1-12" onmouseover="this.className=\'rb2-12\';" onmouseout="this.className=\'rb1-12\';" onclick="javascript:'+_sAction+'" /></div>\
				<div style="clear:both;"></div>\
			</div>\
		   </div>';
	return html;
}
	

	//生成一个iframe窗口
	function openDivHtml(_sWord, _sButton,_cButton , _sWidth, _sHeight, _sTitle , _sAction,_cAction){
		return _openDivHtml(_sWord, _sButton, _cButton, _sWidth, _sHeight, _sTitle , _sAction, _cAction, "");
	}

	function _openDivHtml(_sWord, _sButton, _cButton, _sWidth, _sHeight, _sTitle , _sAction,_cAction,  _excss){
		var oEdit = new dialog(_sWidth, _sHeight, _sTitle);
		oEdit.init();
		oEdit.show();
		var framename = "iframe_parent_" + g_iframeno++;
		var openIframe = "<iframe width='100%' height='100%' name='"+framename+"' id='"+framename+"' src='' frameborder='0' scrolling='no'></iframe>";
		myInnerHTML('dialogBody', openIframe);
		var iframe = window.frames[framename];
		iframe.document.open();
		iframe.document.write('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">\n');
		iframe.document.write('<html xmlns="http://www.w3.org/1999/xhtml">\n');
		iframe.document.write('<head>\n');
		iframe.document.write('<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />\n');
		if (_excss && _excss.length){
			iframe.document.write('<style>\n');
			iframe.document.write(_excss + '\n');
			iframe.document.write('</style>\n');
		}
		iframe.document.write('</head>\n');
		iframe.document.write('<body>\n');
		if(_sAction == undefined){
			_sAction = "new parent.dialog().reset();";
		}else{
			document.getElementById("dialogClose").childNodes[0].onclick=function(){eval(_sAction)};
		}
		
		iframe.document.write(divHtml(_sWord , _sButton, _cButton , _sAction, _cAction)+'\n');
		iframe.document.write('</body>\n');
		iframe.document.write('</html>\n');
		iframe.document.close();
		return iframe;
	}
	//生成一个div,没有图片
	function divHtml(_sWord , _sButton ,_cButton, _sAction,_cAction){
		var html = "";
		var html = '<style>\
	      div {background:#fff;font-family:\'lucida grande\',tahoma,helvetica,arial,\'bitstream vera sans\',sans-serif; font-size:12px;}\
	      body {margin:0;padding:0;background-color:#FFF;}\
	      .rb1-12,.rb2-12{height:23px; color:#fff; font-size:12px; background:#40A1E8; padding:3px 5px; border-left:1px solid #fff; border-top:1px solid #fff; border-right:1px solid #6a6a6a; border-bottom:1px solid #6a6a6a; cursor:pointer;}\
	      .rb2-12{background:#40A1E8;}\
	      </style>\
	      <div style="margin-top:10px;padding:5px 5px;">\
				<div style="text-align:center;padding-top:0;border-top:1px solid #D8DFEA; border-bottom:1px solid #D8DFEA; padding:10px 0 5px 0; margin:0px 0;">\
						<div style="height:50px;">\
							<table style="height:100%;width:100%"><tr><td style="vertical-align:middle;">\
						 		'+_sWord+'\
						 	</td></tr></table>\
						</div>\
					<div style="clear:both;"></div>\
				</div>\
				<div style="text-align:center; padding:5px;float:right; clear:right;">\
					<div style="border:1px solid #f9a4b2; float:left;"><input type="button" value="'+_sButton+'" title="'+_sButton+'" class="rb1-12" onmouseover="this.className=\'rb2-12\';" onmouseout="this.className=\'rb1-12\';" onclick="javascript:'+_sAction+'" />\
					<input type="button" value="'+_cButton+'" title="'+_cButton+'" class="rb1-12" onmouseover="this.className=\'rb2-12\';" onmouseout="this.className=\'rb1-12\';" onclick="javascript:'+_cAction+'" /></div>\
					<div style="clear:both;"></div>\
				</div>\
			   </div>';
			   
		return html;
	}
