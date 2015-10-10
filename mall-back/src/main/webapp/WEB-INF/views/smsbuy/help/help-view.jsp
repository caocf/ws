<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>短信购帮助信息</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add" commandName="smsbuyHelp"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
            <div class="fields">
            
            	<div class="field">
							<div class="label">
								<label for="helpSpcode"  class="req">特服号：</label>
							</div>
							<div class="input">${smsbuyHelp.helpSpcode}
							</div>
						</div>
            
                 <div class="field">
							<div class="label">
								<label for="helpArea">地区：</label>
							</div>
							
							<div class="input">
							<c:if test="${smsbuyHelp.helpAreaName != null}">${smsbuyHelp.helpAreaName }</c:if>
							<c:if test="${smsbuyHelp.helpAreaName == null}">全国</c:if>
							</div>
						</div>
						
						
						<div class="field">
                   <div class="label label-radio">
                        <label for="recommendType">推荐类型：</label>
                    </div>
                    <div class="radios">${smsbuyHelp.recommendTypeName}
                     </div>
                    </div>
                    
                    
                    <div class="field">
							<div class="label">
								<label for="recommendSpcode"  class="req">回复语特服号：</label>
							</div>
							<div class="input">${smsbuyHelp.recommendSpcode}
							</div>
						</div>
						
						  <div class="field">
							<div class="label">
								<label for="recommendSms"  class="req">回复语模板：</label>
							</div>
							<div class="input">${smsbuyHelp.recommendSms}
							</div>
						</div>
                
                <div class="buttons">
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