<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>

<script type="text/javascript">

function selectArea(num){
//alert(document.getElementById("area").style.value);
	if(!num){
		return;
	}
	if(document.getElementById("countrySpan")){
		document.getElementById("countrySpan").innerHTML = '';
	}
	if(document.getElementById("provSpan")){
		document.getElementById("provSpan").innerHTML = '';
	}
	if(document.getElementById("citySpan")){
		document.getElementById("citySpan").innerHTML = '';
	}
	if(num == 1){
		document.getElementById("prov").style.display="none";
		document.getElementById("city").style.display="none";
		document.getElementById("provSpanId").innerHTML = '';
		document.getElementById("citySpanId").innerHTML = '';
		document.getElementById("countrySpanId").innerHTML = '全国';
	}else{
		document.getElementById("prov").style.display="";
		document.getElementById("provSpanId").innerHTML = '省';
		if(num == 2){
			document.getElementById("city").style.display="none";
			document.getElementById("citySpanId").innerHTML = '';
			document.getElementById("countrySpanId").innerHTML = '';
		}else{
			document.getElementById("city").style.display="";
			document.getElementById("citySpanId").innerHTML = '市';
			document.getElementById("countrySpanId").innerHTML = '';
		}
	}
}

function ajaxChangeParentUnit(value){
	selectArea(value);
var unitTypeArray = document.getElementsByName("unitType");
var unitId = document.getElementById("unitId").value;
var unitType = "";
for(var i=0;i<unitTypeArray.length;i++){
	if(unitTypeArray[i].checked){
		//alert(unitTypeArray[i].value);
		unitType = unitTypeArray[i].value;
	}
}

	
$.ajax({
   type: "POST",
   url: "getPraentUnitList.do",
   cache : false, 
   dataType : "json",
   data: {unitId:unitId,unitType:unitType},
   success: function(msg){
  
     makeSelectMenu(msg);
   }
}); 

}

function makeSelectMenu(value){
var obj = document.getElementById("parentUnitId");
var ln = obj.options.length;
while(ln--){
	obj.remove(ln);		
}
var option = new Option("=请选择=",""); 
obj.options.add(option);
//var value=response.split("#");
for(var i=0;i<value.length;i++){
	var option = new Option(value[i].name,value[i].id); 
	obj.options.add(option);
	if(value[i].id== ${unit.parentUnitId}){
    	obj.options[i+1].selected = true;
    }
}
}

function changeProvAjax(){
var prov = document.getElementById("prov").value;
if(prov!=""){
	$.ajax({
   type: "POST",
   url: "getCityList.do",
   cache : false, 
   dataType : "json",
   data: {parentRegonId:prov},
   success: function(msg){
    makeCityMenu(msg);
   }
}); 
}
	
}
function makeCityMenu(value){
var obj = document.getElementById("city");
var ln = obj.options.length;
while(ln--){
	obj.remove(ln);		
}
var option = new Option("=请选择=",""); 
obj.options.add(option);
//var value=response.split("#");
for(var i=0;i<value.length;i++){
	if(value[i]==""){
		continue;
	}	
	var option = new Option(value[i].regionName,value[i].id); 
	obj.options.add(option);
	if(value[i].regionCode == '${unit.areaCode}'){
    	obj.options[i+1].selected = true;
    }
}
}

function sub(){
	var unitTypeArray = document.getElementsByName("unitType");
	var unitType = "";
	for(var i=0;i<unitTypeArray.length;i++){
		if(unitTypeArray[i].checked){
			unitType = unitTypeArray[i].value;
		}
	}
//	if(unitType == ""){
//		alert("请选择分类");
//		return false;
//	}
	var parentUnitId = document.getElementById("parentUnitId").value;
//	if(parentUnitId == ""){
////		alert("请选择所属单位");
//		return false;
//	}
	return true;
}

</script>


</head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>修改单位</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="unit" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
	<input type="hidden" id="unitId" name="unitId" value="${unitId }" />
	 <form:hidden path="id" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${unit.id}</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="name" class="req">名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="name" cssClass="small required" maxlength="100"/>
                        
                    </div>
                </div>
              
                <div class="field">
                    <div class="label label-radio">
                        <label for="unitType" class="req">分类:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        <c:if test="${unitId ==0 }">
                            <input type="radio" id="radio-1" name="unitType" value="1" ${unit.unitType==1?"checked":"" } onclick="ajaxChangeParentUnit(1);"  class="validate-one-required"/><label for="radio-1">电商基地单位</label>
                       </c:if>
                       <c:if test="${unitType<=1 }">
                            <input type="radio" id="radio-2" name="unitType" value="2" ${unit.unitType==2?"checked":"" } onclick="ajaxChangeParentUnit(2);"  /><label for="radio-2">省单位</label>
                       </c:if>
                       <c:if test="${unitType<=2 }">
                            <input type="radio" id="radio-2" name="unitType" value="3" ${unit.unitType==3?"checked":"" } onclick="ajaxChangeParentUnit(3);"  /><label for="radio-2">地市</label>
                        </div>
                        </c:if>
                       
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="name" class="req">所属单位：</label>
                    </div>
                    <div class="input">
                         <select id="parentUnitId" name="parentUnitId">
                       <option value="">请选择</option>
                       
                       </select>
                    </div>
                </div>
              

                <div  class="field" >
                    <div class="label">
                        <label for="areaCode" class="req">选择地区：</label>
                    </div>
                    <div class="input">
                      
	                     <form:hidden path="provId"/>
	                       <select id="prov" name="prov" <c:if test="${unit.unitType eq 1 }">style="display: none;"</c:if> onchange="changeProvAjax();" >
	                       <option value="">请选择</option>
	                        <c:forEach items="${regonList}" var="item">
	                           <option value="${ item.regionCode}">${item.regionName }</option>
	                          </c:forEach>
	                       </select>
	                       
	                        <c:if test="${unit.unitType eq 2 }">
		                      	 <span id="provSpan" >省</span> 
		                       </c:if>
		                       
	                  		<span id="provSpanId"></span> 
	                  		
		                       	<select id="city" name="city" <c:if test="${unit.unitType eq 2 || unit.unitType eq 1}">style="display: none;"</c:if> >
		                        <option value="">请选择</option>
		                       </select>
		                       
		                       <c:if test="${unit.unitType eq 3 }">
		                      	 <span id="citySpan" >市</span> 
		                       </c:if>
	                    
	                      <span id="citySpanId"></span> 
	                      	
	                     
	                 
	                   <c:if test="${unit.unitType eq 1 }">
	              			<span id="countrySpan">全国</span>
	                   </c:if>
	                   
	                   <span id="countrySpanId"></span>
                     
                    </div>
                    
                </div>
				
                 <div class="field">
                    <div class="label">
                        <label for="remark" >备注：</label>
                    </div>
                    <div class="input">
                    <form:textarea path="remark"/>
                    </div>
                </div>


                

                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/unit/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


<script type="text/javascript">
ajaxChangeParentUnit();
document.getElementById("prov").value="${regonCode}"
changeProvAjax();
    <!--
    ajaxFormSubmit('#fm');

    //-->
</script>



</body>
</html>