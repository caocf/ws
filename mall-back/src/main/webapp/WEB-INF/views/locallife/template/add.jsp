<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<head>
<ht:head />
</head>
<script type="text/javascript">
	$(function(){
		var url = document.referrer;
			$("#backUrl").val(url);
	});
</script>
<body>
	<div id="content">
		<div class="box">
			<div class="title">
				<h5>
					<c:if test="${method == 'add'}">添加模版</c:if>
					<c:if test="${method == 'edit'}">修改模版</c:if>
				</h5>
			</div>
			<form:form method="post" id="fm" commandName="info" htmlEscape="true"
				acceptCharset="utf-8" cssClass="required-validate"
				enctype="multipart/form-data">
				<input type="hidden" id="backUrl" name="backUrl" />
				<div class="form">
					<div class="fields">
						<c:if test="${method == 'add'}">
		                <div class="field">
		                    <div class="label noinput">ID：</div>
		                    <div class="input">自动生成</div>
		                </div>
		                </c:if>
		                
		                <c:if test="${method == 'edit'}">
		                <div class="field">
		                    <div class="label noinput">ID：</div>
		                    <div class="input">${info.id}</div>
		                </div>
		                </c:if>
						<div class="field">
							<div class="label">
								<label class="req">模版名称：</label>
							</div>
							<div class="input">
								<form:input path="title"
									cssClass="small required min-length-1 max-length-25"
									maxlength="50" />
								<span class="error" id="advice-required-min-lengt-title"
									style="display:none"></span> <span class="error"
									id="advice-required-max-length-title" style="display:none"></span>
							</div>
						</div>
						<div class="field" >
							<div class="label">
								<label for="filePath" class="req">模版文件:</label>
							</div>
							<input type="hidden" name="confPath" value="${info.confPath}">
							<div class="input input-file">
								<input type="file" id="file1" name="uploadFile" size="40"
									class="validate-file-json" />
								<span class="error" id="advice-validate-file-file1"
									style="display:none"></span>
							</div>
						</div>
						
						<div class="field">
							<div class="label">
								<label >描述：</label>
							</div>
							<div class="input">
								<form:textarea path="remark"
									cssClass="max-length-150"
									maxlength="50" />
								 <span class="error" id="advice-required-max-length-remark" style="display:none"></span>
							</div>
						</div>
						
						<div class="field">
							<div class="buttons">
								<div class="highlight">
									<input type="submit" name="submit.highlight" value="提交" />
								</div>
								<input type="reset" name="reset" value="重置" /> <a
									href="javascript:history.go(-1)" class="btnAnchor">返回</a>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>