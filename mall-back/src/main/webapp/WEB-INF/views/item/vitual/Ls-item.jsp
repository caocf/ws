<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<head>
<ht:head />
<script type="text/javascript">
</script>

</head>
<body>
	<br />

	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div class="title">
			</div>
			<!-- end box / title -->
			<div class="form">
				<div class="fields">
					<div class="field">
						<div class="label">
							<label for="shopId">短信标题：</label>
						</div>
						<div class="input">${lsItem.smsTitle }&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shopId">一级分类：</label>
						</div>
						<div class="input">${lsCatalogOne }&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shopId">二级分类：</label>
						</div>
						<div class="input">${lsCatalogTwo }&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shopId">三级分类：</label>
						</div>
						<div class="input">${lsCatalogThree }&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shopId">省份：</label>
						</div>
						<div class="input">${lsAreaProvince}&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shopId">城市：</label>
						</div>
						<div class="input">${lsAreaCity}&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">品牌：</label>
						</div>
						<div class="input">${lsItem.brand }&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shopId">库存模式：</label>
						</div>
						<div class="input"><c:if test="${lsItem.stockMode == 0  }">共享</c:if>
											<c:if test="${lsItem.stockMode == 1  }">独占</c:if>
						&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shopId">退款策略：</label>
						</div>
						<div class="input"><c:if test="${lsItem.refundStrategy == 0  }">不支持</c:if>
											<c:if test="${lsItem.refundStrategy == 1 }">支持</c:if>
						&nbsp;</div>
					</div>
					
					<div class="field">
						<div class="label">
							<label for="shopId">库存：</label>
						</div>
						<div class="input">${lsItem.maxSale }&nbsp;</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="shopId">创建时间：</label>
						</div>
						<div class="input"><ct:time source="${lsItem.createTime }"/>&nbsp;</div>
					</div>
					
					<div class="field">
						<div class="label">
							<label for="shopId">修改时间：</label>
						</div>
						<div class="input"><ct:time source="${lsItem.updateTime }"/>&nbsp;</div>
					</div>
					

				</div>
			</div>
		</div>
		<!-- end forms -->
	</div>
</body>
</html>