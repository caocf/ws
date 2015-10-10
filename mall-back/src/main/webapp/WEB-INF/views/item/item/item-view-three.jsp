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

   $().ready(function() {

		$("#audit_btn").click(function() {
			var id ='${sale.id}';
			showDialog("商品审核", "../../preAudit.do?id="+id,function(doc){
				commonSubmit(doc.getElementById('fm'));
				//doc.getElementById('fm').submit();
				//doc.submited=true;
				simpleAlert('操作成功',function() {
					window.location.reload();
				});
			},{"Width":600,"Height":270,"ShowMessageRow":false});
			

		});

	});
    
    </script>
 
 
    </head>
<body>
<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn text="商品基本信息" url="/item/item/view/${itemId}" />
        <ht:menu-btn text="商品属性参数" url="/item/item/propertyView/${itemId}" />
        <ht:menu-btn text="商品图片" />
    </ul>
    <br style="clear: left" />
</div>

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>查看商品图片</h5>
    </div>
    <!-- end box / title -->
      <div class="form">
        <div class="fields">
        	
        	  <div class="field">
                <div class="label">
                    <label for="name" >封面图：</label>
                    
                </div>
                <div class="input">
        	 <c:forEach items="${coverFileLink}" var="item" varStatus="index">
        	  
                        <img alt="" src="${item.fileWebPath }"> 
                    </div>
             
             </c:forEach>&nbsp;
        	 </div>
        	<div class="field">
                      <div class="label"> 
                      <label for="name" >商品图：</label>
                      </div>
                    <c:forEach items="${fileLink}" var="item" varStatus="index">
                    <div class="input">
                        <img alt="" src="${item.fileWebPath }"> 
                    </div>
                    </c:forEach>
                </div>
                    
              <div class="buttons">
                <c:if test="${sale.status == 2 }">
                <ct:display model="audit_list" btn="audit_btn"> 
               	 <input type="button" class="common_btn" id="audit_btn" value="审核" />
                </ct:display>
                </c:if>
                  <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
        </div>
</div>
<!-- end forms -->
</div>
</body>
</html>