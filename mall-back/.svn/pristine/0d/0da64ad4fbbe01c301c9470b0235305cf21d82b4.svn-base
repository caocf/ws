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
        <h5>查看权限项目</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="openRoleItem" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${openRoleItem.id}</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="name" >名称：</label>
                    </div>
                    <div class="input">
                       ${openRoleItem.name }
                        
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="name" >路径：</label>
                    </div>
                    <div class="input">
                       ${openRoleItem.path }
                        
                    </div>
                </div>

                <div class="buttons">
                    <a href="${ctx}/sys/open/roleitem-list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>






</body>
</html>