<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    <script type="text/javascript">
    $(document).ready(function(){
    	//获取来源地址
	 	var url = document.referrer;
	 	$("#backUrl").val(url);
    	//选择商户
    	$("#storeName").click(function(){
    		selectStoreCallBackHaveArea("",function(storeId,storeName,areaName){
    			$("#storeId").val(storeId);
        		$("#storeName").val(storeName);
        		$("#areaName").val(areaName);
        		$("#storeName").focus();
    	    	$("#storeName").blur();
    		});
		});
    })
    
    function changeType() {
        var sendChannelId = $("#sendChannelList").val();
        if (sendChannelId != "0") {
        	 var sendChannel = $("#sendChannelList").find("option:selected").text();
	        var channelStr = sendChannelId.split("|");
	        var codeId = channelStr[0];
	        $("#sendChannelId").val(codeId);
	        //获取对应的发码类型
	        var typeStr = channelStr[1];
	        //解析发码类型
	        var typeList = typeStr.split(";");
	        $("#sendTypeList").empty();
	        for (var i=0;i<typeList.length;i++) {
	        	var sendTypeId = typeList[i];
	        	var sendTypeMap  = ${sendTypeJson};
	        	$("#sendTypeList").append("<option value='"+sendTypeId+"'>"+sendTypeMap[sendTypeId]+"</option>");
	        }
	        slectType();
        } else {
        	$("#sendTypeList").empty();
        }
    }
    
     function slectType() {
        var typeId = $("#sendTypeList").val();
        $("#sendTypeId").val(typeId);
    }
    
    //加载选中值
    function loadType() {
        var sendTypeId = "${storeCode.sendTypeId}";
    	$("#sendTypeList").attr('value',sendTypeId);
    	$("#sendTypeId").val(sendTypeId);
    }
    </script>
    </head>
<body >

<div id="content">
<div class="box">
    <div class="title">
        <h5>修改</h5>
    </div>
<form:form method="post" action="/store/code/edit" id="fm" commandName="storeCode" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
        <input type="hidden" id="backUrl" name="backUrl" />
        <form:hidden path="id"/>
            <div class="fields">
            	<div class="field">
						<div class="label">
							<label for="storeName" class="req">商户名称：</label>
						</div>
						<div class="input">
							<form:input path="storeName" id="storeName" maxlength="100" readonly="true" cssClass="small required" class="required" />
							<span class="error" id="advice-required-shopId" style="display:none"></span>
						</div>
				</div>
				<div class="field">
						<div class="label">
							<label for="storeId">商户编号：</label>
						</div>
						<div class="input">
						    <form:input path="storeId" readonly="true"/>
						</div>
				</div>
				<div class="field">
						<div class="label">
							<label for="areaName">商户归属地区：</label>
						</div>
						<div class="input">
						    <form:input path="areaName" id="areaName" readonly="true"/>
						</div>
				</div>
				<div class="field">
						<div class="label">
							<label for="startTime" class="req">生效时间：</label>
						</div>
						<div class="input">
						    <input name="startTime" id="startTime" class="date required" readonly="true" 
						    value="<ct:time source="${storeCode.startTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss"/>"
						    onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'date',maxDate:'#F{$dp.$D(\'stopTime\')||\'2020-10-01\'}'})"/>
						</div>
				</div>
				<div class="field">
						<div class="label">
							<label for="stopTime" class="req">失效时间：</label>
						</div>
						<div class="input">
						    <input name="stopTime" id="stopTime" class="date required" readonly="true" 
						    value="<ct:time source="${storeCode.stopTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss"/>"
						    onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\' , {d:1});}',maxDate:'2020-10-01'})"/>
						</div>
				</div>
				<div id="selectDiv">
					<div class="field">
						<div class="label">
							<label for="send" class="req">发码渠道：</label>
						</div>
						<div class="select">
						<form:hidden path="sendChannelId" id="sendChannelId"/>
							<select name="sendChannelList" id="sendChannelList" onchange="changeType()" class="validate-selection">
								<option value="0">-请选择-</option>
								<c:forEach items="${codelist}" var="codeConfig">
									<option value="${codeConfig.sendChannel }|${codeConfig.sendType }" <c:if test="${codeConfig.sendChannel == storeCode.sendChannelId }">selected="selected"</c:if>>${sendChannelIdMap[codeConfig.sendChannel]}</option>
								</c:forEach>
							</select>
							<span class="error" id="advice-required-sendChannelId" style="display:none"></span>
							<form:hidden path="sendTypeId" id="sendTypeId"/>
							<select name="sendTypeList" id="sendTypeList" onchange="slectType();">
								<option value="0">-请选择-</option>
							</select>
							<script type="text/javascript">changeType();</script>
							<script type="text/javascript">loadType();</script>
							<input name="errorMsg" id="errorMsg" type="hidden">
						</div>
					</div>
                </div>
				<div class="buttons">
                    <div class="highlight">
                        <input id="submitButton" type="submit" name="submit.highlight"  value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                   <input type="button" class="common_btn" onClick="history.back();" value="返回" />
                </div>
			</div>
		</div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>