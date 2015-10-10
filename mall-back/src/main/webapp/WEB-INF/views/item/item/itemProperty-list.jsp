<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    </head>
<body>
<script type="text/javascript">

		//$(document).ready(function(){
		//	$("#saveItemProperties").click(function(){
			//	var propertyIds = $("select[name='propertyId']").serializeArray();
		//		var itemProperyNames = $("input[name='itemProperyName']").serializeArray();
			//	var uploadPropertyPics = $("input[name='uploadPropertyPic']").serializeArray();
			//	 ajaxFormSubmit($('#fm'),function(resp){
			//		 alert(resp.id);
			//	 }, null);
		//	});
		//});
		
		 function sub(){
    	var obj = document.getElementsByName("itemProperyName");
    	var picObj =  document.getElementsByName("uploadPropertyPic");
    	for(var i=0;i<obj.length;i++){
    		if(picObj[i].value=='' || picObj[i].value==null){
    			obj[i].value = obj[i].value+'_void';
    		}
    	}
    }
		
		//删除商品属性
		function deleteItemProperty(obj,propertyId){
			 $.ajax({  
 		        type: "POST",  
	    		    url : "deleteItemPropertyAjax",   
	    		    cache : false, 
	    		    dataType : "json",  
	    		    data: {propertyId:propertyId},
	    		    success : function(res){
	    		    	alert("删除成功！");
	    		    	$(obj).parent().parent().remove();
	        }});
			
		}
		
		function deleteItemTag(obj){
			$(obj).parent().parent().remove();
		}

		function addPropertyDiv(obj){
			var htmlAdd ='<div><table class="datalist fixwidth"><tr><td nowrap="nowrap"><select name="propertyId"><c:forEach items="${sysPropertyList}" var="item"><option value="${item.id}">${item.content}</option></c:forEach></select></td><td nowrap="nowrap">属性内容：<input name="itemProperyName" value=""/></td><td nowrap="nowrap">上传图片：<input type="file"  name="uploadPropertyPic" size="40"  readonly="readonly"/></td><td nowrap="nowrap"><a href="#this" onclick="deleteItemTag(this);">删除</a></td><tr></table></div>';
		 	$(obj).after(htmlAdd);
		}
    </script>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>填写商品参数</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" action="saveItemProperties" id="fm"  htmlEscape="true" acceptCharset="utf-8"  enctype="multipart/form-data"  onsubmit="return sub();">
        <div class="form">
        <input type="hidden" name="itemId" value="${itemId}"/>
            <div class="fields" id="itemParamDiv">
            <table class="datalist fixwidth">
                <tr>
                <input type="button"  class="common_btn"  name="showNewParam" onclick="addPropertyDiv(this)" value="添加属性"/>
               </tr>
             </table>
             <c:forEach items="${sysItemPropertyList}" var="propItem">
               		<div>
               		<table><tr>
               		<td><select name="propertyId"><c:forEach items="${sysPropertyList}" var="item"><option <c:if test="${item.id==propItem.propertyId}">selected="selected"</c:if>  value="${item.id}">${item.content}</option></c:forEach></select></td>
               		<td>属性内容：<input name="itemProperyName" value="${propItem.content}"/></td>
               		<td><c:if test="${propItem.sysFileImg!=null}"><input type="image" src="${propItem.sysFileImg.fileWebPath}"/></c:if></td>
               		<td><button type="button"  href="#this"  onclick="deleteItemProperty(this,'${propItem.id}')">删除</a></td>
               		<tr></table>
               		</div>
              </c:forEach>
             
             <div class="field">
                    <div class="highlight">
                        <input type="submit"  class="common_btn"  value="保存" />
               </div>
        </div>
        </div>
    </form:form>
</div>
</div>
</body>
</html>