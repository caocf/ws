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
        <h5>批量导入过滤字<h5>
    </div>
    <!-- end box / title -->
     <form id="fm" class="required-validate" action="batchadd" method="post" accept-charset="utf-8" enctype="multipart/form-data">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label">
                        <label for="file">文件选择:</label>
                    </div>
                    <div class="input input-file">
                        <input type="file" id="file1" name="batchFile" size="40" class="validate-file-txt"/>
                        <span class="tip">导入的文件格式为“.txt”。</span>
                        <span class="error" id="advice-validate-file-file1" style="display:none"></span>
                    </div>

                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/filterword/list" class="btnAnchor">返回</a>
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