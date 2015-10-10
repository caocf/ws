<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
	<script type="text/javascript">
	function importExcel(flag,batchNo){
		if(flag==2){
			showDialog("部分出库","./preImportExcel?flag="+flag+"&batchNo="+batchNo,function(doc){},
					{"Width":900,"Height":600,"ShowMessageRow":false,"ShowButtonRow":false});
		}else{
			showDialog("部分入库","./preImportExcel?flag="+flag+"&batchNo="+batchNo,function(doc){},
					{"Width":900,"Height":600,"ShowMessageRow":false,"ShowButtonRow":false});
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
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                
              <td>卡批次号：</td>
             <td><input type="text" name="batchNo" value="${param.batchNo}" class="txt validate-number" style="width:120px"/></td>
                <td>
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>成品卡批次列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
        	<colgroup>
		       	<col width="80"></col>
		       	<col width="50"></col>
		       	<col width="50"></col>
		       	<col width="50"></col>
		       	<col width="50"></col>
		       	<col width="50"></col>
		       	<col width="150"></col>
	    	</colgroup>
	    	<!-- 
	    		<tr>
			        	<td colspan="7" align="left">
			        	<ct:display model="stock_model" btn="st_imp_btn">
			        	<a class="btn_blue" href="javascript:;" onClick="importExcel(1);"><span>文件入库</span></a>
			        	</ct:display>
			        	<ct:display model="stock_model" btn="st_imp_btn">
			        	<a class="btn_blue" href="javascript:;" onClick="importExcel(2);"><span>文件出库</span></a>
			        	</ct:display>
			        	</td>
        			</tr>
        	 -->
            <tr>
                <th nowrap="nowrap" width="80">卡批次号</th>
                <th nowrap="nowrap"  width="50">需求数量</th>
                <th nowrap="nowrap"  width="50">入库量</th>
                <th nowrap="nowrap"  width="50">出库量</th>
                <th nowrap="nowrap"  width="50">库存量</th>
                <th nowrap="nowrap"  width="50">备注</th>
                <th nowrap="nowrap" width="150">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.batchNo}</td>
                <td nowrap="nowrap">${item.cardNum}</td>
                <td nowrap="nowrap">${item.stocksIn}</td>
                <td nowrap="nowrap">${item.stocksOut}</td>
                <td nowrap="nowrap">
                ${item.stocks}
                </td>
                <td nowrap="nowrap">
                <c:if test="${empty item.stocks}">未入库</c:if>
                ${item.sremark}
                </td>
                <td nowrap="nowrap">
                <c:if test="${empty item.stocks}">
                 <ct:display model="stock_model" btn="st_ai_btn">
                 	&nbsp;&nbsp;
                	 <a href="#" onclick="dealInfo('确认该批次所有礼品卡入库吗？','./stockIn.do?batchNo=${item.batchNo}');">全部入库</a>
                 </ct:display>
                 </c:if>
                 <c:if test="${ item.stocks > 0 }">
                <ct:display model="stock_model" btn="st_ao_btn">
                 	&nbsp;&nbsp;
                  	<a href="#" onclick="dealInfo('确认该批次所有礼品卡出库吗？','./stockOut.do?batchNo=${item.batchNo}');">全部出库</a>
                 </ct:display>
                 </c:if>
                <ct:display model="stock_model" btn="st_bi_btn">
                 	&nbsp;&nbsp;
                  	<a href="#" onclick="importExcel(1,${item.batchNo});">部分入库</a>
                 </ct:display>
                <ct:display model="stock_model" btn="st_bo_btn">
                 	&nbsp;&nbsp;
                  	<a href="#" onclick="importExcel(2,${item.batchNo});">部分出库</a>
                 </ct:display>
                 <!--  <ct:display model="stock_model" btn="st_bi_btn">
	                 &nbsp;&nbsp;
                 	<a href="./cardList?batchNo=${item.batchNo}&flag=1">批量入库</a>
                 </ct:display>
                 <c:if test="${!empty item.stocks && item.stocks != 0}"> 
                 <ct:display model="stock_model" btn="st_bo_btn">
	                 &nbsp;&nbsp;
                	 <a href="./cardList?batchNo=${item.batchNo}&flag=2">批量出库</a>
                 </ct:display>
                 </c:if>-->
                   <ct:display model="stock_model" btn="view_btn">
	                  &nbsp;&nbsp;
                	 <a href="./detailList?batchNo=${item.batchNo}">查看</a>
                  </ct:display>
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