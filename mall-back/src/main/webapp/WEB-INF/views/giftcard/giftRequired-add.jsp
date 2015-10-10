<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
 	<script type="text/javascript">
	$().ready(function() {
	 	var url = document.referrer;
	 	$("#backUrl").val(url);
	 	
	 	//选择商户
    	$("#storeName").click(function(){
    		selectStoreCallBack("",function(storeId,storeName,shopClass){
    			$("#storeId").val(storeId);
        		$("#storeName").val(storeName);
   
        		$("#storeName").focus();
    	    	$("#storeName").blur();
    	    
    		});
		});
	 	
	 	
	 	
	 	selectRegionCallBack("#regionCodeName",function(regionCode,regionCodeName){
			$("#regionCode").val(regionCode);
			$("#regionCodeName").val(regionCodeName);
			
			$("#regionCodeName").focus();
			$("#regionCodeName").blur();

		},0,{index:3/*maxItems:1*/});
 	});
	
</script>
    </head>
<body>

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>添加礼品卡需求</h5>
    </div>
		
		  <form:form action="${ctx}/gift/required/add" method="post" id="fm" commandName="giftRequired" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
      	<input type="hidden" id="backUrl" name="backUrl" />
        <div class="form">
					<div class="fields">
					
					  <div class="field">
                    <div class="label noinput">批次号：</div>
                    <div class="input">自动生成</div>
                </div>
                
                <div class="field">
						<div class="label">
							<label for="storeId" class="req">所属商户：</label>
						</div>
						<div class="input">
						    <form:hidden path="storeId" maxlength="8"/>
							<input id="storeName" id="storeName" maxlength="100" readonly="true" class="small required" />
							<span class="error" id="advice-required-storeName" style="display:none"></span>
						</div>
				</div>
	
 					<div class="field" >
							<div class="label">
								<label for="modelNo" class="req">卡型号:</label>
							</div>
							<div class="input">
								<form:input path="modelNo" maxlength="100" cssClass="small required" />
								<span class="error" id="advice-required-modelNo" style="display:none"></span>
							</div>
					</div>	
						
								
								
					<div class="field" >
							<div class="label">
								<label for="cardNum" class="req">需要礼品卡数量:</label>
							</div>
							<div class="input">
								<form:input path="cardNum" maxlength="7" cssClass="small required validate-number " />
								<span class="error" id="advice-required-cardNum" style="display:none"></span>
							</div>
					</div>	
					
						<div class="field">
								<div class="label">
									<label for="issuingTime" class="req">期望发卡时间:</label>
								</div>
								<div class="input">
									<input type="text" id="issuingTime" name="issuingTime" 
										class="date required" readOnly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2020-10-01'})" />
								</div>
							</div>
					
					
						<div class="field">
								<div class="label">
									<label for="effortDate" class="req">礼品卡生效日期:</label>
								</div>
								<div class="input">
									<input type="text" id="effortDate" name="effortDate"
										class="date required" readOnly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'expiryDate\')||\'2020-10-01\'}'})" />
								</div>
							</div>
							<div class="field">
								<div class="label">
									<label for="expiryDate" class="req">礼品卡失效日期:</label>
								</div>
								<div class="input">
									<input type="text" id="expiryDate" name="expiryDate" 
										class="date required" readOnly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'effortDate\')}',maxDate:'2020-10-01'})" />
								</div>
							</div>
				
							<div class="field">
							<div class="label">
								<label for="exchangeMode" class="req">礼品兑换方式:</label>
							</div>
							<div class="select">
								<select name="exchangeMode" id="exchangeMode">
										<option value="1">一兑一</option>
										<option value="2">二兑一</option>
										<option value="3">三兑一</option>
										<option value="4">四兑一</option>
										<option value="5">五兑一</option>
										<option value="6">六兑一</option>
										<option value="7">七兑一</option>
										<option value="8">八兑一</option>
										<option value="9">九兑一</option>
										<option value="10">十兑一</option>
								</select>
							</div>
						</div>
						
						
						<div class="field" >
							<div class="label">
								<label for="parValue" class="req">面额:</label>
							</div>
							<div class="input">
								<form:input path="parValue" maxlength="5" cssClass="small required validate-number " style="width:100px"/>元
								<span class="error" id="advice-required-parValue" style="display:none"></span>
							</div>
					</div>	
					
					<div class="field" >
							<div class="label">
								<label for="requiredUser" class="req">客户名:</label>
							</div>
							<div class="input">
								<form:input path="requiredUser" maxlength="10" cssClass="small required " />
								<span class="error" id="advice-required-requiredUser" style="display:none"></span>
							</div>
					</div>	
					
					<div class="field">
							<div class="label">
								<label for="regionCodeName" class="req">所属区域:</label>
							</div>
							<div class="input">
								<input id="regionCodeName" class="small required" readonly="true" />
								<form:hidden path="regionCode" />
								<span class="error" id="advice-required-regionCodeName"
									style="display:none"></span>
							</div>
						</div>
						
					
					<div class="field">
							<div class="label label-textarea" >
								<label for="cardFaceMsg" class="req">卡面封套要求：</label>
							</div>
							<div class="input">
								<textarea id="cardFaceMsg" name="cardFaceMsg" class="max-length-125 required"
									rows="8" cols="100"></textarea>
							</div>
						</div>	
						
						<div class="field">
							<div class="label label-textarea">
								<label for="remark" class="req">兑换礼品描述：</label>
							</div>
							<div class="input">
								<textarea id="remark" name="remark" class="max-length-100 required"
									rows="8" cols="100"></textarea>
							</div>
						</div>		
				
					
						<div class="buttons">
							<div class="highlight">
								<input id="submitButton" type="submit" name="submit.highlight" value="提交" />
							</div>
								<input type="reset" name="reset" value="重置" />
                    			<input type="button" class="common_btn" onclick="history.back();" value="返回" />
							
						</div>	
		</div>
		</div>	
    </form:form>
</div>

</div>
</body>
</html>