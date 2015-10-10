/*
	彩信用js
	修改: 2010-2-27 huangxj@c-platform.com
	对MmsFrame的send()方法增加参数 auditFlag，用以区分彩信是否需要审核。0需要1不需要。
	修改: 2010-3-4 huangxj@c-platform.com
	在初始化图片按钮的的方法里，增加了初始化“上传头像”图片的代码。
	修改: 2010-10-22 yinkun@c-platform.com
	按照飞信客户端的 发送彩信 界面，调整了彩信编辑界面和功能
*/
var frame; /*彩信核心对象*/
var framePanel; /*左部帧浏览对象*/
var editPanel; /*右部单帧数据编辑对象*/
// 允许的图片正则。
var checkPicExpr = /^(jpg|jpeg|gif|bmp)$/;//未加大头贴功能前可以上传bmp格式。
// 允许的声音正则
var checkSoundExpr = /^(mid|wav|mp3|midi|wma|amr)$/;
// ajax方式检查验证码
function checkYz(yyz){
	var str = '1';
	$.ajax({
		    url: "checkYz.jsp?yz=" + yyz,   
		    type:'GET',
		    data:"text",
		   	async: false,
		    error: function(xhr) {
		    	return false;
		    },
		    success: function(text) {
			    s = text;
		    	s = s.replace(/^\s+|\s+$/g,"");
		    	str  = s;
		    }
  	});
  	
  	return str;
}//end checkYz

// ajax方式验证session
function checkSession(){
	var str = '1';
	$.ajax({   
		    url: "checkSession.jsp",   
		    type:'GET',   
		    data:"text",
		   	async: false,
		    error: function(xhr) {  
		    	return false;
		    },   
		    success: function(text) {   
			    s = text;
		    	s = s.replace(/^\s+|\s+$/g,"");
		    	str  = s;
		    }   
  	});
  	
  	return str;
}//end checkSession

// 弹出窗口显示警告信息
function alt(message, title, callback){
	$('#jquery-alert .alert-message').html(message);
	title = (title) || '提示信息';
	$('#jquery-alert').unbind('dialogclose').bind('dialogclose', function() {
			if (callback) {
				callback.call();
			}
		}
	);
	$('#jquery-alert')
		.dialog('option', 'title', title)
		.dialog('option', 'buttons', {"确定":function(){$(this).dialog("close");}})
		.dialog('open');
}//end alt

// 添加声音
function addListSound(frame,framePanel,editPanel,path , size ,soundName) {
	var s = "";
	$.ajax({   
	    url: "../diy/jpye_upload_sound.jsp?path=" + path,   
	    type:'GET',   
	    data:"text",
	    error: function(xhr) {  
	    	alert('添加声音失败!');
	    	return false;
	    },   
	    success: function(text) {   
	    	s = text;
	    	s = s.replace(/^\s+|\s+$/g,"")
	    	if(s == '1'){
	    		alert('添加声音失败!');
	    		return;
	    	}	
	    	var n = frame.index;
			var o = {
					path:s.split(',')[0] + s.split(',')[1],
					size: size, 
					name: s.split(',')[1], 
					realname:  soundName };
			frame.delSound();
			frame.datas[n].sound = o;
			frame.addSound(o);
			framePanel.refreshShow();
			editPanel.loadSound();  	  
	    }   
  	}); 
  	
  	
	
}//end addListSound

// 初始化需要用到的弹出窗口，包含警告、确认、彩信预览
var bindDialog = function() {
	
  $("#jquery-alert").dialog({autoOpen:false, modal:true, bgiframe:true});
  
  $("#jquery-confirm").dialog({autoOpen:false, modal:true, bgiframe:true});
  
  $('#jquery-preview').dialog({
    width:250,       height:400,  resizable:false,
    autoOpen:false,  modal:true,  bigframe:true,
    close:function(){ $(document).stopTime();frame._preview_cur_frame = 0;}
  });

};

// 图标式按钮的鼠标移动效果
var bindIconBtn = function() {
	$('.lpanel .ui-icon, .rpanel .ui-icon').hover(
  		function () {
  			$(this).addClass('ui-state-hover');
  		},
  		function () {
  			$(this).removeClass("ui-state-hover");
  		}
  	);
};


