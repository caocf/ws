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
<div class="title">
        <h5>添加彩信</h5>
    </div>
<form:form method="post" id="f_contMms" action="add.do" commandName="contMms"
	htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
	<div class="form">
	 <input type="hidden" name="mmsFrameData" id="mmsFrameData"/>
     <input type="hidden" name="loadBoxId" id="loadBoxId" />
     <input type="hidden" name="mmsId" id="mmsId" />
	 <div class="fields">
	 		<div class="field">
                    <div class="label">
                        <label for="title" class="req">彩信标题：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="title" cssClass="small required"/>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="startTime" class="req">生效时间:</label>
                    </div>
                    <div class="input">
                        <form:input type="text" path="startTime"  cssClass="small required"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true"/>
                    </div>
                </div>
		         <div class="field">
                    <div class="label">
                        <label for="endTime" class="req">失效时间:</label>
                    </div>
                    <div class="input">
                         <form:input type="text" path="endTime"  cssClass="small required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true"/>
                    </div>
                </div>
                		
                <div class="field">
                <div class="label">
                 <label for="contentSrc" class="req">选择内容源：</label>
                 </div>
                <div class="input">
                <form:select path="contentSrc">
                      <c:forEach items="${contentCodeList}" var="item">
                      <form:option value="${item.code }">${item.name }</form:option>
                      </c:forEach>
                </form:select>
                </div>
                </div>
				
				<dl>
					<div style='padding-left:55px;'>
        				<iframe src="mmsdiy.do" width="100%"  frameborder=0 height="600" marginheight=0 marginwidth=0 scrolling=no style="float:left" id="mmsMb" name="mmsMb" ></iframe>
					</div>
				</dl>
			
					<div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight"   onclick="dealSubFrame();" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/cont/mms/list" class="btnAnchor">返回</a>
                	</div>
			</div>
		</div>
</form:form>

<style>
button {
	font-size: 12px;
	width: 100px;
	cursor: pointer;
}
</style>
<script type="text/javascript">
    <!-- 
    $('#startTime').click(function() {
    	WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'});
    })
     $('#endTime').click(function() {
    	WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'});
    })
    
    $('#validPeriodCfg1, #validPeriodCfg2').click(function(){
    	validPeriodCfgChange();
    });
    function validPeriodCfgChange() {
        var validPeriodCfg = $('input[name=validPeriodCfg]:checked').val();
        if (validPeriodCfg == 1) {
            $('.dl_time').show();
        }
        if (validPeriodCfg == 2) {
            $('.dl_time').hide();
            $('#startTime').val("");
            $('#endTime').val("");
        }
    } 
    validPeriodCfgChange();
    
    function showBlock() {
    	var message = "<table><tr><td><span style='float:left;margin-left:6px;'>提示</span><span style='float:right;margin-right:6px;cursor:pointer'"
    	 +"onclick='closeBlock(2)'>X</span></td></tr><tr><td style='height:12px; background:url(${res}/images/jgx2.jpg) center no-repeat;'></td></tr>"
    	 +"<tr style='height:80px;'><td>彩信内容发布成功！</td></tr><tr  style='height:30px;' ><td><button onclick='closeBlock(0);'>确定</button>&nbsp;&nbsp;"
    	 +"<button onclick='closeBlock(1);'>继续发布</button></td></tr></table><br/>"
    	
    	 $.blockUI({  
    		fadeIn: 1,
            fadeOut: 1,
            overlayCSS:  {
                backgroundColor: '#000',
                opacity:         0,
                cursor:          'wait'
            },
        	message: message,
        	css: {
                border: 'none',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                opacity:.8,
                color: '#fff'
            }
        });
    }
     
    function closeBlock(type) {
    	$.unblockUI();
    	if(type=='1') {
    		location.href = "${ctx}/content/create/${appId}/mms";
    	}else if(type=='0'){
    		location.href = "${ctx}/content/${appId}";
    	}
    }
    $('#f_contMms').ajaxForm({
        dataType:  'json',
        beforeSubmit: function(a,f,o) {
        	$('.tipbox').html('');$('.tips').show();        	 
        	 dealSubFrame();
	         if ($.trim($('input[name=title]').val()).length == 0) {
                 putErrorText('title', '请输入彩信标题');
                 location.hash="title_error";
                 return false;
	          }
            
        	 var validPeriodCfg = $('input[name=validPeriodCfg]:checked').val();
             if (validPeriodCfg == 1) {
            	 if ($.trim($('input[name=startTime]').val()).length == 0) {
                     putErrorText('startTime', '请选择内容生效时间');
                     return false;
                 }
            	 if ($.trim($('input[name=endTime]').val()).length == 0) {
                     putErrorText('endTime', '请选择内容失效时间');
                     return false;
                 }
             }    	          
            waitBlock();
        },
        error: function() {
            $.unblockUI();
        },
        success: function(resp) {
        	 if (formPostCallback(resp)) {
        		 simpleConfirm("彩信内容发布成功！","继续发布",function() {
       			  location.href = "${ctx}/content/${appId}";
                 },function() {
                	 location.href = "${ctx}/content/create/${appId}/mms";
                 });
             }
        }
    });
    
    function dealSubFrame() {
    	$("#mmsMb")[0].contentWindow.dealDataBeforeSubmit();
    	$("#mmsMb")[0].contentWindow.clearContentError();
    } 
    //-->
    </script>
    	</div>
    </div>
</body>
</html>