<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
   
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>${menuBtn }</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="${ctx}/giftCard/${action_type}/edit" commandName="giftCard"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
               <div id="batchNoDiv" class="field">
                    <div class="label noinput">卡序列号：</div>
                    <div class="input">
		               <form:input path="serialNo" cssClass="small  required" maxlength="20" />	
				  	</div>
                </div>
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/giftCard/${action_type }/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>