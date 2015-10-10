<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
<script type="text/javascript">
<!--
	$(function(){
		selectRegionCallBack("#areaCode",function(saleAreaCode,saleAreaName){
			$("#areaCode").val(saleAreaCode);
			$("#areaCode").focus();
			$("#areaCode").blur();
		},0,{index:1,maxItems : 1});
	});
//-->
</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>批量导入</h5>
    </div>
    <!-- end box / title -->
     <form id="fm" class="required-validate" action="upload" method="post" accept-charset="utf-8" enctype="multipart/form-data">
        <div class="form">
            <div class="fields">
            	<div class="field">
	                    <div class="label noinput">导入文件：</div>
	                    <div class="input">txt文本（文件内容格式：一个号段一行）</div>
	            </div>
                <div class="field">
                    <div class="label">
                        <label for="file">文件选择:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="file1" name="uploadFile" size="40" class="validate-file-txt" />
                        <span class="error" id="advice-validate-file-file1" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="operatorCode" class="req">操作码：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                    	<input type="text" name="operatorCode" id="operatorCode" class="small validate-alpha required min-length-0 max-length-50" maxlength="50"  value="${sysSegment.operatorCode}"/>
                    	<span class="error" id="advice-required-operatorCode" style="display:none"></span>
                        <span class="error" id="advice-validate-alpha-operatorCode" style="display:none"></span>
                        <span class="error" id="advice-min-length-operatorCode" style="display:none"></span>
                        <span class="error" id="advice-max-length-operatorCode" style="display:none"></span>
                        <span class="error" id="advice-server-operatorCode" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="areaCode" class="req">地市：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                    	<input type="text" name="areaCode" id="areaCode" class="small validate-digits required min-length-0 max-length-8" maxlength="8" readonly="true"/>
                    	<span class="error" id="advice-required-areaCode" style="display:none"></span>
                        <span class="error" id="advice-validate-digits-areaCode" style="display:none"></span>
                        <span class="error" id="advice-min-length-areaCode" style="display:none"></span>
                        <span class="error" id="advice-max-length-areaCode" style="display:none"></span>
                        <span class="error" id="advice-server-areaCode" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="mmscId" class="req">彩信中心编号：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <input type="text" name="mmscId" id="mmscId" class="small required validate-digits min-length-0 max-length-20" maxlength="20"  value="${sysSegment.mmscId}" />
                    	<span class="error" id="advice-required-mmscId" style="display:none"></span>
                        <span class="error" id="advice-validate-digits-mmscId" style="display:none"></span>
                        <span class="error" id="advice-min-length-mmscId" style="display:none"></span>
                        <span class="error" id="advice-max-length-mmscId" style="display:none"></span>
                        <span class="error" id="advice-server-mmscId" style="display:none"></span>
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
        </div>
    </form>
</div>
<!-- end forms -->
</div>




</body>
</html>