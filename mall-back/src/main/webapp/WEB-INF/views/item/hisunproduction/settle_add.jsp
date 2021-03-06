<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
	    <ht:head/>
	    <script type="text/javascript">
	    function checkIsY(){
			with (fm) {
				//对于需要验证的商品，一种是支付验证都成功后才能做清结算，另一种是支付成功，但是没有验证的就要做清结算，通过此字段来区分，这个字段当veriflag=Y时必输。
				if(verifyflag[0].checked) {
					reqCheckVerifyflagY.className="req";
					verifysettleflagS1.className="validate-one-required";
				} else if(verifyflag[1].checked){
					reqCheckVerifyflagY.className="";
					verifysettleflagS1.className="";
				}
			}
		}

	    </script>
	</head>
	<body>
		<div id="content">
			<div class="box">
			    <div class="title">
			        <h5>商品协议资料编辑</h5>
			    </div>
			    <form:form method="post" id="fm" commandName="settle" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
			        <div class="form">
			        <form:hidden path="merchid"/>
			        <form:hidden path="storeId"/>
			        <c:if test="${method == 'edit'}">
			        <form:hidden path="createTime"/>
			        <form:hidden path="createUser"/>
			        <form:hidden path="status"/>
			        <form:hidden path="syncGyStatus1"/>	
			        <form:hidden path="syncGyStatus2"/>
			        <form:hidden path="syncGyStatus3"/>		        
			        <form:hidden path="serviceid"/>
			        <form:hidden path="contractid"/>
			        <form:hidden path="productionid"/>
			        <form:hidden path="type"/>
			        <input type="hidden" id="backUrl" name="backUrl" />
			        </c:if>
			            <div class="fields">
			            	<c:choose>
			            	<c:when test="${empty settle.storeId}">
			            	<div class="field">
								<div class="label">
									<label for="storeId" class="req">所属商户：</label>
								</div>
								<div class="input">
								    <form:hidden path="storeId" />
									<form:input path="storeName" maxlength="100" readonly="true" cssClass="small required" />
									<span class="error" id="advice-required-storeName" style="display:none"></span>
								</div>
							</div>
							</c:when>
							<c:otherwise>
							<div class="field">
			                    <div class="label noinput">所属商户：</div>
			                    <div class="input">${storeName}</div>
			                </div>
			                </c:otherwise>
			                </c:choose>
			                
			                <!-- 这部分去掉,放在关联协议的时候实现。其字段换成隐藏域 -->
			                <form:input type="hidden" path="productionname" cssClass="small required max-length-125" maxlength="125" />
			                <!--  
							<div class="field">
			                    <div class="label">
			                        <label for="productionname" class="req">商品名:</label>
			                    </div>
			                    <div class="input">
									<form:input path="productionname" cssClass="small required max-length-125" maxlength="125" />
			                   		<span class="error" id="advice-required-productionname" style="display:none"></span>
			                   		<span class="error" id="advice-max-length-productionname" style="display:none"></span>
			                    </div>
			                </div>
			                -->
			                <div class="field">
			                    <div class="label label-radio">
			                        <label>资金类型：</label>
			                    </div>
			                    <div class="radios">
			                        <div class="radio">
			                            <input type="checkbox" id="checkbox-1" name="capitaltype1" checked value="1" onclick="return false;"  /><label for="checkbox-1">现金</label>
			                            <input type="checkbox" id="checkbox-2" name="capitaltype2" checked value="1" onclick="return false;" /><label for="checkbox-2">商城币</label>
			                        	<input type="checkbox" id="checkbox-3" name="capitaltype3" checked value="1" onclick="return false;" /><label for="checkbox-3">积分</label>
			                        	<input type="checkbox" id="checkbox-4" name="capitaltype4" checked value="1" onclick="return false;" /><label for="checkbox-4">话费</label>
			                        </div>
			                    </div>
			                </div>
			                <div class="field">
			                    <div class="label label-radio">
			                        <label for="productiontypeName" class="req">计费分类：</label>
			                    </div>   
			                     <div class="input">
			                     	<form:input path="productiontypeName" maxlength="80" readonly="true" cssClass="small required" value="${settle.productiontypeName}"/>
			                     	<form:hidden path="productiontype" value="${settle.productiontype}"/>
			                        <span class="error" id="advice-required-productiontypeName" style="display:none"></span>
								</div>
			                </div>
			                <div class="field">
								<div class="label label-radio">
									<label for="verifyflag" class="req">验证标识：</label>
								</div>
								<div class="radios">
									<div class="radio">
										<input type="radio" name="verifyflag" value="Y" <c:if test="${settle.verifyflag == 'Y'}">checked="checked"</c:if> onclick="checkIsY()" class="validate-one-required" /><label for="verifyflagY">验证</label>
										<input type="radio" name="verifyflag" value="N" <c:if test="${settle.verifyflag == 'N'}">checked="checked"</c:if> <c:if test="${method == 'add'}">checked="checked"</c:if> onclick="checkIsY()" /><label for="verifyflagN">不验证</label>
									</div>
									<span class="error" id="advice-validate-one-required-verifyflag" style="display:none"></span>
								</div>
							</div>
							<div class="field">
								<div class="label label-radio">
									<label for="verifysettleflag" id="reqCheckVerifyflagY">验证结算：</label>
								</div>
								<div class="radios">
									<div class="radio">
										<input type="radio" id="verifysettleflagS1" name="verifysettleflag" value="S1" <c:if test="${settle.verifysettleflag == 'S1'}">checked="checked"</c:if> onMouseOver="chk=checked;" onclick="checked=chk=!chk;" /><label for="verifysettleflagS1">支付后结算</label>
										<input type="radio" id="verifysettleflagS2" name="verifysettleflag" value="S2" <c:if test="${settle.verifysettleflag == 'S2'}">checked="checked"</c:if> onMouseOver="chk=checked;" onclick="checked=chk=!chk;" /><label for="verifysettleflagS2">验证后结算</label>
									</div>
									<span class="error" id="advice-validate-one-required-verifysettleflag" style="display:none"></span>
								</div>
							</div>
							<div class="field">
								<div class="label">
									<label for="productionefftime" class="req">产品上线时间：</label>
								</div>
								<div class="input">
									<input type="text" id="productionefftime" name="productionefftime" value="<ct:time source="${settle.productionefftime}" />" class="date required" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'date',maxDate:'#F{$dp.$D(\'productionexptime\')||\'2020-10-01\'}'})" />
			                        <span class="error" id="advice-server-productionefftime" style="display:none"></span>
								</div>
							</div>
							<div class="field">
								<div class="label">
									<label for="productionexptime" class="req">产品下线时间：</label>
								</div>
								<div class="input">
									<input type="text" id="productionexptime" name="productionexptime" value="<ct:time source="${settle.productionexptime}" />" class="date required" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'productionefftime\')}',maxDate:'2020-10-01'})" />
			                        <span class="error" id="advice-server-productionexptime" style="display:none"></span>
								</div>
							</div>
							     <div class="field">
				                    <div class="label">
				                        <label for="price" >商品单价：</label>
				                    </div>
				                    <div class="input">
				                        <input type="text" id="price" name="price" <c:if test="${empty settle.price}">value="0"</c:if> <c:if test="${not empty settle.price}">value="${settle.price}"</c:if> class="small required validate-number min-length-0 max-length-9" maxlength="9" style="width:100px" />&nbsp;&nbsp;元
				                    	<span class="error" id="advice-required-price" style="display:none"></span>
				                    	<span class="error" id="advice-validate-number-price" style="display:none"></span>
				                        <span class="error" id="advice-min-length-price" style="display:none"></span>
				                        <span class="error" id="advice-max-length-price" style="display:none"></span>
				                        <span class="error" id="advice-server-price" style="display:none"></span>
				                    </div>
				                </div>
				                <div class="field">
				                    <div class="label" >
				                        <label for="settlementprice" >结算单价 ：</label>
				                    </div>
				                    <div class="input">
				                        <input type="text" id="settlementprice" name="settlementprice"  <c:if test="${empty settle.settlementprice}">value="0"</c:if> <c:if test="${not empty settle.settlementprice}">value="${settle.settlementprice}"</c:if>class="small required validate-number min-length-0 max-length-9" maxlength="9" style="width:100px"/>&nbsp;&nbsp;元
				                    	<span class="error" id="advice-required-settlementprice" style="display:none"></span>
				                    	<span class="error" id="advice-validate-number-settlementprice" style="display:none"></span>
				                        <span class="error" id="advice-min-length-settlementprice" style="display:none"></span>
				                        <span class="error" id="advice-max-length-settlementprice" style="display:none"></span>
				                        <span class="error" id="advice-server-settlementprice" style="display:none"></span>
				                    </div>
				                </div>
				            <c:if test="${not empty settle.filePath}">
			                <div class="field">
			                    <div class="label noinput">
			                        <label for="filePath" >协议文件:</label>
			                    </div>
			                    <div class="input">
			                    	<a href="${ctx}/item/hisunproduction/downfile/${settle.id}">下载协议文件</a>
			                    </div>
			                </div>
			                </c:if>  
			                <div class="field">
								<div class="label">
									<label for="uploadFile">上传协议文件：</label>
								</div>
								<div class="input input-file">
									<input type="file" id="uploadFile" name="uploadFile" size="40" class="validate-file-dat-cfg" />
									<span class="error" id="advice-validate-file-uploadFile" style="display:none"></span>
								</div>
							</div>  
				                
							<!-- 商盟必填 ################## start-->
							<div class="field">
									<div class="label">
										<label >商盟必填：</label>
									</div>
									<div class="input">
									 	<a href="#" id="show">我要填写</a> 
									</div>
							</div>
							<div id="shangm" style="display:none">
									<div class="field">
										<div class="label">
											<label for="contracteffdate" >合同生效日期：</label>
										</div>
										<div class="input">
											<input type="text" id="contracteffdate" name="contracteffdate" value="<ct:time source="${settle.contracteffdate}"  sfmt="yyyyHHmm" tfmt="yyyy-HH-mm"/>" class="date required" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'contractexpdate\')||\'2020-10-01\'}'})" />
					                        <span class="error" id="advice-server-contracteffdate" style="display:none"></span>
										</div>
									</div>
									<div class="field">
										<div class="label">
											<label for="contractexpdate">合同失效时间：</label>
										</div>
										<div class="input">
											<input type="text" id="contractexpdate" name="contractexpdate" value="<ct:time source="${settle.contractexpdate}"  sfmt="yyyyHHmm" tfmt="yyyy-HH-mm"/>" class="date required" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'contracteffdate\')}',maxDate:'2020-10-01'})" />
					                        <span class="error" id="advice-server-contractexpdate" style="display:none"></span>
										</div>
									</div>
							
								<div class="field">
									<div class="label">
										<label for="verifyexpdate">验证截止日期：</label>
									</div>
									<div class="input">
										<input type="text" id="verifyexpdate" name="verifyexpdate" value="<ct:time source="${settle.verifyexpdate}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>" class="date" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				                        <span class="error" id="advice-server-verifyexpdate" style="display:none"></span>
									</div>
								</div>
								<div  class="field" >
				                    <div class="label noinput"><label for="cityid">地市：</label></div>
				                    <div class="input">
				                    	<input type="text" id="cityname" name="cityname" readonly value="${settle.cityname}" class="small min-length-0 max-length-16" maxlength="16" />
				                        <span class="error" id="advice-min-length-cityname" style="display:none"></span>
				                        <span class="error" id="advice-max-length-cityname" style="display:none"></span>
				                        <span class="error" id="advice-server-cityname" style="display:none"></span>
									</div>
				                    <input type="hidden" id="cityid" name="cityid" />
				                </div>
				                <div class="field">
				                    <div class="label">
				                        <label for="cityprofitrate">地市分成比例：</label>
				                    </div>
				                    <div class="input">
				                        <form:input path="cityprofitrate" cssClass="small min-length-0 max-length-1" maxlength="1" />
				                        <span class="error" id="advice-min-length-cityprofitrate" style="display:none"></span>
				                        <span class="error" id="advice-max-length-cityprofitrate" style="display:none"></span>
				                        <span class="error" id="advice-server-cityprofitrate" style="display:none"></span>
				                    </div>
				                </div>
				                <div  class="field" >
				                    <div class="label noinput"><label for="agentid">代理商：</label></div>
				                    <div class="input">
				                    	<input type="text" id="agentname" name="agentname" readonly value="${settle.agentname}" class="small min-length-0 max-length-60" maxlength="60" />
				                        <span class="error" id="advice-min-length-agentname" style="display:none"></span>
				                        <span class="error" id="advice-max-length-agentname" style="display:none"></span>
				                        <span class="error" id="advice-server-agentname" style="display:none"></span>
									</div>
				                    <input type="hidden" id="agentid" name="agentid" />
				                </div>
				                <div class="field">
				                    <div class="label">
				                        <label for="agentprofitrate">代理商分成比例：</label>
				                    </div>
				                    <div class="input">
				                        <form:input path="agentprofitrate" cssClass="small min-length-0 max-length-1" maxlength="1" />
				                        <span class="error" id="advice-min-length-agentprofitrate" style="display:none"></span>
				                        <span class="error" id="advice-max-length-agentprofitrate" style="display:none"></span>
				                        <span class="error" id="advice-server-agentprofitrate" style="display:none"></span>
				                    </div>
				                </div>
				                <div class="field">
				                    <div class="label">
				                        <label for="settleperiod">结算分期数：</label>
				                    </div>
				                    <div class="input">
				                        <form:input path="settleperiod" cssClass="small validate-digits min-length-0 max-length-2" maxlength="2" />
				                    	<span class="error" id="advice-validate-digits-settleperiod" style="display:none"></span>
				                        <span class="error" id="advice-min-length-settleperiod" style="display:none"></span>
				                        <span class="error" id="advice-max-length-settleperiod" style="display:none"></span>
				                        <span class="error" id="advice-server-settleperiod" style="display:none"></span>
				                    </div>
				                </div>
				                <div class="field">
									<div class="label label-radio">
										<label for="settleperiodtype">结算分期类型：</label>
									</div>
									<div class="radios">
										<div class="radio">
											<input type="radio" name="settleperiodtype" value="1P2P" <c:if test="${settle.settleperiodtype == '1P2P'}">checked="checked"</c:if> /><label for="settleperiodtype1P2P">一期支付二期支付</label>
											<input type="radio" name="settleperiodtype" value="1P2V" <c:if test="${settle.settleperiodtype == '1P2V'}">checked="checked"</c:if> /><label for="settleperiodtype1P2V">一期支付二期验证</label>
											<input type="radio" name="settleperiodtype" value="1V2V" <c:if test="${settle.settleperiodtype == '1V2V'}">checked="checked"</c:if> /><label for="settleperiodtype1V2V">一期验证二期验证</label>
										</div>
									</div>
								</div>
								<div class="field">
									<div class="label">
										<label for="settledate1">结算时间：</label>
									</div>
									<div class="input">
										<input type="text" id="settledate1" name="settledate1" value="<ct:time source="${settle.settledate1}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>" class="date" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				                        <span class="error" id="advice-server-settledate1" style="display:none"></span>
									</div>
								</div>
								<div class="field">
				                    <div class="label">
				                        <label for="settlerate1">结算比例：</label>
				                    </div>
				                    <div class="input">
				                        <form:input path="settlerate1" cssClass="small min-length-0 max-length-1" maxlength="1" />
				                    	<span class="error" id="advice-required-settlerate1" style="display:none"></span>
				                        <span class="error" id="advice-min-length-settlerate1" style="display:none"></span>
				                        <span class="error" id="advice-max-length-settlerate1" style="display:none"></span>
				                        <span class="error" id="advice-server-settlerate1" style="display:none"></span>
				                    </div>
				                </div>
				                <div class="field">
									<div class="label">
										<label for="settledate2">结算时间2：</label>
									</div>
									<div class="input">
										<input type="text" id="settledate2" name="settledate2" value="<ct:time source="${settle.settledate2}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>" class="date" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				                        <span class="error" id="advice-server-settledate2" style="display:none"></span>
									</div>
								</div>
								<div class="field">
				                    <div class="label">
				                        <label for="settlerate2">结算比例2：</label>
				                    </div>
				                    <div class="input">
				                        <form:input path="settlerate2" cssClass="small min-length-0 max-length-1" maxlength="1" />
				                    	<span class="error" id="advice-required-settlerate2" style="display:none"></span>
				                        <span class="error" id="advice-min-length-settlerate2" style="display:none"></span>
				                        <span class="error" id="advice-max-length-settlerate2" style="display:none"></span>
				                        <span class="error" id="advice-server-settlerate2" style="display:none"></span>
				                    </div>
				                </div>
				                <div class="field">
									<div class="label">
										<label for="settledate3">结算时间3：</label>
									</div>
									<div class="input">
										<input type="text" id="settledate3" name="settledate3" value="<ct:time source="${settle.settledate3}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>" class="date" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				                        <span class="error" id="advice-server-settledate3" style="display:none"></span>
									</div>
								</div>
								<div class="field">
				                    <div class="label">
				                        <label for="settlerate3">结算比例3：</label>
				                    </div>
				                    <div class="input">
				                        <form:input path="settlerate3" cssClass="small min-length-0 max-length-1" maxlength="1" />
				                    	<span class="error" id="advice-required-settlerate3" style="display:none"></span>
				                        <span class="error" id="advice-min-length-settlerate3" style="display:none"></span>
				                        <span class="error" id="advice-max-length-settlerate3" style="display:none"></span>
				                        <span class="error" id="advice-server-settlerate3" style="display:none"></span>
				                    </div>
				                </div>  
			                </div>
			                <!-- 商盟必填 ################## end-->
			              
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
		</div>
<script type="text/javascript">
$().ready(function() {
	//获取来源地址
	var url = document.referrer;
	$("#backUrl").val(url);
	//选择商户
   	$("#storeName").click(function(){
		selectStore("storeId","storeName",'',{shopClass:'2',status:'3',merchid:'1',sort:'1'});
	});
	//地市选择
	selectRegion("#cityname","cityid","cityname",${regionCode},{maxItems:1, level:1});
	//选择代理商
   	$("#agentname").click(function(){
		selectStore("agentid","agentname",'');
	});
	//显示商盟必填字段
   	$("#show").click(function(){
   		if($("#shangm").is(":visible")){
   			$("#shangm").hide();
   			$(this).html("我要填写");
   		}else{
   			$("#shangm").show();
   			$(this).html("隐藏");
   		}
		
	});
	//显示费率
	$("#productiontypeName").click(function(){
		var storeId=$("#storeId").val();
    	if(storeId==""){
    		alert("请选择商户！");
    		$("#storeName").focus();
    		return;
    	}
    	selectFeeByStore("productiontype","productiontypeName",'',{storeId:$("#storeId").val()} );
	});
});
</script>
	</body>
</html>