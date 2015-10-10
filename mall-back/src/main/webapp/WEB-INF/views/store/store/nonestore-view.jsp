<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
<script type="text/javascript">
function audit(){
	
	window.location.href="../auditPage?id=${store.id}&auditStep=${auditStep}&status=${store.status+1}";
	
}

</script>
</head>
<body>
<br />
<div id="search-menu">
      <ul>
        
    </ul>
    <br style="clear: left" />
</div>
<div id="content">
.<!-- forms -->
<div class="box">
	<div  class="title">
				<h5>商户基本信息查看</h5>
			</div>
 
        <div class="form">
            <div class="fields">
                
                <div class="field">
                  <div class="label">
                        <label for="name" >名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                       ${store.name }&nbsp;
                    </div>
              </div>
             
               
                <div class="field">
                    <div class="label">
                        <label for="areaCode" >归属地市:</label>
                    </div>
                    <div class="input">
                     ${store.areaName }&nbsp;
                     </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="areaId" >行政编码:</label>
                    </div>
                    <div class="input">
                     ${store.areaId }&nbsp;
                    </div>
                </div>
                <!--  
                <div class="field">
                    <div class="label label-radio">
                        <label>是否移动签约:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="sort" value="1"/><label for="radio-1">是</label>
                            <input type="radio" id="radio-2" name="sort" checked="checked" value="0"/><label for="radio-2">否</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                 -->
                
                
               <c:if test="${store.shopClass == 3 }">
                <div class="field">
                    <div class="label">
                        <label for="sleepTime" >代理结算商户:</label>
                    </div>
                    <div class="input">&nbsp;&nbsp;
                  	   <c:forEach items="${storeList}" var="item">
                  	   <a href="../storeView/${item.id}" >${item.name }</a>&nbsp;&nbsp;&nbsp;&nbsp;
                  	   </c:forEach>
                    </div>
                </div>
                </c:if>
                
               
                  <div class="field">
                    <div class="label">
                        <label for="address" >地址:</label>
                    </div>
                    <div class="input">
                     ${store.address }&nbsp;
                    </div>
                </div>
               
                <div class="field">
                    <div class="label">
                        <label for="linkName" >联系人:</label>
                    </div>
                    <div class="input">
                     ${store.linkName }&nbsp;
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="linkPhone" >联系电话:</label>
                    </div>
                    <div class="input">
                      ${store.linkPhone }&nbsp;
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="createTime" >创建时间:</label>
                    </div>
                    <div class="input">
                     <ct:time source="${store.createTime}" tfmt="yyyy-MM-dd HH:mm:ss"/>
                    </div>
                </div>
                <c:if test="${!empty auditStepList}">
                <div class="field">
					<div class="label">
						<label for="remarkL" >审核信息:</label>
					</div>
					<div class="input">
						<c:forEach items="${auditStepList}" var="item">
	                         <label ><ct:time source="${item.updateTime}" tfmt="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;${item.remarkL}</label><br/>
                     	</c:forEach>
					</div>
				</div>
                </c:if>
                <div class="buttons">
                  <div class="highlight">
               	
                        <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                    </div>
                </div>
              
            </div>
        </div>

</div>
<!-- end forms -->
</div>





</body>
</html>