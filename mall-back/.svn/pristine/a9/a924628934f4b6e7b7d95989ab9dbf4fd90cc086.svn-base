<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>广告详细信息</h5>
    </div>
    <!-- end box / title -->
     <div class="form">
         <div class="fields">
             <div class="field">
             	<div class="label noinput">广告位置:</div>
                 <div class="input">${sysAd.positionName}</div>
             </div>
             <div class="field">
             	<div class="label noinput">名称：</div>
                 <div class="input">${sysAd.adName}</div>
             </div>
             <div class="field">
             	<div class="label noinput">类型：</div>
                 <div class="input">
                		<c:if test="${sysAd.adType == '1'}">图片</c:if>
                    	<c:if test="${sysAd.adType == '2'}">文字</c:if>
                    	<c:if test="${sysAd.adType == '3'}">flash</c:if>
                 </div>
             </div>  
             <div class="field">
             	<div class="label noinput">类别：</div>
                 <div class="input">
                 	<c:if test="${sysAd.adFlag == '0'}">内部广告</c:if>
                		<c:if test="${sysAd.adFlag == '1'}">外部广告</c:if>
                 </div>
             </div>
             
           	<div class="field">
				<div class="label label-radio">
					<label for="itemMode">链接：</label>
				</div>
				<div class="input">${sysAd.link}&nbsp;</div>
			</div>
             
            <div class="field">
             	<div class="label noinput">负责人：</div>
                 <div class="input">${sysAd.linkman}</div>
             </div>
             <div id="filefalse" 
              <c:if test="${empty sysAd.adType}">style="display: none"</c:if> 
              <c:if test="${sysAd.adType == '1'}">style="display: none"</c:if>>
              <div class="field">
              	<div class="label noinput">内容：</div>
                  <div class="input">${sysAd.content}</div>
              </div>
             </div>                
             <div id="filetrue" 
              <c:if test="${empty sysAd.adType}">style="display: none"</c:if> <c:if test="${sysAd.adType == '2'}">style="display: none"</c:if> <c:if test="${sysAd.adType == '3'}">style="display: none"</c:if>>
              <c:if test="${sysAd.adType == '1'}">
              <div class="field">
              	<div class="label noinput">图片文件：</div>
                  <div class="input">
                  	<a href="${ctx}/websys/ad/ad_downfile/${sysAd.id}">下载图片文件</a>
                  </div>
              </div>
              </c:if>              
             </div>              
             <div class="field">
             	<div class="label noinput">开始时间：</div>
                 <div class="input"><ct:time source="${sysAd.startTime}" tfmt="yyyy-MM-dd HH:mm:ss"/></div>
             </div>
             <div class="field">
             	<div class="label noinput">有效时间：</div>
                 <div class="input"><ct:time source="${sysAd.validTime}" tfmt="yyyy-MM-dd HH:mm:ss"/></div>
             </div>
             
             <div class="buttons">
                 <a href="javascript:history.go(-1)" class="btnAnchor">返回</a>
             </div>
         </div>
     </div>
</div>
<!-- end forms -->
</div>
</body>
</html>