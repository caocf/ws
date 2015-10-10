<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/t.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#roleName").change(function() {
            var roleName = $('#roleName').val();
            $.ajax({
                type : 'post',
                url : '${ctx}/sysMgmt/roleMgmt/checkRoleName',
                data : {
                    roleName : roleName
                },
                success : function(msg) {
                    if (msg == 1) {
    					$("#repeat-error").css("background","#DA4F49");
    					$("#repeat-error").html("角色名已经存在！");
                        return;
                    } else {
    					$("#repeat-error").css("background","#5ebb0b");
    					$("#repeat-error").html("角色名可用！");
                    }
                }
            });
        });
		
		$("#sub").click(function() {
			var roleName = $('#roleName').val();
			$.ajax({
				type : 'post',
				url : '${ctx}/sysMgmt/roleMgmt/checkRoleName',
				data : {
					roleName : roleName
				},
				success : function(msg) {
					if (msg == 1) {
						$("#repeat-error").css("background", "#DA4F49");
						$("#repeat-error").html("角色名已经存在！")
						return;
					} else {
						$("#repeat-error").css("background", "#5ebb0b");
						$("#repeat-error").html("角色名可用！")
						$("#fm").submit();
					}
				}
			});
		});
	});
</script>
<div class="row-fluid">
    <div class="span12">
        <form:form method="post" id="fm" commandName="role" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate form-horizontal">
            <div class="row-fluid">
                <div class="control-group group-search">
                    <label class="control-label req" for="roleName">角色名：</label>
                    <div class="controls controls-row">
                        <form:input path="roleName" cssClass="span6 required max-length-10" maxlength="10" />
                    </div>
					<span id="repeat-error" style="color: #000000; margin-left: 130px;">检验账号是否可用！</span>
				</div>
				<div class="control-group group-search">
					<label class="control-label req" for="remark">描述：</label>
                    <div class="controls controls-row">
                        <form:input path="remark" cssClass="span6 required max-length-50" maxlength="50" />
                    </div>
                </div>

                <div class="control-group group-search">
                    <label class="control-label"></label>
                    <div class="controls controls-row">
                        <button type="button" class="btn btn-primary" id="sub">
                            <i class="icon16 i-disk"></i> 保 存
                        </button>
                        <!--                         <button type="reset" class="btn"> -->
                        <!--                             <i class="icon16 i-loop"></i> -->
                        <!--                             重 置 -->
                        <!--                         </button> -->
                        <button class="btn" type="button" onclick='history.back();'>
                            <i class="icon16 i-exit"></i> 返 回
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

<%@ include file="../../includes/b.jsp"%>
