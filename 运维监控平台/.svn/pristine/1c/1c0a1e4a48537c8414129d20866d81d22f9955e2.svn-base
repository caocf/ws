<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		//选项卡按钮
		$('button[name=btnTabs]').each(
				function() {
					if ("${subPath}" == $(this).attr("value")) {
						$(this).siblings().removeClass('btn-primary');
						$(this).addClass('btn-primary');
					} else if ("${subPath}" == "dsywWeekly"
							&& $(this).attr("value") == "dsywDaily") {
						$(this).addClass('btn-primary');
					}
				}).click(
				function() {
					$(this).siblings().removeClass('btn-primary');
					$(this).addClass('btn-primary');
					var frm = document.getElementById("toForm");
					frm.action = "${ctx}/businessOverview/daily/"
							+ $(this).attr("value");
					frm.submit();
				});

		//样式
		$('td').each(function() {
			$(this).css("word-break", "keep-all");
		});
		$('.tdTitle').each(function() {
			$(this).css({
				"text-align" : "center",
				"vertical-align" : "middle",
				"background-color" : "#FCFCFC !important"
			});
		});

		//查询按钮
		$('button[name=btnQuery]').click(
				function() {
					var frm = document.getElementById("queryForm");
					frm.action = "${ctx}/businessOverview/daily/"
							+ $(this).attr("value");
					frm.submit();
				});

		//导出按钮
		$('button[name=btnExcel]').click(
				function() {
					var frm = document.getElementById("queryForm");
					frm.action = "${ctx}/businessOverview/daily/toExcel/"
							+ $(this).attr("value");
					frm.submit();
				});

		/**
		 * 生成周期列表
		 * @param yearStr 年份
		 * @param monthStr 月份
		 */
		function createWeekList(yearStr, monthStr) {
			$.ajax({
				type : "post",
				data : {
					"yearStr" : yearStr,
					"monthStr" : monthStr
				},
				url : "${ctx}/businessOverview/daily/weekList",
				dataType : "json",
				success : function(data) {
					var obj = "select[name='weekStr']";
					$(obj).empty();
					for ( var i = 0; i < data.length; i++) {
						$(obj).append(
								"<option value='" + data[i] + "'>" + data[i]
										+ "</option>");
					}
				}
			});
		}

		//年份控制周期联动
		$("select[name='yearStr']").change(function() {
			createWeekList($(this).val(), $("select[name='monthStr']").val());
		});

		//月份控制周期联动
		$("select[name='monthStr']").change(function() {
			createWeekList($("select[name='yearStr']").val(), $(this).val());
		});
	});
</script>
<div class="row-fluid">
	<div class="span12">
		<div style="float: left;">
			<div class="control-group group-search">
				<form id="toForm" name="toForm" class="form-horizontal" method="get">
					<button name="btnTabs" class="btn" value="dzhy">定制会员</button>
					<button name="btnTabs" class="btn" value="zcyh">注册用户</button>
					<button name="btnTabs" class="btn" value="jyqk">交易情况</button>
					<button name="btnTabs" class="btn" value="hyyhsh">活跃用户商户</button>
					<button name="btnTabs" class="btn" value="zdyz">终端验证</button>
					<button name="btnTabs" class="btn" value="khd">客户端</button>
					<button name="btnTabs" class="btn" value="dsywDaily">电商业务</button>
					<button name="btnTabs" class="btn" value="lt">论坛</button>
					<button name="btnTabs" class="btn" value="shspsl">商户商品数量</button>
					<button name="btnTabs" class="btn" value="dxqf">短信群发</button>
					<button name="btnTabs" class="btn" value="dxg">短信购</button>
					<button name="btnTabs" class="btn" value="pvuv">PVUV</button>
				</form>
			</div>
		</div>
	</div>
</div>