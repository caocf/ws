<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
 <script type="text/javascript">
	$().ready(function() {

		$("#audit_btn1").click(function() {
			var id = '${itemSale.id }';
			window.location.href="${ctx}/item/meal/auditing/"+id;
			
		});
		
		$(".item_view").click(function() {
			var id = $(this).attr("id");
			window.location = 'view/' +id;

		});
		
		$("#audit_btn2").click(function() {
			var id = '${itemSale.id }';
			window.location.href="${ctx}/item/meal/twoAuditing/"+id;
			
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
        <h5>查看套餐商品</h5>
    </div>
    <!-- end box / title -->
<form:form method="post" id="fm" commandName="itemSale" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data" >
        <input type="hidden" name ="groupFlag" value="1" />
        <form:hidden path="id"/>
        <div class="form">
            <div class="fields">
            <div class="field">
						<div class="label">
							<label for="shopId" >所属商户：</label>
						</div>
						<div class="input">
						${store.name }&nbsp;
						</div>
				</div>
                 <div class="field"  id="itemIdsDiv" >
                    <div class="label">
                        <label for="itemIds" >套餐商品:</label>
                    </div>
                    <div class="input">
                    ${itemSale.itemName}&nbsp;
                    </div>
                </div>
                    
                   
                <div class="field">
                    <div class="label">
                        <label for="name" >套餐名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                         ${itemSale.name }&nbsp;
                    </div>
                </div>
                
             
              
               
						<div class="field">
							<div class="label">
								<label for="saleStartTime" >销售有效开始时间:</label>
							</div>
							<div class="input">
							<ct:time source="${itemSale.saleStartTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd"  />
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="saleStopTime" >销售有效结束时间:</label>
							</div>
							<div class="input">
							<ct:time source="${itemSale.saleStopTime}"  sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd" />
							</div>
						</div>
						
						<div class="field" >
							<div class="label">
								<label for="stockNum" >商品库存:</label>
							</div>
							<div class="input">
							<c:if test="${itemSale.stockNum eq -1000}"> 999999999(不限)</c:if> 
								<c:if test="${itemSale.stockNum != -1000}">${itemSale.stockNum }</c:if>
							</div>
						</div>
					
						<div class="field" >
							<div class="label">
								<label for="marketPrice" >商城价:</label>
							</div>
							<div class="input">
							${itemSale.marketPrice }&nbsp;
								
							</div>
						</div>
							<div class="field" >
							<div class="label">
								<label for="shopPrice" >优惠后价格:</label>
							</div>
							<div class="input">
								${itemSale.shopPrice }&nbsp;
								
							</div>
						</div>
				
				
						<!-- 不发码显示是否需要物流 -->
						<div class="field">
							<div class="label label-radio">
								<label>是否需要物流:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<c:if test="${itemSale.postFlag eq 0}"> 不需要物流配送</c:if>
									<c:if test="${itemSale.postFlag eq 1}"> 需要物流配送 </c:if>
								</div>
							</div>
						</div>

						<!-- 需要物流配送此div显示 begin-->
						<div id="isPost"
							<c:if test="${itemSale.postFlag eq 0}">style="display: none;"</c:if>>
							<div class="field" style="display:none">
								<div class="label">
									<label for="postArea" >配送区域选择:</label>
								</div>
								<div class="input">${itemSale.postArea}&nbsp;</div>
							</div>
							<div class="field">
								<div class="label">
									<label for="logisticsFee" >物流运费:</label>
								</div>
								<div class="input">
									${itemSale.logisticsFee}
									<c:if test="${itemSale.logisticsFeeType==0 }">&nbsp;&nbsp;&nbsp;√不累计</c:if>
									<c:if test="${itemSale.logisticsFeeType==1 }">&nbsp;&nbsp;&nbsp;√累计</c:if>
								</div>

							</div>


						</div>
						<!-- 需要物流配送此div显示 end -->
				
				
				
				
				
			  <div class="field">
							<div class="label label-radio">
								<label>费率:</label>
							</div>

							<div class="select">
							${fee.name }&nbsp;
								
							</div>
						</div>
            

					<div class="buttons">
					
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