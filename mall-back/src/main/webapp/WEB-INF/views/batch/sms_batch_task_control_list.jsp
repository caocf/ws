<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
        <script type="text/javascript">
	        var setDxgqryBlock = function (o) {
	    		if($(o).val()==2) {
	    			$('#dxg_qry_block').show();
	    		}else {
	    			$('#dxg_qry_block').hide();
	    			$('#actId').val('');
	    			$('#itemId').val('');
	    		}
	    	}
        </script>
    </head>
<body>
<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
        
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="smsmanagerlist" method="get">
        <table>
            <tr>
                
                <td width="70">任务名称：</td>
                <td><input type="text" name="taskName" value="${param.taskName}" class="txt" style="width:206px"/></td>
               
                <td width="70">任务状态：</td>
                <td>
	               <select name="status">
                        <option value="">--请选择--</option>
                        <option value="0" <c:if test="${param.status == 0}">selected="selected"</c:if>>未审核</option>
                        <option value="1" <c:if test="${param.status == 1}">selected="selected"</c:if>>待发送</option>
                        <option value="2" <c:if test="${param.status == 2}">selected="selected"</c:if>>审核不通过</option>
                        <option value="3" <c:if test="${param.status == 3}">selected="selected"</c:if>>暂停</option>
                        <option value="4" <c:if test="${param.status == 4}">selected="selected"</c:if>>发送中</option>
                        <option value="5" <c:if test="${param.status == 5}">selected="selected"</c:if>>发送完毕</option>
                        <option value="6" <c:if test="${param.status == 6}">selected="selected"</c:if>>发送失败</option>
                        <option value="7" <c:if test="${param.status == 7}">selected="selected"</c:if>>任务预处理</option>
                        <option value="8" <c:if test="${param.status == 8}">selected="selected"</c:if>>预处理完成，等待发送</option>
                        <option value="9" <c:if test="${param.status == 9}">selected="selected"</c:if>>取消</option>
                    </select>
                </td>
                 </tr>
                <tr>
                <td>信息内容：</td>
                <td><input type="text" name="title" value="${param.title}" class="txt" style="width:206px"/></td>
                <td width="70">创建时间：</td>
                <td width="300"/>
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime == null ? inputStartTime : param.inputStartTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime == null ? inputStartTime : param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime == null ? inputEndTime : param.inputEndTime}"  class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime == null ? inputEndTime : param.endTime}" />
                </td>
                 </tr>
                <tr>
                <td>申请人：</td>
                <td><input type="text" name="userName" value="${param.userName}" class="txt" style="width:206px"/></td>
                
                <td>群发类型：</td>
                <td>
	               <select name="batchType" onchange='setDxgqryBlock(this);'>
                        <option value="">--请选择--</option>
                        <option value="1" <c:if test="${param.batchType == 1}">selected="selected"</c:if>>普通群发</option>
                        <option value="2" <c:if test="${param.batchType == 2}">selected="selected"</c:if>>短信购</option>                        
                    </select>
                </td> 
                <td>&nbsp;
                    <ct:btn type="search" />
                </td>              
            </tr>
            <tr id='dxg_qry_block' style='display:<c:if test="${empty param.batchType || param.batchType == 1}">none</c:if>'>                 
                <td>活动编号：</td>
                <td><input type="text" name="actId" value="${param.actId}" class="txt" style="width:206px"/></td>
                <td>商品编号：</td>
                <td>
                <input type="text" name="itemId" value="${param.itemId}" class="txt" style="width:206px"/>
                <input type="hidden" name="routerId" value="${param.routerId}">
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>SMS群发任务管理列表</h3>
    <div class="mainbox">
        <c:if test="${not empty taskPage}">


        <table class="datalist fixwidth">
        <colgroup>
			        	<col width="50"></col>
			        	<col width="80"></col>
			        	<col width="40"></col>
			        	<col width="150"></col>
			        	<col width="45"></col>
			        	<col width="45"></col>
			        	<col width="45"></col>
			        	<col width="75"></col>
			        	<col width="60"></col>
        			</colgroup>
            <tr>
                <th nowrap="nowrap" >任务编号</th>
                <th nowrap="nowrap" >任务名称</th>
                <th nowrap="nowrap" >群发类型</th>
                <th nowrap="nowrap" >发送内容</th>
                <th nowrap="nowrap" >提交用户数</th>
                <th nowrap="nowrap" >成功用户数</th>
                <th nowrap="nowrap" >状态</th>
                <th nowrap="nowrap" >创建时间</th>                              
                <th nowrap="nowrap" >操作</th>
            </tr>

            <c:forEach items="${taskPage.data}" var="item">
            <tr>
               <td nowrap="nowrap">${item.id}</td>
               <td nowrap="nowrap">${item.taskName}</td>
               <td nowrap="nowrap">
               		<c:if test="${item.batchType==1}">
               			普通群发
               		</c:if>
               		<c:if test="${item.batchType==2}">
               			短信购
               		</c:if>
               </td>
                <td nowrap="nowrap"  class="ellipsis">${item.title}</td>
                <td nowrap="nowrap">${item.submitCnt}</td>
                <td nowrap="nowrap">${item.successCnt}</td>
                <td nowrap="nowrap">${item.statusName}</td>
                <td nowrap="nowrap" width="10%"><ct:time source="${item.createTime}"/></td>
                
                <td width="10%" nowrap="nowrap">
                <ct:display model="sms_manager_list" btn="view_btn">
                        <a href="view/controlList/${item.id}">查看</a>
                    </ct:display>
                    &nbsp;&nbsp;
                 <c:if test="${item.status==3}">
                    <ct:display model="sms_manager_list" btn="start_btn">
                        <a href="#" onclick="dealInfo('确定执行？','process/renew/${item.id}');">执行</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="sms_batch_list" btn="del_btn">
                        <a href="#" onclick="deleteItem('delete/${item.id}/smsmanagerlist');">删除</a>
                    </ct:display>
                   </c:if> 
                    <c:if test="${item.status==4}">
                    <ct:display model="sms_manager_list" btn="pause_btn">
                        <a href="#" onclick="dealInfo('确定暂停？','process/pause/${item.id}');">暂停</a>
                    </ct:display>
                    &nbsp;&nbsp;
                   </c:if> 
                   <c:if test="${item.status==4}">
                    <ct:display model="sms_manager_list" btn="cancel_btn">
                        <a href="#" onclick="dealInfo('确定取消？','process/cancel/${item.id}');">取消</a>
                    </ct:display>
                    &nbsp;&nbsp;
                   </c:if> 
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${taskPage}" />

        </c:if>
        <c:if test="${empty taskPage}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>