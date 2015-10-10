<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
<script type="text/javascript">

$().ready(function() {
	 var selecthtml = $("#qdIds").html();
	 
	 $(':radio[name="selectChannel"]').click(function(){
		    var val= $(':radio[name="selectChannel"]:checked').val(); 
		    if(val==0) {
			    //$("#channelId").show(); 
			    $("#channelId").hide();
			    
		    }
		    if(val==1) {
			    //$("#channelId").hide(); 
			    $("#channelId").show();  
		    }
	    });
	 
    
	$("#getChannel").click(function(){
		
		selectMultiGoods($("#storeIds").val(),'3',function(ids,txts){
			$("#storeIds").val(ids.join(","));
			$("#storeName").val(txts.join("\n"));
		});
			
	});
	//selectRegion("#areaName","areaCode","areaName",'${pid}',{maxItems:1,showLevel:2});
	
	selectRegionCallBack("#areaName",function(id,txt){ //选择归属地市，自动填充 行政地市
		$("#areaCode").val(id);
		$("#areaName").val(txt);
		//自动填充归属地市
		$("#areaId").val(id);
		$("#areaIdName").val(id);
		
		$("#areaName").focus();
		$("#areaName").blur();
	},'${pid}',{maxItems:1,flag:1});
	
	//selectRegionCallBack("#areaIdName",function(id,txt){ //选择 行政地市
		//自动填充归属地市
	//	$("#areaId").val(id);
	//	$("#areaIdName").val(txt);
	//},'${pid}',{maxItems:1,index:100});
	
	 $("#addRow").click(function(){
			
		   // tr_id = $("#TbData>tbody>tr:last").attr("id"); 
		    tr_id = $("#TbData tr").size(); 
		    tr_id++;       
		    str= " <tr  id='tr"+tr_id+"'>"+
		      " <td align='right'>物流渠道:</td><td><select  name='qdIds' >"+selecthtml+"</sclect>"+
				"<td align='right'>运费:</td><td><input id='logisticsFee' name='logisticsFee' class='validate-number' type='text' maxlength='50' value='0' /></td>"+
				"<td align='center' width='30'><a href='javascript:void(0);' onClick=\"deltr('"+tr_id+"')\">删除</a></td>";
		    $('#TbData').append(str);  
		    
	 });
});

function deltr(id){   
    $('#tr'+id).remove();    
} 

function sub(){
	 var val= $(':radio[name="selectChannel"]:checked').val(); 
	    if(val==0) {
	    	$("#storeIds").val("");
			$("#storeName").val("");
			$("#startTime").val("");
			$("#stopTime").val("");
	    }
		    
	
}

</script>

</head>
<body>
	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div  class="title">
				<h5>
					商户基本信息
				</h5>
			</div>
			<!-- end box / title -->
			<form:form method="post" id="fm" commandName="store"
				htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="sub();" >
				<div class="form">
					<div class="fields">
					
					<div class="field">
						
						</div>

						<div class="field">
							<div class="label">
								<label for="name" class="req">商户名称：</label>
							</div>
							<div class="input">
								<!-- small medium large -->
								<form:input path="name" cssClass="small required" maxlength="25" />
								<span class="error" id="advice-required-userPwd"
									style="display:none"></span>
							</div>
						</div>
						
						<div class="field">
							<div class="label">
								<label for="areaName" class="req">归属地市:</label>
							</div>
							<div class="input">
								<form:input path="areaName" cssClass="small required"
									maxlength="100" readonly="true"/>
								<form:hidden path="areaCode"  />	
								<span class="error" id="advice-required-userPwd"
									style="display:none"></span>
							</div>
						</div>
						<div class="field">
                    		<div class="label">
                        	<label for="areaId" >行政编码:</label>
                    		</div>
                    		<div class="input">
                    		<form:hidden path="areaId"  />
                    		<input type="text" name="areaIdName" id="areaIdName" class="small" maxlength="10"  value="${store.areaName }"  readonly="true"/>
                    		</div>
                		</div>
                		
                		<div class="field">
							<div class="label">
								<label for="address" class="req">商户地址:</label>
							</div>
							<div class="input">
								<form:input path="address" cssClass="small required"
									maxlength="50" />
								<span class="error" id="advice-required-userPwd"
									style="display:none"></span>
							</div>
						</div>
						
					

						
							<div class="field">
                    	<div class="label">
                        <label for="linkName" class="req">联系人:</label>
                    	</div>
                    	<div class="input">
                     	<form:input path="linkName" cssClass="small required validate-chinese"  maxlength="50" />
                         <span class="error" id="advice-validate-chinese-linkName" style="display:none"></span>
                    	</div>
               		</div>
						
					<div class="field">
                    	<div class="label">
                        <label for="linkPhone" class="req">联系电话:</label>
                    	</div>
                    	<div class="input">
                     	<form:input path="linkPhone" cssClass="small required validate-mobile-phone" maxlength="13" />
                         <span class="error" id="advice-required-phone" style="display:none"></span>
                    	</div>
                	</div>
                
                	
					<input type="hidden"  name="sort"  value="0" />
                 
                

						<div class="buttons">
							<div class="highlight">
								<input type="submit" name="submit.highlight" value="提交" />
							</div>
							<input type="reset" name="reset" value="重置" /> 
							<input type="button" class="common_btn" onclick="history.back();" value="返回" />
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<!-- end forms -->
	</div>


	<script type="text/javascript">
	<!--
		ajaxFormSubmit('#fm');
	//-->
	</script>



</body>
</html>