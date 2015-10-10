<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<head>
<ht:head />
    <script type="text/javascript">
    $(document).ready(function(){
		var url = document.referrer;
		$("#backUrl").val(url);
		
		
		
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
	function showDiv(){
		with (fm) {
			//连锁则显示总店与否选择
			if(isUpload[0].checked) {
				uploadTemplateFile.style.display="block";
			} else if(isUpload[1].checked){
				uploadTemplateFile.style.display="none";
			}
		}
	}
	
</script>
</head>
<body>
	<div id="content">
		<div class="box">
			<div class="title">
				<h5>
					<c:if test="${method == 'add'}">添加频道图片管理</c:if>
					<c:if test="${method == 'edit'}">修改频道图片管理</c:if>
				</h5>
			</div>
			<form:form method="post" id="fm" commandName="info" htmlEscape="true"
				acceptCharset="utf-8" cssClass="required-validate"
				enctype="multipart/form-data">	
				   <input type="hidden" id="backUrl" name="backUrl" />
				<div class="form">
					<div class="fields">
						<div class="field">
							<div class="label noinput">ID：</div>
							<div class="input">自动生成</div>
						</div>
				
						
						
						 <div class="field">
                    <div class="label">
                        <label for="channel" class="req">频道：</label>
                    </div>
                    <div class="select">
                        <select name="channel" id ="channel" onchange="changeChannel(this.value);">
                    	<c:forEach items="${channelMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${info.channel == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>		
              <input type="hidden" id="channelId" value="1"/>
                
                <div class="field">
                    <div class="label">
                        <label for="postion" class="req">楼层：</label>
                    </div>
                    <div class="select">
                        <select name="postion" id ="groupId" class="small required" onchange="changeFloor(this.value)">
                        <option value="-1">--请选择--</option>
                    	<c:forEach items="${groupMap }" var="item" >
                        	    <option value="${item.key }" <c:if test="${info.postion == item.key}">selected="selected"</c:if>>${item.value }</option>
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
								<label class="req">图片名称：</label>
							</div>
							<div class="input">
								<form:input path="picAlt"
									cssClass="small required min-length-1 max-length-75"
									maxlength="50" />
								<span class="error" id="advice-required-min-lengt-picAlt"
									style="display:none"></span> <span class="error"
									id="advice-required-max-length-picAlt" style="display:none"></span>
							</div>
						</div>
						<c:if test="${method == 'edit'}">
						 <div class="field">
		                    <div class="label">
		                        <label for="download">图片：</label>
		                    </div>
		                    <div class="input">
		                    	<ht:image webpath="${info.picPath}" type="channel"/>
		                    </div>
		                </div>
		                <div class="field">
							<div class="label label-radio">
								<label for="isUpload">更新图片:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="isUpload1" name="isUpload" value="1" onclick="javascript:showDiv();" /><label for="isUpload1">是</label>
									<input type="radio" id="isUpload0" name="isUpload" value="0" checked="checked" onclick="javascript:showDiv();" /><label for="isUpload0">否</label>
								</div>
							</div>
						</div>
						</c:if>
						<div id="uploadTemplateFile" <c:if test="${method == 'edit'}">style="display: none"</c:if>class="field">
							<div class="label">
								<label for="filePath" class="req">图片路径:</label>
							</div>
							<input type="hidden" name="picPath" value="${info.picPath }">
							<div class="input input-file">
								<input type="file" id="file1" name="uploadFile" size="40"
									class="validate-file-gif-jpg-jpeg-bmp-png" />
								<span class="error" id="advice-validate-file-file1"
									style="display:none"></span>
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label class="req">链接：</label>
							</div>
							<div class="input">
								<form:input path="linkUrl"
									cssClass="small required validate-url min-length-1 max-length-150"/>
								<span class="error" id="advice-required-min-lengt-linkUrl"
									style="display:none"></span> <span class="error"
									id="advice-required-max-length-linkUrl" style="display:none"></span>
									<span class="error" id="advice-required-min-lengt-linkUrl"
									style="display:none"></span> <span class="error"
									id="advice-required-validate-url-linkUrl" style="display:none"></span>
							</div>
						</div>
				
						<div class="field">
							<div class="label">
								<label class="req">图片排序：</label>
							</div>
							<div class="input">
								<form:input path="picIndex"
									cssClass="small required validate-digits min-length-1 max-length-4"
									maxlength="4" />
								<span class="error" id="advice-required-min-lengt-picIndex"
									style="display:none"></span> <span class="error"
									id="advice-required-max-length-picIndex" style="display:none"></span>
									
							</div>
						</div>
						<div class="field">
							<div class="buttons">
								<div class="highlight">
									<input type="submit" name="submit.highlight" value="提交" />
								</div>
								<input type="reset" name="reset" value="重置" /> <a
									href="${ctx}/websys/channel/catalog/pic_list" class="btnAnchor">返回</a>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>