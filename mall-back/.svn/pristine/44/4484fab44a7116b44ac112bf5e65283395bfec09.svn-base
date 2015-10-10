<%--
描述：彩信内容编辑
作者：jisn@c-platform.com
时间：2012-11-19
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>彩信</title>

<%@page language="java" pageEncoding="utf-8"%>

<%
	String contextPath = request.getContextPath();
	String rootPath =request.getRequestURL().toString().replace(request.getRequestURL(), "");
	
   // String tempids [] = (String [])request.getAttribute("id");
 	String loadMmsId = request.getAttribute("mmsId")==null?null: (String)request.getAttribute("mmsId");//tempids ==null ? null :tempids[0];
 	
 	String res = contextPath + "/static";
	
%>
<style type="text/css">
#ik_mms_pic{width:533px; background:#FFF;}
#ik_mms_pic h1{padding:0 10px; margin:0; font-size:12px; line-height:30px; background:#c00; color:#fff;}
#ik_picup_div{margin:3px 0; border:1px solid #CCC; font-size:12px;}
#ik_picedit_div{}
#ik_picedit_nav{overflow:hidden; background:#E8E8E8; zoom:1; margin:3px 0; padding:0 5px 5px 5px; list-style:none;}
#ik_picedit_nav li{float:left; margin:5px 5px 0 0; cursor:pointer; height:23px; line-height:23px; border:1px solid; border-color:#ACA899; background-color:#F6F6F6; background-repeat:no-repeat;}
#ik_picedit_t1{background:url(<%=res %>/images/ik_zoom_a.gif) center center no-repeat; width:23px;}
#ik_picedit_t2{background:url(<%=res %>/images/ik_zoom_b.gif) center center no-repeat; width:23px;}
#ik_picedit_t3{background:url(<%=res %>/images/ik_face.gif) center center no-repeat; width:23px;}
#ik_picedit_t4{background:url(<%=res %>/images/ik_content.gif) center center no-repeat; width:23px;}
#ik_picedit_t5{font-size:12px; padding:0 3px;}
#ik_picedit_t5 input{vertical-align:middle;}
#ik_picedit_edit{border:1px solid #CCC; height:340px; margin:3px 0; overflow:scroll; zoom:1; width:533px;}

#ik_mms_dtt{width:590px; background:#FFF}
#ik_mms_dtt_ifr{margin:3px 0; font-size:12px; width:590px; height:340px;}

</style>
<script type="text/javascript" >
	var mmsId = '<%=loadMmsId%>';// 如果 loadMmsId == null，则 mmsId = 'null'，而不是 mmsId=null
	//alert( mmsId );
	
</script>
<link type="text/css" href="<%=contextPath%>/static/css/all.css" rel="stylesheet" />

<link type="text/css" href="<%=contextPath%>/static/css/dialog.css" rel="stylesheet" />

<link type="text/css" href="<%=contextPath%>/static/js/mms/jquery/css/start/jquery-ui-1.7.2.custom.css" rel="stylesheet" /> 
<link type="text/css" href="<%=contextPath%>/static/js/mms/jquery/jquery.Jcrop.css" rel="stylesheet" />
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/jquery.json-1.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/jquery.timers.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/jquery.form.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/ajaxupload.3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/jquery/tools.scrollable-1.0.5.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/mms.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/mms/dialog.js"></script>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.cplatform.mall.back.cont.mms.util.*" %>
<%@page import="java.io.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<jsp:useBean id="titleUtil" class="com.cplatform.mall.back.cont.mms.util.TitleUtil"></jsp:useBean>

<%
//上传彩信路径
String mmsPath = /*AppConfig.getMmsWebpath()*/ "/data/mms/";

//获得服务器端当前日期
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm");
String curDate = sdf.format(new Date());
%>

