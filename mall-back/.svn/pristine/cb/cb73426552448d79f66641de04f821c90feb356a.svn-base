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
	if(num == 1){
		document.getElementById("area").style.display="none";
	
	}else{
		document.getElementById("area").style.display="";
		if(num == 2){
			document.getElementById("city").style.display="none";
		}else{
			document.getElementById("city").style.display="";
		}
	}
}

function ajaxChangeParentUnit(){
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
   data: "unitId="+unitId+"&unitType="+unitType,
   success: function(msg){
  
     makeSelectMenu(msg);
   }
}); 

}

function makeSelectMenu(response){
var obj = document.getElementById("parentUnitId");
var ln = obj.options.length;
while(ln--){
	obj.remove(ln);		
}
var option = new Option("=请选择=",""); 
obj.options.add(option);
var value=response.split("#");
for(var i=0;i<value.length;i++){
	if(value[i]==""){
		continue;
	}	
	var option = new Option(value[i].split(",")[1],value[i].split(",")[0]); 
	obj.options.add(option);
	if(value[i].split(",")[0]== ${unit.parentUnitId}){
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
   data: "parentRegonId="+prov,
   success: function(msg){
    makeCityMenu(msg);
   }
}); 
}
	
}
function makeCityMenu(response){
var obj = document.getElementById("city");
var ln = obj.options.length;
while(ln--){
	obj.remove(ln);		
}
var option = new Option("=请选择=",""); 
obj.options.add(option);

var value=response.split("#");
for(var i=0;i<value.length;i++){
	if(value[i]==""){
		continue;
	}	
	var option = new Option(value[i].split(",")[1],value[i].split(",")[0]); 
	obj.options.add(option);
	if(value[i].split(",")[0] == '${unit.areaCode}'){
    	obj.options[i+1].selected = true;
    }
}
}

function sub(){
	var unitTypeArray = document.getElementsByName("unitType");
	var unitType = "";
	for(var i=0;i<unitTypeArray.length;i++){
		if(unitTypeArray[i].checked){
		//alert(unitTypeArray[i].value);
			unitType = unitTypeArray[i].value;
		}
	}
	if(unitType == ""){
		alert("请选择分类");
		return false;
	}
	var parentUnitId = document.getElementById("parentUnitId").value;
	if(parentUnitId == ""){
		alert("请选择上级节点");
		return false;
	}
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
        <h5>查看单位</h5>
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
                        <label for="name" >名称：</label>
                    </div>
                    <div class="input">
                       ${unit.name }
                        
                    </div>
                </div>
              
                <div class="field">
                    <div class="label label-radio">
                        <label for="unitType" >分类:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                       <c:if test="${unit.unitType==1 }">
                      		 电商基地单位
                       </c:if>
                       <c:if test="${unit.unitType==2 }">
                       		省单位
                       </c:if>
                        <c:if test="${unit.unitType==3 }">
                        	地市单位
                        </div>
                        </c:if>
                       
                    </div>
                </div>
                
              

                <div  class="field" >
                    <div class="label">
                        <label for="areaCode" >地区编码：</label>
                    </div>
                    <div class="input">
                      	${unit.areaCode }
                    </div>
                </div>
                
                <div  class="field" >
                    <div class="label">
                        <label for="areaCode" >更新时间：</label>
                    </div>
                    <div class="input">
                      	<ct:time source="${unit.updateTime}"/>
                    </div>
                </div>
				<div  class="field" >
                    <div class="label">
                        <label for="areaCode" >状态：</label>
                    </div>
                    <div class="input">
                      ${unit.flag==0?"正常":"已销户"}
                    </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="remark" >备注：</label>
                    </div>
                    <div class="input">
                    ${unit.remark }
                    </div>
                </div>


                

                <div class="buttons">
                    <a href="${ctx}/sys/unit/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>






</body>
</html>