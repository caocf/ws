<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
 $().ready(function() {
	 
	 var selecthtml = $("#qdIds").html();
	 
	 
	 selectRegionCallBack("#areaName",function(id,txt){ //选择归属地市，自动填充 行政地市
		$("#areaCode").val(id);
		$("#areaName").val(txt);
		//自动填充归属地市
		$("#areaId").val(id);
		$("#areaIdName").val(txt);
	},'${pid}',{maxItems:1,showLevel:2});
	 
	 
	selectRegionCallBack("#areaIdName",function(id,txt){ //选择 行政地市
		//自动填充归属地市
		$("#areaId").val(id);
		$("#areaIdName").val(txt);
	},'${pid}',{maxItems:1,showLevel:2,index:100}); 
	

		$("#selectStore").click(function() {
			selectMultiGoods($("#jsStoreIds").val(),'2',function(ids,txts){
				$("#jsStoreIds").val(ids.join(","));
				$("#storeName").val(txts.join("\n"));
			});
		});
		
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
    </script>
    
</head>
<body>
<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>代理结算商户管理</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="agent" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" >
        <div class="form">
        <input type="hidden" id="qdStoreId" name="qdStoreId" value="${agent.qdStoreId }" />
             <div class="fields">
                <div class="field">
                    <div class="label label-radio">
                        <label>是否折扣商户代理:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-3" name="shopAgentFlag" value="1"/><label for="radio-3">是</label>
                            <input type="radio" id="radio-4" name="shopAgentFlag" checked="checked" value="0"/><label for="radio-4">否</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label label-radio">
                        <label>是否商品销售代理:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-3" name="goodsAgentFlag" value="1"/><label for="radio-3">是</label>
                            <input type="radio" id="radio-4" name="goodsAgentFlag" checked="checked" value="0"/><label for="radio-4">否</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="sleepTime" class="req">选择代理结算商户:</label>
                    </div>
                    <div class="input">&nbsp;&nbsp;
                     <a href="javascript:;" id = "selectStore">请选择</a><br />
                     <form:hidden path="jsStoreIds" value="" />
                    <textarea id="storeName" name="storeName" class="small required" readonly="readonly" ></textarea>
                   	<span class="error" id="advice-required-userPwd" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
								<div class="label">
									<label for="beginTime" class="req">代理开始时间:</label>
								</div>
								<div class="input">
									<input type="text" id="beginTime" name="beginTime"
										class="txt Wdate required" readOnly
										onfocus="WdatePicker({vel:'startTime',dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endTime\')||\'2050-10-01\'}'})" />
										 <input type="hidden" name="startTime" id="startTime" />
								</div>
							</div>
							<div class="field">
								<div class="label">
									<label for="endTime" class="req">代理结束时间:</label>
								</div>
								<div class="input">
									<input type="text" id="endTime" name="endTime" 
										class="txt Wdate required" readOnly
										onfocus="WdatePicker({vel:'stopTime',dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'2050-10-01'})" />
								 		<input type="hidden" name="stopTime" id="stopTime" />
								</div>
							</div>  
                
               
               
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