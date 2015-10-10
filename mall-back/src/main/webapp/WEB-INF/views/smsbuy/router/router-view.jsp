<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<br />

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>短信购商品指令信息</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="addItemRouter" commandName="smsbuyItemRouter"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
            	   <div class="field">
							<div class="label">
								<label for="itemName"  class="req">商品：</label>
							</div>
							<div class="input">
								${smsbuyItemRouter.itemName}
							</div>
						</div>
						
						<div class="field">
							<div class="label">
								<label for="spCode"  class="req">特服号：</label>
							</div>
							<div class="input">
								${smsbuyItemRouter.spCode}
							</div>
						</div>
						
				<c:if test="${smsbuyItemRouter.cmdOptType!=null}">
				<div class="field">
                   <div class="label label-radio">
                        <label for="cmdOptType" >指令类型：</label>
                    </div>
                    <div class="radios">
                        	${smsbuyItemRouter.cmdOptTypeName}
                         </div>
                    </div>
                   </c:if>
                     
                    <div class="field">
							<div class="label">
								<label for="command"  class="req">指令内容：</label>
							</div>
							<div class="input">
								${smsbuyItemRouter.command}
							</div>
						</div>
						
				<c:if test="${smsbuyItemRouter.payTypeName!=null}">
				<div class="field">
                   <div class="label label-radio">
                        <label for="payType">商品支付方式：</label>
                    </div>
                    <div class="radios">
                        	${smsbuyItemRouter.payTypeName}
                         </div>
                    </div>
                    </c:if>
                    
                    <c:if test="${smsbuyItemRouter.itemPrice!=null}">
                    <div class="field">
							<div class="label">
								<label for="itemPrice">商品购买价格：</label>
							</div>
							<div class="input">
								${smsbuyItemRouter.itemPrice/100}(元)
							</div>
						</div>
						 </c:if>
						 <div class="field">
		                   <div class="label label-radio">
		                        <label for="payType">优先级：</label>
		                    </div>
		                    <div class="radios">
		                        	${smsbuyItemRouter.priority}
		                         </div>
		                    </div>
                     <c:if test="${smsbuyItemRouter.isSession!=null}">
                    <div class="field">
                   <div class="label label-radio">
                        <label for="isSession">是否需要会话：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                           <c:if test="${smsbuyItemRouter.isSession==0 }">不需要</c:if>
                           <c:if test="${smsbuyItemRouter.isSession==1 }">需要</c:if>
                        </div>
                        <span class="error" id="advice-validate-one-required-isSession" style="display:none"></span>
                         </div>
                    </div>
                    </c:if>
                    
                    <div class="buttons">
                     <a href="${ctx}/smsbuy/act/preAddItemRouter.do?actId=${smsbuyItemRouter.actId}" class="btnAnchor">返回</a>
                </div>
   			</div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>