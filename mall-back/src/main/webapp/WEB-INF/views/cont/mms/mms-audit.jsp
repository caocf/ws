<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
    <ht:head/>
    <script type="text/javascript">
    
    $(document).ready(function(){
		//获取来源地址
 		var url = document.referrer;
 		$("#backUrl").val(url);
 		$("#showAdvice").click(function(){
 		if($("#showAdvice").val()==2){
 	 		$("#adviceDiv").show();
 	 	}else{
 	 		$("#adviceDiv").hide();
 	 	}
	});
});
    </script>
    </head>
<body>
<div id="content">
<div class="box">
<div class="title">
        <h5>彩信审核</h5>
    </div>
<form:form method="post" id="f_contMms" action="audit.do" commandName="contMms"
	htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
	<div class="form">
     <form:hidden path="id"/>
     <input type="hidden" id="backUrl" name="backUrl" />
	 <div class="fields">
	 		<div class="field">
                <div class="label">
                 <label for="contentSrc" class="req">选择操作：</label>
                 </div>
                <div class="input">
                <form:select path="status"  id="showAdvice">
                      <form:option value="1">审核通过</form:option>
                      <form:option value="2">审核驳回</form:option>
                </form:select>
                </div>
	 		
	 		<div class="field" hidden="true"  id="adviceDiv">
                    <div class="label">
                        <label for="advice" class="req">驳回原因：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:textarea path="advice" cols="36" rows="3" cssClass="max-length-200" />
                    </div>
                </div>		
					<div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight"   onclick="dealSubFrame();" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/cont/mms/list" class="btnAnchor">返回</a>
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