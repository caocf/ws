<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<head>
<ht:head />
<script type="text/javascript">
</script>
</head>
<body>

	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div class="title">
				<h5>查看二维码</h5>
			</div>
				<div class="fields">
					<c:forEach items="${giftRequiredItems}" var="item" varStatus="index">
						<div class="field">
							<div class="label">
								<span style="color:blue;font-size:15px;">商品编号：&nbsp;${ item.itemId} &nbsp;&nbsp;商品名称:&nbsp;${ item.itemName} </span>
							</div>
							<div>
								<ht:image webpath="${item.fileWebPath   }" type="qrcode" />
							</div>
					</c:forEach>
				</div>
				<div class="buttons">

					<input type="button" class="common_btn" onclick="history.back();"
						value="返回" />

				</div>
		</div>
	</div>
	</div>

	</div>
</body>
</html>