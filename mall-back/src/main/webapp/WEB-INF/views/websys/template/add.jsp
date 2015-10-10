<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	function changeBody(index) {
		var file = $("#uploadTemplateFile");
		if (index.value == 1) {
			file.fadeIn("slow");
		} else if (index.value == 2) {
			file.fadeOut("slow");
		}
	}
</script>
<head>
<ht:head />
</head>
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
				<div class="form">
					<div class="fields">
						<div class="field">
							<div class="label noinput">用户ID：</div>
							<div class="input">自动生成</div>
						</div>
						<div class="field">
							<div class="label">
								<label class="req">模版名称：</label>
							</div>
							<div class="input">
								<form:input path="tName"
									cssClass="small required min-length-1 max-length-50"
									maxlength="50" />
								<span class="error" id="advice-required-min-lengt-tName"
									style="display:none"></span> <span class="error"
									id="advice-required-max-length-tName" style="display:none"></span>
							</div>
						</div>
						<div class="field">
							<div class="label label-radio">
								<label for="unitType" class="req">生成方式:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="radio-1" name="isValid" value="1"
										${info.type!=2? "checked":"" } onclick="changeBody(this)" /><label
										for="radio-1">模版接口</label> <input type="radio" id="radio-2"
										name="isValid" value="2" ${info.type==2?
										"checked":"" }  onclick="changeBody(this)"
										class="validate-one-required" /><label for="radio-2">页面访问</label>
								</div>
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label class="req">数据查询路径：</label>
							</div>
							<div class="input">
								<form:input path="dataUrl"
									cssClass="small required min-length-1 max-length-50"
									maxlength="50" />
								<span class="error" id="advice-required-min-lengt-dataUrl"
									style="display:none"></span> <span class="error"
									id="advice-required-max-length-dataUrl" style="display:none"></span>
							</div>
						</div>
						<div id="uploadTemplateFile" class="field"  style="display: ${info.type==2?'none':'' }" >
							<div class="label">
								<label for="filePath" class="req">模版文件:</label>
							</div>
							<div class="input input-file">
								<input type="file" id="file1" name="uploadFile" size="40"
									class="validate-file-ftl-jsp-html-htm" />
								<span class="error" id="advice-validate-file-file1"
									style="display:none"></span>
							</div>
						</div>
						
				<div class="field">
                    <div class="label">
                        <label for="select">输出文件编码:</label>
                    </div>
                    <div class="select">
                    	<form:select path="outputCharTset"  items="${charList}" ></form:select>
                    </div>
                </div>
						
						
						<div class="field">
							<div class="buttons">
								<div class="highlight">
									<input type="submit" name="submit.highlight" value="提交" />
								</div>
								<input type="reset" name="reset" value="重置" /> <a
									href="${ctx}/websys/template/list" class="btnAnchor">返回</a>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>