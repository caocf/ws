<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>

	<head>
		<ht:head />

		<script type="text/javascript">
		$(function (){
			$("#query").click(function(){
				var start = $("#startTime").val();
				var end = $("#endTime").val();
				var url = $("#hUrl").val()+"beginTime="+start+"&endTime="+end;
				
				showDialog("订单异常信息", 
						url,function(doc){},
					{"Width":1000,"Height":1000,"ShowMessageRow":false,"ShowButtonRow":false});
			});
			$("#tquery").click(function(){
				var start = $("#startTime").val();
				var end = $("#endTime").val();
				var url = $("#hUrl").val()+"beginTime="+start+"&endTime="+end;
				//window.location.href=url;
				window.open(url);
				
			});
			//<button type="button" id="query" class="btn btn-primary"
				//data-loading-text="查询中 ...">查询</button>
				//<a class="btn btn-primary" id ="hquery" href="http://112.4.27.9:8085/showException?beginTime=2013-09-10%2016:54:13&endTime=2013-09-10%2023:49:05">查询</a>
			
		});


		


</script>
	</head>
	<body>

		<br />
		<div id="search-menu">
			<ul>
				<ht:menu-btn type="search" />
				<ct:display model="order_list" btn="add_btn">
				</ct:display>
			</ul>
			<br style="clear: left" />
		</div>
		<div>
		<br/>
				<table>
					<tr>
						<td>
							开始时间：
						</td>
						<td>
							<input type="text" name="startTime" id="startTime"  
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" readonly/>
						</td>
						<td>
							结束时间：
						</td>
						<td>
							<input type="text" name="endTime" id="endTime"
        					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" readonly/>
						</td>

						<td colspan="2">
							<a class="btn_blue" href="javascript:;" id="tquery"><span>查 询</span></a>
						</td>
					</tr>
				</table>
		</div>
		<div class="container">
			<br />
			<h3>
			</h3>
			 <div id="exceptionInfo">
			 <input type="hidden" id = "hUrl" value="${exceptionUrl}">
              </div> 

		</div>

	</body>
</html>