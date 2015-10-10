<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<div id="content">
	<div class="box">
        <div class="form">
            <div class="fields">
                <c:if test="${not empty contSms.srcName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="srcName">内容源：</label>
                    </div>
                    <div class="input">
                        ${contSms.srcName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.content}">
                <div class="field">
                    <div class="label noinput">
                        <label for="content">内容：</label>
                    </div>
                    <div class="input">
                        ${contSms.content}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.startTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="startTime">开始时间：</label>
                    </div>
                    <div class="input">
                        <ct:time source="${contSms.startTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.endTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="endTime">结束时间：</label>
                    </div>
                    <div class="input">
                        <ct:time source="${contSms.endTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.keyword}">
                <div class="field">
                    <div class="label noinput">
                        <label for="keyword">关键字：</label>
                    </div>
                    <div class="input">
                        ${contSms.keyword}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.unitName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="unitName">录入单位：</label>
                    </div>
                    <div class="input">
                        ${contSms.unitName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.remark}">
                <div class="field">
                    <div class="label noinput">
                        <label for="remark">描述：</label>
                    </div>
                    <div class="input">
                        ${contSms.remark}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.statusName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="statusName">状态：</label>
                    </div>
                    <div class="input">
                        ${contSms.statusName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.auditorId}">
                <div class="field">
                    <div class="label noinput">
                        <label for="auditorId">录入人ID：</label>
                    </div>
                    <div class="input">
                        ${contSms.auditorId}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.advice}">
                <div class="field">
                    <div class="label noinput">
                        <label for="auditorId">驳回原因：</label>
                    </div>
                    <div class="input">
                        ${contSms.advice}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.updateUserId}">
                <div class="field">
                    <div class="label noinput">
                        <label for="updateUserId">更新人ID：</label>
                    </div>
                    <div class="input">
                        ${contSms.updateUserId}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty contSms.updateTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="updateTime">更新时间：</label>
                    </div>
                    <div class="input">
                        <ct:time source="${contSms.updateTime}"/>
                    </div>
                </div>
                </c:if>
                <div class="buttons">
                <ct:display model="cont_sms_list" btn="audit_btn">
                	<c:if test="${contSms.status == '0'}">
                   	<button type="button" id="${contSms.id}" class="common_btn auditing">审核</button>
                   	</c:if>
                </ct:display>       	
                </div>
                      <input class="common_btn" type="button" onclick="window.history.go(-1)"   value="返回">
            </div>
        </div>
	</div>
</div>
<script type="text/javascript">
$().ready(function() {		
	$(".auditing").click(function() {
			var id = $(this).attr("id");
			showDialog("审核", "${ctx}/cont/sms/sms_auditing?auditing=auditing&id="+id,function(doc){
				if('2' == doc.getElementById("checkStatus").value && "" == doc.getElementById("advice").value){		
					simpleAlert('审核失败，请填写驳回原因！',function() {
						return null;
					});
				}else{
					commonSubmit(doc.getElementById("fm"));				
					simpleAlert('审核成功',function() {
						window.location.reload();
					});
				}
				
			},{"Width":500,"Height":300});			
	});
});
</script>
</body>
</html>