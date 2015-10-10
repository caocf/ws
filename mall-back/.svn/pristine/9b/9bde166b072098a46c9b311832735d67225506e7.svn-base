<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    
         <script type="text/javascript">
    	$().ready(function() {
			//获取来源地址
	 		var url = document.referrer;
	 		$("#backUrl").val(url);
		});
	</script>
    <ht:head/>
    </head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>${sysLogistics.id==null?"添加物流信息":"修改物流信息" }</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="sysLogistics" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
      <input type="hidden" id="backUrl" name="backUrl" />
       <c:if test="${sysLogistics.id != null }">
       <form:hidden path="id"/>
       </c:if>
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${sysLogistics.id==null?"自动生成": sysLogistics.id }</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="name" class="req">公司中文名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="name" cssClass="small required  max-length-25" maxlength="25" />
                    </div>
                </div>
				<div class="field">
                    <div class="label">
                        <label for="ename" class="req">公司英文名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="ename" cssClass="small required  max-length-30" maxlength="30" />
                    </div>
                </div>
               
                <div class="field">
                    <div class="label">
                        <label for="interFace" class="req">接口地址：</label>
                    </div>
                    <div class="input">
                        <form:input path="interFace" cssClass="small required  max-length-50" maxlength="50"/>
                    </div>
                </div>

                 <div class="field">
                    <div class="label label-radio">
                        <label for="unitType" class="req">是否有效:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="isValid" value="0" ${sysLogistics.isValid==0?"checked":"" }  class="validate-one-required"/><label for="radio-1">无效</label>
                            <input type="radio" id="radio-2" name="isValid" value="1" ${sysLogistics.isValid!=0?"checked":"" } /><label for="radio-2">有效</label>
                    	</div>
                	</div>
                </div>

             
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/logistics/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


</body>
</html>