<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
 <script type="text/javascript">
</script>

    </head>
<body>
 	<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>查看短信购地址</h5>
    </div>
	
		  <form:form action="${ctx}/order/smsbuy/view?orderId=${ orderId}" method="post" id="fm" commandName="express" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <input type="hidden" name ="groupFlag" value="1" />
        <div class="form">
					<div class="fields">
					<div class="field" >
						<div class="label">
							<label for=" orderId" >订单号：</label>
						</div>
						<div class="input">
						${ orderId }&nbsp;
						</div>
					</div>
 					<div class="field" >
						<div class="label">
							<label for="address" >地址：</label>
						</div>
						<div class="input">
						${info.address }&nbsp;
						</div>
					</div>
					<div class="field" >
						<div class="label">
							<label for="receiverName" >收货人：</label>
						</div>
						<div class="input">
						${info.receiverName}&nbsp;
						</div>
					</div>
					<div class="field" >
						<div class="label">
							<label for="cellphoneNumber" >手机号码：</label>
						</div>
						<div class="input">
						${info.cellphoneNumber}&nbsp;
						</div>
					</div>
						<div class="buttons">
						
						<input type="button" class="common_btn" onclick="history.back();" value="返回" />
					</div>
				</div>
    </form:form>

</div>
</div>
</body>
</html>