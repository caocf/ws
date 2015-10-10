<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
 $().ready(function() {
	 
		$("[name='clearType']").click(function() {
			var value = $(this).val();
			
			if(value == 'P'){
				$("#productionTypeName").val("商品");
				$("#productionType").val("01");
			}else{
				
				 selectType(2,"productionType","productionTypeName",{'one':true});
				 //alert($("#productionType").val());
			}

		});
		
		

		$("[name='feelevelflag']").click(function() {
			var value = $(this).val();
			
			if(value == ' '){
				$("#upreference1").val("99999999999");
				
			}
			if(value == 1){
				$("#upreferences").show();
			}else{
				$("#upreferences").hide();
			}
			
			

		});
		
		$("[name='feemode']").click(function() {
			var value = $(this).val();
			
			if(value == 1 || value == 2){
				if(value == 1){
					$("#minfeeamount").val("0");
					$("#minfeeamount").attr("readonly","readonly");
					$("#maxfeeamount").val("99999999999");
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
				$("#beganamount").val("");
				$("#beganamount").removeAttr("readonly");
				$("#minfeeamount").val("");
				$("#minfeeamount").removeAttr("readonly");
				$("#maxfeeamount").val("");
				$("#maxfeeamount").removeAttr("readonly","readonly");
			}

		});


	
});
  
 
    </script>
    
</head>
<body>

<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>商户费率信息<h5>
    </div>
    <!-- end box / title -->
        <div class="form">
            <div class="fields">
            
      
            <div class="field">
                    <div class="label label-radio">
                        <label>是否现金:</label>
                    </div>
                    <div class="input">
                    ${storeFee.capitallTypeName }&nbsp;
                    </div>
              </div>
              
            <div class="field">
                    <div class="label label-radio">
                        <label>是否商城币:</label>
                    </div>
                    <div class="input">
                    ${storeFee.capital2TypeName }&nbsp;
                    </div>
              </div>
              
            <div class="field">
                    <div class="label label-radio">
                        <label>是否积分:</label>
                    </div>
                    <div class="input">
                    ${storeFee.capital3TypeName }&nbsp;
                    </div>
              </div>
              
              <div class="field">
                    <div class="label label-radio">
                        <label>是否话费:</label>
                    </div>
                    <div class="input">
                    ${storeFee.capital4TypeName }&nbsp;
                    </div>
              </div>
             
                <div class="field">
                    <div class="label label-radio">
                        <label>费用类型:</label>
                    </div>
                     <div class="input">
                    ${storeFee.feeTypeName }&nbsp;
                    </div>
                </div>
            
            <div class="field">
                    <div class="label label-radio">
                        <label>清算类别:</label>
                    </div>
                     <div class="input">
                     ${storeFee.clearTypeName }&nbsp;
                     </div>
             </div>
             
              <div class="field">
                    <div class="label label-radio">
                        <label for="settleDay" >费率类别:</label>
                    </div>
                     <div class="input">
                     ${productionTypeName }&nbsp;
                     </div>
                </div>
            
                 
                <div class="field">
						<div class="label label-radio">
							<label for="effortdate" >生效日期:</label>
						</div>
						 <div class="input">
						<ct:time source="${storeFee.effortdate}"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>&nbsp;
						</div>
				</div>
				<c:if test="${storeFee.phoneEffortdate != null }">
						  <div class="field">
						<div class="label label-radio">
							<label for="phoneEffortdate" >话费生效日期:</label>
						</div>
						 <div class="input">
						<ct:time source="${storeFee.phoneEffortdate}"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>&nbsp;
						</div>
				</div>
				</c:if>
				
				<div class="field">
					<div class="label label-radio">
						<label for="expirydate" >失效日期:</label>
						</div>
						 <div class="input">
						<ct:time source="${storeFee.expirydate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>&nbsp;
						</div>
				</div>
				 
				 <div class="field">
                    <div class="label label-radio">
                        <label>费用方向:</label>
                    </div>
                     <div class="input">
                    ${storeFee.feedrtflagName }&nbsp;
                    </div>
              	</div>
              	
              	 <div class="field">
                    <div class="label label-radio">
                        <label>收费周期:</label>
                    </div>
                    <div class="input">
                     ${storeFee.feeperiodunit }&nbsp;
                    </div>
              	</div>
              	
              	 <div class="field">
                    <div class="label label-radio">
                        <label for="fcName" >收费周期数量:</label>
                    </div>
                     <div class="input">
                     ${storeFee.fcName }&nbsp;
                     </div>
                </div>
                
                <div class="field">
                    <div class="label label-radio">
                        <label>收费方式:</label>
                    </div>
                    <div class="input">
                     ${storeFee.feemodeName }&nbsp;
                     </div>
              	</div>
              	
              	<div class="field">
                    <div class="label label-radio">
                        <label>计费方法:</label>
                    </div>
                     <div class="input">
                     ${storeFee.feemothodName }&nbsp;
              	</div>
              	
              	 <div class="field">
                    <div class="label label-radio">
                        <label for="beganamount" >计费起始金额:</label>
                    </div>
                   <div class="input">
                     ${storeFee.beganamount }&nbsp;
                     </div>
                </div>
                
                <div class="field">
                    <div class="label label-radio">
                        <label for="minfeeamount" >最低收费金额:</label>
                    </div>
                     <div class="input">
                     ${storeFee.minfeeamount }&nbsp;
                     </div>
                </div>
                
                <div class="field">
                    <div class="label label-radio">
                        <label for="maxfeeamount" >最高收费金额:</label>
                    </div>
                     <div class="input">
                     ${storeFee.maxfeeamount }&nbsp;
                     </div>
                </div>
                
                <div class="field">
                    <div class="label label-radio">
                        <label>分层/套档标志:</label>
                    </div>
                     <div class="input">
                      ${storeFee.feelevelflagName }&nbsp;
                      </div>
              	</div>
             
              <div class="field">
                    <div class="label label-radio">
                        <label>分层/套档依据:</label>
                    </div>
                     <div class="input">
                    ${storeFee.feelvlbasicflagName }&nbsp;
                    </div>
              	</div>
              	   
              	   
              	   
              <div id="upreferences" >
              	   
              	 <div class="field" id = "upreference1">
                    <div class="label label-radio">
                        <label for="upreference1" >计费参考1:</label>
                    </div>
                     <div class="input">
                     ${storeFee.upreference1 }&nbsp;
                     </div>
                </div>
                
                <div class="field" id = "fixfeeamount1">
                    <div class="label label-radio">
                        <label for="fixfeeamount1" >固定费用金额1:</label>
                    </div>
                    ${storeFee.fixfeeamount1 }&nbsp;
                </div>
                
                <div class="field" id = "feerate1">
                    <div class="label label-radio">
                        <label for="feerate1" >费率1:</label>
                    </div>
                    <div class="input">
                     ${storeFee.feerate1 }&nbsp;
                     </div>
                </div>
                
                
                <div class="field" id = "upreference2">
                    <div class="label label-radio">
                        <label for="upreference2" >计费参考2:</label>
                    </div>
                    <div class="input">
                      ${storeFee.upreference2 }&nbsp;
                      </div>
                </div>
                
                <div class="field" id = "fixfeeamount2">
                    <div class="label label-radio">
                        <label for="fixfeeamount2" >固定费用金额2:</label>
                    </div>
                     <div class="input">
                     ${storeFee.fixfeeamount2 }&nbsp;
                     </div>
                </div>
                
                <div class="field" id = "feerate2">
                    <div class="label label-radio">
                        <label for="feerate2" >费率2:</label>
                    </div>
                     <div class="input">
                     ${storeFee.feerate2 }&nbsp;
                     </div>
                </div>
                
                
                <div class="field" id = "upreference3">
                    <div class="label label-radio">
                        <label for="upreference3" >计费参考3:</label>
                    </div>
                     <div class="input">
                      ${storeFee.upreference3}&nbsp;
                      </div>
                </div>
                
                <div class="field" id = "fixfeeamount3">
                    <div class="label label-radio">
                        <label for="fixfeeamount3" >固定费用金额3:</label>
                    </div>
                   <div class="input">
                     ${storeFee.fixfeeamount3}&nbsp;
                     </div>
                </div>
                
                <div class="field" id = "feerate3">
                    <div class="label label-radio">
                        <label for="feerate3" >费率3:</label>
                    </div>
                   <div class="input">
                     ${storeFee.feerate3}&nbsp;
                     </div>
                </div>
                
                
                <div class="field" id = "upreference4">
                    <div class="label label-radio">
                        <label for="upreference4" >计费参考4:</label>
                    </div>
                     <div class="input">
                     ${storeFee.upreference4}&nbsp;
                     </div>
                </div>
                
                <div class="field" id = "fixfeeamount4">
                    <div class="label label-radio">
                        <label for="fixfeeamount4" >固定费用金额4:</label>
                    </div>
                   <div class="input">
                     ${storeFee.fixfeeamount4}&nbsp;
                     </div>
                </div>
                
                <div class="field" id = "feerate4">
                    <div class="label label-radio">
                        <label for="feerate4" >费率4:</label>
                    </div>
                   <div class="input">
                      ${storeFee.feerate4}&nbsp;
                      </div>
                </div>
                
                
                <div class="field" id = "upreference5">
                    <div class="label label-radio">
                        <label for="upreference5" >计费参考5:</label>
                    </div>
                  <div class="input">
                     ${storeFee.upreference5}&nbsp;
                     </div>
                </div>
                
                <div class="field" id = "fixfeeamount5">
                    <div class="label label-radio">
                        <label for="fixfeeamount5" >固定费用金额5:</label>
                    </div>
                    <div class="input">
                     ${storeFee.fixfeeamount5}&nbsp;
                     </div>
                </div>
                
                <div class="field" id = "feerate5">
                    <div class="label label-radio">
                        <label for="feerate5" >费率5:</label>
                    </div>
                    <div class="input">
                     ${storeFee.feerate5}&nbsp;
                     </div>
                </div>
                
                 <div class="field" id = "status">
                    <div class="label label-radio">
                        <label for="feerate5" >审核状态</label>
                    </div>
                    <div class="input">
                     ${storeFee.statusName}&nbsp;
                     </div>
                </div>
               <div class="field">
						 <div class="label label-radio">
                        <label for="feerate5" >清算状态</label>
                    </div>	
							<div class="input">
								现金:${storeFee.syncGyFlag1Name }&nbsp;商城币:${storeFee.syncGyFlag2Name }&nbsp;积分:${storeFee.syncGyFlag3Name }&nbsp;话费:${storeFee.syncGyFlag4Name }&nbsp;
								</div>
				</div> 
                
                <div class="buttons">
                    <div class="highlight">
                     
                        <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                    </div>
                </div>
            </div>
        </div>
</div>
<!-- end forms -->
</div>





</body>
</html>