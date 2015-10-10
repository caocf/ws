<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
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
        <h5>批量导入黑名单<h5>
    </div>
    <!-- end box / title -->
     <form id="fm" class="required-validate" action="batchadd.do" commandName="blackUser" method="post" accept-charset="utf-8" enctype="multipart/form-data">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label">
                        <label for="file">文件选择:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="file1" name="batchFile" size="40"  class="validate-file-txt"/>
                        <span class="error" id="advice-validate-file-file1" style="display:none"></span>
                    </div>
                </div>
                 <div class="field">
                   <div class="label label-radio">
                        <label for="levTag" class="req">级别：</label>
                    </div>
                    <div class="radios">
                       <c:forEach items="${levTapMap}" var="item" varStatus="index">
                            <input type="radio" id="radio-${index.count}" <c:if test="${index.count==1}">checked="checked"</c:if> name="levTag" class="validate-one-required"  value="${item.key }"/><label for="radio-${index.count }">${item.value }</label>
                            </c:forEach>
                        <span class="error" id="advice-validate-one-required-levTag" style="display:none"></span>
                         </div>
                    </div>                    
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/black/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form>
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