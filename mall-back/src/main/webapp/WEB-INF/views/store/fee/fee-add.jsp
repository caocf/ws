<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">

 $().ready(function() {
	 
	         
		 $("#productionTypes").change(function(){
			if($("#productionTypes").find("option:selected").text() != '请选择'){
				$("#productionTypeName").val($("#productionTypes").find("option:selected").text());
				$("#productionType").val($(this).val());
			}
			
			////else{
			///	$("#productionTypeName").val('');
			///	$("#productionType").val('flag');
			///}
			
		});
	 
	 
	 
		$("[name='feelevelflag']").click(function() {
			var value = $(this).val();
			
			if( value == ' '){
				$("#upreference1").val("999999999.99");
				$("#tab tr:gt(1)").hide();
			
			}
			
			if( value != ' ' ){
				$("#upreference1").val("0.00");
				$("#fixfeeamount1").val("");
				$("#feerate1").val("");
				$("#tab tr:gt(1)").show();
			}

		});
		
		///feelevelflag1
		
		///$("#upreference1").blur(function() {
		///	if($("#feelevelflag1").val()=="0" ||$("#feelevelflag1").val()=="1"){
		  ///    if($("#upreference1") !="0"){
		  ///     alert("计费参考一必填0");
		  ///    }
		  ///      }
          /// });
		
		$("#fixfeeamount1").blur(function() {
		if($("#fixfeeamount1").val()!=null && $("#fixfeeamount1").val()!=""){
		if($("#fixfeeamount1").val()< $("#upreference1").val()){
		     ////  alert("最小值不能大于最大值");
		      
		        }
		}
		
           });
		
		
	    $("#fixfeeamount2").blur(function(){
	    if($("#fixfeeamount2").val()!=null && $("#fixfeeamount2").val()!=""){
	    if($("#fixfeeamount2").val()<$("#upreference2").val()){
       ///      alert("最小值不能大于最大值");  
	    }
	    }
	    })
	    
	    
	       $("#fixfeeamount3").blur(function(){
	     if($("#fixfeeamount3").val()!=null && $("#fixfeeamount3").val()!=""){
	    if($("#fixfeeamount3").val()<$("#upreference3").val()){
        ////     alert("最小值不能大于最大值");  
	    }
	    }
	    })
	    
	        $("#fixfeeamount4").blur(function(){
	      if($("#fixfeeamount4").val()!=null && $("#fixfeeamount4").val()!=""){  
	    if($("#fixfeeamount4").val()<$("#upreference4").val()){
            /// alert("最小值不能大于最大值");  
	    }
	    }
	    })
	    
	    
	   
	    
	    $("#feemothod1").click(function(){
	    
	      if($("#feemothod1").attr('checked')){
	     ////$("#fixfeeamount5").val(9999999.99);
	    }
	    })
	   //// $("#fixfeeamount5").val(9999999.99);
	   
	        $("#fixfeeamount5").blur(function(){
	        if($("#fixfeeamount5").val()!=null && $("#fixfeeamount5").val()!=""){
	    if($("#fixfeeamount5").val()<$("#upreference5").val()){
	    
          ///   alert("最小值不能大于最大值");  
	    }
	    }
	    })
	    
		$("[name='feemode']").click(function() {
			var value = $(this).val();
			
			if(value == 0){
				$("#jifei").hide();
			}else{
				$("#jifei").show();
			}
			
			if(value == 1 || value == 2){
				if(value == 1){
					$("#minfeeamount").val("0");
					$("#minfeeamount").attr("readonly","readonly");
					$("#maxfeeamount").val("999999999.99");
					$("#maxfeeamount").attr("readonly","readonly");
				}else{
					$("#minfeeamount").val("");
					$("#minfeeamount").removeAttr("readonly");
					$("#maxfeeamount").val("");
					$("#maxfeeamount").removeAttr("readonly","readonly");
				}
				$("#beganamount").val("0");
				$("#beganamount").attr("readonly","readonly");
				
			}else{
				//$("#beganamount").val("");
				$("#beganamount").removeAttr("readonly");
				//$("#minfeeamount").val("");
				$("#minfeeamount").removeAttr("readonly");
				//$("#maxfeeamount").val("");
				$("#maxfeeamount").removeAttr("readonly","readonly");
			}

		});
		
		$("[name='feemothod']").click(function() {
			var value = $(this).val();
			if(value == 1){
				$("#tab tr").find("td:eq(3)").hide();
				$("#tab tr").find("td:eq(3) input").val("");
				$("#tab tr").find("td:eq(2)").show();
			}
			if(value == 2){
				$("#tab tr").find("td:eq(2)").hide();
				$("#tab tr").find("td:eq(2) input").val("");
				$("#tab tr").find("td:eq(3)").show();
			}
			if(value == 3){
				$("#fenceng").hide();
				$("#clearType2").hide();
				$("#clearType2_2").hide();
				$("#clearType1").attr("checked","checked");
			}else{
				$("#fenceng").show();
				$("#clearType2").show();
				$("#clearType2_2").show();
			}

		});
		              
		      
		var feemothod = $("[name='feemothod']:checked").val();
		if(feemothod == 1){
			$("#tab tr").find("td:eq(3)").hide();
			$("#tab tr").find("td:eq(3) input").val("");
			$("#tab tr").find("td:eq(2)").show();
		}
		if(feemothod == 2){
			$("#tab tr").find("td:eq(2)").hide();
			$("#tab tr").find("td:eq(2) input").val("");
			$("#tab tr").find("td:eq(3)").show();
		}
		if(feemothod == 3){
			$("#fenceng").hide();
			$("#clearType2").hide();
			$("#clearType2_2").hide();
			$("#clearType1").attr("checked","checked");
		}
		var feelevelflag = $("[name='feelevelflag']:checked").val();
		if(feelevelflag == ' '){
			$("#tab tr:gt(1)").hide();
		}
	
});
  		
 		function selectPType(){
 			$("#clearTypeSelectDiv").css('display','none');
 			$("#fixfeeamount1").val(0);
 		}
		function selectCType(){
			$("#clearTypeSelectDiv").css('display','block');
			$("#fixfeeamount1").val('');
 		}
 
    </script>
    
