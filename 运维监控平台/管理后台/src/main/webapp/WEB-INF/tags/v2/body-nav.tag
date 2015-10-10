<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="loginuser" value="${sessionScope.session_dc_user_key__}" />

<aside id="sidebar" data-spy="affix-not-use" data-offset-top="0">
    <div class="side-options">
        <ul>
            <li><a href="#" id="collapse-nav" class="act act-primary tip" title="Collapse navigation"><i class="icon16 i-arrow-left-7"></i></a></li>
        </ul>
    </div>

    <div class="sidebar-wrapper">
        <nav id="mainnav">
            <ul class="nav nav-list">
                <c:forEach items="${loginuser.menus}" var="level1" varStatus="lv1">
                    <li><c:if test="${level1.menuUrl eq '#'}">
                            <a href="#">
                        </c:if> <c:if test="${level1.menuUrl ne '#'}">
                            <a href="<c:url value="${level1.menuUrl}"/>">
                        </c:if> <span class="txt">${level1.menuName}</span> </a> 
                        <c:if test="${fn:length(level1.childMenus) > 0}">
                            <ul class="sub">
                                <c:forEach items="${level1.childMenus}" var="level2" varStatus="lv2">
                                    <li>
                                    <a href="<c:url value="${level2.menuUrl}"/>"> <span class="icon"></span> 
                                    <span class="txt">${level2.menuName}</span>
                                    </a>
                                    
                                    <c:if test="${fn:length(level2.childMenus) > 0}">
		                            <ul class="sub">
		                                <c:forEach items="${level2.childMenus}" var="level3" varStatus="lv3">
		                                    <li>
		                                    <a href="<c:url value="${level3.menuUrl}"/>"> <span class="icon"></span> 
		                                    <span class="txt">${level3.menuName}</span>
		                                    </a>
		                                    
		                                <c:if test="${fn:length(level3.childMenus) > 0}">
			                            <ul class="sub">
			                                <c:forEach items="${level3.childMenus}" var="level4" varStatus="lv4">
			                                    <li>
			                                    <a href="<c:url value="${level4.menuUrl}"/>"> <span class="icon"></span> 
			                                    <span class="txt">${level4.menuName}</span>
			                                    </a>
			                                    
			                                    <c:if test="${fn:length(level4.childMenus) > 0}">
					                            <ul class="sub">
					                                <c:forEach items="${level4.childMenus}" var="level5" varStatus="lv5">
					                                    <li>
					                                    <a href="<c:url value="${level5.menuUrl}"/>"> <span class="icon"></span> 
					                                    <span class="txt">${level5.menuName}</span>
					                                    </a>
					                                    </li>
					                                </c:forEach>
					                            </ul>
					                        	</c:if>
			                                    
			                                    </li>
			                                </c:forEach>
			                            </ul>
			                        	</c:if>
		                                    
		                                    
		                                    </li>
		                                </c:forEach>
		                            </ul>
		                        	</c:if>
                                    
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                     </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</aside>