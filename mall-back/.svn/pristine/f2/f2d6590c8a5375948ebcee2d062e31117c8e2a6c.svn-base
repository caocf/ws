<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
	<script type="text/javascript">
		//验证规格参数值输入框
		$(function(){
			$(".test123").keyup(function(){
			     alert($(this).attr("id"));
				$("#paramValueMsg").text("");
				var str = $("#paramValue").val();
				var pattern = /^(\w+;)*\w+$/; 
				if(str !=""){
					if(pattern.test(str) == false){
						$("#sub").attr("disabled","true");
						$("#paramValueMsg").text('输入格式错误，请重新输入');
						$("#paramValueMsg").css('display', 'block');
					}else{
						$("#sub").removeAttr("disabled");
					}
				}
			});
		});
		var num = 0;
		function addparamKey() {
			num = num + 1;
			var html= "<div><br />"+
		                    "<div class='input' style='float: left;'>"+
		                    	" 参数名："+
		                        "<input name='paramKey' id='paramKey"+num+"' class='small required min-length-0 max-length-8' maxlength='8' />"+
		                    	"<span class='error' id='advice-required-paramKey"+num+"' style='display:none;color: red'></span>"+
		                    "</div>"+
		                    "<div class='input' style='float: left;'>"+
		                        "参数值："+
		                        "<input name='paramValue' id='paramValue"+num+"'  class='small required min-length-0 max-length-20' maxlength='20'/>"+
		                        "<span class='tip'>多个值之间用分号(;)分隔,结尾不需要分号(;)</span>"+
		                    	"<span class='error' id='advice-required-paramValue"+num+"' style='display:none;color: red'></span>"+
		                    "</div>"+
	                    "&nbsp;&nbsp;<a href='javascript:void(0);' id='del' onclick='delpara(this)'>删除</a></div>";
			 $('#addpara').append(html); 
	    }
	    
	    function delpara(obj) {
			 $(obj).parent().remove();
	    }
		
	</script>
</head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>添加商品分类参数</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="sysTypeItemParam" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">自动生成</div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="type" class="req">所属分类：</label>
                    </div>
                    <div class="input">
                    	<input type="hidden" id="backUrl" name="backUrl" />
                        <input type="hidden" id="typeId" name="typeId" value="${sysTypeItemParam.typeId}"  />
						<input type="text" id="typeName" name="typeName" value="${sysTypeItemParam.sysType.name}" readonly class="<c:if test="${empty sysTypeItemParam.typeId}">typeclass</c:if> small required min-length-0 max-length-50" maxlength="50"/>
                       	<span class="error" id="advice-required-typeName" style="display:none"></span>
                        <span class="error" id="advice-min-length-typeName" style="display:none"></span>
                        <span class="error" id="advice-max-length-typeName" style="display:none"></span>
                        <span class="error" id="advice-server-typeName" style="display:none"></span>
                    </div>
                </div>
                <div id="addpara">
                		<div class="field">
		                    <div class="label">
		                        <label for="paramKey" class="req">规格参数：</label>
		                    </div>
		                    <div class="input">
		                    <ct:btn type="button" value="增加" name="addbtn" onclick="addparamKey();"/>
		                    </div>
	                    </div>
	                     <br />
	                    <div>
		                    <div class="input" style="float: left;">
		                    	 参数名：
		                        <input name="paramKey" id="paramKey" class="small required min-length-0 max-length-8" maxlength="8" />
		                    	<span class="error" id="advice-required-paramKey" style="display:none;color: red"></span>
		                    </div>
		                    <div class="input" style="float: left;">
		                                                                参数值：
		                        <input name="paramValue" id="paramValue"  class="small required min-length-0 max-length-20" maxlength="20" />
		                        <span class="tip">多个值之间用分号(;)分隔,结尾不需要分号(;)</span>
		                    	<span class="error" id="advice-required-paramValue" style="display:none;color: red"></span>
		                    </div>
	                    </div><br />
                </div>
                <div class="field"></div>
                <div class="field">
                    <div class="label label-radio">
                        <label class="req">参数类型:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        <c:forEach items="${paramTypeMap}" var="item">
                        	<input type="radio" id="radio-${item.key}" name="paramType" <c:if test="${sysTypeItemParam.paramType == item.key}">checked="checked"</c:if> value="${item.key}"/><label for="radio-${item.key}">${item.value}</label>
                        </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-radio">
                        <label class="req">是否必填:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        <c:forEach items="${requiredFlagMap}" var="item">
                        	<input type="radio" id="radio-${item.key}" name="requiredFlag" <c:if test="${sysTypeItemParam.requiredFlag == item.key}">checked="checked"</c:if> value="${item.key}"/><label for="radio-${item.key}">${item.value}</label>
                        </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-radio">
                        <label class="req">是否参与检索:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        <c:forEach items="${searchFlagMap}" var="item">
                        	<input type="radio" id="radio-${item.key}" name="searchFlag" <c:if test="${sysTypeItemParam.searchFlag == item.key}">checked="checked"</c:if> value="${item.key}"/><label for="radio-${item.key}">${item.value}</label>
                        </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" id="sub" name="submit.highlight" value="提交" />
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
<script type="text/javascript">
$().ready(function() {
	//获取来源地址
	var url = document.referrer;
	$("#backUrl").val(url);
	$(".typeclass").click(function() {
		selectType(2,'typeId','typeName',{one:true})
	});
});
</script>
</body>
</html>