<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    
<script type="text/javascript">
	/*屏蔽回退按钮*/
	function forbidBackSpace(e) {
	   var ev = e || window.event; //获取event对象 
	   var obj = ev.target || ev.srcElement; //获取事件源 
	   var t = obj.type || obj.getAttribute('type'); //获取事件源类型 
	   //获取作为判断条件的事件类型 
	   var vReadOnly = obj.readOnly;
	   var vDisabled = obj.disabled;
	   //处理undefined值情况 
	   vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
	   vDisabled = (vDisabled == undefined) ? true : vDisabled;
	   //当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
	   //并且readOnly属性为true或disabled属性为true的，则退格键失效 
	   var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
	   //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
	   var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
	   //判断 
	   if (flag2 || flag1) return false;
	}
	//禁止后退键 作用于Firefox、Opera
	document.onkeypress = forbidBackSpace;
	//禁止后退键  作用于IE、Chrome
	document.onkeydown = forbidBackSpace;
 $().ready(function() {
	  selectRegion("#regonName","regon","regonName",${pid},{maxItems:10,selValues:${json}});
});  
  
</script>
    
    </head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>修改用户</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="user" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
     <form:hidden path="id"/>
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">用户ID：</div>
                    <div class="input">${user.id }</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="userCode" class="req">账号：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                        <form:input path="userCode" cssClass="small "  maxlength="10"  readonly="true"/>
                    </div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="userPwd" >用户密码：</label>
                    </div>
                    <div class="input">
                        <form:password path="userPwd"  cssClass="small min-length-6 max-length-25 " maxlength="20"/>
                        <span class="tip">如果不修改密码则不用输入密码</span>
                        <span class="error" id="advice-required-userPwd" style="display:none"></span>
                        <span class="error" id="advice-min-length-userPwd" style="display:none"></span>
                        <span class="error" id="advice-max-length-userPwd" style="display:none"></span>
                        <span class="error" id="advice-server-userPwd" style="display:none"></span>
                    </div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="confirmPass" >确认密码：</label>
                    </div>
                    <div class="input">
                        <form:password path="confirmPass" cssClass="small min-length-6 max-length-25  " maxlength="20"/>
                        <span class="tip"></span>
                        <span class="error" id="advice-required-confirmPass" style="display:none"></span>
                        <span class="error" id="advice-min-length-confirmPass" style="display:none"></span>
                        <span class="error" id="advice-max-length-confirmPass" style="display:none"></span>
                        <span class="error" id="advice-server-confirmPass" style="display:none"></span>
                    </div>
                </div>
 				<c:if test="${unitId ==0}">
                <div class="field">
                    <div class="label label-textarea">
                        <label for="unitId" >选择单位：</label>
                    </div>
                    <div class="input">
                        <select id="unitId" name="unitId">
                        <option value="">请选择</option>
                         <c:forEach items="${unitList}" var="item">
                          <option value="${item.id }">${item.name }</option>
                         </c:forEach>
                        </select>
                    </div>
                </div>
                </c:if>
                <div class="field">
                    <div class="label">
                        <label for="terminalId" class="req">终端号码：</label>
                    </div>
                    <div class="input">
                        <form:input path="terminalId" cssClass="small required validate-mobile-phone" maxlength="13"/>
                    </div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="email" class="req">注册邮箱：</label>
                    </div>
                    <div class="input">
                        <form:input path="email" cssClass="small required validate-email" maxlength="50"/>
                    </div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="userName" class="req">真实姓名：</label>
                    </div>
                    <div class="input">
                    	<form:input path="userName" cssClass="small required min-length-0 max-length-10" maxlength="10"/>
                    </div>
                </div>
                
                  
                   <div class="field" style="display: none">
                    <div class="label">
                        <label for="flag" class="req">标识：</label>
                    </div>
                    <div class="input">
                      <select id="flag" name="flag">
                     <c:forEach items="${userTypeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${unitType == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                        </select>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label label-textarea">
                        <label for="role" class="req">选择角色：</label>
                    </div>
                    <div class="checkboxes">
                        <div class="checkbox1">
                         <c:forEach items="${sysRoleList}" var="item"  varStatus="s">
                         <input type="checkbox" name="role"  value="${item.id }" />
                          <label >${item.roleName }</label>
                          <c:forEach items="${sysUserRoleList}" var="role">
                         <c:if test="${item.id==role.roleId }" >
                         <script>
                         document.getElementsByName("role")[${s.index}].checked = true;
                         </script>
                         </c:if>
                        
                         </c:forEach>
                         </c:forEach>
                         </div>
                         
                    </div>
                    
                </div>
               
				<div class="field">
                    <div class="label label-textarea">
                        <label for="unitId" >选择区域：</label>
                    </div>
                     <div class="checkboxes">
                        <div >

                         <input type="text" name ="regonName" id="regonName" value="${regonName }" />
                         <input type="hidden" name ="regon" id="regon" value="${regon }" />
                         
                         </div>
                         
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="remark">备注：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="remark" cols="50" rows="8" cssClass="max-length-100" />
                    </div>
                </div>

              
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/user/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
<script type="text/javascript">
document.getElementById("unitId").value = '${user.unitId}';
document.getElementById("flag").value = '${user.flag}';
 </script>

</body>
</html>