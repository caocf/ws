<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!doctype html>
<html>
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
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="${ctx}/gift/required/imgAdd/${id }"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate"  enctype="multipart/form-data">
        <div class="form">
            <div class="fields">
            		<div style="height:20px;"></div>
            		<div>
            				<ht:image webpath="${fileWebPath   }" type="giftrequired" />
            		</div>
            
					 <div class="field">
	                    <div class="field" >
                    <div class="label">
                    	<label for="uploadfile" class="req">选择图片：</label>
                    </div>
                    <div class="input input-file">
                        <input type="file"  name="uploadfile" size="40" class="required validate-file-jpg-png-gif"  />
                        <span class="error" id="advice-required-uploadfile" style="display:none"></span>
                        <span class="error" id="advice-validate-file-uploadfile" style="display:none"></span>
                    </div>
              		  </div>
	                    <div class="buttons">
	                    	<input type="submit" name="submit.highlight" value="提交" />
	              		</div>
	                </div>
   			</div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>