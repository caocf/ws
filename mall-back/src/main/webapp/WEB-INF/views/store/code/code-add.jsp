<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    <script type="text/javascript">
    $(document).ready(function(){
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
   	var num = 0;
	function addSelect() {
		num = num + 1;
		var html= "<div class='field'>"+
						"<div class='label'>"+
							"<label for='send' class='req'>发码渠道：</label>"+
						"</div>"+
						"<div class='select'>"+
						"<input type='hidden' name='sendChannelId' id='sendChannelId"+num+"'>"+
							"<select name='sendChannelList' id='sendChannelList"+num+"' class='validate-selection' onchange='changeType("+num+")'>"+
								"<option value='0'>-请选择-</option>"+
								"<c:forEach items='${codelist}' var='codeConfig'>"+
									"<option value='${codeConfig.sendChannel }|${codeConfig.sendType }'>${sendChannelIdMap[codeConfig.sendChannel]}</option>"+
								"</c:forEach>"+
							"</select>"+
							"<span class='error' id='advice-required-sendChannelList"+num+"' style='display:none'></span>"+
							"<input type='hidden' name='sendTypeId' id='sendTypeId"+num+"'>"+
							"<select name='sendTypeList' id='sendTypeList"+num+"' onchange='slectType("+num+");'>"+
								"<option value='0'>-请选择-</option>"+
							"</select>"+
							"&nbsp;&nbsp;"+
						"</div>"+
                    "<a href='javascript:void(0);' id='del' onclick='delpara(this)'>删除</a></div>";
		 $("#selectDiv").append(html);
    }
    
    function delpara(obj) {
		 $(obj).parent().remove();
    }
    
    function changeType(num) {
        var sendChannelId = $("#sendChannelList"+num).val();
        if (sendChannelId != 0) {
	        var channelStr = sendChannelId.split("|");
	        var codeId = channelStr[0];
	        $("#sendChannelId"+num).val(codeId);
	        //获取对应的发码类型
	        var typeStr = channelStr[1];
	        //解析发码类型
	        var typeList = typeStr.split(";");
	        $("#sendTypeList"+num).empty();
	        for (var i=0;i<typeList.length;i++) {
	        	var sendTypeId = typeList[i];
	        	var sendTypeMap  = ${sendTypeJson};
	        	$("#sendTypeList"+num).append("<option value='"+sendTypeId+"'>"+sendTypeMap[sendTypeId]+"</option>");
	        }
	        slectType(num);
        } else {
        	$("#sendTypeList"+num).empty();
        }
    }
    
     function slectType(num) {
        var typeId = $("#sendTypeList"+num).val();
        $("#sendTypeId"+num).val(typeId);
    }
    </script>
    </head>
<body >

<div id="content">
<div class="box">
    <div class="title">
        <h5>添加</h5>
    </div>
<form:form method="post" action="/store/code/add" id="fm" commandName="storeCode" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
            	<div class="field">
						<div class="label">
							<label for="storeName" class="req">商户名称：</label>
						</div>
						<div class="input">
							<input name="storeName" id="storeName" maxlength="100" readonly="true" cssClass="small required" class="required" />
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
						    <input name="areaName" id="areaName" readonly="true"/>
						</div>
				</div>
				<div class="field">
						<div class="label">
							<label for="startTime" class="req">生效时间：</label>
						</div>
						<div class="input">
						    <input name="startTime" id="startTime" class="date required" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'date',maxDate:'#F{$dp.$D(\'stopTime\')||\'2020-10-01\'}'})"/>
						</div>
				</div>
				<div class="field">
						<div class="label">
							<label for="stopTime" class="req">失效时间：</label>
						</div>
						<div class="input">
						    <input name="stopTime" id="stopTime" class="date required" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\' , {d:1});}',maxDate:'2020-10-01'})"/>
						</div>
				</div>
				<div id="selectDiv">
					<div class="field">
						<div class="label">
							<label for="send" class="req">发码渠道：</label>
						</div>
						<div class="select">
							<input type="hidden" name="sendChannelId" id="sendChannelId0" value="">
							<select name="sendChannelList" id="sendChannelList0" onchange="changeType(0)" class="validate-selection">
								<option value="0">-请选择-</option>
								<c:forEach items="${codelist}" var="codeConfig">
									<option value="${codeConfig.sendChannel }|${codeConfig.sendType }">${sendChannelIdMap[codeConfig.sendChannel]}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="sendTypeId" id="sendTypeId0" value="">
							<select name="sendTypeList" id="sendTypeList0" onchange="slectType(0);">
								<option value="0">-请选择-</option>
							</select>
							&nbsp;&nbsp;
						</div>
						<div class="input">
		                    <ct:btn type="button" value="增加" name="addbtn" onclick="addSelect();"/>&nbsp;&nbsp;
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