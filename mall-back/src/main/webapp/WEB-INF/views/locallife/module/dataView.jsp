<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
</head>
<script type="text/javascript">
	$(function(){
		var jsondata = ${json};
		var moduleData = ${dataJson};
		var jsonArr = jsondata["fields"];
		for(var i =0;i<jsonArr.length;i++){
			var jsonobj = jsonArr[i];
			var req = jsonobj["editor"]["options"]["required"];
			var type = jsonobj["editor"]["type"];
			var html = "<div class='field'>"+
								"<div class='label'>"+
									"<label >"+jsonobj["name"]+":</label>"+
								"</div>"+
								"<div class='input'>"+
									moduleData[jsonobj["code"]]+
								"</div>"+
						"</div>";
			if(type=="textarea"){
				html = "<div class='field'>"+
							"<div class='label'>"+
								"<label >"+jsonobj["name"]+":</label>"+
							"</div>"+
							"<div class='input'>"+
								moduleData[jsonobj["code"]]+
							"</div>"+
						"</div>";
			}
			if(type=="select"){
				html = "<div class='field'>"+
							"<div class='label'>"+
								"<label >"+jsonobj["name"]+":</label>"+
							"</div>"+
							"<div class='input'>"+
								"<select id="+jsonobj["code"]+" disabled >"+
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
					查看模块数据
				</h5>
			</div>
			<form:form method="post" id="fm" commandName="info" htmlEscape="true"
				acceptCharset="utf-8" cssClass="required-validate"
				enctype="multipart/form-data">
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
						</c:forEach>
						<div class="field">
							<div class="label">
								<label for="createUserName">模板名称:</label>
							</div>
							<div class="input">
							${info.tempName }&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="createUserName">模块名称:</label>
							</div>
							<div class="input">
							${info.moduleName }&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="createUserName">状态:</label>
							</div>
							<div class="input">
							<select name="status" disabled>
			                        <option value="">--请选择--</option>
	                        	    <option value= "0" <c:if test="${info.status == 0 }">selected="selected"</c:if>>未审核</option>
	                        	    <option value= "1" <c:if test="${info.status == 1 }">selected="selected"</c:if>>审核通过</option>
	                        	    <option value= "2" <c:if test="${info.status == 2 }">selected="selected"</c:if>>审核驳回</option>
		                    </select>&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="createUserName">区域:</label>
							</div>
							<div class="input">
							${info.regionName }&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="createUserName">创建人:</label>
							</div>
							<div class="input">
							${info.createUserName }&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="createTime">创建时间:</label>
							</div>
							<div class="input">
							<ct:time source="${info.createTime}" />&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="updateUserName">修改人:</label>
							</div>
							<div class="input">
							${info.updateUserName }&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="updateTime">修改时间:</label>
							</div>
							<div class="input">
							<ct:time source="${info.updateTime}" />&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for=auditUserName>审核人:</label>
							</div>
							<div class="input">
							${info.auditUserName }&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="auditTime">审核时间:</label>
							</div>
							<div class="input">
							<ct:time source="${info.auditTime}" />&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="auditAdvice">审核意见:</label>
							</div>
							<div class="input">
							${info.auditAdvice}&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="startTime">开始时间:</label>
							</div>
							<div class="input">
							<ct:time source="${info.startTime}" />&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="endTime">结束时间:</label>
							</div>
							<div class="input">
							<ct:time source="${info.endTime}" />&nbsp;
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label >排序:</label>
							</div>
							<div class="input">
								${info.sortNo }&nbsp;&nbsp;
									
							</div>
						</div>
						<div class="buttons">
						
						<input type="button" class="common_btn" onclick="history.back();" value="返回" />
						</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>