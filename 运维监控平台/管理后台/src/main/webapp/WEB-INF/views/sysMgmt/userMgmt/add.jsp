<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/t.jsp"%>
<script type="text/javascript">
$(function(){
	function checkRole() {
// 		var option = $('select[name=roleId]').find('option:selected');
// 		if (option.length <= 0) {
// 			$('#advice-required-roleId').show();
// 			return false;
// 		} else {
// 			$('#advice-required-roleId').hide();
// 			return true;
// 		}
return true;
	}
	
	$("#code").change(function(){
		var code = $(this).val();
		$.ajax({
			type:'post',
			url:'${ctx}/sysMgmt/userMgmt/check',
			data:{code:code},
			success:function(msg){
				if(msg==1){
					
					$("#repeat-error").css("background","#DA4F49");
					$("#repeat-error").html("用户名已经存在！")
				}else{
					
					$("#repeat-error").css("background","#5ebb0b");
					$("#repeat-error").html("用户名可用！")
			
				}
			},
			error:function(){
				alert("用户验证失败！");
			}
		})
	}); 
	
	$("#sub").click(function(){
		
		var code = $('#code').val();
		$.ajax({
			type:'post',
			url:'${ctx}/sysMgmt/userMgmt/check',
			data:{code:code},
			success:function(msg){
				if(msg==1){
					$("#repeat-error").css("background","#DA4F49");
					$("#repeat-error").html("账号已经存在！")
					return;
				}else{
					
					$("#repeat-error").css("background","#5ebb0b");
					$("#repeat-error").html("账号可用！")
					if(checkRole()){
						$("#fm").submit();
					}
				}
				
			}
		});
		
	
})
});
</script>

<div class="row-fluid">
	<div class="span12">
		<form:form method="post" id="fm" commandName="dcUser"
			htmlEscape="true" acceptCharset="utf-8"
			cssClass="required-validate form-horizontal">
			<div class="row-fluid">
				<div class="control-group group-search">
					<label class="control-label req" for="code">用户名：</label>
					<div class="controls controls-row">
						<form:input path="code" cssClass="span6 required max-length-10" />
					</div>
					<span id="repeat-error" style="color: #000000; margin-left: 130px;">检验账号是否可用！</span>
				</div>
		        <div class="control-group group-search">
		            <label class="control-label req">新密码：</label>
		            <div class="controls controls-row">
		                <form:password path="newPass" cssClass="span6 required min-length-6 max-length-10" maxlength="20"/>
		            </div>
		        </div>
		        <div class="control-group group-search">
		            <label class="control-label req">新密码确认：</label>
		            <div class="controls controls-row">
		                <form:password path="confirmPass" cssClass="span6 equals-newPass" title="两次输入密码不一致"
		                               maxlength="20"/>
		            </div>
		        </div>
				<div class="control-group group-search">
					<label class="control-label req" for="name">姓名：</label>
					<div class="controls controls-row">
						<form:input path="name" cssClass="span6 required max-length-10" />
					</div>
				</div>
				<div class="control-group group-search">
					<label class="control-label req" for="terminalId">电话：</label>
					<div class="controls controls-row">
						<form:input path="terminalId"
							cssClass="span6 required validate-mobile-phone" maxlength="20" />
					</div>
				</div>
				<div class="control-group group-search">
					<label class="control-label req" for=email>邮箱：</label>
					<div class="controls controls-row">
						<form:input path="email" cssClass="span6 required validate-email"
							maxlength="50" />
					</div>
				</div>
				<div class="control-group group-search">
					<label class="control-label" for="roleId">角色：</label>
					<div class="controls controls-row" style="width: 44.2%">
						<form:select path="roleId" id="roleId" cssClass="multiselect"
							multiple="multiple" style="height:200px;width:300px;">
							<c:forEach items="${roles}" var="role">
                                <c:if test="${role.id != 0}">
								    <form:option value="${role.id}">${role.roleName}</form:option>
                                </c:if>
							</c:forEach>
						</form:select>
						<label style="display: none;" id="advice-required-roleId"
							class="error"> 请选择.</label>
					</div>
				</div>
			</div>

			<div class="control-group group-search">
				<label class="control-label"></label>
				<div class="controls controls-row">
					<button type="button" class="btn btn-primary" id="sub"
						onclick="checkRole()">
						<i class="icon16 i-disk"></i> 保 存
					</button>
<!-- 					<button type="reset" class="btn"> -->
<!-- 						<i class="icon16 i-loop"></i> 重 置 -->
<!-- 					</button> -->
					<button class="btn" type="button" onclick='history.back();'>
						<i class="icon16 i-exit"></i> 返 回
					</button>
				</div>
			</div>
	</div>
	</form:form>
</div>
</div>

<%@ include file="../../includes/b.jsp"%>
