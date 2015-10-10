<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!doctype html>
<head>
    <ht:head/>
</head>
<body>

<div id="content">
<div class="box">
 <!-- box / title -->
    <div class="title">
        <h5>密码修改</h5>
    </div>
      <form action="changePwd" method="post" class="required-validate">
     <div class="form">
      <div class="fields">
        <div class="field">
                  <div class="label">
                        <label for="userCode" class="req">原始密码：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                       <input type="password" name="orgPwd" id="orgPwd" title="原始密码" value="" class="small required min-length-6 max-length-25 " maxlength="20" />
                    </div>
                </div>
           <div class="field">    
               <div class="label">
                     <label for="userCode" class="req">新密码：</label>
                 </div>
                 <div class="input"><!-- small medium large -->
                    <input type="password" name="dstPwd" id="dstPwd" title="新密码"" value="" class="small required min-length-6 max-length-20 " maxlength="20" />
                      <span class="tip">支持6位英文字母或是数字或特殊字符，且必须以字母开头、包含特殊字符.</span>
                      <span class="error" id="advice-required-dstPwd" style="display:none"></span>
                     <span class="error" id="advice-min-length-dstPwd" style="display:none"></span>
                     <span class="error" id="advice-max-length-dstPwd" style="display:none"></span>
                     <span class="error" id="advice-server-dstPwd" style="display:none"></span>
                 </div>
             </div>
            <div class="field">    
              <div class="label">
                    <label for="userCode" class="req">确认新密码：</label>
                </div>
                <div class="input"><!-- small medium large -->
                   <input type="password" name="confirmPwd" id="confirmPwd" title="确认新密码"  value="" class="small required min-length-6 max-length-20  equals-dstPwd" maxlength="20" />
                    <span class="tip">支持6位英文字母或是数字或特殊字符，且必须以字母开头、包含特殊字符.</span>
                    <span class="error" id="advice-required-confirmPwd" style="display:none"></span>
                    <span class="error" id="advice-min-length-confirmPwd" style="display:none"></span>
                    <span class="error" id="advice-max-length-confirmPwd" style="display:none"></span>
                    <span class="error" id="advice-server-confirmPwd" style="display:none"></span>
                </div>
            </div>
            
            <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                </div>
      </div>
     </div>
     </form>
</div>
</div>
</body>
</html>
