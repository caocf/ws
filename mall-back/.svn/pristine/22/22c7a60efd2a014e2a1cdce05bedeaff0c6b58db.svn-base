<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">


$(function(){
		selectRegion("#regionName","regionCode","regionName",'0',{maxItems:1});
    		selectItemType("#typeName",function(id,txt){
	    		$("#typeId").val(id);
	    		$("#typeName").val(txt);
	    		$("#typeName").focus();
	    		$("#typeName").blur();
	    	},{leaf:false});
});

</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加导航栏</c:if><c:if test="${method == 'edit'}">修改导航栏</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add" commandName="channelNaviOper"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <div class="form">
        <form:hidden path="id"/>
         <form:hidden path="createTime"/>
        <form:hidden path="createUser"/>
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
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${channelNaviOper.id}</div>
                </div>
                </c:if>		
                                
                
                <div class="field">
                    <div class="label">
                        <label for="channel" class="req">所属网页：</label>
                    </div>
                    <div class="select">
                        <form:select path="pageCode" id ="pageCode">
                       
                    	<c:forEach items="${pageCodeList }" var="item">
                        	    <option value="${item.code }" <c:if test="${channelNaviOper.pageCode == item.code}">selected="selected"</c:if>> ${item.title }</option>
                        </c:forEach>
                    </form:select>
                    </div>
                </div>	
                
                 <div class="field">
                    <div class="label">
                        <label for="title" class="req">区域：</label>
                    </div>
                    <div class="input">
                        <form:hidden path="regionCode" cssClass="small" />
                        <form:input path="regionName"  cssClass="small" maxlength="50"  readonly="true"/>
                        
                        <label for="title" >(不选择，默认是全国)</label>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="title" class="req">栏目名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="title" cssClass="small required max-length-50" maxlength="100" />
                    </div>
                </div>
                    
                    <div class="field">
                    <div class="label">
                        <label for="title" class="req">代码：</label>
                    </div>
                    <div class="input">
                        <form:input id="cde" path="code" cssClass="small  required" maxlength="100" />
                    </div>
                </div>
                    
                 <div class="field">
                    <div class="label"  >
                        <label for="linkUrl" class="req" >链接：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="href"   size="80" cssClass="small required validate-url min-length-1 max-length-150"/>
                         <span class="error" id="advice-validate-url-linkUrl" style="display:none"></span>
                    </div>
                </div>
                    
                    
                     <div class="field">
                    <div class="label">
                        <label for="orderIndex">排序：</label>
                    </div>
                    <div class="input">
                        <form:input path="sortNo" cssClass="int-range-1-99" maxlength="4" />
                        <span class="error" id="advice-int-range-orderIndex" style="display:none"></span>
                    </div>
                </div>
                

              
                 
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/channel/navioper/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>