</head>
<body>

<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div  class="title">
        <h5>费率信息</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="storeFee" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" >
        <form:hidden path="storeId"/>
         <form:hidden path="feeType"/>
         <form:hidden path="feedrtflag"/>
         <form:hidden path="tradeMode"/>
        <c:if test="${storeFee.id!=null }">
          <form:hidden path="id"/>
          <form:hidden path="capitalType"/>
        </c:if>
        <div class="form">
            <div class="fields">
            
           
           
           
            <div class="field">
                    <div class="label label-radio">
                        <label>资金类型:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="checkbox" id="capitalType1" name="capitalType1" <c:if test="${storeFee.capitalType1 ==1 }">checked</c:if> value="1"/><label for="capitalType1">现金</label>
                            <input type="checkbox" id="capitalType2" name="capitalType2" <c:if test="${storeFee.capitalType2 ==1 }">checked</c:if> value="1"/><label for="capitalType2">商城币</label>
                        	<input type="checkbox" id="capitalType3" name="capitalType3" <c:if test="${storeFee.capitalType3 ==1 }">checked</c:if> value="1"/><label for="capitalType3">积分</label>
                        	<input type="checkbox" id="capitalType4" name="capitalType4" onclick="selectEffortdate(${storeFee.effortdate });" <c:if test="${storeFee.capitalType4 ==1 }">checked</c:if> value="1"/><label for="capitalType4">话费</label>
                        </div>
                        <form:hidden path="capitalType"/>
                       <span class="error" id="advice-server-capitalType" style="display:none;color: red;">  </span>
                    </div>
                </div>
              
             <div class="field">
                    <div class="label label-radio">
                        <label>计费方法:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio"  id="feemothod1" name="feemothod" ${storeFee.feemothod=="1"?"checked":"" } value="1"/><label for="feemothod1">按固定金额收取</label>
                            <input type="radio"  id="feemothod2" name="feemothod" ${storeFee.feemothod=="2"?"checked":"" } value="2"/><label for="feemothod2">按金额固定百分比收取</label>
                        	<input type="radio"  id="feemothod3" name="feemothod" ${storeFee.feemothod=="3"?"checked":"" } value="3"/><label for="feemothod3">按照商品差价计算</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
              	</div>
               
            
            <div class="field">
                    <div class="label label-radio">
                        <label>清算类别:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<input type="radio" id="clearType2" name="clearType"  value="C" checked="checked" onclick="selectCType();"  /><label id="clearType2_2" for="clearType2">商品类别(C)</label>
                            <input type="radio" id="clearType1" name="clearType"  value="P" onclick="selectPType();" <c:if test="${storeFee.clearType == 'P' }">checked</c:if> /><label for="clearType1">商品(P)</label>
                            
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
             </div>
             
               <div class="field">
                    <div class="label">
                        <label for="settleDay" class="req">费率分类:</label>
                    </div>
                    <div class="input">
                    
	                    <select id="productionTypes" name="productionTypes" style="width:250px" class="validate-selection">
	                    	<option>请选择</option>
		                    <c:forEach items="${feeList}" var="item" >
		                     <option value="${item.id }" ${storeFee.productionType == item.id?"selected":"" }>${item.name}</option>
		                    </c:forEach>
	                    </select>
	                    <span class="error" id="advice-server-productionTypes" style="display:none"></span>
	                    <c:if test="${empty feeList}"><span class="error">该商户尚未添加(或未同步成功)费率分类</span></c:if>
	                  
                    </div>
                </div>
              <input type="hidden" id="productionType" name="productionType" value="${storeFee.productionType}">
             
             <input type="hidden" id="productionTypeName" class="small required" name="productionTypeName" style="width:300px" maxlength="60" value="${productionTypeName }"/>
                
            
                <div class="field">
						<div class="label">
							<label for="effortdate" class="req">生效日期:</label>
						</div>
						<div class="input">
									<input type="text" id="startTime" name="startTime"  value="<ct:time source="${storeFee.effortdate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>"
										class="txt required Wdate" readOnly
										onfocus="WdatePicker({vel:'effortdate',dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',minDate:'date',maxDate:'#F{$dp.$D(\'endTime\',{d:-1})||\'2050-10-01\'}'})" />
										 <input type="hidden" name="effortdate" id="effortdate""  value="${storeFee.effortdate }"/>
						</div>
				</div>
				
				<div class="field">
					<div class="label">
						<label for="expirydate" class="req">失效日期:</label>
								</div>
						
						<div class="input">
									<input type="text" id="endTime" name="endTime"  value="<ct:time source="${storeFee.expirydate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>"
										class="txt required Wdate" readOnly
										onfocus="WdatePicker({vel:'expirydate',dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startTime\',{d:1})}',maxDate:'2050-10-01'})" />
										<input type="hidden" name="expirydate" id="expirydate" value="${storeFee.expirydate }" />
						</div>
				</div>
				 
		<div id="clearTypeSelectDiv">
              	
              	 <div class="field">
                    <div class="label label-radio">
                        <label>收费周期:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<input type="radio"  id="feeperiodunit3" name="feeperiodunit" ${storeFee.feeperiodunit=="2"?"checked":"" } value="2"/><label for="feeperiodunit3">日</label>
                            <input type="radio"  id="feeperiodunit2" name="feeperiodunit" ${storeFee.feeperiodunit=="1"?"checked":"" } value="1"/><label for="feeperiodunit2">月</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
              	</div>
              	
                
                <div class="field">
                    <div class="label">
                        <label for="fcName" >收费同期数量:</label>
                    </div>
                    <div class="input">
                     <form:input path="fcName" cssClass="small  validate-pattern-/^\d+(\.\d+)?$/ " maxlength="3" />
                         <span class="error" id="advice-validate-number-fcName" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label label-radio">
                        <label>收费方式:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio"  id="feemode2" name="feemode" ${storeFee.feemode=="1"?"checked":"" } value="1"/><label for="feemode2">实时单笔收取</label>
                        	<input type="radio"  id="feemode4" name="feemode" ${storeFee.feemode=="3"?"checked":"" } value="3"/><label for="feemode4">按周期汇总计算并收取</label>
                         	<input type="radio"  id="feemode1" name="feemode" ${storeFee.feemode=="0"?"checked":"" } value="0"/><label for="feemode1">不收取</label>
                           
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
              	</div>
              	
              	<div id="jifei" <c:if test="${storeFee.feemode=='0' }"> style="display: none;"</c:if> >
              	
              	
              	
              	 <div class="field">
                    <div class="label">
                        <label for="beganamount" class="req">计费起始金额:</label>
                    </div>
                    <div class="input">
                     <form:input path="beganamount" maxlength="13" id="beganamount" cssClass="small required  validate-pattern-/^\d+(\.\d+)?$/"/>
                         <span class="error" id="aadvice-validate-number-beganamount" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="minfeeamount" class="req">最低收费金额:</label>
                    </div>
                    <div class="input">
                     <form:input path="minfeeamount" maxlength="11" id ="minfeeamount" cssClass="small required  validate-pattern-/^\d+(\.\d+)?$/"/>
                         <span class="error" id="advice-validate-number--minfeeamount" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="maxfeeamount" class="req">最高收费金额:</label>
                    </div>
                    <div class="input">
                     <form:input path="maxfeeamount" maxlength="11" id = "maxfeeamount" cssClass="small required  validate-pattern-/^\d+(\.\d+)?$/"/>
                         <span class="error" id="advice-validated-number-maxfeeamount" style="display:none"></span>
                    </div>
                </div>
                
              <div id="fenceng">
                
                <div class="field">
                    <div class="label label-radio">
                        <label>分档/套档标志:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<input type="radio"  id="feelevelflag3" name="feelevelflag" ${storeFee.feelevelflag=="1"?"checked":"" } value="1"/><label for="feelevelflag3">分层</label>
                            <input type="radio"  id="feelevelflag1" name="feelevelflag" ${storeFee.feelevelflag==" "?"checked":"" } value=" "/><label for="feelevelflag1">空挡</label>
                        	<input type="radio"  id="feelevelflag2" name="feelevelflag" ${storeFee.feelevelflag=="0"?"checked":"" } value="0"/><label for="feelevelflag2">套档</label>
                        	 </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
              	</div>
             
              <div class="field">
                    <div class="label label-radio">
                        <label>分档/套档依据:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="feelvlbasicflag1" name="feelvlbasicflag" ${storeFee.feelvlbasicflag!="1"?"checked":"" } value="0"/><label for="feelvlbasicflag1">金额</label>
                        	<input type="radio" id="feelvlbasicflag2" name="feelvlbasicflag" ${storeFee.feelvlbasicflag=="1"?"checked":"" } value="1"/><label for="feelvlbasicflag2">笔数</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
              	</div>
             
              	  <table id="tab" width="70%" align="left"  cellpadding="5px" cellspacing="3px" >
              	   <tr align="center" >
              	   <td>计费</td><td>计费参考（单位：元）</td><td>固定费用金额（单位：元）</td><td>费率（单位：%）
              	   
              	   </td>
              	   
              	   </tr>
              	   <tr  align="center" id="tr_upreference1">
              	      <td>1</td> <td> <form:input path="upreference1" maxlength="12" value="0.00" class="max-length-12 validate-number" readonly="true" />
              	      	<br />
              	        <span class="error"  id="advice-validate-number-upreference1" style="display:none;"></span>
              	      </td>
              	      <td  id="td_fixfeeamount1"><form:input path="fixfeeamount1" maxlength="9" class="max-length-9 validate-number"/>
              	      </td>
              	      <br />
              	      <span class="error" id="advice-server-fixfeeamount1" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-fixfeeamount1" style="display:none;color: red;"></span>
              	      </td>
              	      <td id="td_feerate1"  > <form:input path="feerate1" maxlength="5"  class="max-length-5 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-feerate1" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-feerate1" style="display:none;color: red;"></span>
              	      
              	      </td>
              	      
              	   </tr>
              	   <tr  align="center" id="tr_upreference2"   >
              	      <td>2</td> 
              	      <td> <form:input path="upreference2" maxlength="11" class="max-length-11 great-than-upreference1 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-validate-number-upreference2" style="display:none;color: red;"></span>
                	  <span class="error" id="advice-great-than-upreference2" style="display:none;color: red;"></span>
              	      </td>
              	      <td id="td_fixfeeamount2"> <form:input path="fixfeeamount2" maxlength="9" class="max-length-9 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-fixfeeamount2" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-fixfeeamount2" style="display:none;color: red;"></span>
              	      </td>
              	      <td id="td_feerate2"  > <form:input path="feerate2" maxlength="5"  class="max-length-5 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-feerate2" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-feerate2" style="display:none;color: red;"></span>
              	      </td>
              	   </tr>
              	   <tr  align="center" id="tr_upreference3"  >
              	      <td>3</td> 
              	      <td> <form:input path="upreference3"  maxlength="11" class="max-length-11 great-than-upreference2 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-validate-number-upreference3" style="display:none;color: red;"></span>
                	  <span class="error" id="advice-great-than-upreference3" style="display:none;color: red;"></span>
              	      </td>
              	      <td id="td_fixfeeamount3"> <form:input path="fixfeeamount3" maxlength="9" class="max-length-9 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-fixfeeamount3" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-fixfeeamount3" style="display:none;color: red;"></span>
              	      </td>
              	      <td id="td_feerate3"  > <form:input path="feerate3" maxlength="5"  class="max-length-5 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-feerate3" style="display:none;color: red;">  </span>
              	       <span class="error"  id="advice-validate-number-feerate3" style="display:none;color: red;"></span>
              	      </td>
              	   </tr>
              	   <tr  align="center" id="tr_upreference4"  >
              	      <td>4</td> 
              	      <td> <form:input path="upreference4" maxlength="11" class="max-length-11 great-than-upreference3 validate-number"/>
              	                 	       
              	   
              	      <br />
              	      <span class="error" id="advice-validate-number-upreference4" style="display:none;color: red;"></span>
                	  <span class="error" id="advice-great-than-upreference4" style="display:none;color: red;"></span>
                	 
              	      </td>
              	      <td id="td_fixfeeamount4"><form:input path="fixfeeamount4" maxlength="9" class="max-length-9 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-fixfeeamount4" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-fixfeeamount4" style="display:none;color: red;"></span>
              	      </td>
              	      <td id="td_feerate4"  > <form:input path="feerate4" maxlength="5" class="max-length-5 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-feerate4" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-feerate4" style="display:none;color: red;"></span>
              	      </td>
              	   </tr>
              	   <tr  align="center" id="tr_upreference5"  >
              	      <td>5</td> 
              	      <td> <form:input path="upreference5" maxlength="11" class="max-length-11 great-than-upreference4 validate-number"/>
              	        
              	      <br/>
              	       <span class="error" id="advice-validate-number-upreference5" style="display:none;color: red;"></span>
                		<span class="error" id="advice-great-than-upreference5" style="display:none;color: red;"></span>
              	      </td>
              	      <td id="td_fixfeeamount5"><form:input path="fixfeeamount5" maxlength="9" class="max-length-9 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-fixfeeamount5" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-fixfeeamount5" style="display:none;color: red;"></span>
              	      </td>
              	      <td id="td_feerate5" > <form:input path="feerate5" maxlength="5" class="max-length-5 validate-number"/>
              	      <br />
              	      <span class="error" id="advice-server-feerate5" style="display:none;color: red;">  </span>
              	      <span class="error"  id="advice-validate-number-feerate5" style="display:none;color: red;"></span>
              	      </td>
              	   </tr>
              	  </table>
                </div>         
             </div>
                
      </div>        
              
               <div class="field"> 
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


<script type="text/javascript">
    <!--
    ajaxFormSubmit('#fm');

    //-->
</script>



</body>
</html>