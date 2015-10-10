<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
<script>
 <c:if test="${empty store}">
alert("请先录入商户基本信息");
history.back();
</c:if>
<c:if test="${store.sort==0}">
alert("该商户为非签约商户，不能编辑结算信息");
history.back();
</c:if>
</script>
</head>
<body>
<br />

<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div  class="title">
        <h5>商户结算信息</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="storeSettle" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <form:hidden path="storeId"/>
        <form:hidden path="merchid"/>
         <c:if test="${storeSettle.id!=null }">
          <form:hidden path="id"/>
        </c:if>
        <div class="form">
            <div class="fields">
            	<div class="field">
							<div class="label label-radio">
								<label>是否企业帐户:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="radio-3" name="isBsAccount" <c:if test="${storeSettle.isBsAccount!=0 }">checked="checked"</c:if>  value="1" /><label
										for="radio-3">是</label> <input type="radio" id="radio-4"
										name="isBsAccount" <c:if test="${storeSettle.isBsAccount==0 }">checked="checked"</c:if>  value="0" /><label
										for="radio-4">否</label>
								</div>
								<span class="error" id="advice-validate-one-required-radioex1"
									style="display:none"></span>
							</div>
				</div>
            
                 <div class="field">
                    <div class="label label-radio">
                        <label>结算类型:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-3"  name="settleType" ${storeSettle.settleType!=1?"checked":"" } value="0"/><label for="radio-3">系统结算</label>
                            <input type="radio" id="radio-4" name="settleType" ${storeSettle.settleType==1?"checked":"" } value="1"/><label for="radio-4">手工结算</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label label-radio">
                        <label>佣金结算:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-5" name="feeSettle" ${storeSettle.feeSettle==0?"checked":"" } value="0"/><label for="radio-5">收支两条线</label>
                            <input type="radio" id="radio-6" name="feeSettle" ${storeSettle.feeSettle!=0?"checked":"" } value="1"/><label for="radio-6">作扣</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label label-select">
                        <label for="settlePeriod" >结算周期：</label>
                    </div>
                    <div class="input">
                         <select id="settlePeriod" name="settlePeriod">
                       <option ${storeSettle.settlePeriod==0?"selected":"" } value="0">日</option>
                       <option ${storeSettle.settlePeriod==1?"selected":"" } value="1">周</option>
                       <option ${storeSettle.settlePeriod==3?"selected":"" } value="3">月</option>
                       </select>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="settleDay" class="req">结算日:</label>
                    </div>
                    <div class="input">
                     <form:input path="settleDay" cssClass="small required validate-number" maxlength="3" />
                     <span class="tip">标识结算间隔的数量。</span>
                         <span class="error" id="advice-validate-number-settleDay" style="display:none"></span>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="settleDaybit" class="req">结算日标志位:</label>
                    </div>
                    <div class="input">
                     <form:input path="settleDaybit" cssClass="small required validate-number" maxlength="3" />
                          <span class="tip">指定结算日时设置一个月31天的具体日期。</span>
                         <span class="error" id="advice-validate-number-settleDaybit" style="display:none"></span>
                    </div>
                </div>
                
                  <div class="field">
                    <div class="label">
                        <label for="settleTrfdays" class="req">结算划款天数:</label>
                    </div>
                    <div class="input">
                     <form:input path="settleTrfdays" cssClass="small required validate-number" maxlength="3" />
                         <span class="error" id="advice-validate-number-settleTrfdays" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="settleBeginamt" class="req">结算起始金额:</label>
                    </div>
                    <div class="input">
                     <form:input path="settleBeginamt" cssClass="small required validate-number" maxlength="8" />
                         <span class="error" id="advice-validate-number-settleBeginamt" style="display:none"></span>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="minRetainedamt" class="req">最低留存金额:</label>
                    </div>
                    <div class="input">
                     <form:input path="minRetainedamt" cssClass="small required" maxlength="8" />
                         <span class="error" id="advice-validate-number-minRetainedamt" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="withdrawBankid" class="req">结算银行的名称:</label>
                    </div>
                    <div class="input">
                     <form:input path="withdrawBankid" cssClass="small required" maxlength="50" />
                         <span class="error" id="advice-required-withdrawBankid" style="display:none"></span>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="openbankDesc" class="req">开户行详细信息:</label>
                    </div>
                    <div class="input">
                     <form:input path="openbankDesc" cssClass="small required" maxlength="100" />
                         <span class="error" id="advice-required-openbankDesc" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="settleAc" class="req">结算账户:</label>
                    </div>
                    <div class="input">
                     <form:input path="settleAc" cssClass="small required validate-number" maxlength="50" />
                         <span class="error" id="advice-validate-number-settleAc" style="display:none"></span>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="bankAcname" class="req">开户人:</label>
                    </div>
                    <div class="input">
                     <form:input path="bankAcname" cssClass="small required" maxlength="50" />
                         <span class="error" id="advice-required-bankAcname" style="display:none"></span>
                    </div>
                </div>
                
                
              
                <div class="field">
						<div class="label">
							<label for="effortDate" class="req">生效日期:</label>
						</div>
							<div class="input">
							<input type="text" id="startTime" name="startTime"
								class="txt required Wdate" readOnly value="<ct:time source="${storeSettle.effortDate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>"
								onfocus="WdatePicker({vel:'effortDate',dateFmt:'yyyy-MM-dd' ,realDateFmt:'yyyyMMdd',minDate:'date',maxDate:'#F{$dp.$D(\'stopTime\',{d:-1})||\'2020-10-01\'}'})" />
							<input type="hidden" name="effortDate" id="effortDate" value="${storeSettle.effortDate }" />
							</div>
				</div>
				<div class="field">
					<div class="label">
						<label for="expiryDate" class="req">失效日期:</label>
								</div>
						<div class="input">
							<input type="text" id="stopTime" name="stopTime" 
								class="txt required Wdate" readOnly value="<ct:time source="${storeSettle.expiryDate }"  sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>"
								onfocus="WdatePicker({vel:'expiryDate',dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startTime\',{d:1})}',maxDate:'2020-10-01'})" />
								<input type="hidden" name="expiryDate" id="expiryDate" value="${storeSettle.expiryDate }" />
						</div>
				</div>
				
                 <div class="field">
                    <div class="label">
                        <label for="uploadfile"  >上传签署协议:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploadfile" name="uploadfile"  size="40"  />
                        <span class="error" id="advice-validate-file-uploadfile" style="display:none"></span>
                    </div>
                </div>
                <c:if test="${not empty downurl  }">
                 <div class="field">
                    <div class="label">
                        <label for="uploadfile"  >下载原签署协议:</label>
                    </div>
                    <div class="input input-file">
                       <a href="${ctx }/store/storeSettle/busiContentDown/${storeSettle.id}">下载</a>
                    </div>
                </div>
                </c:if>
               
                
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