<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
		$(function(){
			$("#selectresources").change(function(){
				var status = $("#selectresources").val();
				if(status == '0'){
					batchNoDiv.style.display = 'block';
					fileDiv.style.display = 'none'
					serialNoDiv.style.display = 'none'
				}else if(status == '1'){
					batchNoDiv.style.display = 'none';
					fileDiv.style.display = 'block'
					serialNoDiv.style.display = 'none'
				}else{
					batchNoDiv.style.display = 'none';
					fileDiv.style.display = 'none'
					serialNoDiv.style.display = 'block'
				}
			});
		});
	</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加激活任务</c:if><c:if test="${method == 'edit'}">修改激活任务</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add" commandName="data"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <div class="form">
        <form:hidden path="id"/>
            <div class="fields">
            	<c:if test="${method == 'add'}">
                <div class="field">
                    <div class="label noinput">编号：</div>
                    <div class="input">自动生成</div>
                </div>
                </c:if>
                
                <c:if test="${method == 'edit'}">
                <div class="field">
                    <div class="label noinput">编号：</div>
                    <div class="input">${data.id}</div>
                </div>
                </c:if>	
                <div class="field">
                    <div class="label noinput">创建方式：</div>
                    <div class="input">
		                <select name="resources" id = "selectresources" class="small  required">
								<option value="0" <c:if test="${data.status == 0}">selected="selected"</c:if>>批次号</option>
								<option value="1" <c:if test="${data.status == 1}">selected="selected"</c:if>>文件</option>
								<option value="2" <c:if test="${data.status == 2}">selected="selected"</c:if>>起止序列号</option>
					  	</select>	
				  	</div>
                </div>
                
                <div id="batchNoDiv" class="field">
                    <div class="label noinput">批次号：</div>
                    <div class="input">
		               <form:input path="batchNo" cssClass="validate-integer small  required" maxlength="8" />	
				  	</div>
                </div>
                
                
				 <div class="field" id = "fileDiv" style = "display:none">
                    <div class="label">
                        <label for="uploaditemfile">上传文件:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploaditemfile" name="uploadFile" size="40" class="required validate-file-xls-xlsx" />
                        <span class="tip">excel格式</span>
                        <span class="tip"><a href="${ctx }/static/resources/cardModel.xlsx">下载模版</a></span>
                        <span class="error" id="advice-validate-file-uploaditemfile" style="display:none"></span>
                        <span class="error" id="advice-required-uploaditemfile" style="display:none"></span>
                        <input type="hidden" id="filepath" name="filepath">
                    </div>
                    
                 </div>
                 <div id = "serialNoDiv" style="display:none">
                 <div class="field">
                    <div class="label noinput">起始序列号：</div>
                    <div class="input">
		               <form:input path="serialStartNo" cssClass="small  required" maxlength="20" />	
				  	</div>
                </div>
                
                <div class="field">
                    <div class="label noinput">结束序列号：</div>
                    <div class="input">
		               <form:input path="serialEndNo" cssClass="small  required" maxlength="20" />	
				  	</div>
                </div>
                </div>
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/giftcard/task/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>