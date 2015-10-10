<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    $().ready(function() {
      //获取来源地址
	var url = document.referrer;
	$("#backUrl").val(url);
  	//限制地区
    selectRegionCallBack("#areaName",function(regionCode,regionNames,areaCodes){
    	$("#actArea").val(regionCode);
    	$("#areaName").val(regionNames);
    },320000,{index:2});
  	
    
    //选择商户
  	/*$("#storeName").click(function(){
  		showDialog("选择商户", "${ctx}/store/store/selectStores.do",function(doc){
  			var selectedId= doc.getElementById("shopId").value;
  			var selectedName= doc.getElementById("shopName").value;
  			$("#storeId").val(selectedId);
  			$("#storeName").val(selectedName);
  		},{"Width":900,"Height":400});
  	});*/
    });
    function sub() {
    	$("#filepath").val($("#uploaditemfile").val());
    }
    </script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加短信购活动配置</c:if><c:if test="${method == 'edit'}">修改短信购活动配置</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="addAct" commandName="smsBuyActOnline"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();" enctype="multipart/form-data">
        <div class="form">
        <form:hidden path="actId"/>
        <input type="hidden" id="backUrl" name="backUrl" />
            <div class="fields">
            	<c:if test="${method == 'add'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自动生成</div>
                </div>
                </c:if> 
                <c:if test="${method == 'edit'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${smsBuyActOnline.actId}</div>
                </div>
                </c:if>		
                 <div class="field">
                    <div class="label">
                        <label for="actName"  class="req">活动名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="actName" cssClass="required" maxlength="100" size="50" />
                         <span class="error" id="advice-validate-one-required-actName" style="display:none"></span>
                    </div>
                </div>
                
                
                
               <div class="field">
							<div class="label">
								<label for="rootSpcode">特服号：</label>
							</div>
							<div style="margin-left: 120px">
								<select name="rootSpcode">
	                    			<c:forEach items="${sysSpcodeList }" var="item">
	                    			    <c:if test="${fn:contains(router.spCode,item.spcode)}">
	                    				<c:set var="tfh" value="${fn:length(item.spcode)}"></c:set>
	                    				</c:if>
	                        			<option value="${item.spcode }" <c:if test="${fn:contains(router.spCode,item.spcode)}">selected="selected"</c:if>>${item.spcode }</option>
	                        		</c:forEach>
	                    		</select>
	                    		<input type="hidden" name="spCode" value="">
	                    		<!--
	                    		<form:input path="spCode" maxlength="9"  style="width:55px" value="${fn:substring(router.spCode, tfh,-1)}" onkeyup="value=value.replace(/[^\d]/g,'')"  />
	                    		-->
							</div>
						</div>
                
                
                
                
                <div class="field">
                    <div class="label label-textarea">
                        <label for="actDesc">活动描述：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="actDesc" cols="50" rows="8" cssClass="max-length-250"/>
                        <span class="error" id="advice-validate-one-required-actDesc" style="display:none"></span>
                    </div>
                </div>
                
              <div class="field">
							<div class="label">
								<label for="areaName">限制地区：</label>
							</div>
							<div class="input">
								<form:input path="areaName" cssClass="small required"
									maxlength="100" readonly="true" />
								<form:hidden path="actArea" />
								<span class="error" id="advice-required-areaName"
									style="display:none"></span>
							</div>
						</div>
						
				<div class="field">
                    <div class="label">
                        <label for="startTime" class="req">活动开始时间：</label>
                    </div>
                    <div class="input">
                   <input type="text" id="startTime" name="startTime" value="${smsBuyActOnline.startTime}" class="date required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" readonly/>
                    </div>
                </div>
		         <div class="field">
                    <div class="label">
                        <label for="endTime" class="req">活动结束时间:</label>
                    </div>
                    <div class="input">
                    <input type="text" id="endTime" name="endTime" class="date required" value="${smsBuyActOnline.endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" readonly/>
                    </div>
                </div>
                <!-- 
                <div class="field">
                    <div class="label">
                        <label for="uploaditemfile">批量上传商品:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="uploaditemfile" name="uploaditemfile" size="40" class="validate-file-xls-xlsx" />
                        <span class="tip">excel格式</span>
                        <span class="tip"><a href="./downSmsMould">下载模版</a></span>
                        <span class="error" id="advice-validate-file-uploaditemfile" style="display:none"></span>
                        <input type="hidden" id="filepath" name="filepath">
                    </div>
                </div>
                 -->
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/smsbuy/act/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>