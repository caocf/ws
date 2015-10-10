<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/t.jsp"%>
<script type="text/javascript">
	function checkRole(){
		var option = $('select[name=roleId]').find('option:selected');
		if(option.length <= 0){
			$('#advice-required-roleId').show();
			return false;
		}else{
			$('#advice-required-roleId').hide();
			$('form').submit();
			return true;
		}
	}
</script>

<div class="row-fluid">
    <div class="span12">
        <form:form method="post" id="fm" commandName="dcUser" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate form-horizontal" >
            <div class="row-fluid">
                <div class="control-group group-search">
                    <label class="control-label req" for="code">用户名：</label>
                    <div class="controls controls-row">
                        <form:input path="code" cssClass="span6 required max-length-10" disabled="true" />
                    </div>

                    <label class="control-label req" for="name">姓名：</label>
                    <div class="controls controls-row">
                        <form:input path="name" cssClass="span6 required max-length-10" disabled="true" />
                    </div>

                    <label class="control-label req" for="terminalId">电话：</label>
                    <div class="controls controls-row">
                        <form:input path="terminalId" cssClass="span6 required validate-mobile-phone" maxlength="20" disabled="true"/>
                    </div>

                    <label class="control-label req" for=email>邮箱：</label>
                    <div class="controls controls-row">
                        <form:input path="email" cssClass="span6 required validate-email" maxlength="50" disabled="true"/>
                    </div>

                    <div class="control-group group-search">
                        <label class="control-label req" for="roleId">角色：</label>
                        <div class="controls controls-row" style="width: 44.2%">
                            <form:select path="roleId" id="roleId" style="height:200px;width:300px;" disabled="true">
                                <c:forEach items="${roles}" var="role">
                                    <c:forEach items="${have_roles}" var="haverole">
                                        <c:choose>
                                            <c:when test="${haverole.roleId ==  role.id}">
                                                <form:option value="${role.id}">${role.roleName}</form:option>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </c:forEach>
                            </form:select>
                            <label style="display:none;" id="advice-required-roleId" class="error">  请选择.</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="control-group group-search">
                <label class="control-label"></label>
                <div class="controls controls-row">
                    <button class="btn" type="button" onclick='history.back();'>
                        <i class="icon16 i-exit"></i>
                        返 回
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>

<%@ include file="../../includes/b.jsp"%>
