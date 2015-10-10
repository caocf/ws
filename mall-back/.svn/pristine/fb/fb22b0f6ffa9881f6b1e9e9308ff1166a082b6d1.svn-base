<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:if test="${pagination.totalPageCnt>0}">
	<div class="manu">
		<input type="hidden" id="pageIndex" value="${pagination.pageIndex }" />
		<input type="hidden" id="totalPageCnt"
			value="${pagination.totalPageCnt}" />
		<input type="hidden" id="url" value="${pagination.url}" />
		<c:if test="${pagination.pageIndex==1}">
			<span > 首页</span>
			<span > < </span>
		</c:if>
		<c:if test="${pagination.pageIndex!=1}">
			<a href="#" onclick="turnPage(1)">首页</a>
			<a href="#" onclick="turnPage(${pagination.pageIndex-1 })"> < </a>
		</c:if>

		<c:if test="${pagination.totalPageCnt<=10}">
			<c:forEach begin="1" end="${pagination.totalPageCnt}" step="1"
				var="index">
				<c:if test="${pagination.pageIndex==index}">
					<span  onclick="turnPage(${index })">${index}</span>
				</c:if>
				<c:if test="${pagination.pageIndex!=index}">
					<a href="#" onclick="turnPage(${index })">${index}</a>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${pagination.totalPageCnt>10}">
			<c:set var="val"
				value="${pagination.pageIndex%7==0 ? 7 : pagination.pageIndex%7}"></c:set>
			<c:set var="from" value="${pagination.pageIndex-val}"></c:set>
			<c:if test="${(from+7)<pagination.totalPageCnt}">
				<c:forEach begin="${from+1}" end="${from+7}" step="1" var="ind">
					<c:if test="${pagination.pageIndex==ind}">
						<span  onclick="turnPage(${ind})">${ind}</span>
					</c:if>
					<c:if test="${pagination.pageIndex!=ind}">
						<a href="#" onclick="turnPage(${ind })">${ind}</a>
					</c:if>
				</c:forEach>
 		...
 		<a href="#" onclick="turnPage(${pagination.totalPageCnt-1})">${pagination.totalPageCnt-1}</a>
				<a href="#" onclick="turnPage(${pagination.totalPageCnt})">${pagination.totalPageCnt}</a>
			</c:if>
			<c:if test="${(from+7)>=pagination.totalPageCnt}">
				<c:forEach begin="${from}" end="${pagination.totalPageCnt}" step="1"
					var="ind">
					<c:if test="${pagination.pageIndex==ind}">
						<span  onclick="turnPage(${ind})">${ind}</span>
					</c:if>
					<c:if test="${pagination.pageIndex!=ind}">
						<a href="#" onclick="turnPage(${ind })">${ind}</a>
					</c:if>
				</c:forEach>
			</c:if>
		</c:if>

		<c:if test="${pagination.pageIndex==pagination.totalPageCnt}">
			<span >> </span>
			<span >末页</span>
		</c:if>
		<c:if test="${pagination.pageIndex!=pagination.totalPageCnt}">
			<a href="#" onclick="turnPage(${pagination.pageIndex+1})">>
			</a>
			<a href="#" onclick="turnPage(${pagination.totalPageCnt})">末页</a>
		</c:if>

	</div>
</c:if>
