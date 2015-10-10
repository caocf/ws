<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
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
        <h5>查看角色</h5>
    </div>
    <!-- end box / title -->
     <form:form method="post" id="fm" commandName="role" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
   	<input type="hidden" id="menu_privilege" name="menu_privilege" value="" />
   	<form:hidden path="id" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${role.id }</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="roleName" >名称：</label>
                    </div>
                    <div class="input">
                    ${role.roleName }
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="remark" >备注：</label>
                    </div>
                    <div class="input">
                      ${role.remark }
                    </div>
                </div>

                <div class="field">
                    <div class="label">
                        <label  >权限：</label>
                     
                    </div>
                    <div  style="margin: 0 0 0 120px;width:66%; height:350px; overflow:auto; border: 1px solid #b3b3b3;color: #000000;">
                          ${menuPrivilege }
                    </div>
                </div>

                <div class="buttons">
                   
                    <a href="${ctx}/sys/role/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
  </form:form>
</div>
<!-- end forms -->
</div>



</body>
</html>