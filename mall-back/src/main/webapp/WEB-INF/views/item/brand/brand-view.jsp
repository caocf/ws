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
				<h5>
					查看品牌
					</h5>
			</div>
			<!-- end box / title -->
			<div class="form">
				<div class="fields">

					<div class="field">
						<div class="label">
							<label for="name" >品牌名称：</label>
						</div>
						<div class="input">${brand.name }
						</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shortName" >品牌网站:</label>
						</div>
						<div class="input">${brand.website }
						</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="uploadfile" >图片:</label>
						</div>
						<div class="input input-file">
							<ht:image webpath="${brand.brandImg }" type="brand"/>
						</div>
					</div>
					<div class="field">
						<div class="label label-textarea">
							<label for="bsScope">品牌描述：</label>
						</div>
						<div class="input">${brand.remark }
						</div>
					</div>
					
					<div class="buttons">
						<input type="button" class="common_btn" onclick="${queryBackFun}" value="返回" />
					</div>
				</div>
			</div>
		</div>
		<!-- end forms -->
	</div>





</body>
</html>