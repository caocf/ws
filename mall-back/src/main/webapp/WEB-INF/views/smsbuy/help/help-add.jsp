<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    $().ready(function() {
    //获取来源地址
	var url = document.referrer;
	$("#backUrl").val(url);
  	//限制地区
    selectRegion("#helpAreaName","helpArea","helpAreaName",0,{index:2});
    });
    
    
   
    
    </script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加短信购帮助信息</c:if><c:if test="${method == 'edit'}">修改短信购帮助信息</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add"  commandName="smsbuyHelp"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
        <input type="hidden" id="backUrl" name="backUrl" />
            <div class="fields">
            <c:if test="${method == 'add'}">
            <div class="field">
				<div class="label">
						<label for="rootSpcode"  class="req">选择根特服号：</label>
				</div>
				<div class="input">
						<select name="rootSpcode">
                    	<c:forEach items="${sysSpcodeList }" var="item">
                        	<option value="${item.spcode }" <c:if test="${param.spcode == item.spcode}">selected="selected"</c:if>>${item.spcode }</option>
                        </c:forEach>
                    	</select>
					<span class="error" id="advice-required-rootSpcode" style="display:none"></span>
					</div>
				</div>
            </c:if>
            	<div class="field">
							<div class="label">
								<label for="helpSpcode"  class="req">特服号：</label>
							</div>
							<div class="input">
							<form:input path="helpSpcode" cssClass="small required"  size="10" maxlength="9" />
							<span class="error" id="advice-required-helpSpcode"
									style="display:none"></span>
							</div>
						</div>
            
                 <div class="field">
							<div class="label">
								<label for="helpArea" >地区：</label>
							</div>
							<div class="input">
								<form:input path="helpAreaName" 
									maxlength="100" readonly="true" />
								<form:hidden path="helpArea" />
							</div>
						</div>
						
						
						<div class="field">
                   <div class="label label-radio">
                        <label for="recommendType" class="req">推荐类型：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${recommendTypeMap}" var="item" varStatus="index">
                            <input type="radio" id="recommendType" name="recommendType" class="validate-one-required"  <c:if test="${item.key==smsbuyHelp.recommendType}">checked="checked"</c:if> value="${item.key}"/><label for="radio1-${index.count }">${item.value }</label>
                            </c:forEach>
                        </div>
                        <span class="error" id="advice-validate-one-required-recommendType" style="display:none"></span>
                         </div>
                    </div>
                    
                    
                    <div class="field">
							<div class="label">
								<label for="recommendSpcode"  class="req">回复语特服号：</label>
							</div>
							<div class="input">
								<form:input path="recommendSpcode" cssClass="small required" maxlength="100" />
								<span class="error" id="advice-required-recommendSpcode"
									style="display:none"></span>
							</div>
						</div>
						
						  <div class="field">
							<div class="label">
								<label for="recommendSms"  class="req">回复语模板：</label>
							</div>
							<div class="input">
								<form:textarea path="recommendSms" cssClass="small required max-length-150" cols="60" rows="4"/>
								<span class="error" id="advice-required-recommendSms"	style="display:none"></span>
							</div>
						</div>
                    
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/smsbuy/help/helpList" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>