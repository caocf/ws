<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
        <style type="text/css">
        .imgs {
			padding-top: 5px;
			background: #f9f9f9;
		}		
		.piclist {
			margin: 10px 0;
		}	
		li.pllogo {
			position: relative;
			float: left;
			width: 315px;
			margin-left: 19px;
			text-align: center;
			border: 1px solid #DFDDDD;
			height: 325px;
		}
		li.pllogo img {
			margin-top: 5px;
			width: 305px;
			height: 259px;
		}
		li.pllogo dl dd.check {
			font-weight: bold;
			margin-top: 20px;
		}
		li.pic {
			position: relative;
			float: left;
			width: 147px;
			margin-left: 19px;
			margin-bottom: 28px;
			text-align: center;
			border: 1px solid #DFDDDD;
			height: 147px;
		}
		li.pic img {
			margin-top: 5px;
			width: 138px;
			height: 120px;
		}
		li.pic dl dd.check {
			height: 18px;
			width: 125px;
			overflow: hidden;
			margin-left: 8px;
			font-weight: bold;
		}
		h2.title {
		background-color: #65C0E4;
		height: 26px;
		line-height: 26px;
		color: white;
		font-size: 14px;
		text-indent: 15px;
		margin: 6px;
		}
		.imgnote{
			margin: 6px;
			padding: 8px;
			border: 1px solid #B5CFD9;
			background: #F5F9FD;
		}
		.files{
			margin: 0;
			padding: 0;
			clear: both;
			overflow: hidden;
		}
		.file{
			margin: 0;
			padding: 10px 0 10px 0;
			border-bottom: 1px solid #DDD;
			clear: both;
			overflow: hidden;
		}
		.noinput{
			padding-top: 0px;
			font-weight: bold;
		}
		.label{
			left: 20px;
			margin: 0;
			padding: 8px 0 0 5px;
			width: auto;
			position: absolute;
		}
		.input{
			margin: 0 0 0 120px;
			padding: 0;
		}
		.req{
			background: white url('../../static/smooth/images/icons/req.png') no-repeat left center;
			padding-left: 14px;
		}
		.buttons{
			margin: 10px 0 0 120px;
			padding: 0;
		}
        </style>
        <script type="text/javascript">
        function deleteLogoImg(obj){
	    	$(obj).parent().parent().remove();
	    	$("#addMainImgButton").show();
	    }
        
        function deleteImg(obj){
	    	$(obj).parent().parent().remove();
	    }
        </script>
    </head>
<body>
<br />
<div id="search-menu">
    <ul>
    	<ht:menu-btn text="相册" url="/store/shop/img_list?shopId=${shopId}" type=""/>
        <ht:menu-btn text="添加" url="/store/shop/img_add?shopId=${shopId}" type="add"/>
	</ul>
    <br style="clear: left" />
</div>
<h2 class="title"><c:if test="${method == 'manage'}">业务门店图集</c:if><c:if test="${method == 'add'}">添加图片</c:if></h2>
<c:choose>
<c:when test="${method == 'add'}">
<form:form method="post" id="fm" commandName="sysFileImg" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
<input type="hidden" id="shopId" name="shopId" value="${shopId}"/>
<div class="files">
	<div class="file">
		<div class="label noinput" ><label>门店图片：</label></div>
		<div class="input">
		<c:if test="${method == 'edit'}"><a href="#this" id="${shop.id }" class="img_view">图片管理</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
		<c:if test="${empty logo}">
		<button type="button" id="addMainImgButton" class="common_btn">添加LOGO</button>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		<button type="button" id="addOtherImgButton" class="common_btn">添加其它图片</button>
		</div>
	</div>
	<div class="buttons">
	    <button type="submit" class="common_btn">提交</button>
	</div>
</div>
</form:form>
</c:when>
<c:otherwise>
<div class="imgs">
	<c:if test="${not empty imgList}">
	<c:if test="${not empty logo}">
	<ul class="piclist">
		<li class="pllogo">
			<img src="${logo.path}" title="${logo.id}"/>
			<dl><dd class="check">LOGO编号：${logo.id}<a href="#" onclick="deleteItem('./img_delete/${logo.id}/${logo.bsId}');">删除</a></dd></dl>
		</li>
	</ul>
	</c:if>
	<c:if test="${not empty sysFileImgPage}">
	<ul class="piclist">
	<c:forEach items="${sysFileImgPage.data}" var="item">
		<li class="pic">
			<img src="${item.path}" title="${item.id}"/>
			<dl><dd class="check">图片编号：${item.id}<a href="#" onclick="deleteItem('./img_delete/${item.id}/${item.bsId}');">删除</a></dd></dl>
		</li>
	</c:forEach>	
	</ul>
	<ht:page pageData="${sysFileImgPage}" />
	</c:if>
	</c:if>
	<c:if test="${empty imgList}">
        <div class="imgnote">
            <p class="i">目前没有相关图片!</p>
        </div>
    </c:if>
</div>
</c:otherwise>
</c:choose>
<script type="text/javascript">
$().ready(function() {
	$("#addMainImgButton").click(function(){
		var mainImgContent = "<div style='margin: 0;padding: 10px 0 10px 0;border-bottom: 1px solid #DDD;clear: both;overflow: hidden;'><div style='padding-top: 0px;font-weight: bold;left: 20px;margin: 0;padding: 8px 0 0 5px;width: auto;position: absolute;'><label for='uploadFileMain' class='req'>LOGO文件:</label></div><div style='margin: 0 0 0 120px;padding: 0;'><input type='file' id='file1' name='uploadFileMain' size='40' class='float: none; validate-file-bmp-dib-gif-jpg-jpeg-jpe-jfif-png-tif-tiff' /><span class='error' id='advice-validate-file-file1' style='display:none'></span><button type='button' onclick='deleteLogoImg(this);' class='common_btn'>删除</button></div></div>";
    	$("#addMainImgButton").parent().parent().after(mainImgContent);
    	$("#addMainImgButton").hide();
  	});
  	
  	$("#addOtherImgButton").click(function(){
		var otherImgContent = "<div style='margin: 0;padding: 10px 0 10px 0;border-bottom: 1px solid #DDD;clear: both;overflow: hidden;'><div style='padding-top: 0px;font-weight: bold;left: 20px;margin: 0;padding: 8px 0 0 5px;width: auto;position: absolute;'><label for='uploadFileOther' class='req'>其它图片文件:</label></div><div style='margin: 0 0 0 120px;padding: 0;'><input type='file' id='file1' name='uploadFileOther' size='40' class='float: none; validate-file-bmp-dib-gif-jpg-jpeg-jpe-jfif-png-tif-tiff' /><span class='error' id='advice-validate-file-file1' style='display:none'></span><button type='button' onclick='deleteImg(this);' class='common_btn'>删除</button></div></div>";
    	$("#addOtherImgButton").parent().parent().after(otherImgContent);
  	});

});
</script>
</body>
</html>