<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
   		function doCustomize(obj) {
			if(obj.checked==true) {
				$('#templateUrl').attr('href','${ctx}/sms/schedule/downfile/-1');
				$('#smsContentDiv').show();		
				$('#smsContent').addClass("required");        		
			}else {
				$('#templateUrl').attr('href','${ctx}/sms/schedule/downfile/0');
				$('#smsContent').val('');	
				$('#smsContentDiv').hide();	
				$('#smsContent').removeClass("required"); 			
			}
   	    }
    </script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>群发任务排期</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add" commandName="batchTaskSchedule"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">          	
                  <div class="field">
                    <div class="label label-radio">
                        <label for="endTime" class="req">是否个性化群发:</label>
                    </div>
                    <div class="radios">
			            <div class="radio">
			            	<input type="checkbox" name="isCustomize" value="1" onclick='doCustomize(this)' ><label for="checkbox-1">是</label>                 		                    	
                    	</div>
                    </div>
                </div> 
                 <div class="field">
                     <div class="label">
                        <label for="uploadfile"  class="req">群发号码文件:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploadfile" name="uploadfile" size="40"  class="required  validate-file-txt-zip"/>
                        
                        <span class="tip">txt/zip格式(必选)</span>
                        <span class="error" id="advice-validate-file-uploadfile" style="display:none"></span>
                        <span class="error" id="advice-required-uploadfile" style="display:none"></span>
                       
                    </div>
                    <div>
                    <br/>
                    <div class="label">
                       
                    </div>
                    <div class="input">
                  		<a id='templateUrl' href="${ctx}/sms/schedule/downfile/0">模版文件下载</a>&nbsp;&nbsp;
                    </div>
                </div>
          
                <div class="field" id='smsContentDiv' style='display:none' >
                    <div class="label label-textarea">
                        <label for="smsContent" class="req">短信内容模版：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="smsContent" cols="50" rows="8" cssClass="required max-length-500" />
						  <font color='red'>&nbsp;&nbsp;注：通配符：<br/>
						&nbsp;&nbsp;[param1]代表第一个参数（号码文件中的第二列）<br/>
						&nbsp;&nbsp;[param2]代表第二个参数（号码文件中的第三列）<br/>
						&nbsp;&nbsp;以此类推</font>
                    	<span class="error" id="advice-required-smsContent" style="display:none"></span>
                        <span class="error" id="advice-max-length-smsContent" style="display:none"></span>
                        <span class="error" id="advice-server-smsContent" style="display:none"></span>
                    </div>
                </div>  
                   
               
                
                <div class="field">
                    <div class="label label-radio">
                        <label for="isZip" class="req">下载文件是否压缩:</label>
                    </div>
                    <div class="radios">
			            <div class="radio">
			            	<input type="checkbox" id="isZip" name="isZip" value="1" ><label for="checkbox-1">是</label>                 		                    	
                    	</div>
                    </div>
                </div>                           
                               
                <div class="field">
                    <div class="label">
                        <label for="startTime" class="req">日期：</label>
                    </div>
                    <div class="input">
                   <input type="text" id="dateTime" name="dateTime" value="${batchTaskSchedule.dateTime}" class="date required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
                    </div>
                </div>
                            										
		         <div class="field">
                    <div class="label label-radio">
                        <label for="filterType" class="req">过滤范围选择:</label>
                    </div>
                    <div class="radios">
			            <div class="radio">
			            	<input type="radio" name="filterType" class='required' value="1" checked ><label for="checkbox-1">商城（10658364）</label>
				            <input type="radio" name="filterType" class='required' value="2"  ><label for="checkbox-2">商城（10658618）</label>	  
				            <input type="radio" name="filterType" class='required' value="3"  ><label for="checkbox-3">商盟（10658585）</label>	   
				            <input type="radio" name="filterType" class='required' value="4"  ><label for="checkbox-4">商盟（其他）</label>	        				            	        
				            <span class="error" >每日不能超过1条，超过1条的号码将被过滤   </span>    	
	                    	<span class="error" id="advice-required-filterType" style="display:none"></span>
                    	</div>
                    </div>
                </div> 
                                              
                <div class="field">
                    <div class="label label-textarea">
                        <label for="remark" class="req">备注：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="remark" cols="50" rows="4" cssClass="required max-length-50" />
                    	<span class="error" id="advice-required-remark" style="display:none"></span>
                        <span class="error" id="advice-max-length-remark" style="display:none"></span>
                        <span class="error" id="advice-server-remark" style="display:none"></span>
                    </div>
                </div>                                                     
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sms/schedule/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>