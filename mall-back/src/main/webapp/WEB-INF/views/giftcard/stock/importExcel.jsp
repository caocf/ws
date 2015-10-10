<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!doctype html>
<html>
<head>
    <ht:head/>
    
     <script type="text/javascript">
    		 function sub() {
		    	$("#filepath").val($("#uploaditemfile").val());
		    }
    		 $(function(){
    			 	//sumDiv.style.display = 'block';
					//fileDiv.style.display = 'none';
    			 $("#selectInout").change(function(){
    				 var inoutFlag = $("#selectInout").val();
 					if(inoutFlag == '0'){
 						sumDiv.style.display = 'block';
 						fileDiv.style.display = 'none';
 					}else{
 						sumDiv.style.display = 'none';
 						fileDiv.style.display = 'block';
 					}
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
    	<h5>
    	<c:if test="${ '1' eq flag }">部分入库</c:if>
    	<c:if test="${ '2' eq flag }">部分出库</c:if>
    	</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="importExcel"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();" enctype="multipart/form-data">
        <div class="form">
            <div class="fields">
           			 <div class="field" <c:if test="${ '1' eq flag  }">style="display:none"</c:if>>
           			 	<div class="label">
	                        <label >出库方式:</label>
	                    </div>
	                    <div class="input">
	            			<select name="inoutFlag" id="selectInout">
							<option value="0">数量</option>
							<option value="1">文件</option>
					 		</select>
				 		</div>
				 	 </div>
				 	 <div class="field" id="sumDiv" <c:if test="${ '1' eq flag  }">style="display:none"</c:if>>
           			 	<div class="label">
	                        <label >数量:</label>
	                    </div>
	                    <div class="input">
	            		<input type="text" name="cardSum" class="validate-integer small  required" maxlength="8"/>
				 		</div>
				 	 </div>
					 <div class="field" id="fileDiv" <c:if test="${ '2' eq flag  }">style="display:none"</c:if>>
	                    <div class="label">
	                        <label for="uploaditemfile">文件:</label>
	                    </div>
	                    <div class="input input-file">
	                        <input type="file" id="uploaditemfile" name="uploaditemfile" size="40" class="required validate-file-xls-xlsx" />
	                        <span class="tip">excel格式</span>
	                        <span class="tip"><a href="${ctx }/static/resources/cardModel.xlsx">下载模版</a></span>
	                        <span class="error" id="advice-validate-file-uploaditemfile" style="display:none"></span>
	                        <span class="error" id="advice-required-uploaditemfile" style="display:none"></span>
	                        <input type="hidden" id="filepath" name="filepath">
	                    </div>
	                </div>
	                    <input type="hidden" name="flag" value="${ flag }">
	                    <input type="hidden" name="batchNo" value="${ batchNo }">
	                    <div class="buttons">
	                    	<button type="submit" class="common_btn">提交</button>
	              		</div>
   			</div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>