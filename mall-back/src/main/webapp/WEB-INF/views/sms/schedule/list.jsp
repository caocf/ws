<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>	
<html>
<head>
	<ht:head/>
	<script type="text/javascript">
		function del(url,customCallBack){ 
			dealInfo("确定要删除群发任务排期吗？",url,customCallBack);
		}

		function update(url,customCallBack){ 
			dealInfo("确定要取消吗？",url,customCallBack);
		}
	</script>
</head>
<body>
<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
        <ct:display model="sms_schedule_list" btn="add_btn">
        	<ht:menu-btn text="创建任务" url="/sms/schedule/preAdd" type="add"/>
        </ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                
             <td width="70">创建帐号：</td>
             <td width="350"><input type="text" name="userCode" value="${param.userCode}" class="txt" style="width:110px"/></td>
             
                <td width="70">状态：</td>
                <td width="200">
                    <select name="status">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${statusMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
               </tr>
               <tr> 
                <td width="70">创建时间：</td>
                <td width="300">
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
                </td>
                <td>
                    
                </td>
                 <tr> 
                <td width="70">排期时间：</td>
                <td width="300">
                    <input type="text" id="inputPStartTime" name="inputPStartTime" value="${param.inputPStartTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'pStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputPEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="pStartTime" id="pStartTime" value="${param.pStartTime}"/>
                    至
                    <input type="text" id="inputPEndTime" name="inputPEndTime" value="${param.inputPEndTime}" class="txt Wdate"
                           readOnly onclick="WdatePicker({vel:'pEndTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputPStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="pEndTime" id="pEndTime" value="${param.pEndTime}" />
                </td>
                <td>
                    <ct:btn type="search" />
                </td>
            </tr>                           
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>群发任务排期</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <colgroup>
		        	<col width="70"></col>
		        	<col width="80"></col>
		        	<col width="80"></col>
		        	<col width="60"></col>
		        	<col width="80"></col>
		        	<col width="90"></col>
		        	<col width="150"></col>
		        	<col width="150"></col>
	      		</colgroup>
                <th nowrap="nowrap" >编号</th>
                <th nowrap="nowrap" >排期</th>
                <th nowrap="nowrap" >创建账号</th>
                <th nowrap="nowrap" >处理状态</th>
                <th nowrap="nowrap" >上传号码数</th>
                <th nowrap="nowrap" >过滤后号码数</th>
                <th nowrap="nowrap" >创建时间</th>
                <th nowrap="nowrap" >结束时间</th>                
                <th nowrap="nowrap" >操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap"><ct:time source="${item.dateTime}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd" /></td>
                <td nowrap="nowrap">${item.userCode}</td>
                <td nowrap="nowrap">${statusMap[item.status]}</td>
                <td nowrap="nowrap">${item.uploadTerminalCount}</td>
                <td nowrap="nowrap">
                    ${item.resultTerminalCount }               	           	
                </td>
                <td nowrap="nowrap"><ct:time source="${item.createTime}"/></td>
                <td nowrap="nowrap"><ct:time source="${item.endTime}"/></td>
                <td nowrap="nowrap">
               	<c:if test="${item.status!=1 || item.status!=2 || item.status!=5}">
               		<ct:display model="sms_schedule_list" btn="del_btn">
                		<a href="#" onclick="del('./delete.do?id=${item.id}');">删除</a>
                	</ct:display>
                </c:if>
                <c:if test="${item.status==1}">
                	<ct:display model="sms_schedule_list" btn="cancel_btn">
                		<a href="#" onclick="update('./updateStatus.do?id=${item.id}&status=2');">取消</a>
                	</ct:display>
                </c:if>
                <c:if test="${! empty item.resultTerminalCount && loginName==item.userCode}">
                	<ct:display model="sms_schedule_list" btn="download_btn">
               		 	<a href="${ctx}/sms/schedule/downfile/${item.id}">下载文件</a>&nbsp;&nbsp;
               		</ct:display>
               	</c:if>  
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${pageData}" />

        </c:if>
        <c:if test="${empty pageData}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>