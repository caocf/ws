<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    
<!--
$(function(){
		selectRegion("#regionName","regionCode","regionName",'0',{maxItems:1});
    		selectItemType("#typeName",function(id,txt){
	    		$("#typeId").val(id);
	    		$("#typeName").val(txt);
	    		$("#typeName").focus();
	    		$("#typeName").blur();
	    	},{leaf:false});
});


function delLandmark(landmark){	
		var temp = "sp" + landmark.toString();
		document.getElementById("cdetip").style.display="none";
		 jQuery.ajax({ 
		     type: "GET",    
	         url: urlpath + "/delLandmark.do",    
	         data: "landmark=" + landmark + "&good_id=" + good_id,
			 }   
	       );
		 span.parentNode.removeChild(span);
	}
//-->
</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加导航栏</c:if><c:if test="${method == 'edit'}">修改导航栏</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add" commandName="channelNaviPage"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <div class="form">
        <form:hidden path="id"/>
        <form:hidden path="createTime"/>
        <form:hidden path="createUser"/>
            <div class="fields">
            	<c:if test="${method == 'add'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自动生成</div>
                </div>
                </c:if>
                
                <c:if test="${method == 'edit'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${channelNaviPage.id}</div>
                </div>
                </c:if>		
                                
                
                  <div class="field">
                    <div class="label">
                        <label for="title" class="req">导航栏名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="title" cssClass="small  required" maxlength="100" />
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="title" class="req">代码：</label>
                    </div>
                    <div class="input">
                        <form:input id="cde" path="code" cssClass="small  required" maxlength="100" />
                    </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="title" class="req">位置描述：</label>
                    </div>
                    <div class="input">
                        <form:input path="des" cssClass="small  required" maxlength="300"  />
                    </div>
                </div>
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/channel/navipage/list" class="btnAnchor">返回</a>
                </div>
            </div>
                </div>

              
               
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>