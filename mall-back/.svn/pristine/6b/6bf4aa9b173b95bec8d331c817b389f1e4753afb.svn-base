<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    <script type="text/javascript">
    
	$().ready(function() {

		$("#audit_btn").click(function() {
			var id ='${sale.id}';
			showDialog("商品审核", "../preAudit.do?id="+id,function(doc){
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
        <ht:menu-btn text="商品属性参数查看" url=""/>
        <ht:menu-btn text="商品图片查看" url="/item/item/imgView/upd/${sale.id}"/>
    </ul>
    <br style="clear: left" />
</div>

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>修改商品属性参数</h5>
    </div>
    <!-- end box / title -->
<form:form  id="fm" commandName="sale" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data" onsubmit="sub()">
      <div class="form">
        <div class="fields">
        	
        	<div class="field">
                    <div class="label">
                        <label for="name">商品参数</label>
                    </div>
                    <div class="input">
                    		&nbsp;
                    </div>
            </div>
        	 <c:forEach items="${typeItemParamList}" var="item" varStatus="index">
        	 
        	 <c:if test="${item.paramType==1}">
        	 <div class="field">
        	 	<div class="label">
        	 		 <label for="name"  > 
        	 		  ${item.paramKey}：&nbsp;</label>
        	 	</div>
        	 	
        	 	 <div class="radios">
        	 	 	<div class="radio">
        	 		<c:forEach items="${item.paramValueList}" var="str" varStatus="index1">
        	 		
        	 				<c:if test="${item.userParamValue==str}">${str}</c:if>
        	 				
        	 		</c:forEach>
        	 		</div>
                </div>
        	 </div>
        	 </c:if>    	
        	 
        	 <c:if test="${item.paramType==2}">
        	 <div class="field">
        	 	<div class="label">
        	 		 <label for="name"  > 
        	 		  ${item.paramKey}：&nbsp;</label>
        	 	</div>
        	 	
        	 	 <div class="checkboxes">
        	 	 	<div class="checkboxe">
        	 		<c:forEach items="${item.paramValueList}" var="str" varStatus="index1">
        	 			
        	 			 <c:if test="${fn:contains(item.userParamValue,str)}"> ${str } ,&nbsp;</c:if> 
        	 			
        	 		</c:forEach>
        	 		</div>
                </div>
        	 </div>
        	 </c:if>    	
        	 
        	 <c:if test="${item.paramType==3}">
        	 <div class="field">
        	 	<div class="label">
        	 		 <label for="name"  > 
        	 		  ${item.paramKey}：&nbsp;</label>
        	 	</div>
        	 	
        	 	<div class="input">
                     ${item.userParamValue}&nbsp;
                </div>
        	 </div>
        	 </c:if>    	
        	 
        	 </c:forEach>
        	
        	<div class="field">
                    <div class="label">
                        <label for="name">商品属性</label>
                    </div>
                    <div class="input">
                    		 &nbsp;
                    </div>
            </div>
            
            <div class="field">
                    <div class="label">
                        <label for="name">&nbsp;</label>
                    </div>
                    <div class="input">
                
                <c:forEach items="${itemProperty}" var="itemP" varStatus="index">
                   <div class="dl" id="fath" >
                    <div class="dt >${itemP.propertyName}：</div>
                    <div class="dd">
                        ${itemP.content}
                        <div>
                        <c:if test="${! empty itemP.filePath}">
                        <img src="${itemP.filePath}">
                        </c:if>
                        </div>
                    </div>
                    
                </div>
                </c:forEach>
                <div class="dl" id="divAdd">
                </div>
                    		 
                    		 
                    </div>
            </div>
             
               <div class="buttons">
               
                  <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>