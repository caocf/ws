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
	selectItemType("#typeName",function(id,txt){
		$("#typeId").val(id);
		$("#typeName").val(txt);
		//加载该商户的参数
		$("#paramTypeBox").load("./getTypeParams?typeId=" +$("#typeId").val() +"&shopId=" + $("#shopId").val());
		
	});
	$("#storeName").click(function(){
		selectStoreCallBack('',function(id,txt){
			$("#shopId").val(id);
			$("#storeName").val(txt);
		});
	});
	
	
	$("#addParamBtn").click(function(){
		var div = $("#addParamBox div:first").clone();
		$("input",div).val("");//清空数据
		//增加删除按钮
		var delBtn = $('<input type="button" class="btnAnchor" value="删除"  />');
		delBtn.click(function(){
			$(this).parent().remove();
		});
		div.append(delBtn);
		$("#addParamBox").append(div);
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
        <h5><c:if test="${method == 'shopAdd'}">添加商户分类参数</c:if><c:if test="${method == 'shopEdit'}">修改商户分类参数</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="sysTypeItemParam" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
    <c:if test="${method == 'edit'}">
    <input type="hidden" id="paramType" name="paramType" value="${sysTypeItemParam.paramType}"/>
    <input type="hidden" id="requiredFlag" name="requiredFlag" value="${sysTypeItemParam.requiredFlag}"/>
    <input type="hidden" id="rank" name="rank" value="${sysTypeItemParam.rank}"/></c:if>
        <div class="form">
            <div class="fields">
            	<c:if test="${method == 'shopAdd'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">自动生成</div>
                </div>
                </c:if>
                <c:if test="${method == 'shopEdit'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${sysTypeItemParam.id}</div>
                </div>
                </c:if>		
                <div class="field">
                    <div class="label">
                        <label for="type" class="req">所属商户：</label>
                    </div>
                    <div class="input">
                    	<input type="hidden" id="backUrl" name="backUrl" />
                        <input type="hidden" id="shopId" name="shopId" value="${sysTypeItemParam.shopId}"  />
						<input type="text" id="storeName" name="storeName" value="${sysTypeItemParam.storeName}" readonly class=" small required " maxlength="50"/>
                       	<span class="error" id="advice-required-storeName" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="type" class="req">所属分类：</label>
                    </div>
                    <div class="input">
                        <input type="hidden" id="typeId" name="typeId" value="${sysTypeItemParam.typeId}"  />
						<input type="text" id="typeName" name="typeName" value="${sysTypeItemParam.sysType.name}" readonly class=" small required " maxlength="50"/>
                       	<span class="error" id="advice-required-typeName" style="display:none"></span>
                    </div>
                </div>
                
                <div class="buttons">
                	<c:if test="${method != 'shopEdit' }">
                     <input type="button" class="btnAnchor" value="添加"  id="addParamBtn"/>
                     </c:if>
                </div>
                <div id="addParamBox" style="border:1px solid #DDDDDD">
                         <div class="input">
                         参数名： <input type="text"  name="paramKey" value="${ sysTypeItemParam.paramKey}" class="required" cssClass="small required min-length-0 max-length-8" maxlength="8"/>
                         参数值：<input type="text"  name="paramValue" value="${ sysTypeItemParam.paramValue}"  class="required"/>
                        </div>
                </div>
               
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="javascript:history.go(-1)" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
<c:if test="${method == 'shopAdd'}">
<div class="container">

    <br/>
    <h3>参数列表</h3>
    <div class="mainbox" id="paramTypeBox">
     
    </div>

</div>
</c:if>

</body>
</html>