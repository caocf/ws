<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
	    $().ready(function() {
			//获取来源地址
	 		var url = document.referrer;
	 		$("#backUrl").val(url);
		});
	    </script>
</head>
<body>
<div id="content">
<div class="box">
    <div class="title">
        <h5>退款单审核</h5>
    </div>
    <form id="fm" class="required-validate" action="${ctx}/order/refund/refund_auditing" method="post" accept-charset="utf-8">
        <input type="hidden" id="id" name="id" value="${refund.id}">
        <input type="hidden" id="whereAbout" name="whereAbout" value="${whereAbout}">
        <input type="hidden" id="backUrl" name="backUrl" />
        <div class="form">
            <div class="fields">                
                <div class="field">
                  <div class="label">
                        <label for="id">退款单号：</label>
                    </div>
                    <div class="input">
                       ${refund.id}
                    </div>
              	</div>              
                <div class="field">
                    <div class="label label-radio">
                        <label>审核：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="status" checked="checked"  value="4" class="validate-one-required"/><label for="radio-1">通过</label>
                            <input type="radio" id="radio-2" name="status" value="5"/><label for="radio-2">驳回</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-status" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="auditRemark">审核说明：</label>
                    </div>
                    <div class="input">
                        <textarea id="advice" name="auditRemark" cols="50" rows="8" class="max-length-150" >${orderRefund.auditRemark}</textarea>
                        <span class="error" id="advice-max-length-auditRemark" style="display:none"></span>
                        <span class="error" id="advice-server-auditRemark" style="display:none"></span>
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="javascript:history.go(-1)" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form>
</div>
</div>
</body>
</html>