<script>

	// 日期格式化
	Date.prototype.format = function(format) { 
	  var o = { 
	    "M+" : this.getMonth()+1, //month 
	    "d+" : this.getDate(),    //day 
	    "h+" : this.getHours(),   //hour 
	    "m+" : this.getMinutes(), //minute 
	    "s+" : this.getSeconds(), //second 
	    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter 
	    "S" : this.getMilliseconds() //millisecond 
	  };
	  if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
	    (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	  for(var k in o)if(new RegExp("("+ k +")").test(format)) 
	    format = format.replace(RegExp.$1, 
	      RegExp.$1.length==1 ? o[k] : 
	        ("00"+ o[k]).substr((""+ o[k]).length)); 
	  return format; 
	}
	
<%--彩信文件路径--%>
var mmsPath = '<%=rootPath%><%=mmsPath%>';
<%--初始化彩信DIY编辑器--%>
jQuery(function() {
  frame = new MmsFrame();
  editPanel = new EditPanel();
  framePanel = new FramePanel();
  <%
  if (loadMmsId!=null && !"0".equalsIgnoreCase(loadMmsId)) {
  %>
  	<%--根据彩信ID载入彩信--%>
  	frame.load('<%=loadMmsId%>');
  <%
  } else {
  %>
  framePanel.add();
  <%
  }
  %>
  bindIconBtn();
  bindDialog();
});
</script>
</head>
<body>

<table width="730" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="485" align="left" valign="top">
      <form id="sform" name="sform" method="post" style="padding:0;margin:0;">
       <input type="hidden" name="mmsFrameData" id="mmsFrameData"/>
      </form>
      <table width="475" border="0" cellpadding="0" cellspacing="0" bgcolor="#ffffff">
        <tr>
          <td height="35" align="right"><a id="btn_mmsview" href="javascript:;" onclick="frame.preview();"><img src="<%=res %>/images/001_77.jpg" width=96 height=27 border="0" alt="彩信预览" /></a></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="5"></td>
        </tr>
      </table>

      <table WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0" CLASS="grey_bordercolor1">
        <tr>
          <td valign="top">
            <div class="mainbox">
	              <div class="lpanel">
	                <div class="topfun">
	                <ul>
	                <li><img src="<%=res %>/images/000_6.jpg" width="10" alt="添加帧" /></li>
	                <li><img src="<%=res %>/images/000_8.jpg" width="11" height="16" alt="删除帧"/></li>
	                <li><img src="<%=res %>/images/000_10.jpg" width="12" height="16" alt="向下移动"/></li>
	                <li><img src="<%=res %>/images/000_12.jpg" width="12" height="16" alt="向上移动"/></li>
	                <li><img src="<%=res %>/images/000_14.jpg" width="17" height="16" alt="清空"/></li>
	                </ul></div>
	                <div id="framePanel" class="scrollable " style="float:left;">
	                <div class="items"></div>
	                </div>
	                <div class="framenavi" style='display:none;'></div>
	                <div class="clr"></div>
	              </div>
	           	 <div class="rpanel">
	                <div id="picContainer" class="elmdetail">
	                  <span class="fl mgt2">图片名称：<span id="lb-pic">图片的大小不超过80k</span></span>
	                  <span class="split ui-hidden"></span>
	                  <span id="btn-picedit" class="ui-hidden ricon ui-icon ui-icon-scissors" title="编辑/裁减图片"></span>
	                  <span id="btn-picdel" class="ui-hidden ricon ui-icon ui-icon-close" title="删除图片" ></span>
	                  <input type="button" class="rbtn" id="pic-upload-btn" title="上传图片"  />
	                  <div class="clr"></div>
	                </div>				               
	                <div id="soundContainer" class="elmdetail" style='margin-bottom:16px;'>        
	                    <span class="fl mgt2">声音名称：<span id="lb-sound">声音的大小不超过80K</span></span>
	                    <span class="split ui-hidden"></span>
	                    <span id="btn-soundplay" class="ui-hidden ricon ui-icon ui-icon-play" title="播放/暂停"></span>
	                    <span id="btn-sounddel" class="ui-hidden ricon ui-icon ui-icon-close" title="删除声音"></span>
	                    <input type="button" class="rbtn"  title="上传声音"  id="sound-upload-btn"  />
	                    <div class="clr"></div>
	                </div>
	                <div id="picEditor" onselectstart="return false"></div>
	                <textarea id="textContainer" class="textpl" ></textarea>
	               	<div id='content_error' style='display:none;color:red'>填写的彩信内容过长，不超过1500个字符</div>
	                <bgsound id='bgsound' src="" loop=1>
	                <div class="slidg" style= "display:inline">
	                    当前帧/总帧数：<span id="currentFrame"></span>/<span id="totalFrame"></span>
	                                   目前彩信大小：<span id="totalSizeShow"></span><br />
	                    彩信总限制大小：<span id="labelMmsMaxSize"></span>
	                   </div>
	                   <div class="slidf" style= "display:inline">
	                  <span>&nbsp;&nbsp;&nbsp;当前帧彩信显示时间：</span>
	                  <input type="text" id="intervalContainer" style="width:30px;" maxlength="3"/>
	                  <span>秒</span>
	                   </div>
	              </div>
	              <div class="clr"></div>
            </div>
        </td>
        </tr>
        </table>
      </td>
  </tr>
</table>

<div id="jquery-alert">
<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
<span class="alert-message" style="font-size:12px;"></span>
</p>
</div>

<div id="jquery-confirm">
<p><span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 50px 0;" ></span>
<span class="confirm-message" style="font-size:12px;"></span>
</p>
</div>

<div id="jquery-preview"></div>
</td>
</tr>
</table>
</div>
</div>
<div class="blank3"></div>
<div id="ik_mms_pic" style="">
  <div id="ik_picup_div">
    图片大小不超过80k，格式为gif、jpg、jpeg或bmp。<input type="button" id="ik_picup_btn" value="上传图片" />
  </div>
  <div id="ik_picedit_div">
  	<ul id="ik_picedit_nav">
    	<li id="ik_picedit_t5"><input id="ik_picedit_t5_c" type="checkbox" />取消取景框</li>
    	<li id="ik_picedit_t1" rel="" title="放大"></li>
    	<li id="ik_picedit_t2" rel="" title="缩小"></li>
    	<li id="ik_picedit_t3" rel="" title="大头贴"></li>
    	<li id="ik_picedit_t4" rel="" title="图片加文字"></li>
    </ul>
    <div id="ik_picedit_edit">
    	
    </div>
  </div>
</div>

<div id="ik_mms_word">
  <ul id="ik_mms_word_nav">
  	<li><select id="ik_mms_word_family">
    	<option value="'宋体'">宋体</option>
    	<option value="'隶书'">隶书</option>
    	<option value="'楷体_GB2312'">楷体_GB2312</option>
    	<option value="'黑体'">黑体</option>
    </select></li>
  	<li><select id="ik_mms_word_size">
    	<option value="12px">12px</option>
    	<option value="14px">14px</option>
    	<option value="16px">16px</option>
    	<option value="18px">18px</option>
    	<option value="20px">20px</option>
    	<option value="24px">24px</option>
    </select></li>
  	<li id="ik_mms_word_b">&nbsp;B&nbsp;</li>
  	<li id="ik_mms_word_i">&nbsp;I&nbsp;</li>
  	<li id="ik_mms_word_u">&nbsp;U&nbsp;</li>
  	<li><select id="ik_mms_word_color">
    	<option value="#000000" style="color:#000000;">黑色</option>
    	<option value="#00FFFF" style="color:#00FFFF;">天蓝</option>
    	<option value="#0000FF" style="color:#0000FF;">蓝色</option>
    	<option value="#FF00FF" style="color:#FF00FF;">粉色</option>
    	<option value="#808080" style="color:#808080;">深灰</option>
    	<option value="#008000" style="color:#008000;">深绿</option>
    	<option value="#00FF00" style="color:#00FF00;">绿色</option>
    	<option value="#800000" style="color:#800000;">深红</option>
    	<option value="#000080" style="color:#000080;">深蓝</option>
    	<option value="#808000" style="color:#808000;">草绿</option>
    	<option value="#800080" style="color:#800080;">紫色</option>
    	<option value="#FF0000" style="color:#FF0000;">红色</option>
    	<option value="#C0C0C0" style="color:#C0C0C0;">浅灰</option>
    	<option value="#008080" style="color:#008080;">青色</option>
    	<option value="#FFFFFF" style="color:#FFFFFF; background:#000;">白色</option>
    	<option value="#FFFF00" style="color:#FFFF00; background:#000;">黄色</option>
    </select></li>
  </ul>
  <textarea id="ik_mms_word_con" ></textarea>
</div>

<script type="text/javascript">
	var $word_family = $('#ik_mms_word_family')
		, $word_size = $('#ik_mms_word_size')
		, $word_b = $('#ik_mms_word_b')
		, $word_i = $('#ik_mms_word_i')
		, $word_u = $('#ik_mms_word_u')
		, $word_color = $('#ik_mms_word_color')
		, $word_con = $('#ik_mms_word_con');

	$('#ik_picedit_t5_c').click(function(e) {
		editAble(this);
	});
	
	$('#ik_picedit_t1').click(function(e) {
		zoom(1);
	});
	
	$('#ik_picedit_t2').click(function(e) {
		zoom(-1);
	});
	
	$('#ik_picedit_t3').click(function(e) {
		//ik_editpic.dtt('images/ik_tmp.gif');
		selectDtt();
	});
	
	$('#ik_picedit_t4').click(function(e) {
		//selectWord();
	});

	var editAble = function(obj){
		if(obj.checked){
			ik_editpic.release();
			ik_editpic.disable();
			$('#cureditpic').data('c', null); // 清除裁剪数据
		}else{
			ik_editpic.enable();
		}
	}
	var newEditPic = function(){
		ik_editpic = $.Jcrop('#cureditpic', {
			bgColor: 'red'
			, minSize:[50,50]
			//, maxSize:[176,220]
			//, allowResize:false
			, onSelect: function(c) {
				$('#cureditpic').data('c', c);
			}
		}); // 全局 ik_editpic add by ikun
	};
	var zoom = function(bol){
		if(!arguments.length) bol = 0
		var zoomnum = bol
			, step = 0.1
			, $img = $('#cureditpic')
			, size = $img.data('ik_size')
			, resize = zoomnum == 0 ? 0 : size['resize'] + zoomnum
		if(Math.abs(resize) >= 10) return;
		size['resize'] = resize;
		$img.data('ik_size', size);
		//$img.data('c', null); // 清除裁剪数据
		ik_editpic.destroy();
		$img.attr({
			width: size['width'] + Math.floor(size['width']*resize*step)
			,height : size['height'] + Math.floor(size['height']*resize*step)
		});
		if ($img.attr('width') > 50 && $img.attr('height')>50) {
			newEditPic();
			editAble($('#ik_picedit_t5_c')[0]);
		}
		var c = $img.data('c'), attr = $img.data('ik_dtt') && $img.data('ik_dtt').url;
		if(c) ik_editpic.setSelect([c.x, c.y, c.x+c.w, c.y+c.h]);
		if(attr){
			ik_editpic.dtt(attr);
			ik_editpic.setOptions({
				allowResize: false
				,minSize:[160,120]
				,maxSize:[160,120]
			});
			
		}

	}
	// 弹窗-选择大头贴
	$('#ik_mms_dtt').dialog({
		autoOpen : false
		,resizable : false
		,width : 614
		//,height : 490
		,title : '选择大头贴'
		,modal: true
		,buttons: {
			'返回' : function() {$(this).dialog("close"); }
			,'选择' : function() {
				var attr = $('#ik_mms_dtt').attr('dtt')
					, $img = $('#cureditpic');
				if(attr){
					if(attr == 'default'){
						ik_editpic.dtt('');
						$img.data('ik_dtt', null)
						ik_editpic.setOptions({
							allowResize: true
							,minSize:[50,50]
							,maxSize:[0,0]
						});
					}else{
						ik_editpic.dtt(attr);
						$img.data('ik_dtt', {'url':attr});
						ik_editpic.setOptions({
							allowResize: false
							,minSize:[160,120]
							,maxSize:[160,120]
						});
					}
				}
				$(this).dialog("close");
			}
		} 
	});
	var selectDtt = function(){
		$('#ik_mms_dtt').removeAttr('dtt').dialog('open');
	}
	
	// 弹窗-修改文字
	$('#ik_mms_word').dialog({
		autoOpen : false
		,resizable : false
		,width : 415
		//,height : 490
		,title : '编辑文字'
		,modal: true
		,buttons: {
			'返回' : function() {
				$(this).dialog("close");
				
			}
			,'确认' : function() {
				var word = $('#ik_picedit_word')
					, $img = $('#cureditpic');
				if(!word.length){
					$('#ik_picedit_edit').append('<div id="ik_picedit_word"></div>');
					word = $('#ik_picedit_word');
					word.draggable({
						stop:function(){
							var word = $img.data('word')
								, pl = word['picleft']
								, pt = word['pictop']; 
							word['left'] = parseInt(this.style.left) - pl;
							word['top'] = parseInt(this.style.top) - pt;
							$img.data('word', word);
						}
					});
				}
				word.text($word_con.val());
				$(this).dialog("close");
			}
		} 
	});
	var selectWord = function(){
		$('#ik_mms_word').dialog('open');
	}
	// 编辑文字
	function word_sel(select, type){
		select.change(function(){
			$word_con.css(type, select.val());
		});
	};
	function word_click(btn, type, on, off){
		btn.click(function(){
			var _type = $word_con.css(type);
			if(_type == on){
				$word_con.css(type, off);
			}else{
				$word_con.css(type, on);
			}
		});
	}
	word_sel($word_family, 'font-family');
	word_sel($word_size, 'font-size');
	word_sel($word_color, 'color');
	word_click($word_b, 'font-weight', '700', 'normal');
	word_click($word_i, 'font-style', 'italic', 'normal');
	word_click($word_u, 'text-decoration', 'underline', 'none');
	

	// 弹窗-修改图片
	$('#ik_mms_pic').dialog({
		autoOpen : false
		,resizable : false
		,width : 557
		//,height : 490
		,title : '图片编辑'
		,modal: true
		,buttons: {
			'返回' : function() {
				//$('#cureditpic').data('ik_dtt', null); // 删除大头贴信息
				$(this).dialog("close");
			}
			/*,'修改' : function() {
				$('#btn-picedit').click();
			}*/
		} 
	});
	var getExt = function(file) {
		return (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';
	};
	$('#pic-upload-btn').click(function(){
		$('#ik_mms_pic').dialog('open');
		var $img = $('#cureditpic')
			, $nav = $('#ik_picedit_nav');
		if($img[0]){
			if (getExt($img.attr('src')) == 'gif'){
				$nav.hide();
			}else{
				//$nav.show();
				$nav.hide();
				zoom(0);
			}
			
		}else{
			$nav.hide();
		}
	})
	
	function clearContentError() {
		$('#content_error').hide();
	}
</script>

</body>
