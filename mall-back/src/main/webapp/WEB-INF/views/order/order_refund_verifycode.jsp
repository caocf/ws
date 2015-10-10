<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<div id="content">
<div class="box">
    <div class="title">
        <h5>退款单退码</h5>
    </div>
    <form id="fm" class="required-validate" action="${ctx}/order/refund/refund_verifycode" method="post" accept-charset="utf-8">
        <input type="hidden" id="id" name="id" value="${refund.id}">
        <input type="hidden" id="whereAbout" name="whereAbout" value="${whereAbout}">
        <div class="form">
            <div class="fields">                
                <div class="field">
                  <div class="label">
                        <label for="id">退款单号：</label>
                    </div>
                    <div class="input">
                       ${refund.id}
                    </div>
              	</div> 
              	<div class="field">
                    <div class="label">
                    	<label style="color: red;">商品码：</label>			                    
                    </div>
					<div class="select">
						<c:choose>
						<c:when test="${not empty verifycodeList}">
						<table class="datalist fixwidth">
							<tr>
								<th nowrap="nowrap" width="30">选择</th>
								<th nowrap="nowrap" width="180">码编号</th>
								<th nowrap="nowrap" width="100">订单商品号</th>
								<th nowrap="nowrap" width="200">商品名</th>
				                <th nowrap="nowrap" width="180">码</th>
				                <th nowrap="nowrap" width="100">是否可退</th>
				            </tr>
							<c:forEach items="${verifycodeList}" var="verifycode">
				            <tr>
				            	<td nowrap="nowrap">
				            		<c:if test="${verifycode.codeStatus == 0}">
					                	<input type="checkbox" name="orderId" value="${verifycode.orderId}" />
					                </c:if>
				                </td>
				                <td nowrap="nowrap">
				                	${verifycode.orderId}
				                </td>
				            	<td nowrap="nowrap">
				                	${verifycode.itemOrderId}
				                </td>
				                <td nowrap="nowrap" class="ellipsis">
				                	${verifycode.itemName}
				                </td>
				                <td nowrap="nowrap">
				                	${verifycode.code}
				                </td>
				                <td nowrap="nowrap">
				                	<c:choose>
					                	<c:when test="${verifycode.codeStatus == 0}">
					                	可退
					                	</c:when>
					                	<c:otherwise>不可退</c:otherwise>
				                	</c:choose>
				                </td>
				            </tr>
				            </c:forEach>
						</table>
						</c:when>
						<c:otherwise>
						无订单商品
						</c:otherwise>
						</c:choose>
					</div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="javascript:history.go(-1)" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form>
</div>
</div>
</body>
</html>