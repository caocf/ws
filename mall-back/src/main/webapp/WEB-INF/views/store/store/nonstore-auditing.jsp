<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>非结算商户审核</h5>
    </div>
    <!-- end box / title -->
    <form id="fm" class="required-validate" action="${ctx }/store/store/nonStoreAudit" method="post" accept-charset="utf-8">
        <input type="hidden" name="id" id="storeId" value="${id }">
         <input type="hidden" name="auditStep" id="auditStep" value="${auditStep }">
        
        <div class="form">
            <div class="fields">
                
              
                <div class="field">
                    <div class="label label-radio">
                        <label>审核:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="status" checked="checked"  value="3"/><label for="radio-1">通过</label>
                            <input type="radio" id="radio-2" name="status" value="4"/><label for="radio-2">驳回</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="advice">审核意见：</label>
                    </div>
                    <div class="input">
                     <textarea id="remark" name="remark" class="max-length-50" rows="8" cols="50"></textarea>
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
    </form>
</div>
<!-- end forms -->
</div>
</body>
</html>