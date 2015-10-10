<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    		$(document).ready(function(){
    		
    			$("#itemName").selectSinggleGood($("#itemId").val(),function(itemId,itemName){
    					$id('itemId').value =itemId;
    					$id('itemName').value =itemName;
    			});
    			
    		});	
    </script>
</head>
<body>
<br />
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>添加短信购商品指令配置</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="addItemRouter" commandName="smsbuyItemRouter"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
        <form:hidden path="actId"/>
        <form:hidden path="id"/>
            <div class="fields">
            	   <div class="field">
							<div class="label">
								<label for="itemName"  class="req">选择商品：</label>
							</div>
							<div class="input">
								<form:input path="itemName" cssClass="small required"
									maxlength="100" readonly="true" />
								<form:hidden path="itemId" />
								<span class="error" id="advice-required-itemName"
									style="display:none"></span>
							</div>
						</div>
						
				<div class="field">
				<div class="label">
						<label for="rootSpcode"  class="req">选择根特服号：</label>
				</div>
				<div class="input">
						<select name="rootSpcode">
                    	<c:forEach items="${sysSpcodeList }" var="item">
                        	<option value="${item.spcode }" <c:if test="${param.spcode == item.spcode}">selected="selected"</c:if>>${item.spcode }</option>
                        </c:forEach>
                    	</select>
					<span class="error" id="advice-required-rootSpcode" style="display:none"></span>
					</div>
				</div>
						
						<div class="field">
							<div class="label">
								<label for="spCode"  class="req">特服号：</label>
							</div>
							<div class="input">
								<form:input path="spCode" cssClass="small required" maxlength="100" />
								<span class="error" id="advice-required-spCode"
									style="display:none"></span>
							</div>
						</div>
						
						
				<div class="field">
                   <div class="label label-radio">
                        <label for="cmdOptType" class="req">指令类型：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${cmdOptTypeMap}" var="item" varStatus="index">
                            <input type="radio" id="radio0-${index.count }" name="cmdOptType" class="validate-one-required"  <c:if test="${item.key==smsbuyItemRouter.cmdOptType }">checked="checked"</c:if> value="${item.key}"/><label for="radio0-${index.count }">${item.value }</label>
                            </c:forEach>
                        </div>
                        <span class="error" id="advice-validate-one-required-cmdOptType" style="display:none"></span>
                         </div>
                    </div>
                    
                    <div class="field">
							<div class="label">
								<label for="command"  class="req">指令内容：</label>
							</div>
							<div class="input">
								<form:input path="command" cssClass="small required" maxlength="100" />
								<span class="error" id="advice-required-command"
									style="display:none"></span>
							</div>
						</div>
						
						
				<div class="field">
                   <div class="label label-radio">
                        <label for="payType">商品支付方式：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${payTypeMap}" var="item" varStatus="index">
                            <input type="radio" id="radio1-${index.count }" name="payType" <c:if test="${item.key==smsbuyItemRouter.payType }">checked="checked"</c:if> value="${item.key}"/><label for="radio1-${index.count }">${item.value }</label>
                            </c:forEach>
                        </div>
                        <span class="error" id="advice-validate-one-required-payType" style="display:none"></span>
                         </div>
                    </div>
                    
                    
                    <div class="field">
							<div class="label">
								<label for="itemPrice">商品购买价格：</label>
							</div>
							<div class="input">
								<form:input path="itemPrice" cssClass="validate-number" maxlength="100" />
								<span class="error" id="advice-required-itemPrice"
									style="display:none"></span>
							</div>
						</div>
                    
                    <div class="field">
                   <div class="label label-radio">
                        <label for="isSession" class="req">是否需要会话：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio2-1" name="isSession" class="validate-one-required"  value="0"  <c:if test="${smsbuyItemRouter.isSession==0 }">checked="checked"</c:if>/><label for="radio2-1">不需要</label>
                            <input type="radio" id="radio2-2" name="isSession" class="validate-one-required"  value="1" <c:if test="${smsbuyItemRouter.isSession==1 }">checked="checked"</c:if>/><label for="radio2-2">需要</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-isSession" style="display:none"></span>
                         </div>
                    </div>
                    
                    <div class="buttons">
                    <div class="highlight">
                    <input type="submit" name="submit.highlight" value="添加" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                     <a href="${ctx}/smsbuy/act/list" class="btnAnchor">返回</a>
                </div>
   			</div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>