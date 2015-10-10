<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<script type="text/javascript">
	function changeBody(index){
		var file =  $("#uploadTemplateFile");
		if(index.value == 1){
			file.fadeIn("slow");
		}else if(index.value == 2){
			file.fadeOut("slow");
			 $("#uploadTemplateFile").css("display","none");
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
        <h5><c:if test="${method == 'add'}">添加模版事件</c:if><c:if test="${method == 'edit'}">修改模版事件</c:if>
        </h5>
		</div>
		<form:form method="post" id="fm" commandName="info" htmlEscape="true"
			acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
			<div class="form">
				<div class="fields">
					<div class="field">
						<div class="label noinput">ID：</div>
						<div class="input">自动生成</div>
					</div>
					<div class="field">
						<div class="label">
							<label class="req">识别码：</label>
						</div>
						<div class="input">
							<form:input path="code"  cssClass="small required validate-digits validate-number min-length-1 max-length-6" maxlength="6" />
							<span class="error" id="advice-required-min-lengt-code" style="display:none"></span>
							<span class="error" id="advice-required-max-length-code" style="display:none"></span>
							<span class="error" id="advice-required-validate-digits-code" style="display:none"></span>
							<span class="error" id="advice-required-validate-number-code" style="display:none"></span>
						</div>
					</div>
					<div class="field">
						<div class="label">
							<label class="req">模版事件名称：</label>
						</div>
						<div class="input">
							<form:input path="name"  cssClass="small required min-length-1 max-length-25" maxlength="25" />
							<span class="error" id="advice-required-min-lengt-name" style="display:none"></span>
							<span class="error" id="advice-required-max-length-name" style="display:none"></span>
						</div>
					</div>
					
					<div class="field">
						<div class="label">
							<label class="req">模板（组）名称：</label>
						</div>
						<div class="input">
                         <select id="tName" name="tName" class="validate-selection">
                            <option value="">--请选择--</option>
                            <c:forEach items="${sysTemplateInfo.data}" var="snow">
                          <option value="${snow.id},${snow.tName}"  <c:if test="${snow.tName eq info.tgName}">selected="selected"</c:if>>${snow.tName}</option>
                          </c:forEach>
                        </select>
						</div>
					</div>
					 <div class="field">
                    <div class="label">
                        <label for="memo">备注：</label>
                    </div>
                    <div class="input">
                    	<form:textarea path="memo"  cssClass="max-length-50" maxlength="50"  />
                    </div>
                </div>
					
					<div class="field">
							<div class="buttons">
								<div class="highlight">
									<input type="submit" name="submit.highlight" value="提交" />
								</div>
								<input type="reset" name="reset" value="重置" /> <a
									href="${ctx}/websys/template/event_list" class="btnAnchor">返回</a>
						</div>
					</div>
				</div>
				</div>
		</form:form>
		</div>
	</div>
</body>
</html>