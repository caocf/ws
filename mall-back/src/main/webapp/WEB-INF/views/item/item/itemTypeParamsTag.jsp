<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<c:if test="${ !empty typeItemParamList }">
	<div class="field"  id="sizeDivId" >
        <div class="label label-textarea">
            <label >商品规格:</label>
            </div>
        	<div class="inputs"  >
            <c:forEach items="${typeItemParamList}" var="item" varStatus="index">
            	<c:if test="${item.paramType==1}">
            	 <div class="field">
                   <div class="label label-radio">
                        <label for="itemParam_${item.id}"  <c:if test="${item.requiredFlag==1}">class="req"</c:if>>${item.paramKey}：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${item.paramValueList}" var="str" varStatus="index1">
                            <input type="radio" id="radio-${index1.count }" name="itemParam_${item.id}" <c:if test="${item.requiredFlag==1}">class="validate-one-required"</c:if> <c:if test="${item.userParamValue==str}">checked="checked"</c:if> value="${str}"/><label for="radio-${index1.count }">${str }</label>
                            </c:forEach>
                        </div>
                        <span class="error" id="advice-validate-one-required-itemMode_${index.count}" style="display:none"></span>
                         </div>
                    </div>
                </c:if>
                <c:if test="${item.paramType==2}">
            		<div class="field">
                    <div class="label label-radio">
                        <label  <c:if test="${item.requiredFlag==1}">class="req"</c:if>>${item.paramKey}：</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                        	<c:forEach items="${item.paramValueList}" var="str" varStatus="index1">
                            <input type="checkbox" name="itemParam_${item.id}"  value="${str}" <c:if test="${item.requiredFlag==1}">class="validate-one-required"</c:if> <c:if test="${fn:contains(item.userParamValue,str)}"> checked="checked" </c:if> /><label for="checkbox_${index1.count}">${str}</label>
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
                        <input name="itemParam_${item.id}" maxlength="100" 
                         <c:choose>
                         <c:when test="${empty item.userParamValue}">value="${item.paramValue}"</c:when>
                         <c:otherwise>value="${item.userParamValue}" </c:otherwise>
                         </c:choose>
                         <c:if test="${item.requiredFlag==1}">class="small required" </c:if>/>
                        <span class="error" id="advice-validate-one-required-itemParam_${item.id}" style="display:none"></span>
                    </div>
                	</div>
                 </c:if>
               </c:forEach>
                </div>
        	 </div>
       </c:if> 	 