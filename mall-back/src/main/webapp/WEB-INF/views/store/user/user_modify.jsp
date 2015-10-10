<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
  /**  
 $(function(){
      $("#password").blur()(
        $.ajax({url:ajax. ,
                data:{userId: $("#userId").val(), password:$("password")}
    		 success: function(o) {
    				if(o.result ="success"){
    					$("#password").html("密码不正确");
    					return false;
    				}else{
    					$("#suserId").html("");
    				}
    			}
    		)
      )
 })
 
*/
    </script>
</head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    

    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="shopUser" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
    	<form:hidden path="id"/>
            <div class="fields">
            		
                <div class="field">
                    <div class="label noinput">
                        <label for="name">账号：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        ${code}
                    </div>
                </div>
                
<!--                <div class="field">-->
<!--                    <div class="label">-->
<!--                        <label for="src" class="req">请输入旧密码：</label>-->
<!--                    </div>-->
<!--                    <div class="input"> small medium large -->
<!--                        <form:input path="pwd"  cssClass="small required min-length-0 max-length-20" maxlength="20" />-->
<!--                    	<span class="error" id="advice-required-src" style="display:none"></span>-->
<!--                    	<span class="error" id="advice-min-length-src" style="display:none"></span>-->
<!--                        <span class="error" id="advice-max-length-src" style="display:none"></span>-->
<!--                        <span class="error" id="advice-server-src" style="display:none"></span>-->
<!--                    </div>-->
<!--                </div>-->
                
                   <div class="field">
                    <div class="label">
                        <label for="pwd" class="req">用户密码：</label>
                    </div>
                    <div class="input">
                        <form:password  path="pwd" cssClass="small required min-length-6 max-length-25" maxlength="20"/>
                        <span class="tip">支持6位英文字母或是数字或特殊字符，且必须以字母开头、包含特殊字符.</span>
                        <span class="error" id="advice-required-pwd" style="display:none"></span>
                        <span class="error" id="advice-min-length-pwd" style="display:none"></span>
                        <span class="error" id="advice-max-length-pwd" style="display:none"></span>
                        <span class="error" id="advice-server-pwd" style="display:none"></span>
                    </div>
                </div>
           

                <div class="field">
                    <div class="label">
                        <label for="confirmPass" class="req">确认密码：</label>
                    </div>
                    <div class="input">
                        <form:password path="confirmPass" cssClass="small required min-length-6 max-length-25 equals-pwd" maxlength="20"/>
                        <span class="tip">支持6位英文字母或是数字或特殊字符，且必须以字母开头、包含特殊字符.</span>
                        <span class="error" id="advice-required-confirmPass" style="display:none"></span>
                        <span class="error" id="advice-min-length-confirmPass" style="display:none"></span>
                        <span class="error" id="advice-max-length-confirmPass" style="display:none"></span>
                        <span class="error" id="advice-equals-confirmPass" style="display:none"></span>
                        <span class="error" id="advice-server-confirmPass" style="display:none"></span>
                    </div>
                  
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                 
                   <input class="common_btn" type="button" onclick="window.history.go(-1)"   value="返回">
                    
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


</body>
</html>