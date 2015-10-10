<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    function showit_srcfrom(){
	with (fm) {
		if(srcFrom[0].checked) {
			in_id.style.display="block";
			fi_id.style.display="none";
		} else if(srcFrom[1].checked){
			fi_id.style.display="block";
			in_id.style.display="none";
		} 
	}
}
function showit_isNewTerminalId(){
	with (fm) {
		if(isNewTerminalId[0].checked) {
			terId.style.display="block";
		} else if(srcFrom[1].checked){
			terId.style.display="none";
		} 
	}
}
$().ready(function() {
	//获取来源地址
		var url = document.referrer;
		$("#backUrl").val(url);
});
    </script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>短信群发任务修改<h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="task" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <input type="hidden" name="mobileListFile" value="${task.mobileListFile }"/>
        <input type="hidden" name="whiteListFile" value="${task.whiteListFile }"/>
        <input type="hidden" name="blackListFile" value="${task.blackListFile }"/>
        <input type="hidden" id="backUrl" name="backUrl" />
        <div class="form">
            <div class="fields">
                
                <div class="field">
                  <div class="label">
                        <label for="taskName" class="req">任务名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="taskName" cssClass="small required" maxlength="100" />
                         <span class="error" id="advice-required-userPwd" style="display:none"></span>
                    </div>
              </div>
              <div class="field">
                    <div class="label">
                        <label for="startTime" class="req">发送时间:</label>
                    </div>
                    <div class="input">
                        <input type="text" id="startTime" name="startTime" class="date required" value="<ct:time source="${task.startTime }"/>"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'stopTime\')||\'2020-10-01\'}'})" readonly/>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="stopTime" class="req">中止时间:</label>
                    </div>
                    <div class="input">
                        <input type="text" id="stopTime" name="stopTime" class="date required" value="<ct:time source="${task.stopTime }"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" readonly/>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="sleepTime" class="req" >免打扰时间段:</label>
                    </div>
                    <div class="input">
                        <input type="text" id="sleepTime" name="sleepTime" class="date required" value="${task.sleepTime }" />
                    </div>
                </div>
                 <div class="field">
                    <div class="label label-textarea">
                        <label for="title" class="req">发送的短信：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="title" cols="50" rows="8" cssClass="required max-length-100" />
                        <span class="tip">(短信内容最长100个汉字)</span>
                    </div>
                </div>
              <div class="field">
                  <div class="label">
                        <label for="spCode" class="req">特服号：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="spCode" cssClass="small required validate-number" maxlength="20" />
                         <span class="error" id="advice-required-spCode" style="display:none"></span>
                    </div>
              </div>
              <div class="field">
                    <div class="label">
                        <label for="service" class="req">计费代码:</label>
                    </div>
                    <div class="select">
                        <select id="service" name="service" >
                            <option value="FREE|1|0" <c:if test="${task.service=='FREE|1|0' }"> selected="selected" </c:if>>FREE,免费,0</option>
                        </select>  
                    </div>
                    <span class="tip">计费代码,收费类型,资费(分)</span>
                </div>
                <div class="field">
                    <div class="label label-radio">
                        <label>接收状态报告:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="isReport" <c:if test="${task.isReport=='1' }">checked="checked"</c:if> value="1"/><label for="radio-1">是</label>
                            <input type="radio" id="radio-2" name="isReport" <c:if test="${task.isReport=='0' }">checked="checked"</c:if> value="0"/><label for="radio-2">否</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="terminalid"  >群发号码：</label>
                    </div>
                    <div class="input">
                         <a href="${ctx}/batch/downfile/terminal_list.txt/${task.id}/1">下载群发号码</a>&nbsp;&nbsp;
                      <c:if test="${!empty  task.blackListFile}">
                       <a href="${ctx}/batch/downfile/black_list.txt/${task.id}/3">下载黑名单号码</a>&nbsp;&nbsp;
                      </c:if>
                      <c:if test="${!empty task.whiteListFile}">
                       <a href="${ctx}/batch/downfile/white_list.txt/${task.id}/2">下载白名单号码</a>&nbsp;&nbsp;
                      </c:if>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-radio">
                        <label>是否修改群发号码:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-5" name="isNewTerminalId" value="1" onclick="javascript:showit_isNewTerminalId();"  /><label for="radio-5">是</label>
                            <input type="radio" id="radio-6" name="isNewTerminalId" checked="checked" value="0" onclick="javascript:showit_isNewTerminalId();" /><label for="radio-6">否</label>
                             <span class="tip">重新选择将覆盖原群发号码(建议否)</span>                                            
                        </div>
                        
                    </div>
                </div>
                <div id="terId"  style="display: none">
                <div class="field">
                    <div class="label label-radio">
                        <label>群发号码来源:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-3" name="srcFrom" value="0" onclick="javascript:showit_srcfrom();"/><label for="radio-3">手工输入</label>
                            <input type="radio" id="radio-4" name="srcFrom" value="1" checked="checked" onclick="javascript:showit_srcfrom();"/><label for="radio-4">文本文件</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex2" style="display:none"></span>
                    </div>
                </div>
                
                
                <div id="in_id" style="display: none">
                <div class="field">
                    <div class="label label-textarea">
                        <label for="terminalid"  class="req">群发号码：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="terminalid" cols="36" rows="3" cssClass="required" />
                         <span class="tip">(号码之间用逗号","隔开，例如：13800000000,13900000000)</span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="blackid">黑名单号码：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="blackid" cols="36" rows="3"  />
                         <span class="tip">(号码之间用逗号","隔开，例如：13800000000,13900000000)</span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="whiteid">输入白名单号码：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="whiteid" cols="36" rows="3"  />
                         <span class="tip">(号码之间用逗号","隔开，例如：13800000000,13900000000)</span>
                    </div>
                </div>
                </div>
                
                
                
                
                
                
                <div id="fi_id">
                 <div class="field">
                    <div class="label">
                        <label for="uploadfile"  class="req">群发号码文件:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploadfile" name="uploadfile" size="40" class="required validate-file-txt" />
                        <span class="tip">txt格式(必选)</span>
                        <span class="error" id="advice-validate-file-uploadfile" style="display:none"></span>
                        <span class="error" id="advice-required-uploadfile" style="display:none"></span>
                    </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="uploadblackfile">黑名单号码文件:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploadblackfile" name="uploadblackfile" size="40" class="validate-file-txt" />
                        <span class="tip">txt格式(可选)</span>
                        <span class="error" id="advice-validate-file-uploadblackfile" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="uploadwhitefile">白名单号码文件:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploadwhitefile" name="uploadwhitefile" size="40"  class="validate-file-txt"/>
                        <span class="tip">txt格式(可选)</span>
                        <span class="error" id="advice-validate-file-uploadwhitefile" style="display:none"></span>
                        
                    </div>
                </div>
                </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/batch/smsbatchlist" class="btnAnchor">返回</a>
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