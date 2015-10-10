<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    function deleteRouterItem(obj,url){
    	deleteItem(url, function(){$(obj).parent().parent().remove();});    	    	
    }
    </script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">配置短信业务</c:if><c:if test="${method == 'edit'}">编辑短信业务</c:if></h5>
        <h5><c:if test="${method == 'add'}">配置短信业务</c:if><c:if test="${method == 'edit'}">编辑短信业务</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="online" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
        <input type="hidden" id="backUrl" name="backUrl" />
        <c:if test="${method == 'edit'}">
        	<form:hidden path="actId" />
        	<form:hidden path="status" />
        </c:if>
            <div class="fields">
            	<c:if test="${method == 'add'}">
                <div class="field">
                    <div class="label noinput">编号：</div>
                    <div class="input">自动生成</div>
                </div>
                </c:if>
                <c:if test="${method == 'edit'}">
                <div class="field">
                    <div class="label noinput">编号：</div>
                    <div class="input">${online.actId}</div>
                </div>
                </c:if>		
                <div class="field">
                    <div class="label">
                        <label for="actName" class="req">名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="actName" cssClass="small required min-length-0 max-length-25" maxlength="25" />
                        <span class="error" id="advice-required-actName" style="display:none"></span>
                        <span class="error" id="advice-min-length-actName" style="display:none"></span>
                        <span class="error" id="advice-max-length-actName" style="display:none"></span>
                        <span class="error" id="advice-server-actName" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="actDesc" class="req">描述：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="actDesc" cols="50" rows="8" cssClass="required max-length-250" />
                    	<span class="error" id="advice-required-actDesc" style="display:none"></span>
                        <span class="error" id="advice-max-length-actDesc" style="display:none"></span>
                        <span class="error" id="advice-server-actDesc" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="groupId" class="req">分类：</label>
                    </div>
                    <div class="input">
                        <form:input path="groupId" cssClass="small required min-length-0 max-length-5" maxlength="5" />
                    	<span class="error" id="advice-required-groupId" style="display:none"></span>
                    	<span class="error" id="advice-min-length-groupId" style="display:none"></span>
                        <span class="error" id="advice-max-length-groupId" style="display:none"></span>
                        <span class="error" id="advice-server-groupId" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
					<div class="label">
						<label for="startTime" class="req">开始时间:</label>
					</div>
					<div class="input">
						<input type="text" id="startTime" name="startTime" value="<ct:time source="${online.startTime}"/>" class="date required" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" />
						<span class="error" id="advice-required-startTime" style="display:none"></span>
                        <span class="error" id="advice-server-startTime" style="display:none"></span>
					</div>
				</div>
				<div class="field">
					<div class="label">
						<label for="stopTime" class="req">结束时间:</label>
					</div>
					<div class="input">
						<input type="text" id="endTime" name="endTime" value="<ct:time source="${online.endTime}"/>" class="date required" readOnly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" />
						<span class="error" id="advice-required-endTime" style="display:none"></span>
                        <span class="error" id="advice-server-endTime" style="display:none"></span>
					</div>
				</div>
				<c:if test="${method == 'edit'}">
				<div class="field">
                    <div class="label">
                        <label for="router">配置指令：</label>
                    </div>
					<div class="select">
						 发送指令
                        <input type="text" id="command" name="command" value="${router.command}" placeholder="指令" title="指令" style="width:100px;height:20px;" maxlength="25" />
						到
						<select id="rSpCode" name="rSpcode">
                    	<c:forEach items="${sysSpcodeList}" var="item">
                        	<option value="${item.spcode}">${item.spcode}</option>
                        </c:forEach>
                    	</select>
                    	+					
						<input type="text" id="cSpCode" name="cSpCode" value="${router.cSpCode}" placeholder="特服号" title="特服号" style="width:150px;height:20px;" maxlength="10" /> 
						<br /><br />
						支付方式：
						<select id="payType" name="payType">
                            <option value="">--请选择--</option>
                            <c:forEach items="${payTypeMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${router.payType == item.key}">selected="selected"</c:if>>${item.value}</option>
                        	</c:forEach>
                        </select>
                                                                购买价格：
                        <input type="text" id="itemPrice" name="itemPrice" placeholder="购买价格" title="购买价格" style="width:150px;height:20px;" maxlength="9" />     
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <ct:display model="sms_act_online_query" btn="add_btn">
                        <button type="button" id="addRouter" class="common_btn">增加指令</button> 
                        </ct:display>               
					</div>
                </div> 
                <div class="field" id="routerList" <c:if test="${empty routerList}">style="display: none"</c:if>>
                    <div class="label">
                        <label for="router">指令：</label>
                    </div>
					<div class="select">
						<table class="datalist fixwidth" id="routerListTable">
							<tr>
				                <th nowrap="nowrap" width="100">指令</th>
				                <th nowrap="nowrap" width="100">特服号</th>
				                <th nowrap="nowrap" width="80">支付方式</th>
				                <th nowrap="nowrap" width="80">购买价格</th>
				                <th nowrap="nowrap" width="80">操作</th>
				            </tr>
							<c:forEach items="${routerList}" var="item">
				            <tr>
				                <td nowrap="nowrap">${item.command}</td>
				                <td nowrap="nowrap">${item.spCode}</td>
				                <td nowrap="nowrap">${item.payTypeName}</td>
				                <td nowrap="nowrap">${item.itemPrice}</td>
				                <td nowrap="nowrap">
					                <ct:display model="sms_act_online_query" btn="del_btn">
					                	<input type="button" class="common_btn" onclick="deleteRouterItem(this, 'router_delete/${item.id}');" value="删除" />
					                </ct:display>
				                </td>
				            </tr>
				            </c:forEach>
						</table>
					</div>
                </div>      
                </c:if>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
