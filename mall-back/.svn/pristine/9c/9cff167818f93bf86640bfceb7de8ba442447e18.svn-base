<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
	<script type="text/javascript">
	function linkItem(batchNo){
		selectLinkItemCallBack(function(itemId){
				//alert(batchNo+"------"+itemId);
				if(typeof(itemId) != 'undefined'){
				$.ajax({
					type: "post",
					url: "./linkItem?batchNo="+batchNo+"&itemId="+itemId,
					dataType : "json",
					success: function(data){
						if(data.success == "error"){
							simpleWarn(data.message,null);
						}else{
							if(data.success){
								simpleAlert("关联成功", function() {
									window.location.reload();
				                	});
							}else{
								simpleAlert(data.message,null);
							}
						}
					},
					error: function(){
						simpleWarn('程序异常，请联系管理员解决！',null);
					}
					});
				}
	 		});
	}
	function selectLinkItemCallBack(callback,opts){
		var param = $.extend({	
			ShowMessageRow:false,
			Height: 400,
			Width : 800},opts||{});
		
		var url = G_CTX_ROOT + '/gift/relateItem/selectItem?iseckillFlag=-2';
		
		showDialog("选择商品",url, function(doc,win){
			var selectRadio = $("input[name='activeIdSelector']:checked",doc);
			if(!jQuery.isEmptyObject(selectRadio)){
				var itemId =selectRadio.val();
				if(jQuery.isFunction(callback)){
					callback(itemId,doc,win);
				}
			}
		},param);
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
            <tr>
               		    <th nowrap="nowrap" width="120">批次号</th>
               		    <th nowrap="nowrap" width="120">售卖编号</th>
		            	<th nowrap="nowrap" width="120">卡型号</th>
		            	<th nowrap="nowrap" width="120">客户名</th>
		            	<th nowrap="nowrap" width="120">需要礼品卡数量</th>
		            	<th nowrap="nowrap" width="120">期望发卡时间</th>
						<th nowrap="nowrap" width="120">需求提交时间</th>
						<th nowrap="nowrap" width="80">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
              	    <td  nowrap="nowrap">${item.batchNo}</td>
              	    <td  nowrap="nowrap">${item.saleId}</td>
	            	<td  nowrap="nowrap" class="ellipsis">${item.modelNo}</td>
	            	<td  nowrap="nowrap" class="ellipsis">${item.requiredUser}</td>
	            	<td  nowrap="nowrap">${item.cardNum}</td>
	            	<td  nowrap="nowrap"><ct:time source="${item.issuingTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd hh:mm:ss" /></td>
	            	<td  nowrap="nowrap"><ct:time source="${item.createdTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd hh:mm:ss" /></td>
	                <td nowrap="nowrap">
	                	&nbsp;&nbsp;
	                	<ct:display model="gift_link" btn="linkItem_btn">
	                  	<a href="#" onclick="linkItem(${item.batchNo});">关联商品</a>
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