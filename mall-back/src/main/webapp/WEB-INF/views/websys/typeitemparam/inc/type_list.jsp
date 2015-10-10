<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
   <c:if test="${not empty typeItemParamList}">
        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="80">参数类型</th>
                <th nowrap="nowrap" width="100">参数名</th>
                <th nowrap="nowrap" width="120">参数值</th>
            </tr>

            <c:forEach items="${typeItemParamList}" var="item">
            <tr>
                <td nowrap="nowrap">${empty item.shopId ? '通用参数':'商户自定义参数'}</td>
                <td nowrap="nowrap">${item.paramKey}</td>
                <td nowrap="nowrap">${item.paramValue}</td>
            </tr>
            </c:forEach>
        </table>
        </c:if>
        <c:if test="${empty typeItemParamList}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>