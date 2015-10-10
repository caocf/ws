<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">

$().ready(function() {
	var oldhtml="";
	//是否需要物流触发的事件
    $(':radio[name="postFlag"]').click(function(){
	    var val= $(':radio[name="postFlag"]:checked').val(); 
	    if(val==0) {
		    $("#isPost").hide();
		    $("#postArea").val(""); 
		    $("#postAreaCode").val("");
		    $("#logisticsFee").val("");
	    }
	    if(val==1){
		    $("#isPost").show();  
		    $("#postArea").val('${itemSale.postArea}'); 
		    $("#postAreaCode").val('${itemSale.postAreaCode}');
		    $("#logisticsFee").val('${itemSale.logisticsFee}');
	    }
    });
	
  //是否需要物流触发的事件
    $(':radio[name="sendCodeMode"]').click(function(){
	    var val= $(':radio[name="sendCodeMode"]:checked').val(); 
	    if(val==0) {//选择不发码
		    $("#selLogistics").show(); 
		    $("#selSendCode").hide();
		    $("#verifyStartTime").val(""); 
		    $("#verifyStopTime").val("");
		    $("#verifyDay").val("");
		    $("#verifyShopName").val("");
		    $("#verifyShopIds").val("");
	    }else{
		    $("#selLogistics").hide(); 
		    $("#selSendCode").show();  
		    $("#verifyStartTime").val('${itemSale.verifyStartTime}'); 
		    $("#verifyStopTime").val('${itemSale.verifyStopTime}');
		    $("#verifyDay").val('${itemSale.verifyDay}');
		    $("#verifyShopName").val('${itemSale.verifyShopName}');
		    $("#verifyShopIds").val('${itemSale.verifyShopIds}');
	    }
    });
    
  //发码方设置触发的事件
    $(':radio[name="sendCodeChannel"]').click(function(){
	    var val= $(':radio[name="sendCodeChannel"]:checked').val(); 
	    if(val==2) {//选择不发码
		    $("#sendCodeSrcId").show(); 
	    }else{
		    $("#sendCodeSrcId").hide(); 
	    }
    });
    $(':checkbox[name="buyLimit"]').click(function(){
    	 if($("#checkbox-1").attr("checked")){
    		 $("#userLimit").show(); 
    	 }else{
    		 $("#userLimit").hide(); 
    	 }
    	 if($("#checkbox-2").attr("checked")){
    		 $("#areaLimit").show(); 
    	 }else{
    		 $("#areaLimit").hide(); 
    	 }
    	 if($("#checkbox-3").attr("checked")){
    		 $("#numLimit").show(); 
    	 }else{
    		 $("#numLimit").hide(); 
    	 }
    });
    
	$("#saleShopName").click(function(){
		showDialog("选择销售门店", "../shop/"+${itemSale.storeId }+"?ids="+$("#saleShopIds").val(),function(doc){
			var selectedComs= doc.getElementById('second');
			var storeIds="";
			var storeNames="";
			for(var i=0;i<selectedComs.length;i++){  
			storeIds=storeIds+selectedComs.options[i].value+",";
			storeNames=storeNames+selectedComs.getElementsByTagName('option')[i].innerHTML+",";
		    }  
			$id('saleShopIds').value =storeIds.substring(0, storeIds.length-1);
			$id('saleShopName').value =storeNames.substring(0, storeNames.length-1);
		},{"Width":900,"Height":400});
			
	});
	$("#verifyShopName").click(function(){
		showDialog("选择验证门店", "../shop/"+${itemSale.storeId}+"?ids="+$("#verifyShopIds").val(),function(doc){
			var selectedComs= doc.getElementById('second');
			var storeIds="";
			var storeNames="";
			for(var i=0;i<selectedComs.length;i++){  
			storeIds=storeIds+selectedComs.options[i].value+",";
			storeNames=storeNames+selectedComs.getElementsByTagName('option')[i].innerHTML+",";
		    }  
			$id('verifyShopIds').value =storeIds.substring(0, storeIds.length-1);
			$id('verifyShopName').value =storeNames.substring(0, storeNames.length-1);
		},{"Width":900,"Height":400});
			
	});
	//配送区域
	selectRegion("#postArea","postAreaCode","postArea",0,{index:1});
	//购买区域
	selectRegion("#areaLimitName","areaLimitCode","areaLimitName",0,{index:2});
	//销售区域
	//销售区域
	selectRegionCallBack("#saleAreaName",function(saleAreaCode,saleAreaName){
		if(oldhtml==""){
			oldhtml=$("#priceId").html();
		}
		
		$("#saleAreaCode").val(saleAreaCode);
		$("#saleAreaName").val(saleAreaName);
		if(saleAreaCode=='${itemSale.saleAreaCode}'){
			 $("#priceId").html(oldhtml);
		}else{
		//提交Ajax获取该区域的价格设置
		$.ajax({
			   type: "POST",
			   url: "../getPrice/"+saleAreaCode,
			   cache: false,
			   dataType:"json",
			   success: function(msg){ 
				  // $("#priceId").empty();
				   var htmlStr="<table    id='TbData' name='TbData'  cellpadding='0' cellspacing='0' >";
				   for(var i=0;i<msg.length;i++){
					   htmlStr=htmlStr+"<tr >"
						   +"<td align='right'>"+msg[i].priceType+":</td><td><input  name='priceTypeCode'  type='hidden' value='"+msg[i].priceTypeCode+"' /><input  name='price' value='0' class=' required validate-number' type='text' value='0' maxlength='50' />元</td>"
						   +"</tr>";
		    	  }
				   htmlStr=htmlStr+" </table><span class='error' id='advice-required-userPwd' style='display:none'></span>";
				   
				   $("#priceId").html(htmlStr);
			   }
			});
		}
	},0,{index:3,maxItems:1});
	
	$("#unlimitedStockNum").click(function(){
		
		 if($("#unlimitedStockNum").attr("checked")=="checked"){
			 $("#stockNum").attr({ readonly: 'true' });  
			 $("#stockNum").val("0");
	        }else{
	        	$("#stockNum").val("${itemSale.stockNum }");
	        	$("#stockNum").removeAttr("readonly");    	
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
			<div >
				<h5>
					发布商品修改
					<h5>
			</div>
			<!-- end box / title -->
			<form:form method="post" id="fm" commandName="itemSale"
				htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
				<form:hidden path="storeId" value="${itemSale.storeId }"/>
				<form:hidden path="shopClass" value="${itemSale.shopClass }"/>
				<div class="form">
					<div class="fields">

						<div class="field">
							<div class="label">
								<label for="name" class="req">商品名称：</label>
							</div>
							<div class="input">
							<form:input path="name"/>	
							</div>
						</div>
						
						<div class="field">
							<div class="label label-radio">
								<label>发码方式:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="radio-3" name="sendCodeMode" value="0"  <c:if test="${itemSale.sendCodeMode eq 0 }">checked="checked"</c:if>  /><label for="radio-3">不发码</label> 
									<input type="radio" id="radio-4" name="sendCodeMode" value="1" <c:if test="${itemSale.sendCodeMode eq 1 }">checked="checked"</c:if>/><label for="radio-4">按照订单发码</label> 
									<input type="radio" id="radio-5" name="sendCodeMode" value="2" <c:if test="${itemSale.sendCodeMode eq 2 }">checked="checked"</c:if>/> <label for="radio-5">按照商品个数发码</label>
								</div>
								<span class="error" id="advice-validate-one-required-radioex1"
									style="display:none"></span>
							</div>
						</div>
						
						<!-- 需要发码此div显示begin -->
					<div id="selSendCode" <c:if test="${itemSale.sendCodeMode eq 0 }">style="display: none;"</c:if> >
					<div class="field">
							<div class="label label-radio">
								<label>发码类型设置:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="radio-1" name="verifyCodeType" value="1"  class="validate-one-required" <c:if test="${itemSale.verifyCodeType eq 1 }">checked="checked"</c:if> /><label for="radio-1">一维码</label>
									<input type="radio" id="radio-2" name="verifyCodeType" value="2"  class="validate-one-required" <c:if test="${itemSale.verifyCodeType eq 2 }">checked="checked"</c:if>  /><label for="radio-2">二维码</label>
								</div>
								<span class="error" id="advice-validate-one-required-verifyCodeType"
									style="display:none"></span>
							</div>
						</div>
						<div class="field">
							<div class="label label-radio">
								<label>发码方设置:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="radio-6" class="validate-one-required" name="sendCodeChannel" value="0" <c:if test="${itemSale.sendCodeChannel eq 0 }">checked="checked"</c:if> /><label for="radio-6">平台自己</label>
									<input type="radio" id="radio-7" class="validate-one-required" name="sendCodeChannel" value="1" <c:if test="${itemSale.sendCodeChannel eq 1 }">checked="checked"</c:if>/><label for="radio-7">方正码平台</label> 
									<input type="radio" id="radio-8"class="validate-one-required"  name="sendCodeChannel" value="2" <c:if test="${itemSale.sendCodeChannel eq 2 }">checked="checked"</c:if>/> <label for="radio-8">第三方应用</label>
								</div>
								<span class="error" id="advice-validate-one-required-sendCodeChannel"
									style="display:none"></span>
							</div>
						</div>
						<!-- 选择第三方应用此div显示begin -->
						<div class="field" id="sendCodeSrcId" <c:if test="${itemSale.sendCodeChannel ne 2 }">style="display: none;"</c:if> >
							<div class="label">
								<label for="select">选择制码渠道:</label>
							</div>
							<div class="select">
								<select id="select" name="sendCodeSrc" class="validate-selection">
									<option value="">请选择</option>
									<option value="10" <c:if test="${itemSale.sendCodeSrc eq 10 }"> selected="selected" </c:if>>85度C</option>
									<option value="11" <c:if test="${itemSale.sendCodeSrc eq 11 }"> selected="selected" </c:if>>鲜芋仙</option>
								</select>
							</div>
						</div>
						<!-- 选择第三方应用此div显示 end-->
						<div class="field">
							<div class="label">
								<label for="verifyStartTime" class="req">验证有效开始时间:</label>
							</div>
							<div class="input">
								<input type="text" id="verifyStartTime" name="verifyStartTime"
									class="date required" readOnly  value="<ct:time source="${itemSale.verifyStartTime}" />"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'verifyStopTime\')||\'2020-10-01\'}'})" />
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="verifyStopTime" class="req">验证有效结束时间:</label>
							</div>
							<div class="input">
								<input type="text" id="verifyStopTime" name="verifyStopTime" class="date required" readOnly  value="<ct:time source="${itemSale.verifyStopTime}" />"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'verifyStartTime\')}',maxDate:'2020-10-01'})" />
							</div>
						</div>
						<div class="field">	
							<div class="label">
								<label for="verifyDay" class="req">验证天数:</label>
							</div>
							<div class="input">
								 <form:input path="verifyDay" cssClass="small required validate-number" 	maxlength="5"  />
                				<span class="error" id="advice-required-verifyDay" style="display:none"></span>
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="verifyShopName" class="req">验证门店:</label>
							</div>
							<div class="input">
								<form:input path="verifyShopName" cssClass="small required"
									maxlength="100" readonly="true" />
								<form:hidden path="verifyShopIds" />
								<span class="error" id="advice-required-verifyShopName"
									style="display:none"></span>
							</div>
						</div>
						
						</div>
					<!-- 需要发码此div显示end -->

						<!-- 不需要发码此div显示 begin -->
                        <div id="selLogistics" <c:if test="${itemSale.sendCodeMode ne 0}">style="display: none;"</c:if> >

						<!-- 不发码显示是否需要物流 -->
						<div class="field">
							<div class="label label-radio">
								<label>是否需要物流:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="radio-9" name="postFlag" class="validate-one-required" value="0" <c:if test="${itemSale.postFlag eq 0}"> checked="checked" </c:if> /><label for="radio-9">不需要物流配送</label> 
									<input type="radio" id="radio-10" name="postFlag" class="validate-one-required"  value="1" <c:if test="${itemSale.postFlag eq 1}"> checked="checked" </c:if> /><label for="radio-10">需要物流配送</label>
								</div>
								<span class="error" id="advice-validate-one-required-postFlag"
									style="display:none"></span>
							</div>
						</div>
						
						<!-- 需要物流配送此div显示 begin-->
						<div id="isPost" <c:if test="${itemSale.postFlag eq 0}">style="display: none;"</c:if>>
						<div class="field">
							<div class="label">
								<label for="postArea" class="req">配送区域选择:</label>
							</div>
							<div class="input">
								<form:input path="postArea" cssClass="small required"
									 readonly="true"/><form:hidden path="postAreaCode"/>
									
								<span class="error" id="advice-required-postArea"
									style="display:none"></span>
							</div>
						</div>
						<div class="field">	
							<div class="label">
								<label for="logisticsFee" class="req">物流运费:</label>
							</div>
							<div class="input">
								 <form:input path="logisticsFee" cssClass=" required validate-number" maxlength="9"/>
                				 <input type="checkbox" id="logisticsType" name="logisticsType" value="1"   <c:if test="${itemSale.logisticsType eq 1}">checked="checked"</c:if>/>累计计算
								 <span class="error" id="advice-required-logisticsFee" style="display:none"></span>
							</div>
							
						</div>
						
						
						</div>
						<!-- 需要物流配送此div显示 end -->
						</div>
						<!-- 不需要发码此div显示  end-->
						
						
						
						<div class="field">
							<div class="label">
								<label for="saleStartTime" class="req">销售有效开始时间:</label>
							</div>
							<div class="input">
								<input type="text" id="saleStartTime" name="saleStartTime"
									class="date required" readOnly  value="<ct:time source="${itemSale.saleStartTime}" />"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'saleStopTime\')||\'2020-10-01\'}'})" />
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="saleStopTime" class="req">销售有效结束时间:</label>
							</div>
							<div class="input">
								<input type="text" id="saleStopTime" name="saleStopTime" 
									class="date required" readOnly value="<ct:time source="${itemSale.saleStopTime}" />"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'saleStartTime\')}',maxDate:'2020-10-01'})" />
							</div>
						</div>
						
						
						
						<div class="field">
							<div class="label">
								<label for="saleAreaName" class="req">销售区域:</label>
							</div>
							<div class="input">
								<form:input path="saleAreaName" cssClass="small required"
									maxlength="100" readonly="true" />
								<form:hidden path="saleAreaCode" />
								<span class="error" id="advice-required-saleAreaName"
									style="display:none"></span>
							</div>
						</div>
						
						
						
						
						<div class="field">
							<div class="label">
								<label for="saleShopName">销售门店:</label>
							</div>
							<div class="input">
								<form:input path="saleShopName" cssClass="small "
									maxlength="100" readonly="true" />
								<form:hidden path="saleShopIds" />
							</div>
						</div>
						
						
				<div class="field">
                    <div class="label label-checkbox">
                        <label>购买用户限制:</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox">
                            <input type="checkbox" id="checkbox-1" name="buyLimit" value="0" class="validate-one-required" <c:if test="${fn:contains(itemSale.buyLimit,'0')}"> checked="checked" </c:if>/><label for="checkbox-1">会员级别限制</label> 
							<input type="checkbox" id="checkbox-2" name="buyLimit" value="1" class="validate-one-required" <c:if test="${fn:contains(itemSale.buyLimit,'1')}"> checked="checked" </c:if>/><label for="checkbox-2">购买用户地市限制</label>
							<input type="checkbox" id="checkbox-3" name="buyLimit" value="2" class="validate-one-required" <c:if test="${fn:contains(itemSale.buyLimit,'2')}"> checked="checked" </c:if>/><label for="checkbox-3">单个用户购买数量</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-buyLimit" style="display:none"></span>
                    </div>
                </div>
						
						
				<div class="field" id="userLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'0')}"> style="display: none;" </c:if>>
                    <div class="label label-checkbox">
                        <label>会员级别限制:</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox">
                            <input type="checkbox" id="checkbox-4" name="userLimitCode" value="0" <c:if test="${fn:contains(itemSale.userLimitCode,'0')}"> checked="checked" </c:if> class="validate-one-required" /><label for="checkbox-4">金会员</label> 
							<input type="checkbox" id="checkbox-5" name="userLimitCode" value="1" <c:if test="${fn:contains(itemSale.userLimitCode,'1')}"> checked="checked" </c:if>  class="validate-one-required"/><label for="checkbox-5">铜会员</label>
							<input type="checkbox" id="checkbox-6" name="userLimitCode" value="2" <c:if test="${fn:contains(itemSale.userLimitCode,'2')}"> checked="checked" </c:if>  class="validate-one-required"/><label for="checkbox-6">银会员</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-userLimitCode" style="display:none"></span>
                    </div>
                </div>
						
						
						<div class="field" id="areaLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'1')}"> style="display: none;" </c:if>>
							<div class="label">
								<label for="areaLimitName" class="req">购买用户地市限制:</label>
							</div>
							<div class="input">
								<form:input path="areaLimitName" cssClass="small required"
									maxlength="100" readonly="true" />
								<form:hidden path="areaLimitCode" />
								<span class="error" id="advice-required-areaLimitName"
									style="display:none"></span>
							</div>
						</div>
						
						<div class="field" id="numLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'2')}"> style="display: none;" </c:if>>
							<div class="label">
								<label for="userPerBuyNum" class="req">单个用户购买数量:</label>
							</div>
							<div class="input">
								<form:input path="userPerBuyNum" cssClass="validate-number"
									maxlength="8"  />
								<span class="error" id="advice-required-userPerBuyNum"
									style="display:none"></span>
							</div>
						</div>
						
						<div class="field" >
							<div class="label">
								<label for="stockNum" class="req">商品库存:</label>
							</div>
							<div class="input">
								<input  name='stockNum'  id="stockNum" class=' required validate-number' type='text' value='${itemSale.stockNum }' maxlength='50' <c:if test="${storeNum eq initStoreNum}"> readonly="true" </c:if>/>
                <input type="checkbox" id="unlimitedStockNum" name="unlimitedStockNum" value="0"  <c:if test="${storeNum eq initStoreNum}">checked="checked" </c:if> >不限
								<span class="error" id="advice-required-stockNum"
									style="display:none"></span>
							</div>
						</div>
						<div class="field" >
							<div class="label">
								<label for="marketPrice" class="req">市场价:</label>
							</div>
							<div class="input">
								<form:input path="marketPrice" cssClass="validate-number"
									maxlength="9" />
								<span class="error" id="advice-required-marketPrice"
									style="display:none"></span>
							</div>
						</div>
						
						<div class="field" >
							<div class="label">
								<label for="marketPrice" class="req">商城价:</label>
							</div>
							<div class="input">
								<form:input path="shopPrice" cssClass=" validate-number"
									maxlength="9" />
								<span class="error" id="advice-required-shopPrice"
									style="display:none"></span>
							</div>
						</div>
						<div class="field" >
							<div class="label">
								<label for="settlePrice" class="req">结算价:</label>
							</div>
							<div class="input">
								<form:input path="settlePrice" cssClass=" validate-number"
									maxlength="9" />
								<span class="error" id="advice-required-settlePrice"
									style="display:none"></span>
							</div>
						</div>
						<div class="field" >
							<div class="label">
								<label for="stockNum" class="req">支付价格配置:</label>
							</div>
							<div class="input" id="priceId">
								 <table   id="TbData" name="TbData"  cellpadding="0" cellspacing="0" >
								  <c:forEach items="${priceList}" var="item">
							     <tr>
							     <td align='right'>
							     <c:choose>
							       <c:when test="${empty  item.priceType }">
							         ${map[item.priceTypeCode] }:
							       </c:when>
							       <c:otherwise>
							          ${item.priceType }:
							       </c:otherwise>
							     </c:choose>
							     </td>
							      <td>
							       <input  name='priceTypeCode'  type='hidden' value='${item.priceTypeCode }' />
							       <input  name='price'  class=' required validate-number' type='text' value='${item.price }' maxlength='50' />元
								  </td>
								</tr>
								 </c:forEach>
								
							  </table>&nbsp;
							</div>
						</div>


						<div class="buttons">
							<div class="highlight">
								<input type="submit" name="submit.highlight" value="提交" />
							</div>
							<input type="reset" name="reset" value="重置" /> <a
								href="${ctx}/item/saleitem/list" class="btnAnchor">返回</a>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<!-- end forms -->
	</div>


	<script type="text/javascript">
	<!--
		ajaxFormSubmit('#fm');
	//-->
	</script>



</body>
</html>