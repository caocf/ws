<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />


</head>
<body>
	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div class="title">
				<h5>添加品牌</h5>
			</div>
			<!-- end box / title -->
			<form:form method="post" id="fm" commandName="brand"
				htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate"
				enctype="multipart/form-data">
				<div class="form">
					<div class="fields">

						<div class="field">
							<div class="label">
								<label for="name" class="req">品牌名称：</label>
							</div>
							<div class="input">
								<!-- small medium large -->
								<form:input path="name" cssClass="small required max-length-10"/>
								<span class="error" id="advice-required-name"
									style="display:none"></span>
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="website" >品牌网站:</label>
							</div>
							<div class="input">
								<form:input path="website" cssClass="small "		maxlength="50" />
							</div>
						</div>
						<div class="field">
								<div class="label">
									<label for="uploadfile" >品牌图片:</label>
								</div>
								<div class="input input-file">
									<input type="file" id="uploadfile" name="uploadfile" size="40" class="validate-file-png-jpg"/>
									<span class="error" id="advice-validate-file-uploadfile"
										style="display:none"></span>
								</div>
							</div>
						<div class="field">
							<div class="label label-textarea">
								<label for="remark">品牌描述：</label>
							</div>
							<div class="input">
								<form:textarea path="remark" cols="36" rows="3" cssClass="max-length-100" />
							</div>
						</div>
						

						<div class="buttons">
							<div class="highlight">
								<input type="submit" name="submit.highlight" value="提交" />
							</div>
							<input type="reset" name="reset" value="重置" /> <a
								href="${queryBackUrl}" class="btnAnchor">返回</a>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<!-- end forms -->
	</div>

</body>
</html>