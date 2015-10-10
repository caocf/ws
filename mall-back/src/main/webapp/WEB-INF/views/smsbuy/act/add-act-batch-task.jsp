<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
     $(document).ready(function(){
	     var url = document.referrer;
		 	$("#backUrl").val(url);
		 });
	 
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
    </script>
</head>
<body>
<div id="content">
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>短信购群发任务申请<h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="task" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <div class="form">
            <div class="fields">
                <div class="field">
                  <div class="label">
                        <label for="taskName" class="req">任务名称：</label>
                    </div>
                    <div class="input">
                    ${actOnline.actName }(${actOnline.actId })
                    <form:hidden path="taskName" value="${actOnline.actName }(${actOnline.actId })"/>
                    <form:hidden path="actId" value="${actOnline.actId }"/>
                    <input type="hidden" id="backUrl" name="backUrl" />
                    </div>
              </div>
              <div class="field">
                    <div class="label">
                        <label for="startTime" class="req">发送时间:</label>
                    </div>
                    <div class="input">
                        <input type="text" id="startTime" name="startTime" value="<ct:time source="${task.startTime}"/>" class="date required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'stopTime\')||\'2020-10-01\'}'})" readonly/>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="stopTime" class="req">中止时间:</label>
                    </div>
                    <div class="input">
                        <input type="text" id="stopTime" name="stopTime" value="<ct:time source="${task.stopTime}"/>"  class="date required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" readonly/>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="sleepTime" class="req">免打扰时间段:</label>
                    </div>
                    <div class="input">
                        <input type="text" id="sleepTime" name="sleepTime" class="date required" value="0000-0900,2100-2400"   />
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="stopTime" class="req">短信类型:</label>
                    </div>
                    <div class="input">
                    	短信购
                    	<form:hidden path="batchType" value="2"/>
                    </div>
                </div>
                <div class="field">
                   <div class="label">
                       <label for="title" class="req">短信内容：</label>
                   </div>
                   <div class="input">
                       <form:textarea path="title" cols="50" rows="8" cssClass="required max-length-130" />
                       <span class="tip">(短信内容，字数不得超过130字)</span>
                   </div>
               </div>
              <div class="field">
                  <div class="label">
                        <label for="spCode" class="req">特服号：</label>
                    </div>
                    <div class="input">
                    	${actOnline.spCode }
                    	 <form:hidden path="spCode" value="${actOnline.spCode }"/>
                    </div>
              </div>
                <div class="field">
                    <div class="label label-radio">
                        <label>接收状态报告:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="isReport" value="1"/><label for="radio-1">是</label>
                            <input type="radio" id="radio-2" name="isReport" checked="checked" value="0"/><label for="radio-2">否</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-isReport" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-radio">
                        <label>群发号码来源:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-3" name="srcFrom" value="0" onclick="javascript:showit_srcfrom();"/><label for="radio-3">手工输入</label>
                            <input type="radio" id="radio-4" name="srcFrom" value="1" checked="checked" onclick="javascript:showit_srcfrom();"/><label for="radio-4">文本文件</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-srcFrom" style="display:none"></span>
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
                <!-- 
                <div class="field">
                    <div class="label label-textarea">
                        <label for="whiteid">输入白名单号码：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="whiteid" cols="36" rows="3" />
                          <span class="tip">(号码之间用逗号","隔开，例如：13800000000,13900000000)</span>
                    </div>
                </div> -->
                </div>
                
                
                
                
                
                
                <div id="fi_id">
                 <div class="field">
                    <div class="label">
                        <label for="uploadfile"  class="req">群发号码文件:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploadfile" name="uploadfile" size="40"  class="required  validate-file-txt"/>
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
                        <input type="file" id="uploadblackfile" name="uploadblackfile" size="40"  class="validate-file-txt"/>
                        <span class="tip">txt格式(可选)</span>
                        <span class="error" id="advice-validate-file-uploadblackfile" style="display:none"></span>
                         
                    </div>
                </div>
                <!-- 
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
                 -->
                </div>
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="javascript:history.go(-1);" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>