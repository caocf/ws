<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
 <link href="<spring:url value="/static/css/mplus.css"/>" rel="stylesheet" type="text/css" />
 <link href="<spring:url value="/static/css/mplus-ext.css"/>" rel="stylesheet" type="text/css" />
<html>
    <head>
    <ht:head/>
    </head>
<body>
<div id="content">
<div class="box">
<div class="title">
        <h5>彩信详情</h5>
    </div>
<form:form method="post" id="f_contMms" commandName="contMms"
	htmlEscape="true" acceptCharset="utf-8">
	<div class="form">
	<form:hidden path="contentSrc" />
	<form:hidden path="contentPath" />
	<form:hidden path="contentSize" />
	<form:hidden path="id" />
	<form:hidden path="smilName" />
	 <input type="hidden" name="mmsFrameData" id="mmsFrameData"/>
     <input type="hidden" name="loadBoxId" id="loadBoxId" />
     <input type="hidden" name="mmsId" id="mmsId" />
     
	<div class="fields">
			<c:if test="${ !empty contMms.advice  && contMms.status==2}">			
			<p id='apptips' class="apptips" style="text-align:left;">
				<small>审核驳回原因：${contMms.advice }</small>
				<em class="xclose"><a id='tip_btn' href="#" onclick="$('#apptips').remove();return false;">x</a></em>
			</p>
			 </c:if>
			<div class="app_info">
			<dl>
				<dt>彩信标题：</dt>
				<dd>${contMms.title }</dd>
			</dl>
			<dl>
				<dt>内容源：</dt>
				<dd>${contMms.contentSrc }</dd>
			</dl>		
			<c:if test="${ (!empty contMms.startTime)&& (!empty contMms.endTime)  }">
			<dl>
				<dt>开始时间：</dt>
				<dd>${contMms.startTime }</dd>
			</dl>
			<dl>
				<dt>结束时间：</dt>
				<dd>${contMms.endTime }</dd>
			</dl>
			</c:if>
			<dl>
				<div style='padding-left:70px;'>
	      				<iframe src="mmsdiy.do?mmsId=${contMms.id}" width="100%"  frameborder=0 height="600" marginheight=0 marginwidth=0 scrolling=no style="float:left" id="mmsMb" name="mmsMb" ></iframe>
				</div>
			</dl>
		</div>
		<div class="buttons" >
                  <a href="${ctx}/cont/mms/list" class="btnAnchor" >返回</a>
            </div>
          </div>
		</div>
</form:form>
<style>
button {
	font-size: 12px;
	width: 100px;
	cursor: pointer;
}
</style>
 </div>
    </div>
</body>
</html>