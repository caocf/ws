<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    <script type="text/javascript">
    
    $(document).ready(function(){
    	
    	
    	//选择商户
    	$("#storeName").click(function(){
    		selectStoreCallBack("",function(storeId,storeName,shopClass){
    			$("#storeId").val(storeId);
        		$("#storeName").val(storeName);
        		$("#shopClass").val(shopClass);
        		
        		$("#storeName").focus();
    	    	$("#storeName").blur();
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
    <div class="title">
        <h5>添加商户账号</h5>
    </div>
    <!-- end box / title -->
<form method="post" id="fm"  htmlEscape="true" acceptCharset="utf-8" class="required-validate" >
       
        <div class="form">
            <div class="fields">
            <div class="field">
						<div class="label">
							<label for="shopId" class="req">商户名称：</label>
						</div>
						<div class="input">
						    <input type="hidden" id="storeId" name="shopId"/>
							<input type="hidden" id="shopClass" name="shopClass" />
							<input type="text" id="storeName" maxlength="100" readonly="true" class="small required" />
							<span class="error" id="advice-required-storeName" style="display:none"></span>
						</div>
				</div>
                
                  <div class="field">
						<div class="label">
							<label for="shopId" class="req">账号：</label>
						</div>
						<div class="input">
							<input type="text" name="code" maxlength="100"  class="small required" />
							<span class="error" id="advice-required-code" style="display:none"></span>
						</div>
				</div>
                
						
			       <div class="field">
                    <div class="label">
                        <label for="pwd" class="req">用户密码：</label>
                    </div>
                    <div class="input">
                        <input id="pwd" name="pwd" type="text" class="small required " maxlength="20"/>
                    </div>
                </div>

             
					
						   <div class="buttons">
                    <div class="highlight">
                        <input id="submitButton" type="submit" name="submit.highlight"  value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                   <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
			     
            </div>
        </div>
        
 
				
        
    </form>
</div>
<!-- end forms -->
</div>
</body>
</html>