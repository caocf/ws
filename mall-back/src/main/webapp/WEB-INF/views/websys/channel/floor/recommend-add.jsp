<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    $(document).ready(function(){
		var url = document.referrer;
		$("#backUrl").val(url);
		
		$("input[type='radio'][name='type']").click(function(){
			if($(this).val()==1){
				$("#uploadFileBox").hide();
			}else{
				$("#uploadFileBox").show();
			}
		});
		
		
	});
	
	function changeFloor(value){
		$("#selectArea").text('');
		if(-1 == value){
			return;
		}
		var channel =  document.getElementById("channelId").value;
		$.ajax({
			url: G_CTX_ROOT + '/websys/channel/catalog/selectArea?channel='+channel+'&floor='+value,
			success: function(data) {
				var areaAry = eval(data);
				for(var i=0;i<areaAry.length;i++){
					$("#selectArea").append("<input type='checkbox' name='regionCode' checked='checked' value='"+areaAry[i].regionCode+"'/>" +"<label>"+areaAry[i].regionName+"</label>");
				}
		}
		
		});
	}
	
	
	function changeChannel(value){
		$("#selectArea").text('');
		$("#channelId").val(value);
		$("#groupId").val(-1);
	}
	

</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加推荐配置</c:if><c:if test="${method == 'edit'}">修改推荐配置</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add" commandName="channelCatalogRcmd"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <div class="form">
        <input type="hidden" id="backUrl" name="backUrl" />
        <form:hidden path="id"/>
            <div class="fields">
            	<c:if test="${method == 'add'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自动生成</div>
                </div>
                </c:if>
                
                <c:if test="${method == 'edit'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${channelCatalogRcmd.id}</div>
                </div>
                </c:if>		
                                
                	
						 <div class="field">
                    <div class="label">
                        <label for="channel" class="req">频道：</label>
                    </div>
                    <div class="select">
                        <select name="channel" id ="channel" onchange="changeChannel(this.value);">
                    	<c:forEach items="${channelMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${channelCatalogRcmd.channel == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>		
              <input type="hidden" id="channelId" value="1"/>
                
                <div class="field">
                    <div class="label">
                        <label for="groupId" class="req">楼层：</label>
                    </div>
                    <div class="select">
                        <select name="groupId" id ="groupId" class="small required" onchange="changeFloor(this.value)">
                        <option value="-1">--请选择--</option>
                    	<c:forEach items="${groupMap }" var="item" >
                        	    <option value="${item.key }" <c:if test="${channelCatalogRcmd.groupId == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>
                
                <div class="field">
                	<div class="label label-checkbox" >
						<label class="req">区域:</label>
					</div>
					<div class="checkboxes">
                   		 <div class="checkbox" id="selectArea">
                       		${htmlStr }
                   		 </div>
                   	 </div>
                </div>
                
                
                
                <div class="field">
                    <div class="label">
                        <label for="displayName" class="req">显示名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="displayName"  maxlength="10" size="20" cssClass="required max-length-10"/>
                         <span class="error" id="advice-required-displayName" style="display:none"></span>
                         <span class="error" id="advice-max-length-displayName" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label"  >
                        <label for="linkUrl" class="req" >链接地址：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="linkUrl"   size="80" cssClass="small required validate-url min-length-1 max-length-150"/>
                         <span class="error" id="advice-validate-url-linkUrl" style="display:none"></span>
                    </div>
                </div>
              
                 <div class="field">
                    <div class="label">
                        <label for="orderIndex">排序：</label>
                    </div>
                    <div class="input">
                        <form:input path="orderIndex" cssClass="int-range-1-99" maxlength="4" />
                        <span class="error" id="advice-int-range-orderIndex" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-radio">
                        <label>单选框:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<form:radiobutton path="type" cssClass="validate-one-required" value="1"/><label for="type1">文本</label>
                        	<form:radiobutton path="type" cssClass="validate-one-required" value="2"/><label for="type2">图片</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                <div class="field" id="uploadFileBox" style="display:none">
                    <div class="label">
                        <label for="file">文件选择框:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploadFile" name="uploadFile" size="40" class="validate-file-png-jpg-gif" />
                        <span class="error" id="advice-validate-file-uploadFile" style="display:none"></span>
                    </div>
                </div>
                
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/channel/recommend/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>