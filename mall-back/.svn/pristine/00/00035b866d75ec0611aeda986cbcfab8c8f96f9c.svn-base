<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
<script type="text/javascript">
$().ready(function() {
	
	$("#common_btn").click(function() {
		var id='${storeSettle.id }';
		var status = $("[name ='status']:checked").val();
		
		dealInfo("确认审核","storeSettleAudit?id="+id+"&status="+status);
		
	});
	
	$("#audit_btn").click(function() {
		
		$("#auditdiv").removeAttr("style");
	});
	
});
</script>
    
</head>
<body>
<br />
<div id="search-menu">
     
    <br style="clear: left" />
</div>
<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>商户结算信息<h5>
    </div>
    <!-- end box / title -->
        <div class="form">
            <div class="fields">
            
            <div class="field">
                    <div class="label label-radio">
                        <label>状态:</label>
                    </div>
                    <div class="radios">
                    	${storeSettle.statusName }&nbsp;&nbsp;&nbsp;&nbsp;
                    	
                    </div>
                </div>
               
            
                 <div class="field">
                    <div class="label label-radio">
                        <label>结算类型:</label>
                    </div>
                    <div class="radios">
                    	${storeSettle.settleTypeName }&nbsp;
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label label-radio">
                        <label>佣金结算:</label>
                    </div>
                    <div class="radios">
                    ${storeSettle.feeSettleName }&nbsp;
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label label-select">
                        <label for="settlePeriod" >结算周期：</label>
                    </div>
                    <div class="input">
                     ${storeSettle.settlePeriodName }&nbsp;
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="settleDay" >结算日:</label>
                    </div>
                    <div class="input">
                      ${storeSettle.settleDay }&nbsp;
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="settleDaybit" >结算日标志位:</label>
                    </div>
                    <div class="input">
                     ${storeSettle.settleDaybit }&nbsp;
                    </div>
                </div>
                
                  <div class="field">
                    <div class="label">
                        <label for="settleTrfdays" >结算划款天数:</label>
                    </div>
                    <div class="input">
                    ${storeSettle.settleTrfdays }&nbsp;
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="settleBeginamt" >结算起始金额:</label>
                    </div>
                    <div class="input">
                     ${storeSettle.settleBeginamt }&nbsp;
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="minRetainedamt" >最低留存金额:</label>
                    </div>
                    <div class="input">
                    ${storeSettle.minRetainedamt }&nbsp;
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="withdrawBankid" >结算银行的名称:</label>
                    </div>
                    <div class="input">
                     ${storeSettle.withdrawBankid }&nbsp;
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="openbankDesc" >开户行详细信息:</label>
                    </div>
                    <div class="input">
                      ${storeSettle.openbankDesc }&nbsp;
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="settleAc" >结算账户:</label>
                    </div>
                    <div class="input">
                     ${storeSettle.settleAc }&nbsp;
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="bankAcname" >开户人:</label>
                    </div>
                    <div class="input">
                     ${storeSettle.bankAcname }&nbsp;
                    </div>
                </div>
              
                <div class="field">
						<div class="label">
							<label for="effortDate" >生效日期:</label>
						</div>
							<div class="input">
							  <ct:time source="${storeSettle.effortDate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>&nbsp;
								</div>
				</div>
				<div class="field">
					<div class="label">
						<label for="expiryDate" >失效日期:</label>
								</div>
						<div class="input">
						 <ct:time source="${storeSettle.expiryDate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>&nbsp;
								</div>
				</div>
				 <c:if test="${not empty downurl  }">
                 <div class="field">
                    <div class="label">
                        <label for="uploadfile"  >签署协议:</label>
                    </div>
                    <div class="input input-file">
                       <a href="${ctx }/store/storeSettle/busiContentDown/${storeSettle.id}">下载</a>
                    </div>
                </div>
                </c:if>
                 <c:if test="${ empty downurl  }">
                 <div class="field">
                    <div class="label">
                        <label for="uploadfile"  >签署协议:</label>
                    </div>
                    <div class="input input-file">
                      	暂无
                    </div>
                </div>
                </c:if>
                <div class="field" id = "status">
                    <div class="label">
                        <label for="feerate5" >审核状态</label>
                    </div>
                    <div class="input">
                     ${storeSettle.statusName}&nbsp;
                     </div>
                </div>
				
					
					
				 <div class="buttons">
                    <div class="highlight">
                    
                        <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                    </div>
                </div>
                
        </div>
</div>
<!-- end forms -->
</div>





</body>
</html>