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
        <h5>彩信群发审批<h5>
    </div>
    <!-- end box / title -->
    <form id="fm" action="${ctx}/batch/processmms/audit/${task.id }" method="post" accept-charset="utf-8">
        <div class="form">
            <div class="fields">
                
                <div class="field">
                  <div class="label">
                        <label for="taskName">审批彩信标题：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                       ${task.title }
                    </div>
              </div>
              
                <div class="field">
                    <div class="label label-radio">
                        <label>审核:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="status" checked="checked"  value="1"/><label for="radio-1">通过</label>
                            <input type="radio" id="radio-2" name="status" value="2"/><label for="radio-2">拒绝</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="advice">审核意见：</label>
                    </div>
                    <div class="input">
                     <textarea id="advice" name="advice" class="max-length-200" rows="8" cols="50"></textarea>
                    </div>
                </div>
                
               
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- end forms -->
<script type="text/javascript">

$(function() {
    var form = $('#fm');
    var validation = new Validation(form,{});
    ajaxFormSubmit(form, function(resp) {
        if (resp.success) {
        		
        		simpleAlert('操作成功',function() {
        			window.parent.location.reload();
    			});
        	
        
        }else {
        	simpleWarn(resp.error)
        	};

    }, null);

});

</script>
</div>




</body>
</html>