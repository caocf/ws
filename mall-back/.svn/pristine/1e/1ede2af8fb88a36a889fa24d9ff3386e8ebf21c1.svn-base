<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
 	<script type="text/javascript">
 	$().ready(function() {
		//选择活动
		
    	$("#active").click(function(){
    		selectActiveCallBack(function(activeId,activeName,prizeNumValue){
    			$("#activeId").val(activeId);
        		$("#active").val(activeName);
        		$("#prizeNumValue").text('温馨提示：该活动拥有的奖区数量为' + prizeNumValue + '个');
        		$("#prizeNum").val(prizeNumValue);
        		
        		$("#active").focus();
    	    	$("#active").blur();
    		});
			
		});
		
		
		function selectActiveCallBack(callback,opts){
	
			var param = $.extend({	
				ShowMessageRow:false,
				Height: 500,
				Width : 700},opts||{});
			
			var url = G_CTX_ROOT + '/lottery/prize/selectActive';
			
			showDialog("选择活动",url, function(doc,win){
				var selectRadio = $("input[name='activeIdSelector']:checked",doc);
				if(!jQuery.isEmptyObject(selectRadio)){
					var valTxt =selectRadio.attr("activeName");
					var prizeNumValue =selectRadio.attr("prizeNumValue");
					var valId =selectRadio.val();
					if(jQuery.isFunction(callback)){
						callback(valId,valTxt,prizeNumValue,doc,win);
					}
				}
			},param);
			
			
		}
		$("#hitLevel").blur(function(){
			var activeId = $("#activeId").val();
			var hitLevel = $("#hitLevel").val();
			var pattern = /^[0-9]+$/;
			if(pattern.test(hitLevel) == false){
				$("#advice-required-hitLevel").text('请输入数字');
				$("#advice-required-hitLevel").css('display', 'block');
				$("#submitButton").attr("disabled","true");
			}else{
				$.ajax({
				
					url: G_CTX_ROOT + '/lottery/prize/isAdd?activeId='+activeId+'&hitLevel='+hitLevel,
					success: function(data) {
					  var msg = $.parseJSON(data);
					  if(msg.message == 'is'){
					  	$("#submitButton").removeAttr("disabled");
					  }
					  if(msg.message == 'not'){
					  	$("#advice-required-hitLevel").text('该奖区编号已经存在，请换一个奖区编号');
	    				$("#advice-required-hitLevel").css('display', 'block');
	    				$("#submitButton").attr("disabled","true");
					  }
				}
				
				});
			}
		});
		
		$("#position").blur(function(){
			var str = $("#position").val();
			var pattern = /^(\d+,)*\d+$/;  
			if(pattern.test(str) == false){
				$("#submitButton").attr("disabled","true");
				$("#advice-required-position").text('输入格式错误，请重新输入');
				$("#advice-required-position").css('display', 'block');
			}else{
				$("#submitButton").removeAttr("disabled");
			}
		});
		
	});
		
</script>
    </head>
<body>

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>添加奖品</h5>
    </div>
	
		  <form:form action="${ctx}/lottery/prize/add" method="post" id="fm" commandName="prize" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <input type="hidden" name ="groupFlag" value="1" />
        <div class="form">
					<div class="fields">
					  <div class="field">
                    <div class="label noinput">奖品ID：</div>
                    <div class="input">自动生成</div>
                </div>
	
 					<div class="field" >
							<div class="label">
								<label for="name" class="req">奖品名称:</label>
							</div>
							<div class="input">
								<input name="name" class=" required " maxlength="50" style="width:500px"/>
								<span class="error" id="advice-required-name"
									style="display:none"></span>
							</div>
					</div>	
								
				<div class="field" >
							<div class="label">
								<label for="active" class="req">归属活动:</label>
							</div>
							<div class="input">
								<form:hidden path="activeId" maxlength="100"/>
								<input id="active"  class=" required " maxlength="125" style="width:500px"/>
								<span class="error" id="advice-required-active"
									style="display:none"></span>
							</div>
					</div>	
					<br/>
					<span id="prizeNumValue" style="color:red;"></span>
					<input type="hidden" id="prizeNum"/>
						<div class="field" >
							<div class="label">
								<label for="hitLevel" class="req">奖区编号:</label>
							</div>
							<div class="input">
								<input id="hitLevel" name="hitLevel" class=" less-than-equal-prizeNum required validate-number " maxlength="2" style="width:100px"/>
									<span style="color:red;">&nbsp;&nbsp;&nbsp;温馨提示：奖区编号越小等级越高，优先中一等奖、然后二、三......</span>
								<span class="error" id="advice-required-hitLevel"
									style="display:none"></span>
								
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="hitProbability" class="req">中奖几率:</label>
							</div>
							<div class="input">
								<input name="hitProbability" class=" required  validate-number" maxlength="5" style="width:100px"/>
								<span style="color:red;">&nbsp;&nbsp;&nbsp;温馨提示：大于等于1表示必中奖；0（或负数）表示不可能中奖</span>
								<span class="error" id="advice-required-hitProbability"
									style="display:none"></span>
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="hitLimit" class="req">每人中奖限制:</label>
							</div>
							<div class="input">
								<input name="hitLimit" class=" required validate-number" maxlength="2" style="width:100px"/>
								<span class="error" id="advice-required-hitLimit"
									style="display:none"></span>
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="hitMsg" class="req">中奖提示语:</label>
							</div>
							<div class="input">
								<input name="hitMsg" class=" required " maxlength="50" style="width:500px"/>
								<span class="error" id="advice-required-hitMsg"
									style="display:none"></span>
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="numbers" class="req">奖品数量:</label>
							</div>
							<div class="input">
								<input name="numbers" class=" required  validate-number" maxlength="6" style="width:100px"/>
								<span class="error" id="advice-required-numbers"
									style="display:none"></span>
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="position" class="req">奖品位置:</label>
							</div>
							<div class="input">
								<input id="position" name="position" class=" required  " maxlength="20" style="width:100px"/>
								<span style="color:red;">&nbsp;&nbsp;&nbsp;温馨提示：奖品位置输入为数字，多个奖品位置用逗号（，）分隔</span>
								<span class="error" id="advice-required-position"
									style="display:none"></span>
								
							</div>
					</div>	
					
					
								<div class="field">
							<div class="label label-textarea">
								<label for="advice">备注：</label>
							</div>
							<div class="input">
								<textarea id="description" name="description" class="max-length-100"
									rows="8" cols="100"></textarea>
							</div>
						</div>		
				
					
						<div class="buttons">
							<div class="highlight">
								<input id="submitButton" type="submit" name="submit.highlight" value="提交" />
							</div>
								<input type="reset" name="reset" value="重置" />
                    			<input type="button" class="common_btn" onclick="history.back();" value="返回" />
							
						</div>	
				
    </form:form>
</div>

</div>
</body>
</html>