<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
     <script type="text/javascript">
		</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>礼品卡需求审核<h5>
    </div>
    <!-- end box / title -->
   <form:form action="${ctx}/gift/required/audit/${batchNo}" method="post" id="fm" commandName="giftRequired" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label label-radio">
                        <label>审核:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="status" checked="checked"  value="2"/><label for="radio-1">审核通过</label>
                            <input type="radio" id="radio-2" name="status" value="3"/><label for="radio-2">审核驳回</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-status" style="display:none"></span>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label label-textarea">
                        <label for="advice">审核意见：</label>
                    </div>
                    <div class="input">
                     <textarea id=auditMsg name="auditMsg" class="max-length-100" rows="8" cols="50"></textarea>
                    </div>
                </div>
                
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