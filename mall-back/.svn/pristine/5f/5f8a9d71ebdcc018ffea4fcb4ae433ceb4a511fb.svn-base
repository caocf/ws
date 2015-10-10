<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
	$().ready(function() {
		selectRegion("#areaName","regionCode","areaName",${regionCode});	
	})
	</script>
</head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加投放区域</c:if><c:if test="${method == 'edit'}">修改投放区域</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="sysAdRegion" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <input type="hidden" id="adId" name="adId" value="${adId}" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">广告：</div>
                    <div class="input">${adName}</div>
                </div>
                <c:if test="${method == 'edit'}">
                <c:if test="${not empty adRegionList}">
                <div class="field">
                    <div class="label noinput">已配置投放区域：</div>
                    <div class="input">
                    <c:forEach items="${adRegionList}" var="item" varStatus="i">
                         <label >${item.regionName}</label>
                         <a href="#this" onclick="deleteItem('./adr_delete/${item.id}/${adId}/${adName}');">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           
                         <c:if test="${i.count % 5 eq 0}"><br /><br /></c:if>                
                    </c:forEach>
					</div>
                </div>
                </c:if>
                </c:if>
                <div class="field">
                    <div class="label noinput"><c:if test="${method == 'edit'}">添加</c:if>投放区域：</div>
                    <div class="input">
                    <input type="text" id="areaName" name="areaName" readonly class="small required min-length-0 max-length-50" maxlength="50" />
                	<span class="error" id="advice-required-areaName" style="display:none"></span>
                    <span class="error" id="advice-min-length-areaName" style="display:none"></span>
                    <span class="error" id="advice-max-length-areaName" style="display:none"></span>
                    <span class="error" id="advice-server-areaName" style="display:none"></span>
                    <input type="hidden" id="regionCode" name="regionCode" />
                    </div>
                </div>
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/ad/ad_list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>