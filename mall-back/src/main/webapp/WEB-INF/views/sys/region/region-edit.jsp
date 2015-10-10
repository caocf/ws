<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    </head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>修改区域</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="region" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <form:hidden path="parentRegion" />
        <form:hidden path="regionLevel" />
        <form:hidden path="id" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${region.id }</div>
                </div>
                <div class="field">
                    <div class="label noinput">上级区域</div>
                    <div class="input">${regionName }</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="regionCode" class="req">编码：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="regionCode" cssClass="small required" maxlength="50" />
                    </div>
                </div>

				 <div class="field">
                    <div class="label">
                        <label for="regionCode" class="req">区域码：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="areaCode" cssClass="small required " maxlength="50" />
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="regionName" class="req">名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="regionName" cssClass="small required" maxlength="100"/>
                        
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="shortName" class="req">简称：</label>
                    </div>
                    <div class="input">
                        <form:input path="shortName" cssClass="small required" maxlength="25"/>
                       
                    </div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="regionSpell" class="req">拼音：</label>
                    </div>
                    <div class="input">
                        <form:input path="regionSpell" cssClass="small required" maxlength="100"/>
                       
                    </div>
                </div>
				<div class="field">
                    <div class="label label-radio">
                        <label for="isShow" class="req">是否显示:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="isShow" ${region.isShow == 1?"checked":"" }  value="1" class="validate-one-required"/><label for="radio-1">是</label>
                            <input type="radio" id="radio-2" name="isShow" ${region.isShow == 0?"checked":"" } value="0" /><label for="radio-2">否</label>
                            
                        </div>
                       
                    </div>
                </div>
                

                <div class="field">
                    <div class="label">
                        <label for="sortNum" class="req">排序：</label>
                    </div>
                    <div class="input">
                    <form:input path="sortNum" cssClass="small required" maxlength="10"/>
                    </div>
                </div>

                

                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/region/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


<script type="text/javascript">
    <!--
    ajaxFormSubmit('#fm');

    //-->
</script>



</body>
</html>