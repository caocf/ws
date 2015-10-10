<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<head>
<ht:head />
</head>
<script type="text/javascript">
	var tempBatchNoJson = {};
	$(function(){
		var url = document.referrer;
		$("#backUrl").val(url);
		var jsonArr = ${extJson};
		for(var i =0;i<jsonArr.length;i++){
			var tempNum = i*(-1)+(-1);
			var json = jsonArr[i];
			$(tempBatchNoJson).attr(tempNum,json["batchNo"]);
			var tempTip = "该批次库存为"+json["stocks"]+",申请数量应不大于该值！";
			var html= "<div><br />"+
		        "<div class='input' style='float: left;'>"+
		        	"卡批次号:"+
		            "&nbsp;&nbsp;<input name='batchNoValue' id='batchNoValue"+tempNum+"' num = '"+tempNum+"' class='small required ' value='"+json["batchNo"]+"' readOnly onClick='selectBatchNo("+tempNum+");'/>"+
		        	"&nbsp;&nbsp;<span class='error' id='advice-required-batchNoValue"+tempNum+"' style='display:none;color: red'></span>"+
		        "</div>"+
		        "<div class='input' style='float: left;'>"+
		            "卡数量:"+
		            "&nbsp;&nbsp;<input name='numValue' id='numValue"+tempNum+"' num = '"+tempNum+"' value='"+json["num"]+"' class='small required float-range-1-"+json["stocks"]+"'/>"+
		            "&nbsp;&nbsp;<span id='numTip"+tempNum+"'>"+tempTip+"</span>"+
		        	"&nbsp;&nbsp;<span class='error' id='advice-required-numValue"+tempNum+"' style='display:none;color: red'></span>"+
		        	"&nbsp;&nbsp;<span class='error' id='advice-float-range-numValue"+tempNum+"' style='display:none;color: red'></span>"+
		        "</div>";
			    if(i==0){
			    	html = html+"</div><br/>"
			    }else{
			    	html = html+"&nbsp;&nbsp;<a href='javascript:void(0);' id='del' onclick='delpara(this)'>删除</a></div>"
			    }
		$('#addGiftInfo').append(html); 
		}
	});
	var num = 0;
	function addGiftInfo() {
		num = num + 1;
		var html= "<div><br />"+
	                    "<div class='input' style='float: left;'>"+
	                    	"卡批次号:"+
	                        "&nbsp;&nbsp;<input name='batchNoValue' id='batchNoValue"+num+"' num = '"+num+"' class='small required ' readOnly onClick='selectBatchNo("+num+");'/>"+
	                    	"&nbsp;&nbsp;<span class='error' id='advice-required-batchNoValue"+num+"' style='display:none;color: red'></span>"+
	                    "</div>"+
	                    "<div class='input' style='float: left;'>"+
	                        "卡数量:"+
	                        "&nbsp;&nbsp;<input name='numValue' id='numValue"+num+"' num = '"+num+"' class='small required '/>"+
	                        "&nbsp;&nbsp;<span id='numTip"+num+"'></span>"+
	                    	"&nbsp;&nbsp;<span class='error' id='advice-required-numValue"+num+"' style='display:none;color: red'></span>"+
	                    	"&nbsp;&nbsp;<span class='error' id='advice-float-range-numValue"+num+"' style='display:none;color: red'></span>"+
	                    "</div>"+
                    "&nbsp;&nbsp;<a href='javascript:void(0);' id='del' onclick='delpara(this)'>删除</a></div>";
		 $('#addGiftInfo').append(html); 
    }
    
    function delpara(obj) {
		 $(obj).parent().remove();
    }
	
	function selectBatchNo(num){
		selectBatchNoCallBack(function(batchNo,stocks){
			if(typeof(batchNo) != 'undefined' && typeof(stocks) != 'undefined'){
				//simpleAlert("未选择卡批次，请重新选择！",null);
				//alert(num+"--"+batchNo+"------"+stocks);
				if(typeof(num) == 'undefined'){
					num = '';
				}
				$(tempBatchNoJson).attr(num,batchNo);
				var flag = true;
				$.each(tempBatchNoJson,function(key,val){
					if(batchNo==val&&num!=key){
						simpleAlert("该批次已经选择，请选择其他批次！",null);
						eval("delete tempBatchNoJson["+num+"]")
						flag = false;
						return false;
					}
				});
				//console.log(tempBatchNoJson[num]);
				//console.dir(tempBatchNoJson);
				if(flag){
				$("#batchNoValue"+num).attr("value",batchNo);;
				$("#numValue"+num).attr("value",stocks);
				$("#numTip"+num).html("该批次库存为"+stocks+",申请数量应不大于该值！");
				//$("#numValue"+num).attr("class")
				var tempclass = "small required "+"float-range-1-"+stocks;
				$("#numValue"+num).attr("class",tempclass);
				}
			}
	 		});
	}
    
    
	function selectBatchNoCallBack(callback,opts){
		var param = $.extend({	
			ShowMessageRow:false,
			Height: 400,
			Width : 800},opts||{});
		
		var url = G_CTX_ROOT + '/giftcard/stock/selectBatchNolist';
		
		showDialog("选择礼品卡批次",url, function(doc,win){
			var selectRadio = $("input[name='batchNoSelector']:checked",doc);
			if(!jQuery.isEmptyObject(selectRadio)){
				var batchNo =selectRadio.val();
				var stocks =selectRadio.attr("stocks");
				if(jQuery.isFunction(callback)){
					callback(batchNo,stocks,doc,win);
				}
			}
		},param);
		}