<script type="text/javascript">
$().ready(function() {
	//获取来源地址
	var url = document.referrer;
	$("#backUrl").val(url);
	$("#addRouter").click(function() {
		var actId = '${online.actId}';
		var command = $("#command").val();
		if("" == command){
			simpleAlert('请填写指令!',function(){});
			return false;
		}
		var rSpCode = $("#rSpCode").val();
		var cSpCode = $("#cSpCode").val();
		if("" == cSpCode){
			simpleAlert('请填写完整特服号!',function(){});
			return false;
		}
		if(/[^\d]/.test(cSpCode)){
			simpleAlert('特服号必须为数字!',function(){});
			return false;
		}
		var payType = $("#payType").val();
		if("" == payType){
			simpleAlert('请选择支付方式!',function(){});
			return false;
		}
		var itemPrice = $("#itemPrice").val();
		if("" == itemPrice){
			simpleAlert('请填写购买价格!',function(){});
			return false;
		}
		if(/[^\d*$]/.test(itemPrice)){
			simpleAlert('购买价格必须为数字!',function(){});
			return false;
		}
			$.ajax({
				   type: "POST",
				   url: "addRouter.do",
				   cache : false, 
				   dataType : "json",
				   data: {
				   		actId:actId,
				   		command:command,
				   		rSpCode:rSpCode,
				   		cSpCode:cSpCode,
				   		payType:payType,
				   		itemPrice:itemPrice
				   		},
				   success: function(json){
						if(null != json.id){
							$("#routerList").show();
						    $("#routerListTable").append("<tr><td nowrap='nowrap'>" 
						    + json.command + "</td><td nowrap='nowrap'>" 
						    + json.spCode + "</td><td nowrap='nowrap'>" 
						    + json.payTypeName + "</td><td nowrap='nowrap'>" 
						    + json.itemPrice + "</td><td nowrap='nowrap'><input type='button' onclick=\"deleteRouterItem(this, 'router_delete/" 
						    + json.id + "')\");' value='删除' class='common_btn'></td></tr>"); 						    
					    }else if(null != json.msg){
					    	simpleAlert(json.msg, function(){});
					    }else{
					    	simpleAlert('增加指令失败!',function(){});
					    }
				   }
			});					
	});
});
</script>
</body>
</html>