(function(){

/**
 * 弹出定制警告窗
 * message 信息内容
 * title 窗口标题
 * callback 关闭窗口后的回调函数
 */
var jalert = function(message, title, callback) {
	$('#jquery-alert .alert-message').html(message);
	title = (title) || '提示信息';
	$('#jquery-alert').unbind('dialogclose').bind('dialogclose', function() {
			if (callback) {
			callback.call();
			}
		}
	);
	$('#jquery-alert')
		.dialog('option', 'title', title)
		.dialog('option', 'buttons', {"确定":function(){$(this).dialog("close");}})
		.dialog('open');
}

/**
 * 弹出定制确认窗口
 * message 信息内容
 * title 窗口标题
 * callback 点确定后的回调函数
 */
var jconfirm = function(message, title, callback) {
	$('#jquery-confirm .confirm-message').html(message);
	if (!title || title==null) {
		title = '确认信息';
	}
	$('#jquery-confirm')
		.dialog('option', 'title', title)
		.dialog('option', 'buttons', {"取消":function(){	$(this).dialog("close");},"确定":function(){callback.call();	$(this).dialog("close");}})
		.dialog('open');
}

// 一些提醒消息的字符串资源数据
var messageSource = {
	incorrectPictureType : '请选择正确的图片文件 <div style="margin-top:5px;font-size:11px;">支持的格式包含jpg、jpeg、gif、bmp</div>',
	incorrectSoundType : '请选择正确的声音文件 <div style="margin-top:5px;font-size:11px;">支持的格式包含mid、wav、mp3、midi、wma、amr</div>',
	uploadWaiting : '<h1>正在处理中,请稍后...</h1>',
	defaultTextTip : '输入文本（1500个汉字以内）',
	defaultPicTip : '图片的大小不超过80K',
	defaultSoundTip : '声音的大小不超过80K',
	maxTextTip : '输入的文本过长，请控制输入长度'
};

/**
 * 格式化上传文件的文件名，保证不包含一些特殊字符，不超过特定长度
 * realname 传入文件名
 * return 格式化后的文件名，长度不超过20
 */
var formatRealname = function(realname) {
	if (realname.length>20) {
		return realname.substr(0, 15) + '...' + realname.substr(realname.length-4, 4);
	} else { 
		return realname; 
	}
};

/**
 * 得到文件扩展名，不包含‘点号’
 */
var getExt = function(file) {
	return (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';
};

/**
 * 去除字符串左右空白字符
 */
var trim = function(str) {
	return str.replace(/^\s+/, '').replace(/\s+$/, '');
};

// 获得文本的大小,中文算2个单位长度
var getExactSize = function(str) {
	var len = 0;
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 127 || str.charCodeAt(i) == 94) {
			len += 2;
		} else {
			len++;
		}
	}
	return len;
};

// 截取超过长度的字符串，返回截取后的字符串。 中文算2个长度单位
var cutString = function(str, len) { 
    var l=0; var strl=str.length;
    for (var i=0; i<strl; i++) {
    	singlc = str.charAt(i).toString();
    	if (singlc.match(/[^\x00-\xff]/g) != null) {
    		l += 2;
    	} else {
    		l ++;
    	}
    	if (l>len) {
    		break;
    	}
    }
    return str.substring(0, i);
};

// 转化字节到千字节 例如: 12345 -> 12.34
var sizeShow = function(num) {
	var kb = num/1024;
	kb = new Number(kb).toFixed(2);
	for (var i=kb.length-1; i>0; i--) {
		var curChar = kb.charAt(i);
		if (curChar=='0' || curChar=='.'){
			kb = kb.substring(0, i);
		}
		if (curChar=='.'){
			break;
		}
	}
	return kb;
};

/**
 * =====================
 * 每一页的彩信对象操作封装
 * =====================
 * */
EditPanel = function() {
	this.playAudio = 0;
	this.initInterval();this.initPic();this.initSound();this.initText();
}

EditPanel.prototype = {

/** 初始化html元素的事件绑定 */
	
	// 帧显示时间元素
	initInterval : function() {
		var interval = $('#intervalContainer');
		interval.bind('keyup', function() {
			$(this).val($(this).val().replace(/[^\d]/g,''));
		}).bind('blur', function() {
			if ($(this).val()=='' || Math.abs($(this).val())==0) {
      			$(this).val(5);
    		}
    		frame.setInterval(Math.abs($(this).val()));
		});
	},
	
	// 文本显示区域
	initText : function() {
		var text = $('#textContainer');
		if (trim(text.val())==''){
			text.val(messageSource.defaultTextTip);
		}
		
		text.bind('focus', function() {
			if (trim($(this).val())==messageSource.defaultTextTip) {
				$(this).val('');
			}
  		}).bind('keyup', function() {
			if (getExactSize($(this).val())>3000) {
				//jalert(messageSource.maxTextTip, null, function(){$('#textContainer').focus();});
				$('#textContainer').focus();
				$('#content_error').show();
				$(this).val(cutString($(this).val(), 3000));
			} else {
				if (trim($(this).val())!=messageSource.defaultTextTip){
					frame.updateText(trim($(this).val()));
				}
			}
  		}).bind('blur', function() {
  			if (trim($(this).val())==''){
  				$(this).val(messageSource.defaultTextTip);
  			}
  			if (trim($(this).val())!=messageSource.defaultTextTip){
  				frame.updateText($(this).val());
  			}
  			framePanel.refreshShow();
  		});
	},
	
	// 图片显示区域
	initPic : function() {
		var $$$this = this;
		// 图片上传前检查后缀名，上传完成后更新帧数据。
		new Ajax_upload('ik_picup_btn', { //这段代码使得“上传图片”按钮具有了弹出上传窗口上传图片的功能。
			action: 'single_upload.do',
			name: 'mmsupfile',//上传的文件的名字
			data: { type : 'pic',
					mmsId : mmsId
				 },//需要传递的额外参数
			onSubmit: function(file, ext) {//file是文件名，ext是扩展名
				if (!(ext && checkPicExpr.test(ext))){
					jalert(messageSource.incorrectPictureType);
					return false;
				}
				$.blockUI({ message: messageSource.uploadWaiting });
			},
			onComplete : function(file, response) {
				$.unblockUI();
				$(".blockUI").fadeOut("slow");
				var o;
				try {
					o = $.evalJSON(response);//将json数据转成javascript对象
				} catch (e) {
					alert(e.description);
				}
				if (o.success) { 		
					frame.delPic();
					frame.addPic(o);
					framePanel.refreshShow();//控制左边的小缩略图的刷新
					$$$this.loadPic();
				} else {
					jalert(o.errorMessage);
				}
				
			}
		});
		
		// 裁减操作按钮，只有非gif格式图片可裁减
		$('#btn-picedit').bind("click", function() {
			var img = $('#cureditpic');
			//alert(img.data('dtt').url)
			if (getExt(img.attr('src')) == 'gif'){
				img.data('dtt', null); // 删除大头贴信息
				$('#ik_mms_pic').dialog("close"); // 关闭弹窗
				return;
			}
			var c = img.data('c')
				, size = img.data('ik_size')
				, url = img.data('ik_dtt');
			if (!c && !size['resize'] && !url){
				img.data('dtt', null); // 删除大头贴信息
				$('#ik_mms_pic').dialog("close"); // 关闭弹窗
				return;
			}
			//if (parseInt(c.w)>0 && parseInt(c.h)>0) {
				
			if (true) {
				
				jconfirm('确认裁减当前图片，原来的图片将丢失', null, function() {
				
					var postinfo = frame.data().pic;
					if(c){
						postinfo.croph = c.h;
						postinfo.cropw = c.w;
						postinfo.cropx = c.x;
						postinfo.cropy = c.y;
					}
					if(size['resize']){
						//postinfo['resize'] = size['resize']
						postinfo['width'] = size['width'] + Math.floor(size['width']*size['resize']*0.1)
						postinfo['height'] = size['height'] + Math.floor(size['height']*size['resize']*0.1)
					}
					if(url){
						postinfo['url'] = url['url']
					}
					$.ajax({ 
						type: "get", 
						url: "../content/jpye_cxqf_single_crop", 
						data: postinfo,
						success: function(msg) {
							$.unblockUI();
							try {
								var o = $.evalJSON(msg);
							} catch (e) {
								alert(e.description);
							}
							if (o.success) {
								frame.delPic();
								frame.addPic(o);
								framePanel.refreshShow();
								$$$this.loadPic();
							} else {
								jalert(o.msg);
							}
						},
						beforeSend: function(XMLHttpRequest) {
							XMLHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=UTF-8');
							$.blockUI({ message: messageSource.uploadWaiting });
						}
					});
					img.data('dtt', null); // 删除大头贴信息
					$('#ik_mms_pic').dialog("close"); // 关闭弹窗
				});
			};
		});
		
		$('#btn-picdel').bind('click', function() {
			var img = $('#cureditpic');
			jconfirm('确认删除当前图片?', null, function() {
				frame.delPic();
				$('#picEditor').empty();
				$('#ik_picedit_edit').empty(); // 删除编辑区域的图片 add by ikun
				editPanel._showPicName();
				framePanel.refreshShow();
				$('#picContainer .split, #picContainer .ricon').addClass('ui-hidden');
			});
		});
		
	},
	// 声音显示区域
	initSound : function() {
		$$$this = this;
		new Ajax_upload('sound-upload-btn', {
			action: 'single_upload.do',
			name: 'mmsupfile',
			data: { type : 'sound' },
			onSubmit: function(file, ext) {
				if (!(ext && checkSoundExpr.test(ext))){
        			jalert(messageSource.incorrectSoundType);
        			return false;
      			}
				$.blockUI({ message: messageSource.uploadWaiting });
			},
			onComplete : function(file, response) {
				$.unblockUI();
				try {
					var o = $.evalJSON(response);
				} catch (e) {
					alert(e.description);
				}
				
				if (o.success) {
					frame.delSound();
					frame.addSound(o);
					framePanel.refreshShow();
					$$$this.loadSound();
				} else jalert(o.errorMessage);
			}
		});
		//mmsPath是从上层jsp，比如diy-inc.jsp里传过来的
		$('#btn-soundplay').bind('click', function() {
			var sound = frame.data().sound;
			var hasSound = (sound!=undefined);
			if (hasSound) {
				editPanel._playPauseSound(mmsPath + sound.path);
			}
		});
		
		$('#btn-sounddel').bind('click', function() {
			var sound = frame.data().sound;
			jconfirm('确认删除当前声音?', null, function() {
				frame.delSound();
				editPanel._playPauseSound();
				editPanel._showSoundName();
				framePanel.refreshShow();
				$('#soundContainer .split, #soundContainer .ricon').addClass('ui-hidden');
			});			
		});
	},

/** 结束初始化html元素的事件绑定 */
	
/** 供调用方法 */
	
	// 载入当前帧图片数据
	loadPic : function() {
		var isddt = $('#cureditpic').data('ik_isdtt');
		if(isddt){
			var ddturl = $('#cureditpic').attr('src');
			alert(ddturl);
		}
		var o = frame.data().pic;
		$('#picContainer .split, #picContainer .ricon').addClass('ui-hidden');
		var picEditor = $('#ik_picedit_edit').empty()
			, picPreview = $('#picEditor').empty();
		this._showPicName(o ? (o.realname ? o.realname : o.name) : o);
		if (o) {
			var marginTop = 0, marginLeft = 0;
			if (o.w < 430) {
				marginLeft = (430 - o.w) / 2;
			}
			if (o.h < 340) {
				marginTop = (340 - o.h) / 2;
			}
			var cut_height = o.h;
			var cut_width = o.w;
			if(o.w>508 && o.h<310) {
				cut_width = 508;
				cut_height = (508*o.h)/o.w;
			}
			
			if(o.w<508 && o.h>310) {
				cut_width = (310*o.w)/o.h;
				cut_height = 310;
			}
			
			if(o.w>508 && o.h>310) {
				if(o.w/o.h>508/310) {
					cut_height = 310;
					cut_width = (310*o.w)/o.h;
				}else if(o.w/o.h<508/310) {
					cut_width = 508;
					cut_height = (508*o.h)/o.w;
				}else {
					cut_height = 310;
					cut_width = 508;
				}
			}
			
			$('<div>').css('margin-top', marginTop + 'px').css('margin-left', marginLeft + 'px').appendTo(picEditor);
			$('<img id="cureditpic">').attr('src', mmsPath + o.path).attr('width', cut_width).attr('height', cut_height).appendTo(picEditor.find('div'));
			$('<div>').css('margin-top', marginTop + 'px').css('margin-left', marginLeft + 'px').appendTo(picPreview);
			$('<img>').attr('src', mmsPath + o.path).attr('width', o.w).attr('height', o.h).appendTo(picPreview.find('div'));
			/*if (getExt($('#cureditpic').attr('src')) != 'gif' && o.w>50 && o.h>50) {
				ik_editpic = $.Jcrop('#cureditpic', {
					bgColor: 'red'
					, minSize:[50,50]
					//, maxSize:[176,220]
					//, allowResize:false
					, onSelect: function(c) {
						$('#cureditpic').data('c', c);
					}
				}); // 全局 ik_editpic add by ikun
				//$('#btn-picedit').removeClass('ui-hidden');
			}*/
			$('#btn-picdel, #picContainer .split').removeClass('ui-hidden');
			$('#cureditpic').data('ik_size', {'width':o.w, 'height':o.h, 'resize':0});
			
			// 是否显示编辑区域 add by ikun
			var $editnav = $('#ik_picedit_nav');
			/*if (getExt($('#cureditpic').attr('src')) == 'gif'){
				$editnav.hide();
			}else{
				$editnav.show();
			}*/

			if(isddt){
				ik_editpic.dtt(ddturl);
				$('#cureditpic').data('ik_dtt', {'url':ddturl});
				ik_editpic.setOptions({
					allowResize: false
					,minSize:[176,220]
					,maxSize:[176,220]
				});
			}
			$('#cureditpic').data('word', {'picleft':marginLeft, 'pictop':marginTop});/*添加文字 图片位置信息*/
		}
	}, 
	
	// 载入当前帧声音数据
	loadSound : function() {
		var o = frame.data().sound;
		$('#soundContainer .split, #soundContainer .ricon').addClass('ui-hidden');
		this._showSoundName(o ? (o.realname ? o.realname : o.name) : o);
		this._playPauseSound();
		if (o) {
			$('#soundContainer .split, #soundContainer .ricon').removeClass('ui-hidden');
		}
	},
	
	// 文本
	loadText : function() {
		var o = frame.data().text;
		$('#textContainer').val( ($.trim(o.value)=='') ? messageSource.defaultTextTip : o.value );
	},
	
	// 帧显示时间
	loadInterval : function() {
		$('#intervalContainer').val(frame.data().showtime);
	},
	
	// 全部载入
	loadFrame : function() {
		var startTime = new Date().getTime(); 
		this.loadPic();
		var endTime = new Date().getTime();
		
		startTime = new Date().getTime(); 
		this.loadSound();
		endTime = new Date().getTime();
		
		startTime = new Date().getTime(); 
		this.loadText();
		endTime = new Date().getTime();
		
		startTime = new Date().getTime(); 
		this.loadInterval();
		endTime = new Date().getTime();
		
	},
	
	// 播放暂停音乐
	_playPauseSound : function(filePath) {
		if (this.playAudio==0 && filePath!=undefined) {
			// 使用 <bgsound> 标签实现播放，只支持mid, wav, mp3
		    document.getElementById('bgsound').src = filePath;
		    $('#btn-soundplay').removeClass('ui-icon-play ui-icon-pause').addClass('ui-icon-pause');
			this.playAudio = 1;
		} else {
			document.getElementById('bgsound').src = '';
		    $('#btn-soundplay').removeClass('ui-icon-play ui-icon-pause').addClass('ui-icon-play');
			this.playAudio = 0;
		}
	},
	
	// 剪裁图片后更新数据 
	_onCropped : function(o) {
		frame.delPic();
		editPanel.loadPic(o);
	},
	
	// 显示上传图片原来的文件名
	_showPicName : function(realname) {
		var lbPic = $('#lb-pic');
		if (realname) {
			lbPic.text(formatRealname(realname)).css('color','#2160a3');
		} else {
			lbPic.text(messageSource.defaultPicTip).css('color','');
		}
	},
	
	// 显示上传声音原来的文件名
	_showSoundName : function(realname) {
		var lbSound = $('#lb-sound');
		if (realname) {
			lbSound.text(formatRealname(realname)).css('color','#2160a3');
		} else {
			lbSound.text(messageSource.defaultSoundTip).css('color','');
		}
	}
}



/**
 * =================
 * 左边的帧操作对象封装
 * =================
 * */
FramePanel = function() {
	
	this._panel = $('#framePanel');

	// 绑定插件scorllable，可滚动显示帧信息
	this._scroller = this._panel.scrollable({vertical:true,	api:true, size:5, hoverClass: 'hover', navi:'div.framenavi', clickable:false});
	
	this._empty_frame_html = 
		'<div class="frameitem">' +
		'<div class="prebox prebgno"></div>' +
		'<div class="elmbox">' +
		'<span class="elmtextno"></span>' +
		'<span class="elmsoundno"></span>' +
		'<span class="elmpicno"></span>' +
		'</div>' +
		'</div>';
	
	// 限制彩信总帧数
	$('.topfun li:nth-child(1)').click(function() {
		if (frame.count()<25){
			framePanel.add();
		} else {
			jalert('彩信编辑超过25帧，不能再新建页');
		}
	});
	// 删除彩信某帧内容
	$('.topfun li:nth-child(2)').click(function() {
		if (frame.count()<=1){
			return false;
		}
		jconfirm('您确定删除该页内容吗？', null, function() {
			 framePanel.del();
		});
	});
	$('.topfun li:nth-child(3)').click(function() {framePanel.down();});
	$('.topfun li:nth-child(4)').click(function() {framePanel.up();});
	
	// 清空彩信内容。如果载入的彩信清空，则再保存会新建一条彩信。
	$('.topfun li:nth-child(5)').click(function() {
		jconfirm('您确定要清空全部彩信内容吗？', null, function() {
			framePanel.empty();
		});
	});
	
}

FramePanel.prototype = {

	// 模拟点击时间，点击每帧后，选择的帧样式特殊，表示被选择，且为当前帧
	click : function(index) {
		this._scroller.click(index);
	},
	
	// 重新刷新
	reload : function() {
		return this._scroller.reload();
	},
	
	// 获得所有的帧显示对象
	items : function() {
		return this._scroller.getItems();
	},
	
	// 清空帧
	empty : function() {
		$('#loadBoxId').val('');
		this.items().remove();
		frame.reset();
		this.add();
		this.reload();
	},
	
	/**
	 * 添加帧，
	 * o 帧的json对象，未定义表示增加新帧
	 * index 插入位置，未定义插入到最后
	 * silent 是否牵动界面元素， true表示不更新界面元素（用于批量加入帧）
	 */
	add : function(o, index, silent) {
		frame.add(o, index);
		var newframe = $(this._empty_frame_html)
			.appendTo(this._scroller.getItemWrap())
			.bind('click', {fp: this}, function(event) {
				var curIndex = $(this).parent().children().index($(this));
				event.data.fp.click(curIndex);
				if (curIndex != frame.index) {
					frame.pos(curIndex);
					editPanel.loadFrame();
				}
			});
		
		if (!silent) {
			this.reload();
			newframe.click();
			editPanel.loadFrame();
		}
		this.refreshShow(frame.index);
	},
	
	// 删除帧
	del : function() {
		var items = this.items();
		items.eq(frame.index).remove();
		frame.del();
		this.reload().click(frame.index);
		editPanel.loadFrame();
		this.refreshShow(frame.index);
	},
	
	// 下移一帧
	down : function() {
		var items = this.items();
		if (items.length < 2 || frame.index<0 || frame.index==items.length-1) {
			return false;
		}
		items.eq(frame.index + 1).after(items.eq(frame.index));
		frame.change(frame.index, frame.index + 1);
		this.reload().seekTo(frame.index);
	},
	
	// 上移一帧
	up : function() {
		var items = this.items();
		if (items.length < 2 || frame.index<=0) {
			return false;
		}
		items.eq(frame.index - 1).before(items.eq(frame.index));
		frame.change(frame.index, frame.index - 1);
		this.reload().seekTo(frame.index);
	},

	// 刷新当前帧显示预览
	// index 刷新的帧索引，可以为空表示刷新当前选择帧
	refreshShow : function(index) {
		if (index==undefined){
			index = frame.index;
		}
		var data = frame.data(index);
		var preShow = this.items().eq(index).find('div.prebox');
		var icons = this.items().eq(index).find('span');
		
		var hasText = ($.trim(data.text.value)!='');
		icons.eq(0).toggleClass('elmtextyes', hasText).toggleClass('elmtextno', !hasText);
		
		var hasSound = (data.sound!=undefined);
		icons.eq(1).toggleClass('elmsoundyes', hasSound).toggleClass('elmsoundno', !hasSound);
		
		var hasPic = (data.pic!=undefined);
		icons.eq(2).toggleClass('elmpicyes', hasPic).toggleClass('elmpicno', !hasPic);
		
		preShow.removeClass('prebgno prebgpic prebgsound prebgtext').empty();
		if (data.pic!=undefined) {			
			var o = data.pic;
			
			var sw = o.w; var sh = o.h;
			if (sw>sh) {
				if (sw>64) {
					sh=sh/sw*64;sw=64;
				}
			} else {
    			if (sh>64) {
    				sw=sw/sh*64;sh=64;
    			}
    		}
    		var s = 0;
    		if (sh<64){
    			s = (64-sh)/2;
    		}
			$('<img>')
				.attr('src', mmsPath + o.path)
				.css('margin-top', s + 'px')
				.css('margin-left', 0 + 'px')
				.css('height', sh + 'px')
				.css('width', sw + 'px')
				.appendTo(preShow.empty().addClass('prebgpic'));
		} else if (data.sound!=undefined) {
			preShow.addClass('prebgsound');
		} else if (data.text.value!='') {
			preShow.addClass('prebgtext');
		} else {
			preShow.addClass('prebgno');
		}
	}
	
}

/**
 * ==============
 * 彩信数据核心对象
 * ==============
 * */
MmsFrame = function(settings) {
	
	// 当前帧索引
	this.index = -1;
	
	// 帧内容数据
	this.datas = [];
	
	// 总大小
	this.totalSize = 0;
	// 默认参数
	var defaults = {
		// 最大允许的彩信大小(字节)
		maxSize : 1024 * 100,
		// 显示当前帧的element
		elmPosition : '#currentFrame',
		// 显示彩信总帧数的element
		elmCount : '#totalFrame',
		// 显示允许最大彩信值的element
		elmMaxSize : '#labelMmsMaxSize',
		// 显示当前彩信大小的element
		elmTotalSize : '#totalSizeShow',
		// 帧数量变化事件回调函数定义
		onFrameCountChange : function() { return true; }
	};
	this.opt = $.extend({}, defaults, settings);
	
	$(this.opt.elmMaxSize).text(sizeShow(this.opt.maxSize) + 'K');
	
	this._on_size_change(0);
	
	this._preview_cur_frame = 0;
}

MmsFrame.prototype = {
	
	// 当前帧数
	count : function() {
		return this.datas.length;
	},
	
	// 改变当前帧索引
	pos : function(n) {
		var pre = this.index;
		if (pre == n) {
			return;
		}
		this.index = n;
		$(this.opt.elmPosition).text(n+1);
	},
	
	// 获取某帧数据，如果不指定返回当前帧数据
	data : function(n) {
		return (n) ? this.datas[n] : this.datas[this.index];
	},
	
	// 重置彩信数据
	reset : function() {
		this.index = -1; this.datas = []; this.totalSize = 0;
		this._on_size_change(0);
		if (this.opt.onFrameCountChange.call(this)){
			this._on_frame_count_change();
		}
	},
	
	// 添加帧, obj:帧对象，n:添加位置，0开始
	add : function(obj, n) {
		if (obj==undefined){
			obj = { showtime : 5, text : {	value : '' } };
		}
		if (obj.text==null){
			obj.text = { value : '' };
		}
		if (n==undefined){
			n = this.count();
		}
		var nn = this.count();
		if (n >= nn){
			n = nn;
		}
		if (n < 0){
			n = 0; // n超过数组边缘设置为边缘值
		}
		this.datas.splice(n, 0, obj);
		this.pos(n);
		this._on_size_change(this._count_size(n));
		if (this.opt.onFrameCountChange.call(this)){
			this._on_frame_count_change();
		}
	},
	
	// 删除帧数据，n:待删除帧位置
	del : function(n) {
		if (n==undefined){
			n = this.index;
		}
		if (this.datas.length < 2 || n<0) {
			return false; 
		}
		var delIndex = n;
		this._on_size_change(-this._count_size(n));
		this.datas.splice(n, 1);
		if (delIndex == this.datas.length){
			this.pos(delIndex - 1);
		}
		if (this.opt.onFrameCountChange.call(this)){
			this._on_frame_count_change();
		}
	},
	
	// 设置某帧的显示时间, v:时间，n:添加位置，0开始
	setInterval : function(v, n) {
		if (n==undefined) {
			n = this.index;
		}		
		this.datas[n].showtime = v;
	},
	
	// 添加帧的图片对象，obj:图片对象，n:添加位置，0开始
	// 图片对象应该为 {name:'String',path:'String',size:Number,w:Number,h:Number}
	addPic : function(obj, n) {
		if (n==undefined){
			n = this.index;
		}
		var o = {
			//path:"http://localhost:8080/data/tmp/20121120192655ZVfoF.png",
			path:obj.path,
			w:obj.w, 
			h:obj.h, 
			size:obj.size, 
			name:obj.name, 
			realname: ((obj.realname) ? obj.realname : obj.name) };
		this.datas[n].pic = o;
		this._on_size_change(o.size);
	},
	
	// 添加帧的声音对象，obj:声音对象，n:添加位置，0开始
	// 声音对象应该为 {name:'String',path:'String',size:Number}
	addSound : function(obj, n) {
		if (n==undefined){
			n = this.index;
		}
		var o = {
			path:obj.path,
			size:obj.size, 
			name:obj.name, 
			realname: ((obj.realname) ? obj.realname : obj.name) };
		this.datas[n].sound = o;
		this._on_size_change(o.size);
	},
	
	// 添加帧的文本对象，obj:文本对象，n:添加位置，0开始
	// 文本对象应该为 {value:'String'}
	updateText : function(val, n) {
		if (n==undefined){
			n = this.index;
		}
		var oldVal = this.datas[n].text.value;
		this.datas[n].text.value = val;
		this._on_size_change(getExactSize(val) - getExactSize(oldVal));
	},
	
	// 删除图片
	delPic : function(n) {
		if (n==undefined){
			n = this.index;
		}
		if (this.datas[n].pic) {
			this._on_size_change(-this.datas[n].pic.size);
			delete this.datas[n].pic;
		}
	},
	
	// 删除声音
	delSound : function(n) {
		if (n==undefined) {
			n = this.index;
		}
		if (this.datas[n].sound) {
			this._on_size_change(-this.datas[n].sound.size);
			delete this.datas[n].sound;
		}
	},
	
	// 变更某帧的位置， before 帧原来位置 after 调换后的位置
	change : function(before, after) {
		this.datas.splice(after, 0, this.datas.splice(before, 1)[0]);
		if (before < after) {
			if (this.index == before) {
				this.pos(after);
			} else if (this.index > before && this.index <= after) {
				this.pos(this.index-1);
			}
		} else {
			if (this.index == before) {
				this.pos(after);
			} else if (this.index < before && this.index >= after) {
				this.pos(this.index+1);
			}
		}
	},
	
	// 计算当前帧占用控件大小
	_count_size : function(n) {
		var picSize = (this.datas[n].pic) ? this.datas[n].pic.size : 0;
		var soundSize = (this.datas[n].sound) ? this.datas[n].sound.size : 0;
		var textSize = getExactSize(this.datas[n].text.value);
		
		if(picSize == 0 && soundSize == 0){
			return textSize;
		}
		
		if(picSize == 0 && textSize == 0){
			return soundSize;
		}
		
		if(soundSize == 0 && textSize == 0){
			return picSize;
		}
		
		return (Number(picSize) + Number(soundSize) + Number(textSize));
	},

	// 大小改变调用方法
	_on_size_change : function(val) {
		val = parseInt(val);
		this.totalSize = this.totalSize + val;
		var s = sizeShow(this.totalSize) + 'K';
		var tss = $(this.opt.elmTotalSize);
		if (tss.length==1) {
			tss.text(s)
			.attr('title', this.totalSize + '字节')
			.css('color', (this.totalSize > this.opt.maxSize) ? 'red' : '');
		}
	},
	
	// 默认的帧个数变化后触发事件
	_on_frame_count_change : function() {
		$(this.opt.elmCount).text(this.count());
	},
	
	// 返回data的json格式字符串 return string
	toHtml : function() {
		return $.toJSON(this.datas);
	},
	
	// 是否允许提交 return true or false
	canSubmit : function() {		
		if (this.totalSize > this.opt.maxSize) {
        	jalert('彩信总大小超过' + sizeShow(this.opt.maxSize) + 'K，请减少内容后再试');
        	return false;
      	}
      	
		for (var i = 0, len = this.datas.length; i < len; ++i) {
			if (!this.datas[i].pic && this.datas[i].text.value == '') {
				jalert('第' + (i + 1) + '页没有录入图片或文本', null, function() {
					framePanel.items().eq(i).click();
				});
				return false;
			}
		}
		return true;
	},
	
	// 从信息保管箱中选择一条彩信载入
	loadFromBox : function() {
		var url = 'tc_mfsx_cxxz_new.jsp';
		var width = 645; var left = ($(window).width() - width) / 2;
		var height = 611; var top = ($(window).height() - height) / 2;
		var param = 'height='+height+',width='+width+',top='+top+',left='+left+',scrollbars=yes';
		window.open(url, 'loadFromBox', param);
	},
	
	// 根据信息保管箱中彩信id载入
	load : function(id) {
		//$.blockUI({ message: messageSource.uploadWaiting });
		$.getJSON('../mms/loadMms/'+id+'?rnd='+Math.random(), function(ret){
  			$.unblockUI();
  			if (!ret.success) {
  				return alert(ret.msg);
  			}
			try {
				$('#mmstitle').val(ret.title);
				$('#loadBoxId').val(id);
				
				framePanel.items().remove();
				frame.reset();
				
				for (var i=0, len=ret.frames.length; i<len; ++i) {
					var f = ret.frames[i];
					framePanel.add(f, i, true);
			    }
			    
			    framePanel.reload();
			    frame.index = -1;
			    framePanel.items().eq(0).click();
			    $('#mmsId').val(id);
				
				if(ret.isSticker){
					$('#cureditpic').data('ik_isdtt', true);
					//alert(ret.isSticker);
				}
			} catch (e) {
				alert('载入彩信数据异常: ' + e.description);
			}
  			
		});
	},
	
	// 打开预览窗口的调用方法
	preview : function() {
		$('#jquery-preview').unbind('dialogclose').bind('dialogclose', function() {
			$(document).stopTime();$('#jquery-preview bgsound').attr('src', '');
			$('#jquery-preview').empty();
		});
		var cur = this._preview_cur_frame;
		this._preview();
		$(document).oneTime(this.datas[cur].showtime + 's' , function() {
    		frame.preview();
  		});
	},
	
	// 单个帧预览显示操作
	_preview : function() {
		if (this.datas.length==0) {
			return;
		}
		
		$('#jquery-preview')
			.dialog('option', 'buttons', {"关闭":function(){$(this).dialog("close");},
			'下一页':function() { 
				if (frame.count()==1) {
					return; 
				}
				$(document).stopTime(); 
				frame.preview(); 
			},
			'上一页':function() {
				if (frame.count()==1) {
					return;
				}
				$(document).stopTime();
				frame._preview_cur_frame=frame._preview_cur_frame-2;
				if (frame._preview_cur_frame<0) {
					frame._preview_cur_frame=frame.count()+frame._preview_cur_frame;
				}
				frame.preview();
			}})
			.dialog('open');
			
		var f = frame.datas[this._preview_cur_frame];
	  	var div = $('<div class="boxprev"/>').attr('style','text-align:center;');
	  	if (f.pic) {
	    	var sw = f.pic.w; var sh = f.pic.h;
	    	if (sw>sh) {
	      		if (sw>180) {
	      			sh=sh/sw*180;sw=180;
	      		}
	    	} else {
	      		if (sh>180) {
	      			sw=sw/sh*180;sh=180;
	      		}
	    	}
	    	var s = 0;
	    	if (sh<180) {
	    		s = (180-sh)/2;
	    	}
	    	div.append('<img class="imgprev" src="' + mmsPath + f.pic.path + '" width="'+sw+'" height="'+sh+'"/><br/>');
	  	}
	  	if (f.text.value!='') {
		    div.append('<div class="txtprev">&nbsp;&nbsp;&nbsp;&nbsp;' + f.text.value.replace(/\n/gim, '<br/>&nbsp;&nbsp;&nbsp;&nbsp;') + '</div></br>');
		}
	  	if (f.sound) {
	    	div.append('<bgsound src="' + mmsPath + f.sound.path + '" loop="1">');
	  	}
	  	$('#jquery-preview bgsound').attr('src', '');
	  	$('#jquery-preview').empty().append(div);
	  	$("#jquery-preview").dialog('option', 'title', '彩信预览 - 当前 ' + (this._preview_cur_frame + 1) + '/' + this.count() + ' (' + f.showtime + '秒)');
	  	
	  	this._preview_cur_frame++;
	  	if (this._preview_cur_frame>=this.datas.length) {
	  		this._preview_cur_frame = 0;
	  	}
	},
	
	// 保存或更新帧数据。 processNext=true表示直接跳转到彩信群发下一步
	save : function(processNext) {
		if (!frame.canSubmit()) {
			document.getElementById("btn_openfrom").onclick=function(){sendMms();};
			return false;
		}	
		
		$('#mmsFrameData').val(frame.toHtml());
		
		$.blockUI({ message: messageSource.uploadWaiting });
		
		
		$('#sform').ajaxForm({
			url:	'jpye_cxqf_save_mms.jsp?destTerminalId='+ destTerminalId + "&mmsId=" + $('#mmsId').val() + "&yzTerminalId=" + yzTerminalId,
        	type:      'post', 
        	skipEncodingOverride: true,
        	iframe:    true,
        	dataType:  'json', 
        	async: false,
         	success:   function(ret) {
         		if (!processNext) {
         			$.unblockUI();
         		}
				if (!ret.success) {
					return jalert(ret.msg);
				}
         		try {
					$('#loadBoxId').val(ret.mmsid);
					status=-1;
					jalert(ret.msg);
				} catch (e) {
					return; 
				}
         	}
		}).submit(); 
		
		document.getElementById("btn_openfrom").onclick=function(){sendMms();};
		
	},
	// 发送彩信
	send : function(processNext, timing ) {
		if (!frame.canSubmit()) {
			document.getElementById("btn_openfrom").onclick=function(){sendMms();};
			return false;
		}		
		
		$('#mmsFrameData').val(frame.toHtml());		
		
		var s = checkSession();
		
		if(s == 2){
			jalert('服务器超时,请重新登录');
    		return;
		}
		$.blockUI({ message: messageSource.uploadWaiting });
		
		$('#sform').ajaxForm({
			url:	'./jpye_cxqf_save_mms.jsp?destTerminalId=' + destTerminalId + "&mmsId=" + $('#mmsId').val() + "&timing=" + timing,
        	type:      'post', 
        	skipEncodingOverride: true,
        	iframe:    true,
        	dataType:  'json', 
        	async: false,
         	success:   function(ret) {
         		if (!processNext) {
         			$.unblockUI();
         		}
				if (!ret.success) {
					return alert(ret.msg);
				}
         		try {
					$('#loadBoxId').val(ret.mmsid);
					status=-1;
					jalert(ret.msg);
				} catch (e) { return;}
         	},
		    error:   function(ret) {
         			$.unblockUI();
         			alert(ret);
         	}
		}).submit(); 
		document.getElementById("btn_openfrom").onclick=function(){sendMms();};
	}
	
};

})();

function dealDataBeforeSubmit(){
	$('#mmsFrameData').val(frame.toHtml());	
	window.parent.document.getElementById('mmsFrameData').value=$('#mmsFrameData').val();
}