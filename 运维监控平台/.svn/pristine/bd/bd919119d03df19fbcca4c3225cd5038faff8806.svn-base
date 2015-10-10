<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/t.jsp"%>

<div class="row-fluid">
    <div class="span12">
        <div class="page-header">
            <h4>用户管理</h4>
        </div>

        <div style="text-align: right; margin: 15px 0px">
            <a href="${ctx}/sysMgmt/userMgmt/add" style="padding-right: 20px">
                <font style="font-weight: bold;" class="btn">创建用户</font>
            </a>
        </div>

        <c:if test="${empty dcUserPage.data}">
            <div class="alert alert-info">
                <strong>
                    <i class="icon24 i-info"></i>
                    当前没有相关记录！
                </strong>
            </div>
        </c:if>

        <c:if test="${not empty dcUserPage.data}">
            <table class="table table-bordered table-hover table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>角色</th>
                        <th>电话</th>
                        <th>邮箱</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dcUserPage.data}" var="item" varStatus="dcUser">
                        <tr>
                            <td class="center vcenter">${item.id}</td>
                            <td class="center vcenter">${item.user_code}</td>
                            <td class="center vcenter">${item.user_name}</td>
                            <td class="center vcenter">${item.role_name }</td>
                            <td class="center vcenter">${item.TERMINAL_ID}</td>
                            <td class="center vcenter">${item.email}</td>
                            <td class="center vcenter">
                                <c:if test="${item.id != 0}">
                                    <a href="${ctx}/sysMgmt/userMgmt/view/${item.id}">查看</a>
                                    |
                                    <a href="${ctx}/sysMgmt/userMgmt/edit/${item.id}">编辑</a>
                                    |
                                    <a href="${ctx}/sysMgmt/userMgmt/authority/${item.id}">权限</a>
                                    |
                                    <a href="${ctx}/sysMgmt/userMgmt/del/${item.id}" data-confirm="确定删除？">删除</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <ht:page pageData="${dcUserPage}" />
        </c:if>

    </div>
</div>

<%@ include file="../../includes/b.jsp"%>
