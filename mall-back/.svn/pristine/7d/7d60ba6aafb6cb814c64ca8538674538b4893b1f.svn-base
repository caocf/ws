<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    <script type="text/javascript">
    
    $(document).ready(function(){
    	
    	$("#add").click(function() { 
    		var html=      "<div class='dl' ><br />"+
            "<div class='dt class='req'>"+
            "  <select name='propertyId'>"+
             "   <c:forEach items='${sysPropertyList}' var='item' varStatus='index'>"+
              "   <option value='${item.id}'>${item.content}</option>"+
                " </c:forEach>"+
               " </select>："+
           "</div>"+
           "<div class='dd'>"+
           "  <input type='hidden'  name='flag' id='flag' value='0'/>"+
           "    <input type='text'  name='itemProperyName' maxlength='100' class='small required'/>"+
           "   <input class=\"file\" style=\"display: inline; width: 250px;\"></input>"+
          // "    <input type='file'  "+
           //"    class=\"validation-passed\" style=\"position: relative; height: 28px; width: 250px; display: inline; cursor: pointer; opacity: 0; margin-left: -142px;\" "+
         //  "     name='uploadPropertyPic' size='40' />"+
           " <a class=\"ui-input-file\"><input type=\"file\" size=\"40\" re=\"\" name=\"uploadPropertyPic\" style=\"position: relative; height: 28px; width: 250px; display: inline; cursor: pointer; opacity: 0; margin-left: -142px;\" ></input></a>"+
           "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
           "      <a href='javascript:void(0);' id='del' onclick='delDiv(this)' title='删除'><font color='red'>删除</font></a>"+
           "    <span class='error' id='advice-required-itemProperyName' style='display:none'></span>"+
          " </div>"
      " </div>"; 
    		 $('#divAdd').append(html); 
    	});
    	
    	$("#addpara").click(function() { 
    		var html= "<div class='dl'><br /><span style='float:left'>参数名称：</span><input type='text' name='customparakey'  maxlength='10'/><span style='float:left'>参数值:</span><input type='text' name='customparavalue'  maxlength='10'/>"+
    		"<a href='javascript:void(0);' id='del' onclick='delpara(this)' title='删除'><font color='red'>删除</font></a></div>";
    		 $('#divAddpara').append(html); 
    	});

    	$(".item_del").click(function() { 
    		if(confirm("是否将此属性删除?")){
    		var id = $(this).attr("id");
    		$.ajax({
    			type : "POST",
    			url : "${ctx}/item/item/deleteItemPropertyAjax/"+id,
    			cache : false,
    			dataType : "json",
    			success : function(r) {
    				simpleAlert(r.message,function() {
    					window.location.reload();
    				});
    			}
    		});
    		}
    	});
    	
	});	
    
    function sub(){
    	var obj = document.getElementsByName("flag");
    	var picObj =  document.getElementsByName("uploadPropertyPic");
    	for(var i=0;i<obj.length;i++){
    		if(picObj[i].value !='' && picObj[i].value!=null){
    			obj[i].value = '1';
    		}
    	}
    }
    function delDiv(t){ 
    	// del为删除input的id	
    	$(t).parent().parent().remove();
    }
    function delpara(t){ 
    	$(t).parent().remove();
    }
     
    </script>
 
    </head>
