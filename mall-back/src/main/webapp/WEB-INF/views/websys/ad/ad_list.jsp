<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
</head>
<body>
<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
        <ct:display model="ad_list" btn="add_btn">
        	<ht:menu-btn text="添加广告" url="/websys/ad/ad_add" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="ad_list" method="get">
        <table>
            <tr>
                <td width="70">名称：</td>
                <td width="150"><input type="text" name="adName" value="${param.adName}" class="txt" style="width:206px"/></td>
                <td width="70">开始时间：</td> 	
				<td width="300">
                    <input type="text" id="inputStartTimeS" name="inputStartTimeS" value="${param.inputStartTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTimeBegin',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTimeS\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTimeBegin" id="startTimeBegin" value="${param.startTimeBegin}"/>
                    至
                    <input type="text" id="inputEndTimeS" name="inputEndTimeS" value="${param.inputEndTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTimeEnd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTimeS\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="startTimeEnd" id="startTimeEnd" value="${param.startTimeEnd}" />
                </td>
            </tr>
            <tr>
            	<td>负责人：</td>
                <td><input type="text" name="linkman" value="${param.linkman}" class="txt" style="width:206px"/></td>
            	<td>有效期：</td>
				<td>
                    <input type="text" id="inputStartTimeV" name="inputStartTimeV" value="${param.inputStartTimeV}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'validTimeBegin',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTimeV\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="validTimeBegin" id="validTimeBegin" value="${param.validTimeBegin}"/>
                    至
                    <input type="text" id="inputEndTimeV" name="inputEndTimeV" value="${param.inputEndTimeV}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'validTimeEnd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTimeV\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="validTimeEnd" id="validTimeEnd" value="${param.validTimeEnd}" />
                </td>
            </tr>
            <tr>
            	<td width="70">位置：</td>
                <td width="150"><input type="text" name="positionName" value="${param.positionName}" class="txt" style="width:206px"/></td>
            	<td>状态：</td>
                <td>
                    <select name="status">
                        <option value="">--请选择--</option>
                        <c:forEach items="${statusMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>       
                <td>类别：</td>
                <td>
                    <select name="adFlag">
                        <option value="">--请选择--</option>
                        <c:forEach items="${adFlagMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.adFlag == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>类型：</td>
                <td>
                    <select name="adType">
                        <option value="">--请选择--</option>
                        <c:forEach items="${adTypeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.adType == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>广告列表</h3>

    <div class="mainbox">
        <c:if test="${not empty adPage}">

        <table class="datalist fixwidth">
         <colgroup>
			        	<col width="80"></col>
			        	<col width="80"></col>
			        	<col width="80"></col>
			        	<col width="30"></col>
			        	<col width="50"></col>
			        	<col width="120"></col>
			        	<col width="50"></col>
			        	<col width="50"></col>
			        	<col width="50"></col>
			        	<col width="50"></col>
			        	<col width="80"></col>
        			</colgroup>
            <tr>
                <th nowrap="nowrap" width="80">名称</th>
                <th nowrap="nowrap" width="80">位置</th>
                <th nowrap="nowrap" width="220">投放区域</th>
                <th nowrap="nowrap" width="30">类型</th>
                <th nowrap="nowrap" width="50">类别</th>
                <th nowrap="nowrap" width="120">有效期</th>
                <th nowrap="nowrap" width="50">负责人</th>
                <th nowrap="nowrap" width="40">点击次数</th>
                <th nowrap="nowrap" width="40">展现次数</th>
                <th nowrap="nowrap" width="40">状态</th>
                <th nowrap="nowrap" width="80">操作</th>
            </tr>

            <c:forEach items="${adPage.data}" var="item">
            <tr>
                <td nowrap="nowrap" class="ellipsis">${item.adName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.positionName}</td>
                <td nowrap="nowrap">
                <c:if test="${item.isRegion == '0'}">
                <ct:display model="ad_list" btn="add_btn">
                	<a href="./ad_add_region?adId=${item.id}&adName=${item.adName}">添加</a>
                </ct:display>
                </c:if>
                <c:if test="${item.isRegion == '1'}">
                  ${item.position}&nbsp;&nbsp;&nbsp;
                <ct:display model="ad_list" btn="mod_btn">
                	<a href="./ad_edit_region?adId=${item.id}&adName=${item.adName}">修改</a>
                </ct:display>
                </c:if>
                </td>
                <td nowrap="nowrap">${item.adTypeName}</td>
                <td nowrap="nowrap">${item.adFlagName}</td>
                <td nowrap="nowrap"><ct:time source="${item.startTime}" tfmt="yyyy-MM-dd"/>~<ct:time source="${item.validTime}" tfmt="yyyy-MM-dd"/></td>
                <td nowrap="nowrap" class="ellipsis">${item.linkman}</td>
                <td nowrap="nowrap">${item.clickCnt}</td>
                <td nowrap="nowrap">${item.viewCnt}</td>
                <td nowrap="nowrap">${item.statusName}</td>
                <td nowrap="nowrap">
                <ct:display model="ad_list" btn="view_btn">
                   	<a href="./ad_view?id=${item.id}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                <c:if test="${item.status != '1'}">
                <ct:display model="ad_list" btn="mod_btn">
                   	<a href="./ad_edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                <br />
                <ct:display model="ad_list" btn="audit_btn">
                   	<a href="./ad_auditing?id=${item.id}">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
              
                </c:if>
                  <ct:display model="ad_list" btn="del_btn">
                   	<a href="#" onclick="deleteItem('./ad_delete/${item.id}');">删除</a>
                </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${adPage}" />

        </c:if>
        <c:if test="${empty adPage}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>