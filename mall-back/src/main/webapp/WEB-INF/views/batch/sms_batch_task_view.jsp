<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
     <script type="text/javascript">
	$().ready(function() {
		$("#auditId").click(function() {
			showDialog("SMS群发任务审核", "${ctx}/batch/audit/"+${bt.id},null,{"Width":700,"Height":600,"ShowButtonRow" : false});
		});
	});
</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>短信群发任务查看<h5>
    </div>
    <!-- end box / title -->
        <div class="form">
            <div class="fields">
                
                <div class="field">
                  <div class="label label-textarea">
                        <label for="taskName" >任务名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                       ${bt.taskName }
                       &nbsp;
                    </div>
              </div>
              <div class="field">
                    <div class="label label-textarea">
                        <label for="startTime">发送时间:</label>
                    </div>
                    <div class="input">
                        <ct:time source="${bt.startTime}"/>
                        &nbsp;
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="stopTime">中止时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${bt.stopTime}"/>
                    &nbsp;
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="sleepTime">免打扰时间段:</label>
                    </div>
                    <div class="input">
                    ${bt.sleepTime}
                    &nbsp;
                    </div>
                </div>
                 <div class="field">
                    <div class="label label-textarea">
                        <label for="title">发送的短信：</label>
                    </div>
                    <div class="input">
                    ${bt.title}
                    &nbsp;
                    </div>
                </div>
              <div class="field">
                  <div class="label label-textarea">
                        <label for="spCode" >特服号：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                    ${bt.spCode}
                    &nbsp;
                    </div>
              </div>
              <div class="field">
                    <div class="label label-textarea">
                        <label for="service" >业务代码:</label>
                    </div>
                    <div class="select">
                    ${bt.serviceId}
                    &nbsp;
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="service" >资费类型:</label>
                    </div>
                    <div class="select">
                    ${bt.feeType}
                    &nbsp;
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="service" >资费:</label>
                    </div>
                    <div class="select">
                    ${bt.fee}(分) 
                    &nbsp;
                    </div>
                </div>
                <div class="field">
                    <div class="label label-radio">
                        <label>接收状态报告:</label>
                    </div>
                    <div class="radios">
                        <c:choose>
							<c:when test="${bt.isReport==1}">是</c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label label-textarea">
                        <label for="terminalid"  >群发号码：</label>
                    </div>
                    <div class="input">                       
                    	<c:if test="${not empty bt.mobileListFile }">
                   	   <a href="${ctx}/batch/downfile/${bt.id}/1">下载群发号码</a>&nbsp;&nbsp;
                      </c:if>
                      <c:if test="${!empty  bt.blackListFile}">
                       <a href="${ctx}/batch/downfile/${bt.id}/3">下载黑名单号码</a>&nbsp;&nbsp;
                      </c:if>
                      <c:if test="${!empty bt.whiteListFile}">
                       <a href="${ctx}/batch/downfile/${bt.id}/2">下载白名单号码</a>&nbsp;&nbsp;
                      </c:if>
                      &nbsp;
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="service" >审核说明:</label>
                    </div>
                    <div class="select">
                    ${bt.advice} &nbsp;
                    </div>
                </div>
                <div class="buttons">
                 <c:if test="${bt.status==0}">
                    <ct:display model="sms_audit_list" btn="audit_btn">
                         <a href="javascript:void(0);" id="auditId" class="btnAnchor">审核</a>&nbsp;&nbsp;
                    </ct:display>
                    &nbsp;&nbsp;
                   </c:if> 
                <c:if test="${optype=='batchList' }">
                    <a href="${ctx}/batch/smsbatchlist" class="btnAnchor">返回</a>
                </c:if>
                 <c:if test="${optype=='auditList' }">
                    <a href="${ctx}/batch/smsauditlist" class="btnAnchor">返回</a>
                </c:if>
                <c:if test="${optype=='controlList' }">
                    <a href="${ctx}/batch/smsmanagerlist" class="btnAnchor">返回</a>
                </c:if>
                </div>
            </div>
        </div>
</div>
<!-- end forms -->
</div>

</body>
</html>