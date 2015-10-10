<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
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
        <h5>添加用户</h5>
    </div>
    <!-- end box / title -->
    <form id="fm" name="fm" action="other.jsp" >
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">用户ID：</div>
                    <div class="input">自动生成</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="email" class="req">注册邮箱：</label>
                    </div>
                    <div class="input">
                        <input type="text" name="email" id="email" class="small required validate-email" maxlength="50" value="nickycheng@hotmail.com" />
                    </div>
                </div>

                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/user/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- end forms -->
</div>

<script>

    $(function() {
        var form = $('#fm');
        var validation = new Validation(form,{});
        // or
        var validation = new Validation(form, {
            onFormValidate: function(result, form) {
                // result 为验证的结果
                alert("onFormValidate");

                // 改变验证结果, false表示会阻止onsubmit;
                return false;
            }
        })

        // Reset 按钮需要
        // form.bind('reset',function() {validation.reset();});
        ajaxFormSubmit(form, /*可以不写此方法，传null*/ function(resp) {
            alert('提交成功！');
        }, function(arr, f, options) {
            // arr 表单中的项目数组
            // f 表单对象
            // options 无关紧要
            alert('提交前做的事情');

            // return false 阻止继续提交
            // return false
        });

    });


</script>

</body>
</html>