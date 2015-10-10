<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
       <script type="text/javascript">
    	$().ready(function() {
    		
    		
			//获取来源地址
	 		var url = document.referrer;
	 		$("#backUrl").val(url);
	 		
		});
    	
    	function change(){
			if($("#pubDest").val() == '1'){
				$("#endTime").val('');
				$("#adviseType").val('0');
				$("#uploadfileAdvise").val('');
				$("#uploadfileAdviseExtend").val('');
				$("#extendAdvise").css('display','none');
				$("#indexDiv").css('display','none');
				$("#storeDiv").removeAttr('style');
			}
			if($("#pubDest").val() == '3'){
				$("#endTime").val('');
				$("#title").val('');
				$("#description").val('');
				$("#storeDiv").css('display','none');
				$("#indexDiv").removeAttr('style');
			}
		}
    	function changeType(){
			if($("#adviseType").val() == '1'){
				$("#extendAdvise").removeAttr('style');
			}
			if($("#adviseType").val() == '0'){
				$("#extendAdvise").css('display','none');
				$("#uploadfileAdviseExtend").val('');
			}
		}
	</script>
    <ht:xheditor />
    </head>
<body>

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>
        ${sysAnnouncement.id==null?"添加公告":"修改公告" }</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="sysAnnouncement" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
      <input type="hidden" id="backUrl" name="backUrl" />
       <c:if test="${sysAnnouncement.id != null }">
       <form:hidden path="id"/>
       </c:if>
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${sysAnnouncement.id==null?"自动生成": sysAnnouncement.id }</div>
                </div>

                <div class="field">
                    <div class="label" class="req">
                        <label for="pubDest">发布对象:</label>
                    </div>
                    <div class="select">
                        <form:select path="pubDest" onchange="change();">
                            <form:options items="${destMap}" />
                        </form:select>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="title" class="req">结束时间：</label>
                    </div>
                    <div class="input">
	                     <input type="text" id="endTime" name="endTime" 
										class="date required" readOnly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2020-10-01'})" />
                    </div>
                </div>


			<div id="storeDiv">
                <div class="field">
                    <div class="label">
                        <label for="title" class="req">公告标题：</label>
                    </div>
                    <div class="input">
                        <form:input path="title" cssClass="small required max-length-50" maxlength="50" />
                    </div>
                </div>
                

                <div class="field">
                    <div class="label">
                        <label for="description" class="req">公告内容：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="description" cssClass="cxheditor required" cols="80" rows="20" />
                        <span class="error" id="advice-server-description" style="display:none"></span>
                    </div>
                </div>
          </div>
                
                
                
          <div id="indexDiv" style="display:none">
          
          	  <div class="field">
                    <div class="label">
                        <label for="title" class="req">公告类型：</label>
                    </div>
                    <div class="input">
                        <select id="adviseType" name="title" onchange="changeType();">
                        	<option value="0">静态公告</option>
                        	<option value="1">拉伸公告</option>
                        </select>
                    </div>
                </div>
          

                <div class="field">
                    <div class="label">
                        <label for="description" class="req">公告图片：</label>
                    </div>
                    <div class="input">
                       	<input type="file" id="uploadfileAdvise" name="uploadfileAdvise" size="40" class="required validate-file-jpg-png-gif"  />
                       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       	<span id="picSpanId" style="color:red">高度为90px，宽度不得低于1000px</span>
                       <span class="error" id="advice-required-uploadfileAdvise" style="display:none"></span>
	                   <span class="error" id="advice-validate-uploadfileAdvise" style="display:none"></span>
                    </div>
                    
                </div>
                 <div class="field" id="extendAdvise" style="display:none">
	                 <div class="input">
	                       	<input type="file" id="uploadfileAdviseExtend" name="uploadfileAdviseExtend" size="40" class="required validate-file-jpg-png-gif"  />
	                       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                       	<span id="picSpanId" style="color:red">高度为360px，宽度不得低于1000px</span>
	                        <span class="error" id="advice-required-uploadfileAdviseExtend" style="display:none"></span>
	                        <span class="error" id="advice-validate-file-uploadfileAdviseExtend" style="display:none"></span>
	                    </div>
                 </div>
                
          </div>
          
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/announcement" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


</body>
</html>