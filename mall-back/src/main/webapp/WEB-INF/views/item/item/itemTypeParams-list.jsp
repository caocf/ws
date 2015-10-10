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
        <h5>编辑商品参数</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" action="/item/item/editItemParams.do" id="fm"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
        <input type="hidden" name="itemId" value="${itemId }"/>
            <div class="fields" id="itemParamDiv">
            <c:forEach items="${sysTypeItemParamList}" var="item" varStatus="index">
            	<c:if test="${item.paramType==1}">
            	 <div class="field">
                   <div class="label label-radio">
                        <label for="itemParam_${item.id}"  <c:if test="${item.requiredFlag==1}">class="req"</c:if>>${item.paramKey}：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${item.paramValueList}" var="str" varStatus="index1">
                        	
                        
                            <input type="radio" id="radio-${index1.count }"  	<c:forEach items="${itemParamList}" var="realParam" ><c:if test="${str==realParam.paramValue}">checked="checked"</c:if></c:forEach>  name="itemParam_${item.id}" <c:if test="${item.requiredFlag==1}">class="validate-one-required"</c:if>  value="${str}"/><label for="radio-${index1.count }">${str }</label>
            
                              
                            </c:forEach>
                        </div>
                        <span class="error" id="advice-validate-one-required-itemMode_${index.count}" style="display:none"></span>
                         </div>
                    </div>
                </c:if>
                <c:if test="${item.paramType==2}"><tr>
            		<div class="field">
                    <div class="label label-radio">
                        <label  <c:if test="${item.requiredFlag==1}">class="req"</c:if>>${item.paramKey}：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${item.paramValueList}" var="str" varStatus="index1">
                        	
                            <input type="checkbox"  name="itemParam_${item.id}"  <c:forEach items="${itemParamList}" var="realParam" ><c:if test="${str==realParam.paramValue}">checked="checked"</c:if></c:forEach>  value="${str}"   <c:if test="${item.requiredFlag==1}">class="validate-one-required"</c:if> /><label for="checkbox_${index1.count}">${str}</label>
                            
                            </c:forEach>
                        </div>
                        <span class="error" id="advice-validate-one-required-itemParam_${item.id}" style="display:none"></span>
                    </div>
                </div>
               </c:if>
                 <c:if test="${item.paramType==3}">
            		  <div class="field">
                    <div class="label">
                        <label for="itemParam_${item.id}" <c:if test="${item.requiredFlag==1}">class="req"</c:if>>${item.paramKey}：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                    	<c:forEach items="${itemParamList}" var="realParam" >
                    	<c:if test="${item.id==realParam.paramId }">
                        <input name="itemParam_${item.id}" maxlength="100" <c:if test="${item.requiredFlag==1}">class="small required"</c:if> value="${realParam.paramValue}"/>
                        </c:if>
                        </c:forEach>
                        <span class="error" id="advice-validate-one-required-shortName" style="display:none"></span>
                    </div>
                	</div>
                 </c:if>
               </c:forEach>
               <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight"  value="保存" />
                    </div>
                </div>
        </div>
    </form:form>
</div>
</div>
</body>
</html>