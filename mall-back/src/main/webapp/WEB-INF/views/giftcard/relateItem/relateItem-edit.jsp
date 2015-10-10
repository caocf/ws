<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />

    <script type="text/javascript">  
    	function selectItem(index){
    		selectBindItemCallBack(function(itemId,storeId,itemName){
 				$("#item"+index).val(itemName);
 				$("#itemId"+index).val(itemId);
 				$("#storeId"+index).val(storeId);
 				$("#item"+index).focus();
 				$("#item"+index).blur();
 	 		});
    		
    	}
    	function selectBindItemCallBack(callback,opts){
			var param = $.extend({	
				ShowMessageRow:false,
				Height: 400,
				Width : 800},opts||{});
			
			var url = G_CTX_ROOT + '/gift/relateItem/selectItem?iseckillFlag=-1&storeId=${giftRequired.storeId }';
			
			showDialog("选择商品",url, function(doc,win){
				var selectRadio = $("input[name='activeIdSelector']:checked",doc);
				if(!jQuery.isEmptyObject(selectRadio)){
					var storeId =selectRadio.attr("storeId");
					var itemName =selectRadio.attr("itemName");
					var itemId =selectRadio.val();
					if(jQuery.isFunction(callback)){
						callback(itemId,storeId,itemName,doc,win);
					}
				}
			},param);
 		}
    	
		$(function(){
			//获取来源地址
	 		var url = document.referrer;
	 		$("#backUrl").val(url);
		});
	</script>
</head>
<body>
	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div  class="title">
				<h5>
				 添加绑定商品
				</h5>
			</div>
			<!-- end box / title -->
			<form:form method="post" id="fm"  
				htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
			 	<input type="hidden" id="backUrl" name="backUrl" />
			 	<input type="hidden" name="batchNo" value="${giftRequired.batchNo }">
				<div class="form">
					<div class="fields">
						
						<div class="field">
		                    <div class="label">批次号：</div>
		                    <div class="input">${giftRequired.batchNo}</div>
		                </div>
		                <div class="field">
		                    <div class="label">卡型号：</div>
		                    <div class="input">${giftRequired.modelNo}</div>
		                </div>
		                <div class="field">
		                    <div class="label">客户名称：</div>
		                    <div class="input">${giftRequired.requiredUser}</div>
		                </div>
		                <div class="field">
		                    <div class="label">需要礼品卡数量：</div>
		                    <div class="input">${giftRequired.cardNum}</div>
		                </div>
		                <div class="field">
		                    <div class="label">期望发卡时间：</div>
		                    <div class="input"><ct:time source="${giftRequired.issuingTime} " tfmt="yyyy-MM-dd"/></div>
		                </div>
		                <div class="field">
		                    <div class="label">需求提交时间：</div>
		                    <div class="input"><ct:time source="${giftRequired.createdTime}" tfmt="yyyy-MM-dd"/></div>
		                </div>
		                <div class="field">
		                    <div class="label">礼品兑换方式：</div>
		                    <div class="input">${giftRequired.exchangeModeName}</div>
		                </div>
		                <c:forEach items="${giftRequiredItems }"  varStatus="index" var="item">
		                <div class="field">
							<div class="label">
								<label class="req">选择商品：</label>
							</div>
							<div class="input">
								<input name="item${index.index }" id="item${index.index }" value="${item.itemName }" onclick="selectItem(${index.index});"  class="small required" style="width:150px;height:15px;">
								<span class="error" id="advice-required-item${index.index }" style="display:none"></span>
								<input type="hidden" name="id" id="id${index.index }" value="${item.id }">
								<input type="hidden" name="itemId" id="itemId${index.index }" value="${item.itemId }">
								<input type="hidden" name="storeId" id="storeId${index.index }" value="${item.storeId }">
							</div>
						</div>
		                </c:forEach>
						<div class="buttons">
							<div class="highlight">
								<input type="submit" name="submit.highlight" value="提交" />
							</div>
							<input type="reset" name="reset" value="重置" /> 
							<input type="button" class="common_btn" onclick="history.back();" value="返回" />
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<!-- end forms -->
	</div>

</body>
</html>