<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>

<html>
<head>
    <head>
    <ht:head/>
<script src="<spring:url value="/static/js/uploadify/jquery.uploadify.js"/>" type="text/javascript" charset="utf-8"></script>
<link href="<spring:url value="/static/js/uploadify/uploadify.css"/>" rel="stylesheet" type="text/css" />
    
   <script type="text/javascript">

    
  
    $(document).ready(function(){
    	//选择商品分类
    	$("#uploadPropertyPic").uploadify({
			height        : 30,
			removeCompleted:true,
			swf           : G_CTX_ROOT + '/static/js/uploadify/uploadify.swf',
			width         : 120,
			uploader:  "${ctx}/item/vitual/imgUpload/${itemId};jsessionid=<%=request.getRequestedSessionId()%>",
			onUploadSuccess:function(file, data, response){
					var json =jQuery.parseJSON(data);
					//window.location.reload();
					$("#imgBox").append('<div class="input"><img alt="" src="'+json.fileWebPath+'"> <a href="javascript:void(0);" onclick="delImg('+json.id+',this)">删除</a> <a href="javascript:void(0);" onclick="coverImg('+json.id+')">设为封面</a></div>');
					//window.location.reload();
			}
		});
    	$(".form .input .file").hide();

	});		   
    
    function delImg(fileId,el){
    	if(confirm("是否将此图片删除?")){
		$.ajax({
			type : "POST",
			url : "${ctx}/item/vitual/deleteItemImgAjax/"+fileId,
			cache : false,
			dataType : "json",
			success : function(r) {
				simpleAlert(r.message,function() {
					//window.location.reload();
					$(el).parent().remove();
				});
			}
		});
    	}
		
    }
    function coverImg(fileId){
    	if(confirm("是否将此图片设为封面?")){
		$.ajax({
			type : "POST",
			url : "${ctx}/item/vitual/coverItemImgAjax/"+fileId+"/${itemId}",
			cache : false,
			dataType : "json",
			success : function(r) {
				simpleAlert(r.message,function() {
					window.location.reload();
				});
			}
		});
    	}
		
    }
    
    
    </script>
 
 
    </head>
<body>
<br/>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>商品图片管理</h5>
    </div>
    <!-- end box / title -->
      <div class="form">
        <div class="fields">
        	
            
            <div class="field">
                    <div class="label">
                        <label for="name">上传图片</label>
                    </div>
                    <div class="input">
                    	<input type="file"  id="uploadPropertyPic" name="uploadPropertyPic" style="display:none"  size="0" />
                   </div>
                   <span style="color:blue;">建议图片大小为 800*800 或 600*600</span>
            </div>
        	  <div class="field">
                <div class="label">
                    <label for="name" class="req">封面图：</label>
                    
                </div>
                <div class="input">
        	 <c:forEach items="${coverFileLink}" var="item" varStatus="index">
        	  			<ht:image webpath="${item.fileName  }" id="${itemId}"/>
                    </div>
             
             </c:forEach>&nbsp;
        	 </div>
        	<div class="field" id="imgBox">
                      <div class="label"> 
                      <label for="name" class="req">商品图：</label>
                      </div>
                    <c:forEach items="${fileLink}" var="item" varStatus="index">
                    <div class="input">
                    	<ht:image webpath="${item.fileName  }" id="${itemId}"/>
                         <a href="javascript:void(0);" onclick="delImg('${item.id}',this)">删除</a> <a href="javascript:void(0);" onclick="coverImg('${item.id}')">设为封面</a>  
                    </div>
                    </c:forEach>&nbsp;
                </div>
                    
                <div class="buttons">
                   
                    <a href="${ctx}/item/vitual/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
</div>
<!-- end forms -->
</div>
</body>
</html>