<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
</head>
<script type="text/javascript">
	$(function(){
		var url = document.referrer;
 		$("#backUrl").val(url);
		selectRegion("#regionName","regionCode","regionName",${regionCode});
		var jsondata = ${json};
		var moduleData = ${dataJson};
		var jsonArr = jsondata["fields"];
		for(var i =0;i<jsonArr.length;i++){
			var jsonobj = jsonArr[i];
			var req = jsonobj["editor"]["options"]["required"];
			var type = jsonobj["editor"]["type"];
			var html = "<div class='field'>"+
								"<div class='label'>"+
									"<label "+"class="+(req==true?'req':'nreq')+">"+jsonobj["name"]+":</label>"+
								"</div>"+
								"<div class='input'>"+
									"<input type='text' id="+jsonobj["code"]+" name= "+jsonobj["code"]+" value='"+moduleData[jsonobj["code"]]+"' class='small "+(req==true?"required":"")+"'  />"+
								"</div>"+
						"</div>";
			if(type=="textarea"){
				html = "<div class='field'>"+
							"<div class='label'>"+
								"<label "+"class="+(req==true?'req':'nreq')+">"+jsonobj["name"]+":</label>"+
							"</div>"+
							"<div class='input'>"+
								"<textarea id="+jsonobj["code"]+" name= "+jsonobj["code"]+" class='small "+(req==true?"required":"")+"'  >"+moduleData[jsonobj["code"]]+"</textarea>"+
							"</div>"+
						"</div>";
			}
			if(type=="select"){
				html = "<div class='field'>"+
							"<div class='label'>"+
								"<label "+"class="+(req==true?'req':'nreq')+">"+jsonobj["name"]+":</label>"+
							"</div>"+
							"<div class='input'>"+
								"<select id="+jsonobj["code"]+" name= "+jsonobj["code"]+" class='"+(req==true?"required":"")+" validate-selection'>"+
	                            "<option value=''>--请选择--</option>"+
	                       	    "</select>"+
								"</div>"+
						"</div>";
			}
			if(type=="image"){
				$("#hid"+jsonobj["code"]).val(moduleData[jsonobj["code"]]);
			}
			if(type!="image"){
			$("#jsonDiv").append(html);
			}
			if(type=="select"){
				var selectData = jsonobj["editor"]["options"]["data"];
				for(var j =0;j<selectData.length;j++){
					if(selectData[j]["value"] == moduleData[jsonobj["code"]]){
					 $("<option value="+selectData[j]["value"]+" selected="+"selected"+" >"+selectData[j]["text"]+"  </option>").appendTo($("#"+jsonobj["code"]));
					}else{
					 $("<option value="+selectData[j]["value"]+">"+selectData[j]["text"]+"</option>").appendTo($("#"+jsonobj["code"]));
					}
				}
			}
		}
	});
</script>
<body>
	<div id="content">
		<div class="box">
			<div class="title">
				<h5>
					<c:if test="${method == 'add'}">添加模块数据</c:if>
					<c:if test="${method == 'edit'}">修改模块数据</c:if>
				</h5>
			</div>
			<form:form method="post" id="fm" commandName="info" htmlEscape="true"
				acceptCharset="utf-8" cssClass="required-validate"
				enctype="multipart/form-data">
				<input type="hidden" id="backUrl" name="backUrl" />
				<div class="form">
					<div class="fields">
						<c:if test="${method == 'add'}">
		                <div class="field">
		                    <div class="label noinput">ID：</div>
		                    <div class="input">自动生成</div>
		                    
		                </div>
		                </c:if>
		                
		                <c:if test="${method == 'edit'}">
		                <div class="field">
		                    <div class="label noinput">ID：</div>
		                    <div class="input">${info.id}</div>
		                </div>
		                </c:if>
						<input type="hidden" name="moduleId" value="${info.moduleId}"/>
						<div id = "jsonDiv">
						</div>
						<c:forEach items="${json.fields}" var="items"   >
						<c:if test="${items.editor.type eq 'image' }">
						<c:if test="${method == 'edit'}">
							<c:set var='code' value='${items.code}'></c:set>
							<div class="field">
								<div class="label">
									<label for="filePath">${items.name}:</label>
								</div>
								<div class="input">
									<ht:image webpath="${dataJson[code]}" type="locallife"/>
								</div>
							</div>
						</c:if>
						<div class="field">
							<div class="label">
								<c:if test="${method == 'add'}"><label for="filePath" class="req">${items.name}:</label></c:if>
								<c:if test="${method == 'edit'}"><label for="filePath">更新${items.name}:</label></c:if>	
							</div>
							<input type="hidden" name="${items.code}" id="hid${items.code}">
							<div class="input input-file">
								<input type="file" id="${items.code}" name="${items.code}File" 
									class="validate-file-gif-jpg-jpeg-bmp-png <c:if test="${method == 'add'}">required</c:if> " />
								<span class="error" id="advice-validate-file-${items.code}"style="display:none"></span>
								<span class="error" id="advice-required-${items.code}" style="display:none"></span>
							</div>
						</div>
						</c:if>
						</c:forEach>
						<div class="field">
							<div class="label">
								<label class="req">区域：</label>
							</div>
							<div class="input">
	                        <form:hidden path="regionCode" cssClass="small  required" />
	                        <form:input path="regionName" cssClass="small  required"  readonly="true"/>
	                   		</div>
						</div>
						<c:if test="${method == 'add'}">
							<div class="field">
							<div class="label">
								<label for="saleStartTime" class="req">开始时间:</label>
							</div>
							<div class="input">
								<input type="text" id="startTime" name="startTime"
									 readOnly  class="date required"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||$dp.$D(\'endTime\')||\'2020-10-01\'}'})" />
								</div>
							</div>
							<div class="field">
								<div class="label">
									<label for="saleStopTime" class="req">结束时间:</label>
								</div>
								<div class="input">
									<input type="text" id="endTime" name="endTime"  readOnly  class="date required"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\' , {H:1});}',maxDate:'2020-10-01'})" />
								</div>
							</div>
						</c:if>
						<c:if test="${method == 'edit'}">
						<div class="field">
							<div class="label">
								<label for="startTime" class="req">开始时间:</label>
							</div>
							<div class="input">
								<input type="text" id="startTime" name="startTime"
									class="date required" readOnly  value="<ct:time source="${info.startTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss"/>"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'endTime\')||$dp.$D(\'endTime\')||\'2020-10-01\'}'})" />
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="endTime" class="req">结束时间:</label>
							</div>
							<div class="input">
								<input type="text" id="endTime" name="endTime" 
									class="date required" readOnly value="<ct:time source="${info.endTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss"/>"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\', {H:1});}',maxDate:'2020-10-01'})" />
							</div>
						</div>
						</c:if>
						<div class="field">
							<div class="label">
								<label class="req">排序:</label>
							</div>
							<div class="input">
								<form:input path="sortNo"
									cssClass="small required validate-digits min-length-1 max-length-5" />
								<span class="error" id="advice-required-min-lengt-sortNo"
									style="display:none"></span> <span class="error"
									id="advice-required-max-length-sortNo" style="display:none"></span>
									
							</div>
						</div>
						<div class="field">
							<div class="buttons">
								<div class="highlight">
									<input type="submit" name="submit.highlight" value="提交" />
								</div>
								<input type="reset" name="reset" value="重置" /> <a
									href="javascript:history.go(-1)" class="btnAnchor">返回</a>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>