</script>
<body>
	<div id="content">
		<div class="box">
			<div class="title">
				<h5>
					<c:if test="${info.type eq 0}">线上出库申请修改</c:if>
					<c:if test="${info.type eq 1}">线下出库申请修改</c:if>
				</h5>
			</div>
			<form:form method="post" id="fm" action="editor" commandName="info" htmlEscape="true"
				acceptCharset="utf-8" cssClass="required-validate"
				enctype="multipart/form-data">
				<input type="hidden" id="backUrl" name="backUrl" />
				<input type="hidden" name = "type" value="${info.type }">
				<input type="hidden" name = "id" value="${info.id }">
				<div class="form">
					<div class="fields">
		                <div class="field">
		                    <div class="label noinput">ID：</div>
		                    <div class="input">${info.id}</div>
		                </div>
		                <c:if test="${info.type eq 1}">
		                	<div id="addGiftInfo">
		                		<div class="field">
				                    <div class="label">
				                        <label for="giftInfo" class="req">礼品卡信息：</label>
				                    </div>
				                    <div class="input">
				                    <ct:btn type="button" value="增加" name="addbtn" onclick="addGiftInfo();"/>
				                    </div>
			                    </div>
                			</div>
                			 <div class="field"></div>
                			 <div class="field">
								<div class="label">
									<label >入账人 ：</label>
								</div>
								<div class="input">
									<form:input path="accountedName"
										cssClass="small min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
							<div class="field">
								<div class="label">
									<label >入账单号 ：</label>
								</div>
								<div class="input">
									<form:input path="accountedCode"
										cssClass="small min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
							<div class="field">
								<div class="label">
									<label >入账金额 ：</label>
								</div>
								<div class="input">
									<input name = "payment" value =<fmt:formatNumber value="${info.payment/100}" pattern="0.00"/> cssClass="small" maxlength="8" />
								</div>
							</div>
		                	<div class="field">
								<div class="label">
									<label class="req">联系人 ：</label>
								</div>
								<div class="input">
									<form:input path="contactName"
										cssClass="small required min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
		                	<div class="field">
								<div class="label">
									<label >担保人 ：</label>
								</div>
								<div class="input">
									<form:input path="guarantorName"
										cssClass="small min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
		                	<div class="field">
								<div class="label">
									<label class="req">收货地址 ：</label>
								</div>
								<div class="input">
									<form:textarea path="address"
										cssClass="small required min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
		                	<div class="field">
								<div class="label">
									<label class="req">联系电话 ：</label>
								</div>
								<div class="input">
									<form:input path="cellphoneNumber"
										cssClass="small required min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
		                	<div class="field">
								<div class="label">
									<label >邮政编码 ：</label>
								</div>
								<div class="input">
									<form:input path="zipCode"
										cssClass="small min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
							<!-- 
							<div class="field">
								<div class="label">
									<label >付款单位全称 ：</label>
								</div>
								<div class="input">
									<form:input path="payUnit"
										cssClass="small min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
		                	<div class="field">
								<div class="label">
									<label >付款行名称 ：</label>
								</div>
								<div class="input">
									<form:input path="payBank"
										cssClass="small min-length-1 max-length-25"
										maxlength="50" />
								</div>
							</div>
							 -->
		                </c:if>
						<div class="field">
							<div class="label">
								<label class="req">领用人：</label>
							</div>
							<div class="input">
								<form:input path="receiveName"
									cssClass="small required min-length-1 max-length-25"
									maxlength="50" />
								<span class="error" id="advice-required-min-lengt-receiveName"
									style="display:none"></span> <span class="error"
									id="advice-required-max-length-receiveName" style="display:none"></span>
							</div>
						</div>
						
						<div class="field">
							<div class="label">
								<label >描述：</label>
							</div>
							<div class="input">
								<form:textarea path="remark"
									cssClass="max-length-150"
									maxlength="50" />
								 <span class="error" id="advice-required-max-length-remark" style="display:none"></span>
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