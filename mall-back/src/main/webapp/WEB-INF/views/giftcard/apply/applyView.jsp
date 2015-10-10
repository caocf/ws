<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<head>
<ht:head />
</head>
<body>
	<div id="content">
		<div class="box">
			<div class="title">
				<h5>出库申请查看</h5>
			</div>
			<form:form method="post" id="fm" commandName="info" htmlEscape="true"
				acceptCharset="utf-8" cssClass="required-validate"
				enctype="multipart/form-data">
				<div class="form">
					<div class="fields">
		                <div class="field">
		                    <div class="label noinput">ID：</div>
		                    <div class="input">${info.id}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">申请类型：</div>
		                    <div class="input">${info.typeName}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">申请人：</div>
		                    <div class="input">${info.applyUserName}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">申请时间：</div>
		                    <div class="input"><ct:time source="${info.applyTime}" />&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">支付状态：</div>
		                    <div class="input">${info.payStatusName}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">入账金额：</div>
		                    <div class="input"><fmt:formatNumber value="${info.payment/100}" pattern="0.00"/>&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">联系人：</div>
		                    <div class="input">${info.contactName}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">收货地址：</div>
		                    <div class="input">${info.address}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">联系电话：</div>
		                    <div class="input">${info.cellphoneNumber}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">邮政编码：</div>
		                    <div class="input">${info.zipCode}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">状态：</div>
		                    <div class="input">${info.statusName}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">审核人：</div>
		                    <div class="input">${info.auditUser}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">审核时间：</div>
		                    <div class="input"><ct:time source="${info.auditTime}" />&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">审核意见：</div>
		                    <div class="input">${info.auditAdvice}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">领用人：</div>
		                    <div class="input">${info.receiveName}&nbsp;</div>
		                </div>
		                <div class="field">
		                    <div class="label noinput">备注：</div>
		                    <div class="input">${info.remark}&nbsp;</div>
		                </div>
		                <c:if test="${info.type eq 0}">
		                 <div class="field">
		                    <div class="label noinput">支付订单：</div>
		                    <div class="input">${info.orderId}&nbsp;</div>
		                </div>
		                </c:if>
		                <c:if test="${info.type eq 1}">
		                 <div class="field">
		                    <div class="label noinput">付款单位全称：</div>
		                    <div class="input">${info.payUnit}&nbsp;</div>
	                	 </div>
		                 <div class="field">
		                    <div class="label noinput">付款行名称：</div>
		                    <div class="input">${info.payBank}&nbsp;</div>
	                	 </div>
		                 <div class="field">
		                    <div class="label noinput">担保人：</div>
		                    <div class="input">${info.guarantorName}&nbsp;</div>
	                	 </div>
		                 <div class="field">
		                    <div class="label noinput">入账人：</div>
		                    <div class="input">${info.accountedName}&nbsp;</div>
	                	 </div>
		                 <div class="field">
		                    <div class="label noinput">入账单号：</div>
		                    <div class="input">${info.accountedCode}&nbsp;</div>
	                	 </div>
		                 <div class="field">
		                    <div class="label noinput">入账日期：</div>
		                    <div class="input"><ct:time source="${info.accountedTime}" />&nbsp;</div>
	                	 </div>
		                </c:if>
		                <br/>
						<div class="model-block">
				        	<div class="block-head">
				            	<h4>礼品卡信息</h4>
				            </div>
				            <div class="block-body">
				            
				            	<table cellpadding="0" cellspacing="0" class="datalist fixwidth">
				            		<c:if test="${empty extInfoList}">无礼品卡信息</c:if>
				            		<c:if test="${not empty extInfoList}">
				                	<tr>
				                    	<th>卡批次号</th>
				                        <th>卡数量</th>
				                    </tr>
				                    </c:if>
				                    <c:forEach items="${extInfoList }" var="extInfo">
				                    <tr>
				                    	<td>
				                    	${extInfo.batchNo}	
				                    	</td>
				                    	<td>
				                        ${extInfo.num}
				                        </td>
				                    </tr>
				                    </c:forEach>
				                    
				                </table>
				            </div>
				        </div><br/>
						<div class="form">
			            <div class="fields">
			                <div class="buttons" align="center">
			                    <a href="javascript:history.back();" class="btnAnchor">返回</a>
			                </div>
			            </div>
			        	</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>