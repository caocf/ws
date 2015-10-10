<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
	<script type="text/javascript">
	
		function selectStore(){
			selectThirdStore(function(storeId,storeName){
					$("#storeInput").val(storeName);
					$("#storeId").val(storeId);
					$("#storeInput").focus();
					$("#storeInput").blur();
		 		});
			
		}
		
		function selectThirdStore(callback,opts){
			var param = $.extend({	
				ShowMessageRow:false,
				Height: 400,
				Width : 700},opts||{});
			
			var url = G_CTX_ROOT + '/item/thirdcode/selectStore';
			
			showDialog("选择商户",url, function(doc,win){
				var selectRadio = $("input[name='storeSelector']:checked",doc);
				if(!jQuery.isEmptyObject(selectRadio)){
					var storeId =selectRadio.attr("storeId");
					var storeName =selectRadio.attr("storeName");
					if(jQuery.isFunction(callback)){
						callback(storeId,storeName,doc,win);
					}
				}
			},param);
 		}
		$(function(){
			//增加
			$("#add").click(function(){
				$("#new").css("display","block");
			});
			//删除
			$("#del").click(function(){
				$("#new").css("display","none");
				$("#name").val("");
			});
			
			//卡密和非卡密选择
			$("input[name='codeType']").click(function(){
				var codeType=$(this).val();
				var nameobj=$("#nameId");
				$.ajax({
					type:"post",
					url:"${ctx}/item/thirdcode/selectCodeNames?codeType="+codeType,
					dataType:"json",
					success:function(data){
						nameobj.empty();
						for(var i=0;i<data.length;i++){
							nameobj.append("<option value="+data[i].id+">"+data[i].name+"</option>");
						}
					},
					error:function(data){
						alert("获取数据异常");
					}
				});
			});
		});
	</script>
</head>
<body>
	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div  class="title">
				<h5>
				 新建批量导入任务
				</h5>
			</div>
			<!-- end box / title -->
			<form:form method="post" id="fm"  
				htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
				<div class="form">
					<div class="fields">
						<div class="field">
							<div class="label">
								<label class="req">选择商户：</label>
							</div>
							<div class="input">
								<input readonly name="storeInput" id="storeInput" onclick="selectStore();"  class="small required" style="width:150px;height:15px;">
								<span class="error" id="advice-required-itemstoreInput" style="display:none"></span>
								<input type="hidden" name="storeId" id="storeId">
							</div>
						</div>
						<div class="field">
							<div class="label label-radio">
								<label for="sort" class="req">类型:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" name="codeType" value="2" checked /><label for="sort1">非卡密</label>
									<input type="radio" name="codeType" value="3" /><label for="sort1">卡密</label>
								</div>
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="name">验证码名称：</label>
							</div>
							<div class="select">
								<select name="nameId" id="nameId">
									<c:forEach items="${codeNames}" var="item" varStatus="index">
										<option selected value="${item.id }" >${item.name}</option>
									</c:forEach>
								</select>
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;<a id="add" style="cursor:pointer;color:blue">添加</a>
						</div>
						<div id="new" class="field" style="display:none">
							<div class="label">
								<label for="name">新建验证码名称：</label>
							</div>
							<div class="input">
								<input id="name" name="name" style="width:200px" maxlength="50">
								&nbsp;&nbsp;&nbsp;&nbsp;<a id="del" style="cursor:pointer;color:blue">删除</a>
							</div>
						</div>
						<div class="field">
		                    <div class="label">
		                        <label for="file" class="req">上传文件:</label>
		                    </div>
		                    <div class="input input-file">
		                        <input type="file" id="uploadfile" name="uploadfile" class="required validate-file-xls-xlsx"/>
		                        <span class="tip">xls格式</span>
		                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        <a href="${ctx }/static/resources/thirdCodeModel.xls">下载非卡密模版</a>
		                        &nbsp;&nbsp;&nbsp;&nbsp;
		                        <a href="${ctx }/static/resources/CardCodeModel.xls">下载卡密模版</a>
		                        <span class="error" id="advice-validate-file-uploadfile" style="display:none"></span>
		                        <span class="error" id="advice-required-uploadfile" style="display:none"></span>
		                    </div>
		                </div>
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

</body>
</html>