<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
 <script type="text/javascript">
 
 $().ready(function() {
	 $("#viewstorecode").click(function() {
			
			showDialog("查看模板", "../../downTemplate/storecode",function(doc){
				
			
			},{"Width":300,"Height":200,"ShowMessageRow":false,"ShowButtonRow":false});
			

		});
	 $("#viewcardcode").click(function() {
			
			showDialog("查看模板", "../../downTemplate/cardcode",function(doc){
				
			
			},{"Width":300,"Height":200,"ShowMessageRow":false,"ShowButtonRow":false});
			

		});
	 
 });
 
 </script>
    
</head>
<body>
<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5> 导入码<h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="code" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
     <input type="hidden" name="storeId" value="${storeId }" />
     <input type="hidden" name="itemId" value="${itemId }" />
        <div class="form">
              <div class="fields">
              <div class="field">
                    <div class="label">
                        <label for="uploadfile" >商户:</label>
                    </div>
                    <div class="input input-file">
                    ${store.name }
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="uploadfile" >商品:</label>
                    </div>
                    <div class="input input-file">
                    ${itemSale.name }
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="uploadfile" >类型:</label>
                    </div>
                    <div class="input input-file">
                    <c:if test="${codetype=='storecode' }">商户码</c:if>
                    <c:if test="${codetype=='cardcode' }">卡密</c:if>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="uploadfile" class="req">上传文件:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploadfile"  name="uploadfile"  size="40"  class="required validate-file-txt"  />
                        <span class="tip">导入的文件格式为“.txt”。
                        <c:if test="${codetype=='cardcode' }">
                        	格式为: 卡号,密码
                        </c:if>
                        </span>
                        <br />
                        <c:if test="${codetype=='storecode' }">
                        <a id="viewstorecode" href="#this">查看模板</a>
                        </c:if>
                         <c:if test="${codetype=='cardcode' }">
                        <a id="viewcardcode" href="#this">查看模板</a>
                        </c:if>
                        <form:hidden path="code"/>
                        <span class="error" id="advice-validate-file-uploadfile" style="display:none"></span>
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
      </div>
    </form:form>
      </div>
</div>
<!-- end forms -->







</body>
</html>