<body>
<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn text="商品基本信息修改" url="/item/item/preEdit/${sale.id}" />
        <ht:menu-btn text="商品属性参数修改" />
        <ht:menu-btn text="商品图片修改" url="/item/item/img/upd/${sale.id}"/>
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
        	 		 <label for="name"  <c:if test="${item.requiredFlag==1}">class="req"</c:if>> 
        	 		  ${item.paramKey}：&nbsp;</label>
        	 	</div>
        	 	
        	 	 <div class="radios">
        	 	 	<div class="radio">
        	 		<c:forEach items="${item.paramValueList}" var="str" varStatus="index1">
        	 			<input type="radio" id="radio-${index1.count }" name="itemParam_${item.id}" 
        	 				<c:if test="${item.requiredFlag==1}">class="validate-one-required"</c:if>  
        	 				<c:if test="${item.userParamValue==str}">checked="checked"</c:if>
        	 				value="${str}"/> <label for="radio-${index1.count}">${str}</label> 
        	 		</c:forEach>
        	 		</div>
                </div>
        	 </div>
        	 </c:if>    	
        	 
        	 <c:if test="${item.paramType==2}">
        	 <div class="field">
        	 	<div class="label">
        	 		 <label for="name"  <c:if test="${item.requiredFlag==1}">class="req"</c:if>> 
        	 		  ${item.paramKey}：&nbsp;</label>
        	 	</div>
        	 	
        	 	 <div class="checkboxes">
        	 	 	<div class="checkboxe">
        	 		<c:forEach items="${item.paramValueList}" var="str" varStatus="index1">
        	 			 <input type="checkbox" name="itemParam_${item.id}"  value="${str}" 
        	 			 <c:if test="${fn:contains(item.userParamValue,str)}"> checked="checked" </c:if> 
        	 			 <c:if test="${item.requiredFlag==1}">class="validate-one-required"</c:if> />
        	 			 <label for="checkbox_${index1.count}">${str}</label>
        	 		</c:forEach>
        	 		</div>
                </div>
        	 </div>
        	 </c:if>    	
        	 
        	 <c:if test="${item.paramType==3}">
        	 <div class="field">
        	 	<div class="label">
        	 		 <label for="name"  <c:if test="${item.requiredFlag==1}">class="req"</c:if>> 
        	 		  ${item.paramKey}：&nbsp;</label>
        	 	</div>
        	 	
        	 	<div class="input">
                     <input name="itemParam_${item.id}" maxlength="100" <c:if test="${item.requiredFlag==1}">class="small required"</c:if> value="${item.userParamValue}" />
					 <span class="error" id="advice-required-itemParam_${item.id}" style="display:none"></span>
                </div>
        	 </div>
        	 </c:if>    	
        	 
        	 </c:forEach>
        	 
        	 <div class="field">
                    <div class="label">
                        <label for="name">自定义参数</label>
                    </div>
                    <div class="input">
                    		<a href="javascript:void(0);" id="addpara">增加一个参数</a> &nbsp;
                    </div>
            </div>
        	 <div class="field">
             	<div class="label">
                        <label for="name">&nbsp;</label>
                </div>
                <div class="input">
	            	<div class="dl" id="divAddpara">
	            	<c:forEach items="${paralist }" var="item">
	            	<div class='dl'><br />
	            	<span style="float:left">参数名称：</span><input type='text' name='customparakey' value=${item.paramKey } maxlength='10'/>
	            	<span style="float:left">参数值:</span><input type='text' name='customparavalue' value=${item.paramValue } maxlength='10'/>
	            	<a href='javascript:void(0);' id='del' onclick='delpara(this)' title='删除'><font color='red'>删除</font></a>
	            	
	            	</div>
	            	
	            	</c:forEach>
	                </div>
                </div>
            </div>
        	<div class="field">
                    <div class="label">
                        <label for="name">商品属性</label>
                    </div>
                    <div class="input">
                    		 <a href="javascript:void(0);" id="add">增加一个属性</a> &nbsp;
                    </div>
            </div>
            
            <div class="field">
                    <div class="label">
                        <label for="name">&nbsp;</label>
                    </div>
                    <div class="input">
                
                <c:forEach items="${itemProperty}" var="itemP" varStatus="index">
                   <div class="dl" id="fath" >
                    <div class="dt class="req">${itemP.propertyName}：</div>
                    <div class="dd">
                        ${itemP.content}
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         <a href="javascript:void(0);" id="${itemP.id }" class="item_del" title="删除" ><font color="red">删除</font></a>
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
                    <div class="highlight">
                        <input type="submit" name="submit.highlight"  value="提交" />
                    </div>
                    <a href="${ctx}/item/item/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>