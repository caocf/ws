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
			<ht:menu-btn type="search" />
			<ct:display model="lottery_prize_list" btn="add_btn">
			<ht:menu-btn text="添加奖品" url="/lottery/prize/add" />
			</ct:display>
		</ul>
		<br style="clear: left" />
	</div>
	<div class="queryContainer" >
		    <form name="queryForm" id="queryForm" action="?" method="get">
		        <table>
		            <tr>
		            	<td>奖品名称：</td>
		                <td><input type="text" name="name" value="${param.name}" class="txt" style="width:150px"/></td>		 
		                <td>奖品编号：</td>
		                <td><input type="text" name="id" title="奖品编号" value="${param.id}" class="txt validate-number" style="width:150px"/></td>
		                </tr>
		                <tr>     
		                <td>活动状态：</td>
						<td width="200"><select name="status">
							<option value="">--请选择--</option>
							<c:forEach items="${statusMap}" var="item" varStatus="index">
								<option value="${item.key }"
									<c:if test="${item.key == param.status}">selected="selected"</c:if>>${item.value}</option>
							</c:forEach>
					</select>
					           
		             <td width="70">添加时间：</td>
                <td />
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly
             onfocus="WdatePicker({vel:'beginTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2050-10-01\'}'})" />
                    <input type="hidden" name="beginTime" id="beginTime" value="${param.beginTime}"/>
                   	至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}"  class="txt Wdate"
                           readOnly 
             onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2050-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
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
		    <h3>数据查询</h3>
		    <div class="mainbox">
		        <c:if test="${not empty pageData.data}">
		        <table class="datalist fixwidth">
		            <tr>
		            	<th nowrap="nowrap" width="80">奖品编号</th>
		                <th nowrap="nowrap" >奖品名称</th>
		                <th nowrap="nowrap" width="100">归属活动</th>
		                <th nowrap="nowrap" width="80">归属活动状态</th>
		                <th nowrap="nowrap" width="150">奖品数量</th>
		                <th nowrap="nowrap" width="150">中奖概率</th>
		                <th nowrap="nowrap">操作</th>
		            </tr>
		
		            <c:forEach items="${pageData.data}" var="item">
		            <tr>
		            	<td  nowrap="nowrap">${item.id}</td>
		                <td  nowrap="nowrap" class="ellipsis">${item.name }</td>
		                <td  nowrap="nowrap" >${item.activeName }</td>
		               	<td  nowrap="nowrap" >${item.statusName }</td>
		                <td  nowrap="nowrap">${item.numbers}</td>
		                <td  nowrap="nowrap">${item.hitProbability}</td>
		                <td nowrap="nowrap">
		                		<ct:display model="lottery_prize_list" btn="view_btn">
		               			<a href="view/${item.id}">查看</a>
		               			</ct:display>
		               			<ct:display model="lottery_prize_list" btn="mod_btn">
								<a href="edit?id=${item.id}&actId=${item.activeId}">修改</a>
								</ct:display>
								<ct:display model="lottery_prize_list" btn="del_btn">
								<a style="cursor: pointer;" onclick="deleteItem('delete/${item.id}');">删除</a>
								</ct:display>
						</td>
		            </tr>
		            </c:forEach>
		        </table>		
		        <ht:page pageData="${pageData}" />		
		        </c:if>
		        <c:if test="${empty pageData.data}">
		        <div class="note">
		            <p class="i">目前没有相关记录!</p>
		        </div>
		        </c:if>
		    </div>	
		</div>	
	</body>
</html>