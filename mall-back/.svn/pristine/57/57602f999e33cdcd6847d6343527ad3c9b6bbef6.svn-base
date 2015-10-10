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
        <h5>商户信息<h5>
    </div>
    <!-- end box / title -->
        <div class="form">
            <div class="fields">
            <div class="field">
                    <div class="label label-radio">
                        <label>商户名称:</label>
                    </div>
                    <div class="input">
                    ${shopSettings.name}&nbsp;
                    </div>
              </div>
      
            <div class="field">
                    <div class="label label-radio">
                        <label>商户招牌图片路径:</label>
                    </div>
                    <div class="input">
                    ${shopSettings.shopPicUrl}&nbsp;
                    </div>
              </div>
            <div class="field">
                    <div class="label label-radio">
                        <label>最后更新时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${shopSettings.operateEndTime}" />&nbsp;
                    </div>
              </div>
              
            <div class="field">
                    <div class="label label-radio">
                        <label>首页文字介绍:</label>
                    </div>
                    <div class="input">
                    ${shopSettings.homePageWord}&nbsp;
                    </div>
              </div>
              
            <div class="field">
                    <div class="label label-radio">
                        <label>业务门店:</label>
                    </div>
                    <div class="input">
                    ${shopSettings.shopClassName}&nbsp;
                    </div>
              </div>
             
            
            <div class="field">
                    <div class="label label-radio">
                        <label>发布状态:</label>
                    </div>
                     <div class="input">
                     ${shopSettings.pubTagName}&nbsp;
                     </div>
             </div>
            <c:if test="${shopSettings.pubTag eq 1 || shopSettings.pubTag eq 2}">
            <div class="field">
                    <div class="label label-radio">
                        <label>审核意见:</label>
                    </div>
                     <div class="input">
                     ${shopSettings.auditAdvice}&nbsp;
                     </div>
             </div>
             </c:if>
          
                 
             
				
                        <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                    </div>
                </div>
            </div>
        </div>






</body>
</html>