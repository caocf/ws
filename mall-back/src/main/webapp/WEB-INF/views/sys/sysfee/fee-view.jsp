<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">

  
 
    </script>
    
</head>
<body>

<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>商品费率分类信息<h5>
    </div>
    <!-- end box / title -->
        <div class="form">
            <div class="fields">
            
       <div class="field">
                    <div class="label label-radio">
                        <label>	所属商户：</label>
                    </div>
                    <div class="input">
                    ${sysFee.storeName}&nbsp;
                    </div>
              </div>
            <div class="field">
                    <div class="label label-radio">
                        <label>费率分类名称:</label>
                    </div>
                    <div class="input">
                    ${sysFee.name}&nbsp;
                    </div>
              </div>
            <!--  
            <div class="field">
                    <div class="label label-radio">
                        <label>费率:</label>
                    </div>
                    <div class="input">
                    ${sysFee.fee}&nbsp;元
                    </div>
              </div>
              
            <div class="field">
                    <div class="label label-radio">
                        <label>是否有效:</label>
                    </div>
                    <div class="input">
                    ${sysFee.validName}&nbsp;
                    </div>
              </div>
              -->
                <div class="field">
                    <div class="label label-radio">
                        <label>描述:</label>
                    </div>
                     <div class="input">
                    ${sysFee.decrfee}&nbsp;
                    </div>
                </div>
            
            <div class="field">
                    <div class="label label-radio">
                        <label>是否同步:</label>
                    </div>
                     <div class="input">
                     ${sysFee.syncGyFlagName}&nbsp;
                     </div>
             </div>
             
          
                 
                <div class="field">
						<div class="label">
							<label for="effortdate" >同步时间:</label>
						</div>
						 <div class="input">
						<ct:time source="${sysFee.syncGyTime}"  sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss"/>&nbsp;
						</div>
				</div>
					<div class="buttons">
                        <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                    </div>
                    </div>
                </div>
            </div>
        </div>






</body>
</html>