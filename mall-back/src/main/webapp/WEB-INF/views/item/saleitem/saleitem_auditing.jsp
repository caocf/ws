<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
<script type="text/javascript">

$().ready(function() {
    $(':checkbox[name="buyLimit"]').click(function(){
    	 if($("#checkbox-1").attr("checked")){
    		 $("#userLimit").show(); 
    	 }else{
    		 $("#userLimit").hide(); 
    	 }
    	 if($("#checkbox-2").attr("checked")){
    		 $("#areaLimit").show(); 
    	 }else{
    		 $("#areaLimit").hide(); 
    	 }
    	 if($("#checkbox-3").attr("checked")){
    		 $("#numLimit").show(); 
    	 }else{
    		 $("#numLimit").hide(); 
    	 }
    });

	//购买区域
	selectRegion("#areaLimitName","areaLimitCode","areaLimitName",0,{index:2});
});
</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>发布商品审批<h5>
    </div>
    <!-- end box / title -->
    <form id="fm"  action="${ctx}/item/saleitem/audit/doAudit/${step.bsId }" method="get" accept-charset="utf-8">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label label-checkbox">
                        <label>购买用户限制:</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox">
                            <input type="checkbox" id="checkbox-1" name="buyLimit" value="0" class="validate-one-required" <c:if test="${fn:contains(itemSale.buyLimit,'0')}"> checked="checked" </c:if>/><label for="checkbox-1">会员级别限制</label> 
							<input type="checkbox" id="checkbox-2" name="buyLimit" value="1" class="validate-one-required" <c:if test="${fn:contains(itemSale.buyLimit,'1')}"> checked="checked" </c:if>/><label for="checkbox-2">购买用户地市限制</label>
							<input type="checkbox" id="checkbox-3" name="buyLimit" value="2" class="validate-one-required" <c:if test="${fn:contains(itemSale.buyLimit,'2')}"> checked="checked" </c:if>/><label for="checkbox-3">单个用户购买数量</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-buyLimit" style="display:none"></span>
                    </div>
                </div>
						
						
				<div class="field" id="userLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'0')}"> style="display: none;" </c:if>>
                    <div class="label label-checkbox">
                        <label>会员级别限制:</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox">
                            <input type="checkbox" id="checkbox-4" name="userLimitCode" value="0" <c:if test="${fn:contains(itemSale.userLimitCode,'0')}"> checked="checked" </c:if> class="validate-one-required" /><label for="checkbox-4">金会员</label> 
							<input type="checkbox" id="checkbox-5" name="userLimitCode" value="1" <c:if test="${fn:contains(itemSale.userLimitCode,'1')}"> checked="checked" </c:if>  class="validate-one-required"/><label for="checkbox-5">铜会员</label>
							<input type="checkbox" id="checkbox-6" name="userLimitCode" value="2" <c:if test="${fn:contains(itemSale.userLimitCode,'2')}"> checked="checked" </c:if>  class="validate-one-required"/><label for="checkbox-6">银会员</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-userLimitCode" style="display:none"></span>
                    </div>
                </div>
						
						
						<div class="field" id="areaLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'1')}"> style="display: none;" </c:if>>
							<div class="label">
								<label for="areaLimitName" class="req">购买用户地市限制:</label>
							</div>
							<div class="input">
								<input id="areaLimitName" name="areaLimitName" class=" required" readonly="readonly" type="text" value="${itemSale.areaLimitName }" maxlength="100"/>
								<input id="areaLimitCode" name="areaLimitCode" type="hidden" value="${itemSale.areaLimitCode }"/>
								<span class="error" id="advice-required-areaLimitName"
									style="display:none"></span>
							</div>
						</div>
						
						<div class="field" id="numLimit" <c:if test="${!fn:contains(itemSale.buyLimit,'2')}"> style="display: none;" </c:if>>
							<div class="label">
								<label for="userPerBuyNum" class="req">单个用户购买数量:</label>
							</div>
							<div class="input">
								<input id="userPerBuyNum" name="userPerBuyNum" class=" required validate-number" type="text" value="${itemSale.userPerBuyNum }" maxlength="8"/>
								<span class="error" id="advice-required-userPerBuyNum"
									style="display:none"></span>
							</div>
						</div>
					<div class="field">
                    <div class="label label-radio">
                        <label>选择费率:</label>
                    </div>
                    
                    <div class="select">
                    <select name="feeType">
                    <c:forEach items="${feeList }" var="item">
					      <option value="${item.id }">${item.name }</option>              
                    </c:forEach>
                    
                    </select>
                    </div>
                </div>	
              
                <div class="field">
                    <div class="label label-radio">
                        <label>审核:</label>
                    </div>
                    
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="status" checked="checked"  value="1"/><label for="radio-1">通过</label>
                            <input type="radio" id="radio-2" name="status" value="2"/><label for="radio-2">拒绝</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="advice">审核意见：</label>
                    </div>
                    <div class="input">
                     <textarea id="remark" name="remark" class="max-length-200" rows="8" cols="50"></textarea>
                    </div>
                </div>
                <div class="buttons">
							<div class="highlight">
								<input type="submit" name="submit.highlight" value="提交" />
							</div>
							<input type="button" class="common_btn" onclick="history.back();" value="返回" />
						</div>
            </div>
        </div>
    </form>
</div>
<!-- end forms -->
<script type="text/javascript">

$(function() {
    var form = $('#fm');
    var validation = new Validation(form,{});
    ajaxFormSubmit(form, function(resp) {
        if (resp.success) {
        		
        		simpleAlert('操作成功',function() {
        			window.parent.location.reload();
    			});
        	
        
        }else {
        	simpleWarn(resp.error)
        	};

    }, null);

});

</script>
</div>
</body>
</html>