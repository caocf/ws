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
        <h5>修改客服</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="customerService" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
	<input type="hidden" id="customerServiceId" name="id" value="${customerService.id }" />
	 <form:hidden path="id" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${customerService.id}</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="nickName" class="req">客服昵称：</label>
                    </div>
                    <div class="input">
                        <form:input path="nickName" cssClass="small required" maxlength="100"/>
                        
                    </div>
                </div>
              
                <div class="field">
                    <div class="label">
                        <label for="code" class="req">飞信代码：</label>
                    </div>
                    <div class="input">
                    <div class="controls controls-row">
						 <a href='http://space.feixin.10086.cn/callme/login?ul=http://space.feixin.10086.cn/callme/' target='_blank'>查看飞信代码</a>
           				 </div>
                    <form:textarea path="code" cssClass="small required" maxlength="2000" ></form:textarea>
                    </div>
                </div>
                
                 <div class="field">
                    <div class="label">
                        <label for="code">飞信代码：</label>
                    </div>
                    <div class="input">
                    <span>${customerService.code}</span>
                    </div>
                </div>


                

                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/customerservice/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>

</body>
</html>